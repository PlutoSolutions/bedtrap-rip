/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1313;
import net.minecraft.class_243;

public class PlayerMoveEvent {
    public class_1313 type;
    private static final PlayerMoveEvent INSTANCE = new PlayerMoveEvent();
    public class_243 movement;

    public static PlayerMoveEvent get(class_1313 class_13132, class_243 class_2432) {
        PlayerMoveEvent.INSTANCE.type = class_13132;
        PlayerMoveEvent.INSTANCE.movement = class_2432;
        return INSTANCE;
    }
}

