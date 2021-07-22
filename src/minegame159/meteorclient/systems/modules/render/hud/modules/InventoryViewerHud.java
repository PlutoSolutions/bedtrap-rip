/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_290
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
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
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ class_1799[] editorInv;
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ Setting<SettingColor> color;
    private static final /* synthetic */ class_2960 TEXTURE;
    private final /* synthetic */ Setting<Background> background;
    private static final /* synthetic */ class_2960 TEXTURE_TRANSPARENT;

    static {
        TEXTURE = new class_2960("meteor-client", "textures/container.png");
        TEXTURE_TRANSPARENT = new class_2960("meteor-client", "textures/container-transparent.png");
    }

    private class_1799 getStack(int llIIllll) {
        InventoryViewerHud llIlIIlI;
        if (llIlIIlI.isInEditor()) {
            return llIlIIlI.editorInv[llIIllll - 9];
        }
        return llIlIIlI.mc.field_1724.field_7514.method_5438(llIIllll);
    }

    public InventoryViewerHud(HUD lllIllII) {
        super(lllIllII, "inventory-viewer", "Displays your inventory.");
        InventoryViewerHud lllIllll;
        lllIllll.sgGeneral = lllIllll.settings.getDefaultGroup();
        lllIllll.scale = lllIllll.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of inventory viewer.").defaultValue(3.0).min(0.1).sliderMin(0.1).max(10.0).build());
        lllIllll.background = lllIllll.sgGeneral.add(new EnumSetting.Builder().name("background").description("Background of inventory viewer.").defaultValue(Background.Texture).build());
        lllIllll.color = lllIllll.sgGeneral.add(new ColorSetting.Builder().name("background-color").description("Color of the background.").defaultValue(new SettingColor(255, 255, 255)).visible(() -> {
            InventoryViewerHud lIllllIl;
            return lIllllIl.background.get() != Background.None;
        }).build());
        lllIllll.editorInv = new class_1799[27];
        lllIllll.editorInv[0] = class_1802.field_8288.method_7854();
        lllIllll.editorInv[5] = new class_1799((class_1935)class_1802.field_8367, 6);
        lllIllll.editorInv[19] = new class_1799((class_1935)class_1802.field_8281, 64);
        lllIllll.editorInv[lllIllll.editorInv.length - 1] = class_1802.field_22025.method_7854();
    }

    private void drawBackground(int llIIlIII, int llIIIlll) {
        InventoryViewerHud llIIlIIl;
        int llIIIllI = (int)llIIlIIl.box.width;
        int llIIIlIl = (int)llIIlIIl.box.height;
        switch (llIIlIIl.background.get()) {
            case Texture: 
            case Outline: {
                RenderSystem.color4f((float)((float)llIIlIIl.color.get().r / 255.0f), (float)((float)llIIlIIl.color.get().g / 255.0f), (float)((float)llIIlIIl.color.get().b / 255.0f), (float)((float)llIIlIIl.color.get().a / 255.0f));
                llIIlIIl.mc.method_1531().method_22813(llIIlIIl.background.get() == Background.Texture ? TEXTURE : TEXTURE_TRANSPARENT);
                class_332.method_25291((class_4587)Matrices.getMatrixStack(), (int)llIIlIII, (int)llIIIlll, (int)0, (float)0.0f, (float)0.0f, (int)llIIIllI, (int)llIIIlIl, (int)llIIIlIl, (int)llIIIllI);
                break;
            }
            case Flat: {
                Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
                Renderer.NORMAL.quad(llIIlIII, llIIIlll, llIIIllI, llIIIlIl, llIIlIIl.color.get());
                Renderer.NORMAL.end();
            }
        }
    }

    @Override
    public void render(HudRenderer llIlllIl) {
        InventoryViewerHud llIllIlI;
        double llIlllII = llIllIlI.box.getX();
        double llIllIll = llIllIlI.box.getY();
        if (llIllIlI.background.get() != Background.None) {
            llIllIlI.drawBackground((int)llIlllII, (int)llIllIll);
        }
        for (int llIlllll = 0; llIlllll < 3; ++llIlllll) {
            for (int lllIIIII = 0; lllIIIII < 9; ++lllIIIII) {
                class_1799 lllIIIIl = llIllIlI.getStack(9 + llIlllll * 9 + lllIIIII);
                if (lllIIIIl == null) continue;
                RenderUtils.drawItem(lllIIIIl, (int)(llIlllII + (double)(8 + lllIIIII * 18) * llIllIlI.scale.get()), (int)(llIllIll + (double)(7 + llIlllll * 18) * llIllIlI.scale.get()), llIllIlI.scale.get(), true);
            }
        }
    }

    @Override
    public void update(HudRenderer lllIlIIl) {
        InventoryViewerHud lllIlIII;
        lllIlIII.box.setSize(176.0 * lllIlIII.scale.get(), 67.0 * lllIlIII.scale.get());
    }

    public static enum Background {
        None,
        Texture,
        Outline,
        Flat;


        private Background() {
            Background llIIlIlIllI;
        }
    }
}

