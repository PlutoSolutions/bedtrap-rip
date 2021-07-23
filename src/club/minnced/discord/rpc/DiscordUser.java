/*
 * Decompiled with CFR 0.151.
 */
package club.minnced.discord.rpc;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordUser
extends Structure {
    public String discriminator;
    public String userId;
    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList("userId", "username", "discriminator", "avatar"));
    public String avatar;
    public String username;

    public DiscordUser() {
        this("UTF-8");
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId, this.username, this.discriminator, this.avatar);
    }

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }

    public DiscordUser(String string) {
        this.setStringEncoding(string);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DiscordUser)) {
            return false;
        }
        DiscordUser discordUser = (DiscordUser)object;
        return Objects.equals(this.userId, discordUser.userId) && Objects.equals(this.username, discordUser.username) && Objects.equals(this.discriminator, discordUser.discriminator) && Objects.equals(this.avatar, discordUser.avatar);
    }
}

