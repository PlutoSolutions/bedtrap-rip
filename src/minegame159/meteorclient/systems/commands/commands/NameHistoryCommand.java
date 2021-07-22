/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.TypeToken
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
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
        NameHistoryCommand lllIlIllIllllIl;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllIlIllIlllIII) {
        NameHistoryCommand lllIlIllIllIlll;
        lllIlIllIlllIII.then(NameHistoryCommand.argument("player", PlayerListEntryArgumentType.playerListEntry()).executes(lllIlIllIIllIll -> {
            NameHistoryCommand lllIlIllIIlllII;
            class_640 lllIlIllIlIIIIl = PlayerListEntryArgumentType.getPlayerListEntry(lllIlIllIIllIll);
            Type lllIlIllIlIIIII = new TypeToken<List<NameHistoryObject>>(){
                {
                    1 llllllllllllllllIlllIlIlllIlIIII;
                }
            }.getType();
            List lllIlIllIIlllll = (List)HttpUtils.get(String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(lllIlIllIlIIIIl.method_2966().getId().toString().replace("-", "")).append("/names")), lllIlIllIlIIIII);
            if (lllIlIllIIlllll == null || lllIlIllIIlllll.isEmpty()) {
                lllIlIllIIlllII.error("There was an error fetching that users name history.", new Object[0]);
                return 1;
            }
            class_2585 lllIlIllIIllllI = new class_2585(lllIlIllIlIIIIl.method_2966().getName());
            lllIlIllIIllllI.method_10852((class_2561)new class_2585("'s"));
            Color lllIlIllIIlllIl = TextUtils.getMostPopularColor(lllIlIllIlIIIIl.method_2971());
            lllIlIllIIllllI.method_10862(lllIlIllIIllllI.method_10866().method_27703(new class_5251(lllIlIllIIlllIl.getPacked())).method_10958(new class_2558(class_2558.class_2559.field_11749, String.valueOf(new StringBuilder().append("https://namemc.com/search?q=").append(lllIlIllIlIIIIl.method_2966().getName())))).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("View on NameMC").method_27692(class_124.field_1054).method_27692(class_124.field_1056))));
            lllIlIllIIlllII.info((class_2561)lllIlIllIIllllI.method_10852((class_2561)new class_2585(" Username History:").method_27692(class_124.field_1080)));
            for (NameHistoryObject lllIlIllIlIIlII : lllIlIllIIlllll) {
                class_2585 lllIlIllIlIIlIl = new class_2585(lllIlIllIlIIlII.name);
                lllIlIllIlIIlIl.method_27692(class_124.field_1075);
                if (lllIlIllIlIIlII.changedToAt != 0L) {
                    class_2585 lllIlIllIlIlIII = new class_2585("Changed at: ");
                    lllIlIllIlIlIII.method_27692(class_124.field_1080);
                    Date lllIlIllIlIIlll = new Date(lllIlIllIlIIlII.changedToAt);
                    SimpleDateFormat lllIlIllIlIIllI = new SimpleDateFormat("hh:mm:ss, dd/MM/yyyy");
                    lllIlIllIlIlIII.method_10852((class_2561)new class_2585(lllIlIllIlIIllI.format(lllIlIllIlIIlll)).method_27692(class_124.field_1068));
                    lllIlIllIlIIlIl.method_10862(lllIlIllIlIIlIl.method_10866().method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)lllIlIllIlIlIII)));
                }
                ChatUtils.sendMsg((class_2561)lllIlIllIlIIlIl);
            }
            return 1;
        }));
    }
}

