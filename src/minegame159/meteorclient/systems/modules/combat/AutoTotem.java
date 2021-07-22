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
import net.minecraft.class_2587;
import net.minecraft.class_310;
import net.minecraft.class_490;

public class AutoTotem
extends Module {
    private String totemCountString;
    private final class_310 mc;
    private boolean locked;
    private final Setting<Boolean> elytraHold;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> smart;
    static final boolean $assertionsDisabled = !AutoTotem.class.desiredAssertionStatus();
    private final Setting<Integer> health;
    private final Setting<Boolean> fallback;
    private final Setting<Boolean> inventorySwitch;

    @Override
    public void onDeactivate() {
        this.locked = false;
    }

    private double getHealthReduction() {
        double d;
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        double d2 = 0.0;
        if (this.mc.field_1724.field_7503.field_7477) {
            return d2;
        }
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            if (class_12972 instanceof class_1511 && d2 < DamageCalcUtils.crystalDamage((class_1309)this.mc.field_1724, class_12972.method_19538())) {
                d2 = DamageCalcUtils.crystalDamage((class_1309)this.mc.field_1724, class_12972.method_19538());
                continue;
            }
            if (!(class_12972 instanceof class_1657) || !(d2 < DamageCalcUtils.getSwordDamage((class_1657)class_12972, true)) || !Friends.get().shouldAttack((class_1657)class_12972) || !(this.mc.field_1724.method_19538().method_1022(class_12972.method_19538()) < 5.0) || !(((class_1657)class_12972).method_6030().method_7909() instanceof class_1829)) continue;
            d2 = DamageCalcUtils.getSwordDamage((class_1657)class_12972, true);
        }
        if (!Modules.get().isActive(NoFall.class) && this.mc.field_1724.field_6017 > 3.0f && (d = (double)this.mc.field_1724.field_6017 * 0.5) > d2) {
            d2 = d;
        }
        if (Utils.getDimension() != Dimension.Nether) {
            for (class_1297 class_12972 : this.mc.field_1687.field_9231) {
                if (!(class_12972 instanceof class_2587)) continue;
                class_243 class_2432 = new class_243((double)class_12972.method_11016().method_10263(), (double)class_12972.method_11016().method_10264(), (double)class_12972.method_11016().method_10260());
                if (!(d2 < DamageCalcUtils.bedDamage((class_1309)this.mc.field_1724, class_2432))) continue;
                d2 = DamageCalcUtils.bedDamage((class_1309)this.mc.field_1724, new class_243((double)class_12972.method_11016().method_10263(), (double)class_12972.method_11016().method_10264(), (double)class_12972.method_11016().method_10260()));
            }
        }
        return d2;
    }

    private boolean isLow() {
        return this.getHealth() < (double)this.health.get().intValue() || this.getHealth() - this.getHealthReduction() < (double)this.health.get().intValue();
    }

    private double getHealth() {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        return this.mc.field_1724.method_6032() + this.mc.field_1724.method_6067();
    }

    @Override
    public String getInfoString() {
        return this.totemCountString;
    }

    private boolean elytraMove() {
        return this.elytraHold.get() != false && this.mc.field_1724.method_6118(class_1304.field_6174).method_7909() == class_1802.field_8833 && this.mc.field_1724.method_6128();
    }

    public boolean getLocked() {
        return this.locked;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.mc.field_1755 instanceof class_490 && !this.inventorySwitch.get().booleanValue()) {
            return;
        }
        if (this.mc.field_1755 != null && !(this.mc.field_1755 instanceof class_490)) {
            return;
        }
        InvUtils.FindItemResult findItemResult = InvUtils.findItemWithCount(class_1802.field_8288);
        if (findItemResult.count <= 0) {
            if (!Modules.get().isActive(Offhand.class) && this.fallback.get().booleanValue()) {
                Modules.get().get(Offhand.class).toggle();
            }
            Modules.get().get(Offhand.class).setTotems(true);
            this.locked = false;
        } else {
            Modules.get().get(Offhand.class).setTotems(false);
            if (this.mc.field_1724.method_6079().method_7909() != class_1802.field_8288 && (!this.smart.get().booleanValue() || this.isLow() || this.elytraMove())) {
                this.locked = true;
                InvUtils.addSlots(1, 45, InvUtils.invIndexToSlotId(findItemResult.slot), 1000);
            } else if (this.smart.get().booleanValue() && !this.isLow() && !this.elytraMove()) {
                this.locked = false;
            }
        }
        this.totemCountString = Integer.toString(findItemResult.count);
    }

    public AutoTotem() {
        super(Categories.Combat, "auto-totem", "Automatically equips totems in your offhand.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.smart = this.sgGeneral.add(new BoolSetting.Builder().name("smart").description("Only switches to a totem when you are close to death.").defaultValue(false).build());
        this.fallback = this.sgGeneral.add(new BoolSetting.Builder().name("fallback").description("Enables Offhand Extra when you run out of totems.").defaultValue(true).build());
        this.inventorySwitch = this.sgGeneral.add(new BoolSetting.Builder().name("inventory").description("Whether or not to equip totems while in your inventory.").defaultValue(true).build());
        this.health = this.sgGeneral.add(new IntSetting.Builder().name("health").description("The health Auto Totem's smart mode activates at.").defaultValue(10).min(0).sliderMax(20).build());
        this.elytraHold = this.sgGeneral.add(new BoolSetting.Builder().name("elytra-hold").description("Whether or not to always hold a totem when flying with an elytra.").defaultValue(false).build());
        this.totemCountString = "0";
        this.mc = class_310.method_1551();
        this.locked = false;
    }
}

