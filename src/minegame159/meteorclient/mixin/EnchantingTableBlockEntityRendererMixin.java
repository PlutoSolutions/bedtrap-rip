/*
 * Decompiled with CFR 0.151.
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
    private void onRenderBookModelRenderProxy(class_557 class_5572, class_4587 class_45872, class_4588 class_45882, int n, int n2, float f, float f2, float f3, float f4) {
        if (!Modules.get().get(NoRender.class).noEnchTableBook()) {
            class_5572.method_24184(class_45872, class_45882, n, n2, f, f2, f3, f4);
        }
    }
}

