/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.friends.Friend;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_2172;

public class FriendsCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> lIlIlIlIIllllII) {
        FriendsCommand lIlIlIlIIllllIl;
        lIlIlIlIIllllII.then(FriendsCommand.literal("add").then(FriendsCommand.argument("friend", FriendArgumentType.friend()).executes(lIlIlIlIIlIIlIl -> {
            FriendsCommand lIlIlIlIIlIIIll;
            Friend lIlIlIlIIlIIlII = FriendArgumentType.getFriend(lIlIlIlIIlIIlIl, "friend");
            if (Friends.get().add(lIlIlIlIIlIIlII)) {
                lIlIlIlIIlIIIll.info("Added (highlight)%s (default)to friends.", lIlIlIlIIlIIlII.name);
            } else {
                lIlIlIlIIlIIIll.error("That person is already your friend.", new Object[0]);
            }
            return 1;
        })));
        lIlIlIlIIllllII.then(FriendsCommand.literal("remove").then(FriendsCommand.argument("friend", FriendArgumentType.friend()).executes(lIlIlIlIIlIlIll -> {
            FriendsCommand lIlIlIlIIlIllll;
            Friend lIlIlIlIIlIllIl = FriendArgumentType.getFriend(lIlIlIlIIlIlIll, "friend");
            if (Friends.get().remove(lIlIlIlIIlIllIl)) {
                lIlIlIlIIlIllll.info("Removed (highlight)%s (default)from friends.", lIlIlIlIIlIllIl.name);
            } else {
                lIlIlIlIIlIllll.error("That person is not your friend.", new Object[0]);
            }
            return 1;
        })));
        lIlIlIlIIllllII.then(FriendsCommand.literal("list").executes(lIlIlIlIIllIlll -> {
            FriendsCommand lIlIlIlIIlllIII;
            lIlIlIlIIlllIII.info("--- Friends ((highlight)%s(default)) ---", Friends.get().count());
            Friends.get().forEach(lIlIlIlIIllIIll -> ChatUtils.info(String.valueOf(new StringBuilder().append("(highlight)").append(lIlIlIlIIllIIll.name)), new Object[0]));
            return 1;
        }));
    }

    public FriendsCommand() {
        super("friends", "Manages friends.", new String[0]);
        FriendsCommand lIlIlIlIlIIIIII;
    }

    private static class FriendArgumentType
    implements ArgumentType<Friend> {
        public static Friend getFriend(CommandContext<?> lllllllllllllllllIIlIllllllIIlIl, String lllllllllllllllllIIlIllllllIIlll) {
            return (Friend)lllllllllllllllllIIlIllllllIIlIl.getArgument(lllllllllllllllllIIlIllllllIIlll, Friend.class);
        }

        public static FriendArgumentType friend() {
            return new FriendArgumentType();
        }

        public Friend parse(StringReader lllllllllllllllllIIlIlllllllIIlI) throws CommandSyntaxException {
            return new Friend(lllllllllllllllllIIlIlllllllIIlI.readString());
        }

        public Collection<String> getExamples() {
            return Arrays.asList("seasnail8169", "MineGame159");
        }

        private FriendArgumentType() {
            FriendArgumentType lllllllllllllllllIIlIlllllllIlIl;
        }

        public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> lllllllllllllllllIIlIllllllIIIIl, SuggestionsBuilder lllllllllllllllllIIlIlllllIlllll) {
            return class_2172.method_9265((Iterable)mc.method_1562().method_2880().stream().map(lllllllllllllllllIIlIlllllIIllIl -> lllllllllllllllllIIlIlllllIIllIl.method_2966().getName()).collect(Collectors.toList()), (SuggestionsBuilder)lllllllllllllllllIIlIlllllIlllll);
        }
    }
}

