/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828
 */
package minegame159.meteorclient.systems.modules.movement;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2596;
import net.minecraft.class_2828;

public class Blink
extends Module {
    private final /* synthetic */ List<class_2828> packets;
    private /* synthetic */ int timer;

    public Blink() {
        super(Categories.Movement, "blink", "Allows you to essentially teleport while suspending motion updates.");
        Blink lllllllllllllllllllIIIllllIlllII;
        lllllllllllllllllllIIIllllIlllII.packets = new ArrayList<class_2828>();
        lllllllllllllllllllIIIllllIlllII.timer = 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onDeactivate() {
        Blink lllllllllllllllllllIIIllllIlIlll;
        List<class_2828> lllllllllllllllllllIIIllllIlIllI = lllllllllllllllllllIIIllllIlIlll.packets;
        synchronized (lllllllllllllllllllIIIllllIlIllI) {
            lllllllllllllllllllIIIllllIlIlll.packets.forEach(lllllllllllllllllllIIIlllIlllIII -> {
                Blink lllllllllllllllllllIIIlllIlllIll;
                lllllllllllllllllllIIIlllIlllIll.mc.field_1724.field_3944.method_2883((class_2596)lllllllllllllllllllIIIlllIlllIII);
            });
            lllllllllllllllllllIIIllllIlIlll.packets.clear();
            lllllllllllllllllllIIIllllIlIlll.timer = 0;
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllllIIIllllIlIIlI) {
        Blink lllllllllllllllllllIIIllllIlIIIl;
        ++lllllllllllllllllllIIIllllIlIIIl.timer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    private void onSendPacket(PacketEvent.Send lllllllllllllllllllIIIllllIIIlll) {
        Blink lllllllllllllllllllIIIllllIIlIII;
        if (!(lllllllllllllllllllIIIllllIIIlll.packet instanceof class_2828)) {
            return;
        }
        lllllllllllllllllllIIIllllIIIlll.cancel();
        List<class_2828> lllllllllllllllllllIIIllllIIIlII = lllllllllllllllllllIIIllllIIlIII.packets;
        synchronized (lllllllllllllllllllIIIllllIIIlII) {
            class_2828 lllllllllllllllllllIIIllllIIlIIl;
            class_2828 lllllllllllllllllllIIIllllIIlIlI = (class_2828)lllllllllllllllllllIIIllllIIIlll.packet;
            class_2828 class_28282 = lllllllllllllllllllIIIllllIIlIIl = lllllllllllllllllllIIIllllIIlIII.packets.size() == 0 ? null : lllllllllllllllllllIIIllllIIlIII.packets.get(lllllllllllllllllllIIIllllIIlIII.packets.size() - 1);
            if (lllllllllllllllllllIIIllllIIlIIl != null && lllllllllllllllllllIIIllllIIlIlI.method_12273() == lllllllllllllllllllIIIllllIIlIIl.method_12273() && lllllllllllllllllllIIIllllIIlIlI.method_12271(-1.0f) == lllllllllllllllllllIIIllllIIlIIl.method_12271(-1.0f) && lllllllllllllllllllIIIllllIIlIlI.method_12270(-1.0f) == lllllllllllllllllllIIIllllIIlIIl.method_12270(-1.0f) && lllllllllllllllllllIIIllllIIlIlI.method_12269(-1.0) == lllllllllllllllllllIIIllllIIlIIl.method_12269(-1.0) && lllllllllllllllllllIIIllllIIlIlI.method_12268(-1.0) == lllllllllllllllllllIIIllllIIlIIl.method_12268(-1.0) && lllllllllllllllllllIIIllllIIlIlI.method_12274(-1.0) == lllllllllllllllllllIIIllllIIlIIl.method_12274(-1.0)) {
                return;
            }
            lllllllllllllllllllIIIllllIIlIII.packets.add(lllllllllllllllllllIIIllllIIlIlI);
        }
    }

    @Override
    public String getInfoString() {
        Blink lllllllllllllllllllIIIlllIlllllI;
        return String.format("%.1f", Float.valueOf((float)lllllllllllllllllllIIIlllIlllllI.timer / 20.0f));
    }
}

