/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2394
 */
package minegame159.meteorclient.events.world;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_2394;

public class ParticleEvent
extends Cancellable {
    public class_2394 particle;
    private static final ParticleEvent INSTANCE = new ParticleEvent();

    public static ParticleEvent get(class_2394 class_23942) {
        INSTANCE.setCancelled(false);
        ParticleEvent.INSTANCE.particle = class_23942;
        return INSTANCE;
    }
}

