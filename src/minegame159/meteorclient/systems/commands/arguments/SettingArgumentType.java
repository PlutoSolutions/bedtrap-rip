/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Streams
 *  com.mojang.brigadier.Message
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
import com.mojang.brigadier.Message;
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
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2172;
import net.minecraft.class_2585;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class SettingArgumentType
implements ArgumentType<String> {
    private static final DynamicCommandExceptionType NO_SUCH_SETTING = new DynamicCommandExceptionType(SettingArgumentType::lambda$static$0);

    private static String lambda$listSuggestions$2(Setting setting) {
        return setting.name;
    }

    private static Stream lambda$listSuggestions$1(SettingGroup settingGroup) {
        return Streams.stream(settingGroup.iterator());
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandContext, SuggestionsBuilder suggestionsBuilder) {
        Stream<String> stream = Streams.stream(((Module)commandContext.getArgument((String)"module", Module.class)).settings.iterator()).flatMap(SettingArgumentType::lambda$listSuggestions$1).map(SettingArgumentType::lambda$listSuggestions$2);
        return class_2172.method_9264(stream, (SuggestionsBuilder)suggestionsBuilder);
    }

    public Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.parse(stringReader);
    }

    private static Message lambda$static$0(Object object) {
        return new class_2585(String.valueOf(new StringBuilder().append("No such setting '").append(object).append("'.")));
    }

    public String parse(StringReader stringReader) throws CommandSyntaxException {
        return stringReader.readString();
    }

    public static SettingArgumentType setting() {
        return new SettingArgumentType();
    }

    public static Setting<?> getSetting(CommandContext<?> commandContext) throws CommandSyntaxException {
        Module module = (Module)commandContext.getArgument("module", Module.class);
        String string = (String)commandContext.getArgument("setting", String.class);
        Setting<?> setting = module.settings.get(string);
        if (setting == null) {
            throw NO_SUCH_SETTING.create((Object)string);
        }
        return setting;
    }
}

