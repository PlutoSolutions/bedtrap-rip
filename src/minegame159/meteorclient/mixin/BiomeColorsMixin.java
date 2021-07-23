/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.Ambience;
import net.minecraft.class_1163;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1163.class})
public class BiomeColorsMixin {
    @Inject(method={"getWaterColor"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onGetWaterColor(class_1920 class_19202, class_2338 class_23382, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeWaterColor.get().booleanValue()) {
            callbackInfoReturnable.setReturnValue((Object)ambience.waterColor.get().getPacked());
        }
    }

    @Inject(method={"getFoliageColor"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onGetFoliageColor(class_1920 class_19202, class_2338 class_23382, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeFoliageColor.get().booleanValue()) {
            callbackInfoReturnable.setReturnValue((Object)ambience.foliageColor.get().getPacked());
        }
    }

    @Inject(method={"getGrassColor"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onGetGrassColor(class_1920 class_19202, class_2338 class_23382, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeGrassColor.get().booleanValue()) {
            callbackInfoReturnable.setReturnValue((Object)ambience.grassColor.get().getPacked());
        }
    }
}

