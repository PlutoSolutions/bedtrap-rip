/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2625
 *  net.minecraft.class_2877
 *  net.minecraft.class_498
 */
package minegame159.meteorclient.systems.modules.world;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.mixin.SignEditScreenAccessor;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2596;
import net.minecraft.class_2625;
import net.minecraft.class_2877;
import net.minecraft.class_498;

public class AutoSign
extends Module {
    private /* synthetic */ String[] text;

    @EventHandler
    private void onSendPacket(PacketEvent.Send llllllllllllllllllIllIlIIIlIllII) {
        if (!(llllllllllllllllllIllIlIIIlIllII.packet instanceof class_2877)) {
            return;
        }
        llllllllllllllllllIllIlIIIlIllll.text = ((class_2877)llllllllllllllllllIllIlIIIlIllII.packet).method_12508();
    }

    public AutoSign() {
        super(Categories.World, "auto-sign", "Automatically writes signs. The first sign's text will be used.");
        AutoSign llllllllllllllllllIllIlIIIllIllI;
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent llllllllllllllllllIllIlIIIlIIlII) {
        AutoSign llllllllllllllllllIllIlIIIlIIlIl;
        if (!(llllllllllllllllllIllIlIIIlIIlII.screen instanceof class_498) || llllllllllllllllllIllIlIIIlIIlIl.text == null) {
            return;
        }
        class_2625 llllllllllllllllllIllIlIIIlIIllI = ((SignEditScreenAccessor)llllllllllllllllllIllIlIIIlIIlII.screen).getSign();
        llllllllllllllllllIllIlIIIlIIlIl.mc.field_1724.field_3944.method_2883((class_2596)new class_2877(llllllllllllllllllIllIlIIIlIIllI.method_11016(), llllllllllllllllllIllIlIIIlIIlIl.text[0], llllllllllllllllllIllIlIIIlIIlIl.text[1], llllllllllllllllllIllIlIIIlIIlIl.text[2], llllllllllllllllllIllIlIIIlIIlIl.text[3]));
        llllllllllllllllllIllIlIIIlIIlII.cancel();
    }

    @Override
    public void onDeactivate() {
        llllllllllllllllllIllIlIIIllIIlI.text = null;
    }
}

