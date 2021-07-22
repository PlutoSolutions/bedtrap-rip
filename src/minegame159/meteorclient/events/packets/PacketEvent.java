/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 */
package minegame159.meteorclient.events.packets;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_2596;

public class PacketEvent
extends Cancellable {
    public /* synthetic */ class_2596<?> packet;

    public PacketEvent() {
        PacketEvent lllllllllllllllllllllIlIlIIIIIlI;
    }

    public static class Send
    extends PacketEvent {
        private static final /* synthetic */ Send INSTANCE;

        static {
            INSTANCE = new Send();
        }

        public Send() {
            Send lllllllllllllllllllIIlIlIlIllIll;
        }

        public static Send get(class_2596<?> lllllllllllllllllllIIlIlIlIllIII) {
            INSTANCE.setCancelled(false);
            Send.INSTANCE.packet = lllllllllllllllllllIIlIlIlIllIII;
            return INSTANCE;
        }
    }

    public static class Receive
    extends PacketEvent {
        private static final /* synthetic */ Receive INSTANCE;

        public Receive() {
            Receive lllllllllllllllllIIIIIIIlIlIllIl;
        }

        public static Receive get(class_2596<?> lllllllllllllllllIIIIIIIlIlIlIlI) {
            INSTANCE.setCancelled(false);
            Receive.INSTANCE.packet = lllllllllllllllllIIIIIIIlIlIlIlI;
            return INSTANCE;
        }

        static {
            INSTANCE = new Receive();
        }
    }

    public static class Sent
    extends PacketEvent {
        private static final /* synthetic */ Sent INSTANCE;

        public Sent() {
            Sent llllllllllllllllllIllIllIlIllIll;
        }

        static {
            INSTANCE = new Sent();
        }

        public static Sent get(class_2596<?> llllllllllllllllllIllIllIlIlIlll) {
            Sent.INSTANCE.packet = llllllllllllllllllIllIllIlIlIlll;
            return INSTANCE;
        }
    }
}

