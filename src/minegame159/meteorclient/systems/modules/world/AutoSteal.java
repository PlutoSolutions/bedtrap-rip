/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1703
 *  net.minecraft.class_1707
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
    private final /* synthetic */ Setting<Boolean> stealButtonEnabled;
    private final /* synthetic */ Setting<Boolean> autoStealEnabled;
    private final /* synthetic */ Setting<Boolean> dumpButtonEnabled;
    private final /* synthetic */ Setting<Boolean> autoDumpEnabled;
    private final /* synthetic */ SettingGroup sgDelays;
    private final /* synthetic */ Setting<Integer> minimumDelay;
    private final /* synthetic */ Setting<Integer> randomDelay;
    private final /* synthetic */ SettingGroup sgGeneral;

    public AutoSteal() {
        super(Categories.World, "auto-steal", "Automatically dumps or steals from storage blocks.");
        AutoSteal llllllllllllllllllIllIIllIlIIIIl;
        llllllllllllllllllIllIIllIlIIIIl.sgGeneral = llllllllllllllllllIllIIllIlIIIIl.settings.getDefaultGroup();
        llllllllllllllllllIllIIllIlIIIIl.sgDelays = llllllllllllllllllIllIIllIlIIIIl.settings.createGroup("Delay");
        llllllllllllllllllIllIIllIlIIIIl.stealButtonEnabled = llllllllllllllllllIllIIllIlIIIIl.sgGeneral.add(new BoolSetting.Builder().name("steal-button-enabled").description("Shows the Steal button on the container screen.").defaultValue(true).build());
        llllllllllllllllllIllIIllIlIIIIl.autoStealEnabled = llllllllllllllllllIllIIllIlIIIIl.sgGeneral.add(new BoolSetting.Builder().name("auto-steal-enabled").description("Starts the auto steal when a container open.").defaultValue(false).onChanged(llllllllllllllllllIllIIlIIllIlll -> {
            AutoSteal llllllllllllllllllIllIIlIIllIllI;
            llllllllllllllllllIllIIlIIllIllI.checkAutoSettings();
        }).build());
        llllllllllllllllllIllIIllIlIIIIl.dumpButtonEnabled = llllllllllllllllllIllIIllIlIIIIl.sgGeneral.add(new BoolSetting.Builder().name("dump-button-enabled").description("Shows the Dump button on the container screen.").defaultValue(true).build());
        llllllllllllllllllIllIIllIlIIIIl.autoDumpEnabled = llllllllllllllllllIllIIllIlIIIIl.sgGeneral.add(new BoolSetting.Builder().name("auto-dump-enabled").description("Start auto dump when a container opens.").defaultValue(false).onChanged(llllllllllllllllllIllIIlIIlllIll -> {
            AutoSteal llllllllllllllllllIllIIlIIlllIlI;
            llllllllllllllllllIllIIlIIlllIlI.checkAutoSettings();
        }).build());
        llllllllllllllllllIllIIllIlIIIIl.minimumDelay = llllllllllllllllllIllIIllIlIIIIl.sgDelays.add(new IntSetting.Builder().name("min-delay").description("The minimum delay between stealing the next stack in milliseconds.").sliderMax(1000).defaultValue(180).build());
        llllllllllllllllllIllIIllIlIIIIl.randomDelay = llllllllllllllllllIllIIllIlIIIIl.sgDelays.add(new IntSetting.Builder().name("random-delay").description("Randomly adds a delay of up to the specified time in milliseconds. Helps avoid anti-cheats.").min(0).sliderMax(1000).defaultValue(50).build());
    }

    private void steal(class_1703 llllllllllllllllllIllIIlIllIlIll) {
        AutoSteal llllllllllllllllllIllIIlIllIlllI;
        llllllllllllllllllIllIIlIllIlllI.moveSlots(llllllllllllllllllIllIIlIllIlIll, 0, llllllllllllllllllIllIIlIllIlllI.getRows(llllllllllllllllllIllIIlIllIlIll) * 9);
    }

    public boolean getAutoDumpEnabled() {
        AutoSteal llllllllllllllllllIllIIlIlIIlIlI;
        return llllllllllllllllllIllIIlIlIIlIlI.autoDumpEnabled.get();
    }

    public void stealAsync(class_1703 llllllllllllllllllIllIIlIlIlllII) {
        AutoSteal llllllllllllllllllIllIIlIlIlllIl;
        MeteorExecutor.execute(() -> {
            AutoSteal llllllllllllllllllIllIIlIlIIIIIl;
            llllllllllllllllllIllIIlIlIIIIIl.steal(llllllllllllllllllIllIIlIlIlllII);
        });
    }

    private int getSleepTime() {
        AutoSteal llllllllllllllllllIllIIllIIIlllI;
        return llllllllllllllllllIllIIllIIIlllI.minimumDelay.get() + (llllllllllllllllllIllIIllIIIlllI.randomDelay.get() > 0 ? ThreadLocalRandom.current().nextInt(0, llllllllllllllllllIllIIllIIIlllI.randomDelay.get()) : 0);
    }

    public boolean getAutoStealEnabled() {
        AutoSteal llllllllllllllllllIllIIlIlIIllIl;
        return llllllllllllllllllIllIIlIlIIllIl.autoStealEnabled.get();
    }

    private void dump(class_1703 llllllllllllllllllIllIIlIllIIllI) {
        AutoSteal llllllllllllllllllIllIIlIllIIlII;
        int llllllllllllllllllIllIIlIllIIlIl = llllllllllllllllllIllIIlIllIIlII.getRows(llllllllllllllllllIllIIlIllIIllI) * 9;
        llllllllllllllllllIllIIlIllIIlII.moveSlots(llllllllllllllllllIllIIlIllIIllI, llllllllllllllllllIllIIlIllIIlIl, llllllllllllllllllIllIIlIllIIlIl + 36);
    }

    public void dumpAsync(class_1703 llllllllllllllllllIllIIlIlIllIII) {
        AutoSteal llllllllllllllllllIllIIlIlIllIIl;
        MeteorExecutor.execute(() -> {
            AutoSteal llllllllllllllllllIllIIlIlIIIlll;
            llllllllllllllllllIllIIlIlIIIlll.dump(llllllllllllllllllIllIIlIlIllIII);
        });
    }

    public boolean getDumpButtonEnabled() {
        AutoSteal llllllllllllllllllIllIIlIlIlIIII;
        return llllllllllllllllllIllIIlIlIlIIII.dumpButtonEnabled.get();
    }

    private void checkAutoSettings() {
        AutoSteal llllllllllllllllllIllIIllIIllIlI;
        if (llllllllllllllllllIllIIllIIllIlI.autoStealEnabled.get().booleanValue() && llllllllllllllllllIllIIllIIllIlI.autoDumpEnabled.get().booleanValue()) {
            ChatUtils.error("You can't enable Auto Steal and Auto Dump at the same time!", new Object[0]);
            llllllllllllllllllIllIIllIIllIlI.autoDumpEnabled.set(false);
        }
    }

    private int getRows(class_1703 llllllllllllllllllIllIIllIIIIllI) {
        return llllllllllllllllllIllIIllIIIIllI instanceof class_1707 ? ((class_1707)llllllllllllllllllIllIIllIIIIllI).method_17388() : 3;
    }

    private void moveSlots(class_1703 llllllllllllllllllIllIIlIllllIlI, int llllllllllllllllllIllIIlIllllIIl, int llllllllllllllllllIllIIlIllllIII) {
        for (int llllllllllllllllllIllIIlIlllllII = llllllllllllllllllIllIIlIllllIIl; llllllllllllllllllIllIIlIlllllII < llllllllllllllllllIllIIlIllllIII; ++llllllllllllllllllIllIIlIlllllII) {
            AutoSteal llllllllllllllllllIllIIlIlllIlll;
            if (!llllllllllllllllllIllIIlIllllIlI.method_7611(llllllllllllllllllIllIIlIlllllII).method_7681()) continue;
            int llllllllllllllllllIllIIlIlllllIl = llllllllllllllllllIllIIlIlllIlll.getSleepTime();
            if (llllllllllllllllllIllIIlIlllllIl > 0) {
                try {
                    Thread.sleep(llllllllllllllllllIllIIlIlllllIl);
                }
                catch (InterruptedException llllllllllllllllllIllIIlIllllllI) {
                    llllllllllllllllllIllIIlIllllllI.printStackTrace();
                }
            }
            if (llllllllllllllllllIllIIlIlllIlll.mc.field_1755 == null) break;
            InvUtils.quickMove().slotId(llllllllllllllllllIllIIlIlllllII);
        }
    }

    public boolean getStealButtonEnabled() {
        AutoSteal llllllllllllllllllIllIIlIlIlIIll;
        return llllllllllllllllllIllIIlIlIlIIll.stealButtonEnabled.get();
    }
}

