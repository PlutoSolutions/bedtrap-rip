/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
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
        BindsCommand llllllllllllllllllIIIlIlIlIIIlll;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> llllllllllllllllllIIIlIlIlIIIIlI) {
        BindsCommand llllllllllllllllllIIIlIlIlIIIIll;
        llllllllllllllllllIIIlIlIlIIIIlI.executes(llllllllllllllllllIIIlIlIIlIlIlI -> {
            List llllllllllllllllllIIIlIlIIlIlIIl = Modules.get().getAll().stream().filter(llllllllllllllllllIIIlIlIIIllllI -> llllllllllllllllllIIIlIlIIIllllI.keybind.isSet()).collect(Collectors.toList());
            ChatUtils.info("--- Bound Modules ((highlight)%d(default)) ---", llllllllllllllllllIIIlIlIIlIlIIl.size());
            for (Module llllllllllllllllllIIIlIlIIlIllII : llllllllllllllllllIIIlIlIIlIlIIl) {
                BindsCommand llllllllllllllllllIIIlIlIIlIlIll;
                class_2568 llllllllllllllllllIIIlIlIIllIIII = new class_2568(class_2568.class_5247.field_24342, (Object)llllllllllllllllllIIIlIlIIlIlIll.getTooltip(llllllllllllllllllIIIlIlIIlIllII));
                class_5250 llllllllllllllllllIIIlIlIIlIllll = new class_2585(llllllllllllllllllIIIlIlIIlIllII.title).method_27692(class_124.field_1068);
                llllllllllllllllllIIIlIlIIlIllll.method_10862(llllllllllllllllllIIIlIlIIlIllll.method_10866().method_10949(llllllllllllllllllIIIlIlIIllIIII));
                class_2585 llllllllllllllllllIIIlIlIIlIlllI = new class_2585(" - ");
                llllllllllllllllllIIIlIlIIlIlllI.method_10862(llllllllllllllllllIIIlIlIIlIlllI.method_10866().method_10949(llllllllllllllllllIIIlIlIIllIIII));
                llllllllllllllllllIIIlIlIIlIllll.method_10852((class_2561)llllllllllllllllllIIIlIlIIlIlllI.method_27692(class_124.field_1080));
                class_2585 llllllllllllllllllIIIlIlIIlIllIl = new class_2585(llllllllllllllllllIIIlIlIIlIllII.keybind.toString());
                llllllllllllllllllIIIlIlIIlIllIl.method_10862(llllllllllllllllllIIIlIlIIlIllIl.method_10866().method_10949(llllllllllllllllllIIIlIlIIllIIII));
                llllllllllllllllllIIIlIlIIlIllll.method_10852((class_2561)llllllllllllllllllIIIlIlIIlIllIl.method_27692(class_124.field_1080));
                ChatUtils.sendMsg((class_2561)llllllllllllllllllIIIlIlIIlIllll);
            }
            return 1;
        });
    }

    private class_5250 getTooltip(Module llllllllllllllllllIIIlIlIIllllII) {
        class_5250 llllllllllllllllllIIIlIlIIlllIll = new class_2585(Utils.nameToTitle(llllllllllllllllllIIIlIlIIllllII.title)).method_27695(new class_124[]{class_124.field_1078, class_124.field_1067}).method_27693("\n\n");
        llllllllllllllllllIIIlIlIIlllIll.method_10852((class_2561)new class_2585(llllllllllllllllllIIIlIlIIllllII.description).method_27692(class_124.field_1068));
        return llllllllllllllllllIIIlIlIIlllIll;
    }
}

