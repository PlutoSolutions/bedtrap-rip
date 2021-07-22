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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Proxies
extends System<Proxies>
implements Iterable<Proxy> {
    private List<Proxy> proxies = new ArrayList<Proxy>();

    public boolean add(Proxy proxy) {
        for (Proxy proxy2 : this.proxies) {
            if (proxy2.type != proxy.type || !proxy2.ip.equals(proxy.ip) || proxy2.port != proxy.port) continue;
            return false;
        }
        if (this.proxies.isEmpty()) {
            proxy.enabled = true;
        }
        this.proxies.add(proxy);
        this.save();
        return true;
    }

    @Override
    public Proxies fromTag(class_2487 class_24872) {
        this.proxies = NbtUtils.listFromTag(class_24872.method_10554("proxies", 10), Proxies::lambda$fromTag$0);
        return this;
    }

    public Proxies() {
        super("proxies");
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10566("proxies", (class_2520)NbtUtils.listToTag(this.proxies));
        return class_24872;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public void setEnabled(Proxy proxy, boolean bl) {
        for (Proxy proxy2 : this.proxies) {
            proxy2.enabled = false;
        }
        proxy.enabled = bl;
        this.save();
    }

    public Proxy getEnabled() {
        for (Proxy proxy : this.proxies) {
            if (!proxy.enabled) continue;
            return proxy;
        }
        return null;
    }

    private static Proxy lambda$fromTag$0(class_2520 class_25202) {
        return new Proxy().fromTag((class_2487)class_25202);
    }

    @Override
    @NotNull
    public Iterator<Proxy> iterator() {
        return this.proxies.iterator();
    }

    public static Proxies get() {
        return Systems.get(Proxies.class);
    }

    public void remove(Proxy proxy) {
        if (this.proxies.remove(proxy)) {
            this.save();
        }
    }
}

