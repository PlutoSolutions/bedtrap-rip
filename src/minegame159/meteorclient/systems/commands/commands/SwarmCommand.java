/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.commands.commands;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalXZ;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Random;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.ModuleArgumentType;
import minegame159.meteorclient.systems.commands.arguments.PlayerArgumentType;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.swarm.Swarm;
import minegame159.meteorclient.systems.modules.misc.swarm.SwarmConnection;
import minegame159.meteorclient.systems.modules.misc.swarm.SwarmWorker;
import minegame159.meteorclient.systems.modules.world.InfinityMiner;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2172;
import net.minecraft.class_2247;
import net.minecraft.class_2257;
import net.minecraft.class_2585;

public class SwarmCommand
extends Command {
    private static final SimpleCommandExceptionType SWARM_NOT_ACTIVE = new SimpleCommandExceptionType((Message)new class_2585("The swarm module must be active to use this command."));

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(SwarmCommand.literal("disconnect").executes(SwarmCommand::lambda$build$0));
        literalArgumentBuilder.then(SwarmCommand.literal("join").then(SwarmCommand.argument("ip", StringArgumentType.string()).then(SwarmCommand.argument("port", IntegerArgumentType.integer((int)0, (int)65535)).executes(SwarmCommand::lambda$build$1))));
        literalArgumentBuilder.then(SwarmCommand.literal("connections").executes(this::lambda$build$2));
        literalArgumentBuilder.then(((LiteralArgumentBuilder)SwarmCommand.literal("follow").executes(this::lambda$build$3)).then(SwarmCommand.argument("player", PlayerArgumentType.player()).executes(SwarmCommand::lambda$build$5)));
        literalArgumentBuilder.then(SwarmCommand.literal("goto").then(SwarmCommand.argument("x", IntegerArgumentType.integer()).then(SwarmCommand.argument("z", IntegerArgumentType.integer()).executes(SwarmCommand::lambda$build$6))));
        literalArgumentBuilder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)SwarmCommand.literal("infinity-miner").executes(this::lambda$build$7)).then(((RequiredArgumentBuilder)SwarmCommand.argument("target", class_2257.method_9653()).executes(this::lambda$build$8)).then(SwarmCommand.argument("repair", class_2257.method_9653()).executes(this::lambda$build$9)))).then(SwarmCommand.literal("logout").then(SwarmCommand.argument("logout", BoolArgumentType.bool()).executes(SwarmCommand::lambda$build$10)))).then(SwarmCommand.literal("walkhome").then(SwarmCommand.argument("walkhome", BoolArgumentType.bool()).executes(SwarmCommand::lambda$build$11))));
        literalArgumentBuilder.then(SwarmCommand.literal("mine").then(SwarmCommand.argument("block", class_2257.method_9653()).executes(SwarmCommand::lambda$build$12)));
        literalArgumentBuilder.then(SwarmCommand.literal("toggle").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)SwarmCommand.argument("module", ModuleArgumentType.module()).executes(SwarmCommand::lambda$build$13)).then(SwarmCommand.literal("on").executes(SwarmCommand::lambda$build$14))).then(SwarmCommand.literal("off").executes(SwarmCommand::lambda$build$15))));
        literalArgumentBuilder.then(((LiteralArgumentBuilder)SwarmCommand.literal("scatter").executes(this::lambda$build$16)).then(SwarmCommand.argument("radius", IntegerArgumentType.integer()).executes(this::lambda$build$17)));
        literalArgumentBuilder.then(SwarmCommand.literal("stop").executes(SwarmCommand::lambda$build$18));
        literalArgumentBuilder.then(SwarmCommand.literal("exec").then(SwarmCommand.argument("command", StringArgumentType.greedyString()).executes(SwarmCommand::lambda$build$19)));
    }

    private int lambda$build$7(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                this.runInfinityMiner();
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private int lambda$build$17(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                this.scatter(IntegerArgumentType.getInteger((CommandContext)commandContext, (String)"radius"));
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private int lambda$build$16(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                this.scatter(100);
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private static int lambda$build$19(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                SwarmCommand.mc.field_1724.method_3142(StringArgumentType.getString((CommandContext)commandContext, (String)"command"));
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private static int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (!swarm.isActive()) {
            swarm.toggle();
        }
        swarm.close();
        swarm.mode.set(Swarm.Mode.Worker);
        swarm.worker = new SwarmWorker(StringArgumentType.getString((CommandContext)commandContext, (String)"ip"), IntegerArgumentType.getInteger((CommandContext)commandContext, (String)"port"));
        return 1;
    }

    private static int lambda$build$12(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                swarm.worker.target = ((class_2247)commandContext.getArgument("block", class_2247.class)).method_9494().method_26204();
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private void runInfinityMiner() {
        InfinityMiner infinityMiner = Modules.get().get(InfinityMiner.class);
        if (infinityMiner.isActive()) {
            infinityMiner.toggle();
        }
        infinityMiner.smartModuleToggle.set(true);
        if (!infinityMiner.isActive()) {
            infinityMiner.toggle();
        }
    }

    private static int lambda$build$18(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private static boolean lambda$build$4(class_1657 class_16572, class_1297 class_12972) {
        return class_12972.method_5820().equalsIgnoreCase(class_16572.method_5820());
    }

    public SwarmCommand() {
        super("swarm", "Sends commands to connected swarm workers.", new String[0]);
    }

    private int lambda$build$3(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(String.valueOf(new StringBuilder().append(commandContext.getInput()).append(" ").append(SwarmCommand.mc.field_1724.method_5820())));
            } else if (swarm.isWorker()) {
                this.error("The follow host command must be used by the host.", new Object[0]);
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private static int lambda$build$15(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            Module module;
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker() && (module = ModuleArgumentType.getModule(commandContext, "module")).isActive()) {
                module.toggle();
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private int lambda$build$9(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                Modules.get().get(InfinityMiner.class).targetBlock.set(((class_2247)commandContext.getArgument("target", class_2247.class)).method_9494().method_26204());
                Modules.get().get(InfinityMiner.class).repairBlock.set(((class_2247)commandContext.getArgument("repair", class_2247.class)).method_9494().method_26204());
                this.runInfinityMiner();
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private static int lambda$build$14(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            Module module;
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker() && !(module = ModuleArgumentType.getModule(commandContext, "module")).isActive()) {
                module.toggle();
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private int lambda$build$2(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                if (swarm.host.getConnectionCount() > 0) {
                    ChatUtils.info("--- Swarm Connections (highlight)(%s/%s)(default) ---", swarm.host.getConnectionCount(), swarm.host.getConnections().length);
                    for (int i = 0; i < swarm.host.getConnections().length; ++i) {
                        SwarmConnection swarmConnection = swarm.host.getConnections()[i];
                        if (swarmConnection == null) continue;
                        ChatUtils.info("(highlight)Worker %s(default): %s.", i, swarmConnection.getConnection());
                        if (0 >= 0) continue;
                        return 0;
                    }
                } else {
                    this.warning("No active connections", new Object[0]);
                }
            } else if (swarm.isWorker()) {
                this.info("Connected to (highlight)%s", swarm.worker.getConnection());
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private static int lambda$build$6(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                int n = IntegerArgumentType.getInteger((CommandContext)commandContext, (String)"x");
                int n2 = IntegerArgumentType.getInteger((CommandContext)commandContext, (String)"z");
                BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)new GoalXZ(n, n2));
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private static int lambda$build$10(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                Modules.get().get(InfinityMiner.class).autoLogOut.set(BoolArgumentType.getBool((CommandContext)commandContext, (String)"logout"));
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (!swarm.isActive()) {
            throw SWARM_NOT_ACTIVE.create();
        }
        swarm.close();
        return 1;
    }

    private void scatter(int n) {
        Random random = new Random();
        double d = random.nextDouble() * 2.0 * Math.PI;
        double d2 = (double)n * Math.sqrt(random.nextDouble());
        double d3 = SwarmCommand.mc.field_1724.method_23317() + d2 * Math.cos(d);
        double d4 = SwarmCommand.mc.field_1724.method_23321() + d2 * Math.sin(d);
        BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
        BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)new GoalXZ((int)d3, (int)d4));
    }

    private static int lambda$build$5(CommandContext commandContext) throws CommandSyntaxException {
        class_1657 class_16572 = PlayerArgumentType.getPlayer(commandContext);
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker() && class_16572 != null) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getFollowProcess().follow(arg_0 -> SwarmCommand.lambda$build$4(class_16572, arg_0));
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private int lambda$build$8(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                Modules.get().get(InfinityMiner.class).targetBlock.set(((class_2247)commandContext.getArgument("target", class_2247.class)).method_9494().method_26204());
                this.runInfinityMiner();
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private static int lambda$build$13(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                Module module = ModuleArgumentType.getModule(commandContext, "module");
                module.toggle();
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }

    private static int lambda$build$11(CommandContext commandContext) throws CommandSyntaxException {
        Swarm swarm = Modules.get().get(Swarm.class);
        if (swarm.isActive()) {
            if (swarm.isHost()) {
                swarm.host.sendMessage(commandContext.getInput());
            } else if (swarm.isWorker()) {
                Modules.get().get(InfinityMiner.class).autoWalkHome.set(BoolArgumentType.getBool((CommandContext)commandContext, (String)"walkhome"));
            }
        } else {
            throw SWARM_NOT_ACTIVE.create();
        }
        return 1;
    }
}

