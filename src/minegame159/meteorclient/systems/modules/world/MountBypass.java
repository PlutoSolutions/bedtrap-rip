/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.world;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1492;
import net.minecraft.class_1937;
import net.minecraft.class_2824;

public class MountBypass
extends Module {
    private boolean dontCancel;

    public MountBypass() {
        super(Categories.World, "mount-bypass", "Allows you to bypass the IllegalStacks plugin and put chests on entities.");
    }

    @EventHandler
    public void onSendPacket(PacketEvent.Send send) {
        if (this.dontCancel) {
            this.dontCancel = false;
            return;
        }
        if (!(send.packet instanceof class_2824)) {
            return;
        }
        class_2824 class_28242 = (class_2824)send.packet;
        if (class_28242.method_12252() == class_2824.class_2825.field_12873 && class_28242.method_12248((class_1937)this.mc.field_1687) instanceof class_1492) {
            send.cancel();
        }
    }
}

