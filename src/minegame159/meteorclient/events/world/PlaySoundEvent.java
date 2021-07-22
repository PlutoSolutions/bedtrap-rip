/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1113
 */
package minegame159.meteorclient.events.world;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_1113;

public class PlaySoundEvent
extends Cancellable {
    private static final /* synthetic */ PlaySoundEvent INSTANCE;
    public /* synthetic */ class_1113 sound;

    public static PlaySoundEvent get(class_1113 llllIllllIllII) {
        INSTANCE.setCancelled(false);
        PlaySoundEvent.INSTANCE.sound = llllIllllIllII;
        return INSTANCE;
    }

    public PlaySoundEvent() {
        PlaySoundEvent llllIllllIlllI;
    }

    static {
        INSTANCE = new PlaySoundEvent();
    }
}

