/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import baritone.api.BaritoneAPI;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
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
    private final List<class_2248> monumentBlocks;
    private class_243 secondStart;
    private class_243 firstStart;
    private class_243 firstEnd;
    private final List<class_2248> netherFortressBlocks = Arrays.asList(class_2246.field_10266, class_2246.field_10364, class_2246.field_9974);
    private final List<class_2248> strongholdBlocks;
    private class_243 secondEnd;

    private int lambda$build$5(CommandContext commandContext) throws CommandSyntaxException {
        class_2487 class_24872;
        class_2487 class_24873;
        class_2499 class_24992;
        class_1799 class_17992 = LocateCommand.mc.field_1724.field_7514.method_7391();
        if (class_17992.method_7909() == class_1802.field_8204 && (class_24992 = (class_2499)(class_24873 = class_17992.method_7969()).method_10580("Decorations")) != null && (class_24872 = class_24992.method_10602(0)) != null) {
            class_243 class_2432 = new class_243(class_24872.method_10574("x"), class_24872.method_10574("y"), class_24872.method_10574("z"));
            class_2585 class_25852 = new class_2585("Monument located at ");
            class_25852.method_10852((class_2561)ChatUtils.formatCoords(class_2432));
            class_25852.method_27693(".");
            this.info((class_2561)class_25852);
            return 1;
        }
        class_24873 = this.findByBlockList(this.monumentBlocks);
        if (class_24873 == null) {
            this.error("No monument found. You can try using a (highlight)Ocean explorer map(default) for more success.", new Object[0]);
            return 1;
        }
        class_24992 = new class_2585("Monument located at ");
        class_24992.method_10852((class_2561)ChatUtils.formatCoords((class_243)class_24873));
        class_24992.method_27693(".");
        this.info((class_2561)class_24992);
        return 1;
    }

    private void findStronghold() {
        if (this.firstStart == null || this.firstEnd == null || this.secondStart == null || this.secondEnd == null) {
            this.error("Missing position data", new Object[0]);
            this.cancel();
            return;
        }
        double[] dArray = new double[]{this.secondStart.field_1352, this.secondStart.field_1350, this.secondEnd.field_1352, this.secondEnd.field_1350};
        double[] dArray2 = new double[]{this.firstStart.field_1352, this.firstStart.field_1350, this.firstEnd.field_1352, this.firstEnd.field_1350};
        double[] dArray3 = this.calcIntersection(dArray, dArray2);
        if (Double.isNaN(dArray3[0]) || Double.isNaN(dArray3[1]) || Double.isInfinite(dArray3[0]) || Double.isInfinite(dArray3[1])) {
            this.error("Lines are parallel", new Object[0]);
            this.cancel();
            return;
        }
        BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("stop");
        MeteorClient.EVENT_BUS.unsubscribe(this);
        class_243 class_2432 = new class_243(dArray3[0], 0.0, dArray3[1]);
        class_2585 class_25852 = new class_2585("Stronghold roughly located at ");
        class_25852.method_10852((class_2561)ChatUtils.formatCoords(class_2432));
        class_25852.method_27693(".");
        this.info((class_2561)class_25852);
    }

    private int lambda$build$6(CommandContext commandContext) throws CommandSyntaxException {
        this.cancel();
        return 1;
    }

    @EventHandler
    private void onReadPacket(PacketEvent.Receive receive) {
        class_2604 class_26042;
        if (receive.packet instanceof class_2604 && (class_26042 = (class_2604)receive.packet).method_11169() == class_1299.field_6061) {
            this.firstPosition(class_26042.method_11175(), class_26042.method_11174(), class_26042.method_11176());
        }
        if (receive.packet instanceof class_2767 && (class_26042 = (class_2767)receive.packet).method_11894() == class_3417.field_15210) {
            this.lastPosition(class_26042.method_11890(), class_26042.method_11889(), class_26042.method_11893());
        }
    }

    private class_243 findByBlockList(List<class_2248> list) {
        List list2 = BaritoneAPI.getProvider().getWorldScanner().scanChunkRadius(BaritoneAPI.getProvider().getPrimaryBaritone().getPlayerContext(), list, 64, 10, 32);
        if (list2.isEmpty()) {
            return null;
        }
        if (list2.size() < 3) {
            this.warning("Only %d block(s) found. This search might be a false positive.", list2.size());
        }
        return new class_243((double)((class_2338)list2.get(0)).method_10263(), (double)((class_2338)list2.get(0)).method_10264(), (double)((class_2338)list2.get(0)).method_10260());
    }

    private int lambda$build$2(CommandContext commandContext) throws CommandSyntaxException {
        class_1799 class_17992 = LocateCommand.mc.field_1724.field_7514.method_7391();
        if (class_17992.method_7909() != class_1802.field_8204) {
            this.error("You need to hold a woodland explorer map first", new Object[0]);
            return 1;
        }
        class_2487 class_24872 = class_17992.method_7969();
        class_2499 class_24992 = (class_2499)class_24872.method_10580("Decorations");
        if (class_24992 == null) {
            this.error("Couldn't locate the mansion. Are you holding a (highlight)woodland explorer map(default)?", new Object[0]);
            return 1;
        }
        class_2487 class_24873 = class_24992.method_10602(0);
        if (class_24873 == null) {
            this.error("Couldn't locate the mansion. Are you holding a (highlight)woodland explorer map(default)?", new Object[0]);
            return 1;
        }
        class_243 class_2432 = new class_243(class_24873.method_10574("x"), class_24873.method_10574("y"), class_24873.method_10574("z"));
        class_2585 class_25852 = new class_2585("Mansion located at ");
        class_25852.method_10852((class_2561)ChatUtils.formatCoords(class_2432));
        class_25852.method_27693(".");
        this.info((class_2561)class_25852);
        return 1;
    }

    private int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        class_1799 class_17992 = LocateCommand.mc.field_1724.field_7514.method_7391();
        if (class_17992.method_7909() != class_1802.field_8204) {
            this.error("You need to hold a treasure map first", new Object[0]);
            return 1;
        }
        class_2487 class_24872 = class_17992.method_7969();
        class_2499 class_24992 = (class_2499)class_24872.method_10580("Decorations");
        if (class_24992 == null) {
            this.error("Couldn't locate the cross. Are you holding a (highlight)treasure map(default)?", new Object[0]);
            return 1;
        }
        class_2487 class_24873 = class_24992.method_10602(0);
        if (class_24873 == null) {
            this.error("Couldn't locate the cross. Are you holding a (highlight)treasure map(default)?", new Object[0]);
            return 1;
        }
        class_243 class_2432 = new class_243(class_24873.method_10574("x"), class_24873.method_10574("y"), class_24873.method_10574("z"));
        class_2585 class_25852 = new class_2585("Buried Treasure located at ");
        class_25852.method_10852((class_2561)ChatUtils.formatCoords(class_2432));
        class_25852.method_27693(".");
        this.info((class_2561)class_25852);
        return 1;
    }

    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        class_1799 class_17992 = LocateCommand.mc.field_1724.field_7514.method_7391();
        if (class_17992.method_7909() != class_1802.field_8251) {
            this.error("You need to hold a lodestone compass", new Object[0]);
            return 1;
        }
        class_2487 class_24872 = class_17992.method_7969();
        if (class_24872 == null) {
            this.error("Couldn't get the NBT data. Are you holding a (highlight)lodestone(default) compass?", new Object[0]);
            return 1;
        }
        class_2487 class_24873 = class_24872.method_10562("LodestonePos");
        if (class_24873 == null) {
            this.error("Couldn't get the NBT data. Are you holding a (highlight)lodestone(default) compass?", new Object[0]);
            return 1;
        }
        class_243 class_2432 = new class_243(class_24873.method_10574("X"), class_24873.method_10574("Y"), class_24873.method_10574("Z"));
        class_2585 class_25852 = new class_2585("Lodestone located at ");
        class_25852.method_10852((class_2561)ChatUtils.formatCoords(class_2432));
        class_25852.method_27693(".");
        this.info((class_2561)class_25852);
        return 1;
    }

    private double[] calcIntersection(double[] dArray, double[] dArray2) {
        double d = dArray[3] - dArray[1];
        double d2 = dArray[0] - dArray[2];
        double d3 = d * dArray[0] + d2 * dArray[1];
        double d4 = dArray2[3] - dArray2[1];
        double d5 = dArray2[0] - dArray2[2];
        double d6 = d4 * dArray2[0] + d5 * dArray2[1];
        double d7 = d * d5 - d4 * d2;
        return new double[]{(d5 * d3 - d2 * d6) / d7, (d * d6 - d4 * d3) / d7};
    }

    private void lastPosition(double d, double d2, double d3) {
        this.info("%s Eye of Ender's trajectory saved.", this.firstEnd == null ? "First" : "Second");
        class_243 class_2432 = new class_243(d, d2, d3);
        if (this.firstEnd == null) {
            this.firstEnd = class_2432;
            this.info("Please throw the second Eye Of Ender from a different location.", new Object[0]);
        } else {
            this.secondEnd = class_2432;
            this.findStronghold();
        }
    }

    private int lambda$build$4(CommandContext commandContext) throws CommandSyntaxException {
        class_243 class_2432 = this.findByBlockList(this.netherFortressBlocks);
        if (class_2432 == null) {
            this.error("No nether fortress found.", new Object[0]);
            return 1;
        }
        class_2585 class_25852 = new class_2585("Fortress located at ");
        class_25852.method_10852((class_2561)ChatUtils.formatCoords(class_2432));
        class_25852.method_27693(".");
        this.info((class_2561)class_25852);
        return 1;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(LocateCommand.literal("buried_treasure").executes(this::lambda$build$0));
        literalArgumentBuilder.then(LocateCommand.literal("lodestone").executes(this::lambda$build$1));
        literalArgumentBuilder.then(LocateCommand.literal("mansion").executes(this::lambda$build$2));
        literalArgumentBuilder.then(LocateCommand.literal("stronghold").executes(this::lambda$build$3));
        literalArgumentBuilder.then(LocateCommand.literal("nether_fortress").executes(this::lambda$build$4));
        literalArgumentBuilder.then(LocateCommand.literal("monument").executes(this::lambda$build$5));
        literalArgumentBuilder.then(LocateCommand.literal("cancel").executes(this::lambda$build$6));
    }

    public LocateCommand() {
        super("locate", "Locates structures", "loc");
        this.monumentBlocks = Arrays.asList(class_2246.field_10006, class_2246.field_10174, class_2246.field_10297);
        this.strongholdBlocks = Arrays.asList(class_2246.field_10398);
    }

    private void cancel() {
        this.warning("Locate canceled", new Object[0]);
        MeteorClient.EVENT_BUS.unsubscribe(this);
    }

    private int lambda$build$3(CommandContext commandContext) throws CommandSyntaxException {
        FindItemResult findItemResult = InvUtils.findInHotbar(class_1802.field_8449);
        if (findItemResult.found()) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("follow entity minecraft:eye_of_ender");
            this.firstStart = null;
            this.firstEnd = null;
            this.secondStart = null;
            this.secondEnd = null;
            MeteorClient.EVENT_BUS.subscribe(this);
            this.info("Please throw the first Eye of Ender", new Object[0]);
        } else {
            class_243 class_2432 = this.findByBlockList(this.strongholdBlocks);
            if (class_2432 == null) {
                this.error("No stronghold found nearby. You can use (highlight)Ender Eyes(default) for more success.", new Object[0]);
                return 1;
            }
            class_2585 class_25852 = new class_2585("Stronghold located at ");
            class_25852.method_10852((class_2561)ChatUtils.formatCoords(class_2432));
            class_25852.method_27693(".");
            this.info((class_2561)class_25852);
        }
        return 1;
    }

    private void firstPosition(double d, double d2, double d3) {
        class_243 class_2432 = new class_243(d, d2, d3);
        if (this.firstStart == null) {
            this.firstStart = class_2432;
        } else {
            this.secondStart = class_2432;
        }
    }
}

