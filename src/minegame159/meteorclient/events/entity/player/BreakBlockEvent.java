/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public class BreakBlockEvent {
    private static final /* synthetic */ BreakBlockEvent INSTANCE;
    public /* synthetic */ class_2338 blockPos;

    public static BreakBlockEvent get(class_2338 lllllllllllllllllIlIllIIlIlIIIIl) {
        BreakBlockEvent.INSTANCE.blockPos = lllllllllllllllllIlIllIIlIlIIIIl;
        return INSTANCE;
    }

    public BreakBlockEvent() {
        BreakBlockEvent lllllllllllllllllIlIllIIlIlIlIIl;
    }

    static {
        INSTANCE = new BreakBlockEvent();
    }

    public class_2680 getBlockState(class_1937 lllllllllllllllllIlIllIIlIlIIIll) {
        BreakBlockEvent lllllllllllllllllIlIllIIlIlIIlII;
        return lllllllllllllllllIlIllIIlIlIIIll.method_8320(lllllllllllllllllIlIllIIlIlIIlII.blockPos);
    }
}

