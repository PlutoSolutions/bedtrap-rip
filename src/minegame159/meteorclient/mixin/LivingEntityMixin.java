/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1282
 *  net.minecraft.class_1291
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_3611
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.DamageEvent;
import minegame159.meteorclient.events.entity.TookDamageEvent;
import minegame159.meteorclient.events.entity.player.CanWalkOnFluidEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.AntiLevitation;
import minegame159.meteorclient.systems.modules.player.OffhandCrash;
import minegame159.meteorclient.systems.modules.render.HandView;
import minegame159.meteorclient.systems.modules.render.NoRender;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1268;
import net.minecraft.class_1282;
import net.minecraft.class_1291;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_3611;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1309.class})
public abstract class LivingEntityMixin
extends class_1297 {
    public LivingEntityMixin(class_1299<?> type, class_1937 world) {
        super(type, world);
    }

    @Inject(method={"damage"}, at={@At(value="HEAD")})
    private void onDamageHead(class_1282 source, float amount, CallbackInfoReturnable<Boolean> info) {
        if (Utils.canUpdate()) {
            MeteorClient.EVENT_BUS.post(DamageEvent.get((class_1309)this, source));
        }
    }

    @Inject(method={"damage"}, at={@At(value="TAIL")})
    private void onDamageTail(class_1282 source, float amount, CallbackInfoReturnable<Boolean> info) {
        if (Utils.canUpdate()) {
            MeteorClient.EVENT_BUS.post(TookDamageEvent.get((class_1309)this, source));
        }
    }

    @Inject(method={"canWalkOnFluid"}, at={@At(value="HEAD")}, cancellable=true)
    private void onCanWalkOnFluid(class_3611 fluid, CallbackInfoReturnable<Boolean> info) {
        CanWalkOnFluidEvent event = MeteorClient.EVENT_BUS.post(CanWalkOnFluidEvent.get((class_1309)this, fluid));
        if (event.walkOnFluid) {
            info.setReturnValue((Object)true);
        }
    }

    @Redirect(method={"travel"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
    private boolean travelHasStatusEffectProxy(class_1309 self, class_1291 statusEffect) {
        if (statusEffect == class_1294.field_5902 && Modules.get().isActive(AntiLevitation.class)) {
            return false;
        }
        return self.method_6059(statusEffect);
    }

    @Redirect(method={"travel"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;hasNoGravity()Z"))
    private boolean travelHasNoGravityProxy(class_1309 self) {
        if (self.method_6059(class_1294.field_5902) && Modules.get().isActive(AntiLevitation.class)) {
            return !Modules.get().get(AntiLevitation.class).isApplyGravity();
        }
        return self.method_5740();
    }

    @Inject(method={"spawnItemParticles"}, at={@At(value="HEAD")}, cancellable=true)
    private void spawnItemParticles(class_1799 stack, int count, CallbackInfo info) {
        NoRender noRender = Modules.get().get(NoRender.class);
        if (noRender.noEatParticles() && stack.method_19267()) {
            info.cancel();
        }
    }

    @Inject(method={"onEquipStack"}, at={@At(value="HEAD")}, cancellable=true)
    private void onEquipStack(class_1799 stack, CallbackInfo info) {
        if (this == Utils.mc.field_1724 && Modules.get().get(OffhandCrash.class).isAntiCrash()) {
            info.cancel();
        }
    }

    @ModifyArg(method={"swingHand(Lnet/minecraft/util/Hand;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;swingHand(Lnet/minecraft/util/Hand;Z)V"))
    private class_1268 setHand(class_1268 hand) {
        HandView handView = Modules.get().get(HandView.class);
        if (this == Utils.mc.field_1724 && handView.isActive()) {
            if (handView.swingMode.get() == HandView.SwingMode.None) {
                return hand;
            }
            return handView.swingMode.get() == HandView.SwingMode.Offhand ? class_1268.field_5810 : class_1268.field_5808;
        }
        return hand;
    }
}

