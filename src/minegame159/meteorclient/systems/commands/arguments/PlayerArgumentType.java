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
 *  net.minecraft.class_1657
 *  net.minecraft.class_2172
 *  net.minecraft.class_2585
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
import net.minecraft.class_1657;
import net.minecraft.class_2172;
import net.minecraft.class_2585;

public class PlayerArgumentType
implements ArgumentType<class_1657> {
    private static /* synthetic */ Collection<String> EXAMPLES;
    private static final /* synthetic */ DynamicCommandExceptionType NO_SUCH_PLAYER;

    public class_1657 parse(StringReader llllllllllllllllllllllIIIIlIllII) throws CommandSyntaxException {
        String llllllllllllllllllllllIIIIlIlllI = llllllllllllllllllllllIIIIlIllII.readString();
        class_1657 llllllllllllllllllllllIIIIlIllIl = null;
        for (class_1657 llllllllllllllllllllllIIIIllIIIl : Utils.mc.field_1687.method_18456()) {
            if (!llllllllllllllllllllllIIIIllIIIl.method_5820().equalsIgnoreCase(llllllllllllllllllllllIIIIlIlllI)) continue;
            llllllllllllllllllllllIIIIlIllIl = llllllllllllllllllllllIIIIllIIIl;
            break;
        }
        if (llllllllllllllllllllllIIIIlIllIl == null) {
            throw NO_SUCH_PLAYER.create((Object)llllllllllllllllllllllIIIIlIlllI);
        }
        return llllllllllllllllllllllIIIIlIllIl;
    }

    public static class_1657 getPlayer(CommandContext<?> llllllllllllllllllllllIIIIlllIII) {
        return (class_1657)llllllllllllllllllllllIIIIlllIII.getArgument("player", class_1657.class);
    }

    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    public static PlayerArgumentType player() {
        return new PlayerArgumentType();
    }

    static {
        if (Utils.mc.field_1687 != null) {
            EXAMPLES = Utils.mc.field_1687.method_18456().stream().limit(3L).map(class_1657::method_5820).collect(Collectors.toList());
        }
        NO_SUCH_PLAYER = new DynamicCommandExceptionType(llllllllllllllllllllllIIIIIllIll -> new class_2585(String.valueOf(new StringBuilder().append("Player with name ").append(llllllllllllllllllllllIIIIIllIll).append(" doesn't exist."))));
    }

    public PlayerArgumentType() {
        PlayerArgumentType llllllllllllllllllllllIIIIlllIlI;
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> llllllllllllllllllllllIIIIlIIlIl, SuggestionsBuilder llllllllllllllllllllllIIIIlIIlII) {
        return class_2172.method_9264(Utils.mc.field_1687.method_18456().stream().map(class_1657::method_5820), (SuggestionsBuilder)llllllllllllllllllllllIIIIlIIlII);
    }
}

