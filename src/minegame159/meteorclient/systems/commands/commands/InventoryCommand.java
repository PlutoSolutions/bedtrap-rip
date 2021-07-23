/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.PlayerArgumentType;
import net.minecraft.class_2172;
import net.minecraft.class_490;

public class InventoryCommand
extends Command {
    public InventoryCommand() {
        super("inventory", "Allows you to see parts of another player's inventory.", "inv", "invsee");
    }

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        MeteorClient.INSTANCE.screenToOpen = new class_490(PlayerArgumentType.getPlayer(commandContext));
        return 1;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(InventoryCommand.argument("player", PlayerArgumentType.player()).executes(InventoryCommand::lambda$build$0));
    }
}

