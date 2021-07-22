/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_238
 */
package minegame159.meteorclient.systems.modules.movement;

import java.util.stream.Stream;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1297;
import net.minecraft.class_238;

public class Parkour
extends Module {
    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (!this.mc.field_1724.method_24828() || this.mc.field_1690.field_1903.method_1434()) {
            return;
        }
        if (this.mc.field_1724.method_5715() || this.mc.field_1690.field_1832.method_1434()) {
            return;
        }
        class_238 class_2383 = this.mc.field_1724.method_5829();
        class_238 class_2384 = class_2383.method_989(0.0, -0.5, 0.0).method_1009(-0.001, 0.0, -0.001);
        Stream stream = this.mc.field_1687.method_20812((class_1297)this.mc.field_1724, class_2384);
        if (stream.findAny().isPresent()) {
            return;
        }
        this.mc.field_1724.method_6043();
    }

    public Parkour() {
        super(Categories.Movement, "parkour", "Automatically jumps at the edges of blocks.");
    }
}

