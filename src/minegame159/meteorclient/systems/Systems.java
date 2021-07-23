/*
 * Decompiled with CFR 0.151.
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
    private static final List<Runnable> preLoadTasks;
    private static final Map<Class<? extends System>, System<?>> systems;
    private static System<?> config;

    public static void load(File file) {
        MeteorClient.LOG.info("Loading");
        long l = java.lang.System.currentTimeMillis();
        for (Runnable object : preLoadTasks) {
            object.run();
        }
        for (System system : systems.values()) {
            if (system == config) continue;
            system.load(file);
        }
        MeteorClient.LOG.info("Loaded in {} milliseconds", (Object)(java.lang.System.currentTimeMillis() - l));
    }

    private static System<?> add(System<?> system) {
        systems.put(system.getClass(), system);
        MeteorClient.EVENT_BUS.subscribe(system);
        return system;
    }

    static {
        systems = new HashMap();
        preLoadTasks = new ArrayList<Runnable>(1);
    }

    public static void addPreLoadTask(Runnable runnable) {
        preLoadTasks.add(runnable);
    }

    public static void load() {
        Systems.load(null);
    }

    public static <T extends System<?>> T get(Class<T> clazz) {
        return (T)systems.get(clazz);
    }

    public static void save(File file) {
        MeteorClient.LOG.info("Saving");
        long l = java.lang.System.currentTimeMillis();
        for (System<?> system : systems.values()) {
            system.save(file);
        }
        MeteorClient.LOG.info("Saved in {} milliseconds.", (Object)(java.lang.System.currentTimeMillis() - l));
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
        for (System<?> system : systems.values()) {
            if (system == config) continue;
            system.init();
        }
    }

    public static void save() {
        Systems.save(null);
    }
}

