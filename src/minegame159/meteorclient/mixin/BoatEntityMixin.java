/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.BoatMoveEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.BoatFly;
import net.minecraft.class_1690;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_1690.class})
public class BoatEntityMixin {
    @Shadow
    private boolean field_7710;
    @Shadow
    private boolean field_7695;

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/vehicle/BoatEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V")})
    private void onTickInvokeMove(CallbackInfo callbackInfo) {
        MeteorClient.EVENT_BUS.post(BoatMoveEvent.get((class_1690)this));
    }

    @Redirect(method={"updatePaddles"}, at=@At(value="FIELD", target="Lnet/minecraft/entity/vehicle/BoatEntity;pressingLeft:Z"))
    private boolean onUpdatePaddlesPressingLeft(class_1690 class_16902) {
        if (Modules.get().isActive(BoatFly.class)) {
            return false;
        }
        return this.field_7710;
    }

    @Redirect(method={"updatePaddles"}, at=@At(value="FIELD", target="Lnet/minecraft/entity/vehicle/BoatEntity;pressingRight:Z"))
    private boolean onUpdatePaddlesPressingRight(class_1690 class_16902) {
        if (Modules.get().isActive(BoatFly.class)) {
            return false;
        }
        return this.field_7695;
    }
}

