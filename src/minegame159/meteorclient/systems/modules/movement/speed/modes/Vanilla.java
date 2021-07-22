/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1294
 *  net.minecraft.class_243
 */
package minegame159.meteorclient.systems.modules.movement.speed.modes;

import minegame159.meteorclient.events.entity.player.PlayerMoveEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.Anchor;
import minegame159.meteorclient.systems.modules.movement.speed.SpeedMode;
import minegame159.meteorclient.systems.modules.movement.speed.SpeedModes;
import minegame159.meteorclient.utils.player.PlayerUtils;
import net.minecraft.class_1294;
import net.minecraft.class_243;

public class Vanilla
extends SpeedMode {
    public Vanilla() {
        super(SpeedModes.Vanilla);
    }

    @Override
    public void onMove(PlayerMoveEvent playerMoveEvent) {
        Anchor anchor;
        class_243 class_2432 = PlayerUtils.getHorizontalVelocity(this.settings.vanillaSpeed.get());
        double d = class_2432.method_10216();
        double d2 = class_2432.method_10215();
        if (this.mc.field_1724.method_6059(class_1294.field_5904)) {
            double d3 = (double)(this.mc.field_1724.method_6112(class_1294.field_5904).method_5578() + 1) * 0.205;
            d += d * d3;
            d2 += d2 * d3;
        }
        if ((anchor = Modules.get().get(Anchor.class)).isActive() && anchor.controlMovement) {
            d = anchor.deltaX;
            d2 = anchor.deltaZ;
        }
        ((IVec3d)playerMoveEvent.movement).set(d, playerMoveEvent.movement.field_1351, d2);
    }
}

