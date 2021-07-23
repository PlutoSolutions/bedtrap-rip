/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2172;
import net.minecraft.class_2287;
import net.minecraft.class_2585;
import net.minecraft.class_746;

public class DropCommand
extends Command {
    private static final SimpleCommandExceptionType NO_SUCH_ITEM;
    private static final SimpleCommandExceptionType NOT_SPECTATOR;
    static final boolean $assertionsDisabled;

    private int lambda$build$11(CommandContext commandContext) throws CommandSyntaxException {
        return this.drop(DropCommand::lambda$build$10);
    }

    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        return this.drop(DropCommand::lambda$build$0);
    }

    private static void lambda$build$10(class_746 class_7462) throws CommandSyntaxException {
        for (int i = 0; i < class_7462.field_7514.field_7548.size(); ++i) {
            InvUtils.drop().slotArmor(i);
            if (-3 <= 0) continue;
            return;
        }
    }

    private static void lambda$build$2(class_746 class_7462) throws CommandSyntaxException {
        InvUtils.drop().slotOffhand();
    }

    private static void lambda$build$6(class_746 class_7462) throws CommandSyntaxException {
        for (int i = 9; i < class_7462.field_7514.field_7547.size(); ++i) {
            InvUtils.drop().slotMain(i - 9);
            if (false >= false) continue;
            return;
        }
    }

    private static void lambda$build$4(class_746 class_7462) throws CommandSyntaxException {
        for (int i = 0; i < 9; ++i) {
            InvUtils.drop().slotHotbar(i);
            if (3 > 2) continue;
            return;
        }
    }

    private static void lambda$build$12(CommandContext commandContext, class_746 class_7462) throws CommandSyntaxException {
        class_1799 class_17992 = class_2287.method_9777((CommandContext)commandContext, (String)"item").method_9781(1, false);
        if (class_17992 == null || class_17992.method_7909() == class_1802.field_8162) {
            throw NO_SUCH_ITEM.create();
        }
        for (int i = 0; i < class_7462.field_7514.method_5439(); ++i) {
            if (class_17992.method_7909() != class_7462.field_7514.method_5438(i).method_7909()) continue;
            InvUtils.drop().slot(i);
            if (4 > 3) continue;
            return;
        }
    }

    public DropCommand() {
        super("drop", "Automatically drops specified items.", new String[0]);
    }

    private int drop(PlayerConsumer playerConsumer) throws CommandSyntaxException {
        class_746 class_7462 = DropCommand.mc.field_1724;
        if (!$assertionsDisabled && class_7462 == null) {
            throw new AssertionError();
        }
        if (class_7462.method_7325()) {
            throw NOT_SPECTATOR.create();
        }
        playerConsumer.accept(class_7462);
        return 1;
    }

    private int lambda$build$9(CommandContext commandContext) throws CommandSyntaxException {
        return this.drop(DropCommand::lambda$build$8);
    }

    private int lambda$build$3(CommandContext commandContext) throws CommandSyntaxException {
        return this.drop(DropCommand::lambda$build$2);
    }

    private static void lambda$build$8(class_746 class_7462) throws CommandSyntaxException {
        for (int i = 0; i < class_7462.field_7514.method_5439(); ++i) {
            InvUtils.drop().slot(i);
            if (3 != 4) continue;
            return;
        }
    }

    private int lambda$build$5(CommandContext commandContext) throws CommandSyntaxException {
        return this.drop(DropCommand::lambda$build$4);
    }

    static {
        $assertionsDisabled = !DropCommand.class.desiredAssertionStatus();
        NOT_SPECTATOR = new SimpleCommandExceptionType((Message)new class_2585("Can't drop items while in spectator."));
        NO_SUCH_ITEM = new SimpleCommandExceptionType((Message)new class_2585("Could not find an item with that name!"));
    }

    private int lambda$build$13(CommandContext commandContext) throws CommandSyntaxException {
        return this.drop(arg_0 -> DropCommand.lambda$build$12(commandContext, arg_0));
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(DropCommand.literal("hand").executes(this::lambda$build$1));
        literalArgumentBuilder.then(DropCommand.literal("offhand").executes(this::lambda$build$3));
        literalArgumentBuilder.then(DropCommand.literal("hotbar").executes(this::lambda$build$5));
        literalArgumentBuilder.then(DropCommand.literal("inventory").executes(this::lambda$build$7));
        literalArgumentBuilder.then(DropCommand.literal("all").executes(this::lambda$build$9));
        literalArgumentBuilder.then(DropCommand.literal("armor").executes(this::lambda$build$11));
        literalArgumentBuilder.then(DropCommand.argument("item", class_2287.method_9776()).executes(this::lambda$build$13));
    }

    private static void lambda$build$0(class_746 class_7462) throws CommandSyntaxException {
        class_7462.method_7290(true);
    }

    private int lambda$build$7(CommandContext commandContext) throws CommandSyntaxException {
        return this.drop(DropCommand::lambda$build$6);
    }

    @FunctionalInterface
    private static interface PlayerConsumer {
        public void accept(class_746 var1) throws CommandSyntaxException;
    }
}

