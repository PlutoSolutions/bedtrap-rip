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
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Integer> amount;
    private final /* synthetic */ Setting<Boolean> ignoreExisting;
    private /* synthetic */ int startCount;
    private final /* synthetic */ Setting<Boolean> selfToggle;

    @EventHandler
    private void onTick(TickEvent.Pre llIlIlIIIllIlIl) {
        EChestFarmer llIlIlIIIllIllI;
        if (llIlIlIIIllIllI.selfToggle.get().booleanValue()) {
            if (InvUtils.find(class_1802.field_8281).getCount() - (llIlIlIIIllIllI.ignoreExisting.get() != false ? llIlIlIIIllIllI.startCount : 0) >= llIlIlIIIllIllI.amount.get()) {
                llIlIlIIIllIllI.toggle();
                return;
            }
        }
        if (llIlIlIIIllIllI.mc.field_1765.method_17783() != class_239.class_240.field_1332) {
            return;
        }
        class_2338 llIlIlIIIllIlII = ((class_3965)llIlIlIIIllIllI.mc.field_1765).method_17777();
        if (llIlIlIIIllIllI.mc.field_1687.method_8320(llIlIlIIIllIlII).method_26204() == class_2246.field_10443) {
            FindItemResult llIlIlIIIllIlll = InvUtils.findInHotbar(llIlIlIIIlIllll -> AutoTool.isEffectiveOn(llIlIlIIIlIllll.method_7909(), class_2246.field_10443.method_9564()) && class_1890.method_8225((class_1887)class_1893.field_9099, (class_1799)llIlIlIIIlIllll) == 0);
            if (!llIlIlIIIllIlll.found()) {
                return;
            }
            InvUtils.swap(llIlIlIIIllIlll.getSlot());
            llIlIlIIIllIllI.mc.field_1761.method_2902(llIlIlIIIllIlII, class_2350.field_11036);
        } else if (llIlIlIIIllIllI.mc.field_1687.method_8320(llIlIlIIIllIlII.method_10084()).method_26207().method_15800()) {
            BlockUtils.place(llIlIlIIIllIlII.method_10084(), InvUtils.findInHotbar(class_1802.field_8466), false, 0, true);
        }
    }

    @Override
    public void onActivate() {
        llIlIlIIIllllII.startCount = InvUtils.find(class_1802.field_8281).getCount();
    }

    public EChestFarmer() {
        super(Categories.World, "echest-farmer", "Places and mines Ender Chests where you're looking.");
        EChestFarmer llIlIlIIIllllll;
        llIlIlIIIllllll.sgGeneral = llIlIlIIIllllll.settings.getDefaultGroup();
        llIlIlIIIllllll.selfToggle = llIlIlIIIllllll.sgGeneral.add(new BoolSetting.Builder().name("self-toggle").description("Disables when you reach the desired amount of obsidian.").defaultValue(false).build());
        llIlIlIIIllllll.ignoreExisting = llIlIlIIIllllll.sgGeneral.add(new BoolSetting.Builder().name("ignore-existing").description("Ignores existing obsidian in your inventory and mines the total target amount.").defaultValue(true).visible(llIlIlIIIllllll.selfToggle::get).build());
        llIlIlIIIllllll.amount = llIlIlIIIllllll.sgGeneral.add(new IntSetting.Builder().name("amount").description("The amount of obsidian to farm.").defaultValue(64).sliderMax(128).min(8).max(512).visible(llIlIlIIIllllll.selfToggle::get).build());
    }
}

