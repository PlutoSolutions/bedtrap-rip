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
    private final Account<?> account;
    public Runnable refreshScreenAction;
    private final WidgetScreen screen;

    public WAccount(WidgetScreen widgetScreen, Account<?> account) {
        this.screen = widgetScreen;
        this.account = account;
    }

    protected abstract Color accountTypeColor();

    private void lambda$init$2() {
        Accounts.get().remove(this.account);
        if (this.refreshScreenAction != null) {
            this.refreshScreenAction.run();
        }
    }

    protected abstract Color loggedInColor();

    private void lambda$init$1(WButton wButton, WLabel wLabel) {
        wButton.minWidth = wButton.width;
        wButton.set("...");
        this.screen.locked = true;
        MeteorExecutor.execute(() -> this.lambda$init$0(wLabel, wButton));
    }

    @Override
    public void init() {
        this.add(this.theme.texture(32.0, 32.0, 90.0, this.account.getCache().getHeadTexture()));
        WLabel wLabel = this.add(this.theme.label(this.account.getUsername())).widget();
        if (Utils.mc.method_1548().method_1676().equalsIgnoreCase(this.account.getUsername())) {
            wLabel.color = this.loggedInColor();
        }
        WLabel wLabel2 = this.add(this.theme.label(String.valueOf(new StringBuilder().append("(").append((Object)this.account.getType()).append(")")))).expandCellX().right().widget();
        wLabel2.color = this.accountTypeColor();
        WButton wButton = this.add(this.theme.button("Login")).widget();
        wButton.action = () -> this.lambda$init$1(wButton, wLabel);
        WMinus wMinus = this.add(this.theme.minus()).widget();
        wMinus.action = this::lambda$init$2;
    }

    private void lambda$init$0(WLabel wLabel, WButton wButton) {
        if (this.account.login()) {
            wLabel.set(this.account.getUsername());
            Accounts.get().save();
            OnlinePlayers.forcePing();
            this.screen.taskAfterRender = this.refreshScreenAction;
        }
        wButton.minWidth = 0.0;
        wButton.set("Login");
        this.screen.locked = false;
    }
}

