/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 */
package minegame159.meteorclient.events.entity.player;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_1297;

public class AttackEntityEvent
extends Cancellable {
    private static final /* synthetic */ AttackEntityEvent INSTANCE;
    public /* synthetic */ class_1297 entity;

    static {
        INSTANCE = new AttackEntityEvent();
    }

    public AttackEntityEvent() {
        AttackEntityEvent llIlIIlIlIIlllI;
    }

    public static AttackEntityEvent get(class_1297 llIlIIlIlIIlIlI) {
        INSTANCE.setCancelled(false);
        AttackEntityEvent.INSTANCE.entity = llIlIIlIlIIlIlI;
        return INSTANCE;
    }
}

