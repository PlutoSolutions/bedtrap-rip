/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1297;

public class EntityDestroyEvent {
    public /* synthetic */ class_1297 entity;
    private static final /* synthetic */ EntityDestroyEvent INSTANCE;

    static {
        INSTANCE = new EntityDestroyEvent();
    }

    public EntityDestroyEvent() {
        EntityDestroyEvent lllllllllllllllllIIIIlIlIlIIIlll;
    }

    public static EntityDestroyEvent get(class_1297 lllllllllllllllllIIIIlIlIlIIIlII) {
        EntityDestroyEvent.INSTANCE.entity = lllllllllllllllllIIIIlIlIlIIIlII;
        return INSTANCE;
    }
}

