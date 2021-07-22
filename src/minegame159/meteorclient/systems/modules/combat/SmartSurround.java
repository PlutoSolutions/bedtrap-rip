/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1802
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.EntityAddedEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.DamageCalcUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1802;

public class SmartSurround
extends Module {
    private int rPosX;
    private int rPosZ;
    private final Setting<Double> minDamage;
    private class_1297 crystal;
    private final Setting<Boolean> rotate;
    private final SettingGroup sgGeneral;

    public SmartSurround() {
        super(Categories.Combat, "smart-surround", "Attempts to save you from crystals automatically.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.minDamage = this.sgGeneral.add(new DoubleSetting.Builder().name("min-damage").description("The minimum damage before this activates.").defaultValue(5.5).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Forces you to rotate towards the blocks being placed.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        FindItemResult findItemResult = InvUtils.findInHotbar(class_1802.field_8281, class_1802.field_22421);
        if (!findItemResult.found()) {
            return;
        }
        int n = this.mc.field_1724.field_7514.field_7545;
        if (this.rPosX >= 2 && this.rPosZ == 0) {
            this.placeObi(this.crystal, this.rPosX - 1, 0, findItemResult);
        } else if (this.rPosX > 1 && this.rPosZ > 1) {
            this.placeObi(this.crystal, this.rPosX, this.rPosZ - 1, findItemResult);
            this.placeObi(this.crystal, this.rPosX - 1, this.rPosZ, findItemResult);
        } else if (this.rPosX == 0 && this.rPosZ >= 2) {
            this.placeObi(this.crystal, 0, this.rPosZ - 1, findItemResult);
        } else if (this.rPosX < -1 && this.rPosZ < -1) {
            this.placeObi(this.crystal, this.rPosX, this.rPosZ + 1, findItemResult);
            this.placeObi(this.crystal, this.rPosX + 1, this.rPosZ, findItemResult);
        } else if (this.rPosX == 0 && this.rPosZ <= -2) {
            this.placeObi(this.crystal, 0, this.rPosZ + 1, findItemResult);
        } else if (this.rPosX > 1 && this.rPosZ < -1) {
            this.placeObi(this.crystal, this.rPosX, this.rPosZ + 1, findItemResult);
            this.placeObi(this.crystal, this.rPosX - 1, this.rPosZ, findItemResult);
        } else if (this.rPosX <= -2 && this.rPosZ == 0) {
            this.placeObi(this.crystal, this.rPosX + 1, 0, findItemResult);
        } else if (this.rPosX < -1 && this.rPosZ > 1) {
            this.placeObi(this.crystal, this.rPosX, this.rPosZ - 1, findItemResult);
            this.placeObi(this.crystal, this.rPosX + 1, this.rPosZ, findItemResult);
        }
        InvUtils.swap(n);
    }

    private void placeObi(class_1297 class_12972, int n, int n2, FindItemResult findItemResult) {
        BlockUtils.place(class_12972.method_24515().method_10069(n, -1, n2), findItemResult, this.rotate.get(), 100, false, true, false);
    }

    @EventHandler
    private void onSpawn(EntityAddedEvent entityAddedEvent) {
        this.crystal = entityAddedEvent.entity;
        if (entityAddedEvent.entity.method_5864() == class_1299.field_6110 && DamageCalcUtils.crystalDamage((class_1309)this.mc.field_1724, entityAddedEvent.entity.method_19538()) > this.minDamage.get()) {
            this.rPosX = this.mc.field_1724.method_24515().method_10263() - entityAddedEvent.entity.method_24515().method_10263();
            this.rPosZ = this.mc.field_1724.method_24515().method_10260() - entityAddedEvent.entity.method_24515().method_10260();
        }
    }
}

