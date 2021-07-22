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
    /* synthetic */ int pause;
    private final /* synthetic */ Setting<Integer> delay;
    /* synthetic */ boolean firtDone;
    /* synthetic */ boolean isDone;
    /* synthetic */ class_2338 pos;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> range;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ class_2338.class_2339 blockPos;

    @EventHandler
    private void onTick(TickEvent.Pre lIlIIIlIIIlllll) {
        CrystalHead lIlIIIlIIlIIIII;
        if (lIlIIIlIIlIIIII.mc.field_1687 != null && lIlIIIlIIlIIIII.mc.field_1724 != null) {
            if (lIlIIIlIIlIIIII.pause > 0) {
                --lIlIIIlIIlIIIII.pause;
            } else {
                lIlIIIlIIlIIIII.pause = lIlIIIlIIlIIIII.delay.get();
                SortPriority lIlIIIlIIlIIIlI = SortPriority.LowestDistance;
                class_1657 lIlIIIlIIlIIIIl = TargetUtils.getPlayerTarget(7.0, lIlIIIlIIlIIIlI);
                if (lIlIIIlIIlIIIIl != null) {
                    class_2338 lIlIIIlIIlIIlII;
                    class_2338 lIlIIIlIIlIIIll = new class_2338(lIlIIIlIIlIIIIl.method_24515().method_10263(), lIlIIIlIIlIIIIl.method_24515().method_10264() + 2, lIlIIIlIIlIIIIl.method_24515().method_10260());
                    if ((double)lIlIIIlIIlIIIII.mc.field_1724.method_5739((class_1297)lIlIIIlIIlIIIIl) <= lIlIIIlIIlIIIII.range.get() && lIlIIIlIIlIIIII.mc.field_1687.method_8320(lIlIIIlIIlIIlII = new class_2338(lIlIIIlIIlIIIIl.method_24515().method_10263(), lIlIIIlIIlIIIIl.method_24515().method_10264() + 1, lIlIIIlIIlIIIIl.method_24515().method_10260())).method_26204() != class_2246.field_10540 && lIlIIIlIIlIIIII.mc.field_1687.method_8320(lIlIIIlIIlIIlII).method_26204() != class_2246.field_9987) {
                        class_2338 lIlIIIlIIlIIlIl = new class_2338(lIlIIIlIIlIIIIl.method_24515().method_10263(), lIlIIIlIIlIIIIl.method_24515().method_10264() + 3, lIlIIIlIIlIIIIl.method_24515().method_10260());
                        if (lIlIIIlIIlIIIII.mc.field_1687.method_8320(lIlIIIlIIlIIIll).method_26215() || lIlIIIlIIlIIIII.mc.field_1687.method_8320(lIlIIIlIIlIIIll).method_26204() == class_2246.field_10540) {
                            int lIlIIIlIIlIIllI = InvUtils.findItemInHotbar(class_1802.field_8403);
                            if (lIlIIIlIIlIIllI == -1) {
                                lIlIIIlIIlIIllI = InvUtils.findItemInHotbar(class_1802.field_22024);
                            }
                            if (lIlIIIlIIlIIllI == -1) {
                                lIlIIIlIIlIIllI = InvUtils.findItemInHotbar(class_1802.field_8377);
                            }
                            if (lIlIIIlIIlIIllI == -1) {
                                ChatUtils.info("Can't find pickaxe in hotbar!", new Object[0]);
                                lIlIIIlIIlIIIII.toggle();
                            } else {
                                if (lIlIIIlIIlIIIII.mc.field_1687.method_8320(lIlIIIlIIlIIIll).method_26204() != class_2246.field_10540) {
                                    lIlIIIlIIlIIIII.BlockPlace(lIlIIIlIIlIIIll, InvUtils.findItemInHotbar(class_1802.field_8281), lIlIIIlIIlIIIII.rotate.get());
                                }
                                if (!lIlIIIlIIlIIIII.equalsBlockPos(lIlIIIlIIlIIIII.pos, lIlIIIlIIlIIIll)) {
                                    lIlIIIlIIlIIIII.pos = lIlIIIlIIlIIIll;
                                    InvUtils.swap(lIlIIIlIIlIIllI);
                                    lIlIIIlIIlIIIII.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, lIlIIIlIIlIIIII.pos, class_2350.field_11036));
                                    lIlIIIlIIlIIIII.mc.field_1724.method_6104(class_1268.field_5810);
                                    lIlIIIlIIlIIIII.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, lIlIIIlIIlIIIII.pos, class_2350.field_11036));
                                    lIlIIIlIIlIIIII.isDone = false;
                                } else if (lIlIIIlIIlIIIII.isDone) {
                                    class_1511 lIlIIIlIIlIlIII = null;
                                    for (class_1297 lIlIIIlIIlIlIll : lIlIIIlIIlIIIII.mc.field_1687.method_18112()) {
                                        if (!(lIlIIIlIIlIlIll instanceof class_1511) || !lIlIIIlIIlIIIII.equalsBlockPos(lIlIIIlIIlIlIll.method_24515(), lIlIIIlIIlIIlIl)) continue;
                                        lIlIIIlIIlIlIII = (class_1511)lIlIIIlIIlIlIll;
                                        break;
                                    }
                                    if (lIlIIIlIIlIlIII != null) {
                                        if (lIlIIIlIIlIIIII.rotate.get().booleanValue()) {
                                            float[] lIlIIIlIIlIlIlI = PlayerUtils.calculateAngle(lIlIIIlIIlIlIII.method_19538());
                                            Rotations.rotate(lIlIIIlIIlIlIlI[0], lIlIIIlIIlIlIlI[1]);
                                        }
                                        int lIlIIIlIIlIlIIl = lIlIIIlIIlIIIII.mc.field_1724.field_7514.field_7545;
                                        InvUtils.swap(lIlIIIlIIlIIllI);
                                        lIlIIIlIIlIIIII.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, lIlIIIlIIlIIIII.pos, class_2350.field_11036));
                                        InvUtils.swap(lIlIIIlIIlIlIIl);
                                        lIlIIIlIIlIIIII.attackEntity(lIlIIIlIIlIlIII);
                                    } else {
                                        lIlIIIlIIlIIIII.placeCrystal(lIlIIIlIIlIIIIl, lIlIIIlIIlIIIll);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean placeCrystal(class_1657 lIlIIIIlllllIll, class_2338 lIlIIIIllllIllI) {
        CrystalHead lIlIIIIlllllIII;
        class_2338 lIlIIIIlllllIIl = new class_2338(lIlIIIIlllllIll.method_24515().method_10263(), lIlIIIIlllllIll.method_24515().method_10264() + 3, lIlIIIIlllllIll.method_24515().method_10260());
        if (!BlockUtils.canPlace(lIlIIIIlllllIIl, true)) {
            return false;
        }
        assert (lIlIIIIlllllIII.mc.field_1687 != null);
        if (!lIlIIIIlllllIII.mc.field_1687.method_8320(lIlIIIIlllllIIl).method_26215()) {
            return false;
        }
        int lIlIIIIllllllIl = InvUtils.findItemInHotbar(class_1802.field_8301);
        if (lIlIIIIllllllIl == -1) {
            ChatUtils.info("Can't find crystal in hotbar!", new Object[0]);
            lIlIIIIlllllIII.toggle();
            return false;
        }
        lIlIIIIlllllIII.interact(lIlIIIIllllIllI, lIlIIIIllllllIl, class_2350.field_11036);
        return true;
    }

    public boolean equalsBlockPos(class_2338 lIlIIIlIIlllIIl, class_2338 lIlIIIlIIlllIII) {
        if (lIlIIIlIIlllIIl == null || lIlIIIlIIlllIII == null) {
            return false;
        }
        if (lIlIIIlIIlllIIl.method_10263() != lIlIIIlIIlllIII.method_10263()) {
            return false;
        }
        if (lIlIIIlIIlllIIl.method_10264() != lIlIIIlIIlllIII.method_10264()) {
            return false;
        }
        return lIlIIIlIIlllIIl.method_10260() == lIlIIIlIIlllIII.method_10260();
    }

    public CrystalHead() {
        super(Categories.BedTrap, "crystal-head", "Traps people in an obsidian box to prevent them from moving.");
        CrystalHead lIlIIIlIlIIlIlI;
        lIlIIIlIlIIlIlI.sgGeneral = lIlIIIlIlIIlIlI.settings.getDefaultGroup();
        lIlIIIlIlIIlIlI.delay = lIlIIIlIlIIlIlI.sgGeneral.add(new IntSetting.Builder().name("delay").description("How many ticks between block placements.").defaultValue(4).sliderMin(0).sliderMax(20).build());
        lIlIIIlIlIIlIlI.range = lIlIIIlIlIIlIlI.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The radius players can be in to be targeted.").defaultValue(5.0).sliderMin(0.0).sliderMax(10.0).build());
        lIlIIIlIlIIlIlI.rotate = lIlIIIlIlIIlIlI.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Sends rotation packets to the server when placing.").defaultValue(false).build());
        lIlIIIlIlIIlIlI.blockPos = new class_2338.class_2339();
    }

    @EventHandler
    private void BlockUpdate(PacketEvent.Receive lIlIIIlIlIIIIIl) {
        CrystalHead lIlIIIlIIllllll;
        if (!(lIlIIIlIlIIIIIl.packet instanceof class_2626)) {
            return;
        }
        class_2626 lIlIIIlIlIIIIII = (class_2626)lIlIIIlIlIIIIIl.packet;
        if (lIlIIIlIIllllll.equalsBlockPos(lIlIIIlIlIIIIII.method_11309(), lIlIIIlIIllllll.pos) && lIlIIIlIlIIIIII.method_11308().method_26215()) {
            lIlIIIlIIllllll.isDone = true;
        }
    }

    public boolean BlockPlace(class_2338 lIlIIIlIIIIIlll, int lIlIIIlIIIIlIll, boolean lIlIIIlIIIIIlIl) {
        CrystalHead lIlIIIlIIIIlIII;
        if (lIlIIIlIIIIlIll == -1) {
            return false;
        }
        if (!BlockUtils.canPlace(lIlIIIlIIIIIlll, true)) {
            return false;
        }
        assert (lIlIIIlIIIIlIII.mc.field_1724 != null);
        int lIlIIIlIIIIlIIl = lIlIIIlIIIIlIII.mc.field_1724.field_7514.field_7545;
        InvUtils.swap(lIlIIIlIIIIlIll);
        if (lIlIIIlIIIIIlIl) {
            class_243 lIlIIIlIIIIlllI = new class_243(0.0, 0.0, 0.0);
            ((IVec3d)lIlIIIlIIIIlllI).set((double)lIlIIIlIIIIIlll.method_10263() + 0.5, (double)lIlIIIlIIIIIlll.method_10264() + 0.5, (double)lIlIIIlIIIIIlll.method_10260() + 0.5);
            Rotations.rotate(Rotations.getYaw(lIlIIIlIIIIlllI), Rotations.getPitch(lIlIIIlIIIIlllI));
        }
        assert (lIlIIIlIIIIlIII.mc.field_1761 != null);
        lIlIIIlIIIIlIII.mc.field_1761.method_2896(lIlIIIlIIIIlIII.mc.field_1724, lIlIIIlIIIIlIII.mc.field_1687, class_1268.field_5808, new class_3965(lIlIIIlIIIIlIII.mc.field_1724.method_19538(), class_2350.field_11036, lIlIIIlIIIIIlll, true));
        InvUtils.swap(lIlIIIlIIIIlIIl);
        return true;
    }

    @Override
    public void onActivate() {
        lIlIIIlIlIIIllI.pos = null;
        lIlIIIlIlIIIllI.isDone = false;
        lIlIIIlIlIIIllI.firtDone = false;
        lIlIIIlIlIIIllI.pause = 0;
    }

    public void attackEntity(class_1511 lIlIIIIlllIlllI) {
        CrystalHead lIlIIIIlllIllll;
        assert (lIlIIIIlllIllll.mc.field_1761 != null);
        lIlIIIIlllIllll.mc.field_1724.field_3944.method_2883((class_2596)new class_2824((class_1297)lIlIIIIlllIlllI, false));
    }

    public void interact(class_2338 lIlIIIIlllIIIlI, int lIlIIIIlllIIIIl, class_2350 lIlIIIIlllIIlIl) {
        CrystalHead lIlIIIIlllIIIll;
        assert (lIlIIIIlllIIIll.mc.field_1724 != null);
        int lIlIIIIlllIIlII = lIlIIIIlllIIIll.mc.field_1724.field_7514.field_7545;
        InvUtils.swap(lIlIIIIlllIIIIl);
        assert (lIlIIIIlllIIIll.mc.field_1761 != null);
        lIlIIIIlllIIIll.mc.field_1761.method_2896(lIlIIIIlllIIIll.mc.field_1724, lIlIIIIlllIIIll.mc.field_1687, class_1268.field_5808, new class_3965(lIlIIIIlllIIIll.mc.field_1724.method_19538(), lIlIIIIlllIIlIl, lIlIIIIlllIIIlI, true));
        InvUtils.swap(lIlIIIIlllIIlII);
    }
}

