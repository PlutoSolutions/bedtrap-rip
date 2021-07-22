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
    public class_243 movement;
    public class_1309 entity;
    private static final LivingEntityMoveEvent INSTANCE = new LivingEntityMoveEvent();

    public static LivingEntityMoveEvent get(class_1309 class_13092, class_243 class_2432) {
        LivingEntityMoveEvent.INSTANCE.entity = class_13092;
        LivingEntityMoveEvent.INSTANCE.movement = class_2432;
        return INSTANCE;
    }
}

