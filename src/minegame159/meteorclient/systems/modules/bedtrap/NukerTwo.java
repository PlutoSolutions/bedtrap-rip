/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1713
 *  net.minecraft.class_1792
 *  net.minecraft.class_1829
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_259
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_3532
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.bedtrap.InvUtils;
import minegame159.meteorclient.utils.player.ChatUtils;
import minegame159.meteorclient.utils.world.TickRate;
import net.minecraft.class_1268;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1829;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_259;
import net.minecraft.class_2596;
import net.minecraft.class_2846;
import net.minecraft.class_3532;

public class NukerTwo
extends Module {
    private final Setting<Boolean> onlySelected;
    private final Setting<Integer> leftBreak;
    private final Setting<Integer> backwardBreak;
    private final Setting<Integer> spamlimit;
    private final Setting<Double> Distance;
    private final Setting<List<class_2248>> selectedBlocks;
    private final Setting<Boolean> itemsaver;
    static final boolean $assertionsDisabled = !NukerTwo.class.desiredAssertionStatus();
    private final Setting<Boolean> sword;
    private final Setting<Integer> downBreak;
    private final Setting<Integer> upBreak;
    private final Setting<Integer> forwardBreak;
    int limit;
    private final Setting<Double> lagg;
    private final Setting<Boolean> replaceitems;
    private final Setting<Boolean> onlyOnGround;
    private final Setting<Boolean> swing;
    private final Setting<Integer> rightBreak;

    private void lambda$new$0(Boolean bl) {
        this.toggleitem();
    }

    private boolean swap_item() {
        boolean bl = false;
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        class_1792 class_17922 = this.mc.field_1724.method_6047().method_7909();
        for (int i = 0; i < this.mc.field_1724.field_7514.method_5439(); ++i) {
            if (this.mc.field_1724.field_7514.method_5438(i).method_7909() != class_17922 || this.mc.field_1724.field_7514.method_5438(i).method_7936() - this.mc.field_1724.field_7514.method_5438(i).method_7919() < 31) continue;
            InvUtils.clickSlot(InvUtils.invIndexToSlotId(i), this.mc.field_1724.field_7514.field_7545, class_1713.field_7791);
            bl = true;
            break;
        }
        return bl;
    }

    private double distance(double d, double d2, double d3) {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        double d4 = this.mc.field_1724.method_19538().method_10216() - d;
        if (d4 < 0.0) {
            d4 -= 1.0;
        }
        double d5 = this.mc.field_1724.method_19538().method_10214() - d2;
        double d6 = this.mc.field_1724.method_19538().method_10215() - d3;
        if (d6 < 0.0) {
            d6 -= 1.0;
        }
        return class_3532.method_15368((double)(d4 * d4 + d5 * d5 + d6 * d6));
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        try {
            if (this.onlyOnGround.get().booleanValue() && !this.mc.field_1724.method_24828()) {
                return;
            }
            if ((double)TickRate.INSTANCE.getTimeSinceLastTick() >= this.lagg.get()) {
                return;
            }
            if (this.sword.get().booleanValue() && this.mc.field_1724.method_6047().method_7909() instanceof class_1829) {
                return;
            }
            this.limit = 0;
            int n = this.mc.field_1724.method_24515().method_10263();
            int n2 = this.mc.field_1724.method_24515().method_10264();
            int n3 = this.mc.field_1724.method_24515().method_10260();
            for (int i = n - this.forwardBreak.get(); i <= n + this.backwardBreak.get(); ++i) {
                for (int j = n3 - this.rightBreak.get(); j <= n3 + this.leftBreak.get(); ++j) {
                    for (int k = n2 - this.downBreak.get(); k <= n2 + this.upBreak.get() - 1; ++k) {
                        class_2338 class_23382 = new class_2338(i, k, j);
                        if (!$assertionsDisabled && this.mc.field_1687 == null) {
                            throw new AssertionError();
                        }
                        if (this.mc.field_1687.method_8320(class_23382).method_26218((class_1922)this.mc.field_1687, class_23382) == class_259.method_1073() || this.mc.field_1687.method_8320(class_23382).method_26204() == class_2246.field_9987 || !(this.distance(class_23382.method_10263(), class_23382.method_10264(), class_23382.method_10260()) < this.Distance.get()) || this.onlySelected.get().booleanValue() && !this.selectedBlocks.get().contains((Object)this.mc.field_1687.method_8320(class_23382).method_26204())) continue;
                        if (this.limit > this.spamlimit.get()) {
                            return;
                        }
                        if (this.itemsaver.get().booleanValue() && this.mc.field_1724.method_6047().method_7919() != 0 && this.mc.field_1724.method_6047().method_7936() - this.mc.field_1724.method_6047().method_7919() < 31) {
                            if (this.replaceitems.get().booleanValue() && this.swap_item()) {
                                return;
                            }
                            ChatUtils.info("Your pickaxe durability is low!", new Object[0]);
                            this.toggle();
                            return;
                        }
                        if (this.swing.get().booleanValue()) {
                            this.mc.field_1724.method_6104(class_1268.field_5808);
                        }
                        this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, class_23382, class_2350.field_11036));
                        this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, class_23382, class_2350.field_11036));
                        this.limit += 2;
                        if (-1 < 2) continue;
                        return;
                    }
                }
                if (2 < 3) continue;
                return;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void lambda$new$1(Boolean bl) {
        this.togglereplace();
    }

    private void togglereplace() {
        if (this.replaceitems.get().booleanValue() && !this.itemsaver.get().booleanValue()) {
            this.itemsaver.set(true);
        }
    }

    public NukerTwo() {
        super(Categories.BedTrap, "nuker+", "Breaks a large amount of specified blocks around you.");
        SettingGroup settingGroup = this.settings.getDefaultGroup();
        this.onlyOnGround = settingGroup.add(new BoolSetting.Builder().name("only-on-ground").description("Works only when you standing on blocks.").defaultValue(true).build());
        this.itemsaver = settingGroup.add(new BoolSetting.Builder().name("save-items").description("Prevent destruction of tools.").defaultValue(true).onChanged(this::lambda$new$0).build());
        this.replaceitems = settingGroup.add(new BoolSetting.Builder().name("replace-items").description("Replace tools before breakage.").defaultValue(true).onChanged(this::lambda$new$1).build());
        this.sword = settingGroup.add(new BoolSetting.Builder().name("stop-on-sword").description("Pause nuker if sword in main hand.").defaultValue(true).build());
        this.swing = settingGroup.add(new BoolSetting.Builder().name("swing").description("Swing mainhand.").defaultValue(true).build());
        this.spamlimit = settingGroup.add(new IntSetting.Builder().name("speed").description("Block break speed.").defaultValue(25).min(1).sliderMin(1).sliderMax(100).build());
        this.lagg = settingGroup.add(new DoubleSetting.Builder().name("stop-on-lags").description("Pause on server lagging. (Time since last tick)").defaultValue(0.8).min(0.1).max(5.0).sliderMin(0.1).sliderMax(5.0).build());
        this.Distance = settingGroup.add(new DoubleSetting.Builder().name("distance").description("Maximum distance.").defaultValue(6.6).build());
        this.onlySelected = settingGroup.add(new BoolSetting.Builder().name("only-selected").description("Only mines your selected blocks.").defaultValue(false).build());
        this.selectedBlocks = settingGroup.add(new BlockListSetting.Builder().name("selected-blocks").description("The certain type of blocks you want to mine.").defaultValue(new ArrayList<class_2248>(0)).build());
        this.leftBreak = settingGroup.add(new IntSetting.Builder().name("left").defaultValue(1).min(0).max(6).sliderMin(0).sliderMax(6).build());
        this.rightBreak = settingGroup.add(new IntSetting.Builder().name("right").defaultValue(1).min(0).max(6).sliderMin(0).sliderMax(6).build());
        this.forwardBreak = settingGroup.add(new IntSetting.Builder().name("forward").defaultValue(1).min(0).max(6).sliderMin(0).sliderMax(6).build());
        this.backwardBreak = settingGroup.add(new IntSetting.Builder().name("backward").defaultValue(1).min(0).max(6).sliderMin(0).sliderMax(6).build());
        this.upBreak = settingGroup.add(new IntSetting.Builder().name("up").defaultValue(1).min(1).max(6).sliderMin(1).sliderMax(6).build());
        this.downBreak = settingGroup.add(new IntSetting.Builder().name("down").defaultValue(0).min(0).max(7).sliderMin(0).sliderMax(7).build());
        this.limit = 0;
    }

    @Override
    public void onActivate() {
        this.limit = 0;
    }

    private void toggleitem() {
        if (this.replaceitems.get().booleanValue() && !this.itemsaver.get().booleanValue()) {
            this.replaceitems.set(false);
        }
    }
}

