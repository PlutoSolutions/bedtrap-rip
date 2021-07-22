/*
 * Decompiled with CFR 0.150.
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
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ SettingGroup sgGeneral;

    public boolean doPacket() {
        Sneak llllIIIIllIl;
        return llllIIIIllIl.isActive() && !Modules.get().isActive(Freecam.class) && llllIIIIllIl.mode.get() == Mode.Packet;
    }

    public boolean doVanilla() {
        Sneak llllIIIIlIlI;
        return llllIIIIlIlI.isActive() && !Modules.get().isActive(Freecam.class) && llllIIIIlIlI.mode.get() == Mode.Vanilla;
    }

    public Sneak() {
        super(Categories.Movement, "sneak", "Sneaks for you");
        Sneak llllIIIlIIIl;
        llllIIIlIIIl.sgGeneral = llllIIIlIIIl.settings.getDefaultGroup();
        llllIIIlIIIl.mode = llllIIIlIIIl.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Which method to sneak.").defaultValue(Mode.Vanilla).build());
    }

    public static enum Mode {
        Packet,
        Vanilla;


        private Mode() {
            Mode lllllllllllllllllIIIIllIllIIllll;
        }
    }
}

