/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.Matrices;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.render.RenderUtils;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class InventoryViewerHud
extends HudElement {
    private final SettingGroup sgGeneral;
    private final class_1799[] editorInv;
    private final Setting<Double> scale;
    private final Setting<SettingColor> color;
    private static final class_2960 TEXTURE = new class_2960("meteor-client", "textures/container.png");
    private final Setting<Background> background;
    private static final class_2960 TEXTURE_TRANSPARENT = new class_2960("meteor-client", "textures/container-transparent.png");

    private class_1799 getStack(int n) {
        if (this.isInEditor()) {
            return this.editorInv[n - 9];
        }
        return this.mc.field_1724.field_7514.method_5438(n);
    }

    private boolean lambda$new$0() {
        return this.background.get() != Background.None;
    }

    public InventoryViewerHud(HUD hUD) {
        super(hUD, "inventory-viewer", "Displays your inventory.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of inventory viewer.").defaultValue(3.0).min(0.1).sliderMin(0.1).max(10.0).build());
        this.background = this.sgGeneral.add(new EnumSetting.Builder().name("background").description("Background of inventory viewer.").defaultValue(Background.Texture).build());
        this.color = this.sgGeneral.add(new ColorSetting.Builder().name("background-color").description("Color of the background.").defaultValue(new SettingColor(255, 255, 255)).visible(this::lambda$new$0).build());
        this.editorInv = new class_1799[27];
        this.editorInv[0] = class_1802.field_8288.method_7854();
        this.editorInv[5] = new class_1799((class_1935)class_1802.field_8367, 6);
        this.editorInv[19] = new class_1799((class_1935)class_1802.field_8281, 64);
        this.editorInv[this.editorInv.length - 1] = class_1802.field_22025.method_7854();
    }

    private void drawBackground(int n, int n2) {
        int n3 = (int)this.box.width;
        int n4 = (int)this.box.height;
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$render$hud$modules$InventoryViewerHud$Background[this.background.get().ordinal()]) {
            case 1: 
            case 2: {
                RenderSystem.color4f((float)((float)this.color.get().r / 255.0f), (float)((float)this.color.get().g / 255.0f), (float)((float)this.color.get().b / 255.0f), (float)((float)this.color.get().a / 255.0f));
                this.mc.method_1531().method_22813(this.background.get() == Background.Texture ? TEXTURE : TEXTURE_TRANSPARENT);
                class_332.method_25291((class_4587)Matrices.getMatrixStack(), (int)n, (int)n2, (int)0, (float)0.0f, (float)0.0f, (int)n3, (int)n4, (int)n4, (int)n3);
                break;
            }
            case 3: {
                Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
                Renderer.NORMAL.quad(n, n2, n3, n4, this.color.get());
                Renderer.NORMAL.end();
            }
        }
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX();
        double d2 = this.box.getY();
        if (this.background.get() != Background.None) {
            this.drawBackground((int)d, (int)d2);
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                class_1799 class_17992 = this.getStack(9 + i * 9 + j);
                if (class_17992 == null) continue;
                RenderUtils.drawItem(class_17992, (int)(d + (double)(8 + j * 18) * this.scale.get()), (int)(d2 + (double)(7 + i * 18) * this.scale.get()), this.scale.get(), true);
                if (2 > 0) continue;
                return;
            }
            if (2 != 0) continue;
            return;
        }
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        this.box.setSize(176.0 * this.scale.get(), 67.0 * this.scale.get());
    }

    public static enum Background {
        None,
        Texture,
        Outline,
        Flat;

    }
}

