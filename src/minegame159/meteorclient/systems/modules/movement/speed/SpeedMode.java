/*
 * Decompiled with CFR 0.151.
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
    private final SpeedModes type;
    protected final class_310 mc;
    protected double distance;
    protected double speed;
    protected final Speed settings = Modules.get().get(Speed.class);
    protected int stage;

    public void onRubberband() {
        this.reset();
    }

    public void onDeactivate() {
    }

    public void onTick() {
    }

    public void onActivate() {
    }

    protected void reset() {
        this.stage = 0;
        this.distance = 0.0;
        this.speed = 0.2873;
    }

    protected double getDefaultSpeed() {
        int n;
        double d = 0.2873;
        if (this.mc.field_1724.method_6059(class_1294.field_5904)) {
            n = this.mc.field_1724.method_6112(class_1294.field_5904).method_5578();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        if (this.mc.field_1724.method_6059(class_1294.field_5909)) {
            n = this.mc.field_1724.method_6112(class_1294.field_5909).method_5578();
            d /= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public SpeedMode(SpeedModes speedModes) {
        this.mc = class_310.method_1551();
        this.type = speedModes;
        this.reset();
    }

    protected double getHop(double d) {
        class_1293 class_12932;
        class_1293 class_12933 = class_12932 = this.mc.field_1724.method_6059(class_1294.field_5913) ? this.mc.field_1724.method_6112(class_1294.field_5913) : null;
        if (class_12932 != null) {
            d += (double)((float)class_12932.method_5578() + 0.1f);
        }
        return d;
    }

    public void onMove(PlayerMoveEvent playerMoveEvent) {
    }

    public String getHudString() {
        return this.type.name();
    }
}

