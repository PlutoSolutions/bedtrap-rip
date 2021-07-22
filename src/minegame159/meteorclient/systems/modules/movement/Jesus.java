/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1900
 *  net.minecraft.class_1934
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_259
 *  net.minecraft.class_2596
 *  net.minecraft.class_265
 *  net.minecraft.class_2828
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_3532
 *  net.minecraft.class_3612
 *  net.minecraft.class_3614
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
    private /* synthetic */ int packetTimer;
    private final /* synthetic */ SettingGroup sgLava;
    private final /* synthetic */ Setting<Integer> dipIntoWaterHeight;
    private /* synthetic */ boolean preBaritoneAssumeWalkOnLava;
    private final /* synthetic */ Setting<Boolean> disableOnSneakForWater;
    private final /* synthetic */ Setting<Boolean> fireResistanceSafeMode;
    private final /* synthetic */ Setting<Boolean> dipIntoWaterIfBurning;
    private final /* synthetic */ Setting<Boolean> dipIntoWater;
    private final /* synthetic */ Setting<Boolean> dipIntoLavaIfFireResistance;
    private final /* synthetic */ Setting<Boolean> disableOnSneakForLava;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ Setting<Boolean> walkOnWater;
    private final /* synthetic */ Setting<Boolean> walkOnLava;
    private final /* synthetic */ Setting<Integer> dipIntoLavaHeight;
    private /* synthetic */ int tickTimer;
    private /* synthetic */ boolean preBaritoneAssumeWalkOnWater;
    private final /* synthetic */ Setting<Boolean> dipIntoLava;
    private final /* synthetic */ SettingGroup sgWater;

    @Override
    public void onActivate() {
        Jesus lIIlIIIlIlIIlI;
        lIIlIIIlIlIIlI.preBaritoneAssumeWalkOnWater = (Boolean)BaritoneAPI.getSettings().assumeWalkOnWater.value;
        lIIlIIIlIlIIlI.preBaritoneAssumeWalkOnLava = (Boolean)BaritoneAPI.getSettings().assumeWalkOnLava.value;
        BaritoneAPI.getSettings().assumeWalkOnWater.value = lIIlIIIlIlIIlI.walkOnWater.get();
        BaritoneAPI.getSettings().assumeWalkOnLava.value = lIIlIIIlIlIIlI.walkOnLava.get();
    }

    private boolean waterShouldBeSolid() {
        Jesus lIIlIIIIlIIIlI;
        return !(lIIlIIIIlIIIlI.walkOnWater.get() == false || lIIlIIIIlIIIlI.disableOnSneakForWater.get() != false && lIIlIIIIlIIIlI.mc.field_1690.field_1832.method_1434() || lIIlIIIIlIIIlI.dipIntoWater.get() != false && lIIlIIIIlIIIlI.mc.field_1724.field_6017 > (float)lIIlIIIIlIIIlI.dipIntoWaterHeight.get().intValue() || lIIlIIIIlIIIlI.dipIntoWaterIfBurning.get() != false && lIIlIIIIlIIIlI.mc.field_1724.method_5809() || EntityUtils.getGameMode((class_1657)lIIlIIIIlIIIlI.mc.field_1724) == class_1934.field_9219 || lIIlIIIIlIIIlI.mc.field_1724.field_7503.field_7479);
    }

    private boolean isOverLiquid() {
        Jesus lIIlIIIIIlIIIl;
        boolean lIIlIIIIIlIIII = false;
        boolean lIIlIIIIIIllll = false;
        List lIIlIIIIIIlllI = lIIlIIIIIlIIIl.mc.field_1687.method_20812((class_1297)lIIlIIIIIlIIIl.mc.field_1724, lIIlIIIIIlIIIl.mc.field_1724.method_5829().method_989(0.0, -0.5, 0.0)).map(class_265::method_1107).collect(Collectors.toCollection(ArrayList::new));
        for (class_238 lIIlIIIIIlIIlI : lIIlIIIIIIlllI) {
            lIIlIIIIIlIIIl.blockPos.method_10102(class_3532.method_16436((double)0.5, (double)lIIlIIIIIlIIlI.field_1323, (double)lIIlIIIIIlIIlI.field_1320), class_3532.method_16436((double)0.5, (double)lIIlIIIIIlIIlI.field_1322, (double)lIIlIIIIIlIIlI.field_1325), class_3532.method_16436((double)0.5, (double)lIIlIIIIIlIIlI.field_1321, (double)lIIlIIIIIlIIlI.field_1324));
            class_3614 lIIlIIIIIlIIll = lIIlIIIIIlIIIl.mc.field_1687.method_8320((class_2338)lIIlIIIIIlIIIl.blockPos).method_26207();
            if (lIIlIIIIIlIIll == class_3614.field_15920 || lIIlIIIIIlIIll == class_3614.field_15922) {
                lIIlIIIIIlIIII = true;
                continue;
            }
            if (lIIlIIIIIlIIll == class_3614.field_15959) continue;
            lIIlIIIIIIllll = true;
        }
        return lIIlIIIIIlIIII && !lIIlIIIIIIllll;
    }

    @Override
    public void onDeactivate() {
        Jesus lIIlIIIlIIlllI;
        BaritoneAPI.getSettings().assumeWalkOnWater.value = lIIlIIIlIIlllI.preBaritoneAssumeWalkOnWater;
        BaritoneAPI.getSettings().assumeWalkOnLava.value = lIIlIIIlIIlllI.preBaritoneAssumeWalkOnLava;
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send lIIlIIIIllIIII) {
        class_2828.class_2830 lIIlIIIIlIlIll;
        Jesus lIIlIIIIlIlIlI;
        if (!(lIIlIIIIllIIII.packet instanceof class_2828)) {
            return;
        }
        if (lIIlIIIIlIlIlI.mc.field_1724.method_5799() && !lIIlIIIIlIlIlI.waterShouldBeSolid()) {
            return;
        }
        if (lIIlIIIIlIlIlI.mc.field_1724.method_5771() && !lIIlIIIIlIlIlI.lavaShouldBeSolid()) {
            return;
        }
        class_2828 lIIlIIIIlIllll = (class_2828)lIIlIIIIllIIII.packet;
        if (!(lIIlIIIIlIllll instanceof class_2828.class_2829) && !(lIIlIIIIlIllll instanceof class_2828.class_2830)) {
            return;
        }
        if (lIIlIIIIlIlIlI.mc.field_1724.method_5799() || lIIlIIIIlIlIlI.mc.field_1724.method_5771() || lIIlIIIIlIlIlI.mc.field_1724.field_6017 > 3.0f || !lIIlIIIIlIlIlI.isOverLiquid()) {
            return;
        }
        if (lIIlIIIIlIlIlI.mc.field_1724.field_3913.field_3905 == 0.0f && lIIlIIIIlIlIlI.mc.field_1724.field_3913.field_3907 == 0.0f) {
            lIIlIIIIllIIII.cancel();
            return;
        }
        if (lIIlIIIIlIlIlI.packetTimer++ < 4) {
            return;
        }
        lIIlIIIIlIlIlI.packetTimer = 0;
        lIIlIIIIllIIII.cancel();
        double lIIlIIIIlIlllI = lIIlIIIIlIllll.method_12269(0.0);
        double lIIlIIIIlIllIl = lIIlIIIIlIllll.method_12268(0.0) + 0.05;
        double lIIlIIIIlIllII = lIIlIIIIlIllll.method_12274(0.0);
        if (lIIlIIIIlIllll instanceof class_2828.class_2829) {
            class_2828.class_2829 lIIlIIIIllIIlI = new class_2828.class_2829(lIIlIIIIlIlllI, lIIlIIIIlIllIl, lIIlIIIIlIllII, true);
        } else {
            lIIlIIIIlIlIll = new class_2828.class_2830(lIIlIIIIlIlllI, lIIlIIIIlIllIl, lIIlIIIIlIllII, lIIlIIIIlIllll.method_12271(0.0f), lIIlIIIIlIllll.method_12270(0.0f), true);
        }
        lIIlIIIIlIlIlI.mc.method_1562().method_2872().method_10743((class_2596)lIIlIIIIlIlIll);
    }

    private boolean lavaShouldBeSolid() {
        Jesus lIIlIIIIIllIll;
        return !(lIIlIIIIIllIll.walkOnLava.get() == false || (lIIlIIIIIllIll.disableOnSneakForLava.get() != false || lIIlIIIIIllIll.lavaIsSafe()) && lIIlIIIIIllIll.mc.field_1690.field_1832.method_1434() || lIIlIIIIIllIll.dipIntoLava.get() != false && lIIlIIIIIllIll.mc.field_1724.field_6017 > (float)lIIlIIIIIllIll.dipIntoLavaHeight.get().intValue() || lIIlIIIIIllIll.lavaIsSafe() && lIIlIIIIIllIll.mc.field_1724.field_6017 > 3.0f || EntityUtils.getGameMode((class_1657)lIIlIIIIIllIll.mc.field_1724) == class_1934.field_9219 || lIIlIIIIIllIll.mc.field_1724.field_7503.field_7479);
    }

    public Jesus() {
        super(Categories.Movement, "jesus", "Walk on liquids like Jesus.");
        Jesus lIIlIIIlIlIlII;
        lIIlIIIlIlIlII.sgWater = lIIlIIIlIlIlII.settings.createGroup("Water");
        lIIlIIIlIlIlII.sgLava = lIIlIIIlIlIlII.settings.createGroup("Lava");
        lIIlIIIlIlIlII.walkOnWater = lIIlIIIlIlIlII.sgWater.add(new BoolSetting.Builder().name("walk-on-water").description("Lets you walk on water.").defaultValue(true).build());
        lIIlIIIlIlIlII.disableOnSneakForWater = lIIlIIIlIlIlII.sgWater.add(new BoolSetting.Builder().name("disable-on-sneak-for-water").description("Lets you go under the water when your sneak key is held.").defaultValue(true).build());
        lIIlIIIlIlIlII.dipIntoWater = lIIlIIIlIlIlII.sgWater.add(new BoolSetting.Builder().name("dip-into-water").description("Lets you go under the water when you fall over a certain height.").defaultValue(true).build());
        lIIlIIIlIlIlII.dipIntoWaterHeight = lIIlIIIlIlIlII.sgWater.add(new IntSetting.Builder().name("dip-into-water-height").description("Maximum safe height.").defaultValue(4).min(1).max(255).sliderMin(3).sliderMax(21).build());
        lIIlIIIlIlIlII.dipIntoWaterIfBurning = lIIlIIIlIlIlII.sgWater.add(new BoolSetting.Builder().name("dip-into-water-if-burning").description("Lets you go under the water when you are burning.").defaultValue(true).build());
        lIIlIIIlIlIlII.walkOnLava = lIIlIIIlIlIlII.sgLava.add(new BoolSetting.Builder().name("walk-on-lava").description("Lets you walk on lava.").defaultValue(true).build());
        lIIlIIIlIlIlII.disableOnSneakForLava = lIIlIIIlIlIlII.sgLava.add(new BoolSetting.Builder().name("disable-on-sneak-for-lava").description("Lets you go under the lava when your sneak key is held.").defaultValue(false).build());
        lIIlIIIlIlIlII.dipIntoLava = lIIlIIIlIlIlII.sgLava.add(new BoolSetting.Builder().name("dip-into-lava").description("Lets you go under the lava when you fall over than certain height.").defaultValue(false).build());
        lIIlIIIlIlIlII.dipIntoLavaHeight = lIIlIIIlIlIlII.sgLava.add(new IntSetting.Builder().name("dip-into-lava-height").description("Maximum safe height.").defaultValue(15).min(1).max(255).sliderMin(3).sliderMax(21).build());
        lIIlIIIlIlIlII.dipIntoLavaIfFireResistance = lIIlIIIlIlIlII.sgLava.add(new BoolSetting.Builder().name("dip-if-fire-resistance").description("Lets you go under the lava if you have Fire Resistance effect.").defaultValue(true).build());
        lIIlIIIlIlIlII.fireResistanceSafeMode = lIIlIIIlIlIlII.sgLava.add(new BoolSetting.Builder().name("fire-resistance-safe-mode").description("Prevents being in lava when the Fire Resistance effect is nearly over.").defaultValue(true).build());
        lIIlIIIlIlIlII.blockPos = new class_2338.class_2339();
        lIIlIIIlIlIlII.tickTimer = 10;
        lIIlIIIlIlIlII.packetTimer = 0;
    }

    @EventHandler
    private void onTick(TickEvent.Post lIIlIIIlIIlIIl) {
        Jesus lIIlIIIlIIIlll;
        if (lIIlIIIlIIIlll.mc.field_1724.method_5799() && !lIIlIIIlIIIlll.waterShouldBeSolid()) {
            return;
        }
        if (lIIlIIIlIIIlll.mc.field_1724.method_5771() && !lIIlIIIlIIIlll.lavaShouldBeSolid()) {
            return;
        }
        if (lIIlIIIlIIIlll.mc.field_1724.method_5799() || lIIlIIIlIIIlll.mc.field_1724.method_5771()) {
            class_243 lIIlIIIlIIlIll = lIIlIIIlIIIlll.mc.field_1724.method_18798();
            ((IVec3d)lIIlIIIlIIlIll).set(lIIlIIIlIIlIll.field_1352, 0.11, lIIlIIIlIIlIll.field_1350);
            lIIlIIIlIIIlll.tickTimer = 0;
            return;
        }
        class_243 lIIlIIIlIIlIII = lIIlIIIlIIIlll.mc.field_1724.method_18798();
        if (lIIlIIIlIIIlll.tickTimer == 0) {
            ((IVec3d)lIIlIIIlIIlIII).set(lIIlIIIlIIlIII.field_1352, 0.3, lIIlIIIlIIlIII.field_1350);
        } else if (lIIlIIIlIIIlll.tickTimer == 1) {
            ((IVec3d)lIIlIIIlIIlIII).set(lIIlIIIlIIlIII.field_1352, 0.0, lIIlIIIlIIlIII.field_1350);
        }
        ++lIIlIIIlIIIlll.tickTimer;
    }

    private boolean lavaIsSafe() {
        Jesus lIIlIIIIIlllll;
        if (!lIIlIIIIIlllll.dipIntoLavaIfFireResistance.get().booleanValue()) {
            return false;
        }
        return lIIlIIIIIlllll.mc.field_1724.method_6059(class_1294.field_5918) && (lIIlIIIIIlllll.fireResistanceSafeMode.get() == false || lIIlIIIIIlllll.mc.field_1724.method_6112(class_1294.field_5918).method_5584() > class_1900.method_8238((class_1309)lIIlIIIIIlllll.mc.field_1724, (int)300));
    }

    @EventHandler
    private void onFluidCollisionShape(FluidCollisionShapeEvent lIIlIIIIlllIlI) {
        Jesus lIIlIIIIlllIll;
        if (lIIlIIIIlllIlI.state.method_26207() == class_3614.field_15920 && !lIIlIIIIlllIll.mc.field_1724.method_5799() && lIIlIIIIlllIll.waterShouldBeSolid()) {
            lIIlIIIIlllIlI.shape = class_259.method_1077();
        } else if (lIIlIIIIlllIlI.state.method_26207() == class_3614.field_15922 && !lIIlIIIIlllIll.mc.field_1724.method_5771() && lIIlIIIIlllIll.lavaShouldBeSolid()) {
            lIIlIIIIlllIlI.shape = class_259.method_1077();
        }
    }

    @EventHandler
    private void onCanWalkOnFluid(CanWalkOnFluidEvent lIIlIIIlIIIIII) {
        Jesus lIIlIIIlIIIIIl;
        if (lIIlIIIlIIIIII.entity != lIIlIIIlIIIIIl.mc.field_1724) {
            return;
        }
        if ((lIIlIIIlIIIIII.fluid == class_3612.field_15910 || lIIlIIIlIIIIII.fluid == class_3612.field_15909) && lIIlIIIlIIIIIl.waterShouldBeSolid()) {
            lIIlIIIlIIIIII.walkOnFluid = true;
        } else if ((lIIlIIIlIIIIII.fluid == class_3612.field_15908 || lIIlIIIlIIIIII.fluid == class_3612.field_15907) && lIIlIIIlIIIIIl.lavaShouldBeSolid()) {
            lIIlIIIlIIIIII.walkOnFluid = true;
        }
    }
}

