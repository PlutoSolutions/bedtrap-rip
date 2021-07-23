/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import java.math.BigDecimal;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.bedtrap.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2828;
import net.minecraft.class_3726;

public class SurroundTwo
extends Module {
    private final Setting<Boolean> disableOnJump;
    boolean tocenter;
    static final boolean $assertionsDisabled = !SurroundTwo.class.desiredAssertionStatus();
    private final Setting<Boolean> onlyWhenSneaking;
    private int prevSlot;
    int tickskip;
    private final Setting<Boolean> upDown;
    private final Setting<Boolean> wideDown;
    private boolean return_;
    private final Setting<Boolean> turnOff;
    private final class_2338.class_2339 blockPos;
    private final Setting<Boolean> onlyOnGround;
    private final Setting<Boolean> doubleHeight;
    private final Setting<Boolean> rotate;
    private final Setting<Boolean> helpUP;
    private final Setting<Integer> cooldown;
    private final SettingGroup sgGeneral;
    private final Setting<ecenter> center;

    @EventHandler
    private void onTick(TickEvent.Post post) {
        boolean bl;
        boolean bl2;
        boolean bl3;
        boolean bl4;
        boolean bl5;
        if (this.center.get() == ecenter.legit) {
            double d = 0.0;
            double d2 = 0.0;
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            class_243 class_2432 = this.mc.field_1724.method_19538();
            if (class_2432.field_1352 > 0.0 && this.gp(class_2432.field_1352) < 3L) {
                d = 0.185;
            }
            if (class_2432.field_1352 > 0.0 && this.gp(class_2432.field_1352) > 6L) {
                d = -0.185;
            }
            if (class_2432.field_1352 < 0.0 && this.gp(class_2432.field_1352) < 3L) {
                d = -0.185;
            }
            if (class_2432.field_1352 < 0.0 && this.gp(class_2432.field_1352) > 6L) {
                d = 0.185;
            }
            if (class_2432.field_1350 > 0.0 && this.gp(class_2432.field_1350) < 3L) {
                d2 = 0.185;
            }
            if (class_2432.field_1350 > 0.0 && this.gp(class_2432.field_1350) > 6L) {
                d2 = -0.185;
            }
            if (class_2432.field_1350 < 0.0 && this.gp(class_2432.field_1350) < 3L) {
                d2 = -0.185;
            }
            if (class_2432.field_1350 < 0.0 && this.gp(class_2432.field_1350) > 6L) {
                d2 = 0.185;
            }
            if (d != 0.0 || d2 != 0.0) {
                double d3 = this.mc.field_1724.method_23317() + d;
                double d4 = this.mc.field_1724.method_23321() + d2;
                this.mc.field_1724.method_5814(d3, this.mc.field_1724.method_23318(), d4);
                this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(this.mc.field_1724.method_23317(), this.mc.field_1724.method_23318(), this.mc.field_1724.method_23321(), this.mc.field_1724.method_24828()));
                return;
            }
        }
        if (this.tickskip != 0) {
            --this.tickskip;
            return;
        }
        this.tickskip = this.cooldown.get();
        if (this.disableOnJump.get().booleanValue() && this.mc.field_1690.field_1903.method_1434()) {
            this.toggle();
            return;
        }
        if (this.onlyOnGround.get().booleanValue()) {
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            if (!this.mc.field_1724.method_24828()) {
                return;
            }
        }
        if (this.onlyWhenSneaking.get().booleanValue() && !this.mc.field_1690.field_1832.method_1434()) {
            return;
        }
        this.return_ = false;
        boolean bl6 = this.place(0, -1, 0);
        if (this.return_) {
            return;
        }
        boolean bl7 = this.place(1, 0, 0);
        if (this.return_) {
            return;
        }
        boolean bl8 = this.place(-1, 0, 0);
        if (this.return_) {
            return;
        }
        boolean bl9 = this.place(0, 0, 1);
        if (this.return_) {
            return;
        }
        boolean bl10 = this.place(0, 0, -1);
        if (this.return_) {
            return;
        }
        boolean bl11 = this.place(1, -1, 0);
        if (this.return_) {
            return;
        }
        boolean bl12 = this.place(-1, -1, 0);
        if (this.return_) {
            return;
        }
        boolean bl13 = this.place(0, -1, 1);
        if (this.return_) {
            return;
        }
        boolean bl14 = this.place(0, -1, -1);
        if (this.return_) {
            return;
        }
        boolean bl15 = false;
        if (this.wideDown.get().booleanValue()) {
            bl5 = this.place(1, 0, 1);
            if (this.return_) {
                return;
            }
            bl4 = this.place(-1, 0, -1);
            if (this.return_) {
                return;
            }
            bl3 = this.place(-1, 0, 1);
            if (this.return_) {
                return;
            }
            bl2 = this.place(1, 0, -1);
            if (this.return_) {
                return;
            }
            bl = this.place(2, 0, 0);
            if (this.return_) {
                return;
            }
            boolean bl16 = this.place(-2, 0, 0);
            if (this.return_) {
                return;
            }
            boolean bl17 = this.place(0, 0, 2);
            if (this.return_) {
                return;
            }
            boolean bl18 = this.place(0, 0, -2);
            if (this.return_) {
                return;
            }
            if (bl5 && bl4 && bl3 && bl2 && bl && bl16 && bl17 && bl18) {
                bl15 = true;
            }
        }
        bl5 = false;
        if (this.doubleHeight.get().booleanValue()) {
            bl4 = this.place(1, 1, 0);
            if (this.return_) {
                return;
            }
            bl3 = this.place(-1, 1, 0);
            if (this.return_) {
                return;
            }
            bl2 = this.place(0, 1, 1);
            if (this.return_) {
                return;
            }
            bl = this.place(0, 1, -1);
            if (this.return_) {
                return;
            }
            if (bl4 && bl3 && bl2 && bl) {
                bl5 = true;
            }
        }
        bl4 = false;
        if (this.helpUP.get().booleanValue() && bl5) {
            bl3 = this.place(1, 2, 0);
            if (this.return_) {
                return;
            }
            if (bl3) {
                bl4 = true;
            }
        }
        bl3 = false;
        if (this.upDown.get().booleanValue()) {
            bl2 = false;
            if (this.helpUP.get().booleanValue() && bl4 || !this.helpUP.get().booleanValue()) {
                bl2 = this.place(0, 2, 0);
            }
            if (this.return_) {
                return;
            }
            bl = this.place(0, -2, 0);
            if (this.return_) {
                return;
            }
            if (bl2 && bl) {
                bl3 = true;
            }
        }
        if (this.turnOff.get().booleanValue() && bl6 && bl7 && bl8 && bl9 && bl10 && bl11 && bl12 && bl13 && bl14 && (bl5 || !this.doubleHeight.get().booleanValue()) && (bl15 || !this.wideDown.get().booleanValue()) && (bl3 || !this.upDown.get().booleanValue()) && (bl4 || !this.helpUP.get().booleanValue())) {
            this.toggle();
        }
    }

    private long gp(double d) {
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        BigDecimal bigDecimal2 = bigDecimal.remainder(BigDecimal.ONE);
        return Byte.valueOf(String.valueOf(String.valueOf(bigDecimal2).replace("0.", "").replace("-", "").charAt(0))).byteValue();
    }

    private boolean findSlot() {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        this.prevSlot = this.mc.field_1724.field_7514.field_7545;
        for (int i = 0; i < 9; ++i) {
            class_1792 class_17922 = this.mc.field_1724.field_7514.method_5438(i).method_7909();
            if (!(class_17922 instanceof class_1747) || class_17922 != class_1802.field_8281) continue;
            this.mc.field_1724.field_7514.field_7545 = i;
            return true;
        }
        return false;
    }

    private boolean place(int n, int n2, int n3) {
        boolean bl;
        this.setBlockPos(n, n2, n3);
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        if (this.mc.field_1724.method_23318() + (double)n2 < 0.0 || this.mc.field_1724.method_23318() + (double)n2 > 254.0) {
            return true;
        }
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        if (!this.mc.field_1687.method_8628(class_2246.field_10540.method_9564(), (class_2338)this.blockPos, class_3726.method_16194())) {
            return true;
        }
        class_2680 class_26802 = this.mc.field_1687.method_8320((class_2338)this.blockPos);
        boolean bl2 = class_26802.method_26204() == class_2246.field_10540;
        boolean bl3 = bl = !class_26802.method_26207().method_15800();
        if (!bl && this.findSlot()) {
            boolean bl4;
            if (this.rotate.get().booleanValue()) {
                Rotations.rotate(this.mc.field_1724.field_6031, this.mc.field_1724.field_5965);
            }
            bl = PlayerUtils.placeBlock((class_2338)this.blockPos, class_1268.field_5808, true);
            this.resetSlot();
            boolean bl5 = bl4 = this.mc.field_1687.method_8320((class_2338)this.blockPos).method_26204() == class_2246.field_10540;
            if (!bl2 && bl4) {
                this.return_ = true;
            }
        }
        return bl;
    }

    private void resetSlot() {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        this.mc.field_1724.field_7514.field_7545 = this.prevSlot;
    }

    public SurroundTwo() {
        super(Categories.BedTrap, "surround+", "Surrounds you in blocks to prevent you from taking lots of damage.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.center = this.sgGeneral.add(new EnumSetting.Builder().name("center").description("Teleport to center block.").defaultValue(ecenter.legit).build());
        this.cooldown = this.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("Block per tick.").defaultValue(0).min(0).sliderMax(20).build());
        this.doubleHeight = this.sgGeneral.add(new BoolSetting.Builder().name("double-height").description("Places obsidian on top of the original surround blocks to prevent people from face-placing you.").defaultValue(false).build());
        this.wideDown = this.sgGeneral.add(new BoolSetting.Builder().name("wide-down").description("Big Down").defaultValue(false).build());
        this.upDown = this.sgGeneral.add(new BoolSetting.Builder().name("up-down").description("Up Down").defaultValue(false).build());
        this.helpUP = this.sgGeneral.add(new BoolSetting.Builder().name("help-up").description("Help head block place. Only working if Up Down is enabled").defaultValue(false).build());
        this.onlyOnGround = this.sgGeneral.add(new BoolSetting.Builder().name("only-on-ground").description("Works only when you standing on blocks.").defaultValue(true).build());
        this.onlyWhenSneaking = this.sgGeneral.add(new BoolSetting.Builder().name("only-when-sneaking").description("Places blocks only after sneaking.").defaultValue(false).build());
        this.turnOff = this.sgGeneral.add(new BoolSetting.Builder().name("turn-off").description("Toggles off when all blocks are placed.").defaultValue(false).build());
        this.disableOnJump = this.sgGeneral.add(new BoolSetting.Builder().name("disable-on-jump").description("Automatically disables when you jump.").defaultValue(true).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces towards the obsidian being placed.").defaultValue(true).build());
        this.blockPos = new class_2338.class_2339();
        this.tickskip = this.cooldown.get();
        this.tocenter = false;
    }

    @Override
    public void onActivate() {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$bedtrap$SurroundTwo$ecenter[this.center.get().ordinal()]) {
            case 1: {
                this.tocenter = true;
                break;
            }
            case 2: {
                double d = 0.0;
                double d2 = 0.0;
                if (!$assertionsDisabled && this.mc.field_1724 == null) {
                    throw new AssertionError();
                }
                class_243 class_2432 = this.mc.field_1724.method_19538();
                if (class_2432.field_1352 > 0.0 && this.gp(class_2432.field_1352) < 3L) {
                    d = 0.3;
                }
                if (class_2432.field_1352 > 0.0 && this.gp(class_2432.field_1352) > 6L) {
                    d = -0.3;
                }
                if (class_2432.field_1352 < 0.0 && this.gp(class_2432.field_1352) < 3L) {
                    d = -0.3;
                }
                if (class_2432.field_1352 < 0.0 && this.gp(class_2432.field_1352) > 6L) {
                    d = 0.3;
                }
                if (class_2432.field_1350 > 0.0 && this.gp(class_2432.field_1350) < 3L) {
                    d2 = 0.3;
                }
                if (class_2432.field_1350 > 0.0 && this.gp(class_2432.field_1350) > 6L) {
                    d2 = -0.3;
                }
                if (class_2432.field_1350 < 0.0 && this.gp(class_2432.field_1350) < 3L) {
                    d2 = -0.3;
                }
                if (class_2432.field_1350 < 0.0 && this.gp(class_2432.field_1350) > 6L) {
                    d2 = 0.3;
                }
                if (d == 0.0 && d2 == 0.0) break;
                double d3 = this.mc.field_1724.method_23317() + d;
                double d4 = this.mc.field_1724.method_23321() + d2;
                this.mc.field_1724.method_5814(d3, this.mc.field_1724.method_23318(), d4);
                this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(this.mc.field_1724.method_23317(), this.mc.field_1724.method_23318(), this.mc.field_1724.method_23321(), this.mc.field_1724.method_24828()));
                break;
            }
        }
    }

    private void setBlockPos(int n, int n2, int n3) {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        this.blockPos.method_10102(this.mc.field_1724.method_23317() + (double)n, this.mc.field_1724.method_23318() + (double)n2, this.mc.field_1724.method_23321() + (double)n3);
    }

    public static enum ecenter {
        fast,
        legit,
        disable;

    }
}

