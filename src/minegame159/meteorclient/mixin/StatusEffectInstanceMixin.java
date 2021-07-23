/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.player.PotionSaver;
import net.minecraft.class_1293;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1293.class})
public class StatusEffectInstanceMixin {
    @Shadow
    private int field_5895;

    @Inject(method={"updateDuration"}, at={@At(value="HEAD")}, cancellable=true)
    private void tick(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (Modules.get().get(PotionSaver.class).shouldFreeze()) {
            callbackInfoReturnable.setReturnValue((Object)this.field_5895);
        }
    }
}

