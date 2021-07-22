/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
 *  net.minecraft.class_2596
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
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Set<Class<? extends class_2596<?>>>> c2sPackets;
    private final /* synthetic */ Setting<Set<Class<? extends class_2596<?>>>> s2cPackets;

    public PacketCanceller() {
        super(Categories.Misc, "packet-canceller", "Allows you to cancel certain packets.");
        PacketCanceller lllllllllllllllllIlIIlIlIIIllIII;
        lllllllllllllllllIlIIlIlIIIllIII.sgGeneral = lllllllllllllllllIlIIlIlIIIllIII.settings.getDefaultGroup();
        lllllllllllllllllIlIIlIlIIIllIII.s2cPackets = lllllllllllllllllIlIIlIlIIIllIII.sgGeneral.add(new PacketBoolSetting.Builder().name("S2C-packets").description("Server-to-client packets to cancel.").defaultValue((Set<Class<? extends class_2596<?>>>)new ObjectOpenHashSet(0)).build());
        lllllllllllllllllIlIIlIlIIIllIII.c2sPackets = lllllllllllllllllIlIIlIlIIIllIII.sgGeneral.add(new PacketBoolSetting.Builder().name("C2S-packets").description("Client-to-server packets to cancel.").defaultValue((Set<Class<? extends class_2596<?>>>)new ObjectOpenHashSet(0)).build());
    }

    @EventHandler(priority=201)
    private void onSendPacket(PacketEvent.Send lllllllllllllllllIlIIlIlIIIIllIl) {
        PacketCanceller lllllllllllllllllIlIIlIlIIIIlllI;
        if (lllllllllllllllllIlIIlIlIIIIlllI.c2sPackets.get().contains(lllllllllllllllllIlIIlIlIIIIllIl.packet.getClass())) {
            lllllllllllllllllIlIIlIlIIIIllIl.cancel();
        }
    }

    @EventHandler(priority=201)
    private void onReceivePacket(PacketEvent.Receive lllllllllllllllllIlIIlIlIIIlIIll) {
        PacketCanceller lllllllllllllllllIlIIlIlIIIlIlII;
        if (lllllllllllllllllIlIIlIlIIIlIlII.s2cPackets.get().contains(lllllllllllllllllIlIIlIlIIIlIIll.packet.getClass())) {
            lllllllllllllllllIlIIlIlIIIlIIll.cancel();
        }
    }
}

