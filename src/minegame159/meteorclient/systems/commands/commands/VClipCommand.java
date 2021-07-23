/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_2172;
import net.minecraft.class_746;

public class VClipCommand
extends Command {
    static final boolean $assertionsDisabled = !VClipCommand.class.desiredAssertionStatus();

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        class_746 class_7462 = VClipCommand.mc.field_1724;
        if (!$assertionsDisabled && class_7462 == null) {
            throw new AssertionError();
        }
        double d = (Double)commandContext.getArgument("blocks", Double.class);
        class_7462.method_5814(class_7462.method_23317(), class_7462.method_23318() + d, class_7462.method_23321());
        return 1;
    }

    public VClipCommand() {
        super("vclip", "Lets you clip through blocks vertically.", new String[0]);
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(VClipCommand.argument("blocks", DoubleArgumentType.doubleArg()).executes(VClipCommand::lambda$build$0));
    }
}

