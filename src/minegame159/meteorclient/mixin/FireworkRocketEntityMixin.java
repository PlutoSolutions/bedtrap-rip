/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.ElytraBoost;
import net.minecraft.class_1671;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_1671.class})
public abstract class FireworkRocketEntityMixin {
    @Shadow
    private int field_7613;
    @Shadow
    private int field_7612;

    @Shadow
    protected abstract void method_16830();

    @Inject(method={"tick"}, at={@At(value="TAIL")})
    private void onTick(CallbackInfo callbackInfo) {
        if (Modules.get().get(ElytraBoost.class).isFirework((class_1671)this) && this.field_7613 > this.field_7612) {
            this.method_16830();
        }
    }

    @Inject(method={"onEntityHit"}, at={@At(value="HEAD")}, cancellable=true)
    private void onEntityHit(class_3966 class_39662, CallbackInfo callbackInfo) {
        if (Modules.get().get(ElytraBoost.class).isFirework((class_1671)this)) {
            this.method_16830();
            callbackInfo.cancel();
        }
    }

    @Inject(method={"onBlockHit"}, at={@At(value="HEAD")}, cancellable=true)
    private void onBlockHit(class_3965 class_39652, CallbackInfo callbackInfo) {
        if (Modules.get().get(ElytraBoost.class).isFirework((class_1671)this)) {
            this.method_16830();
            callbackInfo.cancel();
        }
    }
}

