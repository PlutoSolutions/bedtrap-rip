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
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ Setting<Boolean> targetBabies;
    private final /* synthetic */ Setting<Boolean> putOutFire;
    private final /* synthetic */ Setting<Double> distance;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ int preSlot;
    private /* synthetic */ int ticks;
    private final /* synthetic */ Setting<Integer> tickInterval;
    private /* synthetic */ class_1297 entity;
    private final /* synthetic */ Setting<Boolean> antiBreak;

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIIIllIIlllIlIll) {
        Flamethrower lllllllllllllllllIIIllIIlllIllII;
        lllllllllllllllllIIIllIIlllIllII.entity = null;
        ++lllllllllllllllllIIIllIIlllIllII.ticks;
        for (class_1297 lllllllllllllllllIIIllIIlllIllIl : lllllllllllllllllIIIllIIlllIllII.mc.field_1687.method_18112()) {
            boolean lllllllllllllllllIIIllIIlllIlllI;
            if (!lllllllllllllllllIIIllIIlllIllII.entities.get().getBoolean((Object)lllllllllllllllllIIIllIIlllIllIl.method_5864()) || (double)lllllllllllllllllIIIllIIlllIllII.mc.field_1724.method_5739(lllllllllllllllllIIIllIIlllIllIl) > lllllllllllllllllIIIllIIlllIllII.distance.get() || lllllllllllllllllIIIllIIlllIllIl.method_5753() || lllllllllllllllllIIIllIIlllIllIl == lllllllllllllllllIIIllIIlllIllII.mc.field_1724 || !lllllllllllllllllIIIllIIlllIllII.targetBabies.get().booleanValue() && lllllllllllllllllIIIllIIlllIllIl instanceof class_1309 && ((class_1309)lllllllllllllllllIIIllIIlllIllIl).method_6109() || !(lllllllllllllllllIIIllIIlllIlllI = lllllllllllllllllIIIllIIlllIllII.selectSlot())) continue;
            lllllllllllllllllIIIllIIlllIllII.entity = lllllllllllllllllIIIllIIlllIllIl;
            if (lllllllllllllllllIIIllIIlllIllII.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(lllllllllllllllllIIIllIIlllIllIl.method_24515()), Rotations.getPitch(lllllllllllllllllIIIllIIlllIllIl.method_24515()), -100, lllllllllllllllllIIIllIIlllIllII::interact);
            } else {
                lllllllllllllllllIIIllIIlllIllII.interact();
            }
            return;
        }
    }

    private void interact() {
        Flamethrower lllllllllllllllllIIIllIIlllIIIlI;
        class_2248 lllllllllllllllllIIIllIIlllIIIIl = lllllllllllllllllIIIllIIlllIIIlI.mc.field_1687.method_8320(lllllllllllllllllIIIllIIlllIIIlI.entity.method_24515()).method_26204();
        class_2248 lllllllllllllllllIIIllIIlllIIIII = lllllllllllllllllIIIllIIlllIIIlI.mc.field_1687.method_8320(lllllllllllllllllIIIllIIlllIIIlI.entity.method_24515().method_10074()).method_26204();
        if (lllllllllllllllllIIIllIIlllIIIIl.method_27839(class_2246.field_10382) || lllllllllllllllllIIIllIIlllIIIII.method_27839(class_2246.field_10382) || lllllllllllllllllIIIllIIlllIIIII.method_27839(class_2246.field_10194)) {
            return;
        }
        if (lllllllllllllllllIIIllIIlllIIIIl.method_27839(class_2246.field_10479)) {
            lllllllllllllllllIIIllIIlllIIIlI.mc.field_1761.method_2910(lllllllllllllllllIIIllIIlllIIIlI.entity.method_24515(), class_2350.field_11033);
        }
        class_1309 lllllllllllllllllIIIllIIllIlllll = (class_1309)lllllllllllllllllIIIllIIlllIIIlI.entity;
        if (lllllllllllllllllIIIllIIlllIIIlI.putOutFire.get().booleanValue() && lllllllllllllllllIIIllIIllIlllll.method_6032() < 1.0f) {
            lllllllllllllllllIIIllIIlllIIIlI.mc.field_1761.method_2910(lllllllllllllllllIIIllIIlllIIIlI.entity.method_24515(), class_2350.field_11033);
            lllllllllllllllllIIIllIIlllIIIlI.mc.field_1761.method_2910(lllllllllllllllllIIIllIIlllIIIlI.entity.method_24515().method_10067(), class_2350.field_11033);
            lllllllllllllllllIIIllIIlllIIIlI.mc.field_1761.method_2910(lllllllllllllllllIIIllIIlllIIIlI.entity.method_24515().method_10078(), class_2350.field_11033);
            lllllllllllllllllIIIllIIlllIIIlI.mc.field_1761.method_2910(lllllllllllllllllIIIllIIlllIIIlI.entity.method_24515().method_10095(), class_2350.field_11033);
            lllllllllllllllllIIIllIIlllIIIlI.mc.field_1761.method_2910(lllllllllllllllllIIIllIIlllIIIlI.entity.method_24515().method_10072(), class_2350.field_11033);
        } else if (lllllllllllllllllIIIllIIlllIIIlI.ticks >= lllllllllllllllllIIIllIIlllIIIlI.tickInterval.get() && !lllllllllllllllllIIIllIIlllIIIlI.entity.method_5809()) {
            lllllllllllllllllIIIllIIlllIIIlI.mc.field_1761.method_2896(lllllllllllllllllIIIllIIlllIIIlI.mc.field_1724, lllllllllllllllllIIIllIIlllIIIlI.mc.field_1687, class_1268.field_5808, new class_3965(lllllllllllllllllIIIllIIlllIIIlI.entity.method_19538().method_1020(new class_243(0.0, 1.0, 0.0)), class_2350.field_11036, lllllllllllllllllIIIllIIlllIIIlI.entity.method_24515().method_10074(), false));
            lllllllllllllllllIIIllIIlllIIIlI.ticks = 0;
        }
        InvUtils.swap(lllllllllllllllllIIIllIIlllIIIlI.preSlot);
    }

    private boolean selectSlot() {
        boolean lllllllllllllllllIIIllIIllIlIlIl;
        Flamethrower lllllllllllllllllIIIllIIllIlIlll;
        lllllllllllllllllIIIllIIllIlIlll.preSlot = lllllllllllllllllIIIllIIllIlIlll.mc.field_1724.field_7514.field_7545;
        boolean lllllllllllllllllIIIllIIllIlIllI = false;
        if (lllllllllllllllllIIIllIIllIlIlll.mc.field_1724.field_7514.method_7391().method_7909() == class_1802.field_8884) {
            if (lllllllllllllllllIIIllIIllIlIlll.antiBreak.get().booleanValue() && lllllllllllllllllIIIllIIllIlIlll.mc.field_1724.field_7514.method_7391().method_7919() >= lllllllllllllllllIIIllIIllIlIlll.mc.field_1724.field_7514.method_7391().method_7936() - 1) {
                lllllllllllllllllIIIllIIllIlIllI = true;
            }
        } else if (((class_1799)lllllllllllllllllIIIllIIllIlIlll.mc.field_1724.field_7514.field_7544.get(0)).method_7909() == class_1802.field_8884) {
            if (lllllllllllllllllIIIllIIllIlIlll.antiBreak.get().booleanValue() && ((class_1799)lllllllllllllllllIIIllIIllIlIlll.mc.field_1724.field_7514.field_7544.get(0)).method_7919() >= ((class_1799)lllllllllllllllllIIIllIIllIlIlll.mc.field_1724.field_7514.field_7544.get(0)).method_7936() - 1) {
                lllllllllllllllllIIIllIIllIlIllI = true;
            }
        } else {
            lllllllllllllllllIIIllIIllIlIllI = true;
        }
        boolean bl = lllllllllllllllllIIIllIIllIlIlIl = !lllllllllllllllllIIIllIIllIlIllI;
        if (lllllllllllllllllIIIllIIllIlIllI) {
            lllllllllllllllllIIIllIIllIlIlIl = InvUtils.swap(InvUtils.findInHotbar(lllllllllllllllllIIIllIIllIIlllI -> {
                Flamethrower lllllllllllllllllIIIllIIllIIllIl;
                return (lllllllllllllllllIIIllIIllIIllIl.antiBreak.get() == false || lllllllllllllllllIIIllIIllIIllIl.antiBreak.get() != false && lllllllllllllllllIIIllIIllIIlllI.method_7919() < lllllllllllllllllIIIllIIllIIlllI.method_7936() - 1) && lllllllllllllllllIIIllIIllIIlllI.method_7909() == class_1802.field_8884;
            }).getSlot());
        }
        return lllllllllllllllllIIIllIIllIlIlIl;
    }

    public Flamethrower() {
        super(Categories.World, "flamethrower", "Ignites every alive piece of food.");
        Flamethrower lllllllllllllllllIIIllIIllllIlll;
        lllllllllllllllllIIIllIIllllIlll.sgGeneral = lllllllllllllllllIIIllIIllllIlll.settings.getDefaultGroup();
        lllllllllllllllllIIIllIIllllIlll.distance = lllllllllllllllllIIIllIIllllIlll.sgGeneral.add(new DoubleSetting.Builder().name("distance").description("The maximum distance the animal has to be to be roasted.").min(0.0).defaultValue(5.0).build());
        lllllllllllllllllIIIllIIllllIlll.antiBreak = lllllllllllllllllIIIllIIllllIlll.sgGeneral.add(new BoolSetting.Builder().name("anti-break").description("Prevents flint and steel from being broken.").defaultValue(false).build());
        lllllllllllllllllIIIllIIllllIlll.putOutFire = lllllllllllllllllIIIllIIllllIlll.sgGeneral.add(new BoolSetting.Builder().name("put-out-fire").description("Tries to put out the fire when animal is low health, so the items don't burn.").defaultValue(true).build());
        lllllllllllllllllIIIllIIllllIlll.targetBabies = lllllllllllllllllIIIllIIllllIlll.sgGeneral.add(new BoolSetting.Builder().name("target-babies").description("If checked babies will also be killed.").defaultValue(false).build());
        lllllllllllllllllIIIllIIllllIlll.tickInterval = lllllllllllllllllIIIllIIllllIlll.sgGeneral.add(new IntSetting.Builder().name("tick-interval").defaultValue(5).build());
        lllllllllllllllllIIIllIIllllIlll.rotate = lllllllllllllllllIIIllIIllllIlll.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces towards the animal roasted.").defaultValue(true).build());
        lllllllllllllllllIIIllIIllllIlll.entities = lllllllllllllllllIIIllIIllllIlll.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to cook.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6093, class_1299.field_6085, class_1299.field_6115, class_1299.field_6132, class_1299.field_6140})).build());
        lllllllllllllllllIIIllIIllllIlll.ticks = 0;
    }

    @Override
    public void onDeactivate() {
        lllllllllllllllllIIIllIIllllIlII.entity = null;
    }
}

