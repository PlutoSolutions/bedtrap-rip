/*
 * Decompiled with CFR 0.151.
 */
package club.minnced.discord.rpc;

import club.minnced.discord.rpc.DiscordUser;
import com.sun.jna.Callback;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordEventHandlers
extends Structure {
    public OnStatus disconnected;
    public OnStatus errored;
    public OnJoinRequest joinRequest;
    public OnReady ready;
    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest"));
    public OnGameUpdate spectateGame;
    public OnGameUpdate joinGame;

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ready, this.disconnected, this.errored, this.joinGame, this.spectateGame, this.joinRequest);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DiscordEventHandlers)) {
            return false;
        }
        DiscordEventHandlers discordEventHandlers = (DiscordEventHandlers)object;
        return Objects.equals(this.ready, discordEventHandlers.ready) && Objects.equals(this.disconnected, discordEventHandlers.disconnected) && Objects.equals(this.errored, discordEventHandlers.errored) && Objects.equals(this.joinGame, discordEventHandlers.joinGame) && Objects.equals(this.spectateGame, discordEventHandlers.spectateGame) && Objects.equals(this.joinRequest, discordEventHandlers.joinRequest);
    }

    public static interface OnStatus
    extends Callback {
        public void accept(int var1, String var2);
    }

    public static interface OnGameUpdate
    extends Callback {
        public void accept(String var1);
    }

    public static interface OnJoinRequest
    extends Callback {
        public void accept(DiscordUser var1);
    }

    public static interface OnReady
    extends Callback {
        public void accept(DiscordUser var1);
    }
}

