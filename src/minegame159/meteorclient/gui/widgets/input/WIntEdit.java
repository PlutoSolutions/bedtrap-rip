/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.input;

import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.input.WSlider;
import minegame159.meteorclient.gui.widgets.input.WTextBox;

public class WIntEdit
extends WHorizontalList {
    public boolean hasSlider = true;
    private int value;
    public Integer max;
    public Integer min;
    private WTextBox textBox;
    public Runnable actionOnRelease;
    private final int sliderMax;
    public Runnable action;
    public boolean small;
    private final int sliderMin;
    private WSlider slider;

    private void lambda$init$0() {
        int n = this.value;
        this.value = this.textBox.get().isEmpty() ? 0 : (this.textBox.get().equals("-") ? 0 : Integer.parseInt(this.textBox.get()));
        if (this.slider != null) {
            this.slider.set(this.value);
        }
        if (this.value != n) {
            if (this.action != null) {
                this.action.run();
            }
            if (this.actionOnRelease != null) {
                this.actionOnRelease.run();
            }
        }
    }

    private void lambda$init$2() {
        if (this.actionOnRelease != null) {
            this.actionOnRelease.run();
        }
    }

    private void lambda$init$1() {
        int n = this.value;
        this.value = (int)Math.round(this.slider.get());
        this.textBox.set(Integer.toString(this.value));
        if (this.action != null && this.value != n) {
            this.action.run();
        }
    }

    public WIntEdit(int n, int n2, int n3) {
        this.value = n;
        this.sliderMin = n2;
        this.sliderMax = n3;
        if (n2 == 0 && n3 == 0) {
            this.hasSlider = false;
        }
    }

    public void set(int n) {
        this.value = n;
        this.textBox.set(Integer.toString(n));
        if (this.slider != null) {
            this.slider.set(n);
        }
    }

    private boolean filter(String string, char c) {
        boolean bl;
        boolean bl2 = true;
        if (c == '-' && string.isEmpty()) {
            bl = true;
            bl2 = false;
        } else {
            bl = Character.isDigit(c);
        }
        if (bl && bl2) {
            try {
                Integer.parseInt(String.valueOf(new StringBuilder().append(string).append(c)));
            }
            catch (NumberFormatException numberFormatException) {
                bl = false;
            }
        }
        return bl;
    }

    public int get() {
        return this.value;
    }

    @Override
    public void init() {
        this.textBox = this.add(this.theme.textBox(Integer.toString(this.value), (arg_0, arg_1) -> this.filter(arg_0, arg_1))).minWidth(75.0).widget();
        if (this.hasSlider) {
            this.slider = this.add(this.theme.slider(this.value, this.sliderMin, this.sliderMax)).minWidth(this.small ? 125.0 - this.spacing : 200.0).centerY().expandX().widget();
        }
        this.textBox.actionOnUnfocused = this::lambda$init$0;
        if (this.slider != null) {
            this.slider.action = this::lambda$init$1;
            this.slider.actionOnRelease = this::lambda$init$2;
        }
    }
}

