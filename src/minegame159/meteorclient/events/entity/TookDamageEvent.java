/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1282
 *  net.minecraft.class_1309
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1282;
import net.minecraft.class_1309;

public class TookDamageEvent {
    public /* synthetic */ class_1309 entity;
    private static final /* synthetic */ TookDamageEvent INSTANCE;
    public /* synthetic */ class_1282 source;

    static {
        INSTANCE = new TookDamageEvent();
    }

    public TookDamageEvent() {
        TookDamageEvent lllllllllllllllllIllIIIlIIlllllI;
    }

    public static TookDamageEvent get(class_1309 lllllllllllllllllIllIIIlIIlllIIl, class_1282 lllllllllllllllllIllIIIlIIlllIII) {
        TookDamageEvent.INSTANCE.entity = lllllllllllllllllIllIIIlIIlllIIl;
        TookDamageEvent.INSTANCE.source = lllllllllllllllllIllIIIlIIlllIII;
        return INSTANCE;
    }
}

