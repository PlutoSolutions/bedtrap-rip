/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.render;

import java.util.Objects;

public class Box {
    public /* synthetic */ double y;
    public /* synthetic */ double x;
    public /* synthetic */ double height;
    public /* synthetic */ double width;

    public boolean equals(Object llllllllllllllllIllllIlIIlIlllll) {
        Box llllllllllllllllIllllIlIIllIIIII;
        if (llllllllllllllllIllllIlIIllIIIII == llllllllllllllllIllllIlIIlIlllll) {
            return true;
        }
        if (llllllllllllllllIllllIlIIlIlllll == null || llllllllllllllllIllllIlIIllIIIII.getClass() != llllllllllllllllIllllIlIIlIlllll.getClass()) {
            return false;
        }
        Box llllllllllllllllIllllIlIIllIIIIl = (Box)llllllllllllllllIllllIlIIlIlllll;
        return Double.compare(llllllllllllllllIllllIlIIllIIIIl.x, llllllllllllllllIllllIlIIllIIIII.x) == 0 && Double.compare(llllllllllllllllIllllIlIIllIIIIl.y, llllllllllllllllIllllIlIIllIIIII.y) == 0 && Double.compare(llllllllllllllllIllllIlIIllIIIIl.width, llllllllllllllllIllllIlIIllIIIII.width) == 0 && Double.compare(llllllllllllllllIllllIlIIllIIIIl.height, llllllllllllllllIllllIlIIllIIIII.height) == 0;
    }

    public int hashCode() {
        Box llllllllllllllllIllllIlIIlIllIll;
        return Objects.hash(llllllllllllllllIllllIlIIlIllIll.x, llllllllllllllllIllllIlIIlIllIll.y, llllllllllllllllIllllIlIIlIllIll.width, llllllllllllllllIllllIlIIlIllIll.height);
    }

    public Box(double llllllllllllllllIllllIlIIlllIIlI, double llllllllllllllllIllllIlIIllIllII, double llllllllllllllllIllllIlIIllIlIll, double llllllllllllllllIllllIlIIllIllll) {
        Box llllllllllllllllIllllIlIIllIlllI;
        llllllllllllllllIllllIlIIllIlllI.x = llllllllllllllllIllllIlIIlllIIlI;
        llllllllllllllllIllllIlIIllIlllI.y = llllllllllllllllIllllIlIIllIllII;
        llllllllllllllllIllllIlIIllIlllI.width = llllllllllllllllIllllIlIIllIlIll;
        llllllllllllllllIllllIlIIllIlllI.height = llllllllllllllllIllllIlIIllIllll;
    }

    public Box() {
        llllllllllllllllIllllIlIIllIIlll(0.0, 0.0, 0.0, 0.0);
        Box llllllllllllllllIllllIlIIllIIlll;
    }
}

