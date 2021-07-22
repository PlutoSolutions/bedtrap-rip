/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1802
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package minegame159.meteorclient.systems.modules.movement.elytrafly.modes;

import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.systems.modules.movement.elytrafly.ElytraFlightMode;
import minegame159.meteorclient.systems.modules.movement.elytrafly.ElytraFlightModes;
import net.minecraft.class_1297;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2848;

public class Packet
extends ElytraFlightMode {
    private final /* synthetic */ class_243 vec3d;

    @Override
    public void onPacketSend(PacketEvent.Send llllllllllIlll) {
        if (llllllllllIlll.packet instanceof class_2828) {
            Packet lllllllllllIlI;
            lllllllllllIlI.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)lllllllllllIlI.mc.field_1724, class_2848.class_2849.field_12982));
        }
    }

    public Packet() {
        super(ElytraFlightModes.Packet);
        Packet lIIIIIIIIIIIIll;
        lIIIIIIIIIIIIll.vec3d = new class_243(0.0, 0.0, 0.0);
    }

    @Override
    public void onDeactivate() {
        lIIIIIIIIIIIIIl.mc.field_1724.field_7503.field_7479 = false;
        lIIIIIIIIIIIIIl.mc.field_1724.field_7503.field_7478 = false;
    }

    @Override
    public void onTick() {
        Packet lllllllllllllI;
        super.onTick();
        if (lllllllllllllI.mc.field_1724.field_7514.method_7372(2).method_7909() != class_1802.field_8833 || (double)lllllllllllllI.mc.field_1724.field_6017 <= 0.2 || lllllllllllllI.mc.field_1690.field_1832.method_1434()) {
            return;
        }
        if (lllllllllllllI.mc.field_1690.field_1894.method_1434()) {
            lllllllllllllI.vec3d.method_1031(0.0, 0.0, lllllllllllllI.settings.horizontalSpeed.get().doubleValue());
            lllllllllllllI.vec3d.method_1024(-((float)Math.toRadians(lllllllllllllI.mc.field_1724.field_6031)));
        } else if (lllllllllllllI.mc.field_1690.field_1881.method_1434()) {
            lllllllllllllI.vec3d.method_1031(0.0, 0.0, lllllllllllllI.settings.horizontalSpeed.get().doubleValue());
            lllllllllllllI.vec3d.method_1024((float)Math.toRadians(lllllllllllllI.mc.field_1724.field_6031));
        }
        if (lllllllllllllI.mc.field_1690.field_1903.method_1434()) {
            lllllllllllllI.vec3d.method_1031(0.0, lllllllllllllI.settings.verticalSpeed.get().doubleValue(), 0.0);
        } else if (!lllllllllllllI.mc.field_1690.field_1903.method_1434()) {
            lllllllllllllI.vec3d.method_1031(0.0, -lllllllllllllI.settings.verticalSpeed.get().doubleValue(), 0.0);
        }
        lllllllllllllI.mc.field_1724.method_18799(lllllllllllllI.vec3d);
        lllllllllllllI.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)lllllllllllllI.mc.field_1724, class_2848.class_2849.field_12982));
        lllllllllllllI.mc.field_1724.field_3944.method_2883((class_2596)new class_2828(true));
    }

    @Override
    public void onPlayerMove() {
        Packet llllllllllIlIl;
        llllllllllIlIl.mc.field_1724.field_7503.field_7479 = true;
        llllllllllIlIl.mc.field_1724.field_7503.method_7248(llllllllllIlIl.settings.horizontalSpeed.get().floatValue() / 20.0f);
    }
}

