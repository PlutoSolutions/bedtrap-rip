/*
 * Decompiled with CFR 0.151.
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Profile
implements ISerializable<Profile> {
    public boolean accounts = false;
    public boolean friends = false;
    public String name = "";
    public List<String> loadOnJoinIps = new ArrayList<String>();
    public boolean modules = true;
    public boolean macros = true;
    public boolean onLaunch = false;
    public boolean config = true;
    public boolean waypoints = false;

    @Override
    public Profile fromTag(class_2487 class_24872) {
        this.name = class_24872.method_10558("name");
        this.onLaunch = class_24872.method_10545("onLaunch") && class_24872.method_10577("onLaunch");
        this.accounts = class_24872.method_10545("accounts") && class_24872.method_10577("accounts");
        this.config = class_24872.method_10545("config") && class_24872.method_10577("config");
        this.friends = class_24872.method_10545("friends") && class_24872.method_10577("friends");
        this.macros = class_24872.method_10545("macros") && class_24872.method_10577("macros");
        this.modules = class_24872.method_10545("modules") && class_24872.method_10577("modules");
        this.waypoints = class_24872.method_10545("waypoints") && class_24872.method_10577("waypoints");
        this.loadOnJoinIps.clear();
        if (class_24872.method_10545("loadOnJoinIps")) {
            class_2499 class_24992 = class_24872.method_10554("loadOnJoinIps", 8);
            for (class_2520 class_25202 : class_24992) {
                this.loadOnJoinIps.add(class_25202.method_10714());
            }
        }
        return this;
    }

    public void save(System<?> system) {
        File file = new File(Profiles.FOLDER, this.name);
        system.save(file);
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public void load(System<?> system) {
        File file = new File(Profiles.FOLDER, this.name);
        system.load(file);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("name", this.name);
        class_24872.method_10556("onLaunch", this.onLaunch);
        class_24872.method_10556("accounts", this.accounts);
        class_24872.method_10556("config", this.config);
        class_24872.method_10556("friends", this.friends);
        class_24872.method_10556("macros", this.macros);
        class_24872.method_10556("modules", this.modules);
        class_24872.method_10556("waypoints", this.waypoints);
        this.loadOnJoinIps.removeIf(String::isEmpty);
        class_2499 class_24992 = new class_2499();
        for (String string : this.loadOnJoinIps) {
            class_24992.add((Object)class_2519.method_23256((String)string));
        }
        class_24872.method_10566("loadOnJoinIps", (class_2520)class_24992);
        return class_24872;
    }

    public Profile set(Profile profile) {
        this.name = profile.name;
        this.onLaunch = profile.onLaunch;
        this.loadOnJoinIps = profile.loadOnJoinIps;
        this.accounts = profile.accounts;
        this.config = profile.config;
        this.friends = profile.friends;
        this.macros = profile.macros;
        this.modules = profile.modules;
        this.waypoints = profile.waypoints;
        return this;
    }

    public void delete(System<?> system) {
        File file = new File(new File(Profiles.FOLDER, this.name), system.getFile().getName());
        file.delete();
    }

    public void delete() {
        try {
            FileUtils.deleteDirectory((File)new File(Profiles.FOLDER, this.name));
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Profile profile = (Profile)object;
        return this.name.equalsIgnoreCase(profile.name);
    }

    public void load() {
        File file = new File(Profiles.FOLDER, this.name);
        if (this.accounts) {
            Accounts.get().load(file);
        }
        if (this.config) {
            Config.get().load(file);
        }
        if (this.friends) {
            Friends.get().load(file);
        }
        if (this.macros) {
            Macros.get().load(file);
        }
        if (this.modules) {
            Modules.get().load(file);
        }
        if (this.waypoints) {
            Waypoints.get().load(file);
        }
    }

    public void save() {
        File file = new File(Profiles.FOLDER, this.name);
        if (this.accounts) {
            Accounts.get().save(file);
        }
        if (this.config) {
            Config.get().save(file);
        }
        if (this.friends) {
            Friends.get().save(file);
        }
        if (this.macros) {
            Macros.get().save(file);
        }
        if (this.modules) {
            Modules.get().save(file);
        }
        if (this.waypoints) {
            Waypoints.get().save(file);
        }
    }
}

