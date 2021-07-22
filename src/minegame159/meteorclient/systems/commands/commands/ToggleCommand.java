/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.ModuleArgumentType;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2172;

public class ToggleCommand
extends Command {
    public ToggleCommand() {
        super("toggle", "Toggles a module.", "t");
        ToggleCommand lllllllllllllllllIllIllIIlIlIlll;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllIllIllIIlIlIIll) {
        lllllllllllllllllIllIllIIlIlIIll.then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)ToggleCommand.argument("module", ModuleArgumentType.module()).executes(lllllllllllllllllIllIllIIlIIIIll -> {
            Module lllllllllllllllllIllIllIIlIIIIlI = ModuleArgumentType.getModule(lllllllllllllllllIllIllIIlIIIIll, "module");
            lllllllllllllllllIllIllIIlIIIIlI.toggle();
            return 1;
        })).then(ToggleCommand.literal("on").executes(lllllllllllllllllIllIllIIlIIlIIl -> {
            Module lllllllllllllllllIllIllIIlIIlIII = ModuleArgumentType.getModule(lllllllllllllllllIllIllIIlIIlIIl, "module");
            if (!lllllllllllllllllIllIllIIlIIlIII.isActive()) {
                lllllllllllllllllIllIllIIlIIlIII.toggle();
            }
            return 1;
        }))).then(ToggleCommand.literal("off").executes(lllllllllllllllllIllIllIIlIIllIl -> {
            Module lllllllllllllllllIllIllIIlIIlllI = ModuleArgumentType.getModule(lllllllllllllllllIllIllIIlIIllIl, "module");
            if (lllllllllllllllllIllIllIIlIIlllI.isActive()) {
                lllllllllllllllllIllIllIIlIIlllI.toggle();
            }
            return 1;
        })));
    }
}

