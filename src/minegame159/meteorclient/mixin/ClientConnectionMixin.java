/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.class_2535
 *  net.minecraft.class_2547
 *  net.minecraft.class_2596
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import io.netty.channel.ChannelHandlerContext;
import java.io.IOException;
import java.net.InetAddress;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.ConnectToServerEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.AntiPacketKick;
import net.minecraft.class_2535;
import net.minecraft.class_2547;
import net.minecraft.class_2596;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_2535.class})
public class ClientConnectionMixin {
    @Inject(method={"handlePacket"}, at={@At(value="HEAD")}, cancellable=true)
    private static <T extends class_2547> void onHandlePacket(class_2596<T> class_25962, class_2547 class_25472, CallbackInfo callbackInfo) {
        PacketEvent.Receive receive = MeteorClient.EVENT_BUS.post(PacketEvent.Receive.get(class_25962));
        if (receive.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"connect"}, at={@At(value="HEAD")})
    private static void onConnect(InetAddress inetAddress, int n, boolean bl, CallbackInfoReturnable<class_2535> callbackInfoReturnable) {
        MeteorClient.EVENT_BUS.post(ConnectToServerEvent.get());
    }

    @Inject(method={"exceptionCaught"}, at={@At(value="HEAD")}, cancellable=true)
    private void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable, CallbackInfo callbackInfo) {
        if (throwable instanceof IOException && Modules.get().isActive(AntiPacketKick.class)) {
            callbackInfo.cancel();
        }
    }

    @Inject(at={@At(value="HEAD")}, method={"send(Lnet/minecraft/network/Packet;)V"}, cancellable=true)
    private void onSendPacketHead(class_2596<?> class_25962, CallbackInfo callbackInfo) {
        PacketEvent.Send send = MeteorClient.EVENT_BUS.post(PacketEvent.Send.get(class_25962));
        if (send.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"send(Lnet/minecraft/network/Packet;)V"}, at={@At(value="TAIL")})
    private void onSendPacketTail(class_2596<?> class_25962, CallbackInfo callbackInfo) {
        MeteorClient.EVENT_BUS.post(PacketEvent.Sent.get(class_25962));
    }
}

