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
    public static /* synthetic */ class_304 OPEN_CLICK_GUI;
    private static final /* synthetic */ String CATEGORY;

    public KeyBinds() {
        KeyBinds llllllllllllllllIlIlIllllIIIIIll;
    }

    public static class_304[] apply(class_304[] llllllllllllllllIlIlIlllIllllIII) {
        Map<String, Integer> llllllllllllllllIlIlIlllIllllIll = KeyBindingAccessor.getCategoryOrderMap();
        int llllllllllllllllIlIlIlllIllllIlI = 0;
        for (int llllllllllllllllIlIlIlllIlllllIl : llllllllllllllllIlIlIlllIllllIll.values()) {
            if (llllllllllllllllIlIlIlllIlllllIl <= llllllllllllllllIlIlIlllIllllIlI) continue;
            llllllllllllllllIlIlIlllIllllIlI = llllllllllllllllIlIlIlllIlllllIl;
        }
        llllllllllllllllIlIlIlllIllllIll.put("BedTrap", llllllllllllllllIlIlIlllIllllIlI + 1);
        class_304[] llllllllllllllllIlIlIlllIllllIIl = new class_304[llllllllllllllllIlIlIlllIllllIII.length + 1];
        System.arraycopy(llllllllllllllllIlIlIlllIllllIII, 0, llllllllllllllllIlIlIlllIllllIIl, 0, llllllllllllllllIlIlIlllIllllIII.length);
        llllllllllllllllIlIlIlllIllllIIl[llllllllllllllllIlIlIlllIllllIII.length] = OPEN_CLICK_GUI;
        return llllllllllllllllIlIlIlllIllllIIl;
    }

    public static int getKey(class_304 llllllllllllllllIlIlIlllIlllIIIl) {
        return ((KeyBindingAccessor)llllllllllllllllIlIlIlllIlllIIIl).getKey().method_1444();
    }

    static {
        CATEGORY = "BedTrap";
        OPEN_CLICK_GUI = new class_304("key.meteor-client.open-click-gui", class_3675.class_307.field_1668, 344, "BedTrap");
    }
}

