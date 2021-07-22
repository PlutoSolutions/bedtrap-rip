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
    private /* synthetic */ int nextIndex;
    public static /* synthetic */ TickRate INSTANCE;
    private /* synthetic */ long timeLastTimeUpdate;
    private /* synthetic */ long timeGameJoined;
    private final /* synthetic */ float[] tickRates;

    private TickRate() {
        TickRate llllllllllllllllllIlllIllIlllllI;
        llllllllllllllllllIlllIllIlllllI.tickRates = new float[20];
        llllllllllllllllllIlllIllIlllllI.nextIndex = 0;
        llllllllllllllllllIlllIllIlllllI.timeLastTimeUpdate = -1L;
        MeteorClient.EVENT_BUS.subscribe(llllllllllllllllllIlllIllIlllllI);
    }

    @EventHandler
    private void onGameJoined(GameJoinedEvent llllllllllllllllllIlllIllIllIIlI) {
        TickRate llllllllllllllllllIlllIllIllIIll;
        Arrays.fill(llllllllllllllllllIlllIllIllIIll.tickRates, 0.0f);
        llllllllllllllllllIlllIllIllIIll.nextIndex = 0;
        llllllllllllllllllIlllIllIllIIll.timeLastTimeUpdate = -1L;
        llllllllllllllllllIlllIllIllIIll.timeGameJoined = System.currentTimeMillis();
    }

    @EventHandler
    private void onReceivePacket(PacketEvent.Receive llllllllllllllllllIlllIllIllIllI) {
        if (llllllllllllllllllIlllIllIllIllI.packet instanceof class_2761) {
            TickRate llllllllllllllllllIlllIllIllIlll;
            if (llllllllllllllllllIlllIllIllIlll.timeLastTimeUpdate != -1L) {
                float llllllllllllllllllIlllIllIlllIlI = (float)(System.currentTimeMillis() - llllllllllllllllllIlllIllIllIlll.timeLastTimeUpdate) / 1000.0f;
                llllllllllllllllllIlllIllIllIlll.tickRates[llllllllllllllllllIlllIllIllIlll.nextIndex % llllllllllllllllllIlllIllIllIlll.tickRates.length] = Utils.clamp(20.0f / llllllllllllllllllIlllIllIlllIlI, 0.0f, 20.0f);
                ++llllllllllllllllllIlllIllIllIlll.nextIndex;
            }
            llllllllllllllllllIlllIllIllIlll.timeLastTimeUpdate = System.currentTimeMillis();
        }
    }

    static {
        INSTANCE = new TickRate();
    }

    public float getTimeSinceLastTick() {
        TickRate llllllllllllllllllIlllIllIIlllII;
        if (System.currentTimeMillis() - llllllllllllllllllIlllIllIIlllII.timeGameJoined < 4000L) {
            return 0.0f;
        }
        return (float)(System.currentTimeMillis() - llllllllllllllllllIlllIllIIlllII.timeLastTimeUpdate) / 1000.0f;
    }

    public float getTickRate() {
        TickRate llllllllllllllllllIlllIllIlIlIII;
        if (!Utils.canUpdate()) {
            return 0.0f;
        }
        if (System.currentTimeMillis() - llllllllllllllllllIlllIllIlIlIII.timeGameJoined < 4000L) {
            return 20.0f;
        }
        float llllllllllllllllllIlllIllIlIIlll = 0.0f;
        float llllllllllllllllllIlllIllIlIIllI = 0.0f;
        for (float llllllllllllllllllIlllIllIlIlIIl : llllllllllllllllllIlllIllIlIlIII.tickRates) {
            if (!(llllllllllllllllllIlllIllIlIlIIl > 0.0f)) continue;
            llllllllllllllllllIlllIllIlIIllI += llllllllllllllllllIlllIllIlIlIIl;
            llllllllllllllllllIlllIllIlIIlll += 1.0f;
        }
        return Utils.clamp(llllllllllllllllllIlllIllIlIIllI / llllllllllllllllllIlllIllIlIIlll, 0.0f, 20.0f);
    }
}

