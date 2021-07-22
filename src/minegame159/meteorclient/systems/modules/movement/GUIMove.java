/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1761
 *  net.minecraft.class_408
 *  net.minecraft.class_463
 *  net.minecraft.class_471
 *  net.minecraft.class_481
 *  net.minecraft.class_497
 *  net.minecraft.class_498
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.mixin.CreativeInventoryScreenAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.input.Input;
import net.minecraft.class_1761;
import net.minecraft.class_408;
import net.minecraft.class_463;
import net.minecraft.class_471;
import net.minecraft.class_481;
import net.minecraft.class_497;
import net.minecraft.class_498;

public class GUIMove
extends Module {
    private final Setting<Boolean> sprint;
    private final Setting<Screens> screens;
    private final Setting<Boolean> arrowsRotate;
    private final Setting<Boolean> jump;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> sneak;
    private final Setting<Double> rotateSpeed;

    public GUIMove() {
        super(Categories.Movement, "gui-move", "Allows you to perform various actions while in GUIs.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.screens = this.sgGeneral.add(new EnumSetting.Builder().name("gUIs").description("Which GUIs to move in.").defaultValue(Screens.Inventory).build());
        this.jump = this.sgGeneral.add(new BoolSetting.Builder().name("jump").description("Allows you to jump while in GUIs.").defaultValue(true).onChanged(this::lambda$new$0).build());
        this.sneak = this.sgGeneral.add(new BoolSetting.Builder().name("sneak").description("Allows you to sneak while in GUIs.").defaultValue(true).onChanged(this::lambda$new$1).build());
        this.sprint = this.sgGeneral.add(new BoolSetting.Builder().name("sprint").description("Allows you to sprint while in GUIs.").defaultValue(true).onChanged(this::lambda$new$2).build());
        this.arrowsRotate = this.sgGeneral.add(new BoolSetting.Builder().name("arrows-rotate").description("Allows you to use your arrow keys to rotate while in GUIs.").defaultValue(true).build());
        this.rotateSpeed = this.sgGeneral.add(new DoubleSetting.Builder().name("rotate-speed").description("Rotation speed while in GUIs.").defaultValue(4.0).min(0.0).build());
    }

    private void lambda$new$0(Boolean bl) {
        if (this.isActive() && !bl.booleanValue()) {
            this.mc.field_1690.field_1903.method_23481(false);
        }
    }

    @Override
    public void onDeactivate() {
        this.mc.field_1690.field_1894.method_23481(false);
        this.mc.field_1690.field_1881.method_23481(false);
        this.mc.field_1690.field_1913.method_23481(false);
        this.mc.field_1690.field_1849.method_23481(false);
        if (this.jump.get().booleanValue()) {
            this.mc.field_1690.field_1903.method_23481(false);
        }
        if (this.sneak.get().booleanValue()) {
            this.mc.field_1690.field_1832.method_23481(false);
        }
        if (this.sprint.get().booleanValue()) {
            this.mc.field_1690.field_1867.method_23481(false);
        }
    }

    private boolean skip() {
        return this.mc.field_1755 == null || Modules.get().isActive(Freecam.class) || this.mc.field_1755 instanceof class_481 && ((CreativeInventoryScreenAccessor)this.mc.field_1755).getSelectedTab() == class_1761.field_7915.method_7741() || this.mc.field_1755 instanceof class_408 || this.mc.field_1755 instanceof class_498 || this.mc.field_1755 instanceof class_471 || this.mc.field_1755 instanceof class_463 || this.mc.field_1755 instanceof class_497;
    }

    private void lambda$new$1(Boolean bl) {
        if (this.isActive() && !bl.booleanValue()) {
            this.mc.field_1690.field_1832.method_23481(false);
        }
    }

    private void lambda$new$2(Boolean bl) {
        if (this.isActive() && !bl.booleanValue()) {
            this.mc.field_1690.field_1867.method_23481(false);
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.skip()) {
            return;
        }
        if (this.screens.get() == Screens.GUI && !(this.mc.field_1755 instanceof WidgetScreen)) {
            return;
        }
        if (this.screens.get() == Screens.Inventory && this.mc.field_1755 instanceof WidgetScreen) {
            return;
        }
        this.mc.field_1690.field_1894.method_23481(Input.isPressed(this.mc.field_1690.field_1894));
        this.mc.field_1690.field_1881.method_23481(Input.isPressed(this.mc.field_1690.field_1881));
        this.mc.field_1690.field_1913.method_23481(Input.isPressed(this.mc.field_1690.field_1913));
        this.mc.field_1690.field_1849.method_23481(Input.isPressed(this.mc.field_1690.field_1849));
        if (this.jump.get().booleanValue()) {
            this.mc.field_1690.field_1903.method_23481(Input.isPressed(this.mc.field_1690.field_1903));
        }
        if (this.sneak.get().booleanValue()) {
            this.mc.field_1690.field_1832.method_23481(Input.isPressed(this.mc.field_1690.field_1832));
        }
        if (this.sprint.get().booleanValue()) {
            this.mc.field_1690.field_1867.method_23481(Input.isPressed(this.mc.field_1690.field_1867));
        }
        if (this.arrowsRotate.get().booleanValue()) {
            int n = 0;
            while ((double)n < this.rotateSpeed.get() * 2.0) {
                if (Input.isKeyPressed(263)) {
                    this.mc.field_1724.field_6031 = (float)((double)this.mc.field_1724.field_6031 - 0.5);
                }
                if (Input.isKeyPressed(262)) {
                    this.mc.field_1724.field_6031 = (float)((double)this.mc.field_1724.field_6031 + 0.5);
                }
                if (Input.isKeyPressed(265)) {
                    this.mc.field_1724.field_5965 = (float)((double)this.mc.field_1724.field_5965 - 0.5);
                }
                if (Input.isKeyPressed(264)) {
                    this.mc.field_1724.field_5965 = (float)((double)this.mc.field_1724.field_5965 + 0.5);
                }
                ++n;
                if (2 >= 0) continue;
                return;
            }
            this.mc.field_1724.field_5965 = Utils.clamp(this.mc.field_1724.field_5965, -90.0f, 90.0f);
        }
    }

    public static enum Screens {
        GUI,
        Inventory,
        Both;

    }
}

