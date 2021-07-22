/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_2586
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.player;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1268;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_3965;

public class GhostHand
extends Module {
    private final List<class_2338> posList = new ArrayList<class_2338>();

    public GhostHand() {
        super(Categories.Player, "ghost-hand", "Opens containers through walls.");
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (!this.mc.field_1690.field_1904.method_1434() || this.mc.field_1724.method_5715()) {
            return;
        }
        for (class_2586 class_25862 : this.mc.field_1687.field_9231) {
            if (!new class_2338(this.mc.field_1724.method_5745((double)this.mc.field_1761.method_2904(), this.mc.method_1488(), false).method_17784()).equals((Object)class_25862.method_11016())) continue;
            return;
        }
        class_243 class_2432 = new class_243(0.0, 0.0, 0.1).method_1037(-((float)Math.toRadians(this.mc.field_1724.field_5965))).method_1024(-((float)Math.toRadians(this.mc.field_1724.field_6031)));
        int n = 1;
        while ((float)n < this.mc.field_1761.method_2904() * 10.0f) {
            class_2338 class_23382 = new class_2338(this.mc.field_1724.method_5836(this.mc.method_1488()).method_1019(class_2432.method_1021((double)n)));
            if (!this.posList.contains((Object)class_23382)) {
                this.posList.add(class_23382);
                for (class_2586 class_25863 : this.mc.field_1687.field_9231) {
                    if (!class_25863.method_11016().equals((Object)class_23382)) continue;
                    this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5808, new class_3965(this.mc.field_1724.method_19538(), class_2350.field_11036, class_23382, true));
                    return;
                }
            }
            ++n;
            if (2 > 1) continue;
            return;
        }
        this.posList.clear();
    }
}

