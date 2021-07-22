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
    private final /* synthetic */ List<class_2338> posList;

    public GhostHand() {
        super(Categories.Player, "ghost-hand", "Opens containers through walls.");
        GhostHand llIlIIIIIIlIll;
        llIlIIIIIIlIll.posList = new ArrayList<class_2338>();
    }

    @EventHandler
    private void onTick(TickEvent.Pre llIIllllllllll) {
        GhostHand llIlIIIIIIIIII;
        if (!llIlIIIIIIIIII.mc.field_1690.field_1904.method_1434() || llIlIIIIIIIIII.mc.field_1724.method_5715()) {
            return;
        }
        for (class_2586 llIlIIIIIIIlII : llIlIIIIIIIIII.mc.field_1687.field_9231) {
            if (!new class_2338(llIlIIIIIIIIII.mc.field_1724.method_5745((double)llIlIIIIIIIIII.mc.field_1761.method_2904(), llIlIIIIIIIIII.mc.method_1488(), false).method_17784()).equals((Object)llIlIIIIIIIlII.method_11016())) continue;
            return;
        }
        class_243 llIIlllllllllI = new class_243(0.0, 0.0, 0.1).method_1037(-((float)Math.toRadians(llIlIIIIIIIIII.mc.field_1724.field_5965))).method_1024(-((float)Math.toRadians(llIlIIIIIIIIII.mc.field_1724.field_6031)));
        int llIlIIIIIIIIIl = 1;
        while ((float)llIlIIIIIIIIIl < llIlIIIIIIIIII.mc.field_1761.method_2904() * 10.0f) {
            class_2338 llIlIIIIIIIIlI = new class_2338(llIlIIIIIIIIII.mc.field_1724.method_5836(llIlIIIIIIIIII.mc.method_1488()).method_1019(llIIlllllllllI.method_1021((double)llIlIIIIIIIIIl)));
            if (!llIlIIIIIIIIII.posList.contains((Object)llIlIIIIIIIIlI)) {
                llIlIIIIIIIIII.posList.add(llIlIIIIIIIIlI);
                for (class_2586 llIlIIIIIIIIll : llIlIIIIIIIIII.mc.field_1687.field_9231) {
                    if (!llIlIIIIIIIIll.method_11016().equals((Object)llIlIIIIIIIIlI)) continue;
                    llIlIIIIIIIIII.mc.field_1761.method_2896(llIlIIIIIIIIII.mc.field_1724, llIlIIIIIIIIII.mc.field_1687, class_1268.field_5808, new class_3965(llIlIIIIIIIIII.mc.field_1724.method_19538(), class_2350.field_11036, llIlIIIIIIIIlI, true));
                    return;
                }
            }
            ++llIlIIIIIIIIIl;
        }
        llIlIIIIIIIIII.posList.clear();
    }
}

