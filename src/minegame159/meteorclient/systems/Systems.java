/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.accounts.Accounts;
import minegame159.meteorclient.systems.commands.Commands;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.macros.Macros;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.profiles.Profiles;
import minegame159.meteorclient.systems.proxies.Proxies;
import minegame159.meteorclient.systems.waypoints.Waypoints;

public class Systems {
    private static final /* synthetic */ List<Runnable> preLoadTasks;
    private static final /* synthetic */ Map<Class<? extends System>, System<?>> systems;
    private static /* synthetic */ System<?> config;

    public static void load(File llllllllllllllllllIlIIlIlIIIlIlI) {
        MeteorClient.LOG.info("Loading");
        long llllllllllllllllllIlIIlIlIIIlIll = java.lang.System.currentTimeMillis();
        for (Runnable runnable : preLoadTasks) {
            runnable.run();
        }
        for (System system : systems.values()) {
            if (system == config) continue;
            system.load(llllllllllllllllllIlIIlIlIIIlIlI);
        }
        MeteorClient.LOG.info("Loaded in {} milliseconds", (Object)(java.lang.System.currentTimeMillis() - llllllllllllllllllIlIIlIlIIIlIll));
    }

    private static System<?> add(System<?> llllllllllllllllllIlIIlIlIlIIIlI) {
        systems.put(llllllllllllllllllIlIIlIlIlIIIlI.getClass(), llllllllllllllllllIlIIlIlIlIIIlI);
        MeteorClient.EVENT_BUS.subscribe(llllllllllllllllllIlIIlIlIlIIIlI);
        return llllllllllllllllllIlIIlIlIlIIIlI;
    }

    static {
        systems = new HashMap();
        preLoadTasks = new ArrayList<Runnable>(1);
    }

    public static void addPreLoadTask(Runnable llllllllllllllllllIlIIlIlIIlIlII) {
        preLoadTasks.add(llllllllllllllllllIlIIlIlIIlIlII);
    }

    public static void load() {
        Systems.load(null);
    }

    public static <T extends System<?>> T get(Class<T> llllllllllllllllllIlIIlIlIIIIlIl) {
        return (T)systems.get(llllllllllllllllllIlIIlIlIIIIlIl);
    }

    public static void save(File llllllllllllllllllIlIIlIlIIllIll) {
        MeteorClient.LOG.info("Saving");
        long llllllllllllllllllIlIIlIlIIllIlI = java.lang.System.currentTimeMillis();
        for (System<?> llllllllllllllllllIlIIlIlIIlllII : systems.values()) {
            llllllllllllllllllIlIIlIlIIlllII.save(llllllllllllllllllIlIIlIlIIllIll);
        }
        MeteorClient.LOG.info("Saved in {} milliseconds.", (Object)(java.lang.System.currentTimeMillis() - llllllllllllllllllIlIIlIlIIllIlI));
    }

    public static void init() {
        config = Systems.add(new Config());
        config.load();
        config.init();
        Systems.add(new Modules());
        Systems.add(new Commands());
        Systems.add(new Friends());
        Systems.add(new Macros());
        Systems.add(new Accounts());
        Systems.add(new Waypoints());
        Systems.add(new Profiles());
        Systems.add(new Proxies());
        for (System<?> llllllllllllllllllIlIIlIlIlIIllI : systems.values()) {
            if (llllllllllllllllllIlIIlIlIlIIllI == config) continue;
            llllllllllllllllllIlIIlIlIlIIllI.init();
        }
    }

    public static void save() {
        Systems.save(null);
    }

    public Systems() {
        Systems llllllllllllllllllIlIIlIlIlIlIlI;
    }
}

