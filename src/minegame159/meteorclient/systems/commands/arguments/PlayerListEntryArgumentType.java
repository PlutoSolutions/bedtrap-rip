/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.minecraft.class_2172
 *  net.minecraft.class_2585
 *  net.minecraft.class_640
 */
package minegame159.meteorclient.systems.commands.arguments;

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

public class PlayerListEntryArgumentType
implements ArgumentType<class_640> {
    private static /* synthetic */ Collection<String> EXAMPLES;
    private static final /* synthetic */ DynamicCommandExceptionType NO_SUCH_PLAYER;

    public class_640 parse(StringReader lIIIIllIIIIllI) throws CommandSyntaxException {
        String lIIIIllIIIlIII = lIIIIllIIIIllI.readString();
        class_640 lIIIIllIIIIlll = null;
        for (class_640 lIIIIllIIIlIll : Utils.mc.method_1562().method_2880()) {
            if (!lIIIIllIIIlIll.method_2966().getName().equalsIgnoreCase(lIIIIllIIIlIII)) continue;
            lIIIIllIIIIlll = lIIIIllIIIlIll;
            break;
        }
        if (lIIIIllIIIIlll == null) {
            throw NO_SUCH_PLAYER.create((Object)lIIIIllIIIlIII);
        }
        return lIIIIllIIIIlll;
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> lIIIIlIlllllll, SuggestionsBuilder lIIIIlIllllllI) {
        return class_2172.method_9264(Utils.mc.method_1562().method_2880().stream().map(lIIIIlIlllIlII -> lIIIIlIlllIlII.method_2966().getName()), (SuggestionsBuilder)lIIIIlIllllllI);
    }

    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    static {
        if (Utils.mc.method_1562() != null) {
            EXAMPLES = Utils.mc.method_1562().method_2880().stream().limit(3L).map(lIIIIlIllIllll -> lIIIIlIllIllll.method_2966().getName()).collect(Collectors.toList());
        }
        NO_SUCH_PLAYER = new DynamicCommandExceptionType(lIIIIlIlllIIIl -> new class_2585(String.valueOf(new StringBuilder().append("Player list entry with name ").append(lIIIIlIlllIIIl).append(" doesn't exist."))));
    }

    public PlayerListEntryArgumentType() {
        PlayerListEntryArgumentType lIIIIllIIlIlIl;
    }

    public static class_640 getPlayerListEntry(CommandContext<?> lIIIIllIIlIIIl) {
        return (class_640)lIIIIllIIlIIIl.getArgument("player", class_640.class);
    }

    public static PlayerListEntryArgumentType playerListEntry() {
        return new PlayerListEntryArgumentType();
    }
}

