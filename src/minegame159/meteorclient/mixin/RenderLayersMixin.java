/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1921
 *  net.minecraft.class_2680
 *  net.minecraft.class_4696
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.WallHack;
import net.minecraft.class_1921;
import net.minecraft.class_2680;
import net.minecraft.class_4696;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_4696.class})
public class RenderLayersMixin {
    @Inject(method={"getBlockLayer"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onGetBlockLayer(class_2680 class_26802, CallbackInfoReturnable<class_1921> callbackInfoReturnable) {
        WallHack wallHack;
        if (Modules.get() != null && (wallHack = Modules.get().get(WallHack.class)).isActive() && wallHack.blocks.get().contains((Object)class_26802.method_26204())) {
            callbackInfoReturnable.setReturnValue((Object)class_1921.method_23583());
        }
    }
}

