/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.utils.misc.Keybind;

public class WKeybind
extends WHorizontalList {
    private final /* synthetic */ int defaultValue;
    private /* synthetic */ WLabel label;
    public /* synthetic */ Runnable actionOnSet;
    public /* synthetic */ Runnable action;
    private final /* synthetic */ Keybind keybind;
    private /* synthetic */ boolean listening;

    public void reset() {
        WKeybind llllllIIIllIll;
        llllllIIIllIll.listening = false;
        llllllIIIllIll.refreshLabel();
    }

    public boolean onAction(boolean llllllIIlIIlII, int llllllIIlIIIII) {
        WKeybind llllllIIlIIIlI;
        if (llllllIIlIIIlI.listening && llllllIIlIIIlI.keybind.canBindTo(llllllIIlIIlII, llllllIIlIIIII)) {
            llllllIIlIIIlI.keybind.set(llllllIIlIIlII, llllllIIlIIIII);
            llllllIIlIIIlI.reset();
            if (llllllIIlIIIlI.action != null) {
                llllllIIlIIIlI.action.run();
            }
            return true;
        }
        return false;
    }

    public WKeybind(Keybind llllllIIllIIll, int llllllIIllIlIl) {
        WKeybind llllllIIllIlII;
        llllllIIllIlII.keybind = llllllIIllIIll;
        llllllIIllIlII.defaultValue = llllllIIllIlIl;
    }

    private String appendBindText(String llllllIIIlIIll) {
        return String.valueOf(new StringBuilder().append("Bind: ").append(llllllIIIlIIll));
    }

    private void refreshLabel() {
        WKeybind llllllIIIllIII;
        llllllIIIllIII.label.set(llllllIIIllIII.appendBindText(llllllIIIllIII.keybind.toString()));
    }

    @Override
    public void init() {
        WKeybind llllllIIlIlIll;
        llllllIIlIlIll.label = llllllIIlIlIll.add(llllllIIlIlIll.theme.label("")).widget();
        WButton llllllIIlIllIl = llllllIIlIlIll.add(llllllIIlIlIll.theme.button("Set")).widget();
        llllllIIlIllIl.action = () -> {
            WKeybind llllllIIIlIIII;
            llllllIIIlIIII.listening = true;
            llllllIIIlIIII.label.set(llllllIIIlIIII.appendBindText("..."));
            if (llllllIIIlIIII.actionOnSet != null) {
                llllllIIIlIIII.actionOnSet.run();
            }
        };
        WButton llllllIIlIllII = llllllIIlIlIll.add(llllllIIlIlIll.theme.button(GuiRenderer.RESET)).expandCellX().right().widget();
        llllllIIlIllII.action = llllllIIlIlIll::resetBind;
        llllllIIlIlIll.refreshLabel();
    }

    public void resetBind() {
        WKeybind llllllIIIlllIl;
        llllllIIIlllIl.keybind.set(true, llllllIIIlllIl.defaultValue);
        llllllIIIlllIl.reset();
    }
}

