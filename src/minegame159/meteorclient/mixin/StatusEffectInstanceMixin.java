/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1293
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
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

