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
    private static final class_2960 TEXTURE = new class_2960("meteor-client", "textures/container.png");
    private final Setting<Boolean> echestNoItem;
    private final Setting<Double> scale;
    private final SettingGroup sgGeneral;
    private final class_1799[] inventory;

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX();
        double d2 = this.box.getY();
        class_1799 class_17992 = this.getContainer();
        if (class_17992 == null) {
            return;
        }
        this.drawBackground((int)d, (int)d2, class_17992);
        Utils.getItemsInContainerItem(class_17992, this.inventory);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                class_1799 class_17993 = this.inventory[i * 9 + j];
                if (class_17993 == null) continue;
                RenderUtils.drawItem(class_17993, (int)(d + (double)(8 + j * 18) * this.scale.get()), (int)(d2 + (double)(7 + i * 18) * this.scale.get()), this.scale.get(), true);
                if (0 >= 0) continue;
                return;
            }
        }
    }

    private class_1799 getContainer() {
        if (this.isInEditor()) {
            return class_1802.field_8466.method_7854();
        }
        class_1799 class_17992 = this.mc.field_1724.field_7514.method_7391();
        if (!(class_17992.method_7909() instanceof class_1747)) {
            class_17992 = this.mc.field_1724.method_6079();
        }
        if (class_17992.method_7909() == class_1802.field_8466) {
            return class_17992;
        }
        if (!(class_17992.method_7909() instanceof class_1747)) {
            return this.echestNoItem.get() != false ? class_1802.field_8466.method_7854() : null;
        }
        if (((class_1747)class_17992.method_7909()).method_7711() instanceof class_2480) {
            return class_17992;
        }
        if (((class_1747)class_17992.method_7909()).method_7711() instanceof class_4739) {
            return class_17992;
        }
        return this.echestNoItem.get() != false ? class_1802.field_8466.method_7854() : null;
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        this.box.setSize(176.0 * this.scale.get(), 67.0 * this.scale.get());
    }

    private void drawBackground(int n, int n2, class_1799 class_17992) {
        int n3 = (int)this.box.width;
        int n4 = (int)this.box.height;
        Color color = Utils.getShulkerColor(class_17992);
        RenderSystem.color4f((float)((float)color.r / 255.0f), (float)((float)color.g / 255.0f), (float)((float)color.b / 255.0f), (float)((float)color.a / 255.0f));
        this.mc.method_1531().method_22813(TEXTURE);
        class_332.method_25291((class_4587)Matrices.getMatrixStack(), (int)n, (int)n2, (int)0, (float)0.0f, (float)0.0f, (int)n3, (int)n4, (int)n4, (int)n3);
    }

    public ContainerViewerHud(HUD hUD) {
        super(hUD, "container-viewer", "Displays held containers.", false);
        this.sgGeneral = this.settings.getDefaultGroup();
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of container viewer.").defaultValue(3.0).min(0.1).sliderMin(0.1).max(10.0).build());
        this.echestNoItem = this.sgGeneral.add(new BoolSetting.Builder().name("echest-when-empty").description("Display contents of ender chest if not holding any other container.").defaultValue(false).build());
        this.inventory = new class_1799[27];
    }
}

