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

public class DamageEvent {
    public /* synthetic */ class_1309 entity;
    public /* synthetic */ class_1282 source;
    private static final /* synthetic */ DamageEvent INSTANCE;

    static {
        INSTANCE = new DamageEvent();
    }

    public DamageEvent() {
        DamageEvent lIIlIIlllIIIlll;
    }

    public static DamageEvent get(class_1309 lIIlIIlllIIIIll, class_1282 lIIlIIlllIIIIII) {
        DamageEvent.INSTANCE.entity = lIIlIIlllIIIIll;
        DamageEvent.INSTANCE.source = lIIlIIlllIIIIII;
        return INSTANCE;
    }
}

