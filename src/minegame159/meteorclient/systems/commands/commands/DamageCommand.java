/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
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
    private static final SimpleCommandExceptionType INVULNERABLE = new SimpleCommandExceptionType((Message)new class_2585("You are invulnerable."));

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(DamageCommand.argument("damage", IntegerArgumentType.integer((int)1, (int)7)).executes(this::lambda$build$0));
    }

    public DamageCommand() {
        super("damage", "Damages self", "dmg");
    }

    private int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        int n = IntegerArgumentType.getInteger((CommandContext)commandContext, (String)"damage");
        if (DamageCommand.mc.field_1724.field_7503.field_7480) {
            throw INVULNERABLE.create();
        }
        this.damagePlayer(n);
        return 1;
    }

    private void sendPosistionPacket(double d, double d2, double d3, boolean bl) {
        DamageCommand.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(d, d2, d3, bl));
    }

    private void damagePlayer(int n) {
        boolean bl;
        boolean bl2 = Modules.get().isActive(NoFall.class);
        if (bl2) {
            Modules.get().get(NoFall.class).toggle();
        }
        if (bl = Modules.get().isActive(AntiHunger.class)) {
            Modules.get().get(AntiHunger.class).toggle();
        }
        class_243 class_2432 = DamageCommand.mc.field_1724.method_19538();
        for (int i = 0; i < 80; ++i) {
            this.sendPosistionPacket(class_2432.field_1352, class_2432.field_1351 + (double)n + 2.1, class_2432.field_1350, false);
            this.sendPosistionPacket(class_2432.field_1352, class_2432.field_1351 + 0.05, class_2432.field_1350, false);
            if (0 <= 0) continue;
            return;
        }
        this.sendPosistionPacket(class_2432.field_1352, class_2432.field_1351, class_2432.field_1350, true);
        if (bl2) {
            Modules.get().get(NoFall.class).toggle();
        }
        if (bl) {
            Modules.get().get(AntiHunger.class).toggle();
        }
    }
}

