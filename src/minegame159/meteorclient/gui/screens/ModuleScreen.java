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
    private final /* synthetic */ Module module;
    private final /* synthetic */ WContainer settings;
    private final /* synthetic */ WKeybind keybind;

    public void method_25393() {
        ModuleScreen llllllllllllllllIllllIIllIlIIlll;
        super.method_25393();
        llllllllllllllllIllllIIllIlIIlll.module.settings.tick(llllllllllllllllIllllIIllIlIIlll.settings, llllllllllllllllIllllIIllIlIIlll.theme);
    }

    @EventHandler
    private void onModuleBindChanged(ModuleBindChangedEvent llllllllllllllllIllllIIllIlIIlII) {
        ModuleScreen llllllllllllllllIllllIIllIlIIIll;
        llllllllllllllllIllllIIllIlIIIll.keybind.reset();
    }

    public ModuleScreen(GuiTheme llllllllllllllllIllllIIllIllllII, Module llllllllllllllllIllllIIllIllIIIl) {
        super(llllllllllllllllIllllIIllIllllII, llllllllllllllllIllllIIllIllIIIl.title);
        WWidget llllllllllllllllIllllIIllIlllIlI;
        ModuleScreen llllllllllllllllIllllIIllIllIIll;
        llllllllllllllllIllllIIllIllIIll.module = llllllllllllllllIllllIIllIllIIIl;
        llllllllllllllllIllllIIllIllIIll.add(llllllllllllllllIllllIIllIllllII.label(llllllllllllllllIllllIIllIllIIIl.description, (double)Utils.getWindowWidth() / 2.0));
        llllllllllllllllIllllIIllIllIIll.settings = llllllllllllllllIllllIIllIllIIll.add(llllllllllllllllIllllIIllIllllII.verticalList()).expandX().widget();
        if (llllllllllllllllIllllIIllIllIIIl.settings.groups.size() > 0) {
            llllllllllllllllIllllIIllIllIIll.settings.add(llllllllllllllllIllllIIllIllllII.settings(llllllllllllllllIllllIIllIllIIIl.settings)).expandX();
        }
        if ((llllllllllllllllIllllIIllIlllIlI = llllllllllllllllIllllIIllIllIIIl.getWidget(llllllllllllllllIllllIIllIllllII)) != null) {
            llllllllllllllllIllllIIllIllIIll.add(llllllllllllllllIllllIIllIllllII.horizontalSeparator()).expandX();
            Cell<WWidget> llllllllllllllllIllllIIllIlllllI = llllllllllllllllIllllIIllIllIIll.add(llllllllllllllllIllllIIllIlllIlI);
            if (llllllllllllllllIllllIIllIlllIlI instanceof WContainer) {
                llllllllllllllllIllllIIllIlllllI.expandX();
            }
        }
        WSection llllllllllllllllIllllIIllIlllIIl = llllllllllllllllIllllIIllIllIIll.add(llllllllllllllllIllllIIllIllllII.section("Bind", true)).expandX().widget();
        llllllllllllllllIllllIIllIllIIll.keybind = llllllllllllllllIllllIIllIlllIIl.add(llllllllllllllllIllllIIllIllllII.keybind(llllllllllllllllIllllIIllIllIIIl.keybind)).expandX().widget();
        llllllllllllllllIllllIIllIllIIll.keybind.actionOnSet = () -> Modules.get().setModuleToBind(llllllllllllllllIllllIIllIllIIIl);
        WHorizontalList llllllllllllllllIllllIIllIlllIII = llllllllllllllllIllllIIllIlllIIl.add(llllllllllllllllIllllIIllIllllII.horizontalList()).widget();
        llllllllllllllllIllllIIllIlllIII.add(llllllllllllllllIllllIIllIllllII.label("Toggle on bind release: "));
        WCheckbox llllllllllllllllIllllIIllIllIlll = llllllllllllllllIllllIIllIlllIII.add(llllllllllllllllIllllIIllIllllII.checkbox(llllllllllllllllIllllIIllIllIIIl.toggleOnBindRelease)).widget();
        llllllllllllllllIllllIIllIllIlll.action = () -> {
            llllllllllllllllIllllIIllIIlIIlI.toggleOnBindRelease = llllllllllllllllIllllIIllIIlIIll.checked;
        };
        llllllllllllllllIllllIIllIllIIll.add(llllllllllllllllIllllIIllIllllII.horizontalSeparator()).expandX();
        WHorizontalList llllllllllllllllIllllIIllIllIllI = llllllllllllllllIllllIIllIllIIll.add(llllllllllllllllIllllIIllIllllII.horizontalList()).expandX().widget();
        llllllllllllllllIllllIIllIllIllI.add(llllllllllllllllIllllIIllIllllII.label("Active: "));
        WCheckbox llllllllllllllllIllllIIllIllIlIl = llllllllllllllllIllllIIllIllIllI.add(llllllllllllllllIllllIIllIllllII.checkbox(llllllllllllllllIllllIIllIllIIIl.isActive())).expandCellX().widget();
        llllllllllllllllIllllIIllIllIlIl.action = () -> {
            if (llllllllllllllllIllllIIllIllIIIl.isActive() != llllllllllllllllIllllIIllIIlIlll.checked) {
                llllllllllllllllIllllIIllIllIIIl.toggle(Utils.canUpdate());
            }
        };
        llllllllllllllllIllllIIllIllIllI.add(llllllllllllllllIllllIIllIllllII.label("Visible: "));
        WCheckbox llllllllllllllllIllllIIllIllIlII = llllllllllllllllIllllIIllIllIllI.add(llllllllllllllllIllllIIllIllllII.checkbox(llllllllllllllllIllllIIllIllIIIl.isVisible())).widget();
        llllllllllllllllIllllIIllIllIlII.action = () -> {
            if (llllllllllllllllIllllIIllIllIIIl.isVisible() != llllllllllllllllIllllIIllIIlllIl.checked) {
                llllllllllllllllIllllIIllIllIIIl.setVisible(llllllllllllllllIllllIIllIIlllIl.checked);
            }
        };
    }
}

