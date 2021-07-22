/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
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
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3965;

public class BlockSelection
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<Boolean> oneSide;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<Boolean> advanced;

    public BlockSelection() {
        super(Categories.Render, "block-selection", "Modifies how your block selection is rendered.");
        BlockSelection llllllllllllllllllIlllIlIIlIlIIl;
        llllllllllllllllllIlllIlIIlIlIIl.sgGeneral = llllllllllllllllllIlllIlIIlIlIIl.settings.getDefaultGroup();
        llllllllllllllllllIlllIlIIlIlIIl.advanced = llllllllllllllllllIlllIlIIlIlIIl.sgGeneral.add(new BoolSetting.Builder().name("advanced").description("Shows a more advanced outline on different types of shape blocks.").defaultValue(true).build());
        llllllllllllllllllIlllIlIIlIlIIl.oneSide = llllllllllllllllllIlllIlIIlIlIIl.sgGeneral.add(new BoolSetting.Builder().name("single-side").description("Only renders the side you are looking at.").defaultValue(false).build());
        llllllllllllllllllIlllIlIIlIlIIl.shapeMode = llllllllllllllllllIlllIlIIlIlIIl.sgGeneral.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        llllllllllllllllllIlllIlIIlIlIIl.sideColor = llllllllllllllllllIlllIlIIlIlIIl.sgGeneral.add(new ColorSetting.Builder().name("side-color").description("The side color.").defaultValue(new SettingColor(255, 255, 255, 50)).build());
        llllllllllllllllllIlllIlIIlIlIIl.lineColor = llllllllllllllllllIlllIlIIlIlIIl.sgGeneral.add(new ColorSetting.Builder().name("line-color").description("The line color.").defaultValue(new SettingColor(255, 255, 255, 255)).build());
    }

    @EventHandler
    private void onRender(RenderEvent llllllllllllllllllIlllIlIIIllIlI) {
        BlockSelection llllllllllllllllllIlllIlIIIlIIll;
        if (llllllllllllllllllIlllIlIIIlIIll.mc.field_1765 == null || !(llllllllllllllllllIlllIlIIIlIIll.mc.field_1765 instanceof class_3965)) {
            return;
        }
        class_3965 llllllllllllllllllIlllIlIIIllIIl = (class_3965)llllllllllllllllllIlllIlIIIlIIll.mc.field_1765;
        class_2338 llllllllllllllllllIlllIlIIIllIII = llllllllllllllllllIlllIlIIIllIIl.method_17777();
        class_2350 llllllllllllllllllIlllIlIIIlIlll = llllllllllllllllllIlllIlIIIllIIl.method_17780();
        class_2680 llllllllllllllllllIlllIlIIIlIllI = llllllllllllllllllIlllIlIIIlIIll.mc.field_1687.method_8320(llllllllllllllllllIlllIlIIIllIII);
        class_265 llllllllllllllllllIlllIlIIIlIlIl = llllllllllllllllllIlllIlIIIlIllI.method_26218((class_1922)llllllllllllllllllIlllIlIIIlIIll.mc.field_1687, llllllllllllllllllIlllIlIIIllIII);
        if (llllllllllllllllllIlllIlIIIlIlIl.method_1110()) {
            return;
        }
        class_238 llllllllllllllllllIlllIlIIIlIlII = llllllllllllllllllIlllIlIIIlIlIl.method_1107();
        if (llllllllllllllllllIlllIlIIIlIIll.oneSide.get().booleanValue()) {
            if (llllllllllllllllllIlllIlIIIlIlll == class_2350.field_11036 || llllllllllllllllllIlllIlIIIlIlll == class_2350.field_11033) {
                Renderer.quadWithLinesHorizontal(Renderer.NORMAL, Renderer.LINES, (double)llllllllllllllllllIlllIlIIIllIII.method_10263() + llllllllllllllllllIlllIlIIIlIlII.field_1323, (double)llllllllllllllllllIlllIlIIIllIII.method_10264() + (llllllllllllllllllIlllIlIIIlIlll == class_2350.field_11033 ? llllllllllllllllllIlllIlIIIlIlII.field_1322 : llllllllllllllllllIlllIlIIIlIlII.field_1325), (double)llllllllllllllllllIlllIlIIIllIII.method_10260() + llllllllllllllllllIlllIlIIIlIlII.field_1321, (double)llllllllllllllllllIlllIlIIIllIII.method_10263() + llllllllllllllllllIlllIlIIIlIlII.field_1320, (double)llllllllllllllllllIlllIlIIIllIII.method_10260() + llllllllllllllllllIlllIlIIIlIlII.field_1324, llllllllllllllllllIlllIlIIIlIIll.sideColor.get(), llllllllllllllllllIlllIlIIIlIIll.lineColor.get(), llllllllllllllllllIlllIlIIIlIIll.shapeMode.get());
            } else if (llllllllllllllllllIlllIlIIIlIlll == class_2350.field_11035 || llllllllllllllllllIlllIlIIIlIlll == class_2350.field_11043) {
                double llllllllllllllllllIlllIlIIIllllI = llllllllllllllllllIlllIlIIIlIlll == class_2350.field_11043 ? llllllllllllllllllIlllIlIIIlIlII.field_1321 : llllllllllllllllllIlllIlIIIlIlII.field_1324;
                Renderer.quadWithLinesVertical(Renderer.NORMAL, Renderer.LINES, (double)llllllllllllllllllIlllIlIIIllIII.method_10263() + llllllllllllllllllIlllIlIIIlIlII.field_1323, (double)llllllllllllllllllIlllIlIIIllIII.method_10264() + llllllllllllllllllIlllIlIIIlIlII.field_1322, (double)llllllllllllllllllIlllIlIIIllIII.method_10260() + llllllllllllllllllIlllIlIIIllllI, (double)llllllllllllllllllIlllIlIIIllIII.method_10263() + llllllllllllllllllIlllIlIIIlIlII.field_1320, (double)llllllllllllllllllIlllIlIIIllIII.method_10264() + llllllllllllllllllIlllIlIIIlIlII.field_1325, (double)llllllllllllllllllIlllIlIIIllIII.method_10260() + llllllllllllllllllIlllIlIIIllllI, llllllllllllllllllIlllIlIIIlIIll.sideColor.get(), llllllllllllllllllIlllIlIIIlIIll.lineColor.get(), llllllllllllllllllIlllIlIIIlIIll.shapeMode.get());
            } else {
                double llllllllllllllllllIlllIlIIIlllIl = llllllllllllllllllIlllIlIIIlIlll == class_2350.field_11039 ? llllllllllllllllllIlllIlIIIlIlII.field_1323 : llllllllllllllllllIlllIlIIIlIlII.field_1320;
                Renderer.quadWithLinesVertical(Renderer.NORMAL, Renderer.LINES, (double)llllllllllllllllllIlllIlIIIllIII.method_10263() + llllllllllllllllllIlllIlIIIlllIl, (double)llllllllllllllllllIlllIlIIIllIII.method_10264() + llllllllllllllllllIlllIlIIIlIlII.field_1322, (double)llllllllllllllllllIlllIlIIIllIII.method_10260() + llllllllllllllllllIlllIlIIIlIlII.field_1321, (double)llllllllllllllllllIlllIlIIIllIII.method_10263() + llllllllllllllllllIlllIlIIIlllIl, (double)llllllllllllllllllIlllIlIIIllIII.method_10264() + llllllllllllllllllIlllIlIIIlIlII.field_1325, (double)llllllllllllllllllIlllIlIIIllIII.method_10260() + llllllllllllllllllIlllIlIIIlIlII.field_1324, llllllllllllllllllIlllIlIIIlIIll.sideColor.get(), llllllllllllllllllIlllIlIIIlIIll.lineColor.get(), llllllllllllllllllIlllIlIIIlIIll.shapeMode.get());
            }
        } else if (llllllllllllllllllIlllIlIIIlIIll.advanced.get().booleanValue()) {
            if (llllllllllllllllllIlllIlIIIlIIll.shapeMode.get() == ShapeMode.Both || llllllllllllllllllIlllIlIIIlIIll.shapeMode.get() == ShapeMode.Lines) {
                llllllllllllllllllIlllIlIIIlIlIl.method_1104((llllllllllllllllllIlllIIlllIllll, llllllllllllllllllIlllIIllllIllI, llllllllllllllllllIlllIIlllIllIl, llllllllllllllllllIlllIIllllIlII, llllllllllllllllllIlllIIllllIIll, llllllllllllllllllIlllIIlllIlIlI) -> {
                    BlockSelection llllllllllllllllllIlllIIllllIIIl;
                    Renderer.LINES.line((double)llllllllllllllllllIlllIlIIIllIII.method_10263() + llllllllllllllllllIlllIIlllIllll, (double)llllllllllllllllllIlllIlIIIllIII.method_10264() + llllllllllllllllllIlllIIllllIllI, (double)llllllllllllllllllIlllIlIIIllIII.method_10260() + llllllllllllllllllIlllIIlllIllIl, (double)llllllllllllllllllIlllIlIIIllIII.method_10263() + llllllllllllllllllIlllIIllllIlII, (double)llllllllllllllllllIlllIlIIIllIII.method_10264() + llllllllllllllllllIlllIIllllIIll, (double)llllllllllllllllllIlllIlIIIllIII.method_10260() + llllllllllllllllllIlllIIlllIlIlI, llllllllllllllllllIlllIIllllIIIl.lineColor.get());
                });
            }
            if (llllllllllllllllllIlllIlIIIlIIll.shapeMode.get() == ShapeMode.Both || llllllllllllllllllIlllIlIIIlIIll.shapeMode.get() == ShapeMode.Sides) {
                for (class_238 llllllllllllllllllIlllIlIIIlllII : llllllllllllllllllIlllIlIIIlIlIl.method_1090()) {
                    llllllllllllllllllIlllIlIIIlIIll.render(llllllllllllllllllIlllIlIIIllIII, llllllllllllllllllIlllIlIIIlllII);
                }
            }
        } else {
            llllllllllllllllllIlllIlIIIlIIll.render(llllllllllllllllllIlllIlIIIllIII, llllllllllllllllllIlllIlIIIlIlII);
        }
    }

    private void render(class_2338 llllllllllllllllllIlllIlIIIIIllI, class_238 llllllllllllllllllIlllIlIIIIIlIl) {
        BlockSelection llllllllllllllllllIlllIlIIIIIlll;
        Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (double)llllllllllllllllllIlllIlIIIIIllI.method_10263() + llllllllllllllllllIlllIlIIIIIlIl.field_1323, (double)llllllllllllllllllIlllIlIIIIIllI.method_10264() + llllllllllllllllllIlllIlIIIIIlIl.field_1322, (double)llllllllllllllllllIlllIlIIIIIllI.method_10260() + llllllllllllllllllIlllIlIIIIIlIl.field_1321, (double)llllllllllllllllllIlllIlIIIIIllI.method_10263() + llllllllllllllllllIlllIlIIIIIlIl.field_1320, (double)llllllllllllllllllIlllIlIIIIIllI.method_10264() + llllllllllllllllllIlllIlIIIIIlIl.field_1325, (double)llllllllllllllllllIlllIlIIIIIllI.method_10260() + llllllllllllllllllIlllIlIIIIIlIl.field_1324, llllllllllllllllllIlllIlIIIIIlll.sideColor.get(), llllllllllllllllllIlllIlIIIIIlll.lineColor.get(), llllllllllllllllllIlllIlIIIIIlll.shapeMode.get(), 0);
    }
}

