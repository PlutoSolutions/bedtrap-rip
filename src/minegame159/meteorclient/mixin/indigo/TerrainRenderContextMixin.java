/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin.indigo;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Xray;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.TerrainRenderContext;
import net.minecraft.class_1087;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_4587;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={TerrainRenderContext.class}, remap=false)
public class TerrainRenderContextMixin {
    @Inject(method={"tesselateBlock"}, at={@At(value="HEAD")}, cancellable=true)
    private void onTesselateBlock(class_2680 class_26802, class_2338 class_23382, class_1087 class_10872, class_4587 class_45872, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        Xray xray = Modules.get().get(Xray.class);
        if (xray.isActive() && xray.isBlocked(class_26802.method_26204())) {
            callbackInfoReturnable.cancel();
        }
    }
}

