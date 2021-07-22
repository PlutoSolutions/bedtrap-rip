/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1297;

public class EntityAddedEvent {
    private static final EntityAddedEvent INSTANCE = new EntityAddedEvent();
    public class_1297 entity;

    public static EntityAddedEvent get(class_1297 class_12972) {
        EntityAddedEvent.INSTANCE.entity = class_12972;
        return INSTANCE;
    }
}

