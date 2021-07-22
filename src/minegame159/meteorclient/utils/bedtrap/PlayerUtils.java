/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  baritone.api.utils.Rotation
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1812
 *  net.minecraft.class_1829
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2586
 *  net.minecraft.class_2587
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.utils.bedtrap;

import baritone.api.BaritoneAPI;
import baritone.api.utils.Rotation;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.NoFall;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.misc.BaritoneUtils;
import minegame159.meteorclient.utils.misc.Vector2;
import minegame159.meteorclient.utils.player.DamageCalcUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1812;
import net.minecraft.class_1829;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_2587;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2828;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class PlayerUtils {
    private static final /* synthetic */ class_243 hitPos;
    private static final /* synthetic */ class_310 mc;
    private static final /* synthetic */ double diagonal;
    private static final /* synthetic */ class_243 horizontalVelocity;

    public static boolean isSprinting() {
        return PlayerUtils.mc.field_1724.method_5624() && (PlayerUtils.mc.field_1724.field_6250 != 0.0f || PlayerUtils.mc.field_1724.field_6212 != 0.0f);
    }

    public static double distanceTo(class_1297 lllllllIIlIlI) {
        return PlayerUtils.distanceTo(lllllllIIlIlI.method_23317(), lllllllIIlIlI.method_23318(), lllllllIIlIlI.method_23321());
    }

    public static boolean placeBlock(class_2338 lIIIIIlIIlIlIl, class_1268 lIIIIIlIIlIlII, boolean lIIIIIlIIlIIll) {
        return PlayerUtils.placeBlock(lIIIIIlIIlIlIl, lIIIIIlIIlIlII, true, lIIIIIlIIlIIll);
    }

    static {
        mc = class_310.method_1551();
        hitPos = new class_243(0.0, 0.0, 0.0);
        diagonal = 1.0 / Math.sqrt(2.0);
        horizontalVelocity = new class_243(0.0, 0.0, 0.0);
    }

    public static boolean placeBlock(class_2338 lIIIIIlIIIIllI, int lIIIIIlIIIIlIl, class_1268 lIIIIIlIIIlIlI, boolean lIIIIIlIIIlIIl) {
        if (lIIIIIlIIIIlIl == -1) {
            return false;
        }
        int lIIIIIlIIIlIII = PlayerUtils.mc.field_1724.field_7514.field_7545;
        PlayerUtils.mc.field_1724.field_7514.field_7545 = lIIIIIlIIIIlIl;
        boolean lIIIIIlIIIIlll = PlayerUtils.placeBlock(lIIIIIlIIIIllI, lIIIIIlIIIlIlI, true, lIIIIIlIIIlIIl);
        PlayerUtils.mc.field_1724.field_7514.field_7545 = lIIIIIlIIIlIII;
        return lIIIIIlIIIIlll;
    }

    public static boolean shouldPause(boolean lIIIIIIIIlllII, boolean lIIIIIIIIllIII, boolean lIIIIIIIIlIlll) {
        if (lIIIIIIIIlllII && PlayerUtils.mc.field_1761.method_2923()) {
            return true;
        }
        if (lIIIIIIIIllIII && PlayerUtils.mc.field_1724.method_6115() && (PlayerUtils.mc.field_1724.method_6047().method_7909().method_19263() || PlayerUtils.mc.field_1724.method_6079().method_7909().method_19263())) {
            return true;
        }
        return lIIIIIIIIlIlll && PlayerUtils.mc.field_1724.method_6115() && (PlayerUtils.mc.field_1724.method_6047().method_7909() instanceof class_1812 || PlayerUtils.mc.field_1724.method_6079().method_7909() instanceof class_1812);
    }

    public static class_243 getHorizontalVelocity(double lIIIIIIlIIlllI) {
        Rotation lIIIIIIlIlIlll;
        float lIIIIIIlIlIlIl = PlayerUtils.mc.field_1724.field_6031;
        if (BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing() && (lIIIIIIlIlIlll = BaritoneUtils.getTarget()) != null) {
            lIIIIIIlIlIlIl = lIIIIIIlIlIlll.getYaw();
        }
        class_243 lIIIIIIlIlIlII = class_243.method_1030((float)0.0f, (float)lIIIIIIlIlIlIl);
        class_243 lIIIIIIlIlIIll = class_243.method_1030((float)0.0f, (float)(lIIIIIIlIlIlIl + 90.0f));
        double lIIIIIIlIlIIlI = 0.0;
        double lIIIIIIlIlIIIl = 0.0;
        boolean lIIIIIIlIlIIII = false;
        if (PlayerUtils.mc.field_1724.field_3913.field_3910) {
            lIIIIIIlIlIIlI += lIIIIIIlIlIlII.field_1352 / 20.0 * lIIIIIIlIIlllI;
            lIIIIIIlIlIIIl += lIIIIIIlIlIlII.field_1350 / 20.0 * lIIIIIIlIIlllI;
            lIIIIIIlIlIIII = true;
        }
        if (PlayerUtils.mc.field_1724.field_3913.field_3909) {
            lIIIIIIlIlIIlI -= lIIIIIIlIlIlII.field_1352 / 20.0 * lIIIIIIlIIlllI;
            lIIIIIIlIlIIIl -= lIIIIIIlIlIlII.field_1350 / 20.0 * lIIIIIIlIIlllI;
            lIIIIIIlIlIIII = true;
        }
        boolean lIIIIIIlIIllll = false;
        if (PlayerUtils.mc.field_1724.field_3913.field_3906) {
            lIIIIIIlIlIIlI += lIIIIIIlIlIIll.field_1352 / 20.0 * lIIIIIIlIIlllI;
            lIIIIIIlIlIIIl += lIIIIIIlIlIIll.field_1350 / 20.0 * lIIIIIIlIIlllI;
            lIIIIIIlIIllll = true;
        }
        if (PlayerUtils.mc.field_1724.field_3913.field_3908) {
            lIIIIIIlIlIIlI -= lIIIIIIlIlIIll.field_1352 / 20.0 * lIIIIIIlIIlllI;
            lIIIIIIlIlIIIl -= lIIIIIIlIlIIll.field_1350 / 20.0 * lIIIIIIlIIlllI;
            lIIIIIIlIIllll = true;
        }
        if (lIIIIIIlIlIIII && lIIIIIIlIIllll) {
            lIIIIIIlIlIIlI *= diagonal;
            lIIIIIIlIlIIIl *= diagonal;
        }
        ((IVec3d)horizontalVelocity).setXZ(lIIIIIIlIlIIlI, lIIIIIIlIlIIIl);
        return horizontalVelocity;
    }

    public static double possibleHealthReductions() {
        return PlayerUtils.possibleHealthReductions(true, true);
    }

    public static double possibleHealthReductions(boolean lllllllIlIIll, boolean lllllllIlIIlI) {
        double lllllllIlIlII;
        double lllllllIlIIIl = 0.0;
        for (class_1297 lllllllIllIII : PlayerUtils.mc.field_1687.method_18112()) {
            if (lllllllIllIII instanceof class_1511 && lllllllIlIIIl < DamageCalcUtils.crystalDamage((class_1309)PlayerUtils.mc.field_1724, lllllllIllIII.method_19538())) {
                lllllllIlIIIl = DamageCalcUtils.crystalDamage((class_1309)PlayerUtils.mc.field_1724, lllllllIllIII.method_19538());
                continue;
            }
            if (!(lllllllIllIII instanceof class_1657) || !(lllllllIlIIIl < DamageCalcUtils.getSwordDamage((class_1657)lllllllIllIII, true)) || !Friends.get().isFriend((class_1657)lllllllIllIII) || !(PlayerUtils.mc.field_1724.method_19538().method_1022(lllllllIllIII.method_19538()) < 5.0) || !(((class_1657)lllllllIllIII).method_6030().method_7909() instanceof class_1829)) continue;
            lllllllIlIIIl = DamageCalcUtils.getSwordDamage((class_1657)lllllllIllIII, true);
        }
        if (minegame159.meteorclient.utils.bedtrap.Utils.getDimension() != Dimension.Overworld) {
            for (class_2586 lllllllIlIlIl : PlayerUtils.mc.field_1687.field_9231) {
                class_2338 lllllllIlIlll = lllllllIlIlIl.method_11016();
                class_243 lllllllIlIllI = new class_243((double)lllllllIlIlll.method_10263(), (double)lllllllIlIlll.method_10264(), (double)lllllllIlIlll.method_10260());
                if (!(lllllllIlIlIl instanceof class_2587) || !(lllllllIlIIIl < DamageCalcUtils.bedDamage((class_1309)PlayerUtils.mc.field_1724, lllllllIlIllI))) continue;
                lllllllIlIIIl = DamageCalcUtils.bedDamage((class_1309)PlayerUtils.mc.field_1724, lllllllIlIllI);
            }
        }
        if (!Modules.get().isActive(NoFall.class) && PlayerUtils.mc.field_1724.field_6017 > 3.0f && (lllllllIlIlII = (double)PlayerUtils.mc.field_1724.field_6017 * 0.5) > lllllllIlIIIl && !EntityUtils.isAboveWater((class_1297)PlayerUtils.mc.field_1724)) {
            lllllllIlIIIl = lllllllIlIlII;
        }
        return lllllllIlIIIl;
    }

    public static float[] calculateAngle(class_243 lIIIIIIIlIlIll) {
        class_243 lIIIIIIIlIlIlI = new class_243(PlayerUtils.mc.field_1724.method_23317(), PlayerUtils.mc.field_1724.method_23318() + (double)PlayerUtils.mc.field_1724.method_18381(PlayerUtils.mc.field_1724.method_18376()), PlayerUtils.mc.field_1724.method_23321());
        double lIIIIIIIlIlIIl = lIIIIIIIlIlIll.field_1352 - lIIIIIIIlIlIlI.field_1352;
        double lIIIIIIIlIlIII = (lIIIIIIIlIlIll.field_1351 - lIIIIIIIlIlIlI.field_1351) * -1.0;
        double lIIIIIIIlIIlll = lIIIIIIIlIlIll.field_1350 - lIIIIIIIlIlIlI.field_1350;
        double lIIIIIIIlIIllI = class_3532.method_15368((double)(lIIIIIIIlIlIIl * lIIIIIIIlIlIIl + lIIIIIIIlIIlll * lIIIIIIIlIIlll));
        return new float[]{(float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(lIIIIIIIlIIlll, lIIIIIIIlIlIIl)) - 90.0)), (float)class_3532.method_15338((double)Math.toDegrees(Math.atan2(lIIIIIIIlIlIII, lIIIIIIIlIIllI)))};
    }

    public static boolean isInHole(boolean llllllllIlIlI) {
        if (!Utils.canUpdate()) {
            return false;
        }
        class_2338 llllllllIllII = PlayerUtils.mc.field_1724.method_24515();
        int llllllllIlIll = 0;
        for (class_2350 llllllllIlllI : class_2350.values()) {
            class_2680 llllllllIllll;
            if (llllllllIlllI == class_2350.field_11036 || (llllllllIllll = PlayerUtils.mc.field_1687.method_8320(llllllllIllII.method_10093(llllllllIlllI))).method_26204() == class_2246.field_9987 || llllllllIllll.method_26204() == class_2246.field_10540) continue;
            if (!llllllllIlIlI || llllllllIlllI == class_2350.field_11033) {
                return false;
            }
            ++llllllllIlIll;
            for (class_2350 lllllllllIIII : class_2350.values()) {
                class_2680 lllllllllIIIl;
                if (lllllllllIIII == llllllllIlllI.method_10153() || lllllllllIIII == class_2350.field_11036 || (lllllllllIIIl = PlayerUtils.mc.field_1687.method_8320(llllllllIllII.method_10093(llllllllIlllI).method_10093(lllllllllIIII))).method_26204() == class_2246.field_9987 || lllllllllIIIl.method_26204() == class_2246.field_10540) continue;
                return false;
            }
        }
        return llllllllIlIll < 2;
    }

    public static double distanceTo(class_2338 lllllllIIIllI) {
        return PlayerUtils.distanceTo(lllllllIIIllI.method_10263(), lllllllIIIllI.method_10264(), lllllllIIIllI.method_10260());
    }

    public static boolean canSeeEntity(class_1297 lIIIIIIIllIllI) {
        class_243 lIIIIIIIlllIlI = new class_243(0.0, 0.0, 0.0);
        class_243 lIIIIIIIlllIIl = new class_243(0.0, 0.0, 0.0);
        ((IVec3d)lIIIIIIIlllIlI).set(PlayerUtils.mc.field_1724.method_23317(), PlayerUtils.mc.field_1724.method_23318() + (double)PlayerUtils.mc.field_1724.method_5751(), PlayerUtils.mc.field_1724.method_23321());
        ((IVec3d)lIIIIIIIlllIIl).set(lIIIIIIIllIllI.method_23317(), lIIIIIIIllIllI.method_23318(), lIIIIIIIllIllI.method_23321());
        boolean lIIIIIIIlllIII = PlayerUtils.mc.field_1687.method_17742(new class_3959(lIIIIIIIlllIlI, lIIIIIIIlllIIl, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)PlayerUtils.mc.field_1724)).method_17783() == class_239.class_240.field_1333;
        ((IVec3d)lIIIIIIIlllIIl).set(lIIIIIIIllIllI.method_23317(), lIIIIIIIllIllI.method_23318() + (double)lIIIIIIIllIllI.method_5751(), lIIIIIIIllIllI.method_23321());
        boolean lIIIIIIIllIlll = PlayerUtils.mc.field_1687.method_17742(new class_3959(lIIIIIIIlllIlI, lIIIIIIIlllIIl, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)PlayerUtils.mc.field_1724)).method_17783() == class_239.class_240.field_1333;
        return lIIIIIIIlllIII || lIIIIIIIllIlll;
    }

    public static boolean isMoving() {
        return PlayerUtils.mc.field_1724.field_6250 != 0.0f || PlayerUtils.mc.field_1724.field_6212 != 0.0f;
    }

    public PlayerUtils() {
        PlayerUtils lIIIIIlIIlllIl;
    }

    public static double distanceTo(double llllllIllllll, double llllllIlllllI, double llllllIllllIl) {
        float llllllIllllII = (float)(PlayerUtils.mc.field_1724.method_23317() - llllllIllllll);
        float llllllIlllIll = (float)(PlayerUtils.mc.field_1724.method_23318() - llllllIlllllI);
        float llllllIlllIlI = (float)(PlayerUtils.mc.field_1724.method_23321() - llllllIllllIl);
        return class_3532.method_15355((float)(llllllIllllII * llllllIllllII + llllllIlllIll * llllllIlllIll + llllllIlllIlI * llllllIlllIlI));
    }

    public static boolean placeBlock(class_2338 lIIIIIIllIlllI, class_1268 lIIIIIIllIllIl, boolean lIIIIIIllIllII, boolean lIIIIIIllIlIll) {
        if (!BlockUtils.canPlace(lIIIIIIllIlllI)) {
            return false;
        }
        for (class_2350 lIIIIIIlllIlII : class_2350.values()) {
            class_2338 lIIIIIIlllIIll = lIIIIIIllIlllI.method_10093(lIIIIIIlllIlII);
            class_2350 lIIIIIIlllIIlI = lIIIIIIlllIlII.method_10153();
            if (PlayerUtils.mc.field_1687.method_8320(lIIIIIIlllIIll).method_26215() || BlockUtils.isClickable(PlayerUtils.mc.field_1687.method_8320(lIIIIIIlllIIll).method_26204())) continue;
            ((IVec3d)hitPos).set((double)lIIIIIIlllIIll.method_10263() + 0.5 + (double)lIIIIIIlllIIlI.method_10163().method_10263() * 0.5, (double)lIIIIIIlllIIll.method_10264() + 0.5 + (double)lIIIIIIlllIIlI.method_10163().method_10264() * 0.5, (double)lIIIIIIlllIIll.method_10260() + 0.5 + (double)lIIIIIIlllIIlI.method_10163().method_10260() * 0.5);
            boolean lIIIIIIlllIlIl = PlayerUtils.mc.field_1724.field_3913.field_3903;
            PlayerUtils.mc.field_1724.field_3913.field_3903 = false;
            PlayerUtils.mc.field_1761.method_2896(PlayerUtils.mc.field_1724, PlayerUtils.mc.field_1687, lIIIIIIllIllIl, new class_3965(hitPos, lIIIIIIlllIIlI, lIIIIIIlllIIll, false));
            if (lIIIIIIllIllII) {
                PlayerUtils.mc.field_1724.method_6104(lIIIIIIllIllIl);
            }
            PlayerUtils.mc.field_1724.field_3913.field_3903 = lIIIIIIlllIlIl;
            return true;
        }
        if (!lIIIIIIllIlIll) {
            return false;
        }
        ((IVec3d)hitPos).set((class_2382)lIIIIIIllIlllI);
        PlayerUtils.mc.field_1761.method_2896(PlayerUtils.mc.field_1724, PlayerUtils.mc.field_1687, lIIIIIIllIllIl, new class_3965(hitPos, class_2350.field_11036, lIIIIIIllIlllI, false));
        if (lIIIIIIllIllII) {
            PlayerUtils.mc.field_1724.method_6104(lIIIIIIllIllIl);
        }
        return true;
    }

    public static void centerPlayer() {
        double lIIIIIIlIIIlII = (double)class_3532.method_15357((double)PlayerUtils.mc.field_1724.method_23317()) + 0.5;
        double lIIIIIIlIIIIll = (double)class_3532.method_15357((double)PlayerUtils.mc.field_1724.method_23321()) + 0.5;
        PlayerUtils.mc.field_1724.method_5814(lIIIIIIlIIIlII, PlayerUtils.mc.field_1724.method_23318(), lIIIIIIlIIIIll);
        PlayerUtils.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(PlayerUtils.mc.field_1724.method_23317(), PlayerUtils.mc.field_1724.method_23318(), PlayerUtils.mc.field_1724.method_23321(), PlayerUtils.mc.field_1724.method_24828()));
    }

    public static Vector2 transformStrafe(double lIIIIIIIIIlllI) {
        float lIIIIIIIIIllIl = PlayerUtils.mc.field_1724.field_3913.field_3905;
        float lIIIIIIIIIllII = PlayerUtils.mc.field_1724.field_3913.field_3907;
        float lIIIIIIIIIlIll = PlayerUtils.mc.field_1724.field_5982 + (PlayerUtils.mc.field_1724.field_6031 - PlayerUtils.mc.field_1724.field_5982) * mc.method_1488();
        if (lIIIIIIIIIllIl == 0.0f && lIIIIIIIIIllII == 0.0f) {
            return new Vector2(0.0, 0.0);
        }
        if (lIIIIIIIIIllIl != 0.0f) {
            if (lIIIIIIIIIllII >= 1.0f) {
                lIIIIIIIIIlIll += (float)(lIIIIIIIIIllIl > 0.0f ? -45 : 45);
                lIIIIIIIIIllII = 0.0f;
            } else if (lIIIIIIIIIllII <= -1.0f) {
                lIIIIIIIIIlIll += (float)(lIIIIIIIIIllIl > 0.0f ? 45 : -45);
                lIIIIIIIIIllII = 0.0f;
            }
            if (lIIIIIIIIIllIl > 0.0f) {
                lIIIIIIIIIllIl = 1.0f;
            } else if (lIIIIIIIIIllIl < 0.0f) {
                lIIIIIIIIIllIl = -1.0f;
            }
        }
        double lIIIIIIIIIlIII = Math.cos(Math.toRadians(lIIIIIIIIIlIll + 90.0f));
        double lIIIIIIIIIIlll = Math.sin(Math.toRadians(lIIIIIIIIIlIll + 90.0f));
        double lIIIIIIIIIlIlI = (double)lIIIIIIIIIllIl * lIIIIIIIIIlllI * lIIIIIIIIIlIII + (double)lIIIIIIIIIllII * lIIIIIIIIIlllI * lIIIIIIIIIIlll;
        double lIIIIIIIIIlIIl = (double)lIIIIIIIIIllIl * lIIIIIIIIIlllI * lIIIIIIIIIIlll - (double)lIIIIIIIIIllII * lIIIIIIIIIlllI * lIIIIIIIIIlIII;
        return new Vector2(lIIIIIIIIIlIlI, lIIIIIIIIIlIIl);
    }
}

