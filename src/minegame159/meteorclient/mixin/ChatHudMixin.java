/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.List;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.ReceiveMessageEvent;
import minegame159.meteorclient.mixininterface.IChatHud;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.BetterChat;
import net.minecraft.class_2561;
import net.minecraft.class_303;
import net.minecraft.class_338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_338.class})
public abstract class ChatHudMixin
implements IChatHud {
    @Shadow
    protected abstract void method_1815(class_2561 var1, int var2, int var3, boolean var4);

    @Inject(at={@At(value="HEAD")}, method={"addMessage(Lnet/minecraft/text/Text;I)V"}, cancellable=true)
    private void onAddMessage(class_2561 class_25612, int n, CallbackInfo callbackInfo) {
        ReceiveMessageEvent receiveMessageEvent = MeteorClient.EVENT_BUS.post(ReceiveMessageEvent.get(class_25612, n));
        if (receiveMessageEvent.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Redirect(method={"addMessage(Lnet/minecraft/text/Text;IIZ)V"}, at=@At(value="INVOKE", target="Ljava/util/List;size()I"))
    private int addMessageListSizeProxy(List<class_303> list) {
        BetterChat betterChat = Modules.get().get(BetterChat.class);
        return betterChat.isLongerChat() && betterChat.getChatLength() > 100 ? 1 : list.size();
    }

    @Override
    public void add(class_2561 class_25612, int n, int n2, boolean bl) {
        this.method_1815(class_25612, n, n2, bl);
    }
}

