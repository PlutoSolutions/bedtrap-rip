/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2761
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2761;

public class TimeChanger
extends Module {
    private final /* synthetic */ Setting<Double> time;
    /* synthetic */ long oldTime;
    private final /* synthetic */ SettingGroup sgGeneral;

    public TimeChanger() {
        super(Categories.Render, "time-changer", "Makes you able to set a custom time.");
        TimeChanger llllllllllllllllIllIIlIIIlIlIIIl;
        llllllllllllllllIllIIlIIIlIlIIIl.sgGeneral = llllllllllllllllIllIIlIIIlIlIIIl.settings.getDefaultGroup();
        llllllllllllllllIllIIlIIIlIlIIIl.time = llllllllllllllllIllIIlIIIlIlIIIl.sgGeneral.add(new DoubleSetting.Builder().name("time").description("The specified time to be set.").defaultValue(0.0).sliderMin(-20000.0).sliderMax(20000.0).build());
    }

    @Override
    public void onDeactivate() {
        TimeChanger llllllllllllllllIllIIlIIIlIIlIlI;
        llllllllllllllllIllIIlIIIlIIlIlI.mc.field_1687.method_8435(llllllllllllllllIllIIlIIIlIIlIlI.oldTime);
    }

    @Override
    public void onActivate() {
        TimeChanger llllllllllllllllIllIIlIIIlIIlllI;
        llllllllllllllllIllIIlIIIlIIlllI.oldTime = llllllllllllllllIllIIlIIIlIIlllI.mc.field_1687.method_8510();
    }

    @EventHandler
    private void onPacketReceive(PacketEvent.Receive llllllllllllllllIllIIlIIIlIIIlII) {
        if (llllllllllllllllIllIIlIIIlIIIlII.packet instanceof class_2761) {
            llllllllllllllllIllIIlIIIlIIIlll.oldTime = ((class_2761)llllllllllllllllIllIIlIIIlIIIlII.packet).method_11871();
            llllllllllllllllIllIIlIIIlIIIlII.setCancelled(true);
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllIllIIlIIIlIIIIIl) {
        TimeChanger llllllllllllllllIllIIlIIIlIIIIII;
        llllllllllllllllIllIIlIIIlIIIIII.mc.field_1687.method_8435(llllllllllllllllIllIIlIIIlIIIIII.time.get().longValue());
    }
}

