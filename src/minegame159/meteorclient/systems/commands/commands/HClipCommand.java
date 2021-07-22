/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.DoubleArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_2172
 *  net.minecraft.class_243
 *  net.minecraft.class_746
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_2172;
import net.minecraft.class_243;
import net.minecraft.class_746;

public class HClipCommand
extends Command {
    public HClipCommand() {
        super("hclip", "Lets you clip through blocks horizontally.", new String[0]);
        HClipCommand llllllllllllllllllIlIlIlllllllII;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> llllllllllllllllllIlIlIllllllIII) {
        llllllllllllllllllIlIlIllllllIII.then(HClipCommand.argument("blocks", DoubleArgumentType.doubleArg()).executes(llllllllllllllllllIlIlIlllllIIll -> {
            class_746 llllllllllllllllllIlIlIlllllIIlI = HClipCommand.mc.field_1724;
            assert (llllllllllllllllllIlIlIlllllIIlI != null);
            double llllllllllllllllllIlIlIlllllIIIl = (Double)llllllllllllllllllIlIlIlllllIIll.getArgument("blocks", Double.class);
            class_243 llllllllllllllllllIlIlIlllllIIII = class_243.method_1030((float)0.0f, (float)llllllllllllllllllIlIlIlllllIIlI.field_6031).method_1029();
            llllllllllllllllllIlIlIlllllIIlI.method_5814(llllllllllllllllllIlIlIlllllIIlI.method_23317() + llllllllllllllllllIlIlIlllllIIII.field_1352 * llllllllllllllllllIlIlIlllllIIIl, llllllllllllllllllIlIlIlllllIIlI.method_23318(), llllllllllllllllllIlIlIlllllIIlI.method_23321() + llllllllllllllllllIlIlIlllllIIII.field_1350 * llllllllllllllllllIlIlIlllllIIIl);
            return 1;
        }));
    }
}

