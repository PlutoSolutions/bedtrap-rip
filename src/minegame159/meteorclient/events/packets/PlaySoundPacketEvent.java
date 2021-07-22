/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2767
 */
package minegame159.meteorclient.events.packets;

import net.minecraft.class_2767;

public class PlaySoundPacketEvent {
    private static final /* synthetic */ PlaySoundPacketEvent INSTANCE;
    public /* synthetic */ class_2767 packet;

    public PlaySoundPacketEvent() {
        PlaySoundPacketEvent lIllllIIIIllI;
    }

    public static PlaySoundPacketEvent get(class_2767 lIllllIIIIIll) {
        PlaySoundPacketEvent.INSTANCE.packet = lIllllIIIIIll;
        return INSTANCE;
    }

    static {
        INSTANCE = new PlaySoundPacketEvent();
    }
}

