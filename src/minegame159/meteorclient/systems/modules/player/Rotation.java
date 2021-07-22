/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class Rotation
extends Module {
    private final /* synthetic */ SettingGroup sgYaw;
    private final /* synthetic */ Setting<LockMode> yawLockMode;
    private final /* synthetic */ SettingGroup sgPitch;
    private final /* synthetic */ Setting<Double> pitchAngle;
    private final /* synthetic */ Setting<Double> yawAngle;
    private final /* synthetic */ Setting<LockMode> pitchLockMode;

    private float getSmartPitchDirection() {
        Rotation llIIlIlIIIIlIII;
        return (float)Math.round((llIIlIlIIIIlIII.mc.field_1724.field_5965 + 1.0f) / 30.0f) * 30.0f;
    }

    private float getSmartYawDirection() {
        Rotation llIIlIlIIIIlIll;
        return (float)Math.round((llIIlIlIIIIlIll.mc.field_1724.field_6031 + 1.0f) / 45.0f) * 45.0f;
    }

    @EventHandler
    private void onTick(TickEvent.Post llIIlIlIIIIlllI) {
        Rotation llIIlIlIIIIllIl;
        switch (llIIlIlIIIIllIl.yawLockMode.get()) {
            case Simple: {
                llIIlIlIIIIllIl.setYawAngle(llIIlIlIIIIllIl.yawAngle.get().floatValue());
                break;
            }
            case Smart: {
                llIIlIlIIIIllIl.setYawAngle(llIIlIlIIIIllIl.getSmartYawDirection());
            }
        }
        switch (llIIlIlIIIIllIl.pitchLockMode.get()) {
            case Simple: {
                llIIlIlIIIIllIl.mc.field_1724.field_5965 = llIIlIlIIIIllIl.pitchAngle.get().floatValue();
                break;
            }
            case Smart: {
                llIIlIlIIIIllIl.mc.field_1724.field_5965 = llIIlIlIIIIllIl.getSmartPitchDirection();
            }
        }
    }

    private void setYawAngle(float llIIlIlIIIIIIll) {
        llIIlIlIIIIIIlI.mc.field_1724.field_6031 = llIIlIlIIIIIIll;
        llIIlIlIIIIIIlI.mc.field_1724.field_6241 = llIIlIlIIIIIIll;
        llIIlIlIIIIIIlI.mc.field_1724.field_6283 = llIIlIlIIIIIIll;
    }

    public Rotation() {
        super(Categories.Player, "rotation", "Changes/locks your yaw and pitch.");
        Rotation llIIlIlIIIlIlIl;
        llIIlIlIIIlIlIl.sgYaw = llIIlIlIIIlIlIl.settings.createGroup("Yaw");
        llIIlIlIIIlIlIl.sgPitch = llIIlIlIIIlIlIl.settings.createGroup("Pitch");
        llIIlIlIIIlIlIl.yawLockMode = llIIlIlIIIlIlIl.sgYaw.add(new EnumSetting.Builder().name("yaw-lock-mode").description("The way in which your yaw is locked.").defaultValue(LockMode.Simple).build());
        llIIlIlIIIlIlIl.yawAngle = llIIlIlIIIlIlIl.sgYaw.add(new DoubleSetting.Builder().name("yaw-angle").description("Yaw angle in degrees.").defaultValue(0.0).sliderMax(360.0).max(360.0).build());
        llIIlIlIIIlIlIl.pitchLockMode = llIIlIlIIIlIlIl.sgPitch.add(new EnumSetting.Builder().name("pitch-lock-mode").description("The way in which your pitch is locked.").defaultValue(LockMode.Simple).build());
        llIIlIlIIIlIlIl.pitchAngle = llIIlIlIIIlIlIl.sgPitch.add(new DoubleSetting.Builder().name("pitch-angle").description("Pitch angle in degrees.").defaultValue(0.0).min(-90.0).max(90.0).sliderMin(-90.0).sliderMax(90.0).build());
    }

    @Override
    public void onActivate() {
        Rotation llIIlIlIIIlIIIl;
        llIIlIlIIIlIIIl.onTick(null);
    }

    public static enum LockMode {
        Smart,
        Simple,
        None;


        private LockMode() {
            LockMode lllllllllllllllllIllIIIlIIlIIIII;
        }
    }
}

