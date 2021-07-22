/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2815
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
    private /* synthetic */ boolean invOpened;

    public XCarry() {
        super(Categories.Player, "XCarry", "Allows you to store four extra items in your crafting grid.");
        XCarry llllllllllllllllllIIlIIllIlIIllI;
    }

    @Override
    public void onActivate() {
        llllllllllllllllllIIlIIllIlIIlII.invOpened = false;
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send llllllllllllllllllIIlIIllIIlllII) {
        XCarry llllllllllllllllllIIlIIllIIllIll;
        if (!(llllllllllllllllllIIlIIllIIlllII.packet instanceof class_2815)) {
            return;
        }
        if (((CloseHandledScreenC2SPacketAccessor)llllllllllllllllllIIlIIllIIlllII.packet).getSyncId() == llllllllllllllllllIIlIIllIIllIll.mc.field_1724.field_7498.field_7763) {
            llllllllllllllllllIIlIIllIIllIll.invOpened = true;
            llllllllllllllllllIIlIIllIIlllII.cancel();
        }
    }

    @Override
    public void onDeactivate() {
        XCarry llllllllllllllllllIIlIIllIlIIIII;
        if (llllllllllllllllllIIlIIllIlIIIII.invOpened) {
            llllllllllllllllllIIlIIllIlIIIII.mc.field_1724.field_3944.method_2883((class_2596)new class_2815(llllllllllllllllllIIlIIllIlIIIII.mc.field_1724.field_7498.field_7763));
        }
    }
}

