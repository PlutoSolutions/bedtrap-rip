/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.DoubleArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_2172
 *  net.minecraft.class_746
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_2172;
import net.minecraft.class_746;

public class VClipCommand
extends Command {
    public VClipCommand() {
        super("vclip", "Lets you clip through blocks vertically.", new String[0]);
        VClipCommand lIIlIllIIllllll;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lIIlIllIIllIlII) {
        lIIlIllIIllIlII.then(VClipCommand.argument("blocks", DoubleArgumentType.doubleArg()).executes(lIIlIllIIIlllll -> {
            class_746 lIIlIllIIlIIIIl = VClipCommand.mc.field_1724;
            assert (lIIlIllIIlIIIIl != null);
            double lIIlIllIIlIIIII = (Double)lIIlIllIIIlllll.getArgument("blocks", Double.class);
            lIIlIllIIlIIIIl.method_5814(lIIlIllIIlIIIIl.method_23317(), lIIlIllIIlIIIIl.method_23318() + lIIlIllIIlIIIII, lIIlIllIIlIIIIl.method_23321());
            return 1;
        }));
    }
}

