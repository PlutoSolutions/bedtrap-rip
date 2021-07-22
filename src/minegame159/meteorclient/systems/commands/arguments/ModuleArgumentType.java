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
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import net.minecraft.class_2172;
import net.minecraft.class_2585;

public class ModuleArgumentType
implements ArgumentType<Module> {
    private static final /* synthetic */ Collection<String> EXAMPLES;
    private static final /* synthetic */ DynamicCommandExceptionType NO_SUCH_MODULE;

    public static ModuleArgumentType module() {
        return new ModuleArgumentType();
    }

    public Module parse(StringReader llIllIIIllIlll) throws CommandSyntaxException {
        String llIllIIIllIllI = llIllIIIllIlll.readString();
        Module llIllIIIllIlIl = Modules.get().get(llIllIIIllIllI);
        if (llIllIIIllIlIl == null) {
            throw NO_SUCH_MODULE.create((Object)llIllIIIllIllI);
        }
        return llIllIIIllIlIl;
    }

    public static Module getModule(CommandContext<?> llIllIIIllllll, String llIllIIIllllII) {
        return (Module)llIllIIIllllll.getArgument(llIllIIIllllII, Module.class);
    }

    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    public ModuleArgumentType() {
        ModuleArgumentType llIllIIlIIIIll;
    }

    static {
        EXAMPLES = Modules.get().getAll().stream().limit(3L).map(llIllIIIIlllll -> llIllIIIIlllll.name).collect(Collectors.toList());
        NO_SUCH_MODULE = new DynamicCommandExceptionType(llIllIIIlIIIIl -> new class_2585(String.valueOf(new StringBuilder().append("Module with name ").append(llIllIIIlIIIIl).append(" doesn't exist."))));
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> llIllIIIlIllll, SuggestionsBuilder llIllIIIlIlllI) {
        return class_2172.method_9264(Modules.get().getAll().stream().map(llIllIIIlIIlIl -> llIllIIIlIIlIl.name), (SuggestionsBuilder)llIllIIIlIlllI);
    }
}

