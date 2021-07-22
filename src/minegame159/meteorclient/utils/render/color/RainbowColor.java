/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.render.color;

import minegame159.meteorclient.utils.render.color.Color;

public class RainbowColor
extends Color {
    private /* synthetic */ double speed;
    private static final /* synthetic */ float[] hsb;

    public double getSpeed() {
        RainbowColor lllllllllllllllllIlIIlIIIllllllI;
        return lllllllllllllllllIlIIlIIIllllllI.speed;
    }

    static {
        hsb = new float[3];
    }

    public RainbowColor getNext(double lllllllllllllllllIlIIlIIIllIlllI) {
        RainbowColor lllllllllllllllllIlIIlIIIllIllIl;
        if (lllllllllllllllllIlIIlIIIllIllIl.speed > 0.0) {
            java.awt.Color.RGBtoHSB(lllllllllllllllllIlIIlIIIllIllIl.r, lllllllllllllllllIlIIlIIIllIllIl.g, lllllllllllllllllIlIIlIIIllIllIl.b, hsb);
            int lllllllllllllllllIlIIlIIIlllIIII = java.awt.Color.HSBtoRGB(hsb[0] + (float)(lllllllllllllllllIlIIlIIIllIllIl.speed * lllllllllllllllllIlIIlIIIllIlllI), 1.0f, 1.0f);
            lllllllllllllllllIlIIlIIIllIllIl.r = Color.toRGBAR(lllllllllllllllllIlIIlIIIlllIIII);
            lllllllllllllllllIlIIlIIIllIllIl.g = Color.toRGBAG(lllllllllllllllllIlIIlIIIlllIIII);
            lllllllllllllllllIlIIlIIIllIllIl.b = Color.toRGBAB(lllllllllllllllllIlIIlIIIlllIIII);
        }
        return lllllllllllllllllIlIIlIIIllIllIl;
    }

    public RainbowColor setSpeed(double lllllllllllllllllIlIIlIIIllllIIl) {
        RainbowColor lllllllllllllllllIlIIlIIIllllIlI;
        lllllllllllllllllIlIIlIIIllllIlI.speed = lllllllllllllllllIlIIlIIIllllIIl;
        return lllllllllllllllllIlIIlIIIllllIlI;
    }

    public RainbowColor() {
        RainbowColor lllllllllllllllllIlIIlIIlIIIIIII;
    }

    @Override
    public RainbowColor set(RainbowColor lllllllllllllllllIlIIlIIIllIIlIl) {
        RainbowColor lllllllllllllllllIlIIlIIIllIIllI;
        lllllllllllllllllIlIIlIIIllIIllI.r = lllllllllllllllllIlIIlIIIllIIlIl.r;
        lllllllllllllllllIlIIlIIIllIIllI.g = lllllllllllllllllIlIIlIIIllIIlIl.g;
        lllllllllllllllllIlIIlIIIllIIllI.b = lllllllllllllllllIlIIlIIIllIIlIl.b;
        lllllllllllllllllIlIIlIIIllIIllI.a = lllllllllllllllllIlIIlIIIllIIlIl.a;
        lllllllllllllllllIlIIlIIIllIIllI.speed = lllllllllllllllllIlIIlIIIllIIlIl.speed;
        return lllllllllllllllllIlIIlIIIllIIllI;
    }

    public RainbowColor getNext() {
        RainbowColor lllllllllllllllllIlIIlIIIlllIlIl;
        return lllllllllllllllllIlIIlIIIlllIlIl.getNext(1.0);
    }
}

