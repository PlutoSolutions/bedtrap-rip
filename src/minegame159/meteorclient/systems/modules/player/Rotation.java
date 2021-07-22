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
    private final SettingGroup sgYaw;
    private final Setting<LockMode> yawLockMode;
    private final SettingGroup sgPitch;
    private final Setting<Double> pitchAngle;
    private final Setting<Double> yawAngle;
    private final Setting<LockMode> pitchLockMode;

    private float getSmartPitchDirection() {
        return (float)Math.round((this.mc.field_1724.field_5965 + 1.0f) / 30.0f) * 30.0f;
    }

    private float getSmartYawDirection() {
        return (float)Math.round((this.mc.field_1724.field_6031 + 1.0f) / 45.0f) * 45.0f;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$player$Rotation$LockMode[this.yawLockMode.get().ordinal()]) {
            case 1: {
                this.setYawAngle(this.yawAngle.get().floatValue());
                break;
            }
            case 2: {
                this.setYawAngle(this.getSmartYawDirection());
            }
        }
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$player$Rotation$LockMode[this.pitchLockMode.get().ordinal()]) {
            case 1: {
                this.mc.field_1724.field_5965 = this.pitchAngle.get().floatValue();
                break;
            }
            case 2: {
                this.mc.field_1724.field_5965 = this.getSmartPitchDirection();
            }
        }
    }

    private void setYawAngle(float f) {
        this.mc.field_1724.field_6031 = f;
        this.mc.field_1724.field_6241 = f;
        this.mc.field_1724.field_6283 = f;
    }

    public Rotation() {
        super(Categories.Player, "rotation", "Changes/locks your yaw and pitch.");
        this.sgYaw = this.settings.createGroup("Yaw");
        this.sgPitch = this.settings.createGroup("Pitch");
        this.yawLockMode = this.sgYaw.add(new EnumSetting.Builder().name("yaw-lock-mode").description("The way in which your yaw is locked.").defaultValue(LockMode.Simple).build());
        this.yawAngle = this.sgYaw.add(new DoubleSetting.Builder().name("yaw-angle").description("Yaw angle in degrees.").defaultValue(0.0).sliderMax(360.0).max(360.0).build());
        this.pitchLockMode = this.sgPitch.add(new EnumSetting.Builder().name("pitch-lock-mode").description("The way in which your pitch is locked.").defaultValue(LockMode.Simple).build());
        this.pitchAngle = this.sgPitch.add(new DoubleSetting.Builder().name("pitch-angle").description("Pitch angle in degrees.").defaultValue(0.0).min(-90.0).max(90.0).sliderMin(-90.0).sliderMax(90.0).build());
    }

    @Override
    public void onActivate() {
        this.onTick(null);
    }

    public static enum LockMode {
        Smart,
        Simple,
        None;

    }
}

