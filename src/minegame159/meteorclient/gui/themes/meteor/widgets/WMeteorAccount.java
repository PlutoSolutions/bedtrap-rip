/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WAccount;
import minegame159.meteorclient.systems.accounts.Account;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorAccount
extends WAccount
implements MeteorWidget {
    @Override
    protected Color accountTypeColor() {
        return this.theme().textSecondaryColor.get();
    }

    @Override
    protected Color loggedInColor() {
        return this.theme().loggedInColor.get();
    }

    public WMeteorAccount(WidgetScreen widgetScreen, Account<?> account) {
        super(widgetScreen, account);
    }
}

