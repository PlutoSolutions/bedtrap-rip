/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 *  net.fabricmc.loader.api.ModContainer
 *  net.fabricmc.loader.api.metadata.ModMetadata
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.systems.config;

import com.g00fy2.versioncompare.Version;
import minegame159.meteorclient.gui.tabs.builtin.ConfigTab;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.utils.render.color.RainbowColors;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.class_2487;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Config
extends System<Config> {
    public boolean useTeamColor;
    public String prefix;
    public boolean titleScreenCredits;
    public int rotationHoldTicks;
    public boolean openChatOnPrefix;
    public boolean deleteChatCommandsInfo;
    public String font = ConfigTab.font.get();
    public boolean customFont = ConfigTab.customFont.get();
    public boolean sendDataToApi = ConfigTab.sendDataToApi.get();
    public boolean customWindowTitle;
    public boolean rainbowPrefix;
    public String customWindowTitleText;
    public final String devBuild;
    public boolean chatCommandsInfo;
    public final Version version;
    public boolean titleScreenSplashes;

    private String getString(class_2487 class_24872, String string, Setting<String> setting) {
        return class_24872.method_10545(string) ? class_24872.method_10558(string) : setting.get();
    }

    private double getDouble(class_2487 class_24872, String string, Setting<Double> setting) {
        return class_24872.method_10545(string) ? class_24872.method_10574(string) : setting.get().doubleValue();
    }

    public static Config get() {
        return Systems.get(Config.class);
    }

    public Config() {
        super("config");
        this.rotationHoldTicks = ConfigTab.rotationHoldTicks.get();
        this.prefix = ConfigTab.prefix.get();
        this.openChatOnPrefix = ConfigTab.openChatOnPrefix.get();
        this.chatCommandsInfo = ConfigTab.chatCommandsInfo.get();
        this.deleteChatCommandsInfo = ConfigTab.deleteChatCommandsInfo.get();
        this.rainbowPrefix = ConfigTab.rainbowPrefix.get();
        this.titleScreenCredits = ConfigTab.titleScreenCredits.get();
        this.titleScreenSplashes = ConfigTab.titleScreenSplashes.get();
        this.customWindowTitle = ConfigTab.customWindowTitle.get();
        this.customWindowTitleText = ConfigTab.customWindowTitleText.get();
        this.useTeamColor = ConfigTab.useTeamColor.get();
        ModMetadata modMetadata = ((ModContainer)FabricLoader.getInstance().getModContainer("meteor-client").get()).getMetadata();
        String string = modMetadata.getVersion().getFriendlyString();
        if (string.contains("-")) {
            string = string.split("-")[0];
        }
        this.version = new Version(string);
        this.devBuild = modMetadata.getCustomValue("meteor-client:devbuild").getAsString();
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("version", this.version.getOriginalString());
        class_24872.method_10582("font", this.font);
        class_24872.method_10556("customFont", this.customFont);
        class_24872.method_10549("rainbowSpeed", RainbowColors.GLOBAL.getSpeed());
        class_24872.method_10556("sendDataToApi", this.sendDataToApi);
        class_24872.method_10569("rotationHoldTicks", this.rotationHoldTicks);
        class_24872.method_10582("prefix", this.prefix);
        class_24872.method_10556("openChatOnPrefix", this.openChatOnPrefix);
        class_24872.method_10556("chatCommandsInfo", this.chatCommandsInfo);
        class_24872.method_10556("deleteChatCommandsInfo", this.deleteChatCommandsInfo);
        class_24872.method_10556("rainbowPrefix", this.rainbowPrefix);
        class_24872.method_10556("titleScreenCredits", this.titleScreenCredits);
        class_24872.method_10556("titleScreenSplashes", this.titleScreenSplashes);
        class_24872.method_10556("customWindowTitle", this.customWindowTitle);
        class_24872.method_10582("customWindowTitleText", this.customWindowTitleText);
        class_24872.method_10556("useTeamColor", this.useTeamColor);
        return class_24872;
    }

    private int getInt(class_2487 class_24872, String string, Setting<Integer> setting) {
        return class_24872.method_10545(string) ? class_24872.method_10550(string) : setting.get().intValue();
    }

    @Override
    public Config fromTag(class_2487 class_24872) {
        this.font = this.getString(class_24872, "font", ConfigTab.font);
        this.customFont = this.getBoolean(class_24872, "customFont", ConfigTab.customFont);
        RainbowColors.GLOBAL.setSpeed(class_24872.method_10545("rainbowSpeed") ? class_24872.method_10574("rainbowSpeed") : ConfigTab.rainbowSpeed.getDefaultValue() / 100.0);
        this.sendDataToApi = this.getBoolean(class_24872, "sendDataToApi", ConfigTab.sendDataToApi);
        this.rotationHoldTicks = this.getInt(class_24872, "rotationHoldTicks", ConfigTab.rotationHoldTicks);
        this.prefix = this.getString(class_24872, "prefix", ConfigTab.prefix);
        this.openChatOnPrefix = this.getBoolean(class_24872, "openChatOnPrefix", ConfigTab.openChatOnPrefix);
        this.chatCommandsInfo = this.getBoolean(class_24872, "chatCommandsInfo", ConfigTab.chatCommandsInfo);
        this.deleteChatCommandsInfo = this.getBoolean(class_24872, "deleteChatCommandsInfo", ConfigTab.deleteChatCommandsInfo);
        this.rainbowPrefix = this.getBoolean(class_24872, "rainbowPrefix", ConfigTab.rainbowPrefix);
        this.titleScreenCredits = this.getBoolean(class_24872, "titleScreenCredits", ConfigTab.titleScreenCredits);
        this.titleScreenSplashes = this.getBoolean(class_24872, "titleScreenSplashes", ConfigTab.titleScreenSplashes);
        this.customWindowTitle = this.getBoolean(class_24872, "customWindowTitle", ConfigTab.customWindowTitle);
        this.customWindowTitleText = this.getString(class_24872, "customWindowTitleText", ConfigTab.customWindowTitleText);
        this.useTeamColor = this.getBoolean(class_24872, "useTeamColor", ConfigTab.useTeamColor);
        return this;
    }

    private boolean getBoolean(class_2487 class_24872, String string, Setting<Boolean> setting) {
        return class_24872.method_10545(string) ? class_24872.method_10577(string) : setting.get().booleanValue();
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }
}

