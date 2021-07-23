/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc;

public class Vec4 {
    public double x;
    public double y;
    public double w;
    public double z;

    public void toScreen() {
        double d = 1.0 / this.w * 0.5;
        this.x = this.x * d + 0.5;
        this.y = this.y * d + 0.5;
        this.z = this.z * d + 0.5;
        this.w = d;
    }

    public void set(double d, double d2, double d3, double d4) {
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.w = d4;
    }
}

