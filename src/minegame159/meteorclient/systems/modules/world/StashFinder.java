/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  baritone.api.pathing.goals.Goal
 *  baritone.api.pathing.goals.GoalXZ
 *  com.google.common.reflect.TypeToken
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1923
 *  net.minecraft.class_2586
 *  net.minecraft.class_2591
 *  net.minecraft.class_2595
 *  net.minecraft.class_2601
 *  net.minecraft.class_2609
 *  net.minecraft.class_2611
 *  net.minecraft.class_2614
 *  net.minecraft.class_2627
 *  net.minecraft.class_2960
 *  net.minecraft.class_368
 *  net.minecraft.class_368$class_369
 *  net.minecraft.class_3719
 *  net.minecraft.class_374
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 */
package minegame159.meteorclient.systems.modules.world;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalXZ;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.systems.RenderSystem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.ChunkDataEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.containers.WVerticalList;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StorageBlockListSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1923;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2595;
import net.minecraft.class_2601;
import net.minecraft.class_2609;
import net.minecraft.class_2611;
import net.minecraft.class_2614;
import net.minecraft.class_2627;
import net.minecraft.class_2960;
import net.minecraft.class_368;
import net.minecraft.class_3719;
import net.minecraft.class_374;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class StashFinder
extends Module {
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<List<class_2591<?>>> storageBlocks;
    private final /* synthetic */ Setting<Boolean> sendNotifications;
    private final /* synthetic */ Setting<Integer> minimumDistance;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Integer> minimumStorageCount;
    private static final /* synthetic */ Gson GSON;
    public /* synthetic */ List<Chunk> chunks;

    public StashFinder() {
        super(Categories.World, "stash-finder", "Searches loaded chunks for storage blocks. Saves to <your minecraft folder>/meteor-client");
        StashFinder llIlIlIIlllll;
        llIlIlIIlllll.sgGeneral = llIlIlIIlllll.settings.getDefaultGroup();
        llIlIlIIlllll.storageBlocks = llIlIlIIlllll.sgGeneral.add(new StorageBlockListSetting.Builder().name("storage-blocks").description("Select the storage blocks to search for.").defaultValue(Arrays.asList(StorageBlockListSetting.STORAGE_BLOCKS)).build());
        llIlIlIIlllll.minimumStorageCount = llIlIlIIlllll.sgGeneral.add(new IntSetting.Builder().name("minimum-storage-cont").description("The minimum amount of storage blocks in a chunk to record the chunk.").defaultValue(4).min(1).build());
        llIlIlIIlllll.minimumDistance = llIlIlIIlllll.sgGeneral.add(new IntSetting.Builder().name("minimum-distance").description("The minimum distance you must be from spawn to record a certain chunk.").defaultValue(0).min(0).sliderMax(10000).build());
        llIlIlIIlllll.sendNotifications = llIlIlIIlllll.sgGeneral.add(new BoolSetting.Builder().name("send-notifications").description("Sends Minecraft notifications when new stashes are found.").defaultValue(true).build());
        llIlIlIIlllll.mode = llIlIlIIlllll.sgGeneral.add(new EnumSetting.Builder().name("notification-mode").description("The mode to use for notifications.").defaultValue(Mode.Toast).visible(llIlIlIIlllll.sendNotifications::get).build());
        llIlIlIIlllll.chunks = new ArrayList<Chunk>();
    }

    private File getJsonFile() {
        return new File(new File(new File(MeteorClient.FOLDER, "stashes"), Utils.getWorldName()), "stashes.json");
    }

    private File getCsvFile() {
        return new File(new File(new File(MeteorClient.FOLDER, "stashes"), Utils.getWorldName()), "stashes.csv");
    }

    @EventHandler
    private void onChunkData(ChunkDataEvent llIlIlIIlIIII) {
        StashFinder llIlIlIIIllII;
        double llIlIlIIIlllI;
        double llIlIlIIIllll = Math.abs(llIlIlIIlIIII.chunk.method_12004().field_9181 * 16);
        if (Math.sqrt(llIlIlIIIllll * llIlIlIIIllll + (llIlIlIIIlllI = (double)Math.abs(llIlIlIIlIIII.chunk.method_12004().field_9180 * 16)) * llIlIlIIIlllI) < (double)llIlIlIIIllII.minimumDistance.get().intValue()) {
            return;
        }
        Chunk llIlIlIIIllIl = new Chunk(llIlIlIIlIIII.chunk.method_12004());
        for (class_2586 llIlIlIIlIlII : llIlIlIIlIIII.chunk.method_12214().values()) {
            if (!llIlIlIIIllII.storageBlocks.get().contains((Object)llIlIlIIlIlII.method_11017())) continue;
            if (llIlIlIIlIlII instanceof class_2595) {
                ++llIlIlIIIllIl.chests;
                continue;
            }
            if (llIlIlIIlIlII instanceof class_3719) {
                ++llIlIlIIIllIl.barrels;
                continue;
            }
            if (llIlIlIIlIlII instanceof class_2627) {
                ++llIlIlIIIllIl.shulkers;
                continue;
            }
            if (llIlIlIIlIlII instanceof class_2611) {
                ++llIlIlIIIllIl.enderChests;
                continue;
            }
            if (llIlIlIIlIlII instanceof class_2609) {
                ++llIlIlIIIllIl.furnaces;
                continue;
            }
            if (llIlIlIIlIlII instanceof class_2601) {
                ++llIlIlIIIllIl.dispensersDroppers;
                continue;
            }
            if (!(llIlIlIIlIlII instanceof class_2614)) continue;
            ++llIlIlIIIllIl.hoppers;
        }
        if (llIlIlIIIllIl.getTotal() >= llIlIlIIIllII.minimumStorageCount.get()) {
            Chunk llIlIlIIlIIll = null;
            int llIlIlIIlIIlI = llIlIlIIIllII.chunks.indexOf(llIlIlIIIllIl);
            if (llIlIlIIlIIlI < 0) {
                llIlIlIIIllII.chunks.add(llIlIlIIIllIl);
            } else {
                llIlIlIIlIIll = llIlIlIIIllII.chunks.set(llIlIlIIlIIlI, llIlIlIIIllIl);
            }
            llIlIlIIIllII.saveJson();
            llIlIlIIIllII.saveCsv();
            if (!(!llIlIlIIIllII.sendNotifications.get().booleanValue() || llIlIlIIIllIl.equals(llIlIlIIlIIll) && llIlIlIIIllIl.countsEqual(llIlIlIIlIIll))) {
                if (llIlIlIIIllII.mode.get() == Mode.Toast) {
                    llIlIlIIIllII.mc.method_1566().method_1999(new class_368(){
                        private /* synthetic */ long timer;
                        private /* synthetic */ long lastTime;
                        {
                            1 llllllllllllllllIllIIIllllllIllI;
                            llllllllllllllllIllIIIllllllIllI.lastTime = -1L;
                        }

                        public class_368.class_369 method_1986(class_4587 llllllllllllllllIllIIIlllllIlIIl, class_374 llllllllllllllllIllIIIlllllIllII, long llllllllllllllllIllIIIlllllIlIll) {
                            1 llllllllllllllllIllIIIlllllIlllI;
                            if (llllllllllllllllIllIIIlllllIlllI.lastTime == -1L) {
                                llllllllllllllllIllIIIlllllIlllI.lastTime = llllllllllllllllIllIIIlllllIlIll;
                            } else {
                                llllllllllllllllIllIIIlllllIlllI.timer += llllllllllllllllIllIIIlllllIlIll - llllllllllllllllIllIIIlllllIlllI.lastTime;
                            }
                            llllllllllllllllIllIIIlllllIllII.method_1995().method_1531().method_22813(new class_2960("textures/gui/toasts.png"));
                            RenderSystem.color4f((float)1.0f, (float)1.0f, (float)1.0f, (float)255.0f);
                            llllllllllllllllIllIIIlllllIllII.method_25302(llllllllllllllllIllIIIlllllIlIIl, 0, 0, 0, 32, 160, 32);
                            llllllllllllllllIllIIIlllllIllII.method_1995().field_1772.method_1729(llllllllllllllllIllIIIlllllIlIIl, "StashRecorder found stash.", 12.0f, 12.0f, -11534256);
                            return llllllllllllllllIllIIIlllllIlllI.timer >= 32000L ? class_368.class_369.field_2209 : class_368.class_369.field_2210;
                        }
                    });
                } else {
                    llIlIlIIIllII.info("(highlight)Found stash.", new Object[0]);
                }
            }
        }
    }

    static {
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }

    private void load() {
        block9: {
            File llIlIIlIIlllI;
            StashFinder llIlIIlIlIIII;
            boolean llIlIIlIIllll;
            block8: {
                llIlIIlIIllll = false;
                llIlIIlIIlllI = llIlIIlIlIIII.getJsonFile();
                if (llIlIIlIIlllI.exists()) {
                    try {
                        FileReader llIlIIlIlIlll = new FileReader(llIlIIlIIlllI);
                        llIlIIlIlIIII.chunks = (List)GSON.fromJson((Reader)llIlIIlIlIlll, new TypeToken<List<Chunk>>(){
                            {
                                2 llllllllllllllllIlllIIlIIlllIIll;
                            }
                        }.getType());
                        llIlIIlIlIlll.close();
                        for (Chunk llIlIIlIllIII : llIlIIlIlIIII.chunks) {
                            llIlIIlIllIII.calculatePos();
                        }
                        llIlIIlIIllll = true;
                    }
                    catch (Exception llIlIIlIlIllI) {
                        if (llIlIIlIlIIII.chunks != null) break block8;
                        llIlIIlIlIIII.chunks = new ArrayList<Chunk>();
                    }
                }
            }
            llIlIIlIIlllI = llIlIIlIlIIII.getCsvFile();
            if (!llIlIIlIIllll && llIlIIlIIlllI.exists()) {
                try {
                    String llIlIIlIlIIlI;
                    BufferedReader llIlIIlIlIIll = new BufferedReader(new FileReader(llIlIIlIIlllI));
                    llIlIIlIlIIll.readLine();
                    while ((llIlIIlIlIIlI = llIlIIlIlIIll.readLine()) != null) {
                        String[] llIlIIlIlIlIl = llIlIIlIlIIlI.split(" ");
                        Chunk llIlIIlIlIlII = new Chunk(new class_1923(Integer.parseInt(llIlIIlIlIlIl[0]), Integer.parseInt(llIlIIlIlIlIl[1])));
                        llIlIIlIlIlII.chests = Integer.parseInt(llIlIIlIlIlIl[2]);
                        llIlIIlIlIlII.shulkers = Integer.parseInt(llIlIIlIlIlIl[3]);
                        llIlIIlIlIlII.enderChests = Integer.parseInt(llIlIIlIlIlIl[4]);
                        llIlIIlIlIlII.furnaces = Integer.parseInt(llIlIIlIlIlIl[5]);
                        llIlIIlIlIlII.dispensersDroppers = Integer.parseInt(llIlIIlIlIlIl[6]);
                        llIlIIlIlIlII.hoppers = Integer.parseInt(llIlIIlIlIlIl[7]);
                        llIlIIlIlIIII.chunks.add(llIlIIlIlIlII);
                    }
                    llIlIIlIlIIll.close();
                }
                catch (Exception llIlIIlIlIIIl) {
                    if (llIlIIlIlIIII.chunks != null) break block9;
                    llIlIIlIlIIII.chunks = new ArrayList<Chunk>();
                }
            }
        }
    }

    private void fillTable(GuiTheme llIlIIllIIllI, WTable llIlIIllIlIII) {
        StashFinder llIlIIllIIlll;
        for (Chunk llIlIIllIlIll : llIlIIllIIlll.chunks) {
            llIlIIllIlIII.add(llIlIIllIIllI.label(String.valueOf(new StringBuilder().append("Pos: ").append(llIlIIllIlIll.x).append(", ").append(llIlIIllIlIll.z))));
            llIlIIllIlIII.add(llIlIIllIIllI.label(String.valueOf(new StringBuilder().append("Total: ").append(llIlIIllIlIll.getTotal()))));
            WButton llIlIIllIlllI = llIlIIllIlIII.add(llIlIIllIIllI.button("Open")).widget();
            llIlIIllIlllI.action = () -> {
                StashFinder llIlIIIIlIllI;
                llIlIIIIlIllI.mc.method_1507((class_437)new ChunkScreen(llIlIIllIIllI, llIlIIllIlIll));
            };
            WButton llIlIIllIllIl = llIlIIllIlIII.add(llIlIIllIIllI.button("Goto")).widget();
            llIlIIllIllIl.action = () -> BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)new GoalXZ(llIlIIIIlllIl.x, llIlIIIIlllIl.z));
            WMinus llIlIIllIllII = llIlIIllIlIII.add(llIlIIllIIllI.minus()).widget();
            llIlIIllIllII.action = () -> {
                StashFinder llIlIIIlIIIll;
                if (llIlIIIlIIIll.chunks.remove(llIlIIllIlIll)) {
                    llIlIIllIlIII.clear();
                    llIlIIIlIIIll.fillTable(llIlIIllIIllI, llIlIIllIlIII);
                    llIlIIIlIIIll.saveJson();
                    llIlIIIlIIIll.saveCsv();
                }
            };
            llIlIIllIlIII.row();
        }
    }

    private void saveCsv() {
        try {
            StashFinder llIlIIIllllII;
            File llIlIIlIIIIII = llIlIIIllllII.getCsvFile();
            llIlIIlIIIIII.getParentFile().mkdirs();
            FileWriter llIlIIIllllll = new FileWriter(llIlIIlIIIIII);
            llIlIIIllllll.write("X,Z,Chests,Shulkers,EnderChests,Furnaces,DispensersDroppers,Hopper\n");
            for (Chunk llIlIIlIIIIIl : llIlIIIllllII.chunks) {
                llIlIIlIIIIIl.write(llIlIIIllllll);
            }
            ((Writer)llIlIIIllllll).close();
        }
        catch (IOException llIlIIIlllllI) {
            llIlIIIlllllI.printStackTrace();
        }
    }

    @Override
    public WWidget getWidget(GuiTheme llIlIIlllllll) {
        StashFinder llIlIlIIIIIII;
        llIlIlIIIIIII.chunks.sort(Comparator.comparingInt(llIlIIIIIllII -> -llIlIIIIIllII.getTotal()));
        WVerticalList llIlIIllllllI = llIlIIlllllll.verticalList();
        WButton llIlIIlllllIl = llIlIIllllllI.add(llIlIIlllllll.button("Clear")).widget();
        WTable llIlIIlllllII = new WTable();
        if (llIlIlIIIIIII.chunks.size() > 0) {
            llIlIIllllllI.add(llIlIIlllllII);
        }
        llIlIIlllllIl.action = () -> {
            StashFinder llIlIIIIIllll;
            llIlIIIIIllll.chunks.clear();
            llIlIIlllllII.clear();
        };
        llIlIlIIIIIII.fillTable(llIlIIlllllll, llIlIIlllllII);
        return llIlIIllllllI;
    }

    private void saveJson() {
        try {
            StashFinder llIlIIIllIIII;
            File llIlIIIllIlII = llIlIIIllIIII.getJsonFile();
            llIlIIIllIlII.getParentFile().mkdirs();
            FileWriter llIlIIIllIIll = new FileWriter(llIlIIIllIlII);
            GSON.toJson(llIlIIIllIIII.chunks, (Appendable)llIlIIIllIIll);
            ((Writer)llIlIIIllIIll).close();
        }
        catch (IOException llIlIIIllIIlI) {
            llIlIIIllIIlI.printStackTrace();
        }
    }

    @Override
    public void onActivate() {
        StashFinder llIlIlIIlllII;
        llIlIlIIlllII.load();
    }

    public static class Chunk {
        public /* synthetic */ int shulkers;
        public /* synthetic */ int barrels;
        public /* synthetic */ int furnaces;
        public /* synthetic */ class_1923 chunkPos;
        public /* synthetic */ int enderChests;
        public /* synthetic */ int dispensersDroppers;
        public /* synthetic */ int hoppers;
        public transient /* synthetic */ int x;
        private static final /* synthetic */ StringBuilder sb;
        public transient /* synthetic */ int z;
        public /* synthetic */ int chests;

        public Chunk(class_1923 lllllIlIIllIlIl) {
            Chunk lllllIlIIllIllI;
            lllllIlIIllIllI.chunkPos = lllllIlIIllIlIl;
            lllllIlIIllIllI.calculatePos();
        }

        static {
            sb = new StringBuilder();
        }

        public int getTotal() {
            Chunk lllllIlIIllIIII;
            return lllllIlIIllIIII.chests + lllllIlIIllIIII.barrels + lllllIlIIllIIII.shulkers + lllllIlIIllIIII.enderChests + lllllIlIIllIIII.furnaces + lllllIlIIllIIII.dispensersDroppers + lllllIlIIllIIII.hoppers;
        }

        public boolean countsEqual(Chunk lllllIlIIlIIlIl) {
            Chunk lllllIlIIlIIlII;
            if (lllllIlIIlIIlIl == null) {
                return false;
            }
            return lllllIlIIlIIlII.chests != lllllIlIIlIIlIl.chests || lllllIlIIlIIlII.barrels != lllllIlIIlIIlIl.barrels || lllllIlIIlIIlII.shulkers != lllllIlIIlIIlIl.shulkers || lllllIlIIlIIlII.enderChests != lllllIlIIlIIlIl.enderChests || lllllIlIIlIIlII.furnaces != lllllIlIIlIIlIl.furnaces || lllllIlIIlIIlII.dispensersDroppers != lllllIlIIlIIlIl.dispensersDroppers || lllllIlIIlIIlII.hoppers != lllllIlIIlIIlIl.hoppers;
        }

        public void write(Writer lllllIlIIlIlIIl) throws IOException {
            Chunk lllllIlIIlIlIlI;
            sb.setLength(0);
            sb.append(lllllIlIIlIlIlI.x).append(',').append(lllllIlIIlIlIlI.z).append(',');
            sb.append(lllllIlIIlIlIlI.chests).append(',').append(lllllIlIIlIlIlI.barrels).append(',').append(lllllIlIIlIlIlI.shulkers).append(',').append(lllllIlIIlIlIlI.enderChests).append(',').append(lllllIlIIlIlIlI.furnaces).append(',').append(lllllIlIIlIlIlI.dispensersDroppers).append(',').append(lllllIlIIlIlIlI.hoppers).append('\n');
            lllllIlIIlIlIIl.write(String.valueOf(sb));
        }

        public boolean equals(Object lllllIlIIIllIll) {
            Chunk lllllIlIIIlllll;
            if (lllllIlIIIlllll == lllllIlIIIllIll) {
                return true;
            }
            if (lllllIlIIIllIll == null || lllllIlIIIlllll.getClass() != lllllIlIIIllIll.getClass()) {
                return false;
            }
            Chunk lllllIlIIIlllIl = (Chunk)lllllIlIIIllIll;
            return Objects.equals((Object)lllllIlIIIlllll.chunkPos, (Object)lllllIlIIIlllIl.chunkPos);
        }

        public int hashCode() {
            Chunk lllllIlIIIllIII;
            return Objects.hash(new Object[]{lllllIlIIIllIII.chunkPos});
        }

        public void calculatePos() {
            Chunk lllllIlIIllIIlI;
            lllllIlIIllIIlI.x = lllllIlIIllIIlI.chunkPos.field_9181 * 16 + 8;
            lllllIlIIllIIlI.z = lllllIlIIllIIlI.chunkPos.field_9180 * 16 + 8;
        }
    }

    private static class ChunkScreen
    extends WindowScreen {
        public ChunkScreen(GuiTheme lllllllllllllllllIIllllIlIlIIlIl, Chunk lllllllllllllllllIIllllIlIlIIlII) {
            super(lllllllllllllllllIIllllIlIlIIlIl, String.valueOf(new StringBuilder().append("Chunk at ").append(lllllllllllllllllIIllllIlIlIIlII.x).append(", ").append(lllllllllllllllllIIllllIlIlIIlII.z)));
            ChunkScreen lllllllllllllllllIIllllIlIlIIllI;
            WTable lllllllllllllllllIIllllIlIlIIIll = lllllllllllllllllIIllllIlIlIIllI.add(lllllllllllllllllIIllllIlIlIIlIl.table()).expandX().widget();
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label("Total:"));
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label(String.valueOf(new StringBuilder().append(lllllllllllllllllIIllllIlIlIIlII.getTotal()).append(""))));
            lllllllllllllllllIIllllIlIlIIIll.row();
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.horizontalSeparator()).expandX();
            lllllllllllllllllIIllllIlIlIIIll.row();
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label("Chests:"));
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label(String.valueOf(new StringBuilder().append(lllllllllllllllllIIllllIlIlIIlII.chests).append(""))));
            lllllllllllllllllIIllllIlIlIIIll.row();
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label("Barrels:"));
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label(String.valueOf(new StringBuilder().append(lllllllllllllllllIIllllIlIlIIlII.barrels).append(""))));
            lllllllllllllllllIIllllIlIlIIIll.row();
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label("Shulkers:"));
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label(String.valueOf(new StringBuilder().append(lllllllllllllllllIIllllIlIlIIlII.shulkers).append(""))));
            lllllllllllllllllIIllllIlIlIIIll.row();
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label("Ender Chests:"));
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label(String.valueOf(new StringBuilder().append(lllllllllllllllllIIllllIlIlIIlII.enderChests).append(""))));
            lllllllllllllllllIIllllIlIlIIIll.row();
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label("Furnaces:"));
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label(String.valueOf(new StringBuilder().append(lllllllllllllllllIIllllIlIlIIlII.furnaces).append(""))));
            lllllllllllllllllIIllllIlIlIIIll.row();
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label("Dispensers and droppers:"));
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label(String.valueOf(new StringBuilder().append(lllllllllllllllllIIllllIlIlIIlII.dispensersDroppers).append(""))));
            lllllllllllllllllIIllllIlIlIIIll.row();
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label("Hoppers:"));
            lllllllllllllllllIIllllIlIlIIIll.add(lllllllllllllllllIIllllIlIlIIlIl.label(String.valueOf(new StringBuilder().append(lllllllllllllllllIIllllIlIlIIlII.hoppers).append(""))));
        }
    }

    public static enum Mode {
        Chat,
        Toast;


        private Mode() {
            Mode lllllllllllllllllIIlllllIIlIIIll;
        }
    }
}

