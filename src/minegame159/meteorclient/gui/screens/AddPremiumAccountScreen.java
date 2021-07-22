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
    public AddPremiumAccountScreen(GuiTheme llIIlIIIlllIll) {
        super(llIIlIIIlllIll, "Add Premium Account");
        AddPremiumAccountScreen llIIlIIIllllII;
        WTable llIIlIIlIIIIII = llIIlIIIllllII.add(llIIlIIIlllIll.table()).widget();
        llIIlIIlIIIIII.add(llIIlIIIlllIll.label("Email: "));
        WTextBox llIIlIIIllllll = llIIlIIlIIIIII.add(llIIlIIIlllIll.textBox("")).minWidth(400.0).expandX().widget();
        llIIlIIIllllll.setFocused(true);
        llIIlIIlIIIIII.row();
        llIIlIIlIIIIII.add(llIIlIIIlllIll.label("Password: "));
        WTextBox llIIlIIIlllllI = llIIlIIlIIIIII.add(llIIlIIIlllIll.textBox("")).minWidth(400.0).expandX().widget();
        llIIlIIlIIIIII.row();
        WButton llIIlIIIllllIl = llIIlIIlIIIIII.add(llIIlIIIlllIll.button("Add")).expandX().widget();
        llIIlIIIllllII.enterAction = llIIlIIIllllIl.action = () -> {
            PremiumAccount llIIlIIIlIllIl = new PremiumAccount(llIIlIIIllllll.get(), llIIlIIIlllllI.get());
            if (!llIIlIIIllllll.get().isEmpty() && !llIIlIIIlllllI.get().isEmpty() && llIIlIIIllllll.get().contains("@") && !Accounts.get().exists(llIIlIIIlIllIl)) {
                AddPremiumAccountScreen llIIlIIIlIllII;
                AccountsScreen.addAccount(llIIlIIIllllIl, llIIlIIIlIllII, llIIlIIIlIllIl);
            }
        };
    }
}

