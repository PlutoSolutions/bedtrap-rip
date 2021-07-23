/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.render.RenderBlockEntityEvent;
import net.minecraft.class_2586;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_824;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_824.class})
public class BlockEntityRenderDispatcherMixin {
    @Inject(method={"render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private <E extends class_2586> void onRenderEntity(E e, float f, class_4587 class_45872, class_4597 class_45972, CallbackInfo callbackInfo) {
        RenderBlockEntityEvent renderBlockEntityEvent = MeteorClient.EVENT_BUS.post(RenderBlockEntityEvent.get(e));
        if (renderBlockEntityEvent.isCancelled()) {
            callbackInfo.cancel();
        }
    }
}

