/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1452
 *  net.minecraft.class_1495
 *  net.minecraft.class_1498
 *  net.minecraft.class_1500
 *  net.minecraft.class_1501
 *  net.minecraft.class_1506
 *  net.minecraft.class_1657
 *  net.minecraft.class_1690
 *  net.minecraft.class_1695
 *  net.minecraft.class_1826
 */
package minegame159.meteorclient.systems.modules.world;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1452;
import net.minecraft.class_1495;
import net.minecraft.class_1498;
import net.minecraft.class_1500;
import net.minecraft.class_1501;
import net.minecraft.class_1506;
import net.minecraft.class_1657;
import net.minecraft.class_1690;
import net.minecraft.class_1695;
import net.minecraft.class_1826;

public class AutoMount
extends Module {
    private final Setting<Boolean> horses;
    private final SettingGroup sgMount;
    private final Setting<Boolean> donkeys;
    private final Setting<Boolean> skeletonHorse;
    private final Setting<Boolean> llamas;
    private final Setting<Boolean> checkSaddle;
    private final Setting<Boolean> pigs;
    private final Setting<Boolean> mules;
    private final Setting<Boolean> rotate;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> minecarts;
    private final Setting<Boolean> boats;

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.mc.field_1724.method_5765()) {
            return;
        }
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            if (this.mc.field_1724.method_5739(class_12972) > 4.0f) continue;
            if (this.mc.field_1724.method_6047().method_7909() instanceof class_1826) {
                return;
            }
            if (this.donkeys.get().booleanValue() && class_12972 instanceof class_1495 && (!this.checkSaddle.get().booleanValue() || ((class_1495)class_12972).method_6725())) {
                this.interact(class_12972);
                continue;
            }
            if (this.llamas.get().booleanValue() && class_12972 instanceof class_1501) {
                this.interact(class_12972);
                continue;
            }
            if (this.boats.get().booleanValue() && class_12972 instanceof class_1690) {
                this.interact(class_12972);
                continue;
            }
            if (this.minecarts.get().booleanValue() && class_12972 instanceof class_1695) {
                this.interact(class_12972);
                continue;
            }
            if (this.horses.get().booleanValue() && class_12972 instanceof class_1498 && (!this.checkSaddle.get().booleanValue() || ((class_1498)class_12972).method_6725())) {
                this.interact(class_12972);
                continue;
            }
            if (this.pigs.get().booleanValue() && class_12972 instanceof class_1452 && ((class_1452)class_12972).method_6725()) {
                this.interact(class_12972);
                continue;
            }
            if (this.mules.get().booleanValue() && class_12972 instanceof class_1500 && (!this.checkSaddle.get().booleanValue() || ((class_1500)class_12972).method_6725())) {
                this.interact(class_12972);
                continue;
            }
            if (!this.skeletonHorse.get().booleanValue() || !(class_12972 instanceof class_1506) || this.checkSaddle.get().booleanValue() && !((class_1506)class_12972).method_6725()) continue;
            this.interact(class_12972);
        }
    }

    public AutoMount() {
        super(Categories.World, "auto-mount", "Automatically mounts entities.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgMount = this.settings.createGroup("Mount");
        this.checkSaddle = this.sgGeneral.add(new BoolSetting.Builder().name("check-saddle").description("Checks if the entity contains a saddle before mounting.").defaultValue(false).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Faces the entity you mount.").defaultValue(true).build());
        this.horses = this.sgMount.add(new BoolSetting.Builder().name("horse").description("Horse").defaultValue(false).build());
        this.donkeys = this.sgMount.add(new BoolSetting.Builder().name("donkey").description("Donkey").defaultValue(false).build());
        this.mules = this.sgMount.add(new BoolSetting.Builder().name("mule").description("Mule").defaultValue(false).build());
        this.skeletonHorse = this.sgMount.add(new BoolSetting.Builder().name("skeleton-horse").description("Skeleton Horse").defaultValue(false).build());
        this.llamas = this.sgMount.add(new BoolSetting.Builder().name("llama").description("Llama").defaultValue(false).build());
        this.pigs = this.sgMount.add(new BoolSetting.Builder().name("pig").description("Pig").defaultValue(false).build());
        this.boats = this.sgMount.add(new BoolSetting.Builder().name("boat").description("Boat").defaultValue(false).build());
        this.minecarts = this.sgMount.add(new BoolSetting.Builder().name("minecart").description("Minecart").defaultValue(false).build());
    }

    private void interact(class_1297 class_12972) {
        if (this.rotate.get().booleanValue()) {
            Rotations.rotate(Rotations.getYaw(class_12972), Rotations.getPitch(class_12972), -100, () -> this.lambda$interact$0(class_12972));
        } else {
            this.mc.field_1761.method_2905((class_1657)this.mc.field_1724, class_12972, class_1268.field_5808);
        }
    }

    private void lambda$interact$0(class_1297 class_12972) {
        this.mc.field_1761.method_2905((class_1657)this.mc.field_1724, class_12972, class_1268.field_5808);
    }
}

