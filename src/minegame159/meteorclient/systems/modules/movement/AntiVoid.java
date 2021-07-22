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
    private final SettingGroup sgGeneral;
    private final Setting<Mode> mode;
    private boolean hasRun;
    private boolean wasFlightEnabled;

    @Override
    public void onActivate() {
        if (this.mode.get() == Mode.Flight) {
            this.wasFlightEnabled = Modules.get().isActive(Flight.class);
        }
    }

    public AntiVoid() {
        super(Categories.Movement, "anti-void", "Attempts to prevent you from falling into the void.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The method to prevent you from falling into the void.").defaultValue(Mode.Jump).onChanged(this::lambda$new$0).build());
    }

    @EventHandler
    public void onPreTick(TickEvent.Pre pre) {
        if (this.mc.field_1724.method_23318() > 0.0 || this.mc.field_1724.method_23318() < -15.0) {
            if (this.hasRun && this.mode.get() == Mode.Flight && Modules.get().isActive(Flight.class)) {
                Modules.get().get(Flight.class).toggle();
                this.hasRun = false;
            }
            return;
        }
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$movement$AntiVoid$Mode[this.mode.get().ordinal()]) {
            case 1: {
                if (!Modules.get().isActive(Flight.class)) {
                    Modules.get().get(Flight.class).toggle();
                }
                this.hasRun = true;
                break;
            }
            case 2: {
                this.mc.field_1724.method_6043();
            }
        }
    }

    private void lambda$new$0(Mode mode) {
        this.onActivate();
    }

    @Override
    public void onDeactivate() {
        if (!this.wasFlightEnabled && this.mode.get() == Mode.Flight && Utils.canUpdate() && Modules.get().isActive(Flight.class)) {
            Modules.get().get(Flight.class).toggle();
        }
    }

    public static enum Mode {
        Flight,
        Jump;

    }
}

