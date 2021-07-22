/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  baritone.api.pathing.goals.Goal
 *  net.minecraft.class_304
 */
package minegame159.meteorclient.systems.modules.movement;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.Goal;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.input.Input;
import minegame159.meteorclient.utils.world.GoalDirection;
import net.minecraft.class_304;

public class AutoWalk
extends Module {
    private /* synthetic */ GoalDirection goal;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ int timer;
    private final /* synthetic */ Setting<Direction> direction;
    private final /* synthetic */ Setting<Mode> mode;

    private void setPressed(class_304 llllIIIlIIlIlIl, boolean llllIIIlIIlIIlI) {
        llllIIIlIIlIlIl.method_23481(llllIIIlIIlIIlI);
        Input.setKeyState(llllIIIlIIlIlIl, llllIIIlIIlIIlI);
    }

    @EventHandler(priority=100)
    private void onTick(TickEvent.Pre llllIIIlIIlllIl) {
        AutoWalk llllIIIlIIlllII;
        if (llllIIIlIIlllII.mode.get() == Mode.Simple) {
            switch (llllIIIlIIlllII.direction.get()) {
                case Forwards: {
                    llllIIIlIIlllII.setPressed(llllIIIlIIlllII.mc.field_1690.field_1894, true);
                    break;
                }
                case Backwards: {
                    llllIIIlIIlllII.setPressed(llllIIIlIIlllII.mc.field_1690.field_1881, true);
                    break;
                }
                case Left: {
                    llllIIIlIIlllII.setPressed(llllIIIlIIlllII.mc.field_1690.field_1913, true);
                    break;
                }
                case Right: {
                    llllIIIlIIlllII.setPressed(llllIIIlIIlllII.mc.field_1690.field_1849, true);
                }
            }
        } else {
            if (llllIIIlIIlllII.timer > 20) {
                llllIIIlIIlllII.timer = 0;
                llllIIIlIIlllII.goal.recalculate(llllIIIlIIlllII.mc.field_1724.method_19538());
            }
            ++llllIIIlIIlllII.timer;
        }
    }

    private void unpress() {
        AutoWalk llllIIIlIIllIIl;
        llllIIIlIIllIIl.setPressed(llllIIIlIIllIIl.mc.field_1690.field_1894, false);
        llllIIIlIIllIIl.setPressed(llllIIIlIIllIIl.mc.field_1690.field_1881, false);
        llllIIIlIIllIIl.setPressed(llllIIIlIIllIIl.mc.field_1690.field_1913, false);
        llllIIIlIIllIIl.setPressed(llllIIIlIIllIIl.mc.field_1690.field_1849, false);
    }

    public AutoWalk() {
        super(Categories.Movement, "auto-walk", "Automatically walks forward.");
        AutoWalk llllIIIlIlIIllI;
        llllIIIlIlIIllI.sgGeneral = llllIIIlIlIIllI.settings.getDefaultGroup();
        llllIIIlIlIIllI.mode = llllIIIlIlIIllI.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Walking mode.").defaultValue(Mode.Smart).onChanged(llllIIIlIIIIlII -> {
            AutoWalk llllIIIlIIIIIll;
            if (llllIIIlIIIIIll.isActive()) {
                if (llllIIIlIIIIlII == Mode.Simple) {
                    BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
                    llllIIIlIIIIIll.goal = null;
                } else {
                    llllIIIlIIIIIll.timer = 0;
                    llllIIIlIIIIIll.createGoal();
                }
                llllIIIlIIIIIll.unpress();
            }
        }).build());
        llllIIIlIlIIllI.direction = llllIIIlIlIIllI.sgGeneral.add(new EnumSetting.Builder().name("simple-direction").description("The direction to walk in simple mode.").defaultValue(Direction.Forwards).onChanged(llllIIIlIIIlIIl -> {
            AutoWalk llllIIIlIIIlIlI;
            if (llllIIIlIIIlIlI.isActive()) {
                llllIIIlIIIlIlI.unpress();
            }
        }).visible(() -> {
            AutoWalk llllIIIlIIIllII;
            return llllIIIlIIIllII.mode.get() == Mode.Simple;
        }).build());
        llllIIIlIlIIllI.timer = 0;
    }

    private void createGoal() {
        AutoWalk llllIIIlIIlIIII;
        llllIIIlIIlIIII.timer = 0;
        llllIIIlIIlIIII.goal = new GoalDirection(llllIIIlIIlIIII.mc.field_1724.method_19538(), llllIIIlIIlIIII.mc.field_1724.field_6031);
        BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)llllIIIlIIlIIII.goal);
    }

    @Override
    public void onActivate() {
        AutoWalk llllIIIlIlIIlII;
        if (llllIIIlIlIIlII.mode.get() == Mode.Smart) {
            llllIIIlIlIIlII.createGoal();
        }
    }

    @Override
    public void onDeactivate() {
        AutoWalk llllIIIlIlIIIII;
        if (llllIIIlIlIIIII.mode.get() == Mode.Simple) {
            llllIIIlIlIIIII.unpress();
        } else {
            BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
        }
        llllIIIlIlIIIII.goal = null;
    }

    public static enum Direction {
        Forwards,
        Backwards,
        Left,
        Right;


        private Direction() {
            Direction lIlIIIIlIIllIl;
        }
    }

    public static enum Mode {
        Simple,
        Smart;


        private Mode() {
            Mode lllllllllllllllllllllIlIlIIlIlIl;
        }
    }
}

