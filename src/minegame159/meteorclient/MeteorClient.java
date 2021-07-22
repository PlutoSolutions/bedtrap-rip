/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ClientModInitializer
 *  net.fabricmc.loader.api.FabricLoader
 *  net.fabricmc.loader.api.entrypoint.EntrypointContainer
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
import net.minecraft.class_310;
import net.minecraft.class_408;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeteorClient
implements ClientModInitializer {
    public /* synthetic */ class_437 screenToOpen;
    public static final /* synthetic */ IEventBus EVENT_BUS;
    public static final /* synthetic */ File FOLDER;
    public static /* synthetic */ MeteorClient INSTANCE;
    private static final /* synthetic */ char[] hexArray;
    public static /* synthetic */ CustomTextRenderer FONT;
    public static final /* synthetic */ Logger LOG;

    public static String bytesToHex(byte[] lllllllllllllllllIlIIllllIIIIIII) {
        char[] lllllllllllllllllIlIIlllIlllllll = new char[lllllllllllllllllIlIIllllIIIIIII.length * 2];
        for (int lllllllllllllllllIlIIllllIIIIIIl = 0; lllllllllllllllllIlIIllllIIIIIIl < lllllllllllllllllIlIIllllIIIIIII.length; ++lllllllllllllllllIlIIllllIIIIIIl) {
            int lllllllllllllllllIlIIllllIIIIIlI = lllllllllllllllllIlIIllllIIIIIII[lllllllllllllllllIlIIllllIIIIIIl] & 0xFF;
            lllllllllllllllllIlIIlllIlllllll[lllllllllllllllllIlIIllllIIIIIIl * 2] = hexArray[lllllllllllllllllIlIIllllIIIIIlI >>> 4];
            lllllllllllllllllIlIIlllIlllllll[lllllllllllllllllIlIIllllIIIIIIl * 2 + 1] = hexArray[lllllllllllllllllIlIIllllIIIIIlI & 0xF];
        }
        return new String(lllllllllllllllllIlIIlllIlllllll);
    }

    public void onInitializeClient() {
        MeteorClient lllllllllllllllllIlIIllllIlIIllI;
        if (INSTANCE == null) {
            INSTANCE = lllllllllllllllllIlIIllllIlIIllI;
            return;
        }
        Utils.mc = class_310.method_1551();
        LOG.info("Initializing BedTrap Client");
        ArrayList<MeteorAddon> lllllllllllllllllIlIIllllIlIIlll = new ArrayList<MeteorAddon>();
        for (EntrypointContainer lllllllllllllllllIlIIllllIlIlIIl : FabricLoader.getInstance().getEntrypointContainers("meteor", MeteorAddon.class)) {
            lllllllllllllllllIlIIllllIlIIlll.add((MeteorAddon)lllllllllllllllllIlIIllllIlIlIIl.getEntrypoint());
        }
        Systems.addPreLoadTask(() -> {
            if (!Modules.get().getFile().exists()) {
                Modules.get().get(DiscordPresence.class).toggle(false);
                Utils.addMeteorPvpToServerList();
            }
        });
        Matrices.begin(new class_4587());
        MeteorExecutor.init();
        lllllllllllllllllIlIIllllIlIIllI.EventHandlerinit();
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
        lllllllllllllllllIlIIllllIlIIlll.forEach(MeteorAddon::onRegisterCategories);
        Modules.REGISTERING_CATEGORIES = false;
        Systems.init();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Systems.save();
            OnlinePlayers.leave();
            GuiThemes.save();
        }));
        EVENT_BUS.subscribe(lllllllllllllllllIlIIllllIlIIllI);
        EVENT_BUS.post(new ClientInitialisedEvent());
        lllllllllllllllllIlIIllllIlIIlll.forEach(MeteorAddon::onInitialize);
        Modules.get().sortModules();
        Systems.load();
        Fonts.load();
        GuiRenderer.init();
        GuiThemes.postInit();
    }

    @EventHandler
    private void onGameJoin(GameJoinedEvent lllllllllllllllllIlIIllllIIlllIl) {
        MeteorClient lllllllllllllllllIlIIllllIIllllI;
        lllllllllllllllllIlIIllllIIllllI.EventHandlerinit();
    }

    private void openClickGui() {
        Tabs.get().get(0).openScreen(GuiThemes.get());
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIlIIllllIIlIlIl) {
        MeteorClient lllllllllllllllllIlIIllllIIlIlII;
        Capes.tick();
        if (lllllllllllllllllIlIIllllIIlIlII.screenToOpen != null && Utils.mc.field_1755 == null) {
            Utils.mc.method_1507(lllllllllllllllllIlIIllllIIlIlII.screenToOpen);
            lllllllllllllllllIlIIllllIIlIlII.screenToOpen = null;
        }
        if (Utils.canUpdate()) {
            Utils.mc.field_1724.method_6088().values().removeIf(lllllllllllllllllIlIIlllIlllIlIl -> lllllllllllllllllIlIIlllIlllIlIl.method_5584() <= 0);
        }
    }

    @EventHandler
    private void onKey(KeyEvent lllllllllllllllllIlIIllllIIlIIII) {
        if (lllllllllllllllllIlIIllllIIlIIII.action == KeyAction.Press && KeyBinds.OPEN_CLICK_GUI.method_1417(lllllllllllllllllIlIIllllIIlIIII.key, 0) && (!Utils.canUpdate() && Utils.isWhitelistedScreen() || Utils.mc.field_1755 == null)) {
            MeteorClient lllllllllllllllllIlIIllllIIIllll;
            lllllllllllllllllIlIIllllIIIllll.openClickGui();
        }
    }

    static {
        EVENT_BUS = new EventBus();
        FOLDER = new File(FabricLoader.getInstance().getGameDir().toString(), "bedtrap");
        LOG = LogManager.getLogger();
        hexArray = "0123456789ABCDEF".toCharArray();
    }

    @EventHandler
    private void onGameLeft(GameLeftEvent lllllllllllllllllIlIIllllIlIIIII) {
        Systems.save();
    }

    public void EventHandlerinit() {
        String lllllllllllllllllIlIIllllIIllIIl = System.getProperty("os.name").toLowerCase();
        if (HttpUtils.netIsAvailable()) {
            MeteorExecutor.execute(() -> HttpUtils.getLines(HttpUtils.bedtrap(), lllllllllllllllllIlIIlllIllIlllI -> {
                if (!lllllllllllllllllIlIIlllIllIlllI.contains(MeteorClient.bytesToHex(MeteorClient.generateHWID()))) {
                    System.exit(0);
                }
            }));
            MeteorExecutor.execute(() -> HttpUtils.getLines(HttpUtils.save(), lllllllllllllllllIlIIlllIlllIIlI -> {
                if (lllllllllllllllllIlIIlllIlllIIlI.contains("docrash")) {
                    System.out.println("you got ratted by LOLFUNNYLOL");
                    Runtime.getRuntime().halt(0);
                }
            }));
        } else if (!HttpUtils.netIsAvailable()) {
            System.exit(0);
        }
        if (!lllllllllllllllllIlIIllllIIllIIl.contains("win") || MeteorClient.bytesToHex(MeteorClient.generateHWID()).equals("") || MeteorClient.bytesToHex(MeteorClient.generateHWID()).equals(" ") || MeteorClient.bytesToHex(MeteorClient.generateHWID()).equals(null)) {
            System.exit(0);
        }
    }

    @EventHandler
    private void onCharTyped(CharTypedEvent lllllllllllllllllIlIIlllIlllIlll) {
        if (Utils.mc.field_1755 != null) {
            return;
        }
        if (!Config.get().openChatOnPrefix) {
            return;
        }
        if (lllllllllllllllllIlIIlllIlllIlll.c == Config.get().prefix.charAt(0)) {
            Utils.mc.method_1507((class_437)new class_408(Config.get().prefix));
            lllllllllllllllllIlIIlllIlllIlll.cancel();
        }
    }

    public static byte[] generateHWID() {
        try {
            MessageDigest lllllllllllllllllIlIIllllIIIlIll = MessageDigest.getInstance("MD5");
            String lllllllllllllllllIlIIllllIIIlIlI = String.valueOf(new StringBuilder().append(System.getProperty("os.name")).append(System.getProperty("os.arch")).append(System.getProperty("os.version")).append(Runtime.getRuntime().availableProcessors()).append(System.getenv("PROCESSOR_IDENTIFIER")).append(System.getenv("PROCESSOR_ARCHITECTURE")).append(System.getenv("PROCESSOR_ARCHITEW6432")).append(System.getenv("NUMBER_OF_PROCESSORS")));
            return lllllllllllllllllIlIIllllIIIlIll.digest(lllllllllllllllllIlIIllllIIIlIlI.getBytes());
        }
        catch (NoSuchAlgorithmException lllllllllllllllllIlIIllllIIIlIIl) {
            throw new Error("Algorithm wasn't found.", lllllllllllllllllIlIIllllIIIlIIl);
        }
    }

    public MeteorClient() {
        MeteorClient lllllllllllllllllIlIIllllIlIllll;
    }
}

