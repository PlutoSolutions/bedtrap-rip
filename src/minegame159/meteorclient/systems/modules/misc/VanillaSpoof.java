/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.misc;

import io.netty.buffer.Unpooled;
import java.nio.charset.StandardCharsets;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.mixin.CustomPayloadC2SPacketAccessor;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2540;
import net.minecraft.class_2817;
import net.minecraft.class_2960;
import org.apache.commons.lang3.StringUtils;

public class VanillaSpoof
extends Module {
    public VanillaSpoof() {
        super(Categories.Misc, "vanilla-spoof", "When connecting to a server it spoofs the client name to be 'vanilla'.");
        MeteorClient.EVENT_BUS.subscribe(new Listener(this, null));
    }

    private class Listener {
        final VanillaSpoof this$0;

        private Listener(VanillaSpoof vanillaSpoof) {
            this.this$0 = vanillaSpoof;
        }

        @EventHandler
        private void onPacketSend(PacketEvent.Send send) {
            if (!this.this$0.isActive() || !(send.packet instanceof class_2817)) {
                return;
            }
            CustomPayloadC2SPacketAccessor customPayloadC2SPacketAccessor = (CustomPayloadC2SPacketAccessor)send.packet;
            class_2960 class_29602 = customPayloadC2SPacketAccessor.getChannel();
            if (class_29602.equals((Object)class_2817.field_12831)) {
                customPayloadC2SPacketAccessor.setData(new class_2540(Unpooled.buffer()).method_10814("vanilla"));
            } else if (StringUtils.containsIgnoreCase((CharSequence)customPayloadC2SPacketAccessor.getData().toString(StandardCharsets.UTF_8), (CharSequence)"fabric")) {
                send.cancel();
            }
        }

        Listener(VanillaSpoof vanillaSpoof, 1 var2_2) {
            this(vanillaSpoof);
        }
    }
}

