/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  baritone.api.pathing.goals.Goal
 *  baritone.api.utils.SettingsUtil
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package minegame159.meteorclient.utils.world;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.Goal;
import baritone.api.utils.SettingsUtil;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class GoalDirection
implements Goal {
    private /* synthetic */ int x;
    private static final /* synthetic */ double SQRT_2;
    private /* synthetic */ int z;
    private final /* synthetic */ float yaw;

    static {
        SQRT_2 = Math.sqrt(2.0);
    }

    public int getZ() {
        GoalDirection lIIlIIllIIlIII;
        return lIIlIIllIIlIII.z;
    }

    public static double calculate(double lIIlIIlllllIII, double lIIlIIllllIlll) {
        double lIIlIIlllllIlI;
        double lIIlIIlllllIll;
        double lIIlIIllllllII = Math.abs(lIIlIIlllllIII);
        if (lIIlIIllllllII < (lIIlIIlllllIll = Math.abs(lIIlIIllllIlll))) {
            double lIIlIlIIIIIIII = lIIlIIlllllIll - lIIlIIllllllII;
            double lIIlIIllllllll = lIIlIIllllllII;
        } else {
            lIIlIIlllllIlI = lIIlIIllllllII - lIIlIIlllllIll;
            double lIIlIIlllllIIl = lIIlIIlllllIll;
        }
        return ((lIIlIIlllllIIl *= SQRT_2) + lIIlIIlllllIlI) * (Double)BaritoneAPI.getSettings().costHeuristic.value;
    }

    public int getX() {
        GoalDirection lIIlIIllIIlIll;
        return lIIlIIllIIlIll.x;
    }

    public void recalculate(class_243 lIIlIIlllIlIll) {
        GoalDirection lIIlIIlllIllll;
        float lIIlIIlllIllIl = (float)Math.toRadians(lIIlIIlllIllll.yaw);
        lIIlIIlllIllll.x = (int)Math.floor(lIIlIIlllIlIll.field_1352 - (double)class_3532.method_15374((float)lIIlIIlllIllIl) * 100.0);
        lIIlIIlllIllll.z = (int)Math.floor(lIIlIIlllIlIll.field_1350 + (double)class_3532.method_15362((float)lIIlIIlllIllIl) * 100.0);
    }

    public GoalDirection(class_243 lIIlIlIIIIlIll, float lIIlIlIIIIlIlI) {
        GoalDirection lIIlIlIIIIllII;
        lIIlIlIIIIllII.yaw = lIIlIlIIIIlIlI;
        lIIlIlIIIIllII.recalculate(lIIlIlIIIIlIll);
    }

    public boolean isInGoal(int lIIlIIlllIIIIl, int lIIlIIlllIIlII, int lIIlIIlllIIIII) {
        GoalDirection lIIlIIlllIIllI;
        return lIIlIIlllIIIIl == lIIlIIlllIIllI.x && lIIlIIlllIIIII == lIIlIIlllIIllI.z;
    }

    public String toString() {
        GoalDirection lIIlIIllIIllIl;
        return String.format("GoalXZ{x=%s,z=%s}", SettingsUtil.maybeCensor((int)lIIlIIllIIllIl.x), SettingsUtil.maybeCensor((int)lIIlIIllIIllIl.z));
    }

    public double heuristic(int lIIlIIllIllIIl, int lIIlIIllIllIII, int lIIlIIllIlIlll) {
        GoalDirection lIIlIIllIlIlII;
        int lIIlIIllIlIllI = lIIlIIllIllIIl - lIIlIIllIlIlII.x;
        int lIIlIIllIlIlIl = lIIlIIllIlIlll - lIIlIIllIlIlII.z;
        return GoalDirection.calculate(lIIlIIllIlIllI, lIIlIIllIlIlIl);
    }
}

