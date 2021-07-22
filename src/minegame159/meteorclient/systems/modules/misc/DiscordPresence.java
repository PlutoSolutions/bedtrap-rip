/*
 * Decompiled with CFR 0.150.
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
    private final /* synthetic */ Setting<String> line2;
    private /* synthetic */ int ticks;
    private final /* synthetic */ SettingGroup sgGeneral;
    private static final /* synthetic */ DiscordRPC instance;
    private static final /* synthetic */ DiscordRichPresence rpc;

    @Override
    public void onActivate() {
        DiscordPresence llllllllllllllllllllllIllIllIIII;
        String llllllllllllllllllllllIllIlIlllI;
        DiscordEventHandlers llllllllllllllllllllllIllIlIllll = new DiscordEventHandlers();
        instance.Discord_Initialize("853721983241551873", llllllllllllllllllllllIllIlIllll, true, null);
        DiscordPresence.rpc.startTimestamp = System.currentTimeMillis() / 1000L;
        DiscordPresence.rpc.largeImageKey = "btpng";
        DiscordPresence.rpc.largeImageText = llllllllllllllllllllllIllIlIlllI = "BedTrap 0.3.1";
        llllllllllllllllllllllIllIllIIII.updateDetails();
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
        DiscordPresence llllllllllllllllllllllIllIlIIlll;
        String llllllllllllllllllllllIllIlIIllI = "discord.gg/4cupzRkP29";
        if (llllllllllllllllllllllIllIlIIlll.isActive() && Utils.canUpdate()) {
            DiscordPresence.rpc.details = Placeholders.apply(llllllllllllllllllllllIllIlIIllI);
            DiscordPresence.rpc.state = Placeholders.apply(llllllllllllllllllllllIllIlIIlll.line2.get());
            instance.Discord_UpdatePresence(rpc);
        }
    }

    public DiscordPresence() {
        super(Categories.Misc, "discord-presence", "Displays a RPC for you on Discord to show that you're playing BedTrap Addon!");
        DiscordPresence llllllllllllllllllllllIllIllIlIl;
        llllllllllllllllllllllIllIllIlIl.sgGeneral = llllllllllllllllllllllIllIllIlIl.settings.getDefaultGroup();
        llllllllllllllllllllllIllIllIlIl.line2 = llllllllllllllllllllllIllIllIlIl.sgGeneral.add(new StringSetting.Builder().name("line").description("The text it displays line of the RPC.").defaultValue("BedTrap on Top!").onChanged(llllllllllllllllllllllIllIlIIIIl -> {
            DiscordPresence llllllllllllllllllllllIllIlIIIlI;
            llllllllllllllllllllllIllIlIIIlI.updateDetails();
        }).build());
    }
}

