/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.minecraft.class_2172
 *  net.minecraft.class_2585
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.profiles.Profile;
import minegame159.meteorclient.systems.profiles.Profiles;
import net.minecraft.class_2172;
import net.minecraft.class_2585;

public class ProfilesCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllIlllIlllIllIIIl) {
        ProfilesCommand lllllllllllllllllIlllIlllIllIlII;
        lllllllllllllllllIlllIlllIllIIIl.then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)ProfilesCommand.argument("profile", ProfileArgumentType.profile()).then(ProfilesCommand.literal("load").executes(lllllllllllllllllIlllIlllIIllIlI -> {
            Profile lllllllllllllllllIlllIlllIIllIIl = ProfileArgumentType.getProfile(lllllllllllllllllIlllIlllIIllIlI, "profile");
            if (lllllllllllllllllIlllIlllIIllIIl != null) {
                ProfilesCommand lllllllllllllllllIlllIlllIIllIII;
                lllllllllllllllllIlllIlllIIllIIl.load();
                lllllllllllllllllIlllIlllIIllIII.info("Loaded profile (highlight)%s(default).", lllllllllllllllllIlllIlllIIllIIl.name);
            }
            return 1;
        }))).then(ProfilesCommand.literal("save").executes(lllllllllllllllllIlllIlllIlIIIII -> {
            Profile lllllllllllllllllIlllIlllIlIIIlI = ProfileArgumentType.getProfile(lllllllllllllllllIlllIlllIlIIIII, "profile");
            if (lllllllllllllllllIlllIlllIlIIIlI != null) {
                ProfilesCommand lllllllllllllllllIlllIlllIlIIIIl;
                lllllllllllllllllIlllIlllIlIIIlI.save();
                lllllllllllllllllIlllIlllIlIIIIl.info("Saved profile (highlight)%s(default).", lllllllllllllllllIlllIlllIlIIIlI.name);
            }
            return 1;
        }))).then(ProfilesCommand.literal("delete").executes(lllllllllllllllllIlllIlllIlIllII -> {
            Profile lllllllllllllllllIlllIlllIlIlIll = ProfileArgumentType.getProfile(lllllllllllllllllIlllIlllIlIllII, "profile");
            if (lllllllllllllllllIlllIlllIlIlIll != null) {
                ProfilesCommand lllllllllllllllllIlllIlllIlIlIlI;
                Profiles.get().remove(lllllllllllllllllIlllIlllIlIlIll);
                lllllllllllllllllIlllIlllIlIlIlI.info("Deleted profile (highlight)%s(default).", lllllllllllllllllIlllIlllIlIlIll.name);
            }
            return 1;
        })));
    }

    public ProfilesCommand() {
        super("profiles", "Loads and saves profiles.", new String[0]);
        ProfilesCommand lllllllllllllllllIlllIlllIllIlll;
    }

    public static class ProfileArgumentType
    implements ArgumentType<String> {
        private static final /* synthetic */ DynamicCommandExceptionType NO_SUCH_PROFILE;

        public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> lllllllllllllllllllllIIlllIllIII, SuggestionsBuilder lllllllllllllllllllllIIlllIlIlll) {
            ProfileArgumentType lllllllllllllllllllllIIlllIlIllI;
            return class_2172.method_9265(lllllllllllllllllllllIIlllIlIllI.getExamples(), (SuggestionsBuilder)lllllllllllllllllllllIIlllIlIlll);
        }

        public static Profile getProfile(CommandContext<?> lllllllllllllllllllllIIllllIIllI, String lllllllllllllllllllllIIllllIIlIl) {
            return Profiles.get().get((String)lllllllllllllllllllllIIllllIIllI.getArgument(lllllllllllllllllllllIIllllIIlIl, String.class));
        }

        public Collection<String> getExamples() {
            ArrayList<String> lllllllllllllllllllllIIlllIIllll = new ArrayList<String>();
            for (Profile lllllllllllllllllllllIIlllIlIIIl : Profiles.get()) {
                lllllllllllllllllllllIIlllIIllll.add(lllllllllllllllllllllIIlllIlIIIl.name);
            }
            return lllllllllllllllllllllIIlllIIllll;
        }

        public static ProfileArgumentType profile() {
            return new ProfileArgumentType();
        }

        public String parse(StringReader lllllllllllllllllllllIIlllIlllIl) throws CommandSyntaxException {
            String lllllllllllllllllllllIIlllIllllI = lllllllllllllllllllllIIlllIlllIl.readString();
            if (Profiles.get().get(lllllllllllllllllllllIIlllIllllI) == null) {
                throw NO_SUCH_PROFILE.create((Object)lllllllllllllllllllllIIlllIllllI);
            }
            return lllllllllllllllllllllIIlllIllllI;
        }

        static {
            NO_SUCH_PROFILE = new DynamicCommandExceptionType(lllllllllllllllllllllIIlllIIIlII -> new class_2585(String.valueOf(new StringBuilder().append("Profile with name ").append(lllllllllllllllllllllIIlllIIIlII).append(" doesn't exist."))));
        }

        public ProfileArgumentType() {
            ProfileArgumentType lllllllllllllllllllllIIllllIlIlI;
        }
    }
}

