/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.screens;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.meteor.ModuleBindChangedEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WKeybind;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WSection;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.utils.Utils;

public class ModuleScreen
extends WindowScreen {
    private final Module module;
    private final WContainer settings;
    private final WKeybind keybind;

    public void method_25393() {
        super.method_25393();
        this.module.settings.tick(this.settings, this.theme);
    }

    private static void lambda$new$2(Module module, WCheckbox wCheckbox) {
        if (module.isActive() != wCheckbox.checked) {
            module.toggle(Utils.canUpdate());
        }
    }

    @EventHandler
    private void onModuleBindChanged(ModuleBindChangedEvent moduleBindChangedEvent) {
        this.keybind.reset();
    }

    private static void lambda$new$3(Module module, WCheckbox wCheckbox) {
        if (module.isVisible() != wCheckbox.checked) {
            module.setVisible(wCheckbox.checked);
        }
    }

    private static void lambda$new$0(Module module) {
        Modules.get().setModuleToBind(module);
    }

    private static void lambda$new$1(Module module, WCheckbox wCheckbox) {
        module.toggleOnBindRelease = wCheckbox.checked;
    }

    public ModuleScreen(GuiTheme guiTheme, Module module) {
        super(guiTheme, module.title);
        Object object;
        WWidget wWidget;
        this.module = module;
        this.add(guiTheme.label(module.description, (double)Utils.getWindowWidth() / 2.0));
        this.settings = this.add(guiTheme.verticalList()).expandX().widget();
        if (module.settings.groups.size() > 0) {
            this.settings.add(guiTheme.settings(module.settings)).expandX();
        }
        if ((wWidget = module.getWidget(guiTheme)) != null) {
            this.add(guiTheme.horizontalSeparator()).expandX();
            object = this.add(wWidget);
            if (wWidget instanceof WContainer) {
                ((Cell)object).expandX();
            }
        }
        object = this.add(guiTheme.section("Bind", true)).expandX().widget();
        this.keybind = ((WSection)object).add(guiTheme.keybind(module.keybind)).expandX().widget();
        this.keybind.actionOnSet = () -> ModuleScreen.lambda$new$0(module);
        WHorizontalList wHorizontalList = ((WSection)object).add(guiTheme.horizontalList()).widget();
        wHorizontalList.add(guiTheme.label("Toggle on bind release: "));
        WCheckbox wCheckbox = wHorizontalList.add(guiTheme.checkbox(module.toggleOnBindRelease)).widget();
        wCheckbox.action = () -> ModuleScreen.lambda$new$1(module, wCheckbox);
        this.add(guiTheme.horizontalSeparator()).expandX();
        WHorizontalList wHorizontalList2 = this.add(guiTheme.horizontalList()).expandX().widget();
        wHorizontalList2.add(guiTheme.label("Active: "));
        WCheckbox wCheckbox2 = wHorizontalList2.add(guiTheme.checkbox(module.isActive())).expandCellX().widget();
        wCheckbox2.action = () -> ModuleScreen.lambda$new$2(module, wCheckbox2);
        wHorizontalList2.add(guiTheme.label("Visible: "));
        WCheckbox wCheckbox3 = wHorizontalList2.add(guiTheme.checkbox(module.isVisible())).widget();
        wCheckbox3.action = () -> ModuleScreen.lambda$new$3(module, wCheckbox3);
    }
}

