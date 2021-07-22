/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1747
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2480
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 *  net.minecraft.class_4739
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.rendering.Matrices;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.RenderUtils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2480;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_4739;

public class ContainerViewerHud
extends HudElement {
    private static final /* synthetic */ class_2960 TEXTURE;
    private final /* synthetic */ Setting<Boolean> echestNoItem;
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ class_1799[] inventory;

    @Override
    public void render(HudRenderer lIlllIIlIlIlIl) {
        ContainerViewerHud lIlllIIlIlIllI;
        double lIlllIIlIlIlII = lIlllIIlIlIllI.box.getX();
        double lIlllIIlIlIIll = lIlllIIlIlIllI.box.getY();
        class_1799 lIlllIIlIlIIlI = lIlllIIlIlIllI.getContainer();
        if (lIlllIIlIlIIlI == null) {
            return;
        }
        lIlllIIlIlIllI.drawBackground((int)lIlllIIlIlIlII, (int)lIlllIIlIlIIll, lIlllIIlIlIIlI);
        Utils.getItemsInContainerItem(lIlllIIlIlIIlI, lIlllIIlIlIllI.inventory);
        for (int lIlllIIlIlIlll = 0; lIlllIIlIlIlll < 3; ++lIlllIIlIlIlll) {
            for (int lIlllIIlIllIII = 0; lIlllIIlIllIII < 9; ++lIlllIIlIllIII) {
                class_1799 lIlllIIlIllIIl = lIlllIIlIlIllI.inventory[lIlllIIlIlIlll * 9 + lIlllIIlIllIII];
                if (lIlllIIlIllIIl == null) continue;
                RenderUtils.drawItem(lIlllIIlIllIIl, (int)(lIlllIIlIlIlII + (double)(8 + lIlllIIlIllIII * 18) * lIlllIIlIlIllI.scale.get()), (int)(lIlllIIlIlIIll + (double)(7 + lIlllIIlIlIlll * 18) * lIlllIIlIlIllI.scale.get()), lIlllIIlIlIllI.scale.get(), true);
            }
        }
    }

    private class_1799 getContainer() {
        ContainerViewerHud lIlllIIlIIIllI;
        if (lIlllIIlIIIllI.isInEditor()) {
            return class_1802.field_8466.method_7854();
        }
        class_1799 lIlllIIlIIIlll = lIlllIIlIIIllI.mc.field_1724.field_7514.method_7391();
        if (!(lIlllIIlIIIlll.method_7909() instanceof class_1747)) {
            lIlllIIlIIIlll = lIlllIIlIIIllI.mc.field_1724.method_6079();
        }
        if (lIlllIIlIIIlll.method_7909() == class_1802.field_8466) {
            return lIlllIIlIIIlll;
        }
        if (!(lIlllIIlIIIlll.method_7909() instanceof class_1747)) {
            return lIlllIIlIIIllI.echestNoItem.get() != false ? class_1802.field_8466.method_7854() : null;
        }
        if (((class_1747)lIlllIIlIIIlll.method_7909()).method_7711() instanceof class_2480) {
            return lIlllIIlIIIlll;
        }
        if (((class_1747)lIlllIIlIIIlll.method_7909()).method_7711() instanceof class_4739) {
            return lIlllIIlIIIlll;
        }
        return lIlllIIlIIIllI.echestNoItem.get() != false ? class_1802.field_8466.method_7854() : null;
    }

    @Override
    public void update(HudRenderer lIlllIIllIIIlI) {
        ContainerViewerHud lIlllIIllIIIIl;
        lIlllIIllIIIIl.box.setSize(176.0 * lIlllIIllIIIIl.scale.get(), 67.0 * lIlllIIllIIIIl.scale.get());
    }

    static {
        TEXTURE = new class_2960("meteor-client", "textures/container.png");
    }

    private void drawBackground(int lIlllIIIllIlIl, int lIlllIIIlllIll, class_1799 lIlllIIIlllIlI) {
        ContainerViewerHud lIlllIIIllllIl;
        int lIlllIIIlllIIl = (int)lIlllIIIllllIl.box.width;
        int lIlllIIIlllIII = (int)lIlllIIIllllIl.box.height;
        Color lIlllIIIllIlll = Utils.getShulkerColor(lIlllIIIlllIlI);
        RenderSystem.color4f((float)((float)lIlllIIIllIlll.r / 255.0f), (float)((float)lIlllIIIllIlll.g / 255.0f), (float)((float)lIlllIIIllIlll.b / 255.0f), (float)((float)lIlllIIIllIlll.a / 255.0f));
        lIlllIIIllllIl.mc.method_1531().method_22813(TEXTURE);
        class_332.method_25291((class_4587)Matrices.getMatrixStack(), (int)lIlllIIIllIlIl, (int)lIlllIIIlllIll, (int)0, (float)0.0f, (float)0.0f, (int)lIlllIIIlllIIl, (int)lIlllIIIlllIII, (int)lIlllIIIlllIII, (int)lIlllIIIlllIIl);
    }

    public ContainerViewerHud(HUD lIlllIIllIIlll) {
        super(lIlllIIllIIlll, "container-viewer", "Displays held containers.", false);
        ContainerViewerHud lIlllIIllIlIII;
        lIlllIIllIlIII.sgGeneral = lIlllIIllIlIII.settings.getDefaultGroup();
        lIlllIIllIlIII.scale = lIlllIIllIlIII.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of container viewer.").defaultValue(3.0).min(0.1).sliderMin(0.1).max(10.0).build());
        lIlllIIllIlIII.echestNoItem = lIlllIIllIlIII.sgGeneral.add(new BoolSetting.Builder().name("echest-when-empty").description("Display contents of ender chest if not holding any other container.").defaultValue(false).build());
        lIlllIIllIlIII.inventory = new class_1799[27];
    }
}

