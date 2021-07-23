/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.CompoundNbtTagArgumentType;
import minegame159.meteorclient.systems.config.Config;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_2172;
import net.minecraft.class_2203;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2585;
import net.minecraft.class_2596;
import net.minecraft.class_2873;

public class NbtCommand
extends Command {
    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        class_1799 class_17992 = NbtCommand.mc.field_1724.field_7514.method_7391();
        if (this.validBasic(class_17992)) {
            class_2487 class_24872 = (class_2487)commandContext.getArgument("nbt_data", class_2487.class);
            class_17992.method_7980(class_24872);
            this.setStack(class_17992);
        }
        return 1;
    }

    private void setStack(class_1799 class_17992) {
        NbtCommand.mc.field_1724.field_3944.method_2883((class_2596)new class_2873(36 + NbtCommand.mc.field_1724.field_7514.field_7545, class_17992));
    }

    private int lambda$build$5(CommandContext commandContext) throws CommandSyntaxException {
        class_1799 class_17992 = NbtCommand.mc.field_1724.field_7514.method_7391();
        if (this.validBasic(class_17992)) {
            int n = IntegerArgumentType.getInteger((CommandContext)commandContext, (String)"count");
            class_17992.method_7939(n);
            this.setStack(class_17992);
            this.info("Set mainhand stack count to %s.", n);
        }
        return 1;
    }

    public NbtCommand() {
        super("nbt", "Modifies NBT data for an item, example: .nbt add {display:{Name:'{\"text\":\"$cRed Name\"}'}}", new String[0]);
    }

    private boolean validBasic(class_1799 class_17992) {
        if (!NbtCommand.mc.field_1724.field_7503.field_7477) {
            this.error("Creative mode only.", new Object[0]);
            return false;
        }
        if (class_17992 == null) {
            this.error("You must hold an item in your main hand.", new Object[0]);
            return false;
        }
        return true;
    }

    private int lambda$build$2(CommandContext commandContext) throws CommandSyntaxException {
        class_1799 class_17992 = NbtCommand.mc.field_1724.field_7514.method_7391();
        if (this.validBasic(class_17992)) {
            class_2203.class_2209 class_22092 = (class_2203.class_2209)commandContext.getArgument("nbt_path", class_2203.class_2209.class);
            class_22092.method_9372((class_2520)class_17992.method_7969());
        }
        return 1;
    }

    private int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        class_1799 class_17992 = NbtCommand.mc.field_1724.field_7514.method_7391();
        if (this.validBasic(class_17992)) {
            class_2487 class_24872 = CompoundNbtTagArgumentType.getTag(commandContext, "nbt_data");
            class_2487 class_24873 = class_17992.method_7969();
            if (class_24872 != null && class_24873 != null) {
                class_17992.method_7969().method_10543(class_24872);
                this.setStack(class_17992);
            } else {
                this.error(String.valueOf(new StringBuilder().append("Some of the NBT data could not be found, try using: ").append(Config.get().prefix).append("nbt set {nbt}")), new Object[0]);
            }
        }
        return 1;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(NbtCommand.literal("add").then(NbtCommand.argument("nbt_data", CompoundNbtTagArgumentType.nbtTag()).executes(this::lambda$build$0)));
        literalArgumentBuilder.then(NbtCommand.literal("set").then(NbtCommand.argument("nbt_data", CompoundNbtTagArgumentType.nbtTag()).executes(this::lambda$build$1)));
        literalArgumentBuilder.then(NbtCommand.literal("remove").then(NbtCommand.argument("nbt_path", class_2203.method_9360()).executes(this::lambda$build$2)));
        literalArgumentBuilder.then(NbtCommand.literal("get").executes(this::lambda$build$3));
        literalArgumentBuilder.then(NbtCommand.literal("copy").executes(this::lambda$build$4));
        literalArgumentBuilder.then(NbtCommand.literal("count").then(NbtCommand.argument("count", IntegerArgumentType.integer((int)-127, (int)127)).executes(this::lambda$build$5)));
    }

    private int lambda$build$4(CommandContext commandContext) throws CommandSyntaxException {
        class_1799 class_17992 = NbtCommand.mc.field_1724.field_7514.method_7391();
        if (class_17992 == null) {
            this.error("You must hold an item in your main hand.", new Object[0]);
        } else {
            class_2487 class_24872 = class_17992.method_7969();
            if (class_24872 == null) {
                this.error("No NBT data on this item.", new Object[0]);
            } else {
                NbtCommand.mc.field_1774.method_1455(class_24872.toString());
                class_2585 class_25852 = new class_2585("NBT");
                class_25852.method_10862(class_25852.method_10866().method_27706(class_124.field_1073).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585(class_24872.toString()))));
                class_2585 class_25853 = new class_2585("");
                class_25853.method_10852((class_2561)class_25852);
                class_25853.method_10852((class_2561)new class_2585(" data copied!"));
                this.info((class_2561)class_25853);
            }
        }
        return 1;
    }

    private int lambda$build$3(CommandContext commandContext) throws CommandSyntaxException {
        class_1799 class_17992 = NbtCommand.mc.field_1724.field_7514.method_7391();
        if (class_17992 == null) {
            this.error("You must hold an item in your main hand.", new Object[0]);
        } else {
            class_2487 class_24872 = class_17992.method_7969();
            String string = class_24872 == null ? "none" : class_24872.method_10714();
            class_2585 class_25852 = new class_2585("NBT");
            class_25852.method_10862(class_25852.method_10866().method_27706(class_124.field_1073).method_10958(new class_2558(class_2558.class_2559.field_11750, this.toString("copy"))).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("Copy the NBT data to your clipboard."))));
            class_2585 class_25853 = new class_2585("");
            class_25853.method_10852((class_2561)class_25852);
            class_25853.method_10852((class_2561)new class_2585(String.valueOf(new StringBuilder().append(": ").append(string))));
            this.info((class_2561)class_25853);
        }
        return 1;
    }
}

