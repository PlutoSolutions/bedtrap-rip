/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1657
 *  net.minecraft.class_1934
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2586
 *  net.minecraft.class_2680
 *  net.minecraft.class_3611
 *  net.minecraft.class_3612
 *  net.minecraft.class_640
 */
package minegame159.meteorclient.utils.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1934;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_640;

public class EntityUtils {
    public static int getPing(class_1657 lllllllllllllllllIIlllIIlIIIlIIl) {
        if (Utils.mc.method_1562() == null) {
            return 0;
        }
        class_640 lllllllllllllllllIIlllIIlIIIlIlI = Utils.mc.method_1562().method_2871(lllllllllllllllllIIlllIIlIIIlIIl.method_5667());
        if (lllllllllllllllllIIlllIIlIIIlIlI == null) {
            return 0;
        }
        return lllllllllllllllllIIlllIIlIIIlIlI.method_2959();
    }

    public EntityUtils() {
        EntityUtils lllllllllllllllllIIlllIIlIIlIlIl;
    }

    public static class_1934 getGameMode(class_1657 lllllllllllllllllIIlllIIlIIIIIll) {
        if (lllllllllllllllllIIlllIIlIIIIIll == null) {
            return class_1934.field_9218;
        }
        class_640 lllllllllllllllllIIlllIIlIIIIlII = Utils.mc.method_1562().method_2871(lllllllllllllllllIIlllIIlIIIIIll.method_5667());
        if (lllllllllllllllllIIlllIIlIIIIlII == null) {
            return class_1934.field_9218;
        }
        return lllllllllllllllllIIlllIIlIIIIlII.method_2958();
    }

    public static class_2338 getCityBlock(class_1657 lllllllllllllllllIIlllIIIlIIIllI) {
        List<class_2338> lllllllllllllllllIIlllIIIlIIIlIl = EntityUtils.getSurroundBlocks(lllllllllllllllllIIlllIIIlIIIllI);
        lllllllllllllllllIIlllIIIlIIIlIl.sort(Comparator.comparingDouble(PlayerUtils::distanceTo));
        return lllllllllllllllllIIlllIIIlIIIlIl.isEmpty() ? null : lllllllllllllllllIIlllIIIlIIIlIl.get(0);
    }

    public static boolean isInRenderDistance(double lllllllllllllllllIIlllIIIllIIlII, double lllllllllllllllllIIlllIIIllIIIll) {
        double lllllllllllllllllIIlllIIIllIIIlI = Math.abs(Utils.mc.field_1773.method_19418().method_19326().field_1352 - lllllllllllllllllIIlllIIIllIIlII);
        double lllllllllllllllllIIlllIIIllIIIIl = Math.abs(Utils.mc.field_1773.method_19418().method_19326().field_1350 - lllllllllllllllllIIlllIIIllIIIll);
        double lllllllllllllllllIIlllIIIllIIIII = (Utils.mc.field_1690.field_1870 + 1) * 16;
        return lllllllllllllllllIIlllIIIllIIIlI < lllllllllllllllllIIlllIIIllIIIII && lllllllllllllllllIIlllIIIllIIIIl < lllllllllllllllllIIlllIIIllIIIII;
    }

    public static boolean isAboveWater(class_1297 lllllllllllllllllIIlllIIIllllIIl) {
        class_2680 lllllllllllllllllIIlllIIIlllllII;
        class_2338.class_2339 lllllllllllllllllIIlllIIIllllIII = lllllllllllllllllIIlllIIIllllIIl.method_24515().method_25503();
        for (int lllllllllllllllllIIlllIIIllllIlI = 0; lllllllllllllllllIIlllIIIllllIlI < 64 && !(lllllllllllllllllIIlllIIIlllllII = Utils.mc.field_1687.method_8320((class_2338)lllllllllllllllllIIlllIIIllllIII)).method_26207().method_15801(); ++lllllllllllllllllIIlllIIIllllIlI) {
            class_3611 lllllllllllllllllIIlllIIIllllIll = lllllllllllllllllIIlllIIIlllllII.method_26227().method_15772();
            if (lllllllllllllllllIIlllIIIllllIll == class_3612.field_15910 || lllllllllllllllllIIlllIIIllllIll == class_3612.field_15909) {
                return true;
            }
            lllllllllllllllllIIlllIIIllllIII.method_10100(0, -1, 0);
        }
        return false;
    }

    public static boolean isAttackable(class_1299<?> lllllllllllllllllIIlllIIlIIlIIlI) {
        return lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6083 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6122 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6089 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6133 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6052 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6124 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6135 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6082 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6064 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6045 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6127 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6112 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6103 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6044 && lllllllllllllllllIIlllIIlIIlIIlI != class_1299.field_6144;
    }

    public static List<class_2338> getSurroundBlocks(class_1657 lllllllllllllllllIIlllIIIlIlIIIl) {
        if (lllllllllllllllllIIlllIIIlIlIIIl == null) {
            return null;
        }
        ArrayList<class_2338> lllllllllllllllllIIlllIIIlIlIIII = new ArrayList<class_2338>();
        for (class_2350 lllllllllllllllllIIlllIIIlIlIIlI : class_2350.values()) {
            class_2338 lllllllllllllllllIIlllIIIlIlIIll;
            if (lllllllllllllllllIIlllIIIlIlIIlI == class_2350.field_11036 || lllllllllllllllllIIlllIIIlIlIIlI == class_2350.field_11033 || Utils.mc.field_1687.method_8320(lllllllllllllllllIIlllIIIlIlIIll = lllllllllllllllllIIlllIIIlIlIIIl.method_24515().method_10093(lllllllllllllllllIIlllIIIlIlIIlI)).method_26204() != class_2246.field_10540) continue;
            lllllllllllllllllIIlllIIIlIlIIII.add(lllllllllllllllllIIlllIIIlIlIIll);
        }
        return lllllllllllllllllIIlllIIIlIlIIII;
    }

    public static float getTotalHealth(class_1657 lllllllllllllllllIIlllIIlIIIlllI) {
        return lllllllllllllllllIIlllIIlIIIlllI.method_6032() + lllllllllllllllllIIlllIIlIIIlllI.method_6067();
    }

    public static boolean isInRenderDistance(class_2338 lllllllllllllllllIIlllIIIllIlIll) {
        if (lllllllllllllllllIIlllIIIllIlIll == null) {
            return false;
        }
        return EntityUtils.isInRenderDistance(lllllllllllllllllIIlllIIIllIlIll.method_10263(), lllllllllllllllllIIlllIIIllIlIll.method_10260());
    }

    public static boolean isInRenderDistance(class_2586 lllllllllllllllllIIlllIIIllIlllI) {
        if (lllllllllllllllllIIlllIIIllIlllI == null) {
            return false;
        }
        return EntityUtils.isInRenderDistance(lllllllllllllllllIIlllIIIllIlllI.method_11016().method_10263(), lllllllllllllllllIIlllIIIllIlllI.method_11016().method_10260());
    }

    public static boolean isInRenderDistance(class_1297 lllllllllllllllllIIlllIIIlllIIII) {
        if (lllllllllllllllllIIlllIIIlllIIII == null) {
            return false;
        }
        return EntityUtils.isInRenderDistance(lllllllllllllllllIIlllIIIlllIIII.method_23317(), lllllllllllllllllIIlllIIIlllIIII.method_23321());
    }
}

