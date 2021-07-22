/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.class_2172
 *  net.minecraft.class_310
 *  net.minecraft.class_637
 */
package minegame159.meteorclient.systems.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.commands.BaritoneCommand;
import minegame159.meteorclient.systems.commands.commands.BindsCommand;
import minegame159.meteorclient.systems.commands.commands.ClearChatCommand;
import minegame159.meteorclient.systems.commands.commands.CommandsCommand;
import minegame159.meteorclient.systems.commands.commands.DamageCommand;
import minegame159.meteorclient.systems.commands.commands.DismountCommand;
import minegame159.meteorclient.systems.commands.commands.DropCommand;
import minegame159.meteorclient.systems.commands.commands.EnchantCommand;
import minegame159.meteorclient.systems.commands.commands.FakePlayerCommand;
import minegame159.meteorclient.systems.commands.commands.FriendsCommand;
import minegame159.meteorclient.systems.commands.commands.GamemodeCommand;
import minegame159.meteorclient.systems.commands.commands.GiveCommand;
import minegame159.meteorclient.systems.commands.commands.HClipCommand;
import minegame159.meteorclient.systems.commands.commands.InventoryCommand;
import minegame159.meteorclient.systems.commands.commands.LocateCommand;
import minegame159.meteorclient.systems.commands.commands.ModulesCommand;
import minegame159.meteorclient.systems.commands.commands.NameHistoryCommand;
import minegame159.meteorclient.systems.commands.commands.NbtCommand;
import minegame159.meteorclient.systems.commands.commands.NotebotCommand;
import minegame159.meteorclient.systems.commands.commands.PanicCommand;
import minegame159.meteorclient.systems.commands.commands.PeekCommand;
import minegame159.meteorclient.systems.commands.commands.ProfilesCommand;
import minegame159.meteorclient.systems.commands.commands.ReloadCommand;
import minegame159.meteorclient.systems.commands.commands.ResetCommand;
import minegame159.meteorclient.systems.commands.commands.SaveMapCommand;
import minegame159.meteorclient.systems.commands.commands.SayCommand;
import minegame159.meteorclient.systems.commands.commands.ServerCommand;
import minegame159.meteorclient.systems.commands.commands.SettingCommand;
import minegame159.meteorclient.systems.commands.commands.SpectateCommand;
import minegame159.meteorclient.systems.commands.commands.SwarmCommand;
import minegame159.meteorclient.systems.commands.commands.ToggleCommand;
import minegame159.meteorclient.systems.commands.commands.VClipCommand;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2172;
import net.minecraft.class_310;
import net.minecraft.class_637;

public class Commands
extends System<Commands> {
    private final Map<Class<? extends Command>, Command> commandInstances;
    private final class_2172 COMMAND_SOURCE;
    private final List<Command> commands;
    private final CommandDispatcher<class_2172> DISPATCHER = new CommandDispatcher();

    public void dispatch(String string, class_2172 class_21722) throws CommandSyntaxException {
        ParseResults parseResults = this.DISPATCHER.parse(string, (Object)class_21722);
        this.DISPATCHER.execute(parseResults);
    }

    public class_2172 getCommandSource() {
        return this.COMMAND_SOURCE;
    }

    public <T extends Command> T get(Class<T> class_) {
        return (T)this.commandInstances.get(class_);
    }

    public CommandDispatcher<class_2172> getDispatcher() {
        return this.DISPATCHER;
    }

    public List<Command> getAll() {
        return this.commands;
    }

    private static boolean lambda$add$1(Command command, Command command2) {
        return command2.getName().equals(command.getName());
    }

    private static boolean lambda$add$0(Command command, Command command2) {
        return command2.getName().equals(command.getName());
    }

    public void dispatch(String string) throws CommandSyntaxException {
        this.dispatch(string, (class_2172)new ChatCommandSource(Utils.mc));
    }

    public int getCount() {
        return this.commands.size();
    }

    public Commands() {
        super(null);
        this.COMMAND_SOURCE = new ChatCommandSource(Utils.mc);
        this.commands = new ArrayList<Command>();
        this.commandInstances = new HashMap<Class<? extends Command>, Command>();
    }

    public static Commands get() {
        return Systems.get(Commands.class);
    }

    @Override
    public void init() {
        this.add(new BaritoneCommand());
        this.add(new VClipCommand());
        this.add(new HClipCommand());
        this.add(new ClearChatCommand());
        this.add(new DismountCommand());
        this.add(new DamageCommand());
        this.add(new DropCommand());
        this.add(new EnchantCommand());
        this.add(new FakePlayerCommand());
        this.add(new FriendsCommand());
        this.add(new CommandsCommand());
        this.add(new InventoryCommand());
        this.add(new LocateCommand());
        this.add(new NbtCommand());
        this.add(new NotebotCommand());
        this.add(new PanicCommand());
        this.add(new PeekCommand());
        this.add(new ProfilesCommand());
        this.add(new ReloadCommand());
        this.add(new ResetCommand());
        this.add(new SayCommand());
        this.add(new ServerCommand());
        this.add(new SwarmCommand());
        this.add(new ToggleCommand());
        this.add(new SettingCommand());
        this.add(new SpectateCommand());
        this.add(new GamemodeCommand());
        this.add(new SaveMapCommand());
        this.add(new ModulesCommand());
        this.add(new BindsCommand());
        this.add(new GiveCommand());
        this.add(new NameHistoryCommand());
        this.commands.sort(Comparator.comparing(Command::getName));
    }

    public void add(Command command) {
        this.commands.removeIf(arg_0 -> Commands.lambda$add$0(command, arg_0));
        this.commandInstances.values().removeIf(arg_0 -> Commands.lambda$add$1(command, arg_0));
        command.registerTo(this.DISPATCHER);
        this.commands.add(command);
        this.commandInstances.put(command.getClass(), command);
    }

    private static final class ChatCommandSource
    extends class_637 {
        public ChatCommandSource(class_310 class_3102) {
            super(null, class_3102);
        }
    }
}

