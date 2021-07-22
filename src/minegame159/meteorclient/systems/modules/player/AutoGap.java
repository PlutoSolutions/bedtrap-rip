/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  net.minecraft.class_1294
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
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
    private final /* synthetic */ Setting<Boolean> potionsFireResistance;
    private /* synthetic */ int slot;
    private final /* synthetic */ Setting<Integer> healthThreshold;
    private /* synthetic */ int prevSlot;
    private final /* synthetic */ Setting<Boolean> preferEGap;
    private final /* synthetic */ Setting<Boolean> pauseAuras;
    private /* synthetic */ boolean eating;
    private final /* synthetic */ Setting<Boolean> pauseBaritone;
    private final /* synthetic */ Setting<Boolean> potionsRegeneration;
    private /* synthetic */ boolean wasBaritone;
    private final /* synthetic */ SettingGroup sgPotions;
    private static final /* synthetic */ Class<? extends Module>[] AURAS;
    private final /* synthetic */ List<Class<? extends Module>> wasAura;
    private final /* synthetic */ Setting<Boolean> potionsResistance;
    private final /* synthetic */ Setting<Boolean> always;
    private final /* synthetic */ SettingGroup sgHealth;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> healthEnabled;
    private /* synthetic */ boolean requiresEGap;

    private void stopEating() {
        AutoGap llIIlllIIIlIllI;
        llIIlllIIIlIllI.changeSlot(llIIlllIIIlIllI.prevSlot);
        llIIlllIIIlIllI.setPressed(false);
        llIIlllIIIlIllI.eating = false;
        if (llIIlllIIIlIllI.pauseAuras.get().booleanValue()) {
            for (Class<? extends Module> llIIlllIIIlIlll : AURAS) {
                Module llIIlllIIIllIII = Modules.get().get(llIIlllIIIlIlll);
                if (!llIIlllIIIlIllI.wasAura.contains(llIIlllIIIlIlll) || llIIlllIIIllIII.isActive()) continue;
                llIIlllIIIllIII.toggle();
            }
        }
        if (llIIlllIIIlIllI.pauseBaritone.get().booleanValue() && llIIlllIIIlIllI.wasBaritone) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("resume");
        }
    }

    private boolean isNotGapOrEGap(class_1799 llIIllIllIlllII) {
        class_1792 llIIllIllIllIll = llIIllIllIlllII.method_7909();
        return llIIllIllIllIll != class_1802.field_8463 && llIIllIllIllIll != class_1802.field_8367;
    }

    private int findSlot() {
        AutoGap llIIllIlllIlIlI;
        boolean llIIllIlllIlIIl = llIIllIlllIlIlI.preferEGap.get();
        if (llIIllIlllIlIlI.requiresEGap) {
            llIIllIlllIlIIl = true;
        }
        int llIIllIlllIlIII = -1;
        class_1792 llIIllIlllIIlll = null;
        for (int llIIllIlllIlIll = 0; llIIllIlllIlIll < 9; ++llIIllIlllIlIll) {
            class_1799 llIIllIlllIllIl = llIIllIlllIlIlI.mc.field_1724.field_7514.method_5438(llIIllIlllIlIll);
            if (llIIllIlllIllIl.method_7960() || llIIllIlllIlIlI.isNotGapOrEGap(llIIllIlllIllIl)) continue;
            class_1792 llIIllIlllIllII = llIIllIlllIllIl.method_7909();
            if (llIIllIlllIIlll == null) {
                llIIllIlllIlIII = llIIllIlllIlIll;
                llIIllIlllIIlll = llIIllIlllIllII;
                continue;
            }
            if (llIIllIlllIIlll == llIIllIlllIllII) continue;
            if (llIIllIlllIllII == class_1802.field_8367 && llIIllIlllIlIIl) {
                llIIllIlllIlIII = llIIllIlllIlIll;
                llIIllIlllIIlll = llIIllIlllIllII;
                break;
            }
            if (llIIllIlllIllII != class_1802.field_8463 || llIIllIlllIlIIl) continue;
            llIIllIlllIlIII = llIIllIlllIlIll;
            llIIllIlllIIlll = llIIllIlllIllII;
            break;
        }
        if (llIIllIlllIlIlI.requiresEGap && llIIllIlllIIlll != class_1802.field_8367) {
            return -1;
        }
        return llIIllIlllIlIII;
    }

    private boolean shouldEat() {
        AutoGap llIIlllIIIIIIIl;
        llIIlllIIIIIIIl.requiresEGap = false;
        if (llIIlllIIIIIIIl.always.get().booleanValue()) {
            return true;
        }
        if (llIIlllIIIIIIIl.shouldEatPotions()) {
            return true;
        }
        return llIIlllIIIIIIIl.shouldEatHealth();
    }

    private void setPressed(boolean llIIlllIIIIlIlI) {
        AutoGap llIIlllIIIIllIl;
        llIIlllIIIIllIl.mc.field_1690.field_1904.method_23481(llIIlllIIIIlIlI);
    }

    private boolean shouldEatPotions() {
        AutoGap llIIllIllllllII;
        Map llIIllIllllllIl = llIIllIllllllII.mc.field_1724.method_6088();
        if (llIIllIllllllII.potionsRegeneration.get().booleanValue() && !llIIllIllllllIl.containsKey((Object)class_1294.field_5924)) {
            return true;
        }
        if (llIIllIllllllII.potionsFireResistance.get().booleanValue() && !llIIllIllllllIl.containsKey((Object)class_1294.field_5918)) {
            llIIllIllllllII.requiresEGap = true;
            return true;
        }
        if (llIIllIllllllII.potionsResistance.get().booleanValue() && !llIIllIllllllIl.containsKey((Object)class_1294.field_5907)) {
            llIIllIllllllII.requiresEGap = true;
            return true;
        }
        return false;
    }

    private void startEating() {
        AutoGap llIIlllIIlIIlll;
        llIIlllIIlIIlll.prevSlot = llIIlllIIlIIlll.mc.field_1724.field_7514.field_7545;
        llIIlllIIlIIlll.eat();
        llIIlllIIlIIlll.wasAura.clear();
        if (llIIlllIIlIIlll.pauseAuras.get().booleanValue()) {
            for (Class<? extends Module> llIIlllIIlIlIIl : AURAS) {
                Module llIIlllIIlIlIlI = Modules.get().get(llIIlllIIlIlIIl);
                if (!llIIlllIIlIlIlI.isActive()) continue;
                llIIlllIIlIIlll.wasAura.add(llIIlllIIlIlIIl);
                llIIlllIIlIlIlI.toggle();
            }
        }
        llIIlllIIlIIlll.wasBaritone = false;
        if (llIIlllIIlIIlll.pauseBaritone.get().booleanValue() && BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing()) {
            llIIlllIIlIIlll.wasBaritone = true;
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("pause");
        }
    }

    private boolean shouldEatHealth() {
        AutoGap llIIllIllllIllI;
        if (!llIIllIllllIllI.healthEnabled.get().booleanValue()) {
            return false;
        }
        int llIIllIllllIlll = Math.round(llIIllIllllIllI.mc.field_1724.method_6032() + llIIllIllllIllI.mc.field_1724.method_6067());
        return llIIllIllllIlll < llIIllIllllIllI.healthThreshold.get();
    }

    public boolean isEating() {
        AutoGap llIIllIllIlIllI;
        return llIIllIllIlIllI.isActive() && llIIllIllIlIllI.eating;
    }

    private void eat() {
        AutoGap llIIlllIIIlllll;
        llIIlllIIIlllll.changeSlot(llIIlllIIIlllll.slot);
        llIIlllIIIlllll.setPressed(true);
        if (!llIIlllIIIlllll.mc.field_1724.method_6115()) {
            Utils.rightClick();
        }
        llIIlllIIIlllll.eating = true;
    }

    static {
        AURAS = new Class[]{KillAura.class, CrystalAura.class, AnchorAura.class, BedAura.class};
    }

    @Override
    public void onDeactivate() {
        AutoGap llIIlllIIllllll;
        if (llIIlllIIllllll.eating) {
            llIIlllIIllllll.stopEating();
        }
    }

    @EventHandler
    private void onItemUseCrosshairTarget(ItemUseCrosshairTargetEvent llIIlllIIllIIIl) {
        AutoGap llIIlllIIllIlII;
        if (llIIlllIIllIlII.eating) {
            llIIlllIIllIIIl.target = null;
        }
    }

    public AutoGap() {
        super(Categories.Player, "auto-gap", "Automatically eats Gaps or E-Gaps.");
        AutoGap llIIlllIlIIIIlI;
        llIIlllIlIIIIlI.sgGeneral = llIIlllIlIIIIlI.settings.getDefaultGroup();
        llIIlllIlIIIIlI.sgPotions = llIIlllIlIIIIlI.settings.createGroup("Potions");
        llIIlllIlIIIIlI.sgHealth = llIIlllIlIIIIlI.settings.createGroup("Health");
        llIIlllIlIIIIlI.preferEGap = llIIlllIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("prefer-egap").description("Prefers to eat E-Gap over Gaps if found.").defaultValue(true).build());
        llIIlllIlIIIIlI.always = llIIlllIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("always").description("If it should always eat.").defaultValue(false).build());
        llIIlllIlIIIIlI.pauseAuras = llIIlllIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("pause-auras").description("Pauses all auras when eating.").defaultValue(true).build());
        llIIlllIlIIIIlI.pauseBaritone = llIIlllIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("pause-baritone").description("Pause baritone when eating.").defaultValue(true).build());
        llIIlllIlIIIIlI.potionsRegeneration = llIIlllIlIIIIlI.sgPotions.add(new BoolSetting.Builder().name("potions-regeneration").description("If it should eat when Regeneration runs out.").defaultValue(false).build());
        llIIlllIlIIIIlI.potionsFireResistance = llIIlllIlIIIIlI.sgPotions.add(new BoolSetting.Builder().name("potions-fire-resistance").description("If it should eat when Fire Resistance runs out. Requires E-Gaps.").defaultValue(true).build());
        llIIlllIlIIIIlI.potionsResistance = llIIlllIlIIIIlI.sgPotions.add(new BoolSetting.Builder().name("potions-absorption").description("If it should eat when Resistance runs out. Requires E-Gaps.").defaultValue(false).build());
        llIIlllIlIIIIlI.healthEnabled = llIIlllIlIIIIlI.sgHealth.add(new BoolSetting.Builder().name("health-enabled").description("If it should eat when health drops below threshold.").defaultValue(true).build());
        llIIlllIlIIIIlI.healthThreshold = llIIlllIlIIIIlI.sgHealth.add(new IntSetting.Builder().name("health-threshold").description("Health threshold to eat at. Includes absorption.").defaultValue(20).min(0).sliderMax(40).build());
        llIIlllIlIIIIlI.wasAura = new ArrayList<Class<? extends Module>>();
    }

    @EventHandler
    private void onTick(TickEvent.Pre llIIlllIIlllIIl) {
        AutoGap llIIlllIIlllIII;
        if (llIIlllIIlllIII.eating) {
            if (llIIlllIIlllIII.shouldEat()) {
                if (llIIlllIIlllIII.isNotGapOrEGap(llIIlllIIlllIII.mc.field_1724.field_7514.method_5438(llIIlllIIlllIII.slot))) {
                    int llIIlllIIlllIll = llIIlllIIlllIII.findSlot();
                    if (llIIlllIIlllIll == -1) {
                        llIIlllIIlllIII.stopEating();
                        return;
                    }
                    llIIlllIIlllIII.changeSlot(llIIlllIIlllIll);
                }
                llIIlllIIlllIII.eat();
            } else {
                llIIlllIIlllIII.stopEating();
            }
        } else if (llIIlllIIlllIII.shouldEat()) {
            llIIlllIIlllIII.slot = llIIlllIIlllIII.findSlot();
            if (llIIlllIIlllIII.slot != -1) {
                llIIlllIIlllIII.startEating();
            }
        }
    }

    private void changeSlot(int llIIlllIIIIIlII) {
        InvUtils.swap(llIIlllIIIIIlII);
        llIIlllIIIIIlll.slot = llIIlllIIIIIlII;
    }
}

