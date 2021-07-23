/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.BetterChat;
import net.minecraft.class_342;
import net.minecraft.class_408;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_408.class})
public class ChatScreenMixin {
    @Shadow
    protected class_342 field_2382;

    @Inject(method={"init"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/widget/TextFieldWidget;setMaxLength(I)V", shift=At.Shift.AFTER)})
    private void onInit(CallbackInfo callbackInfo) {
        if (Modules.get().get(BetterChat.class).isInfiniteChatBox()) {
            this.field_2382.method_1880(Integer.MAX_VALUE);
        }
    }
}

