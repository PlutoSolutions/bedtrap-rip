/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.minecraft.class_2172
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.systems.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.systems.commands.arguments.SettingArgumentType;
import net.minecraft.class_2172;
import net.minecraft.class_2960;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class SettingValueArgumentType
implements ArgumentType<String> {
    public static SettingValueArgumentType value() {
        return new SettingValueArgumentType();
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandContext, SuggestionsBuilder suggestionsBuilder) {
        Setting<?> setting;
        try {
            setting = SettingArgumentType.getSetting(commandContext);
        }
        catch (CommandSyntaxException commandSyntaxException) {
            return null;
        }
        Iterable<class_2960> iterable = setting.getIdentifierSuggestions();
        if (iterable != null) {
            return class_2172.method_9270(iterable, (SuggestionsBuilder)suggestionsBuilder);
        }
        return class_2172.method_9265(setting.getSuggestions(), (SuggestionsBuilder)suggestionsBuilder);
    }

    public String parse(StringReader stringReader) throws CommandSyntaxException {
        String string = stringReader.getRemaining();
        stringReader.setCursor(stringReader.getTotalLength());
        return string;
    }

    public Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.parse(stringReader);
    }
}

