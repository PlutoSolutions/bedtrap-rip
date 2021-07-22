/*
 * Decompiled with CFR 0.150.
 */
package club.minnced.discord.rpc;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordUser
extends Structure {
    public /* synthetic */ String discriminator;
    public /* synthetic */ String userId;
    private static final /* synthetic */ List<String> FIELD_ORDER;
    public /* synthetic */ String avatar;
    public /* synthetic */ String username;

    public DiscordUser() {
        lllllllllllllllllIIllIIIIIlllIlI("UTF-8");
        DiscordUser lllllllllllllllllIIllIIIIIlllIlI;
    }

    @Override
    public int hashCode() {
        DiscordUser lllllllllllllllllIIllIIIIIlIllIl;
        return Objects.hash(lllllllllllllllllIIllIIIIIlIllIl.userId, lllllllllllllllllIIllIIIIIlIllIl.username, lllllllllllllllllIIllIIIIIlIllIl.discriminator, lllllllllllllllllIIllIIIIIlIllIl.avatar);
    }

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }

    static {
        FIELD_ORDER = Collections.unmodifiableList(Arrays.asList("userId", "username", "discriminator", "avatar"));
    }

    public DiscordUser(String lllllllllllllllllIIllIIIIIllllII) {
        DiscordUser lllllllllllllllllIIllIIIIIllllll;
        lllllllllllllllllIIllIIIIIllllll.setStringEncoding(lllllllllllllllllIIllIIIIIllllII);
    }

    @Override
    public boolean equals(Object lllllllllllllllllIIllIIIIIllIlII) {
        DiscordUser lllllllllllllllllIIllIIIIIllIIlI;
        if (lllllllllllllllllIIllIIIIIllIIlI == lllllllllllllllllIIllIIIIIllIlII) {
            return true;
        }
        if (!(lllllllllllllllllIIllIIIIIllIlII instanceof DiscordUser)) {
            return false;
        }
        DiscordUser lllllllllllllllllIIllIIIIIllIIll = (DiscordUser)lllllllllllllllllIIllIIIIIllIlII;
        return Objects.equals(lllllllllllllllllIIllIIIIIllIIlI.userId, lllllllllllllllllIIllIIIIIllIIll.userId) && Objects.equals(lllllllllllllllllIIllIIIIIllIIlI.username, lllllllllllllllllIIllIIIIIllIIll.username) && Objects.equals(lllllllllllllllllIIllIIIIIllIIlI.discriminator, lllllllllllllllllIIllIIIIIllIIll.discriminator) && Objects.equals(lllllllllllllllllIIllIIIIIllIIlI.avatar, lllllllllllllllllIIllIIIIIllIIll.avatar);
    }
}

