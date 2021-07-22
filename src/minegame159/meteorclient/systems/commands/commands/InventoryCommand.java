/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_2172
 *  net.minecraft.class_490
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.PlayerArgumentType;
import net.minecraft.class_2172;
import net.minecraft.class_490;

public class InventoryCommand
extends Command {
    public InventoryCommand() {
        super("inventory", "Allows you to see parts of another player's inventory.", "inv", "invsee");
        InventoryCommand lIlIIlIlIIII;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lIlIIlIIllIl) {
        lIlIIlIIllIl.then(InventoryCommand.argument("player", PlayerArgumentType.player()).executes(lIlIIlIIlIlI -> {
            MeteorClient.INSTANCE.screenToOpen = new class_490(PlayerArgumentType.getPlayer(lIlIIlIIlIlI));
            return 1;
        }));
    }
}

