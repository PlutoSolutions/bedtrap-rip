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
    private static final /* synthetic */ class_243 prevPos3d;
    private /* synthetic */ double airDrag;
    public final /* synthetic */ Vec3 pos;
    private /* synthetic */ double gravity;
    private final /* synthetic */ Vec3 velocity;
    private static final /* synthetic */ class_243 pos3d;
    private /* synthetic */ double waterDrag;
    private static final /* synthetic */ class_2338.class_2339 blockPos;

    static {
        blockPos = new class_2338.class_2339();
        pos3d = new class_243(0.0, 0.0, 0.0);
        prevPos3d = new class_243(0.0, 0.0, 0.0);
    }

    public boolean set(class_1297 lIIlIIl, class_1799 lIIlIII, double lIIIIII, boolean lIIIllI, double lIIIlIl) {
        ProjectileEntitySimulator lIIlIlI;
        class_1792 lIIIlII = lIIlIII.method_7909();
        if (lIIIlII instanceof class_1753) {
            double lIIlIll = class_1753.method_7722((int)Utils.mc.field_1724.method_6048());
            if (lIIlIll <= 0.0) {
                return false;
            }
            lIIlIlI.set(lIIlIIl, 0.0, lIIlIll * 3.0, lIIIIII, 0.05f, 0.6, lIIIllI, lIIIlIl);
        } else if (lIIIlII instanceof class_1764) {
            if (!class_1764.method_7781((class_1799)lIIlIII)) {
                return false;
            }
            lIIlIlI.set(lIIlIIl, 0.0, CrossbowItemAccessor.getSpeed(lIIlIII), lIIIIII, 0.05f, 0.6, lIIIllI, lIIIlIl);
        } else if (lIIIlII instanceof class_1787) {
            lIIlIlI.setFishingBobber(lIIlIIl, lIIIlIl);
        } else if (lIIIlII instanceof class_1835) {
            lIIlIlI.set(lIIlIIl, 0.0, 2.5, lIIIIII, 0.05f, 0.99, lIIIllI, lIIIlIl);
        } else if (lIIIlII instanceof class_1823 || lIIIlII instanceof class_1771 || lIIIlII instanceof class_1776) {
            lIIlIlI.set(lIIlIIl, 0.0, 1.5, lIIIIII, 0.03, 0.8, lIIIllI, lIIIlIl);
        } else if (lIIIlII instanceof class_1779) {
            lIIlIlI.set(lIIlIIl, -20.0, 0.7, lIIIIII, 0.07, 0.8, lIIIllI, lIIIlIl);
        } else if (lIIIlII instanceof class_4537) {
            lIIlIlI.set(lIIlIIl, -20.0, 0.5, lIIIIII, 0.05, 0.8, lIIIllI, lIIIlIl);
        } else {
            return false;
        }
        return true;
    }

    public void setFishingBobber(class_1297 llllllllllllllllllllllllllllIllI, double lllllllllllllllllllllllllllIlIlI) {
        ProjectileEntitySimulator llllllllllllllllllllllllllllIlll;
        double llllllllllllllllllllllllllllIlII = class_3532.method_16436((double)lllllllllllllllllllllllllllIlIlI, (double)llllllllllllllllllllllllllllIllI.field_5982, (double)llllllllllllllllllllllllllllIllI.field_6031);
        double llllllllllllllllllllllllllllIIll = class_3532.method_16436((double)lllllllllllllllllllllllllllIlIlI, (double)llllllllllllllllllllllllllllIllI.field_6004, (double)llllllllllllllllllllllllllllIllI.field_5965);
        double llllllllllllllllllllllllllllIIlI = Math.cos(-llllllllllllllllllllllllllllIlII * 0.01745329238474369 - 3.1415927410125732);
        double llllllllllllllllllllllllllllIIIl = Math.sin(-llllllllllllllllllllllllllllIlII * 0.01745329238474369 - 3.1415927410125732);
        double llllllllllllllllllllllllllllIIII = -Math.cos(-llllllllllllllllllllllllllllIIll * 0.01745329238474369);
        double lllllllllllllllllllllllllllIllll = Math.sin(-llllllllllllllllllllllllllllIIll * 0.01745329238474369);
        llllllllllllllllllllllllllllIlll.pos.set(llllllllllllllllllllllllllllIllI, lllllllllllllllllllllllllllIlIlI).subtract(llllllllllllllllllllllllllllIIIl * 0.3, 0.0, llllllllllllllllllllllllllllIIlI * 0.3).add(0.0, llllllllllllllllllllllllllllIllI.method_18381(llllllllllllllllllllllllllllIllI.method_18376()), 0.0);
        llllllllllllllllllllllllllllIlll.velocity.set(-llllllllllllllllllllllllllllIIIl, Utils.clamp(-(lllllllllllllllllllllllllllIllll / llllllllllllllllllllllllllllIIII), -5.0, 5.0), -llllllllllllllllllllllllllllIIlI);
        double lllllllllllllllllllllllllllIlllI = llllllllllllllllllllllllllllIlll.velocity.length();
        llllllllllllllllllllllllllllIlll.velocity.multiply(0.6 / lllllllllllllllllllllllllllIlllI + 0.5, 0.6 / lllllllllllllllllllllllllllIlllI + 0.5, 0.6 / lllllllllllllllllllllllllllIlllI + 0.5);
        llllllllllllllllllllllllllllIlll.gravity = 0.03;
        llllllllllllllllllllllllllllIlll.airDrag = 0.92;
        llllllllllllllllllllllllllllIlll.waterDrag = 0.0;
    }

    private boolean isTouchingWater() {
        ProjectileEntitySimulator llllllllllllllllllllllllllIlIIll;
        blockPos.method_10102(llllllllllllllllllllllllllIlIIll.pos.x, llllllllllllllllllllllllllIlIIll.pos.y, llllllllllllllllllllllllllIlIIll.pos.z);
        class_3610 llllllllllllllllllllllllllIlIlII = Utils.mc.field_1687.method_8316((class_2338)blockPos);
        if (llllllllllllllllllllllllllIlIlII.method_15772() != class_3612.field_15910 && llllllllllllllllllllllllllIlIlII.method_15772() != class_3612.field_15909) {
            return false;
        }
        return llllllllllllllllllllllllllIlIIll.pos.y - (double)((int)llllllllllllllllllllllllllIlIIll.pos.y) <= (double)llllllllllllllllllllllllllIlIlII.method_20785();
    }

    public class_239 tick() {
        ProjectileEntitySimulator llllllllllllllllllllllllllIllIll;
        ((IVec3d)prevPos3d).set(llllllllllllllllllllllllllIllIll.pos);
        llllllllllllllllllllllllllIllIll.pos.add(llllllllllllllllllllllllllIllIll.velocity);
        llllllllllllllllllllllllllIllIll.velocity.multiply(llllllllllllllllllllllllllIllIll.isTouchingWater() ? llllllllllllllllllllllllllIllIll.waterDrag : llllllllllllllllllllllllllIllIll.airDrag);
        llllllllllllllllllllllllllIllIll.velocity.subtract(0.0, llllllllllllllllllllllllllIllIll.gravity, 0.0);
        if (llllllllllllllllllllllllllIllIll.pos.y < 0.0) {
            return MissHitResult.INSTANCE;
        }
        int llllllllllllllllllllllllllIllllI = (int)(llllllllllllllllllllllllllIllIll.pos.x / 16.0);
        int llllllllllllllllllllllllllIlllIl = (int)(llllllllllllllllllllllllllIllIll.pos.z / 16.0);
        if (!Utils.mc.field_1687.method_2935().method_12123(llllllllllllllllllllllllllIllllI, llllllllllllllllllllllllllIlllIl)) {
            return MissHitResult.INSTANCE;
        }
        ((IVec3d)pos3d).set(llllllllllllllllllllllllllIllIll.pos);
        class_239 llllllllllllllllllllllllllIlllII = llllllllllllllllllllllllllIllIll.getCollision();
        return llllllllllllllllllllllllllIlllII.method_17783() == class_239.class_240.field_1333 ? null : llllllllllllllllllllllllllIlllII;
    }

    private class_239 getCollision() {
        class_3966 llllllllllllllllllllllllllIIlIlI;
        ProjectileEntitySimulator llllllllllllllllllllllllllIIllIl;
        class_243 llllllllllllllllllllllllllIIllII = prevPos3d;
        class_3965 llllllllllllllllllllllllllIIlIll = Utils.mc.field_1687.method_17742(new class_3959(llllllllllllllllllllllllllIIllII, pos3d, class_3959.class_3960.field_17558, llllllllllllllllllllllllllIIllIl.waterDrag == 0.0 ? class_3959.class_242.field_1347 : class_3959.class_242.field_1348, (class_1297)Utils.mc.field_1724));
        if (llllllllllllllllllllllllllIIlIll.method_17783() != class_239.class_240.field_1333) {
            llllllllllllllllllllllllllIIllII = llllllllllllllllllllllllllIIlIll.method_17784();
        }
        if ((llllllllllllllllllllllllllIIlIlI = class_1675.method_18077((class_1937)Utils.mc.field_1687, (class_1297)Utils.mc.field_1724, (class_243)llllllllllllllllllllllllllIIllII, (class_243)pos3d, (class_238)new class_238(llllllllllllllllllllllllllIIllIl.pos.x, llllllllllllllllllllllllllIIllIl.pos.y, llllllllllllllllllllllllllIIllIl.pos.z, llllllllllllllllllllllllllIIllIl.pos.x, llllllllllllllllllllllllllIIllIl.pos.y, llllllllllllllllllllllllllIIllIl.pos.z).method_18804(Utils.mc.field_1724.method_18798()).method_1014(1.0), llllllllllllllllllllllllllIIIlII -> !llllllllllllllllllllllllllIIIlII.method_7325() && llllllllllllllllllllllllllIIIlII.method_5805() && llllllllllllllllllllllllllIIIlII.method_5863())) != null) {
            llllllllllllllllllllllllllIIlIll = llllllllllllllllllllllllllIIlIlI;
        }
        return llllllllllllllllllllllllllIIlIll;
    }

    public void set(class_1297 lIIIII, double lllll, double llllI, double llll, double lllII, double llIl, boolean llIlI, double lIll) {
        double lIlII;
        double lIlIl;
        double lIllI;
        ProjectileEntitySimulator lIIll;
        lIIll.pos.set(lIIIII, lIll).add(0.0, lIIIII.method_18381(lIIIII.method_18376()), 0.0);
        double llIII = class_3532.method_16436((double)lIll, (double)lIIIII.field_5982, (double)lIIIII.field_6031);
        double lIlll = class_3532.method_16436((double)lIll, (double)lIIIII.field_6004, (double)lIIIII.field_5965);
        if (llll == 0.0) {
            double lIlIIl = -Math.sin(llIII * 0.017453292) * Math.cos(lIlll * 0.017453292);
            double lIlIII = -Math.sin((lIlll + lllll) * 0.017453292);
            double lIIlll = Math.cos(llIII * 0.017453292) * Math.cos(lIlll * 0.017453292);
        } else {
            class_243 lIIllI = lIIIII.method_18864(1.0f);
            class_1158 lIIlIl = new class_1158(new class_1160(lIIllI), (float)llll, true);
            class_243 lIIlII = lIIIII.method_5828(1.0f);
            class_1160 lIIIll = new class_1160(lIIlII);
            lIIIll.method_19262(lIIlIl);
            lIllI = lIIIll.method_4943();
            lIlIl = lIIIll.method_4945();
            lIlII = lIIIll.method_4947();
        }
        lIIll.velocity.set(lIllI, lIlIl, lIlII).normalize().multiply(llllI);
        if (llIlI) {
            class_243 lIIIlI = lIIIII.method_18798();
            lIIll.velocity.add(lIIIlI.field_1352, lIIIII.method_24828() ? 0.0 : lIIIlI.field_1351, lIIIlI.field_1350);
        }
        lIIll.gravity = lllII;
        lIIll.airDrag = 0.99;
        lIIll.waterDrag = llIl;
    }

    public ProjectileEntitySimulator() {
        ProjectileEntitySimulator lIlIlIl;
        lIlIlIl.pos = new Vec3();
        lIlIlIl.velocity = new Vec3();
    }
}

