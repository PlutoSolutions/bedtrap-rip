/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class AutoJump
extends Module {
    private final Setting<Double> velocityHeight;
    private final SettingGroup sgGeneral;
    private final Setting<JumpWhen> jumpIf;
    private final Setting<Mode> mode;

    public AutoJump() {
        super(Categories.Movement, "auto-jump", "Automatically jumps.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The method of jumping.").defaultValue(Mode.Jump).build());
        this.jumpIf = this.sgGeneral.add(new EnumSetting.Builder().name("jump-if").description("Jump if.").defaultValue(JumpWhen.Always).build());
        this.velocityHeight = this.sgGeneral.add(new DoubleSetting.Builder().name("velocity-height").description("The distance that velocity mode moves you.").defaultValue(0.25).min(0.0).sliderMax(2.0).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (!this.mc.field_1724.method_24828() || this.mc.field_1724.method_5715() || !this.jump()) {
            return;
        }
        if (this.mode.get() == Mode.Jump) {
            this.mc.field_1724.method_6043();
        } else {
            ((IVec3d)this.mc.field_1724.method_18798()).setY(this.velocityHeight.get());
        }
    }

    private boolean jump() {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$movement$AutoJump$JumpWhen[this.jumpIf.get().ordinal()]) {
            case 1: {
                return this.mc.field_1724.method_5624() && (this.mc.field_1724.field_6250 != 0.0f || this.mc.field_1724.field_6212 != 0.0f);
            }
            case 2: {
                return this.mc.field_1724.field_6250 != 0.0f || this.mc.field_1724.field_6212 != 0.0f;
            }
            case 3: {
                return true;
            }
        }
        return false;
    }

    public static enum JumpWhen {
        Sprinting,
        Walking,
        Always;

    }

    public static enum Mode {
        Jump,
        LowHop;

    }
}

