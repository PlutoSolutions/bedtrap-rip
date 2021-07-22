/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.systems.profiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.GameJoinedEvent;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.profiles.Profile;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.NbtUtils;
import net.minecraft.class_2487;
import net.minecraft.class_2520;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Profiles
extends System<Profiles>
implements Iterable<Profile> {
    public static final File FOLDER = new File(MeteorClient.FOLDER, "profiles");
    private List<Profile> profiles = new ArrayList<Profile>();

    public void add(Profile profile) {
        if (!this.profiles.contains(profile)) {
            this.profiles.add(profile);
        }
        profile.save();
        this.save();
    }

    private static Profile lambda$fromTag$0(class_2520 class_25202) {
        return new Profile().fromTag((class_2487)class_25202);
    }

    public void remove(Profile profile) {
        if (this.profiles.remove(profile)) {
            profile.delete();
        }
        this.save();
    }

    @EventHandler
    private void onGameJoined(GameJoinedEvent gameJoinedEvent) {
        for (Profile profile : this) {
            if (!profile.loadOnJoinIps.contains(Utils.getWorldName())) continue;
            profile.load();
        }
    }

    @Override
    public Iterator<Profile> iterator() {
        return this.profiles.iterator();
    }

    @Override
    public File getFile() {
        return new File(FOLDER, "profiles.nbt");
    }

    public static Profiles get() {
        return Systems.get(Profiles.class);
    }

    public Profiles() {
        super("profiles");
    }

    public Profile get(String string) {
        for (Profile profile : this) {
            if (!profile.name.equalsIgnoreCase(string)) continue;
            return profile;
        }
        return null;
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10566("profiles", (class_2520)NbtUtils.listToTag(this.profiles));
        return class_24872;
    }

    @Override
    public Profiles fromTag(class_2487 class_24872) {
        this.profiles = NbtUtils.listFromTag(class_24872.method_10554("profiles", 10), Profiles::lambda$fromTag$0);
        return this;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public List<Profile> getAll() {
        return this.profiles;
    }
}

