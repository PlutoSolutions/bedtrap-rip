/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_1934;
import net.minecraft.class_2172;

public class GamemodeCommand
extends Command {
    private static int lambda$build$0(class_1934 class_19342, CommandContext commandContext) throws CommandSyntaxException {
        GamemodeCommand.mc.field_1724.method_7336(class_19342);
        GamemodeCommand.mc.field_1761.method_2907(class_19342);
        return 1;
    }

    public GamemodeCommand() {
        super("gamemode", "Changes your gamemode client-side.", "gm");
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        for (class_1934 class_19342 : class_1934.values()) {
            if (class_19342 == class_1934.field_9218) continue;
            literalArgumentBuilder.then(GamemodeCommand.literal(class_19342.method_8381()).executes(arg_0 -> GamemodeCommand.lambda$build$0(class_19342, arg_0)));
            if (-1 < 0) continue;
            return;
        }
    }
}

