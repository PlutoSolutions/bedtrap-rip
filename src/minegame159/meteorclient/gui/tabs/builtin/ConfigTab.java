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
    public static final Setting<Integer> rotationHoldTicks;
    public static final Setting<Boolean> titleScreenCredits;
    public static final Setting<Boolean> openChatOnPrefix;
    public static final Setting<Boolean> titleScreenSplashes;
    private static final SettingGroup sgChat;
    public static final Setting<Boolean> customFont;
    public static final Setting<String> customWindowTitleText;
    public static ConfigScreen currentScreen;
    public static final Setting<Boolean> useTeamColor;
    private static final SettingGroup sgGeneral;
    public static final Setting<Boolean> sendDataToApi;
    public static final Setting<Boolean> chatCommandsInfo;
    public static final Setting<Double> rainbowSpeed;
    public static final Setting<String> prefix;
    public static final Setting<String> font;
    public static final Setting<Boolean> rainbowPrefix;
    private static final SettingGroup sgScreens;
    private static final Settings settings;
    public static final Setting<Boolean> deleteChatCommandsInfo;
    public static final Setting<Boolean> customWindowTitle;

    private static void lambda$static$29(Setting setting) {
        setting.set(Config.get().useTeamColor);
    }

    private static void lambda$static$28(Boolean bl) {
        Config.get().useTeamColor = bl;
    }

    private static void lambda$static$18(Boolean bl) {
        Config.get().rainbowPrefix = bl;
    }

    private static void lambda$static$22(Boolean bl) {
        Config.get().titleScreenSplashes = bl;
    }

    private static void lambda$static$12(Boolean bl) {
        Config.get().openChatOnPrefix = bl;
    }

    private static void lambda$static$5(Setting setting) {
        setting.set(RainbowColors.GLOBAL.getSpeed() * 100.0);
    }

    private static void lambda$static$8(Integer n) {
        Config.get().rotationHoldTicks = n;
    }

    private static void lambda$static$19(Setting setting) {
        setting.set(Config.get().rainbowPrefix);
    }

    private static void lambda$static$13(Setting setting) {
        setting.set(Config.get().openChatOnPrefix);
    }

    private static void lambda$static$17(Setting setting) {
        setting.set(Config.get().deleteChatCommandsInfo);
    }

    static Settings access$000() {
        return settings;
    }

    private static void lambda$static$14(Boolean bl) {
        Config.get().chatCommandsInfo = bl;
    }

    private static void lambda$static$26(String string) {
        Config.get().customWindowTitleText = string;
    }

    public ConfigTab() {
        super("Config");
    }

    private static void lambda$static$25(Setting setting) {
        setting.set(Config.get().customWindowTitle);
    }

    private static void lambda$static$10(String string) {
        Config.get().prefix = string;
    }

    private static void lambda$static$2(String string) {
        Config.get().font = string;
        Fonts.load();
    }

    @Override
    public boolean isScreen(class_437 class_4372) {
        return class_4372 instanceof ConfigScreen;
    }

    private static void lambda$static$9(Setting setting) {
        setting.set(Config.get().rotationHoldTicks);
    }

    static {
        settings = new Settings();
        sgGeneral = settings.getDefaultGroup();
        sgChat = settings.createGroup("Chat");
        sgScreens = settings.createGroup("Screens");
        customFont = sgGeneral.add(new BoolSetting.Builder().name("custom-font").description("Use a custom font.").defaultValue(true).onChanged(ConfigTab::lambda$static$0).onModuleActivated(ConfigTab::lambda$static$1).build());
        font = sgGeneral.add(new ProvidedStringSetting.Builder().name("font").description("Custom font to use (picked from .minecraft/meteor-client/fonts folder).").supplier(Fonts::getAvailableFonts).defaultValue("JetBrains Mono").onChanged(ConfigTab::lambda$static$2).onModuleActivated(ConfigTab::lambda$static$3).visible(customFont::get).build());
        rainbowSpeed = sgGeneral.add(new DoubleSetting.Builder().name("rainbow-speed").description("The global rainbow speed.").min(0.0).sliderMax(5.0).max(10.0).defaultValue(0.5).decimalPlaces(2).onChanged(ConfigTab::lambda$static$4).onModuleActivated(ConfigTab::lambda$static$5).build());
        sendDataToApi = sgGeneral.add(new BoolSetting.Builder().name("send-data-to-api").description("If checked, your UUID will be send to Meteor's servers.").defaultValue(true).onChanged(ConfigTab::lambda$static$6).onModuleActivated(ConfigTab::lambda$static$7).build());
        rotationHoldTicks = sgGeneral.add(new IntSetting.Builder().name("rotation-hold-ticks").description("Hold long to hold server side rotation when not sending any packets.").defaultValue(9).onChanged(ConfigTab::lambda$static$8).onModuleActivated(ConfigTab::lambda$static$9).build());
        prefix = sgChat.add(new StringSetting.Builder().name("prefix").description("Prefix.").defaultValue(".").onChanged(ConfigTab::lambda$static$10).onModuleActivated(ConfigTab::lambda$static$11).build());
        openChatOnPrefix = sgChat.add(new BoolSetting.Builder().name("open-chat-on-prefix").description("Open chat when command prefix is pressed. Works like pressing '/' in vanilla.").defaultValue(true).onChanged(ConfigTab::lambda$static$12).onModuleActivated(ConfigTab::lambda$static$13).build());
        chatCommandsInfo = sgChat.add(new BoolSetting.Builder().name("chat-commands-info").description("Sends a chat message when you use chat commands (eg toggling module, changing a setting, etc).").defaultValue(true).onChanged(ConfigTab::lambda$static$14).onModuleActivated(ConfigTab::lambda$static$15).build());
        deleteChatCommandsInfo = sgChat.add(new BoolSetting.Builder().name("delete-chat-commands-info").description("Delete previous chat messages.").defaultValue(true).onChanged(ConfigTab::lambda$static$16).onModuleActivated(ConfigTab::lambda$static$17).visible(chatCommandsInfo::get).build());
        rainbowPrefix = sgChat.add(new BoolSetting.Builder().name("rainbow-prefix").description("Makes the [Meteor] prefix on chat info rainbow.").defaultValue(false).onChanged(ConfigTab::lambda$static$18).onModuleActivated(ConfigTab::lambda$static$19).build());
        titleScreenCredits = sgScreens.add(new BoolSetting.Builder().name("title-screen-credits").description("Show Meteor credits on title screen").defaultValue(true).onChanged(ConfigTab::lambda$static$20).onModuleActivated(ConfigTab::lambda$static$21).build());
        titleScreenSplashes = sgScreens.add(new BoolSetting.Builder().name("title-screen-splashes").description("Show Meteor splash texts on title screen").defaultValue(true).onChanged(ConfigTab::lambda$static$22).onModuleActivated(ConfigTab::lambda$static$23).build());
        customWindowTitle = sgScreens.add(new BoolSetting.Builder().name("custom-window-title").description("Show custom text in the window title.").defaultValue(false).onChanged(ConfigTab::lambda$static$24).onModuleActivated(ConfigTab::lambda$static$25).build());
        customWindowTitleText = sgScreens.add(new StringSetting.Builder().name("window-title-text").description("The text it displays in the window title.").defaultValue("Minecraft {mc_version} - Meteor Client {version}").onChanged(ConfigTab::lambda$static$26).onModuleActivated(ConfigTab::lambda$static$27).visible(customWindowTitle::get).build());
        useTeamColor = sgGeneral.add(new BoolSetting.Builder().name("use-team-color").description("Uses player's team color for rendering things like esp and tracers.").defaultValue(true).onChanged(ConfigTab::lambda$static$28).onModuleActivated(ConfigTab::lambda$static$29).build());
    }

    private static void lambda$static$16(Boolean bl) {
        Config.get().deleteChatCommandsInfo = bl;
    }

    private static void lambda$static$11(Setting setting) {
        setting.set(Config.get().prefix);
    }

    private static void lambda$static$23(Setting setting) {
        setting.set(Config.get().titleScreenSplashes);
    }

    private static void lambda$static$21(Setting setting) {
        setting.set(Config.get().titleScreenCredits);
    }

    private static void lambda$static$3(Setting setting) {
        setting.set(Config.get().font);
    }

    private static void lambda$static$6(Boolean bl) {
        Config.get().sendDataToApi = bl;
        OnlinePlayers.forcePing();
    }

    private static void lambda$static$15(Setting setting) {
        setting.set(Config.get().chatCommandsInfo);
    }

    private static void lambda$static$7(Setting setting) {
        setting.set(Config.get().sendDataToApi);
    }

    private static void lambda$static$1(Setting setting) {
        setting.set(Config.get().customFont);
    }

    private static void lambda$static$20(Boolean bl) {
        Config.get().titleScreenCredits = bl;
    }

    @Override
    public TabScreen createScreen(GuiTheme guiTheme) {
        currentScreen = new ConfigScreen(guiTheme, this);
        return currentScreen;
    }

    private static void lambda$static$27(Setting setting) {
        setting.set(Config.get().customWindowTitleText);
    }

    private static void lambda$static$0(Boolean bl) {
        Config.get().customFont = bl;
        if (currentScreen != null) {
            currentScreen.invalidate();
        }
    }

    private static void lambda$static$4(Double d) {
        RainbowColors.GLOBAL.setSpeed(d / 100.0);
    }

    private static void lambda$static$24(Boolean bl) {
        Config.get().customWindowTitle = bl;
    }

    public static class ConfigScreen
    extends WindowTabScreen {
        public ConfigScreen(GuiTheme guiTheme, Tab tab) {
            super(guiTheme, tab);
            ConfigTab.access$000().onActivated();
            this.add(guiTheme.settings(ConfigTab.access$000())).expandX();
        }
    }
}

