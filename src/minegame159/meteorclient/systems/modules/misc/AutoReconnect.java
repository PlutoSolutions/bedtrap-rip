/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_642
 */
package minegame159.meteorclient.systems.modules.misc;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.ConnectToServerEvent;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_642;

public class AutoReconnect
extends Module {
    public final /* synthetic */ Setting<Double> time;
    private final /* synthetic */ SettingGroup sgGeneral;
    public /* synthetic */ class_642 lastServerInfo;

    public AutoReconnect() {
        super(Categories.Misc, "auto-reconnect", "Automatically reconnects when disconnected from a server.");
        AutoReconnect lIlllllIIIIIl;
        lIlllllIIIIIl.sgGeneral = lIlllllIIIIIl.settings.getDefaultGroup();
        lIlllllIIIIIl.time = lIlllllIIIIIl.sgGeneral.add(new DoubleSetting.Builder().name("delay").description("The amount of seconds to wait before reconnecting to the server.").defaultValue(3.5).min(0.0).decimalPlaces(1).build());
        MeteorClient.EVENT_BUS.subscribe(lIlllllIIIIIl.new StaticListener());
    }

    private class StaticListener {
        @EventHandler
        private void onConnectToServer(ConnectToServerEvent lIIlllIIllIIIll) {
            StaticListener lIIlllIIllIIIlI;
            lIIlllIIllIIIlI.AutoReconnect.this.lastServerInfo = lIIlllIIllIIIlI.AutoReconnect.this.mc.method_1542() ? null : lIIlllIIllIIIlI.AutoReconnect.this.mc.method_1558();
        }

        private StaticListener() {
            StaticListener lIIlllIIllIlIII;
        }
    }
}

