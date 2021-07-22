/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package minegame159.meteorclient.utils.misc;

import java.util.Objects;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Vec3 {
    public /* synthetic */ double z;
    public /* synthetic */ double x;
    public /* synthetic */ double y;

    public Vec3 set(class_1297 llllllllllllllllllllIllIIIlIlllI, double llllllllllllllllllllIllIIIlIllIl) {
        Vec3 llllllllllllllllllllIllIIIlIllll;
        llllllllllllllllllllIllIIIlIllll.x = class_3532.method_16436((double)llllllllllllllllllllIllIIIlIllIl, (double)llllllllllllllllllllIllIIIlIlllI.field_6038, (double)llllllllllllllllllllIllIIIlIlllI.method_23317());
        llllllllllllllllllllIllIIIlIllll.y = class_3532.method_16436((double)llllllllllllllllllllIllIIIlIllIl, (double)llllllllllllllllllllIllIIIlIlllI.field_5971, (double)llllllllllllllllllllIllIIIlIlllI.method_23318());
        llllllllllllllllllllIllIIIlIllll.z = class_3532.method_16436((double)llllllllllllllllllllIllIIIlIllIl, (double)llllllllllllllllllllIllIIIlIlllI.field_5989, (double)llllllllllllllllllllIllIIIlIlllI.method_23321());
        return llllllllllllllllllllIllIIIlIllll;
    }

    public Vec3 add(Vec3 llllllllllllllllllllIllIIIIllIII) {
        Vec3 llllllllllllllllllllIllIIIIllIIl;
        return llllllllllllllllllllIllIIIIllIIl.add(llllllllllllllllllllIllIIIIllIII.x, llllllllllllllllllllIllIIIIllIII.y, llllllllllllllllllllIllIIIIllIII.z);
    }

    public double length() {
        Vec3 llllllllllllllllllllIlIlllIllIIl;
        return Math.sqrt(llllllllllllllllllllIlIlllIllIIl.x * llllllllllllllllllllIlIlllIllIIl.x + llllllllllllllllllllIlIlllIllIIl.y * llllllllllllllllllllIlIlllIllIIl.y + llllllllllllllllllllIlIlllIllIIl.z * llllllllllllllllllllIlIlllIllIIl.z);
    }

    public Vec3 divide(double llllllllllllllllllllIlIlllllIIII) {
        Vec3 llllllllllllllllllllIlIlllllIIIl;
        llllllllllllllllllllIlIlllllIIIl.x /= llllllllllllllllllllIlIlllllIIII;
        llllllllllllllllllllIlIlllllIIIl.y /= llllllllllllllllllllIlIlllllIIII;
        llllllllllllllllllllIlIlllllIIIl.z /= llllllllllllllllllllIlIlllllIIII;
        return llllllllllllllllllllIlIlllllIIIl;
    }

    public boolean equals(Object llllllllllllllllllllIlIlllIlIIIl) {
        Vec3 llllllllllllllllllllIlIlllIlIIlI;
        if (llllllllllllllllllllIlIlllIlIIlI == llllllllllllllllllllIlIlllIlIIIl) {
            return true;
        }
        if (llllllllllllllllllllIlIlllIlIIIl == null || llllllllllllllllllllIlIlllIlIIlI.getClass() != llllllllllllllllllllIlIlllIlIIIl.getClass()) {
            return false;
        }
        Vec3 llllllllllllllllllllIlIlllIlIIII = (Vec3)llllllllllllllllllllIlIlllIlIIIl;
        return Double.compare(llllllllllllllllllllIlIlllIlIIII.x, llllllllllllllllllllIlIlllIlIIlI.x) == 0 && Double.compare(llllllllllllllllllllIlIlllIlIIII.y, llllllllllllllllllllIlIlllIlIIlI.y) == 0 && Double.compare(llllllllllllllllllllIlIlllIlIIII.z, llllllllllllllllllllIlIlllIlIIlI.z) == 0;
    }

    public Vec3(double llllllllllllllllllllIllIIlIlIIIl, double llllllllllllllllllllIllIIlIIllII, double llllllllllllllllllllIllIIlIIlIll) {
        Vec3 llllllllllllllllllllIllIIlIIlllI;
        llllllllllllllllllllIllIIlIIlllI.x = llllllllllllllllllllIllIIlIlIIIl;
        llllllllllllllllllllIllIIlIIlllI.y = llllllllllllllllllllIllIIlIIllII;
        llllllllllllllllllllIllIIlIIlllI.z = llllllllllllllllllllIllIIlIIlIll;
    }

    public Vec3 set(Vec3 llllllllllllllllllllIllIIIlllIIl) {
        Vec3 llllllllllllllllllllIllIIIllllII;
        llllllllllllllllllllIllIIIllllII.x = llllllllllllllllllllIllIIIlllIIl.x;
        llllllllllllllllllllIllIIIllllII.y = llllllllllllllllllllIllIIIlllIIl.y;
        llllllllllllllllllllIllIIIllllII.z = llllllllllllllllllllIllIIIlllIIl.z;
        return llllllllllllllllllllIllIIIllllII;
    }

    public int hashCode() {
        Vec3 llllllllllllllllllllIlIlllIIlIll;
        return Objects.hash(llllllllllllllllllllIlIlllIIlIll.x, llllllllllllllllllllIlIlllIIlIll.y, llllllllllllllllllllIlIlllIIlIll.z);
    }

    public Vec3 normalize() {
        Vec3 llllllllllllllllllllIlIlllIlIllI;
        return llllllllllllllllllllIlIlllIlIllI.divide(llllllllllllllllllllIlIlllIlIllI.length());
    }

    public Vec3() {
        Vec3 llllllllllllllllllllIllIIlIllIII;
    }

    public Vec3 multiply(double llllllllllllllllllllIlIlllllIllI) {
        Vec3 llllllllllllllllllllIlIlllllIlll;
        return llllllllllllllllllllIlIlllllIlll.multiply(llllllllllllllllllllIlIlllllIllI, llllllllllllllllllllIlIlllllIllI, llllllllllllllllllllIlIlllllIllI);
    }

    public double distanceTo(Vec3 llllllllllllllllllllIlIllllIIlII) {
        Vec3 llllllllllllllllllllIlIllllIIIII;
        double llllllllllllllllllllIlIllllIIIll = llllllllllllllllllllIlIllllIIlII.x - llllllllllllllllllllIlIllllIIIII.x;
        double llllllllllllllllllllIlIllllIIIlI = llllllllllllllllllllIlIllllIIlII.y - llllllllllllllllllllIlIllllIIIII.y;
        double llllllllllllllllllllIlIllllIIIIl = llllllllllllllllllllIlIllllIIlII.z - llllllllllllllllllllIlIllllIIIII.z;
        return Math.sqrt(llllllllllllllllllllIlIllllIIIll * llllllllllllllllllllIlIllllIIIll + llllllllllllllllllllIlIllllIIIlI * llllllllllllllllllllIlIllllIIIlI + llllllllllllllllllllIlIllllIIIIl * llllllllllllllllllllIlIllllIIIIl);
    }

    public void negate() {
        Vec3 llllllllllllllllllllIlIllllIlIll;
        llllllllllllllllllllIlIllllIlIll.x = -llllllllllllllllllllIlIllllIlIll.x;
        llllllllllllllllllllIlIllllIlIll.y = -llllllllllllllllllllIlIllllIlIll.y;
        llllllllllllllllllllIlIllllIlIll.z = -llllllllllllllllllllIlIllllIlIll.z;
    }

    public String toString() {
        Vec3 llllllllllllllllllllIlIlllIIIlll;
        return String.format("[%.3f, %.3f, %.3f]", llllllllllllllllllllIlIlllIIIlll.x, llllllllllllllllllllIlIlllIIIlll.y, llllllllllllllllllllIlIlllIIIlll.z);
    }

    public Vec3 add(double llllllllllllllllllllIllIIIlIIlII, double llllllllllllllllllllIllIIIIlllll, double llllllllllllllllllllIllIIIIllllI) {
        Vec3 llllllllllllllllllllIllIIIlIIIIl;
        llllllllllllllllllllIllIIIlIIIIl.x += llllllllllllllllllllIllIIIlIIlII;
        llllllllllllllllllllIllIIIlIIIIl.y += llllllllllllllllllllIllIIIIlllll;
        llllllllllllllllllllIllIIIlIIIIl.z += llllllllllllllllllllIllIIIIllllI;
        return llllllllllllllllllllIllIIIlIIIIl;
    }

    public Vec3 subtract(class_243 llllllllllllllllllllIllIIIIIlIII) {
        Vec3 llllllllllllllllllllIllIIIIIlIIl;
        return llllllllllllllllllllIllIIIIIlIIl.subtract(llllllllllllllllllllIllIIIIIlIII.field_1352, llllllllllllllllllllIllIIIIIlIII.field_1351, llllllllllllllllllllIllIIIIIlIII.field_1350);
    }

    public Vec3 multiply(double llllllllllllllllllllIllIIIIIIIII, double llllllllllllllllllllIlIllllllIll, double llllllllllllllllllllIlIllllllllI) {
        Vec3 llllllllllllllllllllIlIlllllllIl;
        llllllllllllllllllllIlIlllllllIl.x *= llllllllllllllllllllIllIIIIIIIII;
        llllllllllllllllllllIlIlllllllIl.y *= llllllllllllllllllllIlIllllllIll;
        llllllllllllllllllllIlIlllllllIl.z *= llllllllllllllllllllIlIllllllllI;
        return llllllllllllllllllllIlIlllllllIl;
    }

    public Vec3 set(class_243 llllllllllllllllllllIllIIIllIlIl) {
        Vec3 llllllllllllllllllllIllIIIllIllI;
        llllllllllllllllllllIllIIIllIllI.x = llllllllllllllllllllIllIIIllIlIl.field_1352;
        llllllllllllllllllllIllIIIllIllI.y = llllllllllllllllllllIllIIIllIlIl.field_1351;
        llllllllllllllllllllIllIIIllIllI.z = llllllllllllllllllllIllIIIllIlIl.field_1350;
        return llllllllllllllllllllIllIIIllIllI;
    }

    public Vec3 subtract(double llllllllllllllllllllIllIIIIIlllI, double llllllllllllllllllllIllIIIIlIIIl, double llllllllllllllllllllIllIIIIIllII) {
        Vec3 llllllllllllllllllllIllIIIIIllll;
        llllllllllllllllllllIllIIIIIllll.x -= llllllllllllllllllllIllIIIIIlllI;
        llllllllllllllllllllIllIIIIIllll.y -= llllllllllllllllllllIllIIIIlIIIl;
        llllllllllllllllllllIllIIIIIllll.z -= llllllllllllllllllllIllIIIIIllII;
        return llllllllllllllllllllIllIIIIIllll;
    }

    public Vec3 set(double llllllllllllllllllllIllIIlIIIlIl, double llllllllllllllllllllIllIIlIIIIII, double llllllllllllllllllllIllIIlIIIIll) {
        Vec3 llllllllllllllllllllIllIIlIIIIlI;
        llllllllllllllllllllIllIIlIIIIlI.x = llllllllllllllllllllIllIIlIIIlIl;
        llllllllllllllllllllIllIIlIIIIlI.y = llllllllllllllllllllIllIIlIIIIII;
        llllllllllllllllllllIllIIlIIIIlI.z = llllllllllllllllllllIllIIlIIIIll;
        return llllllllllllllllllllIllIIlIIIIlI;
    }
}

