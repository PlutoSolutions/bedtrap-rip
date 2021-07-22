/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.systems.accounts.Account;
import minegame159.meteorclient.systems.accounts.Accounts;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import minegame159.meteorclient.utils.network.OnlinePlayers;
import minegame159.meteorclient.utils.render.color.Color;

public abstract class WAccount
extends WHorizontalList {
    private final /* synthetic */ Account<?> account;
    public /* synthetic */ Runnable refreshScreenAction;
    private final /* synthetic */ WidgetScreen screen;

    public WAccount(WidgetScreen llIllllIIIIlIl, Account<?> llIllllIIIIIIl) {
        WAccount llIllllIIIIllI;
        llIllllIIIIllI.screen = llIllllIIIIlIl;
        llIllllIIIIllI.account = llIllllIIIIIIl;
    }

    protected abstract Color accountTypeColor();

    protected abstract Color loggedInColor();

    @Override
    public void init() {
        WAccount llIlllIlllIllI;
        llIlllIlllIllI.add(llIlllIlllIllI.theme.texture(32.0, 32.0, 90.0, llIlllIlllIllI.account.getCache().getHeadTexture()));
        WLabel llIlllIllllIlI = llIlllIlllIllI.add(llIlllIlllIllI.theme.label(llIlllIlllIllI.account.getUsername())).widget();
        if (Utils.mc.method_1548().method_1676().equalsIgnoreCase(llIlllIlllIllI.account.getUsername())) {
            llIlllIllllIlI.color = llIlllIlllIllI.loggedInColor();
        }
        WLabel llIlllIllllIIl = llIlllIlllIllI.add(llIlllIlllIllI.theme.label(String.valueOf(new StringBuilder().append("(").append((Object)llIlllIlllIllI.account.getType()).append(")")))).expandCellX().right().widget();
        llIlllIllllIIl.color = llIlllIlllIllI.accountTypeColor();
        WButton llIlllIllllIII = llIlllIlllIllI.add(llIlllIlllIllI.theme.button("Login")).widget();
        llIlllIllllIII.action = () -> {
            WAccount llIlllIllIlIll;
            llIlllIllIlIlI.minWidth = llIlllIllIlIlI.width;
            llIlllIllllIII.set("...");
            llIlllIllIlIll.screen.locked = true;
            MeteorExecutor.execute(() -> {
                WAccount llIlllIlIlllll;
                if (llIlllIlIlllll.account.login()) {
                    llIlllIllllIlI.set(llIlllIlIlllll.account.getUsername());
                    Accounts.get().save();
                    OnlinePlayers.forcePing();
                    llIlllIlIlllll.screen.taskAfterRender = llIlllIlIlllll.refreshScreenAction;
                }
                llIlllIlIlllIl.minWidth = 0.0;
                llIlllIllllIII.set("Login");
                llIlllIlIlllll.screen.locked = false;
            });
        };
        WMinus llIlllIlllIlll = llIlllIlllIllI.add(llIlllIlllIllI.theme.minus()).widget();
        llIlllIlllIlll.action = () -> {
            WAccount llIlllIllIllll;
            Accounts.get().remove(llIlllIllIllll.account);
            if (llIlllIllIllll.refreshScreenAction != null) {
                llIlllIllIllll.refreshScreenAction.run();
            }
        };
    }
}

