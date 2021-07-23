/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.arguments;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2172;
import net.minecraft.class_2585;
import net.minecraft.class_640;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class PlayerListEntryArgumentType
implements ArgumentType<class_640> {
    private static Collection<String> EXAMPLES;
    private static final DynamicCommandExceptionType NO_SUCH_PLAYER;

    public class_640 parse(StringReader stringReader) throws CommandSyntaxException {
        String string = stringReader.readString();
        class_640 class_6402 = null;
        for (class_640 class_6403 : Utils.mc.method_1562().method_2880()) {
            if (!class_6403.method_2966().getName().equalsIgnoreCase(string)) continue;
            class_6402 = class_6403;
            break;
        }
        if (class_6402 == null) {
            throw NO_SUCH_PLAYER.create((Object)string);
        }
        return class_6402;
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandContext, SuggestionsBuilder suggestionsBuilder) {
        return class_2172.method_9264(Utils.mc.method_1562().method_2880().stream().map(PlayerListEntryArgumentType::lambda$listSuggestions$2), (SuggestionsBuilder)suggestionsBuilder);
    }

    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    public Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.parse(stringReader);
    }

    static {
        if (Utils.mc.method_1562() != null) {
            EXAMPLES = Utils.mc.method_1562().method_2880().stream().limit(3L).map(PlayerListEntryArgumentType::lambda$static$0).collect(Collectors.toList());
        }
        NO_SUCH_PLAYER = new DynamicCommandExceptionType(PlayerListEntryArgumentType::lambda$static$1);
    }

    private static String lambda$static$0(class_640 class_6402) {
        return class_6402.method_2966().getName();
    }

    public static class_640 getPlayerListEntry(CommandContext<?> commandContext) {
        return (class_640)commandContext.getArgument("player", class_640.class);
    }

    private static Message lambda$static$1(Object object) {
        return new class_2585(String.valueOf(new StringBuilder().append("Player list entry with name ").append(object).append(" doesn't exist.")));
    }

    private static String lambda$listSuggestions$2(class_640 class_6402) {
        return class_6402.method_2966().getName();
    }

    public static PlayerListEntryArgumentType playerListEntry() {
        return new PlayerListEntryArgumentType();
    }
}

