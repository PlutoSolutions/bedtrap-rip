/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.PlayerArgumentType;
import net.minecraft.class_1297;
import net.minecraft.class_2172;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_310;

public class SpectateCommand
extends Command {
    private final StaticListener shiftListener = new StaticListener(this, null);

    static class_310 access$100() {
        return mc;
    }

    static class_310 access$400() {
        return mc;
    }

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        mc.method_1504((class_1297)SpectateCommand.mc.field_1724);
        return 1;
    }

    static class_310 access$300() {
        return mc;
    }

    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        mc.method_1504((class_1297)PlayerArgumentType.getPlayer(commandContext));
        SpectateCommand.mc.field_1724.method_7353((class_2561)new class_2585("Sneak to un-spectate."), true);
        MeteorClient.EVENT_BUS.subscribe(this.shiftListener);
        return 1;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(SpectateCommand.literal("reset").executes(SpectateCommand::lambda$build$0));
        literalArgumentBuilder.then(SpectateCommand.argument("player", PlayerArgumentType.player()).executes(this::lambda$build$1));
    }

    static class_310 access$200() {
        return mc;
    }

    public SpectateCommand() {
        super("spectate", "Allows you to spectate nearby players", new String[0]);
    }

    private class StaticListener {
        final SpectateCommand this$0;

        StaticListener(SpectateCommand spectateCommand, 1 var2_2) {
            this(spectateCommand);
        }

        private StaticListener(SpectateCommand spectateCommand) {
            this.this$0 = spectateCommand;
        }

        @EventHandler
        private void onKey(KeyEvent keyEvent) {
            if (SpectateCommand.access$100().field_1690.field_1832.method_1417(keyEvent.key, 0) || SpectateCommand.access$200().field_1690.field_1832.method_1433(keyEvent.key)) {
                SpectateCommand.access$400().method_1504((class_1297)SpectateCommand.access$300().field_1724);
                keyEvent.cancel();
                MeteorClient.EVENT_BUS.unsubscribe(this);
            }
        }
    }
}

