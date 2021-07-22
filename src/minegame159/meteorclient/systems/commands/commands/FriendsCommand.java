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
 *  net.minecraft.class_310
 *  net.minecraft.class_640
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
import net.minecraft.class_310;
import net.minecraft.class_640;

public class FriendsCommand
extends Command {
    private static void lambda$build$2(Friend friend) {
        ChatUtils.info(String.valueOf(new StringBuilder().append("(highlight)").append(friend.name)), new Object[0]);
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(FriendsCommand.literal("add").then(FriendsCommand.argument("friend", FriendArgumentType.friend()).executes(this::lambda$build$0)));
        literalArgumentBuilder.then(FriendsCommand.literal("remove").then(FriendsCommand.argument("friend", FriendArgumentType.friend()).executes(this::lambda$build$1)));
        literalArgumentBuilder.then(FriendsCommand.literal("list").executes(this::lambda$build$3));
    }

    static class_310 access$000() {
        return mc;
    }

    private int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        Friend friend = FriendArgumentType.getFriend(commandContext, "friend");
        if (Friends.get().add(friend)) {
            this.info("Added (highlight)%s (default)to friends.", friend.name);
        } else {
            this.error("That person is already your friend.", new Object[0]);
        }
        return 1;
    }

    private int lambda$build$3(CommandContext commandContext) throws CommandSyntaxException {
        this.info("--- Friends ((highlight)%s(default)) ---", Friends.get().count());
        Friends.get().forEach(FriendsCommand::lambda$build$2);
        return 1;
    }

    public FriendsCommand() {
        super("friends", "Manages friends.", new String[0]);
    }

    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        Friend friend = FriendArgumentType.getFriend(commandContext, "friend");
        if (Friends.get().remove(friend)) {
            this.info("Removed (highlight)%s (default)from friends.", friend.name);
        } else {
            this.error("That person is not your friend.", new Object[0]);
        }
        return 1;
    }

    /*
     * Duplicate member names - consider using --renamedupmembers true
     */
    private static class FriendArgumentType
    implements ArgumentType<Friend> {
        public Object parse(StringReader stringReader) throws CommandSyntaxException {
            return this.parse(stringReader);
        }

        public static Friend getFriend(CommandContext<?> commandContext, String string) {
            return (Friend)commandContext.getArgument(string, Friend.class);
        }

        public static FriendArgumentType friend() {
            return new FriendArgumentType();
        }

        public Friend parse(StringReader stringReader) throws CommandSyntaxException {
            return new Friend(stringReader.readString());
        }

        public Collection<String> getExamples() {
            return Arrays.asList("seasnail8169", "MineGame159");
        }

        private FriendArgumentType() {
        }

        private static String lambda$listSuggestions$0(class_640 class_6402) {
            return class_6402.method_2966().getName();
        }

        public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandContext, SuggestionsBuilder suggestionsBuilder) {
            return class_2172.method_9265((Iterable)FriendsCommand.access$000().method_1562().method_2880().stream().map(FriendArgumentType::lambda$listSuggestions$0).collect(Collectors.toList()), (SuggestionsBuilder)suggestionsBuilder);
        }
    }
}

