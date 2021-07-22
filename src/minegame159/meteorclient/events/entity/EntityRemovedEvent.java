/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1297;

public class EntityRemovedEvent {
    private static final EntityRemovedEvent INSTANCE = new EntityRemovedEvent();
    public class_1297 entity;

    public static EntityRemovedEvent get(class_1297 class_12972) {
        EntityRemovedEvent.INSTANCE.entity = class_12972;
        return INSTANCE;
    }
}

