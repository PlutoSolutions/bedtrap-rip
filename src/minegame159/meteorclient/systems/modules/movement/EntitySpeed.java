/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.LivingEntityMoveEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.PlayerUtils;
import net.minecraft.class_1309;
import net.minecraft.class_243;

public class EntitySpeed
extends Module {
    private final /* synthetic */ Setting<Boolean> onlyOnGround;
    private final /* synthetic */ Setting<Boolean> inWater;
    private final /* synthetic */ Setting<Double> speed;
    private final /* synthetic */ SettingGroup sgGeneral;

    public EntitySpeed() {
        super(Categories.Movement, "entity-speed", "Makes you go faster when riding entities.");
        EntitySpeed llIIIlIIlIllII;
        llIIIlIIlIllII.sgGeneral = llIIIlIIlIllII.settings.getDefaultGroup();
        llIIIlIIlIllII.speed = llIIIlIIlIllII.sgGeneral.add(new DoubleSetting.Builder().name("speed").description("Horizontal speed in blocks per second.").defaultValue(10.0).min(0.0).sliderMax(50.0).build());
        llIIIlIIlIllII.onlyOnGround = llIIIlIIlIllII.sgGeneral.add(new BoolSetting.Builder().name("only-on-ground").description("Use speed only when standing on a block.").defaultValue(false).build());
        llIIIlIIlIllII.inWater = llIIIlIIlIllII.sgGeneral.add(new BoolSetting.Builder().name("in-water").description("Use speed when in water.").defaultValue(false).build());
    }

    @EventHandler
    private void onLivingEntityMove(LivingEntityMoveEvent llIIIlIIlIIllI) {
        EntitySpeed llIIIlIIlIIlll;
        if (llIIIlIIlIIllI.entity.method_5642() != llIIIlIIlIIlll.mc.field_1724) {
            return;
        }
        class_1309 llIIIlIIlIIlIl = llIIIlIIlIIllI.entity;
        if (llIIIlIIlIIlll.onlyOnGround.get().booleanValue() && !llIIIlIIlIIlIl.method_24828()) {
            return;
        }
        if (!llIIIlIIlIIlll.inWater.get().booleanValue() && llIIIlIIlIIlIl.method_5799()) {
            return;
        }
        class_243 llIIIlIIlIIlII = PlayerUtils.getHorizontalVelocity(llIIIlIIlIIlll.speed.get());
        ((IVec3d)llIIIlIIlIIllI.movement).setXZ(llIIIlIIlIIlII.field_1352, llIIIlIIlIIlII.field_1350);
    }
}

