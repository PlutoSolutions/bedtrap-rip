/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2653
 */
package minegame159.meteorclient.events.packets;

import net.minecraft.class_2653;

public class ContainerSlotUpdateEvent {
    private static final ContainerSlotUpdateEvent INSTANCE = new ContainerSlotUpdateEvent();
    public class_2653 packet;

    public static ContainerSlotUpdateEvent get(class_2653 class_26532) {
        ContainerSlotUpdateEvent.INSTANCE.packet = class_26532;
        return INSTANCE;
    }
}

