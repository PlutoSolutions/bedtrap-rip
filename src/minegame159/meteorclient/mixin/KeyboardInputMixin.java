/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.Sneak;
import net.minecraft.class_743;
import net.minecraft.class_744;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_743.class})
public class KeyboardInputMixin
extends class_744 {
    @Inject(method={"tick"}, at={@At(value="TAIL")})
    private void isPressed(boolean bl, CallbackInfo callbackInfo) {
        if (Modules.get().get(Sneak.class).doVanilla()) {
            this.field_3903 = true;
        }
    }
}

