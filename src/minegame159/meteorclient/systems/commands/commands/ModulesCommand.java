/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_124
 *  net.minecraft.class_2172
 *  net.minecraft.class_2554
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_2585
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.modules.Category;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_2554;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2585;

public class ModulesCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> llllllllllllllllIllIlIlIlllIlIll) {
        ModulesCommand llllllllllllllllIllIlIlIlllIllII;
        llllllllllllllllIllIlIlIlllIlIll.executes(llllllllllllllllIllIlIlIllIlllII -> {
            ModulesCommand llllllllllllllllIllIlIlIllIllIll;
            ChatUtils.info("--- Modules ((highlight)%d(default)) ---", Modules.get().getCount());
            Modules.loopCategories().forEach(llllllllllllllllIllIlIlIllIlIllI -> {
                ModulesCommand llllllllllllllllIllIlIlIllIlIlll;
                class_2585 llllllllllllllllIllIlIlIllIlIlIl = new class_2585("");
                Modules.get().getGroup((Category)llllllllllllllllIllIlIlIllIlIllI).forEach(arg_0 -> llllllllllllllllIllIlIlIllIlIlll.lambda$build$0((class_2554)llllllllllllllllIllIlIlIllIlIlIl, arg_0));
                ChatUtils.sendMsg(llllllllllllllllIllIlIlIllIlIllI.name, (class_2561)llllllllllllllllIllIlIlIllIlIlIl);
            });
            return 1;
        });
    }

    public ModulesCommand() {
        super("modules", "Displays a list of all modules.", "features");
        ModulesCommand llllllllllllllllIllIlIlIlllIllll;
    }

    private /* synthetic */ void lambda$build$0(class_2554 llllllllllllllllIllIlIlIllIIlIlI, Module llllllllllllllllIllIlIlIllIIllII) {
        ModulesCommand llllllllllllllllIllIlIlIllIIlIll;
        llllllllllllllllIllIlIlIllIIlIlI.method_10852((class_2561)llllllllllllllllIllIlIlIllIIlIll.getModuleText(llllllllllllllllIllIlIlIllIIllII));
    }

    private class_2554 getModuleText(Module llllllllllllllllIllIlIlIlllIIlII) {
        class_2585 llllllllllllllllIllIlIlIlllIIIll = new class_2585("");
        llllllllllllllllIllIlIlIlllIIIll.method_10852((class_2561)new class_2585(llllllllllllllllIllIlIlIlllIIlII.title).method_27695(new class_124[]{class_124.field_1078, class_124.field_1067})).method_27693("\n");
        llllllllllllllllIllIlIlIlllIIIll.method_10852((class_2561)new class_2585(llllllllllllllllIllIlIlIlllIIlII.name).method_27692(class_124.field_1080)).method_27693("\n\n");
        llllllllllllllllIllIlIlIlllIIIll.method_10852((class_2561)new class_2585(llllllllllllllllIllIlIlIlllIIlII.description).method_27692(class_124.field_1068));
        class_2585 llllllllllllllllIllIlIlIlllIIIlI = new class_2585(llllllllllllllllIllIlIlIlllIIlII.title);
        if (llllllllllllllllIllIlIlIlllIIlII != Modules.get().getList().get(Modules.get().getList().size() - 1)) {
            llllllllllllllllIllIlIlIlllIIIlI.method_10852((class_2561)new class_2585(", ").method_27692(class_124.field_1080));
        }
        llllllllllllllllIllIlIlIlllIIIlI.method_10862(llllllllllllllllIllIlIlIlllIIIlI.method_10866().method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)llllllllllllllllIllIlIlIlllIIIll)));
        return llllllllllllllllIllIlIlIlllIIIlI;
    }
}

