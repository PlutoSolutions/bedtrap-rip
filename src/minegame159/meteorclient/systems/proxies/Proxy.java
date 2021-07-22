/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.systems.proxies;

import java.util.regex.Pattern;
import minegame159.meteorclient.systems.proxies.ProxyType;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;

public class Proxy
implements ISerializable<Proxy> {
    public /* synthetic */ ProxyType type;
    public /* synthetic */ int port;
    public /* synthetic */ String password;
    private static final /* synthetic */ Pattern IP_PATTERN;
    public /* synthetic */ String ip;
    public /* synthetic */ String name;
    public /* synthetic */ String username;
    public /* synthetic */ boolean enabled;

    public Proxy() {
        Proxy lllllllllllllllllIIIIllIIIIlIllI;
        lllllllllllllllllIIIIllIIIIlIllI.type = ProxyType.Socks5;
        lllllllllllllllllIIIIllIIIIlIllI.ip = "";
        lllllllllllllllllIIIIllIIIIlIllI.port = 0;
        lllllllllllllllllIIIIllIIIIlIllI.name = "";
        lllllllllllllllllIIIIllIIIIlIllI.username = "";
        lllllllllllllllllIIIIllIIIIlIllI.password = "";
        lllllllllllllllllIIIIllIIIIlIllI.enabled = false;
    }

    static {
        IP_PATTERN = Pattern.compile("\\b(?:(?:2(?:[0-4][0-9]|5[0-5])|[0-1]?[0-9]?[0-9])\\.){3}(?:(?:2([0-4][0-9]|5[0-5])|[0-1]?[0-9]?[0-9]))\\b");
    }

    public boolean isValid() {
        Proxy lllllllllllllllllIIIIllIIIIlIIlI;
        return IP_PATTERN.matcher(lllllllllllllllllIIIIllIIIIlIIlI.ip).matches() && lllllllllllllllllIIIIllIIIIlIIlI.port >= 0 && lllllllllllllllllIIIIllIIIIlIIlI.port <= 65535 && !lllllllllllllllllIIIIllIIIIlIIlI.name.isEmpty();
    }

    @Override
    public class_2487 toTag() {
        Proxy lllllllllllllllllIIIIllIIIIIllll;
        class_2487 lllllllllllllllllIIIIllIIIIIlllI = new class_2487();
        lllllllllllllllllIIIIllIIIIIlllI.method_10582("type", lllllllllllllllllIIIIllIIIIIllll.type.name());
        lllllllllllllllllIIIIllIIIIIlllI.method_10582("ip", lllllllllllllllllIIIIllIIIIIllll.ip);
        lllllllllllllllllIIIIllIIIIIlllI.method_10569("port", lllllllllllllllllIIIIllIIIIIllll.port);
        lllllllllllllllllIIIIllIIIIIlllI.method_10582("name", lllllllllllllllllIIIIllIIIIIllll.name);
        lllllllllllllllllIIIIllIIIIIlllI.method_10582("username", lllllllllllllllllIIIIllIIIIIllll.username);
        lllllllllllllllllIIIIllIIIIIlllI.method_10582("password", lllllllllllllllllIIIIllIIIIIllll.password);
        lllllllllllllllllIIIIllIIIIIlllI.method_10556("enabled", lllllllllllllllllIIIIllIIIIIllll.enabled);
        return lllllllllllllllllIIIIllIIIIIlllI;
    }

    @Override
    public Proxy fromTag(class_2487 lllllllllllllllllIIIIllIIIIIIllI) {
        Proxy lllllllllllllllllIIIIllIIIIIlIIl;
        lllllllllllllllllIIIIllIIIIIlIIl.type = ProxyType.valueOf(lllllllllllllllllIIIIllIIIIIIllI.method_10558("type"));
        lllllllllllllllllIIIIllIIIIIlIIl.ip = lllllllllllllllllIIIIllIIIIIIllI.method_10558("ip");
        lllllllllllllllllIIIIllIIIIIlIIl.port = lllllllllllllllllIIIIllIIIIIIllI.method_10550("port");
        lllllllllllllllllIIIIllIIIIIlIIl.name = lllllllllllllllllIIIIllIIIIIIllI.method_10558("name");
        lllllllllllllllllIIIIllIIIIIlIIl.username = lllllllllllllllllIIIIllIIIIIIllI.method_10558("username");
        lllllllllllllllllIIIIllIIIIIlIIl.password = lllllllllllllllllIIIIllIIIIIIllI.method_10558("password");
        lllllllllllllllllIIIIllIIIIIlIIl.enabled = lllllllllllllllllIIIIllIIIIIIllI.method_10577("enabled");
        return lllllllllllllllllIIIIllIIIIIlIIl;
    }
}

