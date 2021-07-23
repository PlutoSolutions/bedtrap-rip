/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import net.minecraft.class_1088;
import net.minecraft.class_1160;
import net.minecraft.class_1921;
import net.minecraft.class_2215;
import net.minecraft.class_2350;
import net.minecraft.class_2546;
import net.minecraft.class_2573;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_630;
import net.minecraft.class_823;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_823.class})
public class BannerBlockEntityRendererMixin {
    @Final
    @Shadow
    private class_630 field_20811;
    @Final
    @Shadow
    private class_630 field_20812;

    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    private void render(class_2573 class_25732, float f, class_4587 class_45872, class_4597 class_45972, int n, int n2, CallbackInfo callbackInfo) {
        if (class_25732.method_10997() != null) {
            NoRender.BannerRenderMode bannerRenderMode = Modules.get().get(NoRender.class).getBannerRenderMode();
            if (bannerRenderMode == NoRender.BannerRenderMode.None) {
                callbackInfo.cancel();
            } else if (bannerRenderMode == NoRender.BannerRenderMode.Pillar) {
                class_2680 class_26802 = class_25732.method_11010();
                if (class_26802.method_26204() instanceof class_2215) {
                    this.field_20811.field_3665 = true;
                    this.field_20812.field_3665 = false;
                    this.renderPillar(class_25732, class_45872, class_45972, n, n2);
                } else {
                    this.field_20811.field_3665 = false;
                    this.field_20812.field_3665 = true;
                    this.renderCrossbar(class_25732, class_45872, class_45972, n, n2);
                }
                callbackInfo.cancel();
            }
        }
    }

    private void renderPillar(class_2573 class_25732, class_4587 class_45872, class_4597 class_45972, int n, int n2) {
        class_45872.method_22903();
        class_2680 class_26802 = class_25732.method_11010();
        class_45872.method_22904(0.5, 0.5, 0.5);
        float f = (float)(-((Integer)class_26802.method_11654((class_2769)class_2215.field_9924)).intValue() * 360) / 16.0f;
        class_45872.method_22907(class_1160.field_20705.method_23214(f));
        class_45872.method_22903();
        class_45872.method_22905(0.6666667f, -0.6666667f, -0.6666667f);
        class_4588 class_45882 = class_1088.field_20847.method_24145(class_45972, class_1921::method_23572);
        this.field_20811.method_22698(class_45872, class_45882, n, n2);
        class_45872.method_22909();
        class_45872.method_22909();
    }

    private void renderCrossbar(class_2573 class_25732, class_4587 class_45872, class_4597 class_45972, int n, int n2) {
        class_45872.method_22903();
        class_2680 class_26802 = class_25732.method_11010();
        class_45872.method_22904(0.5, -0.1666666716337204, 0.5);
        float f = -((class_2350)class_26802.method_11654((class_2769)class_2546.field_11722)).method_10144();
        class_45872.method_22907(class_1160.field_20705.method_23214(f));
        class_45872.method_22904(0.0, -0.3125, -0.4375);
        class_45872.method_22903();
        class_45872.method_22905(0.6666667f, -0.6666667f, -0.6666667f);
        class_4588 class_45882 = class_1088.field_20847.method_24145(class_45972, class_1921::method_23572);
        this.field_20812.method_22698(class_45872, class_45882, n, n2);
        class_45872.method_22909();
        class_45872.method_22909();
    }
}

