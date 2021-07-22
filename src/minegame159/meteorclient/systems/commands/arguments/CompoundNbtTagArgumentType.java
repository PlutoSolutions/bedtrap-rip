/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.class_2487
 *  net.minecraft.class_2522
 */
package minegame159.meteorclient.systems.commands.arguments;

import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Arrays;
import java.util.Collection;
import net.minecraft.class_2487;
import net.minecraft.class_2522;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class CompoundNbtTagArgumentType
implements ArgumentType<class_2487> {
    private static final Collection<String> EXAMPLES = Arrays.asList("{foo:bar}", "{foo:[aa, bb],bar:15}");

    public class_2487 parse(StringReader stringReader) throws CommandSyntaxException {
        stringReader.skipWhitespace();
        if (!stringReader.canRead()) {
            throw class_2522.field_11605.createWithContext((ImmutableStringReader)stringReader);
        }
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;
        while (stringReader.canRead()) {
            if (stringReader.peek() == '{') {
                ++n;
                if (false) {
                    return null;
                }
            } else if (stringReader.peek() == '}') {
                --n;
            }
            if (n == 0) break;
            stringBuilder.append(stringReader.read());
        }
        stringReader.expect('}');
        stringBuilder.append('}');
        return class_2522.method_10718((String)String.valueOf(stringBuilder).replace("$", "\u00a7").replace("\u00a7\u00a7", "$"));
    }

    public Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.parse(stringReader);
    }

    public static class_2487 getTag(CommandContext<?> commandContext, String string) {
        return (class_2487)commandContext.getArgument(string, class_2487.class);
    }

    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    public static CompoundNbtTagArgumentType nbtTag() {
        return new CompoundNbtTagArgumentType();
    }
}

