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
    private GoalDirection goal;
    private final SettingGroup sgGeneral;
    private int timer;
    private final Setting<Direction> direction;
    private final Setting<Mode> mode;

    private void lambda$new$0(Mode mode) {
        if (this.isActive()) {
            if (mode == Mode.Simple) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
                this.goal = null;
            } else {
                this.timer = 0;
                this.createGoal();
            }
            this.unpress();
        }
    }

    private void setPressed(class_304 class_3042, boolean bl) {
        class_3042.method_23481(bl);
        Input.setKeyState(class_3042, bl);
    }

    @EventHandler(priority=100)
    private void onTick(TickEvent.Pre pre) {
        if (this.mode.get() == Mode.Simple) {
            switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$movement$AutoWalk$Direction[this.direction.get().ordinal()]) {
                case 1: {
                    this.setPressed(this.mc.field_1690.field_1894, true);
                    break;
                }
                case 2: {
                    this.setPressed(this.mc.field_1690.field_1881, true);
                    break;
                }
                case 3: {
                    this.setPressed(this.mc.field_1690.field_1913, true);
                    break;
                }
                case 4: {
                    this.setPressed(this.mc.field_1690.field_1849, true);
                }
            }
        } else {
            if (this.timer > 20) {
                this.timer = 0;
                this.goal.recalculate(this.mc.field_1724.method_19538());
            }
            ++this.timer;
        }
    }

    private void unpress() {
        this.setPressed(this.mc.field_1690.field_1894, false);
        this.setPressed(this.mc.field_1690.field_1881, false);
        this.setPressed(this.mc.field_1690.field_1913, false);
        this.setPressed(this.mc.field_1690.field_1849, false);
    }

    public AutoWalk() {
        super(Categories.Movement, "auto-walk", "Automatically walks forward.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Walking mode.").defaultValue(Mode.Smart).onChanged(this::lambda$new$0).build());
        this.direction = this.sgGeneral.add(new EnumSetting.Builder().name("simple-direction").description("The direction to walk in simple mode.").defaultValue(Direction.Forwards).onChanged(this::lambda$new$1).visible(this::lambda$new$2).build());
        this.timer = 0;
    }

    private void lambda$new$1(Direction direction) {
        if (this.isActive()) {
            this.unpress();
        }
    }

    private boolean lambda$new$2() {
        return this.mode.get() == Mode.Simple;
    }

    private void createGoal() {
        this.timer = 0;
        this.goal = new GoalDirection(this.mc.field_1724.method_19538(), this.mc.field_1724.field_6031);
        BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)this.goal);
    }

    @Override
    public void onActivate() {
        if (this.mode.get() == Mode.Smart) {
            this.createGoal();
        }
    }

    @Override
    public void onDeactivate() {
        if (this.mode.get() == Mode.Simple) {
            this.unpress();
        } else {
            BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
        }
        this.goal = null;
    }

    public static enum Direction {
        Forwards,
        Backwards,
        Left,
        Right;

    }

    public static enum Mode {
        Simple,
        Smart;

    }
}

