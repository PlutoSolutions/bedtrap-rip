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
    public AddAlteningAccountScreen(GuiTheme guiTheme) {
        super(guiTheme, "Add The Altening Account");
        WTable wTable = this.add(guiTheme.table()).widget();
        wTable.add(guiTheme.label("Token: "));
        WTextBox wTextBox = wTable.add(guiTheme.textBox("")).minWidth(400.0).expandX().widget();
        wTextBox.setFocused(true);
        wTable.row();
        WButton wButton = wTable.add(guiTheme.button("Add")).expandX().widget();
        this.enterAction = wButton.action = () -> this.lambda$new$0(wTextBox, wButton);
    }

    private void lambda$new$0(WTextBox wTextBox, WButton wButton) {
        if (!wTextBox.get().isEmpty()) {
            AccountsScreen.addAccount(wButton, this, new TheAlteningAccount(wTextBox.get()));
        }
    }
}

