/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.utils.misc.Keybind;

public class WKeybind
extends WHorizontalList {
    private final int defaultValue;
    private WLabel label;
    public Runnable actionOnSet;
    public Runnable action;
    private final Keybind keybind;
    private boolean listening;

    public void reset() {
        this.listening = false;
        this.refreshLabel();
    }

    public boolean onAction(boolean bl, int n) {
        if (this.listening && this.keybind.canBindTo(bl, n)) {
            this.keybind.set(bl, n);
            this.reset();
            if (this.action != null) {
                this.action.run();
            }
            return true;
        }
        return false;
    }

    private void lambda$init$0() {
        this.listening = true;
        this.label.set(this.appendBindText("..."));
        if (this.actionOnSet != null) {
            this.actionOnSet.run();
        }
    }

    public WKeybind(Keybind keybind, int n) {
        this.keybind = keybind;
        this.defaultValue = n;
    }

    private String appendBindText(String string) {
        return String.valueOf(new StringBuilder().append("Bind: ").append(string));
    }

    private void refreshLabel() {
        this.label.set(this.appendBindText(this.keybind.toString()));
    }

    @Override
    public void init() {
        this.label = this.add(this.theme.label("")).widget();
        WButton wButton = this.add(this.theme.button("Set")).widget();
        wButton.action = this::lambda$init$0;
        WButton wButton2 = this.add(this.theme.button(GuiRenderer.RESET)).expandCellX().right().widget();
        wButton2.action = this::resetBind;
        this.refreshLabel();
    }

    public void resetBind() {
        this.keybind.set(true, this.defaultValue);
        this.reset();
    }
}

