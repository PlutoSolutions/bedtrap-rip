/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_124
 *  net.minecraft.class_2172
 *  net.minecraft.class_2554
 *  net.minecraft.class_2558
 *  net.minecraft.class_2558$class_2559
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_2585
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.Commands;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_2554;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2585;

public class CommandsCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> lIIllllIllllIlI) {
        CommandsCommand lIIllllIllllIll;
        lIIllllIllllIlI.executes(lIIllllIllIIllI -> {
            CommandsCommand lIIllllIllIIlll;
            ChatUtils.info("--- Commands ((highlight)%d(default)) ---", Commands.get().getCount());
            class_2585 lIIllllIllIIlIl = new class_2585("");
            Commands.get().getAll().forEach(arg_0 -> lIIllllIllIIlll.lambda$build$0((class_2554)lIIllllIllIIlIl, arg_0));
            ChatUtils.sendMsg((class_2561)lIIllllIllIIlIl);
            return 1;
        });
    }

    private /* synthetic */ void lambda$build$0(class_2554 lIIllllIlIllIll, Command lIIllllIlIllIlI) {
        CommandsCommand lIIllllIlIlllll;
        lIIllllIlIllIll.method_10852((class_2561)lIIllllIlIlllll.getCommandText(lIIllllIlIllIlI));
    }

    public CommandsCommand() {
        super("commands", "List of all commands.", "help");
        CommandsCommand lIIlllllIIIIIIl;
    }

    private class_2554 getCommandText(Command lIIllllIlllIIlI) {
        class_2585 lIIllllIlllIIIl = new class_2585("");
        lIIllllIlllIIIl.method_10852((class_2561)new class_2585(Utils.nameToTitle(lIIllllIlllIIlI.getName())).method_27695(new class_124[]{class_124.field_1078, class_124.field_1067})).method_27693("\n");
        class_2585 lIIllllIlllIIII = new class_2585(String.valueOf(new StringBuilder().append(Config.get().prefix).append(lIIllllIlllIIlI.getName())));
        if (lIIllllIlllIIlI.getAliases().size() > 0) {
            lIIllllIlllIIII.method_27693(", ");
            for (String lIIllllIlllIlII : lIIllllIlllIIlI.getAliases()) {
                if (lIIllllIlllIlII.isEmpty()) continue;
                lIIllllIlllIIII.method_27693(String.valueOf(new StringBuilder().append(Config.get().prefix).append(lIIllllIlllIlII)));
                if (lIIllllIlllIlII.equals(lIIllllIlllIIlI.getAliases().get(lIIllllIlllIIlI.getAliases().size() - 1))) continue;
                lIIllllIlllIIII.method_27693(", ");
            }
        }
        lIIllllIlllIIIl.method_10852((class_2561)lIIllllIlllIIII.method_27692(class_124.field_1080)).method_27693("\n\n");
        lIIllllIlllIIIl.method_10852((class_2561)new class_2585(lIIllllIlllIIlI.getDescription()).method_27692(class_124.field_1068));
        class_2585 lIIllllIllIllll = new class_2585(Utils.nameToTitle(lIIllllIlllIIlI.getName()));
        if (lIIllllIlllIIlI != Commands.get().getAll().get(Commands.get().getAll().size() - 1)) {
            lIIllllIllIllll.method_10852((class_2561)new class_2585(", ").method_27692(class_124.field_1080));
        }
        lIIllllIllIllll.method_10862(lIIllllIllIllll.method_10866().method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)lIIllllIlllIIIl)).method_10958(new class_2558(class_2558.class_2559.field_11745, String.valueOf(new StringBuilder().append(Config.get().prefix).append(lIIllllIlllIIlI.getName())))));
        return lIIllllIllIllll;
    }
}

