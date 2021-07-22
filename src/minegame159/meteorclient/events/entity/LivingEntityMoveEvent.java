/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1309;
import net.minecraft.class_243;

public class LivingEntityMoveEvent {
    public /* synthetic */ class_243 movement;
    public /* synthetic */ class_1309 entity;
    private static final /* synthetic */ LivingEntityMoveEvent INSTANCE;

    public LivingEntityMoveEvent() {
        LivingEntityMoveEvent lllllllllllllllllllIIlllllIIlllI;
    }

    public static LivingEntityMoveEvent get(class_1309 lllllllllllllllllllIIlllllIIlIIl, class_243 lllllllllllllllllllIIlllllIIlIII) {
        LivingEntityMoveEvent.INSTANCE.entity = lllllllllllllllllllIIlllllIIlIIl;
        LivingEntityMoveEvent.INSTANCE.movement = lllllllllllllllllllIIlllllIIlIII;
        return INSTANCE;
    }

    static {
        INSTANCE = new LivingEntityMoveEvent();
    }
}

