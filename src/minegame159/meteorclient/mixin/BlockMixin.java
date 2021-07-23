/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.Slippy;
import minegame159.meteorclient.systems.modules.render.Xray;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_2248.class})
public abstract class BlockMixin
extends class_4970
implements class_1935 {
    public BlockMixin(class_4970.class_2251 class_22512) {
        super(class_22512);
    }

    @Inject(method={"shouldDrawSide"}, at={@At(value="RETURN")}, cancellable=true)
    private static void onShouldDrawSide(class_2680 class_26802, class_1922 class_19222, class_2338 class_23382, class_2350 class_23502, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        Xray xray = Modules.get().get(Xray.class);
        if (xray.isActive()) {
            callbackInfoReturnable.setReturnValue((Object)xray.modifyDrawSide(class_26802, class_19222, class_23382, class_23502, callbackInfoReturnable.getReturnValueZ()));
        }
    }

    @Inject(method={"getSlipperiness"}, at={@At(value="RETURN")}, cancellable=true)
    public void getSlipperiness(CallbackInfoReturnable<Float> callbackInfoReturnable) {
        if (Modules.get() == null) {
            return;
        }
        Slippy slippy = Modules.get().get(Slippy.class);
        class_2248 class_22482 = (class_2248)this;
        if (slippy.isActive() && !slippy.blocks.get().contains(class_22482)) {
            callbackInfoReturnable.setReturnValue((Object)Float.valueOf(slippy.slippness.get().floatValue()));
        }
    }
}

