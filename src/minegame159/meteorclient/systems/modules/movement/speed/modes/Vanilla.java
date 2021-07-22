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
        Vanilla lllIllIlIIlIIlI;
    }

    @Override
    public void onMove(PlayerMoveEvent lllIllIlIIIIIll) {
        Anchor lllIllIlIIIIlIl;
        Vanilla lllIllIlIIIIlII;
        class_243 lllIllIlIIIlIII = PlayerUtils.getHorizontalVelocity(lllIllIlIIIIlII.settings.vanillaSpeed.get());
        double lllIllIlIIIIlll = lllIllIlIIIlIII.method_10216();
        double lllIllIlIIIIllI = lllIllIlIIIlIII.method_10215();
        if (lllIllIlIIIIlII.mc.field_1724.method_6059(class_1294.field_5904)) {
            double lllIllIlIIIlIll = (double)(lllIllIlIIIIlII.mc.field_1724.method_6112(class_1294.field_5904).method_5578() + 1) * 0.205;
            lllIllIlIIIIlll += lllIllIlIIIIlll * lllIllIlIIIlIll;
            lllIllIlIIIIllI += lllIllIlIIIIllI * lllIllIlIIIlIll;
        }
        if ((lllIllIlIIIIlIl = Modules.get().get(Anchor.class)).isActive() && lllIllIlIIIIlIl.controlMovement) {
            lllIllIlIIIIlll = lllIllIlIIIIlIl.deltaX;
            lllIllIlIIIIllI = lllIllIlIIIIlIl.deltaZ;
        }
        ((IVec3d)lllIllIlIIIIIll.movement).set(lllIllIlIIIIlll, lllIllIlIIIIIll.movement.field_1351, lllIllIlIIIIllI);
    }
}

