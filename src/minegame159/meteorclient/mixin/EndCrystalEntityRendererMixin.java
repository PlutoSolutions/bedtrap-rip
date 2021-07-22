/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1511
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_630
 *  net.minecraft.class_892
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
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
    private void render(class_1511 endCrystalEntity, float f, float g, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, CallbackInfo ci) {
        Chams module = Modules.get().get(Chams.class);
        field_21736 = class_1921.method_23580((class_2960)(module.isActive() && module.crystals.get() != false && module.crystalsTexture.get() == false ? Chams.BLANK : field_4663));
    }

    @ModifyArgs(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V", ordinal=0))
    private void modifyScale(Args args) {
        Chams module = Modules.get().get(Chams.class);
        if (!module.isActive() || !module.crystals.get().booleanValue()) {
            return;
        }
        args.set(0, (Object)Float.valueOf(2.0f * module.crystalsScale.get().floatValue()));
        args.set(1, (Object)Float.valueOf(2.0f * module.crystalsScale.get().floatValue()));
        args.set(2, (Object)Float.valueOf(2.0f * module.crystalsScale.get().floatValue()));
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/EndCrystalEntityRenderer;getYOffset(Lnet/minecraft/entity/decoration/EndCrystalEntity;F)F"))
    private float getYOff(class_1511 crystal, float tickDelta) {
        Chams module = Modules.get().get(Chams.class);
        if (!module.isActive() || !module.crystals.get().booleanValue()) {
            return class_892.method_23155((class_1511)crystal, (float)tickDelta);
        }
        float f = (float)crystal.field_7034 + tickDelta;
        float g = class_3532.method_15374((float)(f * 0.2f)) / 2.0f + 0.5f;
        g = (g * g + g) * 0.4f * module.crystalsBounce.get().floatValue();
        return g - 1.4f;
    }

    @ModifyArgs(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/util/math/Vector3f;getDegreesQuaternion(F)Lnet/minecraft/util/math/Quaternion;"))
    private void modifySpeed(Args args) {
        Chams module = Modules.get().get(Chams.class);
        if (!module.isActive() || !module.crystals.get().booleanValue()) {
            return;
        }
        args.set(0, (Object)Float.valueOf(((Float)args.get(0)).floatValue() * module.crystalsRotationSpeed.get().floatValue()));
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=3))
    private void modifyCore(class_630 modelPart, class_4587 matrices, class_4588 vertices, int light, int overlay) {
        Chams module = Modules.get().get(Chams.class);
        if (!module.isActive() || !module.crystals.get().booleanValue()) {
            this.field_21003.method_22698(matrices, vertices, light, overlay);
            return;
        }
        if (module.renderCore.get().booleanValue()) {
            Color color = module.crystalsCoreColor.get();
            this.field_21003.method_22699(matrices, vertices, light, overlay, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
        }
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=1))
    private void modifyFrame1(class_630 modelPart, class_4587 matrices, class_4588 vertices, int light, int overlay) {
        Chams module = Modules.get().get(Chams.class);
        if (!module.isActive() || !module.crystals.get().booleanValue()) {
            this.field_21004.method_22698(matrices, vertices, light, overlay);
            return;
        }
        if (module.renderFrame1.get().booleanValue()) {
            Color color = module.crystalsFrame1Color.get();
            this.field_21004.method_22699(matrices, vertices, light, overlay, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
        }
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=2))
    private void modifyFrame2(class_630 modelPart, class_4587 matrices, class_4588 vertices, int light, int overlay) {
        Chams module = Modules.get().get(Chams.class);
        if (!module.isActive() || !module.crystals.get().booleanValue()) {
            this.field_21004.method_22698(matrices, vertices, light, overlay);
            return;
        }
        if (module.renderFrame2.get().booleanValue()) {
            Color color = module.crystalsFrame2Color.get();
            this.field_21004.method_22699(matrices, vertices, light, overlay, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
        }
    }
}

