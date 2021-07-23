/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.movement;

import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Freecam;

public class Sneak
extends Module {
    private final Setting<Mode> mode;
    private final SettingGroup sgGeneral;

    public boolean doPacket() {
        return this.isActive() && !Modules.get().isActive(Freecam.class) && this.mode.get() == Mode.Packet;
    }

    public boolean doVanilla() {
        return this.isActive() && !Modules.get().isActive(Freecam.class) && this.mode.get() == Mode.Vanilla;
    }

    public Sneak() {
        super(Categories.Movement, "sneak", "Sneaks for you");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Which method to sneak.").defaultValue(Mode.Vanilla).build());
    }

    public static enum Mode {
        Packet,
        Vanilla;

    }
}

