/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class CustomFOV
extends Module {
    private final Setting<Integer> fovSetting;
    private final SettingGroup sgGeneral;
    private double fov;

    @Override
    public void onActivate() {
        this.fov = this.mc.field_1690.field_1826;
        this.mc.field_1690.field_1826 = this.fovSetting.get().intValue();
    }

    public CustomFOV() {
        super(Categories.Render, "custom-fov", "Allows your FOV to be more customizable.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.fovSetting = this.sgGeneral.add(new IntSetting.Builder().name("fov").description("Your custom fov.").defaultValue(100).sliderMin(1).sliderMax(179).build());
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if ((double)this.fovSetting.get().intValue() != this.mc.field_1690.field_1826) {
            this.mc.field_1690.field_1826 = this.fovSetting.get().intValue();
        }
    }

    @Override
    public void onDeactivate() {
        this.mc.field_1690.field_1826 = this.fov;
    }
}

