/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.tabs;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WWindow;

public class WindowTabScreen
extends TabScreen {
    private final WWindow window;

    @Override
    public void clear() {
        this.window.clear();
    }

    public WindowTabScreen(GuiTheme guiTheme, Tab tab) {
        super(guiTheme, tab);
        this.window = super.add(guiTheme.window(tab.name)).center().widget();
    }

    @Override
    public <W extends WWidget> Cell<W> add(W w) {
        return this.window.add(w);
    }
}

