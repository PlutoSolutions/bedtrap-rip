/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.input.Input;
import net.minecraft.class_5498;

public class FreeLook
extends Module {
    private final SettingGroup sgGeneral;
    public float cameraPitch;
    public final Setting<Double> sensitivity;
    private class_5498 prePers;
    private final SettingGroup sgArrows;
    public float cameraYaw;
    public final Setting<Mode> mode;
    public final Setting<Boolean> arrows;
    private final Setting<Double> arrowSpeed;
    public final Setting<Boolean> togglePerpective;

    public FreeLook() {
        super(Categories.Render, "free-look", "Allows more rotation options in third person.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgArrows = this.settings.createGroup("Arrows");
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Which entity to rotate.").defaultValue(Mode.Player).build());
        this.togglePerpective = this.sgGeneral.add(new BoolSetting.Builder().name("toggle-perspective").description("Changes your perspective on toggle.").defaultValue(true).build());
        this.sensitivity = this.sgGeneral.add(new DoubleSetting.Builder().name("camera-sensitivity").description("How fast the camera moves in camera mode.").defaultValue(8.0).min(0.0).sliderMax(10.0).build());
        this.arrows = this.sgArrows.add(new BoolSetting.Builder().name("arrows-control-opposite").description("Allows you to control the other entities rotation with the arrow keys.").defaultValue(true).build());
        this.arrowSpeed = this.sgArrows.add(new DoubleSetting.Builder().name("arrow-speed").description("Rotation speed with arrow keys.").defaultValue(4.0).min(0.0).build());
    }

    public boolean cameraMode() {
        return this.isActive() && this.mc.field_1690.method_31044() == class_5498.field_26665 && this.mode.get() == Mode.Camera;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.arrows.get().booleanValue()) {
            int n = 0;
            while ((double)n < this.arrowSpeed.get() * 2.0) {
                switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$render$FreeLook$Mode[this.mode.get().ordinal()]) {
                    case 1: {
                        if (Input.isKeyPressed(263)) {
                            this.cameraYaw = (float)((double)this.cameraYaw - 0.5);
                        }
                        if (Input.isKeyPressed(262)) {
                            this.cameraYaw = (float)((double)this.cameraYaw + 0.5);
                        }
                        if (Input.isKeyPressed(265)) {
                            this.cameraPitch = (float)((double)this.cameraPitch - 0.5);
                        }
                        if (!Input.isKeyPressed(264)) break;
                        this.cameraPitch = (float)((double)this.cameraPitch + 0.5);
                        break;
                    }
                    case 2: {
                        if (Input.isKeyPressed(263)) {
                            this.mc.field_1724.field_6031 = (float)((double)this.mc.field_1724.field_6031 - 0.5);
                        }
                        if (Input.isKeyPressed(262)) {
                            this.mc.field_1724.field_6031 = (float)((double)this.mc.field_1724.field_6031 + 0.5);
                        }
                        if (Input.isKeyPressed(265)) {
                            this.mc.field_1724.field_5965 = (float)((double)this.mc.field_1724.field_5965 - 0.5);
                        }
                        if (!Input.isKeyPressed(264)) break;
                        this.mc.field_1724.field_5965 = (float)((double)this.mc.field_1724.field_5965 + 0.5);
                    }
                }
                ++n;
                if (3 >= 2) continue;
                return;
            }
        }
        this.mc.field_1724.field_5965 = Utils.clamp(this.mc.field_1724.field_5965, -90.0f, 90.0f);
        this.cameraPitch = Utils.clamp(this.cameraPitch, -90.0f, 90.0f);
    }

    public boolean playerMode() {
        return this.isActive() && this.mc.field_1690.method_31044() == class_5498.field_26665 && this.mode.get() == Mode.Player;
    }

    @Override
    public void onDeactivate() {
        if (this.mc.field_1690.method_31044() != this.prePers && this.togglePerpective.get().booleanValue()) {
            this.mc.field_1690.method_31043(this.prePers);
        }
    }

    @Override
    public void onActivate() {
        this.cameraYaw = this.mc.field_1724.field_6031;
        this.cameraPitch = this.mc.field_1724.field_5965;
        this.prePers = this.mc.field_1690.method_31044();
        if (this.prePers != class_5498.field_26665 && this.togglePerpective.get().booleanValue()) {
            this.mc.field_1690.method_31043(class_5498.field_26665);
        }
    }

    public static enum Mode {
        Player,
        Camera;

    }
}

