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

public class Profiles
extends System<Profiles>
implements Iterable<Profile> {
    public static final /* synthetic */ File FOLDER;
    private /* synthetic */ List<Profile> profiles;

    public void add(Profile llllllllllllllllllIIlllIlllllIIl) {
        Profiles llllllllllllllllllIIlllIlllllIlI;
        if (!llllllllllllllllllIIlllIlllllIlI.profiles.contains(llllllllllllllllllIIlllIlllllIIl)) {
            llllllllllllllllllIIlllIlllllIlI.profiles.add(llllllllllllllllllIIlllIlllllIIl);
        }
        llllllllllllllllllIIlllIlllllIIl.save();
        llllllllllllllllllIIlllIlllllIlI.save();
    }

    static {
        FOLDER = new File(MeteorClient.FOLDER, "profiles");
    }

    public void remove(Profile llllllllllllllllllIIlllIllllIlIl) {
        Profiles llllllllllllllllllIIlllIllllIlII;
        if (llllllllllllllllllIIlllIllllIlII.profiles.remove(llllllllllllllllllIIlllIllllIlIl)) {
            llllllllllllllllllIIlllIllllIlIl.delete();
        }
        llllllllllllllllllIIlllIllllIlII.save();
    }

    @EventHandler
    private void onGameJoined(GameJoinedEvent llllllllllllllllllIIlllIllIlIIlI) {
        Profiles llllllllllllllllllIIlllIllIlIIll;
        for (Profile llllllllllllllllllIIlllIllIlIlII : llllllllllllllllllIIlllIllIlIIll) {
            if (!llllllllllllllllllIIlllIllIlIlII.loadOnJoinIps.contains(Utils.getWorldName())) continue;
            llllllllllllllllllIIlllIllIlIlII.load();
        }
    }

    @Override
    public Iterator<Profile> iterator() {
        Profiles llllllllllllllllllIIlllIllIIllIl;
        return llllllllllllllllllIIlllIllIIllIl.profiles.iterator();
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
        Profiles llllllllllllllllllIIllllIIIIIIII;
        llllllllllllllllllIIllllIIIIIIII.profiles = new ArrayList<Profile>();
    }

    public Profile get(String llllllllllllllllllIIlllIlllIlIlI) {
        Profiles llllllllllllllllllIIlllIlllIlIll;
        for (Profile llllllllllllllllllIIlllIlllIlllI : llllllllllllllllllIIlllIlllIlIll) {
            if (!llllllllllllllllllIIlllIlllIlllI.name.equalsIgnoreCase(llllllllllllllllllIIlllIlllIlIlI)) continue;
            return llllllllllllllllllIIlllIlllIlllI;
        }
        return null;
    }

    @Override
    public class_2487 toTag() {
        Profiles llllllllllllllllllIIlllIlllIIIIl;
        class_2487 llllllllllllllllllIIlllIlllIIIII = new class_2487();
        llllllllllllllllllIIlllIlllIIIII.method_10566("profiles", (class_2520)NbtUtils.listToTag(llllllllllllllllllIIlllIlllIIIIl.profiles));
        return llllllllllllllllllIIlllIlllIIIII;
    }

    @Override
    public Profiles fromTag(class_2487 llllllllllllllllllIIlllIllIllIII) {
        Profiles llllllllllllllllllIIlllIllIllIIl;
        llllllllllllllllllIIlllIllIllIIl.profiles = NbtUtils.listFromTag(llllllllllllllllllIIlllIllIllIII.method_10554("profiles", 10), llllllllllllllllllIIlllIllIIIlIl -> new Profile().fromTag((class_2487)llllllllllllllllllIIlllIllIIIlIl));
        return llllllllllllllllllIIlllIllIllIIl;
    }

    public List<Profile> getAll() {
        Profiles llllllllllllllllllIIlllIlllIIlIl;
        return llllllllllllllllllIIlllIlllIIlIl.profiles;
    }
}

