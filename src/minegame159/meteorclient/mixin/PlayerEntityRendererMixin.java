/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1007
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_630
 *  net.minecraft.class_742
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
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
    private void modifyRenderLayer(Args args, class_4587 matrices, class_4597 vertexConsumers, int light, class_742 player, class_630 arm, class_630 sleeve) {
        Chams chams = Modules.get().get(Chams.class);
        if (chams.isActive() && chams.hand.get().booleanValue()) {
            class_2960 texture = chams.handTexture.get() != false ? player.method_3117() : Chams.BLANK;
            args.set(1, (Object)vertexConsumers.getBuffer(class_1921.method_23580((class_2960)texture)));
        }
    }

    @Redirect(method={"renderArm"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=0))
    private void redirectRenderMain(class_630 modelPart, class_4587 matrices, class_4588 vertices, int light, int overlay) {
        Chams chams = Modules.get().get(Chams.class);
        if (chams.isActive() && chams.hand.get().booleanValue()) {
            Color color = chams.handColor.get();
            modelPart.method_22699(matrices, vertices, light, overlay, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
        } else {
            modelPart.method_22698(matrices, vertices, light, overlay);
        }
    }

    @Redirect(method={"renderArm"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V", ordinal=1))
    private void redirectRenderSleeve(class_630 modelPart, class_4587 matrices, class_4588 vertices, int light, int overlay) {
        Chams chams = Modules.get().get(Chams.class);
        if (Modules.get().isActive(HandView.class)) {
            return;
        }
        if (chams.isActive() && chams.hand.get().booleanValue()) {
            Color color = chams.handColor.get();
            modelPart.method_22699(matrices, vertices, light, overlay, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
        } else {
            modelPart.method_22698(matrices, vertices, light, overlay);
        }
    }
}

