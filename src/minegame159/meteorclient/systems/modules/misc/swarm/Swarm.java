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
    public final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Integer> serverPort;
    public /* synthetic */ SwarmHost host;
    public /* synthetic */ SwarmWorker worker;
    private final /* synthetic */ Setting<String> ipAddress;

    public Swarm() {
        super(Categories.Misc, "Swarm", "Allows you to control multiple instances of Meteor from one central host.");
        Swarm llllllllllllllllIllIlIIlIIIIlllI;
        llllllllllllllllIllIlIIlIIIIlllI.sgGeneral = llllllllllllllllIllIlIIlIIIIlllI.settings.getDefaultGroup();
        llllllllllllllllIllIlIIlIIIIlllI.mode = llllllllllllllllIllIlIIlIIIIlllI.sgGeneral.add(new EnumSetting.Builder().name("mode").description("What type of client to run.").defaultValue(Mode.Host).build());
        llllllllllllllllIllIlIIlIIIIlllI.ipAddress = llllllllllllllllIllIlIIlIIIIlllI.sgGeneral.add(new StringSetting.Builder().name("ip").description("The IP address of the host server.").defaultValue("localhost").visible(() -> {
            Swarm llllllllllllllllIllIlIIIllIIllll;
            return llllllllllllllllIllIlIIIllIIllll.mode.get() == Mode.Worker;
        }).build());
        llllllllllllllllIllIlIIlIIIIlllI.serverPort = llllllllllllllllIllIlIIlIIIIlllI.sgGeneral.add(new IntSetting.Builder().name("port").description("The port used for connections.").defaultValue(7777).sliderMin(1).sliderMax(65535).build());
    }

    @Override
    public void toggle() {
        Swarm llllllllllllllllIllIlIIIllIlllll;
        llllllllllllllllIllIlIIIllIlllll.close();
        super.toggle();
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllIllIlIIIllIlIllI) {
        Swarm llllllllllllllllIllIlIIIllIlIlIl;
        if (llllllllllllllllIllIlIIIllIlIlIl.isWorker()) {
            llllllllllllllllIllIlIIIllIlIlIl.worker.tick();
        }
    }

    @EventHandler
    private void onGameLeft(GameLeftEvent llllllllllllllllIllIlIIIlllIIlll) {
        Swarm llllllllllllllllIllIlIIIlllIlIII;
        llllllllllllllllIllIlIIIlllIlIII.toggle();
    }

    public boolean isHost() {
        Swarm llllllllllllllllIllIlIIIllIlllIl;
        return llllllllllllllllIllIlIIIllIlllIl.mode.get() == Mode.Host && llllllllllllllllIllIlIIIllIlllIl.host != null && !llllllllllllllllIllIlIIIllIlllIl.host.isInterrupted();
    }

    public boolean isWorker() {
        Swarm llllllllllllllllIllIlIIIllIllIIl;
        return llllllllllllllllIllIlIIIllIllIIl.mode.get() == Mode.Worker && llllllllllllllllIllIlIIIllIllIIl.worker != null && !llllllllllllllllIllIlIIIllIllIIl.worker.isInterrupted();
    }

    @Override
    public void onDeactivate() {
        Swarm llllllllllllllllIllIlIIIlllIllll;
        llllllllllllllllIllIlIIIlllIllll.close();
    }

    @Override
    public void onActivate() {
        Swarm llllllllllllllllIllIlIIIllllIIll;
        llllllllllllllllIllIlIIIllllIIll.close();
    }

    @EventHandler
    private void onGameJoin(GameJoinedEvent llllllllllllllllIllIlIIIlllIIIll) {
        Swarm llllllllllllllllIllIlIIIlllIIIlI;
        llllllllllllllllIllIlIIIlllIIIlI.toggle();
    }

    @Override
    public WWidget getWidget(GuiTheme llllllllllllllllIllIlIIIllllllIl) {
        Swarm llllllllllllllllIllIlIIlIIIIIlIl;
        WVerticalList llllllllllllllllIllIlIIlIIIIIIll = llllllllllllllllIllIlIIIllllllIl.verticalList();
        WHorizontalList llllllllllllllllIllIlIIlIIIIIIlI = llllllllllllllllIllIlIIlIIIIIIll.add(llllllllllllllllIllIlIIIllllllIl.horizontalList()).expandX().widget();
        WButton llllllllllllllllIllIlIIlIIIIIIIl = llllllllllllllllIllIlIIlIIIIIIlI.add(llllllllllllllllIllIlIIIllllllIl.button("Start")).expandX().widget();
        llllllllllllllllIllIlIIlIIIIIIIl.action = () -> {
            Swarm llllllllllllllllIllIlIIIllIlIIlI;
            if (!llllllllllllllllIllIlIIIllIlIIlI.isActive()) {
                return;
            }
            llllllllllllllllIllIlIIIllIlIIlI.close();
            if (llllllllllllllllIllIlIIIllIlIIlI.mode.get() == Mode.Host) {
                llllllllllllllllIllIlIIIllIlIIlI.host = new SwarmHost(llllllllllllllllIllIlIIIllIlIIlI.serverPort.get());
            } else {
                llllllllllllllllIllIlIIIllIlIIlI.worker = new SwarmWorker(llllllllllllllllIllIlIIIllIlIIlI.ipAddress.get(), llllllllllllllllIllIlIIIllIlIIlI.serverPort.get());
            }
        };
        WButton llllllllllllllllIllIlIIlIIIIIIII = llllllllllllllllIllIlIIlIIIIIIlI.add(llllllllllllllllIllIlIIIllllllIl.button("Stop")).expandX().widget();
        llllllllllllllllIllIlIIlIIIIIIII.action = llllllllllllllllIllIlIIlIIIIIlIl::close;
        WButton llllllllllllllllIllIlIIIllllllll = llllllllllllllllIllIlIIlIIIIIIll.add(llllllllllllllllIllIlIIIllllllIl.button("Guide")).expandX().widget();
        llllllllllllllllIllIlIIIllllllll.action = () -> Main.openUrl("https://github.com/MeteorDevelopment/meteor-client/wiki/Swarm-Guide");
        return llllllllllllllllIllIlIIlIIIIIIll;
    }

    @Override
    public String getInfoString() {
        Swarm llllllllllllllllIllIlIIIllllIllI;
        return llllllllllllllllIllIlIIIllllIllI.mode.get().name();
    }

    public void close() {
        try {
            Swarm llllllllllllllllIllIlIIIlllIllII;
            if (llllllllllllllllIllIlIIIlllIllII.host != null) {
                llllllllllllllllIllIlIIIlllIllII.host.disconnect();
                llllllllllllllllIllIlIIIlllIllII.host = null;
            }
            if (llllllllllllllllIllIlIIIlllIllII.worker != null) {
                llllllllllllllllIllIlIIIlllIllII.worker.disconnect();
                llllllllllllllllIllIlIIIlllIllII.worker = null;
            }
        }
        catch (Exception llllllllllllllllIllIlIIIlllIlIlI) {
            // empty catch block
        }
    }

    public static enum Mode {
        Host,
        Worker;


        private Mode() {
            Mode lllllllllllllllllIIllllIIllIIIIl;
        }
    }
}

