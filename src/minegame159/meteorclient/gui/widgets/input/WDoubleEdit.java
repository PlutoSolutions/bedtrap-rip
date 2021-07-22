/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.input;

import java.util.Locale;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.input.WSlider;
import minegame159.meteorclient.gui.widgets.input.WTextBox;

public class WDoubleEdit
extends WHorizontalList {
    public /* synthetic */ Double min;
    public /* synthetic */ Runnable action;
    public /* synthetic */ int decimalPlaces;
    private /* synthetic */ double value;
    public /* synthetic */ boolean noSlider;
    public /* synthetic */ boolean small;
    public /* synthetic */ Runnable actionOnRelease;
    private final /* synthetic */ double sliderMin;
    private /* synthetic */ WSlider slider;
    private /* synthetic */ WTextBox textBox;
    private final /* synthetic */ double sliderMax;
    public /* synthetic */ Double max;

    public double get() {
        WDoubleEdit llllllllllllllllIlIlllIlIllllIIl;
        return llllllllllllllllIlIlllIlIllllIIl.value;
    }

    @Override
    public void init() {
        WDoubleEdit llllllllllllllllIlIlllIllIIIllll;
        llllllllllllllllIlIlllIllIIIllll.textBox = llllllllllllllllIlIlllIllIIIllll.add(llllllllllllllllIlIlllIllIIIllll.theme.textBox(llllllllllllllllIlIlllIllIIIllll.valueString(), (arg_0, arg_1) -> llllllllllllllllIlIlllIllIIIllll.filter(arg_0, arg_1))).minWidth(75.0).widget();
        if (!llllllllllllllllIlIlllIllIIIllll.noSlider) {
            llllllllllllllllIlIlllIllIIIllll.slider = llllllllllllllllIlIlllIllIIIllll.add(llllllllllllllllIlIlllIllIIIllll.theme.slider(llllllllllllllllIlIlllIllIIIllll.value, llllllllllllllllIlIlllIllIIIllll.sliderMin, llllllllllllllllIlIlllIllIIIllll.sliderMax)).minWidth(llllllllllllllllIlIlllIllIIIllll.small ? 125.0 - llllllllllllllllIlIlllIllIIIllll.spacing : 200.0).centerY().expandX().widget();
        }
        llllllllllllllllIlIlllIllIIIllll.textBox.actionOnUnfocused = () -> {
            WDoubleEdit llllllllllllllllIlIlllIlIllIIIll;
            double llllllllllllllllIlIlllIlIllIIIlI = llllllllllllllllIlIlllIlIllIIIll.value;
            llllllllllllllllIlIlllIlIllIIIll.value = llllllllllllllllIlIlllIlIllIIIll.textBox.get().isEmpty() ? 0.0 : (llllllllllllllllIlIlllIlIllIIIll.textBox.get().equals("-") ? 0.0 : (llllllllllllllllIlIlllIlIllIIIll.textBox.get().equals(".") ? 0.0 : (llllllllllllllllIlIlllIlIllIIIll.textBox.get().equals("-.") ? 0.0 : Double.parseDouble(llllllllllllllllIlIlllIlIllIIIll.textBox.get()))));
            double llllllllllllllllIlIlllIlIllIIIIl = llllllllllllllllIlIlllIlIllIIIll.value;
            if (llllllllllllllllIlIlllIlIllIIIll.min != null && llllllllllllllllIlIlllIlIllIIIll.value < llllllllllllllllIlIlllIlIllIIIll.min) {
                llllllllllllllllIlIlllIlIllIIIll.value = llllllllllllllllIlIlllIlIllIIIll.min;
            } else if (llllllllllllllllIlIlllIlIllIIIll.max != null && llllllllllllllllIlIlllIlIllIIIll.value > llllllllllllllllIlIlllIlIllIIIll.max) {
                llllllllllllllllIlIlllIlIllIIIll.value = llllllllllllllllIlIlllIlIllIIIll.max;
            }
            if (llllllllllllllllIlIlllIlIllIIIll.value != llllllllllllllllIlIlllIlIllIIIIl) {
                llllllllllllllllIlIlllIlIllIIIll.textBox.set(llllllllllllllllIlIlllIlIllIIIll.valueString());
            }
            if (llllllllllllllllIlIlllIlIllIIIll.slider != null) {
                llllllllllllllllIlIlllIlIllIIIll.slider.set(llllllllllllllllIlIlllIlIllIIIll.value);
            }
            if (llllllllllllllllIlIlllIlIllIIIll.value != llllllllllllllllIlIlllIlIllIIIlI) {
                if (llllllllllllllllIlIlllIlIllIIIll.action != null) {
                    llllllllllllllllIlIlllIlIllIIIll.action.run();
                }
                if (llllllllllllllllIlIlllIlIllIIIll.actionOnRelease != null) {
                    llllllllllllllllIlIlllIlIllIIIll.actionOnRelease.run();
                }
            }
        };
        if (llllllllllllllllIlIlllIllIIIllll.slider != null) {
            llllllllllllllllIlIlllIllIIIllll.slider.action = () -> {
                WDoubleEdit llllllllllllllllIlIlllIlIllIlIII;
                double llllllllllllllllIlIlllIlIllIlIIl = llllllllllllllllIlIlllIlIllIlIII.value;
                llllllllllllllllIlIlllIlIllIlIII.value = llllllllllllllllIlIlllIlIllIlIII.slider.get();
                llllllllllllllllIlIlllIlIllIlIII.textBox.set(llllllllllllllllIlIlllIlIllIlIII.valueString());
                if (llllllllllllllllIlIlllIlIllIlIII.action != null && llllllllllllllllIlIlllIlIllIlIII.value != llllllllllllllllIlIlllIlIllIlIIl) {
                    llllllllllllllllIlIlllIlIllIlIII.action.run();
                }
            };
            llllllllllllllllIlIlllIllIIIllll.slider.actionOnRelease = () -> {
                WDoubleEdit llllllllllllllllIlIlllIlIllIlllI;
                if (llllllllllllllllIlIlllIlIllIlllI.actionOnRelease != null) {
                    llllllllllllllllIlIlllIlIllIlllI.actionOnRelease.run();
                }
            };
        }
    }

    private String valueString() {
        WDoubleEdit llllllllllllllllIlIlllIlIlllIIIl;
        return String.format(Locale.US, String.valueOf(new StringBuilder().append("%.").append(llllllllllllllllIlIlllIlIlllIIIl.decimalPlaces).append("f")), llllllllllllllllIlIlllIlIlllIIIl.value);
    }

    public WDoubleEdit(double llllllllllllllllIlIlllIllIIlIIll, double llllllllllllllllIlIlllIllIIlIllI, double llllllllllllllllIlIlllIllIIlIlIl) {
        WDoubleEdit llllllllllllllllIlIlllIllIIlIlII;
        llllllllllllllllIlIlllIllIIlIlII.decimalPlaces = 3;
        llllllllllllllllIlIlllIllIIlIlII.noSlider = false;
        llllllllllllllllIlIlllIllIIlIlII.value = llllllllllllllllIlIlllIllIIlIIll;
        llllllllllllllllIlIlllIllIIlIlII.sliderMin = llllllllllllllllIlIlllIllIIlIllI;
        llllllllllllllllIlIlllIllIIlIlII.sliderMax = llllllllllllllllIlIlllIllIIlIlIl;
        if (llllllllllllllllIlIlllIllIIlIllI == 0.0 && llllllllllllllllIlIlllIllIIlIlIl == 0.0) {
            llllllllllllllllIlIlllIllIIlIlII.noSlider = true;
        }
    }

    public void set(double llllllllllllllllIlIlllIlIlllIIll) {
        WDoubleEdit llllllllllllllllIlIlllIlIlllIllI;
        llllllllllllllllIlIlllIlIlllIllI.value = llllllllllllllllIlIlllIlIlllIIll;
        llllllllllllllllIlIlllIlIlllIllI.textBox.set(llllllllllllllllIlIlllIlIlllIllI.valueString());
        if (llllllllllllllllIlIlllIlIlllIllI.slider != null) {
            llllllllllllllllIlIlllIlIlllIllI.slider.set(llllllllllllllllIlIlllIlIlllIIll);
        }
    }

    private boolean filter(String llllllllllllllllIlIlllIllIIIIlII, char llllllllllllllllIlIlllIllIIIIIll) {
        boolean llllllllllllllllIlIlllIllIIIIIlI;
        boolean llllllllllllllllIlIlllIllIIIIIIl = true;
        if (llllllllllllllllIlIlllIllIIIIIll == '-' && llllllllllllllllIlIlllIllIIIIlII.isEmpty()) {
            boolean llllllllllllllllIlIlllIllIIIlIII = true;
            llllllllllllllllIlIlllIllIIIIIIl = false;
        } else if (llllllllllllllllIlIlllIllIIIIIll == '.' && !llllllllllllllllIlIlllIllIIIIlII.contains(".")) {
            boolean llllllllllllllllIlIlllIllIIIIlll = true;
            if (llllllllllllllllIlIlllIllIIIIlII.isEmpty()) {
                llllllllllllllllIlIlllIllIIIIIIl = false;
            }
        } else {
            llllllllllllllllIlIlllIllIIIIIlI = Character.isDigit(llllllllllllllllIlIlllIllIIIIIll);
        }
        if (llllllllllllllllIlIlllIllIIIIIlI && llllllllllllllllIlIlllIllIIIIIIl) {
            try {
                Double.parseDouble(String.valueOf(new StringBuilder().append(llllllllllllllllIlIlllIllIIIIlII).append(llllllllllllllllIlIlllIllIIIIIll)));
            }
            catch (NumberFormatException llllllllllllllllIlIlllIllIIIIllI) {
                llllllllllllllllIlIlllIllIIIIIlI = false;
            }
        }
        return llllllllllllllllIlIlllIllIIIIIlI;
    }
}

