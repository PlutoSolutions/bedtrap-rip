/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.bedtrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.utils.bedtrap.Utils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_2199;
import net.minecraft.class_2231;
import net.minecraft.class_2237;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2269;
import net.minecraft.class_2304;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2349;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_2428;
import net.minecraft.class_243;
import net.minecraft.class_2533;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2879;
import net.minecraft.class_310;
import net.minecraft.class_3191;
import net.minecraft.class_3532;
import net.minecraft.class_3726;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class BlockUtils {
    private static final class_310 mc;
    static final boolean $assertionsDisabled;
    private static final ArrayList<class_2338> blocks;
    private static final class_243 hitPos;
    public static final Map<Integer, class_3191> breakingBlocks;

    public static boolean place(class_2338 class_23382, class_1268 class_12682, int n, boolean bl, int n2, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        class_2338 class_23383;
        class_243 class_2432;
        if (n == -1 || !BlockUtils.canPlace(class_23382, bl3)) {
            return false;
        }
        class_2350 class_23502 = BlockUtils.getPlaceSide(class_23382);
        class_243 class_2433 = class_2432 = bl ? new class_243(0.0, 0.0, 0.0) : hitPos;
        if (class_23502 == null) {
            class_23502 = class_2350.field_11036;
            class_23383 = class_23382;
            ((IVec3d)class_2432).set((double)class_23382.method_10263() + 0.5, (double)class_23382.method_10264() + 0.5, (double)class_23382.method_10260() + 0.5);
        } else {
            class_23383 = class_23382.method_10093(class_23502.method_10153());
            ((IVec3d)class_2432).set((double)class_23383.method_10263() + 0.5 + (double)class_23502.method_10148() * 0.5, (double)class_23383.method_10264() + 0.6 + (double)class_23502.method_10164() * 0.5, (double)class_23383.method_10260() + 0.5 + (double)class_23502.method_10165() * 0.5);
        }
        if (bl) {
            class_2350 class_23503 = class_23502;
            Rotations.rotate(Rotations.getYaw(class_2432), Rotations.getPitch(class_2432), n2, () -> BlockUtils.lambda$place$0(n, class_2432, class_12682, class_23503, class_23383, bl2, bl4, bl5));
        } else {
            BlockUtils.place(n, class_2432, class_12682, class_23502, class_23383, bl2, bl4, bl5);
        }
        return true;
    }

    static {
        $assertionsDisabled = !BlockUtils.class.desiredAssertionStatus();
        mc = class_310.method_1551();
        hitPos = new class_243(0.0, 0.0, 0.0);
        blocks = new ArrayList();
        breakingBlocks = new HashMap<Integer, class_3191>();
    }

    @EventHandler
    private void onGameLeft(GameLeftEvent gameLeftEvent) {
        breakingBlocks.clear();
    }

    public static boolean isClickable(class_2248 class_22482) {
        boolean bl = class_22482 instanceof class_2304 || class_22482 instanceof class_2199 || class_22482 instanceof class_2269 || class_22482 instanceof class_2231 || class_22482 instanceof class_2237 || class_22482 instanceof class_2349 || class_22482 instanceof class_2323 || class_22482 instanceof class_2428 || class_22482 instanceof class_2533;
        return bl;
    }

    public static boolean obbyDoubleSurrounded(class_1309 class_13092) {
        class_2338 class_23382 = class_13092.method_24515();
        return BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(-1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 0, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 0, -1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(1, 1, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(-1, 1, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 1, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 1, -1)).method_26204());
    }

    public static boolean isBlastRes(class_2248 class_22482) {
        if (class_22482 == class_2246.field_9987) {
            return true;
        }
        if (class_22482 == class_2246.field_10540) {
            return true;
        }
        if (class_22482 == class_2246.field_10443) {
            return true;
        }
        if (class_22482 == class_2246.field_22109) {
            return true;
        }
        if (class_22482 == class_2246.field_22423) {
            return true;
        }
        if (class_22482 == class_2246.field_10485) {
            return true;
        }
        if (class_22482 == class_2246.field_22108) {
            return true;
        }
        if (class_22482 == class_2246.field_10535) {
            return true;
        }
        if (class_22482 == class_2246.field_10105) {
            return true;
        }
        if (class_22482 == class_2246.field_10414) {
            return true;
        }
        return class_22482 == class_2246.field_23152 && Utils.getDimension() == Dimension.Nether;
    }

    public static boolean place(class_2338 class_23382, class_1268 class_12682, int n, boolean bl, int n2, boolean bl2) {
        return BlockUtils.place(class_23382, class_12682, n, bl, n2, true, bl2, true, true);
    }

    public static boolean canPlace(class_2338 class_23382) {
        return BlockUtils.canPlace(class_23382, true);
    }

    public static class_2350 rayTraceCheck(class_2338 class_23382, boolean bl) {
        class_243 class_2432 = new class_243(BlockUtils.mc.field_1724.method_23317(), BlockUtils.mc.field_1724.method_23318() + (double)BlockUtils.mc.field_1724.method_18381(BlockUtils.mc.field_1724.method_18376()), BlockUtils.mc.field_1724.method_23321());
        for (class_2350 class_23502 : class_2350.values()) {
            class_3959 class_39592 = new class_3959(class_2432, new class_243((double)class_23382.method_10263() + 0.5 + (double)class_23502.method_10163().method_10263() * 0.5, (double)class_23382.method_10264() + 0.5 + (double)class_23502.method_10163().method_10264() * 0.5, (double)class_23382.method_10260() + 0.5 + (double)class_23502.method_10163().method_10260() * 0.5), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)BlockUtils.mc.field_1724);
            class_3965 class_39652 = BlockUtils.mc.field_1687.method_17742(class_39592);
            if (class_39652 == null || class_39652.method_17783() != class_239.class_240.field_1332 || !class_39652.method_17777().equals((Object)class_23382)) continue;
            return class_23502;
        }
        if (bl) {
            if ((double)class_23382.method_10264() > class_2432.field_1351) {
                return class_2350.field_11033;
            }
            return class_2350.field_11036;
        }
        return null;
    }

    public static boolean isSurrounded(class_1309 class_13092) {
        if (!$assertionsDisabled && BlockUtils.mc.field_1687 == null) {
            throw new AssertionError();
        }
        return !BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(1, 0, 0)).method_26215() && !BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(-1, 0, 0)).method_26215() && !BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(0, 0, 1)).method_26215() && !BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(0, 0, -1)).method_26215();
    }

    public static boolean obbySurrounded(class_1309 class_13092) {
        class_2338 class_23382 = class_13092.method_24515();
        return BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(-1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 0, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 0, -1)).method_26204());
    }

    public static boolean isRetard(class_1309 class_13092) {
        class_2338 class_23382 = class_13092.method_24515();
        return BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(-1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 0, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 0, -1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(1, 1, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(-1, 1, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 1, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 1, -1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, -1, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 2, 0)).method_26204());
    }

    public static boolean obbySurroundedWithAir(class_1309 class_13092) {
        class_2338 class_23382 = class_13092.method_24515();
        return BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(-1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 0, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 0, -1)).method_26204()) && (BlockUtils.isAir(BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(1, 1, 0)).method_26204()) || BlockUtils.isAir(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(-1, 1, 0)).method_26204()) || BlockUtils.isAir(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 1, 1)).method_26204()) || BlockUtils.isAir(BlockUtils.mc.field_1687.method_8320(class_23382.method_10069(0, 1, -1)).method_26204()));
    }

    private static class_2350 getPlaceSide(class_2338 class_23382) {
        for (class_2350 class_23502 : class_2350.values()) {
            class_2338 class_23383 = class_23382.method_10093(class_23502);
            class_2350 class_23503 = class_23502.method_10153();
            class_2680 class_26802 = BlockUtils.mc.field_1687.method_8320(class_23383);
            if (class_26802.method_26215() || BlockUtils.isClickable(class_26802.method_26204()) || !class_26802.method_26227().method_15769()) continue;
            return class_23503;
        }
        return null;
    }

    private static void place(int n, class_243 class_2432, class_1268 class_12682, class_2350 class_23502, class_2338 class_23382, boolean bl, boolean bl2, boolean bl3) {
        int n2 = BlockUtils.mc.field_1724.field_7514.field_7545;
        if (bl2) {
            BlockUtils.mc.field_1724.field_7514.field_7545 = n;
        }
        boolean bl4 = BlockUtils.mc.field_1724.field_3913.field_3903;
        BlockUtils.mc.field_1724.field_3913.field_3903 = false;
        BlockUtils.mc.field_1761.method_2896(BlockUtils.mc.field_1724, BlockUtils.mc.field_1687, class_12682, new class_3965(class_2432, class_23502, class_23382, false));
        if (bl) {
            BlockUtils.mc.field_1724.method_6104(class_12682);
        } else {
            mc.method_1562().method_2883((class_2596)new class_2879(class_12682));
        }
        BlockUtils.mc.field_1724.field_3913.field_3903 = bl4;
        if (bl3) {
            BlockUtils.mc.field_1724.field_7514.field_7545 = n2;
        }
    }

    public static double distanceBetween(class_2338 class_23382, class_2338 class_23383) {
        double d = class_23382.method_10263() - class_23383.method_10263();
        double d2 = class_23382.method_10264() - class_23383.method_10264();
        double d3 = class_23382.method_10260() - class_23383.method_10260();
        return class_3532.method_15355((float)((float)(d * d + d2 * d2 + d3 * d3)));
    }

    public static boolean isAir(class_2248 class_22482) {
        return class_22482 == class_2246.field_10124;
    }

    public static boolean canPlace(class_2338 class_23382, boolean bl) {
        if (class_23382 == null) {
            return false;
        }
        if (class_1937.method_25953((class_2338)class_23382)) {
            return false;
        }
        if (!BlockUtils.mc.field_1687.method_8320(class_23382).method_26207().method_15800()) {
            return false;
        }
        return !bl || BlockUtils.mc.field_1687.method_8628(class_2246.field_10340.method_9564(), class_23382, class_3726.method_16194());
    }

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(BlockUtils.class);
    }

    public static boolean isFucked(class_1309 class_13092) {
        if (!$assertionsDisabled && BlockUtils.mc.field_1687 == null) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && BlockUtils.mc.field_1724 == null) {
            throw new AssertionError();
        }
        int n = 0;
        int n2 = 0;
        if (BlockUtils.isBurrowed(class_13092)) {
            return false;
        }
        if (BlockUtils.isBurrowed((class_1309)BlockUtils.mc.field_1724) && class_13092.method_24515().method_10263() == BlockUtils.mc.field_1724.method_24515().method_10263() && class_13092.method_24515().method_10260() == BlockUtils.mc.field_1724.method_24515().method_10260() && class_13092.method_24515().method_10264() - BlockUtils.mc.field_1724.method_24515().method_10264() <= 2) {
            return true;
        }
        if (!BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(0, 2, 0)).method_26215()) {
            return true;
        }
        if (!BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(1, 0, 0)).method_26215()) {
            ++n;
        }
        if (!BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(-1, 0, 0)).method_26215()) {
            ++n;
        }
        if (!BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(0, 0, 1)).method_26215()) {
            ++n;
        }
        if (!BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(0, 0, -1)).method_26215()) {
            ++n;
        }
        if (n == 3) {
            return true;
        }
        if (!BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(1, 1, 0)).method_26215()) {
            ++n2;
        }
        if (!BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(-1, 1, 0)).method_26215()) {
            ++n2;
        }
        if (!BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(0, 1, 1)).method_26215()) {
            ++n2;
        }
        if (!BlockUtils.mc.field_1687.method_8320(class_13092.method_24515().method_10069(0, 1, -1)).method_26215()) {
            ++n2;
        }
        return n < 4 && n2 == 4;
    }

    public static boolean isBurrowed(class_1309 class_13092) {
        return !BlockUtils.mc.field_1687.method_8320(class_13092.method_24515()).method_26215();
    }

    private static void lambda$place$0(int n, class_243 class_2432, class_1268 class_12682, class_2350 class_23502, class_2338 class_23382, boolean bl, boolean bl2, boolean bl3) {
        BlockUtils.place(n, class_2432, class_12682, class_23502, class_23382, bl, bl2, bl3);
    }

    public static List<class_2338> getSphere(class_2338 class_23382, int n, int n2) {
        blocks.clear();
        for (int i = class_23382.method_10263() - n; i < class_23382.method_10263() + n; ++i) {
            for (int j = class_23382.method_10264() - n2; j < class_23382.method_10264() + n2; ++j) {
                for (int k = class_23382.method_10260() - n; k < class_23382.method_10260() + n; ++k) {
                    class_2338 class_23383 = new class_2338(i, j, k);
                    if (!(BlockUtils.distanceBetween(class_23382, class_23383) <= (double)n) || blocks.contains(class_23383)) continue;
                    blocks.add(class_23383);
                    if (null == null) continue;
                    return null;
                }
            }
            if (2 > 1) continue;
            return null;
        }
        return blocks;
    }
}

