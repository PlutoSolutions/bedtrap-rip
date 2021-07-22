/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Streams
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.minecraft.class_2172
 *  net.minecraft.class_2585
 */
package minegame159.meteorclient.systems.commands.arguments;

import com.google.common.collect.Streams;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2172;
import net.minecraft.class_2585;

public class SettingArgumentType
implements ArgumentType<String> {
    private static final /* synthetic */ DynamicCommandExceptionType NO_SUCH_SETTING;

    public SettingArgumentType() {
        SettingArgumentType lllllllllllllllllllIIlllllIIIlIl;
    }

    static {
        NO_SUCH_SETTING = new DynamicCommandExceptionType(lllllllllllllllllllIIllllIIllllI -> new class_2585(String.valueOf(new StringBuilder().append("No such setting '").append(lllllllllllllllllllIIllllIIllllI).append("'."))));
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> lllllllllllllllllllIIllllIlllIIl, SuggestionsBuilder lllllllllllllllllllIIllllIlllIII) {
        Stream<String> lllllllllllllllllllIIllllIlllIlI = Streams.stream(((Module)lllllllllllllllllllIIllllIlllIIl.getArgument((String)"module", Module.class)).settings.iterator()).flatMap(lllllllllllllllllllIIllllIlIIIIl -> Streams.stream(lllllllllllllllllllIIllllIlIIIIl.iterator())).map(lllllllllllllllllllIIllllIlIIlII -> lllllllllllllllllllIIllllIlIIlII.name);
        return class_2172.method_9264(lllllllllllllllllllIIllllIlllIlI, (SuggestionsBuilder)lllllllllllllllllllIIllllIlllIII);
    }

    public String parse(StringReader lllllllllllllllllllIIlllllIIIIIl) throws CommandSyntaxException {
        return lllllllllllllllllllIIlllllIIIIIl.readString();
    }

    public static SettingArgumentType setting() {
        return new SettingArgumentType();
    }

    public static Setting<?> getSetting(CommandContext<?> lllllllllllllllllllIIllllIlIlllI) throws CommandSyntaxException {
        Module lllllllllllllllllllIIllllIllIIIl = (Module)lllllllllllllllllllIIllllIlIlllI.getArgument("module", Module.class);
        String lllllllllllllllllllIIllllIllIIII = (String)lllllllllllllllllllIIllllIlIlllI.getArgument("setting", String.class);
        Setting<?> lllllllllllllllllllIIllllIlIllll = lllllllllllllllllllIIllllIllIIIl.settings.get(lllllllllllllllllllIIllllIllIIII);
        if (lllllllllllllllllllIIllllIlIllll == null) {
            throw NO_SUCH_SETTING.create((Object)lllllllllllllllllllIIllllIllIIII);
        }
        return lllllllllllllllllllIIllllIlIllll;
    }
}

