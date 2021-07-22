/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_3191
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
    private final /* synthetic */ Setting<SettingColor> startColor;
    private final /* synthetic */ Setting<SettingColor> endColor;
    private final /* synthetic */ Color cSides;
    private final /* synthetic */ Color cLines;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ SettingGroup sgGeneral;

    public BreakIndicators() {
        super(Categories.Render, "break-indicators", "Renders the progress of a block being broken.");
        BreakIndicators llllllllllllllllllIlIIIlIIIlIIll;
        llllllllllllllllllIlIIIlIIIlIIll.sgGeneral = llllllllllllllllllIlIIIlIIIlIIll.settings.getDefaultGroup();
        llllllllllllllllllIlIIIlIIIlIIll.shapeMode = llllllllllllllllllIlIIIlIIIlIIll.sgGeneral.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        llllllllllllllllllIlIIIlIIIlIIll.startColor = llllllllllllllllllIlIIIlIIIlIIll.sgGeneral.add(new ColorSetting.Builder().name("start-color").description("The color for the non-broken block.").defaultValue(new SettingColor(25, 252, 25, 150)).build());
        llllllllllllllllllIlIIIlIIIlIIll.endColor = llllllllllllllllllIlIIIlIIIlIIll.sgGeneral.add(new ColorSetting.Builder().name("end-color").description("The color for the fully-broken block.").defaultValue(new SettingColor(255, 25, 25, 150)).build());
        llllllllllllllllllIlIIIlIIIlIIll.cSides = new Color();
        llllllllllllllllllIlIIIlIIIlIIll.cLines = new Color();
    }

    @EventHandler
    private void onRender(RenderEvent llllllllllllllllllIlIIIlIIIIllIl) {
        BreakIndicators llllllllllllllllllIlIIIlIIIIlIIl;
        Int2ObjectMap<class_3191> llllllllllllllllllIlIIIlIIIIllII = ((WorldRendererAccessor)llllllllllllllllllIlIIIlIIIIlIIl.mc.field_1769).getBlockBreakingInfos();
        float llllllllllllllllllIlIIIlIIIIlIll = ((ClientPlayerInteractionManagerAccessor)llllllllllllllllllIlIIIlIIIIlIIl.mc.field_1761).getBreakingProgress();
        class_2338 llllllllllllllllllIlIIIlIIIIlIlI = ((ClientPlayerInteractionManagerAccessor)llllllllllllllllllIlIIIlIIIIlIIl.mc.field_1761).getCurrentBreakingBlockPos();
        llllllllllllllllllIlIIIlIIIIllII.values().forEach(llllllllllllllllllIlIIIIllIlIIII -> {
            class_238 llllllllllllllllllIlIIIIlllIIlII;
            BreakIndicators llllllllllllllllllIlIIIIllIlIIll;
            class_2338 llllllllllllllllllIlIIIIlllIlIII = llllllllllllllllllIlIIIIllIlIIII.method_13991();
            int llllllllllllllllllIlIIIIlllIIlll = llllllllllllllllllIlIIIIllIlIIII.method_13988();
            class_2680 llllllllllllllllllIlIIIIlllIIllI = llllllllllllllllllIlIIIIllIlIIll.mc.field_1687.method_8320(llllllllllllllllllIlIIIIlllIlIII);
            class_265 llllllllllllllllllIlIIIIlllIIlIl = llllllllllllllllllIlIIIIlllIIllI.method_26218((class_1922)llllllllllllllllllIlIIIIllIlIIll.mc.field_1687, llllllllllllllllllIlIIIIlllIlIII);
            if (llllllllllllllllllIlIIIIlllIIlIl.method_1110()) {
                return;
            }
            class_238 llllllllllllllllllIlIIIIlllIIIll = llllllllllllllllllIlIIIIlllIIlII = llllllllllllllllllIlIIIIlllIIlIl.method_1107();
            double llllllllllllllllllIlIIIIlllIIIlI = (double)(9 - (llllllllllllllllllIlIIIIlllIIlll + 1)) / 9.0;
            if (llllllllllllllllllIlIIIlIIIIlIlI != null && llllllllllllllllllIlIIIlIIIIlIll > 0.0f && llllllllllllllllllIlIIIlIIIIlIlI.equals((Object)llllllllllllllllllIlIIIIlllIlIII)) {
                llllllllllllllllllIlIIIIlllIIIlI = 1.0 - (double)llllllllllllllllllIlIIIlIIIIlIll;
            }
            double llllllllllllllllllIlIIIIlllIIIIl = 1.0 - llllllllllllllllllIlIIIIlllIIIlI;
            llllllllllllllllllIlIIIIlllIIIll = llllllllllllllllllIlIIIIlllIIIll.method_1002(llllllllllllllllllIlIIIIlllIIIll.method_17939() * llllllllllllllllllIlIIIIlllIIIlI, llllllllllllllllllIlIIIIlllIIIll.method_17940() * llllllllllllllllllIlIIIIlllIIIlI, llllllllllllllllllIlIIIIlllIIIll.method_17941() * llllllllllllllllllIlIIIIlllIIIlI);
            double llllllllllllllllllIlIIIIlllIIIII = llllllllllllllllllIlIIIIlllIIlII.method_17939() * llllllllllllllllllIlIIIIlllIIIlI / 2.0;
            double llllllllllllllllllIlIIIIllIlllll = llllllllllllllllllIlIIIIlllIIlII.method_17940() * llllllllllllllllllIlIIIIlllIIIlI / 2.0;
            double llllllllllllllllllIlIIIIllIllllI = llllllllllllllllllIlIIIIlllIIlII.method_17941() * llllllllllllllllllIlIIIIlllIIIlI / 2.0;
            double llllllllllllllllllIlIIIIllIlllIl = (double)llllllllllllllllllIlIIIIlllIlIII.method_10263() + llllllllllllllllllIlIIIIlllIIIll.field_1323 + llllllllllllllllllIlIIIIlllIIIII;
            double llllllllllllllllllIlIIIIllIlllII = (double)llllllllllllllllllIlIIIIlllIlIII.method_10264() + llllllllllllllllllIlIIIIlllIIIll.field_1322 + llllllllllllllllllIlIIIIllIlllll;
            double llllllllllllllllllIlIIIIllIllIll = (double)llllllllllllllllllIlIIIIlllIlIII.method_10260() + llllllllllllllllllIlIIIIlllIIIll.field_1321 + llllllllllllllllllIlIIIIllIllllI;
            double llllllllllllllllllIlIIIIllIllIlI = (double)llllllllllllllllllIlIIIIlllIlIII.method_10263() + llllllllllllllllllIlIIIIlllIIIll.field_1320 + llllllllllllllllllIlIIIIlllIIIII;
            double llllllllllllllllllIlIIIIllIllIIl = (double)llllllllllllllllllIlIIIIlllIlIII.method_10264() + llllllllllllllllllIlIIIIlllIIIll.field_1325 + llllllllllllllllllIlIIIIllIlllll;
            double llllllllllllllllllIlIIIIllIllIII = (double)llllllllllllllllllIlIIIIlllIlIII.method_10260() + llllllllllllllllllIlIIIIlllIIIll.field_1324 + llllllllllllllllllIlIIIIllIllllI;
            Color llllllllllllllllllIlIIIIllIlIlll = llllllllllllllllllIlIIIIllIlIIll.startColor.get().copy().a(llllllllllllllllllIlIIIIllIlIIll.startColor.get().a / 2);
            Color llllllllllllllllllIlIIIIllIlIllI = llllllllllllllllllIlIIIIllIlIIll.endColor.get().copy().a(llllllllllllllllllIlIIIIllIlIIll.endColor.get().a / 2);
            llllllllllllllllllIlIIIIllIlIIll.cSides.set((int)Math.round((double)llllllllllllllllllIlIIIIllIlIlll.r + (double)(llllllllllllllllllIlIIIIllIlIllI.r - llllllllllllllllllIlIIIIllIlIlll.r) * llllllllllllllllllIlIIIIlllIIIIl), (int)Math.round((double)llllllllllllllllllIlIIIIllIlIlll.g + (double)(llllllllllllllllllIlIIIIllIlIllI.g - llllllllllllllllllIlIIIIllIlIlll.g) * llllllllllllllllllIlIIIIlllIIIIl), (int)Math.round((double)llllllllllllllllllIlIIIIllIlIlll.b + (double)(llllllllllllllllllIlIIIIllIlIllI.b - llllllllllllllllllIlIIIIllIlIlll.b) * llllllllllllllllllIlIIIIlllIIIIl), (int)Math.round((double)llllllllllllllllllIlIIIIllIlIlll.a + (double)(llllllllllllllllllIlIIIIllIlIllI.a - llllllllllllllllllIlIIIIllIlIlll.a) * llllllllllllllllllIlIIIIlllIIIIl));
            Color llllllllllllllllllIlIIIIllIlIlIl = llllllllllllllllllIlIIIIllIlIIll.startColor.get();
            Color llllllllllllllllllIlIIIIllIlIlII = llllllllllllllllllIlIIIIllIlIIll.endColor.get();
            llllllllllllllllllIlIIIIllIlIIll.cLines.set((int)Math.round((double)llllllllllllllllllIlIIIIllIlIlIl.r + (double)(llllllllllllllllllIlIIIIllIlIlII.r - llllllllllllllllllIlIIIIllIlIlIl.r) * llllllllllllllllllIlIIIIlllIIIIl), (int)Math.round((double)llllllllllllllllllIlIIIIllIlIlIl.g + (double)(llllllllllllllllllIlIIIIllIlIlII.g - llllllllllllllllllIlIIIIllIlIlIl.g) * llllllllllllllllllIlIIIIlllIIIIl), (int)Math.round((double)llllllllllllllllllIlIIIIllIlIlIl.b + (double)(llllllllllllllllllIlIIIIllIlIlII.b - llllllllllllllllllIlIIIIllIlIlIl.b) * llllllllllllllllllIlIIIIlllIIIIl), (int)Math.round((double)llllllllllllllllllIlIIIIllIlIlIl.a + (double)(llllllllllllllllllIlIIIIllIlIlII.a - llllllllllllllllllIlIIIIllIlIlIl.a) * llllllllllllllllllIlIIIIlllIIIIl));
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllllIlIIIIllIlllIl, llllllllllllllllllIlIIIIllIlllII, llllllllllllllllllIlIIIIllIllIll, llllllllllllllllllIlIIIIllIllIlI, llllllllllllllllllIlIIIIllIllIIl, llllllllllllllllllIlIIIIllIllIII, llllllllllllllllllIlIIIIllIlIIll.cSides, llllllllllllllllllIlIIIIllIlIIll.cLines, llllllllllllllllllIlIIIIllIlIIll.shapeMode.get(), 0);
        });
    }
}

