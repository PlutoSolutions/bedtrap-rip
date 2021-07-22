/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.world;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3965;

public class Flamethrower
extends Module {
    private final Setting<Boolean> rotate;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<Boolean> targetBabies;
    private final Setting<Boolean> putOutFire;
    private final Setting<Double> distance;
    private final SettingGroup sgGeneral;
    private int preSlot;
    private int ticks;
    private final Setting<Integer> tickInterval;
    private class_1297 entity;
    private final Setting<Boolean> antiBreak;

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        this.entity = null;
        ++this.ticks;
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            boolean bl;
            if (!this.entities.get().getBoolean((Object)class_12972.method_5864()) || (double)this.mc.field_1724.method_5739(class_12972) > this.distance.get() || class_12972.method_5753() || class_12972 == this.mc.field_1724 || !this.targetBabies.get().booleanValue() && class_12972 instanceof class_1309 && ((class_1309)class_12972).method_6109() || !(bl = this.selectSlot())) continue;
            this.entity = class_12972;
            if (this.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(class_12972.method_24515()), Rotations.getPitch(class_12972.method_24515()), -100, this::interact);
            } else {
                this.interact();
            }
            return;
        }
    }

    private void interact() {
        class_2248 class_22482 = this.mc.field_1687.method_8320(this.entity.method_24515()).method_26204();
        class_2248 class_22483 = this.mc.field_1687.method_8320(this.entity.method_24515().method_10074()).method_26204();
        if (class_22482.method_27839(class_2246.field_10382) || class_22483.method_27839(class_2246.field_10382) || class_22483.method_27839(class_2246.field_10194)) {
            return;
        }
        if (class_22482.method_27839(class_2246.field_10479)) {
            this.mc.field_1761.method_2910(this.entity.method_24515(), class_2350.field_11033);
        }
        class_1309 class_13092 = (class_1309)this.entity;
        if (this.putOutFire.get().booleanValue() && class_13092.method_6032() < 1.0f) {
            this.mc.field_1761.method_2910(this.entity.method_24515(), class_2350.field_11033);
            this.mc.field_1761.method_2910(this.entity.method_24515().method_10067(), class_2350.field_11033);
            this.mc.field_1761.method_2910(this.entity.method_24515().method_10078(), class_2350.field_11033);
            this.mc.field_1761.method_2910(this.entity.method_24515().method_10095(), class_2350.field_11033);
            this.mc.field_1761.method_2910(this.entity.method_24515().method_10072(), class_2350.field_11033);
        } else if (this.ticks >= this.tickInterval.get() && !this.entity.method_5809()) {
            this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5808, new class_3965(this.entity.method_19538().method_1020(new class_243(0.0, 1.0, 0.0)), class_2350.field_11036, this.entity.method_24515().method_10074(), false));
            this.ticks = 0;
        }
        InvUtils.swap(this.preSlot);
    }

    private boolean selectSlot() {
        boolean bl;
        this.preSlot = this.mc.field_1724.field_7514.field_7545;
        boolean bl2 = false;
        if (this.mc.field_1724.field_7514.method_7391().method_7909() == class_1802.field_8884) {
            if (this.antiBreak.get().booleanValue() && this.mc.field_1724.field_7514.method_7391().method_7919() >= this.mc.field_1724.field_7514.method_7391().method_7936() - 1) {
                bl2 = true;
            }
        } else if (((class_1799)this.mc.field_1724.field_7514.field_7544.get(0)).method_7909() == class_1802.field_8884) {
            if (this.antiBreak.get().booleanValue() && ((class_1799)this.mc.field_1724.field_7514.field_7544.get(0)).method_7919() >= ((class_1799)this.mc.field_1724.field_7514.field_7544.get(0)).method_7936() - 1) {
                bl2 = true;
            }
        } else {
            bl2 = true;
        }
        boolean bl3 = bl = !bl2;
        if (bl2) {
            bl = InvUtils.swap(InvUtils.findInHotbar(this::lambda$selectSlot$0).getSlot());
        }
        return bl;
    }

    private boolean lambda$selectSlot$0(class_1799 class_17992) {
        return (this.antiBreak.get() == false || this.antiBreak.get() != false && class_17992.method_7919() < class_17992.method_7936() - 1) && class_17992.method_7909() == class_1802.field_8884;
    }

    public Flamethrower() {
        super(Categories.World, "flamethrower", "Ignites every alive piece of food.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.distance = this.sgGeneral.add(new DoubleSetting.Builder().name("distance").description("The maximum distance the animal has to be to be roasted.").min(0.0).defaultValue(5.0).build());
        this.antiBreak = this.sgGeneral.add(new BoolSetting.Builder().name("anti-break").description("Prevents flint and steel from being broken.").defaultValue(false).build());
        this.putOutFire = this.sgGeneral.add(new BoolSetting.Builder().name("put-out-fire").description("Tries to put out the fire when animal is low health, so the items don't burn.").defaultValue(true).build());
        this.targetBabies = this.sgGeneral.add(new BoolSetting.Builder().name("target-babies").description("If checked babies will also be killed.").defaultValue(false).build());
        this.tickInterval = this.sgGeneral.add(new IntSetting.Builder().name("tick-interval").defaultValue(5).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces towards the animal roasted.").defaultValue(true).build());
        this.entities = this.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to cook.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6093, class_1299.field_6085, class_1299.field_6115, class_1299.field_6132, class_1299.field_6140})).build());
        this.ticks = 0;
    }

    @Override
    public void onDeactivate() {
        this.entity = null;
    }
}

