/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 */
package minegame159.meteorclient.utils.bedtrap;

import java.util.ArrayList;
import minegame159.meteorclient.mixin.AbstractBlockAccessor;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerEntity;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerManager;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_310;

public class CityUtils {
    private static final /* synthetic */ class_310 mc;
    private static final /* synthetic */ class_2338[] surround;

    private static ArrayList<class_2338> getTargetSurround(class_1657 lllllllllllllllllIIIIllIllllIlll) {
        ArrayList<class_2338> lllllllllllllllllIIIIllIlllllIIl = new ArrayList<class_2338>();
        boolean lllllllllllllllllIIIIllIlllllIII = false;
        for (int lllllllllllllllllIIIIllIlllllIll = 0; lllllllllllllllllIIIIllIlllllIll < 4; ++lllllllllllllllllIIIIllIlllllIll) {
            class_2338 lllllllllllllllllIIIIllIllllllII;
            if (lllllllllllllllllIIIIllIllllIlll == null || (lllllllllllllllllIIIIllIllllllII = CityUtils.getSurround((class_1297)lllllllllllllllllIIIIllIllllIlll, surround[lllllllllllllllllIIIIllIlllllIll])) == null) continue;
            assert (CityUtils.mc.field_1687 != null);
            if (CityUtils.mc.field_1687.method_8320(lllllllllllllllllIIIIllIllllllII) == null) continue;
            if (!((AbstractBlockAccessor)CityUtils.mc.field_1687.method_8320(lllllllllllllllllIIIIllIllllllII).method_26204()).isCollidable()) {
                lllllllllllllllllIIIIllIlllllIII = true;
            }
            if (CityUtils.mc.field_1687.method_8320(lllllllllllllllllIIIIllIllllllII).method_26204() != class_2246.field_10540) continue;
            lllllllllllllllllIIIIllIlllllIIl.add(lllllllllllllllllIIIIllIllllllII);
        }
        if (lllllllllllllllllIIIIllIlllllIII) {
            return null;
        }
        return lllllllllllllllllIIIIllIlllllIIl;
    }

    public CityUtils() {
        CityUtils lllllllllllllllllIIIIlllIIIlllll;
    }

    public static class_2338 getTargetBlock(class_1657 lllllllllllllllllIIIIlllIIIIIlll) {
        class_2338 lllllllllllllllllIIIIlllIIIIlIlI = null;
        ArrayList<class_2338> lllllllllllllllllIIIIlllIIIIlIIl = CityUtils.getTargetSurround(lllllllllllllllllIIIIlllIIIIIlll);
        ArrayList<class_2338> lllllllllllllllllIIIIlllIIIIlIII = CityUtils.getTargetSurround((class_1657)CityUtils.mc.field_1724);
        if (lllllllllllllllllIIIIlllIIIIlIIl == null) {
            return null;
        }
        for (class_2338 lllllllllllllllllIIIIlllIIIIllII : lllllllllllllllllIIIIlllIIIIlIIl) {
            if (lllllllllllllllllIIIIlllIIIIlIII != null && !lllllllllllllllllIIIIlllIIIIlIII.isEmpty() && lllllllllllllllllIIIIlllIIIIlIII.contains((Object)lllllllllllllllllIIIIlllIIIIllII)) continue;
            if (lllllllllllllllllIIIIlllIIIIlIlI == null) {
                lllllllllllllllllIIIIlllIIIIlIlI = lllllllllllllllllIIIIlllIIIIllII;
                continue;
            }
            if (!(CityUtils.mc.field_1724.method_5707(Utils.vec3d(lllllllllllllllllIIIIlllIIIIllII)) < CityUtils.mc.field_1724.method_5707(Utils.vec3d(lllllllllllllllllIIIIlllIIIIlIlI)))) continue;
            lllllllllllllllllIIIIlllIIIIlIlI = lllllllllllllllllIIIIlllIIIIllII;
        }
        return lllllllllllllllllIIIIlllIIIIlIlI;
    }

    public static class_2338 getSurround(class_1297 lllllllllllllllllIIIIllIlllIllll, class_2338 lllllllllllllllllIIIIllIlllIlIll) {
        class_243 lllllllllllllllllIIIIllIlllIllIl = lllllllllllllllllIIIIllIlllIllll.method_19538();
        if (lllllllllllllllllIIIIllIlllIlIll == null) {
            return new class_2338(lllllllllllllllllIIIIllIlllIllIl.field_1352, lllllllllllllllllIIIIllIlllIllIl.field_1351, lllllllllllllllllIIIIllIlllIllIl.field_1350);
        }
        return new class_2338(lllllllllllllllllIIIIllIlllIllIl.field_1352, lllllllllllllllllIIIIllIlllIllIl.field_1351, lllllllllllllllllIIIIllIlllIllIl.field_1350).method_10081((class_2382)lllllllllllllllllIIIIllIlllIlIll);
    }

    public static class_1657 getPlayerTarget(double lllllllllllllllllIIIIlllIIIllIII) {
        if (CityUtils.mc.field_1724.method_29504()) {
            return null;
        }
        Object lllllllllllllllllIIIIlllIIIlIlll = null;
        for (class_1657 lllllllllllllllllIIIIlllIIIllIlI : CityUtils.mc.field_1687.method_18456()) {
            if (lllllllllllllllllIIIIlllIIIllIlI == CityUtils.mc.field_1724 || lllllllllllllllllIIIIlllIIIllIlI.method_29504() || !Friends.get().shouldAttack(lllllllllllllllllIIIIlllIIIllIlI) || (double)CityUtils.mc.field_1724.method_5739((class_1297)lllllllllllllllllIIIIlllIIIllIlI) > lllllllllllllllllIIIIlllIIIllIII) continue;
            if (lllllllllllllllllIIIIlllIIIlIlll == null) {
                lllllllllllllllllIIIIlllIIIlIlll = lllllllllllllllllIIIIlllIIIllIlI;
                continue;
            }
            if (!(CityUtils.mc.field_1724.method_5739((class_1297)lllllllllllllllllIIIIlllIIIllIlI) < CityUtils.mc.field_1724.method_5739((class_1297)lllllllllllllllllIIIIlllIIIlIlll))) continue;
            lllllllllllllllllIIIIlllIIIlIlll = lllllllllllllllllIIIIlllIIIllIlI;
        }
        if (lllllllllllllllllIIIIlllIIIlIlll == null) {
            for (FakePlayerEntity lllllllllllllllllIIIIlllIIIllIIl : FakePlayerManager.getPlayers()) {
                if (lllllllllllllllllIIIIlllIIIllIIl.method_29504() || !Friends.get().shouldAttack((class_1657)lllllllllllllllllIIIIlllIIIllIIl) || (double)CityUtils.mc.field_1724.method_5739((class_1297)lllllllllllllllllIIIIlllIIIllIIl) > lllllllllllllllllIIIIlllIIIllIII) continue;
                if (lllllllllllllllllIIIIlllIIIlIlll == null) {
                    lllllllllllllllllIIIIlllIIIlIlll = lllllllllllllllllIIIIlllIIIllIIl;
                    continue;
                }
                if (!(CityUtils.mc.field_1724.method_5739((class_1297)lllllllllllllllllIIIIlllIIIllIIl) < CityUtils.mc.field_1724.method_5739((class_1297)lllllllllllllllllIIIIlllIIIlIlll))) continue;
                lllllllllllllllllIIIIlllIIIlIlll = lllllllllllllllllIIIIlllIIIllIIl;
            }
        }
        return lllllllllllllllllIIIIlllIIIlIlll;
    }

    static {
        mc = class_310.method_1551();
        surround = new class_2338[]{new class_2338(0, 0, -1), new class_2338(1, 0, 0), new class_2338(0, 0, 1), new class_2338(-1, 0, 0)};
    }
}

