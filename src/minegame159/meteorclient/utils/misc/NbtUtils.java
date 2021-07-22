/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.utils.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;

public class NbtUtils {
    public static <T> List<T> listFromTag(class_2499 lllIIlIlllllIl, ToValue<T> lllIIlIlllllII) {
        ArrayList<T> lllIIlIllllllI = new ArrayList<T>(lllIIlIlllllIl.size());
        for (class_2520 lllIIllIIIIIIl : lllIIlIlllllIl) {
            T lllIIllIIIIIlI = lllIIlIlllllII.toValue(lllIIllIIIIIIl);
            if (lllIIllIIIIIlI == null) continue;
            lllIIlIllllllI.add(lllIIllIIIIIlI);
        }
        return lllIIlIllllllI;
    }

    public NbtUtils() {
        NbtUtils lllIIllIIlIlII;
    }

    public static <T extends ISerializable<?>> class_2499 listToTag(Iterable<T> lllIIllIIIlllI) {
        class_2499 lllIIllIIIllIl = new class_2499();
        for (ISerializable lllIIllIIIllll : lllIIllIIIlllI) {
            lllIIllIIIllIl.add((Object)lllIIllIIIllll.toTag());
        }
        return lllIIllIIIllIl;
    }

    public static <K, V extends ISerializable<?>> class_2487 mapToTag(Map<K, V> lllIIlIlllIIlI) {
        class_2487 lllIIlIlllIIIl = new class_2487();
        for (K lllIIlIlllIIll : lllIIlIlllIIlI.keySet()) {
            lllIIlIlllIIIl.method_10566(lllIIlIlllIIll.toString(), (class_2520)((ISerializable)lllIIlIlllIIlI.get(lllIIlIlllIIll)).toTag());
        }
        return lllIIlIlllIIIl;
    }

    public static <K, V> Map<K, V> mapFromTag(class_2487 lllIIlIllIIlIl, ToKey<K> lllIIlIllIIIII, ToValue<V> lllIIlIllIIIll) {
        HashMap<K, V> lllIIlIllIIIlI = new HashMap<K, V>(lllIIlIllIIlIl.method_10546());
        for (String lllIIlIllIIllI : lllIIlIllIIlIl.method_10541()) {
            lllIIlIllIIIlI.put(lllIIlIllIIIII.toKey(lllIIlIllIIllI), lllIIlIllIIIll.toValue(lllIIlIllIIlIl.method_10580(lllIIlIllIIllI)));
        }
        return lllIIlIllIIIlI;
    }

    public static interface ToValue<T> {
        public T toValue(class_2520 var1);
    }

    public static interface ToKey<T> {
        public T toKey(String var1);
    }
}

