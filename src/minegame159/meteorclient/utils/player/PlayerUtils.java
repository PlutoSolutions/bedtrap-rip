/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.player;

import baritone.api.BaritoneAPI;
import baritone.api.utils.Rotation;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.NoFall;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.misc.BaritoneUtils;
import minegame159.meteorclient.utils.misc.text.TextUtils;
import minegame159.meteorclient.utils.player.DamageCalcUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1812;
import net.minecraft.class_1829;
import net.minecraft.class_1934;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2587;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2828;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_4184;
import net.minecraft.class_640;

public class PlayerUtils {
    private static final Color color;
    private static final double diagonal;
    private static final class_243 horizontalVelocity;

    static {
        diagonal = 1.0 / Math.sqrt(2.0);
        horizontalVelocity = new class_243(0.0, 0.0, 0.0);
        color = new Color();
    }

    public static Color getPlayerColor(class_1657 class_16572, Color color) {
        if (Friends.get().isFriend(class_16572)) {
            return PlayerUtils.color.set(Friends.get().color).a(color.a);
        }
        if (!PlayerUtils.color.set(TextUtils.getMostPopularColor(class_16572.method_5476())).equals(Utils.WHITE) && Config.get().useTeamColor) {
            return PlayerUtils.color.set(PlayerUtils.color).a(color.a);
        }
        return color;
    }

    public static boolean isMoving() {
        return Utils.mc.field_1724.field_6250 != 0.0f || Utils.mc.field_1724.field_6212 != 0.0f;
    }

    public static double possibleHealthReductions() {
        return PlayerUtils.possibleHealthReductions(true, true);
    }

    public static class_1934 getGameMode() {
        class_640 class_6402 = Utils.mc.method_1562().method_2871(Utils.mc.field_1724.method_5667());
        if (class_6402 == null) {
            return class_1934.field_9218;
        }
        return class_6402.method_2958();
    }

    public static int getPing() {
        if (Utils.mc.method_1562() == null) {
            return 0;
        }
        class_640 class_6402 = Utils.mc.method_1562().method_2871(Utils.mc.field_1724.method_5667());
        if (class_6402 == null) {
            return 0;
        }
        return class_6402.method_2959();
    }

    public static double distanceTo(class_1297 class_12972) {
        return PlayerUtils.distanceTo(class_12972.method_23317(), class_12972.method_23318(), class_12972.method_23321());
    }

    public static Dimension getDimension() {
        switch (Utils.mc.field_1687.method_27983().method_29177().method_12832()) {
            case "the_nether": {
                return Dimension.Nether;
            }
            case "the_end": {
                return Dimension.End;
            }
        }
        return Dimension.Overworld;
    }

    public static double distanceTo(double d, double d2, double d3) {
        float f = (float)(Utils.mc.field_1724.method_23317() - d);
        float f2 = (float)(Utils.mc.field_1724.method_23318() - d2);
        float f3 = (float)(Utils.mc.field_1724.method_23321() - d3);
        return class_3532.method_15355((float)(f * f + f2 * f2 + f3 * f3));
    }

    public static double distanceTo(class_243 class_2432) {
        return PlayerUtils.distanceTo(class_2432.method_10216(), class_2432.method_10214(), class_2432.method_10215());
    }

    public static double possibleHealthReductions(boolean bl, boolean bl2) {
        double d;
        double d2 = 0.0;
        if (bl) {
            for (class_1297 class_12972 : Utils.mc.field_1687.method_18112()) {
                if (class_12972 instanceof class_1511 && d2 < DamageCalcUtils.crystalDamage((class_1309)Utils.mc.field_1724, class_12972.method_19538())) {
                    d2 = DamageCalcUtils.crystalDamage((class_1309)Utils.mc.field_1724, class_12972.method_19538());
                    continue;
                }
                if (!(class_12972 instanceof class_1657) || !(d2 < DamageCalcUtils.getSwordDamage((class_1657)class_12972, true)) || Friends.get().isFriend((class_1657)class_12972) || !(Utils.mc.field_1724.method_19538().method_1022(class_12972.method_19538()) < 5.0) || !(((class_1657)class_12972).method_6030().method_7909() instanceof class_1829)) continue;
                d2 = DamageCalcUtils.getSwordDamage((class_1657)class_12972, true);
            }
            if (PlayerUtils.getDimension() != Dimension.Overworld) {
                for (class_1297 class_12972 : Utils.mc.field_1687.field_9231) {
                    class_2338 class_23382 = class_12972.method_11016();
                    class_243 class_2432 = new class_243((double)class_23382.method_10263(), (double)class_23382.method_10264(), (double)class_23382.method_10260());
                    if (!(class_12972 instanceof class_2587) || !(d2 < DamageCalcUtils.bedDamage((class_1309)Utils.mc.field_1724, class_2432))) continue;
                    d2 = DamageCalcUtils.bedDamage((class_1309)Utils.mc.field_1724, class_2432);
                }
            }
        }
        if (bl2 && !Modules.get().isActive(NoFall.class) && Utils.mc.field_1724.field_6017 > 3.0f && (d = (double)Utils.mc.field_1724.field_6017 * 0.5) > d2 && !EntityUtils.isAboveWater((class_1297)Utils.mc.field_1724)) {
            d2 = d;
        }
        return d2;
    }

    public static float[] calculateAngle(class_243 class_2432) {
        class_243 class_2433 = new class_243(Utils.mc.field_1724.method_23317(), Utils.mc.field_1724.method_23318() + (double)Utils.mc.field_1724.method_18381(Utils.mc.field_1724.method_18376()), Utils.mc.field_1724.method_23321());
        double d = class_2432.field_1352 - class_2433.field_1352;
        double d2 = (class_2432.field_1351 - class_2433.field_1351) * -1.0;
        double d3 = class_2432.field_1350 - class_2433.field_1350;
        double d4 = class_3532.method_15368((double)(d * d + d3 * d3));
        return new float[]{(float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(d3, d)) - 90.0)), (float)class_3532.method_15338((double)Math.toDegrees(Math.atan2(d2, d4)))};
    }

    public static class_243 getHorizontalVelocity(double d) {
        Rotation rotation;
        float f = Utils.mc.field_1724.field_6031;
        if (BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing() && (rotation = BaritoneUtils.getTarget()) != null) {
            f = rotation.getYaw();
        }
        rotation = class_243.method_1030((float)0.0f, (float)f);
        class_243 class_2432 = class_243.method_1030((float)0.0f, (float)(f + 90.0f));
        double d2 = 0.0;
        double d3 = 0.0;
        boolean bl = false;
        if (Utils.mc.field_1724.field_3913.field_3910) {
            d2 += rotation.field_1352 / 20.0 * d;
            d3 += rotation.field_1350 / 20.0 * d;
            bl = true;
        }
        if (Utils.mc.field_1724.field_3913.field_3909) {
            d2 -= rotation.field_1352 / 20.0 * d;
            d3 -= rotation.field_1350 / 20.0 * d;
            bl = true;
        }
        boolean bl2 = false;
        if (Utils.mc.field_1724.field_3913.field_3906) {
            d2 += class_2432.field_1352 / 20.0 * d;
            d3 += class_2432.field_1350 / 20.0 * d;
            bl2 = true;
        }
        if (Utils.mc.field_1724.field_3913.field_3908) {
            d2 -= class_2432.field_1352 / 20.0 * d;
            d3 -= class_2432.field_1350 / 20.0 * d;
            bl2 = true;
        }
        if (bl && bl2) {
            d2 *= diagonal;
            d3 *= diagonal;
        }
        ((IVec3d)horizontalVelocity).setXZ(d2, d3);
        return horizontalVelocity;
    }

    public static double distanceToCamera(double d, double d2, double d3) {
        class_4184 class_41842 = Utils.mc.field_1773.method_19418();
        return Math.sqrt(class_41842.method_19326().method_1028(d, d2, d3));
    }

    public static boolean canSeeEntity(class_1297 class_12972) {
        class_243 class_2432 = new class_243(0.0, 0.0, 0.0);
        class_243 class_2433 = new class_243(0.0, 0.0, 0.0);
        ((IVec3d)class_2432).set(Utils.mc.field_1724.method_23317(), Utils.mc.field_1724.method_23318() + (double)Utils.mc.field_1724.method_5751(), Utils.mc.field_1724.method_23321());
        ((IVec3d)class_2433).set(class_12972.method_23317(), class_12972.method_23318(), class_12972.method_23321());
        boolean bl = Utils.mc.field_1687.method_17742(new class_3959(class_2432, class_2433, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)Utils.mc.field_1724)).method_17783() == class_239.class_240.field_1333;
        ((IVec3d)class_2433).set(class_12972.method_23317(), class_12972.method_23318() + (double)class_12972.method_5751(), class_12972.method_23321());
        boolean bl2 = Utils.mc.field_1687.method_17742(new class_3959(class_2432, class_2433, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)Utils.mc.field_1724)).method_17783() == class_239.class_240.field_1333;
        return bl || bl2;
    }

    public static boolean isInHole(boolean bl) {
        if (!Utils.canUpdate()) {
            return false;
        }
        class_2338 class_23382 = Utils.mc.field_1724.method_24515();
        int n = 0;
        for (class_2350 class_23502 : class_2350.values()) {
            class_2680 class_26802;
            if (class_23502 == class_2350.field_11036 || (class_26802 = Utils.mc.field_1687.method_8320(class_23382.method_10093(class_23502))).method_26204() == class_2246.field_9987 || class_26802.method_26204() == class_2246.field_10540) continue;
            if (!bl || class_23502 == class_2350.field_11033) {
                return false;
            }
            ++n;
            for (class_2350 class_23503 : class_2350.values()) {
                class_2680 class_26803;
                if (class_23503 == class_23502.method_10153() || class_23503 == class_2350.field_11036 || (class_26803 = Utils.mc.field_1687.method_8320(class_23382.method_10093(class_23502).method_10093(class_23503))).method_26204() == class_2246.field_9987 || class_26803.method_26204() == class_2246.field_10540) continue;
                return false;
            }
            if (2 != 1) continue;
            return false;
        }
        return n < 2;
    }

    public static double getTotalHealth() {
        return Utils.mc.field_1724.method_6032() + Utils.mc.field_1724.method_6067();
    }

    public static double distanceToCamera(class_1297 class_12972) {
        return PlayerUtils.distanceToCamera(class_12972.method_23317(), class_12972.method_23318(), class_12972.method_23321());
    }

    public static boolean isSprinting() {
        return Utils.mc.field_1724.method_5624() && (Utils.mc.field_1724.field_6250 != 0.0f || Utils.mc.field_1724.field_6212 != 0.0f);
    }

    public static boolean shouldPause(boolean bl, boolean bl2, boolean bl3) {
        if (bl && Utils.mc.field_1761.method_2923()) {
            return true;
        }
        if (bl2 && Utils.mc.field_1724.method_6115() && (Utils.mc.field_1724.method_6047().method_7909().method_19263() || Utils.mc.field_1724.method_6079().method_7909().method_19263())) {
            return true;
        }
        return bl3 && Utils.mc.field_1724.method_6115() && (Utils.mc.field_1724.method_6047().method_7909() instanceof class_1812 || Utils.mc.field_1724.method_6079().method_7909() instanceof class_1812);
    }

    public static void centerPlayer() {
        double d = (double)class_3532.method_15357((double)Utils.mc.field_1724.method_23317()) + 0.5;
        double d2 = (double)class_3532.method_15357((double)Utils.mc.field_1724.method_23321()) + 0.5;
        Utils.mc.field_1724.method_5814(d, Utils.mc.field_1724.method_23318(), d2);
        Utils.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(Utils.mc.field_1724.method_23317(), Utils.mc.field_1724.method_23318(), Utils.mc.field_1724.method_23321(), Utils.mc.field_1724.method_24828()));
    }

    public static double distanceTo(class_2338 class_23382) {
        return PlayerUtils.distanceTo(class_23382.method_10263(), class_23382.method_10264(), class_23382.method_10260());
    }
}

