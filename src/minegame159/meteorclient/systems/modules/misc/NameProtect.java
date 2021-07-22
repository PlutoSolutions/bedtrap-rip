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
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<String> name;
    private /* synthetic */ String username;

    public String getName(String llllllllllllllllIlIlIllIIIlIllIl) {
        NameProtect llllllllllllllllIlIlIllIIIlIllII;
        if (llllllllllllllllIlIlIllIIIlIllII.name.get().length() > 0 && llllllllllllllllIlIlIllIIIlIllII.isActive()) {
            return llllllllllllllllIlIlIllIIIlIllII.name.get();
        }
        return llllllllllllllllIlIlIllIIIlIllIl;
    }

    @Override
    public void onActivate() {
        NameProtect llllllllllllllllIlIlIllIIIllIlll;
        llllllllllllllllIlIlIllIIIllIlll.username = llllllllllllllllIlIlIllIIIllIlll.mc.method_1548().method_1676();
    }

    public String replaceName(String llllllllllllllllIlIlIllIIIllIIIl) {
        NameProtect llllllllllllllllIlIlIllIIIllIlII;
        if (llllllllllllllllIlIlIllIIIllIIIl.contains(llllllllllllllllIlIlIllIIIllIlII.username) && llllllllllllllllIlIlIllIIIllIlII.name.get().length() > 0 && llllllllllllllllIlIlIllIIIllIlII.isActive()) {
            return llllllllllllllllIlIlIllIIIllIIIl.replace(llllllllllllllllIlIlIllIIIllIlII.username, llllllllllllllllIlIlIllIIIllIlII.name.get());
        }
        return llllllllllllllllIlIlIllIIIllIIIl;
    }

    public NameProtect() {
        super(Categories.Player, "name-protect", "Hides your name client-side.");
        NameProtect llllllllllllllllIlIlIllIIIlllIll;
        llllllllllllllllIlIlIllIIIlllIll.sgGeneral = llllllllllllllllIlIlIllIIIlllIll.settings.getDefaultGroup();
        llllllllllllllllIlIlIllIIIlllIll.name = llllllllllllllllIlIlIllIIIlllIll.sgGeneral.add(new StringSetting.Builder().name("name").description("Name to be replaced with.").defaultValue("seasnail").build());
        llllllllllllllllIlIlIllIIIlllIll.username = "If you see this, something is wrong.";
    }
}

