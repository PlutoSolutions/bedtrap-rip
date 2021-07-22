/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 *  net.minecraft.class_2540
 *  net.minecraft.class_2817
 *  net.minecraft.class_2960
 *  org.apache.commons.lang3.StringUtils
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
        VanillaSpoof lllllllllllllllllllIIlllIllIllIl;
        MeteorClient.EVENT_BUS.subscribe(lllllllllllllllllllIIlllIllIllIl.new Listener());
    }

    private class Listener {
        private Listener() {
            Listener llIllIllIIlIIll;
        }

        @EventHandler
        private void onPacketSend(PacketEvent.Send llIllIllIIIllII) {
            Listener llIllIllIIIllIl;
            if (!llIllIllIIIllIl.VanillaSpoof.this.isActive() || !(llIllIllIIIllII.packet instanceof class_2817)) {
                return;
            }
            CustomPayloadC2SPacketAccessor llIllIllIIIlIll = (CustomPayloadC2SPacketAccessor)llIllIllIIIllII.packet;
            class_2960 llIllIllIIIlIlI = llIllIllIIIlIll.getChannel();
            if (llIllIllIIIlIlI.equals((Object)class_2817.field_12831)) {
                llIllIllIIIlIll.setData(new class_2540(Unpooled.buffer()).method_10814("vanilla"));
            } else if (StringUtils.containsIgnoreCase((CharSequence)llIllIllIIIlIll.getData().toString(StandardCharsets.UTF_8), (CharSequence)"fabric")) {
                llIllIllIIIllII.cancel();
            }
        }
    }
}

