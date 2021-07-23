/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.movement.speed.modes;

import minegame159.meteorclient.events.entity.player.PlayerMoveEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.Anchor;
import minegame159.meteorclient.systems.modules.movement.speed.SpeedMode;
import minegame159.meteorclient.systems.modules.movement.speed.SpeedModes;
import minegame159.meteorclient.utils.misc.Vector2;
import minegame159.meteorclient.utils.player.PlayerUtils;

public class Strafe
extends SpeedMode {
    private long timer = 0L;

    private Vector2 transformStrafe(double d) {
        float f = this.mc.field_1724.field_3913.field_3905;
        float f2 = this.mc.field_1724.field_3913.field_3907;
        float f3 = this.mc.field_1724.field_5982 + (this.mc.field_1724.field_6031 - this.mc.field_1724.field_5982) * this.mc.method_1488();
        if (f == 0.0f && f2 == 0.0f) {
            return new Vector2(0.0, 0.0);
        }
        if (f != 0.0f) {
            if (f2 >= 1.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
                f2 = 0.0f;
            } else if (f2 <= -1.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
                f2 = 0.0f;
            }
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d3 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d2 + (double)f2 * d * d3;
        double d5 = (double)f * d * d3 - (double)f2 * d * d2;
        return new Vector2(d4, d5);
    }

    @Override
    public void onTick() {
        this.distance = Math.sqrt((this.mc.field_1724.method_23317() - this.mc.field_1724.field_6014) * (this.mc.field_1724.method_23317() - this.mc.field_1724.field_6014) + (this.mc.field_1724.method_23321() - this.mc.field_1724.field_5969) * (this.mc.field_1724.method_23321() - this.mc.field_1724.field_5969));
    }

    @Override
    public void onMove(PlayerMoveEvent playerMoveEvent) {
        switch (this.stage) {
            case 0: {
                if (PlayerUtils.isMoving()) {
                    ++this.stage;
                    this.speed = (double)1.18f * this.getDefaultSpeed() - 0.01;
                }
            }
            case 1: {
                if (!PlayerUtils.isMoving() || !this.mc.field_1724.method_24828()) break;
                ((IVec3d)playerMoveEvent.movement).setY(this.getHop(0.40123128));
                this.speed *= this.settings.ncpSpeed.get().doubleValue();
                ++this.stage;
                break;
            }
            case 2: {
                this.speed = this.distance - 0.76 * (this.distance - this.getDefaultSpeed());
                ++this.stage;
                break;
            }
            case 3: {
                if (!this.mc.field_1687.method_18026(this.mc.field_1724.method_5829().method_989(0.0, this.mc.field_1724.method_18798().field_1351, 0.0)) || this.mc.field_1724.field_5992 && this.stage > 0) {
                    this.stage = 0;
                }
                this.speed = this.distance - this.distance / 159.0;
            }
        }
        this.speed = Math.max(this.speed, this.getDefaultSpeed());
        if (this.settings.ncpSpeedLimit.get().booleanValue()) {
            if (System.currentTimeMillis() - this.timer > 2500L) {
                this.timer = System.currentTimeMillis();
            }
            this.speed = Math.min(this.speed, System.currentTimeMillis() - this.timer > 1250L ? 0.44 : 0.43);
        }
        Vector2 vector2 = this.transformStrafe(this.speed);
        double d = vector2.x;
        double d2 = vector2.y;
        Anchor anchor = Modules.get().get(Anchor.class);
        if (anchor.isActive() && anchor.controlMovement) {
            d = anchor.deltaX;
            d2 = anchor.deltaZ;
        }
        ((IVec3d)playerMoveEvent.movement).setXZ(d, d2);
    }

    public Strafe() {
        super(SpeedModes.Strafe);
    }
}

