/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  net.minecraft.class_1799
 *  net.minecraft.class_2172
 *  net.minecraft.class_2287
 *  net.minecraft.class_2585
 *  net.minecraft.class_2596
 *  net.minecraft.class_2873
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
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
    private static final /* synthetic */ SimpleCommandExceptionType NOT_IN_CREATIVE;

    private void addItem(class_1799 lllllllIIlIIllI) {
        for (int lllllllIIlIlIII = 0; lllllllIIlIlIII < 36; ++lllllllIIlIlIII) {
            class_1799 lllllllIIlIlIIl = GiveCommand.mc.field_1724.field_7514.method_5438(SlotUtils.indexToId(lllllllIIlIlIII));
            if (!lllllllIIlIlIIl.method_7960()) continue;
            GiveCommand.mc.field_1724.field_3944.method_2883((class_2596)new class_2873(SlotUtils.indexToId(lllllllIIlIlIII), lllllllIIlIIllI));
            return;
        }
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllIIlIllll) {
        GiveCommand lllllllIIlIlllI;
        lllllllIIlIllll.then(((RequiredArgumentBuilder)GiveCommand.argument("item", class_2287.method_9776()).executes(lllllllIIIlIIlI -> {
            GiveCommand lllllllIIIlIllI;
            if (!GiveCommand.mc.field_1724.field_7503.field_7477) {
                throw NOT_IN_CREATIVE.create();
            }
            class_1799 lllllllIIIlIlII = class_2287.method_9777((CommandContext)lllllllIIIlIIlI, (String)"item").method_9781(1, false);
            lllllllIIIlIllI.addItem(lllllllIIIlIlII);
            return 1;
        })).then(GiveCommand.argument("number", IntegerArgumentType.integer()).executes(lllllllIIIllllI -> {
            GiveCommand lllllllIIIlllII;
            if (!GiveCommand.mc.field_1724.field_7503.field_7477) {
                throw NOT_IN_CREATIVE.create();
            }
            class_1799 lllllllIIIlllIl = class_2287.method_9777((CommandContext)lllllllIIIllllI, (String)"item").method_9781(IntegerArgumentType.getInteger((CommandContext)lllllllIIIllllI, (String)"number"), false);
            lllllllIIIlllII.addItem(lllllllIIIlllIl);
            return 1;
        })));
    }

    public GiveCommand() {
        super("give", "Gives you any item.", new String[0]);
        GiveCommand lllllllIIllIIll;
    }

    static {
        NOT_IN_CREATIVE = new SimpleCommandExceptionType((Message)new class_2585("You must be in creative mode to use this."));
    }
}

