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
    private final SettingGroup sgGeneral;
    private final Setting<SettingColor> lineColor;
    private final Setting<ShapeMode> shapeMode;
    private final Setting<Boolean> oneSide;
    private final Setting<SettingColor> sideColor;
    private final Setting<Boolean> advanced;

    public BlockSelection() {
        super(Categories.Render, "block-selection", "Modifies how your block selection is rendered.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.advanced = this.sgGeneral.add(new BoolSetting.Builder().name("advanced").description("Shows a more advanced outline on different types of shape blocks.").defaultValue(true).build());
        this.oneSide = this.sgGeneral.add(new BoolSetting.Builder().name("single-side").description("Only renders the side you are looking at.").defaultValue(false).build());
        this.shapeMode = this.sgGeneral.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgGeneral.add(new ColorSetting.Builder().name("side-color").description("The side color.").defaultValue(new SettingColor(255, 255, 255, 50)).build());
        this.lineColor = this.sgGeneral.add(new ColorSetting.Builder().name("line-color").description("The line color.").defaultValue(new SettingColor(255, 255, 255, 255)).build());
    }

    private void lambda$onRender$0(class_2338 class_23382, double d, double d2, double d3, double d4, double d5, double d6) {
        Renderer.LINES.line((double)class_23382.method_10263() + d, (double)class_23382.method_10264() + d2, (double)class_23382.method_10260() + d3, (double)class_23382.method_10263() + d4, (double)class_23382.method_10264() + d5, (double)class_23382.method_10260() + d6, this.lineColor.get());
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.mc.field_1765 == null || !(this.mc.field_1765 instanceof class_3965)) {
            return;
        }
        class_3965 class_39652 = (class_3965)this.mc.field_1765;
        class_2338 class_23382 = class_39652.method_17777();
        class_2350 class_23502 = class_39652.method_17780();
        class_2680 class_26802 = this.mc.field_1687.method_8320(class_23382);
        class_265 class_2652 = class_26802.method_26218((class_1922)this.mc.field_1687, class_23382);
        if (class_2652.method_1110()) {
            return;
        }
        class_238 class_2383 = class_2652.method_1107();
        if (this.oneSide.get().booleanValue()) {
            if (class_23502 == class_2350.field_11036 || class_23502 == class_2350.field_11033) {
                Renderer.quadWithLinesHorizontal(Renderer.NORMAL, Renderer.LINES, (double)class_23382.method_10263() + class_2383.field_1323, (double)class_23382.method_10264() + (class_23502 == class_2350.field_11033 ? class_2383.field_1322 : class_2383.field_1325), (double)class_23382.method_10260() + class_2383.field_1321, (double)class_23382.method_10263() + class_2383.field_1320, (double)class_23382.method_10260() + class_2383.field_1324, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get());
            } else if (class_23502 == class_2350.field_11035 || class_23502 == class_2350.field_11043) {
                double d = class_23502 == class_2350.field_11043 ? class_2383.field_1321 : class_2383.field_1324;
                Renderer.quadWithLinesVertical(Renderer.NORMAL, Renderer.LINES, (double)class_23382.method_10263() + class_2383.field_1323, (double)class_23382.method_10264() + class_2383.field_1322, (double)class_23382.method_10260() + d, (double)class_23382.method_10263() + class_2383.field_1320, (double)class_23382.method_10264() + class_2383.field_1325, (double)class_23382.method_10260() + d, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get());
            } else {
                double d = class_23502 == class_2350.field_11039 ? class_2383.field_1323 : class_2383.field_1320;
                Renderer.quadWithLinesVertical(Renderer.NORMAL, Renderer.LINES, (double)class_23382.method_10263() + d, (double)class_23382.method_10264() + class_2383.field_1322, (double)class_23382.method_10260() + class_2383.field_1321, (double)class_23382.method_10263() + d, (double)class_23382.method_10264() + class_2383.field_1325, (double)class_23382.method_10260() + class_2383.field_1324, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get());
            }
        } else if (this.advanced.get().booleanValue()) {
            if (this.shapeMode.get() == ShapeMode.Both || this.shapeMode.get() == ShapeMode.Lines) {
                class_2652.method_1104((arg_0, arg_1, arg_2, arg_3, arg_4, arg_5) -> this.lambda$onRender$0(class_23382, arg_0, arg_1, arg_2, arg_3, arg_4, arg_5));
            }
            if (this.shapeMode.get() == ShapeMode.Both || this.shapeMode.get() == ShapeMode.Sides) {
                for (class_238 class_2384 : class_2652.method_1090()) {
                    this.render(class_23382, class_2384);
                }
            }
        } else {
            this.render(class_23382, class_2383);
        }
    }

    private void render(class_2338 class_23382, class_238 class_2383) {
        Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (double)class_23382.method_10263() + class_2383.field_1323, (double)class_23382.method_10264() + class_2383.field_1322, (double)class_23382.method_10260() + class_2383.field_1321, (double)class_23382.method_10263() + class_2383.field_1320, (double)class_23382.method_10264() + class_2383.field_1325, (double)class_23382.method_10260() + class_2383.field_1324, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
    }
}

