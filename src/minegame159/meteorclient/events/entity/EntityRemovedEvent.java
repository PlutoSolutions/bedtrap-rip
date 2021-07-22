/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1297;

public class EntityRemovedEvent {
    private static final /* synthetic */ EntityRemovedEvent INSTANCE;
    public /* synthetic */ class_1297 entity;

    public static EntityRemovedEvent get(class_1297 llllIIlIIIlIIII) {
        EntityRemovedEvent.INSTANCE.entity = llllIIlIIIlIIII;
        return INSTANCE;
    }

    public EntityRemovedEvent() {
        EntityRemovedEvent llllIIlIIIlIIlI;
    }

    static {
        INSTANCE = new EntityRemovedEvent();
    }
}

