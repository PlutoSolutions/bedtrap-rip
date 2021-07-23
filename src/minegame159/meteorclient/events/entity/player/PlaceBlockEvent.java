/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_2248;
import net.minecraft.class_2338;

public class PlaceBlockEvent {
    public class_2248 block;
    private static final PlaceBlockEvent INSTANCE = new PlaceBlockEvent();
    public class_2338 blockPos;

    public static PlaceBlockEvent get(class_2338 class_23382, class_2248 class_22482) {
        PlaceBlockEvent.INSTANCE.blockPos = class_23382;
        PlaceBlockEvent.INSTANCE.block = class_22482;
        return INSTANCE;
    }
}

