/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.containers;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WVerticalList;
import minegame159.meteorclient.utils.Utils;

public abstract class WSection
extends WVerticalList {
    protected String title;
    private double forcedHeight = -1.0;
    private boolean firstTime = true;
    protected final WWidget headerWidget;
    protected boolean expanded;
    private WHeader header;
    public Runnable action;
    private double actualHeight;
    protected double animProgress;
    private double actualWidth;

    public void setExpanded(boolean bl) {
        this.expanded = bl;
    }

    @Override
    public void init() {
        this.header = this.createHeader();
        this.header.theme = this.theme;
        super.add(this.header).expandX();
    }

    @Override
    public <T extends WWidget> Cell<T> add(T t) {
        return super.add(t).padHorizontal(6.0);
    }

    @Override
    protected boolean propagateEvents(WWidget wWidget) {
        return this.expanded || wWidget instanceof WHeader;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    protected abstract WHeader createHeader();

    public WSection(String string, boolean bl, WWidget wWidget) {
        this.title = string;
        this.expanded = bl;
        this.headerWidget = wWidget;
        this.animProgress = bl ? 1.0 : 0.0;
    }

    @Override
    protected void onCalculateSize() {
        if (this.forcedHeight == -1.0) {
            super.onCalculateSize();
            this.actualWidth = this.width;
            this.actualHeight = this.height;
        } else {
            this.width = this.actualWidth;
            this.height = this.forcedHeight;
            if (this.animProgress == 1.0) {
                this.forcedHeight = -1.0;
            }
        }
        if (this.firstTime) {
            this.firstTime = false;
            this.forcedHeight = (this.actualHeight - this.header.height) * this.animProgress + this.header.height;
            this.onCalculateSize();
        }
    }

    @Override
    public boolean render(GuiRenderer guiRenderer, double d, double d2, double d3) {
        boolean bl;
        if (!this.visible) {
            return true;
        }
        double d4 = this.animProgress;
        this.animProgress += (double)(this.expanded ? 1 : -1) * d3 * 14.0;
        this.animProgress = Utils.clamp(this.animProgress, 0.0, 1.0);
        if (this.animProgress != d4) {
            this.forcedHeight = (this.actualHeight - this.header.height) * this.animProgress + this.header.height;
            this.invalidate();
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
    protected void renderWidget(WWidget wWidget, GuiRenderer guiRenderer, double d, double d2, double d3) {
        if (this.expanded || this.animProgress > 0.0 || wWidget instanceof WHeader) {
            wWidget.render(guiRenderer, d, d2, d3);
        }
    }

    protected abstract class WHeader
    extends WHorizontalList {
        final WSection this$0;
        protected String title;

        public WHeader(WSection wSection, String string) {
            this.this$0 = wSection;
            this.title = string;
        }

        protected void onClick() {
            this.this$0.setExpanded(!this.this$0.expanded);
            if (this.this$0.action != null) {
                this.this$0.action.run();
            }
        }

        @Override
        public boolean onMouseClicked(double d, double d2, int n, boolean bl) {
            if (this.mouseOver && n == 0 && !bl) {
                this.onClick();
                return true;
            }
            return false;
        }
    }
}

