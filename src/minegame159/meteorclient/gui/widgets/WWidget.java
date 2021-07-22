/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.utils.BaseWidget;
import minegame159.meteorclient.gui.widgets.WRoot;

public abstract class WWidget
implements BaseWidget {
    public double width;
    public String tooltip;
    protected double mouseOverTimer;
    public boolean visible = true;
    public double minWidth;
    public GuiTheme theme;
    public double x;
    public boolean mouseOver;
    public double y;
    public WWidget parent;
    public double height;

    public boolean onKeyRepeated(int n, int n2) {
        return false;
    }

    public double pad() {
        return this.theme.pad();
    }

    protected void onCalculateSize() {
    }

    public void move(double d, double d2) {
        this.x = Math.round(this.x + d);
        this.y = Math.round(this.y + d2);
    }

    public boolean onMouseClicked(double d, double d2, int n, boolean bl) {
        return false;
    }

    public void calculateSize() {
        this.onCalculateSize();
        double d = this.theme.scale(this.minWidth);
        if (this.width < d) {
            this.width = d;
        }
        this.width = Math.round(this.width);
        this.height = Math.round(this.height);
    }

    public void init() {
    }

    public boolean render(GuiRenderer guiRenderer, double d, double d2, double d3) {
        if (!this.visible) {
            return true;
        }
        if (this.isOver(d, d2)) {
            this.mouseOverTimer += d3;
            if (this.mouseOverTimer >= 1.0 && this.tooltip != null) {
                guiRenderer.tooltip(this.tooltip);
            }
        } else {
            this.mouseOverTimer = 0.0;
        }
        this.onRender(guiRenderer, d, d2, d3);
        return false;
    }

    public boolean mouseReleased(double d, double d2, int n) {
        return this.onMouseReleased(d, d2, n);
    }

    public void onMouseScrolled(double d) {
    }

    public boolean keyRepeated(int n, int n2) {
        return this.onKeyRepeated(n, n2);
    }

    public boolean onMouseReleased(double d, double d2, int n) {
        return false;
    }

    public boolean keyPressed(int n, int n2) {
        return this.onKeyPressed(n, n2);
    }

    protected WWidget getRoot() {
        return this.parent != null ? this.parent.getRoot() : (this instanceof WRoot ? this : null);
    }

    public boolean onCharTyped(char c) {
        return false;
    }

    public boolean mouseClicked(double d, double d2, int n, boolean bl) {
        return this.onMouseClicked(d, d2, n, bl);
    }

    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
    }

    public boolean onKeyPressed(int n, int n2) {
        return false;
    }

    public void mouseScrolled(double d) {
        this.onMouseScrolled(d);
    }

    public void onMouseMoved(double d, double d2, double d3, double d4) {
    }

    protected void onCalculateWidgetPositions() {
    }

    public void invalidate() {
        WWidget wWidget = this.getRoot();
        if (wWidget != null) {
            wWidget.invalidate();
        }
    }

    public boolean isOver(double d, double d2) {
        return d >= this.x && d <= this.x + this.width && d2 >= this.y && d2 <= this.y + this.height;
    }

    @Override
    public GuiTheme getTheme() {
        return this.theme;
    }

    public boolean charTyped(char c) {
        return this.onCharTyped(c);
    }

    public void mouseMoved(double d, double d2, double d3, double d4) {
        this.mouseOver = this.isOver(d, d2);
        this.onMouseMoved(d, d2, d3, d4);
    }

    public void calculateWidgetPositions() {
        this.x = Math.round(this.x);
        this.y = Math.round(this.y);
        this.onCalculateWidgetPositions();
    }
}

