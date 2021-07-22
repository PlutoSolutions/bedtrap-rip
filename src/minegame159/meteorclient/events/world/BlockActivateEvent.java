/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.events.world;

import net.minecraft.class_2680;

public class BlockActivateEvent {
    public /* synthetic */ class_2680 blockState;
    private static final /* synthetic */ BlockActivateEvent INSTANCE;

    public BlockActivateEvent() {
        BlockActivateEvent lIllllIIIIIII;
    }

    public static BlockActivateEvent get(class_2680 lIlllIllllllI) {
        BlockActivateEvent.INSTANCE.blockState = lIlllIllllllI;
        return INSTANCE;
    }

    static {
        INSTANCE = new BlockActivateEvent();
    }
}

