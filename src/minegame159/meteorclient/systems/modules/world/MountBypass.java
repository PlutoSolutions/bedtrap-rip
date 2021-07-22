/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1492
 *  net.minecraft.class_1937
 *  net.minecraft.class_2824
 *  net.minecraft.class_2824$class_2825
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
    private /* synthetic */ boolean dontCancel;

    public MountBypass() {
        super(Categories.World, "mount-bypass", "Allows you to bypass the IllegalStacks plugin and put chests on entities.");
        MountBypass lIIlIllIIIIll;
    }

    @EventHandler
    public void onSendPacket(PacketEvent.Send lIIlIlIlllllI) {
        MountBypass lIIlIlIllllll;
        if (lIIlIlIllllll.dontCancel) {
            lIIlIlIllllll.dontCancel = false;
            return;
        }
        if (!(lIIlIlIlllllI.packet instanceof class_2824)) {
            return;
        }
        class_2824 lIIlIlIllllIl = (class_2824)lIIlIlIlllllI.packet;
        if (lIIlIlIllllIl.method_12252() == class_2824.class_2825.field_12873 && lIIlIlIllllIl.method_12248((class_1937)lIIlIlIllllll.mc.field_1687) instanceof class_1492) {
            lIIlIlIlllllI.cancel();
        }
    }
}

