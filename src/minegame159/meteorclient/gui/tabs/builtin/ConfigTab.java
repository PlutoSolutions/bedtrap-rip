/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 */
package minegame159.meteorclient.gui.tabs.builtin;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.tabs.WindowTabScreen;
import minegame159.meteorclient.rendering.Fonts;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.ProvidedStringSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.network.OnlinePlayers;
import minegame159.meteorclient.utils.render.color.RainbowColors;
import net.minecraft.class_437;

public class ConfigTab
extends Tab {
    public static final /* synthetic */ Setting<Integer> rotationHoldTicks;
    public static final /* synthetic */ Setting<Boolean> titleScreenCredits;
    public static final /* synthetic */ Setting<Boolean> openChatOnPrefix;
    public static final /* synthetic */ Setting<Boolean> titleScreenSplashes;
    private static final /* synthetic */ SettingGroup sgChat;
    public static final /* synthetic */ Setting<Boolean> customFont;
    public static final /* synthetic */ Setting<String> customWindowTitleText;
    public static /* synthetic */ ConfigScreen currentScreen;
    public static final /* synthetic */ Setting<Boolean> useTeamColor;
    private static final /* synthetic */ SettingGroup sgGeneral;
    public static final /* synthetic */ Setting<Boolean> sendDataToApi;
    public static final /* synthetic */ Setting<Boolean> chatCommandsInfo;
    public static final /* synthetic */ Setting<Double> rainbowSpeed;
    public static final /* synthetic */ Setting<String> prefix;
    public static final /* synthetic */ Setting<String> font;
    public static final /* synthetic */ Setting<Boolean> rainbowPrefix;
    private static final /* synthetic */ SettingGroup sgScreens;
    private static final /* synthetic */ Settings settings;
    public static final /* synthetic */ Setting<Boolean> deleteChatCommandsInfo;
    public static final /* synthetic */ Setting<Boolean> customWindowTitle;

    public ConfigTab() {
        super("Config");
        ConfigTab llllIlllIlIlIIl;
    }

    @Override
    public boolean isScreen(class_437 llllIlllIIllllI) {
        return llllIlllIIllllI instanceof ConfigScreen;
    }

    static {
        settings = new Settings();
        sgGeneral = settings.getDefaultGroup();
        sgChat = settings.createGroup("Chat");
        sgScreens = settings.createGroup("Screens");
        customFont = sgGeneral.add(new BoolSetting.Builder().name("custom-font").description("Use a custom font.").defaultValue(true).onChanged(llllIllIlIIIlII -> {
            Config.get().customFont = llllIllIlIIIlII;
            if (currentScreen != null) {
                currentScreen.invalidate();
            }
        }).onModuleActivated(llllIllIlIIlIII -> llllIllIlIIlIII.set(Config.get().customFont)).build());
        font = sgGeneral.add(new ProvidedStringSetting.Builder().name("font").description("Custom font to use (picked from .minecraft/meteor-client/fonts folder).").supplier(Fonts::getAvailableFonts).defaultValue("JetBrains Mono").onChanged(llllIllIlIIlIlI -> {
            Config.get().font = llllIllIlIIlIlI;
            Fonts.load();
        }).onModuleActivated(llllIllIlIIllIl -> llllIllIlIIllIl.set(Config.get().font)).visible(customFont::get).build());
        rainbowSpeed = sgGeneral.add(new DoubleSetting.Builder().name("rainbow-speed").description("The global rainbow speed.").min(0.0).sliderMax(5.0).max(10.0).defaultValue(0.5).decimalPlaces(2).onChanged(llllIllIlIlIIII -> RainbowColors.GLOBAL.setSpeed(llllIllIlIlIIII / 100.0)).onModuleActivated(llllIllIlIlIlII -> llllIllIlIlIlII.set(RainbowColors.GLOBAL.getSpeed() * 100.0)).build());
        sendDataToApi = sgGeneral.add(new BoolSetting.Builder().name("send-data-to-api").description("If checked, your UUID will be send to Meteor's servers.").defaultValue(true).onChanged(llllIllIlIlIllI -> {
            Config.get().sendDataToApi = llllIllIlIlIllI;
            OnlinePlayers.forcePing();
        }).onModuleActivated(llllIllIlIllIIl -> llllIllIlIllIIl.set(Config.get().sendDataToApi)).build());
        rotationHoldTicks = sgGeneral.add(new IntSetting.Builder().name("rotation-hold-ticks").description("Hold long to hold server side rotation when not sending any packets.").defaultValue(9).onChanged(llllIllIlIlllII -> {
            Config.get().rotationHoldTicks = llllIllIlIlllII;
        }).onModuleActivated(llllIllIlIlllll -> llllIllIlIlllll.set(Config.get().rotationHoldTicks)).build());
        prefix = sgChat.add(new StringSetting.Builder().name("prefix").description("Prefix.").defaultValue(".").onChanged(llllIllIllIIIlI -> {
            Config.get().prefix = llllIllIllIIIlI;
        }).onModuleActivated(llllIllIllIIlIl -> llllIllIllIIlIl.set(Config.get().prefix)).build());
        openChatOnPrefix = sgChat.add(new BoolSetting.Builder().name("open-chat-on-prefix").description("Open chat when command prefix is pressed. Works like pressing '/' in vanilla.").defaultValue(true).onChanged(llllIllIllIlIIl -> {
            Config.get().openChatOnPrefix = llllIllIllIlIIl;
        }).onModuleActivated(llllIllIllIllII -> llllIllIllIllII.set(Config.get().openChatOnPrefix)).build());
        chatCommandsInfo = sgChat.add(new BoolSetting.Builder().name("chat-commands-info").description("Sends a chat message when you use chat commands (eg toggling module, changing a setting, etc).").defaultValue(true).onChanged(llllIllIllIllll -> {
            Config.get().chatCommandsInfo = llllIllIllIllll;
        }).onModuleActivated(llllIllIlllIIlI -> llllIllIlllIIlI.set(Config.get().chatCommandsInfo)).build());
        deleteChatCommandsInfo = sgChat.add(new BoolSetting.Builder().name("delete-chat-commands-info").description("Delete previous chat messages.").defaultValue(true).onChanged(llllIllIlllIlIl -> {
            Config.get().deleteChatCommandsInfo = llllIllIlllIlIl;
        }).onModuleActivated(llllIllIllllIII -> llllIllIllllIII.set(Config.get().deleteChatCommandsInfo)).visible(chatCommandsInfo::get).build());
        rainbowPrefix = sgChat.add(new BoolSetting.Builder().name("rainbow-prefix").description("Makes the [Meteor] prefix on chat info rainbow.").defaultValue(false).onChanged(llllIllIllllIll -> {
            Config.get().rainbowPrefix = llllIllIllllIll;
        }).onModuleActivated(llllIllIllllllI -> llllIllIllllllI.set(Config.get().rainbowPrefix)).build());
        titleScreenCredits = sgScreens.add(new BoolSetting.Builder().name("title-screen-credits").description("Show Meteor credits on title screen").defaultValue(true).onChanged(llllIlllIIIIIIl -> {
            Config.get().titleScreenCredits = llllIlllIIIIIIl;
        }).onModuleActivated(llllIlllIIIIlII -> llllIlllIIIIlII.set(Config.get().titleScreenCredits)).build());
        titleScreenSplashes = sgScreens.add(new BoolSetting.Builder().name("title-screen-splashes").description("Show Meteor splash texts on title screen").defaultValue(true).onChanged(llllIlllIIIIlll -> {
            Config.get().titleScreenSplashes = llllIlllIIIIlll;
        }).onModuleActivated(llllIlllIIIlIIl -> llllIlllIIIlIIl.set(Config.get().titleScreenSplashes)).build());
        customWindowTitle = sgScreens.add(new BoolSetting.Builder().name("custom-window-title").description("Show custom text in the window title.").defaultValue(false).onChanged(llllIlllIIIllII -> {
            Config.get().customWindowTitle = llllIlllIIIllII;
        }).onModuleActivated(llllIlllIIIllll -> llllIlllIIIllll.set(Config.get().customWindowTitle)).build());
        customWindowTitleText = sgScreens.add(new StringSetting.Builder().name("window-title-text").description("The text it displays in the window title.").defaultValue("Minecraft {mc_version} - Meteor Client {version}").onChanged(llllIlllIIlIIlI -> {
            Config.get().customWindowTitleText = llllIlllIIlIIlI;
        }).onModuleActivated(llllIlllIIlIllI -> llllIlllIIlIllI.set(Config.get().customWindowTitleText)).visible(customWindowTitle::get).build());
        useTeamColor = sgGeneral.add(new BoolSetting.Builder().name("use-team-color").description("Uses player's team color for rendering things like esp and tracers.").defaultValue(true).onChanged(llllIlllIIllIIl -> {
            Config.get().useTeamColor = llllIlllIIllIIl;
        }).onModuleActivated(llllIlllIIlllII -> llllIlllIIlllII.set(Config.get().useTeamColor)).build());
    }

    @Override
    public TabScreen createScreen(GuiTheme llllIlllIlIIIlI) {
        ConfigTab llllIlllIlIIIll;
        currentScreen = new ConfigScreen(llllIlllIlIIIlI, llllIlllIlIIIll);
        return currentScreen;
    }

    public static class ConfigScreen
    extends WindowTabScreen {
        public ConfigScreen(GuiTheme llllllllllllllllIlIlllIIlIllIIII, Tab llllllllllllllllIlIlllIIlIlIllll) {
            super(llllllllllllllllIlIlllIIlIllIIII, llllllllllllllllIlIlllIIlIlIllll);
            ConfigScreen llllllllllllllllIlIlllIIlIlIlllI;
            settings.onActivated();
            llllllllllllllllIlIlllIIlIlIlllI.add(llllllllllllllllIlIlllIIlIllIIII.settings(settings)).expandX();
        }
    }
}

