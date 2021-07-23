/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public class BreakBlockEvent {
    private static final BreakBlockEvent INSTANCE = new BreakBlockEvent();
    public class_2338 blockPos;

    public static BreakBlockEvent get(class_2338 class_23382) {
        BreakBlockEvent.INSTANCE.blockPos = class_23382;
        return INSTANCE;
    }

    public class_2680 getBlockState(class_1937 class_19372) {
        return class_19372.method_8320(this.blockPos);
    }
}

