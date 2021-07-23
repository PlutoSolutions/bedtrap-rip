/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1268;
import net.minecraft.class_1269;

public class InteractItemEvent {
    public class_1269 toReturn;
    private static final InteractItemEvent INSTANCE = new InteractItemEvent();
    public class_1268 hand;

    public static InteractItemEvent get(class_1268 class_12682) {
        InteractItemEvent.INSTANCE.hand = class_12682;
        InteractItemEvent.INSTANCE.toReturn = null;
        return INSTANCE;
    }
}

