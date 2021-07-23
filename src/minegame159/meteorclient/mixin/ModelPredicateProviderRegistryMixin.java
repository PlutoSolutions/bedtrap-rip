/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_4184;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets={"net.minecraft.client.item.ModelPredicateProviderRegistry$2"})
public class ModelPredicateProviderRegistryMixin {
    @Redirect(method={"call(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/entity/LivingEntity;)F"}, at=@At(value="FIELD", target="Lnet/minecraft/entity/LivingEntity;yaw:F"))
    private float callLivingEntityGetYaw(class_1309 class_13092) {
        if (Modules.get().isActive(Freecam.class)) {
            return Utils.mc.field_1773.method_19418().method_19330();
        }
        return class_13092.field_6031;
    }

    @Inject(method={"getAngleToPos(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/entity/Entity;)D"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetAngleToPos(class_243 class_2432, class_1297 class_12972, CallbackInfoReturnable<Double> callbackInfoReturnable) {
        if (Modules.get().isActive(Freecam.class)) {
            class_4184 class_41842 = Utils.mc.field_1773.method_19418();
            callbackInfoReturnable.setReturnValue((Object)Math.atan2(class_2432.method_10215() - class_41842.method_19326().field_1350, class_2432.method_10216() - class_41842.method_19326().field_1352));
        }
    }
}

