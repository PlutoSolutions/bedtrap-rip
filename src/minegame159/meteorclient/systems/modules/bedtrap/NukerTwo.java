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
    private final /* synthetic */ Setting<Boolean> onlySelected;
    private final /* synthetic */ Setting<Integer> leftBreak;
    private final /* synthetic */ Setting<Integer> backwardBreak;
    private final /* synthetic */ Setting<Integer> spamlimit;
    private final /* synthetic */ Setting<Double> Distance;
    private final /* synthetic */ Setting<List<class_2248>> selectedBlocks;
    private final /* synthetic */ Setting<Boolean> itemsaver;
    private final /* synthetic */ Setting<Boolean> sword;
    private final /* synthetic */ Setting<Integer> downBreak;
    private final /* synthetic */ Setting<Integer> upBreak;
    private final /* synthetic */ Setting<Integer> forwardBreak;
    /* synthetic */ int limit;
    private final /* synthetic */ Setting<Double> lagg;
    private final /* synthetic */ Setting<Boolean> replaceitems;
    private final /* synthetic */ Setting<Boolean> onlyOnGround;
    private final /* synthetic */ Setting<Boolean> swing;
    private final /* synthetic */ Setting<Integer> rightBreak;

    private boolean swap_item() {
        NukerTwo lllllllllllllllllllIIlIlIIlIlIll;
        boolean lllllllllllllllllllIIlIlIIlIlIlI = false;
        assert (lllllllllllllllllllIIlIlIIlIlIll.mc.field_1724 != null);
        class_1792 lllllllllllllllllllIIlIlIIlIlIIl = lllllllllllllllllllIIlIlIIlIlIll.mc.field_1724.method_6047().method_7909();
        for (int lllllllllllllllllllIIlIlIIlIllII = 0; lllllllllllllllllllIIlIlIIlIllII < lllllllllllllllllllIIlIlIIlIlIll.mc.field_1724.field_7514.method_5439(); ++lllllllllllllllllllIIlIlIIlIllII) {
            if (lllllllllllllllllllIIlIlIIlIlIll.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIIlIlIIlIllII).method_7909() != lllllllllllllllllllIIlIlIIlIlIIl || lllllllllllllllllllIIlIlIIlIlIll.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIIlIlIIlIllII).method_7936() - lllllllllllllllllllIIlIlIIlIlIll.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIIlIlIIlIllII).method_7919() < 31) continue;
            InvUtils.clickSlot(InvUtils.invIndexToSlotId(lllllllllllllllllllIIlIlIIlIllII), lllllllllllllllllllIIlIlIIlIlIll.mc.field_1724.field_7514.field_7545, class_1713.field_7791);
            lllllllllllllllllllIIlIlIIlIlIlI = true;
            break;
        }
        return lllllllllllllllllllIIlIlIIlIlIlI;
    }

    private double distance(double lllllllllllllllllllIIlIlIlIIIIII, double lllllllllllllllllllIIlIlIIlllIII, double lllllllllllllllllllIIlIlIIllIlll) {
        NukerTwo lllllllllllllllllllIIlIlIlIIIIIl;
        assert (lllllllllllllllllllIIlIlIlIIIIIl.mc.field_1724 != null);
        double lllllllllllllllllllIIlIlIIllllIl = lllllllllllllllllllIIlIlIlIIIIIl.mc.field_1724.method_19538().method_10216() - lllllllllllllllllllIIlIlIlIIIIII;
        if (lllllllllllllllllllIIlIlIIllllIl < 0.0) {
            lllllllllllllllllllIIlIlIIllllIl -= 1.0;
        }
        double lllllllllllllllllllIIlIlIIllllII = lllllllllllllllllllIIlIlIlIIIIIl.mc.field_1724.method_19538().method_10214() - lllllllllllllllllllIIlIlIIlllIII;
        double lllllllllllllllllllIIlIlIIlllIll = lllllllllllllllllllIIlIlIlIIIIIl.mc.field_1724.method_19538().method_10215() - lllllllllllllllllllIIlIlIIllIlll;
        if (lllllllllllllllllllIIlIlIIlllIll < 0.0) {
            lllllllllllllllllllIIlIlIIlllIll -= 1.0;
        }
        return class_3532.method_15368((double)(lllllllllllllllllllIIlIlIIllllIl * lllllllllllllllllllIIlIlIIllllIl + lllllllllllllllllllIIlIlIIllllII * lllllllllllllllllllIIlIlIIllllII + lllllllllllllllllllIIlIlIIlllIll * lllllllllllllllllllIIlIlIIlllIll));
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllllIIlIlIIIIlIII) {
        NukerTwo lllllllllllllllllllIIlIlIIIIlIIl;
        assert (lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1724 != null);
        try {
            if (lllllllllllllllllllIIlIlIIIIlIIl.onlyOnGround.get().booleanValue() && !lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1724.method_24828()) {
                return;
            }
            if ((double)TickRate.INSTANCE.getTimeSinceLastTick() >= lllllllllllllllllllIIlIlIIIIlIIl.lagg.get()) {
                return;
            }
            if (lllllllllllllllllllIIlIlIIIIlIIl.sword.get().booleanValue() && lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1724.method_6047().method_7909() instanceof class_1829) {
                return;
            }
            lllllllllllllllllllIIlIlIIIIlIIl.limit = 0;
            int lllllllllllllllllllIIlIlIIIIllII = lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1724.method_24515().method_10263();
            int lllllllllllllllllllIIlIlIIIIlIll = lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1724.method_24515().method_10264();
            int lllllllllllllllllllIIlIlIIIIlIlI = lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1724.method_24515().method_10260();
            for (int lllllllllllllllllllIIlIlIIIIllIl = lllllllllllllllllllIIlIlIIIIllII - lllllllllllllllllllIIlIlIIIIlIIl.forwardBreak.get(); lllllllllllllllllllIIlIlIIIIllIl <= lllllllllllllllllllIIlIlIIIIllII + lllllllllllllllllllIIlIlIIIIlIIl.backwardBreak.get(); ++lllllllllllllllllllIIlIlIIIIllIl) {
                for (int lllllllllllllllllllIIlIlIIIIlllI = lllllllllllllllllllIIlIlIIIIlIlI - lllllllllllllllllllIIlIlIIIIlIIl.rightBreak.get(); lllllllllllllllllllIIlIlIIIIlllI <= lllllllllllllllllllIIlIlIIIIlIlI + lllllllllllllllllllIIlIlIIIIlIIl.leftBreak.get(); ++lllllllllllllllllllIIlIlIIIIlllI) {
                    for (int lllllllllllllllllllIIlIlIIIIllll = lllllllllllllllllllIIlIlIIIIlIll - lllllllllllllllllllIIlIlIIIIlIIl.downBreak.get(); lllllllllllllllllllIIlIlIIIIllll <= lllllllllllllllllllIIlIlIIIIlIll + lllllllllllllllllllIIlIlIIIIlIIl.upBreak.get() - 1; ++lllllllllllllllllllIIlIlIIIIllll) {
                        class_2338 lllllllllllllllllllIIlIlIIIlIIII = new class_2338(lllllllllllllllllllIIlIlIIIIllIl, lllllllllllllllllllIIlIlIIIIllll, lllllllllllllllllllIIlIlIIIIlllI);
                        assert (lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1687 != null);
                        if (lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1687.method_8320(lllllllllllllllllllIIlIlIIIlIIII).method_26218((class_1922)lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1687, lllllllllllllllllllIIlIlIIIlIIII) == class_259.method_1073() || lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1687.method_8320(lllllllllllllllllllIIlIlIIIlIIII).method_26204() == class_2246.field_9987 || !(lllllllllllllllllllIIlIlIIIIlIIl.distance(lllllllllllllllllllIIlIlIIIlIIII.method_10263(), lllllllllllllllllllIIlIlIIIlIIII.method_10264(), lllllllllllllllllllIIlIlIIIlIIII.method_10260()) < lllllllllllllllllllIIlIlIIIIlIIl.Distance.get()) || lllllllllllllllllllIIlIlIIIIlIIl.onlySelected.get().booleanValue() && !lllllllllllllllllllIIlIlIIIIlIIl.selectedBlocks.get().contains((Object)lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1687.method_8320(lllllllllllllllllllIIlIlIIIlIIII).method_26204())) continue;
                        if (lllllllllllllllllllIIlIlIIIIlIIl.limit > lllllllllllllllllllIIlIlIIIIlIIl.spamlimit.get()) {
                            return;
                        }
                        if (lllllllllllllllllllIIlIlIIIIlIIl.itemsaver.get().booleanValue() && lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1724.method_6047().method_7919() != 0 && lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1724.method_6047().method_7936() - lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1724.method_6047().method_7919() < 31) {
                            if (lllllllllllllllllllIIlIlIIIIlIIl.replaceitems.get().booleanValue() && lllllllllllllllllllIIlIlIIIIlIIl.swap_item()) {
                                return;
                            }
                            ChatUtils.info("Your pickaxe durability is low!", new Object[0]);
                            lllllllllllllllllllIIlIlIIIIlIIl.toggle();
                            return;
                        }
                        if (lllllllllllllllllllIIlIlIIIIlIIl.swing.get().booleanValue()) {
                            lllllllllllllllllllIIlIlIIIIlIIl.mc.field_1724.method_6104(class_1268.field_5808);
                        }
                        lllllllllllllllllllIIlIlIIIIlIIl.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, lllllllllllllllllllIIlIlIIIlIIII, class_2350.field_11036));
                        lllllllllllllllllllIIlIlIIIIlIIl.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, lllllllllllllllllllIIlIlIIIlIIII, class_2350.field_11036));
                        lllllllllllllllllllIIlIlIIIIlIIl.limit += 2;
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void togglereplace() {
        NukerTwo lllllllllllllllllllIIlIlIIlIIIll;
        if (lllllllllllllllllllIIlIlIIlIIIll.replaceitems.get().booleanValue() && !lllllllllllllllllllIIlIlIIlIIIll.itemsaver.get().booleanValue()) {
            lllllllllllllllllllIIlIlIIlIIIll.itemsaver.set(true);
        }
    }

    public NukerTwo() {
        super(Categories.BedTrap, "nuker+", "Breaks a large amount of specified blocks around you.");
        NukerTwo lllllllllllllllllllIIlIlIIIllIlI;
        SettingGroup lllllllllllllllllllIIlIlIIIllIll = lllllllllllllllllllIIlIlIIIllIlI.settings.getDefaultGroup();
        lllllllllllllllllllIIlIlIIIllIlI.onlyOnGround = lllllllllllllllllllIIlIlIIIllIll.add(new BoolSetting.Builder().name("only-on-ground").description("Works only when you standing on blocks.").defaultValue(true).build());
        lllllllllllllllllllIIlIlIIIllIlI.itemsaver = lllllllllllllllllllIIlIlIIIllIll.add(new BoolSetting.Builder().name("save-items").description("Prevent destruction of tools.").defaultValue(true).onChanged(lllllllllllllllllllIIlIIlllllIIl -> {
            NukerTwo lllllllllllllllllllIIlIIlllllIlI;
            lllllllllllllllllllIIlIIlllllIlI.toggleitem();
        }).build());
        lllllllllllllllllllIIlIlIIIllIlI.replaceitems = lllllllllllllllllllIIlIlIIIllIll.add(new BoolSetting.Builder().name("replace-items").description("Replace tools before breakage.").defaultValue(true).onChanged(lllllllllllllllllllIIlIIllllllIl -> {
            NukerTwo lllllllllllllllllllIIlIIllllllII;
            lllllllllllllllllllIIlIIllllllII.togglereplace();
        }).build());
        lllllllllllllllllllIIlIlIIIllIlI.sword = lllllllllllllllllllIIlIlIIIllIll.add(new BoolSetting.Builder().name("stop-on-sword").description("Pause nuker if sword in main hand.").defaultValue(true).build());
        lllllllllllllllllllIIlIlIIIllIlI.swing = lllllllllllllllllllIIlIlIIIllIll.add(new BoolSetting.Builder().name("swing").description("Swing mainhand.").defaultValue(true).build());
        lllllllllllllllllllIIlIlIIIllIlI.spamlimit = lllllllllllllllllllIIlIlIIIllIll.add(new IntSetting.Builder().name("speed").description("Block break speed.").defaultValue(25).min(1).sliderMin(1).sliderMax(100).build());
        lllllllllllllllllllIIlIlIIIllIlI.lagg = lllllllllllllllllllIIlIlIIIllIll.add(new DoubleSetting.Builder().name("stop-on-lags").description("Pause on server lagging. (Time since last tick)").defaultValue(0.8).min(0.1).max(5.0).sliderMin(0.1).sliderMax(5.0).build());
        lllllllllllllllllllIIlIlIIIllIlI.Distance = lllllllllllllllllllIIlIlIIIllIll.add(new DoubleSetting.Builder().name("distance").description("Maximum distance.").defaultValue(6.6).build());
        lllllllllllllllllllIIlIlIIIllIlI.onlySelected = lllllllllllllllllllIIlIlIIIllIll.add(new BoolSetting.Builder().name("only-selected").description("Only mines your selected blocks.").defaultValue(false).build());
        lllllllllllllllllllIIlIlIIIllIlI.selectedBlocks = lllllllllllllllllllIIlIlIIIllIll.add(new BlockListSetting.Builder().name("selected-blocks").description("The certain type of blocks you want to mine.").defaultValue(new ArrayList<class_2248>(0)).build());
        lllllllllllllllllllIIlIlIIIllIlI.leftBreak = lllllllllllllllllllIIlIlIIIllIll.add(new IntSetting.Builder().name("left").defaultValue(1).min(0).max(6).sliderMin(0).sliderMax(6).build());
        lllllllllllllllllllIIlIlIIIllIlI.rightBreak = lllllllllllllllllllIIlIlIIIllIll.add(new IntSetting.Builder().name("right").defaultValue(1).min(0).max(6).sliderMin(0).sliderMax(6).build());
        lllllllllllllllllllIIlIlIIIllIlI.forwardBreak = lllllllllllllllllllIIlIlIIIllIll.add(new IntSetting.Builder().name("forward").defaultValue(1).min(0).max(6).sliderMin(0).sliderMax(6).build());
        lllllllllllllllllllIIlIlIIIllIlI.backwardBreak = lllllllllllllllllllIIlIlIIIllIll.add(new IntSetting.Builder().name("backward").defaultValue(1).min(0).max(6).sliderMin(0).sliderMax(6).build());
        lllllllllllllllllllIIlIlIIIllIlI.upBreak = lllllllllllllllllllIIlIlIIIllIll.add(new IntSetting.Builder().name("up").defaultValue(1).min(1).max(6).sliderMin(1).sliderMax(6).build());
        lllllllllllllllllllIIlIlIIIllIlI.downBreak = lllllllllllllllllllIIlIlIIIllIll.add(new IntSetting.Builder().name("down").defaultValue(0).min(0).max(7).sliderMin(0).sliderMax(7).build());
        lllllllllllllllllllIIlIlIIIllIlI.limit = 0;
    }

    @Override
    public void onActivate() {
        lllllllllllllllllllIIlIlIIllIIlI.limit = 0;
    }

    private void toggleitem() {
        NukerTwo lllllllllllllllllllIIlIlIIIlllll;
        if (lllllllllllllllllllIIlIlIIIlllll.replaceitems.get().booleanValue() && !lllllllllllllllllllIIlIlIIIlllll.itemsaver.get().booleanValue()) {
            lllllllllllllllllllIIlIlIIIlllll.replaceitems.set(false);
        }
    }
}

