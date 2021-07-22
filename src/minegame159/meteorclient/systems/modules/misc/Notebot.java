/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_2382
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_2428
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_2885
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_437
 *  org.apache.commons.io.FilenameUtils
 *  org.apache.commons.lang3.tuple.ImmutablePair
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
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<NotebotUtils.InstrumentType> instrument;
    private /* synthetic */ int currentIndex;
    private /* synthetic */ int ticks;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ List<class_2338> possibleBlockPos;
    private /* synthetic */ int currentNote;
    private final /* synthetic */ Setting<Boolean> polyphonic;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ List<ImmutablePair<Integer, Integer>> song;
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private /* synthetic */ boolean noSongsFound;
    private final /* synthetic */ HashMap<Integer, class_2338> blockPositions;
    private /* synthetic */ boolean isPlaying;
    private /* synthetic */ int offset;
    private /* synthetic */ WLabel status;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ Setting<Integer> tickDelay;
    private /* synthetic */ Stage stage;
    private final /* synthetic */ List<Integer> uniqueNotes;
    private final /* synthetic */ List<class_2338> scannedNoteblocks;

    private void resetVariables() {
        Notebot lllllllllllllllllIlllllIlIlllIII;
        lllllllllllllllllIlllllIlIlllIII.currentNote = 0;
        lllllllllllllllllIlllllIlIlllIII.currentIndex = 0;
        lllllllllllllllllIlllllIlIlllIII.offset = 0;
        lllllllllllllllllIlllllIlIlllIII.isPlaying = false;
        lllllllllllllllllIlllllIlIlllIII.stage = Stage.None;
        lllllllllllllllllIlllllIlIlllIII.song.clear();
        lllllllllllllllllIlllllIlIlllIII.blockPositions.clear();
        lllllllllllllllllIlllllIlIlllIII.uniqueNotes.clear();
    }

    public Notebot() {
        super(Categories.Misc, "notebot", "Plays noteblock nicely");
        Notebot lllllllllllllllllIlllllIllIIIIll;
        lllllllllllllllllIlllllIllIIIIll.sgGeneral = lllllllllllllllllIlllllIllIIIIll.settings.getDefaultGroup();
        lllllllllllllllllIlllllIllIIIIll.sgRender = lllllllllllllllllIlllllIllIIIIll.settings.createGroup("Render", false);
        lllllllllllllllllIlllllIllIIIIll.tickDelay = lllllllllllllllllIlllllIllIIIIll.sgGeneral.add(new IntSetting.Builder().name("tick-delay").description("The delay when loading a song.").defaultValue(2).min(0).sliderMax(20).build());
        lllllllllllllllllIlllllIllIIIIll.instrument = lllllllllllllllllIlllllIllIIIIll.sgGeneral.add(new EnumSetting.Builder().name("instrument").description("Select which instrument will be played").defaultValue(NotebotUtils.InstrumentType.NotDrums).build());
        lllllllllllllllllIlllllIllIIIIll.polyphonic = lllllllllllllllllIlllllIllIIIIll.sgGeneral.add(new BoolSetting.Builder().name("polyphonic").description("Whether or not to allow multiple notes to be played at the same time").defaultValue(true).build());
        lllllllllllllllllIlllllIllIIIIll.render = lllllllllllllllllIlllllIllIIIIll.sgRender.add(new BoolSetting.Builder().name("render").description("Whether or not to render the outline around the noteblocks.").defaultValue(true).build());
        lllllllllllllllllIlllllIllIIIIll.shapeMode = lllllllllllllllllIlllllIllIIIIll.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lllllllllllllllllIlllllIllIIIIll.sideColor = lllllllllllllllllIlllllIllIIIIll.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        lllllllllllllllllIlllllIllIIIIll.lineColor = lllllllllllllllllIlllllIllIIIIll.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
        lllllllllllllllllIlllllIllIIIIll.possibleBlockPos = new ArrayList<class_2338>();
        lllllllllllllllllIlllllIllIIIIll.stage = Stage.None;
        lllllllllllllllllIlllllIllIIIIll.isPlaying = false;
        lllllllllllllllllIlllllIllIIIIll.song = new ArrayList<ImmutablePair<Integer, Integer>>();
        lllllllllllllllllIlllllIllIIIIll.uniqueNotes = new ArrayList<Integer>();
        lllllllllllllllllIlllllIllIIIIll.blockPositions = new HashMap();
        lllllllllllllllllIlllllIllIIIIll.scannedNoteblocks = new ArrayList<class_2338>();
        lllllllllllllllllIlllllIllIIIIll.currentNote = 0;
        lllllllllllllllllIlllllIllIIIIll.currentIndex = 0;
        lllllllllllllllllIlllllIllIIIIll.offset = 0;
        lllllllllllllllllIlllllIllIIIIll.ticks = 0;
        lllllllllllllllllIlllllIllIIIIll.noSongsFound = true;
        for (int lllllllllllllllllIlllllIllIIIlIl = -5; lllllllllllllllllIlllllIllIIIlIl < 5; ++lllllllllllllllllIlllllIllIIIlIl) {
            for (int lllllllllllllllllIlllllIllIIIllI = -5; lllllllllllllllllIlllllIllIIIllI < 5; ++lllllllllllllllllIlllllIllIIIllI) {
                class_2338 lllllllllllllllllIlllllIllIIIlll;
                if (lllllllllllllllllIlllllIllIIIlIl == 0 && lllllllllllllllllIlllllIllIIIllI == 0 || !((lllllllllllllllllIlllllIllIIIlll = new class_2338(lllllllllllllllllIlllllIllIIIllI, 0, lllllllllllllllllIlllllIllIIIlIl)).method_10268(0.0, 0.0, 0.0, true) < 17.99)) continue;
                lllllllllllllllllIlllllIllIIIIll.possibleBlockPos.add(lllllllllllllllllIlllllIllIIIlll);
            }
        }
        lllllllllllllllllIlllllIllIIIIll.possibleBlockPos.sort(Comparator.comparingDouble(lllllllllllllllllIllllIlIlIlIlll -> lllllllllllllllllIllllIlIlIlIlll.method_10262(new class_2382(0, 0, 0))));
    }

    public void Pause() {
        Notebot lllllllllllllllllIlllllIlIIIIllI;
        if (!lllllllllllllllllIlllllIlIIIIllI.isActive()) {
            lllllllllllllllllIlllllIlIIIIllI.toggle();
        }
        if (lllllllllllllllllIlllllIlIIIIllI.isPlaying) {
            lllllllllllllllllIlllllIlIIIIllI.info("Pausing.", new Object[0]);
            lllllllllllllllllIlllllIlIIIIllI.isPlaying = false;
        } else {
            lllllllllllllllllIlllllIlIIIIllI.info("Resuming.", new Object[0]);
            lllllllllllllllllIlllllIlIIIIllI.isPlaying = true;
        }
    }

    private boolean loadNbsFile(File lllllllllllllllllIlllllIIIlIlIll) {
        Notebot lllllllllllllllllIlllllIIIllIIII;
        Song lllllllllllllllllIlllllIIIlIlllI = NBSDecoder.parse(lllllllllllllllllIlllllIIIlIlIll);
        if (lllllllllllllllllIlllllIIIlIlllI == null) {
            lllllllllllllllllIlllllIIIllIIII.error("Couldn't parse the file. Only classic and opennbs v5 are supported", new Object[0]);
            return false;
        }
        ArrayList<Layer> lllllllllllllllllIlllllIIIlIllIl = new ArrayList<Layer>(lllllllllllllllllIlllllIIIlIlllI.getLayerHashMap().values());
        lllllllllllllllllIlllllIIIllIIII.resetVariables();
        for (Layer lllllllllllllllllIlllllIIIllIIIl : lllllllllllllllllIlllllIIIlIllIl) {
            for (int lllllllllllllllllIlllllIIIllIIlI : lllllllllllllllllIlllllIIIllIIIl.getHashMap().keySet()) {
                byte lllllllllllllllllIlllllIIIllIlII;
                Note lllllllllllllllllIlllllIIIllIlIl = lllllllllllllllllIlllllIIIllIIIl.getNote(lllllllllllllllllIlllllIIIllIIlI);
                lllllllllllllllllIlllllIIIllIIlI = (int)((float)lllllllllllllllllIlllllIIIllIIlI * lllllllllllllllllIlllllIIIlIlllI.getDelay());
                if (lllllllllllllllllIlllllIIIllIlIl == null || !NotebotUtils.isValidInstrumentNbsFile(lllllllllllllllllIlllllIIIllIlII = lllllllllllllllllIlllllIIIllIlIl.getInstrument(), lllllllllllllllllIlllllIIIllIIII.instrument.get())) continue;
                int lllllllllllllllllIlllllIIIllIIll = Byte.toUnsignedInt(lllllllllllllllllIlllllIIIllIlIl.getKey());
                if ((lllllllllllllllllIlllllIIIllIIll -= 33) < 0 || lllllllllllllllllIlllllIIIllIIll > 24) {
                    lllllllllllllllllIlllllIIIllIIII.warning("Note at tick %d out of range.", lllllllllllllllllIlllllIIIllIIlI);
                    continue;
                }
                lllllllllllllllllIlllllIIIllIIII.addNote(lllllllllllllllllIlllllIIIllIIlI, lllllllllllllllllIlllllIIIllIIll);
            }
        }
        return true;
    }

    public void previewSong(File lllllllllllllllllIlllllIIlllIIll) {
        Notebot lllllllllllllllllIlllllIIlllIlII;
        if (!lllllllllllllllllIlllllIIlllIlII.isActive()) {
            lllllllllllllllllIlllllIIlllIlII.toggle();
        }
        if (lllllllllllllllllIlllllIIlllIlII.loadFileToMap(lllllllllllllllllIlllllIIlllIIll)) {
            lllllllllllllllllIlllllIIlllIlII.info("Song \"%s\" loaded.", lllllllllllllllllIlllllIIlllIlII.getFileLabel(lllllllllllllllllIlllllIIlllIIll.toPath()));
            lllllllllllllllllIlllllIIlllIlII.stage = Stage.Preview;
            lllllllllllllllllIlllllIIlllIlII.Play();
        }
    }

    private boolean isValidScanSpot(class_2338 lllllllllllllllllIllllIllIllllll) {
        Notebot lllllllllllllllllIllllIlllIIIIII;
        if (lllllllllllllllllIllllIlllIIIIII.mc.field_1687.method_8320(lllllllllllllllllIllllIllIllllll).method_26204() != class_2246.field_10179) {
            return false;
        }
        return lllllllllllllllllIllllIlllIIIIII.mc.field_1687.method_8320(lllllllllllllllllIllllIllIllllll.method_10084()).method_26215();
    }

    @Override
    public void onActivate() {
        Notebot lllllllllllllllllIlllllIlIlllIll;
        lllllllllllllllllIlllllIlIlllIll.ticks = 0;
        lllllllllllllllllIlllllIlIlllIll.resetVariables();
    }

    private void playRotate() {
        Notebot lllllllllllllllllIllllIlllIIllII;
        if (lllllllllllllllllIllllIlllIIllII.mc.field_1761 == null) {
            return;
        }
        try {
            int lllllllllllllllllIllllIlllIIlllI = (Integer)lllllllllllllllllIllllIlllIIllII.song.get((int)lllllllllllllllllIllllIlllIIllII.currentIndex).right;
            class_2338 lllllllllllllllllIllllIlllIIllIl = lllllllllllllllllIllllIlllIIllII.blockPositions.get(lllllllllllllllllIllllIlllIIlllI);
            lllllllllllllllllIllllIlllIIllII.mc.field_1761.method_2910(lllllllllllllllllIllllIlllIIllIl, class_2350.field_11033);
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIlllllIlIllIIII) {
        Notebot lllllllllllllllllIlllllIlIlIllll;
        ++lllllllllllllllllIlllllIlIlIllll.ticks;
        if (lllllllllllllllllIlllllIlIlIllll.stage == Stage.SetUp) {
            lllllllllllllllllIlllllIlIlIllll.onTickSetup();
        } else if (lllllllllllllllllIlllllIlIlIllll.stage == Stage.Tune) {
            lllllllllllllllllIlllllIlIlIllll.onTickTune();
        } else if (lllllllllllllllllIlllllIlIlIllll.stage == Stage.Preview || lllllllllllllllllIlllllIlIlIllll.stage == Stage.Playing) {
            if (!lllllllllllllllllIlllllIlIlIllll.isPlaying) {
                return;
            }
            if (lllllllllllllllllIlllllIlIlIllll.mc.field_1724 == null || lllllllllllllllllIlllllIlIlIllll.currentIndex >= lllllllllllllllllIlllllIlIlIllll.song.size()) {
                lllllllllllllllllIlllllIlIlIllll.stop();
                return;
            }
            while ((Integer)lllllllllllllllllIlllllIlIlIllll.song.get((int)lllllllllllllllllIlllllIlIlIllll.currentIndex).left < lllllllllllllllllIlllllIlIlIllll.currentNote) {
                ++lllllllllllllllllIlllllIlIlIllll.currentIndex;
            }
            while ((Integer)lllllllllllllllllIlllllIlIlIllll.song.get((int)lllllllllllllllllIlllllIlIlIllll.currentIndex).left == lllllllllllllllllIlllllIlIlIllll.currentNote) {
                if (lllllllllllllllllIlllllIlIlIllll.stage == Stage.Preview) {
                    lllllllllllllllllIlllllIlIlIllll.onTickPreview();
                } else {
                    lllllllllllllllllIlllllIlIlIllll.onTickPlay();
                }
                ++lllllllllllllllllIlllllIlIlIllll.currentIndex;
                if (lllllllllllllllllIlllllIlIlIllll.currentIndex < lllllllllllllllllIlllllIlIlIllll.song.size()) continue;
                return;
            }
            ++lllllllllllllllllIlllllIlIlIllll.currentNote;
            if (lllllllllllllllllIlllllIlIlIllll.status != null) {
                lllllllllllllllllIlllllIlIlIllll.status.set(lllllllllllllllllIlllllIlIlIllll.getStatus());
            }
        }
    }

    private boolean tuneBlock(class_2338 lllllllllllllllllIllllIllllIIIIl, int lllllllllllllllllIllllIlllIlllII) {
        Notebot lllllllllllllllllIllllIllllIIIlI;
        if (lllllllllllllllllIllllIllllIIIlI.mc.field_1687 == null || lllllllllllllllllIllllIllllIIIlI.mc.field_1724 == null) {
            return false;
        }
        class_2680 lllllllllllllllllIllllIlllIlllll = lllllllllllllllllIllllIllllIIIlI.mc.field_1687.method_8320(lllllllllllllllllIllllIllllIIIIl);
        if (lllllllllllllllllIllllIlllIlllll.method_26204() != class_2246.field_10179) {
            ++lllllllllllllllllIllllIllllIIIlI.offset;
            lllllllllllllllllIllllIllllIIIlI.stage = Stage.SetUp;
            return true;
        }
        if (((Integer)lllllllllllllllllIllllIlllIlllll.method_11654((class_2769)class_2428.field_11324)).equals(lllllllllllllllllIllllIlllIlllII)) {
            ++lllllllllllllllllIllllIllllIIIlI.currentNote;
            lllllllllllllllllIllllIllllIIIlI.stage = Stage.SetUp;
            return true;
        }
        lllllllllllllllllIllllIllllIIIlI.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_1268.field_5808, new class_3965(lllllllllllllllllIllllIllllIIIlI.mc.field_1724.method_19538(), lllllllllllllllllIllllIllllIIIlI.rayTraceCheck(lllllllllllllllllIllllIllllIIIIl), lllllllllllllllllIllllIllllIIIIl, true)));
        lllllllllllllllllIllllIllllIIIlI.mc.field_1724.method_6104(class_1268.field_5808);
        return true;
    }

    public void disable() {
        Notebot lllllllllllllllllIlllllIIlllllll;
        lllllllllllllllllIlllllIIlllllll.resetVariables();
        lllllllllllllllllIlllllIIlllllll.info("Stopping.", new Object[0]);
        if (!lllllllllllllllllIlllllIIlllllll.isActive()) {
            lllllllllllllllllIlllllIIlllllll.toggle();
        }
    }

    private boolean setupBlocks() {
        Notebot lllllllllllllllllIlllllIIIIIlIII;
        lllllllllllllllllIlllllIIIIIlIII.song.forEach(lllllllllllllllllIllllIllIIlllll -> {
            Notebot lllllllllllllllllIllllIllIlIIIII;
            if (!lllllllllllllllllIllllIllIlIIIII.uniqueNotes.contains(lllllllllllllllllIllllIllIIlllll.right)) {
                lllllllllllllllllIllllIllIlIIIII.uniqueNotes.add((Integer)lllllllllllllllllIllllIllIIlllll.right);
            }
        });
        lllllllllllllllllIlllllIIIIIlIII.scanForNoteblocks();
        if (lllllllllllllllllIlllllIIIIIlIII.uniqueNotes.size() > lllllllllllllllllIlllllIIIIIlIII.possibleBlockPos.size() + lllllllllllllllllIlllllIIIIIlIII.scannedNoteblocks.size()) {
            lllllllllllllllllIlllllIIIIIlIII.error("Too many notes. %d is the maximum.", lllllllllllllllllIlllllIIIIIlIII.possibleBlockPos.size());
            return false;
        }
        lllllllllllllllllIlllllIIIIIlIII.currentNote = 0;
        lllllllllllllllllIlllllIIIIIlIII.offset = 0;
        lllllllllllllllllIlllllIIIIIlIII.stage = Stage.SetUp;
        return true;
    }

    public void Play() {
        Notebot lllllllllllllllllIlllllIlIIIlIII;
        if (lllllllllllllllllIlllllIlIIIlIII.mc.field_1724 == null) {
            return;
        }
        if (lllllllllllllllllIlllllIlIIIlIII.mc.field_1724.field_7503.field_7477 && lllllllllllllllllIlllllIlIIIlIII.stage != Stage.Preview) {
            lllllllllllllllllIlllllIlIIIlIII.error("You need to be in survival mode.", new Object[0]);
        } else if (lllllllllllllllllIlllllIlIIIlIII.stage == Stage.Preview || lllllllllllllllllIlllllIlIIIlIII.stage == Stage.Playing) {
            lllllllllllllllllIlllllIlIIIlIII.isPlaying = true;
            lllllllllllllllllIlllllIlIIIlIII.info("Playing.", new Object[0]);
        } else {
            lllllllllllllllllIlllllIlIIIlIII.error("No song loaded.", new Object[0]);
        }
    }

    private void tuneRotate() {
        Notebot lllllllllllllllllIllllIllllIlIlI;
        class_2338 lllllllllllllllllIllllIllllIlIIl = lllllllllllllllllIllllIllllIlIlI.blockPositions.get(lllllllllllllllllIllllIllllIlIlI.uniqueNotes.get(lllllllllllllllllIllllIllllIlIlI.currentNote));
        if (lllllllllllllllllIllllIllllIlIIl == null) {
            return;
        }
        if (!lllllllllllllllllIllllIllllIlIlI.tuneBlock(lllllllllllllllllIllllIllllIlIIl, lllllllllllllllllIllllIllllIlIlI.uniqueNotes.get(lllllllllllllllllIllllIllllIlIlI.currentNote))) {
            lllllllllllllllllIllllIllllIlIlI.disable();
        }
    }

    /*
     * WARNING - void declaration
     */
    private boolean loadTextFile(File lllllllllllllllllIlllllIIlIIlIlI) {
        void lllllllllllllllllIlllllIIlIIlIIl;
        Notebot lllllllllllllllllIlllllIIlIIlIll;
        try {
            List<String> lllllllllllllllllIlllllIIlIlIlIl = Files.readAllLines(lllllllllllllllllIlllllIIlIIlIlI.toPath());
        }
        catch (IOException lllllllllllllllllIlllllIIlIlIlII) {
            lllllllllllllllllIlllllIIlIIlIll.error("Error while reading \"%s\"", lllllllllllllllllIlllllIIlIIlIlI.getName());
            return false;
        }
        lllllllllllllllllIlllllIIlIIlIll.resetVariables();
        for (int lllllllllllllllllIlllllIIlIIllII = 0; lllllllllllllllllIlllllIIlIIllII < lllllllllllllllllIlllllIIlIIlIIl.size(); ++lllllllllllllllllIlllllIIlIIllII) {
            void lllllllllllllllllIlllllIIlIIllIl;
            void lllllllllllllllllIlllllIIlIIlllI;
            block7: {
                String[] lllllllllllllllllIlllllIIlIIllll = ((String)lllllllllllllllllIlllllIIlIIlIIl.get(lllllllllllllllllIlllllIIlIIllII)).split(":");
                if (lllllllllllllllllIlllllIIlIIllll.length < 2) {
                    lllllllllllllllllIlllllIIlIIlIll.warning("Malformed line %d", lllllllllllllllllIlllllIIlIIllII);
                    continue;
                }
                try {
                    int lllllllllllllllllIlllllIIlIlIIll;
                    int lllllllllllllllllIlllllIIlIlIIlI = Integer.parseInt(lllllllllllllllllIlllllIIlIIllll[0]);
                    int lllllllllllllllllIlllllIIlIlIIIl = Integer.parseInt(lllllllllllllllllIlllllIIlIIllll[1]);
                    if (lllllllllllllllllIlllllIIlIIllll.length > 2 && !NotebotUtils.isValidIntrumentTextFile(lllllllllllllllllIlllllIIlIlIIll = Integer.parseInt(lllllllllllllllllIlllllIIlIIllll[2]), lllllllllllllllllIlllllIIlIIlIll.instrument.get())) {
                    }
                    break block7;
                }
                catch (NumberFormatException lllllllllllllllllIlllllIIlIlIIII) {
                    lllllllllllllllllIlllllIIlIIlIll.warning("Invalid character at line %d", lllllllllllllllllIlllllIIlIIllII);
                }
                continue;
            }
            lllllllllllllllllIlllllIIlIIlIll.addNote((int)lllllllllllllllllIlllllIIlIIlllI, (int)lllllllllllllllllIlllllIIlIIllIl);
        }
        return true;
    }

    private boolean isValidEmptySpot(class_2338 lllllllllllllllllIllllIlllIIIIll) {
        Notebot lllllllllllllllllIllllIlllIIIllI;
        if (!lllllllllllllllllIllllIlllIIIllI.mc.field_1687.method_8320(lllllllllllllllllIllllIlllIIIIll).method_26215()) {
            return false;
        }
        if (!lllllllllllllllllIllllIlllIIIllI.mc.field_1687.method_8320(lllllllllllllllllIllllIlllIIIIll.method_10084()).method_26215()) {
            return false;
        }
        return lllllllllllllllllIllllIlllIIIllI.mc.field_1687.method_8320(lllllllllllllllllIllllIlllIIIIll.method_10074()).method_26204() != class_2246.field_10179;
    }

    private class_2350 rayTraceCheck(class_2338 lllllllllllllllllIllllIllIlIllll) {
        Notebot lllllllllllllllllIllllIllIllIIII;
        class_243 lllllllllllllllllIllllIllIlIlllI = new class_243(lllllllllllllllllIllllIllIllIIII.mc.field_1724.method_23317(), lllllllllllllllllIllllIllIllIIII.mc.field_1724.method_23318() + (double)lllllllllllllllllIllllIllIllIIII.mc.field_1724.method_18381(lllllllllllllllllIllllIllIllIIII.mc.field_1724.method_18376()), lllllllllllllllllIllllIllIllIIII.mc.field_1724.method_23321());
        for (class_2350 lllllllllllllllllIllllIllIllIIIl : class_2350.values()) {
            class_3959 lllllllllllllllllIllllIllIllIIll = new class_3959(lllllllllllllllllIllllIllIlIlllI, new class_243((double)lllllllllllllllllIllllIllIlIllll.method_10263() + 0.5 + (double)lllllllllllllllllIllllIllIllIIIl.method_10163().method_10263() * 0.5, (double)lllllllllllllllllIllllIllIlIllll.method_10264() + 0.5 + (double)lllllllllllllllllIllllIllIllIIIl.method_10163().method_10264() * 0.5, (double)lllllllllllllllllIllllIllIlIllll.method_10260() + 0.5 + (double)lllllllllllllllllIllllIllIllIIIl.method_10163().method_10260() * 0.5), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)lllllllllllllllllIllllIllIllIIII.mc.field_1724);
            class_3965 lllllllllllllllllIllllIllIllIIlI = lllllllllllllllllIllllIllIllIIII.mc.field_1687.method_17742(lllllllllllllllllIllllIllIllIIll);
            if (lllllllllllllllllIllllIllIllIIlI == null || lllllllllllllllllIllllIllIllIIlI.method_17783() != class_239.class_240.field_1332 || !lllllllllllllllllIllllIllIllIIlI.method_17777().equals((Object)lllllllllllllllllIllllIllIlIllll)) continue;
            return lllllllllllllllllIllllIllIllIIIl;
        }
        if ((double)lllllllllllllllllIllllIllIlIllll.method_10264() > lllllllllllllllllIllllIllIlIlllI.field_1351) {
            return class_2350.field_11033;
        }
        return class_2350.field_11036;
    }

    /*
     * WARNING - void declaration
     */
    private void onTickSetup() {
        void lllllllllllllllllIllllIllllllIIl;
        Notebot lllllllllllllllllIllllIllllllIll;
        if (lllllllllllllllllIllllIllllllIll.ticks < lllllllllllllllllIllllIllllllIll.tickDelay.get()) {
            return;
        }
        lllllllllllllllllIllllIllllllIll.ticks = 0;
        if (lllllllllllllllllIllllIllllllIll.currentNote >= lllllllllllllllllIllllIllllllIll.uniqueNotes.size()) {
            lllllllllllllllllIllllIllllllIll.stage = Stage.Playing;
            lllllllllllllllllIllllIllllllIll.info("Loading done.", new Object[0]);
            lllllllllllllllllIllllIllllllIll.Play();
            return;
        }
        int lllllllllllllllllIllllIllllllIlI = lllllllllllllllllIllllIllllllIll.currentNote + lllllllllllllllllIllllIllllllIll.offset;
        if (lllllllllllllllllIllllIllllllIlI < lllllllllllllllllIllllIllllllIll.scannedNoteblocks.size()) {
            class_2338 lllllllllllllllllIllllIllllllllI = lllllllllllllllllIllllIllllllIll.scannedNoteblocks.get(lllllllllllllllllIllllIllllllIlI);
            if (lllllllllllllllllIllllIllllllIll.mc.field_1687.method_8320(lllllllllllllllllIllllIllllllllI).method_26204() != class_2246.field_10179) {
                ++lllllllllllllllllIllllIllllllIll.offset;
            } else {
                lllllllllllllllllIllllIllllllIll.blockPositions.put(lllllllllllllllllIllllIllllllIll.uniqueNotes.get(lllllllllllllllllIllllIllllllIll.currentNote), lllllllllllllllllIllllIllllllllI);
                lllllllllllllllllIllllIllllllIll.stage = Stage.Tune;
            }
            return;
        }
        FindItemResult lllllllllllllllllIllllIllllllIII = InvUtils.findInHotbar(class_1802.field_8643);
        if (!lllllllllllllllllIllllIllllllIII.found()) {
            lllllllllllllllllIllllIllllllIll.error("Not enough noteblocks", new Object[0]);
            lllllllllllllllllIllllIllllllIll.disable();
            return;
        }
        lllllllllllllllllIllllIllllllIlI -= lllllllllllllllllIllllIllllllIll.scannedNoteblocks.size();
        try {
            class_2338 lllllllllllllllllIllllIlllllllIl = lllllllllllllllllIllllIllllllIll.mc.field_1724.method_24515().method_10081((class_2382)lllllllllllllllllIllllIllllllIll.possibleBlockPos.get(lllllllllllllllllIllllIllllllIlI));
        }
        catch (IndexOutOfBoundsException lllllllllllllllllIllllIlllllllII) {
            lllllllllllllllllIllllIllllllIll.error("Not enough valid positions.", new Object[0]);
            lllllllllllllllllIllllIllllllIll.disable();
            return;
        }
        if (!lllllllllllllllllIllllIllllllIll.isValidEmptySpot((class_2338)lllllllllllllllllIllllIllllllIIl) || !NotebotUtils.isValidInstrument((class_2338)lllllllllllllllllIllllIllllllIIl, lllllllllllllllllIllllIllllllIll.instrument.get())) {
            ++lllllllllllllllllIllllIllllllIll.offset;
            return;
        }
        if (!BlockUtils.place((class_2338)lllllllllllllllllIllllIllllllIIl, lllllllllllllllllIllllIllllllIII, true, 100, true)) {
            ++lllllllllllllllllIllllIllllllIll.offset;
        } else {
            lllllllllllllllllIllllIllllllIll.blockPositions.put(lllllllllllllllllIllllIllllllIll.uniqueNotes.get(lllllllllllllllllIllllIllllllIll.currentNote), (class_2338)lllllllllllllllllIllllIllllllIIl);
            lllllllllllllllllIllllIllllllIll.stage = Stage.Tune;
        }
    }

    private void onTickPlay() {
        Notebot lllllllllllllllllIllllIlllIlIlIl;
        if ((Integer)lllllllllllllllllIllllIlllIlIlIl.song.get((int)lllllllllllllllllIllllIlllIlIlIl.currentIndex).left == lllllllllllllllllIllllIlllIlIlIl.currentNote) {
            int lllllllllllllllllIllllIlllIlIlll = (Integer)lllllllllllllllllIllllIlllIlIlIl.song.get((int)lllllllllllllllllIllllIlllIlIlIl.currentIndex).right;
            class_2338 lllllllllllllllllIllllIlllIlIllI = lllllllllllllllllIllllIlllIlIlIl.blockPositions.get(lllllllllllllllllIllllIlllIlIlll);
            if (lllllllllllllllllIllllIlllIlIlIl.polyphonic.get().booleanValue()) {
                Rotations.setCamRotation(Rotations.getYaw(lllllllllllllllllIllllIlllIlIllI), Rotations.getPitch(lllllllllllllllllIllllIlllIlIllI));
                lllllllllllllllllIllllIlllIlIlIl.playRotate();
            } else {
                Rotations.rotate(Rotations.getYaw(lllllllllllllllllIllllIlllIlIllI), Rotations.getPitch(lllllllllllllllllIllllIlllIlIllI), 100, lllllllllllllllllIllllIlllIlIlIl::playRotate);
            }
        }
    }

    private void onTickTune() {
        Notebot lllllllllllllllllIllllIlllllIIII;
        if (lllllllllllllllllIllllIlllllIIII.ticks < lllllllllllllllllIllllIlllllIIII.tickDelay.get()) {
            return;
        }
        lllllllllllllllllIllllIlllllIIII.ticks = 0;
        class_2338 lllllllllllllllllIllllIllllIllll = lllllllllllllllllIllllIlllllIIII.blockPositions.get(lllllllllllllllllIllllIlllllIIII.uniqueNotes.get(lllllllllllllllllIllllIlllllIIII.currentNote));
        if (lllllllllllllllllIllllIllllIllll == null) {
            return;
        }
        Rotations.rotate(Rotations.getYaw(lllllllllllllllllIllllIllllIllll), Rotations.getPitch(lllllllllllllllllIllllIllllIllll), 100, lllllllllllllllllIllllIlllllIIII::tuneRotate);
    }

    @EventHandler
    private void onRender(RenderEvent lllllllllllllllllIlllllIlIllIlII) {
        Notebot lllllllllllllllllIlllllIlIllIIll;
        if (!lllllllllllllllllIlllllIlIllIIll.render.get().booleanValue()) {
            return;
        }
        if (lllllllllllllllllIlllllIlIllIIll.stage != Stage.SetUp && lllllllllllllllllIlllllIlIllIIll.stage != Stage.Tune && !lllllllllllllllllIlllllIlIllIIll.isPlaying) {
            return;
        }
        lllllllllllllllllIlllllIlIllIIll.blockPositions.values().forEach(lllllllllllllllllIllllIlIllIlIII -> {
            Notebot lllllllllllllllllIllllIlIllIlIIl;
            double lllllllllllllllllIllllIlIllIIlll = lllllllllllllllllIllllIlIllIlIII.method_10263();
            double lllllllllllllllllIllllIlIllIIllI = lllllllllllllllllIllllIlIllIlIII.method_10264();
            double lllllllllllllllllIllllIlIllIIlIl = lllllllllllllllllIllllIlIllIlIII.method_10260();
            double lllllllllllllllllIllllIlIllIIlII = lllllllllllllllllIllllIlIllIlIII.method_10263() + 1;
            double lllllllllllllllllIllllIlIllIIIll = lllllllllllllllllIllllIlIllIlIII.method_10264() + 1;
            double lllllllllllllllllIllllIlIllIIIlI = lllllllllllllllllIllllIlIllIlIII.method_10260() + 1;
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, lllllllllllllllllIllllIlIllIIlll, lllllllllllllllllIllllIlIllIIllI, lllllllllllllllllIllllIlIllIIlIl, lllllllllllllllllIllllIlIllIIlII, lllllllllllllllllIllllIlIllIIIll, lllllllllllllllllIllllIlIllIIIlI, lllllllllllllllllIllllIlIllIlIIl.sideColor.get(), lllllllllllllllllIllllIlIllIlIIl.lineColor.get(), lllllllllllllllllIllllIlIllIlIIl.shapeMode.get(), 0);
        });
    }

    public void printStatus() {
        Notebot lllllllllllllllllIlllllIlIIlIlll;
        lllllllllllllllllIlllllIlIIlIlll.info(lllllllllllllllllIlllllIlIIlIlll.getStatus(), new Object[0]);
    }

    @Override
    public WWidget getWidget(GuiTheme lllllllllllllllllIlllllIlIlIIIII) {
        Notebot lllllllllllllllllIlllllIlIlIIIIl;
        WTable lllllllllllllllllIlllllIlIlIIlII = lllllllllllllllllIlllllIlIlIIIII.table();
        lllllllllllllllllIlllllIlIlIIIIl.status = lllllllllllllllllIlllllIlIlIIlII.add(lllllllllllllllllIlllllIlIlIIIII.label(lllllllllllllllllIlllllIlIlIIIIl.getStatus())).expandCellX().widget();
        WButton lllllllllllllllllIlllllIlIlIIIll = lllllllllllllllllIlllllIlIlIIlII.add(lllllllllllllllllIlllllIlIlIIIII.button(lllllllllllllllllIlllllIlIlIIIIl.isPlaying ? "Pause" : "Resume")).right().widget();
        lllllllllllllllllIlllllIlIlIIIll.action = () -> {
            Notebot lllllllllllllllllIllllIlIlllIlIl;
            lllllllllllllllllIllllIlIlllIlIl.Pause();
            lllllllllllllllllIlllllIlIlIIIll.set(lllllllllllllllllIllllIlIlllIlIl.isPlaying ? "Pause" : "Resume");
            lllllllllllllllllIllllIlIlllIlIl.status.set(lllllllllllllllllIllllIlIlllIlIl.getStatus());
        };
        WButton lllllllllllllllllIlllllIlIlIIIlI = lllllllllllllllllIlllllIlIlIIlII.add(lllllllllllllllllIlllllIlIlIIIII.button("Stop")).right().widget();
        lllllllllllllllllIlllllIlIlIIIlI.action = lllllllllllllllllIlllllIlIlIIIIl::stop;
        lllllllllllllllllIlllllIlIlIIlII.row();
        lllllllllllllllllIlllllIlIlIIIIl.noSongsFound = true;
        try {
            Files.list(MeteorClient.FOLDER.toPath().resolve("notebot")).forEach(lllllllllllllllllIllllIllIIIlIlI -> {
                Notebot lllllllllllllllllIllllIllIIIllIl;
                if (lllllllllllllllllIllllIllIIIllIl.isValidFile((Path)lllllllllllllllllIllllIllIIIlIlI)) {
                    lllllllllllllllllIllllIllIIIllIl.noSongsFound = false;
                    lllllllllllllllllIlllllIlIlIIlII.add(lllllllllllllllllIlllllIlIlIIIII.label(lllllllllllllllllIllllIllIIIllIl.getFileLabel((Path)lllllllllllllllllIllllIllIIIlIlI))).expandCellX();
                    WButton lllllllllllllllllIllllIllIIIllll = lllllllllllllllllIlllllIlIlIIlII.add(lllllllllllllllllIlllllIlIlIIIII.button("Load")).right().widget();
                    lllllllllllllllllIllllIllIIIllll.action = () -> {
                        Notebot lllllllllllllllllIllllIlIllllIll;
                        lllllllllllllllllIllllIlIllllIll.loadSong(lllllllllllllllllIllllIllIIIlIlI.toFile());
                        lllllllllllllllllIllllIlIllllIll.status.set(lllllllllllllllllIllllIlIllllIll.getStatus());
                    };
                    WButton lllllllllllllllllIllllIllIIIlllI = lllllllllllllllllIlllllIlIlIIlII.add(lllllllllllllllllIlllllIlIlIIIII.button("Preview")).right().widget();
                    lllllllllllllllllIllllIllIIIlllI.action = () -> {
                        Notebot lllllllllllllllllIllllIllIIIIIIl;
                        lllllllllllllllllIllllIllIIIIIIl.previewSong(lllllllllllllllllIllllIllIIIlIlI.toFile());
                        lllllllllllllllllIllllIllIIIIIIl.status.set(lllllllllllllllllIllllIllIIIIIIl.getStatus());
                    };
                    lllllllllllllllllIlllllIlIlIIlII.row();
                }
            });
        }
        catch (IOException lllllllllllllllllIlllllIlIlIlIII) {
            lllllllllllllllllIlllllIlIlIIlII.add(lllllllllllllllllIlllllIlIlIIIII.label("Missing \"notebot\" folder.")).expandCellX();
            lllllllllllllllllIlllllIlIlIIlII.row();
        }
        if (lllllllllllllllllIlllllIlIlIIIIl.noSongsFound) {
            lllllllllllllllllIlllllIlIlIIlII.add(lllllllllllllllllIlllllIlIlIIIII.label("No songs found.")).expandCellX();
            lllllllllllllllllIlllllIlIlIIlII.row();
            WButton lllllllllllllllllIlllllIlIlIIlll = lllllllllllllllllIlllllIlIlIIlII.add(lllllllllllllllllIlllllIlIlIIIII.button("Help")).expandCellX().widget();
            lllllllllllllllllIlllllIlIlIIlll.action = () -> {
                Notebot lllllllllllllllllIllllIllIIllIIl;
                lllllllllllllllllIllllIllIIllIIl.mc.method_1507((class_437)new NotebotHelpScreen(lllllllllllllllllIlllllIlIlIIIII));
            };
        }
        return lllllllllllllllllIlllllIlIlIIlII;
    }

    private void onTickPreview() {
        Notebot lllllllllllllllllIlllllIIIIIIlII;
        if ((Integer)lllllllllllllllllIlllllIIIIIIlII.song.get((int)lllllllllllllllllIlllllIIIIIIlII.currentIndex).left == lllllllllllllllllIlllllIIIIIIlII.currentNote) {
            lllllllllllllllllIlllllIIIIIIlII.mc.field_1724.method_5783(NotebotUtils.getInstrumentSound(lllllllllllllllllIlllllIIIIIIlII.instrument.get()), 2.0f, (float)Math.pow(2.0, (double)((Integer)lllllllllllllllllIlllllIIIIIIlII.song.get((int)lllllllllllllllllIlllllIIIIIIlII.currentIndex).right - 12) / 12.0));
        }
    }

    public void loadSong(File lllllllllllllllllIlllllIIllllIIl) {
        Notebot lllllllllllllllllIlllllIIlllllII;
        if (!lllllllllllllllllIlllllIIlllllII.isActive()) {
            lllllllllllllllllIlllllIIlllllII.toggle();
        }
        if (!lllllllllllllllllIlllllIIlllllII.loadFileToMap(lllllllllllllllllIlllllIIllllIIl)) {
            return;
        }
        if (!lllllllllllllllllIlllllIIlllllII.setupBlocks()) {
            return;
        }
        lllllllllllllllllIlllllIIlllllII.info("Loading song \"%s\".", lllllllllllllllllIlllllIIlllllII.getFileLabel(lllllllllllllllllIlllllIIllllIIl.toPath()));
    }

    private void addNote(int lllllllllllllllllIlllllIIllIlIll, int lllllllllllllllllIlllllIIllIllIl) {
        Notebot lllllllllllllllllIlllllIIllIllII;
        if (lllllllllllllllllIlllllIIllIllII.polyphonic.get().booleanValue()) {
            lllllllllllllllllIlllllIIllIllII.song.add((ImmutablePair<Integer, Integer>)new ImmutablePair((Object)lllllllllllllllllIlllllIIllIlIll, (Object)lllllllllllllllllIlllllIIllIllIl));
        } else if (lllllllllllllllllIlllllIIllIllII.song.size() == 0) {
            lllllllllllllllllIlllllIIllIllII.song.add((ImmutablePair<Integer, Integer>)new ImmutablePair((Object)lllllllllllllllllIlllllIIllIlIll, (Object)lllllllllllllllllIlllllIIllIllIl));
        } else if ((Integer)lllllllllllllllllIlllllIIllIllII.song.get((int)(lllllllllllllllllIlllllIIllIllII.song.size() - 1)).left != lllllllllllllllllIlllllIIllIlIll) {
            lllllllllllllllllIlllllIIllIllII.song.add((ImmutablePair<Integer, Integer>)new ImmutablePair((Object)lllllllllllllllllIlllllIIllIlIll, (Object)lllllllllllllllllIlllllIIllIllIl));
        }
    }

    private String getFileLabel(Path lllllllllllllllllIlllllIlIIlIIlI) {
        return lllllllllllllllllIlllllIlIIlIIlI.getFileName().toString().replace(".txt", "").replace(".nbs", "");
    }

    private boolean isValidFile(Path lllllllllllllllllIlllllIlIIIllII) {
        String lllllllllllllllllIlllllIlIIIllIl = FilenameUtils.getExtension((String)lllllllllllllllllIlllllIlIIIllII.toFile().getName());
        if (lllllllllllllllllIlllllIlIIIllIl.equals("txt")) {
            return true;
        }
        return lllllllllllllllllIlllllIlIIIllIl.equals("nbs");
    }

    @Override
    public String getInfoString() {
        Notebot lllllllllllllllllIlllllIlIllllIl;
        return lllllllllllllllllIlllllIlIllllIl.stage.toString();
    }

    private void scanForNoteblocks() {
        Notebot lllllllllllllllllIlllllIIIIlIIIl;
        if (lllllllllllllllllIlllllIIIIlIIIl.mc.field_1761 == null || lllllllllllllllllIlllllIIIIlIIIl.mc.field_1687 == null || lllllllllllllllllIlllllIIIIlIIIl.mc.field_1724 == null) {
            return;
        }
        lllllllllllllllllIlllllIIIIlIIIl.scannedNoteblocks.clear();
        int lllllllllllllllllIlllllIIIIlIIll = (int)(-lllllllllllllllllIlllllIIIIlIIIl.mc.field_1761.method_2904()) - 1;
        int lllllllllllllllllIlllllIIIIlIIlI = (int)lllllllllllllllllIlllllIIIIlIIIl.mc.field_1761.method_2904() + 1;
        for (int lllllllllllllllllIlllllIIIIlIlIl = lllllllllllllllllIlllllIIIIlIIll; lllllllllllllllllIlllllIIIIlIlIl < lllllllllllllllllIlllllIIIIlIIlI; ++lllllllllllllllllIlllllIIIIlIlIl) {
            for (int lllllllllllllllllIlllllIIIIlIllI = lllllllllllllllllIlllllIIIIlIIll; lllllllllllllllllIlllllIIIIlIllI < lllllllllllllllllIlllllIIIIlIIlI; ++lllllllllllllllllIlllllIIIIlIllI) {
                for (int lllllllllllllllllIlllllIIIIlIlll = lllllllllllllllllIlllllIIIIlIIll; lllllllllllllllllIlllllIIIIlIlll < lllllllllllllllllIlllllIIIIlIIlI; ++lllllllllllllllllIlllllIIIIlIlll) {
                    class_2338 lllllllllllllllllIlllllIIIIllIIl = lllllllllllllllllIlllllIIIIlIIIl.mc.field_1724.method_24515().method_10069(lllllllllllllllllIlllllIIIIlIlIl, lllllllllllllllllIlllllIIIIlIllI, lllllllllllllllllIlllllIIIIlIlll);
                    if (lllllllllllllllllIlllllIIIIlIIIl.mc.field_1687.method_8320(lllllllllllllllllIlllllIIIIllIIl).method_26204() != class_2246.field_10179) continue;
                    float lllllllllllllllllIlllllIIIIllIII = lllllllllllllllllIlllllIIIIlIIIl.mc.field_1761.method_2904();
                    lllllllllllllllllIlllllIIIIllIII *= lllllllllllllllllIlllllIIIIllIII;
                    if (lllllllllllllllllIlllllIIIIllIIl.method_19770((class_2374)lllllllllllllllllIlllllIIIIlIIIl.mc.field_1724.method_19538(), false) > (double)lllllllllllllllllIlllllIIIIllIII || !lllllllllllllllllIlllllIIIIlIIIl.isValidScanSpot(lllllllllllllllllIlllllIIIIllIIl) || !NotebotUtils.isValidInstrument(lllllllllllllllllIlllllIIIIllIIl, lllllllllllllllllIlllllIIIIlIIIl.instrument.get())) continue;
                    lllllllllllllllllIlllllIIIIlIIIl.scannedNoteblocks.add(lllllllllllllllllIlllllIIIIllIIl);
                }
            }
        }
    }

    private boolean loadFileToMap(File lllllllllllllllllIlllllIIllIIlII) {
        Notebot lllllllllllllllllIlllllIIllIIIIl;
        if (!lllllllllllllllllIlllllIIllIIlII.exists() || !lllllllllllllllllIlllllIIllIIlII.isFile()) {
            lllllllllllllllllIlllllIIllIIIIl.error("File not found", new Object[0]);
            return false;
        }
        String lllllllllllllllllIlllllIIllIIIll = FilenameUtils.getExtension((String)lllllllllllllllllIlllllIIllIIlII.getName());
        boolean lllllllllllllllllIlllllIIllIIIlI = false;
        if (lllllllllllllllllIlllllIIllIIIll.equals("txt")) {
            lllllllllllllllllIlllllIIllIIIlI = lllllllllllllllllIlllllIIllIIIIl.loadTextFile(lllllllllllllllllIlllllIIllIIlII);
        } else if (lllllllllllllllllIlllllIIllIIIll.equals("nbs")) {
            lllllllllllllllllIlllllIIllIIIlI = lllllllllllllllllIlllllIIllIIIIl.loadNbsFile(lllllllllllllllllIlllllIIllIIlII);
        }
        if (lllllllllllllllllIlllllIIllIIIlI) {
            lllllllllllllllllIlllllIIllIIIIl.song.sort(Comparator.comparingInt(lllllllllllllllllIllllIllIIlllIl -> (Integer)lllllllllllllllllIllllIllIIlllIl.left));
        }
        return lllllllllllllllllIlllllIIllIIIlI;
    }

    public void stop() {
        Notebot lllllllllllllllllIlllllIlIIIIIll;
        lllllllllllllllllIlllllIlIIIIIll.info("Stopping.", new Object[0]);
        if (lllllllllllllllllIlllllIlIIIIIll.stage == Stage.SetUp || lllllllllllllllllIlllllIlIIIIIll.stage == Stage.Tune) {
            lllllllllllllllllIlllllIlIIIIIll.resetVariables();
        } else {
            lllllllllllllllllIlllllIlIIIIIll.isPlaying = false;
            lllllllllllllllllIlllllIlIIIIIll.currentNote = 0;
            lllllllllllllllllIlllllIlIIIIIll.currentIndex = 0;
        }
        if (lllllllllllllllllIlllllIlIIIIIll.status != null) {
            lllllllllllllllllIlllllIlIIIIIll.status.set(lllllllllllllllllIlllllIlIIIIIll.getStatus());
        }
    }

    private String getStatus() {
        Notebot lllllllllllllllllIlllllIlIIllIIl;
        if (!lllllllllllllllllIlllllIlIIllIIl.isActive()) {
            return "Module disabled.";
        }
        if (lllllllllllllllllIlllllIlIIllIIl.song.isEmpty()) {
            return "No song loaded.";
        }
        if (lllllllllllllllllIlllllIlIIllIIl.isPlaying) {
            return String.format("Playing song. %d/%d", lllllllllllllllllIlllllIlIIllIIl.currentIndex, lllllllllllllllllIlllllIlIIllIIl.song.size());
        }
        if (lllllllllllllllllIlllllIlIIllIIl.stage == Stage.Playing || lllllllllllllllllIlllllIlIIllIIl.stage == Stage.Preview) {
            return "Ready to play.";
        }
        if (lllllllllllllllllIlllllIlIIllIIl.stage == Stage.SetUp || lllllllllllllllllIlllllIlIIllIIl.stage == Stage.Tune) {
            return "Setting up the noteblocks.";
        }
        return String.format("Stage: %s.", lllllllllllllllllIlllllIlIIllIIl.stage.toString());
    }

    private static enum Stage {
        None,
        SetUp,
        Tune,
        Playing,
        Preview;


        private Stage() {
            Stage lIlllIIIlIll;
        }
    }
}

