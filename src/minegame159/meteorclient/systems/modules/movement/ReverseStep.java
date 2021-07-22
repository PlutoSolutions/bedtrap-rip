/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2244
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2244;
import net.minecraft.class_2338;

public class ReverseStep
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> fallSpeed;
    private final /* synthetic */ Setting<Double> fallDistance;

    private boolean check(class_2338.class_2339 llIlIllIllIIIIl, int llIlIllIllIIIII, int llIlIllIlIlllll) {
        ReverseStep llIlIllIllIIIlI;
        llIlIllIllIIIIl.method_10100(llIlIllIllIIIII, 0, llIlIllIlIlllll);
        boolean llIlIllIlIllllI = llIlIllIllIIIlI.mc.field_1687.method_8320((class_2338)llIlIllIllIIIIl).method_26204() instanceof class_2244;
        llIlIllIllIIIIl.method_10100(-llIlIllIllIIIII, 0, -llIlIllIlIlllll);
        return llIlIllIlIllllI;
    }

    public ReverseStep() {
        super(Categories.Movement, "reverse-step", "Allows you to fall down blocks at a greater speed.");
        ReverseStep llIlIllIllllIII;
        llIlIllIllllIII.sgGeneral = llIlIllIllllIII.settings.getDefaultGroup();
        llIlIllIllllIII.fallSpeed = llIlIllIllllIII.sgGeneral.add(new DoubleSetting.Builder().name("fall-speed").description("How fast to fall in blocks per second.").defaultValue(3.0).min(0.0).sliderMax(10.0).build());
        llIlIllIllllIII.fallDistance = llIlIllIllllIII.sgGeneral.add(new DoubleSetting.Builder().name("fall-distance").description("The maximum fall distance this setting will activate at.").defaultValue(3.0).min(0.0).sliderMax(10.0).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post llIlIllIlllIlIl) {
        ReverseStep llIlIllIlllIllI;
        if (!llIlIllIlllIllI.mc.field_1724.method_24828() || llIlIllIlllIllI.mc.field_1724.method_21754() || llIlIllIlllIllI.mc.field_1724.method_5869() || llIlIllIlllIllI.mc.field_1724.method_5771() || llIlIllIlllIllI.mc.field_1690.field_1903.method_1434() || llIlIllIlllIllI.mc.field_1724.field_5960 || llIlIllIlllIllI.mc.field_1724.field_6250 == 0.0f && llIlIllIlllIllI.mc.field_1724.field_6212 == 0.0f) {
            return;
        }
        if (!llIlIllIlllIllI.isOnBed() && !llIlIllIlllIllI.mc.field_1687.method_18026(llIlIllIlllIllI.mc.field_1724.method_5829().method_989(0.0, (double)((float)(-(llIlIllIlllIllI.fallDistance.get() + 0.01))), 0.0))) {
            ((IVec3d)llIlIllIlllIllI.mc.field_1724.method_18798()).setY(-llIlIllIlllIllI.fallSpeed.get().doubleValue());
        }
    }

    private boolean isOnBed() {
        ReverseStep llIlIllIllIlIll;
        class_2338.class_2339 llIlIllIllIlllI = llIlIllIllIlIll.mc.field_1724.method_24515().method_25503();
        if (llIlIllIllIlIll.check(llIlIllIllIlllI, 0, 0)) {
            return true;
        }
        double llIlIllIllIllIl = llIlIllIllIlIll.mc.field_1724.method_23317() - (double)llIlIllIllIlllI.method_10263();
        double llIlIllIllIllII = llIlIllIllIlIll.mc.field_1724.method_23321() - (double)llIlIllIllIlllI.method_10260();
        if (llIlIllIllIllIl >= 0.0 && llIlIllIllIllIl <= 0.3 && llIlIllIllIlIll.check(llIlIllIllIlllI, -1, 0)) {
            return true;
        }
        if (llIlIllIllIllIl >= 0.7 && llIlIllIllIlIll.check(llIlIllIllIlllI, 1, 0)) {
            return true;
        }
        if (llIlIllIllIllII >= 0.0 && llIlIllIllIllII <= 0.3 && llIlIllIllIlIll.check(llIlIllIllIlllI, 0, -1)) {
            return true;
        }
        if (llIlIllIllIllII >= 0.7 && llIlIllIllIlIll.check(llIlIllIllIlllI, 0, 1)) {
            return true;
        }
        if (llIlIllIllIllIl >= 0.0 && llIlIllIllIllIl <= 0.3 && llIlIllIllIllII >= 0.0 && llIlIllIllIllII <= 0.3 && llIlIllIllIlIll.check(llIlIllIllIlllI, -1, -1)) {
            return true;
        }
        if (llIlIllIllIllIl >= 0.0 && llIlIllIllIllIl <= 0.3 && llIlIllIllIllII >= 0.7 && llIlIllIllIlIll.check(llIlIllIllIlllI, -1, 1)) {
            return true;
        }
        if (llIlIllIllIllIl >= 0.7 && llIlIllIllIllII >= 0.0 && llIlIllIllIllII <= 0.3 && llIlIllIllIlIll.check(llIlIllIllIlllI, 1, -1)) {
            return true;
        }
        return llIlIllIllIllIl >= 0.7 && llIlIllIllIllII >= 0.7 && llIlIllIllIlIll.check(llIlIllIllIlllI, 1, 1);
    }
}

