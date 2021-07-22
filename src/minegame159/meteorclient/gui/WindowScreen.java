/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WWindow;

public abstract class WindowScreen
extends WidgetScreen {
    private final WWindow window;

    @Override
    public <W extends WWidget> Cell<W> add(W w) {
        return this.window.add(w);
    }

    public WindowScreen(GuiTheme guiTheme, String string) {
        super(guiTheme, string);
        this.window = super.add(guiTheme.window(string)).center().widget();
        this.window.view.scrollOnlyWhenMouseOver = false;
    }

    @Override
    public void clear() {
        this.window.clear();
    }
}

