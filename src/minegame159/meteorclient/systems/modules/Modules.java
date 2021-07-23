/*
 * Decompiled with CFR 0.151.
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Modules
extends System<Modules> {
    public static final ModuleRegistry REGISTRY = new ModuleRegistry();
    private static final List<Category> CATEGORIES = new ArrayList<Category>();
    private final List<Module> modules = new ArrayList<Module>();
    public static boolean REGISTERING_CATEGORIES;
    private final List<Module> active;
    private final Map<Category, List<Module>> groups;
    private Module moduleToBind;
    private final Map<Class<? extends Module>, Module> moduleInstances = new HashMap<Class<? extends Module>, Module>();

    private static String lambda$sortModules$0(Module module) {
        return module.title;
    }

    public <T extends Module> T get(Class<T> clazz) {
        return (T)this.moduleInstances.get(clazz);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @EventHandler
    private void onGameLeft(GameLeftEvent gameLeftEvent) {
        List<Module> list = this.active;
        synchronized (list) {
            Iterator<Module> iterator = this.modules.iterator();
            while (iterator.hasNext()) {
                Module module = iterator.next();
                if (!module.isActive()) continue;
                MeteorClient.EVENT_BUS.unsubscribe(module);
                module.onDeactivate();
            }
            return;
        }
    }

    private static boolean lambda$add$5(Module module, AtomicReference atomicReference, Module module2) {
        if (module2.name.equals(module.name)) {
            atomicReference.set(module2);
            module2.settings.unregisterColorSettings();
            return true;
        }
        return false;
    }

    private void initRender() {
        this.add(new BetterTooltips());
        this.add(new BlockSelection());
        this.add(new BossStack());
        this.add(new Breadcrumbs());
        this.add(new BreakIndicators());
        this.add(new CameraTweaks());
        this.add(new Chams());
        this.add(new CityESP());
        this.add(new CustomFOV());
        this.add(new EntityOwner());
        this.add(new ESP());
        this.add(new Freecam());
        this.add(new FreeLook());
        this.add(new Fullbright());
        this.add(new HandView());
        this.add(new HoleESP());
        this.add(new ItemPhysics());
        this.add(new ItemHighlight());
        this.add(new LightOverlay());
        this.add(new LogoutSpots());
        this.add(new Nametags());
        this.add(new NoRender());
        this.add(new Search());
        this.add(new StorageESP());
        this.add(new TimeChanger());
        this.add(new Tracers());
        this.add(new Trail());
        this.add(new Trajectories());
        this.add(new UnfocusedCPU());
        this.add(new VoidESP());
        this.add(new WallHack());
        this.add(new WaypointsModule());
        this.add(new Xray());
        this.add(new Zoom());
    }

    public static Iterable<Category> loopCategories() {
        return CATEGORIES;
    }

    public static Category getCategoryByHash(int n) {
        for (Category category : CATEGORIES) {
            if (category.hashCode() != n) continue;
            return category;
        }
        return null;
    }

    public static void registerCategory(Category category) {
        if (!REGISTERING_CATEGORIES) {
            throw new RuntimeException("Modules.registerCategory - Cannot register category outside of onRegisterCategories callback.");
        }
        CATEGORIES.add(category);
    }

    private static List lambda$getGroup$2(Category category) {
        return new ArrayList();
    }

    private void initBedTrap() {
        this.add(new AntiGhostBlock());
        this.add(new AntiRespawnLose());
        this.add(new AutoBuild());
        this.add(new AutoEz());
        this.add(new AutoLeave());
        this.add(new AutoLogin());
        this.add(new BedAuraTwo());
        this.add(new BedrockTravel());
        this.add(new ButtonTrap());
        this.add(new CrashExploits());
        this.add(new CrystalAuraTwo());
        this.add(new CrystalHead());
        this.add(new FastUseTwo());
        this.add(new HeadProtect());
        this.add(new InstaAutoCity());
        this.add(new minegame159.meteorclient.systems.modules.bedtrap.Notifier());
        this.add(new OldAnimations());
        this.add(new PacketFly());
        this.add(new Phase());
        this.add(new SevilaMode());
        this.add(new SkeletonESP());
        this.add(new StrictAutoTotem());
        this.add(new SurroundTwo());
        this.add(new NukerTwo());
    }

    @EventHandler(priority=100)
    private void onKey(KeyEvent keyEvent) {
        if (keyEvent.action == KeyAction.Repeat) {
            return;
        }
        this.onAction(true, keyEvent.key, keyEvent.action == KeyAction.Press);
    }

    public boolean isActive(Class<? extends Module> clazz) {
        Module module = this.get(clazz);
        return module != null && module.isActive();
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_2499 class_24992 = new class_2499();
        for (Module module : this.getAll()) {
            class_2487 class_24873 = module.toTag();
            if (class_24873 == null) continue;
            class_24992.add((Object)class_24873);
        }
        class_24872.method_10566("modules", (class_2520)class_24992);
        return class_24872;
    }

    public int getCount() {
        return this.moduleInstances.values().size();
    }

    @EventHandler(priority=100)
    private void onMouseButton(MouseButtonEvent mouseButtonEvent) {
        if (mouseButtonEvent.action == KeyAction.Repeat) {
            return;
        }
        this.onAction(false, mouseButtonEvent.button, mouseButtonEvent.action == KeyAction.Press);
    }

    public List<Module> getList() {
        return this.modules;
    }

    private boolean onBinding(boolean bl, int n) {
        if (this.moduleToBind != null && this.moduleToBind.keybind.canBindTo(bl, n)) {
            if (n != 256) {
                this.moduleToBind.keybind.set(bl, n);
                ChatUtils.info("KeyBinds", "Module (highlight)%s (default)bound to (highlight)%s(default).", this.moduleToBind.title, this.moduleToBind.keybind);
            }
            MeteorClient.EVENT_BUS.post(ModuleBindChangedEvent.get(this.moduleToBind));
            this.moduleToBind = null;
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void removeActive(Module module) {
        List<Module> list = this.active;
        synchronized (list) {
            if (!this.active.remove(module)) return;
            MeteorClient.EVENT_BUS.post(ActiveModulesChangedEvent.get());
            return;
        }
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @EventHandler(priority=201)
    private void onOpenScreen(OpenScreenEvent openScreenEvent) {
        if (!Utils.canUpdate()) {
            return;
        }
        for (Module module : this.moduleInstances.values()) {
            if (!module.toggleOnBindRelease || !module.isActive()) continue;
            module.toggle();
            module.sendToggledMsg();
        }
    }

    public static Modules get() {
        return Systems.get(Modules.class);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public List<Module> getActive() {
        List<Module> list = this.active;
        // MONITORENTER : list
        // MONITOREXIT : list
        return this.active;
    }

    private void initCombat() {
        this.add(new AimAssist());
        this.add(new AnchorAura());
        this.add(new AntiAnvil());
        this.add(new AntiBed());
        this.add(new AntiHit());
        this.add(new ArrowDodge());
        this.add(new AutoAnvil());
        this.add(new AutoArmor());
        this.add(new AutoCity());
        this.add(new AutoTotem());
        this.add(new AutoTrap());
        this.add(new AutoWeapon());
        this.add(new AutoWeb());
        this.add(new BedAura());
        this.add(new BowAimbot());
        this.add(new BowSpam());
        this.add(new Burrow());
        this.add(new Criticals());
        this.add(new CrystalAura());
        this.add(new Hitboxes());
        this.add(new HoleFiller());
        this.add(new KillAura());
        this.add(new Offhand());
        this.add(new Quiver());
        this.add(new SelfAnvil());
        this.add(new SelfTrap());
        this.add(new SelfWeb());
        this.add(new Surround());
    }

    public void add(Module module) {
        if (!CATEGORIES.contains(module.category)) {
            throw new RuntimeException("Modules.addModule - Module's category was not registered.");
        }
        AtomicReference atomicReference = new AtomicReference();
        if (this.moduleInstances.values().removeIf(arg_0 -> Modules.lambda$add$5(module, atomicReference, arg_0))) {
            this.getGroup(((Module)atomicReference.get()).category).remove(atomicReference.get());
        }
        this.moduleInstances.put(module.getClass(), module);
        this.modules.add(module);
        this.getGroup(module.category).add(module);
        module.settings.registerColorSettings(module);
    }

    @EventHandler(priority=200)
    private void onKeyBinding(KeyEvent keyEvent) {
        if (this.onBinding(true, keyEvent.key)) {
            keyEvent.cancel();
        }
    }

    @EventHandler(priority=200)
    private void onButtonBinding(MouseButtonEvent mouseButtonEvent) {
        if (this.onBinding(false, mouseButtonEvent.button)) {
            mouseButtonEvent.cancel();
        }
    }

    public Module get(String string) {
        for (Module module : this.moduleInstances.values()) {
            if (!module.name.equalsIgnoreCase(string)) continue;
            return module;
        }
        return null;
    }

    private void initPlayer() {
        this.add(new AntiHunger());
        this.add(new AutoEat());
        this.add(new AutoFish());
        this.add(new AutoGap());
        this.add(new AutoMend());
        this.add(new AutoReplenish());
        this.add(new AutoTool());
        this.add(new ChestSwap());
        this.add(new EXPThrower());
        this.add(new FakePlayer());
        this.add(new FastUse());
        this.add(new GhostHand());
        this.add(new LiquidInteract());
        this.add(new MiddleClickExtra());
        this.add(new NoBreakDelay());
        this.add(new NoInteract());
        this.add(new NoMiningTrace());
        this.add(new NoRotate());
        this.add(new OffhandCrash());
        this.add(new Portals());
        this.add(new PotionSaver());
        this.add(new PotionSpoof());
        this.add(new Reach());
        this.add(new Rotation());
        this.add(new SpeedMine());
        this.add(new XCarry());
    }

    private static String lambda$sortModules$1(Module module) {
        return module.title;
    }

    public List<class_3545<Module, Integer>> searchTitles(String string) {
        ArrayList<class_3545<Module, Integer>> arrayList = new ArrayList<class_3545<Module, Integer>>();
        for (Module module : this.moduleInstances.values()) {
            int n = Utils.search(module.title, string);
            if (n <= 0) continue;
            arrayList.add((class_3545<Module, Integer>)new class_3545((Object)module, (Object)n));
        }
        arrayList.sort(Comparator.comparingInt(Modules::lambda$searchTitles$3));
        return arrayList;
    }

    private void initMovement() {
        this.add(new AirJump());
        this.add(new Anchor());
        this.add(new AntiAFK());
        this.add(new AntiLevitation());
        this.add(new AntiVoid());
        this.add(new AutoJump());
        this.add(new AutoWalk());
        this.add(new Blink());
        this.add(new BoatFly());
        this.add(new ClickTP());
        this.add(new ElytraBoost());
        this.add(new ElytraFly());
        this.add(new EntityControl());
        this.add(new EntitySpeed());
        this.add(new FastClimb());
        this.add(new Flight());
        this.add(new GUIMove());
        this.add(new HighJump());
        this.add(new Jesus());
        this.add(new NoFall());
        this.add(new NoSlow());
        this.add(new Parkour());
        this.add(new ReverseStep());
        this.add(new SafeWalk());
        this.add(new Scaffold());
        this.add(new Slippy());
        this.add(new Sneak());
        this.add(new Speed());
        this.add(new Spider());
        this.add(new Sprint());
        this.add(new Step());
        this.add(new Velocity());
    }

    private void initWorld() {
        this.add(new AirPlace());
        this.add(new Ambience());
        this.add(new AntiCactus());
        this.add(new AutoBreed());
        this.add(new AutoBrewer());
        this.add(new AutoMount());
        this.add(new AutoNametag());
        this.add(new AutoShearer());
        this.add(new AutoSign());
        this.add(new AutoSmelter());
        this.add(new AutoSteal());
        this.add(new BuildHeight());
        this.add(new EChestFarmer());
        this.add(new EndermanLook());
        this.add(new EntityLogger());
        this.add(new Flamethrower());
        this.add(new InfinityMiner());
        this.add(new InstaMine());
        this.add(new LiquidFiller());
        this.add(new MountBypass());
        this.add(new Nuker());
        this.add(new PacketMine());
        this.add(new StashFinder());
        this.add(new Timer());
        this.add(new VeinMiner());
    }

    @Override
    public Modules fromTag(class_2487 class_24872) {
        this.disableAll();
        class_2499 class_24992 = class_24872.method_10554("modules", 10);
        for (class_2520 class_25202 : class_24992) {
            class_2487 class_24873 = (class_2487)class_25202;
            Module module = this.get(class_24873.method_10558("name"));
            if (module == null) continue;
            module.fromTag(class_24873);
        }
        return this;
    }

    public List<Module> getGroup(Category category) {
        return this.groups.computeIfAbsent(category, Modules::lambda$getGroup$2);
    }

    public Modules() {
        super("modules");
        this.groups = new HashMap<Category, List<Module>>();
        this.active = new ArrayList<Module>();
    }

    private void onAction(boolean bl, int n, boolean bl2) {
        if (Utils.mc.field_1755 == null && !Input.isKeyPressed(292)) {
            for (Module module : this.moduleInstances.values()) {
                if (!module.keybind.matches(bl, n) || !bl2 && !module.toggleOnBindRelease) continue;
                module.toggle();
                module.sendToggledMsg();
            }
        }
    }

    private void initMisc() {
        this.add(new Swarm());
        this.add(new Announcer());
        this.add(new AntiPacketKick());
        this.add(new AutoClicker());
        this.add(new AutoLog());
        this.add(new AutoReconnect());
        this.add(new AutoRespawn());
        this.add(new BetterChat());
        this.add(new BetterTab());
        this.add(new BookBot());
        this.add(new DiscordPresence());
        this.add(new MessageAura());
        this.add(new MiddleClickFriend());
        this.add(new NameProtect());
        this.add(new Notebot());
        this.add(new Notifier());
        this.add(new PacketCanceller());
        this.add(new SoundBlocker());
        this.add(new Spam());
        this.add(new TPSSync());
        this.add(new VanillaSpoof());
        this.add(new InventoryTweaks());
    }

    private static int lambda$searchTitles$3(class_3545 class_35452) {
        return -((Integer)class_35452.method_15441()).intValue();
    }

    public Collection<Module> getAll() {
        return this.moduleInstances.values();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void disableAll() {
        List<Module> list = this.active;
        synchronized (list) {
            Iterator<Module> iterator = this.modules.iterator();
            while (iterator.hasNext()) {
                Module module = iterator.next();
                if (!module.isActive()) continue;
                module.toggle(Utils.canUpdate());
            }
            return;
        }
    }

    public void sortModules() {
        for (List<Module> list : this.groups.values()) {
            list.sort(Comparator.comparing(Modules::lambda$sortModules$0));
        }
        this.modules.sort(Comparator.comparing(Modules::lambda$sortModules$1));
    }

    @Override
    public void init() {
        this.initBedTrap();
        this.initCombat();
        this.initPlayer();
        this.initMovement();
        this.initRender();
        this.initWorld();
        this.initMisc();
        this.add(new HUD());
    }

    public void setModuleToBind(Module module) {
        this.moduleToBind = module;
    }

    private static int lambda$searchSettingTitles$4(class_3545 class_35452) {
        return -((Integer)class_35452.method_15441()).intValue();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @EventHandler
    private void onGameJoined(GameJoinedEvent gameJoinedEvent) {
        List<Module> list = this.active;
        synchronized (list) {
            Iterator<Module> iterator = this.modules.iterator();
            while (iterator.hasNext()) {
                Module module = iterator.next();
                if (!module.isActive()) continue;
                MeteorClient.EVENT_BUS.subscribe(module);
                module.onActivate();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void addActive(Module module) {
        List<Module> list = this.active;
        synchronized (list) {
            if (this.active.contains(module)) return;
            this.active.add(module);
            MeteorClient.EVENT_BUS.post(ActiveModulesChangedEvent.get());
            return;
        }
    }

    public List<class_3545<Module, Integer>> searchSettingTitles(String string) {
        ArrayList<class_3545<Module, Integer>> arrayList = new ArrayList<class_3545<Module, Integer>>();
        for (Module module : this.moduleInstances.values()) {
            block1: for (SettingGroup settingGroup : module.settings) {
                for (Setting<?> setting : settingGroup) {
                    int n = Utils.search(setting.title, string);
                    if (n <= 0) continue;
                    arrayList.add((class_3545<Module, Integer>)new class_3545((Object)module, (Object)n));
                    continue block1;
                }
            }
        }
        arrayList.sort(Comparator.comparingInt(Modules::lambda$searchSettingTitles$4));
        return arrayList;
    }

    public static class ModuleRegistry
    extends class_2378<Module> {
        @Nullable
        public Module get(@Nullable class_5321<Module> class_53212) {
            return null;
        }

        public Lifecycle method_31138() {
            return null;
        }

        protected Lifecycle method_31139(Object object) {
            return this.getEntryLifecycle((Module)object);
        }

        @Nullable
        public Module get(@Nullable class_2960 class_29602) {
            return null;
        }

        @Nullable
        public Object method_10223(@Nullable class_2960 class_29602) {
            return this.get(class_29602);
        }

        @Nullable
        public Object method_29107(@Nullable class_5321 class_53212) {
            return this.get((class_5321<Module>)class_53212);
        }

        public Set<Map.Entry<class_5321<Module>, Module>> method_29722() {
            return null;
        }

        @Nullable
        public Object method_10200(int n) {
            return this.get(n);
        }

        @Nullable
        public class_2960 getId(Module module) {
            return null;
        }

        @Nullable
        public class_2960 method_10221(Object object) {
            return this.getId((Module)object);
        }

        public Iterator<Module> iterator() {
            return new ModuleIterator(null);
        }

        public Optional method_29113(Object object) {
            return this.getKey((Module)object);
        }

        public int getRawId(@Nullable Module module) {
            return 0;
        }

        @Nullable
        public Module get(int n) {
            return null;
        }

        public int method_10206(@Nullable Object object) {
            return this.getRawId((Module)object);
        }

        protected Lifecycle getEntryLifecycle(Module module) {
            return null;
        }

        public boolean method_10250(class_2960 class_29602) {
            return false;
        }

        public ModuleRegistry() {
            super(class_5321.method_29180((class_2960)new class_2960("meteor-client", "modules")), Lifecycle.stable());
        }

        public Optional<class_5321<Module>> getKey(Module module) {
            return Optional.empty();
        }

        public Set<class_2960> method_10235() {
            return null;
        }

        /*
         * Duplicate member names - consider using --renamedupmembers true
         */
        private static class ModuleIterator
        implements Iterator<Module> {
            private final Iterator<Module> iterator = Modules.get().getAll().iterator();

            ModuleIterator(1 var1_1) {
                this();
            }

            private ModuleIterator() {
            }

            @Override
            public boolean hasNext() {
                return this.iterator.hasNext();
            }

            @Override
            public Object next() {
                return this.next();
            }

            @Override
            public Module next() {
                return this.iterator.next();
            }
        }
    }
}

