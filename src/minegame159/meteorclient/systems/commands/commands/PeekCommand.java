/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1799;
import net.minecraft.class_2172;
import net.minecraft.class_2585;

public class PeekCommand
extends Command {
    private static final class_1799[] ITEMS = new class_1799[27];
    private static final SimpleCommandExceptionType NOT_HOLDING_SHULKER_BOX = new SimpleCommandExceptionType((Message)new class_2585("You must be holding a storage block with items in it."));

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.executes(PeekCommand::lambda$build$0);
    }

    public PeekCommand() {
        super("peek", "Lets you see what's inside storage block items.", new String[0]);
    }

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        if (Utils.openContainer(PeekCommand.mc.field_1724.method_6047(), ITEMS, true)) {
            return 1;
        }
        if (Utils.openContainer(PeekCommand.mc.field_1724.method_6079(), ITEMS, true)) {
            return 1;
        }
        throw NOT_HOLDING_SHULKER_BOX.create();
    }
}

