/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2586
 */
package minegame159.meteorclient.events.render;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_2586;

public class RenderBlockEntityEvent
extends Cancellable {
    private static final /* synthetic */ RenderBlockEntityEvent INSTANCE;
    public /* synthetic */ class_2586 blockEntity;

    static {
        INSTANCE = new RenderBlockEntityEvent();
    }

    public RenderBlockEntityEvent() {
        RenderBlockEntityEvent lllIlIlIlIlI;
    }

    public static RenderBlockEntityEvent get(class_2586 lllIlIlIIllI) {
        INSTANCE.setCancelled(false);
        RenderBlockEntityEvent.INSTANCE.blockEntity = lllIlIlIIllI;
        return INSTANCE;
    }
}

