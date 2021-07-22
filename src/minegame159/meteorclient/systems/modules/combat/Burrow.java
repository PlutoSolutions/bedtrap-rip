/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
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
import net.minecraft.class_1799;
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
    private final Setting<Boolean> instant;
    private final SettingGroup sgGeneral;
    private final Setting<Double> timer;
    private final Setting<Double> rubberbandHeight;
    private boolean shouldBurrow;
    private final Setting<Boolean> rotate;
    private final Setting<Block> block;
    private final Setting<Boolean> onlyInHole;
    private final Setting<Double> triggerHeight;
    private final class_2338.class_2339 blockPos;
    private final Setting<Boolean> automatic;
    private final Setting<Boolean> center;

    @EventHandler
    private void onKey(KeyEvent keyEvent) {
        if (this.instant.get().booleanValue() && !this.shouldBurrow) {
            if (keyEvent.action == KeyAction.Press && this.mc.field_1690.field_1903.method_1417(keyEvent.key, 0)) {
                this.shouldBurrow = true;
            }
            this.blockPos.method_10101((class_2382)this.mc.field_1724.method_24515());
        }
    }

    private static boolean lambda$getItem$1(class_1799 class_17992) {
        return true;
    }

    private FindItemResult getItem() {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$combat$Burrow$Block[this.block.get().ordinal()]) {
            case 1: {
                return InvUtils.findInHotbar(class_1802.field_8466);
            }
            case 2: {
                return InvUtils.findInHotbar(Burrow::lambda$getItem$0);
            }
            case 3: {
                return InvUtils.findInHotbar(Burrow::lambda$getItem$1);
            }
        }
        return InvUtils.findInHotbar(class_1802.field_8281, class_1802.field_22421);
    }

    public Burrow() {
        super(Categories.Combat, "Burrow", "Attempts to clip you into a block.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.block = this.sgGeneral.add(new EnumSetting.Builder().name("block-to-use").description("The block to use for Burrow.").defaultValue(Block.EChest).build());
        this.instant = this.sgGeneral.add(new BoolSetting.Builder().name("instant").description("Jumps with packets rather than vanilla jump.").defaultValue(true).build());
        this.automatic = this.sgGeneral.add(new BoolSetting.Builder().name("automatic").description("Automatically burrows on activate rather than waiting for jump.").defaultValue(true).build());
        this.triggerHeight = this.sgGeneral.add(new DoubleSetting.Builder().name("trigger-height").description("How high you have to jump before a rubberband is triggered.").defaultValue(1.12).min(0.01).sliderMax(1.4).build());
        this.rubberbandHeight = this.sgGeneral.add(new DoubleSetting.Builder().name("rubberband-height").description("How far to attempt to cause rubberband.").defaultValue(12.0).sliderMin(-30.0).sliderMax(30.0).build());
        this.timer = this.sgGeneral.add(new DoubleSetting.Builder().name("timer").description("Timer override.").defaultValue(1.0).min(0.01).sliderMax(10.0).build());
        this.onlyInHole = this.sgGeneral.add(new BoolSetting.Builder().name("only-in-holes").description("Stops you from burrowing when not in a hole.").defaultValue(false).build());
        this.center = this.sgGeneral.add(new BoolSetting.Builder().name("center").description("Centers you to the middle of the block before burrowing.").defaultValue(true).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Faces the block you place server-side.").defaultValue(true).build());
        this.blockPos = new class_2338.class_2339();
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (!this.instant.get().booleanValue()) {
            boolean bl = this.shouldBurrow = this.mc.field_1724.method_23318() > (double)this.blockPos.method_10264() + this.triggerHeight.get();
        }
        if (!this.shouldBurrow && this.instant.get().booleanValue()) {
            this.blockPos.method_10101((class_2382)this.mc.field_1724.method_24515());
        }
        if (this.shouldBurrow) {
            if (!this.mc.field_1724.method_24828()) {
                this.toggle();
                return;
            }
            if (this.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(this.mc.field_1724.method_24515()), Rotations.getPitch(this.mc.field_1724.method_24515()), 50, this::burrow);
            } else {
                this.burrow();
            }
            this.toggle();
        }
    }

    private static boolean lambda$getItem$0(class_1799 class_17992) {
        return class_2248.method_9503((class_1792)class_17992.method_7909()) instanceof class_2199;
    }

    private void burrow() {
        if (this.center.get().booleanValue()) {
            PlayerUtils.centerPlayer();
        }
        if (this.instant.get().booleanValue()) {
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(this.mc.field_1724.method_23317(), this.mc.field_1724.method_23318() + 0.4, this.mc.field_1724.method_23321(), true));
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(this.mc.field_1724.method_23317(), this.mc.field_1724.method_23318() + 0.75, this.mc.field_1724.method_23321(), true));
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(this.mc.field_1724.method_23317(), this.mc.field_1724.method_23318() + 1.01, this.mc.field_1724.method_23321(), true));
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(this.mc.field_1724.method_23317(), this.mc.field_1724.method_23318() + 1.15, this.mc.field_1724.method_23321(), true));
        }
        int n = this.mc.field_1724.field_7514.field_7545;
        InvUtils.swap(this.getItem().getSlot());
        this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5808, new class_3965(Utils.vec3d((class_2338)this.blockPos), class_2350.field_11036, (class_2338)this.blockPos, false));
        this.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
        InvUtils.swap(n);
        if (this.instant.get().booleanValue()) {
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(this.mc.field_1724.method_23317(), this.mc.field_1724.method_23318() + this.rubberbandHeight.get(), this.mc.field_1724.method_23321(), false));
        } else {
            this.mc.field_1724.method_18799(this.mc.field_1724.method_18798().method_1031(0.0, this.rubberbandHeight.get().doubleValue(), 0.0));
        }
    }

    @Override
    public void onActivate() {
        if (!this.mc.field_1687.method_8320(this.mc.field_1724.method_24515()).method_26207().method_15800()) {
            this.error("Already burrowed, disabling.", new Object[0]);
            this.toggle();
            return;
        }
        if (!PlayerUtils.isInHole(false) && this.onlyInHole.get().booleanValue()) {
            this.error("Not in a hole, disabling.", new Object[0]);
            this.toggle();
            return;
        }
        if (!this.checkHead()) {
            this.error("Not enough headroom to burrow, disabling.", new Object[0]);
            this.toggle();
            return;
        }
        FindItemResult findItemResult = this.getItem();
        if (!findItemResult.isHotbar() && !findItemResult.isOffhand()) {
            this.error("No burrow block found, disabling.", new Object[0]);
            this.toggle();
            return;
        }
        this.blockPos.method_10101((class_2382)this.mc.field_1724.method_24515());
        Modules.get().get(Timer.class).setOverride(this.timer.get());
        this.shouldBurrow = false;
        if (this.automatic.get().booleanValue()) {
            if (this.instant.get().booleanValue()) {
                this.shouldBurrow = true;
            } else {
                this.mc.field_1724.method_6043();
            }
        } else {
            this.info("Waiting for manual jump.", new Object[0]);
        }
    }

    private boolean checkHead() {
        class_2680 class_26802 = this.mc.field_1687.method_8320((class_2338)this.blockPos.method_10102(this.mc.field_1724.method_23317() + 0.3, this.mc.field_1724.method_23318() + 2.3, this.mc.field_1724.method_23321() + 0.3));
        class_2680 class_26803 = this.mc.field_1687.method_8320((class_2338)this.blockPos.method_10102(this.mc.field_1724.method_23317() + 0.3, this.mc.field_1724.method_23318() + 2.3, this.mc.field_1724.method_23321() - 0.3));
        class_2680 class_26804 = this.mc.field_1687.method_8320((class_2338)this.blockPos.method_10102(this.mc.field_1724.method_23317() - 0.3, this.mc.field_1724.method_23318() + 2.3, this.mc.field_1724.method_23321() - 0.3));
        class_2680 class_26805 = this.mc.field_1687.method_8320((class_2338)this.blockPos.method_10102(this.mc.field_1724.method_23317() - 0.3, this.mc.field_1724.method_23318() + 2.3, this.mc.field_1724.method_23321() + 0.3));
        boolean bl = class_26802.method_26207().method_15800();
        boolean bl2 = class_26803.method_26207().method_15800();
        boolean bl3 = class_26804.method_26207().method_15800();
        boolean bl4 = class_26805.method_26207().method_15800();
        return bl & bl2 & bl3 & bl4;
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

    }
}

