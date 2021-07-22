/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_2338
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
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private /* synthetic */ class_2338 target;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ SettingGroup sgRender;

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIIlIllllIIllIIl) {
        CityESP lllllllllllllllllIIlIllllIIllIlI;
        class_1657 lllllllllllllllllIIlIllllIIllIII = TargetUtils.getPlayerTarget(lllllllllllllllllIIlIllllIIllIlI.mc.field_1761.method_2904() + 2.0f, SortPriority.LowestDistance);
        lllllllllllllllllIIlIllllIIllIlI.target = TargetUtils.isBadTarget(lllllllllllllllllIIlIllllIIllIII, lllllllllllllllllIIlIllllIIllIlI.mc.field_1761.method_2904() + 2.0f) ? null : EntityUtils.getCityBlock(lllllllllllllllllIIlIllllIIllIII);
    }

    @EventHandler
    private void onRender(RenderEvent lllllllllllllllllIIlIllllIIlIIll) {
        CityESP lllllllllllllllllIIlIllllIIlIlII;
        if (lllllllllllllllllIIlIllllIIlIlII.target == null) {
            return;
        }
        Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, lllllllllllllllllIIlIllllIIlIlII.target, lllllllllllllllllIIlIllllIIlIlII.sideColor.get(), lllllllllllllllllIIlIllllIIlIlII.lineColor.get(), lllllllllllllllllIIlIllllIIlIlII.shapeMode.get(), 0);
    }

    public CityESP() {
        super(Categories.Render, "city-esp", "Displays blocks that can be broken in order to city another player.");
        CityESP lllllllllllllllllIIlIllllIIlllIl;
        lllllllllllllllllIIlIllllIIlllIl.sgRender = lllllllllllllllllIIlIllllIIlllIl.settings.createGroup("Render");
        lllllllllllllllllIIlIllllIIlllIl.shapeMode = lllllllllllllllllIIlIllllIIlllIl.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lllllllllllllllllIIlIllllIIlllIl.sideColor = lllllllllllllllllIIlIllllIIlllIl.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the rendering.").defaultValue(new SettingColor(225, 0, 0, 75)).build());
        lllllllllllllllllIIlIllllIIlllIl.lineColor = lllllllllllllllllIIlIllllIIlllIl.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the rendering.").defaultValue(new SettingColor(225, 0, 0, 255)).build());
    }
}

