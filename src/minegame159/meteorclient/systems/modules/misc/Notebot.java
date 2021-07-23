/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.misc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.screens.NotebotHelpScreen;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.notebot.NBSDecoder;
import minegame159.meteorclient.utils.notebot.NotebotUtils;
import minegame159.meteorclient.utils.notebot.nbs.Layer;
import minegame159.meteorclient.utils.notebot.nbs.Note;
import minegame159.meteorclient.utils.notebot.nbs.Song;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_2428;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_2885;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class Notebot
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<NotebotUtils.InstrumentType> instrument;
    private int currentIndex;
    private int ticks;
    private final Setting<ShapeMode> shapeMode;
    private final List<class_2338> possibleBlockPos;
    private int currentNote;
    private final Setting<Boolean> polyphonic;
    private final SettingGroup sgRender;
    private final List<ImmutablePair<Integer, Integer>> song;
    private final Setting<Boolean> render;
    private final Setting<SettingColor> sideColor;
    private boolean noSongsFound;
    private final HashMap<Integer, class_2338> blockPositions;
    private boolean isPlaying;
    private int offset;
    private WLabel status;
    private final Setting<SettingColor> lineColor;
    private final Setting<Integer> tickDelay;
    private Stage stage;
    private final List<Integer> uniqueNotes;
    private final List<class_2338> scannedNoteblocks;

    private void resetVariables() {
        this.currentNote = 0;
        this.currentIndex = 0;
        this.offset = 0;
        this.isPlaying = false;
        this.stage = Stage.None;
        this.song.clear();
        this.blockPositions.clear();
        this.uniqueNotes.clear();
    }

    public Notebot() {
        super(Categories.Misc, "notebot", "Plays noteblock nicely");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render", false);
        this.tickDelay = this.sgGeneral.add(new IntSetting.Builder().name("tick-delay").description("The delay when loading a song.").defaultValue(2).min(0).sliderMax(20).build());
        this.instrument = this.sgGeneral.add(new EnumSetting.Builder().name("instrument").description("Select which instrument will be played").defaultValue(NotebotUtils.InstrumentType.NotDrums).build());
        this.polyphonic = this.sgGeneral.add(new BoolSetting.Builder().name("polyphonic").description("Whether or not to allow multiple notes to be played at the same time").defaultValue(true).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Whether or not to render the outline around the noteblocks.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
        this.possibleBlockPos = new ArrayList<class_2338>();
        this.stage = Stage.None;
        this.isPlaying = false;
        this.song = new ArrayList<ImmutablePair<Integer, Integer>>();
        this.uniqueNotes = new ArrayList<Integer>();
        this.blockPositions = new HashMap();
        this.scannedNoteblocks = new ArrayList<class_2338>();
        this.currentNote = 0;
        this.currentIndex = 0;
        this.offset = 0;
        this.ticks = 0;
        this.noSongsFound = true;
        for (int i = -5; i < 5; ++i) {
            for (int j = -5; j < 5; ++j) {
                class_2338 class_23382;
                if (i == 0 && j == 0 || !((class_23382 = new class_2338(j, 0, i)).method_10268(0.0, 0.0, 0.0, true) < 17.99)) continue;
                this.possibleBlockPos.add(class_23382);
                if (-5 < 0) continue;
                throw null;
            }
            if (!false) continue;
            throw null;
        }
        this.possibleBlockPos.sort(Comparator.comparingDouble(Notebot::lambda$new$0));
    }

    private void lambda$onRender$1(class_2338 class_23382) {
        double d = class_23382.method_10263();
        double d2 = class_23382.method_10264();
        double d3 = class_23382.method_10260();
        double d4 = class_23382.method_10263() + 1;
        double d5 = class_23382.method_10264() + 1;
        double d6 = class_23382.method_10260() + 1;
        Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, d, d2, d3, d4, d5, d6, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
    }

    public void Pause() {
        if (!this.isActive()) {
            this.toggle();
        }
        if (this.isPlaying) {
            this.info("Pausing.", new Object[0]);
            this.isPlaying = false;
        } else {
            this.info("Resuming.", new Object[0]);
            this.isPlaying = true;
        }
    }

    private boolean loadNbsFile(File file) {
        Song song = NBSDecoder.parse(file);
        if (song == null) {
            this.error("Couldn't parse the file. Only classic and opennbs v5 are supported", new Object[0]);
            return false;
        }
        ArrayList<Layer> arrayList = new ArrayList<Layer>(song.getLayerHashMap().values());
        this.resetVariables();
        for (Layer layer : arrayList) {
            for (int n : layer.getHashMap().keySet()) {
                byte by;
                Note note = layer.getNote(n);
                n = (int)((float)n * song.getDelay());
                if (note == null || !NotebotUtils.isValidInstrumentNbsFile(by = note.getInstrument(), this.instrument.get())) continue;
                int n2 = Byte.toUnsignedInt(note.getKey());
                if ((n2 -= 33) < 0 || n2 > 24) {
                    this.warning("Note at tick %d out of range.", n);
                    continue;
                }
                this.addNote(n, n2);
            }
        }
        return true;
    }

    public void previewSong(File file) {
        if (!this.isActive()) {
            this.toggle();
        }
        if (this.loadFileToMap(file)) {
            this.info("Song \"%s\" loaded.", this.getFileLabel(file.toPath()));
            this.stage = Stage.Preview;
            this.Play();
        }
    }

    private boolean isValidScanSpot(class_2338 class_23382) {
        if (this.mc.field_1687.method_8320(class_23382).method_26204() != class_2246.field_10179) {
            return false;
        }
        return this.mc.field_1687.method_8320(class_23382.method_10084()).method_26215();
    }

    @Override
    public void onActivate() {
        this.ticks = 0;
        this.resetVariables();
    }

    private static double lambda$new$0(class_2338 class_23382) {
        return class_23382.method_10262(new class_2382(0, 0, 0));
    }

    private void playRotate() {
        if (this.mc.field_1761 == null) {
            return;
        }
        try {
            int n = (Integer)this.song.get((int)this.currentIndex).right;
            class_2338 class_23382 = this.blockPositions.get(n);
            this.mc.field_1761.method_2910(class_23382, class_2350.field_11033);
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        ++this.ticks;
        if (this.stage == Stage.SetUp) {
            this.onTickSetup();
        } else if (this.stage == Stage.Tune) {
            this.onTickTune();
        } else if (this.stage == Stage.Preview || this.stage == Stage.Playing) {
            if (!this.isPlaying) {
                return;
            }
            if (this.mc.field_1724 == null || this.currentIndex >= this.song.size()) {
                this.stop();
                return;
            }
            while ((Integer)this.song.get((int)this.currentIndex).left < this.currentNote) {
                ++this.currentIndex;
            }
            while ((Integer)this.song.get((int)this.currentIndex).left == this.currentNote) {
                if (this.stage == Stage.Preview) {
                    this.onTickPreview();
                } else {
                    this.onTickPlay();
                }
                ++this.currentIndex;
                if (this.currentIndex < this.song.size()) continue;
                return;
            }
            ++this.currentNote;
            if (this.status != null) {
                this.status.set(this.getStatus());
            }
        }
    }

    private boolean tuneBlock(class_2338 class_23382, int n) {
        if (this.mc.field_1687 == null || this.mc.field_1724 == null) {
            return false;
        }
        class_2680 class_26802 = this.mc.field_1687.method_8320(class_23382);
        if (class_26802.method_26204() != class_2246.field_10179) {
            ++this.offset;
            this.stage = Stage.SetUp;
            return true;
        }
        if (((Integer)class_26802.method_11654((class_2769)class_2428.field_11324)).equals(n)) {
            ++this.currentNote;
            this.stage = Stage.SetUp;
            return true;
        }
        this.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_1268.field_5808, new class_3965(this.mc.field_1724.method_19538(), this.rayTraceCheck(class_23382), class_23382, true)));
        this.mc.field_1724.method_6104(class_1268.field_5808);
        return true;
    }

    public void disable() {
        this.resetVariables();
        this.info("Stopping.", new Object[0]);
        if (!this.isActive()) {
            this.toggle();
        }
    }

    private boolean setupBlocks() {
        this.song.forEach(this::lambda$setupBlocks$8);
        this.scanForNoteblocks();
        if (this.uniqueNotes.size() > this.possibleBlockPos.size() + this.scannedNoteblocks.size()) {
            this.error("Too many notes. %d is the maximum.", this.possibleBlockPos.size());
            return false;
        }
        this.currentNote = 0;
        this.offset = 0;
        this.stage = Stage.SetUp;
        return true;
    }

    public void Play() {
        if (this.mc.field_1724 == null) {
            return;
        }
        if (this.mc.field_1724.field_7503.field_7477 && this.stage != Stage.Preview) {
            this.error("You need to be in survival mode.", new Object[0]);
        } else if (this.stage == Stage.Preview || this.stage == Stage.Playing) {
            this.isPlaying = true;
            this.info("Playing.", new Object[0]);
        } else {
            this.error("No song loaded.", new Object[0]);
        }
    }

    private void tuneRotate() {
        class_2338 class_23382 = this.blockPositions.get(this.uniqueNotes.get(this.currentNote));
        if (class_23382 == null) {
            return;
        }
        if (!this.tuneBlock(class_23382, this.uniqueNotes.get(this.currentNote))) {
            this.disable();
        }
    }

    private boolean loadTextFile(File file) {
        List<String> list;
        try {
            list = Files.readAllLines(file.toPath());
        }
        catch (IOException iOException) {
            this.error("Error while reading \"%s\"", file.getName());
            return false;
        }
        this.resetVariables();
        for (int i = 0; i < list.size(); ++i) {
            int n;
            int n2;
            block7: {
                String[] stringArray = list.get(i).split(":");
                if (stringArray.length < 2) {
                    this.warning("Malformed line %d", i);
                    continue;
                }
                try {
                    int n3;
                    n2 = Integer.parseInt(stringArray[0]);
                    n = Integer.parseInt(stringArray[1]);
                    if (stringArray.length > 2 && !NotebotUtils.isValidIntrumentTextFile(n3 = Integer.parseInt(stringArray[2]), this.instrument.get())) {
                    }
                    break block7;
                }
                catch (NumberFormatException numberFormatException) {
                    this.warning("Invalid character at line %d", i);
                }
                continue;
            }
            this.addNote(n2, n);
            if (2 > 0) continue;
            return false;
        }
        return true;
    }

    private boolean isValidEmptySpot(class_2338 class_23382) {
        if (!this.mc.field_1687.method_8320(class_23382).method_26215()) {
            return false;
        }
        if (!this.mc.field_1687.method_8320(class_23382.method_10084()).method_26215()) {
            return false;
        }
        return this.mc.field_1687.method_8320(class_23382.method_10074()).method_26204() != class_2246.field_10179;
    }

    private void lambda$getWidget$2(WButton wButton) {
        this.Pause();
        wButton.set(this.isPlaying ? "Pause" : "Resume");
        this.status.set(this.getStatus());
    }

    private class_2350 rayTraceCheck(class_2338 class_23382) {
        class_243 class_2432 = new class_243(this.mc.field_1724.method_23317(), this.mc.field_1724.method_23318() + (double)this.mc.field_1724.method_18381(this.mc.field_1724.method_18376()), this.mc.field_1724.method_23321());
        for (class_2350 class_23502 : class_2350.values()) {
            class_3959 class_39592 = new class_3959(class_2432, new class_243((double)class_23382.method_10263() + 0.5 + (double)class_23502.method_10163().method_10263() * 0.5, (double)class_23382.method_10264() + 0.5 + (double)class_23502.method_10163().method_10264() * 0.5, (double)class_23382.method_10260() + 0.5 + (double)class_23502.method_10163().method_10260() * 0.5), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)this.mc.field_1724);
            class_3965 class_39652 = this.mc.field_1687.method_17742(class_39592);
            if (class_39652 == null || class_39652.method_17783() != class_239.class_240.field_1332 || !class_39652.method_17777().equals((Object)class_23382)) continue;
            return class_23502;
        }
        if ((double)class_23382.method_10264() > class_2432.field_1351) {
            return class_2350.field_11033;
        }
        return class_2350.field_11036;
    }

    private static int lambda$loadFileToMap$7(ImmutablePair immutablePair) {
        return (Integer)immutablePair.left;
    }

    private void onTickSetup() {
        class_2338 class_23382;
        if (this.ticks < this.tickDelay.get()) {
            return;
        }
        this.ticks = 0;
        if (this.currentNote >= this.uniqueNotes.size()) {
            this.stage = Stage.Playing;
            this.info("Loading done.", new Object[0]);
            this.Play();
            return;
        }
        int n = this.currentNote + this.offset;
        if (n < this.scannedNoteblocks.size()) {
            class_2338 class_23383 = this.scannedNoteblocks.get(n);
            if (this.mc.field_1687.method_8320(class_23383).method_26204() != class_2246.field_10179) {
                ++this.offset;
            } else {
                this.blockPositions.put(this.uniqueNotes.get(this.currentNote), class_23383);
                this.stage = Stage.Tune;
            }
            return;
        }
        FindItemResult findItemResult = InvUtils.findInHotbar(class_1802.field_8643);
        if (!findItemResult.found()) {
            this.error("Not enough noteblocks", new Object[0]);
            this.disable();
            return;
        }
        n -= this.scannedNoteblocks.size();
        try {
            class_23382 = this.mc.field_1724.method_24515().method_10081((class_2382)this.possibleBlockPos.get(n));
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            this.error("Not enough valid positions.", new Object[0]);
            this.disable();
            return;
        }
        if (!this.isValidEmptySpot(class_23382) || !NotebotUtils.isValidInstrument(class_23382, this.instrument.get())) {
            ++this.offset;
            return;
        }
        if (!BlockUtils.place(class_23382, findItemResult, true, 100, true)) {
            ++this.offset;
        } else {
            this.blockPositions.put(this.uniqueNotes.get(this.currentNote), class_23382);
            this.stage = Stage.Tune;
        }
    }

    private void onTickPlay() {
        if ((Integer)this.song.get((int)this.currentIndex).left == this.currentNote) {
            int n = (Integer)this.song.get((int)this.currentIndex).right;
            class_2338 class_23382 = this.blockPositions.get(n);
            if (this.polyphonic.get().booleanValue()) {
                Rotations.setCamRotation(Rotations.getYaw(class_23382), Rotations.getPitch(class_23382));
                this.playRotate();
            } else {
                Rotations.rotate(Rotations.getYaw(class_23382), Rotations.getPitch(class_23382), 100, this::playRotate);
            }
        }
    }

    private void lambda$getWidget$6(GuiTheme guiTheme) {
        this.mc.method_1507((class_437)new NotebotHelpScreen(guiTheme));
    }

    private void lambda$getWidget$5(WTable wTable, GuiTheme guiTheme, Path path) {
        if (this.isValidFile(path)) {
            this.noSongsFound = false;
            wTable.add(guiTheme.label(this.getFileLabel(path))).expandCellX();
            WButton wButton = wTable.add(guiTheme.button("Load")).right().widget();
            wButton.action = () -> this.lambda$getWidget$3(path);
            WButton wButton2 = wTable.add(guiTheme.button("Preview")).right().widget();
            wButton2.action = () -> this.lambda$getWidget$4(path);
            wTable.row();
        }
    }

    private void onTickTune() {
        if (this.ticks < this.tickDelay.get()) {
            return;
        }
        this.ticks = 0;
        class_2338 class_23382 = this.blockPositions.get(this.uniqueNotes.get(this.currentNote));
        if (class_23382 == null) {
            return;
        }
        Rotations.rotate(Rotations.getYaw(class_23382), Rotations.getPitch(class_23382), 100, this::tuneRotate);
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (!this.render.get().booleanValue()) {
            return;
        }
        if (this.stage != Stage.SetUp && this.stage != Stage.Tune && !this.isPlaying) {
            return;
        }
        this.blockPositions.values().forEach(this::lambda$onRender$1);
    }

    public void printStatus() {
        this.info(this.getStatus(), new Object[0]);
    }

    @Override
    public WWidget getWidget(GuiTheme guiTheme) {
        WTable wTable = guiTheme.table();
        this.status = wTable.add(guiTheme.label(this.getStatus())).expandCellX().widget();
        WButton wButton = wTable.add(guiTheme.button(this.isPlaying ? "Pause" : "Resume")).right().widget();
        wButton.action = () -> this.lambda$getWidget$2(wButton);
        WButton wButton2 = wTable.add(guiTheme.button("Stop")).right().widget();
        wButton2.action = this::stop;
        wTable.row();
        this.noSongsFound = true;
        try {
            Files.list(MeteorClient.FOLDER.toPath().resolve("notebot")).forEach(arg_0 -> this.lambda$getWidget$5(wTable, guiTheme, arg_0));
        }
        catch (IOException iOException) {
            wTable.add(guiTheme.label("Missing \"notebot\" folder.")).expandCellX();
            wTable.row();
        }
        if (this.noSongsFound) {
            wTable.add(guiTheme.label("No songs found.")).expandCellX();
            wTable.row();
            WButton wButton3 = wTable.add(guiTheme.button("Help")).expandCellX().widget();
            wButton3.action = () -> this.lambda$getWidget$6(guiTheme);
        }
        return wTable;
    }

    private void onTickPreview() {
        if ((Integer)this.song.get((int)this.currentIndex).left == this.currentNote) {
            this.mc.field_1724.method_5783(NotebotUtils.getInstrumentSound(this.instrument.get()), 2.0f, (float)Math.pow(2.0, (double)((Integer)this.song.get((int)this.currentIndex).right - 12) / 12.0));
        }
    }

    public void loadSong(File file) {
        if (!this.isActive()) {
            this.toggle();
        }
        if (!this.loadFileToMap(file)) {
            return;
        }
        if (!this.setupBlocks()) {
            return;
        }
        this.info("Loading song \"%s\".", this.getFileLabel(file.toPath()));
    }

    private void addNote(int n, int n2) {
        if (this.polyphonic.get().booleanValue()) {
            this.song.add((ImmutablePair<Integer, Integer>)new ImmutablePair((Object)n, (Object)n2));
        } else if (this.song.size() == 0) {
            this.song.add((ImmutablePair<Integer, Integer>)new ImmutablePair((Object)n, (Object)n2));
        } else if ((Integer)this.song.get((int)(this.song.size() - 1)).left != n) {
            this.song.add((ImmutablePair<Integer, Integer>)new ImmutablePair((Object)n, (Object)n2));
        }
    }

    private String getFileLabel(Path path) {
        return path.getFileName().toString().replace(".txt", "").replace(".nbs", "");
    }

    private void lambda$setupBlocks$8(ImmutablePair immutablePair) {
        if (!this.uniqueNotes.contains(immutablePair.right)) {
            this.uniqueNotes.add((Integer)immutablePair.right);
        }
    }

    private boolean isValidFile(Path path) {
        String string = FilenameUtils.getExtension((String)path.toFile().getName());
        if (string.equals("txt")) {
            return true;
        }
        return string.equals("nbs");
    }

    @Override
    public String getInfoString() {
        return this.stage.toString();
    }

    private void scanForNoteblocks() {
        if (this.mc.field_1761 == null || this.mc.field_1687 == null || this.mc.field_1724 == null) {
            return;
        }
        this.scannedNoteblocks.clear();
        int n = (int)(-this.mc.field_1761.method_2904()) - 1;
        int n2 = (int)this.mc.field_1761.method_2904() + 1;
        for (int i = n; i < n2; ++i) {
            for (int j = n; j < n2; ++j) {
                for (int k = n; k < n2; ++k) {
                    class_2338 class_23382 = this.mc.field_1724.method_24515().method_10069(i, j, k);
                    if (this.mc.field_1687.method_8320(class_23382).method_26204() != class_2246.field_10179) continue;
                    float f = this.mc.field_1761.method_2904();
                    f *= f;
                    if (class_23382.method_19770((class_2374)this.mc.field_1724.method_19538(), false) > (double)f || !this.isValidScanSpot(class_23382) || !NotebotUtils.isValidInstrument(class_23382, this.instrument.get())) continue;
                    this.scannedNoteblocks.add(class_23382);
                    if (-2 < 0) continue;
                    return;
                }
                if (!false) continue;
                return;
            }
            if (2 >= -1) continue;
            return;
        }
    }

    private boolean loadFileToMap(File file) {
        if (!file.exists() || !file.isFile()) {
            this.error("File not found", new Object[0]);
            return false;
        }
        String string = FilenameUtils.getExtension((String)file.getName());
        boolean bl = false;
        if (string.equals("txt")) {
            bl = this.loadTextFile(file);
        } else if (string.equals("nbs")) {
            bl = this.loadNbsFile(file);
        }
        if (bl) {
            this.song.sort(Comparator.comparingInt(Notebot::lambda$loadFileToMap$7));
        }
        return bl;
    }

    private void lambda$getWidget$4(Path path) {
        this.previewSong(path.toFile());
        this.status.set(this.getStatus());
    }

    public void stop() {
        this.info("Stopping.", new Object[0]);
        if (this.stage == Stage.SetUp || this.stage == Stage.Tune) {
            this.resetVariables();
        } else {
            this.isPlaying = false;
            this.currentNote = 0;
            this.currentIndex = 0;
        }
        if (this.status != null) {
            this.status.set(this.getStatus());
        }
    }

    private String getStatus() {
        if (!this.isActive()) {
            return "Module disabled.";
        }
        if (this.song.isEmpty()) {
            return "No song loaded.";
        }
        if (this.isPlaying) {
            return String.format("Playing song. %d/%d", this.currentIndex, this.song.size());
        }
        if (this.stage == Stage.Playing || this.stage == Stage.Preview) {
            return "Ready to play.";
        }
        if (this.stage == Stage.SetUp || this.stage == Stage.Tune) {
            return "Setting up the noteblocks.";
        }
        return String.format("Stage: %s.", this.stage.toString());
    }

    private void lambda$getWidget$3(Path path) {
        this.loadSong(path.toFile());
        this.status.set(this.getStatus());
    }

    private static enum Stage {
        None,
        SetUp,
        Tune,
        Playing,
        Preview;

    }
}

