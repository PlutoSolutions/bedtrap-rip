/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
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
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
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
    List<List<Integer>> song = new ArrayList<List<Integer>>();
    int ticks = -1;
    private static final SimpleCommandExceptionType INVALID_NAME = new SimpleCommandExceptionType((Message)new class_2585("Invalid name."));

    private int getNote(float f) {
        for (int i = 0; i < 25; ++i) {
            if (!((double)((float)Math.pow(2.0, (double)(i - 12) / 12.0)) - 0.01 < (double)f) || !((double)((float)Math.pow(2.0, (double)(i - 12) / 12.0)) + 0.01 > (double)f)) continue;
            return i;
        }
        return 0;
    }

    public NotebotCommand() {
        super("notebot", "Allows you load notebot files", new String[0]);
    }

    private static int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        Notebot notebot = Modules.get().get(Notebot.class);
        notebot.printStatus();
        return 1;
    }

    private void saveRecording(Path path) {
        if (this.song.size() < 1) {
            MeteorClient.EVENT_BUS.unsubscribe(this);
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(path.toFile());
            for (int i = 0; i < this.song.size() - 1; ++i) {
                List<Integer> list = this.song.get(i);
                fileWriter.write(String.format("%d:%d\n", list.get(0), list.get(1)));
                if (null == null) continue;
                return;
            }
            List<Integer> list = this.song.get(this.song.size() - 1);
            fileWriter.write(String.format("%d:%d", list.get(0), list.get(1)));
            fileWriter.close();
            this.info(String.format("Song saved. Length: (highlight)%d(default).", list.get(0)), new Object[0]);
            MeteorClient.EVENT_BUS.unsubscribe(this);
        }
        catch (IOException iOException) {
            this.info("Couldn't create the file.", new Object[0]);
            MeteorClient.EVENT_BUS.unsubscribe(this);
        }
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(NotebotCommand.literal("help").executes(NotebotCommand::lambda$build$0));
        literalArgumentBuilder.then(NotebotCommand.literal("status").executes(NotebotCommand::lambda$build$1));
        literalArgumentBuilder.then(NotebotCommand.literal("pause").executes(NotebotCommand::lambda$build$2));
        literalArgumentBuilder.then(NotebotCommand.literal("resume").executes(NotebotCommand::lambda$build$3));
        literalArgumentBuilder.then(NotebotCommand.literal("stop").executes(NotebotCommand::lambda$build$4));
        literalArgumentBuilder.then(NotebotCommand.literal("play").then(NotebotCommand.argument("name", StringArgumentType.greedyString()).executes(NotebotCommand::lambda$build$5)));
        literalArgumentBuilder.then(NotebotCommand.literal("preview").then(NotebotCommand.argument("name", StringArgumentType.greedyString()).executes(NotebotCommand::lambda$build$6)));
        literalArgumentBuilder.then(NotebotCommand.literal("record").then(NotebotCommand.literal("start").executes(this::lambda$build$7)));
        literalArgumentBuilder.then(NotebotCommand.literal("record").then(NotebotCommand.literal("cancel").executes(this::lambda$build$8)));
        literalArgumentBuilder.then(NotebotCommand.literal("record").then(NotebotCommand.literal("save").then(NotebotCommand.argument("name", StringArgumentType.greedyString()).executes(this::lambda$build$9))));
    }

    private static int lambda$build$4(CommandContext commandContext) throws CommandSyntaxException {
        Notebot notebot = Modules.get().get(Notebot.class);
        notebot.stop();
        return 1;
    }

    private int lambda$build$9(CommandContext commandContext) throws CommandSyntaxException {
        String string = (String)commandContext.getArgument("name", String.class);
        if (string == null || string == "") {
            throw INVALID_NAME.create();
        }
        Path path = MeteorClient.FOLDER.toPath().resolve(String.format("notebot/%s.txt", string));
        this.saveRecording(path);
        return 1;
    }

    @EventHandler
    public void onTick(TickEvent.Post post) {
        if (this.ticks == -1) {
            return;
        }
        ++this.ticks;
    }

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        MeteorClient.INSTANCE.screenToOpen = new NotebotHelpScreen(GuiThemes.get());
        return 1;
    }

    private static int lambda$build$2(CommandContext commandContext) throws CommandSyntaxException {
        Notebot notebot = Modules.get().get(Notebot.class);
        notebot.Pause();
        return 1;
    }

    private int lambda$build$7(CommandContext commandContext) throws CommandSyntaxException {
        this.ticks = -1;
        this.song.clear();
        MeteorClient.EVENT_BUS.subscribe(this);
        this.info("Recording started", new Object[0]);
        return 1;
    }

    @EventHandler
    public void onReadPacket(PacketEvent.Receive receive) {
        class_2767 class_27672;
        if (receive.packet instanceof class_2767 && (class_27672 = (class_2767)receive.packet).method_11894() == class_3417.field_15114) {
            if (this.ticks == -1) {
                this.ticks = 0;
            }
            this.song.add(Arrays.asList(this.ticks, this.getNote(class_27672.method_11892())));
        }
    }

    private static int lambda$build$5(CommandContext commandContext) throws CommandSyntaxException {
        Notebot notebot = Modules.get().get(Notebot.class);
        String string = (String)commandContext.getArgument("name", String.class);
        if (string == null || string.equals("")) {
            throw INVALID_NAME.create();
        }
        Path path = MeteorClient.FOLDER.toPath().resolve(String.format("notebot/%s.txt", string));
        if (!path.toFile().exists()) {
            path = MeteorClient.FOLDER.toPath().resolve(String.format("notebot/%s.nbs", string));
        }
        notebot.loadSong(path.toFile());
        return 1;
    }

    private int lambda$build$8(CommandContext commandContext) throws CommandSyntaxException {
        MeteorClient.EVENT_BUS.unsubscribe(this);
        this.info("Recording cancelled", new Object[0]);
        return 1;
    }

    private static int lambda$build$3(CommandContext commandContext) throws CommandSyntaxException {
        Notebot notebot = Modules.get().get(Notebot.class);
        notebot.Pause();
        return 1;
    }

    private static int lambda$build$6(CommandContext commandContext) throws CommandSyntaxException {
        Notebot notebot = Modules.get().get(Notebot.class);
        String string = (String)commandContext.getArgument("name", String.class);
        if (string == null || string == "") {
            throw INVALID_NAME.create();
        }
        Path path = MeteorClient.FOLDER.toPath().resolve(String.format("notebot/%s.txt", string));
        if (!path.toFile().exists()) {
            path = MeteorClient.FOLDER.toPath().resolve(String.format("notebot/%s.nbs", string));
        }
        notebot.previewSong(path.toFile());
        return 1;
    }
}

