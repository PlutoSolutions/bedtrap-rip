/*
 * Decompiled with CFR 0.150.
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
    public /* synthetic */ OnStatus disconnected;
    public /* synthetic */ OnStatus errored;
    public /* synthetic */ OnJoinRequest joinRequest;
    public /* synthetic */ OnReady ready;
    private static final /* synthetic */ List<String> FIELD_ORDER;
    public /* synthetic */ OnGameUpdate spectateGame;
    public /* synthetic */ OnGameUpdate joinGame;

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }

    @Override
    public int hashCode() {
        DiscordEventHandlers lllllllllllllllllIlIIIIIlIIlIIlI;
        return Objects.hash(lllllllllllllllllIlIIIIIlIIlIIlI.ready, lllllllllllllllllIlIIIIIlIIlIIlI.disconnected, lllllllllllllllllIlIIIIIlIIlIIlI.errored, lllllllllllllllllIlIIIIIlIIlIIlI.joinGame, lllllllllllllllllIlIIIIIlIIlIIlI.spectateGame, lllllllllllllllllIlIIIIIlIIlIIlI.joinRequest);
    }

    static {
        FIELD_ORDER = Collections.unmodifiableList(Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest"));
    }

    @Override
    public boolean equals(Object lllllllllllllllllIlIIIIIlIIllIIl) {
        DiscordEventHandlers lllllllllllllllllIlIIIIIlIIlIlll;
        if (lllllllllllllllllIlIIIIIlIIlIlll == lllllllllllllllllIlIIIIIlIIllIIl) {
            return true;
        }
        if (!(lllllllllllllllllIlIIIIIlIIllIIl instanceof DiscordEventHandlers)) {
            return false;
        }
        DiscordEventHandlers lllllllllllllllllIlIIIIIlIIllIII = (DiscordEventHandlers)lllllllllllllllllIlIIIIIlIIllIIl;
        return Objects.equals(lllllllllllllllllIlIIIIIlIIlIlll.ready, lllllllllllllllllIlIIIIIlIIllIII.ready) && Objects.equals(lllllllllllllllllIlIIIIIlIIlIlll.disconnected, lllllllllllllllllIlIIIIIlIIllIII.disconnected) && Objects.equals(lllllllllllllllllIlIIIIIlIIlIlll.errored, lllllllllllllllllIlIIIIIlIIllIII.errored) && Objects.equals(lllllllllllllllllIlIIIIIlIIlIlll.joinGame, lllllllllllllllllIlIIIIIlIIllIII.joinGame) && Objects.equals(lllllllllllllllllIlIIIIIlIIlIlll.spectateGame, lllllllllllllllllIlIIIIIlIIllIII.spectateGame) && Objects.equals(lllllllllllllllllIlIIIIIlIIlIlll.joinRequest, lllllllllllllllllIlIIIIIlIIllIII.joinRequest);
    }

    public DiscordEventHandlers() {
        DiscordEventHandlers lllllllllllllllllIlIIIIIlIIlllll;
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

