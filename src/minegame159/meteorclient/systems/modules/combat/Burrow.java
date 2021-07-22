/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2199
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_2879
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.Timer;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2199;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2828;
import net.minecraft.class_2879;
import net.minecraft.class_3965;

public class Burrow
extends Module {
    private final /* synthetic */ Setting<Boolean> instant;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> timer;
    private final /* synthetic */ Setting<Double> rubberbandHeight;
    private /* synthetic */ boolean shouldBurrow;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Block> block;
    private final /* synthetic */ Setting<Boolean> onlyInHole;
    private final /* synthetic */ Setting<Double> triggerHeight;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ Setting<Boolean> automatic;
    private final /* synthetic */ Setting<Boolean> center;

    @EventHandler
    private void onKey(KeyEvent lIlIIlIlIlIIII) {
        Burrow lIlIIlIlIlIIll;
        if (lIlIIlIlIlIIll.instant.get().booleanValue() && !lIlIIlIlIlIIll.shouldBurrow) {
            if (lIlIIlIlIlIIII.action == KeyAction.Press && lIlIIlIlIlIIll.mc.field_1690.field_1903.method_1417(lIlIIlIlIlIIII.key, 0)) {
                lIlIIlIlIlIIll.shouldBurrow = true;
            }
            lIlIIlIlIlIIll.blockPos.method_10101((class_2382)lIlIIlIlIlIIll.mc.field_1724.method_24515());
        }
    }

    private FindItemResult getItem() {
        Burrow lIlIIlIlIlIlll;
        switch (lIlIIlIlIlIlll.block.get()) {
            case EChest: {
                return InvUtils.findInHotbar(class_1802.field_8466);
            }
            case Anvil: {
                return InvUtils.findInHotbar(lIlIIlIIllIIIl -> class_2248.method_9503((class_1792)lIlIIlIIllIIIl.method_7909()) instanceof class_2199);
            }
            case Held: {
                return InvUtils.findInHotbar(lIlIIlIIllIlII -> true);
            }
        }
        return InvUtils.findInHotbar(class_1802.field_8281, class_1802.field_22421);
    }

    public Burrow() {
        super(Categories.Combat, "Burrow", "Attempts to clip you into a block.");
        Burrow lIlIIlIllIIlII;
        lIlIIlIllIIlII.sgGeneral = lIlIIlIllIIlII.settings.getDefaultGroup();
        lIlIIlIllIIlII.block = lIlIIlIllIIlII.sgGeneral.add(new EnumSetting.Builder().name("block-to-use").description("The block to use for Burrow.").defaultValue(Block.EChest).build());
        lIlIIlIllIIlII.instant = lIlIIlIllIIlII.sgGeneral.add(new BoolSetting.Builder().name("instant").description("Jumps with packets rather than vanilla jump.").defaultValue(true).build());
        lIlIIlIllIIlII.automatic = lIlIIlIllIIlII.sgGeneral.add(new BoolSetting.Builder().name("automatic").description("Automatically burrows on activate rather than waiting for jump.").defaultValue(true).build());
        lIlIIlIllIIlII.triggerHeight = lIlIIlIllIIlII.sgGeneral.add(new DoubleSetting.Builder().name("trigger-height").description("How high you have to jump before a rubberband is triggered.").defaultValue(1.12).min(0.01).sliderMax(1.4).build());
        lIlIIlIllIIlII.rubberbandHeight = lIlIIlIllIIlII.sgGeneral.add(new DoubleSetting.Builder().name("rubberband-height").description("How far to attempt to cause rubberband.").defaultValue(12.0).sliderMin(-30.0).sliderMax(30.0).build());
        lIlIIlIllIIlII.timer = lIlIIlIllIIlII.sgGeneral.add(new DoubleSetting.Builder().name("timer").description("Timer override.").defaultValue(1.0).min(0.01).sliderMax(10.0).build());
        lIlIIlIllIIlII.onlyInHole = lIlIIlIllIIlII.sgGeneral.add(new BoolSetting.Builder().name("only-in-holes").description("Stops you from burrowing when not in a hole.").defaultValue(false).build());
        lIlIIlIllIIlII.center = lIlIIlIllIIlII.sgGeneral.add(new BoolSetting.Builder().name("center").description("Centers you to the middle of the block before burrowing.").defaultValue(true).build());
        lIlIIlIllIIlII.rotate = lIlIIlIllIIlII.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Faces the block you place server-side.").defaultValue(true).build());
        lIlIIlIllIIlII.blockPos = new class_2338.class_2339();
    }

    @EventHandler
    private void onTick(TickEvent.Pre lIlIIlIlIllIlI) {
        Burrow lIlIIlIlIllIIl;
        if (!lIlIIlIlIllIIl.instant.get().booleanValue()) {
            boolean bl = lIlIIlIlIllIIl.shouldBurrow = lIlIIlIlIllIIl.mc.field_1724.method_23318() > (double)lIlIIlIlIllIIl.blockPos.method_10264() + lIlIIlIlIllIIl.triggerHeight.get();
        }
        if (!lIlIIlIlIllIIl.shouldBurrow && lIlIIlIlIllIIl.instant.get().booleanValue()) {
            lIlIIlIlIllIIl.blockPos.method_10101((class_2382)lIlIIlIlIllIIl.mc.field_1724.method_24515());
        }
        if (lIlIIlIlIllIIl.shouldBurrow) {
            if (!lIlIIlIlIllIIl.mc.field_1724.method_24828()) {
                lIlIIlIlIllIIl.toggle();
                return;
            }
            if (lIlIIlIlIllIIl.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(lIlIIlIlIllIIl.mc.field_1724.method_24515()), Rotations.getPitch(lIlIIlIlIllIIl.mc.field_1724.method_24515()), 50, lIlIIlIlIllIIl::burrow);
            } else {
                lIlIIlIlIllIIl.burrow();
            }
            lIlIIlIlIllIIl.toggle();
        }
    }

    private void burrow() {
        Burrow lIlIIlIlIlllll;
        if (lIlIIlIlIlllll.center.get().booleanValue()) {
            PlayerUtils.centerPlayer();
        }
        if (lIlIIlIlIlllll.instant.get().booleanValue()) {
            lIlIIlIlIlllll.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(lIlIIlIlIlllll.mc.field_1724.method_23317(), lIlIIlIlIlllll.mc.field_1724.method_23318() + 0.4, lIlIIlIlIlllll.mc.field_1724.method_23321(), true));
            lIlIIlIlIlllll.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(lIlIIlIlIlllll.mc.field_1724.method_23317(), lIlIIlIlIlllll.mc.field_1724.method_23318() + 0.75, lIlIIlIlIlllll.mc.field_1724.method_23321(), true));
            lIlIIlIlIlllll.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(lIlIIlIlIlllll.mc.field_1724.method_23317(), lIlIIlIlIlllll.mc.field_1724.method_23318() + 1.01, lIlIIlIlIlllll.mc.field_1724.method_23321(), true));
            lIlIIlIlIlllll.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(lIlIIlIlIlllll.mc.field_1724.method_23317(), lIlIIlIlIlllll.mc.field_1724.method_23318() + 1.15, lIlIIlIlIlllll.mc.field_1724.method_23321(), true));
        }
        int lIlIIlIllIIIII = lIlIIlIlIlllll.mc.field_1724.field_7514.field_7545;
        InvUtils.swap(lIlIIlIlIlllll.getItem().getSlot());
        lIlIIlIlIlllll.mc.field_1761.method_2896(lIlIIlIlIlllll.mc.field_1724, lIlIIlIlIlllll.mc.field_1687, class_1268.field_5808, new class_3965(Utils.vec3d((class_2338)lIlIIlIlIlllll.blockPos), class_2350.field_11036, (class_2338)lIlIIlIlIlllll.blockPos, false));
        lIlIIlIlIlllll.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
        InvUtils.swap(lIlIIlIllIIIII);
        if (lIlIIlIlIlllll.instant.get().booleanValue()) {
            lIlIIlIlIlllll.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(lIlIIlIlIlllll.mc.field_1724.method_23317(), lIlIIlIlIlllll.mc.field_1724.method_23318() + lIlIIlIlIlllll.rubberbandHeight.get(), lIlIIlIlIlllll.mc.field_1724.method_23321(), false));
        } else {
            lIlIIlIlIlllll.mc.field_1724.method_18799(lIlIIlIlIlllll.mc.field_1724.method_18798().method_1031(0.0, lIlIIlIlIlllll.rubberbandHeight.get().doubleValue(), 0.0));
        }
    }

    @Override
    public void onActivate() {
        Burrow lIlIIlIllIlIII;
        if (!lIlIIlIllIlIII.mc.field_1687.method_8320(lIlIIlIllIlIII.mc.field_1724.method_24515()).method_26207().method_15800()) {
            lIlIIlIllIlIII.error("Already burrowed, disabling.", new Object[0]);
            lIlIIlIllIlIII.toggle();
            return;
        }
        if (!PlayerUtils.isInHole(false) && lIlIIlIllIlIII.onlyInHole.get().booleanValue()) {
            lIlIIlIllIlIII.error("Not in a hole, disabling.", new Object[0]);
            lIlIIlIllIlIII.toggle();
            return;
        }
        if (!lIlIIlIllIlIII.checkHead()) {
            lIlIIlIllIlIII.error("Not enough headroom to burrow, disabling.", new Object[0]);
            lIlIIlIllIlIII.toggle();
            return;
        }
        FindItemResult lIlIIlIllIlIIl = lIlIIlIllIlIII.getItem();
        if (!lIlIIlIllIlIIl.isHotbar() && !lIlIIlIllIlIIl.isOffhand()) {
            lIlIIlIllIlIII.error("No burrow block found, disabling.", new Object[0]);
            lIlIIlIllIlIII.toggle();
            return;
        }
        lIlIIlIllIlIII.blockPos.method_10101((class_2382)lIlIIlIllIlIII.mc.field_1724.method_24515());
        Modules.get().get(Timer.class).setOverride(lIlIIlIllIlIII.timer.get());
        lIlIIlIllIlIII.shouldBurrow = false;
        if (lIlIIlIllIlIII.automatic.get().booleanValue()) {
            if (lIlIIlIllIlIII.instant.get().booleanValue()) {
                lIlIIlIllIlIII.shouldBurrow = true;
            } else {
                lIlIIlIllIlIII.mc.field_1724.method_6043();
            }
        } else {
            lIlIIlIllIlIII.info("Waiting for manual jump.", new Object[0]);
        }
    }

    private boolean checkHead() {
        Burrow lIlIIlIIllllIl;
        class_2680 lIlIIlIlIIIlIl = lIlIIlIIllllIl.mc.field_1687.method_8320((class_2338)lIlIIlIIllllIl.blockPos.method_10102(lIlIIlIIllllIl.mc.field_1724.method_23317() + 0.3, lIlIIlIIllllIl.mc.field_1724.method_23318() + 2.3, lIlIIlIIllllIl.mc.field_1724.method_23321() + 0.3));
        class_2680 lIlIIlIlIIIlII = lIlIIlIIllllIl.mc.field_1687.method_8320((class_2338)lIlIIlIIllllIl.blockPos.method_10102(lIlIIlIIllllIl.mc.field_1724.method_23317() + 0.3, lIlIIlIIllllIl.mc.field_1724.method_23318() + 2.3, lIlIIlIIllllIl.mc.field_1724.method_23321() - 0.3));
        class_2680 lIlIIlIlIIIIll = lIlIIlIIllllIl.mc.field_1687.method_8320((class_2338)lIlIIlIIllllIl.blockPos.method_10102(lIlIIlIIllllIl.mc.field_1724.method_23317() - 0.3, lIlIIlIIllllIl.mc.field_1724.method_23318() + 2.3, lIlIIlIIllllIl.mc.field_1724.method_23321() - 0.3));
        class_2680 lIlIIlIlIIIIlI = lIlIIlIIllllIl.mc.field_1687.method_8320((class_2338)lIlIIlIIllllIl.blockPos.method_10102(lIlIIlIIllllIl.mc.field_1724.method_23317() - 0.3, lIlIIlIIllllIl.mc.field_1724.method_23318() + 2.3, lIlIIlIIllllIl.mc.field_1724.method_23321() + 0.3));
        boolean lIlIIlIlIIIIIl = lIlIIlIlIIIlIl.method_26207().method_15800();
        boolean lIlIIlIlIIIIII = lIlIIlIlIIIlII.method_26207().method_15800();
        boolean lIlIIlIIllllll = lIlIIlIlIIIIll.method_26207().method_15800();
        boolean lIlIIlIIlllllI = lIlIIlIlIIIIlI.method_26207().method_15800();
        return lIlIIlIlIIIIIl & lIlIIlIlIIIIII & lIlIIlIIllllll & lIlIIlIIlllllI;
    }

    @Override
    public void onDeactivate() {
        Modules.get().get(Timer.class).setOverride(1.0);
    }

    public static enum Block {
        EChest,
        Obsidian,
        Anvil,
        Held;


        private Block() {
            Block lllllllllllllllllllIlIIIlIIlIIll;
        }
    }
}

