/*
 * Decompiled with CFR 0.150.
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
    private final /* synthetic */ Setting<String> msg;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<SettingColor> color;

    @Override
    protected String getRight() {
        WelcomeHud lIIlllllllIl;
        return lIIlllllllIl.msg.get().replace("{player}", Modules.get().get(NameProtect.class).getName(lIIlllllllIl.mc.method_1548().method_1676()));
    }

    public WelcomeHud(HUD lIlIIIIIIIlI) {
        super(lIlIIIIIIIlI, "welcome", "Displays a welcome message.", "");
        WelcomeHud lIlIIIIIIIll;
        lIlIIIIIIIll.sgGeneral = lIlIIIIIIIll.settings.getDefaultGroup();
        lIlIIIIIIIll.color = lIlIIIIIIIll.sgGeneral.add(new ColorSetting.Builder().name("color").description("Color of welcome text.").defaultValue(new SettingColor(120, 43, 153)).build());
        lIlIIIIIIIll.msg = lIlIIIIIIIll.sgGeneral.add(new StringSetting.Builder().name("message").description("The message to display on the hud.").defaultValue("Welcome to BedTrap, {player}!").build());
        lIlIIIIIIIll.rightColor = lIlIIIIIIIll.color.get();
    }
}

