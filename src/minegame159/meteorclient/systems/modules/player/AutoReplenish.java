/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 */
package minegame159.meteorclient.systems.modules.player;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.ItemStackAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.ItemListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.combat.AutoTotem;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;

public class AutoReplenish
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Integer> tickDelay;
    private final /* synthetic */ Setting<Boolean> unstackable;
    private final /* synthetic */ Setting<Boolean> searchHotbar;
    private final /* synthetic */ Setting<Boolean> offhand;
    private final /* synthetic */ Setting<List<class_1792>> excludedItems;
    private /* synthetic */ int tickDelayLeft;
    private final /* synthetic */ class_1799[] items;
    private /* synthetic */ boolean prevHadOpenScreen;
    private final /* synthetic */ Setting<Integer> threshold;

    private void addSlots(int lllllllllllllllllllIIlIlllIIllll, int lllllllllllllllllllIIlIlllIlIIII) {
        InvUtils.move().from(lllllllllllllllllllIIlIlllIlIIII).to(lllllllllllllllllllIIlIlllIIllll);
    }

    public AutoReplenish() {
        super(Categories.Player, "auto-replenish", "Automatically refills items in your hotbar, main hand, or offhand.");
        AutoReplenish lllllllllllllllllllIIllIIIIIlIII;
        lllllllllllllllllllIIllIIIIIlIII.sgGeneral = lllllllllllllllllllIIllIIIIIlIII.settings.getDefaultGroup();
        lllllllllllllllllllIIllIIIIIlIII.threshold = lllllllllllllllllllIIllIIIIIlIII.sgGeneral.add(new IntSetting.Builder().name("threshold").description("The threshold of items left this actives at.").defaultValue(8).min(1).sliderMax(63).build());
        lllllllllllllllllllIIllIIIIIlIII.tickDelay = lllllllllllllllllllIIllIIIIIlIII.sgGeneral.add(new IntSetting.Builder().name("delay").description("The tick delay to replenish your hotbar.").defaultValue(1).min(0).sliderMax(10).build());
        lllllllllllllllllllIIllIIIIIlIII.offhand = lllllllllllllllllllIIllIIIIIlIII.sgGeneral.add(new BoolSetting.Builder().name("offhand").description("Whether or not to refill your offhand with items.").defaultValue(true).build());
        lllllllllllllllllllIIllIIIIIlIII.unstackable = lllllllllllllllllllIIllIIIIIlIII.sgGeneral.add(new BoolSetting.Builder().name("unstackable").description("Replenishes unstackable items.").defaultValue(true).build());
        lllllllllllllllllllIIllIIIIIlIII.searchHotbar = lllllllllllllllllllIIllIIIIIlIII.sgGeneral.add(new BoolSetting.Builder().name("search-hotbar").description("Uses items in your hotbar to replenish if they are the only ones left.").defaultValue(true).build());
        lllllllllllllllllllIIllIIIIIlIII.excludedItems = lllllllllllllllllllIIllIIIIIlIII.sgGeneral.add(new ItemListSetting.Builder().name("excluded-items").description("Items that WILL NOT replenish.").defaultValue(new ArrayList<class_1792>()).build());
        lllllllllllllllllllIIllIIIIIlIII.items = new class_1799[10];
        for (int lllllllllllllllllllIIllIIIIIlIlI = 0; lllllllllllllllllllIIllIIIIIlIlI < lllllllllllllllllllIIllIIIIIlIII.items.length; ++lllllllllllllllllllIIllIIIIIlIlI) {
            lllllllllllllllllllIIllIIIIIlIII.items[lllllllllllllllllllIIllIIIIIlIlI] = new class_1799((class_1935)class_1802.field_8162);
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllllIIlIlllllllII) {
        AutoReplenish lllllllllllllllllllIIlIllllllIll;
        if (lllllllllllllllllllIIlIllllllIll.mc.field_1755 == null && lllllllllllllllllllIIlIllllllIll.prevHadOpenScreen) {
            lllllllllllllllllllIIlIllllllIll.fillItems();
        }
        boolean bl = lllllllllllllllllllIIlIllllllIll.prevHadOpenScreen = lllllllllllllllllllIIlIllllllIll.mc.field_1755 != null;
        if (lllllllllllllllllllIIlIllllllIll.mc.field_1724.field_7512.method_7602().size() != 46 || lllllllllllllllllllIIlIllllllIll.mc.field_1755 != null) {
            return;
        }
        if (lllllllllllllllllllIIlIllllllIll.tickDelayLeft <= 0) {
            lllllllllllllllllllIIlIllllllIll.tickDelayLeft = lllllllllllllllllllIIlIllllllIll.tickDelay.get();
            for (int lllllllllllllllllllIIlIlllllllll = 0; lllllllllllllllllllIIlIlllllllll < 9; ++lllllllllllllllllllIIlIlllllllll) {
                class_1799 lllllllllllllllllllIIllIIIIIIIII = lllllllllllllllllllIIlIllllllIll.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIIlIlllllllll);
                lllllllllllllllllllIIlIllllllIll.checkSlot(lllllllllllllllllllIIlIlllllllll, lllllllllllllllllllIIllIIIIIIIII);
            }
            if (lllllllllllllllllllIIlIllllllIll.offhand.get().booleanValue() && !Modules.get().get(AutoTotem.class).getLocked()) {
                class_1799 lllllllllllllllllllIIlIllllllllI = lllllllllllllllllllIIlIllllllIll.mc.field_1724.method_6079();
                lllllllllllllllllllIIlIllllllIll.checkSlot(45, lllllllllllllllllllIIlIllllllllI);
            }
        } else {
            --lllllllllllllllllllIIlIllllllIll.tickDelayLeft;
        }
    }

    private int findItem(class_1799 lllllllllllllllllllIIlIllllIIIIl, int lllllllllllllllllllIIlIlllIllIlI, int lllllllllllllllllllIIlIlllIlllll) {
        AutoReplenish lllllllllllllllllllIIlIllllIIIlI;
        int lllllllllllllllllllIIlIlllIllllI = -1;
        int lllllllllllllllllllIIlIlllIlllIl = 0;
        for (int lllllllllllllllllllIIlIllllIIIll = lllllllllllllllllllIIlIllllIIIlI.mc.field_1724.field_7514.method_5439() - 2; lllllllllllllllllllIIlIllllIIIll >= (lllllllllllllllllllIIlIllllIIIlI.searchHotbar.get() != false ? 0 : 9); --lllllllllllllllllllIIlIllllIIIll) {
            class_1799 lllllllllllllllllllIIlIllllIIlII = lllllllllllllllllllIIlIllllIIIlI.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIIlIllllIIIll);
            if (lllllllllllllllllllIIlIllllIIIll == lllllllllllllllllllIIlIlllIllIlI || lllllllllllllllllllIIlIllllIIlII.method_7909() != lllllllllllllllllllIIlIllllIIIIl.method_7909() || !class_1799.method_7975((class_1799)lllllllllllllllllllIIlIllllIIIIl, (class_1799)lllllllllllllllllllIIlIllllIIlII) || lllllllllllllllllllIIlIllllIIlII.method_7947() <= lllllllllllllllllllIIlIlllIlllIl) continue;
            lllllllllllllllllllIIlIlllIllllI = lllllllllllllllllllIIlIllllIIIll;
            lllllllllllllllllllIIlIlllIlllIl = lllllllllllllllllllIIlIllllIIlII.method_7947();
            if (lllllllllllllllllllIIlIlllIlllIl >= lllllllllllllllllllIIlIlllIlllll) break;
        }
        return lllllllllllllllllllIIlIlllIllllI;
    }

    private void fillItems() {
        AutoReplenish lllllllllllllllllllIIlIlllIIlIlI;
        for (int lllllllllllllllllllIIlIlllIIlIll = 0; lllllllllllllllllllIIlIlllIIlIll < 9; ++lllllllllllllllllllIIlIlllIIlIll) {
            lllllllllllllllllllIIlIlllIIlIlI.setItem(lllllllllllllllllllIIlIlllIIlIll, lllllllllllllllllllIIlIlllIIlIlI.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIIlIlllIIlIll));
        }
        lllllllllllllllllllIIlIlllIIlIlI.setItem(45, lllllllllllllllllllIIlIlllIIlIlI.mc.field_1724.method_6079());
    }

    private void setItem(int lllllllllllllllllllIIlIllIllllII, class_1799 lllllllllllllllllllIIlIllIllIlll) {
        AutoReplenish lllllllllllllllllllIIlIllIlllIIl;
        if (lllllllllllllllllllIIlIllIllllII == 45) {
            lllllllllllllllllllIIlIllIllllII = 9;
        }
        class_1799 lllllllllllllllllllIIlIllIlllIlI = lllllllllllllllllllIIlIllIlllIIl.items[lllllllllllllllllllIIlIllIllllII];
        ((ItemStackAccessor)lllllllllllllllllllIIlIllIlllIlI).setItem(lllllllllllllllllllIIlIllIllIlll.method_7909());
        lllllllllllllllllllIIlIllIlllIlI.method_7939(lllllllllllllllllllIIlIllIllIlll.method_7947());
        lllllllllllllllllllIIlIllIlllIlI.method_7980(lllllllllllllllllllIIlIllIllIlll.method_7969());
        ((ItemStackAccessor)lllllllllllllllllllIIlIllIlllIlI).setEmpty(lllllllllllllllllllIIlIllIllIlll.method_7960());
    }

    @Override
    public void onActivate() {
        AutoReplenish lllllllllllllllllllIIllIIIIIIlII;
        lllllllllllllllllllIIllIIIIIIlII.fillItems();
        lllllllllllllllllllIIllIIIIIIlII.tickDelayLeft = lllllllllllllllllllIIllIIIIIIlII.tickDelay.get();
        lllllllllllllllllllIIllIIIIIIlII.prevHadOpenScreen = lllllllllllllllllllIIllIIIIIIlII.mc.field_1755 != null;
    }

    private void checkSlot(int lllllllllllllllllllIIlIlllllIIll, class_1799 lllllllllllllllllllIIlIllllIlllI) {
        AutoReplenish lllllllllllllllllllIIlIlllllIlII;
        class_1799 lllllllllllllllllllIIlIlllllIIIl = lllllllllllllllllllIIlIlllllIlII.getItem(lllllllllllllllllllIIlIlllllIIll);
        if (!lllllllllllllllllllIIlIllllIlllI.method_7960() && lllllllllllllllllllIIlIllllIlllI.method_7946() && !lllllllllllllllllllIIlIlllllIlII.excludedItems.get().contains((Object)lllllllllllllllllllIIlIllllIlllI.method_7909()) && lllllllllllllllllllIIlIllllIlllI.method_7947() <= lllllllllllllllllllIIlIlllllIlII.threshold.get()) {
            lllllllllllllllllllIIlIlllllIlII.addSlots(lllllllllllllllllllIIlIlllllIIll, lllllllllllllllllllIIlIlllllIlII.findItem(lllllllllllllllllllIIlIllllIlllI, lllllllllllllllllllIIlIlllllIIll, lllllllllllllllllllIIlIlllllIlII.threshold.get() - lllllllllllllllllllIIlIllllIlllI.method_7947() + 1));
        }
        if (lllllllllllllllllllIIlIllllIlllI.method_7960() && !lllllllllllllllllllIIlIlllllIIIl.method_7960() && !lllllllllllllllllllIIlIlllllIlII.excludedItems.get().contains((Object)lllllllllllllllllllIIlIlllllIIIl.method_7909())) {
            if (lllllllllllllllllllIIlIlllllIIIl.method_7946()) {
                lllllllllllllllllllIIlIlllllIlII.addSlots(lllllllllllllllllllIIlIlllllIIll, lllllllllllllllllllIIlIlllllIlII.findItem(lllllllllllllllllllIIlIlllllIIIl, lllllllllllllllllllIIlIlllllIIll, lllllllllllllllllllIIlIlllllIlII.threshold.get() - lllllllllllllllllllIIlIllllIlllI.method_7947() + 1));
            } else if (lllllllllllllllllllIIlIlllllIlII.unstackable.get().booleanValue()) {
                lllllllllllllllllllIIlIlllllIlII.addSlots(lllllllllllllllllllIIlIlllllIIll, lllllllllllllllllllIIlIlllllIlII.findItem(lllllllllllllllllllIIlIlllllIIIl, lllllllllllllllllllIIlIlllllIIll, 1));
            }
        }
        lllllllllllllllllllIIlIlllllIlII.setItem(lllllllllllllllllllIIlIlllllIIll, lllllllllllllllllllIIlIllllIlllI);
    }

    private class_1799 getItem(int lllllllllllllllllllIIlIlllIIIIlI) {
        AutoReplenish lllllllllllllllllllIIlIlllIIIIll;
        if (lllllllllllllllllllIIlIlllIIIIlI == 45) {
            lllllllllllllllllllIIlIlllIIIIlI = 9;
        }
        return lllllllllllllllllllIIlIlllIIIIll.items[lllllllllllllllllllIIlIlllIIIIlI];
    }
}

