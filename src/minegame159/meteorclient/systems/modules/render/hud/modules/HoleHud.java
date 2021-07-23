/*
 * Decompiled with CFR 0.151.
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
import net.minecraft.class_3191;
import net.minecraft.class_3532;

public class HoleHud
extends HudElement {
    public final Setting<List<class_2248>> safe;
    private final SettingGroup sgGeneral;
    private final Setting<Double> scale;
    private final Color BG_COLOR;
    private final Color OL_COLOR;

    private void lambda$drawBlock$0(class_2350 class_23502, double d, double d2, class_3191 class_31912) {
        if (class_31912.method_13991().equals((Object)this.mc.field_1724.method_24515().method_10093(class_23502))) {
            this.renderBreaking(d, d2, (float)class_31912.method_13988() / 9.0f);
        }
    }

    public HoleHud(HUD hUD) {
        super(hUD, "hole", "Displays information about the hole you are standing in.", false);
        this.sgGeneral = this.settings.getDefaultGroup();
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").defaultValue(3.0).min(1.0).sliderMin(1.0).build());
        this.safe = this.sgGeneral.add(new BlockListSetting.Builder().name("safe-blocks").description("Which blocks to consider safe.").defaultValue(Arrays.asList(class_2246.field_10540, class_2246.field_9987, class_2246.field_22423, class_2246.field_22108)).build());
        this.BG_COLOR = new Color(255, 25, 25, 100);
        this.OL_COLOR = new Color(255, 25, 25, 255);
    }

    private class_2350 get(Facing facing) {
        if (!Utils.canUpdate() || this.isInEditor()) {
            return class_2350.field_11033;
        }
        return class_2350.method_10150((double)class_3532.method_15393((float)(this.mc.field_1724.field_6031 + (float)facing.offset)));
    }

    private void renderBreaking(double d, double d2, double d3) {
        Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
        Renderer.NORMAL.quad(d, d2, 16.0 * d3 * this.scale.get(), 16.0 * this.scale.get(), this.BG_COLOR);
        Renderer.NORMAL.quad(d, d2, 16.0 * this.scale.get(), 1.0 * this.scale.get(), this.OL_COLOR);
        Renderer.NORMAL.quad(d, d2 + 15.0 * this.scale.get(), 16.0 * this.scale.get(), 1.0 * this.scale.get(), this.OL_COLOR);
        Renderer.NORMAL.quad(d, d2, 1.0 * this.scale.get(), 16.0 * this.scale.get(), this.OL_COLOR);
        Renderer.NORMAL.quad(d + 15.0 * this.scale.get(), d2, 1.0 * this.scale.get(), 16.0 * this.scale.get(), this.OL_COLOR);
        Renderer.NORMAL.end();
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX();
        double d2 = this.box.getY();
        this.drawBlock(this.get(Facing.Left), d, d2 + 16.0 * this.scale.get());
        this.drawBlock(this.get(Facing.Front), d + 16.0 * this.scale.get(), d2);
        this.drawBlock(this.get(Facing.Right), d + 32.0 * this.scale.get(), d2 + 16.0 * this.scale.get());
        this.drawBlock(this.get(Facing.Back), d + 16.0 * this.scale.get(), d2 + 32.0 * this.scale.get());
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        this.box.setSize(48.0 * this.scale.get(), 48.0 * this.scale.get());
    }

    private void drawBlock(class_2350 class_23502, double d, double d2) {
        class_2248 class_22482;
        class_2248 class_22483 = class_22482 = class_23502 == class_2350.field_11033 ? class_2246.field_10540 : this.mc.field_1687.method_8320(this.mc.field_1724.method_24515().method_10093(class_23502)).method_26204();
        if (!this.safe.get().contains(class_22482)) {
            return;
        }
        RenderUtils.drawItem(class_22482.method_8389().method_7854(), (int)d, (int)d2, this.scale.get(), false);
        if (class_23502 == class_2350.field_11033) {
            return;
        }
        ((WorldRendererAccessor)this.mc.field_1769).getBlockBreakingInfos().values().forEach(arg_0 -> this.lambda$drawBlock$0(class_23502, d, d2, arg_0));
    }

    private static enum Facing {
        Left(-90),
        Right(90),
        Front(0),
        Back(180);

        int offset;

        private Facing(int n2) {
            this.offset = n2;
        }
    }
}

