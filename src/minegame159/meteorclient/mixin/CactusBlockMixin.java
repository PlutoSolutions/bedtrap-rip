/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.AntiCactus;
import net.minecraft.class_1922;
import net.minecraft.class_2266;
import net.minecraft.class_2338;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_2266.class})
public class CactusBlockMixin {
    @Inject(method={"getCollisionShape"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetCollisionShape(class_2680 class_26802, class_1922 class_19222, class_2338 class_23382, class_3726 class_37262, CallbackInfoReturnable<class_265> callbackInfoReturnable) {
        if (Modules.get().isActive(AntiCactus.class)) {
            callbackInfoReturnable.setReturnValue((Object)class_259.method_1077());
        }
    }
}

