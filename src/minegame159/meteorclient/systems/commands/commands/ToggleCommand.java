/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.ModuleArgumentType;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2172;

public class ToggleCommand
extends Command {
    public ToggleCommand() {
        super("toggle", "Toggles a module.", "t");
    }

    private static int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        Module module = ModuleArgumentType.getModule(commandContext, "module");
        if (!module.isActive()) {
            module.toggle();
        }
        return 1;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)ToggleCommand.argument("module", ModuleArgumentType.module()).executes(ToggleCommand::lambda$build$0)).then(ToggleCommand.literal("on").executes(ToggleCommand::lambda$build$1))).then(ToggleCommand.literal("off").executes(ToggleCommand::lambda$build$2)));
    }

    private static int lambda$build$2(CommandContext commandContext) throws CommandSyntaxException {
        Module module = ModuleArgumentType.getModule(commandContext, "module");
        if (module.isActive()) {
            module.toggle();
        }
        return 1;
    }

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        Module module = ModuleArgumentType.getModule(commandContext, "module");
        module.toggle();
        return 1;
    }
}

