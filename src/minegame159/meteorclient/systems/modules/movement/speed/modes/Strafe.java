/*
 * Decompiled with CFR 0.150.
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
    private /* synthetic */ long timer;

    private Vector2 transformStrafe(double lllllllllllllllllIlIIllIllIIlllI) {
        Strafe lllllllllllllllllIlIIllIllIIllll;
        float lllllllllllllllllIlIIllIllIIllIl = lllllllllllllllllIlIIllIllIIllll.mc.field_1724.field_3913.field_3905;
        float lllllllllllllllllIlIIllIllIIllII = lllllllllllllllllIlIIllIllIIllll.mc.field_1724.field_3913.field_3907;
        float lllllllllllllllllIlIIllIllIIlIll = lllllllllllllllllIlIIllIllIIllll.mc.field_1724.field_5982 + (lllllllllllllllllIlIIllIllIIllll.mc.field_1724.field_6031 - lllllllllllllllllIlIIllIllIIllll.mc.field_1724.field_5982) * lllllllllllllllllIlIIllIllIIllll.mc.method_1488();
        if (lllllllllllllllllIlIIllIllIIllIl == 0.0f && lllllllllllllllllIlIIllIllIIllII == 0.0f) {
            return new Vector2(0.0, 0.0);
        }
        if (lllllllllllllllllIlIIllIllIIllIl != 0.0f) {
            if (lllllllllllllllllIlIIllIllIIllII >= 1.0f) {
                lllllllllllllllllIlIIllIllIIlIll += (float)(lllllllllllllllllIlIIllIllIIllIl > 0.0f ? -45 : 45);
                lllllllllllllllllIlIIllIllIIllII = 0.0f;
            } else if (lllllllllllllllllIlIIllIllIIllII <= -1.0f) {
                lllllllllllllllllIlIIllIllIIlIll += (float)(lllllllllllllllllIlIIllIllIIllIl > 0.0f ? 45 : -45);
                lllllllllllllllllIlIIllIllIIllII = 0.0f;
            }
            if (lllllllllllllllllIlIIllIllIIllIl > 0.0f) {
                lllllllllllllllllIlIIllIllIIllIl = 1.0f;
            } else if (lllllllllllllllllIlIIllIllIIllIl < 0.0f) {
                lllllllllllllllllIlIIllIllIIllIl = -1.0f;
            }
        }
        double lllllllllllllllllIlIIllIllIIlIII = Math.cos(Math.toRadians(lllllllllllllllllIlIIllIllIIlIll + 90.0f));
        double lllllllllllllllllIlIIllIllIIIlll = Math.sin(Math.toRadians(lllllllllllllllllIlIIllIllIIlIll + 90.0f));
        double lllllllllllllllllIlIIllIllIIlIlI = (double)lllllllllllllllllIlIIllIllIIllIl * lllllllllllllllllIlIIllIllIIlllI * lllllllllllllllllIlIIllIllIIlIII + (double)lllllllllllllllllIlIIllIllIIllII * lllllllllllllllllIlIIllIllIIlllI * lllllllllllllllllIlIIllIllIIIlll;
        double lllllllllllllllllIlIIllIllIIlIIl = (double)lllllllllllllllllIlIIllIllIIllIl * lllllllllllllllllIlIIllIllIIlllI * lllllllllllllllllIlIIllIllIIIlll - (double)lllllllllllllllllIlIIllIllIIllII * lllllllllllllllllIlIIllIllIIlllI * lllllllllllllllllIlIIllIllIIlIII;
        return new Vector2(lllllllllllllllllIlIIllIllIIlIlI, lllllllllllllllllIlIIllIllIIlIIl);
    }

    @Override
    public void onTick() {
        Strafe lllllllllllllllllIlIIllIlIlllIll;
        lllllllllllllllllIlIIllIlIlllIll.distance = Math.sqrt((lllllllllllllllllIlIIllIlIlllIll.mc.field_1724.method_23317() - lllllllllllllllllIlIIllIlIlllIll.mc.field_1724.field_6014) * (lllllllllllllllllIlIIllIlIlllIll.mc.field_1724.method_23317() - lllllllllllllllllIlIIllIlIlllIll.mc.field_1724.field_6014) + (lllllllllllllllllIlIIllIlIlllIll.mc.field_1724.method_23321() - lllllllllllllllllIlIIllIlIlllIll.mc.field_1724.field_5969) * (lllllllllllllllllIlIIllIlIlllIll.mc.field_1724.method_23321() - lllllllllllllllllIlIIllIlIlllIll.mc.field_1724.field_5969));
    }

    @Override
    public void onMove(PlayerMoveEvent lllllllllllllllllIlIIllIllIlllIl) {
        Strafe lllllllllllllllllIlIIllIllIllllI;
        switch (lllllllllllllllllIlIIllIllIllllI.stage) {
            case 0: {
                if (PlayerUtils.isMoving()) {
                    ++lllllllllllllllllIlIIllIllIllllI.stage;
                    lllllllllllllllllIlIIllIllIllllI.speed = (double)1.18f * lllllllllllllllllIlIIllIllIllllI.getDefaultSpeed() - 0.01;
                }
            }
            case 1: {
                if (!PlayerUtils.isMoving() || !lllllllllllllllllIlIIllIllIllllI.mc.field_1724.method_24828()) break;
                ((IVec3d)lllllllllllllllllIlIIllIllIlllIl.movement).setY(lllllllllllllllllIlIIllIllIllllI.getHop(0.40123128));
                lllllllllllllllllIlIIllIllIllllI.speed *= lllllllllllllllllIlIIllIllIllllI.settings.ncpSpeed.get().doubleValue();
                ++lllllllllllllllllIlIIllIllIllllI.stage;
                break;
            }
            case 2: {
                lllllllllllllllllIlIIllIllIllllI.speed = lllllllllllllllllIlIIllIllIllllI.distance - 0.76 * (lllllllllllllllllIlIIllIllIllllI.distance - lllllllllllllllllIlIIllIllIllllI.getDefaultSpeed());
                ++lllllllllllllllllIlIIllIllIllllI.stage;
                break;
            }
            case 3: {
                if (!lllllllllllllllllIlIIllIllIllllI.mc.field_1687.method_18026(lllllllllllllllllIlIIllIllIllllI.mc.field_1724.method_5829().method_989(0.0, lllllllllllllllllIlIIllIllIllllI.mc.field_1724.method_18798().field_1351, 0.0)) || lllllllllllllllllIlIIllIllIllllI.mc.field_1724.field_5992 && lllllllllllllllllIlIIllIllIllllI.stage > 0) {
                    lllllllllllllllllIlIIllIllIllllI.stage = 0;
                }
                lllllllllllllllllIlIIllIllIllllI.speed = lllllllllllllllllIlIIllIllIllllI.distance - lllllllllllllllllIlIIllIllIllllI.distance / 159.0;
            }
        }
        lllllllllllllllllIlIIllIllIllllI.speed = Math.max(lllllllllllllllllIlIIllIllIllllI.speed, lllllllllllllllllIlIIllIllIllllI.getDefaultSpeed());
        if (lllllllllllllllllIlIIllIllIllllI.settings.ncpSpeedLimit.get().booleanValue()) {
            if (System.currentTimeMillis() - lllllllllllllllllIlIIllIllIllllI.timer > 2500L) {
                lllllllllllllllllIlIIllIllIllllI.timer = System.currentTimeMillis();
            }
            lllllllllllllllllIlIIllIllIllllI.speed = Math.min(lllllllllllllllllIlIIllIllIllllI.speed, System.currentTimeMillis() - lllllllllllllllllIlIIllIllIllllI.timer > 1250L ? 0.44 : 0.43);
        }
        Vector2 lllllllllllllllllIlIIllIlllIIIlI = lllllllllllllllllIlIIllIllIllllI.transformStrafe(lllllllllllllllllIlIIllIllIllllI.speed);
        double lllllllllllllllllIlIIllIlllIIIIl = lllllllllllllllllIlIIllIlllIIIlI.x;
        double lllllllllllllllllIlIIllIlllIIIII = lllllllllllllllllIlIIllIlllIIIlI.y;
        Anchor lllllllllllllllllIlIIllIllIlllll = Modules.get().get(Anchor.class);
        if (lllllllllllllllllIlIIllIllIlllll.isActive() && lllllllllllllllllIlIIllIllIlllll.controlMovement) {
            lllllllllllllllllIlIIllIlllIIIIl = lllllllllllllllllIlIIllIllIlllll.deltaX;
            lllllllllllllllllIlIIllIlllIIIII = lllllllllllllllllIlIIllIllIlllll.deltaZ;
        }
        ((IVec3d)lllllllllllllllllIlIIllIllIlllIl.movement).setXZ(lllllllllllllllllIlIIllIlllIIIIl, lllllllllllllllllIlIIllIlllIIIII);
    }

    public Strafe() {
        super(SpeedModes.Strafe);
        Strafe lllllllllllllllllIlIIllIlllIlIll;
        lllllllllllllllllIlIIllIlllIlIll.timer = 0L;
    }
}

