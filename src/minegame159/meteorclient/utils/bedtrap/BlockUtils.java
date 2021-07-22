/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1937
 *  net.minecraft.class_2199
 *  net.minecraft.class_2231
 *  net.minecraft.class_2237
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2269
 *  net.minecraft.class_2304
 *  net.minecraft.class_2323
 *  net.minecraft.class_2338
 *  net.minecraft.class_2349
 *  net.minecraft.class_2350
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_2428
 *  net.minecraft.class_243
 *  net.minecraft.class_2533
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2879
 *  net.minecraft.class_310
 *  net.minecraft.class_3191
 *  net.minecraft.class_3532
 *  net.minecraft.class_3726
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
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
    private static final /* synthetic */ class_310 mc;
    private static final /* synthetic */ ArrayList<class_2338> blocks;
    private static final /* synthetic */ class_243 hitPos;
    public static final /* synthetic */ Map<Integer, class_3191> breakingBlocks;

    public static boolean place(class_2338 llllllllllllllllIlIlIlIlllIlllII, class_1268 llllllllllllllllIlIlIlIlllIIllll, int llllllllllllllllIlIlIlIlllIllIlI, boolean llllllllllllllllIlIlIlIlllIllIIl, int llllllllllllllllIlIlIlIlllIllIII, boolean llllllllllllllllIlIlIlIlllIlIlll, boolean llllllllllllllllIlIlIlIlllIIlIlI, boolean llllllllllllllllIlIlIlIlllIIlIIl, boolean llllllllllllllllIlIlIlIlllIIlIII) {
        class_2338 llllllllllllllllIlIlIlIlllIlIIlI;
        class_243 llllllllllllllllIlIlIlIlllIlIIIl;
        if (llllllllllllllllIlIlIlIlllIllIlI == -1 || !BlockUtils.canPlace(llllllllllllllllIlIlIlIlllIlllII, llllllllllllllllIlIlIlIlllIIlIlI)) {
            return false;
        }
        class_2350 llllllllllllllllIlIlIlIlllIlIIll = BlockUtils.getPlaceSide(llllllllllllllllIlIlIlIlllIlllII);
        class_243 class_2432 = llllllllllllllllIlIlIlIlllIlIIIl = llllllllllllllllIlIlIlIlllIllIIl ? new class_243(0.0, 0.0, 0.0) : hitPos;
        if (llllllllllllllllIlIlIlIlllIlIIll == null) {
            llllllllllllllllIlIlIlIlllIlIIll = class_2350.field_11036;
            class_2338 llllllllllllllllIlIlIlIlllIllllI = llllllllllllllllIlIlIlIlllIlllII;
            ((IVec3d)llllllllllllllllIlIlIlIlllIlIIIl).set((double)llllllllllllllllIlIlIlIlllIlllII.method_10263() + 0.5, (double)llllllllllllllllIlIlIlIlllIlllII.method_10264() + 0.5, (double)llllllllllllllllIlIlIlIlllIlllII.method_10260() + 0.5);
        } else {
            llllllllllllllllIlIlIlIlllIlIIlI = llllllllllllllllIlIlIlIlllIlllII.method_10093(llllllllllllllllIlIlIlIlllIlIIll.method_10153());
            ((IVec3d)llllllllllllllllIlIlIlIlllIlIIIl).set((double)llllllllllllllllIlIlIlIlllIlIIlI.method_10263() + 0.5 + (double)llllllllllllllllIlIlIlIlllIlIIll.method_10148() * 0.5, (double)llllllllllllllllIlIlIlIlllIlIIlI.method_10264() + 0.6 + (double)llllllllllllllllIlIlIlIlllIlIIll.method_10164() * 0.5, (double)llllllllllllllllIlIlIlIlllIlIIlI.method_10260() + 0.5 + (double)llllllllllllllllIlIlIlIlllIlIIll.method_10165() * 0.5);
        }
        if (llllllllllllllllIlIlIlIlllIllIIl) {
            class_2350 llllllllllllllllIlIlIlIlllIlllIl = llllllllllllllllIlIlIlIlllIlIIll;
            Rotations.rotate(Rotations.getYaw(llllllllllllllllIlIlIlIlllIlIIIl), Rotations.getPitch(llllllllllllllllIlIlIlIlllIlIIIl), llllllllllllllllIlIlIlIlllIllIII, () -> BlockUtils.place(llllllllllllllllIlIlIlIlllIllIlI, llllllllllllllllIlIlIlIlllIlIIIl, llllllllllllllllIlIlIlIlllIIllll, llllllllllllllllIlIlIlIlllIlllIl, llllllllllllllllIlIlIlIlllIlIIlI, llllllllllllllllIlIlIlIlllIlIlll, llllllllllllllllIlIlIlIlllIIlIIl, llllllllllllllllIlIlIlIlllIIlIII));
        } else {
            BlockUtils.place(llllllllllllllllIlIlIlIlllIllIlI, llllllllllllllllIlIlIlIlllIlIIIl, llllllllllllllllIlIlIlIlllIIllll, llllllllllllllllIlIlIlIlllIlIIll, llllllllllllllllIlIlIlIlllIlIIlI, llllllllllllllllIlIlIlIlllIlIlll, llllllllllllllllIlIlIlIlllIIlIIl, llllllllllllllllIlIlIlIlllIIlIII);
        }
        return true;
    }

    static {
        mc = class_310.method_1551();
        hitPos = new class_243(0.0, 0.0, 0.0);
        blocks = new ArrayList();
        breakingBlocks = new HashMap<Integer, class_3191>();
    }

    @EventHandler
    private void onGameLeft(GameLeftEvent llllllllllllllllIlIlIlIllllIllII) {
        breakingBlocks.clear();
    }

    public static boolean isClickable(class_2248 llllllllllllllllIlIlIlIllIIIIllI) {
        boolean llllllllllllllllIlIlIlIllIIIIlll = llllllllllllllllIlIlIlIllIIIIllI instanceof class_2304 || llllllllllllllllIlIlIlIllIIIIllI instanceof class_2199 || llllllllllllllllIlIlIlIllIIIIllI instanceof class_2269 || llllllllllllllllIlIlIlIllIIIIllI instanceof class_2231 || llllllllllllllllIlIlIlIllIIIIllI instanceof class_2237 || llllllllllllllllIlIlIlIllIIIIllI instanceof class_2349 || llllllllllllllllIlIlIlIllIIIIllI instanceof class_2323 || llllllllllllllllIlIlIlIllIIIIllI instanceof class_2428 || llllllllllllllllIlIlIlIllIIIIllI instanceof class_2533;
        return llllllllllllllllIlIlIlIllIIIIlll;
    }

    public static boolean obbyDoubleSurrounded(class_1309 llllllllllllllllIlIlIlIlIIIlIlII) {
        class_2338 llllllllllllllllIlIlIlIlIIIlIlIl = llllllllllllllllIlIlIlIlIIIlIlII.method_24515();
        return BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIlIlII.method_24515().method_10069(1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIlIlIl.method_10069(-1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIlIlIl.method_10069(0, 0, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIlIlIl.method_10069(0, 0, -1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIlIlII.method_24515().method_10069(1, 1, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIlIlIl.method_10069(-1, 1, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIlIlIl.method_10069(0, 1, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIlIlIl.method_10069(0, 1, -1)).method_26204());
    }

    public static boolean isBlastRes(class_2248 llllllllllllllllIlIlIlIlIIIIIlll) {
        if (llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_9987) {
            return true;
        }
        if (llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_10540) {
            return true;
        }
        if (llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_10443) {
            return true;
        }
        if (llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_22109) {
            return true;
        }
        if (llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_22423) {
            return true;
        }
        if (llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_10485) {
            return true;
        }
        if (llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_22108) {
            return true;
        }
        if (llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_10535) {
            return true;
        }
        if (llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_10105) {
            return true;
        }
        if (llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_10414) {
            return true;
        }
        return llllllllllllllllIlIlIlIlIIIIIlll == class_2246.field_23152 && Utils.getDimension() == Dimension.Nether;
    }

    public static boolean place(class_2338 llllllllllllllllIlIlIlIllIllllIl, class_1268 llllllllllllllllIlIlIlIllIllllII, int llllllllllllllllIlIlIlIllIlllIll, boolean llllllllllllllllIlIlIlIllIllIlII, int llllllllllllllllIlIlIlIllIllIIll, boolean llllllllllllllllIlIlIlIllIllIIlI) {
        return BlockUtils.place(llllllllllllllllIlIlIlIllIllllIl, llllllllllllllllIlIlIlIllIllllII, llllllllllllllllIlIlIlIllIlllIll, llllllllllllllllIlIlIlIllIllIlII, llllllllllllllllIlIlIlIllIllIIll, true, llllllllllllllllIlIlIlIllIllIIlI, true, true);
    }

    public static boolean canPlace(class_2338 llllllllllllllllIlIlIlIllIIIllII) {
        return BlockUtils.canPlace(llllllllllllllllIlIlIlIllIIIllII, true);
    }

    public static class_2350 rayTraceCheck(class_2338 llllllllllllllllIlIlIlIlIIlllllI, boolean llllllllllllllllIlIlIlIlIIlllIII) {
        class_243 llllllllllllllllIlIlIlIlIIllllII = new class_243(BlockUtils.mc.field_1724.method_23317(), BlockUtils.mc.field_1724.method_23318() + (double)BlockUtils.mc.field_1724.method_18381(BlockUtils.mc.field_1724.method_18376()), BlockUtils.mc.field_1724.method_23321());
        for (class_2350 llllllllllllllllIlIlIlIlIlIIIIlI : class_2350.values()) {
            class_3959 llllllllllllllllIlIlIlIlIlIIIIIl = new class_3959(llllllllllllllllIlIlIlIlIIllllII, new class_243((double)llllllllllllllllIlIlIlIlIIlllllI.method_10263() + 0.5 + (double)llllllllllllllllIlIlIlIlIlIIIIlI.method_10163().method_10263() * 0.5, (double)llllllllllllllllIlIlIlIlIIlllllI.method_10264() + 0.5 + (double)llllllllllllllllIlIlIlIlIlIIIIlI.method_10163().method_10264() * 0.5, (double)llllllllllllllllIlIlIlIlIIlllllI.method_10260() + 0.5 + (double)llllllllllllllllIlIlIlIlIlIIIIlI.method_10163().method_10260() * 0.5), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)BlockUtils.mc.field_1724);
            class_3965 llllllllllllllllIlIlIlIlIlIIIIII = BlockUtils.mc.field_1687.method_17742(llllllllllllllllIlIlIlIlIlIIIIIl);
            if (llllllllllllllllIlIlIlIlIlIIIIII == null || llllllllllllllllIlIlIlIlIlIIIIII.method_17783() != class_239.class_240.field_1332 || !llllllllllllllllIlIlIlIlIlIIIIII.method_17777().equals((Object)llllllllllllllllIlIlIlIlIIlllllI)) continue;
            return llllllllllllllllIlIlIlIlIlIIIIlI;
        }
        if (llllllllllllllllIlIlIlIlIIlllIII) {
            if ((double)llllllllllllllllIlIlIlIlIIlllllI.method_10264() > llllllllllllllllIlIlIlIlIIllllII.field_1351) {
                return class_2350.field_11033;
            }
            return class_2350.field_11036;
        }
        return null;
    }

    public static boolean isSurrounded(class_1309 llllllllllllllllIlIlIlIlIIIIIlII) {
        assert (BlockUtils.mc.field_1687 != null);
        return !BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIIlII.method_24515().method_10069(1, 0, 0)).method_26215() && !BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIIlII.method_24515().method_10069(-1, 0, 0)).method_26215() && !BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIIlII.method_24515().method_10069(0, 0, 1)).method_26215() && !BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIIlII.method_24515().method_10069(0, 0, -1)).method_26215();
    }

    public BlockUtils() {
        BlockUtils llllllllllllllllIlIlIlIllllIllll;
    }

    public static boolean obbySurrounded(class_1309 llllllllllllllllIlIlIlIlIIlIIIII) {
        class_2338 llllllllllllllllIlIlIlIlIIlIIIIl = llllllllllllllllIlIlIlIlIIlIIIII.method_24515();
        return BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIIIII.method_24515().method_10069(1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIIIIl.method_10069(-1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIIIIl.method_10069(0, 0, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIIIIl.method_10069(0, 0, -1)).method_26204());
    }

    public static boolean isRetard(class_1309 llllllllllllllllIlIlIlIlIIIIlllI) {
        class_2338 llllllllllllllllIlIlIlIlIIIIllll = llllllllllllllllIlIlIlIlIIIIlllI.method_24515();
        return BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIlllI.method_24515().method_10069(1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIllll.method_10069(-1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIllll.method_10069(0, 0, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIllll.method_10069(0, 0, -1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIlllI.method_24515().method_10069(1, 1, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIllll.method_10069(-1, 1, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIllll.method_10069(0, 1, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIllll.method_10069(0, 1, -1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIllll.method_10069(0, -1, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIIllll.method_10069(0, 2, 0)).method_26204());
    }

    public static boolean obbySurroundedWithAir(class_1309 llllllllllllllllIlIlIlIlIIIllIlI) {
        class_2338 llllllllllllllllIlIlIlIlIIIllIll = llllllllllllllllIlIlIlIlIIIllIlI.method_24515();
        return BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIllIlI.method_24515().method_10069(1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIllIll.method_10069(-1, 0, 0)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIllIll.method_10069(0, 0, 1)).method_26204()) && BlockUtils.isBlastRes(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIllIll.method_10069(0, 0, -1)).method_26204()) && (BlockUtils.isAir(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIllIlI.method_24515().method_10069(1, 1, 0)).method_26204()) || BlockUtils.isAir(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIllIll.method_10069(-1, 1, 0)).method_26204()) || BlockUtils.isAir(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIllIll.method_10069(0, 1, 1)).method_26204()) || BlockUtils.isAir(BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIIllIll.method_10069(0, 1, -1)).method_26204()));
    }

    private static class_2350 getPlaceSide(class_2338 llllllllllllllllIlIlIlIlIllllIII) {
        for (class_2350 llllllllllllllllIlIlIlIlIllllIIl : class_2350.values()) {
            class_2338 llllllllllllllllIlIlIlIlIlllllII = llllllllllllllllIlIlIlIlIllllIII.method_10093(llllllllllllllllIlIlIlIlIllllIIl);
            class_2350 llllllllllllllllIlIlIlIlIllllIll = llllllllllllllllIlIlIlIlIllllIIl.method_10153();
            class_2680 llllllllllllllllIlIlIlIlIllllIlI = BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIlllllII);
            if (llllllllllllllllIlIlIlIlIllllIlI.method_26215() || BlockUtils.isClickable(llllllllllllllllIlIlIlIlIllllIlI.method_26204()) || !llllllllllllllllIlIlIlIlIllllIlI.method_26227().method_15769()) continue;
            return llllllllllllllllIlIlIlIlIllllIll;
        }
        return null;
    }

    private static void place(int llllllllllllllllIlIlIlIllIIlllIl, class_243 llllllllllllllllIlIlIlIllIIlllII, class_1268 llllllllllllllllIlIlIlIllIlIIlIl, class_2350 llllllllllllllllIlIlIlIllIIllIlI, class_2338 llllllllllllllllIlIlIlIllIlIIIll, boolean llllllllllllllllIlIlIlIllIIllIII, boolean llllllllllllllllIlIlIlIllIIlIlll, boolean llllllllllllllllIlIlIlIllIlIIIII) {
        int llllllllllllllllIlIlIlIllIIlllll = BlockUtils.mc.field_1724.field_7514.field_7545;
        if (llllllllllllllllIlIlIlIllIIlIlll) {
            BlockUtils.mc.field_1724.field_7514.field_7545 = llllllllllllllllIlIlIlIllIIlllIl;
        }
        boolean llllllllllllllllIlIlIlIllIIllllI = BlockUtils.mc.field_1724.field_3913.field_3903;
        BlockUtils.mc.field_1724.field_3913.field_3903 = false;
        BlockUtils.mc.field_1761.method_2896(BlockUtils.mc.field_1724, BlockUtils.mc.field_1687, llllllllllllllllIlIlIlIllIlIIlIl, new class_3965(llllllllllllllllIlIlIlIllIIlllII, llllllllllllllllIlIlIlIllIIllIlI, llllllllllllllllIlIlIlIllIlIIIll, false));
        if (llllllllllllllllIlIlIlIllIIllIII) {
            BlockUtils.mc.field_1724.method_6104(llllllllllllllllIlIlIlIllIlIIlIl);
        } else {
            mc.method_1562().method_2883((class_2596)new class_2879(llllllllllllllllIlIlIlIllIlIIlIl));
        }
        BlockUtils.mc.field_1724.field_3913.field_3903 = llllllllllllllllIlIlIlIllIIllllI;
        if (llllllllllllllllIlIlIlIllIlIIIII) {
            BlockUtils.mc.field_1724.field_7514.field_7545 = llllllllllllllllIlIlIlIllIIlllll;
        }
    }

    public static double distanceBetween(class_2338 llllllllllllllllIlIlIlIlIlIlIlIl, class_2338 llllllllllllllllIlIlIlIlIlIIllll) {
        double llllllllllllllllIlIlIlIlIlIlIIll = llllllllllllllllIlIlIlIlIlIlIlIl.method_10263() - llllllllllllllllIlIlIlIlIlIIllll.method_10263();
        double llllllllllllllllIlIlIlIlIlIlIIlI = llllllllllllllllIlIlIlIlIlIlIlIl.method_10264() - llllllllllllllllIlIlIlIlIlIIllll.method_10264();
        double llllllllllllllllIlIlIlIlIlIlIIIl = llllllllllllllllIlIlIlIlIlIlIlIl.method_10260() - llllllllllllllllIlIlIlIlIlIIllll.method_10260();
        return class_3532.method_15355((float)((float)(llllllllllllllllIlIlIlIlIlIlIIll * llllllllllllllllIlIlIlIlIlIlIIll + llllllllllllllllIlIlIlIlIlIlIIlI * llllllllllllllllIlIlIlIlIlIlIIlI + llllllllllllllllIlIlIlIlIlIlIIIl * llllllllllllllllIlIlIlIlIlIlIIIl)));
    }

    public static boolean isAir(class_2248 llllllllllllllllIlIlIlIlIIIIlIll) {
        return llllllllllllllllIlIlIlIlIIIIlIll == class_2246.field_10124;
    }

    public static boolean canPlace(class_2338 llllllllllllllllIlIlIlIllIIIllll, boolean llllllllllllllllIlIlIlIllIIlIIII) {
        if (llllllllllllllllIlIlIlIllIIIllll == null) {
            return false;
        }
        if (class_1937.method_25953((class_2338)llllllllllllllllIlIlIlIllIIIllll)) {
            return false;
        }
        if (!BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIllIIIllll).method_26207().method_15800()) {
            return false;
        }
        return !llllllllllllllllIlIlIlIllIIlIIII || BlockUtils.mc.field_1687.method_8628(class_2246.field_10340.method_9564(), llllllllllllllllIlIlIlIllIIIllll, class_3726.method_16194());
    }

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(BlockUtils.class);
    }

    public static boolean isFucked(class_1309 llllllllllllllllIlIlIlIlIIlIllIl) {
        assert (BlockUtils.mc.field_1687 != null);
        assert (BlockUtils.mc.field_1724 != null);
        int llllllllllllllllIlIlIlIlIIlIllII = 0;
        int llllllllllllllllIlIlIlIlIIlIlIll = 0;
        if (BlockUtils.isBurrowed(llllllllllllllllIlIlIlIlIIlIllIl)) {
            return false;
        }
        if (BlockUtils.isBurrowed((class_1309)BlockUtils.mc.field_1724) && llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10263() == BlockUtils.mc.field_1724.method_24515().method_10263() && llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10260() == BlockUtils.mc.field_1724.method_24515().method_10260() && llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10264() - BlockUtils.mc.field_1724.method_24515().method_10264() <= 2) {
            return true;
        }
        if (!BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10069(0, 2, 0)).method_26215()) {
            return true;
        }
        if (!BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10069(1, 0, 0)).method_26215()) {
            ++llllllllllllllllIlIlIlIlIIlIllII;
        }
        if (!BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10069(-1, 0, 0)).method_26215()) {
            ++llllllllllllllllIlIlIlIlIIlIllII;
        }
        if (!BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10069(0, 0, 1)).method_26215()) {
            ++llllllllllllllllIlIlIlIlIIlIllII;
        }
        if (!BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10069(0, 0, -1)).method_26215()) {
            ++llllllllllllllllIlIlIlIlIIlIllII;
        }
        if (llllllllllllllllIlIlIlIlIIlIllII == 3) {
            return true;
        }
        if (!BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10069(1, 1, 0)).method_26215()) {
            ++llllllllllllllllIlIlIlIlIIlIlIll;
        }
        if (!BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10069(-1, 1, 0)).method_26215()) {
            ++llllllllllllllllIlIlIlIlIIlIlIll;
        }
        if (!BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10069(0, 1, 1)).method_26215()) {
            ++llllllllllllllllIlIlIlIlIIlIlIll;
        }
        if (!BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIllIl.method_24515().method_10069(0, 1, -1)).method_26215()) {
            ++llllllllllllllllIlIlIlIlIIlIlIll;
        }
        return llllllllllllllllIlIlIlIlIIlIllII < 4 && llllllllllllllllIlIlIlIlIIlIlIll == 4;
    }

    public static boolean isBurrowed(class_1309 llllllllllllllllIlIlIlIlIIlIIllI) {
        return !BlockUtils.mc.field_1687.method_8320(llllllllllllllllIlIlIlIlIIlIIllI.method_24515()).method_26215();
    }

    public static List<class_2338> getSphere(class_2338 llllllllllllllllIlIlIlIlIllIIlII, int llllllllllllllllIlIlIlIlIllIIIll, int llllllllllllllllIlIlIlIlIlIlllll) {
        blocks.clear();
        for (int llllllllllllllllIlIlIlIlIllIIlIl = llllllllllllllllIlIlIlIlIllIIlII.method_10263() - llllllllllllllllIlIlIlIlIllIIIll; llllllllllllllllIlIlIlIlIllIIlIl < llllllllllllllllIlIlIlIlIllIIlII.method_10263() + llllllllllllllllIlIlIlIlIllIIIll; ++llllllllllllllllIlIlIlIlIllIIlIl) {
            for (int llllllllllllllllIlIlIlIlIllIIllI = llllllllllllllllIlIlIlIlIllIIlII.method_10264() - llllllllllllllllIlIlIlIlIlIlllll; llllllllllllllllIlIlIlIlIllIIllI < llllllllllllllllIlIlIlIlIllIIlII.method_10264() + llllllllllllllllIlIlIlIlIlIlllll; ++llllllllllllllllIlIlIlIlIllIIllI) {
                for (int llllllllllllllllIlIlIlIlIllIIlll = llllllllllllllllIlIlIlIlIllIIlII.method_10260() - llllllllllllllllIlIlIlIlIllIIIll; llllllllllllllllIlIlIlIlIllIIlll < llllllllllllllllIlIlIlIlIllIIlII.method_10260() + llllllllllllllllIlIlIlIlIllIIIll; ++llllllllllllllllIlIlIlIlIllIIlll) {
                    class_2338 llllllllllllllllIlIlIlIlIllIlIII = new class_2338(llllllllllllllllIlIlIlIlIllIIlIl, llllllllllllllllIlIlIlIlIllIIllI, llllllllllllllllIlIlIlIlIllIIlll);
                    if (!(BlockUtils.distanceBetween(llllllllllllllllIlIlIlIlIllIIlII, llllllllllllllllIlIlIlIlIllIlIII) <= (double)llllllllllllllllIlIlIlIlIllIIIll) || blocks.contains((Object)llllllllllllllllIlIlIlIlIllIlIII)) continue;
                    blocks.add(llllllllllllllllIlIlIlIlIllIlIII);
                }
            }
        }
        return blocks;
    }
}

