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
    private final SettingGroup sgGeneral;
    private final Setting<Integer> tickDelay;
    private final Setting<Boolean> unstackable;
    private final Setting<Boolean> searchHotbar;
    private final Setting<Boolean> offhand;
    private final Setting<List<class_1792>> excludedItems;
    private int tickDelayLeft;
    private final class_1799[] items;
    private boolean prevHadOpenScreen;
    private final Setting<Integer> threshold;

    private void addSlots(int n, int n2) {
        InvUtils.move().from(n2).to(n);
    }

    public AutoReplenish() {
        super(Categories.Player, "auto-replenish", "Automatically refills items in your hotbar, main hand, or offhand.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.threshold = this.sgGeneral.add(new IntSetting.Builder().name("threshold").description("The threshold of items left this actives at.").defaultValue(8).min(1).sliderMax(63).build());
        this.tickDelay = this.sgGeneral.add(new IntSetting.Builder().name("delay").description("The tick delay to replenish your hotbar.").defaultValue(1).min(0).sliderMax(10).build());
        this.offhand = this.sgGeneral.add(new BoolSetting.Builder().name("offhand").description("Whether or not to refill your offhand with items.").defaultValue(true).build());
        this.unstackable = this.sgGeneral.add(new BoolSetting.Builder().name("unstackable").description("Replenishes unstackable items.").defaultValue(true).build());
        this.searchHotbar = this.sgGeneral.add(new BoolSetting.Builder().name("search-hotbar").description("Uses items in your hotbar to replenish if they are the only ones left.").defaultValue(true).build());
        this.excludedItems = this.sgGeneral.add(new ItemListSetting.Builder().name("excluded-items").description("Items that WILL NOT replenish.").defaultValue(new ArrayList<class_1792>()).build());
        this.items = new class_1799[10];
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i] = new class_1799((class_1935)class_1802.field_8162);
            if (2 > 1) continue;
            throw null;
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.mc.field_1755 == null && this.prevHadOpenScreen) {
            this.fillItems();
        }
        boolean bl = this.prevHadOpenScreen = this.mc.field_1755 != null;
        if (this.mc.field_1724.field_7512.method_7602().size() != 46 || this.mc.field_1755 != null) {
            return;
        }
        if (this.tickDelayLeft <= 0) {
            this.tickDelayLeft = this.tickDelay.get();
            for (int i = 0; i < 9; ++i) {
                class_1799 class_17992 = this.mc.field_1724.field_7514.method_5438(i);
                this.checkSlot(i, class_17992);
                if (true) continue;
                return;
            }
            if (this.offhand.get().booleanValue() && !Modules.get().get(AutoTotem.class).getLocked()) {
                class_1799 class_17993 = this.mc.field_1724.method_6079();
                this.checkSlot(45, class_17993);
            }
        } else {
            --this.tickDelayLeft;
        }
    }

    private int findItem(class_1799 class_17992, int n, int n2) {
        int n3 = -1;
        int n4 = 0;
        for (int i = this.mc.field_1724.field_7514.method_5439() - 2; i >= (this.searchHotbar.get() != false ? 0 : 9); --i) {
            class_1799 class_17993 = this.mc.field_1724.field_7514.method_5438(i);
            if (i == n || class_17993.method_7909() != class_17992.method_7909() || !class_1799.method_7975((class_1799)class_17992, (class_1799)class_17993) || class_17993.method_7947() <= n4) continue;
            n3 = i;
            n4 = class_17993.method_7947();
            if (n4 >= n2) break;
            if (-1 < 1) continue;
            return 0;
        }
        return n3;
    }

    private void fillItems() {
        for (int i = 0; i < 9; ++i) {
            this.setItem(i, this.mc.field_1724.field_7514.method_5438(i));
            if (null == null) continue;
            return;
        }
        this.setItem(45, this.mc.field_1724.method_6079());
    }

    private void setItem(int n, class_1799 class_17992) {
        if (n == 45) {
            n = 9;
        }
        class_1799 class_17993 = this.items[n];
        ((ItemStackAccessor)class_17993).setItem(class_17992.method_7909());
        class_17993.method_7939(class_17992.method_7947());
        class_17993.method_7980(class_17992.method_7969());
        ((ItemStackAccessor)class_17993).setEmpty(class_17992.method_7960());
    }

    @Override
    public void onActivate() {
        this.fillItems();
        this.tickDelayLeft = this.tickDelay.get();
        this.prevHadOpenScreen = this.mc.field_1755 != null;
    }

    private void checkSlot(int n, class_1799 class_17992) {
        class_1799 class_17993 = this.getItem(n);
        if (!class_17992.method_7960() && class_17992.method_7946() && !this.excludedItems.get().contains((Object)class_17992.method_7909()) && class_17992.method_7947() <= this.threshold.get()) {
            this.addSlots(n, this.findItem(class_17992, n, this.threshold.get() - class_17992.method_7947() + 1));
        }
        if (class_17992.method_7960() && !class_17993.method_7960() && !this.excludedItems.get().contains((Object)class_17993.method_7909())) {
            if (class_17993.method_7946()) {
                this.addSlots(n, this.findItem(class_17993, n, this.threshold.get() - class_17992.method_7947() + 1));
            } else if (this.unstackable.get().booleanValue()) {
                this.addSlots(n, this.findItem(class_17993, n, 1));
            }
        }
        this.setItem(n, class_17992);
    }

    private class_1799 getItem(int n) {
        if (n == 45) {
            n = 9;
        }
        return this.items[n];
    }
}

