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
    public /* synthetic */ class_2680 newState;
    public /* synthetic */ class_2338 pos;
    private static final /* synthetic */ BlockUpdateEvent INSTANCE;
    public /* synthetic */ class_2680 oldState;

    public BlockUpdateEvent() {
        BlockUpdateEvent lllllllllllllllllIIlIIlIIlIlIIII;
    }

    public static BlockUpdateEvent get(class_2338 lllllllllllllllllIIlIIlIIlIIlIII, class_2680 lllllllllllllllllIIlIIlIIlIIlIlI, class_2680 lllllllllllllllllIIlIIlIIlIIlIIl) {
        BlockUpdateEvent.INSTANCE.pos = lllllllllllllllllIIlIIlIIlIIlIII;
        BlockUpdateEvent.INSTANCE.oldState = lllllllllllllllllIIlIIlIIlIIlIlI;
        BlockUpdateEvent.INSTANCE.newState = lllllllllllllllllIIlIIlIIlIIlIIl;
        return INSTANCE;
    }

    static {
        INSTANCE = new BlockUpdateEvent();
    }
}

