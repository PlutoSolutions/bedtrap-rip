/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.reflect.TypeToken
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  net.minecraft.class_1657
 */
package minegame159.meteorclient.utils.misc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.utils.json.UUIDSerializer;
import minegame159.meteorclient.utils.network.HttpUtils;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import net.minecraft.class_1657;

public class MeteorPlayers {
    private static final /* synthetic */ List<UUID> toCheck;
    private static final /* synthetic */ Type uuidBooleanMapType;
    private static final /* synthetic */ Object2BooleanMap<UUID> players;
    private static final /* synthetic */ Gson gson;
    private static /* synthetic */ int checkTimer;

    static {
        uuidBooleanMapType = new TypeToken<Map<UUID, Boolean>>(){
            {
                1 llllllllllllllllllIIlIIIIIlIIIlI;
            }
        }.getType();
        players = new Object2BooleanOpenHashMap();
        toCheck = new ArrayList<UUID>();
        gson = new GsonBuilder().registerTypeAdapter(UUID.class, (Object)new UUIDSerializer()).create();
    }

    public static boolean get(class_1657 lllllllllllllllllIIllllIllIIllIl) {
        return MeteorPlayers.get(lllllllllllllllllIIllllIllIIllIl.method_7334().getId());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    private static void onGameLeft(GameLeftEvent lllllllllllllllllIIllllIllllIIII) {
        Object lllllllllllllllllIIllllIlllIllll = players;
        synchronized (lllllllllllllllllIIllllIlllIllll) {
            players.clear();
        }
        lllllllllllllllllIIllllIlllIllll = toCheck;
        synchronized (lllllllllllllllllIIllllIlllIllll) {
            toCheck.clear();
        }
    }

    @EventHandler
    private static void onTick(TickEvent.Post lllllllllllllllllIIllllIlllIllII) {
        if (toCheck.isEmpty()) {
            return;
        }
        if (checkTimer >= 10) {
            checkTimer = 0;
            MeteorExecutor.execute(MeteorPlayers::check);
        } else {
            ++checkTimer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    private static void check() {
        void lllllllllllllllllIIllllIlllIIIlI;
        List<UUID> lllllllllllllllllIIllllIllIllllI = toCheck;
        synchronized (lllllllllllllllllIIllllIllIllllI) {
            String lllllllllllllllllIIllllIlllIIlII = gson.toJson(toCheck);
            toCheck.clear();
        }
        InputStream lllllllllllllllllIIllllIlllIIIIl = HttpUtils.post("http://meteorclient.com/api/online/usingMeteor", (String)lllllllllllllllllIIllllIlllIIIlI);
        Map lllllllllllllllllIIllllIlllIIIII = (Map)gson.fromJson((Reader)new InputStreamReader(lllllllllllllllllIIllllIlllIIIIl, StandardCharsets.UTF_8), uuidBooleanMapType);
        Object2BooleanMap<UUID> lllllllllllllllllIIllllIllIlllII = players;
        synchronized (lllllllllllllllllIIllllIllIlllII) {
            for (UUID lllllllllllllllllIIllllIlllIIIll : lllllllllllllllllIIllllIlllIIIII.keySet()) {
                players.put((Object)lllllllllllllllllIIllllIlllIIIll, (Boolean)lllllllllllllllllIIllllIlllIIIII.get(lllllllllllllllllIIllllIlllIIIll));
            }
        }
    }

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(MeteorPlayers.class);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean get(UUID lllllllllllllllllIIllllIllIlIlII) {
        if (players.containsKey((Object)lllllllllllllllllIIllllIllIlIlII)) {
            return players.getBoolean((Object)lllllllllllllllllIIllllIllIlIlII);
        }
        List<UUID> lllllllllllllllllIIllllIllIlIIlI = toCheck;
        synchronized (lllllllllllllllllIIllllIllIlIIlI) {
            toCheck.add(lllllllllllllllllIIllllIllIlIlII);
        }
        lllllllllllllllllIIllllIllIlIIlI = players;
        synchronized (lllllllllllllllllIIllllIllIlIIlI) {
            players.put((Object)lllllllllllllllllIIllllIllIlIlII, false);
        }
        return false;
    }

    public MeteorPlayers() {
        MeteorPlayers lllllllllllllllllIIllllIllllIlII;
    }
}

