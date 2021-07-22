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
    public class_2596<?> packet;

    public static class Send
    extends PacketEvent {
        private static final Send INSTANCE = new Send();

        public static Send get(class_2596<?> class_25962) {
            INSTANCE.setCancelled(false);
            Send.INSTANCE.packet = class_25962;
            return INSTANCE;
        }
    }

    public static class Receive
    extends PacketEvent {
        private static final Receive INSTANCE = new Receive();

        public static Receive get(class_2596<?> class_25962) {
            INSTANCE.setCancelled(false);
            Receive.INSTANCE.packet = class_25962;
            return INSTANCE;
        }
    }

    public static class Sent
    extends PacketEvent {
        private static final Sent INSTANCE = new Sent();

        public static Sent get(class_2596<?> class_25962) {
            Sent.INSTANCE.packet = class_25962;
            return INSTANCE;
        }
    }
}

