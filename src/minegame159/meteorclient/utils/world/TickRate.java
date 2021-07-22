/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2761
 */
package minegame159.meteorclient.utils.world;

import java.util.Arrays;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.GameJoinedEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2761;

public class TickRate {
    private int nextIndex = 0;
    public static TickRate INSTANCE = new TickRate();
    private long timeLastTimeUpdate = -1L;
    private long timeGameJoined;
    private final float[] tickRates = new float[20];

    private TickRate() {
        MeteorClient.EVENT_BUS.subscribe(this);
    }

    @EventHandler
    private void onGameJoined(GameJoinedEvent gameJoinedEvent) {
        Arrays.fill(this.tickRates, 0.0f);
        this.nextIndex = 0;
        this.timeLastTimeUpdate = -1L;
        this.timeGameJoined = System.currentTimeMillis();
    }

    @EventHandler
    private void onReceivePacket(PacketEvent.Receive receive) {
        if (receive.packet instanceof class_2761) {
            if (this.timeLastTimeUpdate != -1L) {
                float f = (float)(System.currentTimeMillis() - this.timeLastTimeUpdate) / 1000.0f;
                this.tickRates[this.nextIndex % this.tickRates.length] = Utils.clamp(20.0f / f, 0.0f, 20.0f);
                ++this.nextIndex;
            }
            this.timeLastTimeUpdate = System.currentTimeMillis();
        }
    }

    public float getTimeSinceLastTick() {
        if (System.currentTimeMillis() - this.timeGameJoined < 4000L) {
            return 0.0f;
        }
        return (float)(System.currentTimeMillis() - this.timeLastTimeUpdate) / 1000.0f;
    }

    public float getTickRate() {
        if (!Utils.canUpdate()) {
            return 0.0f;
        }
        if (System.currentTimeMillis() - this.timeGameJoined < 4000L) {
            return 20.0f;
        }
        float f = 0.0f;
        float f2 = 0.0f;
        for (float f3 : this.tickRates) {
            if (!(f3 > 0.0f)) continue;
            f2 += f3;
            f += 1.0f;
            if (4 >= 3) continue;
            return 0.0f;
        }
        return Utils.clamp(f2 / f, 0.0f, 20.0f);
    }
}

