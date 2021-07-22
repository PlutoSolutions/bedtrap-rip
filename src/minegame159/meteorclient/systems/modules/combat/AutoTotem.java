/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_1829
 *  net.minecraft.class_243
 *  net.minecraft.class_2586
 *  net.minecraft.class_2587
 *  net.minecraft.class_310
 *  net.minecraft.class_490
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.combat.Offhand;
import minegame159.meteorclient.systems.modules.movement.NoFall;
import minegame159.meteorclient.utils.bedtrap.InvUtils;
import minegame159.meteorclient.utils.bedtrap.Utils;
import minegame159.meteorclient.utils.player.DamageCalcUtils;
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_1829;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_2587;
import net.minecraft.class_310;
import net.minecraft.class_490;

public class AutoTotem
extends Module {
    private /* synthetic */ String totemCountString;
    private final /* synthetic */ class_310 mc;
    private /* synthetic */ boolean locked;
    private final /* synthetic */ Setting<Boolean> elytraHold;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> smart;
    private final /* synthetic */ Setting<Integer> health;
    private final /* synthetic */ Setting<Boolean> fallback;
    private final /* synthetic */ Setting<Boolean> inventorySwitch;

    @Override
    public void onDeactivate() {
        lllllIlIlIlllI.locked = false;
    }

    private double getHealthReduction() {
        double lllllIlIIllllI;
        AutoTotem lllllIlIIllIlI;
        assert (lllllIlIIllIlI.mc.field_1687 != null);
        assert (lllllIlIIllIlI.mc.field_1724 != null);
        double lllllIlIIllIll = 0.0;
        if (lllllIlIIllIlI.mc.field_1724.field_7503.field_7477) {
            return lllllIlIIllIll;
        }
        for (class_1297 lllllIlIIlllll : lllllIlIIllIlI.mc.field_1687.method_18112()) {
            if (lllllIlIIlllll instanceof class_1511 && lllllIlIIllIll < DamageCalcUtils.crystalDamage((class_1309)lllllIlIIllIlI.mc.field_1724, lllllIlIIlllll.method_19538())) {
                lllllIlIIllIll = DamageCalcUtils.crystalDamage((class_1309)lllllIlIIllIlI.mc.field_1724, lllllIlIIlllll.method_19538());
                continue;
            }
            if (!(lllllIlIIlllll instanceof class_1657) || !(lllllIlIIllIll < DamageCalcUtils.getSwordDamage((class_1657)lllllIlIIlllll, true)) || !Friends.get().shouldAttack((class_1657)lllllIlIIlllll) || !(lllllIlIIllIlI.mc.field_1724.method_19538().method_1022(lllllIlIIlllll.method_19538()) < 5.0) || !(((class_1657)lllllIlIIlllll).method_6030().method_7909() instanceof class_1829)) continue;
            lllllIlIIllIll = DamageCalcUtils.getSwordDamage((class_1657)lllllIlIIlllll, true);
        }
        if (!Modules.get().isActive(NoFall.class) && lllllIlIIllIlI.mc.field_1724.field_6017 > 3.0f && (lllllIlIIllllI = (double)lllllIlIIllIlI.mc.field_1724.field_6017 * 0.5) > lllllIlIIllIll) {
            lllllIlIIllIll = lllllIlIIllllI;
        }
        if (Utils.getDimension() != Dimension.Nether) {
            for (class_2586 lllllIlIIlllIl : lllllIlIIllIlI.mc.field_1687.field_9231) {
                if (!(lllllIlIIlllIl instanceof class_2587)) continue;
                class_243 class_2432 = new class_243((double)lllllIlIIlllIl.method_11016().method_10263(), (double)lllllIlIIlllIl.method_11016().method_10264(), (double)lllllIlIIlllIl.method_11016().method_10260());
                if (!(lllllIlIIllIll < DamageCalcUtils.bedDamage((class_1309)lllllIlIIllIlI.mc.field_1724, class_2432))) continue;
                lllllIlIIllIll = DamageCalcUtils.bedDamage((class_1309)lllllIlIIllIlI.mc.field_1724, new class_243((double)lllllIlIIlllIl.method_11016().method_10263(), (double)lllllIlIIlllIl.method_11016().method_10264(), (double)lllllIlIIlllIl.method_11016().method_10260()));
            }
        }
        return lllllIlIIllIll;
    }

    private boolean isLow() {
        AutoTotem lllllIlIIIllll;
        return lllllIlIIIllll.getHealth() < (double)lllllIlIIIllll.health.get().intValue() || lllllIlIIIllll.getHealth() - lllllIlIIIllll.getHealthReduction() < (double)lllllIlIIIllll.health.get().intValue();
    }

    private double getHealth() {
        AutoTotem lllllIlIIlIlIl;
        assert (lllllIlIIlIlIl.mc.field_1724 != null);
        return lllllIlIIlIlIl.mc.field_1724.method_6032() + lllllIlIIlIlIl.mc.field_1724.method_6067();
    }

    @Override
    public String getInfoString() {
        AutoTotem lllllIlIlIIlII;
        return lllllIlIlIIlII.totemCountString;
    }

    private boolean elytraMove() {
        AutoTotem lllllIlIIIllII;
        return lllllIlIIIllII.elytraHold.get() != false && lllllIlIIIllII.mc.field_1724.method_6118(class_1304.field_6174).method_7909() == class_1802.field_8833 && lllllIlIIIllII.mc.field_1724.method_6128();
    }

    public boolean getLocked() {
        AutoTotem lllllIlIIlIIIl;
        return lllllIlIIlIIIl.locked;
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllIlIlIlIlI) {
        AutoTotem lllllIlIlIlIII;
        if (lllllIlIlIlIII.mc.field_1755 instanceof class_490 && !lllllIlIlIlIII.inventorySwitch.get().booleanValue()) {
            return;
        }
        if (lllllIlIlIlIII.mc.field_1755 != null && !(lllllIlIlIlIII.mc.field_1755 instanceof class_490)) {
            return;
        }
        InvUtils.FindItemResult lllllIlIlIlIIl = InvUtils.findItemWithCount(class_1802.field_8288);
        if (lllllIlIlIlIIl.count <= 0) {
            if (!Modules.get().isActive(Offhand.class) && lllllIlIlIlIII.fallback.get().booleanValue()) {
                Modules.get().get(Offhand.class).toggle();
            }
            Modules.get().get(Offhand.class).setTotems(true);
            lllllIlIlIlIII.locked = false;
        } else {
            Modules.get().get(Offhand.class).setTotems(false);
            if (lllllIlIlIlIII.mc.field_1724.method_6079().method_7909() != class_1802.field_8288 && (!lllllIlIlIlIII.smart.get().booleanValue() || lllllIlIlIlIII.isLow() || lllllIlIlIlIII.elytraMove())) {
                lllllIlIlIlIII.locked = true;
                InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(lllllIlIlIlIIl.slot), 1000);
            } else if (lllllIlIlIlIII.smart.get().booleanValue() && !lllllIlIlIlIII.isLow() && !lllllIlIlIlIII.elytraMove()) {
                lllllIlIlIlIII.locked = false;
            }
        }
        lllllIlIlIlIII.totemCountString = Integer.toString(lllllIlIlIlIIl.count);
    }

    public AutoTotem() {
        super(Categories.Combat, "auto-totem", "Automatically equips totems in your offhand.");
        AutoTotem lllllIlIllIIlI;
        lllllIlIllIIlI.sgGeneral = lllllIlIllIIlI.settings.getDefaultGroup();
        lllllIlIllIIlI.smart = lllllIlIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("smart").description("Only switches to a totem when you are close to death.").defaultValue(false).build());
        lllllIlIllIIlI.fallback = lllllIlIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("fallback").description("Enables Offhand Extra when you run out of totems.").defaultValue(true).build());
        lllllIlIllIIlI.inventorySwitch = lllllIlIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("inventory").description("Whether or not to equip totems while in your inventory.").defaultValue(true).build());
        lllllIlIllIIlI.health = lllllIlIllIIlI.sgGeneral.add(new IntSetting.Builder().name("health").description("The health Auto Totem's smart mode activates at.").defaultValue(10).min(0).sliderMax(20).build());
        lllllIlIllIIlI.elytraHold = lllllIlIllIIlI.sgGeneral.add(new BoolSetting.Builder().name("elytra-hold").description("Whether or not to always hold a totem when flying with an elytra.").defaultValue(false).build());
        lllllIlIllIIlI.totemCountString = "0";
        lllllIlIllIIlI.mc = class_310.method_1551();
        lllllIlIllIIlI.locked = false;
    }
}

