/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.arguments.FloatArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.player.FakePlayer;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerManager;
import net.minecraft.class_2172;

public class FakePlayerCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllIIllIIIlllllIII) {
        FakePlayerCommand lllllllllllllllllIIllIIIlllllIll;
        lllllllllllllllllIIllIIIlllllIII.then(((LiteralArgumentBuilder)FakePlayerCommand.literal("spawn").executes(lllllllllllllllllIIllIIIllIlllII -> {
            FakePlayerCommand lllllllllllllllllIIllIIIllIllIll;
            if (lllllllllllllllllIIllIIIllIllIll.active()) {
                FakePlayerManager.add("BedTrap on Top", 36.0f, true);
            }
            return 1;
        })).then(((RequiredArgumentBuilder)FakePlayerCommand.argument("name", StringArgumentType.word()).executes(lllllllllllllllllIIllIIIlllIIIIl -> {
            FakePlayerCommand lllllllllllllllllIIllIIIlllIIIlI;
            if (lllllllllllllllllIIllIIIlllIIIlI.active()) {
                FakePlayerManager.add(StringArgumentType.getString((CommandContext)lllllllllllllllllIIllIIIlllIIIIl, (String)"name"), 36.0f, true);
            }
            return 1;
        })).then(((RequiredArgumentBuilder)FakePlayerCommand.argument("health", FloatArgumentType.floatArg((float)0.0f)).executes(lllllllllllllllllIIllIIIlllIIlIl -> {
            FakePlayerCommand lllllllllllllllllIIllIIIlllIIllI;
            if (lllllllllllllllllIIllIIIlllIIllI.active()) {
                FakePlayerManager.add(StringArgumentType.getString((CommandContext)lllllllllllllllllIIllIIIlllIIlIl, (String)"name"), FloatArgumentType.getFloat((CommandContext)lllllllllllllllllIIllIIIlllIIlIl, (String)"health"), true);
            }
            return 1;
        })).then(FakePlayerCommand.argument("copy-inv", BoolArgumentType.bool()).executes(lllllllllllllllllIIllIIIlllIllIl -> {
            FakePlayerCommand lllllllllllllllllIIllIIIlllIlllI;
            if (lllllllllllllllllIIllIIIlllIlllI.active()) {
                FakePlayerManager.add(StringArgumentType.getString((CommandContext)lllllllllllllllllIIllIIIlllIllIl, (String)"name"), FloatArgumentType.getFloat((CommandContext)lllllllllllllllllIIllIIIlllIllIl, (String)"health"), BoolArgumentType.getBool((CommandContext)lllllllllllllllllIIllIIIlllIllIl, (String)"copy-inv"));
            }
            return 1;
        })))));
        lllllllllllllllllIIllIIIlllllIII.then(FakePlayerCommand.literal("clear").executes(lllllllllllllllllIIllIIIllllIIlI -> {
            FakePlayerCommand lllllllllllllllllIIllIIIllllIIIl;
            if (lllllllllllllllllIIllIIIllllIIIl.active()) {
                FakePlayerManager.clear();
            }
            return 1;
        }));
    }

    public FakePlayerCommand() {
        super("fake-player", "Manages fake players that you can use for testing.", new String[0]);
        FakePlayerCommand lllllllllllllllllIIllIIIlllllllI;
    }

    private boolean active() {
        if (!Modules.get().isActive(FakePlayer.class)) {
            FakePlayerCommand lllllllllllllllllIIllIIIllllIllI;
            lllllllllllllllllIIllIIIllllIllI.error("The FakePlayer module must be enabled.", new Object[0]);
            return false;
        }
        return true;
    }
}

