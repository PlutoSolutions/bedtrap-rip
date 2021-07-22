/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 */
package minegame159.meteorclient.systems.modules.player;

import baritone.api.BaritoneAPI;
import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.ItemUseCrosshairTargetEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.ItemListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.combat.AnchorAura;
import minegame159.meteorclient.systems.modules.combat.BedAura;
import minegame159.meteorclient.systems.modules.combat.CrystalAura;
import minegame159.meteorclient.systems.modules.combat.KillAura;
import minegame159.meteorclient.systems.modules.player.AutoGap;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1792;
import net.minecraft.class_1802;

public class AutoEat
extends Module {
    private final List<Class<? extends Module>> wasAura;
    private static final Class<? extends Module>[] AURAS = new Class[]{KillAura.class, CrystalAura.class, AnchorAura.class, BedAura.class};
    private boolean wasBaritone;
    private int slot;
    private final SettingGroup sgHunger;
    private int prevSlot;
    private final Setting<Integer> hungerThreshold;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> pauseBaritone;
    private final Setting<Boolean> pauseAuras;
    private final Setting<List<class_1792>> blacklist;
    private boolean eating;

    @EventHandler(priority=-100)
    private void onTick(TickEvent.Pre pre) {
        if (Modules.get().get(AutoGap.class).isEating()) {
            return;
        }
        if (this.eating) {
            if (this.shouldEat()) {
                if (!this.mc.field_1724.field_7514.method_5438(this.slot).method_19267()) {
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

    private void stopEating() {
        this.changeSlot(this.prevSlot);
        this.setPressed(false);
        this.eating = false;
        if (this.pauseAuras.get().booleanValue()) {
            for (Class<? extends Module> class_ : AURAS) {
                Module module = Modules.get().get(class_);
                if (!this.wasAura.contains(class_) || module.isActive()) continue;
                module.toggle();
                if (!false) continue;
                return;
            }
        }
        if (this.pauseBaritone.get().booleanValue() && this.wasBaritone) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("resume");
        }
    }

    @EventHandler
    private void onItemUseCrosshairTarget(ItemUseCrosshairTargetEvent itemUseCrosshairTargetEvent) {
        if (this.eating) {
            itemUseCrosshairTargetEvent.target = null;
        }
    }

    private void startEating() {
        this.prevSlot = this.mc.field_1724.field_7514.field_7545;
        this.eat();
        this.wasAura.clear();
        if (this.pauseAuras.get().booleanValue()) {
            for (Class<? extends Module> class_ : AURAS) {
                Module module = Modules.get().get(class_);
                if (!module.isActive()) continue;
                this.wasAura.add(class_);
                module.toggle();
                if (!false) continue;
                return;
            }
        }
        this.wasBaritone = false;
        if (this.pauseBaritone.get().booleanValue() && BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing()) {
            this.wasBaritone = true;
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("pause");
        }
    }

    private static List<class_1792> getDefaultBlacklist() {
        ArrayList<class_1792> arrayList = new ArrayList<class_1792>(9);
        arrayList.add(class_1802.field_8367);
        arrayList.add(class_1802.field_8463);
        arrayList.add(class_1802.field_8233);
        arrayList.add(class_1802.field_8635);
        arrayList.add(class_1802.field_8323);
        arrayList.add(class_1802.field_8726);
        arrayList.add(class_1802.field_8511);
        arrayList.add(class_1802.field_8680);
        arrayList.add(class_1802.field_8766);
        return arrayList;
    }

    private void eat() {
        this.changeSlot(this.slot);
        this.setPressed(true);
        if (!this.mc.field_1724.method_6115()) {
            Utils.rightClick();
        }
        this.eating = true;
    }

    public AutoEat() {
        super(Categories.Player, "auto-eat", "Automatically eats food.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgHunger = this.settings.createGroup("Hunger");
        this.blacklist = this.sgGeneral.add(new ItemListSetting.Builder().name("blacklist").description("Which items to not eat.").defaultValue(AutoEat.getDefaultBlacklist()).filter(class_1792::method_19263).build());
        this.pauseAuras = this.sgGeneral.add(new BoolSetting.Builder().name("pause-auras").description("Pauses all auras when eating.").defaultValue(true).build());
        this.pauseBaritone = this.sgGeneral.add(new BoolSetting.Builder().name("pause-baritone").description("Pause baritone when eating.").defaultValue(true).build());
        this.hungerThreshold = this.sgHunger.add(new IntSetting.Builder().name("hunger-threshold").description("The level of hunger you eat at.").defaultValue(16).min(1).max(19).sliderMin(1).sliderMax(19).build());
        this.wasAura = new ArrayList<Class<? extends Module>>();
    }

    private void setPressed(boolean bl) {
        this.mc.field_1690.field_1904.method_23481(bl);
    }

    private void changeSlot(int n) {
        InvUtils.swap(n);
        this.slot = n;
    }

    private int findSlot() {
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < 9; ++i) {
            int n3;
            class_1792 class_17922 = this.mc.field_1724.field_7514.method_5438(i).method_7909();
            if (!class_17922.method_19263() || (n3 = class_17922.method_19264().method_19230()) <= n2 || this.blacklist.get().contains((Object)class_17922)) continue;
            n = i;
            n2 = n3;
            if (null == null) continue;
            return 0;
        }
        return n;
    }

    private boolean shouldEat() {
        return this.mc.field_1724.method_7344().method_7586() <= this.hungerThreshold.get();
    }

    @Override
    public void onDeactivate() {
        if (this.eating) {
            this.stopEating();
        }
    }
}

