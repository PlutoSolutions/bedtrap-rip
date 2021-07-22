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

public class SettingValueArgumentType
implements ArgumentType<String> {
    public static SettingValueArgumentType value() {
        return new SettingValueArgumentType();
    }

    /*
     * WARNING - void declaration
     */
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> lIlIIIlIIIIlIl, SuggestionsBuilder lIlIIIlIIIIlII) {
        void lIlIIIlIIIIIll;
        try {
            Setting<?> lIlIIIlIIIlIII = SettingArgumentType.getSetting(lIlIIIlIIIIlIl);
        }
        catch (CommandSyntaxException lIlIIIlIIIIlll) {
            return null;
        }
        Iterable<class_2960> lIlIIIlIIIIIlI = lIlIIIlIIIIIll.getIdentifierSuggestions();
        if (lIlIIIlIIIIIlI != null) {
            return class_2172.method_9270(lIlIIIlIIIIIlI, (SuggestionsBuilder)lIlIIIlIIIIlII);
        }
        return class_2172.method_9265(lIlIIIlIIIIIll.getSuggestions(), (SuggestionsBuilder)lIlIIIlIIIIlII);
    }

    public String parse(StringReader lIlIIIlIIlIIII) throws CommandSyntaxException {
        String lIlIIIlIIIllll = lIlIIIlIIlIIII.getRemaining();
        lIlIIIlIIlIIII.setCursor(lIlIIIlIIlIIII.getTotalLength());
        return lIlIIIlIIIllll;
    }

    public SettingValueArgumentType() {
        SettingValueArgumentType lIlIIIlIIlIlIl;
    }
}

