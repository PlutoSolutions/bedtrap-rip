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
import net.minecraft.class_243;
import net.minecraft.class_746;

public class HClipCommand
extends Command {
    static final boolean $assertionsDisabled = !HClipCommand.class.desiredAssertionStatus();

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        class_746 class_7462 = HClipCommand.mc.field_1724;
        if (!$assertionsDisabled && class_7462 == null) {
            throw new AssertionError();
        }
        double d = (Double)commandContext.getArgument("blocks", Double.class);
        class_243 class_2432 = class_243.method_1030((float)0.0f, (float)class_7462.field_6031).method_1029();
        class_7462.method_5814(class_7462.method_23317() + class_2432.field_1352 * d, class_7462.method_23318(), class_7462.method_23321() + class_2432.field_1350 * d);
        return 1;
    }

    public HClipCommand() {
        super("hclip", "Lets you clip through blocks horizontally.", new String[0]);
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(HClipCommand.argument("blocks", DoubleArgumentType.doubleArg()).executes(HClipCommand::lambda$build$0));
    }
}

