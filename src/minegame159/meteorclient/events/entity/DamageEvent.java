/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1282;
import net.minecraft.class_1309;

public class DamageEvent {
    public class_1309 entity;
    public class_1282 source;
    private static final DamageEvent INSTANCE = new DamageEvent();

    public static DamageEvent get(class_1309 class_13092, class_1282 class_12822) {
        DamageEvent.INSTANCE.entity = class_13092;
        DamageEvent.INSTANCE.source = class_12822;
        return INSTANCE;
    }
}

