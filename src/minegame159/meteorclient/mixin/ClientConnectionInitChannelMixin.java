/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import minegame159.meteorclient.systems.proxies.Proxies;
import minegame159.meteorclient.systems.proxies.Proxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets={"net.minecraft.network.ClientConnection$1"})
public class ClientConnectionInitChannelMixin {
    @Inject(method={"initChannel"}, at={@At(value="HEAD")})
    private void onInitChannel(Channel channel, CallbackInfo callbackInfo) {
        Proxy proxy = Proxies.get().getEnabled();
        if (proxy == null) {
            return;
        }
        switch (1.$SwitchMap$minegame159$meteorclient$systems$proxies$ProxyType[proxy.type.ordinal()]) {
            case 1: {
                channel.pipeline().addFirst(new ChannelHandler[]{new Socks4ProxyHandler((SocketAddress)new InetSocketAddress(proxy.ip, proxy.port), proxy.username)});
                break;
            }
            case 2: {
                channel.pipeline().addFirst(new ChannelHandler[]{new Socks5ProxyHandler((SocketAddress)new InetSocketAddress(proxy.ip, proxy.port), proxy.username, proxy.password)});
            }
        }
    }
}

