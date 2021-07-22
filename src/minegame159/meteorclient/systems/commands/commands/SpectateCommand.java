/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_1297
 *  net.minecraft.class_2172
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.PlayerArgumentType;
import net.minecraft.class_1297;
import net.minecraft.class_2172;
import net.minecraft.class_2561;
import net.minecraft.class_2585;

public class SpectateCommand
extends Command {
    private final /* synthetic */ StaticListener shiftListener;

    @Override
    public void build(LiteralArgumentBuilder<class_2172> llllllllllllllllllIIIlIllllIlIlI) {
        SpectateCommand llllllllllllllllllIIIlIllllIlIll;
        llllllllllllllllllIIIlIllllIlIlI.then(SpectateCommand.literal("reset").executes(llllllllllllllllllIIIlIllllIIIll -> {
            mc.method_1504((class_1297)SpectateCommand.mc.field_1724);
            return 1;
        }));
        llllllllllllllllllIIIlIllllIlIlI.then(SpectateCommand.argument("player", PlayerArgumentType.player()).executes(llllllllllllllllllIIIlIllllIIlII -> {
            SpectateCommand llllllllllllllllllIIIlIllllIIlll;
            mc.method_1504((class_1297)PlayerArgumentType.getPlayer(llllllllllllllllllIIIlIllllIIlII));
            SpectateCommand.mc.field_1724.method_7353((class_2561)new class_2585("Sneak to un-spectate."), true);
            MeteorClient.EVENT_BUS.subscribe(llllllllllllllllllIIIlIllllIIlll.shiftListener);
            return 1;
        }));
    }

    public SpectateCommand() {
        super("spectate", "Allows you to spectate nearby players", new String[0]);
        SpectateCommand llllllllllllllllllIIIlIlllllIIIl;
        llllllllllllllllllIIIlIlllllIIIl.shiftListener = llllllllllllllllllIIIlIlllllIIIl.new StaticListener();
    }

    private class StaticListener {
        private StaticListener() {
            StaticListener llIIIlllllllIlI;
        }

        @EventHandler
        private void onKey(KeyEvent llIIIllllllIlII) {
            if (mc.field_1690.field_1832.method_1417(llIIIllllllIlII.key, 0) || mc.field_1690.field_1832.method_1433(llIIIllllllIlII.key)) {
                StaticListener llIIIllllllIIll;
                mc.method_1504((class_1297)mc.field_1724);
                llIIIllllllIlII.cancel();
                MeteorClient.EVENT_BUS.unsubscribe(llIIIllllllIIll);
            }
        }
    }
}

