/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1159
 *  net.minecraft.class_1297
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_757
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package minegame159.meteorclient.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.rendering.Matrices;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.player.LiquidInteract;
import minegame159.meteorclient.systems.modules.player.NoMiningTrace;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.systems.modules.render.NoRender;
import minegame159.meteorclient.systems.modules.render.UnfocusedCPU;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.NametagUtils;
import net.minecraft.class_1159;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_239;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={class_757.class})
public abstract class GameRendererMixin {
    @Shadow
    @Final
    private class_310 field_4015;
    private boolean a = false;
    private boolean freecamSet = false;

    @Shadow
    public abstract void method_3190(float var1);

    @Shadow
    public abstract void method_3203();

    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderHead(float tickDelta, long startTime, boolean tick, CallbackInfo info) {
        if (Modules.get().isActive(UnfocusedCPU.class) && !this.field_4015.method_1569()) {
            info.cancel();
        }
        this.a = false;
    }

    @Inject(method={"renderWorld"}, at={@At(value="HEAD")})
    private void onRenderWorldHead(float tickDelta, long limitTime, class_4587 matrix, CallbackInfo info) {
        Matrices.begin(matrix);
        Matrices.push();
        RenderSystem.pushMatrix();
        this.a = true;
    }

    @Inject(method={"renderWorld"}, at={@At(value="INVOKE_STRING", target="Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args={"ldc=hand"})}, locals=LocalCapture.CAPTURE_FAILSOFT)
    private void onRenderWorld(float tickDelta, long limitTime, class_4587 matrix, CallbackInfo info, boolean bl, class_4184 camera, class_4587 matrixStack2, class_1159 matrix4f) {
        if (!Utils.canUpdate()) {
            return;
        }
        this.field_4015.method_16011().method_15396("meteor-client_render");
        RenderEvent event = RenderEvent.get(matrix, tickDelta, camera.method_19326().field_1352, camera.method_19326().field_1351, camera.method_19326().field_1350);
        Renderer.begin(event);
        NametagUtils.onRender(matrix, matrix4f);
        MeteorClient.EVENT_BUS.post(event);
        Renderer.end();
        this.field_4015.method_16011().method_15407();
    }

    @Inject(method={"updateTargetedEntity"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/projectile/ProjectileUtil;raycast(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Box;Ljava/util/function/Predicate;D)Lnet/minecraft/util/hit/EntityHitResult;")}, cancellable=true)
    private void onUpdateTargetedEntity(float tickDelta, CallbackInfo info) {
        if (Modules.get().get(NoMiningTrace.class).canWork() && this.field_4015.field_1765.method_17783() == class_239.class_240.field_1332) {
            this.field_4015.method_16011().method_15407();
            info.cancel();
        }
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lcom/mojang/blaze3d/systems/RenderSystem;clear(IZ)V", ordinal=0)})
    private void onRenderBeforeGuiRender(float tickDelta, long startTime, boolean tick, CallbackInfo info) {
        if (this.a) {
            Matrices.pop();
            RenderSystem.popMatrix();
        }
    }

    @Redirect(method={"updateTargetedEntity"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;raycast(DFZ)Lnet/minecraft/util/hit/HitResult;"))
    private class_239 updateTargetedEntityEntityRayTraceProxy(class_1297 entity, double maxDistance, float tickDelta, boolean includeFluids) {
        if (Modules.get().isActive(LiquidInteract.class)) {
            class_239 result = entity.method_5745(maxDistance, tickDelta, includeFluids);
            if (result.method_17783() != class_239.class_240.field_1333) {
                return result;
            }
            return entity.method_5745(maxDistance, tickDelta, true);
        }
        return entity.method_5745(maxDistance, tickDelta, includeFluids);
    }

    @Inject(method={"bobViewWhenHurt"}, at={@At(value="HEAD")}, cancellable=true)
    private void onBobViewWhenHurt(class_4587 matrixStack, float f, CallbackInfo info) {
        if (Modules.get().get(NoRender.class).noHurtCam()) {
            info.cancel();
        }
    }

    @Inject(method={"showFloatingItem"}, at={@At(value="HEAD")}, cancellable=true)
    private void onShowFloatingItem(class_1799 floatingItem, CallbackInfo info) {
        if (floatingItem.method_7909() == class_1802.field_8288 && Modules.get().get(NoRender.class).noTotemAnimation()) {
            info.cancel();
        }
    }

    @Redirect(method={"renderWorld"}, at=@At(value="INVOKE", target="Lnet/minecraft/util/math/MathHelper;lerp(FFF)F"))
    private float applyCameraTransformationsMathHelperLerpProxy(float delta, float first, float second) {
        if (Modules.get().get(NoRender.class).noNausea()) {
            return 0.0f;
        }
        return class_3532.method_16439((float)delta, (float)first, (float)second);
    }

    @Inject(method={"updateTargetedEntity"}, at={@At(value="INVOKE")}, cancellable=true)
    private void updateTargetedEntityInvoke(float tickDelta, CallbackInfo info) {
        Freecam freecam = Modules.get().get(Freecam.class);
        if (freecam.isActive() && this.field_4015.method_1560() != null && !this.freecamSet) {
            info.cancel();
            class_1297 camera = this.field_4015.method_1560();
            double x = camera.method_23317();
            double y = camera.method_23318();
            double z = camera.method_23321();
            double prevX = camera.field_6014;
            double prevY = camera.field_6036;
            double prevZ = camera.field_5969;
            float yaw = camera.field_6031;
            float pitch = camera.field_5965;
            float prevYaw = camera.field_5982;
            float prevPitch = camera.field_6004;
            ((IVec3d)camera.method_19538()).set(freecam.pos.x, freecam.pos.y - (double)camera.method_18381(camera.method_18376()), freecam.pos.z);
            camera.field_6014 = freecam.prevPos.x;
            camera.field_6036 = freecam.prevPos.y - (double)camera.method_18381(camera.method_18376());
            camera.field_5969 = freecam.prevPos.z;
            camera.field_6031 = freecam.yaw;
            camera.field_5965 = freecam.pitch;
            camera.field_5982 = freecam.prevYaw;
            camera.field_6004 = freecam.prevPitch;
            this.freecamSet = true;
            this.method_3190(tickDelta);
            this.freecamSet = false;
            ((IVec3d)camera.method_19538()).set(x, y, z);
            camera.field_6014 = prevX;
            camera.field_6036 = prevY;
            camera.field_5969 = prevZ;
            camera.field_6031 = yaw;
            camera.field_5965 = pitch;
            camera.field_5982 = prevYaw;
            camera.field_6004 = prevPitch;
        }
    }

    @Inject(method={"renderHand"}, at={@At(value="INVOKE")}, cancellable=true)
    private void renderHand(class_4587 matrices, class_4184 camera, float tickDelta, CallbackInfo info) {
        if (!Modules.get().get(Freecam.class).renderHands()) {
            info.cancel();
        }
    }
}

