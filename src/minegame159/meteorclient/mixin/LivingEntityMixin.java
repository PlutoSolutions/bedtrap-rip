/*
 * Decompiled with CFR 0.151.
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
    public LivingEntityMixin(class_1299<?> class_12992, class_1937 class_19372) {
        super(class_12992, class_19372);
    }

    @Inject(method={"damage"}, at={@At(value="HEAD")})
    private void onDamageHead(class_1282 class_12822, float f, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (Utils.canUpdate()) {
            MeteorClient.EVENT_BUS.post(DamageEvent.get((class_1309)this, class_12822));
        }
    }

    @Inject(method={"damage"}, at={@At(value="TAIL")})
    private void onDamageTail(class_1282 class_12822, float f, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (Utils.canUpdate()) {
            MeteorClient.EVENT_BUS.post(TookDamageEvent.get((class_1309)this, class_12822));
        }
    }

    @Inject(method={"canWalkOnFluid"}, at={@At(value="HEAD")}, cancellable=true)
    private void onCanWalkOnFluid(class_3611 class_36112, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        CanWalkOnFluidEvent canWalkOnFluidEvent = MeteorClient.EVENT_BUS.post(CanWalkOnFluidEvent.get((class_1309)this, class_36112));
        if (canWalkOnFluidEvent.walkOnFluid) {
            callbackInfoReturnable.setReturnValue((Object)true);
        }
    }

    @Redirect(method={"travel"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
    private boolean travelHasStatusEffectProxy(class_1309 class_13092, class_1291 class_12912) {
        if (class_12912 == class_1294.field_5902 && Modules.get().isActive(AntiLevitation.class)) {
            return false;
        }
        return class_13092.method_6059(class_12912);
    }

    @Redirect(method={"travel"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;hasNoGravity()Z"))
    private boolean travelHasNoGravityProxy(class_1309 class_13092) {
        if (class_13092.method_6059(class_1294.field_5902) && Modules.get().isActive(AntiLevitation.class)) {
            return !Modules.get().get(AntiLevitation.class).isApplyGravity();
        }
        return class_13092.method_5740();
    }

    @Inject(method={"spawnItemParticles"}, at={@At(value="HEAD")}, cancellable=true)
    private void spawnItemParticles(class_1799 class_17992, int n, CallbackInfo callbackInfo) {
        NoRender noRender = Modules.get().get(NoRender.class);
        if (noRender.noEatParticles() && class_17992.method_19267()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"onEquipStack"}, at={@At(value="HEAD")}, cancellable=true)
    private void onEquipStack(class_1799 class_17992, CallbackInfo callbackInfo) {
        if (this == Utils.mc.field_1724 && Modules.get().get(OffhandCrash.class).isAntiCrash()) {
            callbackInfo.cancel();
        }
    }

    @ModifyArg(method={"swingHand(Lnet/minecraft/util/Hand;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;swingHand(Lnet/minecraft/util/Hand;Z)V"))
    private class_1268 setHand(class_1268 class_12682) {
        HandView handView = Modules.get().get(HandView.class);
        if (this == Utils.mc.field_1724 && handView.isActive()) {
            if (handView.swingMode.get() == HandView.SwingMode.None) {
                return class_12682;
            }
            return handView.swingMode.get() == HandView.SwingMode.Offhand ? class_1268.field_5810 : class_1268.field_5808;
        }
        return class_12682;
    }
}

