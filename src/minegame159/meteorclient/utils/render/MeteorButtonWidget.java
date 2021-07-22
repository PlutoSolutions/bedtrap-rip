/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_327
 *  net.minecraft.class_3532
 *  net.minecraft.class_4264
 *  net.minecraft.class_4587
 */
package minegame159.meteorclient.utils.render;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_327;
import net.minecraft.class_3532;
import net.minecraft.class_4264;
import net.minecraft.class_4587;

public class MeteorButtonWidget
extends class_4264 {
    public static final /* synthetic */ TooltipSupplier EMPTY;
    protected final /* synthetic */ TooltipSupplier tooltipSupplier;
    protected final /* synthetic */ PressAction onPress;
    public static final /* synthetic */ class_2960 BUTTON_TEXTURE;

    public void method_25306() {
        MeteorButtonWidget lllllllllllllllllIlIllIllIIlIIll;
        lllllllllllllllllIlIllIllIIlIIll.onPress.onPress(lllllllllllllllllIlIllIllIIlIIll);
    }

    public void method_25352(class_4587 lllllllllllllllllIlIllIlIllIIlll, int lllllllllllllllllIlIllIlIllIIllI, int lllllllllllllllllIlIllIlIllIlIIl) {
        MeteorButtonWidget lllllllllllllllllIlIllIlIllIlIII;
        lllllllllllllllllIlIllIlIllIlIII.tooltipSupplier.onTooltip(lllllllllllllllllIlIllIlIllIlIII, lllllllllllllllllIlIllIlIllIIlll, lllllllllllllllllIlIllIlIllIIllI, lllllllllllllllllIlIllIlIllIlIIl);
    }

    public void method_25359(class_4587 lllllllllllllllllIlIllIllIIIIlll, int lllllllllllllllllIlIllIllIIIIIll, int lllllllllllllllllIlIllIllIIIIIlI, float lllllllllllllllllIlIllIllIIIIIIl) {
        MeteorButtonWidget lllllllllllllllllIlIllIllIIIIIII;
        lllllllllllllllllIlIllIllIIIIIII.customRender(lllllllllllllllllIlIllIllIIIIlll);
        if (lllllllllllllllllIlIllIllIIIIIII.method_25367()) {
            lllllllllllllllllIlIllIllIIIIIII.method_25352(lllllllllllllllllIlIllIllIIIIlll, lllllllllllllllllIlIllIllIIIIIll, lllllllllllllllllIlIllIllIIIIIlI);
        }
    }

    public MeteorButtonWidget(int lllllllllllllllllIlIllIllIllIIll, int lllllllllllllllllIlIllIllIlllIIl, int lllllllllllllllllIlIllIllIllIIIl, int lllllllllllllllllIlIllIllIllIIII, class_2561 lllllllllllllllllIlIllIllIlIllll, PressAction lllllllllllllllllIlIllIllIlIlllI) {
        lllllllllllllllllIlIllIllIlllIll(lllllllllllllllllIlIllIllIllIIll, lllllllllllllllllIlIllIllIlllIIl, lllllllllllllllllIlIllIllIllIIIl, lllllllllllllllllIlIllIllIllIIII, lllllllllllllllllIlIllIllIlIllll, lllllllllllllllllIlIllIllIlIlllI, EMPTY);
        MeteorButtonWidget lllllllllllllllllIlIllIllIlllIll;
    }

    static {
        BUTTON_TEXTURE = new class_2960("meteor-client", "textures/button.png");
        EMPTY = (lllllllllllllllllIlIllIlIllIIlII, lllllllllllllllllIlIllIlIllIIIll, lllllllllllllllllIlIllIlIllIIIlI, lllllllllllllllllIlIllIlIllIIIIl) -> {};
    }

    public void customRender(class_4587 lllllllllllllllllIlIllIlIlllIlll) {
        MeteorButtonWidget lllllllllllllllllIlIllIlIlllIlII;
        class_327 lllllllllllllllllIlIllIlIlllIllI = Utils.mc.field_1772;
        Utils.mc.method_1531().method_22813(BUTTON_TEXTURE);
        int lllllllllllllllllIlIllIlIlllIlIl = lllllllllllllllllIlIllIlIlllIlII.field_22763 ? 0xFFFFFF : 0xA0A0A0;
        RenderSystem.color4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        MeteorButtonWidget.method_25290((class_4587)lllllllllllllllllIlIllIlIlllIlll, (int)lllllllllllllllllIlIllIlIlllIlII.field_22760, (int)lllllllllllllllllIlIllIlIlllIlII.field_22761, (float)0.0f, (float)(lllllllllllllllllIlIllIlIlllIlII.method_25367() ? 12.0f : 0.0f), (int)lllllllllllllllllIlIllIlIlllIlII.field_22758, (int)lllllllllllllllllIlIllIlIlllIlII.field_22759, (int)40, (int)24);
        MeteorButtonWidget.method_27534((class_4587)lllllllllllllllllIlIllIlIlllIlll, (class_327)lllllllllllllllllIlIllIlIlllIllI, (class_2561)lllllllllllllllllIlIllIlIlllIlII.method_25369(), (int)(lllllllllllllllllIlIllIlIlllIlII.field_22760 + lllllllllllllllllIlIllIlIlllIlII.field_22758 / 2), (int)(lllllllllllllllllIlIllIlIlllIlII.field_22761 + (lllllllllllllllllIlIllIlIlllIlII.field_22759 - 8) / 2), (int)(lllllllllllllllllIlIllIlIlllIlIl | class_3532.method_15386((float)(lllllllllllllllllIlIllIlIlllIlII.field_22765 * 255.0f)) << 24));
    }

    public MeteorButtonWidget(int lllllllllllllllllIlIllIllIIlllII, int lllllllllllllllllIlIllIllIlIIIll, int lllllllllllllllllIlIllIllIlIIIlI, int lllllllllllllllllIlIllIllIlIIIIl, class_2561 lllllllllllllllllIlIllIllIIllIII, PressAction lllllllllllllllllIlIllIllIIlIlll, TooltipSupplier lllllllllllllllllIlIllIllIIlIllI) {
        super(lllllllllllllllllIlIllIllIIlllII, lllllllllllllllllIlIllIllIlIIIll, lllllllllllllllllIlIllIllIlIIIlI, lllllllllllllllllIlIllIllIlIIIIl, lllllllllllllllllIlIllIllIIllIII);
        MeteorButtonWidget lllllllllllllllllIlIllIllIlIIlIl;
        lllllllllllllllllIlIllIllIlIIlIl.onPress = lllllllllllllllllIlIllIllIIlIlll;
        lllllllllllllllllIlIllIllIlIIlIl.tooltipSupplier = lllllllllllllllllIlIllIllIIlIllI;
    }

    public static interface PressAction {
        public void onPress(MeteorButtonWidget var1);
    }

    public static interface TooltipSupplier {
        public void onTooltip(MeteorButtonWidget var1, class_4587 var2, int var3, int var4);
    }
}

