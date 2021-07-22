/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  org.jetbrains.annotations.NotNull
 */
package minegame159.meteorclient.systems.proxies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.proxies.Proxy;
import minegame159.meteorclient.utils.misc.NbtUtils;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import org.jetbrains.annotations.NotNull;

public class Proxies
extends System<Proxies>
implements Iterable<Proxy> {
    private /* synthetic */ List<Proxy> proxies;

    public boolean add(Proxy llllllllllllllllllllIlIllIIllIlI) {
        Proxies llllllllllllllllllllIlIllIIlllII;
        for (Proxy llllllllllllllllllllIlIllIIllllI : llllllllllllllllllllIlIllIIlllII.proxies) {
            if (llllllllllllllllllllIlIllIIllllI.type != llllllllllllllllllllIlIllIIllIlI.type || !llllllllllllllllllllIlIllIIllllI.ip.equals(llllllllllllllllllllIlIllIIllIlI.ip) || llllllllllllllllllllIlIllIIllllI.port != llllllllllllllllllllIlIllIIllIlI.port) continue;
            return false;
        }
        if (llllllllllllllllllllIlIllIIlllII.proxies.isEmpty()) {
            llllllllllllllllllllIlIllIIllIlI.enabled = true;
        }
        llllllllllllllllllllIlIllIIlllII.proxies.add(llllllllllllllllllllIlIllIIllIlI);
        llllllllllllllllllllIlIllIIlllII.save();
        return true;
    }

    @Override
    public Proxies fromTag(class_2487 llllllllllllllllllllIlIlIllIlIII) {
        Proxies llllllllllllllllllllIlIlIllIlIIl;
        llllllllllllllllllllIlIlIllIlIIl.proxies = NbtUtils.listFromTag(llllllllllllllllllllIlIlIllIlIII.method_10554("proxies", 10), llllllllllllllllllllIlIlIllIIIII -> new Proxy().fromTag((class_2487)llllllllllllllllllllIlIlIllIIIII));
        return llllllllllllllllllllIlIlIllIlIIl;
    }

    public Proxies() {
        super("proxies");
        Proxies llllllllllllllllllllIlIllIlIllIl;
        llllllllllllllllllllIlIllIlIllIl.proxies = new ArrayList<Proxy>();
    }

    @Override
    public class_2487 toTag() {
        Proxies llllllllllllllllllllIlIlIllIllll;
        class_2487 llllllllllllllllllllIlIlIlllIIII = new class_2487();
        llllllllllllllllllllIlIlIlllIIII.method_10566("proxies", (class_2520)NbtUtils.listToTag(llllllllllllllllllllIlIlIllIllll.proxies));
        return llllllllllllllllllllIlIlIlllIIII;
    }

    public void setEnabled(Proxy llllllllllllllllllllIlIlIlllllIl, boolean llllllllllllllllllllIlIlIllllIIl) {
        Proxies llllllllllllllllllllIlIlIllllIll;
        for (Proxy llllllllllllllllllllIlIlIlllllll : llllllllllllllllllllIlIlIllllIll.proxies) {
            llllllllllllllllllllIlIlIlllllll.enabled = false;
        }
        llllllllllllllllllllIlIlIlllllIl.enabled = llllllllllllllllllllIlIlIllllIIl;
        llllllllllllllllllllIlIlIllllIll.save();
    }

    public Proxy getEnabled() {
        Proxies llllllllllllllllllllIlIllIIIlIII;
        for (Proxy llllllllllllllllllllIlIllIIIlIIl : llllllllllllllllllllIlIllIIIlIII.proxies) {
            if (!llllllllllllllllllllIlIllIIIlIIl.enabled) continue;
            return llllllllllllllllllllIlIllIIIlIIl;
        }
        return null;
    }

    @Override
    @NotNull
    public Iterator<Proxy> iterator() {
        Proxies llllllllllllllllllllIlIlIlllIlII;
        return llllllllllllllllllllIlIlIlllIlII.proxies.iterator();
    }

    public static Proxies get() {
        return Systems.get(Proxies.class);
    }

    public void remove(Proxy llllllllllllllllllllIlIllIIIllll) {
        Proxies llllllllllllllllllllIlIllIIIlllI;
        if (llllllllllllllllllllIlIllIIIlllI.proxies.remove(llllllllllllllllllllIlIllIIIllll)) {
            llllllllllllllllllllIlIllIIIlllI.save();
        }
    }
}

