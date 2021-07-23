/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.world;

import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1937;
import net.minecraft.class_2199;
import net.minecraft.class_2231;
import net.minecraft.class_2237;
import net.minecraft.class_2244;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2269;
import net.minecraft.class_2304;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2349;
import net.minecraft.class_2350;
import net.minecraft.class_2428;
import net.minecraft.class_243;
import net.minecraft.class_2533;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2879;
import net.minecraft.class_3726;
import net.minecraft.class_3965;

public class BlockUtils {
    private static final class_243 hitPos = new class_243(0.0, 0.0, 0.0);

    public static boolean place(class_2338 class_23382, FindItemResult findItemResult, boolean bl, int n) {
        return BlockUtils.place(class_23382, findItemResult, bl, n, true);
    }

    public static boolean place(class_2338 class_23382, FindItemResult findItemResult, int n) {
        return BlockUtils.place(class_23382, findItemResult, n, true);
    }

    private static class_2350 getPlaceSide(class_2338 class_23382) {
        for (class_2350 class_23502 : class_2350.values()) {
            class_2338 class_23383 = class_23382.method_10093(class_23502);
            class_2350 class_23503 = class_23502.method_10153();
            class_2680 class_26802 = Utils.mc.field_1687.method_8320(class_23383);
            if (class_26802.method_26215() || BlockUtils.isClickable(class_26802.method_26204()) || !class_26802.method_26227().method_15769()) continue;
            return class_23503;
        }
        return null;
    }

    public static boolean place(class_2338 class_23382, class_1268 class_12682, int n, boolean bl, int n2, boolean bl2, boolean bl3, boolean bl4) {
        class_2338 class_23383;
        if (n < 0 || n > 8) {
            return false;
        }
        if (!BlockUtils.canPlace(class_23382, bl3)) {
            return false;
        }
        ((IVec3d)hitPos).set((double)class_23382.method_10263() + 0.5, (double)class_23382.method_10264() + 0.5, (double)class_23382.method_10260() + 0.5);
        class_2350 class_23502 = BlockUtils.getPlaceSide(class_23382);
        if (class_23502 == null) {
            class_23502 = class_2350.field_11036;
            class_23383 = class_23382;
        } else {
            class_23383 = class_23382.method_10093(class_23502.method_10153());
            hitPos.method_1031((double)class_23502.method_10148() * 0.5, (double)class_23502.method_10164() * 0.5, (double)class_23502.method_10165() * 0.5);
        }
        class_2350 class_23503 = class_23502;
        if (bl) {
            Rotations.rotate(Rotations.getYaw(hitPos), Rotations.getPitch(hitPos), n2, () -> BlockUtils.lambda$place$0(n, class_23503, class_23383, class_12682, bl2, bl4));
        } else {
            int n3 = Utils.mc.field_1724.field_7514.field_7545;
            InvUtils.swap(n);
            BlockUtils.place(new class_3965(hitPos, class_23503, class_23383, false), class_12682, bl2);
            if (bl4) {
                InvUtils.swap(n3);
            }
        }
        return true;
    }

    public static boolean place(class_2338 class_23382, FindItemResult findItemResult, boolean bl, int n, boolean bl2, boolean bl3) {
        return BlockUtils.place(class_23382, findItemResult, bl, n, bl2, bl3, true);
    }

    public static boolean canPlace(class_2338 class_23382) {
        return BlockUtils.canPlace(class_23382, true);
    }

    private static void lambda$place$0(int n, class_2350 class_23502, class_2338 class_23382, class_1268 class_12682, boolean bl, boolean bl2) {
        int n2 = Utils.mc.field_1724.field_7514.field_7545;
        InvUtils.swap(n);
        BlockUtils.place(new class_3965(hitPos, class_23502, class_23382, false), class_12682, bl);
        if (bl2) {
            InvUtils.swap(n2);
        }
    }

    private static void place(class_3965 class_39652, class_1268 class_12682, boolean bl) {
        boolean bl2 = Utils.mc.field_1724.field_3913.field_3903;
        Utils.mc.field_1724.field_3913.field_3903 = false;
        class_1269 class_12692 = Utils.mc.field_1761.method_2896(Utils.mc.field_1724, Utils.mc.field_1687, class_12682, class_39652);
        if (class_12692.method_23666()) {
            if (bl) {
                Utils.mc.field_1724.method_6104(class_12682);
            } else {
                Utils.mc.method_1562().method_2883((class_2596)new class_2879(class_12682));
            }
        }
        Utils.mc.field_1724.field_3913.field_3903 = bl2;
    }

    public static boolean place(class_2338 class_23382, FindItemResult findItemResult, int n, boolean bl) {
        return BlockUtils.place(class_23382, findItemResult, true, n, true, bl);
    }

    public static boolean place(class_2338 class_23382, FindItemResult findItemResult, boolean bl, int n, boolean bl2) {
        return BlockUtils.place(class_23382, findItemResult, bl, n, true, bl2);
    }

    public static boolean canPlace(class_2338 class_23382, boolean bl) {
        if (class_23382 == null) {
            return false;
        }
        if (class_1937.method_8518((class_2338)class_23382)) {
            return false;
        }
        if (!Utils.mc.field_1687.method_8320(class_23382).method_26207().method_15800()) {
            return false;
        }
        return !bl || Utils.mc.field_1687.method_8628(class_2246.field_10340.method_9564(), class_23382, class_3726.method_16194());
    }

    public static boolean place(class_2338 class_23382, FindItemResult findItemResult, boolean bl, int n, boolean bl2, boolean bl3, boolean bl4) {
        if (findItemResult.isOffhand()) {
            return BlockUtils.place(class_23382, class_1268.field_5810, Utils.mc.field_1724.field_7514.field_7545, bl, n, bl2, bl3, bl4);
        }
        if (findItemResult.isHotbar()) {
            return BlockUtils.place(class_23382, class_1268.field_5808, findItemResult.getSlot(), bl, n, bl2, bl3, bl4);
        }
        return false;
    }

    public static boolean isClickable(class_2248 class_22482) {
        return class_22482 instanceof class_2304 || class_22482 instanceof class_2199 || class_22482 instanceof class_2269 || class_22482 instanceof class_2231 || class_22482 instanceof class_2237 || class_22482 instanceof class_2244 || class_22482 instanceof class_2349 || class_22482 instanceof class_2323 || class_22482 instanceof class_2428 || class_22482 instanceof class_2533;
    }
}

