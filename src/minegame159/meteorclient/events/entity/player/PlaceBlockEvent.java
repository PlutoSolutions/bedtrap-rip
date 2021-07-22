/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_2248;
import net.minecraft.class_2338;

public class PlaceBlockEvent {
    public /* synthetic */ class_2248 block;
    private static final /* synthetic */ PlaceBlockEvent INSTANCE;
    public /* synthetic */ class_2338 blockPos;

    public static PlaceBlockEvent get(class_2338 llllIIllllIIl, class_2248 llllIIllllIII) {
        PlaceBlockEvent.INSTANCE.blockPos = llllIIllllIIl;
        PlaceBlockEvent.INSTANCE.block = llllIIllllIII;
        return INSTANCE;
    }

    static {
        INSTANCE = new PlaceBlockEvent();
    }

    public PlaceBlockEvent() {
        PlaceBlockEvent llllIIlllllII;
    }
}

