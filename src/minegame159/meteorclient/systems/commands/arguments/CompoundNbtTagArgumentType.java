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

public class CompoundNbtTagArgumentType
implements ArgumentType<class_2487> {
    private static final /* synthetic */ Collection<String> EXAMPLES;

    public class_2487 parse(StringReader lllIlIIIIIIIllI) throws CommandSyntaxException {
        lllIlIIIIIIIllI.skipWhitespace();
        if (!lllIlIIIIIIIllI.canRead()) {
            throw class_2522.field_11605.createWithContext((ImmutableStringReader)lllIlIIIIIIIllI);
        }
        StringBuilder lllIlIIIIIIIlIl = new StringBuilder();
        int lllIlIIIIIIIlII = 0;
        while (lllIlIIIIIIIllI.canRead()) {
            if (lllIlIIIIIIIllI.peek() == '{') {
                ++lllIlIIIIIIIlII;
            } else if (lllIlIIIIIIIllI.peek() == '}') {
                --lllIlIIIIIIIlII;
            }
            if (lllIlIIIIIIIlII == 0) break;
            lllIlIIIIIIIlIl.append(lllIlIIIIIIIllI.read());
        }
        lllIlIIIIIIIllI.expect('}');
        lllIlIIIIIIIlIl.append('}');
        return class_2522.method_10718((String)String.valueOf(lllIlIIIIIIIlIl).replace("$", "\u00a7").replace("\u00a7\u00a7", "$"));
    }

    static {
        EXAMPLES = Arrays.asList("{foo:bar}", "{foo:[aa, bb],bar:15}");
    }

    public static class_2487 getTag(CommandContext<?> lllIIlllllllllI, String lllIIlllllllIll) {
        return (class_2487)lllIIlllllllllI.getArgument(lllIIlllllllIll, class_2487.class);
    }

    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    public CompoundNbtTagArgumentType() {
        CompoundNbtTagArgumentType lllIlIIIIIIlIll;
    }

    public static CompoundNbtTagArgumentType nbtTag() {
        return new CompoundNbtTagArgumentType();
    }
}

