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
    protected WWidget getValueWidget(Module module) {
        return this.theme.label(this.getValueName(module));
    }

    @Override
    protected String getValueName(Module module) {
        return module.title;
    }

    @Override
    protected String getValueName(Object object) {
        return this.getValueName((Module)object);
    }

    @Override
    protected WWidget getValueWidget(Object object) {
        return this.getValueWidget((Module)object);
    }

    public ModuleListSettingScreen(GuiTheme guiTheme, Setting<List<Module>> setting) {
        super(guiTheme, "Select modules", setting, (Collection)setting.get(), Modules.REGISTRY);
    }
}

