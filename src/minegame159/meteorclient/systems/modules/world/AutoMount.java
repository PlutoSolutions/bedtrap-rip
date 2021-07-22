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
    private final /* synthetic */ Setting<Boolean> horses;
    private final /* synthetic */ SettingGroup sgMount;
    private final /* synthetic */ Setting<Boolean> donkeys;
    private final /* synthetic */ Setting<Boolean> skeletonHorse;
    private final /* synthetic */ Setting<Boolean> llamas;
    private final /* synthetic */ Setting<Boolean> checkSaddle;
    private final /* synthetic */ Setting<Boolean> pigs;
    private final /* synthetic */ Setting<Boolean> mules;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> minecarts;
    private final /* synthetic */ Setting<Boolean> boats;

    @EventHandler
    private void onTick(TickEvent.Pre lIllIllIllllllI) {
        AutoMount lIllIllIlllllll;
        if (lIllIllIlllllll.mc.field_1724.method_5765()) {
            return;
        }
        for (class_1297 lIllIlllIIIIIII : lIllIllIlllllll.mc.field_1687.method_18112()) {
            if (lIllIllIlllllll.mc.field_1724.method_5739(lIllIlllIIIIIII) > 4.0f) continue;
            if (lIllIllIlllllll.mc.field_1724.method_6047().method_7909() instanceof class_1826) {
                return;
            }
            if (lIllIllIlllllll.donkeys.get().booleanValue() && lIllIlllIIIIIII instanceof class_1495 && (!lIllIllIlllllll.checkSaddle.get().booleanValue() || ((class_1495)lIllIlllIIIIIII).method_6725())) {
                lIllIllIlllllll.interact(lIllIlllIIIIIII);
                continue;
            }
            if (lIllIllIlllllll.llamas.get().booleanValue() && lIllIlllIIIIIII instanceof class_1501) {
                lIllIllIlllllll.interact(lIllIlllIIIIIII);
                continue;
            }
            if (lIllIllIlllllll.boats.get().booleanValue() && lIllIlllIIIIIII instanceof class_1690) {
                lIllIllIlllllll.interact(lIllIlllIIIIIII);
                continue;
            }
            if (lIllIllIlllllll.minecarts.get().booleanValue() && lIllIlllIIIIIII instanceof class_1695) {
                lIllIllIlllllll.interact(lIllIlllIIIIIII);
                continue;
            }
            if (lIllIllIlllllll.horses.get().booleanValue() && lIllIlllIIIIIII instanceof class_1498 && (!lIllIllIlllllll.checkSaddle.get().booleanValue() || ((class_1498)lIllIlllIIIIIII).method_6725())) {
                lIllIllIlllllll.interact(lIllIlllIIIIIII);
                continue;
            }
            if (lIllIllIlllllll.pigs.get().booleanValue() && lIllIlllIIIIIII instanceof class_1452 && ((class_1452)lIllIlllIIIIIII).method_6725()) {
                lIllIllIlllllll.interact(lIllIlllIIIIIII);
                continue;
            }
            if (lIllIllIlllllll.mules.get().booleanValue() && lIllIlllIIIIIII instanceof class_1500 && (!lIllIllIlllllll.checkSaddle.get().booleanValue() || ((class_1500)lIllIlllIIIIIII).method_6725())) {
                lIllIllIlllllll.interact(lIllIlllIIIIIII);
                continue;
            }
            if (!lIllIllIlllllll.skeletonHorse.get().booleanValue() || !(lIllIlllIIIIIII instanceof class_1506) || lIllIllIlllllll.checkSaddle.get().booleanValue() && !((class_1506)lIllIlllIIIIIII).method_6725()) continue;
            lIllIllIlllllll.interact(lIllIlllIIIIIII);
        }
    }

    public AutoMount() {
        super(Categories.World, "auto-mount", "Automatically mounts entities.");
        AutoMount lIllIlllIIIIlIl;
        lIllIlllIIIIlIl.sgGeneral = lIllIlllIIIIlIl.settings.getDefaultGroup();
        lIllIlllIIIIlIl.sgMount = lIllIlllIIIIlIl.settings.createGroup("Mount");
        lIllIlllIIIIlIl.checkSaddle = lIllIlllIIIIlIl.sgGeneral.add(new BoolSetting.Builder().name("check-saddle").description("Checks if the entity contains a saddle before mounting.").defaultValue(false).build());
        lIllIlllIIIIlIl.rotate = lIllIlllIIIIlIl.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Faces the entity you mount.").defaultValue(true).build());
        lIllIlllIIIIlIl.horses = lIllIlllIIIIlIl.sgMount.add(new BoolSetting.Builder().name("horse").description("Horse").defaultValue(false).build());
        lIllIlllIIIIlIl.donkeys = lIllIlllIIIIlIl.sgMount.add(new BoolSetting.Builder().name("donkey").description("Donkey").defaultValue(false).build());
        lIllIlllIIIIlIl.mules = lIllIlllIIIIlIl.sgMount.add(new BoolSetting.Builder().name("mule").description("Mule").defaultValue(false).build());
        lIllIlllIIIIlIl.skeletonHorse = lIllIlllIIIIlIl.sgMount.add(new BoolSetting.Builder().name("skeleton-horse").description("Skeleton Horse").defaultValue(false).build());
        lIllIlllIIIIlIl.llamas = lIllIlllIIIIlIl.sgMount.add(new BoolSetting.Builder().name("llama").description("Llama").defaultValue(false).build());
        lIllIlllIIIIlIl.pigs = lIllIlllIIIIlIl.sgMount.add(new BoolSetting.Builder().name("pig").description("Pig").defaultValue(false).build());
        lIllIlllIIIIlIl.boats = lIllIlllIIIIlIl.sgMount.add(new BoolSetting.Builder().name("boat").description("Boat").defaultValue(false).build());
        lIllIlllIIIIlIl.minecarts = lIllIlllIIIIlIl.sgMount.add(new BoolSetting.Builder().name("minecart").description("Minecart").defaultValue(false).build());
    }

    private void interact(class_1297 lIllIllIlllIlIl) {
        AutoMount lIllIllIlllIllI;
        if (lIllIllIlllIllI.rotate.get().booleanValue()) {
            Rotations.rotate(Rotations.getYaw(lIllIllIlllIlIl), Rotations.getPitch(lIllIllIlllIlIl), -100, () -> {
                AutoMount lIllIllIlllIIII;
                lIllIllIlllIIII.mc.field_1761.method_2905((class_1657)lIllIllIlllIIII.mc.field_1724, lIllIllIlllIlIl, class_1268.field_5808);
            });
        } else {
            lIllIllIlllIllI.mc.field_1761.method_2905((class_1657)lIllIllIlllIllI.mc.field_1724, lIllIllIlllIlIl, class_1268.field_5808);
        }
    }
}

