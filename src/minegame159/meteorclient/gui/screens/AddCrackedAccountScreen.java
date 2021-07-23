/*
 * Decompiled with CFR 0.151.
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
    private void lambda$new$0(WTextBox wTextBox, WButton wButton) {
        CrackedAccount crackedAccount = new CrackedAccount(wTextBox.get());
        if (!wTextBox.get().trim().isEmpty() && !Accounts.get().exists(crackedAccount)) {
            AccountsScreen.addAccount(wButton, this, crackedAccount);
        }
    }

    public AddCrackedAccountScreen(GuiTheme guiTheme) {
        super(guiTheme, "Add Cracked Account");
        WTable wTable = this.add(guiTheme.table()).widget();
        wTable.add(guiTheme.label("Name: "));
        WTextBox wTextBox = wTable.add(guiTheme.textBox("")).minWidth(400.0).expandX().widget();
        wTextBox.setFocused(true);
        wTable.row();
        WButton wButton = wTable.add(guiTheme.button("Add")).expandX().widget();
        this.enterAction = wButton.action = () -> this.lambda$new$0(wTextBox, wButton);
    }
}

