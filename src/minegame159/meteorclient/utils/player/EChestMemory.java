/*
 * Decompiled with CFR 0.151.
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
    public static final class_2371<class_1799> ITEMS = class_2371.method_10213((int)27, (Object)class_1799.field_8037);
    private static int echestOpenedState;

    @EventHandler
    private static void onOpenScreenEvent(OpenScreenEvent openScreenEvent) {
        if (echestOpenedState == 1 && openScreenEvent.screen instanceof class_476) {
            echestOpenedState = 2;
            return;
        }
        if (echestOpenedState == 0) {
            return;
        }
        if (!(Utils.mc.field_1755 instanceof class_476)) {
            return;
        }
        class_1707 class_17072 = (class_1707)((class_476)Utils.mc.field_1755).method_17577();
        if (class_17072 == null) {
            return;
        }
        class_1263 class_12632 = class_17072.method_7629();
        for (int i = 0; i < 27; ++i) {
            ITEMS.set(i, (Object)class_12632.method_5438(i));
            if (!false) continue;
            return;
        }
        echestOpenedState = 0;
    }

    @EventHandler
    private static void onBlockActivate(BlockActivateEvent blockActivateEvent) {
        if (blockActivateEvent.blockState.method_26204() instanceof class_2336 && echestOpenedState == 0) {
            echestOpenedState = 1;
        }
    }

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(EChestMemory.class);
    }
}

