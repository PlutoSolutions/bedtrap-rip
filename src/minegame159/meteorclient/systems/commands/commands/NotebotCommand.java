/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  net.minecraft.class_2172
 *  net.minecraft.class_2585
 *  net.minecraft.class_2767
 *  net.minecraft.class_3417
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.GuiThemes;
import minegame159.meteorclient.gui.screens.NotebotHelpScreen;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.Notebot;
import net.minecraft.class_2172;
import net.minecraft.class_2585;
import net.minecraft.class_2767;
import net.minecraft.class_3417;

public class NotebotCommand
extends Command {
    /* synthetic */ List<List<Integer>> song;
    /* synthetic */ int ticks;
    private static final /* synthetic */ SimpleCommandExceptionType INVALID_NAME;

    static {
        INVALID_NAME = new SimpleCommandExceptionType((Message)new class_2585("Invalid name."));
    }

    private int getNote(float llllllllllllllllIlllllllllllllIl) {
        for (int llllllllllllllllIlllllllllllllll = 0; llllllllllllllllIlllllllllllllll < 25; ++llllllllllllllllIlllllllllllllll) {
            if (!((double)((float)Math.pow(2.0, (double)(llllllllllllllllIlllllllllllllll - 12) / 12.0)) - 0.01 < (double)llllllllllllllllIlllllllllllllIl) || !((double)((float)Math.pow(2.0, (double)(llllllllllllllllIlllllllllllllll - 12) / 12.0)) + 0.01 > (double)llllllllllllllllIlllllllllllllIl)) continue;
            return llllllllllllllllIlllllllllllllll;
        }
        return 0;
    }

    public NotebotCommand() {
        super("notebot", "Allows you load notebot files", new String[0]);
        NotebotCommand lllllllllllllllllIIIIIIIIIlIIlll;
        lllllllllllllllllIIIIIIIIIlIIlll.ticks = -1;
        lllllllllllllllllIIIIIIIIIlIIlll.song = new ArrayList<List<Integer>>();
    }

    private void saveRecording(Path lllllllllllllllllIIIIIIIIIIIIlIl) {
        NotebotCommand lllllllllllllllllIIIIIIIIIIIIllI;
        if (lllllllllllllllllIIIIIIIIIIIIllI.song.size() < 1) {
            MeteorClient.EVENT_BUS.unsubscribe(lllllllllllllllllIIIIIIIIIIIIllI);
            return;
        }
        try {
            FileWriter lllllllllllllllllIIIIIIIIIIIlIll = new FileWriter(lllllllllllllllllIIIIIIIIIIIIlIl.toFile());
            for (int lllllllllllllllllIIIIIIIIIIIllII = 0; lllllllllllllllllIIIIIIIIIIIllII < lllllllllllllllllIIIIIIIIIIIIllI.song.size() - 1; ++lllllllllllllllllIIIIIIIIIIIllII) {
                List<Integer> lllllllllllllllllIIIIIIIIIIIllIl = lllllllllllllllllIIIIIIIIIIIIllI.song.get(lllllllllllllllllIIIIIIIIIIIllII);
                lllllllllllllllllIIIIIIIIIIIlIll.write(String.format("%d:%d\n", lllllllllllllllllIIIIIIIIIIIllIl.get(0), lllllllllllllllllIIIIIIIIIIIllIl.get(1)));
            }
            List<Integer> lllllllllllllllllIIIIIIIIIIIlIlI = lllllllllllllllllIIIIIIIIIIIIllI.song.get(lllllllllllllllllIIIIIIIIIIIIllI.song.size() - 1);
            lllllllllllllllllIIIIIIIIIIIlIll.write(String.format("%d:%d", lllllllllllllllllIIIIIIIIIIIlIlI.get(0), lllllllllllllllllIIIIIIIIIIIlIlI.get(1)));
            lllllllllllllllllIIIIIIIIIIIlIll.close();
            lllllllllllllllllIIIIIIIIIIIIllI.info(String.format("Song saved. Length: (highlight)%d(default).", lllllllllllllllllIIIIIIIIIIIlIlI.get(0)), new Object[0]);
            MeteorClient.EVENT_BUS.unsubscribe(lllllllllllllllllIIIIIIIIIIIIllI);
        }
        catch (IOException lllllllllllllllllIIIIIIIIIIIlIIl) {
            lllllllllllllllllIIIIIIIIIIIIllI.info("Couldn't create the file.", new Object[0]);
            MeteorClient.EVENT_BUS.unsubscribe(lllllllllllllllllIIIIIIIIIIIIllI);
        }
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllIIIIIIIIIlIIIlI) {
        NotebotCommand lllllllllllllllllIIIIIIIIIlIIIIl;
        lllllllllllllllllIIIIIIIIIlIIIlI.then(NotebotCommand.literal("help").executes(llllllllllllllllIllllllllIlllllI -> {
            MeteorClient.INSTANCE.screenToOpen = new NotebotHelpScreen(GuiThemes.get());
            return 1;
        }));
        lllllllllllllllllIIIIIIIIIlIIIlI.then(NotebotCommand.literal("status").executes(llllllllllllllllIlllllllllIIIIIl -> {
            Notebot llllllllllllllllIlllllllllIIIIII = Modules.get().get(Notebot.class);
            llllllllllllllllIlllllllllIIIIII.printStatus();
            return 1;
        }));
        lllllllllllllllllIIIIIIIIIlIIIlI.then(NotebotCommand.literal("pause").executes(llllllllllllllllIlllllllllIIIlIl -> {
            Notebot llllllllllllllllIlllllllllIIIlII = Modules.get().get(Notebot.class);
            llllllllllllllllIlllllllllIIIlII.Pause();
            return 1;
        }));
        lllllllllllllllllIIIIIIIIIlIIIlI.then(NotebotCommand.literal("resume").executes(llllllllllllllllIlllllllllIIlIIl -> {
            Notebot llllllllllllllllIlllllllllIIlIII = Modules.get().get(Notebot.class);
            llllllllllllllllIlllllllllIIlIII.Pause();
            return 1;
        }));
        lllllllllllllllllIIIIIIIIIlIIIlI.then(NotebotCommand.literal("stop").executes(llllllllllllllllIlllllllllIIllIl -> {
            Notebot llllllllllllllllIlllllllllIIllII = Modules.get().get(Notebot.class);
            llllllllllllllllIlllllllllIIllII.stop();
            return 1;
        }));
        lllllllllllllllllIIIIIIIIIlIIIlI.then(NotebotCommand.literal("play").then(NotebotCommand.argument("name", StringArgumentType.greedyString()).executes(llllllllllllllllIlllllllllIlIIlI -> {
            Notebot llllllllllllllllIlllllllllIlIlIl = Modules.get().get(Notebot.class);
            String llllllllllllllllIlllllllllIlIlII = (String)llllllllllllllllIlllllllllIlIIlI.getArgument("name", String.class);
            if (llllllllllllllllIlllllllllIlIlII == null || llllllllllllllllIlllllllllIlIlII.equals("")) {
                throw INVALID_NAME.create();
            }
            Path llllllllllllllllIlllllllllIlIIll = MeteorClient.FOLDER.toPath().resolve(String.format("notebot/%s.txt", llllllllllllllllIlllllllllIlIlII));
            if (!llllllllllllllllIlllllllllIlIIll.toFile().exists()) {
                llllllllllllllllIlllllllllIlIIll = MeteorClient.FOLDER.toPath().resolve(String.format("notebot/%s.nbs", llllllllllllllllIlllllllllIlIlII));
            }
            llllllllllllllllIlllllllllIlIlIl.loadSong(llllllllllllllllIlllllllllIlIIll.toFile());
            return 1;
        })));
        lllllllllllllllllIIIIIIIIIlIIIlI.then(NotebotCommand.literal("preview").then(NotebotCommand.argument("name", StringArgumentType.greedyString()).executes(llllllllllllllllIllllllllllIIIlI -> {
            Notebot llllllllllllllllIllllllllllIIIIl = Modules.get().get(Notebot.class);
            String llllllllllllllllIllllllllllIIIII = (String)llllllllllllllllIllllllllllIIIlI.getArgument("name", String.class);
            if (llllllllllllllllIllllllllllIIIII == null || llllllllllllllllIllllllllllIIIII == "") {
                throw INVALID_NAME.create();
            }
            Path llllllllllllllllIlllllllllIlllll = MeteorClient.FOLDER.toPath().resolve(String.format("notebot/%s.txt", llllllllllllllllIllllllllllIIIII));
            if (!llllllllllllllllIlllllllllIlllll.toFile().exists()) {
                llllllllllllllllIlllllllllIlllll = MeteorClient.FOLDER.toPath().resolve(String.format("notebot/%s.nbs", llllllllllllllllIllllllllllIIIII));
            }
            llllllllllllllllIllllllllllIIIIl.previewSong(llllllllllllllllIlllllllllIlllll.toFile());
            return 1;
        })));
        lllllllllllllllllIIIIIIIIIlIIIlI.then(NotebotCommand.literal("record").then(NotebotCommand.literal("start").executes(llllllllllllllllIllllllllllIlIII -> {
            NotebotCommand llllllllllllllllIllllllllllIlIIl;
            llllllllllllllllIllllllllllIlIIl.ticks = -1;
            llllllllllllllllIllllllllllIlIIl.song.clear();
            MeteorClient.EVENT_BUS.subscribe(llllllllllllllllIllllllllllIlIIl);
            llllllllllllllllIllllllllllIlIIl.info("Recording started", new Object[0]);
            return 1;
        })));
        lllllllllllllllllIIIIIIIIIlIIIlI.then(NotebotCommand.literal("record").then(NotebotCommand.literal("cancel").executes(llllllllllllllllIllllllllllIllII -> {
            NotebotCommand llllllllllllllllIllllllllllIllIl;
            MeteorClient.EVENT_BUS.unsubscribe(llllllllllllllllIllllllllllIllIl);
            llllllllllllllllIllllllllllIllIl.info("Recording cancelled", new Object[0]);
            return 1;
        })));
        lllllllllllllllllIIIIIIIIIlIIIlI.then(NotebotCommand.literal("record").then(NotebotCommand.literal("save").then(NotebotCommand.argument("name", StringArgumentType.greedyString()).executes(llllllllllllllllIlllllllllllIlIl -> {
            NotebotCommand llllllllllllllllIlllllllllllIllI;
            String llllllllllllllllIlllllllllllIlII = (String)llllllllllllllllIlllllllllllIlIl.getArgument("name", String.class);
            if (llllllllllllllllIlllllllllllIlII == null || llllllllllllllllIlllllllllllIlII == "") {
                throw INVALID_NAME.create();
            }
            Path llllllllllllllllIlllllllllllIIll = MeteorClient.FOLDER.toPath().resolve(String.format("notebot/%s.txt", llllllllllllllllIlllllllllllIlII));
            llllllllllllllllIlllllllllllIllI.saveRecording(llllllllllllllllIlllllllllllIIll);
            return 1;
        }))));
    }

    @EventHandler
    public void onTick(TickEvent.Post lllllllllllllllllIIIIIIIIIIlllIl) {
        NotebotCommand lllllllllllllllllIIIIIIIIIIllllI;
        if (lllllllllllllllllIIIIIIIIIIllllI.ticks == -1) {
            return;
        }
        ++lllllllllllllllllIIIIIIIIIIllllI.ticks;
    }

    @EventHandler
    public void onReadPacket(PacketEvent.Receive lllllllllllllllllIIIIIIIIIIlIlII) {
        class_2767 lllllllllllllllllIIIIIIIIIIllIII;
        if (lllllllllllllllllIIIIIIIIIIlIlII.packet instanceof class_2767 && (lllllllllllllllllIIIIIIIIIIllIII = (class_2767)lllllllllllllllllIIIIIIIIIIlIlII.packet).method_11894() == class_3417.field_15114) {
            NotebotCommand lllllllllllllllllIIIIIIIIIIlIlll;
            if (lllllllllllllllllIIIIIIIIIIlIlll.ticks == -1) {
                lllllllllllllllllIIIIIIIIIIlIlll.ticks = 0;
            }
            lllllllllllllllllIIIIIIIIIIlIlll.song.add(Arrays.asList(lllllllllllllllllIIIIIIIIIIlIlll.ticks, lllllllllllllllllIIIIIIIIIIlIlll.getNote(lllllllllllllllllIIIIIIIIIIllIII.method_11892())));
        }
    }
}

