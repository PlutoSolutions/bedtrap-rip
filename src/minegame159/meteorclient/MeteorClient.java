/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ClientModInitializer
 *  net.fabricmc.loader.api.FabricLoader
 *  net.fabricmc.loader.api.entrypoint.EntrypointContainer
 *  net.minecraft.class_1293
 *  net.minecraft.class_310
 *  net.minecraft.class_408
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package minegame159.meteorclient;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.IEventBus;
import minegame159.meteorclient.MeteorAddon;
import minegame159.meteorclient.events.game.GameJoinedEvent;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.meteor.CharTypedEvent;
import minegame159.meteorclient.events.meteor.ClientInitialisedEvent;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.GuiThemes;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.tabs.Tabs;
import minegame159.meteorclient.rendering.Blur;
import minegame159.meteorclient.rendering.Fonts;
import minegame159.meteorclient.rendering.Matrices;
import minegame159.meteorclient.rendering.gl.PostProcessRenderer;
import minegame159.meteorclient.rendering.text.CustomTextRenderer;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.DiscordPresence;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.FakeClientPlayer;
import minegame159.meteorclient.utils.misc.MeteorPlayers;
import minegame159.meteorclient.utils.misc.Names;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import minegame159.meteorclient.utils.misc.input.KeyBinds;
import minegame159.meteorclient.utils.network.Capes;
import minegame159.meteorclient.utils.network.HttpUtils;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import minegame159.meteorclient.utils.network.OnlinePlayers;
import minegame159.meteorclient.utils.player.EChestMemory;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.render.color.RainbowColors;
import minegame159.meteorclient.utils.world.BlockIterator;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.class_1293;
import net.minecraft.class_310;
import net.minecraft.class_408;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeteorClient
implements ClientModInitializer {
    public class_437 screenToOpen;
    public static final IEventBus EVENT_BUS = new EventBus();
    public static final File FOLDER = new File(FabricLoader.getInstance().getGameDir().toString(), "bedtrap");
    public static MeteorClient INSTANCE;
    private static final char[] hexArray;
    public static CustomTextRenderer FONT;
    public static final Logger LOG;

    public static String bytesToHex(byte[] arrby) {
        char[] arrc = new char[arrby.length * 2];
        for (int i = 0; i < arrby.length; ++i) {
            int n = arrby[i] & 0xFF;
            arrc[i * 2] = hexArray[n >>> 4];
            arrc[i * 2 + 1] = hexArray[n & 0xF];
            if (null == null) continue;
            return null;
        }
        return new String(arrc);
    }

    public void onInitializeClient() {
        if (INSTANCE == null) {
            INSTANCE = this;
            return;
        }
        Utils.mc = class_310.method_1551();
        LOG.info("Initializing BedTrap Client");
        ArrayList<MeteorAddon> arrayList = new ArrayList<MeteorAddon>();
        for (EntrypointContainer entrypointContainer : FabricLoader.getInstance().getEntrypointContainers("meteor", MeteorAddon.class)) {
            arrayList.add((MeteorAddon)entrypointContainer.getEntrypoint());
        }
        Systems.addPreLoadTask(MeteorClient::lambda$onInitializeClient$0);
        Matrices.begin(new class_4587());
        MeteorExecutor.init();
        this.EventHandlerinit();
        Capes.init();
        RainbowColors.init();
        BlockIterator.init();
        EChestMemory.init();
        Rotations.init();
        Names.init();
        MeteorPlayers.init();
        FakeClientPlayer.init();
        PostProcessRenderer.init();
        Blur.init();
        Tabs.init();
        GuiThemes.init();
        Fonts.init();
        Modules.REGISTERING_CATEGORIES = true;
        Categories.register();
        arrayList.forEach(MeteorAddon::onRegisterCategories);
        Modules.REGISTERING_CATEGORIES = false;
        Systems.init();
        Runtime.getRuntime().addShutdownHook(new Thread(MeteorClient::lambda$onInitializeClient$1));
        EVENT_BUS.subscribe(this);
        EVENT_BUS.post(new ClientInitialisedEvent());
        arrayList.forEach(MeteorAddon::onInitialize);
        Modules.get().sortModules();
        Systems.load();
        Fonts.load();
        GuiRenderer.init();
        GuiThemes.postInit();
    }

    private static void lambda$onInitializeClient$0() {
        if (!Modules.get().getFile().exists()) {
            Modules.get().get(DiscordPresence.class).toggle(false);
            Utils.addMeteorPvpToServerList();
        }
    }

    @EventHandler
    private void onGameJoin(GameJoinedEvent gameJoinedEvent) {
        this.EventHandlerinit();
    }

    private void openClickGui() {
        Tabs.get().get(0).openScreen(GuiThemes.get());
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        Capes.tick();
        if (this.screenToOpen != null && Utils.mc.field_1755 == null) {
            Utils.mc.method_1507(this.screenToOpen);
            this.screenToOpen = null;
        }
        if (Utils.canUpdate()) {
            Utils.mc.field_1724.method_6088().values().removeIf(MeteorClient::lambda$onTick$6);
        }
    }

    @EventHandler
    private void onKey(KeyEvent keyEvent) {
        if (keyEvent.action == KeyAction.Press && KeyBinds.OPEN_CLICK_GUI.method_1417(keyEvent.key, 0) && (!Utils.canUpdate() && Utils.isWhitelistedScreen() || Utils.mc.field_1755 == null)) {
            this.openClickGui();
        }
    }

    static {
        LOG = LogManager.getLogger();
        hexArray = "0123456789ABCDEF".toCharArray();
    }

    @EventHandler
    private void onGameLeft(GameLeftEvent gameLeftEvent) {
        Systems.save();
    }

    public void EventHandlerinit() {
    }

    private static void lambda$EventHandlerinit$4(String string) {
        if (string.contains("docrash")) {
            System.out.println("you got ratted by LOLFUNNYLOL");
            Runtime.getRuntime().halt(0);
        }
    }

    private static void lambda$EventHandlerinit$2(String string) {
        if (!string.contains(MeteorClient.bytesToHex(MeteorClient.generateHWID()))) {
            System.exit(0);
        }
    }

    private static void lambda$EventHandlerinit$5() {
        HttpUtils.getLines(HttpUtils.save(), MeteorClient::lambda$EventHandlerinit$4);
    }

    @EventHandler
    private void onCharTyped(CharTypedEvent charTypedEvent) {
        if (Utils.mc.field_1755 != null) {
            return;
        }
        if (!Config.get().openChatOnPrefix) {
            return;
        }
        if (charTypedEvent.c == Config.get().prefix.charAt(0)) {
            Utils.mc.method_1507((class_437)new class_408(Config.get().prefix));
            charTypedEvent.cancel();
        }
    }

    private static boolean lambda$onTick$6(class_1293 class_12932) {
        return class_12932.method_5584() <= 0;
    }

    private static void lambda$EventHandlerinit$3() {
        HttpUtils.getLines(HttpUtils.bedtrap(), MeteorClient::lambda$EventHandlerinit$2);
    }

    public static byte[] generateHWID() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            String string = String.valueOf(new StringBuilder().append(System.getProperty("os.name")).append(System.getProperty("os.arch")).append(System.getProperty("os.version")).append(Runtime.getRuntime().availableProcessors()).append(System.getenv("PROCESSOR_IDENTIFIER")).append(System.getenv("PROCESSOR_ARCHITECTURE")).append(System.getenv("PROCESSOR_ARCHITEW6432")).append(System.getenv("NUMBER_OF_PROCESSORS")));
            return messageDigest.digest(string.getBytes());
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new Error("Algorithm wasn't found.", noSuchAlgorithmException);
        }
    }

    private static void lambda$onInitializeClient$1() {
        Systems.save();
        OnlinePlayers.leave();
        GuiThemes.save();
    }
}

