/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.misc;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Set;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.settings.PacketBoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2596;

public class PacketCanceller
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<Set<Class<? extends class_2596<?>>>> c2sPackets;
    private final Setting<Set<Class<? extends class_2596<?>>>> s2cPackets;

    public PacketCanceller() {
        super(Categories.Misc, "packet-canceller", "Allows you to cancel certain packets.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.s2cPackets = this.sgGeneral.add(new PacketBoolSetting.Builder().name("S2C-packets").description("Server-to-client packets to cancel.").defaultValue((Set<Class<? extends class_2596<?>>>)new ObjectOpenHashSet(0)).build());
        this.c2sPackets = this.sgGeneral.add(new PacketBoolSetting.Builder().name("C2S-packets").description("Client-to-server packets to cancel.").defaultValue((Set<Class<? extends class_2596<?>>>)new ObjectOpenHashSet(0)).build());
    }

    @EventHandler(priority=201)
    private void onSendPacket(PacketEvent.Send send) {
        if (this.c2sPackets.get().contains(send.packet.getClass())) {
            send.cancel();
        }
    }

    @EventHandler(priority=201)
    private void onReceivePacket(PacketEvent.Receive receive) {
        if (this.s2cPackets.get().contains(receive.packet.getClass())) {
            receive.cancel();
        }
    }
}

