/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1158
 *  net.minecraft.class_1160
 *  net.minecraft.class_1297
 *  net.minecraft.class_1675
 *  net.minecraft.class_1753
 *  net.minecraft.class_1764
 *  net.minecraft.class_1771
 *  net.minecraft.class_1776
 *  net.minecraft.class_1779
 *  net.minecraft.class_1787
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1823
 *  net.minecraft.class_1835
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_238
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_3610
 *  net.minecraft.class_3612
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 *  net.minecraft.class_4537
 */
package minegame159.meteorclient.utils.entity;

import minegame159.meteorclient.mixin.CrossbowItemAccessor;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.MissHitResult;
import minegame159.meteorclient.utils.misc.Vec3;
import net.minecraft.class_1158;
import net.minecraft.class_1160;
import net.minecraft.class_1297;
import net.minecraft.class_1675;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1771;
import net.minecraft.class_1776;
import net.minecraft.class_1779;
import net.minecraft.class_1787;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1823;
import net.minecraft.class_1835;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_4537;

public class ProjectileEntitySimulator {
    private static final class_243 prevPos3d;
    private double airDrag;
    public final Vec3 pos = new Vec3();
    private double gravity;
    private final Vec3 velocity = new Vec3();
    private static final class_243 pos3d;
    private double waterDrag;
    private static final class_2338.class_2339 blockPos;

    private static boolean lambda$getCollision$0(class_1297 class_12972) {
        return !class_12972.method_7325() && class_12972.method_5805() && class_12972.method_5863();
    }

    static {
        blockPos = new class_2338.class_2339();
        pos3d = new class_243(0.0, 0.0, 0.0);
        prevPos3d = new class_243(0.0, 0.0, 0.0);
    }

    public boolean set(class_1297 class_12972, class_1799 class_17992, double d, boolean bl, double d2) {
        class_1792 class_17922 = class_17992.method_7909();
        if (class_17922 instanceof class_1753) {
            double d3 = class_1753.method_7722((int)Utils.mc.field_1724.method_6048());
            if (d3 <= 0.0) {
                return false;
            }
            this.set(class_12972, 0.0, d3 * 3.0, d, 0.05f, 0.6, bl, d2);
        } else if (class_17922 instanceof class_1764) {
            if (!class_1764.method_7781((class_1799)class_17992)) {
                return false;
            }
            this.set(class_12972, 0.0, CrossbowItemAccessor.getSpeed(class_17992), d, 0.05f, 0.6, bl, d2);
        } else if (class_17922 instanceof class_1787) {
            this.setFishingBobber(class_12972, d2);
        } else if (class_17922 instanceof class_1835) {
            this.set(class_12972, 0.0, 2.5, d, 0.05f, 0.99, bl, d2);
        } else if (class_17922 instanceof class_1823 || class_17922 instanceof class_1771 || class_17922 instanceof class_1776) {
            this.set(class_12972, 0.0, 1.5, d, 0.03, 0.8, bl, d2);
        } else if (class_17922 instanceof class_1779) {
            this.set(class_12972, -20.0, 0.7, d, 0.07, 0.8, bl, d2);
        } else if (class_17922 instanceof class_4537) {
            this.set(class_12972, -20.0, 0.5, d, 0.05, 0.8, bl, d2);
        } else {
            return false;
        }
        return true;
    }

    public void setFishingBobber(class_1297 class_12972, double d) {
        double d2 = class_3532.method_16436((double)d, (double)class_12972.field_5982, (double)class_12972.field_6031);
        double d3 = class_3532.method_16436((double)d, (double)class_12972.field_6004, (double)class_12972.field_5965);
        double d4 = Math.cos(-d2 * 0.01745329238474369 - 3.1415927410125732);
        double d5 = Math.sin(-d2 * 0.01745329238474369 - 3.1415927410125732);
        double d6 = -Math.cos(-d3 * 0.01745329238474369);
        double d7 = Math.sin(-d3 * 0.01745329238474369);
        this.pos.set(class_12972, d).subtract(d5 * 0.3, 0.0, d4 * 0.3).add(0.0, class_12972.method_18381(class_12972.method_18376()), 0.0);
        this.velocity.set(-d5, Utils.clamp(-(d7 / d6), -5.0, 5.0), -d4);
        double d8 = this.velocity.length();
        this.velocity.multiply(0.6 / d8 + 0.5, 0.6 / d8 + 0.5, 0.6 / d8 + 0.5);
        this.gravity = 0.03;
        this.airDrag = 0.92;
        this.waterDrag = 0.0;
    }

    private boolean isTouchingWater() {
        blockPos.method_10102(this.pos.x, this.pos.y, this.pos.z);
        class_3610 class_36102 = Utils.mc.field_1687.method_8316((class_2338)blockPos);
        if (class_36102.method_15772() != class_3612.field_15910 && class_36102.method_15772() != class_3612.field_15909) {
            return false;
        }
        return this.pos.y - (double)((int)this.pos.y) <= (double)class_36102.method_20785();
    }

    public class_239 tick() {
        ((IVec3d)prevPos3d).set(this.pos);
        this.pos.add(this.velocity);
        this.velocity.multiply(this.isTouchingWater() ? this.waterDrag : this.airDrag);
        this.velocity.subtract(0.0, this.gravity, 0.0);
        if (this.pos.y < 0.0) {
            return MissHitResult.INSTANCE;
        }
        int n = (int)(this.pos.x / 16.0);
        int n2 = (int)(this.pos.z / 16.0);
        if (!Utils.mc.field_1687.method_2935().method_12123(n, n2)) {
            return MissHitResult.INSTANCE;
        }
        ((IVec3d)pos3d).set(this.pos);
        class_239 class_2392 = this.getCollision();
        return class_2392.method_17783() == class_239.class_240.field_1333 ? null : class_2392;
    }

    private class_239 getCollision() {
        class_3966 class_39662;
        class_243 class_2432 = prevPos3d;
        class_3965 class_39652 = Utils.mc.field_1687.method_17742(new class_3959(class_2432, pos3d, class_3959.class_3960.field_17558, this.waterDrag == 0.0 ? class_3959.class_242.field_1347 : class_3959.class_242.field_1348, (class_1297)Utils.mc.field_1724));
        if (class_39652.method_17783() != class_239.class_240.field_1333) {
            class_2432 = class_39652.method_17784();
        }
        if ((class_39662 = class_1675.method_18077((class_1937)Utils.mc.field_1687, (class_1297)Utils.mc.field_1724, (class_243)class_2432, (class_243)pos3d, (class_238)new class_238(this.pos.x, this.pos.y, this.pos.z, this.pos.x, this.pos.y, this.pos.z).method_18804(Utils.mc.field_1724.method_18798()).method_1014(1.0), ProjectileEntitySimulator::lambda$getCollision$0)) != null) {
            class_39652 = class_39662;
        }
        return class_39652;
    }

    public void set(class_1297 class_12972, double d, double d2, double d3, double d4, double d5, boolean bl, double d6) {
        class_243 class_2432;
        double d7;
        double d8;
        double d9;
        this.pos.set(class_12972, d6).add(0.0, class_12972.method_18381(class_12972.method_18376()), 0.0);
        double d10 = class_3532.method_16436((double)d6, (double)class_12972.field_5982, (double)class_12972.field_6031);
        double d11 = class_3532.method_16436((double)d6, (double)class_12972.field_6004, (double)class_12972.field_5965);
        if (d3 == 0.0) {
            d9 = -Math.sin(d10 * 0.017453292) * Math.cos(d11 * 0.017453292);
            d8 = -Math.sin((d11 + d) * 0.017453292);
            d7 = Math.cos(d10 * 0.017453292) * Math.cos(d11 * 0.017453292);
        } else {
            class_2432 = class_12972.method_18864(1.0f);
            class_1158 class_11582 = new class_1158(new class_1160(class_2432), (float)d3, true);
            class_243 class_2433 = class_12972.method_5828(1.0f);
            class_1160 class_11602 = new class_1160(class_2433);
            class_11602.method_19262(class_11582);
            d9 = class_11602.method_4943();
            d8 = class_11602.method_4945();
            d7 = class_11602.method_4947();
        }
        this.velocity.set(d9, d8, d7).normalize().multiply(d2);
        if (bl) {
            class_2432 = class_12972.method_18798();
            this.velocity.add(class_2432.field_1352, class_12972.method_24828() ? 0.0 : class_2432.field_1351, class_2432.field_1350);
        }
        this.gravity = d4;
        this.airDrag = 0.99;
        this.waterDrag = d5;
    }
}

