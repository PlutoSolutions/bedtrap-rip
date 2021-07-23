/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.misc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.Placeholders;

public class DiscordPresence
extends Module {
    private final Setting<String> line2;
    private int ticks;
    private final SettingGroup sgGeneral;
    private static final DiscordRPC instance;
    private static final DiscordRichPresence rpc;

    @Override
    public void onActivate() {
        String string;
        DiscordEventHandlers discordEventHandlers = new DiscordEventHandlers();
        instance.Discord_Initialize("853721983241551873", discordEventHandlers, true, null);
        DiscordPresence.rpc.startTimestamp = System.currentTimeMillis() / 1000L;
        DiscordPresence.rpc.largeImageKey = "btpng";
        DiscordPresence.rpc.largeImageText = string = "BedTrap 0.3.1";
        this.updateDetails();
        instance.Discord_UpdatePresence(rpc);
        instance.Discord_RunCallbacks();
    }

    @Override
    public void onDeactivate() {
        instance.Discord_ClearPresence();
        instance.Discord_Shutdown();
    }

    static {
        rpc = new DiscordRichPresence();
        instance = DiscordRPC.INSTANCE;
    }

    private void updateDetails() {
        String string = "discord.gg/4cupzRkP29";
        if (this.isActive() && Utils.canUpdate()) {
            DiscordPresence.rpc.details = Placeholders.apply(string);
            DiscordPresence.rpc.state = Placeholders.apply(this.line2.get());
            instance.Discord_UpdatePresence(rpc);
        }
    }

    private void lambda$new$0(String string) {
        this.updateDetails();
    }

    public DiscordPresence() {
        super(Categories.Misc, "discord-presence", "Displays a RPC for you on Discord to show that you're playing BedTrap Addon!");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.line2 = this.sgGeneral.add(new StringSetting.Builder().name("line").description("The text it displays line of the RPC.").defaultValue("BedTrap on Top!").onChanged(this::lambda$new$0).build());
    }
}

