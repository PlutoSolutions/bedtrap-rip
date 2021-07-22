/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  com.mojang.authlib.GameProfile
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.class_2596
 *  net.minecraft.class_2797
 *  net.minecraft.class_310
 *  net.minecraft.class_437
 *  net.minecraft.class_634
 *  net.minecraft.class_638
 *  net.minecraft.class_742
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import baritone.api.BaritoneAPI;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.DropItemsEvent;
import minegame159.meteorclient.events.entity.player.SendMovementPacketsEvent;
import minegame159.meteorclient.events.game.SendMessageEvent;
import minegame159.meteorclient.systems.commands.Commands;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.NoSlow;
import minegame159.meteorclient.systems.modules.movement.Scaffold;
import minegame159.meteorclient.systems.modules.movement.Sneak;
import minegame159.meteorclient.systems.modules.movement.Velocity;
import minegame159.meteorclient.systems.modules.player.Portals;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_2596;
import net.minecraft.class_2797;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_746.class})
public abstract class ClientPlayerEntityMixin
extends class_742 {
    @Shadow
    @Final
    public class_634 field_3944;

    public ClientPlayerEntityMixin(class_638 class_6382, GameProfile gameProfile) {
        super(class_6382, gameProfile);
    }

    @Inject(method={"dropSelectedItem"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDropSelectedItem(boolean bl, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (MeteorClient.EVENT_BUS.post(DropItemsEvent.get(this.method_6047())).isCancelled()) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }

    @Inject(at={@At(value="HEAD")}, method={"sendChatMessage"}, cancellable=true)
    private void onSendChatMessage(String string, CallbackInfo callbackInfo) {
        if (!(string.startsWith(Config.get().prefix) || string.startsWith("/") || string.startsWith((String)BaritoneAPI.getSettings().prefix.value))) {
            SendMessageEvent sendMessageEvent = MeteorClient.EVENT_BUS.post(SendMessageEvent.get(string));
            if (!sendMessageEvent.isCancelled()) {
                this.field_3944.method_2883((class_2596)new class_2797(sendMessageEvent.message));
            }
            callbackInfo.cancel();
            return;
        }
        if (string.startsWith(Config.get().prefix)) {
            try {
                Commands.get().dispatch(string.substring(Config.get().prefix.length()));
            }
            catch (CommandSyntaxException commandSyntaxException) {
                ChatUtils.error(commandSyntaxException.getMessage(), new Object[0]);
            }
            callbackInfo.cancel();
        }
    }

    @Redirect(method={"updateNausea"}, at=@At(value="FIELD", target="Lnet/minecraft/client/MinecraftClient;currentScreen:Lnet/minecraft/client/gui/screen/Screen;"))
    private class_437 updateNauseaGetCurrentScreenProxy(class_310 class_3102) {
        if (Modules.get().isActive(Portals.class)) {
            return null;
        }
        return class_3102.field_1755;
    }

    @Redirect(method={"tickMovement"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean proxy_tickMovement_isUsingItem(class_746 class_7462) {
        if (Modules.get().get(NoSlow.class).items()) {
            return false;
        }
        return class_7462.method_6115();
    }

    @Inject(method={"isSneaking"}, at={@At(value="HEAD")}, cancellable=true)
    private void onIsSneaking(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (Modules.get().isActive(Scaffold.class)) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }

    @Inject(method={"shouldSlowDown"}, at={@At(value="HEAD")}, cancellable=true)
    private void onShouldSlowDown(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (Modules.get().get(NoSlow.class).sneaking()) {
            callbackInfoReturnable.setReturnValue((Object)this.method_20448());
        }
    }

    @Inject(method={"pushOutOfBlocks"}, at={@At(value="HEAD")}, cancellable=true)
    private void onPushOutOfBlocks(double d, double d2, CallbackInfo callbackInfo) {
        Velocity velocity = Modules.get().get(Velocity.class);
        if (velocity.isActive() && velocity.blocks.get().booleanValue()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"sendMovementPackets"}, at={@At(value="HEAD")})
    private void onSendMovementPacketsHead(CallbackInfo callbackInfo) {
        MeteorClient.EVENT_BUS.post(SendMovementPacketsEvent.Pre.get());
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V", ordinal=0)})
    private void onTickHasVehicleBeforeSendPackets(CallbackInfo callbackInfo) {
        MeteorClient.EVENT_BUS.post(SendMovementPacketsEvent.Pre.get());
    }

    @Inject(method={"sendMovementPackets"}, at={@At(value="TAIL")})
    private void onSendMovementPacketsTail(CallbackInfo callbackInfo) {
        MeteorClient.EVENT_BUS.post(SendMovementPacketsEvent.Post.get());
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V", ordinal=1, shift=At.Shift.AFTER)})
    private void onTickHasVehicleAfterSendPackets(CallbackInfo callbackInfo) {
        MeteorClient.EVENT_BUS.post(SendMovementPacketsEvent.Post.get());
    }

    @Redirect(method={"sendMovementPackets"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isSneaking()Z"))
    private boolean isSneaking(class_746 class_7462) {
        return Modules.get().get(Sneak.class).doPacket() || Modules.get().get(NoSlow.class).airStrict() || class_7462.method_5715();
    }
}

