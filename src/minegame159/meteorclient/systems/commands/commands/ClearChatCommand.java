/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_2172;

public class ClearChatCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.executes(ClearChatCommand::lambda$build$0);
    }

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        ClearChatCommand.mc.field_1705.method_1743().method_1808(false);
        return 1;
    }

    public ClearChatCommand() {
        super("clear-chat", "Clears your chat.", new String[0]);
    }
}

