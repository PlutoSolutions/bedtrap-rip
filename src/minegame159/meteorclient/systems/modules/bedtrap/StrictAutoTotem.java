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
    public /* synthetic */ int DelayTake;
    private final /* synthetic */ Setting<Integer> TotemTake;
    public /* synthetic */ int TotemCount;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Integer> TotemPlace;
    public /* synthetic */ int DelayPlace;
    public /* synthetic */ int TotemSlot;

    @Override
    public void onActivate() {
        StrictAutoTotem llllllllllllllllllllIIIIlllIIIlI;
        llllllllllllllllllllIIIIlllIIIlI.DelayTake = llllllllllllllllllllIIIIlllIIIlI.TotemTake.get();
        llllllllllllllllllllIIIIlllIIIlI.DelayPlace = llllllllllllllllllllIIIIlllIIIlI.TotemPlace.get();
    }

    private void FullPlace() {
        StrictAutoTotem llllllllllllllllllllIIIIllIlIlIl;
        if (llllllllllllllllllllIIIIllIlIlIl.DelayTake < llllllllllllllllllllIIIIllIlIlIl.TotemTake.get()) {
            ++llllllllllllllllllllIIIIllIlIlIl.DelayTake;
            return;
        }
        InvUtils.clickSlot(InvUtils.invIndexToSlotId(llllllllllllllllllllIIIIllIlIlIl.TotemSlot), 0, class_1713.field_7790);
        if (llllllllllllllllllllIIIIllIlIlIl.DelayPlace < llllllllllllllllllllIIIIllIlIlIl.TotemPlace.get()) {
            ++llllllllllllllllllllIIIIllIlIlIl.DelayPlace;
            return;
        }
        InvUtils.clickSlot(InvUtils.invIndexToSlotId(45), 0, class_1713.field_7790);
        llllllllllllllllllllIIIIllIlIlIl.DelayPlace = 0;
        llllllllllllllllllllIIIIllIlIlIl.DelayTake = 0;
    }

    public StrictAutoTotem() {
        super(Categories.BedTrap, "strict-auto-totem", "AutoTotem that bypasses Matrix anticheat.");
        StrictAutoTotem llllllllllllllllllllIIIIlllIIlIl;
        llllllllllllllllllllIIIIlllIIlIl.sgGeneral = llllllllllllllllllllIIIIlllIIlIl.settings.getDefaultGroup();
        llllllllllllllllllllIIIIlllIIlIl.TotemTake = llllllllllllllllllllIIIIlllIIlIl.sgGeneral.add(new IntSetting.Builder().name("take-delay").description("Delay ticks between take.").defaultValue(0).min(0).sliderMax(20).build());
        llllllllllllllllllllIIIIlllIIlIl.TotemPlace = llllllllllllllllllllIIIIlllIIlIl.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("Delay ticks between take.").defaultValue(0).min(0).sliderMax(20).build());
        llllllllllllllllllllIIIIlllIIlIl.TotemSlot = -1;
        llllllllllllllllllllIIIIlllIIlIl.DelayTake = 0;
        llllllllllllllllllllIIIIlllIIlIl.DelayPlace = 0;
    }

    private void FindTotem() {
        llllllllllllllllllllIIIIllIllIII.TotemCount = 0;
        llllllllllllllllllllIIIIllIllIII.TotemSlot = -1;
        for (int llllllllllllllllllllIIIIllIllIlI = 0; llllllllllllllllllllIIIIllIllIlI < 44; ++llllllllllllllllllllIIIIllIllIlI) {
            StrictAutoTotem llllllllllllllllllllIIIIllIllIII;
            assert (llllllllllllllllllllIIIIllIllIII.mc.field_1724 != null);
            if (llllllllllllllllllllIIIIllIllIII.mc.field_1724.field_7514.method_5438(llllllllllllllllllllIIIIllIllIlI).method_7909() != class_1802.field_8288) continue;
            ++llllllllllllllllllllIIIIllIllIII.TotemCount;
            llllllllllllllllllllIIIIllIllIII.TotemSlot = llllllllllllllllllllIIIIllIllIlI;
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre llllllllllllllllllllIIIIllIllllI) {
        StrictAutoTotem llllllllllllllllllllIIIIllIlllll;
        if (llllllllllllllllllllIIIIllIlllll.mc.field_1724 == null) {
            return;
        }
        llllllllllllllllllllIIIIllIlllll.FindTotem();
        if (llllllllllllllllllllIIIIllIlllll.mc.field_1724.method_6079().method_7909() == class_1802.field_8288) {
            return;
        }
        if (llllllllllllllllllllIIIIllIlllll.TotemCount == 0 && llllllllllllllllllllIIIIllIlllll.mc.field_1724.field_7514.method_7399().method_7909() == class_1802.field_8288) {
            ++llllllllllllllllllllIIIIllIlllll.TotemCount;
            if (llllllllllllllllllllIIIIllIlllll.mc.field_1755 instanceof class_490) {
                return;
            }
            if (!(llllllllllllllllllllIIIIllIlllll.mc.field_1755 instanceof class_465)) {
                llllllllllllllllllllIIIIllIlllll.FullPlace();
                return;
            }
        } else if (llllllllllllllllllllIIIIllIlllll.TotemCount > 0 && (llllllllllllllllllllIIIIllIlllll.mc.field_1755 instanceof class_490 || !(llllllllllllllllllllIIIIllIlllll.mc.field_1755 instanceof class_465))) {
            llllllllllllllllllllIIIIllIlllll.FullPlace();
        }
    }
}

