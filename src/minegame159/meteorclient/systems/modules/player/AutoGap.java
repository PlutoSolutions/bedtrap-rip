/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.player;

import baritone.api.BaritoneAPI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.ItemUseCrosshairTargetEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.combat.AnchorAura;
import minegame159.meteorclient.systems.modules.combat.BedAura;
import minegame159.meteorclient.systems.modules.combat.CrystalAura;
import minegame159.meteorclient.systems.modules.combat.KillAura;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1294;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;

public class AutoGap
extends Module {
    private final Setting<Boolean> potionsFireResistance;
    private int slot;
    private final Setting<Integer> healthThreshold;
    private int prevSlot;
    private final Setting<Boolean> preferEGap;
    private final Setting<Boolean> pauseAuras;
    private boolean eating;
    private final Setting<Boolean> pauseBaritone;
    private final Setting<Boolean> potionsRegeneration;
    private boolean wasBaritone;
    private final SettingGroup sgPotions;
    private static final Class<? extends Module>[] AURAS = new Class[]{KillAura.class, CrystalAura.class, AnchorAura.class, BedAura.class};
    private final List<Class<? extends Module>> wasAura;
    private final Setting<Boolean> potionsResistance;
    private final Setting<Boolean> always;
    private final SettingGroup sgHealth;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> healthEnabled;
    private boolean requiresEGap;

    private void stopEating() {
        this.changeSlot(this.prevSlot);
        this.setPressed(false);
        this.eating = false;
        if (this.pauseAuras.get().booleanValue()) {
            for (Class<? extends Module> clazz : AURAS) {
                Module module = Modules.get().get(clazz);
                if (!this.wasAura.contains(clazz) || module.isActive()) continue;
                module.toggle();
                if (-1 <= 4) continue;
                return;
            }
        }
        if (this.pauseBaritone.get().booleanValue() && this.wasBaritone) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("resume");
        }
    }

    private boolean isNotGapOrEGap(class_1799 class_17992) {
        class_1792 class_17922 = class_17992.method_7909();
        return class_17922 != class_1802.field_8463 && class_17922 != class_1802.field_8367;
    }

    private int findSlot() {
        boolean bl = this.preferEGap.get();
        if (this.requiresEGap) {
            bl = true;
        }
        int n = -1;
        class_1792 class_17922 = null;
        for (int i = 0; i < 9; ++i) {
            class_1799 class_17992 = this.mc.field_1724.field_7514.method_5438(i);
            if (class_17992.method_7960() || this.isNotGapOrEGap(class_17992)) continue;
            class_1792 class_17923 = class_17992.method_7909();
            if (class_17922 == null) {
                n = i;
                class_17922 = class_17923;
                continue;
            }
            if (class_17922 == class_17923) continue;
            if (class_17923 == class_1802.field_8367 && bl) {
                n = i;
                class_17922 = class_17923;
                break;
            }
            if (class_17923 != class_1802.field_8463 || bl) continue;
            n = i;
            class_17922 = class_17923;
            break;
        }
        if (this.requiresEGap && class_17922 != class_1802.field_8367) {
            return -1;
        }
        return n;
    }

    private boolean shouldEat() {
        this.requiresEGap = false;
        if (this.always.get().booleanValue()) {
            return true;
        }
        if (this.shouldEatPotions()) {
            return true;
        }
        return this.shouldEatHealth();
    }

    private void setPressed(boolean bl) {
        this.mc.field_1690.field_1904.method_23481(bl);
    }

    private boolean shouldEatPotions() {
        Map map = this.mc.field_1724.method_6088();
        if (this.potionsRegeneration.get().booleanValue() && !map.containsKey(class_1294.field_5924)) {
            return true;
        }
        if (this.potionsFireResistance.get().booleanValue() && !map.containsKey(class_1294.field_5918)) {
            this.requiresEGap = true;
            return true;
        }
        if (this.potionsResistance.get().booleanValue() && !map.containsKey(class_1294.field_5907)) {
            this.requiresEGap = true;
            return true;
        }
        return false;
    }

    private void startEating() {
        this.prevSlot = this.mc.field_1724.field_7514.field_7545;
        this.eat();
        this.wasAura.clear();
        if (this.pauseAuras.get().booleanValue()) {
            for (Class<? extends Module> clazz : AURAS) {
                Module module = Modules.get().get(clazz);
                if (module.isActive()) {
                    this.wasAura.add(clazz);
                    module.toggle();
                }
            }
        }
        this.wasBaritone = false;
        if (this.pauseBaritone.get().booleanValue() && BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing()) {
            this.wasBaritone = true;
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("pause");
        }
    }

    private boolean shouldEatHealth() {
        if (!this.healthEnabled.get().booleanValue()) {
            return false;
        }
        int n = Math.round(this.mc.field_1724.method_6032() + this.mc.field_1724.method_6067());
        return n < this.healthThreshold.get();
    }

    public boolean isEating() {
        return this.isActive() && this.eating;
    }

    private void eat() {
        this.changeSlot(this.slot);
        this.setPressed(true);
        if (!this.mc.field_1724.method_6115()) {
            Utils.rightClick();
        }
        this.eating = true;
    }

    @Override
    public void onDeactivate() {
        if (this.eating) {
            this.stopEating();
        }
    }

    @EventHandler
    private void onItemUseCrosshairTarget(ItemUseCrosshairTargetEvent itemUseCrosshairTargetEvent) {
        if (this.eating) {
            itemUseCrosshairTargetEvent.target = null;
        }
    }

    public AutoGap() {
        super(Categories.Player, "auto-gap", "Automatically eats Gaps or E-Gaps.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgPotions = this.settings.createGroup("Potions");
        this.sgHealth = this.settings.createGroup("Health");
        this.preferEGap = this.sgGeneral.add(new BoolSetting.Builder().name("prefer-egap").description("Prefers to eat E-Gap over Gaps if found.").defaultValue(true).build());
        this.always = this.sgGeneral.add(new BoolSetting.Builder().name("always").description("If it should always eat.").defaultValue(false).build());
        this.pauseAuras = this.sgGeneral.add(new BoolSetting.Builder().name("pause-auras").description("Pauses all auras when eating.").defaultValue(true).build());
        this.pauseBaritone = this.sgGeneral.add(new BoolSetting.Builder().name("pause-baritone").description("Pause baritone when eating.").defaultValue(true).build());
        this.potionsRegeneration = this.sgPotions.add(new BoolSetting.Builder().name("potions-regeneration").description("If it should eat when Regeneration runs out.").defaultValue(false).build());
        this.potionsFireResistance = this.sgPotions.add(new BoolSetting.Builder().name("potions-fire-resistance").description("If it should eat when Fire Resistance runs out. Requires E-Gaps.").defaultValue(true).build());
        this.potionsResistance = this.sgPotions.add(new BoolSetting.Builder().name("potions-absorption").description("If it should eat when Resistance runs out. Requires E-Gaps.").defaultValue(false).build());
        this.healthEnabled = this.sgHealth.add(new BoolSetting.Builder().name("health-enabled").description("If it should eat when health drops below threshold.").defaultValue(true).build());
        this.healthThreshold = this.sgHealth.add(new IntSetting.Builder().name("health-threshold").description("Health threshold to eat at. Includes absorption.").defaultValue(20).min(0).sliderMax(40).build());
        this.wasAura = new ArrayList<Class<? extends Module>>();
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.eating) {
            if (this.shouldEat()) {
                if (this.isNotGapOrEGap(this.mc.field_1724.field_7514.method_5438(this.slot))) {
                    int n = this.findSlot();
                    if (n == -1) {
                        this.stopEating();
                        return;
                    }
                    this.changeSlot(n);
                }
                this.eat();
            } else {
                this.stopEating();
            }
        } else if (this.shouldEat()) {
            this.slot = this.findSlot();
            if (this.slot != -1) {
                this.startEating();
            }
        }
    }

    private void changeSlot(int n) {
        InvUtils.swap(n);
        this.slot = n;
    }
}

