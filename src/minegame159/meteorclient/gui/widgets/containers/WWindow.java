/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.containers;

import java.util.function.Consumer;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.utils.WindowConfig;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WVerticalList;
import minegame159.meteorclient.gui.widgets.containers.WView;
import minegame159.meteorclient.gui.widgets.pressable.WTriangle;
import minegame159.meteorclient.utils.Utils;

public abstract class WWindow
extends WVerticalList {
    protected boolean moved = false;
    protected boolean dragged;
    public WView view;
    public double padding = 8.0;
    private boolean propagateEventsExpanded;
    protected WHeader header;
    protected double animProgress = 1.0;
    protected boolean dragging;
    protected double movedY;
    protected double movedX;
    protected boolean expanded = true;
    protected final String title;
    public String id;
    public Consumer<WContainer> beforeHeaderInit;

    public WWindow(String string) {
        this.title = string;
    }

    @Override
    protected void onCalculateWidgetPositions() {
        if (this.id != null) {
            WindowConfig windowConfig = this.theme.getWindowConfig(this.id);
            if (windowConfig.x != -1.0) {
                this.x = windowConfig.x;
                if (this.x + this.width > (double)Utils.getWindowWidth()) {
                    this.x = (double)Utils.getWindowWidth() - this.width;
                }
            }
            if (windowConfig.y != -1.0) {
                this.y = windowConfig.y;
                if (this.y + this.height > (double)Utils.getWindowHeight()) {
                    this.y = (double)Utils.getWindowHeight() - this.height;
                }
            }
        }
        super.onCalculateWidgetPositions();
        if (this.moved) {
            this.move(this.movedX - this.x, this.movedY - this.y);
        }
    }

    @Override
    public boolean render(GuiRenderer guiRenderer, double d, double d2, double d3) {
        boolean bl;
        if (!this.visible) {
            return true;
        }
        boolean bl2 = bl = this.animProgress != 0.0 && this.animProgress != 1.0 || this.expanded && this.animProgress != 1.0;
        if (bl) {
            guiRenderer.scissorStart(this.x, this.y, this.width, (this.height - this.header.height) * this.animProgress + this.header.height);
        }
        boolean bl3 = super.render(guiRenderer, d, d2, d3);
        if (bl) {
            guiRenderer.scissorEnd();
        }
        return bl3;
    }

    @Override
    public void init() {
        this.header = this.header();
        this.header.theme = this.theme;
        super.add(this.header).expandWidgetX().widget();
        this.view = super.add(this.theme.view()).expandX().pad(this.padding).widget();
        if (this.id != null) {
            this.expanded = this.theme.getWindowConfig((String)this.id).expanded;
            this.animProgress = this.expanded ? 1.0 : 0.0;
        }
    }

    @Override
    public void clear() {
        this.view.clear();
    }

    @Override
    protected void renderWidget(WWidget wWidget, GuiRenderer guiRenderer, double d, double d2, double d3) {
        if (this.expanded || this.animProgress > 0.0 || wWidget instanceof WHeader) {
            wWidget.render(guiRenderer, d, d2, d3);
        }
        this.propagateEventsExpanded = this.expanded;
    }

    public void setExpanded(boolean bl) {
        this.expanded = bl;
        if (this.id != null) {
            WindowConfig windowConfig = this.theme.getWindowConfig(this.id);
            windowConfig.expanded = bl;
        }
    }

    protected abstract WHeader header();

    @Override
    public <T extends WWidget> Cell<T> add(T t) {
        return this.view.add(t);
    }

    @Override
    protected boolean propagateEvents(WWidget wWidget) {
        return wWidget instanceof WHeader || this.propagateEventsExpanded;
    }

    protected abstract class WHeader
    extends WContainer {
        private WTriangle triangle;
        private WHorizontalList list;
        final WWindow this$0;

        @Override
        public void onMouseMoved(double d, double d2, double d3, double d4) {
            if (this.this$0.dragging) {
                this.this$0.move(d - d3, d2 - d4);
                this.this$0.moved = true;
                this.this$0.movedX = this.x;
                this.this$0.movedY = this.y;
                if (this.this$0.id != null) {
                    WindowConfig windowConfig = this.theme.getWindowConfig(this.this$0.id);
                    windowConfig.x = this.x;
                    windowConfig.y = this.y;
                }
                this.this$0.dragged = true;
            }
        }

        @Override
        public boolean onMouseReleased(double d, double d2, int n) {
            if (this.this$0.dragging) {
                this.this$0.dragging = false;
                if (!this.this$0.dragged) {
                    this.this$0.setExpanded(!this.this$0.expanded);
                }
            }
            return false;
        }

        @Override
        public boolean onMouseClicked(double d, double d2, int n, boolean bl) {
            if (this.mouseOver && !bl) {
                if (n == 1) {
                    this.this$0.setExpanded(!this.this$0.expanded);
                } else {
                    this.this$0.dragging = true;
                    this.this$0.dragged = false;
                }
                return true;
            }
            return false;
        }

        @Override
        protected void onCalculateSize() {
            this.width = 0.0;
            this.height = 0.0;
            for (Cell cell : this.cells) {
                double d = cell.padLeft() + ((WWidget)cell.widget()).width + cell.padRight();
                if (cell.widget() instanceof WTriangle) {
                    d *= 2.0;
                }
                this.width += d;
                this.height = Math.max(this.height, cell.padTop() + ((WWidget)cell.widget()).height + cell.padBottom());
            }
        }

        protected WHeader(WWindow wWindow) {
            this.this$0 = wWindow;
        }

        @Override
        public void init() {
            if (this.this$0.beforeHeaderInit != null) {
                this.list = this.add(this.theme.horizontalList()).expandX().widget();
                this.list.spacing = 0.0;
                this.this$0.beforeHeaderInit.accept(this);
            }
            this.add(this.theme.label(this.this$0.title, true)).expandCellX().center().pad(4.0);
            this.triangle = this.add(this.theme.triangle()).pad(4.0).right().centerY().widget();
            this.triangle.action = this::lambda$init$0;
        }

        @Override
        public <T extends WWidget> Cell<T> add(T t) {
            if (this.list != null) {
                return this.list.add(t);
            }
            return super.add(t);
        }

        @Override
        public boolean render(GuiRenderer guiRenderer, double d, double d2, double d3) {
            this.this$0.animProgress += (double)(this.this$0.expanded ? 1 : -1) * d3 * 14.0;
            this.this$0.animProgress = Utils.clamp(this.this$0.animProgress, 0.0, 1.0);
            this.triangle.rotation = (1.0 - this.this$0.animProgress) * -90.0;
            return super.render(guiRenderer, d, d2, d3);
        }

        private void lambda$init$0() {
            this.this$0.setExpanded(!this.this$0.expanded);
        }
    }
}

