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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Proxy
implements ISerializable<Proxy> {
    public ProxyType type = ProxyType.Socks5;
    public int port = 0;
    public String password = "";
    private static final Pattern IP_PATTERN = Pattern.compile("\\b(?:(?:2(?:[0-4][0-9]|5[0-5])|[0-1]?[0-9]?[0-9])\\.){3}(?:(?:2([0-4][0-9]|5[0-5])|[0-1]?[0-9]?[0-9]))\\b");
    public String ip = "";
    public String name = "";
    public String username = "";
    public boolean enabled = false;

    public boolean isValid() {
        return IP_PATTERN.matcher(this.ip).matches() && this.port >= 0 && this.port <= 65535 && !this.name.isEmpty();
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("type", this.type.name());
        class_24872.method_10582("ip", this.ip);
        class_24872.method_10569("port", this.port);
        class_24872.method_10582("name", this.name);
        class_24872.method_10582("username", this.username);
        class_24872.method_10582("password", this.password);
        class_24872.method_10556("enabled", this.enabled);
        return class_24872;
    }

    @Override
    public Proxy fromTag(class_2487 class_24872) {
        this.type = ProxyType.valueOf(class_24872.method_10558("type"));
        this.ip = class_24872.method_10558("ip");
        this.port = class_24872.method_10550("port");
        this.name = class_24872.method_10558("name");
        this.username = class_24872.method_10558("username");
        this.password = class_24872.method_10558("password");
        this.enabled = class_24872.method_10577("enabled");
        return this;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }
}

