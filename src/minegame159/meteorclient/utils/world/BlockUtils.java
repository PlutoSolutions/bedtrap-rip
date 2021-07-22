/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1937
 *  net.minecraft.class_2199
 *  net.minecraft.class_2231
 *  net.minecraft.class_2237
 *  net.minecraft.class_2244
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2269
 *  net.minecraft.class_2304
 *  net.minecraft.class_2323
 *  net.minecraft.class_2338
 *  net.minecraft.class_2349
 *  net.minecraft.class_2350
 *  net.minecraft.class_2428
 *  net.minecraft.class_243
 *  net.minecraft.class_2533
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2879
 *  net.minecraft.class_3726
 *  net.minecraft.class_3965
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
    private static final /* synthetic */ class_243 hitPos;

    public static boolean place(class_2338 llllllllllllllllllIIIlllllIIlllI, FindItemResult llllllllllllllllllIIIlllllIIllIl, boolean llllllllllllllllllIIIlllllIIllII, int llllllllllllllllllIIIlllllIIlIll) {
        return BlockUtils.place(llllllllllllllllllIIIlllllIIlllI, llllllllllllllllllIIIlllllIIllIl, llllllllllllllllllIIIlllllIIllII, llllllllllllllllllIIIlllllIIlIll, true);
    }

    public static boolean place(class_2338 llllllllllllllllllIIIlllllIllIII, FindItemResult llllllllllllllllllIIIlllllIlIlII, int llllllllllllllllllIIIlllllIlIIll) {
        return BlockUtils.place(llllllllllllllllllIIIlllllIllIII, llllllllllllllllllIIIlllllIlIlII, llllllllllllllllllIIIlllllIlIIll, true);
    }

    private static class_2350 getPlaceSide(class_2338 llllllllllllllllllIIIlllIIlllIll) {
        for (class_2350 llllllllllllllllllIIIlllIIllllII : class_2350.values()) {
            class_2338 llllllllllllllllllIIIlllIIllllll = llllllllllllllllllIIIlllIIlllIll.method_10093(llllllllllllllllllIIIlllIIllllII);
            class_2350 llllllllllllllllllIIIlllIIlllllI = llllllllllllllllllIIIlllIIllllII.method_10153();
            class_2680 llllllllllllllllllIIIlllIIllllIl = Utils.mc.field_1687.method_8320(llllllllllllllllllIIIlllIIllllll);
            if (llllllllllllllllllIIIlllIIllllIl.method_26215() || BlockUtils.isClickable(llllllllllllllllllIIIlllIIllllIl.method_26204()) || !llllllllllllllllllIIIlllIIllllIl.method_26227().method_15769()) continue;
            return llllllllllllllllllIIIlllIIlllllI;
        }
        return null;
    }

    public static boolean place(class_2338 llllllllllllllllllIIIlllIlllIllI, class_1268 llllllllllllllllllIIIlllIlllIlIl, int llllllllllllllllllIIIlllIlllIlII, boolean llllllllllllllllllIIIlllIllIlIII, int llllllllllllllllllIIIlllIllIIlll, boolean llllllllllllllllllIIIlllIlllIIIl, boolean llllllllllllllllllIIIlllIlllIIII, boolean llllllllllllllllllIIIlllIllIllll) {
        class_2338 llllllllllllllllllIIIlllIllIlllI;
        if (llllllllllllllllllIIIlllIlllIlII < 0 || llllllllllllllllllIIIlllIlllIlII > 8) {
            return false;
        }
        if (!BlockUtils.canPlace(llllllllllllllllllIIIlllIlllIllI, llllllllllllllllllIIIlllIlllIIII)) {
            return false;
        }
        ((IVec3d)hitPos).set((double)llllllllllllllllllIIIlllIlllIllI.method_10263() + 0.5, (double)llllllllllllllllllIIIlllIlllIllI.method_10264() + 0.5, (double)llllllllllllllllllIIIlllIlllIllI.method_10260() + 0.5);
        class_2350 llllllllllllllllllIIIlllIllIllIl = BlockUtils.getPlaceSide(llllllllllllllllllIIIlllIlllIllI);
        if (llllllllllllllllllIIIlllIllIllIl == null) {
            llllllllllllllllllIIIlllIllIllIl = class_2350.field_11036;
            class_2338 llllllllllllllllllIIIlllIllllIII = llllllllllllllllllIIIlllIlllIllI;
        } else {
            llllllllllllllllllIIIlllIllIlllI = llllllllllllllllllIIIlllIlllIllI.method_10093(llllllllllllllllllIIIlllIllIllIl.method_10153());
            hitPos.method_1031((double)llllllllllllllllllIIIlllIllIllIl.method_10148() * 0.5, (double)llllllllllllllllllIIIlllIllIllIl.method_10164() * 0.5, (double)llllllllllllllllllIIIlllIllIllIl.method_10165() * 0.5);
        }
        class_2350 llllllllllllllllllIIIlllIllIllII = llllllllllllllllllIIIlllIllIllIl;
        if (llllllllllllllllllIIIlllIllIlIII) {
            Rotations.rotate(Rotations.getYaw(hitPos), Rotations.getPitch(hitPos), llllllllllllllllllIIIlllIllIIlll, () -> {
                int llllllllllllllllllIIIlllIIlIIIlI = Utils.mc.field_1724.field_7514.field_7545;
                InvUtils.swap(llllllllllllllllllIIIlllIlllIlII);
                BlockUtils.place(new class_3965(hitPos, llllllllllllllllllIIIlllIllIllII, llllllllllllllllllIIIlllIllIlllI, false), llllllllllllllllllIIIlllIlllIlIl, llllllllllllllllllIIIlllIlllIIIl);
                if (llllllllllllllllllIIIlllIllIllll) {
                    InvUtils.swap(llllllllllllllllllIIIlllIIlIIIlI);
                }
            });
        } else {
            int llllllllllllllllllIIIlllIlllIlll = Utils.mc.field_1724.field_7514.field_7545;
            InvUtils.swap(llllllllllllllllllIIIlllIlllIlII);
            BlockUtils.place(new class_3965(hitPos, llllllllllllllllllIIIlllIllIllII, llllllllllllllllllIIIlllIllIlllI, false), llllllllllllllllllIIIlllIlllIlIl, llllllllllllllllllIIIlllIlllIIIl);
            if (llllllllllllllllllIIIlllIllIllll) {
                InvUtils.swap(llllllllllllllllllIIIlllIlllIlll);
            }
        }
        return true;
    }

    public static boolean place(class_2338 llllllllllllllllllIIIllllIIlllll, FindItemResult llllllllllllllllllIIIllllIlIIlII, boolean llllllllllllllllllIIIllllIlIIIll, int llllllllllllllllllIIIllllIlIIIlI, boolean llllllllllllllllllIIIllllIlIIIIl, boolean llllllllllllllllllIIIllllIlIIIII) {
        return BlockUtils.place(llllllllllllllllllIIIllllIIlllll, llllllllllllllllllIIIllllIlIIlII, llllllllllllllllllIIIllllIlIIIll, llllllllllllllllllIIIllllIlIIIlI, llllllllllllllllllIIIllllIlIIIIl, llllllllllllllllllIIIllllIlIIIII, true);
    }

    public BlockUtils() {
        BlockUtils llllllllllllllllllIIIlllllIlllIl;
    }

    public static boolean canPlace(class_2338 llllllllllllllllllIIIlllIlIIlIII) {
        return BlockUtils.canPlace(llllllllllllllllllIIIlllIlIIlIII, true);
    }

    private static void place(class_3965 llllllllllllllllllIIIlllIlIlIlIl, class_1268 llllllllllllllllllIIIlllIlIlIlII, boolean llllllllllllllllllIIIlllIlIlIIll) {
        boolean llllllllllllllllllIIIlllIlIlIlll = Utils.mc.field_1724.field_3913.field_3903;
        Utils.mc.field_1724.field_3913.field_3903 = false;
        class_1269 llllllllllllllllllIIIlllIlIlIllI = Utils.mc.field_1761.method_2896(Utils.mc.field_1724, Utils.mc.field_1687, llllllllllllllllllIIIlllIlIlIlII, llllllllllllllllllIIIlllIlIlIlIl);
        if (llllllllllllllllllIIIlllIlIlIllI.method_23666()) {
            if (llllllllllllllllllIIIlllIlIlIIll) {
                Utils.mc.field_1724.method_6104(llllllllllllllllllIIIlllIlIlIlII);
            } else {
                Utils.mc.method_1562().method_2883((class_2596)new class_2879(llllllllllllllllllIIIlllIlIlIlII));
            }
        }
        Utils.mc.field_1724.field_3913.field_3903 = llllllllllllllllllIIIlllIlIlIlll;
    }

    public static boolean place(class_2338 llllllllllllllllllIIIllllIlIllll, FindItemResult llllllllllllllllllIIIllllIlIlllI, int llllllllllllllllllIIIllllIlIllIl, boolean llllllllllllllllllIIIllllIlIllII) {
        return BlockUtils.place(llllllllllllllllllIIIllllIlIllll, llllllllllllllllllIIIllllIlIlllI, true, llllllllllllllllllIIIllllIlIllIl, true, llllllllllllllllllIIIllllIlIllII);
    }

    public static boolean place(class_2338 llllllllllllllllllIIIlllllIIIIIl, FindItemResult llllllllllllllllllIIIllllIlllIll, boolean llllllllllllllllllIIIllllIlllIlI, int llllllllllllllllllIIIllllIlllIIl, boolean llllllllllllllllllIIIllllIllllIl) {
        return BlockUtils.place(llllllllllllllllllIIIlllllIIIIIl, llllllllllllllllllIIIllllIlllIll, llllllllllllllllllIIIllllIlllIlI, llllllllllllllllllIIIllllIlllIIl, true, llllllllllllllllllIIIllllIllllIl);
    }

    public static boolean canPlace(class_2338 llllllllllllllllllIIIlllIlIIllII, boolean llllllllllllllllllIIIlllIlIIllIl) {
        if (llllllllllllllllllIIIlllIlIIllII == null) {
            return false;
        }
        if (class_1937.method_8518((class_2338)llllllllllllllllllIIIlllIlIIllII)) {
            return false;
        }
        if (!Utils.mc.field_1687.method_8320(llllllllllllllllllIIIlllIlIIllII).method_26207().method_15800()) {
            return false;
        }
        return !llllllllllllllllllIIIlllIlIIllIl || Utils.mc.field_1687.method_8628(class_2246.field_10340.method_9564(), llllllllllllllllllIIIlllIlIIllII, class_3726.method_16194());
    }

    public static boolean place(class_2338 llllllllllllllllllIIIllllIIlIIlI, FindItemResult llllllllllllllllllIIIllllIIlIIIl, boolean llllllllllllllllllIIIllllIIIlIIl, int llllllllllllllllllIIIllllIIIlIII, boolean llllllllllllllllllIIIllllIIIIlll, boolean llllllllllllllllllIIIllllIIIIllI, boolean llllllllllllllllllIIIllllIIIllII) {
        if (llllllllllllllllllIIIllllIIlIIIl.isOffhand()) {
            return BlockUtils.place(llllllllllllllllllIIIllllIIlIIlI, class_1268.field_5810, Utils.mc.field_1724.field_7514.field_7545, llllllllllllllllllIIIllllIIIlIIl, llllllllllllllllllIIIllllIIIlIII, llllllllllllllllllIIIllllIIIIlll, llllllllllllllllllIIIllllIIIIllI, llllllllllllllllllIIIllllIIIllII);
        }
        if (llllllllllllllllllIIIllllIIlIIIl.isHotbar()) {
            return BlockUtils.place(llllllllllllllllllIIIllllIIlIIlI, class_1268.field_5808, llllllllllllllllllIIIllllIIlIIIl.getSlot(), llllllllllllllllllIIIllllIIIlIIl, llllllllllllllllllIIIllllIIIlIII, llllllllllllllllllIIIllllIIIIlll, llllllllllllllllllIIIllllIIIIllI, llllllllllllllllllIIIllllIIIllII);
        }
        return false;
    }

    static {
        hitPos = new class_243(0.0, 0.0, 0.0);
    }

    public static boolean isClickable(class_2248 llllllllllllllllllIIIlllIIllIIII) {
        return llllllllllllllllllIIIlllIIllIIII instanceof class_2304 || llllllllllllllllllIIIlllIIllIIII instanceof class_2199 || llllllllllllllllllIIIlllIIllIIII instanceof class_2269 || llllllllllllllllllIIIlllIIllIIII instanceof class_2231 || llllllllllllllllllIIIlllIIllIIII instanceof class_2237 || llllllllllllllllllIIIlllIIllIIII instanceof class_2244 || llllllllllllllllllIIIlllIIllIIII instanceof class_2349 || llllllllllllllllllIIIlllIIllIIII instanceof class_2323 || llllllllllllllllllIIIlllIIllIIII instanceof class_2428 || llllllllllllllllllIIIlllIIllIIII instanceof class_2533;
    }
}

