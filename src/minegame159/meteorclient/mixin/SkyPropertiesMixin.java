/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Fullbright;
import net.minecraft.class_5294;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_5294.class})
public class SkyPropertiesMixin {
    @Inject(method={"shouldBrightenLighting"}, at={@At(value="HEAD")}, cancellable=true)
    private void onShouldBrightenLighting(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        Fullbright fullbright = Modules.get().get(Fullbright.class);
        if (fullbright.mode.get() == Fullbright.Mode.Luminance && Fullbright.isEnabled()) {
            callbackInfoReturnable.setReturnValue((Object)true);
        }
    }
}

