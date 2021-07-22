/*
 * Decompiled with CFR 0.150.
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
    private final /* synthetic */ Setting<Double> velocityHeight;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<JumpWhen> jumpIf;
    private final /* synthetic */ Setting<Mode> mode;

    public AutoJump() {
        super(Categories.Movement, "auto-jump", "Automatically jumps.");
        AutoJump lllllllllllllllllIlIIIlIlIIlllll;
        lllllllllllllllllIlIIIlIlIIlllll.sgGeneral = lllllllllllllllllIlIIIlIlIIlllll.settings.getDefaultGroup();
        lllllllllllllllllIlIIIlIlIIlllll.mode = lllllllllllllllllIlIIIlIlIIlllll.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The method of jumping.").defaultValue(Mode.Jump).build());
        lllllllllllllllllIlIIIlIlIIlllll.jumpIf = lllllllllllllllllIlIIIlIlIIlllll.sgGeneral.add(new EnumSetting.Builder().name("jump-if").description("Jump if.").defaultValue(JumpWhen.Always).build());
        lllllllllllllllllIlIIIlIlIIlllll.velocityHeight = lllllllllllllllllIlIIIlIlIIlllll.sgGeneral.add(new DoubleSetting.Builder().name("velocity-height").description("The distance that velocity mode moves you.").defaultValue(0.25).min(0.0).sliderMax(2.0).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIlIIIlIlIIIIIll) {
        AutoJump lllllllllllllllllIlIIIlIlIIIIlII;
        if (!lllllllllllllllllIlIIIlIlIIIIlII.mc.field_1724.method_24828() || lllllllllllllllllIlIIIlIlIIIIlII.mc.field_1724.method_5715() || !lllllllllllllllllIlIIIlIlIIIIlII.jump()) {
            return;
        }
        if (lllllllllllllllllIlIIIlIlIIIIlII.mode.get() == Mode.Jump) {
            lllllllllllllllllIlIIIlIlIIIIlII.mc.field_1724.method_6043();
        } else {
            ((IVec3d)lllllllllllllllllIlIIIlIlIIIIlII.mc.field_1724.method_18798()).setY(lllllllllllllllllIlIIIlIlIIIIlII.velocityHeight.get());
        }
    }

    private boolean jump() {
        AutoJump lllllllllllllllllIlIIIlIlIIIllII;
        switch (lllllllllllllllllIlIIIlIlIIIllII.jumpIf.get()) {
            case Sprinting: {
                return lllllllllllllllllIlIIIlIlIIIllII.mc.field_1724.method_5624() && (lllllllllllllllllIlIIIlIlIIIllII.mc.field_1724.field_6250 != 0.0f || lllllllllllllllllIlIIIlIlIIIllII.mc.field_1724.field_6212 != 0.0f);
            }
            case Walking: {
                return lllllllllllllllllIlIIIlIlIIIllII.mc.field_1724.field_6250 != 0.0f || lllllllllllllllllIlIIIlIlIIIllII.mc.field_1724.field_6212 != 0.0f;
            }
            case Always: {
                return true;
            }
        }
        return false;
    }

    public static enum JumpWhen {
        Sprinting,
        Walking,
        Always;


        private JumpWhen() {
            JumpWhen llllllllllllllllIlIllllIlIlIlIll;
        }
    }

    public static enum Mode {
        Jump,
        LowHop;


        private Mode() {
            Mode lIIIIlllllllI;
        }
    }
}

