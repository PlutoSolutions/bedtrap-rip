/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.player.FakePlayer;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerManager;
import net.minecraft.class_2172;

public class FakePlayerCommand
extends Command {
    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        if (this.active()) {
            FakePlayerManager.add(StringArgumentType.getString((CommandContext)commandContext, (String)"name"), 36.0f, true);
        }
        return 1;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(((LiteralArgumentBuilder)FakePlayerCommand.literal("spawn").executes(this::lambda$build$0)).then(((RequiredArgumentBuilder)FakePlayerCommand.argument("name", StringArgumentType.word()).executes(this::lambda$build$1)).then(((RequiredArgumentBuilder)FakePlayerCommand.argument("health", FloatArgumentType.floatArg((float)0.0f)).executes(this::lambda$build$2)).then(FakePlayerCommand.argument("copy-inv", BoolArgumentType.bool()).executes(this::lambda$build$3)))));
        literalArgumentBuilder.then(FakePlayerCommand.literal("clear").executes(this::lambda$build$4));
    }

    private int lambda$build$2(CommandContext commandContext) throws CommandSyntaxException {
        if (this.active()) {
            FakePlayerManager.add(StringArgumentType.getString((CommandContext)commandContext, (String)"name"), FloatArgumentType.getFloat((CommandContext)commandContext, (String)"health"), true);
        }
        return 1;
    }

    private int lambda$build$3(CommandContext commandContext) throws CommandSyntaxException {
        if (this.active()) {
            FakePlayerManager.add(StringArgumentType.getString((CommandContext)commandContext, (String)"name"), FloatArgumentType.getFloat((CommandContext)commandContext, (String)"health"), BoolArgumentType.getBool((CommandContext)commandContext, (String)"copy-inv"));
        }
        return 1;
    }

    public FakePlayerCommand() {
        super("fake-player", "Manages fake players that you can use for testing.", new String[0]);
    }

    private int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        if (this.active()) {
            FakePlayerManager.add("BedTrap on Top", 36.0f, true);
        }
        return 1;
    }

    private boolean active() {
        if (!Modules.get().isActive(FakePlayer.class)) {
            this.error("The FakePlayer module must be enabled.", new Object[0]);
            return false;
        }
        return true;
    }

    private int lambda$build$4(CommandContext commandContext) throws CommandSyntaxException {
        if (this.active()) {
            FakePlayerManager.clear();
        }
        return 1;
    }
}

