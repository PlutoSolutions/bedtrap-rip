/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.NameProtect;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;
import minegame159.meteorclient.utils.render.color.SettingColor;

public class WelcomeHud
extends DoubleTextHudElement {
    private final Setting<String> msg;
    private final SettingGroup sgGeneral;
    private final Setting<SettingColor> color;

    @Override
    protected String getRight() {
        return this.msg.get().replace("{player}", Modules.get().get(NameProtect.class).getName(this.mc.method_1548().method_1676()));
    }

    public WelcomeHud(HUD hUD) {
        super(hUD, "welcome", "Displays a welcome message.", "");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.color = this.sgGeneral.add(new ColorSetting.Builder().name("color").description("Color of welcome text.").defaultValue(new SettingColor(120, 43, 153)).build());
        this.msg = this.sgGeneral.add(new StringSetting.Builder().name("message").description("The message to display on the hud.").defaultValue("Welcome to BedTrap, {player}!").build());
        this.rightColor = this.color.get();
    }
}

