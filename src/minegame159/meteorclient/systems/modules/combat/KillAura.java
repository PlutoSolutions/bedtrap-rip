/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1429
 *  net.minecraft.class_1657
 *  net.minecraft.class_1743
 *  net.minecraft.class_1792
 *  net.minecraft.class_1829
 *  net.minecraft.class_1934
 *  net.minecraft.class_2868
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
import net.minecraft.class_1829;
import net.minecraft.class_1934;
import net.minecraft.class_2868;

public class KillAura
extends Module {
    private final /* synthetic */ Setting<Integer> switchDelay;
    private final /* synthetic */ Setting<Boolean> pauseOnCombat;
    private final /* synthetic */ Setting<Integer> hitDelay;
    private final /* synthetic */ Setting<Boolean> smartDelay;
    private /* synthetic */ int switchTimer;
    private final /* synthetic */ Setting<Boolean> onlyWhenLook;
    private final /* synthetic */ List<class_1297> targets;
    private final /* synthetic */ Setting<Boolean> autoSwitch;
    private final /* synthetic */ Setting<Weapon> weapon;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> randomDelayEnabled;
    private final /* synthetic */ Setting<Boolean> nametagged;
    private final /* synthetic */ Setting<Double> hitChance;
    private final /* synthetic */ Setting<Boolean> randomTeleport;
    private /* synthetic */ boolean wasPathing;
    private final /* synthetic */ Setting<Double> wallsRange;
    private final /* synthetic */ Setting<Boolean> onlyOnClick;
    private final /* synthetic */ Setting<Integer> maxTargets;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ Setting<RotationMode> rotation;
    private /* synthetic */ int hitDelayTimer;
    private final /* synthetic */ Setting<Boolean> babies;
    private final /* synthetic */ Setting<SortPriority> priority;
    private final /* synthetic */ SettingGroup sgTargeting;
    private final /* synthetic */ Setting<Integer> randomDelayMax;
    private /* synthetic */ int randomDelayTimer;
    private final /* synthetic */ SettingGroup sgDelay;
    private final /* synthetic */ Setting<Double> range;

    public class_1297 getTarget() {
        KillAura lllllllllllllllllllIIIlIlIlIIIll;
        if (!lllllllllllllllllllIIIlIlIlIIIll.targets.isEmpty()) {
            return lllllllllllllllllllIIIlIlIlIIIll.targets.get(0);
        }
        return null;
    }

    private void hitEntity(class_1297 lllllllllllllllllllIIIlIlIllIlIl) {
        KillAura lllllllllllllllllllIIIlIlIllIllI;
        lllllllllllllllllllIIIlIlIllIllI.mc.field_1761.method_2918((class_1657)lllllllllllllllllllIIIlIlIllIllI.mc.field_1724, lllllllllllllllllllIIIlIlIllIlIl);
        lllllllllllllllllllIIIlIlIllIllI.mc.field_1724.method_6104(class_1268.field_5808);
    }

    public KillAura() {
        super(Categories.Combat, "kill-aura", "Attacks specified entities around you.");
        KillAura lllllllllllllllllllIIIlIllIllllI;
        lllllllllllllllllllIIIlIllIllllI.sgGeneral = lllllllllllllllllllIIIlIllIllllI.settings.getDefaultGroup();
        lllllllllllllllllllIIIlIllIllllI.sgTargeting = lllllllllllllllllllIIIlIllIllllI.settings.createGroup("Targeting");
        lllllllllllllllllllIIIlIllIllllI.sgDelay = lllllllllllllllllllIIIlIllIllllI.settings.createGroup("Delay");
        lllllllllllllllllllIIIlIllIllllI.autoSwitch = lllllllllllllllllllIIIlIllIllllI.sgGeneral.add(new BoolSetting.Builder().name("auto-switch").description("Switches to your selected weapon when attacking the target.").defaultValue(false).build());
        lllllllllllllllllllIIIlIllIllllI.weapon = lllllllllllllllllllIIIlIllIllllI.sgGeneral.add(new EnumSetting.Builder().name("weapon").description("Only attacks an entity when a specified item is in your hand.").defaultValue(Weapon.Both).build());
        lllllllllllllllllllIIIlIllIllllI.onlyOnClick = lllllllllllllllllllIIIlIllIllllI.sgGeneral.add(new BoolSetting.Builder().name("only-on-click").description("Only attacks when hold left click.").defaultValue(false).build());
        lllllllllllllllllllIIIlIllIllllI.onlyWhenLook = lllllllllllllllllllIIIlIllIllllI.sgGeneral.add(new BoolSetting.Builder().name("only-when-look").description("Only attacks when you are looking at the entity.").defaultValue(false).build());
        lllllllllllllllllllIIIlIllIllllI.randomTeleport = lllllllllllllllllllIIIlIllIllllI.sgGeneral.add(new BoolSetting.Builder().name("random-teleport").description("Randomly teleport around the target").defaultValue(false).visible(() -> {
            KillAura lllllllllllllllllllIIIlIlIIIIlll;
            return lllllllllllllllllllIIIlIlIIIIlll.onlyWhenLook.get() == false;
        }).build());
        lllllllllllllllllllIIIlIllIllllI.rotation = lllllllllllllllllllIIIlIllIllllI.sgGeneral.add(new EnumSetting.Builder().name("rotate").description("Determines when you should rotate towards the target.").defaultValue(RotationMode.Always).build());
        lllllllllllllllllllIIIlIllIllllI.hitChance = lllllllllllllllllllIIIlIllIllllI.sgGeneral.add(new DoubleSetting.Builder().name("hit-chance").description("The probability of your hits landing.").defaultValue(100.0).min(0.0).max(100.0).sliderMax(100.0).build());
        lllllllllllllllllllIIIlIllIllllI.pauseOnCombat = lllllllllllllllllllIIIlIllIllllI.sgGeneral.add(new BoolSetting.Builder().name("pause-on-combat").description("Freezes Baritone temporarily until you are finished attacking the entity.").defaultValue(true).build());
        lllllllllllllllllllIIIlIllIllllI.entities = lllllllllllllllllllIIIlIllIllllI.sgTargeting.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to attack.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).onlyAttackable().build());
        lllllllllllllllllllIIIlIllIllllI.range = lllllllllllllllllllIIIlIllIllllI.sgTargeting.add(new DoubleSetting.Builder().name("range").description("The maximum range the entity can be to attack it.").defaultValue(4.5).min(0.0).sliderMax(6.0).build());
        lllllllllllllllllllIIIlIllIllllI.wallsRange = lllllllllllllllllllIIIlIllIllllI.sgTargeting.add(new DoubleSetting.Builder().name("walls-range").description("The maximum range the entity can be attacked through walls.").defaultValue(3.5).min(0.0).sliderMax(6.0).build());
        lllllllllllllllllllIIIlIllIllllI.priority = lllllllllllllllllllIIIlIllIllllI.sgTargeting.add(new EnumSetting.Builder().name("priority").description("How to filter targets within range.").defaultValue(SortPriority.LowestHealth).build());
        lllllllllllllllllllIIIlIllIllllI.maxTargets = lllllllllllllllllllIIIlIllIllllI.sgTargeting.add(new IntSetting.Builder().name("max-targets").description("How many entities to target at once.").defaultValue(1).min(1).max(10).sliderMin(1).sliderMax(5).build());
        lllllllllllllllllllIIIlIllIllllI.babies = lllllllllllllllllllIIIlIllIllllI.sgTargeting.add(new BoolSetting.Builder().name("babies").description("Whether or not to attack baby variants of the entity.").defaultValue(true).build());
        lllllllllllllllllllIIIlIllIllllI.nametagged = lllllllllllllllllllIIIlIllIllllI.sgTargeting.add(new BoolSetting.Builder().name("nametagged").description("Whether or not to attack mobs with a name tag.").defaultValue(false).build());
        lllllllllllllllllllIIIlIllIllllI.smartDelay = lllllllllllllllllllIIIlIllIllllI.sgDelay.add(new BoolSetting.Builder().name("smart-delay").description("Uses the vanilla cooldown to attack entities.").defaultValue(true).build());
        lllllllllllllllllllIIIlIllIllllI.hitDelay = lllllllllllllllllllIIIlIllIllllI.sgDelay.add(new IntSetting.Builder().name("hit-delay").description("How fast you hit the entity in ticks.").defaultValue(0).min(0).sliderMax(60).visible(() -> {
            KillAura lllllllllllllllllllIIIlIlIIIlIll;
            return lllllllllllllllllllIIIlIlIIIlIll.smartDelay.get() == false;
        }).build());
        lllllllllllllllllllIIIlIllIllllI.randomDelayEnabled = lllllllllllllllllllIIIlIllIllllI.sgDelay.add(new BoolSetting.Builder().name("random-delay-enabled").description("Adds a random delay between hits to attempt to bypass anti-cheats.").defaultValue(false).visible(() -> {
            KillAura lllllllllllllllllllIIIlIlIIIlllI;
            return lllllllllllllllllllIIIlIlIIIlllI.smartDelay.get() == false;
        }).build());
        lllllllllllllllllllIIIlIllIllllI.randomDelayMax = lllllllllllllllllllIIIlIllIllllI.sgDelay.add(new IntSetting.Builder().name("random-delay-max").description("The maximum value for random delay.").defaultValue(4).min(0).sliderMax(20).visible(() -> {
            KillAura lllllllllllllllllllIIIlIlIIlIIII;
            return lllllllllllllllllllIIIlIlIIlIIII.randomDelayEnabled.get() != false && lllllllllllllllllllIIIlIlIIlIIII.smartDelay.get() == false;
        }).build());
        lllllllllllllllllllIIIlIllIllllI.switchDelay = lllllllllllllllllllIIIlIllIllllI.sgDelay.add(new IntSetting.Builder().name("switch-delay").description("How many ticks to wait before hitting an entity after switching hotbar slots.").defaultValue(0).min(0).sliderMax(10).build());
        lllllllllllllllllllIIIlIllIllllI.targets = new ArrayList<class_1297>();
    }

    private boolean entityCheck(class_1297 lllllllllllllllllllIIIlIllIIIllI) {
        KillAura lllllllllllllllllllIIIlIllIIIlIl;
        if (lllllllllllllllllllIIIlIllIIIllI.equals((Object)lllllllllllllllllllIIIlIllIIIlIl.mc.field_1724) || lllllllllllllllllllIIIlIllIIIllI.equals((Object)lllllllllllllllllllIIIlIllIIIlIl.mc.field_1719)) {
            return false;
        }
        if (lllllllllllllllllllIIIlIllIIIllI instanceof class_1309 && ((class_1309)lllllllllllllllllllIIIlIllIIIllI).method_29504() || !lllllllllllllllllllIIIlIllIIIllI.method_5805()) {
            return false;
        }
        if (PlayerUtils.distanceTo(lllllllllllllllllllIIIlIllIIIllI) > lllllllllllllllllllIIIlIllIIIlIl.range.get()) {
            return false;
        }
        if (!lllllllllllllllllllIIIlIllIIIlIl.entities.get().getBoolean((Object)lllllllllllllllllllIIIlIllIIIllI.method_5864())) {
            return false;
        }
        if (!lllllllllllllllllllIIIlIllIIIlIl.nametagged.get().booleanValue() && lllllllllllllllllllIIIlIllIIIllI.method_16914()) {
            return false;
        }
        if (!PlayerUtils.canSeeEntity(lllllllllllllllllllIIIlIllIIIllI) && PlayerUtils.distanceTo(lllllllllllllllllllIIIlIllIIIllI) > lllllllllllllllllllIIIlIllIIIlIl.wallsRange.get()) {
            return false;
        }
        if (lllllllllllllllllllIIIlIllIIIllI instanceof class_1657) {
            if (((class_1657)lllllllllllllllllllIIIlIllIIIllI).method_7337()) {
                return false;
            }
            if (!Friends.get().shouldAttack((class_1657)lllllllllllllllllllIIIlIllIIIllI)) {
                return false;
            }
        }
        return !(lllllllllllllllllllIIIlIllIIIllI instanceof class_1429) || lllllllllllllllllllIIIlIllIIIlIl.babies.get() != false || !((class_1429)lllllllllllllllllllIIIlIllIIIllI).method_6109();
    }

    private boolean itemInHand() {
        KillAura lllllllllllllllllllIIIlIlIlIlIll;
        switch (lllllllllllllllllllIIIlIlIlIlIll.weapon.get()) {
            case Axe: {
                return lllllllllllllllllllIIIlIlIlIlIll.mc.field_1724.method_6047().method_7909() instanceof class_1743;
            }
            case Sword: {
                return lllllllllllllllllllIIIlIlIlIlIll.mc.field_1724.method_6047().method_7909() instanceof class_1829;
            }
            case Both: {
                return lllllllllllllllllllIIIlIlIlIlIll.mc.field_1724.method_6047().method_7909() instanceof class_1743 || lllllllllllllllllllIIIlIlIlIlIll.mc.field_1724.method_6047().method_7909() instanceof class_1829;
            }
        }
        return true;
    }

    private boolean delayCheck() {
        KillAura lllllllllllllllllllIIIlIllIIIIlI;
        if (lllllllllllllllllllIIIlIllIIIIlI.switchTimer > 0) {
            --lllllllllllllllllllIIIlIllIIIIlI.switchTimer;
            return false;
        }
        if (lllllllllllllllllllIIIlIllIIIIlI.smartDelay.get().booleanValue()) {
            return lllllllllllllllllllIIIlIllIIIIlI.mc.field_1724.method_7261(0.5f) >= 1.0f;
        }
        if (lllllllllllllllllllIIIlIllIIIIlI.hitDelayTimer >= 0) {
            --lllllllllllllllllllIIIlIllIIIIlI.hitDelayTimer;
            return false;
        }
        lllllllllllllllllllIIIlIllIIIIlI.hitDelayTimer = lllllllllllllllllllIIIlIllIIIIlI.hitDelay.get();
        if (lllllllllllllllllllIIIlIllIIIIlI.randomDelayEnabled.get().booleanValue()) {
            if (lllllllllllllllllllIIIlIllIIIIlI.randomDelayTimer > 0) {
                --lllllllllllllllllllIIIlIllIIIIlI.randomDelayTimer;
                return false;
            }
            lllllllllllllllllllIIIlIllIIIIlI.randomDelayTimer = (int)Math.round(Math.random() * (double)lllllllllllllllllllIIIlIllIIIIlI.randomDelayMax.get().intValue());
        }
        return true;
    }

    private double randomOffset() {
        return Math.random() * 4.0 - 2.0;
    }

    @Override
    public String getInfoString() {
        KillAura lllllllllllllllllllIIIlIlIlIIlll;
        if (!lllllllllllllllllllIIIlIlIlIIlll.targets.isEmpty()) {
            class_1297 lllllllllllllllllllIIIlIlIlIlIII = lllllllllllllllllllIIIlIlIlIIlll.targets.get(0);
            if (lllllllllllllllllllIIIlIlIlIlIII instanceof class_1657) {
                return lllllllllllllllllllIIIlIlIlIlIII.method_5820();
            }
            return lllllllllllllllllllIIIlIlIlIlIII.method_5864().method_5897().getString();
        }
        return null;
    }

    @Override
    public void onDeactivate() {
        KillAura lllllllllllllllllllIIIlIllIllIll;
        lllllllllllllllllllIIIlIllIllIll.hitDelayTimer = 0;
        lllllllllllllllllllIIIlIllIllIll.randomDelayTimer = 0;
        lllllllllllllllllllIIIlIllIllIll.targets.clear();
    }

    private void rotate(class_1297 lllllllllllllllllllIIIlIlIlIllll, Runnable lllllllllllllllllllIIIlIlIlIlllI) {
        Rotations.rotate(Rotations.getYaw(lllllllllllllllllllIIIlIlIlIllll), Rotations.getPitch(lllllllllllllllllllIIIlIlIlIllll, Target.Body), lllllllllllllllllllIIIlIlIlIlllI);
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send lllllllllllllllllllIIIlIllIIllIl) {
        if (lllllllllllllllllllIIIlIllIIllIl.packet instanceof class_2868) {
            KillAura lllllllllllllllllllIIIlIllIIllII;
            lllllllllllllllllllIIIlIllIIllII.switchTimer = lllllllllllllllllllIIIlIllIIllII.switchDelay.get();
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllllIIIlIllIlIlIl) {
        KillAura lllllllllllllllllllIIIlIllIlIIll;
        if (!lllllllllllllllllllIIIlIllIlIIll.mc.field_1724.method_5805() || PlayerUtils.getGameMode() == class_1934.field_9219) {
            return;
        }
        TargetUtils.getList(lllllllllllllllllllIIIlIllIlIIll.targets, lllllllllllllllllllIIIlIllIlIIll::entityCheck, lllllllllllllllllllIIIlIllIlIIll.priority.get(), lllllllllllllllllllIIIlIllIlIIll.maxTargets.get());
        if (lllllllllllllllllllIIIlIllIlIIll.targets.isEmpty()) {
            if (lllllllllllllllllllIIIlIllIlIIll.wasPathing) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("resume");
                lllllllllllllllllllIIIlIllIlIIll.wasPathing = false;
            }
            return;
        }
        if (lllllllllllllllllllIIIlIllIlIIll.pauseOnCombat.get().booleanValue() && BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing() && !lllllllllllllllllllIIIlIllIlIIll.wasPathing) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("pause");
            lllllllllllllllllllIIIlIllIlIIll.wasPathing = true;
        }
        class_1297 lllllllllllllllllllIIIlIllIlIlII = lllllllllllllllllllIIIlIllIlIIll.targets.get(0);
        if (lllllllllllllllllllIIIlIllIlIIll.rotation.get() == RotationMode.Always) {
            lllllllllllllllllllIIIlIllIlIIll.rotate(lllllllllllllllllllIIIlIllIlIlII, null);
        }
        if (lllllllllllllllllllIIIlIllIlIIll.onlyOnClick.get().booleanValue() && !lllllllllllllllllllIIIlIllIlIIll.mc.field_1690.field_1886.method_1434()) {
            return;
        }
        if (lllllllllllllllllllIIIlIllIlIIll.onlyWhenLook.get().booleanValue()) {
            lllllllllllllllllllIIIlIllIlIlII = lllllllllllllllllllIIIlIllIlIIll.mc.field_1692;
            if (lllllllllllllllllllIIIlIllIlIlII == null) {
                return;
            }
            if (!lllllllllllllllllllIIIlIllIlIIll.entityCheck(lllllllllllllllllllIIIlIllIlIlII)) {
                return;
            }
            lllllllllllllllllllIIIlIllIlIIll.targets.clear();
            lllllllllllllllllllIIIlIllIlIIll.targets.add(lllllllllllllllllllIIIlIllIlIlII);
        }
        if (lllllllllllllllllllIIIlIllIlIIll.autoSwitch.get().booleanValue()) {
            FindItemResult lllllllllllllllllllIIIlIllIlIlll = InvUtils.findInHotbar(lllllllllllllllllllIIIlIlIIlIlII -> {
                KillAura lllllllllllllllllllIIIlIlIIlIlIl;
                class_1792 lllllllllllllllllllIIIlIlIIlIllI = lllllllllllllllllllIIIlIlIIlIlII.method_7909();
                switch (lllllllllllllllllllIIIlIlIIlIlIl.weapon.get()) {
                    case Axe: {
                        return lllllllllllllllllllIIIlIlIIlIllI instanceof class_1743;
                    }
                    case Sword: {
                        return lllllllllllllllllllIIIlIlIIlIllI instanceof class_1829;
                    }
                    case Both: {
                        return lllllllllllllllllllIIIlIlIIlIllI instanceof class_1743 || lllllllllllllllllllIIIlIlIIlIllI instanceof class_1829;
                    }
                }
                return true;
            });
            InvUtils.swap(lllllllllllllllllllIIIlIllIlIlll.getSlot());
        }
        if (!lllllllllllllllllllIIIlIllIlIIll.itemInHand()) {
            return;
        }
        if (lllllllllllllllllllIIIlIllIlIIll.delayCheck()) {
            lllllllllllllllllllIIIlIllIlIIll.targets.forEach(lllllllllllllllllllIIIlIllIlIIll::attack);
        }
        if (lllllllllllllllllllIIIlIllIlIIll.randomTeleport.get().booleanValue() && !lllllllllllllllllllIIIlIllIlIIll.onlyWhenLook.get().booleanValue()) {
            lllllllllllllllllllIIIlIllIlIIll.mc.field_1724.method_5814(lllllllllllllllllllIIIlIllIlIlII.method_23317() + lllllllllllllllllllIIIlIllIlIIll.randomOffset(), lllllllllllllllllllIIIlIllIlIlII.method_23318(), lllllllllllllllllllIIIlIllIlIlII.method_23321() + lllllllllllllllllllIIIlIllIlIIll.randomOffset());
        }
    }

    private void attack(class_1297 lllllllllllllllllllIIIlIlIllllIl) {
        KillAura lllllllllllllllllllIIIlIlIllllII;
        if (Math.random() > lllllllllllllllllllIIIlIlIllllII.hitChance.get() / 100.0) {
            return;
        }
        if (lllllllllllllllllllIIIlIlIllllII.rotation.get() == RotationMode.OnHit) {
            lllllllllllllllllllIIIlIlIllllII.rotate(lllllllllllllllllllIIIlIlIllllIl, () -> {
                KillAura lllllllllllllllllllIIIlIlIIlllll;
                lllllllllllllllllllIIIlIlIIlllll.hitEntity(lllllllllllllllllllIIIlIlIllllIl);
            });
        } else {
            lllllllllllllllllllIIIlIlIllllII.hitEntity(lllllllllllllllllllIIIlIlIllllIl);
        }
    }

    public static enum Weapon {
        Sword,
        Axe,
        Both,
        Any;


        private Weapon() {
            Weapon llllllllllllllllllIllIlIIIIIlIIl;
        }
    }

    public static enum RotationMode {
        Always,
        OnHit,
        None;


        private RotationMode() {
            RotationMode lllllllllllllllllIllIlIIllIIIIIl;
        }
    }
}

