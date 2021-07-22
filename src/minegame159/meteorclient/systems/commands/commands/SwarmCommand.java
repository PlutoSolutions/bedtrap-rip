/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  baritone.api.pathing.goals.Goal
 *  baritone.api.pathing.goals.GoalXZ
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  net.minecraft.class_1657
 *  net.minecraft.class_2172
 *  net.minecraft.class_2247
 *  net.minecraft.class_2257
 *  net.minecraft.class_2585
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
import net.minecraft.class_1657;
import net.minecraft.class_2172;
import net.minecraft.class_2247;
import net.minecraft.class_2257;
import net.minecraft.class_2585;

public class SwarmCommand
extends Command {
    private static final /* synthetic */ SimpleCommandExceptionType SWARM_NOT_ACTIVE;

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllIlIIllIIIIlIIlI) {
        SwarmCommand lllllllllllllllllIlIIllIIIIlIIIl;
        lllllllllllllllllIlIIllIIIIlIIlI.then(SwarmCommand.literal("disconnect").executes(lllllllllllllllllIlIIlIlIlIllIlI -> {
            Swarm lllllllllllllllllIlIIlIlIlIllIIl = Modules.get().get(Swarm.class);
            if (!lllllllllllllllllIlIIlIlIlIllIIl.isActive()) {
                throw SWARM_NOT_ACTIVE.create();
            }
            lllllllllllllllllIlIIlIlIlIllIIl.close();
            return 1;
        }));
        lllllllllllllllllIlIIllIIIIlIIlI.then(SwarmCommand.literal("join").then(SwarmCommand.argument("ip", StringArgumentType.string()).then(SwarmCommand.argument("port", IntegerArgumentType.integer((int)0, (int)65535)).executes(lllllllllllllllllIlIIlIlIlIlllll -> {
            Swarm lllllllllllllllllIlIIlIlIlIllllI = Modules.get().get(Swarm.class);
            if (!lllllllllllllllllIlIIlIlIlIllllI.isActive()) {
                lllllllllllllllllIlIIlIlIlIllllI.toggle();
            }
            lllllllllllllllllIlIIlIlIlIllllI.close();
            lllllllllllllllllIlIIlIlIlIllllI.mode.set(Swarm.Mode.Worker);
            lllllllllllllllllIlIIlIlIlIllllI.worker = new SwarmWorker(StringArgumentType.getString((CommandContext)lllllllllllllllllIlIIlIlIlIlllll, (String)"ip"), IntegerArgumentType.getInteger((CommandContext)lllllllllllllllllIlIIlIlIlIlllll, (String)"port"));
            return 1;
        }))));
        lllllllllllllllllIlIIllIIIIlIIlI.then(SwarmCommand.literal("connections").executes(lllllllllllllllllIlIIlIlIllIIlll -> {
            Swarm lllllllllllllllllIlIIlIlIllIIllI = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIlIllIIllI.isActive()) {
                SwarmCommand lllllllllllllllllIlIIlIlIllIlIII;
                if (lllllllllllllllllIlIIlIlIllIIllI.isHost()) {
                    if (lllllllllllllllllIlIIlIlIllIIllI.host.getConnectionCount() > 0) {
                        ChatUtils.info("--- Swarm Connections (highlight)(%s/%s)(default) ---", lllllllllllllllllIlIIlIlIllIIllI.host.getConnectionCount(), lllllllllllllllllIlIIlIlIllIIllI.host.getConnections().length);
                        for (int lllllllllllllllllIlIIlIlIllIlIIl = 0; lllllllllllllllllIlIIlIlIllIlIIl < lllllllllllllllllIlIIlIlIllIIllI.host.getConnections().length; ++lllllllllllllllllIlIIlIlIllIlIIl) {
                            SwarmConnection lllllllllllllllllIlIIlIlIllIlIlI = lllllllllllllllllIlIIlIlIllIIllI.host.getConnections()[lllllllllllllllllIlIIlIlIllIlIIl];
                            if (lllllllllllllllllIlIIlIlIllIlIlI == null) continue;
                            ChatUtils.info("(highlight)Worker %s(default): %s.", lllllllllllllllllIlIIlIlIllIlIIl, lllllllllllllllllIlIIlIlIllIlIlI.getConnection());
                        }
                    } else {
                        lllllllllllllllllIlIIlIlIllIlIII.warning("No active connections", new Object[0]);
                    }
                } else if (lllllllllllllllllIlIIlIlIllIIllI.isWorker()) {
                    lllllllllllllllllIlIIlIlIllIlIII.info("Connected to (highlight)%s", lllllllllllllllllIlIIlIlIllIIllI.worker.getConnection());
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        }));
        lllllllllllllllllIlIIllIIIIlIIlI.then(((LiteralArgumentBuilder)SwarmCommand.literal("follow").executes(lllllllllllllllllIlIIlIlIlllIIll -> {
            Swarm lllllllllllllllllIlIIlIlIlllIIlI = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIlIlllIIlI.isActive()) {
                if (lllllllllllllllllIlIIlIlIlllIIlI.isHost()) {
                    lllllllllllllllllIlIIlIlIlllIIlI.host.sendMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllIlIIlIlIlllIIll.getInput()).append(" ").append(SwarmCommand.mc.field_1724.method_5820())));
                } else if (lllllllllllllllllIlIIlIlIlllIIlI.isWorker()) {
                    SwarmCommand lllllllllllllllllIlIIlIlIlllIlII;
                    lllllllllllllllllIlIIlIlIlllIlII.error("The follow host command must be used by the host.", new Object[0]);
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })).then(SwarmCommand.argument("player", PlayerArgumentType.player()).executes(lllllllllllllllllIlIIlIllIIIIIII -> {
            class_1657 lllllllllllllllllIlIIlIllIIIIIlI = PlayerArgumentType.getPlayer(lllllllllllllllllIlIIlIllIIIIIII);
            Swarm lllllllllllllllllIlIIlIllIIIIIIl = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIllIIIIIIl.isActive()) {
                if (lllllllllllllllllIlIIlIllIIIIIIl.isHost()) {
                    lllllllllllllllllIlIIlIllIIIIIIl.host.sendMessage(lllllllllllllllllIlIIlIllIIIIIII.getInput());
                } else if (lllllllllllllllllIlIIlIllIIIIIIl.isWorker() && lllllllllllllllllIlIIlIllIIIIIlI != null) {
                    BaritoneAPI.getProvider().getPrimaryBaritone().getFollowProcess().follow(lllllllllllllllllIlIIlIlIllllIlI -> lllllllllllllllllIlIIlIlIllllIlI.method_5820().equalsIgnoreCase(lllllllllllllllllIlIIlIllIIIIIlI.method_5820()));
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })));
        lllllllllllllllllIlIIllIIIIlIIlI.then(SwarmCommand.literal("goto").then(SwarmCommand.argument("x", IntegerArgumentType.integer()).then(SwarmCommand.argument("z", IntegerArgumentType.integer()).executes(lllllllllllllllllIlIIlIllIIIlIlI -> {
            Swarm lllllllllllllllllIlIIlIllIIIlIll = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIllIIIlIll.isActive()) {
                if (lllllllllllllllllIlIIlIllIIIlIll.isHost()) {
                    lllllllllllllllllIlIIlIllIIIlIll.host.sendMessage(lllllllllllllllllIlIIlIllIIIlIlI.getInput());
                } else if (lllllllllllllllllIlIIlIllIIIlIll.isWorker()) {
                    int lllllllllllllllllIlIIlIllIIIlllI = IntegerArgumentType.getInteger((CommandContext)lllllllllllllllllIlIIlIllIIIlIlI, (String)"x");
                    int lllllllllllllllllIlIIlIllIIIllIl = IntegerArgumentType.getInteger((CommandContext)lllllllllllllllllIlIIlIllIIIlIlI, (String)"z");
                    BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)new GoalXZ(lllllllllllllllllIlIIlIllIIIlllI, lllllllllllllllllIlIIlIllIIIllIl));
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        }))));
        lllllllllllllllllIlIIllIIIIlIIlI.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)SwarmCommand.literal("infinity-miner").executes(lllllllllllllllllIlIIlIllIIlIlII -> {
            Swarm lllllllllllllllllIlIIlIllIIlIllI = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIllIIlIllI.isActive()) {
                if (lllllllllllllllllIlIIlIllIIlIllI.isHost()) {
                    lllllllllllllllllIlIIlIllIIlIllI.host.sendMessage(lllllllllllllllllIlIIlIllIIlIlII.getInput());
                } else if (lllllllllllllllllIlIIlIllIIlIllI.isWorker()) {
                    SwarmCommand lllllllllllllllllIlIIlIllIIllIII;
                    lllllllllllllllllIlIIlIllIIllIII.runInfinityMiner();
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })).then(((RequiredArgumentBuilder)SwarmCommand.argument("target", class_2257.method_9653()).executes(lllllllllllllllllIlIIlIllIIlllIl -> {
            Swarm lllllllllllllllllIlIIlIllIIlllll = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIllIIlllll.isActive()) {
                if (lllllllllllllllllIlIIlIllIIlllll.isHost()) {
                    lllllllllllllllllIlIIlIllIIlllll.host.sendMessage(lllllllllllllllllIlIIlIllIIlllIl.getInput());
                } else if (lllllllllllllllllIlIIlIllIIlllll.isWorker()) {
                    SwarmCommand lllllllllllllllllIlIIlIllIIllllI;
                    Modules.get().get(InfinityMiner.class).targetBlock.set(((class_2247)lllllllllllllllllIlIIlIllIIlllIl.getArgument("target", class_2247.class)).method_9494().method_26204());
                    lllllllllllllllllIlIIlIllIIllllI.runInfinityMiner();
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })).then(SwarmCommand.argument("repair", class_2257.method_9653()).executes(lllllllllllllllllIlIIlIllIlIIllI -> {
            Swarm lllllllllllllllllIlIIlIllIlIlIII = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIllIlIlIII.isActive()) {
                if (lllllllllllllllllIlIIlIllIlIlIII.isHost()) {
                    lllllllllllllllllIlIIlIllIlIlIII.host.sendMessage(lllllllllllllllllIlIIlIllIlIIllI.getInput());
                } else if (lllllllllllllllllIlIIlIllIlIlIII.isWorker()) {
                    SwarmCommand lllllllllllllllllIlIIlIllIlIIlll;
                    Modules.get().get(InfinityMiner.class).targetBlock.set(((class_2247)lllllllllllllllllIlIIlIllIlIIllI.getArgument("target", class_2247.class)).method_9494().method_26204());
                    Modules.get().get(InfinityMiner.class).repairBlock.set(((class_2247)lllllllllllllllllIlIIlIllIlIIllI.getArgument("repair", class_2247.class)).method_9494().method_26204());
                    lllllllllllllllllIlIIlIllIlIIlll.runInfinityMiner();
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })))).then(SwarmCommand.literal("logout").then(SwarmCommand.argument("logout", BoolArgumentType.bool()).executes(lllllllllllllllllIlIIlIllIlIllll -> {
            Swarm lllllllllllllllllIlIIlIllIllIIII = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIllIllIIII.isActive()) {
                if (lllllllllllllllllIlIIlIllIllIIII.isHost()) {
                    lllllllllllllllllIlIIlIllIllIIII.host.sendMessage(lllllllllllllllllIlIIlIllIlIllll.getInput());
                } else if (lllllllllllllllllIlIIlIllIllIIII.isWorker()) {
                    Modules.get().get(InfinityMiner.class).autoLogOut.set(BoolArgumentType.getBool((CommandContext)lllllllllllllllllIlIIlIllIlIllll, (String)"logout"));
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })))).then(SwarmCommand.literal("walkhome").then(SwarmCommand.argument("walkhome", BoolArgumentType.bool()).executes(lllllllllllllllllIlIIlIllIllIlll -> {
            Swarm lllllllllllllllllIlIIlIllIllIllI = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIllIllIllI.isActive()) {
                if (lllllllllllllllllIlIIlIllIllIllI.isHost()) {
                    lllllllllllllllllIlIIlIllIllIllI.host.sendMessage(lllllllllllllllllIlIIlIllIllIlll.getInput());
                } else if (lllllllllllllllllIlIIlIllIllIllI.isWorker()) {
                    Modules.get().get(InfinityMiner.class).autoWalkHome.set(BoolArgumentType.getBool((CommandContext)lllllllllllllllllIlIIlIllIllIlll, (String)"walkhome"));
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        }))));
        lllllllllllllllllIlIIllIIIIlIIlI.then(SwarmCommand.literal("mine").then(SwarmCommand.argument("block", class_2257.method_9653()).executes(lllllllllllllllllIlIIlIllIllllIl -> {
            Swarm lllllllllllllllllIlIIlIllIllllII = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIllIllllII.isActive()) {
                if (lllllllllllllllllIlIIlIllIllllII.isHost()) {
                    lllllllllllllllllIlIIlIllIllllII.host.sendMessage(lllllllllllllllllIlIIlIllIllllIl.getInput());
                } else if (lllllllllllllllllIlIIlIllIllllII.isWorker()) {
                    lllllllllllllllllIlIIlIllIllllII.worker.target = ((class_2247)lllllllllllllllllIlIIlIllIllllIl.getArgument("block", class_2247.class)).method_9494().method_26204();
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })));
        lllllllllllllllllIlIIllIIIIlIIlI.then(SwarmCommand.literal("toggle").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)SwarmCommand.argument("module", ModuleArgumentType.module()).executes(lllllllllllllllllIlIIlIlllIIIIlI -> {
            Swarm lllllllllllllllllIlIIlIlllIIIIll = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIlllIIIIll.isActive()) {
                if (lllllllllllllllllIlIIlIlllIIIIll.isHost()) {
                    lllllllllllllllllIlIIlIlllIIIIll.host.sendMessage(lllllllllllllllllIlIIlIlllIIIIlI.getInput());
                } else if (lllllllllllllllllIlIIlIlllIIIIll.isWorker()) {
                    Module lllllllllllllllllIlIIlIlllIIIlIl = ModuleArgumentType.getModule(lllllllllllllllllIlIIlIlllIIIIlI, "module");
                    lllllllllllllllllIlIIlIlllIIIlIl.toggle();
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })).then(SwarmCommand.literal("on").executes(lllllllllllllllllIlIIlIlllIIlIll -> {
            Swarm lllllllllllllllllIlIIlIlllIIllII = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIlllIIllII.isActive()) {
                Module lllllllllllllllllIlIIlIlllIIlllI;
                if (lllllllllllllllllIlIIlIlllIIllII.isHost()) {
                    lllllllllllllllllIlIIlIlllIIllII.host.sendMessage(lllllllllllllllllIlIIlIlllIIlIll.getInput());
                } else if (lllllllllllllllllIlIIlIlllIIllII.isWorker() && !(lllllllllllllllllIlIIlIlllIIlllI = ModuleArgumentType.getModule(lllllllllllllllllIlIIlIlllIIlIll, "module")).isActive()) {
                    lllllllllllllllllIlIIlIlllIIlllI.toggle();
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        }))).then(SwarmCommand.literal("off").executes(lllllllllllllllllIlIIlIlllIlIllI -> {
            Swarm lllllllllllllllllIlIIlIlllIlIlIl = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIlllIlIlIl.isActive()) {
                Module lllllllllllllllllIlIIlIlllIlIlll;
                if (lllllllllllllllllIlIIlIlllIlIlIl.isHost()) {
                    lllllllllllllllllIlIIlIlllIlIlIl.host.sendMessage(lllllllllllllllllIlIIlIlllIlIllI.getInput());
                } else if (lllllllllllllllllIlIIlIlllIlIlIl.isWorker() && (lllllllllllllllllIlIIlIlllIlIlll = ModuleArgumentType.getModule(lllllllllllllllllIlIIlIlllIlIllI, "module")).isActive()) {
                    lllllllllllllllllIlIIlIlllIlIlll.toggle();
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        }))));
        lllllllllllllllllIlIIllIIIIlIIlI.then(((LiteralArgumentBuilder)SwarmCommand.literal("scatter").executes(lllllllllllllllllIlIIlIlllIlllII -> {
            Swarm lllllllllllllllllIlIIlIlllIllllI = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIlllIllllI.isActive()) {
                if (lllllllllllllllllIlIIlIlllIllllI.isHost()) {
                    lllllllllllllllllIlIIlIlllIllllI.host.sendMessage(lllllllllllllllllIlIIlIlllIlllII.getInput());
                } else if (lllllllllllllllllIlIIlIlllIllllI.isWorker()) {
                    SwarmCommand lllllllllllllllllIlIIlIlllIlllIl;
                    lllllllllllllllllIlIIlIlllIlllIl.scatter(100);
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })).then(SwarmCommand.argument("radius", IntegerArgumentType.integer()).executes(lllllllllllllllllIlIIlIllllIlIII -> {
            Swarm lllllllllllllllllIlIIlIllllIIlll = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIllllIIlll.isActive()) {
                if (lllllllllllllllllIlIIlIllllIIlll.isHost()) {
                    lllllllllllllllllIlIIlIllllIIlll.host.sendMessage(lllllllllllllllllIlIIlIllllIlIII.getInput());
                } else if (lllllllllllllllllIlIIlIllllIIlll.isWorker()) {
                    SwarmCommand lllllllllllllllllIlIIlIllllIIllI;
                    lllllllllllllllllIlIIlIllllIIllI.scatter(IntegerArgumentType.getInteger((CommandContext)lllllllllllllllllIlIIlIllllIlIII, (String)"radius"));
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })));
        lllllllllllllllllIlIIllIIIIlIIlI.then(SwarmCommand.literal("stop").executes(lllllllllllllllllIlIIlIllllIlllI -> {
            Swarm lllllllllllllllllIlIIlIllllIllll = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIllllIllll.isActive()) {
                if (lllllllllllllllllIlIIlIllllIllll.isHost()) {
                    lllllllllllllllllIlIIlIllllIllll.host.sendMessage(lllllllllllllllllIlIIlIllllIlllI.getInput());
                } else if (lllllllllllllllllIlIIlIllllIllll.isWorker()) {
                    BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        }));
        lllllllllllllllllIlIIllIIIIlIIlI.then(SwarmCommand.literal("exec").then(SwarmCommand.argument("command", StringArgumentType.greedyString()).executes(lllllllllllllllllIlIIlIlllllIlII -> {
            Swarm lllllllllllllllllIlIIlIlllllIlIl = Modules.get().get(Swarm.class);
            if (lllllllllllllllllIlIIlIlllllIlIl.isActive()) {
                if (lllllllllllllllllIlIIlIlllllIlIl.isHost()) {
                    lllllllllllllllllIlIIlIlllllIlIl.host.sendMessage(lllllllllllllllllIlIIlIlllllIlII.getInput());
                } else if (lllllllllllllllllIlIIlIlllllIlIl.isWorker()) {
                    SwarmCommand.mc.field_1724.method_3142(StringArgumentType.getString((CommandContext)lllllllllllllllllIlIIlIlllllIlII, (String)"command"));
                }
            } else {
                throw SWARM_NOT_ACTIVE.create();
            }
            return 1;
        })));
    }

    private void runInfinityMiner() {
        InfinityMiner lllllllllllllllllIlIIllIIIIIllIl = Modules.get().get(InfinityMiner.class);
        if (lllllllllllllllllIlIIllIIIIIllIl.isActive()) {
            lllllllllllllllllIlIIllIIIIIllIl.toggle();
        }
        lllllllllllllllllIlIIllIIIIIllIl.smartModuleToggle.set(true);
        if (!lllllllllllllllllIlIIllIIIIIllIl.isActive()) {
            lllllllllllllllllIlIIllIIIIIllIl.toggle();
        }
    }

    public SwarmCommand() {
        super("swarm", "Sends commands to connected swarm workers.", new String[0]);
        SwarmCommand lllllllllllllllllIlIIllIIIIlIlll;
    }

    static {
        SWARM_NOT_ACTIVE = new SimpleCommandExceptionType((Message)new class_2585("The swarm module must be active to use this command."));
    }

    private void scatter(int lllllllllllllllllIlIIllIIIIIIlII) {
        Random lllllllllllllllllIlIIllIIIIIIIll = new Random();
        double lllllllllllllllllIlIIllIIIIIIIlI = lllllllllllllllllIlIIllIIIIIIIll.nextDouble() * 2.0 * Math.PI;
        double lllllllllllllllllIlIIllIIIIIIIIl = (double)lllllllllllllllllIlIIllIIIIIIlII * Math.sqrt(lllllllllllllllllIlIIllIIIIIIIll.nextDouble());
        double lllllllllllllllllIlIIllIIIIIIIII = SwarmCommand.mc.field_1724.method_23317() + lllllllllllllllllIlIIllIIIIIIIIl * Math.cos(lllllllllllllllllIlIIllIIIIIIIlI);
        double lllllllllllllllllIlIIlIlllllllll = SwarmCommand.mc.field_1724.method_23321() + lllllllllllllllllIlIIllIIIIIIIIl * Math.sin(lllllllllllllllllIlIIllIIIIIIIlI);
        BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
        BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)new GoalXZ((int)lllllllllllllllllIlIIllIIIIIIIII, (int)lllllllllllllllllIlIIlIlllllllll));
    }
}

