/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.class_124
 *  net.minecraft.class_1799
 *  net.minecraft.class_2172
 *  net.minecraft.class_2203
 *  net.minecraft.class_2203$class_2209
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_2558
 *  net.minecraft.class_2558$class_2559
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_2585
 *  net.minecraft.class_2596
 *  net.minecraft.class_2873
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
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
    private void setStack(class_1799 lllllllllllllllllllIlIlIIIlIIlll) {
        NbtCommand.mc.field_1724.field_3944.method_2883((class_2596)new class_2873(36 + NbtCommand.mc.field_1724.field_7514.field_7545, lllllllllllllllllllIlIlIIIlIIlll));
    }

    public NbtCommand() {
        super("nbt", "Modifies NBT data for an item, example: .nbt add {display:{Name:'{\"text\":\"$cRed Name\"}'}}", new String[0]);
        NbtCommand lllllllllllllllllllIlIlIIIllIIII;
    }

    private boolean validBasic(class_1799 lllllllllllllllllllIlIlIIIlIIIII) {
        NbtCommand lllllllllllllllllllIlIlIIIlIIIIl;
        if (!NbtCommand.mc.field_1724.field_7503.field_7477) {
            lllllllllllllllllllIlIlIIIlIIIIl.error("Creative mode only.", new Object[0]);
            return false;
        }
        if (lllllllllllllllllllIlIlIIIlIIIII == null) {
            lllllllllllllllllllIlIlIIIlIIIIl.error("You must hold an item in your main hand.", new Object[0]);
            return false;
        }
        return true;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllllIlIlIIIlIlIlI) {
        NbtCommand lllllllllllllllllllIlIlIIIlIllIl;
        lllllllllllllllllllIlIlIIIlIlIlI.then(NbtCommand.literal("add").then(NbtCommand.argument("nbt_data", CompoundNbtTagArgumentType.nbtTag()).executes(lllllllllllllllllllIlIIlllIlIIII -> {
            NbtCommand lllllllllllllllllllIlIIlllIIlllI;
            class_1799 lllllllllllllllllllIlIIlllIIllll = NbtCommand.mc.field_1724.field_7514.method_7391();
            if (lllllllllllllllllllIlIIlllIIlllI.validBasic(lllllllllllllllllllIlIIlllIIllll)) {
                class_2487 lllllllllllllllllllIlIIlllIlIIll = CompoundNbtTagArgumentType.getTag(lllllllllllllllllllIlIIlllIlIIII, "nbt_data");
                class_2487 lllllllllllllllllllIlIIlllIlIIlI = lllllllllllllllllllIlIIlllIIllll.method_7969();
                if (lllllllllllllllllllIlIIlllIlIIll != null && lllllllllllllllllllIlIIlllIlIIlI != null) {
                    lllllllllllllllllllIlIIlllIIllll.method_7969().method_10543(lllllllllllllllllllIlIIlllIlIIll);
                    lllllllllllllllllllIlIIlllIIlllI.setStack(lllllllllllllllllllIlIIlllIIllll);
                } else {
                    lllllllllllllllllllIlIIlllIIlllI.error(String.valueOf(new StringBuilder().append("Some of the NBT data could not be found, try using: ").append(Config.get().prefix).append("nbt set {nbt}")), new Object[0]);
                }
            }
            return 1;
        })));
        lllllllllllllllllllIlIlIIIlIlIlI.then(NbtCommand.literal("set").then(NbtCommand.argument("nbt_data", CompoundNbtTagArgumentType.nbtTag()).executes(lllllllllllllllllllIlIIlllIllIll -> {
            NbtCommand lllllllllllllllllllIlIIlllIlllll;
            class_1799 lllllllllllllllllllIlIIlllIlllIl = NbtCommand.mc.field_1724.field_7514.method_7391();
            if (lllllllllllllllllllIlIIlllIlllll.validBasic(lllllllllllllllllllIlIIlllIlllIl)) {
                class_2487 lllllllllllllllllllIlIIllllIIIII = (class_2487)lllllllllllllllllllIlIIlllIllIll.getArgument("nbt_data", class_2487.class);
                lllllllllllllllllllIlIIlllIlllIl.method_7980(lllllllllllllllllllIlIIllllIIIII);
                lllllllllllllllllllIlIIlllIlllll.setStack(lllllllllllllllllllIlIIlllIlllIl);
            }
            return 1;
        })));
        lllllllllllllllllllIlIlIIIlIlIlI.then(NbtCommand.literal("remove").then(NbtCommand.argument("nbt_path", class_2203.method_9360()).executes(lllllllllllllllllllIlIIllllIIlll -> {
            NbtCommand lllllllllllllllllllIlIIllllIlIll;
            class_1799 lllllllllllllllllllIlIIllllIlIIl = NbtCommand.mc.field_1724.field_7514.method_7391();
            if (lllllllllllllllllllIlIIllllIlIll.validBasic(lllllllllllllllllllIlIIllllIlIIl)) {
                class_2203.class_2209 lllllllllllllllllllIlIIllllIllII = (class_2203.class_2209)lllllllllllllllllllIlIIllllIIlll.getArgument("nbt_path", class_2203.class_2209.class);
                lllllllllllllllllllIlIIllllIllII.method_9372((class_2520)lllllllllllllllllllIlIIllllIlIIl.method_7969());
            }
            return 1;
        })));
        lllllllllllllllllllIlIlIIIlIlIlI.then(NbtCommand.literal("get").executes(lllllllllllllllllllIlIIllllllIII -> {
            NbtCommand lllllllllllllllllllIlIIlllllIllI;
            class_1799 lllllllllllllllllllIlIIlllllIlll = NbtCommand.mc.field_1724.field_7514.method_7391();
            if (lllllllllllllllllllIlIIlllllIlll == null) {
                lllllllllllllllllllIlIIlllllIllI.error("You must hold an item in your main hand.", new Object[0]);
            } else {
                class_2487 lllllllllllllllllllIlIIlllllllIl = lllllllllllllllllllIlIIlllllIlll.method_7969();
                String lllllllllllllllllllIlIIlllllllII = lllllllllllllllllllIlIIlllllllIl == null ? "none" : lllllllllllllllllllIlIIlllllllIl.method_10714();
                class_2585 lllllllllllllllllllIlIIllllllIll = new class_2585("NBT");
                lllllllllllllllllllIlIIllllllIll.method_10862(lllllllllllllllllllIlIIllllllIll.method_10866().method_27706(class_124.field_1073).method_10958(new class_2558(class_2558.class_2559.field_11750, lllllllllllllllllllIlIIlllllIllI.toString("copy"))).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("Copy the NBT data to your clipboard."))));
                class_2585 lllllllllllllllllllIlIIllllllIlI = new class_2585("");
                lllllllllllllllllllIlIIllllllIlI.method_10852((class_2561)lllllllllllllllllllIlIIllllllIll);
                lllllllllllllllllllIlIIllllllIlI.method_10852((class_2561)new class_2585(String.valueOf(new StringBuilder().append(": ").append(lllllllllllllllllllIlIIlllllllII))));
                lllllllllllllllllllIlIIlllllIllI.info((class_2561)lllllllllllllllllllIlIIllllllIlI);
            }
            return 1;
        }));
        lllllllllllllllllllIlIlIIIlIlIlI.then(NbtCommand.literal("copy").executes(lllllllllllllllllllIlIlIIIIIlIlI -> {
            NbtCommand lllllllllllllllllllIlIlIIIIIlIll;
            class_1799 lllllllllllllllllllIlIlIIIIIlIIl = NbtCommand.mc.field_1724.field_7514.method_7391();
            if (lllllllllllllllllllIlIlIIIIIlIIl == null) {
                lllllllllllllllllllIlIlIIIIIlIll.error("You must hold an item in your main hand.", new Object[0]);
            } else {
                class_2487 lllllllllllllllllllIlIlIIIIIllII = lllllllllllllllllllIlIlIIIIIlIIl.method_7969();
                if (lllllllllllllllllllIlIlIIIIIllII == null) {
                    lllllllllllllllllllIlIlIIIIIlIll.error("No NBT data on this item.", new Object[0]);
                } else {
                    NbtCommand.mc.field_1774.method_1455(lllllllllllllllllllIlIlIIIIIllII.toString());
                    class_2585 lllllllllllllllllllIlIlIIIIIlllI = new class_2585("NBT");
                    lllllllllllllllllllIlIlIIIIIlllI.method_10862(lllllllllllllllllllIlIlIIIIIlllI.method_10866().method_27706(class_124.field_1073).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585(lllllllllllllllllllIlIlIIIIIllII.toString()))));
                    class_2585 lllllllllllllllllllIlIlIIIIIllIl = new class_2585("");
                    lllllllllllllllllllIlIlIIIIIllIl.method_10852((class_2561)lllllllllllllllllllIlIlIIIIIlllI);
                    lllllllllllllllllllIlIlIIIIIllIl.method_10852((class_2561)new class_2585(" data copied!"));
                    lllllllllllllllllllIlIlIIIIIlIll.info((class_2561)lllllllllllllllllllIlIlIIIIIllIl);
                }
            }
            return 1;
        }));
        lllllllllllllllllllIlIlIIIlIlIlI.then(NbtCommand.literal("count").then(NbtCommand.argument("count", IntegerArgumentType.integer((int)-127, (int)127)).executes(lllllllllllllllllllIlIlIIIIllIIl -> {
            NbtCommand lllllllllllllllllllIlIlIIIIllIlI;
            class_1799 lllllllllllllllllllIlIlIIIIllIII = NbtCommand.mc.field_1724.field_7514.method_7391();
            if (lllllllllllllllllllIlIlIIIIllIlI.validBasic(lllllllllllllllllllIlIlIIIIllIII)) {
                int lllllllllllllllllllIlIlIIIIllIll = IntegerArgumentType.getInteger((CommandContext)lllllllllllllllllllIlIlIIIIllIIl, (String)"count");
                lllllllllllllllllllIlIlIIIIllIII.method_7939(lllllllllllllllllllIlIlIIIIllIll);
                lllllllllllllllllllIlIlIIIIllIlI.setStack(lllllllllllllllllllIlIlIIIIllIII);
                lllllllllllllllllllIlIlIIIIllIlI.info("Set mainhand stack count to %s.", lllllllllllllllllllIlIlIIIIllIll);
            }
            return 1;
        })));
    }
}

