/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.network;

import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.network.HttpUtils;
import minegame159.meteorclient.utils.network.MeteorExecutor;

public class OnlinePlayers {
    private static long lastPingTime;

    private static void lambda$leave$1() {
        HttpUtils.post("http://meteorclient.com/api/online/leave");
    }

    public static void update() {
        long l = System.currentTimeMillis();
        if (l - lastPingTime > 300000L) {
            MeteorExecutor.execute(OnlinePlayers::lambda$update$0);
            lastPingTime = l;
        }
    }

    public static void leave() {
        MeteorExecutor.execute(OnlinePlayers::lambda$leave$1);
    }

    private static void lambda$update$0() {
        String string = "http://meteorclient.com/api/online/ping";
        String string2 = Utils.mc.method_1548().method_1673();
        if (string2 != null && !string2.isEmpty() && Config.get().sendDataToApi) {
            string = String.valueOf(new StringBuilder().append(string).append("?uuid=").append(string2));
        }
        HttpUtils.post(string);
    }

    public static void forcePing() {
        lastPingTime = 0L;
    }
}

