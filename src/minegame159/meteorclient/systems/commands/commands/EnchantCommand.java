/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  net.minecraft.class_1799
 *  net.minecraft.class_1887
 *  net.minecraft.class_2172
 *  net.minecraft.class_2194
 *  net.minecraft.class_2378
 *  net.minecraft.class_2585
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1799;
import net.minecraft.class_1887;
import net.minecraft.class_2172;
import net.minecraft.class_2194;
import net.minecraft.class_2378;
import net.minecraft.class_2585;

public class EnchantCommand
extends Command {
    private static final SimpleCommandExceptionType NOT_IN_CREATIVE = new SimpleCommandExceptionType((Message)new class_2585("You must be in creative mode to use this."));

    private class_1799 getItemStack() {
        class_1799 class_17992 = EnchantCommand.mc.field_1724.method_6047();
        if (class_17992 == null) {
            class_17992 = EnchantCommand.mc.field_1724.method_6079();
        }
        return class_17992;
    }

    private int lambda$build$2(CommandContext commandContext) throws CommandSyntaxException {
        if (!EnchantCommand.mc.field_1724.method_7337()) {
            throw NOT_IN_CREATIVE.create();
        }
        class_1799 class_17992 = this.getItemStack();
        if (class_17992 != null) {
            int n = (Integer)commandContext.getArgument("level", Integer.class);
            for (class_1887 class_18872 : class_2378.field_11160) {
                Utils.addEnchantment(class_17992, class_18872, n);
            }
        }
        return 1;
    }

    public EnchantCommand() {
        super("enchant", "Enchants the item in your hand. REQUIRES Creative mode.", new String[0]);
    }

    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        if (!EnchantCommand.mc.field_1724.method_7337()) {
            throw NOT_IN_CREATIVE.create();
        }
        class_1799 class_17992 = this.getItemStack();
        if (class_17992 != null) {
            int n = (Integer)commandContext.getArgument("level", Integer.class);
            for (class_1887 class_18872 : class_2378.field_11160) {
                if (!class_18872.method_8192(class_17992)) continue;
                Utils.addEnchantment(class_17992, class_18872, n);
            }
        }
        return 1;
    }

    private int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        if (!EnchantCommand.mc.field_1724.method_7337()) {
            throw NOT_IN_CREATIVE.create();
        }
        class_1799 class_17992 = this.getItemStack();
        if (class_17992 != null) {
            class_1887 class_18872 = (class_1887)commandContext.getArgument("enchantment", class_1887.class);
            int n = (Integer)commandContext.getArgument("level", Integer.class);
            Utils.addEnchantment(class_17992, class_18872, n);
        }
        return 1;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(EnchantCommand.literal("one").then(EnchantCommand.argument("enchantment", class_2194.method_9336()).then(EnchantCommand.argument("level", IntegerArgumentType.integer()).executes(this::lambda$build$0))));
        literalArgumentBuilder.then(EnchantCommand.literal("all_possible").then(EnchantCommand.argument("level", IntegerArgumentType.integer()).executes(this::lambda$build$1)));
        literalArgumentBuilder.then(EnchantCommand.literal("all").then(EnchantCommand.argument("level", IntegerArgumentType.integer()).executes(this::lambda$build$2)));
    }
}

