/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_1299
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2172
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_2604
 *  net.minecraft.class_2767
 *  net.minecraft.class_3417
 */
package minegame159.meteorclient.systems.commands.commands;

import baritone.api.BaritoneAPI;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.Arrays;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.utils.player.ChatUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1299;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2172;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2604;
import net.minecraft.class_2767;
import net.minecraft.class_3417;

public class LocateCommand
extends Command {
    private final /* synthetic */ List<class_2248> monumentBlocks;
    private /* synthetic */ class_243 secondStart;
    private /* synthetic */ class_243 firstStart;
    private /* synthetic */ class_243 firstEnd;
    private final /* synthetic */ List<class_2248> netherFortressBlocks;
    private final /* synthetic */ List<class_2248> strongholdBlocks;
    private /* synthetic */ class_243 secondEnd;

    private void findStronghold() {
        LocateCommand lIIllIllIIIllI;
        if (lIIllIllIIIllI.firstStart == null || lIIllIllIIIllI.firstEnd == null || lIIllIllIIIllI.secondStart == null || lIIllIllIIIllI.secondEnd == null) {
            lIIllIllIIIllI.error("Missing position data", new Object[0]);
            lIIllIllIIIllI.cancel();
            return;
        }
        double[] lIIllIllIIlIll = new double[]{lIIllIllIIIllI.secondStart.field_1352, lIIllIllIIIllI.secondStart.field_1350, lIIllIllIIIllI.secondEnd.field_1352, lIIllIllIIIllI.secondEnd.field_1350};
        double[] lIIllIllIIlIlI = new double[]{lIIllIllIIIllI.firstStart.field_1352, lIIllIllIIIllI.firstStart.field_1350, lIIllIllIIIllI.firstEnd.field_1352, lIIllIllIIIllI.firstEnd.field_1350};
        double[] lIIllIllIIlIIl = lIIllIllIIIllI.calcIntersection(lIIllIllIIlIll, lIIllIllIIlIlI);
        if (Double.isNaN(lIIllIllIIlIIl[0]) || Double.isNaN(lIIllIllIIlIIl[1]) || Double.isInfinite(lIIllIllIIlIIl[0]) || Double.isInfinite(lIIllIllIIlIIl[1])) {
            lIIllIllIIIllI.error("Lines are parallel", new Object[0]);
            lIIllIllIIIllI.cancel();
            return;
        }
        BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("stop");
        MeteorClient.EVENT_BUS.unsubscribe(lIIllIllIIIllI);
        class_243 lIIllIllIIlIII = new class_243(lIIllIllIIlIIl[0], 0.0, lIIllIllIIlIIl[1]);
        class_2585 lIIllIllIIIlll = new class_2585("Stronghold roughly located at ");
        lIIllIllIIIlll.method_10852((class_2561)ChatUtils.formatCoords(lIIllIllIIlIII));
        lIIllIllIIIlll.method_27693(".");
        lIIllIllIIIllI.info((class_2561)lIIllIllIIIlll);
    }

    @EventHandler
    private void onReadPacket(PacketEvent.Receive lIIllIllllIIlI) {
        class_2767 lIIllIllllIllI;
        LocateCommand lIIllIllllIlIl;
        class_2604 lIIllIllllIlll;
        if (lIIllIllllIIlI.packet instanceof class_2604 && (lIIllIllllIlll = (class_2604)lIIllIllllIIlI.packet).method_11169() == class_1299.field_6061) {
            lIIllIllllIlIl.firstPosition(lIIllIllllIlll.method_11175(), lIIllIllllIlll.method_11174(), lIIllIllllIlll.method_11176());
        }
        if (lIIllIllllIIlI.packet instanceof class_2767 && (lIIllIllllIllI = (class_2767)lIIllIllllIIlI.packet).method_11894() == class_3417.field_15210) {
            lIIllIllllIlIl.lastPosition(lIIllIllllIllI.method_11890(), lIIllIllllIllI.method_11889(), lIIllIllllIllI.method_11893());
        }
    }

    private class_243 findByBlockList(List<class_2248> lIIllIllllllll) {
        List lIIllIlllllllI = BaritoneAPI.getProvider().getWorldScanner().scanChunkRadius(BaritoneAPI.getProvider().getPrimaryBaritone().getPlayerContext(), lIIllIllllllll, 64, 10, 32);
        if (lIIllIlllllllI.isEmpty()) {
            return null;
        }
        if (lIIllIlllllllI.size() < 3) {
            LocateCommand lIIlllIIIIIIII;
            lIIlllIIIIIIII.warning("Only %d block(s) found. This search might be a false positive.", lIIllIlllllllI.size());
        }
        return new class_243((double)((class_2338)lIIllIlllllllI.get(0)).method_10263(), (double)((class_2338)lIIllIlllllllI.get(0)).method_10264(), (double)((class_2338)lIIllIlllllllI.get(0)).method_10260());
    }

    private double[] calcIntersection(double[] lIIllIlIllIllI, double[] lIIllIlIlIllII) {
        double lIIllIlIllIlII = lIIllIlIllIllI[3] - lIIllIlIllIllI[1];
        double lIIllIlIllIIll = lIIllIlIllIllI[0] - lIIllIlIllIllI[2];
        double lIIllIlIllIIlI = lIIllIlIllIlII * lIIllIlIllIllI[0] + lIIllIlIllIIll * lIIllIlIllIllI[1];
        double lIIllIlIllIIIl = lIIllIlIlIllII[3] - lIIllIlIlIllII[1];
        double lIIllIlIllIIII = lIIllIlIlIllII[0] - lIIllIlIlIllII[2];
        double lIIllIlIlIllll = lIIllIlIllIIIl * lIIllIlIlIllII[0] + lIIllIlIllIIII * lIIllIlIlIllII[1];
        double lIIllIlIlIlllI = lIIllIlIllIlII * lIIllIlIllIIII - lIIllIlIllIIIl * lIIllIlIllIIll;
        return new double[]{(lIIllIlIllIIII * lIIllIlIllIIlI - lIIllIlIllIIll * lIIllIlIlIllll) / lIIllIlIlIlllI, (lIIllIlIllIlII * lIIllIlIlIllll - lIIllIlIllIIIl * lIIllIlIllIIlI) / lIIllIlIlIlllI};
    }

    private void lastPosition(double lIIllIllIlIllI, double lIIllIllIlIlIl, double lIIllIllIlIlII) {
        LocateCommand lIIllIllIlllII;
        lIIllIllIlllII.info("%s Eye of Ender's trajectory saved.", lIIllIllIlllII.firstEnd == null ? "First" : "Second");
        class_243 lIIllIllIllIII = new class_243(lIIllIllIlIllI, lIIllIllIlIlIl, lIIllIllIlIlII);
        if (lIIllIllIlllII.firstEnd == null) {
            lIIllIllIlllII.firstEnd = lIIllIllIllIII;
            lIIllIllIlllII.info("Please throw the second Eye Of Ender from a different location.", new Object[0]);
        } else {
            lIIllIllIlllII.secondEnd = lIIllIllIllIII;
            lIIllIllIlllII.findStronghold();
        }
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lIIlllIIIIlIIl) {
        LocateCommand lIIlllIIIIlIlI;
        lIIlllIIIIlIIl.then(LocateCommand.literal("buried_treasure").executes(lIIllIIlIIIIII -> {
            LocateCommand lIIllIIIlllIIl;
            class_1799 lIIllIIIllllll = LocateCommand.mc.field_1724.field_7514.method_7391();
            if (lIIllIIIllllll.method_7909() != class_1802.field_8204) {
                lIIllIIIlllIIl.error("You need to hold a treasure map first", new Object[0]);
                return 1;
            }
            class_2487 lIIllIIIlllllI = lIIllIIIllllll.method_7969();
            class_2499 lIIllIIIllllIl = (class_2499)lIIllIIIlllllI.method_10580("Decorations");
            if (lIIllIIIllllIl == null) {
                lIIllIIIlllIIl.error("Couldn't locate the cross. Are you holding a (highlight)treasure map(default)?", new Object[0]);
                return 1;
            }
            class_2487 lIIllIIIllllII = lIIllIIIllllIl.method_10602(0);
            if (lIIllIIIllllII == null) {
                lIIllIIIlllIIl.error("Couldn't locate the cross. Are you holding a (highlight)treasure map(default)?", new Object[0]);
                return 1;
            }
            class_243 lIIllIIIlllIll = new class_243(lIIllIIIllllII.method_10574("x"), lIIllIIIllllII.method_10574("y"), lIIllIIIllllII.method_10574("z"));
            class_2585 lIIllIIIlllIlI = new class_2585("Buried Treasure located at ");
            lIIllIIIlllIlI.method_10852((class_2561)ChatUtils.formatCoords(lIIllIIIlllIll));
            lIIllIIIlllIlI.method_27693(".");
            lIIllIIIlllIIl.info((class_2561)lIIllIIIlllIlI);
            return 1;
        }));
        lIIlllIIIIlIIl.then(LocateCommand.literal("lodestone").executes(lIIllIIlIlIlII -> {
            LocateCommand lIIllIIlIlIlIl;
            class_1799 lIIllIIlIlIIll = LocateCommand.mc.field_1724.field_7514.method_7391();
            if (lIIllIIlIlIIll.method_7909() != class_1802.field_8251) {
                lIIllIIlIlIlIl.error("You need to hold a lodestone compass", new Object[0]);
                return 1;
            }
            class_2487 lIIllIIlIlIIlI = lIIllIIlIlIIll.method_7969();
            if (lIIllIIlIlIIlI == null) {
                lIIllIIlIlIlIl.error("Couldn't get the NBT data. Are you holding a (highlight)lodestone(default) compass?", new Object[0]);
                return 1;
            }
            class_2487 lIIllIIlIlIIIl = lIIllIIlIlIIlI.method_10562("LodestonePos");
            if (lIIllIIlIlIIIl == null) {
                lIIllIIlIlIlIl.error("Couldn't get the NBT data. Are you holding a (highlight)lodestone(default) compass?", new Object[0]);
                return 1;
            }
            class_243 lIIllIIlIlIIII = new class_243(lIIllIIlIlIIIl.method_10574("X"), lIIllIIlIlIIIl.method_10574("Y"), lIIllIIlIlIIIl.method_10574("Z"));
            class_2585 lIIllIIlIIllll = new class_2585("Lodestone located at ");
            lIIllIIlIIllll.method_10852((class_2561)ChatUtils.formatCoords(lIIllIIlIlIIII));
            lIIllIIlIIllll.method_27693(".");
            lIIllIIlIlIlIl.info((class_2561)lIIllIIlIIllll);
            return 1;
        }));
        lIIlllIIIIlIIl.then(LocateCommand.literal("mansion").executes(lIIllIIllIlIIl -> {
            LocateCommand lIIllIIllIIIlI;
            class_1799 lIIllIIllIlIII = LocateCommand.mc.field_1724.field_7514.method_7391();
            if (lIIllIIllIlIII.method_7909() != class_1802.field_8204) {
                lIIllIIllIIIlI.error("You need to hold a woodland explorer map first", new Object[0]);
                return 1;
            }
            class_2487 lIIllIIllIIlll = lIIllIIllIlIII.method_7969();
            class_2499 lIIllIIllIIllI = (class_2499)lIIllIIllIIlll.method_10580("Decorations");
            if (lIIllIIllIIllI == null) {
                lIIllIIllIIIlI.error("Couldn't locate the mansion. Are you holding a (highlight)woodland explorer map(default)?", new Object[0]);
                return 1;
            }
            class_2487 lIIllIIllIIlIl = lIIllIIllIIllI.method_10602(0);
            if (lIIllIIllIIlIl == null) {
                lIIllIIllIIIlI.error("Couldn't locate the mansion. Are you holding a (highlight)woodland explorer map(default)?", new Object[0]);
                return 1;
            }
            class_243 lIIllIIllIIlII = new class_243(lIIllIIllIIlIl.method_10574("x"), lIIllIIllIIlIl.method_10574("y"), lIIllIIllIIlIl.method_10574("z"));
            class_2585 lIIllIIllIIIll = new class_2585("Mansion located at ");
            lIIllIIllIIIll.method_10852((class_2561)ChatUtils.formatCoords(lIIllIIllIIlII));
            lIIllIIllIIIll.method_27693(".");
            lIIllIIllIIIlI.info((class_2561)lIIllIIllIIIll);
            return 1;
        }));
        lIIlllIIIIlIIl.then(LocateCommand.literal("stronghold").executes(lIIllIIlllIlll -> {
            LocateCommand lIIllIIllllIII;
            FindItemResult lIIllIIlllIllI = InvUtils.findInHotbar(class_1802.field_8449);
            if (lIIllIIlllIllI.found()) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("follow entity minecraft:eye_of_ender");
                lIIllIIllllIII.firstStart = null;
                lIIllIIllllIII.firstEnd = null;
                lIIllIIllllIII.secondStart = null;
                lIIllIIllllIII.secondEnd = null;
                MeteorClient.EVENT_BUS.subscribe(lIIllIIllllIII);
                lIIllIIllllIII.info("Please throw the first Eye of Ender", new Object[0]);
            } else {
                class_243 lIIllIIllllIlI = lIIllIIllllIII.findByBlockList(lIIllIIllllIII.strongholdBlocks);
                if (lIIllIIllllIlI == null) {
                    lIIllIIllllIII.error("No stronghold found nearby. You can use (highlight)Ender Eyes(default) for more success.", new Object[0]);
                    return 1;
                }
                class_2585 lIIllIIllllIIl = new class_2585("Stronghold located at ");
                lIIllIIllllIIl.method_10852((class_2561)ChatUtils.formatCoords(lIIllIIllllIlI));
                lIIllIIllllIIl.method_27693(".");
                lIIllIIllllIII.info((class_2561)lIIllIIllllIIl);
            }
            return 1;
        }));
        lIIlllIIIIlIIl.then(LocateCommand.literal("nether_fortress").executes(lIIllIlIIIIlII -> {
            LocateCommand lIIllIlIIIIlIl;
            class_243 lIIllIlIIIIIll = lIIllIlIIIIlIl.findByBlockList(lIIllIlIIIIlIl.netherFortressBlocks);
            if (lIIllIlIIIIIll == null) {
                lIIllIlIIIIlIl.error("No nether fortress found.", new Object[0]);
                return 1;
            }
            class_2585 lIIllIlIIIIIlI = new class_2585("Fortress located at ");
            lIIllIlIIIIIlI.method_10852((class_2561)ChatUtils.formatCoords(lIIllIlIIIIIll));
            lIIllIlIIIIIlI.method_27693(".");
            lIIllIlIIIIlIl.info((class_2561)lIIllIlIIIIIlI);
            return 1;
        }));
        lIIlllIIIIlIIl.then(LocateCommand.literal("monument").executes(lIIllIlIIlIIll -> {
            LocateCommand lIIllIlIIIllll;
            class_2487 lIIllIlIIlIlll;
            class_2487 lIIllIlIIlIllI;
            class_2499 lIIllIlIIlIlIl;
            class_1799 lIIllIlIIlIIlI = LocateCommand.mc.field_1724.field_7514.method_7391();
            if (lIIllIlIIlIIlI.method_7909() == class_1802.field_8204 && (lIIllIlIIlIlIl = (class_2499)(lIIllIlIIlIllI = lIIllIlIIlIIlI.method_7969()).method_10580("Decorations")) != null && (lIIllIlIIlIlll = lIIllIlIIlIlIl.method_10602(0)) != null) {
                class_243 lIIllIlIIllIIl = new class_243(lIIllIlIIlIlll.method_10574("x"), lIIllIlIIlIlll.method_10574("y"), lIIllIlIIlIlll.method_10574("z"));
                class_2585 lIIllIlIIllIII = new class_2585("Monument located at ");
                lIIllIlIIllIII.method_10852((class_2561)ChatUtils.formatCoords(lIIllIlIIllIIl));
                lIIllIlIIllIII.method_27693(".");
                lIIllIlIIIllll.info((class_2561)lIIllIlIIllIII);
                return 1;
            }
            class_243 lIIllIlIIlIIIl = lIIllIlIIIllll.findByBlockList(lIIllIlIIIllll.monumentBlocks);
            if (lIIllIlIIlIIIl == null) {
                lIIllIlIIIllll.error("No monument found. You can try using a (highlight)Ocean explorer map(default) for more success.", new Object[0]);
                return 1;
            }
            class_2585 lIIllIlIIlIIII = new class_2585("Monument located at ");
            lIIllIlIIlIIII.method_10852((class_2561)ChatUtils.formatCoords(lIIllIlIIlIIIl));
            lIIllIlIIlIIII.method_27693(".");
            lIIllIlIIIllll.info((class_2561)lIIllIlIIlIIII);
            return 1;
        }));
        lIIlllIIIIlIIl.then(LocateCommand.literal("cancel").executes(lIIllIlIlIIIlI -> {
            LocateCommand lIIllIlIlIIIll;
            lIIllIlIlIIIll.cancel();
            return 1;
        }));
    }

    public LocateCommand() {
        super("locate", "Locates structures", "loc");
        LocateCommand lIIlllIIIIllIl;
        lIIlllIIIIllIl.netherFortressBlocks = Arrays.asList(new class_2248[]{class_2246.field_10266, class_2246.field_10364, class_2246.field_9974});
        lIIlllIIIIllIl.monumentBlocks = Arrays.asList(new class_2248[]{class_2246.field_10006, class_2246.field_10174, class_2246.field_10297});
        lIIlllIIIIllIl.strongholdBlocks = Arrays.asList(new class_2248[]{class_2246.field_10398});
    }

    private void cancel() {
        LocateCommand lIIlllIIIIIlIl;
        lIIlllIIIIIlIl.warning("Locate canceled", new Object[0]);
        MeteorClient.EVENT_BUS.unsubscribe(lIIlllIIIIIlIl);
    }

    private void firstPosition(double lIIllIlllIlIlI, double lIIllIlllIlIIl, double lIIllIlllIlIII) {
        LocateCommand lIIllIlllIIllI;
        class_243 lIIllIlllIIlll = new class_243(lIIllIlllIlIlI, lIIllIlllIlIIl, lIIllIlllIlIII);
        if (lIIllIlllIIllI.firstStart == null) {
            lIIllIlllIIllI.firstStart = lIIllIlllIIlll;
        } else {
            lIIllIlllIIllI.secondStart = lIIllIlllIIlll;
        }
    }
}

