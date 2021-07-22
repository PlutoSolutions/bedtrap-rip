/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.screens;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.screens.AccountsScreen;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.systems.accounts.types.TheAlteningAccount;

public class AddAlteningAccountScreen
extends WindowScreen {
    public AddAlteningAccountScreen(GuiTheme lIllIllllIIIII) {
        super(lIllIllllIIIII, "Add The Altening Account");
        AddAlteningAccountScreen lIllIlllIlllII;
        WTable lIllIlllIlllll = lIllIlllIlllII.add(lIllIllllIIIII.table()).widget();
        lIllIlllIlllll.add(lIllIllllIIIII.label("Token: "));
        WTextBox lIllIlllIllllI = lIllIlllIlllll.add(lIllIllllIIIII.textBox("")).minWidth(400.0).expandX().widget();
        lIllIlllIllllI.setFocused(true);
        lIllIlllIlllll.row();
        WButton lIllIlllIlllIl = lIllIlllIlllll.add(lIllIllllIIIII.button("Add")).expandX().widget();
        lIllIlllIlllII.enterAction = lIllIlllIlllIl.action = () -> {
            if (!lIllIlllIllllI.get().isEmpty()) {
                AddAlteningAccountScreen lIllIlllIlIIIl;
                AccountsScreen.addAccount(lIllIlllIlllIl, lIllIlllIlIIIl, new TheAlteningAccount(lIllIlllIllllI.get()));
            }
        };
    }
}

