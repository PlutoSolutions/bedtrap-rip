/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.misc;

import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class NameProtect
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<String> name;
    private String username;

    public String getName(String string) {
        if (this.name.get().length() > 0 && this.isActive()) {
            return this.name.get();
        }
        return string;
    }

    @Override
    public void onActivate() {
        this.username = this.mc.method_1548().method_1676();
    }

    public String replaceName(String string) {
        if (string.contains(this.username) && this.name.get().length() > 0 && this.isActive()) {
            return string.replace(this.username, this.name.get());
        }
        return string;
    }

    public NameProtect() {
        super(Categories.Player, "name-protect", "Hides your name client-side.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.name = this.sgGeneral.add(new StringSetting.Builder().name("name").description("Name to be replaced with.").defaultValue("seasnail").build());
        this.username = "If you see this, something is wrong.";
    }
}

