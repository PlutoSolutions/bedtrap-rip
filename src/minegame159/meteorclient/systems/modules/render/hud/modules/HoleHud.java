/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2350
 *  net.minecraft.class_290
 *  net.minecraft.class_3532
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import java.util.Arrays;
import java.util.List;
import minegame159.meteorclient.mixin.WorldRendererAccessor;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.RenderUtils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2350;
import net.minecraft.class_290;
import net.minecraft.class_3532;

public class HoleHud
extends HudElement {
    public final /* synthetic */ Setting<List<class_2248>> safe;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ Color BG_COLOR;
    private final /* synthetic */ Color OL_COLOR;

    public HoleHud(HUD llllllllllllllllIlIllllllIlllIIl) {
        super(llllllllllllllllIlIllllllIlllIIl, "hole", "Displays information about the hole you are standing in.", false);
        HoleHud llllllllllllllllIlIllllllIllllII;
        llllllllllllllllIlIllllllIllllII.sgGeneral = llllllllllllllllIlIllllllIllllII.settings.getDefaultGroup();
        llllllllllllllllIlIllllllIllllII.scale = llllllllllllllllIlIllllllIllllII.sgGeneral.add(new DoubleSetting.Builder().name("scale").defaultValue(3.0).min(1.0).sliderMin(1.0).build());
        llllllllllllllllIlIllllllIllllII.safe = llllllllllllllllIlIllllllIllllII.sgGeneral.add(new BlockListSetting.Builder().name("safe-blocks").description("Which blocks to consider safe.").defaultValue(Arrays.asList(new class_2248[]{class_2246.field_10540, class_2246.field_9987, class_2246.field_22423, class_2246.field_22108})).build());
        llllllllllllllllIlIllllllIllllII.BG_COLOR = new Color(255, 25, 25, 100);
        llllllllllllllllIlIllllllIllllII.OL_COLOR = new Color(255, 25, 25, 255);
    }

    private class_2350 get(Facing llllllllllllllllIlIllllllIlIIlIl) {
        HoleHud llllllllllllllllIlIllllllIlIlIII;
        if (!Utils.canUpdate() || llllllllllllllllIlIllllllIlIlIII.isInEditor()) {
            return class_2350.field_11033;
        }
        return class_2350.method_10150((double)class_3532.method_15393((float)(llllllllllllllllIlIllllllIlIlIII.mc.field_1724.field_6031 + (float)llllllllllllllllIlIllllllIlIIlIl.offset)));
    }

    private void renderBreaking(double llllllllllllllllIlIllllllIIIllII, double llllllllllllllllIlIllllllIIIlIll, double llllllllllllllllIlIllllllIIIlllI) {
        HoleHud llllllllllllllllIlIllllllIIlIIIl;
        Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
        Renderer.NORMAL.quad(llllllllllllllllIlIllllllIIIllII, llllllllllllllllIlIllllllIIIlIll, 16.0 * llllllllllllllllIlIllllllIIIlllI * llllllllllllllllIlIllllllIIlIIIl.scale.get(), 16.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), llllllllllllllllIlIllllllIIlIIIl.BG_COLOR);
        Renderer.NORMAL.quad(llllllllllllllllIlIllllllIIIllII, llllllllllllllllIlIllllllIIIlIll, 16.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), 1.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), llllllllllllllllIlIllllllIIlIIIl.OL_COLOR);
        Renderer.NORMAL.quad(llllllllllllllllIlIllllllIIIllII, llllllllllllllllIlIllllllIIIlIll + 15.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), 16.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), 1.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), llllllllllllllllIlIllllllIIlIIIl.OL_COLOR);
        Renderer.NORMAL.quad(llllllllllllllllIlIllllllIIIllII, llllllllllllllllIlIllllllIIIlIll, 1.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), 16.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), llllllllllllllllIlIllllllIIlIIIl.OL_COLOR);
        Renderer.NORMAL.quad(llllllllllllllllIlIllllllIIIllII + 15.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), llllllllllllllllIlIllllllIIIlIll, 1.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), 16.0 * llllllllllllllllIlIllllllIIlIIIl.scale.get(), llllllllllllllllIlIllllllIIlIIIl.OL_COLOR);
        Renderer.NORMAL.end();
    }

    @Override
    public void render(HudRenderer llllllllllllllllIlIllllllIllIIII) {
        HoleHud llllllllllllllllIlIllllllIllIIIl;
        double llllllllllllllllIlIllllllIlIllll = llllllllllllllllIlIllllllIllIIIl.box.getX();
        double llllllllllllllllIlIllllllIlIlllI = llllllllllllllllIlIllllllIllIIIl.box.getY();
        llllllllllllllllIlIllllllIllIIIl.drawBlock(llllllllllllllllIlIllllllIllIIIl.get(Facing.Left), llllllllllllllllIlIllllllIlIllll, llllllllllllllllIlIllllllIlIlllI + 16.0 * llllllllllllllllIlIllllllIllIIIl.scale.get());
        llllllllllllllllIlIllllllIllIIIl.drawBlock(llllllllllllllllIlIllllllIllIIIl.get(Facing.Front), llllllllllllllllIlIllllllIlIllll + 16.0 * llllllllllllllllIlIllllllIllIIIl.scale.get(), llllllllllllllllIlIllllllIlIlllI);
        llllllllllllllllIlIllllllIllIIIl.drawBlock(llllllllllllllllIlIllllllIllIIIl.get(Facing.Right), llllllllllllllllIlIllllllIlIllll + 32.0 * llllllllllllllllIlIllllllIllIIIl.scale.get(), llllllllllllllllIlIllllllIlIlllI + 16.0 * llllllllllllllllIlIllllllIllIIIl.scale.get());
        llllllllllllllllIlIllllllIllIIIl.drawBlock(llllllllllllllllIlIllllllIllIIIl.get(Facing.Back), llllllllllllllllIlIllllllIlIllll + 16.0 * llllllllllllllllIlIllllllIllIIIl.scale.get(), llllllllllllllllIlIllllllIlIlllI + 32.0 * llllllllllllllllIlIllllllIllIIIl.scale.get());
    }

    @Override
    public void update(HudRenderer llllllllllllllllIlIllllllIllIllI) {
        HoleHud llllllllllllllllIlIllllllIllIlIl;
        llllllllllllllllIlIllllllIllIlIl.box.setSize(48.0 * llllllllllllllllIlIllllllIllIlIl.scale.get(), 48.0 * llllllllllllllllIlIllllllIllIlIl.scale.get());
    }

    private void drawBlock(class_2350 llllllllllllllllIlIllllllIIllllI, double llllllllllllllllIlIllllllIIlllIl, double llllllllllllllllIlIllllllIIlllII) {
        HoleHud llllllllllllllllIlIllllllIIllIlI;
        class_2248 llllllllllllllllIlIllllllIIllIll;
        class_2248 class_22482 = llllllllllllllllIlIllllllIIllIll = llllllllllllllllIlIllllllIIllllI == class_2350.field_11033 ? class_2246.field_10540 : llllllllllllllllIlIllllllIIllIlI.mc.field_1687.method_8320(llllllllllllllllIlIllllllIIllIlI.mc.field_1724.method_24515().method_10093(llllllllllllllllIlIllllllIIllllI)).method_26204();
        if (!llllllllllllllllIlIllllllIIllIlI.safe.get().contains((Object)llllllllllllllllIlIllllllIIllIll)) {
            return;
        }
        RenderUtils.drawItem(llllllllllllllllIlIllllllIIllIll.method_8389().method_7854(), (int)llllllllllllllllIlIllllllIIlllIl, (int)llllllllllllllllIlIllllllIIlllII, llllllllllllllllIlIllllllIIllIlI.scale.get(), false);
        if (llllllllllllllllIlIllllllIIllllI == class_2350.field_11033) {
            return;
        }
        ((WorldRendererAccessor)llllllllllllllllIlIllllllIIllIlI.mc.field_1769).getBlockBreakingInfos().values().forEach(llllllllllllllllIlIllllllIIIIIII -> {
            HoleHud llllllllllllllllIlIlllllIlllllll;
            if (llllllllllllllllIlIllllllIIIIIII.method_13991().equals((Object)llllllllllllllllIlIlllllIlllllll.mc.field_1724.method_24515().method_10093(llllllllllllllllIlIllllllIIllllI))) {
                llllllllllllllllIlIlllllIlllllll.renderBreaking(llllllllllllllllIlIllllllIIlllIl, llllllllllllllllIlIllllllIIlllII, (float)llllllllllllllllIlIllllllIIIIIII.method_13988() / 9.0f);
            }
        });
    }

    private static enum Facing {
        Left(-90),
        Right(90),
        Front(0),
        Back(180);

        /* synthetic */ int offset;

        private Facing(int lllllllllllllllllIIIllIIIllllIIl) {
            Facing lllllllllllllllllIIIllIIIlllllII;
            lllllllllllllllllIIIllIIIlllllII.offset = lllllllllllllllllIIIllIIIllllIIl;
        }
    }
}

