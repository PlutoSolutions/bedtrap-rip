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
    public static final TooltipSupplier EMPTY;
    protected final TooltipSupplier tooltipSupplier;
    protected final PressAction onPress;
    public static final class_2960 BUTTON_TEXTURE;

    public void method_25306() {
        this.onPress.onPress(this);
    }

    public void method_25352(class_4587 class_45872, int n, int n2) {
        this.tooltipSupplier.onTooltip(this, class_45872, n, n2);
    }

    public void method_25359(class_4587 class_45872, int n, int n2, float f) {
        this.customRender(class_45872);
        if (this.method_25367()) {
            this.method_25352(class_45872, n, n2);
        }
    }

    public MeteorButtonWidget(int n, int n2, int n3, int n4, class_2561 class_25612, PressAction pressAction) {
        this(n, n2, n3, n4, class_25612, pressAction, EMPTY);
    }

    static {
        BUTTON_TEXTURE = new class_2960("meteor-client", "textures/button.png");
        EMPTY = MeteorButtonWidget::lambda$static$0;
    }

    public void customRender(class_4587 class_45872) {
        class_327 class_3272 = Utils.mc.field_1772;
        Utils.mc.method_1531().method_22813(BUTTON_TEXTURE);
        int n = this.field_22763 ? 0xFFFFFF : 0xA0A0A0;
        RenderSystem.color4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        MeteorButtonWidget.method_25290((class_4587)class_45872, (int)this.field_22760, (int)this.field_22761, (float)0.0f, (float)(this.method_25367() ? 12.0f : 0.0f), (int)this.field_22758, (int)this.field_22759, (int)40, (int)24);
        MeteorButtonWidget.method_27534((class_4587)class_45872, (class_327)class_3272, (class_2561)this.method_25369(), (int)(this.field_22760 + this.field_22758 / 2), (int)(this.field_22761 + (this.field_22759 - 8) / 2), (int)(n | class_3532.method_15386((float)(this.field_22765 * 255.0f)) << 24));
    }

    private static void lambda$static$0(MeteorButtonWidget meteorButtonWidget, class_4587 class_45872, int n, int n2) {
    }

    public MeteorButtonWidget(int n, int n2, int n3, int n4, class_2561 class_25612, PressAction pressAction, TooltipSupplier tooltipSupplier) {
        super(n, n2, n3, n4, class_25612);
        this.onPress = pressAction;
        this.tooltipSupplier = tooltipSupplier;
    }

    public static interface PressAction {
        public void onPress(MeteorButtonWidget var1);
    }

    public static interface TooltipSupplier {
        public void onTooltip(MeteorButtonWidget var1, class_4587 var2, int var3, int var4);
    }
}

