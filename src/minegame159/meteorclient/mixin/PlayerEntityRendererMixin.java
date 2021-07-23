/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Chams;
import minegame159.meteorclient.systems.modules.render.HandView;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1007;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_630;
import net.minecraft.class_742;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={class_1007.class})
public class PlayerEntityRendererMixin {
    @ModifyArgs(method={"renderArm"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=0))
    private void modifyRenderLayer(Args args, class_4587 class_45872, class_4597 class_45972, int n, class_742 class_7422, class_630 class_6302, class_630 class_6303) {
        Chams chams = Modules.get().get(Chams.class);
        if (chams.isActive() && chams.hand.get().booleanValue()) {
            class_2960 class_29602 = chams.handTexture.get() != false ? class_7422.method_3117() : Chams.BLANK;
            args.set(1, (Object)class_45972.getBuffer(class_1921.method_23580((class_2960)class_29602)));
        }
    }

    @Redirect(method={"renderArm"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=0))
    private void redirectRenderMain(class_630 class_6302, class_4587 class_45872, class_4588 class_45882, int n, int n2) {
        Chams chams = Modules.get().get(Chams.class);
        if (chams.isActive() && chams.hand.get().booleanValue()) {
            Color color = chams.handColor.get();
            class_6302.method_22699(class_45872, class_45882, n, n2, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
        } else {
            class_6302.method_22698(class_45872, class_45882, n, n2);
        }
    }

    @Redirect(method={"renderArm"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=1))
    private void redirectRenderSleeve(class_630 class_6302, class_4587 class_45872, class_4588 class_45882, int n, int n2) {
        Chams chams = Modules.get().get(Chams.class);
        if (Modules.get().isActive(HandView.class)) {
            return;
        }
        if (chams.isActive() && chams.hand.get().booleanValue()) {
            Color color = chams.handColor.get();
            class_6302.method_22699(class_45872, class_45882, n, n2, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
        } else {
            class_6302.method_22698(class_45872, class_45882, n, n2);
        }
    }
}

