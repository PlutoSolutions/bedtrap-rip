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
    public Double min;
    public Runnable action;
    public int decimalPlaces = 3;
    private double value;
    public boolean noSlider = false;
    public boolean small;
    public Runnable actionOnRelease;
    private final double sliderMin;
    private WSlider slider;
    private WTextBox textBox;
    private final double sliderMax;
    public Double max;

    public double get() {
        return this.value;
    }

    private void lambda$init$1() {
        double d = this.value;
        this.value = this.slider.get();
        this.textBox.set(this.valueString());
        if (this.action != null && this.value != d) {
            this.action.run();
        }
    }

    @Override
    public void init() {
        this.textBox = this.add(this.theme.textBox(this.valueString(), (arg_0, arg_1) -> this.filter(arg_0, arg_1))).minWidth(75.0).widget();
        if (!this.noSlider) {
            this.slider = this.add(this.theme.slider(this.value, this.sliderMin, this.sliderMax)).minWidth(this.small ? 125.0 - this.spacing : 200.0).centerY().expandX().widget();
        }
        this.textBox.actionOnUnfocused = this::lambda$init$0;
        if (this.slider != null) {
            this.slider.action = this::lambda$init$1;
            this.slider.actionOnRelease = this::lambda$init$2;
        }
    }

    private String valueString() {
        return String.format(Locale.US, String.valueOf(new StringBuilder().append("%.").append(this.decimalPlaces).append("f")), this.value);
    }

    private void lambda$init$2() {
        if (this.actionOnRelease != null) {
            this.actionOnRelease.run();
        }
    }

    public WDoubleEdit(double d, double d2, double d3) {
        this.value = d;
        this.sliderMin = d2;
        this.sliderMax = d3;
        if (d2 == 0.0 && d3 == 0.0) {
            this.noSlider = true;
        }
    }

    public void set(double d) {
        this.value = d;
        this.textBox.set(this.valueString());
        if (this.slider != null) {
            this.slider.set(d);
        }
    }

    private boolean filter(String string, char c) {
        boolean bl;
        boolean bl2 = true;
        if (c == '-' && string.isEmpty()) {
            bl = true;
            bl2 = false;
        } else if (c == '.' && !string.contains(".")) {
            bl = true;
            if (string.isEmpty()) {
                bl2 = false;
            }
        } else {
            bl = Character.isDigit(c);
        }
        if (bl && bl2) {
            try {
                Double.parseDouble(String.valueOf(new StringBuilder().append(string).append(c)));
            }
            catch (NumberFormatException numberFormatException) {
                bl = false;
            }
        }
        return bl;
    }

    private void lambda$init$0() {
        double d = this.value;
        this.value = this.textBox.get().isEmpty() ? 0.0 : (this.textBox.get().equals("-") ? 0.0 : (this.textBox.get().equals(".") ? 0.0 : (this.textBox.get().equals("-.") ? 0.0 : Double.parseDouble(this.textBox.get()))));
        double d2 = this.value;
        if (this.min != null && this.value < this.min) {
            this.value = this.min;
        } else if (this.max != null && this.value > this.max) {
            this.value = this.max;
        }
        if (this.value != d2) {
            this.textBox.set(this.valueString());
        }
        if (this.slider != null) {
            this.slider.set(this.value);
        }
        if (this.value != d) {
            if (this.action != null) {
                this.action.run();
            }
            if (this.actionOnRelease != null) {
                this.actionOnRelease.run();
            }
        }
    }
}

