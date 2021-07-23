/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.utils.player.SlotUtils;
import net.minecraft.class_1799;
import net.minecraft.class_2172;
import net.minecraft.class_2287;
import net.minecraft.class_2585;
import net.minecraft.class_2596;
import net.minecraft.class_2873;

public class GiveCommand
extends Command {
    private static final SimpleCommandExceptionType NOT_IN_CREATIVE = new SimpleCommandExceptionType((Message)new class_2585("You must be in creative mode to use this."));

    private void addItem(class_1799 class_17992) {
        for (int i = 0; i < 36; ++i) {
            class_1799 class_17993 = GiveCommand.mc.field_1724.field_7514.method_5438(SlotUtils.indexToId(i));
            if (!class_17993.method_7960()) {
                if (-2 <= 0) continue;
                return;
            }
            GiveCommand.mc.field_1724.field_3944.method_2883((class_2596)new class_2873(SlotUtils.indexToId(i), class_17992));
            return;
        }
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(((RequiredArgumentBuilder)GiveCommand.argument("item", class_2287.method_9776()).executes(this::lambda$build$0)).then(GiveCommand.argument("number", IntegerArgumentType.integer()).executes(this::lambda$build$1)));
    }

    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        if (!GiveCommand.mc.field_1724.field_7503.field_7477) {
            throw NOT_IN_CREATIVE.create();
        }
        class_1799 class_17992 = class_2287.method_9777((CommandContext)commandContext, (String)"item").method_9781(IntegerArgumentType.getInteger((CommandContext)commandContext, (String)"number"), false);
        this.addItem(class_17992);
        return 1;
    }

    private int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        if (!GiveCommand.mc.field_1724.field_7503.field_7477) {
            throw NOT_IN_CREATIVE.create();
        }
        class_1799 class_17992 = class_2287.method_9777((CommandContext)commandContext, (String)"item").method_9781(1, false);
        this.addItem(class_17992);
        return 1;
    }

    public GiveCommand() {
        super("give", "Gives you any item.", new String[0]);
    }
}

