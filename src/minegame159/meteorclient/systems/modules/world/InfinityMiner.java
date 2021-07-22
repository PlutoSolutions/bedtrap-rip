/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  baritone.api.pathing.goals.Goal
 *  baritone.api.pathing.goals.GoalBlock
 *  com.google.common.collect.Lists
 *  net.minecraft.class_1831
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2404
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_2661
 */
package minegame159.meteorclient.systems.modules.world;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalBlock;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.GameJoinedEvent;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.meteor.ActiveModulesChangedEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BlockSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.AutoLog;
import minegame159.meteorclient.systems.modules.movement.GUIMove;
import minegame159.meteorclient.systems.modules.movement.Jesus;
import minegame159.meteorclient.systems.modules.movement.NoFall;
import minegame159.meteorclient.systems.modules.player.AntiHunger;
import minegame159.meteorclient.systems.modules.player.AutoEat;
import minegame159.meteorclient.systems.modules.player.AutoTool;
import minegame159.meteorclient.systems.modules.player.NoBreakDelay;
import net.minecraft.class_1831;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2404;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2661;

public class InfinityMiner
extends Module {
    private volatile /* synthetic */ Boolean BLOCKER;
    public final /* synthetic */ Setting<Boolean> smartModuleToggle;
    public final /* synthetic */ Setting<class_2248> targetBlock;
    private /* synthetic */ int playerY;
    public final /* synthetic */ Setting<Boolean> autoWalkHome;
    private /* synthetic */ Mode currentMode;
    private /* synthetic */ boolean baritoneRunning;
    public final /* synthetic */ Setting<Boolean> autoLogOut;
    private final /* synthetic */ HashMap<String, Boolean> originalSettings;
    private /* synthetic */ int playerX;
    private /* synthetic */ int playerZ;
    private final /* synthetic */ SettingGroup sgAutoToggles;
    private final /* synthetic */ SettingGroup sgExtras;
    private /* synthetic */ Mode secondaryMode;
    public final /* synthetic */ Setting<class_2248> repairBlock;
    public final /* synthetic */ Setting<Double> durabilityThreshold;
    private final /* synthetic */ SettingGroup sgGeneral;

    @EventHandler
    private void moduleChange(ActiveModulesChangedEvent lIIIIlIIlllIIIl) {
        InfinityMiner lIIIIlIIlllIIII;
        if (!lIIIIlIIlllIIII.BLOCKER.booleanValue()) {
            for (Module lIIIIlIIlllIIll : lIIIIlIIlllIIII.getToggleModules()) {
                if (lIIIIlIIlllIIll == null || lIIIIlIIlllIIll.isActive()) continue;
                lIIIIlIIlllIIII.originalSettings.remove(lIIIIlIIlllIIll.name);
            }
        }
    }

    public InfinityMiner() {
        super(Categories.World, "infinity-miner", "Allows you to essentially mine forever.");
        InfinityMiner lIIIIlIlIIlIlII;
        lIIIIlIlIIlIlII.sgGeneral = lIIIIlIlIIlIlII.settings.getDefaultGroup();
        lIIIIlIlIIlIlII.sgAutoToggles = lIIIIlIlIIlIlII.settings.createGroup("Auto Toggles");
        lIIIIlIlIIlIlII.sgExtras = lIIIIlIlIIlIlII.settings.createGroup("Extras");
        lIIIIlIlIIlIlII.targetBlock = lIIIIlIlIIlIlII.sgGeneral.add(new BlockSetting.Builder().name("target-block").description("The target block to mine.").defaultValue(class_2246.field_22109).filter(lIIIIlIlIIlIlII::filter).build());
        lIIIIlIlIIlIlII.repairBlock = lIIIIlIlIIlIlII.sgGeneral.add(new BlockSetting.Builder().name("repair-block").description("The block mined to repair your pickaxe.").defaultValue(class_2246.field_10213).filter(lIIIIlIlIIlIlII::filter).build());
        lIIIIlIlIIlIlII.durabilityThreshold = lIIIIlIlIIlIlII.sgGeneral.add(new DoubleSetting.Builder().name("durability-threshold").description("The durability at which to start repairing as a percent of maximum durability.").defaultValue(0.15).max(0.95).min(0.05).sliderMin(0.05).sliderMax(0.95).build());
        lIIIIlIlIIlIlII.smartModuleToggle = lIIIIlIlIIlIlII.sgAutoToggles.add(new BoolSetting.Builder().name("smart-module-toggle").description("Will automatically enable helpful modules.").defaultValue(true).build());
        lIIIIlIlIIlIlII.autoWalkHome = lIIIIlIlIIlIlII.sgExtras.add(new BoolSetting.Builder().name("walk-home").description("Will walk 'home' when your inventory is full.").defaultValue(false).build());
        lIIIIlIlIIlIlII.autoLogOut = lIIIIlIlIIlIlII.sgExtras.add(new BoolSetting.Builder().name("log-out").description("Logs out when your inventory is full. Will walk home FIRST if walk home is enabled.").defaultValue(false).build());
        lIIIIlIlIIlIlII.currentMode = Mode.Still;
        lIIIIlIlIIlIlII.baritoneRunning = false;
        lIIIIlIlIIlIlII.originalSettings = new HashMap();
        lIIIIlIlIIlIlII.BLOCKER = false;
    }

    private int getCurrentDamage() {
        InfinityMiner lIIIIlIIIlllIlI;
        return lIIIIlIIIlllIlI.mc.field_1724 != null ? lIIIIlIIIlllIlI.mc.field_1724.method_6047().method_7909().method_7841() - lIIIIlIIIlllIlI.mc.field_1724.method_6047().method_7919() : -1;
    }

    public Mode getMode() {
        InfinityMiner lIIIIlIIlIIIlll;
        return lIIIIlIIlIIIlll.currentMode;
    }

    @Override
    public void onDeactivate() {
        InfinityMiner lIIIIlIlIIIIIII;
        if (lIIIIlIlIIIIIII.smartModuleToggle.get().booleanValue()) {
            lIIIIlIlIIIIIII.BLOCKER = true;
            try {
                for (Module lIIIIlIlIIIIIlI : lIIIIlIlIIIIIII.getToggleModules()) {
                    if (lIIIIlIlIIIIIlI == null || lIIIIlIlIIIIIII.originalSettings.get(lIIIIlIlIIIIIlI.name).booleanValue() == lIIIIlIlIIIIIlI.isActive()) continue;
                    lIIIIlIlIIIIIlI.toggle();
                }
            }
            catch (NullPointerException lIIIIlIIlllllll) {
                // empty catch block
            }
            lIIIIlIlIIIIIII.originalSettings.clear();
            lIIIIlIlIIIIIII.BLOCKER = false;
        }
        if (!((Boolean)BaritoneAPI.getSettings().mineScanDroppedItems.value).booleanValue()) {
            BaritoneAPI.getSettings().mineScanDroppedItems.value = true;
        }
        lIIIIlIlIIIIIII.baritoneRequestStop();
        lIIIIlIlIIIIIII.baritoneRunning = false;
        lIIIIlIlIIIIIII.currentMode = Mode.Still;
        lIIIIlIlIIIIIII.secondaryMode = null;
    }

    private boolean isTool() {
        InfinityMiner lIIIIlIIIllllIl;
        return lIIIIlIIIllllIl.mc.field_1724 != null && lIIIIlIIIllllIl.mc.field_1724.method_6047() != null && lIIIIlIIIllllIl.mc.field_1724.method_6047().method_7909() instanceof class_1831;
    }

    public int[] getHomeCoords() {
        InfinityMiner lIIIIlIIlIIIIIl;
        return new int[]{lIIIIlIIlIIIIIl.playerX, lIIIIlIIlIIIIIl.playerY, lIIIIlIIlIIIIIl.playerX};
    }

    @EventHandler
    private void onGameJoin(GameJoinedEvent lIIIIlIIlIIlIlI) {
        InfinityMiner lIIIIlIIlIIlIIl;
        lIIIIlIIlIIlIIl.baritoneRequestStop();
        if (!((Boolean)BaritoneAPI.getSettings().mineScanDroppedItems.value).booleanValue()) {
            BaritoneAPI.getSettings().mineScanDroppedItems.value = true;
        }
        if (lIIIIlIIlIIlIIl.isActive()) {
            lIIIIlIIlIIlIIl.toggle();
        }
    }

    @Override
    public void onActivate() {
        InfinityMiner lIIIIlIlIIIlIII;
        if (lIIIIlIlIIIlIII.smartModuleToggle.get().booleanValue()) {
            lIIIIlIlIIIlIII.BLOCKER = true;
            for (Module lIIIIlIlIIIlIlI : lIIIIlIlIIIlIII.getToggleModules()) {
                lIIIIlIlIIIlIII.originalSettings.put(lIIIIlIlIIIlIlI.name, lIIIIlIlIIIlIlI.isActive());
                if (lIIIIlIlIIIlIlI.isActive()) continue;
                lIIIIlIlIIIlIlI.toggle();
            }
            lIIIIlIlIIIlIII.BLOCKER = false;
        }
        if (lIIIIlIlIIIlIII.mc.field_1724 != null) {
            lIIIIlIlIIIlIII.playerX = (int)lIIIIlIlIIIlIII.mc.field_1724.method_23317();
            lIIIIlIlIIIlIII.playerY = (int)lIIIIlIlIIIlIII.mc.field_1724.method_23318();
            lIIIIlIlIIIlIII.playerZ = (int)lIIIIlIlIIIlIII.mc.field_1724.method_23321();
        }
    }

    private void baritoneRequestStop() {
        BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
        lIIIIlIIllIIIlI.baritoneRunning = false;
        lIIIIlIIllIIIlI.currentMode = Mode.Still;
    }

    private List<Module> getToggleModules() {
        return Lists.newArrayList((Object[])new Module[]{Modules.get().get(Jesus.class), Modules.get().get(NoBreakDelay.class), Modules.get().get(AntiHunger.class), Modules.get().get(AutoEat.class), Modules.get().get(NoFall.class), Modules.get().get(AutoLog.class), Modules.get().get(AutoTool.class), Modules.get().get(GUIMove.class)});
    }

    @Override
    public String getInfoString() {
        InfinityMiner lIIIIlIIIllIllI;
        switch (lIIIIlIIIllIllI.getMode()) {
            case Home: {
                int[] lIIIIlIIIllIlll = lIIIIlIIIllIllI.getHomeCoords();
                return String.valueOf(new StringBuilder().append("Heading Home: ").append(lIIIIlIIIllIlll[0]).append(" ").append(lIIIIlIIIllIlll[1]).append(" ").append(lIIIIlIIIllIlll[2]));
            }
            case Target: {
                return String.valueOf(new StringBuilder().append("Mining: ").append(lIIIIlIIIllIllI.getCurrentTarget().method_9518().getString()));
            }
            case Repair: {
                return String.valueOf(new StringBuilder().append("Repair-Mining: ").append(lIIIIlIIIllIllI.getCurrentTarget().method_9518().getString()));
            }
            case Still: {
                return "Resting";
            }
        }
        return "";
    }

    private boolean filter(class_2248 lIIIIlIlIIlIIII) {
        InfinityMiner lIIIIlIlIIIllll;
        return lIIIIlIlIIlIIII != class_2246.field_10124 && lIIIIlIlIIlIIII.method_9564().method_26214((class_1922)lIIIIlIlIIIllll.mc.field_1687, null) != -1.0f && !(lIIIIlIlIIlIIII instanceof class_2404);
    }

    @EventHandler
    private void onGameDisconnect(GameLeftEvent lIIIIlIIlIIlllI) {
        InfinityMiner lIIIIlIIlIIllIl;
        lIIIIlIIlIIllIl.baritoneRequestStop();
        if (!((Boolean)BaritoneAPI.getSettings().mineScanDroppedItems.value).booleanValue()) {
            BaritoneAPI.getSettings().mineScanDroppedItems.value = true;
        }
        if (lIIIIlIIlIIllIl.isActive()) {
            lIIIIlIIlIIllIl.toggle();
        }
    }

    private void requestLogout(Mode lIIIIlIIlIlIIIl) {
        InfinityMiner lIIIIlIIlIlIlII;
        if (lIIIIlIIlIlIlII.mc.field_1724 != null) {
            if (lIIIIlIIlIlIIIl == Mode.Home) {
                lIIIIlIIlIlIlII.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585("[Infinity Miner] Inventory is Full and You Are Home")));
            } else {
                lIIIIlIIlIlIlII.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585("[Infinity Miner] Inventory is Full")));
            }
        }
    }

    private void baritoneRequestMineTargetBlock() {
        try {
            InfinityMiner lIIIIlIIllIlIll;
            BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().mine(new class_2248[]{lIIIIlIIllIlIll.targetBlock.get()});
            lIIIIlIIllIlIll.baritoneRunning = true;
        }
        catch (Exception lIIIIlIIllIlIIl) {
            // empty catch block
        }
    }

    private Boolean isInventoryFull() {
        InfinityMiner lIIIIlIIlIllIlI;
        if (lIIIIlIIlIllIlI.mc.field_1724 == null) {
            return false;
        }
        if (lIIIIlIIlIllIlI.mc.field_1724.field_7514.method_7376() != -1) {
            return false;
        }
        for (int lIIIIlIIlIllIll = 0; lIIIIlIIlIllIll < lIIIIlIIlIllIlI.mc.field_1724.field_7514.method_5439(); ++lIIIIlIIlIllIll) {
            if (lIIIIlIIlIllIlI.mc.field_1724.field_7514.method_5438(lIIIIlIIlIllIll).method_7909() != lIIIIlIIlIllIlI.targetBlock.get().method_8389() || lIIIIlIIlIllIlI.mc.field_1724.field_7514.method_5438(lIIIIlIIlIllIll).method_7947() >= lIIIIlIIlIllIlI.targetBlock.get().method_8389().method_7882()) continue;
            return false;
        }
        return true;
    }

    @EventHandler
    private void onTick(TickEvent.Post lIIIIlIIllllIIl) {
        try {
            InfinityMiner lIIIIlIIllllIlI;
            if (lIIIIlIIllllIlI.mc.field_1724 == null) {
                return;
            }
            if (!lIIIIlIIllllIlI.baritoneRunning && lIIIIlIIllllIlI.currentMode == Mode.Still) {
                if (lIIIIlIIllllIlI.autoWalkHome.get().booleanValue() && lIIIIlIIllllIlI.isInventoryFull().booleanValue() && lIIIIlIIllllIlI.secondaryMode != Mode.Home) {
                    lIIIIlIIllllIlI.baritoneRequestPathHome();
                    return;
                }
                Mode mode = lIIIIlIIllllIlI.currentMode = lIIIIlIIllllIlI.isTool() && (double)lIIIIlIIllllIlI.getCurrentDamage() <= lIIIIlIIllllIlI.durabilityThreshold.get() ? Mode.Repair : Mode.Target;
                if (lIIIIlIIllllIlI.currentMode == Mode.Repair) {
                    lIIIIlIIllllIlI.baritoneRequestMineRepairBlock();
                } else {
                    lIIIIlIIllllIlI.baritoneRequestMineTargetBlock();
                }
            } else if (lIIIIlIIllllIlI.autoWalkHome.get().booleanValue() && lIIIIlIIllllIlI.isInventoryFull().booleanValue() && lIIIIlIIllllIlI.secondaryMode != Mode.Home) {
                lIIIIlIIllllIlI.baritoneRequestPathHome();
            } else if (!lIIIIlIIllllIlI.autoWalkHome.get().booleanValue() && lIIIIlIIllllIlI.isInventoryFull().booleanValue() && lIIIIlIIllllIlI.autoLogOut.get().booleanValue()) {
                lIIIIlIIllllIlI.toggle();
                lIIIIlIIllllIlI.requestLogout(lIIIIlIIllllIlI.currentMode);
            } else if (lIIIIlIIllllIlI.currentMode == Mode.Repair) {
                int lIIIIlIIllllIll = 15;
                if (((Boolean)BaritoneAPI.getSettings().mineScanDroppedItems.value).booleanValue()) {
                    BaritoneAPI.getSettings().mineScanDroppedItems.value = false;
                }
                if (lIIIIlIIllllIlI.isTool() && lIIIIlIIllllIlI.getCurrentDamage() >= lIIIIlIIllllIlI.mc.field_1724.method_6047().method_7936() - lIIIIlIIllllIll) {
                    if (lIIIIlIIllllIlI.secondaryMode != Mode.Home) {
                        lIIIIlIIllllIlI.currentMode = Mode.Target;
                        lIIIIlIIllllIlI.baritoneRequestMineTargetBlock();
                    } else {
                        lIIIIlIIllllIlI.currentMode = Mode.Home;
                        lIIIIlIIllllIlI.baritoneRequestPathHome();
                    }
                }
            } else if (lIIIIlIIllllIlI.currentMode == Mode.Target) {
                if (!((Boolean)BaritoneAPI.getSettings().mineScanDroppedItems.value).booleanValue()) {
                    BaritoneAPI.getSettings().mineScanDroppedItems.value = true;
                }
                if (lIIIIlIIllllIlI.isTool() && (double)lIIIIlIIllllIlI.getCurrentDamage() <= lIIIIlIIllllIlI.durabilityThreshold.get() * (double)lIIIIlIIllllIlI.mc.field_1724.method_6047().method_7936()) {
                    lIIIIlIIllllIlI.currentMode = Mode.Repair;
                    lIIIIlIIllllIlI.baritoneRequestMineRepairBlock();
                } else if (lIIIIlIIllllIlI.autoWalkHome.get().booleanValue() && lIIIIlIIllllIlI.isInventoryFull().booleanValue()) {
                    lIIIIlIIllllIlI.baritoneRequestPathHome();
                } else if (!lIIIIlIIllllIlI.autoWalkHome.get().booleanValue() && lIIIIlIIllllIlI.isInventoryFull().booleanValue() && lIIIIlIIllllIlI.autoWalkHome.get().booleanValue()) {
                    lIIIIlIIllllIlI.requestLogout(lIIIIlIIllllIlI.currentMode);
                }
            } else if (lIIIIlIIllllIlI.currentMode == Mode.Home) {
                if (Math.abs(lIIIIlIIllllIlI.mc.field_1724.method_23318() - (double)lIIIIlIIllllIlI.playerY) <= 0.5 && Math.abs(lIIIIlIIllllIlI.mc.field_1724.method_23317() - (double)lIIIIlIIllllIlI.playerX) <= 0.5 && Math.abs(lIIIIlIIllllIlI.mc.field_1724.method_23321() - (double)lIIIIlIIllllIlI.playerZ) <= 0.5) {
                    if (lIIIIlIIllllIlI.autoLogOut.get().booleanValue()) {
                        lIIIIlIIllllIlI.requestLogout(lIIIIlIIllllIlI.currentMode);
                    }
                    lIIIIlIIllllIlI.toggle();
                } else if (lIIIIlIIllllIlI.isTool() && (double)lIIIIlIIllllIlI.getCurrentDamage() <= lIIIIlIIllllIlI.durabilityThreshold.get()) {
                    lIIIIlIIllllIlI.currentMode = Mode.Repair;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public class_2248 getCurrentTarget() {
        InfinityMiner lIIIIlIIlIIIlII;
        return lIIIIlIIlIIIlII.currentMode == Mode.Repair ? lIIIIlIIlIIIlII.repairBlock.get() : lIIIIlIIlIIIlII.targetBlock.get();
    }

    private void baritoneRequestMineRepairBlock() {
        try {
            InfinityMiner lIIIIlIIllIIllI;
            BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().mine(new class_2248[]{lIIIIlIIllIIllI.repairBlock.get()});
            lIIIIlIIllIIllI.baritoneRunning = true;
        }
        catch (Exception lIIIIlIIllIIlII) {
            // empty catch block
        }
    }

    private void baritoneRequestPathHome() {
        InfinityMiner lIIIIlIIlIlllll;
        if (lIIIIlIIlIlllll.autoWalkHome.get().booleanValue()) {
            lIIIIlIIlIlllll.baritoneRequestStop();
            lIIIIlIIlIlllll.secondaryMode = Mode.Home;
            lIIIIlIIlIlllll.currentMode = Mode.Home;
            BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)new GoalBlock(lIIIIlIIlIlllll.playerX, lIIIIlIIlIlllll.playerY, lIIIIlIIlIlllll.playerZ));
        }
    }

    public static enum Mode {
        Target,
        Repair,
        Still,
        Home;


        private Mode() {
            Mode llIlIIllllIIIIl;
        }
    }
}

