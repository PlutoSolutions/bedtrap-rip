/*
 * Decompiled with CFR 0.151.
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
    private static final Map<class_2396<?>, String> particleTypesNames;
    private static final Map<class_2248, String> blockNames;
    private static final Map<class_1299<?>, String> entityTypeNames;
    private static final Map<class_1291, String> statusEffectNames;
    private static final Map<class_1887, String> enchantmentNames;
    private static final Map<class_1792, String> itemNames;
    private static final Map<class_2960, String> soundNames;

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(Names.class);
    }

    public static String get(class_1792 class_17922) {
        return itemNames.computeIfAbsent(class_17922, Names::lambda$get$1);
    }

    private static String lambda$get$4(class_1299 class_12992) {
        return class_3544.method_15440((String)class_12992.method_5897().getString());
    }

    public static String get(class_1299<?> class_12992) {
        return entityTypeNames.computeIfAbsent(class_12992, Names::lambda$get$4);
    }

    public static String getSoundName(class_2960 class_29602) {
        return soundNames.computeIfAbsent(class_29602, Names::lambda$getSoundName$6);
    }

    public static String get(class_1291 class_12912) {
        return statusEffectNames.computeIfAbsent(class_12912, Names::lambda$get$0);
    }

    private static String lambda$get$3(class_1887 class_18872) {
        return class_3544.method_15440((String)new class_2588(class_18872.method_8184()).getString());
    }

    @EventHandler
    private static void onResourcePacksReloaded(ResourcePacksReloadedEvent resourcePacksReloadedEvent) {
        statusEffectNames.clear();
        itemNames.clear();
        blockNames.clear();
        enchantmentNames.clear();
        entityTypeNames.clear();
        particleTypesNames.clear();
        soundNames.clear();
    }

    public static String get(class_2248 class_22482) {
        return blockNames.computeIfAbsent(class_22482, Names::lambda$get$2);
    }

    private static String lambda$get$5(class_2396 class_23962) {
        return WordUtils.capitalize((String)((class_2394)class_23962).method_10293().substring(10).replace("_", " "));
    }

    private static String lambda$get$0(class_1291 class_12912) {
        return class_3544.method_15440((String)class_12912.method_5560().getString());
    }

    public static String get(class_1887 class_18872) {
        return enchantmentNames.computeIfAbsent(class_18872, Names::lambda$get$3);
    }

    private static String lambda$get$2(class_2248 class_22482) {
        return class_3544.method_15440((String)class_22482.method_9518().getString());
    }

    private static String lambda$get$1(class_1792 class_17922) {
        return class_3544.method_15440((String)class_17922.method_7848().getString());
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

    public static String get(class_2396<?> class_23962) {
        if (!(class_23962 instanceof class_2394)) {
            return "";
        }
        return particleTypesNames.computeIfAbsent(class_23962, Names::lambda$get$5);
    }

    private static String lambda$getSoundName$6(class_2960 class_29602) {
        class_1146 class_11462 = Utils.mc.method_1483().method_4869(class_29602);
        if (class_11462 == null) {
            return class_29602.method_12832();
        }
        class_2561 class_25612 = class_11462.method_4886();
        if (class_25612 == null) {
            return class_29602.method_12832();
        }
        return class_3544.method_15440((String)class_25612.getString());
    }
}

