/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_2172
 *  net.minecraft.class_2596
 *  net.minecraft.class_2851
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_2172;
import net.minecraft.class_2596;
import net.minecraft.class_2851;

public class DismountCommand
extends Command {
    public DismountCommand() {
        super("dismount", "Dismounts you from entity you are riding.", new String[0]);
        DismountCommand llllllllllllllllIllIlIIllIIIlIII;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> llllllllllllllllIllIlIIllIIIIlII) {
        llllllllllllllllIllIlIIllIIIIlII.executes(llllllllllllllllIllIlIIllIIIIIll -> {
            mc.method_1562().method_2883((class_2596)new class_2851(0.0f, 0.0f, false, true));
            return 1;
        });
    }
}

