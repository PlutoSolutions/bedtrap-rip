/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.containers;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WVerticalList;
import minegame159.meteorclient.utils.Utils;

public abstract class WView
extends WVerticalList {
    public boolean scrollOnlyWhenMouseOver = true;
    private double targetScroll;
    protected boolean handleMouseOver;
    private double scroll;
    private boolean moveAfterPositionWidgets;
    public boolean hasScrollBar = true;
    public double maxHeight = Double.MAX_VALUE;
    private double actualHeight;
    protected boolean handlePressed;
    protected boolean canScroll;

    protected double handleX() {
        return this.x + this.width - this.handleWidth();
    }

    @Override
    protected boolean propagateEvents(WWidget wWidget) {
        return wWidget.y >= this.y && wWidget.y <= this.y + this.height || wWidget.y + wWidget.height >= this.y && wWidget.y + wWidget.height <= this.y + this.height || this.y >= wWidget.y && this.y <= wWidget.y + wWidget.height || this.y + this.height >= wWidget.y && this.y + this.height <= wWidget.y + wWidget.height;
    }

    @Override
    public boolean onMouseReleased(double d, double d2, int n) {
        if (this.handlePressed) {
            this.handlePressed = false;
        }
        return false;
    }

    protected double handleY() {
        return this.y + (this.height - this.handleHeight()) * (this.scroll / (this.actualHeight - this.height));
    }

    @Override
    public void init() {
        this.maxHeight = (double)Utils.getWindowHeight() - this.theme.scale(128.0);
    }

    @Override
    public boolean onMouseClicked(double d, double d2, int n, boolean bl) {
        if (this.handleMouseOver && n == 0 && !bl) {
            this.handlePressed = true;
            return true;
        }
        return false;
    }

    @Override
    protected void onCalculateSize() {
        boolean bl = this.canScroll;
        this.canScroll = false;
        this.widthRemove = 0.0;
        super.onCalculateSize();
        if (this.height > this.maxHeight) {
            this.actualHeight = this.height;
            this.height = this.maxHeight;
            this.canScroll = true;
            if (this.hasScrollBar) {
                this.widthRemove = this.handleWidth() * 2.0;
                this.width += this.widthRemove;
            }
            if (bl) {
                this.moveAfterPositionWidgets = true;
            }
        } else {
            this.actualHeight = this.height;
            this.scroll = 0.0;
            this.targetScroll = 0.0;
        }
    }

    @Override
    protected void onCalculateWidgetPositions() {
        super.onCalculateWidgetPositions();
        if (this.moveAfterPositionWidgets) {
            this.targetScroll = this.scroll = Utils.clamp(this.scroll, 0.0, this.actualHeight - this.height);
            this.moveCells(0.0, -this.scroll);
            this.moveAfterPositionWidgets = false;
        }
    }

    protected double handleWidth() {
        return this.theme.scale(6.0);
    }

    @Override
    public boolean render(GuiRenderer guiRenderer, double d, double d2, double d3) {
        this.updateScroll(d3);
        if (this.canScroll) {
            guiRenderer.scissorStart(this.x, this.y, this.width, this.height);
        }
        boolean bl = super.render(guiRenderer, d, d2, d3);
        if (this.canScroll) {
            guiRenderer.scissorEnd();
        }
        return bl;
    }

    private void updateScroll(double d) {
        double d2 = this.scroll;
        double d3 = this.actualHeight - this.height;
        if (Math.abs(this.targetScroll - this.scroll) < 1.0) {
            this.scroll = this.targetScroll;
        } else if (this.targetScroll > this.scroll) {
            this.scroll += (double)Math.round(this.theme.scale(d * 300.0 + d * 100.0 * (Math.abs(this.targetScroll - this.scroll) / 10.0)));
            if (this.scroll > this.targetScroll) {
                this.scroll = this.targetScroll;
            }
        } else if (this.targetScroll < this.scroll) {
            this.scroll -= (double)Math.round(this.theme.scale(d * 300.0 + d * 100.0 * (Math.abs(this.targetScroll - this.scroll) / 10.0)));
            if (this.scroll < this.targetScroll) {
                this.scroll = this.targetScroll;
            }
        }
        this.scroll = Utils.clamp(this.scroll, 0.0, d3);
        double d4 = this.scroll - d2;
        if (d4 != 0.0) {
            this.moveCells(0.0, -d4);
        }
    }

    @Override
    public void onMouseScrolled(double d) {
        if (!this.scrollOnlyWhenMouseOver || this.mouseOver) {
            this.targetScroll -= (double)Math.round(this.theme.scale(d * 40.0));
            this.targetScroll = Utils.clamp(this.targetScroll, 0.0, this.actualHeight - this.height);
        }
    }

    @Override
    public void onMouseMoved(double d, double d2, double d3, double d4) {
        double d5;
        double d6;
        this.handleMouseOver = false;
        if (this.canScroll && this.hasScrollBar) {
            d6 = this.handleX();
            d5 = this.handleY();
            if (d >= d6 && d <= d6 + this.handleWidth() && d2 >= d5 && d2 <= d5 + this.handleHeight()) {
                this.handleMouseOver = true;
            }
        }
        if (this.handlePressed) {
            d6 = this.scroll;
            d5 = d2 - d4;
            this.scroll += (double)Math.round(d5 * ((this.actualHeight - this.handleHeight() / 2.0) / this.height));
            this.targetScroll = this.scroll = Utils.clamp(this.scroll, 0.0, this.actualHeight - this.height);
            double d7 = this.scroll - d6;
            if (d7 != 0.0) {
                this.moveCells(0.0, -d7);
            }
        }
    }

    protected double handleHeight() {
        return this.height / this.actualHeight * this.height;
    }
}

