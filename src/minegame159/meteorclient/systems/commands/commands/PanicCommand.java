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
import java.util.ArrayList;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import net.minecraft.class_2172;

public class PanicCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.executes(PanicCommand::lambda$build$0);
    }

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        new ArrayList<Module>(Modules.get().getActive()).forEach(Module::toggle);
        return 1;
    }

    public PanicCommand() {
        super("panic", "Disables all modules.", new String[0]);
    }
}

