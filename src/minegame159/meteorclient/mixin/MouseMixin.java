/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.meteor.MouseButtonEvent;
import minegame159.meteorclient.events.meteor.MouseScrollEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.FreeLook;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.misc.input.Input;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import net.minecraft.class_312;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={class_312.class})
public class MouseMixin {
    @Inject(method={"onMouseButton"}, at={@At(value="HEAD")}, cancellable=true)
    private void onMouseButton(long l, int n, int n2, int n3, CallbackInfo callbackInfo) {
        Input.setButtonState(n, n2 != 0);
        if (MeteorClient.EVENT_BUS.post(MouseButtonEvent.get(n, KeyAction.get(n2))).isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"onMouseScroll"}, at={@At(value="HEAD")}, cancellable=true)
    private void onMouseScroll(long l, double d, double d2, CallbackInfo callbackInfo) {
        if (MeteorClient.EVENT_BUS.post(MouseScrollEvent.get(d2)).isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Redirect(method={"updateMouse"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"))
    private void updateMouseChangeLookDirection(class_746 class_7462, double d, double d2) {
        Freecam freecam = Modules.get().get(Freecam.class);
        FreeLook freeLook = Modules.get().get(FreeLook.class);
        if (freecam.isActive()) {
            freecam.changeLookDirection(d * 0.15, d2 * 0.15);
        } else if (!freeLook.cameraMode()) {
            class_7462.method_5872(d, d2);
        }
    }

    @Inject(method={"updateMouse"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/tutorial/TutorialManager;onUpdateMouse(DD)V")}, locals=LocalCapture.CAPTURE_FAILEXCEPTION)
    private void perspectiveUpdatePitchYaw(CallbackInfo callbackInfo, double d, double d2, double d3, int n) {
        FreeLook freeLook = Modules.get().get(FreeLook.class);
        if (freeLook.cameraMode()) {
            freeLook.cameraYaw = (float)((double)freeLook.cameraYaw + d2 / (double)freeLook.sensitivity.get().floatValue());
            freeLook.cameraPitch = (float)((double)freeLook.cameraPitch + d3 * (double)n / (double)freeLook.sensitivity.get().floatValue());
            if (Math.abs(freeLook.cameraPitch) > 90.0f) {
                freeLook.cameraPitch = freeLook.cameraPitch > 0.0f ? 90.0f : -90.0f;
            }
        }
    }
}

