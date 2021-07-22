/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 */
package minegame159.meteorclient.events.game;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_437;

public class OpenScreenEvent
extends Cancellable {
    private static final /* synthetic */ OpenScreenEvent INSTANCE;
    public /* synthetic */ class_437 screen;

    public static OpenScreenEvent get(class_437 lIllIlIIlIlIIIl) {
        INSTANCE.setCancelled(false);
        OpenScreenEvent.INSTANCE.screen = lIllIlIIlIlIIIl;
        return INSTANCE;
    }

    public OpenScreenEvent() {
        OpenScreenEvent lIllIlIIlIlIlIl;
    }

    static {
        INSTANCE = new OpenScreenEvent();
    }
}

