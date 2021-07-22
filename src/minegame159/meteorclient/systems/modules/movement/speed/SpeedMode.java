/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_310
 */
package minegame159.meteorclient.systems.modules.movement.speed;

import minegame159.meteorclient.events.entity.player.PlayerMoveEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.speed.Speed;
import minegame159.meteorclient.systems.modules.movement.speed.SpeedModes;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_310;

public class SpeedMode {
    private final /* synthetic */ SpeedModes type;
    protected final /* synthetic */ class_310 mc;
    protected /* synthetic */ double distance;
    protected /* synthetic */ double speed;
    protected final /* synthetic */ Speed settings;
    protected /* synthetic */ int stage;

    public void onRubberband() {
        SpeedMode llllIIllIlIll;
        llllIIllIlIll.reset();
    }

    public void onDeactivate() {
    }

    public void onTick() {
    }

    public void onActivate() {
    }

    protected void reset() {
        llllIIlIllIll.stage = 0;
        llllIIlIllIll.distance = 0.0;
        llllIIlIllIll.speed = 0.2873;
    }

    protected double getDefaultSpeed() {
        SpeedMode llllIIllIIIlI;
        double llllIIllIIIIl = 0.2873;
        if (llllIIllIIIlI.mc.field_1724.method_6059(class_1294.field_5904)) {
            int llllIIllIIlII = llllIIllIIIlI.mc.field_1724.method_6112(class_1294.field_5904).method_5578();
            llllIIllIIIIl *= 1.0 + 0.2 * (double)(llllIIllIIlII + 1);
        }
        if (llllIIllIIIlI.mc.field_1724.method_6059(class_1294.field_5909)) {
            int llllIIllIIIll = llllIIllIIIlI.mc.field_1724.method_6112(class_1294.field_5909).method_5578();
            llllIIllIIIIl /= 1.0 + 0.2 * (double)(llllIIllIIIll + 1);
        }
        return llllIIllIIIIl;
    }

    public SpeedMode(SpeedModes llllIIlllIIlI) {
        SpeedMode llllIIlllIIIl;
        llllIIlllIIIl.settings = Modules.get().get(Speed.class);
        llllIIlllIIIl.mc = class_310.method_1551();
        llllIIlllIIIl.type = llllIIlllIIlI;
        llllIIlllIIIl.reset();
    }

    protected double getHop(double llllIIlIlIIll) {
        SpeedMode llllIIlIlIlll;
        class_1293 llllIIlIlIlIl;
        class_1293 class_12932 = llllIIlIlIlIl = llllIIlIlIlll.mc.field_1724.method_6059(class_1294.field_5913) ? llllIIlIlIlll.mc.field_1724.method_6112(class_1294.field_5913) : null;
        if (llllIIlIlIlIl != null) {
            llllIIlIlIIll += (double)((float)llllIIlIlIlIl.method_5578() + 0.1f);
        }
        return llllIIlIlIIll;
    }

    public void onMove(PlayerMoveEvent llllIIllIllIl) {
    }

    public String getHudString() {
        SpeedMode llllIIlIIllll;
        return llllIIlIIllll.type.name();
    }
}

