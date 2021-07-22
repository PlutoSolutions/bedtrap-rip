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
    private final List<class_2828> packets = new ArrayList<class_2828>();
    private int timer = 0;

    public Blink() {
        super(Categories.Movement, "blink", "Allows you to essentially teleport while suspending motion updates.");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onDeactivate() {
        List<class_2828> list = this.packets;
        synchronized (list) {
            this.packets.forEach(this::lambda$onDeactivate$0);
            this.packets.clear();
            this.timer = 0;
            return;
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        ++this.timer;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @EventHandler
    private void onSendPacket(PacketEvent.Send send) {
        if (!(send.packet instanceof class_2828)) {
            return;
        }
        send.cancel();
        List<class_2828> list = this.packets;
        synchronized (list) {
            class_2828 class_28282;
            class_2828 class_28283 = (class_2828)send.packet;
            class_2828 class_28284 = class_28282 = this.packets.size() == 0 ? null : this.packets.get(this.packets.size() - 1);
            if (class_28282 != null && class_28283.method_12273() == class_28282.method_12273() && class_28283.method_12271(-1.0f) == class_28282.method_12271(-1.0f) && class_28283.method_12270(-1.0f) == class_28282.method_12270(-1.0f) && class_28283.method_12269(-1.0) == class_28282.method_12269(-1.0) && class_28283.method_12268(-1.0) == class_28282.method_12268(-1.0) && class_28283.method_12274(-1.0) == class_28282.method_12274(-1.0)) {
                return;
            }
            this.packets.add(class_28283);
            return;
        }
    }

    private void lambda$onDeactivate$0(class_2828 class_28282) {
        this.mc.field_1724.field_3944.method_2883((class_2596)class_28282);
    }

    @Override
    public String getInfoString() {
        return String.format("%.1f", Float.valueOf((float)this.timer / 20.0f));
    }
}

