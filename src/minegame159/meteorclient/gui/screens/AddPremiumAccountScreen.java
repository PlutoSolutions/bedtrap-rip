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
import minegame159.meteorclient.systems.accounts.types.PremiumAccount;

public class AddPremiumAccountScreen
extends WindowScreen {
    public AddPremiumAccountScreen(GuiTheme guiTheme) {
        super(guiTheme, "Add Premium Account");
        WTable wTable = this.add(guiTheme.table()).widget();
        wTable.add(guiTheme.label("Email: "));
        WTextBox wTextBox = wTable.add(guiTheme.textBox("")).minWidth(400.0).expandX().widget();
        wTextBox.setFocused(true);
        wTable.row();
        wTable.add(guiTheme.label("Password: "));
        WTextBox wTextBox2 = wTable.add(guiTheme.textBox("")).minWidth(400.0).expandX().widget();
        wTable.row();
        WButton wButton = wTable.add(guiTheme.button("Add")).expandX().widget();
        this.enterAction = wButton.action = () -> this.lambda$new$0(wTextBox, wTextBox2, wButton);
    }

    private void lambda$new$0(WTextBox wTextBox, WTextBox wTextBox2, WButton wButton) {
        PremiumAccount premiumAccount = new PremiumAccount(wTextBox.get(), wTextBox2.get());
        if (!wTextBox.get().isEmpty() && !wTextBox2.get().isEmpty() && wTextBox.get().contains("@") && !Accounts.get().exists(premiumAccount)) {
            AccountsScreen.addAccount(wButton, this, premiumAccount);
        }
    }
}

