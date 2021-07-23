/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.world;

import java.util.concurrent.ThreadLocalRandom;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import minegame159.meteorclient.utils.player.ChatUtils;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1703;
import net.minecraft.class_1707;

public class AutoSteal
extends Module {
    private final Setting<Boolean> stealButtonEnabled;
    private final Setting<Boolean> autoStealEnabled;
    private final Setting<Boolean> dumpButtonEnabled;
    private final Setting<Boolean> autoDumpEnabled;
    private final SettingGroup sgDelays;
    private final Setting<Integer> minimumDelay;
    private final Setting<Integer> randomDelay;
    private final SettingGroup sgGeneral;

    public AutoSteal() {
        super(Categories.World, "auto-steal", "Automatically dumps or steals from storage blocks.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgDelays = this.settings.createGroup("Delay");
        this.stealButtonEnabled = this.sgGeneral.add(new BoolSetting.Builder().name("steal-button-enabled").description("Shows the Steal button on the container screen.").defaultValue(true).build());
        this.autoStealEnabled = this.sgGeneral.add(new BoolSetting.Builder().name("auto-steal-enabled").description("Starts the auto steal when a container open.").defaultValue(false).onChanged(this::lambda$new$0).build());
        this.dumpButtonEnabled = this.sgGeneral.add(new BoolSetting.Builder().name("dump-button-enabled").description("Shows the Dump button on the container screen.").defaultValue(true).build());
        this.autoDumpEnabled = this.sgGeneral.add(new BoolSetting.Builder().name("auto-dump-enabled").description("Start auto dump when a container opens.").defaultValue(false).onChanged(this::lambda$new$1).build());
        this.minimumDelay = this.sgDelays.add(new IntSetting.Builder().name("min-delay").description("The minimum delay between stealing the next stack in milliseconds.").sliderMax(1000).defaultValue(180).build());
        this.randomDelay = this.sgDelays.add(new IntSetting.Builder().name("random-delay").description("Randomly adds a delay of up to the specified time in milliseconds. Helps avoid anti-cheats.").min(0).sliderMax(1000).defaultValue(50).build());
    }

    private void steal(class_1703 class_17032) {
        this.moveSlots(class_17032, 0, this.getRows(class_17032) * 9);
    }

    private void lambda$new$0(Boolean bl) {
        this.checkAutoSettings();
    }

    public boolean getAutoDumpEnabled() {
        return this.autoDumpEnabled.get();
    }

    public void stealAsync(class_1703 class_17032) {
        MeteorExecutor.execute(() -> this.lambda$stealAsync$2(class_17032));
    }

    private int getSleepTime() {
        return this.minimumDelay.get() + (this.randomDelay.get() > 0 ? ThreadLocalRandom.current().nextInt(0, this.randomDelay.get()) : 0);
    }

    public boolean getAutoStealEnabled() {
        return this.autoStealEnabled.get();
    }

    private void lambda$dumpAsync$3(class_1703 class_17032) {
        this.dump(class_17032);
    }

    private void lambda$new$1(Boolean bl) {
        this.checkAutoSettings();
    }

    private void dump(class_1703 class_17032) {
        int n = this.getRows(class_17032) * 9;
        this.moveSlots(class_17032, n, n + 36);
    }

    public void dumpAsync(class_1703 class_17032) {
        MeteorExecutor.execute(() -> this.lambda$dumpAsync$3(class_17032));
    }

    public boolean getDumpButtonEnabled() {
        return this.dumpButtonEnabled.get();
    }

    private void lambda$stealAsync$2(class_1703 class_17032) {
        this.steal(class_17032);
    }

    private void checkAutoSettings() {
        if (this.autoStealEnabled.get().booleanValue() && this.autoDumpEnabled.get().booleanValue()) {
            ChatUtils.error("You can't enable Auto Steal and Auto Dump at the same time!", new Object[0]);
            this.autoDumpEnabled.set(false);
        }
    }

    private int getRows(class_1703 class_17032) {
        return class_17032 instanceof class_1707 ? ((class_1707)class_17032).method_17388() : 3;
    }

    private void moveSlots(class_1703 class_17032, int n, int n2) {
        for (int i = n; i < n2; ++i) {
            if (!class_17032.method_7611(i).method_7681()) continue;
            int n3 = this.getSleepTime();
            if (n3 > 0) {
                try {
                    Thread.sleep(n3);
                }
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            if (this.mc.field_1755 == null) break;
            InvUtils.quickMove().slotId(i);
            if (true) continue;
            return;
        }
    }

    public boolean getStealButtonEnabled() {
        return this.stealButtonEnabled.get();
    }
}

