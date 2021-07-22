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
    private boolean offHand;
    private final Setting<Boolean> rotate;
    private final SettingGroup sgGeneral;
    private class_1297 entity;
    private final Setting<Boolean> antiBreak;
    private int preSlot;
    private final Setting<Double> distance;

    public AutoShearer() {
        super(Categories.World, "auto-shearer", "Automatically shears sheep.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.distance = this.sgGeneral.add(new DoubleSetting.Builder().name("distance").description("The maximum distance the sheep have to be to be sheared.").min(0.0).defaultValue(5.0).build());
        this.antiBreak = this.sgGeneral.add(new BoolSetting.Builder().name("anti-break").description("Prevents shears from being broken.").defaultValue(false).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces towards the animal being sheared.").defaultValue(true).build());
    }

    private void interact() {
        this.mc.field_1761.method_2905((class_1657)this.mc.field_1724, this.entity, this.offHand ? class_1268.field_5810 : class_1268.field_5808);
        InvUtils.swap(this.preSlot);
    }

    private boolean lambda$onTick$0(class_1799 class_17992) {
        return (this.antiBreak.get() == false || this.antiBreak.get() != false && class_17992.method_7919() < class_17992.method_7936() - 1) && class_17992.method_7909() == class_1802.field_8868;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        this.entity = null;
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            FindItemResult findItemResult;
            boolean bl;
            if (!(class_12972 instanceof class_1472) || ((class_1472)class_12972).method_6629() || ((class_1472)class_12972).method_6109() || (double)this.mc.field_1724.method_5739(class_12972) > this.distance.get()) continue;
            boolean bl2 = false;
            if (this.mc.field_1724.field_7514.method_7391().method_7909() instanceof class_1820) {
                if (this.antiBreak.get().booleanValue() && this.mc.field_1724.field_7514.method_7391().method_7919() >= this.mc.field_1724.field_7514.method_7391().method_7936() - 1) {
                    bl2 = true;
                }
            } else if (((class_1799)this.mc.field_1724.field_7514.field_7544.get(0)).method_7909() instanceof class_1820) {
                if (this.antiBreak.get().booleanValue() && ((class_1799)this.mc.field_1724.field_7514.field_7544.get(0)).method_7919() >= ((class_1799)this.mc.field_1724.field_7514.field_7544.get(0)).method_7936() - 1) {
                    bl2 = true;
                } else {
                    this.offHand = true;
                }
            } else {
                bl2 = true;
            }
            boolean bl3 = bl = !bl2;
            if (bl2 && InvUtils.swap((findItemResult = InvUtils.findInHotbar(this::lambda$onTick$0)).getSlot())) {
                bl = true;
            }
            if (!bl) continue;
            this.entity = class_12972;
            if (this.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(class_12972), Rotations.getPitch(class_12972), -100, this::interact);
            } else {
                this.interact();
            }
            return;
        }
    }

    @Override
    public void onDeactivate() {
        this.entity = null;
    }
}

