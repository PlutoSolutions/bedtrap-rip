/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1146
 *  net.minecraft.class_1291
 *  net.minecraft.class_1299
 *  net.minecraft.class_1792
 *  net.minecraft.class_1887
 *  net.minecraft.class_2248
 *  net.minecraft.class_2394
 *  net.minecraft.class_2396
 *  net.minecraft.class_2561
 *  net.minecraft.class_2588
 *  net.minecraft.class_2960
 *  net.minecraft.class_3544
 *  org.apache.commons.lang3.text.WordUtils
 */
package minegame159.meteorclient.utils.misc;

import java.util.HashMap;
import java.util.Map;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.ResourcePacksReloadedEvent;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1146;
import net.minecraft.class_1291;
import net.minecraft.class_1299;
import net.minecraft.class_1792;
import net.minecraft.class_1887;
import net.minecraft.class_2248;
import net.minecraft.class_2394;
import net.minecraft.class_2396;
import net.minecraft.class_2561;
import net.minecraft.class_2588;
import net.minecraft.class_2960;
import net.minecraft.class_3544;
import org.apache.commons.lang3.text.WordUtils;

public class Names {
    private static final /* synthetic */ Map<class_2396<?>, String> particleTypesNames;
    private static final /* synthetic */ Map<class_2248, String> blockNames;
    private static final /* synthetic */ Map<class_1299<?>, String> entityTypeNames;
    private static final /* synthetic */ Map<class_1291, String> statusEffectNames;
    private static final /* synthetic */ Map<class_1887, String> enchantmentNames;
    private static final /* synthetic */ Map<class_1792, String> itemNames;
    private static final /* synthetic */ Map<class_2960, String> soundNames;

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(Names.class);
    }

    public static String get(class_1792 lIlllllIIlIIII) {
        return itemNames.computeIfAbsent(lIlllllIIlIIII, lIllllIllIlIlI -> class_3544.method_15440((String)lIllllIllIlIlI.method_7848().getString()));
    }

    public static String get(class_1299<?> lIlllllIIIIlll) {
        return entityTypeNames.computeIfAbsent(lIlllllIIIIlll, lIllllIlllIIll -> class_3544.method_15440((String)lIllllIlllIIll.method_5897().getString()));
    }

    public static String getSoundName(class_2960 lIlllllIIIIIlI) {
        return soundNames.computeIfAbsent(lIlllllIIIIIlI, lIllllIllllIlI -> {
            class_1146 lIllllIlllllII = Utils.mc.method_1483().method_4869(lIllllIllllIlI);
            if (lIllllIlllllII == null) {
                return lIllllIllllIlI.method_12832();
            }
            class_2561 lIllllIllllIll = lIllllIlllllII.method_4886();
            if (lIllllIllllIll == null) {
                return lIllllIllllIlI.method_12832();
            }
            return class_3544.method_15440((String)lIllllIllllIll.getString());
        });
    }

    public static String get(class_1291 lIlllllIIlIIll) {
        return statusEffectNames.computeIfAbsent(lIlllllIIlIIll, lIllllIllIIlll -> class_3544.method_15440((String)lIllllIllIIlll.method_5560().getString()));
    }

    @EventHandler
    private static void onResourcePacksReloaded(ResourcePacksReloadedEvent lIlllllIIlIllI) {
        statusEffectNames.clear();
        itemNames.clear();
        blockNames.clear();
        enchantmentNames.clear();
        entityTypeNames.clear();
        particleTypesNames.clear();
        soundNames.clear();
    }

    public static String get(class_2248 lIlllllIIIlllI) {
        return blockNames.computeIfAbsent(lIlllllIIIlllI, lIllllIllIllIl -> class_3544.method_15440((String)lIllllIllIllIl.method_9518().getString()));
    }

    public Names() {
        Names lIlllllIIlIlll;
    }

    public static String get(class_1887 lIlllllIIIlIlI) {
        return enchantmentNames.computeIfAbsent(lIlllllIIIlIlI, lIllllIllIllll -> class_3544.method_15440((String)new class_2588(lIllllIllIllll.method_8184()).getString()));
    }

    static {
        statusEffectNames = new HashMap<class_1291, String>(16);
        itemNames = new HashMap<class_1792, String>(128);
        blockNames = new HashMap<class_2248, String>(128);
        enchantmentNames = new HashMap<class_1887, String>(16);
        entityTypeNames = new HashMap(64);
        particleTypesNames = new HashMap(64);
        soundNames = new HashMap<class_2960, String>(64);
    }

    public static String get(class_2396<?> lIlllllIIIIlII) {
        if (!(lIlllllIIIIlII instanceof class_2394)) {
            return "";
        }
        return particleTypesNames.computeIfAbsent(lIlllllIIIIlII, lIllllIlllIlIl -> WordUtils.capitalize((String)((class_2394)lIllllIlllIlIl).method_10293().substring(10).replace("_", " ")));
    }
}

