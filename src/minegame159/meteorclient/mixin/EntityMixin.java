/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1313
 *  net.minecraft.class_1657
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_3610
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
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
    private class_243 updateMovementInFluidFluidStateGetVelocity(class_3610 state, class_1922 world, class_2338 pos) {
        class_243 vec = state.method_15758(world, pos);
        Velocity velocity = Modules.get().get(Velocity.class);
        if (velocity.isActive() && velocity.liquids.get().booleanValue()) {
            vec = vec.method_18805(velocity.getHorizontal(velocity.liquidsHorizontal), velocity.getVertical(velocity.liquidsVertical), velocity.getHorizontal(velocity.liquidsHorizontal));
        }
        return vec;
    }

    @ModifyArgs(method={"pushAwayFrom(Lnet/minecraft/entity/Entity;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    private void onPushAwayFrom(Args args) {
        Velocity velocity = Modules.get().get(Velocity.class);
        if (velocity.isActive() && velocity.entityPush.get().booleanValue() && Utils.mc.field_1724 == this) {
            double multiplier = velocity.entityPushAmount.get();
            args.set(0, (Object)((Double)args.get(0) * multiplier));
            args.set(2, (Object)((Double)args.get(2) * multiplier));
        }
    }

    @Inject(method={"getJumpVelocityMultiplier"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetJumpVelocityMultiplier(CallbackInfoReturnable<Float> info) {
        if (this == Utils.mc.field_1724) {
            float f = this.field_6002.method_8320(this.method_24515()).method_26204().method_23350();
            float g = this.field_6002.method_8320(this.method_23314()).method_26204().method_23350();
            float a = (double)f == 1.0 ? g : f;
            JumpVelocityMultiplierEvent event = MeteorClient.EVENT_BUS.post(JumpVelocityMultiplierEvent.get());
            info.setReturnValue((Object)Float.valueOf(a * event.multiplier));
        }
    }

    @Inject(method={"move"}, at={@At(value="HEAD")})
    private void onMove(class_1313 type, class_243 movement, CallbackInfo info) {
        if (this == Utils.mc.field_1724) {
            MeteorClient.EVENT_BUS.post(PlayerMoveEvent.get(type, movement));
        } else if (this instanceof class_1309) {
            MeteorClient.EVENT_BUS.post(LivingEntityMoveEvent.get((class_1309)this, movement));
        }
    }

    @Inject(method={"getTeamColorValue"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetTeamColorValue(CallbackInfoReturnable<Integer> info) {
        if (Outlines.renderingOutlines) {
            info.setReturnValue((Object)Modules.get().get(ESP.class).getColor((class_1297)this).getPacked());
        }
    }

    @Redirect(method={"getVelocityMultiplier"}, at=@At(value="INVOKE", target="Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"))
    private class_2248 getVelocityMultiplierGetBlockProxy(class_2680 blockState) {
        if (blockState.method_26204() == class_2246.field_10114 && Modules.get().get(NoSlow.class).soulSand()) {
            return class_2246.field_10340;
        }
        return blockState.method_26204();
    }

    @Inject(method={"isInvisibleTo(Lnet/minecraft/entity/player/PlayerEntity;)Z"}, at={@At(value="HEAD")}, cancellable=true)
    private void isInvisibleToCanceller(class_1657 player, CallbackInfoReturnable<Boolean> info) {
        if (player == null || Modules.get().get(NoRender.class).noInvisibility()) {
            info.setReturnValue((Object)false);
        }
    }

    @Inject(method={"getTargetingMargin"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetTargetingMargin(CallbackInfoReturnable<Float> info) {
        double v = Modules.get().get(Hitboxes.class).getEntityValue((class_1297)this);
        if (v != 0.0) {
            info.setReturnValue((Object)Float.valueOf((float)v));
        }
    }
}

