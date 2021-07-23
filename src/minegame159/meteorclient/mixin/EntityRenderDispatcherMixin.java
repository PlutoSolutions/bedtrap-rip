/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixininterface.IBox;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.combat.Hitboxes;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_898;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={class_898.class})
public class EntityRenderDispatcherMixin {
    @Shadow
    public class_4184 field_4686;

    @Inject(method={"drawBox"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/WorldRenderer;drawBox(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/util/math/Box;FFFF)V")}, locals=LocalCapture.CAPTURE_FAILSOFT)
    private void onDrawBox(class_4587 class_45872, class_4588 class_45882, class_1297 class_12972, float f, float f2, float f3, CallbackInfo callbackInfo, class_238 class_2383) {
        double d = Modules.get().get(Hitboxes.class).getEntityValue(class_12972);
        if (d != 0.0) {
            ((IBox)class_2383).expand(d);
        }
    }

    @Inject(method={"getSquaredDistanceToCamera(Lnet/minecraft/entity/Entity;)D"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetSquaredDistanceToCameraEntity(class_1297 class_12972, CallbackInfoReturnable<Double> callbackInfoReturnable) {
        if (this.field_4686 == null) {
            callbackInfoReturnable.setReturnValue((Object)0.0);
        }
    }

    @Inject(method={"getSquaredDistanceToCamera(DDD)D"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetSquaredDistanceToCameraXYZ(double d, double d2, double d3, CallbackInfoReturnable<Double> callbackInfoReturnable) {
        if (this.field_4686 == null) {
            callbackInfoReturnable.setReturnValue((Object)0.0);
        }
    }
}

