/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_259
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.systems.modules.world;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_259;
import net.minecraft.class_2680;

public class Nuker
extends Module {
    private final /* synthetic */ Setting<Double> range;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> onlySelected;
    private /* synthetic */ boolean hasLastBlockPos;
    private final /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ int timer;
    private final /* synthetic */ class_2338.class_2339 lastBlockPos;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ Setting<Boolean> noParticles;
    private final /* synthetic */ Pool<class_2338.class_2339> blockPool;
    private final /* synthetic */ Setting<List<class_2248>> selectedBlocks;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<SortMode> sortMode;
    private final /* synthetic */ List<class_2338.class_2339> blocks;

    @Override
    public void onActivate() {
        lllllIIIlIllIll.timer = 0;
    }

    public boolean noParticles() {
        Nuker lllllIIIIIlIIll;
        return lllllIIIIIlIIll.isActive() && lllllIIIIIlIIll.noParticles.get() != false;
    }

    private void mine(class_2338 lllllIIIIIllIll) {
        Nuker lllllIIIIIllllI;
        if (lllllIIIIIllllI.mc.field_1761 != null && lllllIIIIIllllI.mc.field_1724 != null) {
            lllllIIIIIllllI.mc.field_1761.method_2902(lllllIIIIIllIll, class_2350.field_11036);
            lllllIIIIIllllI.mc.field_1724.method_6104(class_1268.field_5808);
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllIIIIllllIl) {
        Nuker lllllIIIIlllllI;
        if (lllllIIIIlllllI.hasLastBlockPos && lllllIIIIlllllI.mc.field_1687.method_8320((class_2338)lllllIIIIlllllI.lastBlockPos).method_26204() != class_2246.field_10124) {
            lllllIIIIlllllI.mc.field_1761.method_2902((class_2338)lllllIIIIlllllI.lastBlockPos, class_2350.field_11036);
            return;
        }
        lllllIIIIlllllI.hasLastBlockPos = false;
        if (lllllIIIIlllllI.timer < lllllIIIIlllllI.delay.get()) {
            ++lllllIIIIlllllI.timer;
            return;
        }
        lllllIIIIlllllI.timer = 0;
        double lllllIIIIllllII = lllllIIIIlllllI.mc.field_1724.method_23317() - 0.5;
        double lllllIIIIlllIll = lllllIIIIlllllI.mc.field_1724.method_23318();
        double lllllIIIIlllIlI = lllllIIIIlllllI.mc.field_1724.method_23321() - 0.5;
        int lllllIIIIlllIIl = (int)Math.floor(lllllIIIIllllII - lllllIIIIlllllI.range.get());
        int lllllIIIIlllIII = (int)Math.floor(lllllIIIIlllIll - lllllIIIIlllllI.range.get());
        int lllllIIIIllIlll = (int)Math.floor(lllllIIIIlllIlI - lllllIIIIlllllI.range.get());
        int lllllIIIIllIllI = (int)Math.floor(lllllIIIIllllII + lllllIIIIlllllI.range.get());
        int lllllIIIIllIlIl = (int)Math.floor(lllllIIIIlllIll + lllllIIIIlllllI.range.get());
        int lllllIIIIllIlII = (int)Math.floor(lllllIIIIlllIlI + lllllIIIIlllllI.range.get());
        double lllllIIIIllIIll = Math.pow(lllllIIIIlllllI.range.get(), 2.0);
        block2: for (int lllllIIIlIIIIIl = lllllIIIIlllIII; lllllIIIlIIIIIl <= lllllIIIIllIlIl; ++lllllIIIlIIIIIl) {
            boolean lllllIIIlIIIIlI = false;
            for (int lllllIIIlIIIIll = lllllIIIIlllIIl; lllllIIIlIIIIll <= lllllIIIIllIllI; ++lllllIIIlIIIIll) {
                for (int lllllIIIlIIIlII = lllllIIIIllIlll; lllllIIIlIIIlII <= lllllIIIIllIlII; ++lllllIIIlIIIlII) {
                    if (Utils.squaredDistance(lllllIIIIllllII, lllllIIIIlllIll, lllllIIIIlllIlI, lllllIIIlIIIIll, lllllIIIlIIIIIl, lllllIIIlIIIlII) > lllllIIIIllIIll) continue;
                    lllllIIIIlllllI.blockPos.method_10103(lllllIIIlIIIIll, lllllIIIlIIIIIl, lllllIIIlIIIlII);
                    class_2680 lllllIIIlIIIllI = lllllIIIIlllllI.mc.field_1687.method_8320((class_2338)lllllIIIIlllllI.blockPos);
                    if (lllllIIIlIIIllI.method_26218((class_1922)lllllIIIIlllllI.mc.field_1687, (class_2338)lllllIIIIlllllI.blockPos) == class_259.method_1073()) continue;
                    if (lllllIIIIlllllI.mode.get() == Mode.Flatten && (double)lllllIIIlIIIIIl < lllllIIIIlllllI.mc.field_1724.method_23318()) {
                        lllllIIIlIIIIlI = true;
                        break;
                    }
                    if (lllllIIIIlllllI.mode.get() == Mode.Smash && lllllIIIlIIIllI.method_26214((class_1922)lllllIIIIlllllI.mc.field_1687, (class_2338)lllllIIIIlllllI.blockPos) != 0.0f || lllllIIIIlllllI.onlySelected.get().booleanValue() && !lllllIIIIlllllI.selectedBlocks.get().contains((Object)lllllIIIlIIIllI.method_26204())) continue;
                    class_2338.class_2339 lllllIIIlIIIlIl = lllllIIIIlllllI.blockPool.get();
                    lllllIIIlIIIlIl.method_10103(lllllIIIlIIIIll, lllllIIIlIIIIIl, lllllIIIlIIIlII);
                    lllllIIIIlllllI.blocks.add(lllllIIIlIIIlIl);
                }
                if (lllllIIIlIIIIlI) continue block2;
            }
        }
        if (lllllIIIIlllllI.sortMode.get() != SortMode.None) {
            lllllIIIIlllllI.blocks.sort(Comparator.comparingDouble(llllIllllllllII -> {
                Nuker llllIlllllllIll;
                return Utils.squaredDistance(lllllIIIIllllII, lllllIIIIlllIll, lllllIIIIlllIlI, llllIllllllllII.method_10263(), llllIllllllllII.method_10264(), llllIllllllllII.method_10260()) * (double)(llllIlllllllIll.sortMode.get() == SortMode.Closest ? 1 : -1);
            }));
        }
        boolean lllllIIIIllIIlI = false;
        Iterator<class_2338.class_2339> iterator = lllllIIIIlllllI.blocks.iterator();
        if (iterator.hasNext()) {
            class_2338.class_2339 lllllIIIlIIIIII = iterator.next();
            if (!lllllIIIIlllllI.lastBlockPos.equals((Object)lllllIIIlIIIIII)) {
                try {
                    if (lllllIIIIlllllI.rotate.get().booleanValue()) {
                        Rotations.rotate(Rotations.getYaw((class_2338)lllllIIIlIIIIII), Rotations.getPitch((class_2338)lllllIIIlIIIIII), -50, () -> {
                            Nuker lllllIIIIIIlIIl;
                            lllllIIIIIIlIIl.cancelMine((class_2338)lllllIIIlIIIIII);
                        });
                    } else {
                        lllllIIIIlllllI.cancelMine((class_2338)lllllIIIlIIIIII);
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            lllllIIIIlllllI.lastBlockPos.method_10101((class_2382)lllllIIIlIIIIII);
            if (lllllIIIIlllllI.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw((class_2338)lllllIIIlIIIIII), Rotations.getPitch((class_2338)lllllIIIlIIIIII), -50, () -> {
                    Nuker lllllIIIIIIllIl;
                    lllllIIIIIIllIl.mine((class_2338)lllllIIIlIIIIII);
                });
            } else {
                lllllIIIIlllllI.mine((class_2338)lllllIIIlIIIIII);
            }
            lllllIIIIllIIlI = true;
            lllllIIIIlllllI.hasLastBlockPos = true;
        }
        if (!lllllIIIIllIIlI) {
            lllllIIIIlllllI.mc.field_1761.method_2925();
        }
        for (class_2338.class_2339 lllllIIIIllllll : lllllIIIIlllllI.blocks) {
            lllllIIIIlllllI.blockPool.free(lllllIIIIllllll);
        }
        lllllIIIIlllllI.blocks.clear();
    }

    @Override
    public void onDeactivate() {
        Nuker lllllIIIlIllIIl;
        lllllIIIlIllIIl.mc.field_1761.method_2925();
        lllllIIIlIllIIl.hasLastBlockPos = false;
    }

    private void cancelMine(class_2338 lllllIIIIIlIlIl) {
        Nuker lllllIIIIIlIllI;
        lllllIIIIIlIllI.mc.field_1761.method_2925();
        if (lllllIIIIIlIlIl != null) {
            lllllIIIIIlIllI.mc.field_1761.method_2910(lllllIIIIIlIlIl, class_2350.field_11036);
            lllllIIIIIlIllI.mc.field_1724.method_6104(class_1268.field_5808);
        }
    }

    public Nuker() {
        super(Categories.World, "nuker", "Breaks a large amount of specified blocks around you.");
        Nuker lllllIIIlIllllI;
        lllllIIIlIllllI.sgGeneral = lllllIIIlIllllI.settings.getDefaultGroup();
        lllllIIIlIllllI.mode = lllllIIIlIllllI.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The way the blocks are broken.").defaultValue(Mode.All).build());
        lllllIIIlIllllI.delay = lllllIIIlIllllI.sgGeneral.add(new IntSetting.Builder().name("delay").description("Delay between mining blocks in ticks.").defaultValue(0).min(0).build());
        lllllIIIlIllllI.selectedBlocks = lllllIIIlIllllI.sgGeneral.add(new BlockListSetting.Builder().name("selected-blocks").description("The certain type of blocks you want to mine.").defaultValue(new ArrayList<class_2248>(0)).build());
        lllllIIIlIllllI.onlySelected = lllllIIIlIllllI.sgGeneral.add(new BoolSetting.Builder().name("only-selected").description("Only mines your selected blocks.").defaultValue(false).build());
        lllllIIIlIllllI.range = lllllIIIlIllllI.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The break range.").defaultValue(5.0).min(0.0).build());
        lllllIIIlIllllI.sortMode = lllllIIIlIllllI.sgGeneral.add(new EnumSetting.Builder().name("sort-mode").description("The blocks you want to mine first.").defaultValue(SortMode.Closest).build());
        lllllIIIlIllllI.noParticles = lllllIIIlIllllI.sgGeneral.add(new BoolSetting.Builder().name("no-particles").description("Disables all block breaking particles.").defaultValue(false).build());
        lllllIIIlIllllI.rotate = lllllIIIlIllllI.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces the blocks being mined.").defaultValue(true).build());
        lllllIIIlIllllI.blockPool = new Pool<class_2338.class_2339>(class_2338.class_2339::new);
        lllllIIIlIllllI.blocks = new ArrayList<class_2338.class_2339>();
        lllllIIIlIllllI.blockPos = new class_2338.class_2339();
        lllllIIIlIllllI.lastBlockPos = new class_2338.class_2339();
    }

    public static enum SortMode {
        None,
        Closest,
        Furthest;


        private SortMode() {
            SortMode llllllllllllllllllIlIllIlllllIIl;
        }
    }

    public static enum Mode {
        All,
        Flatten,
        Smash;


        private Mode() {
            Mode llIIIIlIIllIllI;
        }
    }
}

