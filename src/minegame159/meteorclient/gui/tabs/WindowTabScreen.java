/*
 * Decompiled with CFR 0.150.
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
    private final /* synthetic */ WWindow window;

    @Override
    public void clear() {
        WindowTabScreen llllllllllllllllIlllllIlIIIlIIlI;
        llllllllllllllllIlllllIlIIIlIIlI.window.clear();
    }

    public WindowTabScreen(GuiTheme llllllllllllllllIlllllIlIIIlllII, Tab llllllllllllllllIlllllIlIIIllIll) {
        super(llllllllllllllllIlllllIlIIIlllII, llllllllllllllllIlllllIlIIIllIll);
        WindowTabScreen llllllllllllllllIlllllIlIIIlllIl;
        llllllllllllllllIlllllIlIIIlllIl.window = super.add(llllllllllllllllIlllllIlIIIlllII.window(llllllllllllllllIlllllIlIIIllIll.name)).center().widget();
    }

    @Override
    public <W extends WWidget> Cell<W> add(W llllllllllllllllIlllllIlIIIlIlll) {
        WindowTabScreen llllllllllllllllIlllllIlIIIllIII;
        return llllllllllllllllIlllllIlIIIllIII.window.add(llllllllllllllllIlllllIlIIIlIlll);
    }
}

