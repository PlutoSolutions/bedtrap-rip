/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_2172;

public class ClearChatCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllIIIlIllIl) {
        lllIIIlIllIl.executes(lllIIIlIllII -> {
            ClearChatCommand.mc.field_1705.method_1743().method_1808(false);
            return 1;
        });
    }

    public ClearChatCommand() {
        super("clear-chat", "Clears your chat.", new String[0]);
        ClearChatCommand lllIIIllIIIl;
    }
}

