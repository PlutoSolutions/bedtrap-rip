/*
 * Decompiled with CFR 0.151.
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
    private void onRenderHead(float f, long l, boolean bl, CallbackInfo callbackInfo) {
        if (Modules.get().isActive(UnfocusedCPU.class) && !this.field_4015.method_1569()) {
            callbackInfo.cancel();
        }
        this.a = false;
    }

    @Inject(method={"renderWorld"}, at={@At(value="HEAD")})
    private void onRenderWorldHead(float f, long l, class_4587 class_45872, CallbackInfo callbackInfo) {
        Matrices.begin(class_45872);
        Matrices.push();
        RenderSystem.pushMatrix();
        this.a = true;
    }

    @Inject(method={"renderWorld"}, at={@At(value="INVOKE_STRING", target="Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args={"ldc=hand"})}, locals=LocalCapture.CAPTURE_FAILSOFT)
    private void onRenderWorld(float f, long l, class_4587 class_45872, CallbackInfo callbackInfo, boolean bl, class_4184 class_41842, class_4587 class_45873, class_1159 class_11592) {
        if (!Utils.canUpdate()) {
            return;
        }
        this.field_4015.method_16011().method_15396("meteor-client_render");
        RenderEvent renderEvent = RenderEvent.get(class_45872, f, class_41842.method_19326().field_1352, class_41842.method_19326().field_1351, class_41842.method_19326().field_1350);
        Renderer.begin(renderEvent);
        NametagUtils.onRender(class_45872, class_11592);
        MeteorClient.EVENT_BUS.post(renderEvent);
        Renderer.end();
        this.field_4015.method_16011().method_15407();
    }

    @Inject(method={"updateTargetedEntity"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/projectile/ProjectileUtil;raycast(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Box;Ljava/util/function/Predicate;D)Lnet/minecraft/util/hit/EntityHitResult;")}, cancellable=true)
    private void onUpdateTargetedEntity(float f, CallbackInfo callbackInfo) {
        if (Modules.get().get(NoMiningTrace.class).canWork() && this.field_4015.field_1765.method_17783() == class_239.class_240.field_1332) {
            this.field_4015.method_16011().method_15407();
            callbackInfo.cancel();
        }
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lcom/mojang/blaze3d/systems/RenderSystem;clear(IZ)V", ordinal=0)})
    private void onRenderBeforeGuiRender(float f, long l, boolean bl, CallbackInfo callbackInfo) {
        if (this.a) {
            Matrices.pop();
            RenderSystem.popMatrix();
        }
    }

    @Redirect(method={"updateTargetedEntity"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;raycast(DFZ)Lnet/minecraft/util/hit/HitResult;"))
    private class_239 updateTargetedEntityEntityRayTraceProxy(class_1297 class_12972, double d, float f, boolean bl) {
        if (Modules.get().isActive(LiquidInteract.class)) {
            class_239 class_2392 = class_12972.method_5745(d, f, bl);
            if (class_2392.method_17783() != class_239.class_240.field_1333) {
                return class_2392;
            }
            return class_12972.method_5745(d, f, true);
        }
        return class_12972.method_5745(d, f, bl);
    }

    @Inject(method={"bobViewWhenHurt"}, at={@At(value="HEAD")}, cancellable=true)
    private void onBobViewWhenHurt(class_4587 class_45872, float f, CallbackInfo callbackInfo) {
        if (Modules.get().get(NoRender.class).noHurtCam()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"showFloatingItem"}, at={@At(value="HEAD")}, cancellable=true)
    private void onShowFloatingItem(class_1799 class_17992, CallbackInfo callbackInfo) {
        if (class_17992.method_7909() == class_1802.field_8288 && Modules.get().get(NoRender.class).noTotemAnimation()) {
            callbackInfo.cancel();
        }
    }

    @Redirect(method={"renderWorld"}, at=@At(value="INVOKE", target="Lnet/minecraft/util/math/MathHelper;lerp(FFF)F"))
    private float applyCameraTransformationsMathHelperLerpProxy(float f, float f2, float f3) {
        if (Modules.get().get(NoRender.class).noNausea()) {
            return 0.0f;
        }
        return class_3532.method_16439((float)f, (float)f2, (float)f3);
    }

    @Inject(method={"updateTargetedEntity"}, at={@At(value="INVOKE")}, cancellable=true)
    private void updateTargetedEntityInvoke(float f, CallbackInfo callbackInfo) {
        Freecam freecam = Modules.get().get(Freecam.class);
        if (freecam.isActive() && this.field_4015.method_1560() != null && !this.freecamSet) {
            callbackInfo.cancel();
            class_1297 class_12972 = this.field_4015.method_1560();
            double d = class_12972.method_23317();
            double d2 = class_12972.method_23318();
            double d3 = class_12972.method_23321();
            double d4 = class_12972.field_6014;
            double d5 = class_12972.field_6036;
            double d6 = class_12972.field_5969;
            float f2 = class_12972.field_6031;
            float f3 = class_12972.field_5965;
            float f4 = class_12972.field_5982;
            float f5 = class_12972.field_6004;
            ((IVec3d)class_12972.method_19538()).set(freecam.pos.x, freecam.pos.y - (double)class_12972.method_18381(class_12972.method_18376()), freecam.pos.z);
            class_12972.field_6014 = freecam.prevPos.x;
            class_12972.field_6036 = freecam.prevPos.y - (double)class_12972.method_18381(class_12972.method_18376());
            class_12972.field_5969 = freecam.prevPos.z;
            class_12972.field_6031 = freecam.yaw;
            class_12972.field_5965 = freecam.pitch;
            class_12972.field_5982 = freecam.prevYaw;
            class_12972.field_6004 = freecam.prevPitch;
            this.freecamSet = true;
            this.method_3190(f);
            this.freecamSet = false;
            ((IVec3d)class_12972.method_19538()).set(d, d2, d3);
            class_12972.field_6014 = d4;
            class_12972.field_6036 = d5;
            class_12972.field_5969 = d6;
            class_12972.field_6031 = f2;
            class_12972.field_5965 = f3;
            class_12972.field_5982 = f4;
            class_12972.field_6004 = f5;
        }
    }

    @Inject(method={"renderHand"}, at={@At(value="INVOKE")}, cancellable=true)
    private void renderHand(class_4587 class_45872, class_4184 class_41842, float f, CallbackInfo callbackInfo) {
        if (!Modules.get().get(Freecam.class).renderHands()) {
            callbackInfo.cancel();
        }
    }
}

