/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  net.minecraft.class_2172
 *  net.minecraft.class_243
 *  net.minecraft.class_2585
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.NoFall;
import minegame159.meteorclient.systems.modules.player.AntiHunger;
import net.minecraft.class_2172;
import net.minecraft.class_243;
import net.minecraft.class_2585;
import net.minecraft.class_2596;
import net.minecraft.class_2828;

public class DamageCommand
extends Command {
    private static final /* synthetic */ SimpleCommandExceptionType INVULNERABLE;

    @Override
    public void build(LiteralArgumentBuilder<class_2172> llIIIIIllIIIIll) {
        DamageCommand llIIIIIllIIIllI;
        llIIIIIllIIIIll.then(DamageCommand.argument("damage", IntegerArgumentType.integer((int)1, (int)7)).executes(llIIIIIlIIlllll -> {
            DamageCommand llIIIIIlIlIIIII;
            int llIIIIIlIIllllI = IntegerArgumentType.getInteger((CommandContext)llIIIIIlIIlllll, (String)"damage");
            if (DamageCommand.mc.field_1724.field_7503.field_7480) {
                throw INVULNERABLE.create();
            }
            llIIIIIlIlIIIII.damagePlayer(llIIIIIlIIllllI);
            return 1;
        }));
    }

    public DamageCommand() {
        super("damage", "Damages self", "dmg");
        DamageCommand llIIIIIllIIlIlI;
    }

    static {
        INVULNERABLE = new SimpleCommandExceptionType((Message)new class_2585("You are invulnerable."));
    }

    private void sendPosistionPacket(double llIIIIIlIlIIlll, double llIIIIIlIlIlIlI, double llIIIIIlIlIlIIl, boolean llIIIIIlIlIIlII) {
        DamageCommand.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(llIIIIIlIlIIlll, llIIIIIlIlIlIlI, llIIIIIlIlIlIIl, llIIIIIlIlIIlII));
    }

    private void damagePlayer(int llIIIIIlIllIlIl) {
        DamageCommand llIIIIIlIllIllI;
        boolean llIIIIIlIlllIII;
        boolean llIIIIIlIlllIIl = Modules.get().isActive(NoFall.class);
        if (llIIIIIlIlllIIl) {
            Modules.get().get(NoFall.class).toggle();
        }
        if (llIIIIIlIlllIII = Modules.get().isActive(AntiHunger.class)) {
            Modules.get().get(AntiHunger.class).toggle();
        }
        class_243 llIIIIIlIllIlll = DamageCommand.mc.field_1724.method_19538();
        for (int llIIIIIlIllllII = 0; llIIIIIlIllllII < 80; ++llIIIIIlIllllII) {
            llIIIIIlIllIllI.sendPosistionPacket(llIIIIIlIllIlll.field_1352, llIIIIIlIllIlll.field_1351 + (double)llIIIIIlIllIlIl + 2.1, llIIIIIlIllIlll.field_1350, false);
            llIIIIIlIllIllI.sendPosistionPacket(llIIIIIlIllIlll.field_1352, llIIIIIlIllIlll.field_1351 + 0.05, llIIIIIlIllIlll.field_1350, false);
        }
        llIIIIIlIllIllI.sendPosistionPacket(llIIIIIlIllIlll.field_1352, llIIIIIlIllIlll.field_1351, llIIIIIlIllIlll.field_1350, true);
        if (llIIIIIlIlllIIl) {
            Modules.get().get(NoFall.class).toggle();
        }
        if (llIIIIIlIlllIII) {
            Modules.get().get(AntiHunger.class).toggle();
        }
    }
}

