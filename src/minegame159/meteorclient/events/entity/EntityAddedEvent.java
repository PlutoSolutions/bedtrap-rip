/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1297;

public class EntityAddedEvent {
    private static final /* synthetic */ EntityAddedEvent INSTANCE;
    public /* synthetic */ class_1297 entity;

    public EntityAddedEvent() {
        EntityAddedEvent lIllIlIllllIII;
    }

    public static EntityAddedEvent get(class_1297 lIllIlIlllIlIl) {
        EntityAddedEvent.INSTANCE.entity = lIllIlIlllIlIl;
        return INSTANCE;
    }

    static {
        INSTANCE = new EntityAddedEvent();
    }
}

