/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
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
    public TabScreen createScreen(GuiTheme lllllIIIIIIl) {
        GuiTab lllllIIIIIII;
        return new GuiScreen(lllllIIIIIIl, lllllIIIIIII);
    }

    public GuiTab() {
        super("GUI");
        GuiTab lllllIIIIllI;
    }

    @Override
    public boolean isScreen(class_437 llllIllllIll) {
        return llllIllllIll instanceof GuiScreen;
    }

    private static class GuiScreen
    extends WindowTabScreen {
        public GuiScreen(GuiTheme lIlIIIIllIlIll, Tab lIlIIIIllIllll) {
            super(lIlIIIIllIlIll, lIlIIIIllIllll);
            GuiScreen lIlIIIIlllIIIl;
            WTable lIlIIIIllIlllI = lIlIIIIlllIIIl.add(lIlIIIIllIlIll.table()).expandX().widget();
            lIlIIIIllIlllI.add(lIlIIIIllIlIll.label("Theme:"));
            WDropdown<String> lIlIIIIllIllIl = lIlIIIIllIlllI.add(lIlIIIIllIlIll.dropdown(GuiThemes.getNames(), GuiThemes.get().name)).widget();
            lIlIIIIllIllIl.action = () -> {
                GuiThemes.select((String)lIlIIIIllIllIl.get());
                Utils.mc.method_1507(null);
                lIlIIIIllIllll.openScreen(GuiThemes.get());
            };
            lIlIIIIllIlIll.settings.onActivated();
            lIlIIIIlllIIIl.add(lIlIIIIllIlIll.settings(lIlIIIIllIlIll.settings)).expandX();
        }
    }
}

