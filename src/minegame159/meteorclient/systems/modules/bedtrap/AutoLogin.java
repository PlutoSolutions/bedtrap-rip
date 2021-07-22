/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2635
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2635;

public class AutoLogin
extends Module {
    private final SettingGroup autoLogin;
    private final Setting<String> loginText;

    @EventHandler
    public void onPacket(PacketEvent.Receive receive) {
        String[] arrstring;
        if (!(receive.packet instanceof class_2635)) {
            return;
        }
        String string = ((class_2635)receive.packet).method_11388().getString();
        for (String string2 : arrstring = new String[]{"/l"}) {
            if (!string.contains(string2)) continue;
            this.mc.field_1724.method_3142(String.valueOf(new StringBuilder().append("/login ").append(this.loginText)));
            break;
        }
    }

    public AutoLogin() {
        super(Categories.BedTrap, "auto-login", "Sends /login on cracked servers.");
        this.autoLogin = this.settings.createGroup("Auto Login");
        this.loginText = this.autoLogin.add(new StringSetting.Builder().name("password").description("/login *pass*").defaultValue("qwerty").build());
    }
}

