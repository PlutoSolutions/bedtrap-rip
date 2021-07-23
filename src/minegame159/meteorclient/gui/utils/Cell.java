/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.utils;

import minegame159.meteorclient.gui.utils.AlignmentX;
import minegame159.meteorclient.gui.utils.AlignmentY;
import minegame159.meteorclient.gui.widgets.WWidget;

public class Cell<T extends WWidget> {
    private double padBottom;
    private double padTop;
    public double y;
    private boolean expandWidgetY;
    public boolean expandCellX;
    public double width;
    private double padRight;
    public double height;
    private double padLeft;
    private boolean expandWidgetX;
    public double x;
    private final T widget;
    private AlignmentX alignX = AlignmentX.Left;
    private AlignmentY alignY = AlignmentY.Top;

    public double padLeft() {
        return this.s(this.padLeft);
    }

    public Cell(T t) {
        this.widget = t;
    }

    public Cell<T> padBottom(double d) {
        this.padBottom = d;
        return this;
    }

    public double padBottom() {
        return this.s(this.padBottom);
    }

    public Cell<T> minWidth(double d) {
        ((WWidget)this.widget).minWidth = d;
        return this;
    }

    public T widget() {
        return this.widget;
    }

    public double padRight() {
        return this.s(this.padRight);
    }

    public double padTop() {
        return this.s(this.padTop);
    }

    public Cell<T> padLeft(double d) {
        this.padLeft = d;
        return this;
    }

    public Cell<T> padRight(double d) {
        this.padRight = d;
        return this;
    }

    public Cell<T> padTop(double d) {
        this.padTop = d;
        return this;
    }

    public Cell<T> center() {
        this.alignX = AlignmentX.Center;
        this.alignY = AlignmentY.Center;
        return this;
    }

    public Cell<T> top() {
        this.alignY = AlignmentY.Top;
        return this;
    }

    private double s(double d) {
        return ((WWidget)this.widget).theme.scale(d);
    }

    public Cell<T> centerX() {
        this.alignX = AlignmentX.Center;
        return this;
    }

    public Cell<T> expandWidgetY() {
        this.expandWidgetY = true;
        return this;
    }

    public void move(double d, double d2) {
        this.x += d;
        this.y += d2;
        ((WWidget)this.widget).move(d, d2);
    }

    public Cell<T> bottom() {
        this.alignY = AlignmentY.Bottom;
        return this;
    }

    public Cell<T> padVertical(double d) {
        this.padTop = this.padBottom = d;
        return this;
    }

    public Cell<T> centerY() {
        this.alignY = AlignmentY.Center;
        return this;
    }

    public Cell<T> right() {
        this.alignX = AlignmentX.Right;
        return this;
    }

    public Cell<T> expandX() {
        this.expandWidgetX = true;
        this.expandCellX = true;
        return this;
    }

    public Cell<T> pad(double d) {
        this.padBottom = this.padLeft = d;
        this.padRight = this.padLeft;
        this.padTop = this.padLeft;
        return this;
    }

    public Cell<T> padHorizontal(double d) {
        this.padRight = this.padLeft = d;
        return this;
    }

    public void alignWidget() {
        if (this.expandWidgetX) {
            ((WWidget)this.widget).x = this.x;
            ((WWidget)this.widget).width = this.width;
        } else {
            switch (this.alignX) {
                case Left: {
                    ((WWidget)this.widget).x = this.x;
                    break;
                }
                case Center: {
                    ((WWidget)this.widget).x = this.x + this.width / 2.0 - ((WWidget)this.widget).width / 2.0;
                    break;
                }
                case Right: {
                    ((WWidget)this.widget).x = this.x + this.width - ((WWidget)this.widget).width;
                }
            }
        }
        if (this.expandWidgetY) {
            ((WWidget)this.widget).y = this.y;
            ((WWidget)this.widget).height = this.height;
        } else {
            switch (this.alignY) {
                case Top: {
                    ((WWidget)this.widget).y = this.y;
                    break;
                }
                case Center: {
                    ((WWidget)this.widget).y = this.y + this.height / 2.0 - ((WWidget)this.widget).height / 2.0;
                    break;
                }
                case Bottom: {
                    ((WWidget)this.widget).y = this.y + this.height - ((WWidget)this.widget).height;
                }
            }
        }
    }

    public Cell<T> expandWidgetX() {
        this.expandWidgetX = true;
        return this;
    }

    public Cell<T> expandCellX() {
        this.expandCellX = true;
        return this;
    }
}

