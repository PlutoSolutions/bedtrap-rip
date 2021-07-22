/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_3726
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
    private final /* synthetic */ Setting<Boolean> disableOnJump;
    /* synthetic */ boolean tocenter;
    private final /* synthetic */ Setting<Boolean> onlyWhenSneaking;
    private /* synthetic */ int prevSlot;
    /* synthetic */ int tickskip;
    private final /* synthetic */ Setting<Boolean> upDown;
    private final /* synthetic */ Setting<Boolean> wideDown;
    private /* synthetic */ boolean return_;
    private final /* synthetic */ Setting<Boolean> turnOff;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ Setting<Boolean> onlyOnGround;
    private final /* synthetic */ Setting<Boolean> doubleHeight;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> helpUP;
    private final /* synthetic */ Setting<Integer> cooldown;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<ecenter> center;

    @EventHandler
    private void onTick(TickEvent.Post llIIlIllllIllII) {
        SurroundTwo llIIlIlllIllllI;
        if (llIIlIlllIllllI.center.get() == ecenter.legit) {
            double llIIlIlllllllll = 0.0;
            double llIIlIllllllllI = 0.0;
            assert (llIIlIlllIllllI.mc.field_1724 != null);
            class_243 llIIlIlllllllIl = llIIlIlllIllllI.mc.field_1724.method_19538();
            if (llIIlIlllllllIl.field_1352 > 0.0 && llIIlIlllIllllI.gp(llIIlIlllllllIl.field_1352) < 3L) {
                llIIlIlllllllll = 0.185;
            }
            if (llIIlIlllllllIl.field_1352 > 0.0 && llIIlIlllIllllI.gp(llIIlIlllllllIl.field_1352) > 6L) {
                llIIlIlllllllll = -0.185;
            }
            if (llIIlIlllllllIl.field_1352 < 0.0 && llIIlIlllIllllI.gp(llIIlIlllllllIl.field_1352) < 3L) {
                llIIlIlllllllll = -0.185;
            }
            if (llIIlIlllllllIl.field_1352 < 0.0 && llIIlIlllIllllI.gp(llIIlIlllllllIl.field_1352) > 6L) {
                llIIlIlllllllll = 0.185;
            }
            if (llIIlIlllllllIl.field_1350 > 0.0 && llIIlIlllIllllI.gp(llIIlIlllllllIl.field_1350) < 3L) {
                llIIlIllllllllI = 0.185;
            }
            if (llIIlIlllllllIl.field_1350 > 0.0 && llIIlIlllIllllI.gp(llIIlIlllllllIl.field_1350) > 6L) {
                llIIlIllllllllI = -0.185;
            }
            if (llIIlIlllllllIl.field_1350 < 0.0 && llIIlIlllIllllI.gp(llIIlIlllllllIl.field_1350) < 3L) {
                llIIlIllllllllI = -0.185;
            }
            if (llIIlIlllllllIl.field_1350 < 0.0 && llIIlIlllIllllI.gp(llIIlIlllllllIl.field_1350) > 6L) {
                llIIlIllllllllI = 0.185;
            }
            if (llIIlIlllllllll != 0.0 || llIIlIllllllllI != 0.0) {
                double llIIllIIIIIIIIl = llIIlIlllIllllI.mc.field_1724.method_23317() + llIIlIlllllllll;
                double llIIllIIIIIIIII = llIIlIlllIllllI.mc.field_1724.method_23321() + llIIlIllllllllI;
                llIIlIlllIllllI.mc.field_1724.method_5814(llIIllIIIIIIIIl, llIIlIlllIllllI.mc.field_1724.method_23318(), llIIllIIIIIIIII);
                llIIlIlllIllllI.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(llIIlIlllIllllI.mc.field_1724.method_23317(), llIIlIlllIllllI.mc.field_1724.method_23318(), llIIlIlllIllllI.mc.field_1724.method_23321(), llIIlIlllIllllI.mc.field_1724.method_24828()));
                return;
            }
        }
        if (llIIlIlllIllllI.tickskip != 0) {
            --llIIlIlllIllllI.tickskip;
            return;
        }
        llIIlIlllIllllI.tickskip = llIIlIlllIllllI.cooldown.get();
        if (llIIlIlllIllllI.disableOnJump.get().booleanValue() && llIIlIlllIllllI.mc.field_1690.field_1903.method_1434()) {
            llIIlIlllIllllI.toggle();
            return;
        }
        if (llIIlIlllIllllI.onlyOnGround.get().booleanValue()) {
            assert (llIIlIlllIllllI.mc.field_1724 != null);
            if (!llIIlIlllIllllI.mc.field_1724.method_24828()) {
                return;
            }
        }
        if (llIIlIlllIllllI.onlyWhenSneaking.get().booleanValue() && !llIIlIlllIllllI.mc.field_1690.field_1832.method_1434()) {
            return;
        }
        llIIlIlllIllllI.return_ = false;
        boolean llIIlIllllIlIll = llIIlIlllIllllI.place(0, -1, 0);
        if (llIIlIlllIllllI.return_) {
            return;
        }
        boolean llIIlIllllIlIlI = llIIlIlllIllllI.place(1, 0, 0);
        if (llIIlIlllIllllI.return_) {
            return;
        }
        boolean llIIlIllllIlIIl = llIIlIlllIllllI.place(-1, 0, 0);
        if (llIIlIlllIllllI.return_) {
            return;
        }
        boolean llIIlIllllIlIII = llIIlIlllIllllI.place(0, 0, 1);
        if (llIIlIlllIllllI.return_) {
            return;
        }
        boolean llIIlIllllIIlll = llIIlIlllIllllI.place(0, 0, -1);
        if (llIIlIlllIllllI.return_) {
            return;
        }
        boolean llIIlIllllIIllI = llIIlIlllIllllI.place(1, -1, 0);
        if (llIIlIlllIllllI.return_) {
            return;
        }
        boolean llIIlIllllIIlIl = llIIlIlllIllllI.place(-1, -1, 0);
        if (llIIlIlllIllllI.return_) {
            return;
        }
        boolean llIIlIllllIIlII = llIIlIlllIllllI.place(0, -1, 1);
        if (llIIlIlllIllllI.return_) {
            return;
        }
        boolean llIIlIllllIIIll = llIIlIlllIllllI.place(0, -1, -1);
        if (llIIlIlllIllllI.return_) {
            return;
        }
        boolean llIIlIllllIIIlI = false;
        if (llIIlIlllIllllI.wideDown.get().booleanValue()) {
            boolean llIIlIlllllllII = llIIlIlllIllllI.place(1, 0, 1);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIllllllIll = llIIlIlllIllllI.place(-1, 0, -1);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIllllllIlI = llIIlIlllIllllI.place(-1, 0, 1);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIllllllIIl = llIIlIlllIllllI.place(1, 0, -1);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIllllllIII = llIIlIlllIllllI.place(2, 0, 0);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIlllllIlll = llIIlIlllIllllI.place(-2, 0, 0);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIlllllIllI = llIIlIlllIllllI.place(0, 0, 2);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIlllllIlIl = llIIlIlllIllllI.place(0, 0, -2);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            if (llIIlIlllllllII && llIIlIllllllIll && llIIlIllllllIlI && llIIlIllllllIIl && llIIlIllllllIII && llIIlIlllllIlll && llIIlIlllllIllI && llIIlIlllllIlIl) {
                llIIlIllllIIIlI = true;
            }
        }
        boolean llIIlIllllIIIIl = false;
        if (llIIlIlllIllllI.doubleHeight.get().booleanValue()) {
            boolean llIIlIlllllIlII = llIIlIlllIllllI.place(1, 1, 0);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIlllllIIll = llIIlIlllIllllI.place(-1, 1, 0);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIlllllIIlI = llIIlIlllIllllI.place(0, 1, 1);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIlllllIIIl = llIIlIlllIllllI.place(0, 1, -1);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            if (llIIlIlllllIlII && llIIlIlllllIIll && llIIlIlllllIIlI && llIIlIlllllIIIl) {
                llIIlIllllIIIIl = true;
            }
        }
        boolean llIIlIllllIIIII = false;
        if (llIIlIlllIllllI.helpUP.get().booleanValue() && llIIlIllllIIIIl) {
            boolean llIIlIlllllIIII = llIIlIlllIllllI.place(1, 2, 0);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            if (llIIlIlllllIIII) {
                llIIlIllllIIIII = true;
            }
        }
        boolean llIIlIlllIlllll = false;
        if (llIIlIlllIllllI.upDown.get().booleanValue()) {
            boolean llIIlIllllIllll = false;
            if (llIIlIlllIllllI.helpUP.get().booleanValue() && llIIlIllllIIIII || !llIIlIlllIllllI.helpUP.get().booleanValue()) {
                llIIlIllllIllll = llIIlIlllIllllI.place(0, 2, 0);
            }
            if (llIIlIlllIllllI.return_) {
                return;
            }
            boolean llIIlIllllIlllI = llIIlIlllIllllI.place(0, -2, 0);
            if (llIIlIlllIllllI.return_) {
                return;
            }
            if (llIIlIllllIllll && llIIlIllllIlllI) {
                llIIlIlllIlllll = true;
            }
        }
        if (llIIlIlllIllllI.turnOff.get().booleanValue() && llIIlIllllIlIll && llIIlIllllIlIlI && llIIlIllllIlIIl && llIIlIllllIlIII && llIIlIllllIIlll && llIIlIllllIIllI && llIIlIllllIIlIl && llIIlIllllIIlII && llIIlIllllIIIll && (llIIlIllllIIIIl || !llIIlIlllIllllI.doubleHeight.get().booleanValue()) && (llIIlIllllIIIlI || !llIIlIlllIllllI.wideDown.get().booleanValue()) && (llIIlIlllIlllll || !llIIlIlllIllllI.upDown.get().booleanValue()) && (llIIlIllllIIIII || !llIIlIlllIllllI.helpUP.get().booleanValue())) {
            llIIlIlllIllllI.toggle();
        }
    }

    private long gp(double llIIllIIIIllIlI) {
        BigDecimal llIIllIIIIllIIl = BigDecimal.valueOf(llIIllIIIIllIlI);
        BigDecimal llIIllIIIIllIII = llIIllIIIIllIIl.remainder(BigDecimal.ONE);
        return Byte.valueOf(String.valueOf(String.valueOf(llIIllIIIIllIII).replace("0.", "").replace("-", "").charAt(0))).byteValue();
    }

    private boolean findSlot() {
        SurroundTwo llIIlIllIIlllll;
        assert (llIIlIllIIlllll.mc.field_1724 != null);
        llIIlIllIIlllll.prevSlot = llIIlIllIIlllll.mc.field_1724.field_7514.field_7545;
        for (int llIIlIllIlIIIlI = 0; llIIlIllIlIIIlI < 9; ++llIIlIllIlIIIlI) {
            class_1792 llIIlIllIlIIIll = llIIlIllIIlllll.mc.field_1724.field_7514.method_5438(llIIlIllIlIIIlI).method_7909();
            if (!(llIIlIllIlIIIll instanceof class_1747) || llIIlIllIlIIIll != class_1802.field_8281) continue;
            llIIlIllIIlllll.mc.field_1724.field_7514.field_7545 = llIIlIllIlIIIlI;
            return true;
        }
        return false;
    }

    private boolean place(int llIIlIlllIIIIIl, int llIIlIllIlllIIl, int llIIlIllIllllll) {
        boolean llIIlIllIllllII;
        SurroundTwo llIIlIlllIIIIlI;
        llIIlIlllIIIIlI.setBlockPos(llIIlIlllIIIIIl, llIIlIllIlllIIl, llIIlIllIllllll);
        assert (llIIlIlllIIIIlI.mc.field_1724 != null);
        if (llIIlIlllIIIIlI.mc.field_1724.method_23318() + (double)llIIlIllIlllIIl < 0.0 || llIIlIlllIIIIlI.mc.field_1724.method_23318() + (double)llIIlIllIlllIIl > 254.0) {
            return true;
        }
        assert (llIIlIlllIIIIlI.mc.field_1687 != null);
        if (!llIIlIlllIIIIlI.mc.field_1687.method_8628(class_2246.field_10540.method_9564(), (class_2338)llIIlIlllIIIIlI.blockPos, class_3726.method_16194())) {
            return true;
        }
        class_2680 llIIlIllIlllllI = llIIlIlllIIIIlI.mc.field_1687.method_8320((class_2338)llIIlIlllIIIIlI.blockPos);
        boolean llIIlIllIllllIl = llIIlIllIlllllI.method_26204() == class_2246.field_10540;
        boolean bl = llIIlIllIllllII = !llIIlIllIlllllI.method_26207().method_15800();
        if (!llIIlIllIllllII && llIIlIlllIIIIlI.findSlot()) {
            boolean llIIlIlllIIIIll;
            if (llIIlIlllIIIIlI.rotate.get().booleanValue()) {
                Rotations.rotate(llIIlIlllIIIIlI.mc.field_1724.field_6031, llIIlIlllIIIIlI.mc.field_1724.field_5965);
            }
            llIIlIllIllllII = PlayerUtils.placeBlock((class_2338)llIIlIlllIIIIlI.blockPos, class_1268.field_5808, true);
            llIIlIlllIIIIlI.resetSlot();
            boolean bl2 = llIIlIlllIIIIll = llIIlIlllIIIIlI.mc.field_1687.method_8320((class_2338)llIIlIlllIIIIlI.blockPos).method_26204() == class_2246.field_10540;
            if (!llIIlIllIllllIl && llIIlIlllIIIIll) {
                llIIlIlllIIIIlI.return_ = true;
            }
        }
        return llIIlIllIllllII;
    }

    private void resetSlot() {
        SurroundTwo llIIlIllIIlIIll;
        assert (llIIlIllIIlIIll.mc.field_1724 != null);
        llIIlIllIIlIIll.mc.field_1724.field_7514.field_7545 = llIIlIllIIlIIll.prevSlot;
    }

    public SurroundTwo() {
        super(Categories.BedTrap, "surround+", "Surrounds you in blocks to prevent you from taking lots of damage.");
        SurroundTwo llIIllIIIllIIlI;
        llIIllIIIllIIlI.sgGeneral = llIIllIIIllIIlI.settings.getDefaultGroup();
        llIIllIIIllIIlI.center = llIIllIIIllIIlI.sgGeneral.add(new EnumSetting.Builder().name("center").description("Teleport to center block.").defaultValue(ecenter.legit).build());
        llIIllIIIllIIlI.cooldown = llIIllIIIllIIlI.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("Block per tick.").defaultValue(0).min(0).sliderMax(20).build());
        llIIllIIIllIIlI.doubleHeight = llIIllIIIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("double-height").description("Places obsidian on top of the original surround blocks to prevent people from face-placing you.").defaultValue(false).build());
        llIIllIIIllIIlI.wideDown = llIIllIIIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("wide-down").description("Big Down").defaultValue(false).build());
        llIIllIIIllIIlI.upDown = llIIllIIIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("up-down").description("Up Down").defaultValue(false).build());
        llIIllIIIllIIlI.helpUP = llIIllIIIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("help-up").description("Help head block place. Only working if Up Down is enabled").defaultValue(false).build());
        llIIllIIIllIIlI.onlyOnGround = llIIllIIIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("only-on-ground").description("Works only when you standing on blocks.").defaultValue(true).build());
        llIIllIIIllIIlI.onlyWhenSneaking = llIIllIIIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("only-when-sneaking").description("Places blocks only after sneaking.").defaultValue(false).build());
        llIIllIIIllIIlI.turnOff = llIIllIIIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("turn-off").description("Toggles off when all blocks are placed.").defaultValue(false).build());
        llIIllIIIllIIlI.disableOnJump = llIIllIIIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("disable-on-jump").description("Automatically disables when you jump.").defaultValue(true).build());
        llIIllIIIllIIlI.rotate = llIIllIIIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces towards the obsidian being placed.").defaultValue(true).build());
        llIIllIIIllIIlI.blockPos = new class_2338.class_2339();
        llIIllIIIllIIlI.tickskip = llIIllIIIllIIlI.cooldown.get();
        llIIllIIIllIIlI.tocenter = false;
    }

    @Override
    public void onActivate() {
        SurroundTwo llIIllIIIlIIlIl;
        switch (llIIllIIIlIIlIl.center.get()) {
            case legit: {
                llIIllIIIlIIlIl.tocenter = true;
                break;
            }
            case fast: {
                double llIIllIIIlIlIII = 0.0;
                double llIIllIIIlIIlll = 0.0;
                assert (llIIllIIIlIIlIl.mc.field_1724 != null);
                class_243 llIIllIIIlIIllI = llIIllIIIlIIlIl.mc.field_1724.method_19538();
                if (llIIllIIIlIIllI.field_1352 > 0.0 && llIIllIIIlIIlIl.gp(llIIllIIIlIIllI.field_1352) < 3L) {
                    llIIllIIIlIlIII = 0.3;
                }
                if (llIIllIIIlIIllI.field_1352 > 0.0 && llIIllIIIlIIlIl.gp(llIIllIIIlIIllI.field_1352) > 6L) {
                    llIIllIIIlIlIII = -0.3;
                }
                if (llIIllIIIlIIllI.field_1352 < 0.0 && llIIllIIIlIIlIl.gp(llIIllIIIlIIllI.field_1352) < 3L) {
                    llIIllIIIlIlIII = -0.3;
                }
                if (llIIllIIIlIIllI.field_1352 < 0.0 && llIIllIIIlIIlIl.gp(llIIllIIIlIIllI.field_1352) > 6L) {
                    llIIllIIIlIlIII = 0.3;
                }
                if (llIIllIIIlIIllI.field_1350 > 0.0 && llIIllIIIlIIlIl.gp(llIIllIIIlIIllI.field_1350) < 3L) {
                    llIIllIIIlIIlll = 0.3;
                }
                if (llIIllIIIlIIllI.field_1350 > 0.0 && llIIllIIIlIIlIl.gp(llIIllIIIlIIllI.field_1350) > 6L) {
                    llIIllIIIlIIlll = -0.3;
                }
                if (llIIllIIIlIIllI.field_1350 < 0.0 && llIIllIIIlIIlIl.gp(llIIllIIIlIIllI.field_1350) < 3L) {
                    llIIllIIIlIIlll = -0.3;
                }
                if (llIIllIIIlIIllI.field_1350 < 0.0 && llIIllIIIlIIlIl.gp(llIIllIIIlIIllI.field_1350) > 6L) {
                    llIIllIIIlIIlll = 0.3;
                }
                if (llIIllIIIlIlIII == 0.0 && llIIllIIIlIIlll == 0.0) break;
                double llIIllIIIlIlIlI = llIIllIIIlIIlIl.mc.field_1724.method_23317() + llIIllIIIlIlIII;
                double llIIllIIIlIlIIl = llIIllIIIlIIlIl.mc.field_1724.method_23321() + llIIllIIIlIIlll;
                llIIllIIIlIIlIl.mc.field_1724.method_5814(llIIllIIIlIlIlI, llIIllIIIlIIlIl.mc.field_1724.method_23318(), llIIllIIIlIlIIl);
                llIIllIIIlIIlIl.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(llIIllIIIlIIlIl.mc.field_1724.method_23317(), llIIllIIIlIIlIl.mc.field_1724.method_23318(), llIIllIIIlIIlIl.mc.field_1724.method_23321(), llIIllIIIlIIlIl.mc.field_1724.method_24828()));
                break;
            }
        }
    }

    private void setBlockPos(int llIIlIllIlIlIlI, int llIIlIllIlIlIIl, int llIIlIllIlIlIII) {
        SurroundTwo llIIlIllIlIlIll;
        assert (llIIlIllIlIlIll.mc.field_1724 != null);
        llIIlIllIlIlIll.blockPos.method_10102(llIIlIllIlIlIll.mc.field_1724.method_23317() + (double)llIIlIllIlIlIlI, llIIlIllIlIlIll.mc.field_1724.method_23318() + (double)llIIlIllIlIlIIl, llIIlIllIlIlIll.mc.field_1724.method_23321() + (double)llIIlIllIlIlIII);
    }

    public static enum ecenter {
        fast,
        legit,
        disable;


        private ecenter() {
            ecenter llllllllllllllllIllIIIlIllIlIlII;
        }
    }
}

