/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  net.minecraft.class_2172
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 */
package minegame159.meteorclient.systems.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_2172;
import net.minecraft.class_2561;
import net.minecraft.class_310;

public abstract class Command {
    private final /* synthetic */ String name;
    private final /* synthetic */ String title;
    protected static /* synthetic */ class_310 mc;
    private final /* synthetic */ List<String> aliases;
    private final /* synthetic */ String description;

    public Command(String llIlllllllIlIl, String llIlllllllIlII, String ... llIlllllllIIll) {
        Command llIlllllllIIlI;
        llIlllllllIIlI.aliases = new ArrayList<String>();
        llIlllllllIIlI.name = llIlllllllIlIl;
        llIlllllllIIlI.title = Utils.nameToTitle(llIlllllllIlIl);
        llIlllllllIIlI.description = llIlllllllIlII;
        Collections.addAll(llIlllllllIIlI.aliases, llIlllllllIIll);
        mc = class_310.method_1551();
    }

    public void register(CommandDispatcher<class_2172> llIlllllIlIlIl, String llIlllllIlIIII) {
        Command llIlllllIlIllI;
        LiteralArgumentBuilder llIlllllIlIIll = LiteralArgumentBuilder.literal((String)llIlllllIlIIII);
        llIlllllIlIllI.build((LiteralArgumentBuilder<class_2172>)llIlllllIlIIll);
        llIlllllIlIlIl.register(llIlllllIlIIll);
    }

    public void info(class_2561 llIllllIlIllIl) {
        Command llIllllIlIllII;
        ChatUtils.sendMsg(llIllllIlIllII.title, llIllllIlIllIl);
    }

    public List<String> getAliases() {
        Command llIlllllIIIlll;
        return llIlllllIIIlll.aliases;
    }

    public String toString(String ... llIllllIlllIIl) {
        Command llIllllIllIlll;
        StringBuilder llIllllIlllIII = new StringBuilder(llIllllIllIlll.toString());
        for (String llIllllIlllIll : llIllllIlllIIl) {
            llIllllIlllIII.append(' ').append(llIllllIlllIll);
        }
        return String.valueOf(llIllllIlllIII);
    }

    public void error(String llIllllIIlIIIl, Object ... llIllllIIlIIII) {
        Command llIllllIIlIIlI;
        ChatUtils.error(llIllllIIlIIlI.title, llIllllIIlIIIl, llIllllIIlIIII);
    }

    public void warning(String llIllllIIlllIl, Object ... llIllllIIllIIl) {
        Command llIllllIIllllI;
        ChatUtils.warning(llIllllIIllllI.title, llIllllIIlllIl, llIllllIIllIIl);
    }

    public final void registerTo(CommandDispatcher<class_2172> llIlllllIlllll) {
        Command llIllllllIIIII;
        llIllllllIIIII.register(llIlllllIlllll, llIllllllIIIII.name);
        for (String llIllllllIIIIl : llIllllllIIIII.aliases) {
            llIllllllIIIII.register(llIlllllIlllll, llIllllllIIIIl);
        }
    }

    public abstract void build(LiteralArgumentBuilder<class_2172> var1);

    public void info(String llIllllIlIIIll, Object ... llIllllIlIIlIl) {
        Command llIllllIlIIlll;
        ChatUtils.info(llIllllIlIIlll.title, llIllllIlIIIll, llIllllIlIIlIl);
    }

    protected static <T> RequiredArgumentBuilder<class_2172, T> argument(String llIllllllIllII, ArgumentType<T> llIllllllIlIIl) {
        return RequiredArgumentBuilder.argument((String)llIllllllIllII, llIllllllIlIIl);
    }

    protected static LiteralArgumentBuilder<class_2172> literal(String llIllllllIIlll) {
        return LiteralArgumentBuilder.literal((String)llIllllllIIlll);
    }

    public String toString() {
        Command llIlllllIIIIll;
        return String.valueOf(new StringBuilder().append(Config.get().prefix).append(llIlllllIIIIll.name));
    }

    public String getName() {
        Command llIlllllIIllIl;
        return llIlllllIIllIl.name;
    }

    public String getDescription() {
        Command llIlllllIIlIlI;
        return llIlllllIIlIlI.description;
    }
}

