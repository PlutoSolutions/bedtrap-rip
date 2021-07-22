/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 */
package minegame159.meteorclient.events.entity.player;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_2338;
import net.minecraft.class_2350;

public class StartBreakingBlockEvent
extends Cancellable {
    public /* synthetic */ class_2338 blockPos;
    public /* synthetic */ class_2350 direction;
    private static final /* synthetic */ StartBreakingBlockEvent INSTANCE;

    public static StartBreakingBlockEvent get(class_2338 llllllllllllllllllllllllIlIIlIlI, class_2350 llllllllllllllllllllllllIlIIlIIl) {
        INSTANCE.setCancelled(false);
        StartBreakingBlockEvent.INSTANCE.blockPos = llllllllllllllllllllllllIlIIlIlI;
        StartBreakingBlockEvent.INSTANCE.direction = llllllllllllllllllllllllIlIIlIIl;
        return INSTANCE;
    }

    static {
        INSTANCE = new StartBreakingBlockEvent();
    }

    public StartBreakingBlockEvent() {
        StartBreakingBlockEvent llllllllllllllllllllllllIlIIllll;
    }
}

