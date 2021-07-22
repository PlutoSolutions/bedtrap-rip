/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1713
 *  net.minecraft.class_1802
 *  net.minecraft.class_465
 *  net.minecraft.class_490
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.bedtrap.InvUtils;
import net.minecraft.class_1713;
import net.minecraft.class_1802;
import net.minecraft.class_465;
import net.minecraft.class_490;

public class StrictAutoTotem
extends Module {
    static final boolean $assertionsDisabled = !StrictAutoTotem.class.desiredAssertionStatus();
    public int DelayTake;
    private final Setting<Integer> TotemTake;
    public int TotemCount;
    private final SettingGroup sgGeneral;
    private final Setting<Integer> TotemPlace;
    public int DelayPlace;
    public int TotemSlot;

    @Override
    public void onActivate() {
        this.DelayTake = this.TotemTake.get();
        this.DelayPlace = this.TotemPlace.get();
    }

    private void FullPlace() {
        if (this.DelayTake < this.TotemTake.get()) {
            ++this.DelayTake;
            return;
        }
        InvUtils.clickSlot(InvUtils.invIndexToSlotId(this.TotemSlot), 0, class_1713.field_7790);
        if (this.DelayPlace < this.TotemPlace.get()) {
            ++this.DelayPlace;
            return;
        }
        InvUtils.clickSlot(InvUtils.invIndexToSlotId(45), 0, class_1713.field_7790);
        this.DelayPlace = 0;
        this.DelayTake = 0;
    }

    public StrictAutoTotem() {
        super(Categories.BedTrap, "strict-auto-totem", "AutoTotem that bypasses Matrix anticheat.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.TotemTake = this.sgGeneral.add(new IntSetting.Builder().name("take-delay").description("Delay ticks between take.").defaultValue(0).min(0).sliderMax(20).build());
        this.TotemPlace = this.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("Delay ticks between take.").defaultValue(0).min(0).sliderMax(20).build());
        this.TotemSlot = -1;
        this.DelayTake = 0;
        this.DelayPlace = 0;
    }

    private void FindTotem() {
        this.TotemCount = 0;
        this.TotemSlot = -1;
        for (int i = 0; i < 44; ++i) {
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            if (this.mc.field_1724.field_7514.method_5438(i).method_7909() != class_1802.field_8288) continue;
            ++this.TotemCount;
            this.TotemSlot = i;
            if (-1 < 3) continue;
            return;
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.mc.field_1724 == null) {
            return;
        }
        this.FindTotem();
        if (this.mc.field_1724.method_6079().method_7909() == class_1802.field_8288) {
            return;
        }
        if (this.TotemCount == 0 && this.mc.field_1724.field_7514.method_7399().method_7909() == class_1802.field_8288) {
            ++this.TotemCount;
            if (this.mc.field_1755 instanceof class_490) {
                return;
            }
            if (!(this.mc.field_1755 instanceof class_465)) {
                this.FullPlace();
                return;
            }
        } else if (this.TotemCount > 0 && (this.mc.field_1755 instanceof class_490 || !(this.mc.field_1755 instanceof class_465))) {
            this.FullPlace();
        }
    }
}

