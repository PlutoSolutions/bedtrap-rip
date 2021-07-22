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
    private int x;
    private static final double SQRT_2 = Math.sqrt(2.0);
    private int z;
    private final float yaw;

    public int getZ() {
        return this.z;
    }

    public static double calculate(double d, double d2) {
        double d3;
        double d4;
        double d5;
        double d6 = Math.abs(d);
        if (d6 < (d5 = Math.abs(d2))) {
            d4 = d5 - d6;
            d3 = d6;
        } else {
            d4 = d6 - d5;
            d3 = d5;
        }
        return ((d3 *= SQRT_2) + d4) * (Double)BaritoneAPI.getSettings().costHeuristic.value;
    }

    public int getX() {
        return this.x;
    }

    public void recalculate(class_243 class_2432) {
        float f = (float)Math.toRadians(this.yaw);
        this.x = (int)Math.floor(class_2432.field_1352 - (double)class_3532.method_15374((float)f) * 100.0);
        this.z = (int)Math.floor(class_2432.field_1350 + (double)class_3532.method_15362((float)f) * 100.0);
    }

    public GoalDirection(class_243 class_2432, float f) {
        this.yaw = f;
        this.recalculate(class_2432);
    }

    public boolean isInGoal(int n, int n2, int n3) {
        return n == this.x && n3 == this.z;
    }

    public String toString() {
        return String.format("GoalXZ{x=%s,z=%s}", SettingsUtil.maybeCensor((int)this.x), SettingsUtil.maybeCensor((int)this.z));
    }

    public double heuristic(int n, int n2, int n3) {
        int n4 = n - this.x;
        int n5 = n3 - this.z;
        return GoalDirection.calculate(n4, n5);
    }
}

