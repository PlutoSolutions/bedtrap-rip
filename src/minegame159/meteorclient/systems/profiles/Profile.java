/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  org.apache.commons.io.FileUtils
 */
package minegame159.meteorclient.systems.profiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.accounts.Accounts;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.macros.Macros;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.profiles.Profiles;
import minegame159.meteorclient.systems.waypoints.Waypoints;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import org.apache.commons.io.FileUtils;

public class Profile
implements ISerializable<Profile> {
    public /* synthetic */ boolean accounts;
    public /* synthetic */ boolean friends;
    public /* synthetic */ String name;
    public /* synthetic */ List<String> loadOnJoinIps;
    public /* synthetic */ boolean modules;
    public /* synthetic */ boolean macros;
    public /* synthetic */ boolean onLaunch;
    public /* synthetic */ boolean config;
    public /* synthetic */ boolean waypoints;

    @Override
    public Profile fromTag(class_2487 lllllllllllllllllIllIlllIlIIIlIl) {
        Profile lllllllllllllllllIllIlllIlIIIlII;
        lllllllllllllllllIllIlllIlIIIlII.name = lllllllllllllllllIllIlllIlIIIlIl.method_10558("name");
        lllllllllllllllllIllIlllIlIIIlII.onLaunch = lllllllllllllllllIllIlllIlIIIlIl.method_10545("onLaunch") && lllllllllllllllllIllIlllIlIIIlIl.method_10577("onLaunch");
        lllllllllllllllllIllIlllIlIIIlII.accounts = lllllllllllllllllIllIlllIlIIIlIl.method_10545("accounts") && lllllllllllllllllIllIlllIlIIIlIl.method_10577("accounts");
        lllllllllllllllllIllIlllIlIIIlII.config = lllllllllllllllllIllIlllIlIIIlIl.method_10545("config") && lllllllllllllllllIllIlllIlIIIlIl.method_10577("config");
        lllllllllllllllllIllIlllIlIIIlII.friends = lllllllllllllllllIllIlllIlIIIlIl.method_10545("friends") && lllllllllllllllllIllIlllIlIIIlIl.method_10577("friends");
        lllllllllllllllllIllIlllIlIIIlII.macros = lllllllllllllllllIllIlllIlIIIlIl.method_10545("macros") && lllllllllllllllllIllIlllIlIIIlIl.method_10577("macros");
        lllllllllllllllllIllIlllIlIIIlII.modules = lllllllllllllllllIllIlllIlIIIlIl.method_10545("modules") && lllllllllllllllllIllIlllIlIIIlIl.method_10577("modules");
        lllllllllllllllllIllIlllIlIIIlII.waypoints = lllllllllllllllllIllIlllIlIIIlIl.method_10545("waypoints") && lllllllllllllllllIllIlllIlIIIlIl.method_10577("waypoints");
        lllllllllllllllllIllIlllIlIIIlII.loadOnJoinIps.clear();
        if (lllllllllllllllllIllIlllIlIIIlIl.method_10545("loadOnJoinIps")) {
            class_2499 lllllllllllllllllIllIlllIlIIIlll = lllllllllllllllllIllIlllIlIIIlIl.method_10554("loadOnJoinIps", 8);
            for (class_2520 lllllllllllllllllIllIlllIlIIlIII : lllllllllllllllllIllIlllIlIIIlll) {
                lllllllllllllllllIllIlllIlIIIlII.loadOnJoinIps.add(lllllllllllllllllIllIlllIlIIlIII.method_10714());
            }
        }
        return lllllllllllllllllIllIlllIlIIIlII;
    }

    public void save(System<?> lllllllllllllllllIllIlllIlllIIlI) {
        Profile lllllllllllllllllIllIlllIlllIIll;
        File lllllllllllllllllIllIlllIlllIlII = new File(Profiles.FOLDER, lllllllllllllllllIllIlllIlllIIll.name);
        lllllllllllllllllIllIlllIlllIIlI.save(lllllllllllllllllIllIlllIlllIlII);
    }

    public void load(System<?> lllllllllllllllllIllIllllIIIIIIl) {
        Profile lllllllllllllllllIllIllllIIIIlIl;
        File lllllllllllllllllIllIllllIIIIIll = new File(Profiles.FOLDER, lllllllllllllllllIllIllllIIIIlIl.name);
        lllllllllllllllllIllIllllIIIIIIl.load(lllllllllllllllllIllIllllIIIIIll);
    }

    @Override
    public class_2487 toTag() {
        Profile lllllllllllllllllIllIlllIlIlIIlI;
        class_2487 lllllllllllllllllIllIlllIlIlIlII = new class_2487();
        lllllllllllllllllIllIlllIlIlIlII.method_10582("name", lllllllllllllllllIllIlllIlIlIIlI.name);
        lllllllllllllllllIllIlllIlIlIlII.method_10556("onLaunch", lllllllllllllllllIllIlllIlIlIIlI.onLaunch);
        lllllllllllllllllIllIlllIlIlIlII.method_10556("accounts", lllllllllllllllllIllIlllIlIlIIlI.accounts);
        lllllllllllllllllIllIlllIlIlIlII.method_10556("config", lllllllllllllllllIllIlllIlIlIIlI.config);
        lllllllllllllllllIllIlllIlIlIlII.method_10556("friends", lllllllllllllllllIllIlllIlIlIIlI.friends);
        lllllllllllllllllIllIlllIlIlIlII.method_10556("macros", lllllllllllllllllIllIlllIlIlIIlI.macros);
        lllllllllllllllllIllIlllIlIlIlII.method_10556("modules", lllllllllllllllllIllIlllIlIlIIlI.modules);
        lllllllllllllllllIllIlllIlIlIlII.method_10556("waypoints", lllllllllllllllllIllIlllIlIlIIlI.waypoints);
        lllllllllllllllllIllIlllIlIlIIlI.loadOnJoinIps.removeIf(String::isEmpty);
        class_2499 lllllllllllllllllIllIlllIlIlIIll = new class_2499();
        for (String lllllllllllllllllIllIlllIlIlIllI : lllllllllllllllllIllIlllIlIlIIlI.loadOnJoinIps) {
            lllllllllllllllllIllIlllIlIlIIll.add((Object)class_2519.method_23256((String)lllllllllllllllllIllIlllIlIlIllI));
        }
        lllllllllllllllllIllIlllIlIlIlII.method_10566("loadOnJoinIps", (class_2520)lllllllllllllllllIllIlllIlIlIIll);
        return lllllllllllllllllIllIlllIlIlIlII;
    }

    public Profile set(Profile lllllllllllllllllIllIlllIIllllII) {
        Profile lllllllllllllllllIllIlllIIlllIll;
        lllllllllllllllllIllIlllIIlllIll.name = lllllllllllllllllIllIlllIIllllII.name;
        lllllllllllllllllIllIlllIIlllIll.onLaunch = lllllllllllllllllIllIlllIIllllII.onLaunch;
        lllllllllllllllllIllIlllIIlllIll.loadOnJoinIps = lllllllllllllllllIllIlllIIllllII.loadOnJoinIps;
        lllllllllllllllllIllIlllIIlllIll.accounts = lllllllllllllllllIllIlllIIllllII.accounts;
        lllllllllllllllllIllIlllIIlllIll.config = lllllllllllllllllIllIlllIIllllII.config;
        lllllllllllllllllIllIlllIIlllIll.friends = lllllllllllllllllIllIlllIIllllII.friends;
        lllllllllllllllllIllIlllIIlllIll.macros = lllllllllllllllllIllIlllIIllllII.macros;
        lllllllllllllllllIllIlllIIlllIll.modules = lllllllllllllllllIllIlllIIllllII.modules;
        lllllllllllllllllIllIlllIIlllIll.waypoints = lllllllllllllllllIllIlllIIllllII.waypoints;
        return lllllllllllllllllIllIlllIIlllIll;
    }

    public void delete(System<?> lllllllllllllllllIllIlllIllIIllI) {
        Profile lllllllllllllllllIllIlllIllIIlll;
        File lllllllllllllllllIllIlllIllIIlIl = new File(new File(Profiles.FOLDER, lllllllllllllllllIllIlllIllIIlll.name), lllllllllllllllllIllIlllIllIIllI.getFile().getName());
        lllllllllllllllllIllIlllIllIIlIl.delete();
    }

    public void delete() {
        try {
            Profile lllllllllllllllllIllIlllIlIllllI;
            FileUtils.deleteDirectory((File)new File(Profiles.FOLDER, lllllllllllllllllIllIlllIlIllllI.name));
        }
        catch (IOException lllllllllllllllllIllIlllIlIlllll) {
            lllllllllllllllllIllIlllIlIlllll.printStackTrace();
        }
    }

    public Profile() {
        Profile lllllllllllllllllIllIllllIIIlIlI;
        lllllllllllllllllIllIllllIIIlIlI.name = "";
        lllllllllllllllllIllIllllIIIlIlI.onLaunch = false;
        lllllllllllllllllIllIllllIIIlIlI.loadOnJoinIps = new ArrayList<String>();
        lllllllllllllllllIllIllllIIIlIlI.accounts = false;
        lllllllllllllllllIllIllllIIIlIlI.config = true;
        lllllllllllllllllIllIllllIIIlIlI.friends = false;
        lllllllllllllllllIllIllllIIIlIlI.macros = true;
        lllllllllllllllllIllIllllIIIlIlI.modules = true;
        lllllllllllllllllIllIllllIIIlIlI.waypoints = false;
    }

    public boolean equals(Object lllllllllllllllllIllIlllIIllIIlI) {
        Profile lllllllllllllllllIllIlllIIllIllI;
        if (lllllllllllllllllIllIlllIIllIllI == lllllllllllllllllIllIlllIIllIIlI) {
            return true;
        }
        if (lllllllllllllllllIllIlllIIllIIlI == null || lllllllllllllllllIllIlllIIllIllI.getClass() != lllllllllllllllllIllIlllIIllIIlI.getClass()) {
            return false;
        }
        Profile lllllllllllllllllIllIlllIIllIlII = (Profile)lllllllllllllllllIllIlllIIllIIlI;
        return lllllllllllllllllIllIlllIIllIllI.name.equalsIgnoreCase(lllllllllllllllllIllIlllIIllIlII.name);
    }

    public void load() {
        Profile lllllllllllllllllIllIlllIllllIll;
        File lllllllllllllllllIllIlllIlllllII = new File(Profiles.FOLDER, lllllllllllllllllIllIlllIllllIll.name);
        if (lllllllllllllllllIllIlllIllllIll.accounts) {
            Accounts.get().load(lllllllllllllllllIllIlllIlllllII);
        }
        if (lllllllllllllllllIllIlllIllllIll.config) {
            Config.get().load(lllllllllllllllllIllIlllIlllllII);
        }
        if (lllllllllllllllllIllIlllIllllIll.friends) {
            Friends.get().load(lllllllllllllllllIllIlllIlllllII);
        }
        if (lllllllllllllllllIllIlllIllllIll.macros) {
            Macros.get().load(lllllllllllllllllIllIlllIlllllII);
        }
        if (lllllllllllllllllIllIlllIllllIll.modules) {
            Modules.get().load(lllllllllllllllllIllIlllIlllllII);
        }
        if (lllllllllllllllllIllIlllIllllIll.waypoints) {
            Waypoints.get().load(lllllllllllllllllIllIlllIlllllII);
        }
    }

    public void save() {
        Profile lllllllllllllllllIllIlllIllIllII;
        File lllllllllllllllllIllIlllIllIllIl = new File(Profiles.FOLDER, lllllllllllllllllIllIlllIllIllII.name);
        if (lllllllllllllllllIllIlllIllIllII.accounts) {
            Accounts.get().save(lllllllllllllllllIllIlllIllIllIl);
        }
        if (lllllllllllllllllIllIlllIllIllII.config) {
            Config.get().save(lllllllllllllllllIllIlllIllIllIl);
        }
        if (lllllllllllllllllIllIlllIllIllII.friends) {
            Friends.get().save(lllllllllllllllllIllIlllIllIllIl);
        }
        if (lllllllllllllllllIllIlllIllIllII.macros) {
            Macros.get().save(lllllllllllllllllIllIlllIllIllIl);
        }
        if (lllllllllllllllllIllIlllIllIllII.modules) {
            Modules.get().save(lllllllllllllllllIllIlllIllIllIl);
        }
        if (lllllllllllllllllIllIlllIllIllII.waypoints) {
            Waypoints.get().save(lllllllllllllllllIllIlllIllIllIl);
        }
    }
}

