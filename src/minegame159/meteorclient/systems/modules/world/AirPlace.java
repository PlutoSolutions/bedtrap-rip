/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1747
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.world;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1747;
import net.minecraft.class_3965;

public class AirPlace
extends Module {
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;

    @EventHandler
    private void onTick(TickEvent.Post lIllIlIIlIIlIIl) {
        AirPlace lIllIlIIlIIlIlI;
        if (!(lIllIlIIlIIlIlI.mc.field_1765 instanceof class_3965) || !(lIllIlIIlIIlIlI.mc.field_1724.method_6047().method_7909() instanceof class_1747)) {
            return;
        }
        if (lIllIlIIlIIlIlI.mc.field_1690.field_1904.method_1434()) {
            BlockUtils.place(((class_3965)lIllIlIIlIIlIlI.mc.field_1765).method_17777(), class_1268.field_5808, lIllIlIIlIIlIlI.mc.field_1724.field_7514.field_7545, false, 0, true, true, false);
        }
    }

    public AirPlace() {
        super(Categories.Player, "air-place", "Places a block where your crosshair is pointing at.");
        AirPlace lIllIlIIlIIllIl;
        lIllIlIIlIIllIl.sgGeneral = lIllIlIIlIIllIl.settings.getDefaultGroup();
        lIllIlIIlIIllIl.render = lIllIlIIlIIllIl.sgGeneral.add(new BoolSetting.Builder().name("render").description("Renders a block overlay where the obsidian will be placed.").defaultValue(true).build());
        lIllIlIIlIIllIl.shapeMode = lIllIlIIlIIllIl.sgGeneral.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lIllIlIIlIIllIl.sideColor = lIllIlIIlIIllIl.sgGeneral.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        lIllIlIIlIIllIl.lineColor = lIllIlIIlIIllIl.sgGeneral.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
    }

    @EventHandler
    private void onRender(RenderEvent lIllIlIIlIIIlIl) {
        AirPlace lIllIlIIlIIIllI;
        if (!(lIllIlIIlIIIllI.mc.field_1765 instanceof class_3965 && lIllIlIIlIIIllI.mc.field_1687.method_8320(((class_3965)lIllIlIIlIIIllI.mc.field_1765).method_17777()).method_26207().method_15800() && lIllIlIIlIIIllI.mc.field_1724.method_6047().method_7909() instanceof class_1747 && lIllIlIIlIIIllI.render.get().booleanValue())) {
            return;
        }
        Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, ((class_3965)lIllIlIIlIIIllI.mc.field_1765).method_17777(), lIllIlIIlIIIllI.sideColor.get(), lIllIlIIlIIIllI.lineColor.get(), lIllIlIIlIIIllI.shapeMode.get(), 0);
    }
}

