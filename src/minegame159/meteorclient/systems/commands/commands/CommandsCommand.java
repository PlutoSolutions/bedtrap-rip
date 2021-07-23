/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
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
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.executes(this::lambda$build$1);
    }

    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        ChatUtils.info("--- Commands ((highlight)%d(default)) ---", Commands.get().getCount());
        class_2585 class_25852 = new class_2585("");
        Commands.get().getAll().forEach(arg_0 -> this.lambda$build$0((class_2554)class_25852, arg_0));
        ChatUtils.sendMsg((class_2561)class_25852);
        return 1;
    }

    private void lambda$build$0(class_2554 class_25542, Command command) {
        class_25542.method_10852((class_2561)this.getCommandText(command));
    }

    public CommandsCommand() {
        super("commands", "List of all commands.", "help");
    }

    private class_2554 getCommandText(Command command) {
        class_2585 class_25852 = new class_2585("");
        class_25852.method_10852((class_2561)new class_2585(Utils.nameToTitle(command.getName())).method_27695(new class_124[]{class_124.field_1078, class_124.field_1067})).method_27693("\n");
        class_2585 class_25853 = new class_2585(String.valueOf(new StringBuilder().append(Config.get().prefix).append(command.getName())));
        if (command.getAliases().size() > 0) {
            class_25853.method_27693(", ");
            for (String string : command.getAliases()) {
                if (string.isEmpty()) continue;
                class_25853.method_27693(String.valueOf(new StringBuilder().append(Config.get().prefix).append(string)));
                if (string.equals(command.getAliases().get(command.getAliases().size() - 1))) continue;
                class_25853.method_27693(", ");
            }
        }
        class_25852.method_10852((class_2561)class_25853.method_27692(class_124.field_1080)).method_27693("\n\n");
        class_25852.method_10852((class_2561)new class_2585(command.getDescription()).method_27692(class_124.field_1068));
        class_2585 class_25854 = new class_2585(Utils.nameToTitle(command.getName()));
        if (command != Commands.get().getAll().get(Commands.get().getAll().size() - 1)) {
            class_25854.method_10852((class_2561)new class_2585(", ").method_27692(class_124.field_1080));
        }
        class_25854.method_10862(class_25854.method_10866().method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)class_25852)).method_10958(new class_2558(class_2558.class_2559.field_11745, String.valueOf(new StringBuilder().append(Config.get().prefix).append(command.getName())))));
        return class_25854;
    }
}

