/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.CameraTweaks;
import minegame159.meteorclient.systems.modules.render.FreeLook;
import minegame159.meteorclient.systems.modules.render.Freecam;
import net.minecraft.class_1297;
import net.minecraft.class_1922;
import net.minecraft.class_4184;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={class_4184.class})
public abstract class CameraMixin {
    @Shadow
    private boolean field_18719;
    @Unique
    private float tickDelta;

    @Shadow
    protected abstract double method_19318(double var1);

    @ModifyArgs(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;moveBy(DDD)V", ordinal=0))
    private void modifyCameraDistance(Args args) {
        args.set(0, (Object)(-this.method_19318(Modules.get().get(CameraTweaks.class).getDistance())));
    }

    @Inject(method={"clipToSpace"}, at={@At(value="HEAD")}, cancellable=true)
    private void onClipToSpace(double d, CallbackInfoReturnable<Double> callbackInfoReturnable) {
        if (Modules.get().get(CameraTweaks.class).clip()) {
            callbackInfoReturnable.setReturnValue((Object)d);
        }
    }

    @Inject(method={"update"}, at={@At(value="HEAD")})
    private void onUpdateHead(class_1922 class_19222, class_1297 class_12972, boolean bl, boolean bl2, float f, CallbackInfo callbackInfo) {
        this.tickDelta = f;
    }

    @Inject(method={"update"}, at={@At(value="TAIL")})
    private void onUpdateTail(class_1922 class_19222, class_1297 class_12972, boolean bl, boolean bl2, float f, CallbackInfo callbackInfo) {
        if (Modules.get().isActive(Freecam.class)) {
            this.field_18719 = true;
        }
    }

    @ModifyArgs(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;setPos(DDD)V"))
    private void onUpdateSetPosArgs(Args args) {
        Freecam freecam = Modules.get().get(Freecam.class);
        if (freecam.isActive()) {
            args.set(0, (Object)freecam.getX(this.tickDelta));
            args.set(1, (Object)freecam.getY(this.tickDelta));
            args.set(2, (Object)freecam.getZ(this.tickDelta));
        }
    }

    @ModifyArgs(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;setRotation(FF)V"))
    private void onUpdateSetRotationArgs(Args args) {
        Freecam freecam = Modules.get().get(Freecam.class);
        FreeLook freeLook = Modules.get().get(FreeLook.class);
        if (freecam.isActive()) {
            args.set(0, (Object)Float.valueOf((float)freecam.getYaw(this.tickDelta)));
            args.set(1, (Object)Float.valueOf((float)freecam.getPitch(this.tickDelta)));
        }
        if (freeLook.isActive()) {
            args.set(0, (Object)Float.valueOf(freeLook.cameraYaw));
            args.set(1, (Object)Float.valueOf(freeLook.cameraPitch));
        }
    }
}

