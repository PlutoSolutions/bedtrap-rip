/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Lifecycle
 *  javax.annotation.Nullable
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 *  net.minecraft.class_3545
 *  net.minecraft.class_5321
 */
package minegame159.meteorclient.systems.modules;

import com.mojang.serialization.Lifecycle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.GameJoinedEvent;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.events.meteor.ActiveModulesChangedEvent;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.events.meteor.ModuleBindChangedEvent;
import minegame159.meteorclient.events.meteor.MouseButtonEvent;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.modules.Category;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.bedtrap.AntiGhostBlock;
import minegame159.meteorclient.systems.modules.bedtrap.AntiRespawnLose;
import minegame159.meteorclient.systems.modules.bedtrap.AutoBuild;
import minegame159.meteorclient.systems.modules.bedtrap.AutoEz;
import minegame159.meteorclient.systems.modules.bedtrap.AutoLeave;
import minegame159.meteorclient.systems.modules.bedtrap.AutoLogin;
import minegame159.meteorclient.systems.modules.bedtrap.BedAuraTwo;
import minegame159.meteorclient.systems.modules.bedtrap.BedrockTravel;
import minegame159.meteorclient.systems.modules.bedtrap.ButtonTrap;
import minegame159.meteorclient.systems.modules.bedtrap.CrashExploits;
import minegame159.meteorclient.systems.modules.bedtrap.CrystalAuraTwo;
import minegame159.meteorclient.systems.modules.bedtrap.CrystalHead;
import minegame159.meteorclient.systems.modules.bedtrap.FastUseTwo;
import minegame159.meteorclient.systems.modules.bedtrap.HeadProtect;
import minegame159.meteorclient.systems.modules.bedtrap.InstaAutoCity;
import minegame159.meteorclient.systems.modules.bedtrap.NukerTwo;
import minegame159.meteorclient.systems.modules.bedtrap.OldAnimations;
import minegame159.meteorclient.systems.modules.bedtrap.PacketFly;
import minegame159.meteorclient.systems.modules.bedtrap.Phase;
import minegame159.meteorclient.systems.modules.bedtrap.SevilaMode;
import minegame159.meteorclient.systems.modules.bedtrap.SkeletonESP;
import minegame159.meteorclient.systems.modules.bedtrap.StrictAutoTotem;
import minegame159.meteorclient.systems.modules.bedtrap.SurroundTwo;
import minegame159.meteorclient.systems.modules.combat.AimAssist;
import minegame159.meteorclient.systems.modules.combat.AnchorAura;
import minegame159.meteorclient.systems.modules.combat.AntiAnvil;
import minegame159.meteorclient.systems.modules.combat.AntiBed;
import minegame159.meteorclient.systems.modules.combat.AntiHit;
import minegame159.meteorclient.systems.modules.combat.ArrowDodge;
import minegame159.meteorclient.systems.modules.combat.AutoAnvil;
import minegame159.meteorclient.systems.modules.combat.AutoArmor;
import minegame159.meteorclient.systems.modules.combat.AutoCity;
import minegame159.meteorclient.systems.modules.combat.AutoTotem;
import minegame159.meteorclient.systems.modules.combat.AutoTrap;
import minegame159.meteorclient.systems.modules.combat.AutoWeapon;
import minegame159.meteorclient.systems.modules.combat.AutoWeb;
import minegame159.meteorclient.systems.modules.combat.BedAura;
import minegame159.meteorclient.systems.modules.combat.BowAimbot;
import minegame159.meteorclient.systems.modules.combat.BowSpam;
import minegame159.meteorclient.systems.modules.combat.Burrow;
import minegame159.meteorclient.systems.modules.combat.Criticals;
import minegame159.meteorclient.systems.modules.combat.CrystalAura;
import minegame159.meteorclient.systems.modules.combat.Hitboxes;
import minegame159.meteorclient.systems.modules.combat.HoleFiller;
import minegame159.meteorclient.systems.modules.combat.KillAura;
import minegame159.meteorclient.systems.modules.combat.Offhand;
import minegame159.meteorclient.systems.modules.combat.Quiver;
import minegame159.meteorclient.systems.modules.combat.SelfAnvil;
import minegame159.meteorclient.systems.modules.combat.SelfTrap;
import minegame159.meteorclient.systems.modules.combat.SelfWeb;
import minegame159.meteorclient.systems.modules.combat.Surround;
import minegame159.meteorclient.systems.modules.misc.Announcer;
import minegame159.meteorclient.systems.modules.misc.AntiPacketKick;
import minegame159.meteorclient.systems.modules.misc.AutoClicker;
import minegame159.meteorclient.systems.modules.misc.AutoLog;
import minegame159.meteorclient.systems.modules.misc.AutoReconnect;
import minegame159.meteorclient.systems.modules.misc.AutoRespawn;
import minegame159.meteorclient.systems.modules.misc.BetterChat;
import minegame159.meteorclient.systems.modules.misc.BetterTab;
import minegame159.meteorclient.systems.modules.misc.BookBot;
import minegame159.meteorclient.systems.modules.misc.DiscordPresence;
import minegame159.meteorclient.systems.modules.misc.InventoryTweaks;
import minegame159.meteorclient.systems.modules.misc.MessageAura;
import minegame159.meteorclient.systems.modules.misc.MiddleClickFriend;
import minegame159.meteorclient.systems.modules.misc.NameProtect;
import minegame159.meteorclient.systems.modules.misc.Notebot;
import minegame159.meteorclient.systems.modules.misc.Notifier;
import minegame159.meteorclient.systems.modules.misc.PacketCanceller;
import minegame159.meteorclient.systems.modules.misc.SoundBlocker;
import minegame159.meteorclient.systems.modules.misc.Spam;
import minegame159.meteorclient.systems.modules.misc.TPSSync;
import minegame159.meteorclient.systems.modules.misc.VanillaSpoof;
import minegame159.meteorclient.systems.modules.misc.swarm.Swarm;
import minegame159.meteorclient.systems.modules.movement.AirJump;
import minegame159.meteorclient.systems.modules.movement.Anchor;
import minegame159.meteorclient.systems.modules.movement.AntiAFK;
import minegame159.meteorclient.systems.modules.movement.AntiLevitation;
import minegame159.meteorclient.systems.modules.movement.AntiVoid;
import minegame159.meteorclient.systems.modules.movement.AutoJump;
import minegame159.meteorclient.systems.modules.movement.AutoWalk;
import minegame159.meteorclient.systems.modules.movement.Blink;
import minegame159.meteorclient.systems.modules.movement.BoatFly;
import minegame159.meteorclient.systems.modules.movement.ClickTP;
import minegame159.meteorclient.systems.modules.movement.ElytraBoost;
import minegame159.meteorclient.systems.modules.movement.EntityControl;
import minegame159.meteorclient.systems.modules.movement.EntitySpeed;
import minegame159.meteorclient.systems.modules.movement.FastClimb;
import minegame159.meteorclient.systems.modules.movement.Flight;
import minegame159.meteorclient.systems.modules.movement.GUIMove;
import minegame159.meteorclient.systems.modules.movement.HighJump;
import minegame159.meteorclient.systems.modules.movement.Jesus;
import minegame159.meteorclient.systems.modules.movement.NoFall;
import minegame159.meteorclient.systems.modules.movement.NoSlow;
import minegame159.meteorclient.systems.modules.movement.Parkour;
import minegame159.meteorclient.systems.modules.movement.ReverseStep;
import minegame159.meteorclient.systems.modules.movement.SafeWalk;
import minegame159.meteorclient.systems.modules.movement.Scaffold;
import minegame159.meteorclient.systems.modules.movement.Slippy;
import minegame159.meteorclient.systems.modules.movement.Sneak;
import minegame159.meteorclient.systems.modules.movement.Spider;
import minegame159.meteorclient.systems.modules.movement.Sprint;
import minegame159.meteorclient.systems.modules.movement.Step;
import minegame159.meteorclient.systems.modules.movement.Velocity;
import minegame159.meteorclient.systems.modules.movement.elytrafly.ElytraFly;
import minegame159.meteorclient.systems.modules.movement.speed.Speed;
import minegame159.meteorclient.systems.modules.player.AntiHunger;
import minegame159.meteorclient.systems.modules.player.AutoEat;
import minegame159.meteorclient.systems.modules.player.AutoFish;
import minegame159.meteorclient.systems.modules.player.AutoGap;
import minegame159.meteorclient.systems.modules.player.AutoMend;
import minegame159.meteorclient.systems.modules.player.AutoReplenish;
import minegame159.meteorclient.systems.modules.player.AutoTool;
import minegame159.meteorclient.systems.modules.player.ChestSwap;
import minegame159.meteorclient.systems.modules.player.EXPThrower;
import minegame159.meteorclient.systems.modules.player.FakePlayer;
import minegame159.meteorclient.systems.modules.player.FastUse;
import minegame159.meteorclient.systems.modules.player.GhostHand;
import minegame159.meteorclient.systems.modules.player.LiquidInteract;
import minegame159.meteorclient.systems.modules.player.MiddleClickExtra;
import minegame159.meteorclient.systems.modules.player.NoBreakDelay;
import minegame159.meteorclient.systems.modules.player.NoInteract;
import minegame159.meteorclient.systems.modules.player.NoMiningTrace;
import minegame159.meteorclient.systems.modules.player.NoRotate;
import minegame159.meteorclient.systems.modules.player.OffhandCrash;
import minegame159.meteorclient.systems.modules.player.Portals;
import minegame159.meteorclient.systems.modules.player.PotionSaver;
import minegame159.meteorclient.systems.modules.player.PotionSpoof;
import minegame159.meteorclient.systems.modules.player.Reach;
import minegame159.meteorclient.systems.modules.player.Rotation;
import minegame159.meteorclient.systems.modules.player.SpeedMine;
import minegame159.meteorclient.systems.modules.player.XCarry;
import minegame159.meteorclient.systems.modules.render.BetterTooltips;
import minegame159.meteorclient.systems.modules.render.BlockSelection;
import minegame159.meteorclient.systems.modules.render.BossStack;
import minegame159.meteorclient.systems.modules.render.Breadcrumbs;
import minegame159.meteorclient.systems.modules.render.BreakIndicators;
import minegame159.meteorclient.systems.modules.render.CameraTweaks;
import minegame159.meteorclient.systems.modules.render.Chams;
import minegame159.meteorclient.systems.modules.render.CityESP;
import minegame159.meteorclient.systems.modules.render.CustomFOV;
import minegame159.meteorclient.systems.modules.render.ESP;
import minegame159.meteorclient.systems.modules.render.EntityOwner;
import minegame159.meteorclient.systems.modules.render.FreeLook;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.systems.modules.render.Fullbright;
import minegame159.meteorclient.systems.modules.render.HandView;
import minegame159.meteorclient.systems.modules.render.HoleESP;
import minegame159.meteorclient.systems.modules.render.ItemHighlight;
import minegame159.meteorclient.systems.modules.render.ItemPhysics;
import minegame159.meteorclient.systems.modules.render.LightOverlay;
import minegame159.meteorclient.systems.modules.render.LogoutSpots;
import minegame159.meteorclient.systems.modules.render.Nametags;
import minegame159.meteorclient.systems.modules.render.NoRender;
import minegame159.meteorclient.systems.modules.render.StorageESP;
import minegame159.meteorclient.systems.modules.render.TimeChanger;
import minegame159.meteorclient.systems.modules.render.Tracers;
import minegame159.meteorclient.systems.modules.render.Trail;
import minegame159.meteorclient.systems.modules.render.Trajectories;
import minegame159.meteorclient.systems.modules.render.UnfocusedCPU;
import minegame159.meteorclient.systems.modules.render.VoidESP;
import minegame159.meteorclient.systems.modules.render.WallHack;
import minegame159.meteorclient.systems.modules.render.WaypointsModule;
import minegame159.meteorclient.systems.modules.render.Xray;
import minegame159.meteorclient.systems.modules.render.Zoom;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.search.Search;
import minegame159.meteorclient.systems.modules.world.AirPlace;
import minegame159.meteorclient.systems.modules.world.Ambience;
import minegame159.meteorclient.systems.modules.world.AntiCactus;
import minegame159.meteorclient.systems.modules.world.AutoBreed;
import minegame159.meteorclient.systems.modules.world.AutoBrewer;
import minegame159.meteorclient.systems.modules.world.AutoMount;
import minegame159.meteorclient.systems.modules.world.AutoNametag;
import minegame159.meteorclient.systems.modules.world.AutoShearer;
import minegame159.meteorclient.systems.modules.world.AutoSign;
import minegame159.meteorclient.systems.modules.world.AutoSmelter;
import minegame159.meteorclient.systems.modules.world.AutoSteal;
import minegame159.meteorclient.systems.modules.world.BuildHeight;
import minegame159.meteorclient.systems.modules.world.EChestFarmer;
import minegame159.meteorclient.systems.modules.world.EndermanLook;
import minegame159.meteorclient.systems.modules.world.EntityLogger;
import minegame159.meteorclient.systems.modules.world.Flamethrower;
import minegame159.meteorclient.systems.modules.world.InfinityMiner;
import minegame159.meteorclient.systems.modules.world.InstaMine;
import minegame159.meteorclient.systems.modules.world.LiquidFiller;
import minegame159.meteorclient.systems.modules.world.MountBypass;
import minegame159.meteorclient.systems.modules.world.Nuker;
import minegame159.meteorclient.systems.modules.world.PacketMine;
import minegame159.meteorclient.systems.modules.world.StashFinder;
import minegame159.meteorclient.systems.modules.world.Timer;
import minegame159.meteorclient.systems.modules.world.VeinMiner;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.input.Input;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.minecraft.class_3545;
import net.minecraft.class_5321;

public class Modules
extends System<Modules> {
    public static final /* synthetic */ ModuleRegistry REGISTRY;
    private static final /* synthetic */ List<Category> CATEGORIES;
    private final /* synthetic */ List<Module> modules;
    public static /* synthetic */ boolean REGISTERING_CATEGORIES;
    private final /* synthetic */ List<Module> active;
    private final /* synthetic */ Map<Category, List<Module>> groups;
    private /* synthetic */ Module moduleToBind;
    private final /* synthetic */ Map<Class<? extends Module>, Module> moduleInstances;

    public <T extends Module> T get(Class<T> llllllllllllllllllIIllIIlllIIIll) {
        Modules llllllllllllllllllIIllIIlllIIllI;
        return (T)llllllllllllllllllIIllIIlllIIllI.moduleInstances.get(llllllllllllllllllIIllIIlllIIIll);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    private void onGameLeft(GameLeftEvent llllllllllllllllllIIllIIIIlIIIll) {
        Modules llllllllllllllllllIIllIIIIlIIlII;
        List<Module> llllllllllllllllllIIllIIIIlIIIIl = llllllllllllllllllIIllIIIIlIIlII.active;
        synchronized (llllllllllllllllllIIllIIIIlIIIIl) {
            for (Module llllllllllllllllllIIllIIIIlIIlIl : llllllllllllllllllIIllIIIIlIIlII.modules) {
                if (!llllllllllllllllllIIllIIIIlIIlIl.isActive()) continue;
                MeteorClient.EVENT_BUS.unsubscribe(llllllllllllllllllIIllIIIIlIIlIl);
                llllllllllllllllllIIllIIIIlIIlIl.onDeactivate();
            }
        }
    }

    private void initRender() {
        Modules llllllllllllllllllIIlIllllIlIlIl;
        llllllllllllllllllIIlIllllIlIlIl.add(new BetterTooltips());
        llllllllllllllllllIIlIllllIlIlIl.add(new BlockSelection());
        llllllllllllllllllIIlIllllIlIlIl.add(new BossStack());
        llllllllllllllllllIIlIllllIlIlIl.add(new Breadcrumbs());
        llllllllllllllllllIIlIllllIlIlIl.add(new BreakIndicators());
        llllllllllllllllllIIlIllllIlIlIl.add(new CameraTweaks());
        llllllllllllllllllIIlIllllIlIlIl.add(new Chams());
        llllllllllllllllllIIlIllllIlIlIl.add(new CityESP());
        llllllllllllllllllIIlIllllIlIlIl.add(new CustomFOV());
        llllllllllllllllllIIlIllllIlIlIl.add(new EntityOwner());
        llllllllllllllllllIIlIllllIlIlIl.add(new ESP());
        llllllllllllllllllIIlIllllIlIlIl.add(new Freecam());
        llllllllllllllllllIIlIllllIlIlIl.add(new FreeLook());
        llllllllllllllllllIIlIllllIlIlIl.add(new Fullbright());
        llllllllllllllllllIIlIllllIlIlIl.add(new HandView());
        llllllllllllllllllIIlIllllIlIlIl.add(new HoleESP());
        llllllllllllllllllIIlIllllIlIlIl.add(new ItemPhysics());
        llllllllllllllllllIIlIllllIlIlIl.add(new ItemHighlight());
        llllllllllllllllllIIlIllllIlIlIl.add(new LightOverlay());
        llllllllllllllllllIIlIllllIlIlIl.add(new LogoutSpots());
        llllllllllllllllllIIlIllllIlIlIl.add(new Nametags());
        llllllllllllllllllIIlIllllIlIlIl.add(new NoRender());
        llllllllllllllllllIIlIllllIlIlIl.add(new Search());
        llllllllllllllllllIIlIllllIlIlIl.add(new StorageESP());
        llllllllllllllllllIIlIllllIlIlIl.add(new TimeChanger());
        llllllllllllllllllIIlIllllIlIlIl.add(new Tracers());
        llllllllllllllllllIIlIllllIlIlIl.add(new Trail());
        llllllllllllllllllIIlIllllIlIlIl.add(new Trajectories());
        llllllllllllllllllIIlIllllIlIlIl.add(new UnfocusedCPU());
        llllllllllllllllllIIlIllllIlIlIl.add(new VoidESP());
        llllllllllllllllllIIlIllllIlIlIl.add(new WallHack());
        llllllllllllllllllIIlIllllIlIlIl.add(new WaypointsModule());
        llllllllllllllllllIIlIllllIlIlIl.add(new Xray());
        llllllllllllllllllIIlIllllIlIlIl.add(new Zoom());
    }

    public static Iterable<Category> loopCategories() {
        return CATEGORIES;
    }

    public static Category getCategoryByHash(int llllllllllllllllllIIllIIlllIlIll) {
        for (Category llllllllllllllllllIIllIIlllIllIl : CATEGORIES) {
            if (llllllllllllllllllIIllIIlllIllIl.hashCode() != llllllllllllllllllIIllIIlllIlIll) continue;
            return llllllllllllllllllIIllIIlllIllIl;
        }
        return null;
    }

    public static void registerCategory(Category llllllllllllllllllIIllIIllllIIIl) {
        if (!REGISTERING_CATEGORIES) {
            throw new RuntimeException("Modules.registerCategory - Cannot register category outside of onRegisterCategories callback.");
        }
        CATEGORIES.add(llllllllllllllllllIIllIIllllIIIl);
    }

    private void initBedTrap() {
        Modules llllllllllllllllllIIlIlllllIIIlI;
        llllllllllllllllllIIlIlllllIIIlI.add(new AntiGhostBlock());
        llllllllllllllllllIIlIlllllIIIlI.add(new AntiRespawnLose());
        llllllllllllllllllIIlIlllllIIIlI.add(new AutoBuild());
        llllllllllllllllllIIlIlllllIIIlI.add(new AutoEz());
        llllllllllllllllllIIlIlllllIIIlI.add(new AutoLeave());
        llllllllllllllllllIIlIlllllIIIlI.add(new AutoLogin());
        llllllllllllllllllIIlIlllllIIIlI.add(new BedAuraTwo());
        llllllllllllllllllIIlIlllllIIIlI.add(new BedrockTravel());
        llllllllllllllllllIIlIlllllIIIlI.add(new ButtonTrap());
        llllllllllllllllllIIlIlllllIIIlI.add(new CrashExploits());
        llllllllllllllllllIIlIlllllIIIlI.add(new CrystalAuraTwo());
        llllllllllllllllllIIlIlllllIIIlI.add(new CrystalHead());
        llllllllllllllllllIIlIlllllIIIlI.add(new FastUseTwo());
        llllllllllllllllllIIlIlllllIIIlI.add(new HeadProtect());
        llllllllllllllllllIIlIlllllIIIlI.add(new InstaAutoCity());
        llllllllllllllllllIIlIlllllIIIlI.add(new minegame159.meteorclient.systems.modules.bedtrap.Notifier());
        llllllllllllllllllIIlIlllllIIIlI.add(new OldAnimations());
        llllllllllllllllllIIlIlllllIIIlI.add(new PacketFly());
        llllllllllllllllllIIlIlllllIIIlI.add(new Phase());
        llllllllllllllllllIIlIlllllIIIlI.add(new SevilaMode());
        llllllllllllllllllIIlIlllllIIIlI.add(new SkeletonESP());
        llllllllllllllllllIIlIlllllIIIlI.add(new StrictAutoTotem());
        llllllllllllllllllIIlIlllllIIIlI.add(new SurroundTwo());
        llllllllllllllllllIIlIlllllIIIlI.add(new NukerTwo());
    }

    @EventHandler(priority=100)
    private void onKey(KeyEvent llllllllllllllllllIIllIIIlIllIlI) {
        Modules llllllllllllllllllIIllIIIlIllIll;
        if (llllllllllllllllllIIllIIIlIllIlI.action == KeyAction.Repeat) {
            return;
        }
        llllllllllllllllllIIllIIIlIllIll.onAction(true, llllllllllllllllllIIllIIIlIllIlI.key, llllllllllllllllllIIllIIIlIllIlI.action == KeyAction.Press);
    }

    public boolean isActive(Class<? extends Module> llllllllllllllllllIIllIIllIlIIII) {
        Modules llllllllllllllllllIIllIIllIlIIIl;
        Module llllllllllllllllllIIllIIllIlIIlI = llllllllllllllllllIIllIIllIlIIIl.get(llllllllllllllllllIIllIIllIlIIII);
        return llllllllllllllllllIIllIIllIlIIlI != null && llllllllllllllllllIIllIIllIlIIlI.isActive();
    }

    @Override
    public class_2487 toTag() {
        Modules llllllllllllllllllIIllIIIIIIlIIl;
        class_2487 llllllllllllllllllIIllIIIIIIlIII = new class_2487();
        class_2499 llllllllllllllllllIIllIIIIIIIlll = new class_2499();
        for (Module llllllllllllllllllIIllIIIIIIlIlI : llllllllllllllllllIIllIIIIIIlIIl.getAll()) {
            class_2487 llllllllllllllllllIIllIIIIIIlIll = llllllllllllllllllIIllIIIIIIlIlI.toTag();
            if (llllllllllllllllllIIllIIIIIIlIll == null) continue;
            llllllllllllllllllIIllIIIIIIIlll.add((Object)llllllllllllllllllIIllIIIIIIlIll);
        }
        llllllllllllllllllIIllIIIIIIlIII.method_10566("modules", (class_2520)llllllllllllllllllIIllIIIIIIIlll);
        return llllllllllllllllllIIllIIIIIIlIII;
    }

    public int getCount() {
        Modules llllllllllllllllllIIllIIllIIIIII;
        return llllllllllllllllllIIllIIllIIIIII.moduleInstances.values().size();
    }

    @EventHandler(priority=100)
    private void onMouseButton(MouseButtonEvent llllllllllllllllllIIllIIIlIlIlII) {
        Modules llllllllllllllllllIIllIIIlIlIlIl;
        if (llllllllllllllllllIIllIIIlIlIlII.action == KeyAction.Repeat) {
            return;
        }
        llllllllllllllllllIIllIIIlIlIlIl.onAction(false, llllllllllllllllllIIllIIIlIlIlII.button, llllllllllllllllllIIllIIIlIlIlII.action == KeyAction.Press);
    }

    public List<Module> getList() {
        Modules llllllllllllllllllIIllIIllIIIIll;
        return llllllllllllllllllIIllIIllIIIIll.modules;
    }

    private boolean onBinding(boolean llllllllllllllllllIIllIIIlIlllll, int llllllllllllllllllIIllIIIlIllllI) {
        Modules llllllllllllllllllIIllIIIllIIIll;
        if (llllllllllllllllllIIllIIIllIIIll.moduleToBind != null && llllllllllllllllllIIllIIIllIIIll.moduleToBind.keybind.canBindTo(llllllllllllllllllIIllIIIlIlllll, llllllllllllllllllIIllIIIlIllllI)) {
            if (llllllllllllllllllIIllIIIlIllllI != 256) {
                llllllllllllllllllIIllIIIllIIIll.moduleToBind.keybind.set(llllllllllllllllllIIllIIIlIlllll, llllllllllllllllllIIllIIIlIllllI);
                ChatUtils.info("KeyBinds", "Module (highlight)%s (default)bound to (highlight)%s(default).", llllllllllllllllllIIllIIIllIIIll.moduleToBind.title, llllllllllllllllllIIllIIIllIIIll.moduleToBind.keybind);
            }
            MeteorClient.EVENT_BUS.post(ModuleBindChangedEvent.get(llllllllllllllllllIIllIIIllIIIll.moduleToBind));
            llllllllllllllllllIIllIIIllIIIll.moduleToBind = null;
            return true;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void removeActive(Module llllllllllllllllllIIllIIIllllIll) {
        Modules llllllllllllllllllIIllIIIllllllI;
        List<Module> llllllllllllllllllIIllIIIllllIlI = llllllllllllllllllIIllIIIllllllI.active;
        synchronized (llllllllllllllllllIIllIIIllllIlI) {
            if (llllllllllllllllllIIllIIIllllllI.active.remove(llllllllllllllllllIIllIIIllllIll)) {
                MeteorClient.EVENT_BUS.post(ActiveModulesChangedEvent.get());
            }
        }
    }

    @EventHandler(priority=201)
    private void onOpenScreen(OpenScreenEvent llllllllllllllllllIIllIIIIlllIll) {
        Modules llllllllllllllllllIIllIIIIllllII;
        if (!Utils.canUpdate()) {
            return;
        }
        for (Module llllllllllllllllllIIllIIIIllllIl : llllllllllllllllllIIllIIIIllllII.moduleInstances.values()) {
            if (!llllllllllllllllllIIllIIIIllllIl.toggleOnBindRelease || !llllllllllllllllllIIllIIIIllllIl.isActive()) continue;
            llllllllllllllllllIIllIIIIllllIl.toggle();
            llllllllllllllllllIIllIIIIllllIl.sendToggledMsg();
        }
    }

    public static Modules get() {
        return Systems.get(Modules.class);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public List<Module> getActive() {
        Modules llllllllllllllllllIIllIIlIllllII;
        List<Module> llllllllllllllllllIIllIIlIlllIlI = llllllllllllllllllIIllIIlIllllII.active;
        synchronized (llllllllllllllllllIIllIIlIlllIlI) {
            return llllllllllllllllllIIllIIlIllllII.active;
        }
    }

    private void initCombat() {
        Modules llllllllllllllllllIIlIllllIlllll;
        llllllllllllllllllIIlIllllIlllll.add(new AimAssist());
        llllllllllllllllllIIlIllllIlllll.add(new AnchorAura());
        llllllllllllllllllIIlIllllIlllll.add(new AntiAnvil());
        llllllllllllllllllIIlIllllIlllll.add(new AntiBed());
        llllllllllllllllllIIlIllllIlllll.add(new AntiHit());
        llllllllllllllllllIIlIllllIlllll.add(new ArrowDodge());
        llllllllllllllllllIIlIllllIlllll.add(new AutoAnvil());
        llllllllllllllllllIIlIllllIlllll.add(new AutoArmor());
        llllllllllllllllllIIlIllllIlllll.add(new AutoCity());
        llllllllllllllllllIIlIllllIlllll.add(new AutoTotem());
        llllllllllllllllllIIlIllllIlllll.add(new AutoTrap());
        llllllllllllllllllIIlIllllIlllll.add(new AutoWeapon());
        llllllllllllllllllIIlIllllIlllll.add(new AutoWeb());
        llllllllllllllllllIIlIllllIlllll.add(new BedAura());
        llllllllllllllllllIIlIllllIlllll.add(new BowAimbot());
        llllllllllllllllllIIlIllllIlllll.add(new BowSpam());
        llllllllllllllllllIIlIllllIlllll.add(new Burrow());
        llllllllllllllllllIIlIllllIlllll.add(new Criticals());
        llllllllllllllllllIIlIllllIlllll.add(new CrystalAura());
        llllllllllllllllllIIlIllllIlllll.add(new Hitboxes());
        llllllllllllllllllIIlIllllIlllll.add(new HoleFiller());
        llllllllllllllllllIIlIllllIlllll.add(new KillAura());
        llllllllllllllllllIIlIllllIlllll.add(new Offhand());
        llllllllllllllllllIIlIllllIlllll.add(new Quiver());
        llllllllllllllllllIIlIllllIlllll.add(new SelfAnvil());
        llllllllllllllllllIIlIllllIlllll.add(new SelfTrap());
        llllllllllllllllllIIlIllllIlllll.add(new SelfWeb());
        llllllllllllllllllIIlIllllIlllll.add(new Surround());
    }

    public void add(Module llllllllllllllllllIIlIlllllIIlIl) {
        Modules llllllllllllllllllIIlIlllllIIllI;
        if (!CATEGORIES.contains(llllllllllllllllllIIlIlllllIIlIl.category)) {
            throw new RuntimeException("Modules.addModule - Module's category was not registered.");
        }
        AtomicReference llllllllllllllllllIIlIlllllIIlll = new AtomicReference();
        if (llllllllllllllllllIIlIlllllIIllI.moduleInstances.values().removeIf(llllllllllllllllllIIlIllllIIIlII -> {
            if (llllllllllllllllllIIlIllllIIIlII.name.equals(llllllllllllllllllIIlIllllIIIIll.name)) {
                llllllllllllllllllIIlIlllllIIlll.set(llllllllllllllllllIIlIllllIIIlII);
                llllllllllllllllllIIlIllllIIIlII.settings.unregisterColorSettings();
                return true;
            }
            return false;
        })) {
            llllllllllllllllllIIlIlllllIIllI.getGroup(((Module)llllllllllllllllllIIlIlllllIIlll.get()).category).remove(llllllllllllllllllIIlIlllllIIlll.get());
        }
        llllllllllllllllllIIlIlllllIIllI.moduleInstances.put(llllllllllllllllllIIlIlllllIIlIl.getClass(), llllllllllllllllllIIlIlllllIIlIl);
        llllllllllllllllllIIlIlllllIIllI.modules.add(llllllllllllllllllIIlIlllllIIlIl);
        llllllllllllllllllIIlIlllllIIllI.getGroup(llllllllllllllllllIIlIlllllIIlIl.category).add(llllllllllllllllllIIlIlllllIIlIl);
        llllllllllllllllllIIlIlllllIIlIl.settings.registerColorSettings(llllllllllllllllllIIlIlllllIIlIl);
    }

    @EventHandler(priority=200)
    private void onKeyBinding(KeyEvent llllllllllllllllllIIllIIIllIllIl) {
        Modules llllllllllllllllllIIllIIIlllIIII;
        if (llllllllllllllllllIIllIIIlllIIII.onBinding(true, llllllllllllllllllIIllIIIllIllIl.key)) {
            llllllllllllllllllIIllIIIllIllIl.cancel();
        }
    }

    @EventHandler(priority=200)
    private void onButtonBinding(MouseButtonEvent llllllllllllllllllIIllIIIllIIlll) {
        Modules llllllllllllllllllIIllIIIllIlIII;
        if (llllllllllllllllllIIllIIIllIlIII.onBinding(false, llllllllllllllllllIIllIIIllIIlll.button)) {
            llllllllllllllllllIIllIIIllIIlll.cancel();
        }
    }

    public Module get(String llllllllllllllllllIIllIIllIlllII) {
        Modules llllllllllllllllllIIllIIllIlllIl;
        for (Module llllllllllllllllllIIllIIllIllllI : llllllllllllllllllIIllIIllIlllIl.moduleInstances.values()) {
            if (!llllllllllllllllllIIllIIllIllllI.name.equalsIgnoreCase(llllllllllllllllllIIllIIllIlllII)) continue;
            return llllllllllllllllllIIllIIllIllllI;
        }
        return null;
    }

    private void initPlayer() {
        Modules llllllllllllllllllIIlIllllIlllII;
        llllllllllllllllllIIlIllllIlllII.add(new AntiHunger());
        llllllllllllllllllIIlIllllIlllII.add(new AutoEat());
        llllllllllllllllllIIlIllllIlllII.add(new AutoFish());
        llllllllllllllllllIIlIllllIlllII.add(new AutoGap());
        llllllllllllllllllIIlIllllIlllII.add(new AutoMend());
        llllllllllllllllllIIlIllllIlllII.add(new AutoReplenish());
        llllllllllllllllllIIlIllllIlllII.add(new AutoTool());
        llllllllllllllllllIIlIllllIlllII.add(new ChestSwap());
        llllllllllllllllllIIlIllllIlllII.add(new EXPThrower());
        llllllllllllllllllIIlIllllIlllII.add(new FakePlayer());
        llllllllllllllllllIIlIllllIlllII.add(new FastUse());
        llllllllllllllllllIIlIllllIlllII.add(new GhostHand());
        llllllllllllllllllIIlIllllIlllII.add(new LiquidInteract());
        llllllllllllllllllIIlIllllIlllII.add(new MiddleClickExtra());
        llllllllllllllllllIIlIllllIlllII.add(new NoBreakDelay());
        llllllllllllllllllIIlIllllIlllII.add(new NoInteract());
        llllllllllllllllllIIlIllllIlllII.add(new NoMiningTrace());
        llllllllllllllllllIIlIllllIlllII.add(new NoRotate());
        llllllllllllllllllIIlIllllIlllII.add(new OffhandCrash());
        llllllllllllllllllIIlIllllIlllII.add(new Portals());
        llllllllllllllllllIIlIllllIlllII.add(new PotionSaver());
        llllllllllllllllllIIlIllllIlllII.add(new PotionSpoof());
        llllllllllllllllllIIlIllllIlllII.add(new Reach());
        llllllllllllllllllIIlIllllIlllII.add(new Rotation());
        llllllllllllllllllIIlIllllIlllII.add(new SpeedMine());
        llllllllllllllllllIIlIllllIlllII.add(new XCarry());
    }

    static {
        REGISTRY = new ModuleRegistry();
        CATEGORIES = new ArrayList<Category>();
    }

    public List<class_3545<Module, Integer>> searchTitles(String llllllllllllllllllIIllIIlIlIllII) {
        Modules llllllllllllllllllIIllIIlIlIllIl;
        ArrayList<class_3545<Module, Integer>> llllllllllllllllllIIllIIlIlIlllI = new ArrayList<class_3545<Module, Integer>>();
        for (Module llllllllllllllllllIIllIIlIllIIIl : llllllllllllllllllIIllIIlIlIllIl.moduleInstances.values()) {
            int llllllllllllllllllIIllIIlIllIIlI = Utils.search(llllllllllllllllllIIllIIlIllIIIl.title, llllllllllllllllllIIllIIlIlIllII);
            if (llllllllllllllllllIIllIIlIllIIlI <= 0) continue;
            llllllllllllllllllIIllIIlIlIlllI.add((class_3545<Module, Integer>)new class_3545((Object)llllllllllllllllllIIllIIlIllIIIl, (Object)llllllllllllllllllIIllIIlIllIIlI));
        }
        llllllllllllllllllIIllIIlIlIlllI.sort(Comparator.comparingInt(llllllllllllllllllIIlIlllIlllIll -> -((Integer)llllllllllllllllllIIlIlllIlllIll.method_15441()).intValue()));
        return llllllllllllllllllIIllIIlIlIlllI;
    }

    private void initMovement() {
        Modules llllllllllllllllllIIlIllllIllIIl;
        llllllllllllllllllIIlIllllIllIIl.add(new AirJump());
        llllllllllllllllllIIlIllllIllIIl.add(new Anchor());
        llllllllllllllllllIIlIllllIllIIl.add(new AntiAFK());
        llllllllllllllllllIIlIllllIllIIl.add(new AntiLevitation());
        llllllllllllllllllIIlIllllIllIIl.add(new AntiVoid());
        llllllllllllllllllIIlIllllIllIIl.add(new AutoJump());
        llllllllllllllllllIIlIllllIllIIl.add(new AutoWalk());
        llllllllllllllllllIIlIllllIllIIl.add(new Blink());
        llllllllllllllllllIIlIllllIllIIl.add(new BoatFly());
        llllllllllllllllllIIlIllllIllIIl.add(new ClickTP());
        llllllllllllllllllIIlIllllIllIIl.add(new ElytraBoost());
        llllllllllllllllllIIlIllllIllIIl.add(new ElytraFly());
        llllllllllllllllllIIlIllllIllIIl.add(new EntityControl());
        llllllllllllllllllIIlIllllIllIIl.add(new EntitySpeed());
        llllllllllllllllllIIlIllllIllIIl.add(new FastClimb());
        llllllllllllllllllIIlIllllIllIIl.add(new Flight());
        llllllllllllllllllIIlIllllIllIIl.add(new GUIMove());
        llllllllllllllllllIIlIllllIllIIl.add(new HighJump());
        llllllllllllllllllIIlIllllIllIIl.add(new Jesus());
        llllllllllllllllllIIlIllllIllIIl.add(new NoFall());
        llllllllllllllllllIIlIllllIllIIl.add(new NoSlow());
        llllllllllllllllllIIlIllllIllIIl.add(new Parkour());
        llllllllllllllllllIIlIllllIllIIl.add(new ReverseStep());
        llllllllllllllllllIIlIllllIllIIl.add(new SafeWalk());
        llllllllllllllllllIIlIllllIllIIl.add(new Scaffold());
        llllllllllllllllllIIlIllllIllIIl.add(new Slippy());
        llllllllllllllllllIIlIllllIllIIl.add(new Sneak());
        llllllllllllllllllIIlIllllIllIIl.add(new Speed());
        llllllllllllllllllIIlIllllIllIIl.add(new Spider());
        llllllllllllllllllIIlIllllIllIIl.add(new Sprint());
        llllllllllllllllllIIlIllllIllIIl.add(new Step());
        llllllllllllllllllIIlIllllIllIIl.add(new Velocity());
    }

    private void initWorld() {
        Modules llllllllllllllllllIIlIllllIlIIll;
        llllllllllllllllllIIlIllllIlIIll.add(new AirPlace());
        llllllllllllllllllIIlIllllIlIIll.add(new Ambience());
        llllllllllllllllllIIlIllllIlIIll.add(new AntiCactus());
        llllllllllllllllllIIlIllllIlIIll.add(new AutoBreed());
        llllllllllllllllllIIlIllllIlIIll.add(new AutoBrewer());
        llllllllllllllllllIIlIllllIlIIll.add(new AutoMount());
        llllllllllllllllllIIlIllllIlIIll.add(new AutoNametag());
        llllllllllllllllllIIlIllllIlIIll.add(new AutoShearer());
        llllllllllllllllllIIlIllllIlIIll.add(new AutoSign());
        llllllllllllllllllIIlIllllIlIIll.add(new AutoSmelter());
        llllllllllllllllllIIlIllllIlIIll.add(new AutoSteal());
        llllllllllllllllllIIlIllllIlIIll.add(new BuildHeight());
        llllllllllllllllllIIlIllllIlIIll.add(new EChestFarmer());
        llllllllllllllllllIIlIllllIlIIll.add(new EndermanLook());
        llllllllllllllllllIIlIllllIlIIll.add(new EntityLogger());
        llllllllllllllllllIIlIllllIlIIll.add(new Flamethrower());
        llllllllllllllllllIIlIllllIlIIll.add(new InfinityMiner());
        llllllllllllllllllIIlIllllIlIIll.add(new InstaMine());
        llllllllllllllllllIIlIllllIlIIll.add(new LiquidFiller());
        llllllllllllllllllIIlIllllIlIIll.add(new MountBypass());
        llllllllllllllllllIIlIllllIlIIll.add(new Nuker());
        llllllllllllllllllIIlIllllIlIIll.add(new PacketMine());
        llllllllllllllllllIIlIllllIlIIll.add(new StashFinder());
        llllllllllllllllllIIlIllllIlIIll.add(new Timer());
        llllllllllllllllllIIlIllllIlIIll.add(new VeinMiner());
    }

    @Override
    public Modules fromTag(class_2487 llllllllllllllllllIIlIllllllIlIl) {
        Modules llllllllllllllllllIIlIllllllIIll;
        llllllllllllllllllIIlIllllllIIll.disableAll();
        class_2499 llllllllllllllllllIIlIllllllIlII = llllllllllllllllllIIlIllllllIlIl.method_10554("modules", 10);
        for (class_2520 llllllllllllllllllIIlIllllllIlll : llllllllllllllllllIIlIllllllIlII) {
            class_2487 llllllllllllllllllIIlIlllllllIIl = (class_2487)llllllllllllllllllIIlIllllllIlll;
            Module llllllllllllllllllIIlIlllllllIII = llllllllllllllllllIIlIllllllIIll.get(llllllllllllllllllIIlIlllllllIIl.method_10558("name"));
            if (llllllllllllllllllIIlIlllllllIII == null) continue;
            llllllllllllllllllIIlIlllllllIII.fromTag(llllllllllllllllllIIlIlllllllIIl);
        }
        return llllllllllllllllllIIlIllllllIIll;
    }

    public List<Module> getGroup(Category llllllllllllllllllIIllIIllIIlIll) {
        Modules llllllllllllllllllIIllIIllIIlIlI;
        return llllllllllllllllllIIllIIllIIlIlI.groups.computeIfAbsent(llllllllllllllllllIIllIIllIIlIll, llllllllllllllllllIIlIlllIlllIlI -> new ArrayList());
    }

    public Modules() {
        super("modules");
        Modules llllllllllllllllllIIllIlIIIIIIII;
        llllllllllllllllllIIllIlIIIIIIII.modules = new ArrayList<Module>();
        llllllllllllllllllIIllIlIIIIIIII.moduleInstances = new HashMap<Class<? extends Module>, Module>();
        llllllllllllllllllIIllIlIIIIIIII.groups = new HashMap<Category, List<Module>>();
        llllllllllllllllllIIllIlIIIIIIII.active = new ArrayList<Module>();
    }

    private void onAction(boolean llllllllllllllllllIIllIIIlIIIlIl, int llllllllllllllllllIIllIIIlIIIlII, boolean llllllllllllllllllIIllIIIlIIIIll) {
        if (Utils.mc.field_1755 == null && !Input.isKeyPressed(292)) {
            Modules llllllllllllllllllIIllIIIlIIlIlI;
            for (Module llllllllllllllllllIIllIIIlIIlIll : llllllllllllllllllIIllIIIlIIlIlI.moduleInstances.values()) {
                if (!llllllllllllllllllIIllIIIlIIlIll.keybind.matches(llllllllllllllllllIIllIIIlIIIlIl, llllllllllllllllllIIllIIIlIIIlII) || !llllllllllllllllllIIllIIIlIIIIll && !llllllllllllllllllIIllIIIlIIlIll.toggleOnBindRelease) continue;
                llllllllllllllllllIIllIIIlIIlIll.toggle();
                llllllllllllllllllIIllIIIlIIlIll.sendToggledMsg();
            }
        }
    }

    private void initMisc() {
        Modules llllllllllllllllllIIlIllllIIllll;
        llllllllllllllllllIIlIllllIIllll.add(new Swarm());
        llllllllllllllllllIIlIllllIIllll.add(new Announcer());
        llllllllllllllllllIIlIllllIIllll.add(new AntiPacketKick());
        llllllllllllllllllIIlIllllIIllll.add(new AutoClicker());
        llllllllllllllllllIIlIllllIIllll.add(new AutoLog());
        llllllllllllllllllIIlIllllIIllll.add(new AutoReconnect());
        llllllllllllllllllIIlIllllIIllll.add(new AutoRespawn());
        llllllllllllllllllIIlIllllIIllll.add(new BetterChat());
        llllllllllllllllllIIlIllllIIllll.add(new BetterTab());
        llllllllllllllllllIIlIllllIIllll.add(new BookBot());
        llllllllllllllllllIIlIllllIIllll.add(new DiscordPresence());
        llllllllllllllllllIIlIllllIIllll.add(new MessageAura());
        llllllllllllllllllIIlIllllIIllll.add(new MiddleClickFriend());
        llllllllllllllllllIIlIllllIIllll.add(new NameProtect());
        llllllllllllllllllIIlIllllIIllll.add(new Notebot());
        llllllllllllllllllIIlIllllIIllll.add(new Notifier());
        llllllllllllllllllIIlIllllIIllll.add(new PacketCanceller());
        llllllllllllllllllIIlIllllIIllll.add(new SoundBlocker());
        llllllllllllllllllIIlIllllIIllll.add(new Spam());
        llllllllllllllllllIIlIllllIIllll.add(new TPSSync());
        llllllllllllllllllIIlIllllIIllll.add(new VanillaSpoof());
        llllllllllllllllllIIlIllllIIllll.add(new InventoryTweaks());
    }

    public Collection<Module> getAll() {
        Modules llllllllllllllllllIIllIIllIIIlll;
        return llllllllllllllllllIIllIIllIIIlll.moduleInstances.values();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void disableAll() {
        Modules llllllllllllllllllIIllIIIIIlIllI;
        List<Module> llllllllllllllllllIIllIIIIIlIlIl = llllllllllllllllllIIllIIIIIlIllI.active;
        synchronized (llllllllllllllllllIIllIIIIIlIlIl) {
            for (Module llllllllllllllllllIIllIIIIIllIII : llllllllllllllllllIIllIIIIIlIllI.modules) {
                if (!llllllllllllllllllIIllIIIIIllIII.isActive()) continue;
                llllllllllllllllllIIllIIIIIllIII.toggle(Utils.canUpdate());
            }
        }
    }

    public void sortModules() {
        Modules llllllllllllllllllIIllIIllllIllI;
        for (List<Module> llllllllllllllllllIIllIIlllllIII : llllllllllllllllllIIllIIllllIllI.groups.values()) {
            llllllllllllllllllIIllIIlllllIII.sort(Comparator.comparing(llllllllllllllllllIIlIlllIllIlII -> llllllllllllllllllIIlIlllIllIlII.title));
        }
        llllllllllllllllllIIllIIllllIllI.modules.sort(Comparator.comparing(llllllllllllllllllIIlIlllIlllIII -> llllllllllllllllllIIlIlllIlllIII.title));
    }

    @Override
    public void init() {
        Modules llllllllllllllllllIIllIIllllllIl;
        llllllllllllllllllIIllIIllllllIl.initBedTrap();
        llllllllllllllllllIIllIIllllllIl.initCombat();
        llllllllllllllllllIIllIIllllllIl.initPlayer();
        llllllllllllllllllIIllIIllllllIl.initMovement();
        llllllllllllllllllIIllIIllllllIl.initRender();
        llllllllllllllllllIIllIIllllllIl.initWorld();
        llllllllllllllllllIIllIIllllllIl.initMisc();
        llllllllllllllllllIIllIIllllllIl.add(new HUD());
    }

    public void setModuleToBind(Module llllllllllllllllllIIllIIIlllIlIl) {
        llllllllllllllllllIIllIIIlllIllI.moduleToBind = llllllllllllllllllIIllIIIlllIlIl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    private void onGameJoined(GameJoinedEvent llllllllllllllllllIIllIIIIllIIII) {
        Modules llllllllllllllllllIIllIIIIlIllll;
        List<Module> llllllllllllllllllIIllIIIIlIlllI = llllllllllllllllllIIllIIIIlIllll.active;
        synchronized (llllllllllllllllllIIllIIIIlIlllI) {
            for (Module llllllllllllllllllIIllIIIIllIIlI : llllllllllllllllllIIllIIIIlIllll.modules) {
                if (!llllllllllllllllllIIllIIIIllIIlI.isActive()) continue;
                MeteorClient.EVENT_BUS.subscribe(llllllllllllllllllIIllIIIIllIIlI);
                llllllllllllllllllIIllIIIIllIIlI.onActivate();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void addActive(Module llllllllllllllllllIIllIIlIIIIlll) {
        Modules llllllllllllllllllIIllIIlIIIlIII;
        List<Module> llllllllllllllllllIIllIIlIIIIlII = llllllllllllllllllIIllIIlIIIlIII.active;
        synchronized (llllllllllllllllllIIllIIlIIIIlII) {
            if (!llllllllllllllllllIIllIIlIIIlIII.active.contains(llllllllllllllllllIIllIIlIIIIlll)) {
                llllllllllllllllllIIllIIlIIIlIII.active.add(llllllllllllllllllIIllIIlIIIIlll);
                MeteorClient.EVENT_BUS.post(ActiveModulesChangedEvent.get());
            }
        }
    }

    public List<class_3545<Module, Integer>> searchSettingTitles(String llllllllllllllllllIIllIIlIIlIlIl) {
        Modules llllllllllllllllllIIllIIlIIlIllI;
        ArrayList<class_3545<Module, Integer>> llllllllllllllllllIIllIIlIIlIlll = new ArrayList<class_3545<Module, Integer>>();
        for (Module llllllllllllllllllIIllIIlIIllIlI : llllllllllllllllllIIllIIlIIlIllI.moduleInstances.values()) {
            block1: for (SettingGroup llllllllllllllllllIIllIIlIIllIll : llllllllllllllllllIIllIIlIIllIlI.settings) {
                for (Setting<?> llllllllllllllllllIIllIIlIIlllII : llllllllllllllllllIIllIIlIIllIll) {
                    int llllllllllllllllllIIllIIlIIlllIl = Utils.search(llllllllllllllllllIIllIIlIIlllII.title, llllllllllllllllllIIllIIlIIlIlIl);
                    if (llllllllllllllllllIIllIIlIIlllIl <= 0) continue;
                    llllllllllllllllllIIllIIlIIlIlll.add((class_3545<Module, Integer>)new class_3545((Object)llllllllllllllllllIIllIIlIIllIlI, (Object)llllllllllllllllllIIllIIlIIlllIl));
                    continue block1;
                }
            }
        }
        llllllllllllllllllIIllIIlIIlIlll.sort(Comparator.comparingInt(llllllllllllllllllIIlIlllIllllll -> -((Integer)llllllllllllllllllIIlIlllIllllll.method_15441()).intValue()));
        return llllllllllllllllllIIllIIlIIlIlll;
    }

    public static class ModuleRegistry
    extends class_2378<Module> {
        @Nullable
        public Module get(@Nullable class_5321<Module> lIlIIlllIIIlIl) {
            return null;
        }

        public Lifecycle method_31138() {
            return null;
        }

        @Nullable
        public Module get(@Nullable class_2960 lIlIIlllIIIIll) {
            return null;
        }

        public Set<Map.Entry<class_5321<Module>, Module>> method_29722() {
            return null;
        }

        @Nullable
        public class_2960 getId(Module lIlIIlllIIlIll) {
            return null;
        }

        public Iterator<Module> iterator() {
            return new ModuleIterator();
        }

        public int getRawId(@Nullable Module lIlIIlllIIIlll) {
            return 0;
        }

        @Nullable
        public Module get(int lIlIIllIlllIlI) {
            return null;
        }

        protected Lifecycle getEntryLifecycle(Module lIlIIlllIIIIIl) {
            return null;
        }

        public boolean method_10250(class_2960 lIlIIllIllllII) {
            return false;
        }

        public ModuleRegistry() {
            super(class_5321.method_29180((class_2960)new class_2960("meteor-client", "modules")), Lifecycle.stable());
            ModuleRegistry lIlIIlllIIlllI;
        }

        public Optional<class_5321<Module>> getKey(Module lIlIIlllIIlIIl) {
            return Optional.empty();
        }

        public Set<class_2960> method_10235() {
            return null;
        }

        private static class ModuleIterator
        implements Iterator<Module> {
            private final /* synthetic */ Iterator<Module> iterator;

            private ModuleIterator() {
                ModuleIterator lllIlIIlIlIII;
                lllIlIIlIlIII.iterator = Modules.get().getAll().iterator();
            }

            @Override
            public boolean hasNext() {
                ModuleIterator lllIlIIlIIlIl;
                return lllIlIIlIIlIl.iterator.hasNext();
            }

            @Override
            public Module next() {
                ModuleIterator lllIlIIlIIIlI;
                return lllIlIIlIIIlI.iterator.next();
            }
        }
    }
}

