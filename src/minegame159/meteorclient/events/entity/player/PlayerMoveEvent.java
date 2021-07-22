/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1313
 *  net.minecraft.class_243
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1313;
import net.minecraft.class_243;

public class PlayerMoveEvent {
    public /* synthetic */ class_1313 type;
    private static final /* synthetic */ PlayerMoveEvent INSTANCE;
    public /* synthetic */ class_243 movement;

    public static PlayerMoveEvent get(class_1313 lllllllllllllllllllllIlIllIIIIII, class_243 lllllllllllllllllllllIlIlIllllIl) {
        PlayerMoveEvent.INSTANCE.type = lllllllllllllllllllllIlIllIIIIII;
        PlayerMoveEvent.INSTANCE.movement = lllllllllllllllllllllIlIlIllllIl;
        return INSTANCE;
    }

    static {
        INSTANCE = new PlayerMoveEvent();
    }

    public PlayerMoveEvent() {
        PlayerMoveEvent lllllllllllllllllllllIlIllIIIlII;
    }
}

