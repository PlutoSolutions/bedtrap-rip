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
    private final /* synthetic */ WWindow window;

    @Override
    public <W extends WWidget> Cell<W> add(W llllllllllllllllllllIlIIIIIlllII) {
        WindowScreen llllllllllllllllllllIlIIIIIlllll;
        return llllllllllllllllllllIlIIIIIlllll.window.add(llllllllllllllllllllIlIIIIIlllII);
    }

    public WindowScreen(GuiTheme llllllllllllllllllllIlIIIIlIIllI, String llllllllllllllllllllIlIIIIlIIlIl) {
        super(llllllllllllllllllllIlIIIIlIIllI, llllllllllllllllllllIlIIIIlIIlIl);
        WindowScreen llllllllllllllllllllIlIIIIlIIlll;
        llllllllllllllllllllIlIIIIlIIlll.window = super.add(llllllllllllllllllllIlIIIIlIIllI.window(llllllllllllllllllllIlIIIIlIIlIl)).center().widget();
        llllllllllllllllllllIlIIIIlIIlll.window.view.scrollOnlyWhenMouseOver = false;
    }

    @Override
    public void clear() {
        WindowScreen llllllllllllllllllllIlIIIIIllIIl;
        llllllllllllllllllllIlIIIIIllIIl.window.clear();
    }
}

