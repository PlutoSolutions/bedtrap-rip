/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_2846;

public class AutoCity
extends Module {
    private final Setting<Boolean> selfToggle;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> rotate;
    private class_2338 blockPosTarget;
    private boolean sentMessage;
    private final Setting<Double> targetRange;
    private class_1657 target;
    private final Setting<Boolean> support;

    private void lambda$onTick$1() {
        this.mine(this.blockPosTarget);
    }

    private static boolean lambda$onTick$0(class_1799 class_17992) {
        return class_17992.method_7909() == class_1802.field_8377 || class_17992.method_7909() == class_1802.field_22024;
    }

    public AutoCity() {
        super(Categories.Combat, "auto-city", "Automatically cities a target by mining the nearest obsidian next to them.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.targetRange = this.sgGeneral.add(new DoubleSetting.Builder().name("target-range").description("The radius in which players get targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        this.support = this.sgGeneral.add(new BoolSetting.Builder().name("support").description("If there is no block below a city block it will place one before mining.").defaultValue(true).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically rotates you towards the city block.").defaultValue(true).build());
        this.selfToggle = this.sgGeneral.add(new BoolSetting.Builder().name("self-toggle").description("Automatically toggles off after activation.").defaultValue(true).build());
    }

    private void mine(class_2338 class_23382) {
        this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, class_23382, class_2350.field_11036));
        this.mc.field_1724.method_6104(class_1268.field_5808);
        this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, class_23382, class_2350.field_11036));
    }

    @Override
    public String getInfoString() {
        if (this.target != null) {
            return this.target.method_5820();
        }
        return null;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        Object object;
        if (TargetUtils.isBadTarget(this.target, this.targetRange.get())) {
            object = TargetUtils.getPlayerTarget(this.targetRange.get(), SortPriority.LowestDistance);
            if (object != this.target) {
                this.sentMessage = false;
            }
            this.target = object;
        }
        if (TargetUtils.isBadTarget(this.target, this.targetRange.get())) {
            this.target = null;
            this.blockPosTarget = null;
            if (this.selfToggle.get().booleanValue()) {
                this.toggle();
            }
            return;
        }
        this.blockPosTarget = EntityUtils.getCityBlock(this.target);
        if (this.blockPosTarget == null) {
            if (this.selfToggle.get().booleanValue()) {
                this.error("No target block found... disabling.", new Object[0]);
                this.toggle();
            }
            this.target = null;
            return;
        }
        if (PlayerUtils.distanceTo(this.blockPosTarget) > (double)this.mc.field_1761.method_2904() && this.selfToggle.get().booleanValue()) {
            this.error("Target block out of reach... disabling.", new Object[0]);
            this.toggle();
            return;
        }
        if (!this.sentMessage) {
            this.info("Attempting to city %s.", this.target.method_5820());
            this.sentMessage = true;
        }
        if (!((FindItemResult)(object = InvUtils.find(AutoCity::lambda$onTick$0))).isHotbar()) {
            if (this.selfToggle.get().booleanValue()) {
                this.error("No pickaxe found... disabling.", new Object[0]);
                this.toggle();
            }
            return;
        }
        if (this.support.get().booleanValue()) {
            BlockUtils.place(this.blockPosTarget.method_10087(1), InvUtils.findInHotbar(class_1802.field_8281), this.rotate.get(), 0, true);
        }
        InvUtils.swap(((FindItemResult)object).getSlot());
        if (this.rotate.get().booleanValue()) {
            Rotations.rotate(Rotations.getYaw(this.blockPosTarget), Rotations.getPitch(this.blockPosTarget), this::lambda$onTick$1);
        } else {
            this.mine(this.blockPosTarget);
        }
        if (this.selfToggle.get().booleanValue()) {
            this.toggle();
        }
    }
}

