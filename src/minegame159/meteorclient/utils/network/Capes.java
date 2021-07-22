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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
    private static final /* synthetic */ List<Cape> TO_RETRY;
    private static final /* synthetic */ Map<String, String> URLS;
    private static final /* synthetic */ String CAPE_OWNERS_URL;
    private static final /* synthetic */ Map<UUID, String> OWNERS;
    private static final /* synthetic */ List<Cape> TO_REGISTER;
    private static final /* synthetic */ String CAPES_URL;
    private static final /* synthetic */ Map<String, Cape> TEXTURES;
    private static final /* synthetic */ List<Cape> TO_REMOVE;

    public static void init() {
        OWNERS.clear();
        URLS.clear();
        TEXTURES.clear();
        TO_REGISTER.clear();
        TO_RETRY.clear();
        TO_REMOVE.clear();
        MeteorExecutor.execute(() -> HttpUtils.getLines("http://meteorclient.com/api/capeowners", lllllllllllllllllIIIlllIIIlIllll -> {
            String[] lllllllllllllllllIIIlllIIIllIIII = lllllllllllllllllIIIlllIIIlIllll.split(" ");
            if (lllllllllllllllllIIIlllIIIllIIII.length >= 2) {
                OWNERS.put(UUID.fromString(lllllllllllllllllIIIlllIIIllIIII[0]), lllllllllllllllllIIIlllIIIllIIII[1]);
                if (!TEXTURES.containsKey(lllllllllllllllllIIIlllIIIllIIII[1])) {
                    TEXTURES.put(lllllllllllllllllIIIlllIIIllIIII[1], new Cape(lllllllllllllllllIIIlllIIIllIIII[1]));
                }
            }
        }));
        MeteorExecutor.execute(() -> HttpUtils.getLines("http://meteorclient.com/api/capes", lllllllllllllllllIIIlllIIIllIlll -> {
            String[] lllllllllllllllllIIIlllIIIllIllI = lllllllllllllllllIIIlllIIIllIlll.split(" ");
            if (lllllllllllllllllIIIlllIIIllIllI.length >= 2 && !URLS.containsKey(lllllllllllllllllIIIlllIIIllIllI[0])) {
                URLS.put(lllllllllllllllllIIIlllIIIllIllI[0], lllllllllllllllllIIIlllIIIllIllI[1]);
            }
        }));
    }

    public Capes() {
        Capes lllllllllllllllllIIIlllIIlIlIIlI;
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

    public static class_2960 get(class_1657 lllllllllllllllllIIIlllIIlIIlIlI) {
        String lllllllllllllllllIIIlllIIlIIlIll = OWNERS.get(lllllllllllllllllIIIlllIIlIIlIlI.method_5667());
        if (lllllllllllllllllIIIlllIIlIIlIll != null) {
            Cape lllllllllllllllllIIIlllIIlIIllIl = TEXTURES.get(lllllllllllllllllIIIlllIIlIIlIll);
            if (lllllllllllllllllIIIlllIIlIIllIl == null) {
                return null;
            }
            if (lllllllllllllllllIIIlllIIlIIllIl.isDownloaded()) {
                return lllllllllllllllllIIIlllIIlIIllIl;
            }
            lllllllllllllllllIIIlllIIlIIllIl.download();
            return null;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void tick() {
        List<Cape> lllllllllllllllllIIIlllIIIllllll = TO_REGISTER;
        synchronized (lllllllllllllllllIIIlllIIIllllll) {
            for (Cape lllllllllllllllllIIIlllIIlIIIIIl : TO_REGISTER) {
                lllllllllllllllllIIIlllIIlIIIIIl.register();
            }
            TO_REGISTER.clear();
        }
        lllllllllllllllllIIIlllIIIllllll = TO_RETRY;
        synchronized (lllllllllllllllllIIIlllIIIllllll) {
            TO_RETRY.removeIf(Cape::tick);
        }
        lllllllllllllllllIIIlllIIIllllll = TO_REMOVE;
        synchronized (lllllllllllllllllIIIlllIIIllllll) {
            for (Cape lllllllllllllllllIIIlllIIlIIIIII : TO_REMOVE) {
                URLS.remove(lllllllllllllllllIIIlllIIlIIIIII.name);
                TEXTURES.remove(lllllllllllllllllIIIlllIIlIIIIII.name);
                TO_REGISTER.remove((Object)lllllllllllllllllIIIlllIIlIIIIII);
                TO_RETRY.remove((Object)lllllllllllllllllIIIlllIIlIIIIII);
            }
            TO_REMOVE.clear();
        }
    }

    private static class Cape
    extends class_2960 {
        private /* synthetic */ int retryTimer;
        private /* synthetic */ boolean downloading;
        private final /* synthetic */ String name;
        private /* synthetic */ class_1011 img;
        private /* synthetic */ boolean downloaded;

        public boolean tick() {
            Cape llIIIIIIlIlIlIl;
            if (llIIIIIIlIlIlIl.retryTimer > 0) {
                --llIIIIIIlIlIlIl.retryTimer;
            } else {
                llIIIIIIlIlIlIl.download();
                return true;
            }
            return false;
        }

        public Cape(String llIIIIIIlIlllIl) {
            super("meteor-client", String.valueOf(new StringBuilder().append("capes/").append(llIIIIIIlIlllIl)));
            Cape llIIIIIIllIIIII;
            llIIIIIIllIIIII.name = llIIIIIIlIlllIl;
        }

        public void register() {
            Cape llIIIIIIlIllIII;
            Utils.mc.method_1531().method_4616((class_2960)llIIIIIIlIllIII, (class_1044)new class_1043(llIIIIIIlIllIII.img));
            llIIIIIIlIllIII.img = null;
            llIIIIIIlIllIII.downloading = false;
            llIIIIIIlIllIII.downloaded = true;
        }

        public boolean isDownloaded() {
            Cape llIIIIIIlIlIIlI;
            return llIIIIIIlIlIIlI.downloaded;
        }

        public void download() {
            Cape llIIIIIIlIllIll;
            if (llIIIIIIlIllIll.downloaded || llIIIIIIlIllIll.downloading || llIIIIIIlIllIll.retryTimer > 0) {
                return;
            }
            llIIIIIIlIllIll.downloading = true;
            MeteorExecutor.execute(() -> {
                try {
                    Cape llIIIIIIlIIIIIl;
                    String llIIIIIIlIIIlIl = (String)URLS.get(llIIIIIIlIIIIIl.name);
                    if (llIIIIIIlIIIlIl == null) {
                        List llIIIIIIIllllll = TO_RETRY;
                        synchronized (llIIIIIIIllllll) {
                            TO_REMOVE.add(llIIIIIIlIIIIIl);
                            llIIIIIIlIIIIIl.downloading = false;
                            return;
                        }
                    }
                    InputStream llIIIIIIlIIIlII = HttpUtils.get(llIIIIIIlIIIlIl);
                    if (llIIIIIIlIIIlII == null) {
                        List llIIIIIIIlllllI = TO_RETRY;
                        synchronized (llIIIIIIIlllllI) {
                            TO_RETRY.add(llIIIIIIlIIIIIl);
                            llIIIIIIlIIIIIl.retryTimer = 200;
                            llIIIIIIlIIIIIl.downloading = false;
                            return;
                        }
                    }
                    llIIIIIIlIIIIIl.img = class_1011.method_4309((InputStream)llIIIIIIlIIIlII);
                    List llIIIIIIIlllllI = TO_REGISTER;
                    synchronized (llIIIIIIIlllllI) {
                        TO_REGISTER.add(llIIIIIIlIIIIIl);
                    }
                }
                catch (IOException llIIIIIIlIIIIll) {
                    llIIIIIIlIIIIll.printStackTrace();
                }
            });
        }
    }
}

