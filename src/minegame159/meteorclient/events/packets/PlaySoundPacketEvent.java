/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.packets;

import net.minecraft.class_2767;

public class PlaySoundPacketEvent {
    private static final PlaySoundPacketEvent INSTANCE = new PlaySoundPacketEvent();
    public class_2767 packet;

    public static PlaySoundPacketEvent get(class_2767 class_27672) {
        PlaySoundPacketEvent.INSTANCE.packet = class_27672;
        return INSTANCE;
    }
}

