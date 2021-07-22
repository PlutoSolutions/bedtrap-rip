/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_2172
 *  net.minecraft.class_2596
 *  net.minecraft.class_2797
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_2172;
import net.minecraft.class_2596;
import net.minecraft.class_2797;

public class SayCommand
extends Command {
    public SayCommand() {
        super("say", "Sends messages in chat.", new String[0]);
        SayCommand lllllllllllllllllIlllIIlIllIIlll;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllIlllIIlIllIIIIl) {
        lllllllllllllllllIlllIIlIllIIIIl.then(SayCommand.argument("message", StringArgumentType.greedyString()).executes(lllllllllllllllllIlllIIlIlIllIlI -> {
            mc.method_1562().method_2883((class_2596)new class_2797((String)lllllllllllllllllIlllIIlIlIllIlI.getArgument("message", String.class)));
            return 1;
        }));
    }
}

