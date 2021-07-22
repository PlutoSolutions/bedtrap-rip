/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2708
 */
package minegame159.meteorclient.systems.modules.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.mixin.PlayerPositionLookS2CPacketAccessor;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2708;

public class NoRotate
extends Module {
    @EventHandler
    private void onReceivePacket(PacketEvent.Receive receive) {
        if (receive.packet instanceof class_2708) {
            ((PlayerPositionLookS2CPacketAccessor)receive.packet).setPitch(this.mc.field_1724.field_5965);
            ((PlayerPositionLookS2CPacketAccessor)receive.packet).setYaw(this.mc.field_1724.field_6031);
        }
    }

    public NoRotate() {
        super(Categories.Player, "no-rotate", "Attempts to block rotations sent from server to client.");
    }
}

