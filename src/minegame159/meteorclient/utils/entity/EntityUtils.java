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
    public static int getPing(class_1657 class_16572) {
        if (Utils.mc.method_1562() == null) {
            return 0;
        }
        class_640 class_6402 = Utils.mc.method_1562().method_2871(class_16572.method_5667());
        if (class_6402 == null) {
            return 0;
        }
        return class_6402.method_2959();
    }

    public static class_1934 getGameMode(class_1657 class_16572) {
        if (class_16572 == null) {
            return class_1934.field_9218;
        }
        class_640 class_6402 = Utils.mc.method_1562().method_2871(class_16572.method_5667());
        if (class_6402 == null) {
            return class_1934.field_9218;
        }
        return class_6402.method_2958();
    }

    public static class_2338 getCityBlock(class_1657 class_16572) {
        List<class_2338> list = EntityUtils.getSurroundBlocks(class_16572);
        list.sort(Comparator.comparingDouble(PlayerUtils::distanceTo));
        return list.isEmpty() ? null : list.get(0);
    }

    public static boolean isInRenderDistance(double d, double d2) {
        double d3 = Math.abs(Utils.mc.field_1773.method_19418().method_19326().field_1352 - d);
        double d4 = Math.abs(Utils.mc.field_1773.method_19418().method_19326().field_1350 - d2);
        double d5 = (Utils.mc.field_1690.field_1870 + 1) * 16;
        return d3 < d5 && d4 < d5;
    }

    public static boolean isAboveWater(class_1297 class_12972) {
        class_2680 class_26802;
        class_2338.class_2339 class_23392 = class_12972.method_24515().method_25503();
        for (int i = 0; i < 64 && !(class_26802 = Utils.mc.field_1687.method_8320((class_2338)class_23392)).method_26207().method_15801(); ++i) {
            class_3611 class_36112 = class_26802.method_26227().method_15772();
            if (class_36112 == class_3612.field_15910 || class_36112 == class_3612.field_15909) {
                return true;
            }
            class_23392.method_10100(0, -1, 0);
            if (2 != 0) continue;
            return false;
        }
        return false;
    }

    public static boolean isAttackable(class_1299<?> class_12992) {
        return class_12992 != class_1299.field_6083 && class_12992 != class_1299.field_6122 && class_12992 != class_1299.field_6089 && class_12992 != class_1299.field_6133 && class_12992 != class_1299.field_6052 && class_12992 != class_1299.field_6124 && class_12992 != class_1299.field_6135 && class_12992 != class_1299.field_6082 && class_12992 != class_1299.field_6064 && class_12992 != class_1299.field_6045 && class_12992 != class_1299.field_6127 && class_12992 != class_1299.field_6112 && class_12992 != class_1299.field_6103 && class_12992 != class_1299.field_6044 && class_12992 != class_1299.field_6144;
    }

    public static List<class_2338> getSurroundBlocks(class_1657 class_16572) {
        if (class_16572 == null) {
            return null;
        }
        ArrayList<class_2338> arrayList = new ArrayList<class_2338>();
        for (class_2350 class_23502 : class_2350.values()) {
            class_2338 class_23382;
            if (class_23502 == class_2350.field_11036 || class_23502 == class_2350.field_11033 || Utils.mc.field_1687.method_8320(class_23382 = class_16572.method_24515().method_10093(class_23502)).method_26204() != class_2246.field_10540) continue;
            arrayList.add(class_23382);
            if (false <= true) continue;
            return null;
        }
        return arrayList;
    }

    public static float getTotalHealth(class_1657 class_16572) {
        return class_16572.method_6032() + class_16572.method_6067();
    }

    public static boolean isInRenderDistance(class_2338 class_23382) {
        if (class_23382 == null) {
            return false;
        }
        return EntityUtils.isInRenderDistance(class_23382.method_10263(), class_23382.method_10260());
    }

    public static boolean isInRenderDistance(class_2586 class_25862) {
        if (class_25862 == null) {
            return false;
        }
        return EntityUtils.isInRenderDistance(class_25862.method_11016().method_10263(), class_25862.method_11016().method_10260());
    }

    public static boolean isInRenderDistance(class_1297 class_12972) {
        if (class_12972 == null) {
            return false;
        }
        return EntityUtils.isInRenderDistance(class_12972.method_23317(), class_12972.method_23321());
    }
}

