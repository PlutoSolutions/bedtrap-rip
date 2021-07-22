/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.systems.friends;

import java.util.Objects;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_1657;
import net.minecraft.class_2487;

public class Friend
implements ISerializable<Friend> {
    public /* synthetic */ String name;

    public Friend(class_2487 lIllIIIIllllll) {
        Friend lIllIIIlIIIIlI;
        lIllIIIlIIIIlI.fromTag(lIllIIIIllllll);
    }

    public int hashCode() {
        Friend lIllIIIIlIlIII;
        return Objects.hash(lIllIIIIlIlIII.name);
    }

    @Override
    public Friend fromTag(class_2487 lIllIIIIllIlIl) {
        Friend lIllIIIIllIllI;
        lIllIIIIllIllI.name = lIllIIIIllIlIl.method_10558("name");
        return lIllIIIIllIllI;
    }

    public Friend(String lIllIIIlIIllIl) {
        Friend lIllIIIlIIllII;
        lIllIIIlIIllII.name = lIllIIIlIIllIl;
    }

    public boolean equals(Object lIllIIIIlIlIll) {
        Friend lIllIIIIlIllII;
        if (lIllIIIIlIllII == lIllIIIIlIlIll) {
            return true;
        }
        if (lIllIIIIlIlIll == null || lIllIIIIlIllII.getClass() != lIllIIIIlIlIll.getClass()) {
            return false;
        }
        Friend lIllIIIIlIllIl = (Friend)lIllIIIIlIlIll;
        return Objects.equals(lIllIIIIlIllII.name, lIllIIIIlIllIl.name);
    }

    @Override
    public class_2487 toTag() {
        Friend lIllIIIIllllII;
        class_2487 lIllIIIIlllIll = new class_2487();
        lIllIIIIlllIll.method_10582("name", lIllIIIIllllII.name);
        return lIllIIIIlllIll;
    }

    public Friend(class_1657 lIllIIIlIIIlIl) {
        lIllIIIlIIlIII(lIllIIIlIIIlIl.method_5820());
        Friend lIllIIIlIIlIII;
    }
}

