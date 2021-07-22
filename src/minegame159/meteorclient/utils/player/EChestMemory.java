/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1263
 *  net.minecraft.class_1707
 *  net.minecraft.class_1799
 *  net.minecraft.class_2336
 *  net.minecraft.class_2371
 *  net.minecraft.class_476
 */
package minegame159.meteorclient.utils.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.events.world.BlockActivateEvent;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1263;
import net.minecraft.class_1707;
import net.minecraft.class_1799;
import net.minecraft.class_2336;
import net.minecraft.class_2371;
import net.minecraft.class_476;

public class EChestMemory {
    public static final /* synthetic */ class_2371<class_1799> ITEMS;
    private static /* synthetic */ int echestOpenedState;

    static {
        ITEMS = class_2371.method_10213((int)27, (Object)class_1799.field_8037);
    }

    public EChestMemory() {
        EChestMemory lIllIlIlIllIIl;
    }

    @EventHandler
    private static void onOpenScreenEvent(OpenScreenEvent lIllIlIlIIllIl) {
        if (echestOpenedState == 1 && lIllIlIlIIllIl.screen instanceof class_476) {
            echestOpenedState = 2;
            return;
        }
        if (echestOpenedState == 0) {
            return;
        }
        if (!(Utils.mc.field_1755 instanceof class_476)) {
            return;
        }
        class_1707 lIllIlIlIIllll = (class_1707)((class_476)Utils.mc.field_1755).method_17577();
        if (lIllIlIlIIllll == null) {
            return;
        }
        class_1263 lIllIlIlIIlllI = lIllIlIlIIllll.method_7629();
        for (int lIllIlIlIlIIIl = 0; lIllIlIlIlIIIl < 27; ++lIllIlIlIlIIIl) {
            ITEMS.set(lIllIlIlIlIIIl, (Object)lIllIlIlIIlllI.method_5438(lIllIlIlIlIIIl));
        }
        echestOpenedState = 0;
    }

    @EventHandler
    private static void onBlockActivate(BlockActivateEvent lIllIlIlIlIlll) {
        if (lIllIlIlIlIlll.blockState.method_26204() instanceof class_2336 && echestOpenedState == 0) {
            echestOpenedState = 1;
        }
    }

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(EChestMemory.class);
    }
}

