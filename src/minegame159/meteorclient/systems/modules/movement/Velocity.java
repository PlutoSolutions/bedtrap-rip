/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2743
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
    public final /* synthetic */ Setting<Boolean> blocks;
    public final /* synthetic */ Setting<Double> knockbackVertical;
    public final /* synthetic */ Setting<Boolean> entityPush;
    public final /* synthetic */ Setting<Double> explosionsVertical;
    public final /* synthetic */ Setting<Boolean> explosions;
    public final /* synthetic */ Setting<Double> explosionsHorizontal;
    public final /* synthetic */ Setting<Double> knockbackHorizontal;
    public final /* synthetic */ Setting<Double> liquidsVertical;
    public final /* synthetic */ Setting<Double> liquidsHorizontal;
    public final /* synthetic */ Setting<Boolean> liquids;
    public final /* synthetic */ Setting<Double> entityPushAmount;
    public final /* synthetic */ Setting<Boolean> knockback;
    private final /* synthetic */ SettingGroup sgGeneral;

    public double getVertical(Setting<Double> lllllllllllllllllIIllIIIlIIIIIlI) {
        Velocity lllllllllllllllllIIllIIIlIIIIIll;
        return lllllllllllllllllIIllIIIlIIIIIll.isActive() ? lllllllllllllllllIIllIIIlIIIIIlI.get() : 1.0;
    }

    @EventHandler
    public void onPacketReceive(PacketEvent.Receive lllllllllllllllllIIllIIIlIIlIIlI) {
        Velocity lllllllllllllllllIIllIIIlIIlIIll;
        if (lllllllllllllllllIIllIIIlIIlIIll.knockback.get().booleanValue() && lllllllllllllllllIIllIIIlIIlIIlI.packet instanceof class_2743 && ((class_2743)lllllllllllllllllIIllIIIlIIlIIlI.packet).method_11818() == lllllllllllllllllIIllIIIlIIlIIll.mc.field_1724.method_5628()) {
            class_2743 lllllllllllllllllIIllIIIlIIlIlll = (class_2743)lllllllllllllllllIIllIIIlIIlIIlI.packet;
            double lllllllllllllllllIIllIIIlIIlIllI = ((double)lllllllllllllllllIIllIIIlIIlIlll.method_11815() / 8000.0 - lllllllllllllllllIIllIIIlIIlIIll.mc.field_1724.method_18798().field_1352) * lllllllllllllllllIIllIIIlIIlIIll.knockbackHorizontal.get();
            double lllllllllllllllllIIllIIIlIIlIlIl = ((double)lllllllllllllllllIIllIIIlIIlIlll.method_11816() / 8000.0 - lllllllllllllllllIIllIIIlIIlIIll.mc.field_1724.method_18798().field_1351) * lllllllllllllllllIIllIIIlIIlIIll.knockbackVertical.get();
            double lllllllllllllllllIIllIIIlIIlIlII = ((double)lllllllllllllllllIIllIIIlIIlIlll.method_11819() / 8000.0 - lllllllllllllllllIIllIIIlIIlIIll.mc.field_1724.method_18798().field_1350) * lllllllllllllllllIIllIIIlIIlIIll.knockbackHorizontal.get();
            ((EntityVelocityUpdateS2CPacketAccessor)lllllllllllllllllIIllIIIlIIlIlll).setX((int)(lllllllllllllllllIIllIIIlIIlIllI * 8000.0 + lllllllllllllllllIIllIIIlIIlIIll.mc.field_1724.method_18798().field_1352 * 8000.0));
            ((EntityVelocityUpdateS2CPacketAccessor)lllllllllllllllllIIllIIIlIIlIlll).setY((int)(lllllllllllllllllIIllIIIlIIlIlIl * 8000.0 + lllllllllllllllllIIllIIIlIIlIIll.mc.field_1724.method_18798().field_1351 * 8000.0));
            ((EntityVelocityUpdateS2CPacketAccessor)lllllllllllllllllIIllIIIlIIlIlll).setZ((int)(lllllllllllllllllIIllIIIlIIlIlII * 8000.0 + lllllllllllllllllIIllIIIlIIlIIll.mc.field_1724.method_18798().field_1350 * 8000.0));
        }
    }

    public Velocity() {
        super(Categories.Movement, "velocity", "Prevents you from being moved by external forces.");
        Velocity lllllllllllllllllIIllIIIlIIlllll;
        lllllllllllllllllIIllIIIlIIlllll.sgGeneral = lllllllllllllllllIIllIIIlIIlllll.settings.getDefaultGroup();
        lllllllllllllllllIIllIIIlIIlllll.knockback = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new BoolSetting.Builder().name("knockback").description("Modifies the amount of knockback you take from attacks.").defaultValue(true).build());
        lllllllllllllllllIIllIIIlIIlllll.knockbackHorizontal = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new DoubleSetting.Builder().name("knockback-horizontal").description("How much horizontal knockback you will take.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(lllllllllllllllllIIllIIIlIIlllll.knockback::get).build());
        lllllllllllllllllIIllIIIlIIlllll.knockbackVertical = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new DoubleSetting.Builder().name("knockback-vertical").description("How much vertical knockback you will take.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(lllllllllllllllllIIllIIIlIIlllll.knockback::get).build());
        lllllllllllllllllIIllIIIlIIlllll.explosions = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new BoolSetting.Builder().name("explosions").description("Modifies your knockback from explosions.").defaultValue(true).build());
        lllllllllllllllllIIllIIIlIIlllll.explosionsHorizontal = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new DoubleSetting.Builder().name("explosions-horizontal").description("How much velocity you will take from explosions horizontally.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(lllllllllllllllllIIllIIIlIIlllll.explosions::get).build());
        lllllllllllllllllIIllIIIlIIlllll.explosionsVertical = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new DoubleSetting.Builder().name("explosions-vertical").description("How much velocity you will take from explosions vertically.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(lllllllllllllllllIIllIIIlIIlllll.explosions::get).build());
        lllllllllllllllllIIllIIIlIIlllll.liquids = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new BoolSetting.Builder().name("liquids").description("Modifies the amount you are pushed by flowing liquids.").defaultValue(true).build());
        lllllllllllllllllIIllIIIlIIlllll.liquidsHorizontal = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new DoubleSetting.Builder().name("liquids-horizontal").description("How much velocity you will take from liquids horizontally.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(lllllllllllllllllIIllIIIlIIlllll.liquids::get).build());
        lllllllllllllllllIIllIIIlIIlllll.liquidsVertical = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new DoubleSetting.Builder().name("liquids-vertical").description("How much velocity you will take from liquids vertically.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(lllllllllllllllllIIllIIIlIIlllll.liquids::get).build());
        lllllllllllllllllIIllIIIlIIlllll.entityPush = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new BoolSetting.Builder().name("entity-push").description("Modifies the amount you are pushed by entities.").defaultValue(true).build());
        lllllllllllllllllIIllIIIlIIlllll.entityPushAmount = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new DoubleSetting.Builder().name("entity-push-amount").description("How much you will be pushed.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).visible(lllllllllllllllllIIllIIIlIIlllll.entityPush::get).build());
        lllllllllllllllllIIllIIIlIIlllll.blocks = lllllllllllllllllIIllIIIlIIlllll.sgGeneral.add(new BoolSetting.Builder().name("blocks").description("Prevents you from being pushed out of blocks.").defaultValue(true).build());
    }

    public double getHorizontal(Setting<Double> lllllllllllllllllIIllIIIlIIIIllI) {
        Velocity lllllllllllllllllIIllIIIlIIIlIIl;
        return lllllllllllllllllIIllIIIlIIIlIIl.isActive() ? lllllllllllllllllIIllIIIlIIIIllI.get() : 1.0;
    }
}

