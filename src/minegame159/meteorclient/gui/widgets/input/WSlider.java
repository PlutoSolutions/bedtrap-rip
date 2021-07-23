/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.widgets.input;

import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.Utils;

public abstract class WSlider
extends WWidget {
    protected double max;
    public Runnable action;
    public Runnable actionOnRelease;
    protected double valueAtDragStart;
    protected double value;
    protected double min;
    protected boolean handleMouseOver;
    protected boolean dragging;

    public void set(double d) {
        this.value = Utils.clamp(d, this.min, this.max);
    }

    public double get() {
        return this.value;
    }

    protected double handleSize() {
        return this.theme.textHeight();
    }

    protected double valueWidth() {
        double d = (this.value - this.min) / (this.max - this.min);
        return d * (this.width - this.handleSize());
    }

    @Override
    protected void onCalculateSize() {
        double d;
        this.width = d = this.handleSize();
        this.height = d;
    }

    @Override
    public boolean onMouseReleased(double d, double d2, int n) {
        if (this.dragging) {
            if (this.value != this.valueAtDragStart && this.actionOnRelease != null) {
                this.actionOnRelease.run();
            }
            this.dragging = false;
            return true;
        }
        return false;
    }

    @Override
    public void onMouseMoved(double d, double d2, double d3, double d4) {
        double d5 = this.valueWidth();
        double d6 = this.handleSize();
        double d7 = d6 / 2.0;
        double d8 = this.x + d7 + d5 - this.height / 2.0;
        this.handleMouseOver = d >= d8 && d <= d8 + this.height && d2 >= this.y && d2 <= this.y + this.height;
        boolean bl = d >= this.x + d7 && d <= this.x + d7 + this.width - d6;
        boolean bl2 = this.mouseOver = bl && d2 >= this.y && d2 <= this.y + this.height;
        if (this.dragging) {
            if (bl) {
                d5 += d - d3;
                d5 = Utils.clamp(d5, 0.0, this.width - d6);
                this.set(d5 / (this.width - d6) * (this.max - this.min) + this.min);
                if (this.action != null) {
                    this.action.run();
                }
            } else if (this.value > this.min && d < this.x + d7) {
                this.value = this.min;
                if (this.action != null) {
                    this.action.run();
                }
            } else if (this.value < this.max && d > this.x + d7 + this.width - d6) {
                this.value = this.max;
                if (this.action != null) {
                    this.action.run();
                }
            }
        }
    }

    public WSlider(double d, double d2, double d3) {
        this.value = Utils.clamp(d, d2, d3);
        this.min = d2;
        this.max = d3;
    }

    @Override
    public boolean onMouseClicked(double d, double d2, int n, boolean bl) {
        if (this.mouseOver && !bl) {
            this.valueAtDragStart = this.value;
            double d3 = this.handleSize();
            double d4 = d - (this.x + d3 / 2.0);
            this.set(d4 / (this.width - d3) * (this.max - this.min) + this.min);
            if (this.action != null) {
                this.action.run();
            }
            this.dragging = true;
            return true;
        }
        return false;
    }
}

