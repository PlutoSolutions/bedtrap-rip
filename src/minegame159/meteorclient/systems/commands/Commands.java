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
    private final /* synthetic */ Map<Class<? extends Command>, Command> commandInstances;
    private final /* synthetic */ class_2172 COMMAND_SOURCE;
    private final /* synthetic */ List<Command> commands;
    private final /* synthetic */ CommandDispatcher<class_2172> DISPATCHER;

    public void dispatch(String llllllllllllllllIlIllllIllIlllIl, class_2172 llllllllllllllllIlIllllIllIlllII) throws CommandSyntaxException {
        Commands llllllllllllllllIlIllllIllIllIlI;
        ParseResults llllllllllllllllIlIllllIllIllIll = llllllllllllllllIlIllllIllIllIlI.DISPATCHER.parse(llllllllllllllllIlIllllIllIlllIl, (Object)llllllllllllllllIlIllllIllIlllII);
        llllllllllllllllIlIllllIllIllIlI.DISPATCHER.execute(llllllllllllllllIlIllllIllIllIll);
    }

    public class_2172 getCommandSource() {
        Commands llllllllllllllllIlIllllIllIlIIlI;
        return llllllllllllllllIlIllllIllIlIIlI.COMMAND_SOURCE;
    }

    public <T extends Command> T get(Class<T> llllllllllllllllIlIllllIlIllllll) {
        Commands llllllllllllllllIlIllllIllIIIIlI;
        return (T)llllllllllllllllIlIllllIllIIIIlI.commandInstances.get(llllllllllllllllIlIllllIlIllllll);
    }

    public CommandDispatcher<class_2172> getDispatcher() {
        Commands llllllllllllllllIlIllllIllIlIlIl;
        return llllllllllllllllIlIllllIllIlIlIl.DISPATCHER;
    }

    public List<Command> getAll() {
        Commands llllllllllllllllIlIllllIllIIIlIl;
        return llllllllllllllllIlIllllIllIIIlIl.commands;
    }

    public void dispatch(String llllllllllllllllIlIllllIlllIIlIl) throws CommandSyntaxException {
        Commands llllllllllllllllIlIllllIlllIIlII;
        llllllllllllllllIlIllllIlllIIlII.dispatch(llllllllllllllllIlIllllIlllIIlIl, (class_2172)new ChatCommandSource(Utils.mc));
    }

    public int getCount() {
        Commands llllllllllllllllIlIllllIllIIlIII;
        return llllllllllllllllIlIllllIllIIlIII.commands.size();
    }

    public Commands() {
        super(null);
        Commands llllllllllllllllIlIllllIlllIllII;
        llllllllllllllllIlIllllIlllIllII.DISPATCHER = new CommandDispatcher();
        llllllllllllllllIlIllllIlllIllII.COMMAND_SOURCE = new ChatCommandSource(Utils.mc);
        llllllllllllllllIlIllllIlllIllII.commands = new ArrayList<Command>();
        llllllllllllllllIlIllllIlllIllII.commandInstances = new HashMap<Class<? extends Command>, Command>();
    }

    public static Commands get() {
        return Systems.get(Commands.class);
    }

    @Override
    public void init() {
        Commands llllllllllllllllIlIllllIlllIlIIl;
        llllllllllllllllIlIllllIlllIlIIl.add(new BaritoneCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new VClipCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new HClipCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new ClearChatCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new DismountCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new DamageCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new DropCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new EnchantCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new FakePlayerCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new FriendsCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new CommandsCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new InventoryCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new LocateCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new NbtCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new NotebotCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new PanicCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new PeekCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new ProfilesCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new ReloadCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new ResetCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new SayCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new ServerCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new SwarmCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new ToggleCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new SettingCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new SpectateCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new GamemodeCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new SaveMapCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new ModulesCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new BindsCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new GiveCommand());
        llllllllllllllllIlIllllIlllIlIIl.add(new NameHistoryCommand());
        llllllllllllllllIlIllllIlllIlIIl.commands.sort(Comparator.comparing(Command::getName));
    }

    public void add(Command llllllllllllllllIlIllllIllIIlIll) {
        Commands llllllllllllllllIlIllllIllIIlllI;
        llllllllllllllllIlIllllIllIIlllI.commands.removeIf(llllllllllllllllIlIllllIlIllIlIl -> llllllllllllllllIlIllllIlIllIlIl.getName().equals(llllllllllllllllIlIllllIllIIlIll.getName()));
        llllllllllllllllIlIllllIllIIlllI.commandInstances.values().removeIf(llllllllllllllllIlIllllIlIlllIll -> llllllllllllllllIlIllllIlIlllIll.getName().equals(llllllllllllllllIlIllllIllIIlIll.getName()));
        llllllllllllllllIlIllllIllIIlIll.registerTo(llllllllllllllllIlIllllIllIIlllI.DISPATCHER);
        llllllllllllllllIlIllllIllIIlllI.commands.add(llllllllllllllllIlIllllIllIIlIll);
        llllllllllllllllIlIllllIllIIlllI.commandInstances.put(llllllllllllllllIlIllllIllIIlIll.getClass(), llllllllllllllllIlIllllIllIIlIll);
    }

    private static final class ChatCommandSource
    extends class_637 {
        public ChatCommandSource(class_310 lllllllllllllllllIlIIllIIIIllIll) {
            super(null, lllllllllllllllllIlIIllIIIIllIll);
            ChatCommandSource lllllllllllllllllIlIIllIIIIlllII;
        }
    }
}

