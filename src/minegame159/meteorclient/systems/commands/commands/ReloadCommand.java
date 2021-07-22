/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.utils.network.Capes;
import net.minecraft.class_2172;

public class ReloadCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.executes(ReloadCommand::lambda$build$0);
    }

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        Systems.load();
        Capes.init();
        return 1;
    }

    public ReloadCommand() {
        super("reload", "Reloads the config, modules, friends, macros, accounts and capes.", new String[0]);
    }
}

