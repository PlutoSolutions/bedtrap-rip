/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  net.minecraft.class_1799
 *  net.minecraft.class_2172
 *  net.minecraft.class_2585
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1799;
import net.minecraft.class_2172;
import net.minecraft.class_2585;

public class PeekCommand
extends Command {
    private static final /* synthetic */ class_1799[] ITEMS;
    private static final /* synthetic */ SimpleCommandExceptionType NOT_HOLDING_SHULKER_BOX;

    @Override
    public void build(LiteralArgumentBuilder<class_2172> llIIlIlIIIlIl) {
        llIIlIlIIIlIl.executes(llIIlIlIIIIll -> {
            if (Utils.openContainer(PeekCommand.mc.field_1724.method_6047(), ITEMS, true)) {
                return 1;
            }
            if (Utils.openContainer(PeekCommand.mc.field_1724.method_6079(), ITEMS, true)) {
                return 1;
            }
            throw NOT_HOLDING_SHULKER_BOX.create();
        });
    }

    public PeekCommand() {
        super("peek", "Lets you see what's inside storage block items.", new String[0]);
        PeekCommand llIIlIlIIlIII;
    }

    static {
        ITEMS = new class_1799[27];
        NOT_HOLDING_SHULKER_BOX = new SimpleCommandExceptionType((Message)new class_2585("You must be holding a storage block with items in it."));
    }
}

