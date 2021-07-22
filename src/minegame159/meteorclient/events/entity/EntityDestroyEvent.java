/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1297;

public class EntityDestroyEvent {
    public class_1297 entity;
    private static final EntityDestroyEvent INSTANCE = new EntityDestroyEvent();

    public static EntityDestroyEvent get(class_1297 class_12972) {
        EntityDestroyEvent.INSTANCE.entity = class_12972;
        return INSTANCE;
    }
}

