/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.render.color;

import minegame159.meteorclient.utils.render.color.Color;

public class RainbowColor
extends Color {
    private double speed;
    private static final float[] hsb = new float[3];

    public double getSpeed() {
        return this.speed;
    }

    public RainbowColor getNext(double d) {
        if (this.speed > 0.0) {
            java.awt.Color.RGBtoHSB(this.r, this.g, this.b, hsb);
            int n = java.awt.Color.HSBtoRGB(hsb[0] + (float)(this.speed * d), 1.0f, 1.0f);
            this.r = Color.toRGBAR(n);
            this.g = Color.toRGBAG(n);
            this.b = Color.toRGBAB(n);
        }
        return this;
    }

    public RainbowColor setSpeed(double d) {
        this.speed = d;
        return this;
    }

    @Override
    public RainbowColor set(RainbowColor rainbowColor) {
        this.r = rainbowColor.r;
        this.g = rainbowColor.g;
        this.b = rainbowColor.b;
        this.a = rainbowColor.a;
        this.speed = rainbowColor.speed;
        return this;
    }

    public RainbowColor getNext() {
        return this.getNext(1.0);
    }
}

