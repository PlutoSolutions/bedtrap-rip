/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2172
 *  net.minecraft.class_2287
 *  net.minecraft.class_2585
 *  net.minecraft.class_746
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
    private static final /* synthetic */ SimpleCommandExceptionType NO_SUCH_ITEM;
    private static final /* synthetic */ SimpleCommandExceptionType NOT_SPECTATOR;

    public DropCommand() {
        super("drop", "Automatically drops specified items.", new String[0]);
        DropCommand lIIllIIIlIllIIl;
    }

    private int drop(PlayerConsumer lIIllIIIlIIllII) throws CommandSyntaxException {
        class_746 lIIllIIIlIIllIl = DropCommand.mc.field_1724;
        assert (lIIllIIIlIIllIl != null);
        if (lIIllIIIlIIllIl.method_7325()) {
            throw NOT_SPECTATOR.create();
        }
        lIIllIIIlIIllII.accept(lIIllIIIlIIllIl);
        return 1;
    }

    static {
        NOT_SPECTATOR = new SimpleCommandExceptionType((Message)new class_2585("Can't drop items while in spectator."));
        NO_SUCH_ITEM = new SimpleCommandExceptionType((Message)new class_2585("Could not find an item with that name!"));
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lIIllIIIlIlIIlI) {
        DropCommand lIIllIIIlIlIIll;
        lIIllIIIlIlIIlI.then(DropCommand.literal("hand").executes(lIIllIIIIIIlIll -> {
            DropCommand lIIllIIIIIIlIlI;
            return lIIllIIIIIIlIlI.drop(lIIllIIIIIIIlll -> lIIllIIIIIIIlll.method_7290(true));
        }));
        lIIllIIIlIlIIlI.then(DropCommand.literal("offhand").executes(lIIllIIIIIlIIII -> {
            DropCommand lIIllIIIIIIllll;
            return lIIllIIIIIIllll.drop(lIIllIIIIIIlllI -> InvUtils.drop().slotOffhand());
        }));
        lIIllIIIlIlIIlI.then(DropCommand.literal("hotbar").executes(lIIllIIIIIllIII -> {
            DropCommand lIIllIIIIIllIIl;
            return lIIllIIIIIllIIl.drop(lIIllIIIIIlIlII -> {
                for (int lIIllIIIIIlIlIl = 0; lIIllIIIIIlIlIl < 9; ++lIIllIIIIIlIlIl) {
                    InvUtils.drop().slotHotbar(lIIllIIIIIlIlIl);
                }
            });
        }));
        lIIllIIIlIlIIlI.then(DropCommand.literal("inventory").executes(lIIllIIIIlIIIlI -> {
            DropCommand lIIllIIIIlIIIll;
            return lIIllIIIIlIIIll.drop(lIIllIIIIIlllII -> {
                for (int lIIllIIIIIllllI = 9; lIIllIIIIIllllI < lIIllIIIIIlllII.field_7514.field_7547.size(); ++lIIllIIIIIllllI) {
                    InvUtils.drop().slotMain(lIIllIIIIIllllI - 9);
                }
            });
        }));
        lIIllIIIlIlIIlI.then(DropCommand.literal("all").executes(lIIllIIIIlIllII -> {
            DropCommand lIIllIIIIlIllIl;
            return lIIllIIIIlIllIl.drop(lIIllIIIIlIIlll -> {
                for (int lIIllIIIIlIlIII = 0; lIIllIIIIlIlIII < lIIllIIIIlIIlll.field_7514.method_5439(); ++lIIllIIIIlIlIII) {
                    InvUtils.drop().slot(lIIllIIIIlIlIII);
                }
            });
        }));
        lIIllIIIlIlIIlI.then(DropCommand.literal("armor").executes(lIIllIIIIllIllI -> {
            DropCommand lIIllIIIIllIlIl;
            return lIIllIIIIllIlIl.drop(lIIllIIIIllIIII -> {
                for (int lIIllIIIIllIIlI = 0; lIIllIIIIllIIlI < lIIllIIIIllIIII.field_7514.field_7548.size(); ++lIIllIIIIllIIlI) {
                    InvUtils.drop().slotArmor(lIIllIIIIllIIlI);
                }
            });
        }));
        lIIllIIIlIlIIlI.then(DropCommand.argument("item", class_2287.method_9776()).executes(lIIllIIIlIIIlll -> {
            DropCommand lIIllIIIlIIIllI;
            return lIIllIIIlIIIllI.drop(lIIllIIIIlllIll -> {
                class_1799 lIIllIIIIllllIl = class_2287.method_9777((CommandContext)lIIllIIIlIIIlll, (String)"item").method_9781(1, false);
                if (lIIllIIIIllllIl == null || lIIllIIIIllllIl.method_7909() == class_1802.field_8162) {
                    throw NO_SUCH_ITEM.create();
                }
                for (int lIIllIIIlIIIIII = 0; lIIllIIIlIIIIII < lIIllIIIIlllIll.field_7514.method_5439(); ++lIIllIIIlIIIIII) {
                    if (lIIllIIIIllllIl.method_7909() != lIIllIIIIlllIll.field_7514.method_5438(lIIllIIIlIIIIII).method_7909()) continue;
                    InvUtils.drop().slot(lIIllIIIlIIIIII);
                }
            });
        }));
    }

    @FunctionalInterface
    private static interface PlayerConsumer {
        public void accept(class_746 var1) throws CommandSyntaxException;
    }
}

