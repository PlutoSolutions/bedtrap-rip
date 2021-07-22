/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.entity.fakeplayer;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerEntity;

public class FakePlayerManager {
    private static final /* synthetic */ List<FakePlayerEntity> fakePlayers;

    public static void clear() {
        if (fakePlayers.isEmpty()) {
            return;
        }
        fakePlayers.forEach(FakePlayerEntity::despawn);
        fakePlayers.clear();
    }

    public static List<FakePlayerEntity> getPlayers() {
        return fakePlayers;
    }

    public FakePlayerManager() {
        FakePlayerManager lllllllllllllllllIllIIIlIIllIllI;
    }

    public static int size() {
        return fakePlayers.size();
    }

    static {
        fakePlayers = new ArrayList<FakePlayerEntity>();
    }

    public static void add(String lllllllllllllllllIllIIIlIIlIllII, float lllllllllllllllllIllIIIlIIlIllll, boolean lllllllllllllllllIllIIIlIIlIlIlI) {
        FakePlayerEntity lllllllllllllllllIllIIIlIIlIllIl = new FakePlayerEntity(lllllllllllllllllIllIIIlIIlIllII, lllllllllllllllllIllIIIlIIlIllll, lllllllllllllllllIllIIIlIIlIlIlI);
        fakePlayers.add(lllllllllllllllllIllIIIlIIlIllIl);
    }
}

