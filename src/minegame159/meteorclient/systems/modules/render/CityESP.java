/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1657;
import net.minecraft.class_2338;

public class CityESP
extends Module {
    private final Setting<ShapeMode> shapeMode;
    private final Setting<SettingColor> lineColor;
    private class_2338 target;
    private final Setting<SettingColor> sideColor;
    private final SettingGroup sgRender;

    @EventHandler
    private void onTick(TickEvent.Post post) {
        class_1657 class_16572 = TargetUtils.getPlayerTarget(this.mc.field_1761.method_2904() + 2.0f, SortPriority.LowestDistance);
        this.target = TargetUtils.isBadTarget(class_16572, this.mc.field_1761.method_2904() + 2.0f) ? null : EntityUtils.getCityBlock(class_16572);
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.target == null) {
            return;
        }
        Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, this.target, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
    }

    public CityESP() {
        super(Categories.Render, "city-esp", "Displays blocks that can be broken in order to city another player.");
        this.sgRender = this.settings.createGroup("Render");
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the rendering.").defaultValue(new SettingColor(225, 0, 0, 75)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the rendering.").defaultValue(new SettingColor(225, 0, 0, 255)).build());
    }
}

