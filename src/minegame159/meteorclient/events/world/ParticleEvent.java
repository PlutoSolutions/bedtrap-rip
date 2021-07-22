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
    public /* synthetic */ class_2394 particle;
    private static final /* synthetic */ ParticleEvent INSTANCE;

    public ParticleEvent() {
        ParticleEvent lllIllIlIlIlIlI;
    }

    public static ParticleEvent get(class_2394 lllIllIlIlIIlll) {
        INSTANCE.setCancelled(false);
        ParticleEvent.INSTANCE.particle = lllIllIlIlIIlll;
        return INSTANCE;
    }

    static {
        INSTANCE = new ParticleEvent();
    }
}

