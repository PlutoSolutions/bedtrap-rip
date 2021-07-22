/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_5498
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.ChangePerspectiveEvent;
import minegame159.meteorclient.events.meteor.MouseScrollEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_5498;

public class CameraTweaks
extends Module {
    public double distance;
    private final Setting<Double> cameraDistance;
    private final Setting<Double> scrollSensitivity;
    private final Setting<Boolean> clip;
    private final SettingGroup sgGeneral;

    public double getDistance() {
        return this.isActive() ? this.distance : 4.0;
    }

    public CameraTweaks() {
        super(Categories.Render, "camera-tweaks", "Allows modification of the third person camera.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.clip = this.sgGeneral.add(new BoolSetting.Builder().name("clip").description("Allows the camera to clip through blocks.").defaultValue(true).build());
        this.cameraDistance = this.sgGeneral.add(new DoubleSetting.Builder().name("camera-distance").description("The distance the third person camera is from the player.").defaultValue(4.0).min(0.0).onChanged(this::lambda$new$0).build());
        this.scrollSensitivity = this.sgGeneral.add(new DoubleSetting.Builder().name("distance-scroll-sensitivity").description("Scroll sensitivity when changing the cameras distance. 0 to disable.").defaultValue(1.0).min(0.0).build());
    }

    @Override
    public void onActivate() {
        this.distance = this.cameraDistance.get();
    }

    @EventHandler
    private void onPerspectiveChanged(ChangePerspectiveEvent changePerspectiveEvent) {
        this.distance = this.cameraDistance.get();
    }

    @EventHandler
    private void onMouseScroll(MouseScrollEvent mouseScrollEvent) {
        if (this.mc.field_1690.method_31044() == class_5498.field_26664) {
            return;
        }
        if (this.scrollSensitivity.get() > 0.0) {
            this.distance += mouseScrollEvent.value * 0.25 * (this.scrollSensitivity.get() * this.distance);
            mouseScrollEvent.cancel();
        }
    }

    private void lambda$new$0(Double d) {
        this.distance = d;
    }

    public boolean clip() {
        return this.isActive() && this.clip.get() != false;
    }
}

