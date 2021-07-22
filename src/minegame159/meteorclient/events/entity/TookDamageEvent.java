/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1282
 *  net.minecraft.class_1309
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1282;
import net.minecraft.class_1309;

public class TookDamageEvent {
    public class_1309 entity;
    private static final TookDamageEvent INSTANCE = new TookDamageEvent();
    public class_1282 source;

    public static TookDamageEvent get(class_1309 class_13092, class_1282 class_12822) {
        TookDamageEvent.INSTANCE.entity = class_13092;
        TookDamageEvent.INSTANCE.source = class_12822;
        return INSTANCE;
    }
}

