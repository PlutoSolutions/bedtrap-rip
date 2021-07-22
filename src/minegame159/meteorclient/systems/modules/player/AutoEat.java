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
    private final /* synthetic */ List<Class<? extends Module>> wasAura;
    private static final /* synthetic */ Class<? extends Module>[] AURAS;
    private /* synthetic */ boolean wasBaritone;
    private /* synthetic */ int slot;
    private final /* synthetic */ SettingGroup sgHunger;
    private /* synthetic */ int prevSlot;
    private final /* synthetic */ Setting<Integer> hungerThreshold;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> pauseBaritone;
    private final /* synthetic */ Setting<Boolean> pauseAuras;
    private final /* synthetic */ Setting<List<class_1792>> blacklist;
    private /* synthetic */ boolean eating;

    @EventHandler(priority=-100)
    private void onTick(TickEvent.Pre llllllllllllllllllIlIlIIIlIIIIII) {
        AutoEat llllllllllllllllllIlIlIIIIllllll;
        if (Modules.get().get(AutoGap.class).isEating()) {
            return;
        }
        if (llllllllllllllllllIlIlIIIIllllll.eating) {
            if (llllllllllllllllllIlIlIIIIllllll.shouldEat()) {
                if (!llllllllllllllllllIlIlIIIIllllll.mc.field_1724.field_7514.method_5438(llllllllllllllllllIlIlIIIIllllll.slot).method_19267()) {
                    int llllllllllllllllllIlIlIIIlIIIIlI = llllllllllllllllllIlIlIIIIllllll.findSlot();
                    if (llllllllllllllllllIlIlIIIlIIIIlI == -1) {
                        llllllllllllllllllIlIlIIIIllllll.stopEating();
                        return;
                    }
                    llllllllllllllllllIlIlIIIIllllll.changeSlot(llllllllllllllllllIlIlIIIlIIIIlI);
                }
                llllllllllllllllllIlIlIIIIllllll.eat();
            } else {
                llllllllllllllllllIlIlIIIIllllll.stopEating();
            }
        } else if (llllllllllllllllllIlIlIIIIllllll.shouldEat()) {
            llllllllllllllllllIlIlIIIIllllll.slot = llllllllllllllllllIlIlIIIIllllll.findSlot();
            if (llllllllllllllllllIlIlIIIIllllll.slot != -1) {
                llllllllllllllllllIlIlIIIIllllll.startEating();
            }
        }
    }

    private void stopEating() {
        AutoEat llllllllllllllllllIlIlIIIIIlllIl;
        llllllllllllllllllIlIlIIIIIlllIl.changeSlot(llllllllllllllllllIlIlIIIIIlllIl.prevSlot);
        llllllllllllllllllIlIlIIIIIlllIl.setPressed(false);
        llllllllllllllllllIlIlIIIIIlllIl.eating = false;
        if (llllllllllllllllllIlIlIIIIIlllIl.pauseAuras.get().booleanValue()) {
            for (Class<? extends Module> llllllllllllllllllIlIlIIIIIllllI : AURAS) {
                Module llllllllllllllllllIlIlIIIIIlllll = Modules.get().get(llllllllllllllllllIlIlIIIIIllllI);
                if (!llllllllllllllllllIlIlIIIIIlllIl.wasAura.contains(llllllllllllllllllIlIlIIIIIllllI) || llllllllllllllllllIlIlIIIIIlllll.isActive()) continue;
                llllllllllllllllllIlIlIIIIIlllll.toggle();
            }
        }
        if (llllllllllllllllllIlIlIIIIIlllIl.pauseBaritone.get().booleanValue() && llllllllllllllllllIlIlIIIIIlllIl.wasBaritone) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("resume");
        }
    }

    @EventHandler
    private void onItemUseCrosshairTarget(ItemUseCrosshairTargetEvent llllllllllllllllllIlIlIIIIlllIlI) {
        AutoEat llllllllllllllllllIlIlIIIIlllIIl;
        if (llllllllllllllllllIlIlIIIIlllIIl.eating) {
            llllllllllllllllllIlIlIIIIlllIlI.target = null;
        }
    }

    private void startEating() {
        AutoEat llllllllllllllllllIlIlIIIIlIllll;
        llllllllllllllllllIlIlIIIIlIllll.prevSlot = llllllllllllllllllIlIlIIIIlIllll.mc.field_1724.field_7514.field_7545;
        llllllllllllllllllIlIlIIIIlIllll.eat();
        llllllllllllllllllIlIlIIIIlIllll.wasAura.clear();
        if (llllllllllllllllllIlIlIIIIlIllll.pauseAuras.get().booleanValue()) {
            for (Class<? extends Module> llllllllllllllllllIlIlIIIIllIIII : AURAS) {
                Module llllllllllllllllllIlIlIIIIllIIIl = Modules.get().get(llllllllllllllllllIlIlIIIIllIIII);
                if (!llllllllllllllllllIlIlIIIIllIIIl.isActive()) continue;
                llllllllllllllllllIlIlIIIIlIllll.wasAura.add(llllllllllllllllllIlIlIIIIllIIII);
                llllllllllllllllllIlIlIIIIllIIIl.toggle();
            }
        }
        llllllllllllllllllIlIlIIIIlIllll.wasBaritone = false;
        if (llllllllllllllllllIlIlIIIIlIllll.pauseBaritone.get().booleanValue() && BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing()) {
            llllllllllllllllllIlIlIIIIlIllll.wasBaritone = true;
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("pause");
        }
    }

    private static List<class_1792> getDefaultBlacklist() {
        ArrayList<class_1792> llllllllllllllllllIlIIllllllIlII = new ArrayList<class_1792>(9);
        llllllllllllllllllIlIIllllllIlII.add(class_1802.field_8367);
        llllllllllllllllllIlIIllllllIlII.add(class_1802.field_8463);
        llllllllllllllllllIlIIllllllIlII.add(class_1802.field_8233);
        llllllllllllllllllIlIIllllllIlII.add(class_1802.field_8635);
        llllllllllllllllllIlIIllllllIlII.add(class_1802.field_8323);
        llllllllllllllllllIlIIllllllIlII.add(class_1802.field_8726);
        llllllllllllllllllIlIIllllllIlII.add(class_1802.field_8511);
        llllllllllllllllllIlIIllllllIlII.add(class_1802.field_8680);
        llllllllllllllllllIlIIllllllIlII.add(class_1802.field_8766);
        return llllllllllllllllllIlIIllllllIlII;
    }

    private void eat() {
        AutoEat llllllllllllllllllIlIlIIIIlIIlll;
        llllllllllllllllllIlIlIIIIlIIlll.changeSlot(llllllllllllllllllIlIlIIIIlIIlll.slot);
        llllllllllllllllllIlIlIIIIlIIlll.setPressed(true);
        if (!llllllllllllllllllIlIlIIIIlIIlll.mc.field_1724.method_6115()) {
            Utils.rightClick();
        }
        llllllllllllllllllIlIlIIIIlIIlll.eating = true;
    }

    public AutoEat() {
        super(Categories.Player, "auto-eat", "Automatically eats food.");
        AutoEat llllllllllllllllllIlIlIIIlIIlIIl;
        llllllllllllllllllIlIlIIIlIIlIIl.sgGeneral = llllllllllllllllllIlIlIIIlIIlIIl.settings.getDefaultGroup();
        llllllllllllllllllIlIlIIIlIIlIIl.sgHunger = llllllllllllllllllIlIlIIIlIIlIIl.settings.createGroup("Hunger");
        llllllllllllllllllIlIlIIIlIIlIIl.blacklist = llllllllllllllllllIlIlIIIlIIlIIl.sgGeneral.add(new ItemListSetting.Builder().name("blacklist").description("Which items to not eat.").defaultValue(AutoEat.getDefaultBlacklist()).filter(class_1792::method_19263).build());
        llllllllllllllllllIlIlIIIlIIlIIl.pauseAuras = llllllllllllllllllIlIlIIIlIIlIIl.sgGeneral.add(new BoolSetting.Builder().name("pause-auras").description("Pauses all auras when eating.").defaultValue(true).build());
        llllllllllllllllllIlIlIIIlIIlIIl.pauseBaritone = llllllllllllllllllIlIlIIIlIIlIIl.sgGeneral.add(new BoolSetting.Builder().name("pause-baritone").description("Pause baritone when eating.").defaultValue(true).build());
        llllllllllllllllllIlIlIIIlIIlIIl.hungerThreshold = llllllllllllllllllIlIlIIIlIIlIIl.sgHunger.add(new IntSetting.Builder().name("hunger-threshold").description("The level of hunger you eat at.").defaultValue(16).min(1).max(19).sliderMin(1).sliderMax(19).build());
        llllllllllllllllllIlIlIIIlIIlIIl.wasAura = new ArrayList<Class<? extends Module>>();
    }

    private void setPressed(boolean llllllllllllllllllIlIlIIIIIlIIIl) {
        AutoEat llllllllllllllllllIlIlIIIIIlIlII;
        llllllllllllllllllIlIlIIIIIlIlII.mc.field_1690.field_1904.method_23481(llllllllllllllllllIlIlIIIIIlIIIl);
    }

    private void changeSlot(int llllllllllllllllllIlIlIIIIIIllIl) {
        InvUtils.swap(llllllllllllllllllIlIlIIIIIIllIl);
        llllllllllllllllllIlIlIIIIIIlllI.slot = llllllllllllllllllIlIlIIIIIIllIl;
    }

    private int findSlot() {
        int llllllllllllllllllIlIIllllllllIl = -1;
        int llllllllllllllllllIlIIllllllllII = -1;
        for (int llllllllllllllllllIlIIllllllllll = 0; llllllllllllllllllIlIIllllllllll < 9; ++llllllllllllllllllIlIIllllllllll) {
            int llllllllllllllllllIlIlIIIIIIIIII;
            AutoEat llllllllllllllllllIlIIlllllllIll;
            class_1792 llllllllllllllllllIlIlIIIIIIIIIl = llllllllllllllllllIlIIlllllllIll.mc.field_1724.field_7514.method_5438(llllllllllllllllllIlIIllllllllll).method_7909();
            if (!llllllllllllllllllIlIlIIIIIIIIIl.method_19263() || (llllllllllllllllllIlIlIIIIIIIIII = llllllllllllllllllIlIlIIIIIIIIIl.method_19264().method_19230()) <= llllllllllllllllllIlIIllllllllII || llllllllllllllllllIlIIlllllllIll.blacklist.get().contains((Object)llllllllllllllllllIlIlIIIIIIIIIl)) continue;
            llllllllllllllllllIlIIllllllllIl = llllllllllllllllllIlIIllllllllll;
            llllllllllllllllllIlIIllllllllII = llllllllllllllllllIlIlIIIIIIIIII;
        }
        return llllllllllllllllllIlIIllllllllIl;
    }

    static {
        AURAS = new Class[]{KillAura.class, CrystalAura.class, AnchorAura.class, BedAura.class};
    }

    private boolean shouldEat() {
        AutoEat llllllllllllllllllIlIlIIIIIIlIII;
        return llllllllllllllllllIlIlIIIIIIlIII.mc.field_1724.method_7344().method_7586() <= llllllllllllllllllIlIlIIIIIIlIII.hungerThreshold.get();
    }

    @Override
    public void onDeactivate() {
        AutoEat llllllllllllllllllIlIlIIIlIIIllI;
        if (llllllllllllllllllIlIlIIIlIIIllI.eating) {
            llllllllllllllllllIlIlIIIlIIIllI.stopEating();
        }
    }
}

