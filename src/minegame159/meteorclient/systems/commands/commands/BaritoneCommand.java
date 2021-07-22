/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import baritone.api.BaritoneAPI;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_2172;

public class BaritoneCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> lIIlIIlIIIllIlI) {
        lIIlIIlIIIllIlI.then(BaritoneCommand.argument("command", StringArgumentType.greedyString()).executes(lIIlIIlIIIlIllI -> {
            String lIIlIIlIIIlIlIl = (String)lIIlIIlIIIlIllI.getArgument("command", String.class);
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute(lIIlIIlIIIlIlIl);
            return 1;
        }));
    }

    public BaritoneCommand() {
        super("baritone", "Executes baritone commands.", "b");
        BaritoneCommand lIIlIIlIIIlllIl;
    }
}

