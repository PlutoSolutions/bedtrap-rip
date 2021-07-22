/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
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
    private static final /* synthetic */ SimpleCommandExceptionType NOT_IN_CREATIVE;

    private class_1799 getItemStack() {
        class_1799 lllllllllllllllllIlIlIIllIlIIllI = EnchantCommand.mc.field_1724.method_6047();
        if (lllllllllllllllllIlIlIIllIlIIllI == null) {
            lllllllllllllllllIlIlIIllIlIIllI = EnchantCommand.mc.field_1724.method_6079();
        }
        return lllllllllllllllllIlIlIIllIlIIllI;
    }

    public EnchantCommand() {
        super("enchant", "Enchants the item in your hand. REQUIRES Creative mode.", new String[0]);
        EnchantCommand lllllllllllllllllIlIlIIllIllIIII;
    }

    static {
        NOT_IN_CREATIVE = new SimpleCommandExceptionType((Message)new class_2585("You must be in creative mode to use this."));
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllIlIlIIllIlIlIll) {
        EnchantCommand lllllllllllllllllIlIlIIllIlIllII;
        lllllllllllllllllIlIlIIllIlIlIll.then(EnchantCommand.literal("one").then(EnchantCommand.argument("enchantment", class_2194.method_9336()).then(EnchantCommand.argument("level", IntegerArgumentType.integer()).executes(lllllllllllllllllIlIlIIlIllllIlI -> {
            EnchantCommand lllllllllllllllllIlIlIIlIllllIll;
            if (!EnchantCommand.mc.field_1724.method_7337()) {
                throw NOT_IN_CREATIVE.create();
            }
            class_1799 lllllllllllllllllIlIlIIlIllllIIl = lllllllllllllllllIlIlIIlIllllIll.getItemStack();
            if (lllllllllllllllllIlIlIIlIllllIIl != null) {
                class_1887 lllllllllllllllllIlIlIIlIlllllIl = (class_1887)lllllllllllllllllIlIlIIlIllllIlI.getArgument("enchantment", class_1887.class);
                int lllllllllllllllllIlIlIIlIlllllII = (Integer)lllllllllllllllllIlIlIIlIllllIlI.getArgument("level", Integer.class);
                Utils.addEnchantment(lllllllllllllllllIlIlIIlIllllIIl, lllllllllllllllllIlIlIIlIlllllIl, lllllllllllllllllIlIlIIlIlllllII);
            }
            return 1;
        }))));
        lllllllllllllllllIlIlIIllIlIlIll.then(EnchantCommand.literal("all_possible").then(EnchantCommand.argument("level", IntegerArgumentType.integer()).executes(lllllllllllllllllIlIlIIllIIIlIlI -> {
            EnchantCommand lllllllllllllllllIlIlIIllIIIlIII;
            if (!EnchantCommand.mc.field_1724.method_7337()) {
                throw NOT_IN_CREATIVE.create();
            }
            class_1799 lllllllllllllllllIlIlIIllIIIlIIl = lllllllllllllllllIlIlIIllIIIlIII.getItemStack();
            if (lllllllllllllllllIlIlIIllIIIlIIl != null) {
                int lllllllllllllllllIlIlIIllIIIllII = (Integer)lllllllllllllllllIlIlIIllIIIlIlI.getArgument("level", Integer.class);
                for (class_1887 lllllllllllllllllIlIlIIllIIIllIl : class_2378.field_11160) {
                    if (!lllllllllllllllllIlIlIIllIIIllIl.method_8192(lllllllllllllllllIlIlIIllIIIlIIl)) continue;
                    Utils.addEnchantment(lllllllllllllllllIlIlIIllIIIlIIl, lllllllllllllllllIlIlIIllIIIllIl, lllllllllllllllllIlIlIIllIIIllII);
                }
            }
            return 1;
        })));
        lllllllllllllllllIlIlIIllIlIlIll.then(EnchantCommand.literal("all").then(EnchantCommand.argument("level", IntegerArgumentType.integer()).executes(lllllllllllllllllIlIlIIllIIllIII -> {
            EnchantCommand lllllllllllllllllIlIlIIllIIlllII;
            if (!EnchantCommand.mc.field_1724.method_7337()) {
                throw NOT_IN_CREATIVE.create();
            }
            class_1799 lllllllllllllllllIlIlIIllIIllIlI = lllllllllllllllllIlIlIIllIIlllII.getItemStack();
            if (lllllllllllllllllIlIlIIllIIllIlI != null) {
                int lllllllllllllllllIlIlIIllIIlllIl = (Integer)lllllllllllllllllIlIlIIllIIllIII.getArgument("level", Integer.class);
                for (class_1887 lllllllllllllllllIlIlIIllIIllllI : class_2378.field_11160) {
                    Utils.addEnchantment(lllllllllllllllllIlIlIIllIIllIlI, lllllllllllllllllIlIlIIllIIllllI, lllllllllllllllllIlIlIIllIIlllIl);
                }
            }
            return 1;
        })));
    }
}

