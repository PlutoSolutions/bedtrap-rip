/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.LivingEntityMoveEvent;
import minegame159.meteorclient.events.entity.player.JumpVelocityMultiplierEvent;
import minegame159.meteorclient.events.entity.player.PlayerMoveEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.combat.Hitboxes;
import minegame159.meteorclient.systems.modules.movement.NoSlow;
import minegame159.meteorclient.systems.modules.movement.Velocity;
import minegame159.meteorclient.systems.modules.render.ESP;
import minegame159.meteorclient.systems.modules.render.NoRender;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.Outlines;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1313;
import net.minecraft.class_1657;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={class_1297.class})
public abstract class EntityMixin {
    @Shadow
    public class_1937 field_6002;

    @Shadow
    public abstract class_2338 method_24515();

    @Shadow
    protected abstract class_2338 method_23314();

    @Shadow
    public abstract void method_18800(double var1, double var3, double var5);

    @Redirect(method={"updateMovementInFluid"}, at=@At(value="INVOKE", target="Lnet/minecraft/fluid/FluidState;getVelocity(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/Vec3d;"))
    private class_243 updateMovementInFluidFluidStateGetVelocity(class_3610 class_36102, class_1922 class_19222, class_2338 class_23382) {
        class_243 class_2432 = class_36102.method_15758(class_19222, class_23382);
        Velocity velocity = Modules.get().get(Velocity.class);
        if (velocity.isActive() && velocity.liquids.get().booleanValue()) {
            class_2432 = class_2432.method_18805(velocity.getHorizontal(velocity.liquidsHorizontal), velocity.getVertical(velocity.liquidsVertical), velocity.getHorizontal(velocity.liquidsHorizontal));
        }
        return class_2432;
    }

    @ModifyArgs(method={"pushAwayFrom(Lnet/minecraft/entity/Entity;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    private void onPushAwayFrom(Args args) {
        Velocity velocity = Modules.get().get(Velocity.class);
        if (velocity.isActive() && velocity.entityPush.get().booleanValue() && Utils.mc.field_1724 == this) {
            double d = velocity.entityPushAmount.get();
            args.set(0, (Object)((Double)args.get(0) * d));
            args.set(2, (Object)((Double)args.get(2) * d));
        }
    }

    @Inject(method={"getJumpVelocityMultiplier"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetJumpVelocityMultiplier(CallbackInfoReturnable<Float> callbackInfoReturnable) {
        if (this == Utils.mc.field_1724) {
            float f = this.field_6002.method_8320(this.method_24515()).method_26204().method_23350();
            float f2 = this.field_6002.method_8320(this.method_23314()).method_26204().method_23350();
            float f3 = (double)f == 1.0 ? f2 : f;
            JumpVelocityMultiplierEvent jumpVelocityMultiplierEvent = MeteorClient.EVENT_BUS.post(JumpVelocityMultiplierEvent.get());
            callbackInfoReturnable.setReturnValue((Object)Float.valueOf(f3 * jumpVelocityMultiplierEvent.multiplier));
        }
    }

    @Inject(method={"move"}, at={@At(value="HEAD")})
    private void onMove(class_1313 class_13132, class_243 class_2432, CallbackInfo callbackInfo) {
        if (this == Utils.mc.field_1724) {
            MeteorClient.EVENT_BUS.post(PlayerMoveEvent.get(class_13132, class_2432));
        } else if (this instanceof class_1309) {
            MeteorClient.EVENT_BUS.post(LivingEntityMoveEvent.get((class_1309)this, class_2432));
        }
    }

    @Inject(method={"getTeamColorValue"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetTeamColorValue(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (Outlines.renderingOutlines) {
            callbackInfoReturnable.setReturnValue((Object)Modules.get().get(ESP.class).getColor((class_1297)this).getPacked());
        }
    }

    @Redirect(method={"getVelocityMultiplier"}, at=@At(value="INVOKE", target="Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"))
    private class_2248 getVelocityMultiplierGetBlockProxy(class_2680 class_26802) {
        if (class_26802.method_26204() == class_2246.field_10114 && Modules.get().get(NoSlow.class).soulSand()) {
            return class_2246.field_10340;
        }
        return class_26802.method_26204();
    }

    @Inject(method={"isInvisibleTo(Lnet/minecraft/entity/player/PlayerEntity;)Z"}, at={@At(value="HEAD")}, cancellable=true)
    private void isInvisibleToCanceller(class_1657 class_16572, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (class_16572 == null || Modules.get().get(NoRender.class).noInvisibility()) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }

    @Inject(method={"getTargetingMargin"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetTargetingMargin(CallbackInfoReturnable<Float> callbackInfoReturnable) {
        double d = Modules.get().get(Hitboxes.class).getEntityValue((class_1297)this);
        if (d != 0.0) {
            callbackInfoReturnable.setReturnValue((Object)Float.valueOf((float)d));
        }
    }
}

