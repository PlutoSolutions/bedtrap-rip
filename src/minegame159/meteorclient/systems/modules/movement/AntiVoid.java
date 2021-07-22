/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.Flight;
import minegame159.meteorclient.utils.Utils;

public class AntiVoid
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ boolean hasRun;
    private /* synthetic */ boolean wasFlightEnabled;

    @Override
    public void onActivate() {
        AntiVoid lllllllllllllllllIllIIlIIlIIlIII;
        if (lllllllllllllllllIllIIlIIlIIlIII.mode.get() == Mode.Flight) {
            lllllllllllllllllIllIIlIIlIIlIII.wasFlightEnabled = Modules.get().isActive(Flight.class);
        }
    }

    public AntiVoid() {
        super(Categories.Movement, "anti-void", "Attempts to prevent you from falling into the void.");
        AntiVoid lllllllllllllllllIllIIlIIlIIlIlI;
        lllllllllllllllllIllIIlIIlIIlIlI.sgGeneral = lllllllllllllllllIllIIlIIlIIlIlI.settings.getDefaultGroup();
        lllllllllllllllllIllIIlIIlIIlIlI.mode = lllllllllllllllllIllIIlIIlIIlIlI.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The method to prevent you from falling into the void.").defaultValue(Mode.Jump).onChanged(lllllllllllllllllIllIIlIIIllllIl -> {
            AntiVoid lllllllllllllllllIllIIlIIIllllII;
            lllllllllllllllllIllIIlIIIllllII.onActivate();
        }).build());
    }

    @EventHandler
    public void onPreTick(TickEvent.Pre lllllllllllllllllIllIIlIIlIIIIIl) {
        AntiVoid lllllllllllllllllIllIIlIIlIIIIlI;
        if (lllllllllllllllllIllIIlIIlIIIIlI.mc.field_1724.method_23318() > 0.0 || lllllllllllllllllIllIIlIIlIIIIlI.mc.field_1724.method_23318() < -15.0) {
            if (lllllllllllllllllIllIIlIIlIIIIlI.hasRun && lllllllllllllllllIllIIlIIlIIIIlI.mode.get() == Mode.Flight && Modules.get().isActive(Flight.class)) {
                Modules.get().get(Flight.class).toggle();
                lllllllllllllllllIllIIlIIlIIIIlI.hasRun = false;
            }
            return;
        }
        switch (lllllllllllllllllIllIIlIIlIIIIlI.mode.get()) {
            case Flight: {
                if (!Modules.get().isActive(Flight.class)) {
                    Modules.get().get(Flight.class).toggle();
                }
                lllllllllllllllllIllIIlIIlIIIIlI.hasRun = true;
                break;
            }
            case Jump: {
                lllllllllllllllllIllIIlIIlIIIIlI.mc.field_1724.method_6043();
            }
        }
    }

    @Override
    public void onDeactivate() {
        AntiVoid lllllllllllllllllIllIIlIIlIIIlIl;
        if (!lllllllllllllllllIllIIlIIlIIIlIl.wasFlightEnabled && lllllllllllllllllIllIIlIIlIIIlIl.mode.get() == Mode.Flight && Utils.canUpdate() && Modules.get().isActive(Flight.class)) {
            Modules.get().get(Flight.class).toggle();
        }
    }

    public static enum Mode {
        Flight,
        Jump;


        private Mode() {
            Mode llIIIllIIllII;
        }
    }
}

