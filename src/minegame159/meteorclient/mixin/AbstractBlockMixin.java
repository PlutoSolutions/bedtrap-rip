/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.AmbientOcclusionEvent;
import minegame159.meteorclient.events.world.FluidCollisionShapeEvent;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_4970.class})
public class AbstractBlockMixin {
    @Inject(method={"getAmbientOcclusionLightLevel"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetAmbientOcclusionLightLevel(class_2680 class_26802, class_1922 class_19222, class_2338 class_23382, CallbackInfoReturnable<Float> callbackInfoReturnable) {
        AmbientOcclusionEvent ambientOcclusionEvent = MeteorClient.EVENT_BUS.post(AmbientOcclusionEvent.get());
        if (ambientOcclusionEvent.lightLevel != -1.0f) {
            callbackInfoReturnable.setReturnValue((Object)Float.valueOf(ambientOcclusionEvent.lightLevel));
        }
    }

    @Inject(method={"getCollisionShape"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetCollisionShape(class_2680 class_26802, class_1922 class_19222, class_2338 class_23382, class_3726 class_37262, CallbackInfoReturnable<class_265> callbackInfoReturnable) {
        if (!class_26802.method_26227().method_15769()) {
            FluidCollisionShapeEvent fluidCollisionShapeEvent = MeteorClient.EVENT_BUS.post(FluidCollisionShapeEvent.get(class_26802.method_26227().method_15759()));
            if (fluidCollisionShapeEvent.shape != null) {
                callbackInfoReturnable.setReturnValue((Object)fluidCollisionShapeEvent.shape);
            }
        }
    }
}

