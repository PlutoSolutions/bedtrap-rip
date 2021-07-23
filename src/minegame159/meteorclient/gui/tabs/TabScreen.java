/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.tabs;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;

public class TabScreen
extends WidgetScreen {
    public final Tab tab;

    public TabScreen(GuiTheme guiTheme, Tab tab) {
        super(guiTheme, tab.name);
        this.tab = tab;
    }

    public <T extends WWidget> Cell<T> addDirect(T t) {
        return super.add(t);
    }
}

