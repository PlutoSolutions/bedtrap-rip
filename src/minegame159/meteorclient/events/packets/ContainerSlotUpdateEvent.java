/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2653
 */
package minegame159.meteorclient.events.packets;

import net.minecraft.class_2653;

public class ContainerSlotUpdateEvent {
    private static final /* synthetic */ ContainerSlotUpdateEvent INSTANCE;
    public /* synthetic */ class_2653 packet;

    public static ContainerSlotUpdateEvent get(class_2653 lllllllllllllllllIIIIlIlIIllIIIl) {
        ContainerSlotUpdateEvent.INSTANCE.packet = lllllllllllllllllIIIIlIlIIllIIIl;
        return INSTANCE;
    }

    static {
        INSTANCE = new ContainerSlotUpdateEvent();
    }

    public ContainerSlotUpdateEvent() {
        ContainerSlotUpdateEvent lllllllllllllllllIIIIlIlIIllIlIl;
    }
}

