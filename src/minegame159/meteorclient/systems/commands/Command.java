/*
 * Decompiled with CFR 0.151.
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
    private final String name;
    private final String title;
    protected static class_310 mc;
    private final List<String> aliases = new ArrayList<String>();
    private final String description;

    public Command(String string, String string2, String ... stringArray) {
        this.name = string;
        this.title = Utils.nameToTitle(string);
        this.description = string2;
        Collections.addAll(this.aliases, stringArray);
        mc = class_310.method_1551();
    }

    public void register(CommandDispatcher<class_2172> commandDispatcher, String string) {
        LiteralArgumentBuilder literalArgumentBuilder = LiteralArgumentBuilder.literal((String)string);
        this.build((LiteralArgumentBuilder<class_2172>)literalArgumentBuilder);
        commandDispatcher.register(literalArgumentBuilder);
    }

    public void info(class_2561 class_25612) {
        ChatUtils.sendMsg(this.title, class_25612);
    }

    public List<String> getAliases() {
        return this.aliases;
    }

    public String toString(String ... stringArray) {
        StringBuilder stringBuilder = new StringBuilder(this.toString());
        for (String string : stringArray) {
            stringBuilder.append(' ').append(string);
            if (true > false) continue;
            return null;
        }
        return String.valueOf(stringBuilder);
    }

    public void error(String string, Object ... objectArray) {
        ChatUtils.error(this.title, string, objectArray);
    }

    public void warning(String string, Object ... objectArray) {
        ChatUtils.warning(this.title, string, objectArray);
    }

    public final void registerTo(CommandDispatcher<class_2172> commandDispatcher) {
        this.register(commandDispatcher, this.name);
        for (String string : this.aliases) {
            this.register(commandDispatcher, string);
        }
    }

    public abstract void build(LiteralArgumentBuilder<class_2172> var1);

    public void info(String string, Object ... objectArray) {
        ChatUtils.info(this.title, string, objectArray);
    }

    protected static <T> RequiredArgumentBuilder<class_2172, T> argument(String string, ArgumentType<T> argumentType) {
        return RequiredArgumentBuilder.argument((String)string, argumentType);
    }

    protected static LiteralArgumentBuilder<class_2172> literal(String string) {
        return LiteralArgumentBuilder.literal((String)string);
    }

    public String toString() {
        return String.valueOf(new StringBuilder().append(Config.get().prefix).append(this.name));
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}

