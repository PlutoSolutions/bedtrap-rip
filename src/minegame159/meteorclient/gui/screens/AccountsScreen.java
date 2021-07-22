/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 */
package minegame159.meteorclient.gui.screens;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.screens.AddAlteningAccountScreen;
import minegame159.meteorclient.gui.screens.AddCrackedAccountScreen;
import minegame159.meteorclient.gui.screens.AddPremiumAccountScreen;
import minegame159.meteorclient.gui.widgets.WAccount;
import minegame159.meteorclient.gui.widgets.containers.WContainer;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.systems.accounts.Account;
import minegame159.meteorclient.systems.accounts.Accounts;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import net.minecraft.class_437;

public class AccountsScreen
extends WindowScreen {
    private void addButton(WContainer lllllllllllllllllIIIIllIlIIIIIIl, String lllllllllllllllllIIIIllIlIIIIIII, Runnable lllllllllllllllllIIIIllIIlllllll) {
        AccountsScreen lllllllllllllllllIIIIllIlIIIIlll;
        WButton lllllllllllllllllIIIIllIlIIIIIll = lllllllllllllllllIIIIllIlIIIIIIl.add(lllllllllllllllllIIIIllIlIIIIlll.theme.button(lllllllllllllllllIIIIllIlIIIIIII)).expandX().widget();
        lllllllllllllllllIIIIllIlIIIIIll.action = lllllllllllllllllIIIIllIIlllllll;
    }

    private void initWidgets() {
        AccountsScreen lllllllllllllllllIIIIllIlIIlIIII;
        for (Account<?> lllllllllllllllllIIIIllIlIIlIIll : Accounts.get()) {
            WAccount lllllllllllllllllIIIIllIlIIlIlII = lllllllllllllllllIIIIllIlIIlIIII.add(lllllllllllllllllIIIIllIlIIlIIII.theme.account(lllllllllllllllllIIIIllIlIIlIIII, lllllllllllllllllIIIIllIlIIlIIll)).expandX().widget();
            lllllllllllllllllIIIIllIlIIlIlII.refreshScreenAction = () -> {
                AccountsScreen lllllllllllllllllIIIIllIIllIIIII;
                lllllllllllllllllIIIIllIIllIIIII.clear();
                lllllllllllllllllIIIIllIIllIIIII.initWidgets();
            };
        }
        WHorizontalList lllllllllllllllllIIIIllIlIIlIIIl = lllllllllllllllllIIIIllIlIIlIIII.add(lllllllllllllllllIIIIllIlIIlIIII.theme.horizontalList()).expandX().widget();
        lllllllllllllllllIIIIllIlIIlIIII.addButton(lllllllllllllllllIIIIllIlIIlIIIl, "Cracked", () -> {
            AccountsScreen lllllllllllllllllIIIIllIIllIIIll;
            Utils.mc.method_1507((class_437)new AddCrackedAccountScreen(lllllllllllllllllIIIIllIIllIIIll.theme));
        });
        lllllllllllllllllIIIIllIlIIlIIII.addButton(lllllllllllllllllIIIIllIlIIlIIIl, "Premium", () -> {
            AccountsScreen lllllllllllllllllIIIIllIIllIIlll;
            Utils.mc.method_1507((class_437)new AddPremiumAccountScreen(lllllllllllllllllIIIIllIIllIIlll.theme));
        });
        lllllllllllllllllIIIIllIlIIlIIII.addButton(lllllllllllllllllIIIIllIlIIlIIIl, "The Altening", () -> {
            AccountsScreen lllllllllllllllllIIIIllIIllIlIlI;
            Utils.mc.method_1507((class_437)new AddAlteningAccountScreen(lllllllllllllllllIIIIllIIllIlIlI.theme));
        });
    }

    @Override
    protected void method_25426() {
        AccountsScreen lllllllllllllllllIIIIllIlIIllIIl;
        super.method_25426();
        lllllllllllllllllIIIIllIlIIllIIl.clear();
        lllllllllllllllllIIIIllIlIIllIIl.initWidgets();
    }

    public static void addAccount(WButton lllllllllllllllllIIIIllIIlllIlll, WidgetScreen lllllllllllllllllIIIIllIIllllIIl, Account<?> lllllllllllllllllIIIIllIIlllIlIl) {
        lllllllllllllllllIIIIllIIlllIlll.set("...");
        lllllllllllllllllIIIIllIIllllIIl.locked = true;
        MeteorExecutor.execute(() -> {
            if (lllllllllllllllllIIIIllIIlllIlIl.fetchInfo() && lllllllllllllllllIIIIllIIlllIlIl.fetchHead()) {
                Accounts.get().add(lllllllllllllllllIIIIllIIlllIlIl);
                lllllllllllllllllIIIIllIIllIllIl.locked = false;
                lllllllllllllllllIIIIllIIllllIIl.method_25419();
            }
            lllllllllllllllllIIIIllIIlllIlll.set("Add");
            lllllllllllllllllIIIIllIIllIllIl.locked = false;
        });
    }

    public AccountsScreen(GuiTheme lllllllllllllllllIIIIllIlIIlllII) {
        super(lllllllllllllllllIIIIllIlIIlllII, "Accounts");
        AccountsScreen lllllllllllllllllIIIIllIlIIlllll;
    }
}

