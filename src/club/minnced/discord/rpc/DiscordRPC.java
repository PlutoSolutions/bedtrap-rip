/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 */
package club.minnced.discord.rpc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import com.sun.jna.Library;
import com.sun.jna.Native;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface DiscordRPC
extends Library {
    public static final /* synthetic */ int DISCORD_REPLY_NO;
    public static final /* synthetic */ int DISCORD_REPLY_IGNORE;
    public static final /* synthetic */ int DISCORD_REPLY_YES;
    public static final /* synthetic */ DiscordRPC INSTANCE;

    public void Discord_UpdatePresence(@Nullable DiscordRichPresence var1);

    static {
        DISCORD_REPLY_IGNORE = 2;
        DISCORD_REPLY_NO = 0;
        DISCORD_REPLY_YES = 1;
        INSTANCE = Native.loadLibrary("discord-rpc", DiscordRPC.class);
    }

    public void Discord_Respond(@Nonnull String var1, int var2);

    public void Discord_Register(String var1, String var2);

    public void Discord_UpdateHandlers(@Nullable DiscordEventHandlers var1);

    public void Discord_UpdateConnection();

    public void Discord_ClearPresence();

    public void Discord_Initialize(@Nonnull String var1, @Nullable DiscordEventHandlers var2, boolean var3, @Nullable String var4);

    public void Discord_RegisterSteamGame(String var1, String var2);

    public void Discord_RunCallbacks();

    public void Discord_Shutdown();
}

