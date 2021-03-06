/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.UUID;
import net.minecraft.class_1068;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1068.class})
public class DefaultSkinHelperMixin {
    @Inject(method={"shouldUseSlimModel"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onShouldUseSlimModel(UUID uUID, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (uUID == null) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }
}

