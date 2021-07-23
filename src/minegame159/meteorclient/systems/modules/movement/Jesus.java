/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.movement;

import baritone.api.BaritoneAPI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.CanWalkOnFluidEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.FluidCollisionShapeEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.EntityUtils;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1900;
import net.minecraft.class_1934;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_2596;
import net.minecraft.class_265;
import net.minecraft.class_2828;
import net.minecraft.class_3532;
import net.minecraft.class_3612;
import net.minecraft.class_3614;

public class Jesus
extends Module {
    private int packetTimer;
    private final SettingGroup sgLava;
    private final Setting<Integer> dipIntoWaterHeight;
    private boolean preBaritoneAssumeWalkOnLava;
    private final Setting<Boolean> disableOnSneakForWater;
    private final Setting<Boolean> fireResistanceSafeMode;
    private final Setting<Boolean> dipIntoWaterIfBurning;
    private final Setting<Boolean> dipIntoWater;
    private final Setting<Boolean> dipIntoLavaIfFireResistance;
    private final Setting<Boolean> disableOnSneakForLava;
    private final class_2338.class_2339 blockPos;
    private final Setting<Boolean> walkOnWater;
    private final Setting<Boolean> walkOnLava;
    private final Setting<Integer> dipIntoLavaHeight;
    private int tickTimer;
    private boolean preBaritoneAssumeWalkOnWater;
    private final Setting<Boolean> dipIntoLava;
    private final SettingGroup sgWater;

    @Override
    public void onActivate() {
        this.preBaritoneAssumeWalkOnWater = (Boolean)BaritoneAPI.getSettings().assumeWalkOnWater.value;
        this.preBaritoneAssumeWalkOnLava = (Boolean)BaritoneAPI.getSettings().assumeWalkOnLava.value;
        BaritoneAPI.getSettings().assumeWalkOnWater.value = this.walkOnWater.get();
        BaritoneAPI.getSettings().assumeWalkOnLava.value = this.walkOnLava.get();
    }

    private boolean waterShouldBeSolid() {
        return !(this.walkOnWater.get() == false || this.disableOnSneakForWater.get() != false && this.mc.field_1690.field_1832.method_1434() || this.dipIntoWater.get() != false && this.mc.field_1724.field_6017 > (float)this.dipIntoWaterHeight.get().intValue() || this.dipIntoWaterIfBurning.get() != false && this.mc.field_1724.method_5809() || EntityUtils.getGameMode((class_1657)this.mc.field_1724) == class_1934.field_9219 || this.mc.field_1724.field_7503.field_7479);
    }

    private boolean isOverLiquid() {
        boolean bl = false;
        boolean bl2 = false;
        List list = this.mc.field_1687.method_20812((class_1297)this.mc.field_1724, this.mc.field_1724.method_5829().method_989(0.0, -0.5, 0.0)).map(class_265::method_1107).collect(Collectors.toCollection(ArrayList::new));
        for (class_238 class_2383 : list) {
            this.blockPos.method_10102(class_3532.method_16436((double)0.5, (double)class_2383.field_1323, (double)class_2383.field_1320), class_3532.method_16436((double)0.5, (double)class_2383.field_1322, (double)class_2383.field_1325), class_3532.method_16436((double)0.5, (double)class_2383.field_1321, (double)class_2383.field_1324));
            class_3614 class_36142 = this.mc.field_1687.method_8320((class_2338)this.blockPos).method_26207();
            if (class_36142 == class_3614.field_15920 || class_36142 == class_3614.field_15922) {
                bl = true;
                continue;
            }
            if (class_36142 == class_3614.field_15959) continue;
            bl2 = true;
        }
        return bl && !bl2;
    }

    @Override
    public void onDeactivate() {
        BaritoneAPI.getSettings().assumeWalkOnWater.value = this.preBaritoneAssumeWalkOnWater;
        BaritoneAPI.getSettings().assumeWalkOnLava.value = this.preBaritoneAssumeWalkOnLava;
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send send) {
        if (!(send.packet instanceof class_2828)) {
            return;
        }
        if (this.mc.field_1724.method_5799() && !this.waterShouldBeSolid()) {
            return;
        }
        if (this.mc.field_1724.method_5771() && !this.lavaShouldBeSolid()) {
            return;
        }
        class_2828 class_28282 = (class_2828)send.packet;
        if (!(class_28282 instanceof class_2828.class_2829) && !(class_28282 instanceof class_2828.class_2830)) {
            return;
        }
        if (this.mc.field_1724.method_5799() || this.mc.field_1724.method_5771() || this.mc.field_1724.field_6017 > 3.0f || !this.isOverLiquid()) {
            return;
        }
        if (this.mc.field_1724.field_3913.field_3905 == 0.0f && this.mc.field_1724.field_3913.field_3907 == 0.0f) {
            send.cancel();
            return;
        }
        if (this.packetTimer++ < 4) {
            return;
        }
        this.packetTimer = 0;
        send.cancel();
        double d = class_28282.method_12269(0.0);
        double d2 = class_28282.method_12268(0.0) + 0.05;
        double d3 = class_28282.method_12274(0.0);
        Object object = class_28282 instanceof class_2828.class_2829 ? new class_2828.class_2829(d, d2, d3, true) : new class_2828.class_2830(d, d2, d3, class_28282.method_12271(0.0f), class_28282.method_12270(0.0f), true);
        this.mc.method_1562().method_2872().method_10743((class_2596)object);
    }

    private boolean lavaShouldBeSolid() {
        return !(this.walkOnLava.get() == false || (this.disableOnSneakForLava.get() != false || this.lavaIsSafe()) && this.mc.field_1690.field_1832.method_1434() || this.dipIntoLava.get() != false && this.mc.field_1724.field_6017 > (float)this.dipIntoLavaHeight.get().intValue() || this.lavaIsSafe() && this.mc.field_1724.field_6017 > 3.0f || EntityUtils.getGameMode((class_1657)this.mc.field_1724) == class_1934.field_9219 || this.mc.field_1724.field_7503.field_7479);
    }

    public Jesus() {
        super(Categories.Movement, "jesus", "Walk on liquids like Jesus.");
        this.sgWater = this.settings.createGroup("Water");
        this.sgLava = this.settings.createGroup("Lava");
        this.walkOnWater = this.sgWater.add(new BoolSetting.Builder().name("walk-on-water").description("Lets you walk on water.").defaultValue(true).build());
        this.disableOnSneakForWater = this.sgWater.add(new BoolSetting.Builder().name("disable-on-sneak-for-water").description("Lets you go under the water when your sneak key is held.").defaultValue(true).build());
        this.dipIntoWater = this.sgWater.add(new BoolSetting.Builder().name("dip-into-water").description("Lets you go under the water when you fall over a certain height.").defaultValue(true).build());
        this.dipIntoWaterHeight = this.sgWater.add(new IntSetting.Builder().name("dip-into-water-height").description("Maximum safe height.").defaultValue(4).min(1).max(255).sliderMin(3).sliderMax(21).build());
        this.dipIntoWaterIfBurning = this.sgWater.add(new BoolSetting.Builder().name("dip-into-water-if-burning").description("Lets you go under the water when you are burning.").defaultValue(true).build());
        this.walkOnLava = this.sgLava.add(new BoolSetting.Builder().name("walk-on-lava").description("Lets you walk on lava.").defaultValue(true).build());
        this.disableOnSneakForLava = this.sgLava.add(new BoolSetting.Builder().name("disable-on-sneak-for-lava").description("Lets you go under the lava when your sneak key is held.").defaultValue(false).build());
        this.dipIntoLava = this.sgLava.add(new BoolSetting.Builder().name("dip-into-lava").description("Lets you go under the lava when you fall over than certain height.").defaultValue(false).build());
        this.dipIntoLavaHeight = this.sgLava.add(new IntSetting.Builder().name("dip-into-lava-height").description("Maximum safe height.").defaultValue(15).min(1).max(255).sliderMin(3).sliderMax(21).build());
        this.dipIntoLavaIfFireResistance = this.sgLava.add(new BoolSetting.Builder().name("dip-if-fire-resistance").description("Lets you go under the lava if you have Fire Resistance effect.").defaultValue(true).build());
        this.fireResistanceSafeMode = this.sgLava.add(new BoolSetting.Builder().name("fire-resistance-safe-mode").description("Prevents being in lava when the Fire Resistance effect is nearly over.").defaultValue(true).build());
        this.blockPos = new class_2338.class_2339();
        this.tickTimer = 10;
        this.packetTimer = 0;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.mc.field_1724.method_5799() && !this.waterShouldBeSolid()) {
            return;
        }
        if (this.mc.field_1724.method_5771() && !this.lavaShouldBeSolid()) {
            return;
        }
        if (this.mc.field_1724.method_5799() || this.mc.field_1724.method_5771()) {
            class_243 class_2432 = this.mc.field_1724.method_18798();
            ((IVec3d)class_2432).set(class_2432.field_1352, 0.11, class_2432.field_1350);
            this.tickTimer = 0;
            return;
        }
        class_243 class_2433 = this.mc.field_1724.method_18798();
        if (this.tickTimer == 0) {
            ((IVec3d)class_2433).set(class_2433.field_1352, 0.3, class_2433.field_1350);
        } else if (this.tickTimer == 1) {
            ((IVec3d)class_2433).set(class_2433.field_1352, 0.0, class_2433.field_1350);
        }
        ++this.tickTimer;
    }

    private boolean lavaIsSafe() {
        if (!this.dipIntoLavaIfFireResistance.get().booleanValue()) {
            return false;
        }
        return this.mc.field_1724.method_6059(class_1294.field_5918) && (this.fireResistanceSafeMode.get() == false || this.mc.field_1724.method_6112(class_1294.field_5918).method_5584() > class_1900.method_8238((class_1309)this.mc.field_1724, (int)300));
    }

    @EventHandler
    private void onFluidCollisionShape(FluidCollisionShapeEvent fluidCollisionShapeEvent) {
        if (fluidCollisionShapeEvent.state.method_26207() == class_3614.field_15920 && !this.mc.field_1724.method_5799() && this.waterShouldBeSolid()) {
            fluidCollisionShapeEvent.shape = class_259.method_1077();
        } else if (fluidCollisionShapeEvent.state.method_26207() == class_3614.field_15922 && !this.mc.field_1724.method_5771() && this.lavaShouldBeSolid()) {
            fluidCollisionShapeEvent.shape = class_259.method_1077();
        }
    }

    @EventHandler
    private void onCanWalkOnFluid(CanWalkOnFluidEvent canWalkOnFluidEvent) {
        if (canWalkOnFluidEvent.entity != this.mc.field_1724) {
            return;
        }
        if ((canWalkOnFluidEvent.fluid == class_3612.field_15910 || canWalkOnFluidEvent.fluid == class_3612.field_15909) && this.waterShouldBeSolid()) {
            canWalkOnFluidEvent.walkOnFluid = true;
        } else if ((canWalkOnFluidEvent.fluid == class_3612.field_15908 || canWalkOnFluidEvent.fluid == class_3612.field_15907) && this.lavaShouldBeSolid()) {
            canWalkOnFluidEvent.walkOnFluid = true;
        }
    }
}

