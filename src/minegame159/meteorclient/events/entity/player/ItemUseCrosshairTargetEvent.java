/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_239
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_239;

public class ItemUseCrosshairTargetEvent {
    private static final /* synthetic */ ItemUseCrosshairTargetEvent INSTANCE;
    public /* synthetic */ class_239 target;

    public static ItemUseCrosshairTargetEvent get(class_239 lIllIlllIIIlIII) {
        ItemUseCrosshairTargetEvent.INSTANCE.target = lIllIlllIIIlIII;
        return INSTANCE;
    }

    public ItemUseCrosshairTargetEvent() {
        ItemUseCrosshairTargetEvent lIllIlllIIIlIlI;
    }

    static {
        INSTANCE = new ItemUseCrosshairTargetEvent();
    }
}

