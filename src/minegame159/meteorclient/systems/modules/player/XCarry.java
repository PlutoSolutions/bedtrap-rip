/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.mixin.CloseHandledScreenC2SPacketAccessor;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2596;
import net.minecraft.class_2815;

public class XCarry
extends Module {
    private boolean invOpened;

    public XCarry() {
        super(Categories.Player, "XCarry", "Allows you to store four extra items in your crafting grid.");
    }

    @Override
    public void onActivate() {
        this.invOpened = false;
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send send) {
        if (!(send.packet instanceof class_2815)) {
            return;
        }
        if (((CloseHandledScreenC2SPacketAccessor)send.packet).getSyncId() == this.mc.field_1724.field_7498.field_7763) {
            this.invOpened = true;
            send.cancel();
        }
    }

    @Override
    public void onDeactivate() {
        if (this.invOpened) {
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2815(this.mc.field_1724.field_7498.field_7763));
        }
    }
}

