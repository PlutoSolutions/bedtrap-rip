/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc;

import minegame159.meteorclient.mixin.DimensionTypeAccessor;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.PlayerListEntryFactory;
import net.minecraft.class_1267;
import net.minecraft.class_1657;
import net.minecraft.class_1934;
import net.minecraft.class_1937;
import net.minecraft.class_2535;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2598;
import net.minecraft.class_310;
import net.minecraft.class_4599;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_640;
import net.minecraft.class_745;
import net.minecraft.class_761;

public class FakeClientPlayer {
    private static String lastId;
    private static class_640 playerListEntry;
    private static boolean needsNewEntry;
    private static class_1657 player;
    private static class_638 world;

    public static class_1657 getPlayer() {
        String string = Utils.mc.method_1548().method_1673();
        if (player == null || !string.equals(lastId)) {
            player = new class_745(world, Utils.mc.method_1548().method_1677());
            lastId = string;
            needsNewEntry = true;
        }
        return player;
    }

    public static class_640 getPlayerListEntry() {
        if (playerListEntry == null || needsNewEntry) {
            playerListEntry = new class_640(PlayerListEntryFactory.create(Utils.mc.method_1548().method_1677(), 0, class_1934.field_9215, (class_2561)new class_2585(Utils.mc.method_1548().method_1677().getName())));
            needsNewEntry = false;
        }
        return playerListEntry;
    }

    public static void init() {
        world = new class_638(new class_634(Utils.mc, null, new class_2535(class_2598.field_11942), Utils.mc.method_1548().method_1677()), new class_638.class_5271(class_1267.field_5802, false, false), class_1937.field_25179, DimensionTypeAccessor.getOverworld(), 1, () -> ((class_310)Utils.mc).method_16011(), new class_761(Utils.mc, new class_4599()), false, 0L);
    }
}

