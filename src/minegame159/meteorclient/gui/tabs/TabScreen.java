/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.tabs;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;

public class TabScreen
extends WidgetScreen {
    public final /* synthetic */ Tab tab;

    public TabScreen(GuiTheme llllllllllllllllIlllIIIlIIlllllI, Tab llllllllllllllllIlllIIIlIIlllIlI) {
        super(llllllllllllllllIlllIIIlIIlllllI, llllllllllllllllIlllIIIlIIlllIlI.name);
        TabScreen llllllllllllllllIlllIIIlIIllllII;
        llllllllllllllllIlllIIIlIIllllII.tab = llllllllllllllllIlllIIIlIIlllIlI;
    }

    public <T extends WWidget> Cell<T> addDirect(T llllllllllllllllIlllIIIlIIllIllI) {
        TabScreen llllllllllllllllIlllIIIlIIllIlIl;
        return super.add(llllllllllllllllIlllIIIlIIllIllI);
    }
}

