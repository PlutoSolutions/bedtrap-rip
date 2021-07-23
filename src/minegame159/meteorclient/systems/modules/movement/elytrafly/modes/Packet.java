/*
 * Decompiled with CFR 0.151.
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
    private final class_243 vec3d = new class_243(0.0, 0.0, 0.0);

    @Override
    public void onPacketSend(PacketEvent.Send send) {
        if (send.packet instanceof class_2828) {
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)this.mc.field_1724, class_2848.class_2849.field_12982));
        }
    }

    public Packet() {
        super(ElytraFlightModes.Packet);
    }

    @Override
    public void onDeactivate() {
        this.mc.field_1724.field_7503.field_7479 = false;
        this.mc.field_1724.field_7503.field_7478 = false;
    }

    @Override
    public void onTick() {
        super.onTick();
        if (this.mc.field_1724.field_7514.method_7372(2).method_7909() != class_1802.field_8833 || (double)this.mc.field_1724.field_6017 <= 0.2 || this.mc.field_1690.field_1832.method_1434()) {
            return;
        }
        if (this.mc.field_1690.field_1894.method_1434()) {
            this.vec3d.method_1031(0.0, 0.0, this.settings.horizontalSpeed.get().doubleValue());
            this.vec3d.method_1024(-((float)Math.toRadians(this.mc.field_1724.field_6031)));
        } else if (this.mc.field_1690.field_1881.method_1434()) {
            this.vec3d.method_1031(0.0, 0.0, this.settings.horizontalSpeed.get().doubleValue());
            this.vec3d.method_1024((float)Math.toRadians(this.mc.field_1724.field_6031));
        }
        if (this.mc.field_1690.field_1903.method_1434()) {
            this.vec3d.method_1031(0.0, this.settings.verticalSpeed.get().doubleValue(), 0.0);
        } else if (!this.mc.field_1690.field_1903.method_1434()) {
            this.vec3d.method_1031(0.0, -this.settings.verticalSpeed.get().doubleValue(), 0.0);
        }
        this.mc.field_1724.method_18799(this.vec3d);
        this.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)this.mc.field_1724, class_2848.class_2849.field_12982));
        this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828(true));
    }

    @Override
    public void onPlayerMove() {
        this.mc.field_1724.field_7503.field_7479 = true;
        this.mc.field_1724.field_7503.method_7248(this.settings.horizontalSpeed.get().floatValue() / 20.0f);
    }
}

