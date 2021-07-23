/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.entity.fakeplayer;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerEntity;

public class FakePlayerManager {
    private static final List<FakePlayerEntity> fakePlayers = new ArrayList<FakePlayerEntity>();

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

    public static int size() {
        return fakePlayers.size();
    }

    public static void add(String string, float f, boolean bl) {
        FakePlayerEntity fakePlayerEntity = new FakePlayerEntity(string, f, bl);
        fakePlayers.add(fakePlayerEntity);
    }
}

