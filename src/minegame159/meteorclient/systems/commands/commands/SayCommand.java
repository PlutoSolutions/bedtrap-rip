/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_2172;
import net.minecraft.class_2596;
import net.minecraft.class_2797;

public class SayCommand
extends Command {
    public SayCommand() {
        super("say", "Sends messages in chat.", new String[0]);
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(SayCommand.argument("message", StringArgumentType.greedyString()).executes(SayCommand::lambda$build$0));
    }

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        mc.method_1562().method_2883((class_2596)new class_2797((String)commandContext.getArgument("message", String.class)));
        return 1;
    }
}

