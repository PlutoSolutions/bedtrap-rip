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

public class Config
extends System<Config> {
    public /* synthetic */ boolean useTeamColor;
    public /* synthetic */ String prefix;
    public /* synthetic */ boolean titleScreenCredits;
    public /* synthetic */ int rotationHoldTicks;
    public /* synthetic */ boolean openChatOnPrefix;
    public /* synthetic */ boolean deleteChatCommandsInfo;
    public /* synthetic */ String font;
    public /* synthetic */ boolean customFont;
    public /* synthetic */ boolean sendDataToApi;
    public /* synthetic */ boolean customWindowTitle;
    public /* synthetic */ boolean rainbowPrefix;
    public /* synthetic */ String customWindowTitleText;
    public final /* synthetic */ String devBuild;
    public /* synthetic */ boolean chatCommandsInfo;
    public final /* synthetic */ Version version;
    public /* synthetic */ boolean titleScreenSplashes;

    private String getString(class_2487 lllIIIllIIIlIII, String lllIIIllIIIIlII, Setting<String> lllIIIllIIIIllI) {
        return lllIIIllIIIlIII.method_10545(lllIIIllIIIIlII) ? lllIIIllIIIlIII.method_10558(lllIIIllIIIIlII) : lllIIIllIIIIllI.get();
    }

    private double getDouble(class_2487 lllIIIlIllllllI, String lllIIIlIllllIlI, Setting<Double> lllIIIlIlllllII) {
        return lllIIIlIllllllI.method_10545(lllIIIlIllllIlI) ? lllIIIlIllllllI.method_10574(lllIIIlIllllIlI) : lllIIIlIlllllII.get().doubleValue();
    }

    public static Config get() {
        return Systems.get(Config.class);
    }

    public Config() {
        super("config");
        Config lllIIIllIlIlIII;
        lllIIIllIlIlIII.font = ConfigTab.font.get();
        lllIIIllIlIlIII.customFont = ConfigTab.customFont.get();
        lllIIIllIlIlIII.sendDataToApi = ConfigTab.sendDataToApi.get();
        lllIIIllIlIlIII.rotationHoldTicks = ConfigTab.rotationHoldTicks.get();
        lllIIIllIlIlIII.prefix = ConfigTab.prefix.get();
        lllIIIllIlIlIII.openChatOnPrefix = ConfigTab.openChatOnPrefix.get();
        lllIIIllIlIlIII.chatCommandsInfo = ConfigTab.chatCommandsInfo.get();
        lllIIIllIlIlIII.deleteChatCommandsInfo = ConfigTab.deleteChatCommandsInfo.get();
        lllIIIllIlIlIII.rainbowPrefix = ConfigTab.rainbowPrefix.get();
        lllIIIllIlIlIII.titleScreenCredits = ConfigTab.titleScreenCredits.get();
        lllIIIllIlIlIII.titleScreenSplashes = ConfigTab.titleScreenSplashes.get();
        lllIIIllIlIlIII.customWindowTitle = ConfigTab.customWindowTitle.get();
        lllIIIllIlIlIII.customWindowTitleText = ConfigTab.customWindowTitleText.get();
        lllIIIllIlIlIII.useTeamColor = ConfigTab.useTeamColor.get();
        ModMetadata lllIIIllIlIIlll = ((ModContainer)FabricLoader.getInstance().getModContainer("meteor-client").get()).getMetadata();
        String lllIIIllIlIIllI = lllIIIllIlIIlll.getVersion().getFriendlyString();
        if (lllIIIllIlIIllI.contains("-")) {
            lllIIIllIlIIllI = lllIIIllIlIIllI.split("-")[0];
        }
        lllIIIllIlIlIII.version = new Version(lllIIIllIlIIllI);
        lllIIIllIlIlIII.devBuild = lllIIIllIlIIlll.getCustomValue("meteor-client:devbuild").getAsString();
    }

    @Override
    public class_2487 toTag() {
        Config lllIIIllIlIIIII;
        class_2487 lllIIIllIIlllll = new class_2487();
        lllIIIllIIlllll.method_10582("version", lllIIIllIlIIIII.version.getOriginalString());
        lllIIIllIIlllll.method_10582("font", lllIIIllIlIIIII.font);
        lllIIIllIIlllll.method_10556("customFont", lllIIIllIlIIIII.customFont);
        lllIIIllIIlllll.method_10549("rainbowSpeed", RainbowColors.GLOBAL.getSpeed());
        lllIIIllIIlllll.method_10556("sendDataToApi", lllIIIllIlIIIII.sendDataToApi);
        lllIIIllIIlllll.method_10569("rotationHoldTicks", lllIIIllIlIIIII.rotationHoldTicks);
        lllIIIllIIlllll.method_10582("prefix", lllIIIllIlIIIII.prefix);
        lllIIIllIIlllll.method_10556("openChatOnPrefix", lllIIIllIlIIIII.openChatOnPrefix);
        lllIIIllIIlllll.method_10556("chatCommandsInfo", lllIIIllIlIIIII.chatCommandsInfo);
        lllIIIllIIlllll.method_10556("deleteChatCommandsInfo", lllIIIllIlIIIII.deleteChatCommandsInfo);
        lllIIIllIIlllll.method_10556("rainbowPrefix", lllIIIllIlIIIII.rainbowPrefix);
        lllIIIllIIlllll.method_10556("titleScreenCredits", lllIIIllIlIIIII.titleScreenCredits);
        lllIIIllIIlllll.method_10556("titleScreenSplashes", lllIIIllIlIIIII.titleScreenSplashes);
        lllIIIllIIlllll.method_10556("customWindowTitle", lllIIIllIlIIIII.customWindowTitle);
        lllIIIllIIlllll.method_10582("customWindowTitleText", lllIIIllIlIIIII.customWindowTitleText);
        lllIIIllIIlllll.method_10556("useTeamColor", lllIIIllIlIIIII.useTeamColor);
        return lllIIIllIIlllll;
    }

    private int getInt(class_2487 lllIIIlIlllIIIl, String lllIIIlIlllIIII, Setting<Integer> lllIIIlIllIllll) {
        return lllIIIlIlllIIIl.method_10545(lllIIIlIlllIIII) ? lllIIIlIlllIIIl.method_10550(lllIIIlIlllIIII) : lllIIIlIllIllll.get().intValue();
    }

    @Override
    public Config fromTag(class_2487 lllIIIllIIllIIl) {
        Config lllIIIllIIllIII;
        lllIIIllIIllIII.font = lllIIIllIIllIII.getString(lllIIIllIIllIIl, "font", ConfigTab.font);
        lllIIIllIIllIII.customFont = lllIIIllIIllIII.getBoolean(lllIIIllIIllIIl, "customFont", ConfigTab.customFont);
        RainbowColors.GLOBAL.setSpeed(lllIIIllIIllIIl.method_10545("rainbowSpeed") ? lllIIIllIIllIIl.method_10574("rainbowSpeed") : ConfigTab.rainbowSpeed.getDefaultValue() / 100.0);
        lllIIIllIIllIII.sendDataToApi = lllIIIllIIllIII.getBoolean(lllIIIllIIllIIl, "sendDataToApi", ConfigTab.sendDataToApi);
        lllIIIllIIllIII.rotationHoldTicks = lllIIIllIIllIII.getInt(lllIIIllIIllIIl, "rotationHoldTicks", ConfigTab.rotationHoldTicks);
        lllIIIllIIllIII.prefix = lllIIIllIIllIII.getString(lllIIIllIIllIIl, "prefix", ConfigTab.prefix);
        lllIIIllIIllIII.openChatOnPrefix = lllIIIllIIllIII.getBoolean(lllIIIllIIllIIl, "openChatOnPrefix", ConfigTab.openChatOnPrefix);
        lllIIIllIIllIII.chatCommandsInfo = lllIIIllIIllIII.getBoolean(lllIIIllIIllIIl, "chatCommandsInfo", ConfigTab.chatCommandsInfo);
        lllIIIllIIllIII.deleteChatCommandsInfo = lllIIIllIIllIII.getBoolean(lllIIIllIIllIIl, "deleteChatCommandsInfo", ConfigTab.deleteChatCommandsInfo);
        lllIIIllIIllIII.rainbowPrefix = lllIIIllIIllIII.getBoolean(lllIIIllIIllIIl, "rainbowPrefix", ConfigTab.rainbowPrefix);
        lllIIIllIIllIII.titleScreenCredits = lllIIIllIIllIII.getBoolean(lllIIIllIIllIIl, "titleScreenCredits", ConfigTab.titleScreenCredits);
        lllIIIllIIllIII.titleScreenSplashes = lllIIIllIIllIII.getBoolean(lllIIIllIIllIIl, "titleScreenSplashes", ConfigTab.titleScreenSplashes);
        lllIIIllIIllIII.customWindowTitle = lllIIIllIIllIII.getBoolean(lllIIIllIIllIIl, "customWindowTitle", ConfigTab.customWindowTitle);
        lllIIIllIIllIII.customWindowTitleText = lllIIIllIIllIII.getString(lllIIIllIIllIIl, "customWindowTitleText", ConfigTab.customWindowTitleText);
        lllIIIllIIllIII.useTeamColor = lllIIIllIIllIII.getBoolean(lllIIIllIIllIIl, "useTeamColor", ConfigTab.useTeamColor);
        return lllIIIllIIllIII;
    }

    private boolean getBoolean(class_2487 lllIIIllIIlIIlI, String lllIIIllIIlIIIl, Setting<Boolean> lllIIIllIIIllIl) {
        return lllIIIllIIlIIlI.method_10545(lllIIIllIIlIIIl) ? lllIIIllIIlIIlI.method_10577(lllIIIllIIlIIIl) : lllIIIllIIIllIl.get().booleanValue();
    }
}

