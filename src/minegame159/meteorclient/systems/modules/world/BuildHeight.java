/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.world;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.mixin.BlockHitResultAccessor;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2350;
import net.minecraft.class_2885;

public class BuildHeight
extends Module {
    public BuildHeight() {
        super(Categories.World, "build-height", "Allows you to interact with objects at the build limit.");
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send send) {
        if (!(send.packet instanceof class_2885)) {
            return;
        }
        class_2885 class_28852 = (class_2885)send.packet;
        if (class_28852.method_12543().method_17784().field_1351 >= 255.0 && class_28852.method_12543().method_17780() == class_2350.field_11036) {
            ((BlockHitResultAccessor)class_28852.method_12543()).setSide(class_2350.field_11033);
        }
    }
}

