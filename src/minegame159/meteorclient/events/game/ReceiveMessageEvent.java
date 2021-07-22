/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 */
package minegame159.meteorclient.events.game;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_2561;

public class ReceiveMessageEvent
extends Cancellable {
    private static final /* synthetic */ ReceiveMessageEvent INSTANCE;
    public /* synthetic */ class_2561 message;
    public /* synthetic */ int id;

    public ReceiveMessageEvent() {
        ReceiveMessageEvent lllllllIIllllII;
    }

    static {
        INSTANCE = new ReceiveMessageEvent();
    }

    public static ReceiveMessageEvent get(class_2561 lllllllIIlllIIl, int lllllllIIlllIII) {
        INSTANCE.setCancelled(false);
        ReceiveMessageEvent.INSTANCE.message = lllllllIIlllIIl;
        ReceiveMessageEvent.INSTANCE.id = lllllllIIlllIII;
        return INSTANCE;
    }
}

