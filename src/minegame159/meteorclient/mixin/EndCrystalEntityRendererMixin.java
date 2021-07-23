/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Chams;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1511;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_630;
import net.minecraft.class_892;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={class_892.class})
public abstract class EndCrystalEntityRendererMixin {
    @Mutable
    @Shadow
    @Final
    private static class_1921 field_21736;
    @Shadow
    @Final
    private static class_2960 field_4663;
    @Shadow
    @Final
    private class_630 field_21003;
    @Shadow
    @Final
    private class_630 field_21004;

    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    private void render(class_1511 class_15112, float f, float f2, class_4587 class_45872, class_4597 class_45972, int n, CallbackInfo callbackInfo) {
        Chams chams = Modules.get().get(Chams.class);
        field_21736 = class_1921.method_23580((class_2960)(chams.isActive() && chams.crystals.get() != false && chams.crystalsTexture.get() == false ? Chams.BLANK : field_4663));
    }

    @ModifyArgs(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V", ordinal=0))
    private void modifyScale(Args args) {
        Chams chams = Modules.get().get(Chams.class);
        if (!chams.isActive() || !chams.crystals.get().booleanValue()) {
            return;
        }
        args.set(0, (Object)Float.valueOf(2.0f * chams.crystalsScale.get().floatValue()));
        args.set(1, (Object)Float.valueOf(2.0f * chams.crystalsScale.get().floatValue()));
        args.set(2, (Object)Float.valueOf(2.0f * chams.crystalsScale.get().floatValue()));
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/EndCrystalEntityRenderer;getYOffset(Lnet/minecraft/entity/decoration/EndCrystalEntity;F)F"))
    private float getYOff(class_1511 class_15112, float f) {
        Chams chams = Modules.get().get(Chams.class);
        if (!chams.isActive() || !chams.crystals.get().booleanValue()) {
            return class_892.method_23155((class_1511)class_15112, (float)f);
        }
        float f2 = (float)class_15112.field_7034 + f;
        float f3 = class_3532.method_15374((float)(f2 * 0.2f)) / 2.0f + 0.5f;
        f3 = (f3 * f3 + f3) * 0.4f * chams.crystalsBounce.get().floatValue();
        return f3 - 1.4f;
    }

    @ModifyArgs(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/util/math/Vector3f;getDegreesQuaternion(F)Lnet/minecraft/util/math/Quaternion;"))
    private void modifySpeed(Args args) {
        Chams chams = Modules.get().get(Chams.class);
        if (!chams.isActive() || !chams.crystals.get().booleanValue()) {
            return;
        }
        args.set(0, (Object)Float.valueOf(((Float)args.get(0)).floatValue() * chams.crystalsRotationSpeed.get().floatValue()));
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=3))
    private void modifyCore(class_630 class_6302, class_4587 class_45872, class_4588 class_45882, int n, int n2) {
        Chams chams = Modules.get().get(Chams.class);
        if (!chams.isActive() || !chams.crystals.get().booleanValue()) {
            this.field_21003.method_22698(class_45872, class_45882, n, n2);
            return;
        }
        if (chams.renderCore.get().booleanValue()) {
            Color color = chams.crystalsCoreColor.get();
            this.field_21003.method_22699(class_45872, class_45882, n, n2, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
        }
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=1))
    private void modifyFrame1(class_630 class_6302, class_4587 class_45872, class_4588 class_45882, int n, int n2) {
        Chams chams = Modules.get().get(Chams.class);
        if (!chams.isActive() || !chams.crystals.get().booleanValue()) {
            this.field_21004.method_22698(class_45872, class_45882, n, n2);
            return;
        }
        if (chams.renderFrame1.get().booleanValue()) {
            Color color = chams.crystalsFrame1Color.get();
            this.field_21004.method_22699(class_45872, class_45882, n, n2, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
        }
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=2))
    private void modifyFrame2(class_630 class_6302, class_4587 class_45872, class_4588 class_45882, int n, int n2) {
        Chams chams = Modules.get().get(Chams.class);
        if (!chams.isActive() || !chams.crystals.get().booleanValue()) {
            this.field_21004.method_22698(class_45872, class_45882, n, n2);
            return;
        }
        if (chams.renderFrame2.get().booleanValue()) {
            Color color = chams.crystalsFrame2Color.get();
            this.field_21004.method_22699(class_45872, class_45882, n, n2, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
        }
    }
}

