/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.class_1041
 *  net.minecraft.class_239
 *  net.minecraft.class_310
 *  net.minecraft.class_312
 *  net.minecraft.class_3695
 *  net.minecraft.class_437
 *  net.minecraft.class_638
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.player.ItemUseCrosshairTargetEvent;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.events.game.ResourcePacksReloadedEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.mixininterface.IMinecraftClient;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.misc.Placeholders;
import minegame159.meteorclient.utils.network.OnlinePlayers;
import net.minecraft.class_1041;
import net.minecraft.class_239;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_3695;
import net.minecraft.class_437;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_310.class}, priority=1001)
public abstract class MinecraftClientMixin
implements IMinecraftClient {
    @Shadow
    public class_638 field_1687;
    @Shadow
    @Final
    public class_312 field_1729;
    @Shadow
    @Final
    private class_1041 field_1704;
    @Shadow
    @Nullable
    public class_437 field_1755;
    @Unique
    private boolean doItemUseCalled;
    @Unique
    private boolean rightClick;

    @Shadow
    protected abstract void method_1583();

    @Shadow
    public abstract class_3695 method_16011();

    @Inject(method={"<init>"}, at={@At(value="TAIL")})
    private void onInit(CallbackInfo callbackInfo) {
        MeteorClient.INSTANCE.onInitializeClient();
    }

    @Inject(at={@At(value="HEAD")}, method={"tick"})
    private void onPreTick(CallbackInfo callbackInfo) {
        OnlinePlayers.update();
        this.doItemUseCalled = false;
        this.method_16011().method_15396("meteor-client_pre_update");
        MeteorClient.EVENT_BUS.post(TickEvent.Pre.get());
        this.method_16011().method_15407();
        if (this.rightClick && !this.doItemUseCalled) {
            this.method_1583();
        }
        this.rightClick = false;
    }

    @Inject(at={@At(value="TAIL")}, method={"tick"})
    private void onTick(CallbackInfo callbackInfo) {
        this.method_16011().method_15396("meteor-client_post_update");
        MeteorClient.EVENT_BUS.post(TickEvent.Post.get());
        this.method_16011().method_15407();
    }

    @Inject(method={"doItemUse"}, at={@At(value="HEAD")})
    private void onDoItemUse(CallbackInfo callbackInfo) {
        this.doItemUseCalled = true;
    }

    @Inject(method={"disconnect(Lnet/minecraft/client/gui/screen/Screen;)V"}, at={@At(value="HEAD")})
    private void onDisconnect(class_437 class_4372, CallbackInfo callbackInfo) {
        if (this.field_1687 != null) {
            MeteorClient.EVENT_BUS.post(GameLeftEvent.get());
        }
    }

    @Inject(method={"openScreen"}, at={@At(value="HEAD")}, cancellable=true)
    private void onOpenScreen(class_437 class_4372, CallbackInfo callbackInfo) {
        if (class_4372 instanceof WidgetScreen) {
            class_4372.method_16014(this.field_1729.method_1603() * this.field_1704.method_4495(), this.field_1729.method_1604() * this.field_1704.method_4495());
        }
        OpenScreenEvent openScreenEvent = OpenScreenEvent.get(class_4372);
        MeteorClient.EVENT_BUS.post(openScreenEvent);
        if (openScreenEvent.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Redirect(method={"doItemUse"}, at=@At(value="FIELD", target="Lnet/minecraft/client/MinecraftClient;crosshairTarget:Lnet/minecraft/util/hit/HitResult;", ordinal=1))
    private class_239 doItemUseMinecraftClientCrosshairTargetProxy(class_310 class_3102) {
        return MeteorClient.EVENT_BUS.post(ItemUseCrosshairTargetEvent.get((class_239)class_3102.field_1765)).target;
    }

    @ModifyVariable(method={"reloadResources"}, at=@At(value="STORE"), ordinal=0)
    private CompletableFuture<Void> onReloadResourcesNewCompletableFuture(CompletableFuture<Void> completableFuture) {
        completableFuture.thenRun(MinecraftClientMixin::lambda$onReloadResourcesNewCompletableFuture$0);
        return completableFuture;
    }

    @ModifyArg(method={"updateWindowTitle"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/util/Window;setTitle(Ljava/lang/String;)V"))
    private String setTitle(String string) {
        if (Config.get() == null || !Config.get().customWindowTitle) {
            return "Skidtrap 0.3.1 | cracked by github.com/PlutoSolutions";
        }
        return Placeholders.apply(Config.get().customWindowTitleText);
    }

    @Override
    public void rightClick() {
        this.rightClick = true;
    }

    private static void lambda$onReloadResourcesNewCompletableFuture$0() {
        MeteorClient.EVENT_BUS.post(ResourcePacksReloadedEvent.get());
    }
}

