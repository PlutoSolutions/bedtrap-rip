/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_2661
 */
package minegame159.meteorclient.systems.modules.misc;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.DamageCalcUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2661;

public class AutoLog
extends Module {
    private final /* synthetic */ Setting<Boolean> crystalLog;
    private final /* synthetic */ Setting<Boolean> smart;
    private final /* synthetic */ Setting<Boolean> toggleOff;
    private final /* synthetic */ Setting<Boolean> instantDeath;
    private final /* synthetic */ Setting<Integer> health;
    private final /* synthetic */ Setting<Boolean> onlyTrusted;
    private final /* synthetic */ Setting<Boolean> smartToggle;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ StaticListener staticListener;
    private final /* synthetic */ Setting<Integer> range;

    public AutoLog() {
        super(Categories.Combat, "auto-log", "Automatically disconnects you when certain requirements are met.");
        AutoLog llIIlllllllIIlI;
        llIIlllllllIIlI.sgGeneral = llIIlllllllIIlI.settings.getDefaultGroup();
        llIIlllllllIIlI.health = llIIlllllllIIlI.sgGeneral.add(new IntSetting.Builder().name("health").description("Automatically disconnects when health is lower or equal to this value.").defaultValue(6).min(0).max(20).sliderMax(20).build());
        llIIlllllllIIlI.smart = llIIlllllllIIlI.sgGeneral.add(new BoolSetting.Builder().name("smart").description("Disconnects when you're about to take enough damage to kill you.").defaultValue(true).build());
        llIIlllllllIIlI.onlyTrusted = llIIlllllllIIlI.sgGeneral.add(new BoolSetting.Builder().name("only-trusted").description("Disconnects when a player not on your friends list appears in render distance.").defaultValue(false).build());
        llIIlllllllIIlI.instantDeath = llIIlllllllIIlI.sgGeneral.add(new BoolSetting.Builder().name("32K").description("Disconnects when a player near you can instantly kill you.").defaultValue(false).build());
        llIIlllllllIIlI.crystalLog = llIIlllllllIIlI.sgGeneral.add(new BoolSetting.Builder().name("crystal-nearby").description("Disconnects when a crystal appears near you.").defaultValue(false).build());
        llIIlllllllIIlI.range = llIIlllllllIIlI.sgGeneral.add(new IntSetting.Builder().name("range").description("How close a crystal has to be to you before you disconnect.").defaultValue(4).min(1).max(10).sliderMax(5).visible(llIIlllllllIIlI.crystalLog::get).build());
        llIIlllllllIIlI.smartToggle = llIIlllllllIIlI.sgGeneral.add(new BoolSetting.Builder().name("smart-toggle").description("Disables Auto Log after a low-health logout. WILL re-enable once you heal.").defaultValue(false).build());
        llIIlllllllIIlI.toggleOff = llIIlllllllIIlI.sgGeneral.add(new BoolSetting.Builder().name("toggle-off").description("Disables Auto Log after usage.").defaultValue(true).build());
        llIIlllllllIIlI.staticListener = llIIlllllllIIlI.new StaticListener();
    }

    @EventHandler
    private void onTick(TickEvent.Post llIIllllllIllII) {
        AutoLog llIIllllllIllIl;
        if (llIIllllllIllIl.mc.field_1724.method_6032() <= 0.0f) {
            llIIllllllIllIl.toggle();
            return;
        }
        if (llIIllllllIllIl.mc.field_1724.method_6032() <= (float)llIIllllllIllIl.health.get().intValue()) {
            llIIllllllIllIl.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585(String.valueOf(new StringBuilder().append("[AutoLog] Health was lower than ").append(llIIllllllIllIl.health.get()).append(".")))));
            if (llIIllllllIllIl.smartToggle.get().booleanValue()) {
                llIIllllllIllIl.toggle();
                llIIllllllIllIl.enableHealthListener();
            }
        }
        if (llIIllllllIllIl.smart.get().booleanValue() && (double)(llIIllllllIllIl.mc.field_1724.method_6032() + llIIllllllIllIl.mc.field_1724.method_6067()) - PlayerUtils.possibleHealthReductions() < (double)llIIllllllIllIl.health.get().intValue()) {
            llIIllllllIllIl.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585(String.valueOf(new StringBuilder().append("[AutoLog] Health was going to be lower than ").append(llIIllllllIllIl.health.get()).append(".")))));
            if (llIIllllllIllIl.toggleOff.get().booleanValue()) {
                llIIllllllIllIl.toggle();
            }
        }
        for (class_1297 llIIllllllIlllI : llIIllllllIllIl.mc.field_1687.method_18112()) {
            if (llIIllllllIlllI instanceof class_1657 && llIIllllllIlllI.method_5667() != llIIllllllIllIl.mc.field_1724.method_5667()) {
                if (llIIllllllIllIl.onlyTrusted.get().booleanValue() && llIIllllllIlllI != llIIllllllIllIl.mc.field_1724 && !Friends.get().isFriend((class_1657)llIIllllllIlllI)) {
                    llIIllllllIllIl.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585("[AutoLog] A non-trusted player appeared in your render distance.")));
                    if (!llIIllllllIllIl.toggleOff.get().booleanValue()) break;
                    llIIllllllIllIl.toggle();
                    break;
                }
                if (llIIllllllIllIl.mc.field_1724.method_5739(llIIllllllIlllI) < 8.0f && llIIllllllIllIl.instantDeath.get().booleanValue() && DamageCalcUtils.getSwordDamage((class_1657)llIIllllllIlllI, true) > (double)(llIIllllllIllIl.mc.field_1724.method_6032() + llIIllllllIllIl.mc.field_1724.method_6067())) {
                    llIIllllllIllIl.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585("[AutoLog] Anti-32k measures.")));
                    if (!llIIllllllIllIl.toggleOff.get().booleanValue()) break;
                    llIIllllllIllIl.toggle();
                    break;
                }
            }
            if (!(llIIllllllIlllI instanceof class_1511) || !(llIIllllllIllIl.mc.field_1724.method_5739(llIIllllllIlllI) < (float)llIIllllllIllIl.range.get().intValue()) || !llIIllllllIllIl.crystalLog.get().booleanValue()) continue;
            llIIllllllIllIl.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585("[AutoLog] End Crystal appeared within specified range.")));
            if (!llIIllllllIllIl.toggleOff.get().booleanValue()) continue;
            llIIllllllIllIl.toggle();
        }
    }

    private void disableHealthListener() {
        AutoLog llIIllllllIIlII;
        MeteorClient.EVENT_BUS.unsubscribe(llIIllllllIIlII.staticListener);
    }

    private void enableHealthListener() {
        AutoLog llIIllllllIIlll;
        MeteorClient.EVENT_BUS.subscribe(llIIllllllIIlll.staticListener);
    }

    private class StaticListener {
        @EventHandler
        private void healthListener(TickEvent.Post llIIllIIlIIlIII) {
            StaticListener llIIllIIlIIlIIl;
            if (llIIllIIlIIlIIl.AutoLog.this.isActive()) {
                llIIllIIlIIlIIl.AutoLog.this.disableHealthListener();
            } else if (Utils.canUpdate() && !((AutoLog)llIIllIIlIIlIIl.AutoLog.this).mc.field_1724.method_29504() && ((AutoLog)llIIllIIlIIlIIl.AutoLog.this).mc.field_1724.method_6032() >= (float)((Integer)llIIllIIlIIlIIl.AutoLog.this.health.get()).intValue()) {
                llIIllIIlIIlIIl.AutoLog.this.toggle();
                llIIllIIlIIlIIl.AutoLog.this.disableHealthListener();
            }
        }

        private StaticListener() {
            StaticListener llIIllIIlIIllIl;
        }
    }
}

