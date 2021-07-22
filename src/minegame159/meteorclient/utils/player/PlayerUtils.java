/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  baritone.api.utils.Rotation
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1812
 *  net.minecraft.class_1829
 *  net.minecraft.class_1934
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2586
 *  net.minecraft.class_2587
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_4184
 *  net.minecraft.class_640
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
import net.minecraft.class_2586;
import net.minecraft.class_2587;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2828;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_4184;
import net.minecraft.class_640;

public class PlayerUtils {
    private static final /* synthetic */ Color color;
    private static final /* synthetic */ double diagonal;
    private static final /* synthetic */ class_243 horizontalVelocity;

    static {
        diagonal = 1.0 / Math.sqrt(2.0);
        horizontalVelocity = new class_243(0.0, 0.0, 0.0);
        color = new Color();
    }

    public static Color getPlayerColor(class_1657 lllllllllllllllllllllIllllllIlII, Color lllllllllllllllllllllIllllllIIIl) {
        if (Friends.get().isFriend(lllllllllllllllllllllIllllllIlII)) {
            return color.set(Friends.get().color).a(lllllllllllllllllllllIllllllIIIl.a);
        }
        if (!color.set(TextUtils.getMostPopularColor(lllllllllllllllllllllIllllllIlII.method_5476())).equals(Utils.WHITE) && Config.get().useTeamColor) {
            return color.set(color).a(lllllllllllllllllllllIllllllIIIl.a);
        }
        return lllllllllllllllllllllIllllllIIIl;
    }

    public static boolean isMoving() {
        return Utils.mc.field_1724.field_6250 != 0.0f || Utils.mc.field_1724.field_6212 != 0.0f;
    }

    public static double possibleHealthReductions() {
        return PlayerUtils.possibleHealthReductions(true, true);
    }

    public static class_1934 getGameMode() {
        class_640 lllllllllllllllllllllIllIlIIIIIl = Utils.mc.method_1562().method_2871(Utils.mc.field_1724.method_5667());
        if (lllllllllllllllllllllIllIlIIIIIl == null) {
            return class_1934.field_9218;
        }
        return lllllllllllllllllllllIllIlIIIIIl.method_2958();
    }

    public static int getPing() {
        if (Utils.mc.method_1562() == null) {
            return 0;
        }
        class_640 lllllllllllllllllllllIllIIlllllI = Utils.mc.method_1562().method_2871(Utils.mc.field_1724.method_5667());
        if (lllllllllllllllllllllIllIIlllllI == null) {
            return 0;
        }
        return lllllllllllllllllllllIllIIlllllI.method_2959();
    }

    public PlayerUtils() {
        PlayerUtils lllllllllllllllllllllIllllllIlll;
    }

    public static double distanceTo(class_1297 lllllllllllllllllllllIllIllIllll) {
        return PlayerUtils.distanceTo(lllllllllllllllllllllIllIllIllll.method_23317(), lllllllllllllllllllllIllIllIllll.method_23318(), lllllllllllllllllllllIllIllIllll.method_23321());
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

    public static double distanceTo(double lllllllllllllllllllllIllIlIllIll, double lllllllllllllllllllllIllIlIllIlI, double lllllllllllllllllllllIllIlIllIIl) {
        float lllllllllllllllllllllIllIlIllllI = (float)(Utils.mc.field_1724.method_23317() - lllllllllllllllllllllIllIlIllIll);
        float lllllllllllllllllllllIllIlIlllIl = (float)(Utils.mc.field_1724.method_23318() - lllllllllllllllllllllIllIlIllIlI);
        float lllllllllllllllllllllIllIlIlllII = (float)(Utils.mc.field_1724.method_23321() - lllllllllllllllllllllIllIlIllIIl);
        return class_3532.method_15355((float)(lllllllllllllllllllllIllIlIllllI * lllllllllllllllllllllIllIlIllllI + lllllllllllllllllllllIllIlIlllIl * lllllllllllllllllllllIllIlIlllIl + lllllllllllllllllllllIllIlIlllII * lllllllllllllllllllllIllIlIlllII));
    }

    public static double distanceTo(class_243 lllllllllllllllllllllIllIllIlIII) {
        return PlayerUtils.distanceTo(lllllllllllllllllllllIllIllIlIII.method_10216(), lllllllllllllllllllllIllIllIlIII.method_10214(), lllllllllllllllllllllIllIllIlIII.method_10215());
    }

    public static double possibleHealthReductions(boolean lllllllllllllllllllllIllIllllIlI, boolean lllllllllllllllllllllIllIllllIIl) {
        double lllllllllllllllllllllIllIllllIll;
        double lllllllllllllllllllllIllIllllIII = 0.0;
        if (lllllllllllllllllllllIllIllllIlI) {
            for (class_1297 lllllllllllllllllllllIllIlllllll : Utils.mc.field_1687.method_18112()) {
                if (lllllllllllllllllllllIllIlllllll instanceof class_1511 && lllllllllllllllllllllIllIllllIII < DamageCalcUtils.crystalDamage((class_1309)Utils.mc.field_1724, lllllllllllllllllllllIllIlllllll.method_19538())) {
                    lllllllllllllllllllllIllIllllIII = DamageCalcUtils.crystalDamage((class_1309)Utils.mc.field_1724, lllllllllllllllllllllIllIlllllll.method_19538());
                    continue;
                }
                if (!(lllllllllllllllllllllIllIlllllll instanceof class_1657) || !(lllllllllllllllllllllIllIllllIII < DamageCalcUtils.getSwordDamage((class_1657)lllllllllllllllllllllIllIlllllll, true)) || Friends.get().isFriend((class_1657)lllllllllllllllllllllIllIlllllll) || !(Utils.mc.field_1724.method_19538().method_1022(lllllllllllllllllllllIllIlllllll.method_19538()) < 5.0) || !(((class_1657)lllllllllllllllllllllIllIlllllll).method_6030().method_7909() instanceof class_1829)) continue;
                lllllllllllllllllllllIllIllllIII = DamageCalcUtils.getSwordDamage((class_1657)lllllllllllllllllllllIllIlllllll, true);
            }
            if (PlayerUtils.getDimension() != Dimension.Overworld) {
                for (class_2586 lllllllllllllllllllllIllIlllllII : Utils.mc.field_1687.field_9231) {
                    class_2338 lllllllllllllllllllllIllIllllllI = lllllllllllllllllllllIllIlllllII.method_11016();
                    class_243 lllllllllllllllllllllIllIlllllIl = new class_243((double)lllllllllllllllllllllIllIllllllI.method_10263(), (double)lllllllllllllllllllllIllIllllllI.method_10264(), (double)lllllllllllllllllllllIllIllllllI.method_10260());
                    if (!(lllllllllllllllllllllIllIlllllII instanceof class_2587) || !(lllllllllllllllllllllIllIllllIII < DamageCalcUtils.bedDamage((class_1309)Utils.mc.field_1724, lllllllllllllllllllllIllIlllllIl))) continue;
                    lllllllllllllllllllllIllIllllIII = DamageCalcUtils.bedDamage((class_1309)Utils.mc.field_1724, lllllllllllllllllllllIllIlllllIl);
                }
            }
        }
        if (lllllllllllllllllllllIllIllllIIl && !Modules.get().isActive(NoFall.class) && Utils.mc.field_1724.field_6017 > 3.0f && (lllllllllllllllllllllIllIllllIll = (double)Utils.mc.field_1724.field_6017 * 0.5) > lllllllllllllllllllllIllIllllIII && !EntityUtils.isAboveWater((class_1297)Utils.mc.field_1724)) {
            lllllllllllllllllllllIllIllllIII = lllllllllllllllllllllIllIllllIll;
        }
        return lllllllllllllllllllllIllIllllIII;
    }

    public static float[] calculateAngle(class_243 lllllllllllllllllllllIlllIllIllI) {
        class_243 lllllllllllllllllllllIlllIlllIll = new class_243(Utils.mc.field_1724.method_23317(), Utils.mc.field_1724.method_23318() + (double)Utils.mc.field_1724.method_18381(Utils.mc.field_1724.method_18376()), Utils.mc.field_1724.method_23321());
        double lllllllllllllllllllllIlllIlllIlI = lllllllllllllllllllllIlllIllIllI.field_1352 - lllllllllllllllllllllIlllIlllIll.field_1352;
        double lllllllllllllllllllllIlllIlllIIl = (lllllllllllllllllllllIlllIllIllI.field_1351 - lllllllllllllllllllllIlllIlllIll.field_1351) * -1.0;
        double lllllllllllllllllllllIlllIlllIII = lllllllllllllllllllllIlllIllIllI.field_1350 - lllllllllllllllllllllIlllIlllIll.field_1350;
        double lllllllllllllllllllllIlllIllIlll = class_3532.method_15368((double)(lllllllllllllllllllllIlllIlllIlI * lllllllllllllllllllllIlllIlllIlI + lllllllllllllllllllllIlllIlllIII * lllllllllllllllllllllIlllIlllIII));
        return new float[]{(float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(lllllllllllllllllllllIlllIlllIII, lllllllllllllllllllllIlllIlllIlI)) - 90.0)), (float)class_3532.method_15338((double)Math.toDegrees(Math.atan2(lllllllllllllllllllllIlllIlllIIl, lllllllllllllllllllllIlllIllIlll)))};
    }

    public static class_243 getHorizontalVelocity(double lllllllllllllllllllllIlllllIIlll) {
        Rotation lllllllllllllllllllllIlllllIlIII;
        float lllllllllllllllllllllIlllllIIllI = Utils.mc.field_1724.field_6031;
        if (BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing() && (lllllllllllllllllllllIlllllIlIII = BaritoneUtils.getTarget()) != null) {
            lllllllllllllllllllllIlllllIIllI = lllllllllllllllllllllIlllllIlIII.getYaw();
        }
        class_243 lllllllllllllllllllllIlllllIIlIl = class_243.method_1030((float)0.0f, (float)lllllllllllllllllllllIlllllIIllI);
        class_243 lllllllllllllllllllllIlllllIIlII = class_243.method_1030((float)0.0f, (float)(lllllllllllllllllllllIlllllIIllI + 90.0f));
        double lllllllllllllllllllllIlllllIIIll = 0.0;
        double lllllllllllllllllllllIlllllIIIlI = 0.0;
        boolean lllllllllllllllllllllIlllllIIIIl = false;
        if (Utils.mc.field_1724.field_3913.field_3910) {
            lllllllllllllllllllllIlllllIIIll += lllllllllllllllllllllIlllllIIlIl.field_1352 / 20.0 * lllllllllllllllllllllIlllllIIlll;
            lllllllllllllllllllllIlllllIIIlI += lllllllllllllllllllllIlllllIIlIl.field_1350 / 20.0 * lllllllllllllllllllllIlllllIIlll;
            lllllllllllllllllllllIlllllIIIIl = true;
        }
        if (Utils.mc.field_1724.field_3913.field_3909) {
            lllllllllllllllllllllIlllllIIIll -= lllllllllllllllllllllIlllllIIlIl.field_1352 / 20.0 * lllllllllllllllllllllIlllllIIlll;
            lllllllllllllllllllllIlllllIIIlI -= lllllllllllllllllllllIlllllIIlIl.field_1350 / 20.0 * lllllllllllllllllllllIlllllIIlll;
            lllllllllllllllllllllIlllllIIIIl = true;
        }
        boolean lllllllllllllllllllllIlllllIIIII = false;
        if (Utils.mc.field_1724.field_3913.field_3906) {
            lllllllllllllllllllllIlllllIIIll += lllllllllllllllllllllIlllllIIlII.field_1352 / 20.0 * lllllllllllllllllllllIlllllIIlll;
            lllllllllllllllllllllIlllllIIIlI += lllllllllllllllllllllIlllllIIlII.field_1350 / 20.0 * lllllllllllllllllllllIlllllIIlll;
            lllllllllllllllllllllIlllllIIIII = true;
        }
        if (Utils.mc.field_1724.field_3913.field_3908) {
            lllllllllllllllllllllIlllllIIIll -= lllllllllllllllllllllIlllllIIlII.field_1352 / 20.0 * lllllllllllllllllllllIlllllIIlll;
            lllllllllllllllllllllIlllllIIIlI -= lllllllllllllllllllllIlllllIIlII.field_1350 / 20.0 * lllllllllllllllllllllIlllllIIlll;
            lllllllllllllllllllllIlllllIIIII = true;
        }
        if (lllllllllllllllllllllIlllllIIIIl && lllllllllllllllllllllIlllllIIIII) {
            lllllllllllllllllllllIlllllIIIll *= diagonal;
            lllllllllllllllllllllIlllllIIIlI *= diagonal;
        }
        ((IVec3d)horizontalVelocity).setXZ(lllllllllllllllllllllIlllllIIIll, lllllllllllllllllllllIlllllIIIlI);
        return horizontalVelocity;
    }

    public static double distanceToCamera(double lllllllllllllllllllllIllIlIIllIl, double lllllllllllllllllllllIllIlIIllII, double lllllllllllllllllllllIllIlIIllll) {
        class_4184 lllllllllllllllllllllIllIlIIlllI = Utils.mc.field_1773.method_19418();
        return Math.sqrt(lllllllllllllllllllllIllIlIIlllI.method_19326().method_1028(lllllllllllllllllllllIllIlIIllIl, lllllllllllllllllllllIllIlIIllII, lllllllllllllllllllllIllIlIIllll));
    }

    public static boolean canSeeEntity(class_1297 lllllllllllllllllllllIllllIIIlll) {
        class_243 lllllllllllllllllllllIllllIIlIll = new class_243(0.0, 0.0, 0.0);
        class_243 lllllllllllllllllllllIllllIIlIlI = new class_243(0.0, 0.0, 0.0);
        ((IVec3d)lllllllllllllllllllllIllllIIlIll).set(Utils.mc.field_1724.method_23317(), Utils.mc.field_1724.method_23318() + (double)Utils.mc.field_1724.method_5751(), Utils.mc.field_1724.method_23321());
        ((IVec3d)lllllllllllllllllllllIllllIIlIlI).set(lllllllllllllllllllllIllllIIIlll.method_23317(), lllllllllllllllllllllIllllIIIlll.method_23318(), lllllllllllllllllllllIllllIIIlll.method_23321());
        boolean lllllllllllllllllllllIllllIIlIIl = Utils.mc.field_1687.method_17742(new class_3959(lllllllllllllllllllllIllllIIlIll, lllllllllllllllllllllIllllIIlIlI, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)Utils.mc.field_1724)).method_17783() == class_239.class_240.field_1333;
        ((IVec3d)lllllllllllllllllllllIllllIIlIlI).set(lllllllllllllllllllllIllllIIIlll.method_23317(), lllllllllllllllllllllIllllIIIlll.method_23318() + (double)lllllllllllllllllllllIllllIIIlll.method_5751(), lllllllllllllllllllllIllllIIIlll.method_23321());
        boolean lllllllllllllllllllllIllllIIlIII = Utils.mc.field_1687.method_17742(new class_3959(lllllllllllllllllllllIllllIIlIll, lllllllllllllllllllllIllllIIlIlI, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)Utils.mc.field_1724)).method_17783() == class_239.class_240.field_1333;
        return lllllllllllllllllllllIllllIIlIIl || lllllllllllllllllllllIllllIIlIII;
    }

    public static boolean isInHole(boolean lllllllllllllllllllllIlllIIlIIll) {
        if (!Utils.canUpdate()) {
            return false;
        }
        class_2338 lllllllllllllllllllllIlllIIlIlIl = Utils.mc.field_1724.method_24515();
        int lllllllllllllllllllllIlllIIlIlII = 0;
        for (class_2350 lllllllllllllllllllllIlllIIlIlll : class_2350.values()) {
            class_2680 lllllllllllllllllllllIlllIIllIII;
            if (lllllllllllllllllllllIlllIIlIlll == class_2350.field_11036 || (lllllllllllllllllllllIlllIIllIII = Utils.mc.field_1687.method_8320(lllllllllllllllllllllIlllIIlIlIl.method_10093(lllllllllllllllllllllIlllIIlIlll))).method_26204() == class_2246.field_9987 || lllllllllllllllllllllIlllIIllIII.method_26204() == class_2246.field_10540) continue;
            if (!lllllllllllllllllllllIlllIIlIIll || lllllllllllllllllllllIlllIIlIlll == class_2350.field_11033) {
                return false;
            }
            ++lllllllllllllllllllllIlllIIlIlII;
            for (class_2350 lllllllllllllllllllllIlllIIllIIl : class_2350.values()) {
                class_2680 lllllllllllllllllllllIlllIIllIlI;
                if (lllllllllllllllllllllIlllIIllIIl == lllllllllllllllllllllIlllIIlIlll.method_10153() || lllllllllllllllllllllIlllIIllIIl == class_2350.field_11036 || (lllllllllllllllllllllIlllIIllIlI = Utils.mc.field_1687.method_8320(lllllllllllllllllllllIlllIIlIlIl.method_10093(lllllllllllllllllllllIlllIIlIlll).method_10093(lllllllllllllllllllllIlllIIllIIl))).method_26204() == class_2246.field_9987 || lllllllllllllllllllllIlllIIllIlI.method_26204() == class_2246.field_10540) continue;
                return false;
            }
        }
        return lllllllllllllllllllllIlllIIlIlII < 2;
    }

    public static double getTotalHealth() {
        return Utils.mc.field_1724.method_6032() + Utils.mc.field_1724.method_6067();
    }

    public static double distanceToCamera(class_1297 lllllllllllllllllllllIllIlIIlIII) {
        return PlayerUtils.distanceToCamera(lllllllllllllllllllllIllIlIIlIII.method_23317(), lllllllllllllllllllllIllIlIIlIII.method_23318(), lllllllllllllllllllllIllIlIIlIII.method_23321());
    }

    public static boolean isSprinting() {
        return Utils.mc.field_1724.method_5624() && (Utils.mc.field_1724.field_6250 != 0.0f || Utils.mc.field_1724.field_6212 != 0.0f);
    }

    public static boolean shouldPause(boolean lllllllllllllllllllllIlllIlIlIlI, boolean lllllllllllllllllllllIlllIlIlIIl, boolean lllllllllllllllllllllIlllIlIlIll) {
        if (lllllllllllllllllllllIlllIlIlIlI && Utils.mc.field_1761.method_2923()) {
            return true;
        }
        if (lllllllllllllllllllllIlllIlIlIIl && Utils.mc.field_1724.method_6115() && (Utils.mc.field_1724.method_6047().method_7909().method_19263() || Utils.mc.field_1724.method_6079().method_7909().method_19263())) {
            return true;
        }
        return lllllllllllllllllllllIlllIlIlIll && Utils.mc.field_1724.method_6115() && (Utils.mc.field_1724.method_6047().method_7909() instanceof class_1812 || Utils.mc.field_1724.method_6079().method_7909() instanceof class_1812);
    }

    public static void centerPlayer() {
        double lllllllllllllllllllllIllllIlIlIl = (double)class_3532.method_15357((double)Utils.mc.field_1724.method_23317()) + 0.5;
        double lllllllllllllllllllllIllllIlIlII = (double)class_3532.method_15357((double)Utils.mc.field_1724.method_23321()) + 0.5;
        Utils.mc.field_1724.method_5814(lllllllllllllllllllllIllllIlIlIl, Utils.mc.field_1724.method_23318(), lllllllllllllllllllllIllllIlIlII);
        Utils.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(Utils.mc.field_1724.method_23317(), Utils.mc.field_1724.method_23318(), Utils.mc.field_1724.method_23321(), Utils.mc.field_1724.method_24828()));
    }

    public static double distanceTo(class_2338 lllllllllllllllllllllIllIllIlIll) {
        return PlayerUtils.distanceTo(lllllllllllllllllllllIllIllIlIll.method_10263(), lllllllllllllllllllllIllIllIlIll.method_10264(), lllllllllllllllllllllIllIllIlIll.method_10260());
    }
}

