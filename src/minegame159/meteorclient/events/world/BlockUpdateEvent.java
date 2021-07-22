/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.events.world;

import net.minecraft.class_2338;
import net.minecraft.class_2680;

public class BlockUpdateEvent {
    public class_2680 newState;
    public class_2338 pos;
    private static final BlockUpdateEvent INSTANCE = new BlockUpdateEvent();
    public class_2680 oldState;

    public static BlockUpdateEvent get(class_2338 class_23382, class_2680 class_26802, class_2680 class_26803) {
        BlockUpdateEvent.INSTANCE.pos = class_23382;
        BlockUpdateEvent.INSTANCE.oldState = class_26802;
        BlockUpdateEvent.INSTANCE.newState = class_26803;
        return INSTANCE;
    }
}

