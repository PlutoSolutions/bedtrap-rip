/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1472
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1820
 */
package minegame159.meteorclient.systems.modules.world;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1472;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1820;

public class AutoShearer
extends Module {
    private /* synthetic */ boolean offHand;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ class_1297 entity;
    private final /* synthetic */ Setting<Boolean> antiBreak;
    private /* synthetic */ int preSlot;
    private final /* synthetic */ Setting<Double> distance;

    public AutoShearer() {
        super(Categories.World, "auto-shearer", "Automatically shears sheep.");
        AutoShearer llIlIIllIllIIll;
        llIlIIllIllIIll.sgGeneral = llIlIIllIllIIll.settings.getDefaultGroup();
        llIlIIllIllIIll.distance = llIlIIllIllIIll.sgGeneral.add(new DoubleSetting.Builder().name("distance").description("The maximum distance the sheep have to be to be sheared.").min(0.0).defaultValue(5.0).build());
        llIlIIllIllIIll.antiBreak = llIlIIllIllIIll.sgGeneral.add(new BoolSetting.Builder().name("anti-break").description("Prevents shears from being broken.").defaultValue(false).build());
        llIlIIllIllIIll.rotate = llIlIIllIllIIll.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces towards the animal being sheared.").defaultValue(true).build());
    }

    private void interact() {
        AutoShearer llIlIIllIIllIlI;
        llIlIIllIIllIlI.mc.field_1761.method_2905((class_1657)llIlIIllIIllIlI.mc.field_1724, llIlIIllIIllIlI.entity, llIlIIllIIllIlI.offHand ? class_1268.field_5810 : class_1268.field_5808);
        InvUtils.swap(llIlIIllIIllIlI.preSlot);
    }

    @EventHandler
    private void onTick(TickEvent.Pre llIlIIllIlIIIll) {
        AutoShearer llIlIIllIlIIIlI;
        llIlIIllIlIIIlI.entity = null;
        for (class_1297 llIlIIllIlIIlIl : llIlIIllIlIIIlI.mc.field_1687.method_18112()) {
            FindItemResult llIlIIllIlIlIII;
            boolean llIlIIllIlIIllI;
            if (!(llIlIIllIlIIlIl instanceof class_1472) || ((class_1472)llIlIIllIlIIlIl).method_6629() || ((class_1472)llIlIIllIlIIlIl).method_6109() || (double)llIlIIllIlIIIlI.mc.field_1724.method_5739(llIlIIllIlIIlIl) > llIlIIllIlIIIlI.distance.get()) continue;
            boolean llIlIIllIlIIlll = false;
            if (llIlIIllIlIIIlI.mc.field_1724.field_7514.method_7391().method_7909() instanceof class_1820) {
                if (llIlIIllIlIIIlI.antiBreak.get().booleanValue() && llIlIIllIlIIIlI.mc.field_1724.field_7514.method_7391().method_7919() >= llIlIIllIlIIIlI.mc.field_1724.field_7514.method_7391().method_7936() - 1) {
                    llIlIIllIlIIlll = true;
                }
            } else if (((class_1799)llIlIIllIlIIIlI.mc.field_1724.field_7514.field_7544.get(0)).method_7909() instanceof class_1820) {
                if (llIlIIllIlIIIlI.antiBreak.get().booleanValue() && ((class_1799)llIlIIllIlIIIlI.mc.field_1724.field_7514.field_7544.get(0)).method_7919() >= ((class_1799)llIlIIllIlIIIlI.mc.field_1724.field_7514.field_7544.get(0)).method_7936() - 1) {
                    llIlIIllIlIIlll = true;
                } else {
                    llIlIIllIlIIIlI.offHand = true;
                }
            } else {
                llIlIIllIlIIlll = true;
            }
            boolean bl = llIlIIllIlIIllI = !llIlIIllIlIIlll;
            if (llIlIIllIlIIlll && InvUtils.swap((llIlIIllIlIlIII = InvUtils.findInHotbar(llIlIIllIIlIllI -> {
                AutoShearer llIlIIllIIlIlIl;
                return (llIlIIllIIlIlIl.antiBreak.get() == false || llIlIIllIIlIlIl.antiBreak.get() != false && llIlIIllIIlIllI.method_7919() < llIlIIllIIlIllI.method_7936() - 1) && llIlIIllIIlIllI.method_7909() == class_1802.field_8868;
            })).getSlot())) {
                llIlIIllIlIIllI = true;
            }
            if (!llIlIIllIlIIllI) continue;
            llIlIIllIlIIIlI.entity = llIlIIllIlIIlIl;
            if (llIlIIllIlIIIlI.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(llIlIIllIlIIlIl), Rotations.getPitch(llIlIIllIlIIlIl), -100, llIlIIllIlIIIlI::interact);
            } else {
                llIlIIllIlIIIlI.interact();
            }
            return;
        }
    }

    @Override
    public void onDeactivate() {
        llIlIIllIllIIII.entity = null;
    }
}

