/*
 * Decompiled with CFR 0.150.
 */
package club.minnced.discord.rpc;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordRichPresence
extends Structure {
    public /* synthetic */ String partyId;
    public /* synthetic */ String state;
    public /* synthetic */ String largeImageKey;
    public /* synthetic */ String smallImageKey;
    public /* synthetic */ String smallImageText;
    public /* synthetic */ String details;
    public /* synthetic */ byte instance;
    public /* synthetic */ String joinSecret;
    public /* synthetic */ long endTimestamp;
    public /* synthetic */ long startTimestamp;
    public /* synthetic */ String spectateSecret;
    public /* synthetic */ int partySize;
    private static final /* synthetic */ List<String> FIELD_ORDER;
    public /* synthetic */ int partyMax;
    public /* synthetic */ String largeImageText;
    public /* synthetic */ String matchSecret;

    @Override
    public int hashCode() {
        DiscordRichPresence lllllllllllllllllIIIIlIlIIIIlIII;
        return Objects.hash(lllllllllllllllllIIIIlIlIIIIlIII.state, lllllllllllllllllIIIIlIlIIIIlIII.details, lllllllllllllllllIIIIlIlIIIIlIII.startTimestamp, lllllllllllllllllIIIIlIlIIIIlIII.endTimestamp, lllllllllllllllllIIIIlIlIIIIlIII.largeImageKey, lllllllllllllllllIIIIlIlIIIIlIII.largeImageText, lllllllllllllllllIIIIlIlIIIIlIII.smallImageKey, lllllllllllllllllIIIIlIlIIIIlIII.smallImageText, lllllllllllllllllIIIIlIlIIIIlIII.partyId, lllllllllllllllllIIIIlIlIIIIlIII.partySize, lllllllllllllllllIIIIlIlIIIIlIII.partyMax, lllllllllllllllllIIIIlIlIIIIlIII.matchSecret, lllllllllllllllllIIIIlIlIIIIlIII.joinSecret, lllllllllllllllllIIIIlIlIIIIlIII.spectateSecret, lllllllllllllllllIIIIlIlIIIIlIII.instance);
    }

    public DiscordRichPresence(String lllllllllllllllllIIIIlIlIIIlIllI) {
        DiscordRichPresence lllllllllllllllllIIIIlIlIIIllIIl;
        lllllllllllllllllIIIIlIlIIIllIIl.setStringEncoding(lllllllllllllllllIIIIlIlIIIlIllI);
    }

    @Override
    public boolean equals(Object lllllllllllllllllIIIIlIlIIIIlIll) {
        DiscordRichPresence lllllllllllllllllIIIIlIlIIIIllII;
        if (lllllllllllllllllIIIIlIlIIIIllII == lllllllllllllllllIIIIlIlIIIIlIll) {
            return true;
        }
        if (!(lllllllllllllllllIIIIlIlIIIIlIll instanceof DiscordRichPresence)) {
            return false;
        }
        DiscordRichPresence lllllllllllllllllIIIIlIlIIIIllIl = (DiscordRichPresence)lllllllllllllllllIIIIlIlIIIIlIll;
        return lllllllllllllllllIIIIlIlIIIIllII.startTimestamp == lllllllllllllllllIIIIlIlIIIIllIl.startTimestamp && lllllllllllllllllIIIIlIlIIIIllII.endTimestamp == lllllllllllllllllIIIIlIlIIIIllIl.endTimestamp && lllllllllllllllllIIIIlIlIIIIllII.partySize == lllllllllllllllllIIIIlIlIIIIllIl.partySize && lllllllllllllllllIIIIlIlIIIIllII.partyMax == lllllllllllllllllIIIIlIlIIIIllIl.partyMax && lllllllllllllllllIIIIlIlIIIIllII.instance == lllllllllllllllllIIIIlIlIIIIllIl.instance && Objects.equals(lllllllllllllllllIIIIlIlIIIIllII.state, lllllllllllllllllIIIIlIlIIIIllIl.state) && Objects.equals(lllllllllllllllllIIIIlIlIIIIllII.details, lllllllllllllllllIIIIlIlIIIIllIl.details) && Objects.equals(lllllllllllllllllIIIIlIlIIIIllII.largeImageKey, lllllllllllllllllIIIIlIlIIIIllIl.largeImageKey) && Objects.equals(lllllllllllllllllIIIIlIlIIIIllII.largeImageText, lllllllllllllllllIIIIlIlIIIIllIl.largeImageText) && Objects.equals(lllllllllllllllllIIIIlIlIIIIllII.smallImageKey, lllllllllllllllllIIIIlIlIIIIllIl.smallImageKey) && Objects.equals(lllllllllllllllllIIIIlIlIIIIllII.smallImageText, lllllllllllllllllIIIIlIlIIIIllIl.smallImageText) && Objects.equals(lllllllllllllllllIIIIlIlIIIIllII.partyId, lllllllllllllllllIIIIlIlIIIIllIl.partyId) && Objects.equals(lllllllllllllllllIIIIlIlIIIIllII.matchSecret, lllllllllllllllllIIIIlIlIIIIllIl.matchSecret) && Objects.equals(lllllllllllllllllIIIIlIlIIIIllII.joinSecret, lllllllllllllllllIIIIlIlIIIIllIl.joinSecret) && Objects.equals(lllllllllllllllllIIIIlIlIIIIllII.spectateSecret, lllllllllllllllllIIIIlIlIIIIllIl.spectateSecret);
    }

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }

    public DiscordRichPresence() {
        lllllllllllllllllIIIIlIlIIIlIIll("UTF-8");
        DiscordRichPresence lllllllllllllllllIIIIlIlIIIlIIll;
    }

    static {
        FIELD_ORDER = Collections.unmodifiableList(Arrays.asList("state", "details", "startTimestamp", "endTimestamp", "largeImageKey", "largeImageText", "smallImageKey", "smallImageText", "partyId", "partySize", "partyMax", "matchSecret", "joinSecret", "spectateSecret", "instance"));
    }
}

