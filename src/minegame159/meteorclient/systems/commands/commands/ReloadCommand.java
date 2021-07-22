/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.utils.network.Capes;
import net.minecraft.class_2172;

public class ReloadCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllllIIIlIIIllllIl) {
        lllllllllllllllllllIIIlIIIllllIl.executes(lllllllllllllllllllIIIlIIIlllIll -> {
            Systems.load();
            Capes.init();
            return 1;
        });
    }

    public ReloadCommand() {
        super("reload", "Reloads the config, modules, friends, macros, accounts and capes.", new String[0]);
        ReloadCommand lllllllllllllllllllIIIlIIlIIIIII;
    }
}

