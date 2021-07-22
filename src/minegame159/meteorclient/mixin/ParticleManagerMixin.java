/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2394
 *  net.minecraft.class_2680
 *  net.minecraft.class_702
 *  net.minecraft.class_703
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.ParticleEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import minegame159.meteorclient.systems.modules.world.Nuker;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2394;
import net.minecraft.class_2680;
import net.minecraft.class_702;
import net.minecraft.class_703;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_702.class})
public class ParticleManagerMixin {
    @Inject(method={"addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAddParticle(class_2394 class_23942, double d, double d2, double d3, double d4, double d5, double d6, CallbackInfoReturnable<class_703> callbackInfoReturnable) {
        ParticleEvent particleEvent = MeteorClient.EVENT_BUS.post(ParticleEvent.get(class_23942));
        if (particleEvent.isCancelled()) {
            callbackInfoReturnable.cancel();
        }
    }

    @Inject(method={"addBlockBreakParticles"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAddBlockBreakParticles(class_2338 class_23382, class_2680 class_26802, CallbackInfo callbackInfo) {
        if (Modules.get().get(Nuker.class).noParticles() || Modules.get().get(NoRender.class).noBlockBreakParticles()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"addBlockBreakingParticles"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAddBlockBreakingParticles(class_2338 class_23382, class_2350 class_23502, CallbackInfo callbackInfo) {
        if (Modules.get().get(Nuker.class).noParticles() || Modules.get().get(NoRender.class).noBlockBreakParticles()) {
            callbackInfo.cancel();
        }
    }
}

