/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.meteor.MouseScrollEvent;
import minegame159.meteorclient.events.render.GetFovEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class Zoom
extends Module {
    private double lastFov;
    private final Setting<Boolean> cinematic;
    private double value;
    private final Setting<Double> scrollSensitivity;
    private final Setting<Double> zoom;
    private double preMouseSensitivity;
    private final SettingGroup sgGeneral;
    private boolean preCinematic;

    @Override
    public void onActivate() {
        this.preCinematic = this.mc.field_1690.field_1914;
        this.preMouseSensitivity = this.mc.field_1690.field_1843;
        this.value = this.zoom.get();
        this.lastFov = this.mc.field_1690.field_1826;
    }

    @EventHandler
    private void onGetFov(GetFovEvent getFovEvent) {
        getFovEvent.fov /= this.value;
        if (this.lastFov != getFovEvent.fov) {
            this.mc.field_1769.method_3292();
        }
        this.lastFov = getFovEvent.fov;
    }

    public Zoom() {
        super(Categories.Render, "zoom", "Zooms your view.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.zoom = this.sgGeneral.add(new DoubleSetting.Builder().name("zoom").description("How much to zoom.").defaultValue(6.0).min(1.0).build());
        this.scrollSensitivity = this.sgGeneral.add(new DoubleSetting.Builder().name("scroll-sensitivity").description("Allows you to change zoom value using scroll wheel. 0 to disable.").defaultValue(1.0).min(0.0).build());
        this.cinematic = this.sgGeneral.add(new BoolSetting.Builder().name("cinematic").description("Enables cinematic camera.").defaultValue(false).build());
    }

    @EventHandler
    private void onMouseScroll(MouseScrollEvent mouseScrollEvent) {
        if (this.scrollSensitivity.get() > 0.0) {
            this.value += mouseScrollEvent.value * 0.25 * (this.scrollSensitivity.get() * this.value);
            if (this.value < 1.0) {
                this.value = 1.0;
            }
            mouseScrollEvent.cancel();
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        this.mc.field_1690.field_1914 = this.cinematic.get();
        if (!this.cinematic.get().booleanValue()) {
            this.mc.field_1690.field_1843 = this.preMouseSensitivity / Math.max(this.value * 0.5, 1.0);
        }
    }

    @Override
    public void onDeactivate() {
        this.mc.field_1690.field_1914 = this.preCinematic;
        this.mc.field_1690.field_1843 = this.preMouseSensitivity;
        this.mc.field_1769.method_3292();
    }
}

