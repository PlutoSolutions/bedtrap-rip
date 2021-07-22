/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.screens.settings;

import java.util.Collection;
import java.util.List;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.screens.settings.LeftRightListSettingScreen;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;

public class ModuleListSettingScreen
extends LeftRightListSettingScreen<Module> {
    @Override
    protected WWidget getValueWidget(Module llllllllllllllllllIlIIlIlllIIIIl) {
        ModuleListSettingScreen llllllllllllllllllIlIIlIlllIIIlI;
        return llllllllllllllllllIlIIlIlllIIIlI.theme.label(llllllllllllllllllIlIIlIlllIIIlI.getValueName(llllllllllllllllllIlIIlIlllIIIIl));
    }

    @Override
    protected String getValueName(Module llllllllllllllllllIlIIlIllIlllII) {
        return llllllllllllllllllIlIIlIllIlllII.title;
    }

    public ModuleListSettingScreen(GuiTheme llllllllllllllllllIlIIlIlllIIllI, Setting<List<Module>> llllllllllllllllllIlIIlIlllIIlIl) {
        super(llllllllllllllllllIlIIlIlllIIllI, "Select modules", llllllllllllllllllIlIIlIlllIIlIl, (Collection)llllllllllllllllllIlIIlIlllIIlIl.get(), Modules.REGISTRY);
        ModuleListSettingScreen llllllllllllllllllIlIIlIlllIIlll;
    }
}

