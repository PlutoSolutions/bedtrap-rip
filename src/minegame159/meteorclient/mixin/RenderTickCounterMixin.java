/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.Timer;
import net.minecraft.class_317;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_317.class})
public class RenderTickCounterMixin {
    @Shadow
    public float field_1969;

    @Inject(method={"beginRenderTick"}, at={@At(value="FIELD", target="Lnet/minecraft/client/render/RenderTickCounter;prevTimeMillis:J", opcode=181)})
    private void onBeingRenderTick(long l, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        this.field_1969 = (float)((double)this.field_1969 * Modules.get().get(Timer.class).getMultiplier());
    }
}

