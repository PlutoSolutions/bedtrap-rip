/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.entity.player;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_2338;
import net.minecraft.class_2350;

public class StartBreakingBlockEvent
extends Cancellable {
    public class_2338 blockPos;
    public class_2350 direction;
    private static final StartBreakingBlockEvent INSTANCE = new StartBreakingBlockEvent();

    public static StartBreakingBlockEvent get(class_2338 class_23382, class_2350 class_23502) {
        INSTANCE.setCancelled(false);
        StartBreakingBlockEvent.INSTANCE.blockPos = class_23382;
        StartBreakingBlockEvent.INSTANCE.direction = class_23502;
        return INSTANCE;
    }
}

