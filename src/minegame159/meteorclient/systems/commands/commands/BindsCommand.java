/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.class_124
 *  net.minecraft.class_2172
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_2585
 *  net.minecraft.class_5250
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2585;
import net.minecraft.class_5250;

public class BindsCommand
extends Command {
    public BindsCommand() {
        super("binds", "List of all bound modules.", new String[0]);
    }

    private static boolean lambda$build$0(Module module) {
        return module.keybind.isSet();
    }

    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        List list = Modules.get().getAll().stream().filter(BindsCommand::lambda$build$0).collect(Collectors.toList());
        ChatUtils.info("--- Bound Modules ((highlight)%d(default)) ---", list.size());
        for (Module module : list) {
            class_2568 class_25682 = new class_2568(class_2568.class_5247.field_24342, (Object)this.getTooltip(module));
            class_5250 class_52502 = new class_2585(module.title).method_27692(class_124.field_1068);
            class_52502.method_10862(class_52502.method_10866().method_10949(class_25682));
            class_2585 class_25852 = new class_2585(" - ");
            class_25852.method_10862(class_25852.method_10866().method_10949(class_25682));
            class_52502.method_10852((class_2561)class_25852.method_27692(class_124.field_1080));
            class_2585 class_25853 = new class_2585(module.keybind.toString());
            class_25853.method_10862(class_25853.method_10866().method_10949(class_25682));
            class_52502.method_10852((class_2561)class_25853.method_27692(class_124.field_1080));
            ChatUtils.sendMsg((class_2561)class_52502);
        }
        return 1;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.executes(this::lambda$build$1);
    }

    private class_5250 getTooltip(Module module) {
        class_5250 class_52502 = new class_2585(Utils.nameToTitle(module.title)).method_27695(new class_124[]{class_124.field_1078, class_124.field_1067}).method_27693("\n\n");
        class_52502.method_10852((class_2561)new class_2585(module.description).method_27692(class_124.field_1068));
        return class_52502;
    }
}

