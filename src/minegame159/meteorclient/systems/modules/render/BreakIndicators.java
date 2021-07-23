/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.mixin.ClientPlayerInteractionManagerAccessor;
import minegame159.meteorclient.mixin.WorldRendererAccessor;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3191;

public class BreakIndicators
extends Module {
    private final Setting<SettingColor> startColor;
    private final Setting<SettingColor> endColor;
    private final Color cSides;
    private final Color cLines;
    private final Setting<ShapeMode> shapeMode;
    private final SettingGroup sgGeneral;

    public BreakIndicators() {
        super(Categories.Render, "break-indicators", "Renders the progress of a block being broken.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.shapeMode = this.sgGeneral.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.startColor = this.sgGeneral.add(new ColorSetting.Builder().name("start-color").description("The color for the non-broken block.").defaultValue(new SettingColor(25, 252, 25, 150)).build());
        this.endColor = this.sgGeneral.add(new ColorSetting.Builder().name("end-color").description("The color for the fully-broken block.").defaultValue(new SettingColor(255, 25, 25, 150)).build());
        this.cSides = new Color();
        this.cLines = new Color();
    }

    private void lambda$onRender$0(class_2338 class_23382, float f, class_3191 class_31912) {
        class_238 class_2383;
        class_2338 class_23383 = class_31912.method_13991();
        int n = class_31912.method_13988();
        class_2680 class_26802 = this.mc.field_1687.method_8320(class_23383);
        class_265 class_2652 = class_26802.method_26218((class_1922)this.mc.field_1687, class_23383);
        if (class_2652.method_1110()) {
            return;
        }
        class_238 class_2384 = class_2383 = class_2652.method_1107();
        double d = (double)(9 - (n + 1)) / 9.0;
        if (class_23382 != null && f > 0.0f && class_23382.equals((Object)class_23383)) {
            d = 1.0 - (double)f;
        }
        double d2 = 1.0 - d;
        class_2384 = class_2384.method_1002(class_2384.method_17939() * d, class_2384.method_17940() * d, class_2384.method_17941() * d);
        double d3 = class_2383.method_17939() * d / 2.0;
        double d4 = class_2383.method_17940() * d / 2.0;
        double d5 = class_2383.method_17941() * d / 2.0;
        double d6 = (double)class_23383.method_10263() + class_2384.field_1323 + d3;
        double d7 = (double)class_23383.method_10264() + class_2384.field_1322 + d4;
        double d8 = (double)class_23383.method_10260() + class_2384.field_1321 + d5;
        double d9 = (double)class_23383.method_10263() + class_2384.field_1320 + d3;
        double d10 = (double)class_23383.method_10264() + class_2384.field_1325 + d4;
        double d11 = (double)class_23383.method_10260() + class_2384.field_1324 + d5;
        Color color = this.startColor.get().copy().a(this.startColor.get().a / 2);
        Color color2 = this.endColor.get().copy().a(this.endColor.get().a / 2);
        this.cSides.set((int)Math.round((double)color.r + (double)(color2.r - color.r) * d2), (int)Math.round((double)color.g + (double)(color2.g - color.g) * d2), (int)Math.round((double)color.b + (double)(color2.b - color.b) * d2), (int)Math.round((double)color.a + (double)(color2.a - color.a) * d2));
        Color color3 = this.startColor.get();
        Color color4 = this.endColor.get();
        this.cLines.set((int)Math.round((double)color3.r + (double)(color4.r - color3.r) * d2), (int)Math.round((double)color3.g + (double)(color4.g - color3.g) * d2), (int)Math.round((double)color3.b + (double)(color4.b - color3.b) * d2), (int)Math.round((double)color3.a + (double)(color4.a - color3.a) * d2));
        Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, d6, d7, d8, d9, d10, d11, this.cSides, this.cLines, this.shapeMode.get(), 0);
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        Int2ObjectMap<class_3191> int2ObjectMap = ((WorldRendererAccessor)this.mc.field_1769).getBlockBreakingInfos();
        float f = ((ClientPlayerInteractionManagerAccessor)this.mc.field_1761).getBreakingProgress();
        class_2338 class_23382 = ((ClientPlayerInteractionManagerAccessor)this.mc.field_1761).getCurrentBreakingBlockPos();
        int2ObjectMap.values().forEach(arg_0 -> this.lambda$onRender$0(class_23382, f, arg_0));
    }
}

