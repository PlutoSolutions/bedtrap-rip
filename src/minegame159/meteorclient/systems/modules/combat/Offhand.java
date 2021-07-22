/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1743
 *  net.minecraft.class_1775
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_1829
 *  net.minecraft.class_465
 *  net.minecraft.class_490
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.meteor.MouseButtonEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.combat.AutoTotem;
import minegame159.meteorclient.systems.modules.combat.CrystalAura;
import minegame159.meteorclient.utils.bedtrap.InvUtils;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_1743;
import net.minecraft.class_1775;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_1829;
import net.minecraft.class_465;
import net.minecraft.class_490;

public class Offhand
extends Module {
    private final /* synthetic */ Setting<Boolean> sword;
    private final /* synthetic */ Setting<Boolean> asimov;
    private final /* synthetic */ Setting<Boolean> offhandCA;
    private final /* synthetic */ Setting<Boolean> offhandCrystal;
    private /* synthetic */ Mode currentMode;
    private /* synthetic */ boolean isClicking;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> selfToggle;
    private final /* synthetic */ Setting<Boolean> hotBar;
    private final /* synthetic */ Setting<Boolean> replace;
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ boolean noTotems;
    private /* synthetic */ boolean sentMessage;
    private final /* synthetic */ SettingGroup sgExtra;
    private final /* synthetic */ Setting<Integer> health;

    private class_1792 getItem() {
        Offhand lIlIlIlIlIlIlI;
        class_1792 lIlIlIlIlIlIll = class_1802.field_8288;
        switch (lIlIlIlIlIlIlI.currentMode) {
            case EGap: {
                lIlIlIlIlIlIll = class_1802.field_8367;
                break;
            }
            case EXP: {
                lIlIlIlIlIlIll = class_1802.field_8287;
                break;
            }
            case Gap: {
                lIlIlIlIlIlIll = class_1802.field_8463;
                break;
            }
            case Crystal: {
                lIlIlIlIlIlIll = class_1802.field_8301;
                break;
            }
            case Shield: {
                lIlIlIlIlIlIll = class_1802.field_8255;
            }
        }
        return lIlIlIlIlIlIll;
    }

    @EventHandler
    private void onTick(TickEvent.Post lIlIlIlIllllIl) {
        int lIlIlIlIllllll;
        Offhand lIlIlIlIllllII;
        assert (lIlIlIlIllllII.mc.field_1724 != null);
        lIlIlIlIllllII.currentMode = lIlIlIlIllllII.mode.get();
        if (!(lIlIlIlIllllII.mc.field_1755 == null || (lIlIlIlIllllII.mc.field_1755 instanceof class_490 || lIlIlIlIllllII.mc.field_1755 instanceof WidgetScreen) && lIlIlIlIllllII.asimov.get().booleanValue())) {
            return;
        }
        if (!lIlIlIlIllllII.mc.field_1724.method_6115()) {
            lIlIlIlIllllII.isClicking = false;
        }
        if (Modules.get().get(AutoTotem.class).getLocked()) {
            return;
        }
        if ((lIlIlIlIllllII.mc.field_1724.method_6047().method_7909() instanceof class_1829 || lIlIlIlIllllII.mc.field_1724.method_6047().method_7909() instanceof class_1743) && lIlIlIlIllllII.sword.get().booleanValue()) {
            lIlIlIlIllllII.currentMode = Mode.EGap;
        } else if (lIlIlIlIllllII.mc.field_1724.method_6047().method_7909() instanceof class_1775 && lIlIlIlIllllII.offhandCrystal.get().booleanValue()) {
            lIlIlIlIllllII.currentMode = Mode.Crystal;
        } else if (Modules.get().isActive(CrystalAura.class) && lIlIlIlIllllII.offhandCA.get().booleanValue()) {
            lIlIlIlIllllII.currentMode = Mode.Crystal;
        }
        if ((lIlIlIlIllllII.asimov.get().booleanValue() || lIlIlIlIllllII.noTotems) && lIlIlIlIllllII.mc.field_1724.method_6079().method_7909() != lIlIlIlIllllII.getItem()) {
            int lIlIlIllIIIIII = lIlIlIlIllllII.findSlot(lIlIlIlIllllII.getItem());
            if (lIlIlIllIIIIII == -1 && lIlIlIlIllllII.mc.field_1724.method_6079().method_7909() != lIlIlIlIllllII.getItem()) {
                if (lIlIlIlIllllII.currentMode != lIlIlIlIllllII.mode.get()) {
                    lIlIlIlIllllII.currentMode = lIlIlIlIllllII.mode.get();
                    if (lIlIlIlIllllII.mc.field_1724.method_6079().method_7909() != lIlIlIlIllllII.getItem() && (lIlIlIllIIIIII = lIlIlIlIllllII.findSlot(lIlIlIlIllllII.getItem())) != -1) {
                        InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(lIlIlIllIIIIII), 1);
                        return;
                    }
                }
                if (!lIlIlIlIllllII.sentMessage) {
                    ChatUtils.warning(String.valueOf(new StringBuilder().append("Chosen item not found.").append(lIlIlIlIllllII.selfToggle.get() != false ? " Disabling." : "")), new Object[0]);
                    lIlIlIlIllllII.sentMessage = true;
                }
                if (lIlIlIlIllllII.selfToggle.get().booleanValue()) {
                    lIlIlIlIllllII.toggle();
                }
                return;
            }
            if (lIlIlIlIllllII.mc.field_1724.method_6079().method_7909() != lIlIlIlIllllII.getItem() && lIlIlIlIllllII.replace.get().booleanValue()) {
                InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(lIlIlIllIIIIII), 1);
                lIlIlIlIllllII.sentMessage = false;
            }
        } else if (!lIlIlIlIllllII.asimov.get().booleanValue() && !lIlIlIlIllllII.isClicking && lIlIlIlIllllII.mc.field_1724.method_6079().method_7909() != class_1802.field_8288 && (lIlIlIlIllllll = lIlIlIlIllllII.findSlot(class_1802.field_8288)) != -1) {
            InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(lIlIlIlIllllll), 1);
        }
    }

    @Override
    public String getInfoString() {
        Offhand lIlIlIlIIlIlIl;
        return lIlIlIlIIlIlIl.mode.get().name();
    }

    @Override
    public void onActivate() {
        Offhand lIlIlIllIIlIlI;
        lIlIlIllIIlIlI.currentMode = lIlIlIllIIlIlI.mode.get();
    }

    private int findSlot(class_1792 lIlIlIlIIllIII) {
        Offhand lIlIlIlIIllIIl;
        assert (lIlIlIlIIllIIl.mc.field_1724 != null);
        for (int lIlIlIlIIlllII = 9; lIlIlIlIIlllII < lIlIlIlIIllIIl.mc.field_1724.field_7514.method_5439(); ++lIlIlIlIIlllII) {
            if (lIlIlIlIIllIIl.mc.field_1724.field_7514.method_5438(lIlIlIlIIlllII).method_7909() != lIlIlIlIIllIII) continue;
            return lIlIlIlIIlllII;
        }
        if (lIlIlIlIIllIIl.hotBar.get().booleanValue()) {
            return InvUtils.findItemWithCount((class_1792)lIlIlIlIIllIII).slot;
        }
        return -1;
    }

    @EventHandler
    private void onMouseButton(MouseButtonEvent lIlIlIlIllIIIl) {
        Offhand lIlIlIlIllIlII;
        if (lIlIlIlIllIIIl.action != KeyAction.Press || lIlIlIlIllIIIl.button != 1) {
            return;
        }
        if (lIlIlIlIllIlII.mc.field_1755 != null) {
            return;
        }
        if (Modules.get().get(AutoTotem.class).getLocked() || !lIlIlIlIllIlII.canMove()) {
            return;
        }
        if (lIlIlIlIllIlII.mc.field_1724.method_6079().method_7909() != class_1802.field_8288 || lIlIlIlIllIlII.mc.field_1724.method_6032() + lIlIlIlIllIlII.mc.field_1724.method_6067() > (float)lIlIlIlIllIlII.health.get().intValue() && lIlIlIlIllIlII.mc.field_1724.method_6079().method_7909() != lIlIlIlIllIlII.getItem() && !(lIlIlIlIllIlII.mc.field_1755 instanceof class_465)) {
            if (lIlIlIlIllIlII.mc.field_1724.method_6047().method_7909() instanceof class_1829 && lIlIlIlIllIlII.sword.get().booleanValue()) {
                lIlIlIlIllIlII.currentMode = Mode.EGap;
            } else if (lIlIlIlIllIlII.mc.field_1724.method_6047().method_7909() instanceof class_1775 && lIlIlIlIllIlII.offhandCrystal.get().booleanValue()) {
                lIlIlIlIllIlII.currentMode = Mode.Crystal;
            } else if (Modules.get().isActive(CrystalAura.class) && lIlIlIlIllIlII.offhandCA.get().booleanValue()) {
                lIlIlIlIllIlII.currentMode = Mode.Crystal;
            }
            if (lIlIlIlIllIlII.mc.field_1724.method_6079().method_7909() == lIlIlIlIllIlII.getItem()) {
                return;
            }
            lIlIlIlIllIlII.isClicking = true;
            class_1792 lIlIlIlIllIllI = lIlIlIlIllIlII.getItem();
            int lIlIlIlIllIlIl = lIlIlIlIllIlII.findSlot(lIlIlIlIllIllI);
            if (lIlIlIlIllIlIl == -1 && lIlIlIlIllIlII.mc.field_1724.method_6079().method_7909() != lIlIlIlIllIlII.getItem()) {
                if (!lIlIlIlIllIlII.sentMessage) {
                    ChatUtils.warning(String.valueOf(new StringBuilder().append("Chosen item not found.").append(lIlIlIlIllIlII.selfToggle.get() != false ? " Disabling." : "")), new Object[0]);
                    lIlIlIlIllIlII.sentMessage = true;
                }
                if (lIlIlIlIllIlII.selfToggle.get().booleanValue()) {
                    lIlIlIlIllIlII.toggle();
                }
                return;
            }
            if (lIlIlIlIllIlII.mc.field_1724.method_6079().method_7909() != lIlIlIlIllIllI && lIlIlIlIllIlII.mc.field_1724.method_6047().method_7909() != lIlIlIlIllIllI && lIlIlIlIllIlII.replace.get().booleanValue()) {
                InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(lIlIlIlIllIlIl), 1);
                lIlIlIlIllIlII.sentMessage = false;
            }
            lIlIlIlIllIlII.currentMode = lIlIlIlIllIlII.mode.get();
        }
    }

    @Override
    public void onDeactivate() {
        Offhand lIlIlIllIIIlIl;
        if (lIlIlIllIIIlIl.mc.field_1687 == null || lIlIlIllIIIlIl.mc.field_1724 == null) {
            return;
        }
        if (Modules.get().isActive(AutoTotem.class) && lIlIlIllIIIlIl.mc.field_1724.method_6079().method_7909() != class_1802.field_8288) {
            InvUtils.FindItemResult lIlIlIllIIIllI = InvUtils.findItemWithCount(class_1802.field_8288);
            if (lIlIlIllIIIllI.slot != -1) {
                InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(lIlIlIllIIIllI.slot), 1);
            }
        }
    }

    public Offhand() {
        super(Categories.Combat, "offhand-extra", "Allows you to use specified items in your offhand. REQUIRES AutoTotem to be on smart mode.");
        Offhand lIlIlIllIIllII;
        lIlIlIllIIllII.sgGeneral = lIlIlIllIIllII.settings.getDefaultGroup();
        lIlIlIllIIllII.sgExtra = lIlIlIllIIllII.settings.createGroup("Extras");
        lIlIlIllIIllII.mode = lIlIlIllIIllII.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Changes what item that will go into your offhand.").defaultValue(Mode.EGap).onChanged(lIlIlIlIIlIIII -> {
            lIlIlIlIIIllll.currentMode = lIlIlIlIIlIIII;
        }).build());
        lIlIlIllIIllII.replace = lIlIlIllIIllII.sgGeneral.add(new BoolSetting.Builder().name("replace").description("Replaces your offhand or waits until your offhand is empty.").defaultValue(true).build());
        lIlIlIllIIllII.asimov = lIlIlIllIIllII.sgGeneral.add(new BoolSetting.Builder().name("asimov").description("Always holds the item specified in your offhand.").defaultValue(false).build());
        lIlIlIllIIllII.health = lIlIlIllIIllII.sgGeneral.add(new IntSetting.Builder().name("health").description("The health at which Offhand Extra stops working.").defaultValue(10).min(0).sliderMax(20).build());
        lIlIlIllIIllII.selfToggle = lIlIlIllIIllII.sgGeneral.add(new BoolSetting.Builder().name("self-toggle").description("Toggles when you run out of the item you chose.").defaultValue(false).build());
        lIlIlIllIIllII.hotBar = lIlIlIllIIllII.sgGeneral.add(new BoolSetting.Builder().name("search-hotbar").description("Whether to take items out of your hotbar or not.").defaultValue(false).build());
        lIlIlIllIIllII.sword = lIlIlIllIIllII.sgExtra.add(new BoolSetting.Builder().name("sword-gap").description("Changes the mode to EGap if you are holding a sword in your main hand.").defaultValue(false).build());
        lIlIlIllIIllII.offhandCrystal = lIlIlIllIIllII.sgExtra.add(new BoolSetting.Builder().name("offhand-crystal-on-gap").description("Changes the mode to Crystal if you are holding an enchanted golden apple in your main hand.").defaultValue(false).build());
        lIlIlIllIIllII.offhandCA = lIlIlIllIIllII.sgExtra.add(new BoolSetting.Builder().name("offhand-crystal-on-ca").description("Changes the mode to Crystal when Crystal Aura is on.").defaultValue(false).build());
        lIlIlIllIIllII.isClicking = false;
        lIlIlIllIIllII.sentMessage = false;
        lIlIlIllIIllII.noTotems = false;
        lIlIlIllIIllII.currentMode = lIlIlIllIIllII.mode.get();
    }

    private boolean canMove() {
        Offhand lIlIlIlIlIIIII;
        assert (lIlIlIlIlIIIII.mc.field_1724 != null);
        return lIlIlIlIlIIIII.mc.field_1724.method_6047().method_7909() != class_1802.field_8102 && lIlIlIlIlIIIII.mc.field_1724.method_6047().method_7909() != class_1802.field_8547 && lIlIlIlIlIIIII.mc.field_1724.method_6047().method_7909() != class_1802.field_8399 && !lIlIlIlIlIIIII.mc.field_1724.method_6047().method_7909().method_19263();
    }

    public void setTotems(boolean lIlIlIlIlIIIll) {
        lIlIlIlIlIIlII.noTotems = lIlIlIlIlIIIll;
    }

    public static enum Mode {
        EGap,
        Gap,
        EXP,
        Crystal,
        Shield;


        private Mode() {
            Mode llllllllllllllllllIllIIIllllIIIl;
        }
    }
}

