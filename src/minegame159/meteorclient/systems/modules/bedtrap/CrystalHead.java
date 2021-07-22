/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2626
 *  net.minecraft.class_2824
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.bedtrap.InvUtils;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.ChatUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2626;
import net.minecraft.class_2824;
import net.minecraft.class_2846;
import net.minecraft.class_3965;

public class CrystalHead
extends Module {
    int pause;
    private final Setting<Integer> delay;
    boolean firtDone;
    boolean isDone;
    static final boolean $assertionsDisabled = !CrystalHead.class.desiredAssertionStatus();
    class_2338 pos;
    private final SettingGroup sgGeneral;
    private final Setting<Double> range;
    private final Setting<Boolean> rotate;
    private final class_2338.class_2339 blockPos;

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.mc.field_1687 != null && this.mc.field_1724 != null) {
            if (this.pause > 0) {
                --this.pause;
            } else {
                this.pause = this.delay.get();
                SortPriority sortPriority = SortPriority.LowestDistance;
                class_1657 class_16572 = TargetUtils.getPlayerTarget(7.0, sortPriority);
                if (class_16572 != null) {
                    class_2338 class_23382;
                    class_2338 class_23383 = new class_2338(class_16572.method_24515().method_10263(), class_16572.method_24515().method_10264() + 2, class_16572.method_24515().method_10260());
                    if ((double)this.mc.field_1724.method_5739((class_1297)class_16572) <= this.range.get() && this.mc.field_1687.method_8320(class_23382 = new class_2338(class_16572.method_24515().method_10263(), class_16572.method_24515().method_10264() + 1, class_16572.method_24515().method_10260())).method_26204() != class_2246.field_10540 && this.mc.field_1687.method_8320(class_23382).method_26204() != class_2246.field_9987) {
                        class_2338 class_23384 = new class_2338(class_16572.method_24515().method_10263(), class_16572.method_24515().method_10264() + 3, class_16572.method_24515().method_10260());
                        if (this.mc.field_1687.method_8320(class_23383).method_26215() || this.mc.field_1687.method_8320(class_23383).method_26204() == class_2246.field_10540) {
                            int n = InvUtils.findItemInHotbar(class_1802.field_8403);
                            if (n == -1) {
                                n = InvUtils.findItemInHotbar(class_1802.field_22024);
                            }
                            if (n == -1) {
                                n = InvUtils.findItemInHotbar(class_1802.field_8377);
                            }
                            if (n == -1) {
                                ChatUtils.info("Can't find pickaxe in hotbar!", new Object[0]);
                                this.toggle();
                            } else {
                                if (this.mc.field_1687.method_8320(class_23383).method_26204() != class_2246.field_10540) {
                                    this.BlockPlace(class_23383, InvUtils.findItemInHotbar(class_1802.field_8281), this.rotate.get());
                                }
                                if (!this.equalsBlockPos(this.pos, class_23383)) {
                                    this.pos = class_23383;
                                    InvUtils.swap(n);
                                    this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, this.pos, class_2350.field_11036));
                                    this.mc.field_1724.method_6104(class_1268.field_5810);
                                    this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, this.pos, class_2350.field_11036));
                                    this.isDone = false;
                                } else if (this.isDone) {
                                    class_1511 class_15112 = null;
                                    for (Object object : this.mc.field_1687.method_18112()) {
                                        if (!(object instanceof class_1511) || !this.equalsBlockPos(object.method_24515(), class_23384)) continue;
                                        class_15112 = (class_1511)object;
                                        break;
                                    }
                                    if (class_15112 != null) {
                                        if (this.rotate.get().booleanValue()) {
                                            Object object;
                                            object = PlayerUtils.calculateAngle(class_15112.method_19538());
                                            Rotations.rotate((double)object[0], (double)object[1]);
                                        }
                                        int n2 = this.mc.field_1724.field_7514.field_7545;
                                        InvUtils.swap(n);
                                        this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, this.pos, class_2350.field_11036));
                                        InvUtils.swap(n2);
                                        this.attackEntity(class_15112);
                                    } else {
                                        this.placeCrystal(class_16572, class_23383);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean placeCrystal(class_1657 class_16572, class_2338 class_23382) {
        class_2338 class_23383 = new class_2338(class_16572.method_24515().method_10263(), class_16572.method_24515().method_10264() + 3, class_16572.method_24515().method_10260());
        if (!BlockUtils.canPlace(class_23383, true)) {
            return false;
        }
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        if (!this.mc.field_1687.method_8320(class_23383).method_26215()) {
            return false;
        }
        int n = InvUtils.findItemInHotbar(class_1802.field_8301);
        if (n == -1) {
            ChatUtils.info("Can't find crystal in hotbar!", new Object[0]);
            this.toggle();
            return false;
        }
        this.interact(class_23382, n, class_2350.field_11036);
        return true;
    }

    public boolean equalsBlockPos(class_2338 class_23382, class_2338 class_23383) {
        if (class_23382 == null || class_23383 == null) {
            return false;
        }
        if (class_23382.method_10263() != class_23383.method_10263()) {
            return false;
        }
        if (class_23382.method_10264() != class_23383.method_10264()) {
            return false;
        }
        return class_23382.method_10260() == class_23383.method_10260();
    }

    public CrystalHead() {
        super(Categories.BedTrap, "crystal-head", "Traps people in an obsidian box to prevent them from moving.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("delay").description("How many ticks between block placements.").defaultValue(4).sliderMin(0).sliderMax(20).build());
        this.range = this.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The radius players can be in to be targeted.").defaultValue(5.0).sliderMin(0.0).sliderMax(10.0).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Sends rotation packets to the server when placing.").defaultValue(false).build());
        this.blockPos = new class_2338.class_2339();
    }

    @EventHandler
    private void BlockUpdate(PacketEvent.Receive receive) {
        if (!(receive.packet instanceof class_2626)) {
            return;
        }
        class_2626 class_26262 = (class_2626)receive.packet;
        if (this.equalsBlockPos(class_26262.method_11309(), this.pos) && class_26262.method_11308().method_26215()) {
            this.isDone = true;
        }
    }

    public boolean BlockPlace(class_2338 class_23382, int n, boolean bl) {
        if (n == -1) {
            return false;
        }
        if (!BlockUtils.canPlace(class_23382, true)) {
            return false;
        }
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        int n2 = this.mc.field_1724.field_7514.field_7545;
        InvUtils.swap(n);
        if (bl) {
            class_243 class_2432 = new class_243(0.0, 0.0, 0.0);
            ((IVec3d)class_2432).set((double)class_23382.method_10263() + 0.5, (double)class_23382.method_10264() + 0.5, (double)class_23382.method_10260() + 0.5);
            Rotations.rotate(Rotations.getYaw(class_2432), Rotations.getPitch(class_2432));
        }
        if (!$assertionsDisabled && this.mc.field_1761 == null) {
            throw new AssertionError();
        }
        this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5808, new class_3965(this.mc.field_1724.method_19538(), class_2350.field_11036, class_23382, true));
        InvUtils.swap(n2);
        return true;
    }

    @Override
    public void onActivate() {
        this.pos = null;
        this.isDone = false;
        this.firtDone = false;
        this.pause = 0;
    }

    public void attackEntity(class_1511 class_15112) {
        if (!$assertionsDisabled && this.mc.field_1761 == null) {
            throw new AssertionError();
        }
        this.mc.field_1724.field_3944.method_2883((class_2596)new class_2824((class_1297)class_15112, false));
    }

    public void interact(class_2338 class_23382, int n, class_2350 class_23502) {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        int n2 = this.mc.field_1724.field_7514.field_7545;
        InvUtils.swap(n);
        if (!$assertionsDisabled && this.mc.field_1761 == null) {
            throw new AssertionError();
        }
        this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5808, new class_3965(this.mc.field_1724.method_19538(), class_23502, class_23382, true));
        InvUtils.swap(n2);
    }
}

