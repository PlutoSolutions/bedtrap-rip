/*
 * Decompiled with CFR 0.151.
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
    private final Setting<Boolean> onlyOnGround;
    private final Setting<Boolean> inWater;
    private final Setting<Double> speed;
    private final SettingGroup sgGeneral;

    public EntitySpeed() {
        super(Categories.Movement, "entity-speed", "Makes you go faster when riding entities.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.speed = this.sgGeneral.add(new DoubleSetting.Builder().name("speed").description("Horizontal speed in blocks per second.").defaultValue(10.0).min(0.0).sliderMax(50.0).build());
        this.onlyOnGround = this.sgGeneral.add(new BoolSetting.Builder().name("only-on-ground").description("Use speed only when standing on a block.").defaultValue(false).build());
        this.inWater = this.sgGeneral.add(new BoolSetting.Builder().name("in-water").description("Use speed when in water.").defaultValue(false).build());
    }

    @EventHandler
    private void onLivingEntityMove(LivingEntityMoveEvent livingEntityMoveEvent) {
        if (livingEntityMoveEvent.entity.method_5642() != this.mc.field_1724) {
            return;
        }
        class_1309 class_13092 = livingEntityMoveEvent.entity;
        if (this.onlyOnGround.get().booleanValue() && !class_13092.method_24828()) {
            return;
        }
        if (!this.inWater.get().booleanValue() && class_13092.method_5799()) {
            return;
        }
        class_243 class_2432 = PlayerUtils.getHorizontalVelocity(this.speed.get());
        ((IVec3d)livingEntityMoveEvent.movement).setXZ(class_2432.field_1352, class_2432.field_1350);
    }
}

