/*
 * Decompiled with CFR 0.151.
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
import java.io.InputStreamReader;
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
    private final Setting<Mode> mode;
    private final Setting<List<class_2591<?>>> storageBlocks;
    private final Setting<Boolean> sendNotifications;
    private final Setting<Integer> minimumDistance;
    private final SettingGroup sgGeneral;
    private final Setting<Integer> minimumStorageCount;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public List<Chunk> chunks;

    public StashFinder() {
        super(Categories.World, "stash-finder", "Searches loaded chunks for storage blocks. Saves to <your minecraft folder>/meteor-client");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.storageBlocks = this.sgGeneral.add(new StorageBlockListSetting.Builder().name("storage-blocks").description("Select the storage blocks to search for.").defaultValue(Arrays.asList(StorageBlockListSetting.STORAGE_BLOCKS)).build());
        this.minimumStorageCount = this.sgGeneral.add(new IntSetting.Builder().name("minimum-storage-cont").description("The minimum amount of storage blocks in a chunk to record the chunk.").defaultValue(4).min(1).build());
        this.minimumDistance = this.sgGeneral.add(new IntSetting.Builder().name("minimum-distance").description("The minimum distance you must be from spawn to record a certain chunk.").defaultValue(0).min(0).sliderMax(10000).build());
        this.sendNotifications = this.sgGeneral.add(new BoolSetting.Builder().name("send-notifications").description("Sends Minecraft notifications when new stashes are found.").defaultValue(true).build());
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("notification-mode").description("The mode to use for notifications.").defaultValue(Mode.Toast).visible(this.sendNotifications::get).build());
        this.chunks = new ArrayList<Chunk>();
    }

    private File getJsonFile() {
        return new File(new File(new File(MeteorClient.FOLDER, "stashes"), Utils.getWorldName()), "stashes.json");
    }

    private static int lambda$getWidget$0(Chunk chunk) {
        return -chunk.getTotal();
    }

    private File getCsvFile() {
        return new File(new File(new File(MeteorClient.FOLDER, "stashes"), Utils.getWorldName()), "stashes.csv");
    }

    @EventHandler
    private void onChunkData(ChunkDataEvent chunkDataEvent) {
        double d;
        double d2 = Math.abs(chunkDataEvent.chunk.method_12004().field_9181 * 16);
        if (Math.sqrt(d2 * d2 + (d = (double)Math.abs(chunkDataEvent.chunk.method_12004().field_9180 * 16)) * d) < (double)this.minimumDistance.get().intValue()) {
            return;
        }
        Chunk chunk = new Chunk(chunkDataEvent.chunk.method_12004());
        for (class_2586 class_25862 : chunkDataEvent.chunk.method_12214().values()) {
            if (!this.storageBlocks.get().contains(class_25862.method_11017())) continue;
            if (class_25862 instanceof class_2595) {
                ++chunk.chests;
                continue;
            }
            if (class_25862 instanceof class_3719) {
                ++chunk.barrels;
                continue;
            }
            if (class_25862 instanceof class_2627) {
                ++chunk.shulkers;
                continue;
            }
            if (class_25862 instanceof class_2611) {
                ++chunk.enderChests;
                continue;
            }
            if (class_25862 instanceof class_2609) {
                ++chunk.furnaces;
                continue;
            }
            if (class_25862 instanceof class_2601) {
                ++chunk.dispensersDroppers;
                continue;
            }
            if (!(class_25862 instanceof class_2614)) continue;
            ++chunk.hoppers;
        }
        if (chunk.getTotal() >= this.minimumStorageCount.get()) {
            Object object = null;
            int n = this.chunks.indexOf(chunk);
            if (n < 0) {
                this.chunks.add(chunk);
            } else {
                object = this.chunks.set(n, chunk);
            }
            this.saveJson();
            this.saveCsv();
            if (!(!this.sendNotifications.get().booleanValue() || chunk.equals(object) && chunk.countsEqual((Chunk)object))) {
                if (this.mode.get() == Mode.Toast) {
                    this.mc.method_1566().method_1999(new class_368(this){
                        private long timer;
                        final StashFinder this$0;
                        private long lastTime;
                        {
                            this.this$0 = stashFinder;
                            this.lastTime = -1L;
                        }

                        public class_368.class_369 method_1986(class_4587 class_45872, class_374 class_3742, long l) {
                            if (this.lastTime == -1L) {
                                this.lastTime = l;
                            } else {
                                this.timer += l - this.lastTime;
                            }
                            class_3742.method_1995().method_1531().method_22813(new class_2960("textures/gui/toasts.png"));
                            RenderSystem.color4f((float)1.0f, (float)1.0f, (float)1.0f, (float)255.0f);
                            class_3742.method_25302(class_45872, 0, 0, 0, 32, 160, 32);
                            class_3742.method_1995().field_1772.method_1729(class_45872, "StashRecorder found stash.", 12.0f, 12.0f, -11534256);
                            return this.timer >= 32000L ? class_368.class_369.field_2209 : class_368.class_369.field_2210;
                        }
                    });
                } else {
                    this.info("(highlight)Found stash.", new Object[0]);
                }
            }
        }
    }

    private void lambda$getWidget$1(WTable wTable) {
        this.chunks.clear();
        wTable.clear();
    }

    private void load() {
        block9: {
            Object object;
            Object object2;
            Reader reader;
            File file;
            boolean bl;
            block8: {
                bl = false;
                file = this.getJsonFile();
                if (file.exists()) {
                    try {
                        reader = new FileReader(file);
                        this.chunks = (List)GSON.fromJson(reader, new TypeToken<List<Chunk>>(this){
                            final StashFinder this$0;
                            {
                                this.this$0 = stashFinder;
                            }
                        }.getType());
                        ((InputStreamReader)reader).close();
                        object2 = this.chunks.iterator();
                        while (object2.hasNext()) {
                            object = (Chunk)object2.next();
                            ((Chunk)object).calculatePos();
                        }
                        bl = true;
                    }
                    catch (Exception exception) {
                        if (this.chunks != null) break block8;
                        this.chunks = new ArrayList<Chunk>();
                    }
                }
            }
            file = this.getCsvFile();
            if (!bl && file.exists()) {
                try {
                    reader = new BufferedReader(new FileReader(file));
                    ((BufferedReader)reader).readLine();
                    while ((object2 = ((BufferedReader)reader).readLine()) != null) {
                        object = ((String)object2).split(" ");
                        Chunk chunk = new Chunk(new class_1923(Integer.parseInt((String)object[0]), Integer.parseInt((String)object[1])));
                        chunk.chests = Integer.parseInt((String)object[2]);
                        chunk.shulkers = Integer.parseInt((String)object[3]);
                        chunk.enderChests = Integer.parseInt((String)object[4]);
                        chunk.furnaces = Integer.parseInt((String)object[5]);
                        chunk.dispensersDroppers = Integer.parseInt((String)object[6]);
                        chunk.hoppers = Integer.parseInt((String)object[7]);
                        this.chunks.add(chunk);
                    }
                    ((BufferedReader)reader).close();
                }
                catch (Exception exception) {
                    if (this.chunks != null) break block9;
                    this.chunks = new ArrayList<Chunk>();
                }
            }
        }
    }

    private void fillTable(GuiTheme guiTheme, WTable wTable) {
        for (Chunk chunk : this.chunks) {
            wTable.add(guiTheme.label(String.valueOf(new StringBuilder().append("Pos: ").append(chunk.x).append(", ").append(chunk.z))));
            wTable.add(guiTheme.label(String.valueOf(new StringBuilder().append("Total: ").append(chunk.getTotal()))));
            WButton wButton = wTable.add(guiTheme.button("Open")).widget();
            wButton.action = () -> this.lambda$fillTable$2(guiTheme, chunk);
            WButton wButton2 = wTable.add(guiTheme.button("Goto")).widget();
            wButton2.action = () -> StashFinder.lambda$fillTable$3(chunk);
            WMinus wMinus = wTable.add(guiTheme.minus()).widget();
            wMinus.action = () -> this.lambda$fillTable$4(chunk, wTable, guiTheme);
            wTable.row();
        }
    }

    private void saveCsv() {
        try {
            File file = this.getCsvFile();
            file.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("X,Z,Chests,Shulkers,EnderChests,Furnaces,DispensersDroppers,Hopper\n");
            for (Chunk chunk : this.chunks) {
                chunk.write(fileWriter);
            }
            ((Writer)fileWriter).close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private static void lambda$fillTable$3(Chunk chunk) {
        BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)new GoalXZ(chunk.x, chunk.z));
    }

    @Override
    public WWidget getWidget(GuiTheme guiTheme) {
        this.chunks.sort(Comparator.comparingInt(StashFinder::lambda$getWidget$0));
        WVerticalList wVerticalList = guiTheme.verticalList();
        WButton wButton = wVerticalList.add(guiTheme.button("Clear")).widget();
        WTable wTable = new WTable();
        if (this.chunks.size() > 0) {
            wVerticalList.add(wTable);
        }
        wButton.action = () -> this.lambda$getWidget$1(wTable);
        this.fillTable(guiTheme, wTable);
        return wVerticalList;
    }

    private void lambda$fillTable$2(GuiTheme guiTheme, Chunk chunk) {
        this.mc.method_1507((class_437)new ChunkScreen(guiTheme, chunk));
    }

    private void lambda$fillTable$4(Chunk chunk, WTable wTable, GuiTheme guiTheme) {
        if (this.chunks.remove(chunk)) {
            wTable.clear();
            this.fillTable(guiTheme, wTable);
            this.saveJson();
            this.saveCsv();
        }
    }

    private void saveJson() {
        try {
            File file = this.getJsonFile();
            file.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(file);
            GSON.toJson(this.chunks, (Appendable)fileWriter);
            ((Writer)fileWriter).close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    @Override
    public void onActivate() {
        this.load();
    }

    public static class Chunk {
        public int shulkers;
        public int barrels;
        public int furnaces;
        public class_1923 chunkPos;
        public int enderChests;
        public int dispensersDroppers;
        public int hoppers;
        public transient int x;
        private static final StringBuilder sb = new StringBuilder();
        public transient int z;
        public int chests;

        public Chunk(class_1923 class_19232) {
            this.chunkPos = class_19232;
            this.calculatePos();
        }

        public int getTotal() {
            return this.chests + this.barrels + this.shulkers + this.enderChests + this.furnaces + this.dispensersDroppers + this.hoppers;
        }

        public boolean countsEqual(Chunk chunk) {
            if (chunk == null) {
                return false;
            }
            return this.chests != chunk.chests || this.barrels != chunk.barrels || this.shulkers != chunk.shulkers || this.enderChests != chunk.enderChests || this.furnaces != chunk.furnaces || this.dispensersDroppers != chunk.dispensersDroppers || this.hoppers != chunk.hoppers;
        }

        public void write(Writer writer) throws IOException {
            sb.setLength(0);
            sb.append(this.x).append(',').append(this.z).append(',');
            sb.append(this.chests).append(',').append(this.barrels).append(',').append(this.shulkers).append(',').append(this.enderChests).append(',').append(this.furnaces).append(',').append(this.dispensersDroppers).append(',').append(this.hoppers).append('\n');
            writer.write(String.valueOf(sb));
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || this.getClass() != object.getClass()) {
                return false;
            }
            Chunk chunk = (Chunk)object;
            return Objects.equals(this.chunkPos, chunk.chunkPos);
        }

        public int hashCode() {
            return Objects.hash(this.chunkPos);
        }

        public void calculatePos() {
            this.x = this.chunkPos.field_9181 * 16 + 8;
            this.z = this.chunkPos.field_9180 * 16 + 8;
        }
    }

    private static class ChunkScreen
    extends WindowScreen {
        public ChunkScreen(GuiTheme guiTheme, Chunk chunk) {
            super(guiTheme, String.valueOf(new StringBuilder().append("Chunk at ").append(chunk.x).append(", ").append(chunk.z)));
            WTable wTable = this.add(guiTheme.table()).expandX().widget();
            wTable.add(guiTheme.label("Total:"));
            wTable.add(guiTheme.label(String.valueOf(new StringBuilder().append(chunk.getTotal()).append(""))));
            wTable.row();
            wTable.add(guiTheme.horizontalSeparator()).expandX();
            wTable.row();
            wTable.add(guiTheme.label("Chests:"));
            wTable.add(guiTheme.label(String.valueOf(new StringBuilder().append(chunk.chests).append(""))));
            wTable.row();
            wTable.add(guiTheme.label("Barrels:"));
            wTable.add(guiTheme.label(String.valueOf(new StringBuilder().append(chunk.barrels).append(""))));
            wTable.row();
            wTable.add(guiTheme.label("Shulkers:"));
            wTable.add(guiTheme.label(String.valueOf(new StringBuilder().append(chunk.shulkers).append(""))));
            wTable.row();
            wTable.add(guiTheme.label("Ender Chests:"));
            wTable.add(guiTheme.label(String.valueOf(new StringBuilder().append(chunk.enderChests).append(""))));
            wTable.row();
            wTable.add(guiTheme.label("Furnaces:"));
            wTable.add(guiTheme.label(String.valueOf(new StringBuilder().append(chunk.furnaces).append(""))));
            wTable.row();
            wTable.add(guiTheme.label("Dispensers and droppers:"));
            wTable.add(guiTheme.label(String.valueOf(new StringBuilder().append(chunk.dispensersDroppers).append(""))));
            wTable.row();
            wTable.add(guiTheme.label("Hoppers:"));
            wTable.add(guiTheme.label(String.valueOf(new StringBuilder().append(chunk.hoppers).append(""))));
        }
    }

    public static enum Mode {
        Chat,
        Toast;

    }
}

