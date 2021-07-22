/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1011
 *  net.minecraft.class_1043
 *  net.minecraft.class_1044
 *  net.minecraft.class_1657
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.utils.network;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.network.HttpUtils;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1044;
import net.minecraft.class_1657;
import net.minecraft.class_2960;

public class Capes {
    private static final List<Cape> TO_RETRY;
    private static final Map<String, String> URLS;
    private static final String CAPE_OWNERS_URL;
    private static final Map<UUID, String> OWNERS;
    private static final List<Cape> TO_REGISTER;
    private static final String CAPES_URL;
    private static final Map<String, Cape> TEXTURES;
    private static final List<Cape> TO_REMOVE;

    static List access$300() {
        return TO_REMOVE;
    }

    private static void lambda$init$3() {
        HttpUtils.getLines("http://meteorclient.com/api/capes", Capes::lambda$init$2);
    }

    static Map access$100() {
        return URLS;
    }

    static List access$200() {
        return TO_RETRY;
    }

    private static void lambda$init$1() {
        HttpUtils.getLines("http://meteorclient.com/api/capeowners", Capes::lambda$init$0);
    }

    public static void init() {
        OWNERS.clear();
        URLS.clear();
        TEXTURES.clear();
        TO_REGISTER.clear();
        TO_RETRY.clear();
        TO_REMOVE.clear();
        MeteorExecutor.execute(Capes::lambda$init$1);
        MeteorExecutor.execute(Capes::lambda$init$3);
    }

    private static void lambda$init$0(String string) {
        String[] arrstring = string.split(" ");
        if (arrstring.length >= 2) {
            OWNERS.put(UUID.fromString(arrstring[0]), arrstring[1]);
            if (!TEXTURES.containsKey(arrstring[1])) {
                TEXTURES.put(arrstring[1], new Cape(arrstring[1]));
            }
        }
    }

    static {
        CAPES_URL = "http://meteorclient.com/api/capes";
        CAPE_OWNERS_URL = "http://meteorclient.com/api/capeowners";
        OWNERS = new HashMap<UUID, String>();
        URLS = new HashMap<String, String>();
        TEXTURES = new HashMap<String, Cape>();
        TO_REGISTER = new ArrayList<Cape>();
        TO_RETRY = new ArrayList<Cape>();
        TO_REMOVE = new ArrayList<Cape>();
    }

    private static void lambda$init$2(String string) {
        String[] arrstring = string.split(" ");
        if (arrstring.length >= 2 && !URLS.containsKey(arrstring[0])) {
            URLS.put(arrstring[0], arrstring[1]);
        }
    }

    static List access$400() {
        return TO_REGISTER;
    }

    public static class_2960 get(class_1657 class_16572) {
        String string = OWNERS.get(class_16572.method_5667());
        if (string != null) {
            Cape cape = TEXTURES.get(string);
            if (cape == null) {
                return null;
            }
            if (cape.isDownloaded()) {
                return cape;
            }
            cape.download();
            return null;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void tick() {
        List<Cape> list = TO_REGISTER;
        synchronized (list) {
            for (Cape cape : TO_REGISTER) {
                cape.register();
            }
            TO_REGISTER.clear();
        }
        list = TO_RETRY;
        synchronized (list) {
            TO_RETRY.removeIf(Cape::tick);
        }
        list = TO_REMOVE;
        synchronized (list) {
            Iterator<Cape> iterator = TO_REMOVE.iterator();
            while (true) {
                Cape cape;
                if (!iterator.hasNext()) {
                    TO_REMOVE.clear();
                    return;
                }
                cape = iterator.next();
                URLS.remove(Cape.access$000(cape));
                TEXTURES.remove(Cape.access$000(cape));
                TO_REGISTER.remove((Object)cape);
                TO_RETRY.remove((Object)cape);
            }
        }
    }

    private static class Cape
    extends class_2960 {
        private int retryTimer;
        private boolean downloading;
        private final String name;
        private class_1011 img;
        private boolean downloaded;

        public int compareTo(Object object) {
            return super.method_12833((class_2960)object);
        }

        public boolean tick() {
            if (this.retryTimer > 0) {
                --this.retryTimer;
                return false;
            }
            this.download();
            return true;
        }

        public Cape(String string) {
            super("meteor-client", String.valueOf(new StringBuilder().append("capes/").append(string)));
            this.name = string;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        private void lambda$download$0() {
            var1_1 = (String)Capes.access$100().get(this.name);
            if (var1_1 != null) ** GOTO lbl-1000
            var2_2 = Capes.access$200();
            // MONITORENTER : var2_2
            Capes.access$300().add(this);
            this.downloading = false;
            // MONITOREXIT : var2_2
            return;
lbl-1000:
            // 1 sources

            {
                var2_3 = HttpUtils.get(var1_1);
                if (var2_3 == null) {
                    var3_4 = Capes.access$200();
                    // MONITORENTER : var3_4
                    Capes.access$200().add(this);
                    this.retryTimer = 200;
                    this.downloading = false;
                    // MONITOREXIT : var3_4
                    return;
                }
                this.img = class_1011.method_4309((InputStream)var2_3);
                var3_5 = Capes.access$400();
                // MONITORENTER : var3_5
                Capes.access$400().add(this);
                // MONITOREXIT : var3_5
                return;
            }
        }

        public void register() {
            Utils.mc.method_1531().method_4616((class_2960)this, (class_1044)new class_1043(this.img));
            this.img = null;
            this.downloading = false;
            this.downloaded = true;
        }

        public boolean isDownloaded() {
            return this.downloaded;
        }

        public void download() {
            if (this.downloaded || this.downloading || this.retryTimer > 0) {
                return;
            }
            this.downloading = true;
            MeteorExecutor.execute(this::lambda$download$0);
        }

        static String access$000(Cape cape) {
            return cape.name;
        }
    }
}

