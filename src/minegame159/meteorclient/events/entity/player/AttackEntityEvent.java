/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.entity.player;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_1297;

public class AttackEntityEvent
extends Cancellable {
    private static final AttackEntityEvent INSTANCE = new AttackEntityEvent();
    public class_1297 entity;

    public static AttackEntityEvent get(class_1297 class_12972) {
        INSTANCE.setCancelled(false);
        AttackEntityEvent.INSTANCE.entity = class_12972;
        return INSTANCE;
    }
}

