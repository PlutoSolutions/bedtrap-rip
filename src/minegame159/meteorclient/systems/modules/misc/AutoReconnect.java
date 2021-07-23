/*
 * Decompiled with CFR 0.151.
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
import net.minecraft.class_310;
import net.minecraft.class_642;

public class AutoReconnect
extends Module {
    public final Setting<Double> time;
    private final SettingGroup sgGeneral;
    public class_642 lastServerInfo;

    static class_310 access$200(AutoReconnect autoReconnect) {
        return autoReconnect.mc;
    }

    static class_310 access$100(AutoReconnect autoReconnect) {
        return autoReconnect.mc;
    }

    public AutoReconnect() {
        super(Categories.Misc, "auto-reconnect", "Automatically reconnects when disconnected from a server.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.time = this.sgGeneral.add(new DoubleSetting.Builder().name("delay").description("The amount of seconds to wait before reconnecting to the server.").defaultValue(3.5).min(0.0).decimalPlaces(1).build());
        MeteorClient.EVENT_BUS.subscribe(new StaticListener(this, null));
    }

    private class StaticListener {
        final AutoReconnect this$0;

        @EventHandler
        private void onConnectToServer(ConnectToServerEvent connectToServerEvent) {
            this.this$0.lastServerInfo = AutoReconnect.access$100(this.this$0).method_1542() ? null : AutoReconnect.access$200(this.this$0).method_1558();
        }

        private StaticListener(AutoReconnect autoReconnect) {
            this.this$0 = autoReconnect;
        }

        StaticListener(AutoReconnect autoReconnect, 1 var2_2) {
            this(autoReconnect);
        }
    }
}

