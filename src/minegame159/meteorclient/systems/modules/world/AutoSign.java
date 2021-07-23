/*
 * Decompiled with CFR 0.151.
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
    private String[] text;

    @EventHandler
    private void onSendPacket(PacketEvent.Send send) {
        if (!(send.packet instanceof class_2877)) {
            return;
        }
        this.text = ((class_2877)send.packet).method_12508();
    }

    public AutoSign() {
        super(Categories.World, "auto-sign", "Automatically writes signs. The first sign's text will be used.");
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent openScreenEvent) {
        if (!(openScreenEvent.screen instanceof class_498) || this.text == null) {
            return;
        }
        class_2625 class_26252 = ((SignEditScreenAccessor)openScreenEvent.screen).getSign();
        this.mc.field_1724.field_3944.method_2883((class_2596)new class_2877(class_26252.method_11016(), this.text[0], this.text[1], this.text[2], this.text[3]));
        openScreenEvent.cancel();
    }

    @Override
    public void onDeactivate() {
        this.text = null;
    }
}

