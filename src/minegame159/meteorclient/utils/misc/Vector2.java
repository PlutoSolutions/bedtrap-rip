/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.utils.misc;

import java.util.Objects;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;

public class Vector2
implements ISerializable<Vector2> {
    public static final /* synthetic */ Vector2 ZERO;
    public /* synthetic */ double y;
    public /* synthetic */ double x;

    @Override
    public Vector2 fromTag(class_2487 lllllllllllllllllllIlIlllIlllIII) {
        Vector2 lllllllllllllllllllIlIlllIlllIIl;
        lllllllllllllllllllIlIlllIlllIIl.x = lllllllllllllllllllIlIlllIlllIII.method_10574("x");
        lllllllllllllllllllIlIlllIlllIIl.y = lllllllllllllllllllIlIlllIlllIII.method_10574("y");
        return lllllllllllllllllllIlIlllIlllIIl;
    }

    public int hashCode() {
        Vector2 lllllllllllllllllllIlIlllIlIlIIl;
        return Objects.hash(lllllllllllllllllllIlIlllIlIlIIl.x, lllllllllllllllllllIlIlllIlIlIIl.y);
    }

    public Vector2() {
        lllllllllllllllllllIlIllllIlIlII(0.0, 0.0);
        Vector2 lllllllllllllllllllIlIllllIlIlII;
    }

    public void set(double lllllllllllllllllllIlIllllIIIlIl, double lllllllllllllllllllIlIllllIIIlll) {
        lllllllllllllllllllIlIllllIIIllI.x = lllllllllllllllllllIlIllllIIIlIl;
        lllllllllllllllllllIlIllllIIIllI.y = lllllllllllllllllllIlIllllIIIlll;
    }

    public Vector2(Vector2 lllllllllllllllllllIlIllllIIllll) {
        lllllllllllllllllllIlIllllIlIIII(lllllllllllllllllllIlIllllIIllll.x, lllllllllllllllllllIlIllllIIllll.y);
        Vector2 lllllllllllllllllllIlIllllIlIIII;
    }

    public String toString() {
        Vector2 lllllllllllllllllllIlIlllIllIllI;
        return String.valueOf(new StringBuilder().append(lllllllllllllllllllIlIlllIllIllI.x).append(", ").append(lllllllllllllllllllIlIlllIllIllI.y));
    }

    public Vector2(double lllllllllllllllllllIlIllllIlIlll, double lllllllllllllllllllIlIllllIlIllI) {
        Vector2 lllllllllllllllllllIlIllllIllIII;
        lllllllllllllllllllIlIllllIllIII.x = lllllllllllllllllllIlIllllIlIlll;
        lllllllllllllllllllIlIllllIllIII.y = lllllllllllllllllllIlIllllIlIllI;
    }

    public boolean equals(Object lllllllllllllllllllIlIlllIllIIII) {
        Vector2 lllllllllllllllllllIlIlllIlIlllI;
        if (lllllllllllllllllllIlIlllIlIlllI == lllllllllllllllllllIlIlllIllIIII) {
            return true;
        }
        if (lllllllllllllllllllIlIlllIllIIII == null || lllllllllllllllllllIlIlllIlIlllI.getClass() != lllllllllllllllllllIlIlllIllIIII.getClass()) {
            return false;
        }
        Vector2 lllllllllllllllllllIlIlllIlIllll = (Vector2)lllllllllllllllllllIlIlllIllIIII;
        return Double.compare(lllllllllllllllllllIlIlllIlIllll.x, lllllllllllllllllllIlIlllIlIlllI.x) == 0 && Double.compare(lllllllllllllllllllIlIlllIlIllll.y, lllllllllllllllllllIlIlllIlIlllI.y) == 0;
    }

    static {
        ZERO = new Vector2(0.0, 0.0);
    }

    @Override
    public class_2487 toTag() {
        Vector2 lllllllllllllllllllIlIllllIIIIIl;
        class_2487 lllllllllllllllllllIlIllllIIIIII = new class_2487();
        lllllllllllllllllllIlIllllIIIIII.method_10549("x", lllllllllllllllllllIlIllllIIIIIl.x);
        lllllllllllllllllllIlIllllIIIIII.method_10549("y", lllllllllllllllllllIlIllllIIIIIl.y);
        return lllllllllllllllllllIlIllllIIIIII;
    }
}

