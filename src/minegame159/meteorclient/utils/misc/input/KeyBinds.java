/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc.input;

import java.util.Map;
import minegame159.meteorclient.mixin.KeyBindingAccessor;
import net.minecraft.class_304;
import net.minecraft.class_3675;

public class KeyBinds {
    public static class_304 OPEN_CLICK_GUI;
    private static final String CATEGORY;

    public static class_304[] apply(class_304[] class_304Array) {
        Map<String, Integer> map = KeyBindingAccessor.getCategoryOrderMap();
        int n = 0;
        class_304[] class_304Array2 = map.values().iterator();
        while (class_304Array2.hasNext()) {
            int n2 = class_304Array2.next();
            if (n2 <= n) continue;
            n = n2;
        }
        map.put("BedTrap", n + 1);
        class_304Array2 = new class_304[class_304Array.length + 1];
        System.arraycopy(class_304Array, 0, class_304Array2, 0, class_304Array.length);
        class_304Array2[class_304Array.length] = OPEN_CLICK_GUI;
        return class_304Array2;
    }

    public static int getKey(class_304 class_3042) {
        return ((KeyBindingAccessor)class_3042).getKey().method_1444();
    }

    static {
        CATEGORY = "BedTrap";
        OPEN_CLICK_GUI = new class_304("key.meteor-client.open-click-gui", class_3675.class_307.field_1668, 344, "BedTrap");
    }
}

