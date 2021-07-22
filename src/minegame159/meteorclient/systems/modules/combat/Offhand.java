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
    private final Setting<Boolean> sword;
    private final Setting<Boolean> asimov;
    private final Setting<Boolean> offhandCA;
    private final Setting<Boolean> offhandCrystal;
    private Mode currentMode;
    private boolean isClicking;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> selfToggle;
    private final Setting<Boolean> hotBar;
    private final Setting<Boolean> replace;
    private final Setting<Mode> mode;
    static final boolean $assertionsDisabled = !Offhand.class.desiredAssertionStatus();
    private boolean noTotems;
    private boolean sentMessage;
    private final SettingGroup sgExtra;
    private final Setting<Integer> health;

    private class_1792 getItem() {
        class_1792 class_17922 = class_1802.field_8288;
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$combat$Offhand$Mode[this.currentMode.ordinal()]) {
            case 1: {
                class_17922 = class_1802.field_8367;
                break;
            }
            case 2: {
                class_17922 = class_1802.field_8287;
                break;
            }
            case 3: {
                class_17922 = class_1802.field_8463;
                break;
            }
            case 4: {
                class_17922 = class_1802.field_8301;
                break;
            }
            case 5: {
                class_17922 = class_1802.field_8255;
            }
        }
        return class_17922;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        int n;
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        this.currentMode = this.mode.get();
        if (!(this.mc.field_1755 == null || (this.mc.field_1755 instanceof class_490 || this.mc.field_1755 instanceof WidgetScreen) && this.asimov.get().booleanValue())) {
            return;
        }
        if (!this.mc.field_1724.method_6115()) {
            this.isClicking = false;
        }
        if (Modules.get().get(AutoTotem.class).getLocked()) {
            return;
        }
        if ((this.mc.field_1724.method_6047().method_7909() instanceof class_1829 || this.mc.field_1724.method_6047().method_7909() instanceof class_1743) && this.sword.get().booleanValue()) {
            this.currentMode = Mode.EGap;
        } else if (this.mc.field_1724.method_6047().method_7909() instanceof class_1775 && this.offhandCrystal.get().booleanValue()) {
            this.currentMode = Mode.Crystal;
        } else if (Modules.get().isActive(CrystalAura.class) && this.offhandCA.get().booleanValue()) {
            this.currentMode = Mode.Crystal;
        }
        if ((this.asimov.get().booleanValue() || this.noTotems) && this.mc.field_1724.method_6079().method_7909() != this.getItem()) {
            int n2 = this.findSlot(this.getItem());
            if (n2 == -1 && this.mc.field_1724.method_6079().method_7909() != this.getItem()) {
                if (this.currentMode != this.mode.get()) {
                    this.currentMode = this.mode.get();
                    if (this.mc.field_1724.method_6079().method_7909() != this.getItem() && (n2 = this.findSlot(this.getItem())) != -1) {
                        InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(n2), 1);
                        return;
                    }
                }
                if (!this.sentMessage) {
                    ChatUtils.warning(String.valueOf(new StringBuilder().append("Chosen item not found.").append(this.selfToggle.get() != false ? " Disabling." : "")), new Object[0]);
                    this.sentMessage = true;
                }
                if (this.selfToggle.get().booleanValue()) {
                    this.toggle();
                }
                return;
            }
            if (this.mc.field_1724.method_6079().method_7909() != this.getItem() && this.replace.get().booleanValue()) {
                InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(n2), 1);
                this.sentMessage = false;
            }
        } else if (!this.asimov.get().booleanValue() && !this.isClicking && this.mc.field_1724.method_6079().method_7909() != class_1802.field_8288 && (n = this.findSlot(class_1802.field_8288)) != -1) {
            InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(n), 1);
        }
    }

    @Override
    public String getInfoString() {
        return this.mode.get().name();
    }

    @Override
    public void onActivate() {
        this.currentMode = this.mode.get();
    }

    private int findSlot(class_1792 class_17922) {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        for (int i = 9; i < this.mc.field_1724.field_7514.method_5439(); ++i) {
            if (this.mc.field_1724.field_7514.method_5438(i).method_7909() != class_17922) continue;
            return i;
        }
        if (this.hotBar.get().booleanValue()) {
            return InvUtils.findItemWithCount((class_1792)class_17922).slot;
        }
        return -1;
    }

    @EventHandler
    private void onMouseButton(MouseButtonEvent mouseButtonEvent) {
        if (mouseButtonEvent.action != KeyAction.Press || mouseButtonEvent.button != 1) {
            return;
        }
        if (this.mc.field_1755 != null) {
            return;
        }
        if (Modules.get().get(AutoTotem.class).getLocked() || !this.canMove()) {
            return;
        }
        if (this.mc.field_1724.method_6079().method_7909() != class_1802.field_8288 || this.mc.field_1724.method_6032() + this.mc.field_1724.method_6067() > (float)this.health.get().intValue() && this.mc.field_1724.method_6079().method_7909() != this.getItem() && !(this.mc.field_1755 instanceof class_465)) {
            if (this.mc.field_1724.method_6047().method_7909() instanceof class_1829 && this.sword.get().booleanValue()) {
                this.currentMode = Mode.EGap;
            } else if (this.mc.field_1724.method_6047().method_7909() instanceof class_1775 && this.offhandCrystal.get().booleanValue()) {
                this.currentMode = Mode.Crystal;
            } else if (Modules.get().isActive(CrystalAura.class) && this.offhandCA.get().booleanValue()) {
                this.currentMode = Mode.Crystal;
            }
            if (this.mc.field_1724.method_6079().method_7909() == this.getItem()) {
                return;
            }
            this.isClicking = true;
            class_1792 class_17922 = this.getItem();
            int n = this.findSlot(class_17922);
            if (n == -1 && this.mc.field_1724.method_6079().method_7909() != this.getItem()) {
                if (!this.sentMessage) {
                    ChatUtils.warning(String.valueOf(new StringBuilder().append("Chosen item not found.").append(this.selfToggle.get() != false ? " Disabling." : "")), new Object[0]);
                    this.sentMessage = true;
                }
                if (this.selfToggle.get().booleanValue()) {
                    this.toggle();
                }
                return;
            }
            if (this.mc.field_1724.method_6079().method_7909() != class_17922 && this.mc.field_1724.method_6047().method_7909() != class_17922 && this.replace.get().booleanValue()) {
                InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(n), 1);
                this.sentMessage = false;
            }
            this.currentMode = this.mode.get();
        }
    }

    @Override
    public void onDeactivate() {
        if (this.mc.field_1687 == null || this.mc.field_1724 == null) {
            return;
        }
        if (Modules.get().isActive(AutoTotem.class) && this.mc.field_1724.method_6079().method_7909() != class_1802.field_8288) {
            InvUtils.FindItemResult findItemResult = InvUtils.findItemWithCount(class_1802.field_8288);
            if (findItemResult.slot != -1) {
                InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(findItemResult.slot), 1);
            }
        }
    }

    private void lambda$new$0(Mode mode) {
        this.currentMode = mode;
    }

    public Offhand() {
        super(Categories.Combat, "offhand-extra", "Allows you to use specified items in your offhand. REQUIRES AutoTotem to be on smart mode.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgExtra = this.settings.createGroup("Extras");
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Changes what item that will go into your offhand.").defaultValue(Mode.EGap).onChanged(this::lambda$new$0).build());
        this.replace = this.sgGeneral.add(new BoolSetting.Builder().name("replace").description("Replaces your offhand or waits until your offhand is empty.").defaultValue(true).build());
        this.asimov = this.sgGeneral.add(new BoolSetting.Builder().name("asimov").description("Always holds the item specified in your offhand.").defaultValue(false).build());
        this.health = this.sgGeneral.add(new IntSetting.Builder().name("health").description("The health at which Offhand Extra stops working.").defaultValue(10).min(0).sliderMax(20).build());
        this.selfToggle = this.sgGeneral.add(new BoolSetting.Builder().name("self-toggle").description("Toggles when you run out of the item you chose.").defaultValue(false).build());
        this.hotBar = this.sgGeneral.add(new BoolSetting.Builder().name("search-hotbar").description("Whether to take items out of your hotbar or not.").defaultValue(false).build());
        this.sword = this.sgExtra.add(new BoolSetting.Builder().name("sword-gap").description("Changes the mode to EGap if you are holding a sword in your main hand.").defaultValue(false).build());
        this.offhandCrystal = this.sgExtra.add(new BoolSetting.Builder().name("offhand-crystal-on-gap").description("Changes the mode to Crystal if you are holding an enchanted golden apple in your main hand.").defaultValue(false).build());
        this.offhandCA = this.sgExtra.add(new BoolSetting.Builder().name("offhand-crystal-on-ca").description("Changes the mode to Crystal when Crystal Aura is on.").defaultValue(false).build());
        this.isClicking = false;
        this.sentMessage = false;
        this.noTotems = false;
        this.currentMode = this.mode.get();
    }

    private boolean canMove() {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        return this.mc.field_1724.method_6047().method_7909() != class_1802.field_8102 && this.mc.field_1724.method_6047().method_7909() != class_1802.field_8547 && this.mc.field_1724.method_6047().method_7909() != class_1802.field_8399 && !this.mc.field_1724.method_6047().method_7909().method_19263();
    }

    public void setTotems(boolean bl) {
        this.noTotems = bl;
    }

    public static enum Mode {
        EGap,
        Gap,
        EXP,
        Crystal,
        Shield;

    }
}

