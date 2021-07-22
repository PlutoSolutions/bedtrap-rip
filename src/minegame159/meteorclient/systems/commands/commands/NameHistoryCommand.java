/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.TypeToken
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.class_124
 *  net.minecraft.class_2172
 *  net.minecraft.class_2558
 *  net.minecraft.class_2558$class_2559
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_2585
 *  net.minecraft.class_5251
 *  net.minecraft.class_640
 */
package minegame159.meteorclient.systems.commands.commands;

import com.google.common.reflect.TypeToken;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.PlayerListEntryArgumentType;
import minegame159.meteorclient.systems.commands.commands.NameHistoryObject;
import minegame159.meteorclient.utils.misc.text.TextUtils;
import minegame159.meteorclient.utils.network.HttpUtils;
import minegame159.meteorclient.utils.player.ChatUtils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2585;
import net.minecraft.class_5251;
import net.minecraft.class_640;

public class NameHistoryCommand
extends Command {
    public NameHistoryCommand() {
        super("name-history", "Provides a list of a players previous names from the Mojang api.", "history", "names");
    }

    private int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        class_640 class_6402 = PlayerListEntryArgumentType.getPlayerListEntry(commandContext);
        Type type = new TypeToken<List<NameHistoryObject>>(this){
            final NameHistoryCommand this$0;
            {
                this.this$0 = nameHistoryCommand;
            }
        }.getType();
        List list = (List)HttpUtils.get(String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(class_6402.method_2966().getId().toString().replace("-", "")).append("/names")), type);
        if (list == null || list.isEmpty()) {
            this.error("There was an error fetching that users name history.", new Object[0]);
            return 1;
        }
        class_2585 class_25852 = new class_2585(class_6402.method_2966().getName());
        class_25852.method_10852((class_2561)new class_2585("'s"));
        Color color = TextUtils.getMostPopularColor(class_6402.method_2971());
        class_25852.method_10862(class_25852.method_10866().method_27703(new class_5251(color.getPacked())).method_10958(new class_2558(class_2558.class_2559.field_11749, String.valueOf(new StringBuilder().append("https://namemc.com/search?q=").append(class_6402.method_2966().getName())))).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("View on NameMC").method_27692(class_124.field_1054).method_27692(class_124.field_1056))));
        this.info((class_2561)class_25852.method_10852((class_2561)new class_2585(" Username History:").method_27692(class_124.field_1080)));
        for (NameHistoryObject nameHistoryObject : list) {
            class_2585 class_25853 = new class_2585(nameHistoryObject.name);
            class_25853.method_27692(class_124.field_1075);
            if (nameHistoryObject.changedToAt != 0L) {
                class_2585 class_25854 = new class_2585("Changed at: ");
                class_25854.method_27692(class_124.field_1080);
                Date date = new Date(nameHistoryObject.changedToAt);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss, dd/MM/yyyy");
                class_25854.method_10852((class_2561)new class_2585(simpleDateFormat.format(date)).method_27692(class_124.field_1068));
                class_25853.method_10862(class_25853.method_10866().method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)class_25854)));
            }
            ChatUtils.sendMsg((class_2561)class_25853);
        }
        return 1;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(NameHistoryCommand.argument("player", PlayerListEntryArgumentType.playerListEntry()).executes(this::lambda$build$0));
    }
}

