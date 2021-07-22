/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.input;

import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.input.WSlider;
import minegame159.meteorclient.gui.widgets.input.WTextBox;

public class WIntEdit
extends WHorizontalList {
    public /* synthetic */ boolean hasSlider;
    private /* synthetic */ int value;
    public /* synthetic */ Integer max;
    public /* synthetic */ Integer min;
    private /* synthetic */ WTextBox textBox;
    public /* synthetic */ Runnable actionOnRelease;
    private final /* synthetic */ int sliderMax;
    public /* synthetic */ Runnable action;
    public /* synthetic */ boolean small;
    private final /* synthetic */ int sliderMin;
    private /* synthetic */ WSlider slider;

    public WIntEdit(int lllllllllllllllllllllIIIlIlIIIII, int lllllllllllllllllllllIIIlIIllIll, int lllllllllllllllllllllIIIlIIllIlI) {
        WIntEdit lllllllllllllllllllllIIIlIlIIIIl;
        lllllllllllllllllllllIIIlIlIIIIl.hasSlider = true;
        lllllllllllllllllllllIIIlIlIIIIl.value = lllllllllllllllllllllIIIlIlIIIII;
        lllllllllllllllllllllIIIlIlIIIIl.sliderMin = lllllllllllllllllllllIIIlIIllIll;
        lllllllllllllllllllllIIIlIlIIIIl.sliderMax = lllllllllllllllllllllIIIlIIllIlI;
        if (lllllllllllllllllllllIIIlIIllIll == 0 && lllllllllllllllllllllIIIlIIllIlI == 0) {
            lllllllllllllllllllllIIIlIlIIIIl.hasSlider = false;
        }
    }

    public void set(int lllllllllllllllllllllIIIIlllllIl) {
        WIntEdit lllllllllllllllllllllIIIlIIIIIII;
        lllllllllllllllllllllIIIlIIIIIII.value = lllllllllllllllllllllIIIIlllllIl;
        lllllllllllllllllllllIIIlIIIIIII.textBox.set(Integer.toString(lllllllllllllllllllllIIIIlllllIl));
        if (lllllllllllllllllllllIIIlIIIIIII.slider != null) {
            lllllllllllllllllllllIIIlIIIIIII.slider.set(lllllllllllllllllllllIIIIlllllIl);
        }
    }

    private boolean filter(String lllllllllllllllllllllIIIlIIIlllI, char lllllllllllllllllllllIIIlIIIllIl) {
        boolean lllllllllllllllllllllIIIlIIIllII;
        boolean lllllllllllllllllllllIIIlIIIlIll = true;
        if (lllllllllllllllllllllIIIlIIIllIl == '-' && lllllllllllllllllllllIIIlIIIlllI.isEmpty()) {
            boolean lllllllllllllllllllllIIIlIIlIIIl = true;
            lllllllllllllllllllllIIIlIIIlIll = false;
        } else {
            lllllllllllllllllllllIIIlIIIllII = Character.isDigit(lllllllllllllllllllllIIIlIIIllIl);
        }
        if (lllllllllllllllllllllIIIlIIIllII && lllllllllllllllllllllIIIlIIIlIll) {
            try {
                Integer.parseInt(String.valueOf(new StringBuilder().append(lllllllllllllllllllllIIIlIIIlllI).append(lllllllllllllllllllllIIIlIIIllIl)));
            }
            catch (NumberFormatException lllllllllllllllllllllIIIlIIlIIII) {
                lllllllllllllllllllllIIIlIIIllII = false;
            }
        }
        return lllllllllllllllllllllIIIlIIIllII;
    }

    public int get() {
        WIntEdit lllllllllllllllllllllIIIlIIIIIll;
        return lllllllllllllllllllllIIIlIIIIIll.value;
    }

    @Override
    public void init() {
        WIntEdit lllllllllllllllllllllIIIlIIllIII;
        lllllllllllllllllllllIIIlIIllIII.textBox = lllllllllllllllllllllIIIlIIllIII.add(lllllllllllllllllllllIIIlIIllIII.theme.textBox(Integer.toString(lllllllllllllllllllllIIIlIIllIII.value), (arg_0, arg_1) -> lllllllllllllllllllllIIIlIIllIII.filter(arg_0, arg_1))).minWidth(75.0).widget();
        if (lllllllllllllllllllllIIIlIIllIII.hasSlider) {
            lllllllllllllllllllllIIIlIIllIII.slider = lllllllllllllllllllllIIIlIIllIII.add(lllllllllllllllllllllIIIlIIllIII.theme.slider(lllllllllllllllllllllIIIlIIllIII.value, lllllllllllllllllllllIIIlIIllIII.sliderMin, lllllllllllllllllllllIIIlIIllIII.sliderMax)).minWidth(lllllllllllllllllllllIIIlIIllIII.small ? 125.0 - lllllllllllllllllllllIIIlIIllIII.spacing : 200.0).centerY().expandX().widget();
        }
        lllllllllllllllllllllIIIlIIllIII.textBox.actionOnUnfocused = () -> {
            WIntEdit lllllllllllllllllllllIIIIlllIIIl;
            int lllllllllllllllllllllIIIIlllIIII = lllllllllllllllllllllIIIIlllIIIl.value;
            lllllllllllllllllllllIIIIlllIIIl.value = lllllllllllllllllllllIIIIlllIIIl.textBox.get().isEmpty() ? 0 : (lllllllllllllllllllllIIIIlllIIIl.textBox.get().equals("-") ? 0 : Integer.parseInt(lllllllllllllllllllllIIIIlllIIIl.textBox.get()));
            if (lllllllllllllllllllllIIIIlllIIIl.slider != null) {
                lllllllllllllllllllllIIIIlllIIIl.slider.set(lllllllllllllllllllllIIIIlllIIIl.value);
            }
            if (lllllllllllllllllllllIIIIlllIIIl.value != lllllllllllllllllllllIIIIlllIIII) {
                if (lllllllllllllllllllllIIIIlllIIIl.action != null) {
                    lllllllllllllllllllllIIIIlllIIIl.action.run();
                }
                if (lllllllllllllllllllllIIIIlllIIIl.actionOnRelease != null) {
                    lllllllllllllllllllllIIIIlllIIIl.actionOnRelease.run();
                }
            }
        };
        if (lllllllllllllllllllllIIIlIIllIII.slider != null) {
            lllllllllllllllllllllIIIlIIllIII.slider.action = () -> {
                WIntEdit lllllllllllllllllllllIIIIlllIlIl;
                int lllllllllllllllllllllIIIIlllIllI = lllllllllllllllllllllIIIIlllIlIl.value;
                lllllllllllllllllllllIIIIlllIlIl.value = (int)Math.round(lllllllllllllllllllllIIIIlllIlIl.slider.get());
                lllllllllllllllllllllIIIIlllIlIl.textBox.set(Integer.toString(lllllllllllllllllllllIIIIlllIlIl.value));
                if (lllllllllllllllllllllIIIIlllIlIl.action != null && lllllllllllllllllllllIIIIlllIlIl.value != lllllllllllllllllllllIIIIlllIllI) {
                    lllllllllllllllllllllIIIIlllIlIl.action.run();
                }
            };
            lllllllllllllllllllllIIIlIIllIII.slider.actionOnRelease = () -> {
                WIntEdit lllllllllllllllllllllIIIIllllIll;
                if (lllllllllllllllllllllIIIIllllIll.actionOnRelease != null) {
                    lllllllllllllllllllllIIIIllllIll.actionOnRelease.run();
                }
            };
        }
    }
}

