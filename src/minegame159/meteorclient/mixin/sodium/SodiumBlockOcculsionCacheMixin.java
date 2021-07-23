/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin.sodium;

import me.jellysquid.mods.sodium.client.render.occlusion.BlockOcclusionCache;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Xray;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BlockOcclusionCache.class}, remap=false)
public class SodiumBlockOcculsionCacheMixin {
    @Inject(method={"shouldDrawSide"}, at={@At(value="RETURN")}, cancellable=true)
    private void shouldDrawSide(class_2680 class_26802, class_1922 class_19222, class_2338 class_23382, class_2350 class_23502, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        Xray xray = Modules.get().get(Xray.class);
        if (xray.isActive()) {
            callbackInfoReturnable.setReturnValue((Object)xray.modifyDrawSide(class_26802, class_19222, class_23382, class_23502, callbackInfoReturnable.getReturnValueZ()));
        }
    }
}

