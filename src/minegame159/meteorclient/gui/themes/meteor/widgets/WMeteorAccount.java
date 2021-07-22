/*
 * Decompiled with CFR 0.150.
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
        WMeteorAccount lIIlIllIlllIlll;
        return lIIlIllIlllIlll.theme().textSecondaryColor.get();
    }

    @Override
    protected Color loggedInColor() {
        WMeteorAccount lIIlIllIllllIIl;
        return lIIlIllIllllIIl.theme().loggedInColor.get();
    }

    public WMeteorAccount(WidgetScreen lIIlIlllIIIIIII, Account<?> lIIlIllIlllllII) {
        super(lIIlIlllIIIIIII, lIIlIllIlllllII);
        WMeteorAccount lIIlIllIllllllI;
    }
}

