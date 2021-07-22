/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_1934
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_1934;
import net.minecraft.class_2172;

public class GamemodeCommand
extends Command {
    public GamemodeCommand() {
        super("gamemode", "Changes your gamemode client-side.", "gm");
        GamemodeCommand lllllllllllllllllllIllIllIlllIll;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllllIllIllIllIIlI) {
        for (class_1934 lllllllllllllllllllIllIllIllIlIl : class_1934.values()) {
            if (lllllllllllllllllllIllIllIllIlIl == class_1934.field_9218) continue;
            lllllllllllllllllllIllIllIllIIlI.then(GamemodeCommand.literal(lllllllllllllllllllIllIllIllIlIl.method_8381()).executes(lllllllllllllllllllIllIllIlIlIll -> {
                GamemodeCommand.mc.field_1724.method_7336(lllllllllllllllllllIllIllIllIlIl);
                GamemodeCommand.mc.field_1761.method_2907(lllllllllllllllllllIllIllIllIlIl);
                return 1;
            }));
        }
    }
}

