/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.systems.modules;

import net.minecraft.class_1799;

public class Category {
    private final /* synthetic */ int nameHash;
    public final /* synthetic */ class_1799 icon;
    public final /* synthetic */ String name;

    public Category(String lllllllllllllllllIIlIIIlllIlIIIl, class_1799 lllllllllllllllllIIlIIIlllIlIIII) {
        Category lllllllllllllllllIIlIIIlllIlIlIl;
        lllllllllllllllllIIlIIIlllIlIlIl.name = lllllllllllllllllIIlIIIlllIlIIIl;
        lllllllllllllllllIIlIIIlllIlIlIl.nameHash = lllllllllllllllllIIlIIIlllIlIIIl.hashCode();
        lllllllllllllllllIIlIIIlllIlIlIl.icon = lllllllllllllllllIIlIIIlllIlIIII;
    }

    public int hashCode() {
        Category lllllllllllllllllIIlIIIllIlllIll;
        return lllllllllllllllllIIlIIIllIlllIll.nameHash;
    }

    public String toString() {
        Category lllllllllllllllllIIlIIIlllIIlIII;
        return lllllllllllllllllIIlIIIlllIIlIII.name;
    }

    public boolean equals(Object lllllllllllllllllIIlIIIllIllllll) {
        Category lllllllllllllllllIIlIIIlllIIIIII;
        if (lllllllllllllllllIIlIIIlllIIIIII == lllllllllllllllllIIlIIIllIllllll) {
            return true;
        }
        if (lllllllllllllllllIIlIIIllIllllll == null || lllllllllllllllllIIlIIIlllIIIIII.getClass() != lllllllllllllllllIIlIIIllIllllll.getClass()) {
            return false;
        }
        Category lllllllllllllllllIIlIIIlllIIIIIl = (Category)lllllllllllllllllIIlIIIllIllllll;
        return lllllllllllllllllIIlIIIlllIIIIII.nameHash == lllllllllllllllllIIlIIIlllIIIIIl.nameHash;
    }

    public Category(String lllllllllllllllllIIlIIIlllIIlIlI) {
        lllllllllllllllllIIlIIIlllIIllIl(lllllllllllllllllIIlIIIlllIIlIlI, null);
        Category lllllllllllllllllIIlIIIlllIIllIl;
    }
}

