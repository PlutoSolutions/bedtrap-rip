/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.io.File;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.ChangePerspectiveEvent;
import minegame159.meteorclient.utils.misc.input.KeyBinds;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_315;
import net.minecraft.class_5498;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_315.class})
public class GameOptionsMixin {
    @Shadow
    @Final
    @Mutable
    public class_304[] field_1839;

    @Inject(method={"<init>"}, at={@At(value="FIELD", target="Lnet/minecraft/client/options/GameOptions;keysAll:[Lnet/minecraft/client/options/KeyBinding;", opcode=181, shift=At.Shift.AFTER)})
    private void onInitAfterKeysAll(class_310 class_3102, File file, CallbackInfo callbackInfo) {
        this.field_1839 = KeyBinds.apply(this.field_1839);
    }

    @Inject(method={"setPerspective"}, at={@At(value="HEAD")}, cancellable=true)
    private void setPerspective(class_5498 class_54982, CallbackInfo callbackInfo) {
        ChangePerspectiveEvent changePerspectiveEvent = MeteorClient.EVENT_BUS.post(ChangePerspectiveEvent.get(class_54982));
        if (changePerspectiveEvent.isCancelled()) {
            callbackInfo.cancel();
        }
    }
}

