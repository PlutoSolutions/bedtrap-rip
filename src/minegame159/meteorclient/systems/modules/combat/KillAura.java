/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.combat;

import baritone.api.BaritoneAPI;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.Target;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_1934;
import net.minecraft.class_2868;

public class KillAura
extends Module {
    private final Setting<Integer> switchDelay;
    private final Setting<Boolean> pauseOnCombat;
    private final Setting<Integer> hitDelay;
    private final Setting<Boolean> smartDelay;
    private int switchTimer;
    private final Setting<Boolean> onlyWhenLook;
    private final List<class_1297> targets;
    private final Setting<Boolean> autoSwitch;
    private final Setting<Weapon> weapon;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> randomDelayEnabled;
    private final Setting<Boolean> nametagged;
    private final Setting<Double> hitChance;
    private final Setting<Boolean> randomTeleport;
    private boolean wasPathing;
    private final Setting<Double> wallsRange;
    private final Setting<Boolean> onlyOnClick;
    private final Setting<Integer> maxTargets;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<RotationMode> rotation;
    private int hitDelayTimer;
    private final Setting<Boolean> babies;
    private final Setting<SortPriority> priority;
    private final SettingGroup sgTargeting;
    private final Setting<Integer> randomDelayMax;
    private int randomDelayTimer;
    private final SettingGroup sgDelay;
    private final Setting<Double> range;

    public class_1297 getTarget() {
        if (!this.targets.isEmpty()) {
            return this.targets.get(0);
        }
        return null;
    }

    private void hitEntity(class_1297 class_12972) {
        this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, class_12972);
        this.mc.field_1724.method_6104(class_1268.field_5808);
    }

    public KillAura() {
        super(Categories.Combat, "kill-aura", "Attacks specified entities around you.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgTargeting = this.settings.createGroup("Targeting");
        this.sgDelay = this.settings.createGroup("Delay");
        this.autoSwitch = this.sgGeneral.add(new BoolSetting.Builder().name("auto-switch").description("Switches to your selected weapon when attacking the target.").defaultValue(false).build());
        this.weapon = this.sgGeneral.add(new EnumSetting.Builder().name("weapon").description("Only attacks an entity when a specified item is in your hand.").defaultValue(Weapon.Both).build());
        this.onlyOnClick = this.sgGeneral.add(new BoolSetting.Builder().name("only-on-click").description("Only attacks when hold left click.").defaultValue(false).build());
        this.onlyWhenLook = this.sgGeneral.add(new BoolSetting.Builder().name("only-when-look").description("Only attacks when you are looking at the entity.").defaultValue(false).build());
        this.randomTeleport = this.sgGeneral.add(new BoolSetting.Builder().name("random-teleport").description("Randomly teleport around the target").defaultValue(false).visible(this::lambda$new$0).build());
        this.rotation = this.sgGeneral.add(new EnumSetting.Builder().name("rotate").description("Determines when you should rotate towards the target.").defaultValue(RotationMode.Always).build());
        this.hitChance = this.sgGeneral.add(new DoubleSetting.Builder().name("hit-chance").description("The probability of your hits landing.").defaultValue(100.0).min(0.0).max(100.0).sliderMax(100.0).build());
        this.pauseOnCombat = this.sgGeneral.add(new BoolSetting.Builder().name("pause-on-combat").description("Freezes Baritone temporarily until you are finished attacking the entity.").defaultValue(true).build());
        this.entities = this.sgTargeting.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to attack.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).onlyAttackable().build());
        this.range = this.sgTargeting.add(new DoubleSetting.Builder().name("range").description("The maximum range the entity can be to attack it.").defaultValue(4.5).min(0.0).sliderMax(6.0).build());
        this.wallsRange = this.sgTargeting.add(new DoubleSetting.Builder().name("walls-range").description("The maximum range the entity can be attacked through walls.").defaultValue(3.5).min(0.0).sliderMax(6.0).build());
        this.priority = this.sgTargeting.add(new EnumSetting.Builder().name("priority").description("How to filter targets within range.").defaultValue(SortPriority.LowestHealth).build());
        this.maxTargets = this.sgTargeting.add(new IntSetting.Builder().name("max-targets").description("How many entities to target at once.").defaultValue(1).min(1).max(10).sliderMin(1).sliderMax(5).build());
        this.babies = this.sgTargeting.add(new BoolSetting.Builder().name("babies").description("Whether or not to attack baby variants of the entity.").defaultValue(true).build());
        this.nametagged = this.sgTargeting.add(new BoolSetting.Builder().name("nametagged").description("Whether or not to attack mobs with a name tag.").defaultValue(false).build());
        this.smartDelay = this.sgDelay.add(new BoolSetting.Builder().name("smart-delay").description("Uses the vanilla cooldown to attack entities.").defaultValue(true).build());
        this.hitDelay = this.sgDelay.add(new IntSetting.Builder().name("hit-delay").description("How fast you hit the entity in ticks.").defaultValue(0).min(0).sliderMax(60).visible(this::lambda$new$1).build());
        this.randomDelayEnabled = this.sgDelay.add(new BoolSetting.Builder().name("random-delay-enabled").description("Adds a random delay between hits to attempt to bypass anti-cheats.").defaultValue(false).visible(this::lambda$new$2).build());
        this.randomDelayMax = this.sgDelay.add(new IntSetting.Builder().name("random-delay-max").description("The maximum value for random delay.").defaultValue(4).min(0).sliderMax(20).visible(this::lambda$new$3).build());
        this.switchDelay = this.sgDelay.add(new IntSetting.Builder().name("switch-delay").description("How many ticks to wait before hitting an entity after switching hotbar slots.").defaultValue(0).min(0).sliderMax(10).build());
        this.targets = new ArrayList<class_1297>();
    }

    private boolean entityCheck(class_1297 class_12972) {
        if (class_12972.equals((Object)this.mc.field_1724) || class_12972.equals((Object)this.mc.field_1719)) {
            return false;
        }
        if (class_12972 instanceof class_1309 && ((class_1309)class_12972).method_29504() || !class_12972.method_5805()) {
            return false;
        }
        if (PlayerUtils.distanceTo(class_12972) > this.range.get()) {
            return false;
        }
        if (!this.entities.get().getBoolean((Object)class_12972.method_5864())) {
            return false;
        }
        if (!this.nametagged.get().booleanValue() && class_12972.method_16914()) {
            return false;
        }
        if (!PlayerUtils.canSeeEntity(class_12972) && PlayerUtils.distanceTo(class_12972) > this.wallsRange.get()) {
            return false;
        }
        if (class_12972 instanceof class_1657) {
            if (((class_1657)class_12972).method_7337()) {
                return false;
            }
            if (!Friends.get().shouldAttack((class_1657)class_12972)) {
                return false;
            }
        }
        return !(class_12972 instanceof class_1429) || this.babies.get() != false || !((class_1429)class_12972).method_6109();
    }

    private boolean itemInHand() {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$combat$KillAura$Weapon[this.weapon.get().ordinal()]) {
            case 1: {
                return this.mc.field_1724.method_6047().method_7909() instanceof class_1743;
            }
            case 2: {
                return this.mc.field_1724.method_6047().method_7909() instanceof class_1829;
            }
            case 3: {
                return this.mc.field_1724.method_6047().method_7909() instanceof class_1743 || this.mc.field_1724.method_6047().method_7909() instanceof class_1829;
            }
        }
        return true;
    }

    private boolean delayCheck() {
        if (this.switchTimer > 0) {
            --this.switchTimer;
            return false;
        }
        if (this.smartDelay.get().booleanValue()) {
            return this.mc.field_1724.method_7261(0.5f) >= 1.0f;
        }
        if (this.hitDelayTimer >= 0) {
            --this.hitDelayTimer;
            return false;
        }
        this.hitDelayTimer = this.hitDelay.get();
        if (this.randomDelayEnabled.get().booleanValue()) {
            if (this.randomDelayTimer > 0) {
                --this.randomDelayTimer;
                return false;
            }
            this.randomDelayTimer = (int)Math.round(Math.random() * (double)this.randomDelayMax.get().intValue());
        }
        return true;
    }

    private double randomOffset() {
        return Math.random() * 4.0 - 2.0;
    }

    @Override
    public String getInfoString() {
        if (!this.targets.isEmpty()) {
            class_1297 class_12972 = this.targets.get(0);
            if (class_12972 instanceof class_1657) {
                return class_12972.method_5820();
            }
            return class_12972.method_5864().method_5897().getString();
        }
        return null;
    }

    private boolean lambda$onTick$4(class_1799 class_17992) {
        class_1792 class_17922 = class_17992.method_7909();
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$combat$KillAura$Weapon[this.weapon.get().ordinal()]) {
            case 1: {
                return class_17922 instanceof class_1743;
            }
            case 2: {
                return class_17922 instanceof class_1829;
            }
            case 3: {
                return class_17922 instanceof class_1743 || class_17922 instanceof class_1829;
            }
        }
        return true;
    }

    @Override
    public void onDeactivate() {
        this.hitDelayTimer = 0;
        this.randomDelayTimer = 0;
        this.targets.clear();
    }

    private void rotate(class_1297 class_12972, Runnable runnable) {
        Rotations.rotate(Rotations.getYaw(class_12972), Rotations.getPitch(class_12972, Target.Body), runnable);
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send send) {
        if (send.packet instanceof class_2868) {
            this.switchTimer = this.switchDelay.get();
        }
    }

    private void lambda$attack$5(class_1297 class_12972) {
        this.hitEntity(class_12972);
    }

    private boolean lambda$new$3() {
        return this.randomDelayEnabled.get() != false && this.smartDelay.get() == false;
    }

    private boolean lambda$new$0() {
        return this.onlyWhenLook.get() == false;
    }

    private boolean lambda$new$1() {
        return this.smartDelay.get() == false;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (!this.mc.field_1724.method_5805() || PlayerUtils.getGameMode() == class_1934.field_9219) {
            return;
        }
        TargetUtils.getList(this.targets, this::entityCheck, this.priority.get(), this.maxTargets.get());
        if (this.targets.isEmpty()) {
            if (this.wasPathing) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("resume");
                this.wasPathing = false;
            }
            return;
        }
        if (this.pauseOnCombat.get().booleanValue() && BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing() && !this.wasPathing) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("pause");
            this.wasPathing = true;
        }
        class_1297 class_12972 = this.targets.get(0);
        if (this.rotation.get() == RotationMode.Always) {
            this.rotate(class_12972, null);
        }
        if (this.onlyOnClick.get().booleanValue() && !this.mc.field_1690.field_1886.method_1434()) {
            return;
        }
        if (this.onlyWhenLook.get().booleanValue()) {
            class_12972 = this.mc.field_1692;
            if (class_12972 == null) {
                return;
            }
            if (!this.entityCheck(class_12972)) {
                return;
            }
            this.targets.clear();
            this.targets.add(class_12972);
        }
        if (this.autoSwitch.get().booleanValue()) {
            FindItemResult findItemResult = InvUtils.findInHotbar(this::lambda$onTick$4);
            InvUtils.swap(findItemResult.getSlot());
        }
        if (!this.itemInHand()) {
            return;
        }
        if (this.delayCheck()) {
            this.targets.forEach(this::attack);
        }
        if (this.randomTeleport.get().booleanValue() && !this.onlyWhenLook.get().booleanValue()) {
            this.mc.field_1724.method_5814(class_12972.method_23317() + this.randomOffset(), class_12972.method_23318(), class_12972.method_23321() + this.randomOffset());
        }
    }

    private boolean lambda$new$2() {
        return this.smartDelay.get() == false;
    }

    private void attack(class_1297 class_12972) {
        if (Math.random() > this.hitChance.get() / 100.0) {
            return;
        }
        if (this.rotation.get() == RotationMode.OnHit) {
            this.rotate(class_12972, () -> this.lambda$attack$5(class_12972));
        } else {
            this.hitEntity(class_12972);
        }
    }

    public static enum Weapon {
        Sword,
        Axe,
        Both,
        Any;

    }

    public static enum RotationMode {
        Always,
        OnHit,
        None;

    }
}

