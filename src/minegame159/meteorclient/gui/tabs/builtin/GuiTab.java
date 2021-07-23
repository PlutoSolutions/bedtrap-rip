/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.tabs.builtin;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.GuiThemes;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.tabs.WindowTabScreen;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WDropdown;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_437;

public class GuiTab
extends Tab {
    @Override
    public TabScreen createScreen(GuiTheme guiTheme) {
        return new GuiScreen(guiTheme, this);
    }

    public GuiTab() {
        super("GUI");
    }

    @Override
    public boolean isScreen(class_437 class_4372) {
        return class_4372 instanceof GuiScreen;
    }

    private static class GuiScreen
    extends WindowTabScreen {
        public GuiScreen(GuiTheme guiTheme, Tab tab) {
            super(guiTheme, tab);
            WTable wTable = this.add(guiTheme.table()).expandX().widget();
            wTable.add(guiTheme.label("Theme:"));
            WDropdown<String> wDropdown = wTable.add(guiTheme.dropdown(GuiThemes.getNames(), GuiThemes.get().name)).widget();
            wDropdown.action = () -> GuiScreen.lambda$new$0(wDropdown, tab);
            guiTheme.settings.onActivated();
            this.add(guiTheme.settings(guiTheme.settings)).expandX();
        }

        private static void lambda$new$0(WDropdown wDropdown, Tab tab) {
            GuiThemes.select((String)wDropdown.get());
            Utils.mc.method_1507(null);
            tab.openScreen(GuiThemes.get());
        }
    }
}

