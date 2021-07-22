/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_304
 *  net.minecraft.class_3675$class_307
 */
package minegame159.meteorclient.utils.misc.input;

import java.util.Map;
import minegame159.meteorclient.mixin.KeyBindingAccessor;
import net.minecraft.class_304;
import net.minecraft.class_3675;

public class KeyBinds {
    public static class_304 OPEN_CLICK_GUI;
    private static final String CATEGORY;

    public static class_304[] apply(class_304[] arrclass_304) {
        Map<String, Integer> map = KeyBindingAccessor.getCategoryOrderMap();
        int n = 0;
        class_304[] arrclass_3042 = map.values().iterator();
        while (arrclass_3042.hasNext()) {
            int n2 = arrclass_3042.next();
            if (n2 <= n) continue;
            n = n2;
        }
        map.put("BedTrap", n + 1);
        arrclass_3042 = new class_304[arrclass_304.length + 1];
        System.arraycopy(arrclass_304, 0, arrclass_3042, 0, arrclass_304.length);
        arrclass_3042[arrclass_304.length] = OPEN_CLICK_GUI;
        return arrclass_3042;
    }

    public static int getKey(class_304 class_3042) {
        return ((KeyBindingAccessor)class_3042).getKey().method_1444();
    }

    static {
        CATEGORY = "BedTrap";
        OPEN_CLICK_GUI = new class_304("key.meteor-client.open-click-gui", class_3675.class_307.field_1668, 344, "BedTrap");
    }
}

