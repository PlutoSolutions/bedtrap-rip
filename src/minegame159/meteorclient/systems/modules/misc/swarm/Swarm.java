/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.misc.swarm;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.Main;
import minegame159.meteorclient.events.game.GameJoinedEvent;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WVerticalList;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.misc.swarm.SwarmHost;
import minegame159.meteorclient.systems.modules.misc.swarm.SwarmWorker;

public class Swarm
extends Module {
    public final Setting<Mode> mode;
    private final SettingGroup sgGeneral;
    private final Setting<Integer> serverPort;
    public SwarmHost host;
    public SwarmWorker worker;
    private final Setting<String> ipAddress;

    private void lambda$getWidget$1() {
        if (!this.isActive()) {
            return;
        }
        this.close();
        if (this.mode.get() == Mode.Host) {
            this.host = new SwarmHost(this.serverPort.get());
        } else {
            this.worker = new SwarmWorker(this.ipAddress.get(), this.serverPort.get());
        }
    }

    public Swarm() {
        super(Categories.Misc, "Swarm", "Allows you to control multiple instances of Meteor from one central host.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("What type of client to run.").defaultValue(Mode.Host).build());
        this.ipAddress = this.sgGeneral.add(new StringSetting.Builder().name("ip").description("The IP address of the host server.").defaultValue("localhost").visible(this::lambda$new$0).build());
        this.serverPort = this.sgGeneral.add(new IntSetting.Builder().name("port").description("The port used for connections.").defaultValue(7777).sliderMin(1).sliderMax(65535).build());
    }

    private boolean lambda$new$0() {
        return this.mode.get() == Mode.Worker;
    }

    private static void lambda$getWidget$2() {
        Main.openUrl("https://github.com/MeteorDevelopment/meteor-client/wiki/Swarm-Guide");
    }

    @Override
    public void toggle() {
        this.close();
        super.toggle();
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.isWorker()) {
            this.worker.tick();
        }
    }

    @EventHandler
    private void onGameLeft(GameLeftEvent gameLeftEvent) {
        this.toggle();
    }

    public boolean isHost() {
        return this.mode.get() == Mode.Host && this.host != null && !this.host.isInterrupted();
    }

    public boolean isWorker() {
        return this.mode.get() == Mode.Worker && this.worker != null && !this.worker.isInterrupted();
    }

    @Override
    public void onDeactivate() {
        this.close();
    }

    @Override
    public void onActivate() {
        this.close();
    }

    @EventHandler
    private void onGameJoin(GameJoinedEvent gameJoinedEvent) {
        this.toggle();
    }

    @Override
    public WWidget getWidget(GuiTheme guiTheme) {
        WVerticalList wVerticalList = guiTheme.verticalList();
        WHorizontalList wHorizontalList = wVerticalList.add(guiTheme.horizontalList()).expandX().widget();
        WButton wButton = wHorizontalList.add(guiTheme.button("Start")).expandX().widget();
        wButton.action = this::lambda$getWidget$1;
        WButton wButton2 = wHorizontalList.add(guiTheme.button("Stop")).expandX().widget();
        wButton2.action = this::close;
        WButton wButton3 = wVerticalList.add(guiTheme.button("Guide")).expandX().widget();
        wButton3.action = Swarm::lambda$getWidget$2;
        return wVerticalList;
    }

    @Override
    public String getInfoString() {
        return this.mode.get().name();
    }

    public void close() {
        try {
            if (this.host != null) {
                this.host.disconnect();
                this.host = null;
            }
            if (this.worker != null) {
                this.worker.disconnect();
                this.worker = null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static enum Mode {
        Host,
        Worker;

    }
}

