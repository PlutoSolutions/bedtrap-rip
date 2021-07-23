/*
 * Decompiled with CFR 0.151.
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
    private final Setting<Double> range;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> onlySelected;
    private boolean hasLastBlockPos;
    private final Setting<Integer> delay;
    private int timer;
    private final class_2338.class_2339 lastBlockPos;
    private final Setting<Boolean> rotate;
    private final class_2338.class_2339 blockPos;
    private final Setting<Boolean> noParticles;
    private final Pool<class_2338.class_2339> blockPool;
    private final Setting<List<class_2248>> selectedBlocks;
    private final Setting<Mode> mode;
    private final Setting<SortMode> sortMode;
    private final List<class_2338.class_2339> blocks;

    @Override
    public void onActivate() {
        this.timer = 0;
    }

    public boolean noParticles() {
        return this.isActive() && this.noParticles.get() != false;
    }

    private void lambda$onTick$1(class_2338.class_2339 class_23392) {
        this.cancelMine((class_2338)class_23392);
    }

    private void mine(class_2338 class_23382) {
        if (this.mc.field_1761 != null && this.mc.field_1724 != null) {
            this.mc.field_1761.method_2902(class_23382, class_2350.field_11036);
            this.mc.field_1724.method_6104(class_1268.field_5808);
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        int n;
        if (this.hasLastBlockPos && this.mc.field_1687.method_8320((class_2338)this.lastBlockPos).method_26204() != class_2246.field_10124) {
            this.mc.field_1761.method_2902((class_2338)this.lastBlockPos, class_2350.field_11036);
            return;
        }
        this.hasLastBlockPos = false;
        if (this.timer < this.delay.get()) {
            ++this.timer;
            return;
        }
        this.timer = 0;
        double d = this.mc.field_1724.method_23317() - 0.5;
        double d2 = this.mc.field_1724.method_23318();
        double d3 = this.mc.field_1724.method_23321() - 0.5;
        int n2 = (int)Math.floor(d - this.range.get());
        int n3 = (int)Math.floor(d2 - this.range.get());
        int n4 = (int)Math.floor(d3 - this.range.get());
        int n5 = (int)Math.floor(d + this.range.get());
        int n6 = (int)Math.floor(d2 + this.range.get());
        int n7 = (int)Math.floor(d3 + this.range.get());
        double d4 = Math.pow(this.range.get(), 2.0);
        block2: for (n = n3; n <= n6; ++n) {
            boolean bl = false;
            for (int i = n2; i <= n5; ++i) {
                for (int j = n4; j <= n7; ++j) {
                    if (Utils.squaredDistance(d, d2, d3, i, n, j) > d4) continue;
                    this.blockPos.method_10103(i, n, j);
                    class_2680 class_26802 = this.mc.field_1687.method_8320((class_2338)this.blockPos);
                    if (class_26802.method_26218((class_1922)this.mc.field_1687, (class_2338)this.blockPos) == class_259.method_1073()) continue;
                    if (this.mode.get() == Mode.Flatten && (double)n < this.mc.field_1724.method_23318()) {
                        bl = true;
                        break;
                    }
                    if (this.mode.get() == Mode.Smash && class_26802.method_26214((class_1922)this.mc.field_1687, (class_2338)this.blockPos) != 0.0f || this.onlySelected.get().booleanValue() && !this.selectedBlocks.get().contains(class_26802.method_26204())) continue;
                    class_2338.class_2339 class_23392 = this.blockPool.get();
                    class_23392.method_10103(i, n, j);
                    this.blocks.add(class_23392);
                    if (-1 != 4) continue;
                    return;
                }
                if (bl) continue block2;
            }
            if (null == null) continue;
            return;
        }
        if (this.sortMode.get() != SortMode.None) {
            this.blocks.sort(Comparator.comparingDouble(arg_0 -> this.lambda$onTick$0(d, d2, d3, arg_0)));
        }
        n = 0;
        Iterator<class_2338.class_2339> iterator = this.blocks.iterator();
        if (iterator.hasNext()) {
            class_2338.class_2339 class_23393 = iterator.next();
            if (!this.lastBlockPos.equals((Object)class_23393)) {
                try {
                    if (this.rotate.get().booleanValue()) {
                        Rotations.rotate(Rotations.getYaw((class_2338)class_23393), Rotations.getPitch((class_2338)class_23393), -50, () -> this.lambda$onTick$1(class_23393));
                    } else {
                        this.cancelMine((class_2338)class_23393);
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            this.lastBlockPos.method_10101((class_2382)class_23393);
            if (this.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw((class_2338)class_23393), Rotations.getPitch((class_2338)class_23393), -50, () -> this.lambda$onTick$2(class_23393));
            } else {
                this.mine((class_2338)class_23393);
            }
            n = 1;
            this.hasLastBlockPos = true;
        }
        if (n == 0) {
            this.mc.field_1761.method_2925();
        }
        for (class_2338.class_2339 class_23394 : this.blocks) {
            this.blockPool.free(class_23394);
        }
        this.blocks.clear();
    }

    @Override
    public void onDeactivate() {
        this.mc.field_1761.method_2925();
        this.hasLastBlockPos = false;
    }

    private double lambda$onTick$0(double d, double d2, double d3, class_2338.class_2339 class_23392) {
        return Utils.squaredDistance(d, d2, d3, class_23392.method_10263(), class_23392.method_10264(), class_23392.method_10260()) * (double)(this.sortMode.get() == SortMode.Closest ? 1 : -1);
    }

    private void cancelMine(class_2338 class_23382) {
        this.mc.field_1761.method_2925();
        if (class_23382 != null) {
            this.mc.field_1761.method_2910(class_23382, class_2350.field_11036);
            this.mc.field_1724.method_6104(class_1268.field_5808);
        }
    }

    public Nuker() {
        super(Categories.World, "nuker", "Breaks a large amount of specified blocks around you.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The way the blocks are broken.").defaultValue(Mode.All).build());
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("delay").description("Delay between mining blocks in ticks.").defaultValue(0).min(0).build());
        this.selectedBlocks = this.sgGeneral.add(new BlockListSetting.Builder().name("selected-blocks").description("The certain type of blocks you want to mine.").defaultValue(new ArrayList<class_2248>(0)).build());
        this.onlySelected = this.sgGeneral.add(new BoolSetting.Builder().name("only-selected").description("Only mines your selected blocks.").defaultValue(false).build());
        this.range = this.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The break range.").defaultValue(5.0).min(0.0).build());
        this.sortMode = this.sgGeneral.add(new EnumSetting.Builder().name("sort-mode").description("The blocks you want to mine first.").defaultValue(SortMode.Closest).build());
        this.noParticles = this.sgGeneral.add(new BoolSetting.Builder().name("no-particles").description("Disables all block breaking particles.").defaultValue(false).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces the blocks being mined.").defaultValue(true).build());
        this.blockPool = new Pool<class_2338.class_2339>(class_2338.class_2339::new);
        this.blocks = new ArrayList<class_2338.class_2339>();
        this.blockPos = new class_2338.class_2339();
        this.lastBlockPos = new class_2338.class_2339();
    }

    private void lambda$onTick$2(class_2338.class_2339 class_23392) {
        this.mine((class_2338)class_23392);
    }

    public static enum SortMode {
        None,
        Closest,
        Furthest;

    }

    public static enum Mode {
        All,
        Flatten,
        Smash;

    }
}

