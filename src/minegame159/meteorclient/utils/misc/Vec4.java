/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.misc;

public class Vec4 {
    public /* synthetic */ double x;
    public /* synthetic */ double y;
    public /* synthetic */ double w;
    public /* synthetic */ double z;

    public Vec4() {
        Vec4 lllIllIIllllIlI;
    }

    public void toScreen() {
        Vec4 lllIllIIllIlIII;
        double lllIllIIllIIlll = 1.0 / lllIllIIllIlIII.w * 0.5;
        lllIllIIllIlIII.x = lllIllIIllIlIII.x * lllIllIIllIIlll + 0.5;
        lllIllIIllIlIII.y = lllIllIIllIlIII.y * lllIllIIllIIlll + 0.5;
        lllIllIIllIlIII.z = lllIllIIllIlIII.z * lllIllIIllIIlll + 0.5;
        lllIllIIllIlIII.w = lllIllIIllIIlll;
    }

    public void set(double lllIllIIllIlllI, double lllIllIIllIllIl, double lllIllIIlllIIIl, double lllIllIIlllIIII) {
        lllIllIIllIllll.x = lllIllIIllIlllI;
        lllIllIIllIllll.y = lllIllIIllIllIl;
        lllIllIIllIllll.z = lllIllIIlllIIIl;
        lllIllIIllIllll.w = lllIllIIlllIIII;
    }
}

