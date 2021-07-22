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
    private Boolean BLOCKER;
    public final Setting<Boolean> smartModuleToggle;
    public final Setting<class_2248> targetBlock;
    private int playerY;
    public final Setting<Boolean> autoWalkHome;
    private Mode currentMode;
    private boolean baritoneRunning;
    public final Setting<Boolean> autoLogOut;
    private final HashMap<String, Boolean> originalSettings;
    private int playerX;
    private int playerZ;
    private final SettingGroup sgAutoToggles;
    private final SettingGroup sgExtras;
    private Mode secondaryMode;
    public final Setting<class_2248> repairBlock;
    public final Setting<Double> durabilityThreshold;
    private final SettingGroup sgGeneral;

    @EventHandler
    private void moduleChange(ActiveModulesChangedEvent activeModulesChangedEvent) {
        if (!this.BLOCKER.booleanValue()) {
            for (Module module : this.getToggleModules()) {
                if (module == null || module.isActive()) continue;
                this.originalSettings.remove(module.name);
            }
        }
    }

    public InfinityMiner() {
        super(Categories.World, "infinity-miner", "Allows you to essentially mine forever.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgAutoToggles = this.settings.createGroup("Auto Toggles");
        this.sgExtras = this.settings.createGroup("Extras");
        this.targetBlock = this.sgGeneral.add(new BlockSetting.Builder().name("target-block").description("The target block to mine.").defaultValue(class_2246.field_22109).filter(this::filter).build());
        this.repairBlock = this.sgGeneral.add(new BlockSetting.Builder().name("repair-block").description("The block mined to repair your pickaxe.").defaultValue(class_2246.field_10213).filter(this::filter).build());
        this.durabilityThreshold = this.sgGeneral.add(new DoubleSetting.Builder().name("durability-threshold").description("The durability at which to start repairing as a percent of maximum durability.").defaultValue(0.15).max(0.95).min(0.05).sliderMin(0.05).sliderMax(0.95).build());
        this.smartModuleToggle = this.sgAutoToggles.add(new BoolSetting.Builder().name("smart-module-toggle").description("Will automatically enable helpful modules.").defaultValue(true).build());
        this.autoWalkHome = this.sgExtras.add(new BoolSetting.Builder().name("walk-home").description("Will walk 'home' when your inventory is full.").defaultValue(false).build());
        this.autoLogOut = this.sgExtras.add(new BoolSetting.Builder().name("log-out").description("Logs out when your inventory is full. Will walk home FIRST if walk home is enabled.").defaultValue(false).build());
        this.currentMode = Mode.Still;
        this.baritoneRunning = false;
        this.originalSettings = new HashMap();
        this.BLOCKER = false;
    }

    private int getCurrentDamage() {
        return this.mc.field_1724 != null ? this.mc.field_1724.method_6047().method_7909().method_7841() - this.mc.field_1724.method_6047().method_7919() : -1;
    }

    public Mode getMode() {
        return this.currentMode;
    }

    @Override
    public void onDeactivate() {
        if (this.smartModuleToggle.get().booleanValue()) {
            this.BLOCKER = true;
            try {
                for (Module module : this.getToggleModules()) {
                    if (module == null || this.originalSettings.get(module.name).booleanValue() == module.isActive()) continue;
                    module.toggle();
                }
            }
            catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            this.originalSettings.clear();
            this.BLOCKER = false;
        }
        if (!((Boolean)BaritoneAPI.getSettings().mineScanDroppedItems.value).booleanValue()) {
            BaritoneAPI.getSettings().mineScanDroppedItems.value = true;
        }
        this.baritoneRequestStop();
        this.baritoneRunning = false;
        this.currentMode = Mode.Still;
        this.secondaryMode = null;
    }

    private boolean isTool() {
        return this.mc.field_1724 != null && this.mc.field_1724.method_6047() != null && this.mc.field_1724.method_6047().method_7909() instanceof class_1831;
    }

    public int[] getHomeCoords() {
        return new int[]{this.playerX, this.playerY, this.playerX};
    }

    @EventHandler
    private void onGameJoin(GameJoinedEvent gameJoinedEvent) {
        this.baritoneRequestStop();
        if (!((Boolean)BaritoneAPI.getSettings().mineScanDroppedItems.value).booleanValue()) {
            BaritoneAPI.getSettings().mineScanDroppedItems.value = true;
        }
        if (this.isActive()) {
            this.toggle();
        }
    }

    @Override
    public void onActivate() {
        if (this.smartModuleToggle.get().booleanValue()) {
            this.BLOCKER = true;
            for (Module module : this.getToggleModules()) {
                this.originalSettings.put(module.name, module.isActive());
                if (module.isActive()) continue;
                module.toggle();
            }
            this.BLOCKER = false;
        }
        if (this.mc.field_1724 != null) {
            this.playerX = (int)this.mc.field_1724.method_23317();
            this.playerY = (int)this.mc.field_1724.method_23318();
            this.playerZ = (int)this.mc.field_1724.method_23321();
        }
    }

    private void baritoneRequestStop() {
        BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
        this.baritoneRunning = false;
        this.currentMode = Mode.Still;
    }

    private List<Module> getToggleModules() {
        return Lists.newArrayList((Object[])new Module[]{Modules.get().get(Jesus.class), Modules.get().get(NoBreakDelay.class), Modules.get().get(AntiHunger.class), Modules.get().get(AutoEat.class), Modules.get().get(NoFall.class), Modules.get().get(AutoLog.class), Modules.get().get(AutoTool.class), Modules.get().get(GUIMove.class)});
    }

    @Override
    public String getInfoString() {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$world$InfinityMiner$Mode[this.getMode().ordinal()]) {
            case 1: {
                int[] arrn = this.getHomeCoords();
                return String.valueOf(new StringBuilder().append("Heading Home: ").append(arrn[0]).append(" ").append(arrn[1]).append(" ").append(arrn[2]));
            }
            case 2: {
                return String.valueOf(new StringBuilder().append("Mining: ").append(this.getCurrentTarget().method_9518().getString()));
            }
            case 3: {
                return String.valueOf(new StringBuilder().append("Repair-Mining: ").append(this.getCurrentTarget().method_9518().getString()));
            }
            case 4: {
                return "Resting";
            }
        }
        return "";
    }

    private boolean filter(class_2248 class_22482) {
        return class_22482 != class_2246.field_10124 && class_22482.method_9564().method_26214((class_1922)this.mc.field_1687, null) != -1.0f && !(class_22482 instanceof class_2404);
    }

    @EventHandler
    private void onGameDisconnect(GameLeftEvent gameLeftEvent) {
        this.baritoneRequestStop();
        if (!((Boolean)BaritoneAPI.getSettings().mineScanDroppedItems.value).booleanValue()) {
            BaritoneAPI.getSettings().mineScanDroppedItems.value = true;
        }
        if (this.isActive()) {
            this.toggle();
        }
    }

    private void requestLogout(Mode mode) {
        if (this.mc.field_1724 != null) {
            if (mode == Mode.Home) {
                this.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585("[Infinity Miner] Inventory is Full and You Are Home")));
            } else {
                this.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585("[Infinity Miner] Inventory is Full")));
            }
        }
    }

    private void baritoneRequestMineTargetBlock() {
        try {
            BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().mine(new class_2248[]{this.targetBlock.get()});
            this.baritoneRunning = true;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private Boolean isInventoryFull() {
        if (this.mc.field_1724 == null) {
            return false;
        }
        if (this.mc.field_1724.field_7514.method_7376() != -1) {
            return false;
        }
        for (int i = 0; i < this.mc.field_1724.field_7514.method_5439(); ++i) {
            if (this.mc.field_1724.field_7514.method_5438(i).method_7909() != this.targetBlock.get().method_8389() || this.mc.field_1724.field_7514.method_5438(i).method_7947() >= this.targetBlock.get().method_8389().method_7882()) continue;
            return false;
        }
        return true;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        try {
            if (this.mc.field_1724 == null) {
                return;
            }
            if (!this.baritoneRunning && this.currentMode == Mode.Still) {
                if (this.autoWalkHome.get().booleanValue() && this.isInventoryFull().booleanValue() && this.secondaryMode != Mode.Home) {
                    this.baritoneRequestPathHome();
                    return;
                }
                Mode mode = this.currentMode = this.isTool() && (double)this.getCurrentDamage() <= this.durabilityThreshold.get() ? Mode.Repair : Mode.Target;
                if (this.currentMode == Mode.Repair) {
                    this.baritoneRequestMineRepairBlock();
                } else {
                    this.baritoneRequestMineTargetBlock();
                }
            } else if (this.autoWalkHome.get().booleanValue() && this.isInventoryFull().booleanValue() && this.secondaryMode != Mode.Home) {
                this.baritoneRequestPathHome();
            } else if (!this.autoWalkHome.get().booleanValue() && this.isInventoryFull().booleanValue() && this.autoLogOut.get().booleanValue()) {
                this.toggle();
                this.requestLogout(this.currentMode);
            } else if (this.currentMode == Mode.Repair) {
                int n = 15;
                if (((Boolean)BaritoneAPI.getSettings().mineScanDroppedItems.value).booleanValue()) {
                    BaritoneAPI.getSettings().mineScanDroppedItems.value = false;
                }
                if (this.isTool() && this.getCurrentDamage() >= this.mc.field_1724.method_6047().method_7936() - n) {
                    if (this.secondaryMode != Mode.Home) {
                        this.currentMode = Mode.Target;
                        this.baritoneRequestMineTargetBlock();
                    } else {
                        this.currentMode = Mode.Home;
                        this.baritoneRequestPathHome();
                    }
                }
            } else if (this.currentMode == Mode.Target) {
                if (!((Boolean)BaritoneAPI.getSettings().mineScanDroppedItems.value).booleanValue()) {
                    BaritoneAPI.getSettings().mineScanDroppedItems.value = true;
                }
                if (this.isTool() && (double)this.getCurrentDamage() <= this.durabilityThreshold.get() * (double)this.mc.field_1724.method_6047().method_7936()) {
                    this.currentMode = Mode.Repair;
                    this.baritoneRequestMineRepairBlock();
                } else if (this.autoWalkHome.get().booleanValue() && this.isInventoryFull().booleanValue()) {
                    this.baritoneRequestPathHome();
                } else if (!this.autoWalkHome.get().booleanValue() && this.isInventoryFull().booleanValue() && this.autoWalkHome.get().booleanValue()) {
                    this.requestLogout(this.currentMode);
                }
            } else if (this.currentMode == Mode.Home) {
                if (Math.abs(this.mc.field_1724.method_23318() - (double)this.playerY) <= 0.5 && Math.abs(this.mc.field_1724.method_23317() - (double)this.playerX) <= 0.5 && Math.abs(this.mc.field_1724.method_23321() - (double)this.playerZ) <= 0.5) {
                    if (this.autoLogOut.get().booleanValue()) {
                        this.requestLogout(this.currentMode);
                    }
                    this.toggle();
                } else if (this.isTool() && (double)this.getCurrentDamage() <= this.durabilityThreshold.get()) {
                    this.currentMode = Mode.Repair;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public class_2248 getCurrentTarget() {
        return this.currentMode == Mode.Repair ? this.repairBlock.get() : this.targetBlock.get();
    }

    private void baritoneRequestMineRepairBlock() {
        try {
            BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().mine(new class_2248[]{this.repairBlock.get()});
            this.baritoneRunning = true;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void baritoneRequestPathHome() {
        if (this.autoWalkHome.get().booleanValue()) {
            this.baritoneRequestStop();
            this.secondaryMode = Mode.Home;
            this.currentMode = Mode.Home;
            BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath((Goal)new GoalBlock(this.playerX, this.playerY, this.playerZ));
        }
    }

    public static enum Mode {
        Target,
        Repair,
        Still,
        Home;

    }
}

