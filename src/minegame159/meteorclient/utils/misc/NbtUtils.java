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
    public static <T> List<T> listFromTag(class_2499 class_24992, ToValue<T> toValue) {
        ArrayList<T> arrayList = new ArrayList<T>(class_24992.size());
        for (class_2520 class_25202 : class_24992) {
            T t = toValue.toValue(class_25202);
            if (t == null) continue;
            arrayList.add(t);
        }
        return arrayList;
    }

    public static <T extends ISerializable<?>> class_2499 listToTag(Iterable<T> iterable) {
        class_2499 class_24992 = new class_2499();
        for (ISerializable iSerializable : iterable) {
            class_24992.add((Object)iSerializable.toTag());
        }
        return class_24992;
    }

    public static <K, V extends ISerializable<?>> class_2487 mapToTag(Map<K, V> map) {
        class_2487 class_24872 = new class_2487();
        for (K k : map.keySet()) {
            class_24872.method_10566(k.toString(), (class_2520)((ISerializable)map.get(k)).toTag());
        }
        return class_24872;
    }

    public static <K, V> Map<K, V> mapFromTag(class_2487 class_24872, ToKey<K> toKey, ToValue<V> toValue) {
        HashMap<K, V> hashMap = new HashMap<K, V>(class_24872.method_10546());
        for (String string : class_24872.method_10541()) {
            hashMap.put(toKey.toKey(string), toValue.toValue(class_24872.method_10580(string)));
        }
        return hashMap;
    }

    public static interface ToValue<T> {
        public T toValue(class_2520 var1);
    }

    public static interface ToKey<T> {
        public T toKey(String var1);
    }
}

