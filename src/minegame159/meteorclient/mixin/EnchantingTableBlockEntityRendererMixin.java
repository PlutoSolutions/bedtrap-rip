/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_557
 *  net.minecraft.class_828
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_557;
import net.minecraft.class_828;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_828.class})
public class EnchantingTableBlockEntityRendererMixin {
    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/model/BookModel;method_24184(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"))
    private void onRenderBookModelRenderProxy(class_557 model, class_4587 matrixStack, class_4588 vertexConsumer, int i, int j, float f, float g, float h, float k) {
        if (!Modules.get().get(NoRender.class).noEnchTableBook()) {
            model.method_24184(matrixStack, vertexConsumer, i, j, f, g, h, k);
        }
    }
}

