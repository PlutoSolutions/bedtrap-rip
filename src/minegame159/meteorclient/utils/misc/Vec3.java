/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc;

import java.util.Objects;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Vec3 {
    public double z;
    public double x;
    public double y;

    public Vec3 set(class_1297 class_12972, double d) {
        this.x = class_3532.method_16436((double)d, (double)class_12972.field_6038, (double)class_12972.method_23317());
        this.y = class_3532.method_16436((double)d, (double)class_12972.field_5971, (double)class_12972.method_23318());
        this.z = class_3532.method_16436((double)d, (double)class_12972.field_5989, (double)class_12972.method_23321());
        return this;
    }

    public Vec3 add(Vec3 vec3) {
        return this.add(vec3.x, vec3.y, vec3.z);
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vec3 divide(double d) {
        this.x /= d;
        this.y /= d;
        this.z /= d;
        return this;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Vec3 vec3 = (Vec3)object;
        return Double.compare(vec3.x, this.x) == 0 && Double.compare(vec3.y, this.y) == 0 && Double.compare(vec3.z, this.z) == 0;
    }

    public Vec3(double d, double d2, double d3) {
        this.x = d;
        this.y = d2;
        this.z = d3;
    }

    public Vec3 set(Vec3 vec3) {
        this.x = vec3.x;
        this.y = vec3.y;
        this.z = vec3.z;
        return this;
    }

    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z);
    }

    public Vec3 normalize() {
        return this.divide(this.length());
    }

    public Vec3() {
    }

    public Vec3 multiply(double d) {
        return this.multiply(d, d, d);
    }

    public double distanceTo(Vec3 vec3) {
        double d = vec3.x - this.x;
        double d2 = vec3.y - this.y;
        double d3 = vec3.z - this.z;
        return Math.sqrt(d * d + d2 * d2 + d3 * d3);
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    public String toString() {
        return String.format("[%.3f, %.3f, %.3f]", this.x, this.y, this.z);
    }

    public Vec3 add(double d, double d2, double d3) {
        this.x += d;
        this.y += d2;
        this.z += d3;
        return this;
    }

    public Vec3 subtract(class_243 class_2432) {
        return this.subtract(class_2432.field_1352, class_2432.field_1351, class_2432.field_1350);
    }

    public Vec3 multiply(double d, double d2, double d3) {
        this.x *= d;
        this.y *= d2;
        this.z *= d3;
        return this;
    }

    public Vec3 set(class_243 class_2432) {
        this.x = class_2432.field_1352;
        this.y = class_2432.field_1351;
        this.z = class_2432.field_1350;
        return this;
    }

    public Vec3 subtract(double d, double d2, double d3) {
        this.x -= d;
        this.y -= d2;
        this.z -= d3;
        return this;
    }

    public Vec3 set(double d, double d2, double d3) {
        this.x = d;
        this.y = d2;
        this.z = d3;
        return this;
    }
}

