/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2350
 *  net.minecraft.class_2885
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
        BuildHeight llIllIlIIlIIlll;
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send llIllIlIIlIIIII) {
        if (!(llIllIlIIlIIIII.packet instanceof class_2885)) {
            return;
        }
        class_2885 llIllIlIIlIIIIl = (class_2885)llIllIlIIlIIIII.packet;
        if (llIllIlIIlIIIIl.method_12543().method_17784().field_1351 >= 255.0 && llIllIlIIlIIIIl.method_12543().method_17780() == class_2350.field_11036) {
            ((BlockHitResultAccessor)llIllIlIIlIIIIl.method_12543()).setSide(class_2350.field_11033);
        }
    }
}

