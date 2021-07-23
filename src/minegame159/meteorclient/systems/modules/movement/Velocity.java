/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.mixin.EntityVelocityUpdateS2CPacketAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2743;

public class Velocity
extends Module {
    public final Setting<Boolean> blocks;
    public final Setting<Double> knockbackVertical;
    public final Setting<Boolean> entityPush;
    public final Setting<Double> explosionsVertical;
    public final Setting<Boolean> explosions;
    public final Setting<Double> explosionsHorizontal;
    public final Setting<Double> knockbackHorizontal;
    public final Setting<Double> liquidsVertical;
    public final Setting<Double> liquidsHorizontal;
    public final Setting<Boolean> liquids;
    public final Setting<Double> entityPushAmount;
    public final Setting<Boolean> knockback;
    private final SettingGroup sgGeneral;

    public double getVertical(Setting<Double> setting) {
        return this.isActive() ? setting.get() : 1.0;
    }

    @EventHandler
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (this.knockback.get().booleanValue() && receive.packet instanceof class_2743 && ((class_2743)receive.packet).method_11818() == this.mc.field_1724.method_5628()) {
            class_2743 class_27432 = (class_2743)receive.packet;
            double d = ((double)class_27432.method_11815() / 8000.0 - this.mc.field_1724.method_18798().field_1352) * this.knockbackHorizontal.get();
            double d2 = ((double)class_27432.method_11816() / 8000.0 - this.mc.field_1724.method_18798().field_1351) * this.knockbackVertical.get();
            double d3 = ((double)class_27432.method_11819() / 8000.0 - this.mc.field_1724.method_18798().field_1350) * this.knockbackHorizontal.get();
            ((EntityVelocityUpdateS2CPacketAccessor)class_27432).setX((int)(d * 8000.0 + this.mc.field_1724.method_18798().field_1352 * 8000.0));
            ((EntityVelocityUpdateS2CPacketAccessor)class_27432).setY((int)(d2 * 8000.0 + this.mc.field_1724.method_18798().field_1351 * 8000.0));
            ((EntityVelocityUpdateS2CPacketAccessor)class_27432).setZ((int)(d3 * 8000.0 + this.mc.field_1724.method_18798().field_1350 * 8000.0));
        }
    }

    public Velocity() {
        super(Categories.Movement, "velocity", "Prevents you from being moved by external forces.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.knockback = this.sgGeneral.add(new BoolSetting.Builder().name("knockback").description("Modifies the amount of knockback you take from attacks.").defaultValue(true).build());
        this.knockbackHorizontal = this.sgGeneral.add(new DoubleSetting.Builder().name("knockback-horizontal").description("How much horizontal knockback you will take.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(this.knockback::get).build());
        this.knockbackVertical = this.sgGeneral.add(new DoubleSetting.Builder().name("knockback-vertical").description("How much vertical knockback you will take.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(this.knockback::get).build());
        this.explosions = this.sgGeneral.add(new BoolSetting.Builder().name("explosions").description("Modifies your knockback from explosions.").defaultValue(true).build());
        this.explosionsHorizontal = this.sgGeneral.add(new DoubleSetting.Builder().name("explosions-horizontal").description("How much velocity you will take from explosions horizontally.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(this.explosions::get).build());
        this.explosionsVertical = this.sgGeneral.add(new DoubleSetting.Builder().name("explosions-vertical").description("How much velocity you will take from explosions vertically.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(this.explosions::get).build());
        this.liquids = this.sgGeneral.add(new BoolSetting.Builder().name("liquids").description("Modifies the amount you are pushed by flowing liquids.").defaultValue(true).build());
        this.liquidsHorizontal = this.sgGeneral.add(new DoubleSetting.Builder().name("liquids-horizontal").description("How much velocity you will take from liquids horizontally.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(this.liquids::get).build());
        this.liquidsVertical = this.sgGeneral.add(new DoubleSetting.Builder().name("liquids-vertical").description("How much velocity you will take from liquids vertically.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(this.liquids::get).build());
        this.entityPush = this.sgGeneral.add(new BoolSetting.Builder().name("entity-push").description("Modifies the amount you are pushed by entities.").defaultValue(true).build());
        this.entityPushAmount = this.sgGeneral.add(new DoubleSetting.Builder().name("entity-push-amount").description("How much you will be pushed.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(this.entityPush::get).build());
        this.blocks = this.sgGeneral.add(new BoolSetting.Builder().name("blocks").description("Prevents you from being pushed out of blocks.").defaultValue(true).build());
    }

    public double getHorizontal(Setting<Double> setting) {
        return this.isActive() ? setting.get() : 1.0;
    }
}

