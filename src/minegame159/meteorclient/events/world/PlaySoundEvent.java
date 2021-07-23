/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.world;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_1113;

public class PlaySoundEvent
extends Cancellable {
    private static final PlaySoundEvent INSTANCE = new PlaySoundEvent();
    public class_1113 sound;

    public static PlaySoundEvent get(class_1113 class_11132) {
        INSTANCE.setCancelled(false);
        PlaySoundEvent.INSTANCE.sound = class_11132;
        return INSTANCE;
    }
}

