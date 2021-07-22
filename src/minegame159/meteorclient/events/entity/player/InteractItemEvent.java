/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1268;
import net.minecraft.class_1269;

public class InteractItemEvent {
    public /* synthetic */ class_1269 toReturn;
    private static final /* synthetic */ InteractItemEvent INSTANCE;
    public /* synthetic */ class_1268 hand;

    public InteractItemEvent() {
        InteractItemEvent llllllllllllllllllllIlIlIIlllIlI;
    }

    static {
        INSTANCE = new InteractItemEvent();
    }

    public static InteractItemEvent get(class_1268 llllllllllllllllllllIlIlIIllIllI) {
        InteractItemEvent.INSTANCE.hand = llllllllllllllllllllIlIlIIllIllI;
        InteractItemEvent.INSTANCE.toReturn = null;
        return INSTANCE;
    }
}

