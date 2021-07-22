/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
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
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import net.minecraft.class_2172;
import net.minecraft.class_2585;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ModuleArgumentType
implements ArgumentType<Module> {
    private static final Collection<String> EXAMPLES = Modules.get().getAll().stream().limit(3L).map(ModuleArgumentType::lambda$static$0).collect(Collectors.toList());
    private static final DynamicCommandExceptionType NO_SUCH_MODULE = new DynamicCommandExceptionType(ModuleArgumentType::lambda$static$1);

    public static ModuleArgumentType module() {
        return new ModuleArgumentType();
    }

    public Module parse(StringReader stringReader) throws CommandSyntaxException {
        String string = stringReader.readString();
        Module module = Modules.get().get(string);
        if (module == null) {
            throw NO_SUCH_MODULE.create((Object)string);
        }
        return module;
    }

    public static Module getModule(CommandContext<?> commandContext, String string) {
        return (Module)commandContext.getArgument(string, Module.class);
    }

    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    private static String lambda$listSuggestions$2(Module module) {
        return module.name;
    }

    private static String lambda$static$0(Module module) {
        return module.name;
    }

    private static Message lambda$static$1(Object object) {
        return new class_2585(String.valueOf(new StringBuilder().append("Module with name ").append(object).append(" doesn't exist.")));
    }

    public Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.parse(stringReader);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandContext, SuggestionsBuilder suggestionsBuilder) {
        return class_2172.method_9264(Modules.get().getAll().stream().map(ModuleArgumentType::lambda$listSuggestions$2), (SuggestionsBuilder)suggestionsBuilder);
    }
}

