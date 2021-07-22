/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_2248
 *  net.minecraft.class_2263
 *  net.minecraft.class_2338
 *  net.minecraft.class_2404
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_3726
 *  net.minecraft.class_4970$class_2251
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.FluidCollisionShapeEvent;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2263;
import net.minecraft.class_2338;
import net.minecraft.class_2404;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_2404.class})
public abstract class FluidBlockMixin
extends class_2248
implements class_2263 {
    public FluidBlockMixin(class_4970.class_2251 class_22512) {
        super(class_22512);
    }

    @Inject(method={"getCollisionShape"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetCollisionShape(class_2680 class_26802, class_1922 class_19222, class_2338 class_23382, class_3726 class_37262, CallbackInfoReturnable<class_265> callbackInfoReturnable) {
        FluidCollisionShapeEvent fluidCollisionShapeEvent = MeteorClient.EVENT_BUS.post(FluidCollisionShapeEvent.get(class_26802));
        if (fluidCollisionShapeEvent.shape != null) {
            callbackInfoReturnable.setReturnValue((Object)fluidCollisionShapeEvent.shape);
        }
    }
}

