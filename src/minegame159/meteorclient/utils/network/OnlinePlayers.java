/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.network;

import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.network.HttpUtils;
import minegame159.meteorclient.utils.network.MeteorExecutor;

public class OnlinePlayers {
    private static /* synthetic */ long lastPingTime;

    public static void update() {
        long llllllllllllllllIllllIlIlIlIIIlI = System.currentTimeMillis();
        if (llllllllllllllllIllllIlIlIlIIIlI - lastPingTime > 300000L) {
            MeteorExecutor.execute(() -> {
                String llllllllllllllllIllllIlIlIIllllI = "http://meteorclient.com/api/online/ping";
                String llllllllllllllllIllllIlIlIIlllIl = Utils.mc.method_1548().method_1673();
                if (llllllllllllllllIllllIlIlIIlllIl != null && !llllllllllllllllIllllIlIlIIlllIl.isEmpty() && Config.get().sendDataToApi) {
                    llllllllllllllllIllllIlIlIIllllI = String.valueOf(new StringBuilder().append(llllllllllllllllIllllIlIlIIllllI).append("?uuid=").append(llllllllllllllllIllllIlIlIIlllIl));
                }
                HttpUtils.post(llllllllllllllllIllllIlIlIIllllI);
            });
            lastPingTime = llllllllllllllllIllllIlIlIlIIIlI;
        }
    }

    public static void leave() {
        MeteorExecutor.execute(() -> HttpUtils.post("http://meteorclient.com/api/online/leave"));
    }

    public OnlinePlayers() {
        OnlinePlayers llllllllllllllllIllllIlIlIlIIlIl;
    }

    public static void forcePing() {
        lastPingTime = 0L;
    }
}

