/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1887
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.world;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.player.AutoTool;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_3965;

public class EChestFarmer
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<Integer> amount;
    private final Setting<Boolean> ignoreExisting;
    private int startCount;
    private final Setting<Boolean> selfToggle;

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.selfToggle.get().booleanValue()) {
            if (InvUtils.find(class_1802.field_8281).getCount() - (this.ignoreExisting.get() != false ? this.startCount : 0) >= this.amount.get()) {
                this.toggle();
                return;
            }
        }
        if (this.mc.field_1765.method_17783() != class_239.class_240.field_1332) {
            return;
        }
        class_2338 class_23382 = ((class_3965)this.mc.field_1765).method_17777();
        if (this.mc.field_1687.method_8320(class_23382).method_26204() == class_2246.field_10443) {
            FindItemResult findItemResult = InvUtils.findInHotbar(EChestFarmer::lambda$onTick$0);
            if (!findItemResult.found()) {
                return;
            }
            InvUtils.swap(findItemResult.getSlot());
            this.mc.field_1761.method_2902(class_23382, class_2350.field_11036);
        } else if (this.mc.field_1687.method_8320(class_23382.method_10084()).method_26207().method_15800()) {
            BlockUtils.place(class_23382.method_10084(), InvUtils.findInHotbar(class_1802.field_8466), false, 0, true);
        }
    }

    private static boolean lambda$onTick$0(class_1799 class_17992) {
        return AutoTool.isEffectiveOn(class_17992.method_7909(), class_2246.field_10443.method_9564()) && class_1890.method_8225((class_1887)class_1893.field_9099, (class_1799)class_17992) == 0;
    }

    @Override
    public void onActivate() {
        this.startCount = InvUtils.find(class_1802.field_8281).getCount();
    }

    public EChestFarmer() {
        super(Categories.World, "echest-farmer", "Places and mines Ender Chests where you're looking.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.selfToggle = this.sgGeneral.add(new BoolSetting.Builder().name("self-toggle").description("Disables when you reach the desired amount of obsidian.").defaultValue(false).build());
        this.ignoreExisting = this.sgGeneral.add(new BoolSetting.Builder().name("ignore-existing").description("Ignores existing obsidian in your inventory and mines the total target amount.").defaultValue(true).visible(this.selfToggle::get).build());
        this.amount = this.sgGeneral.add(new IntSetting.Builder().name("amount").description("The amount of obsidian to farm.").defaultValue(64).sliderMax(128).min(8).max(512).visible(this.selfToggle::get).build());
    }
}

