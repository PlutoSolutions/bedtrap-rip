/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1926
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.Ambience;
import net.minecraft.class_1926;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1926.class})
public class FoliageColorsMixin {
    @Inject(method={"getBirchColor"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onGetBirchColor(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeFoliageColor.get().booleanValue()) {
            callbackInfoReturnable.setReturnValue((Object)ambience.foliageColor.get().getPacked());
            callbackInfoReturnable.cancel();
        }
    }

    @Inject(method={"getSpruceColor"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onGetSpruceColor(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeFoliageColor.get().booleanValue()) {
            callbackInfoReturnable.setReturnValue((Object)ambience.foliageColor.get().getPacked());
            callbackInfoReturnable.cancel();
        }
    }
}

