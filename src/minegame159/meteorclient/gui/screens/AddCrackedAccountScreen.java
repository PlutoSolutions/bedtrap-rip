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
import minegame159.meteorclient.systems.accounts.Accounts;
import minegame159.meteorclient.systems.accounts.types.CrackedAccount;

public class AddCrackedAccountScreen
extends WindowScreen {
    public AddCrackedAccountScreen(GuiTheme lllllllllllllllllIlIlIllllIlllll) {
        super(lllllllllllllllllIlIlIllllIlllll, "Add Cracked Account");
        AddCrackedAccountScreen lllllllllllllllllIlIlIlllllIIIII;
        WTable lllllllllllllllllIlIlIlllllIIIll = lllllllllllllllllIlIlIlllllIIIII.add(lllllllllllllllllIlIlIllllIlllll.table()).widget();
        lllllllllllllllllIlIlIlllllIIIll.add(lllllllllllllllllIlIlIllllIlllll.label("Name: "));
        WTextBox lllllllllllllllllIlIlIlllllIIIlI = lllllllllllllllllIlIlIlllllIIIll.add(lllllllllllllllllIlIlIllllIlllll.textBox("")).minWidth(400.0).expandX().widget();
        lllllllllllllllllIlIlIlllllIIIlI.setFocused(true);
        lllllllllllllllllIlIlIlllllIIIll.row();
        WButton lllllllllllllllllIlIlIlllllIIIIl = lllllllllllllllllIlIlIlllllIIIll.add(lllllllllllllllllIlIlIllllIlllll.button("Add")).expandX().widget();
        lllllllllllllllllIlIlIlllllIIIII.enterAction = lllllllllllllllllIlIlIlllllIIIIl.action = () -> {
            CrackedAccount lllllllllllllllllIlIlIllllIlIlII = new CrackedAccount(lllllllllllllllllIlIlIlllllIIIlI.get());
            if (!lllllllllllllllllIlIlIlllllIIIlI.get().trim().isEmpty() && !Accounts.get().exists(lllllllllllllllllIlIlIllllIlIlII)) {
                AddCrackedAccountScreen lllllllllllllllllIlIlIllllIlIlll;
                AccountsScreen.addAccount(lllllllllllllllllIlIlIlllllIIIIl, lllllllllllllllllIlIlIllllIlIlll, lllllllllllllllllIlIlIllllIlIlII);
            }
        };
    }
}

