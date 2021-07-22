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
    protected /* synthetic */ boolean moved;
    protected /* synthetic */ boolean dragged;
    public /* synthetic */ WView view;
    public /* synthetic */ double padding;
    private /* synthetic */ boolean propagateEventsExpanded;
    protected /* synthetic */ WHeader header;
    protected /* synthetic */ double animProgress;
    protected /* synthetic */ boolean dragging;
    protected /* synthetic */ double movedY;
    protected /* synthetic */ double movedX;
    protected /* synthetic */ boolean expanded;
    protected final /* synthetic */ String title;
    public /* synthetic */ String id;
    public /* synthetic */ Consumer<WContainer> beforeHeaderInit;

    public WWindow(String llIlIllIllIIII) {
        WWindow llIlIllIllIIIl;
        llIlIllIllIIIl.padding = 8.0;
        llIlIllIllIIIl.expanded = true;
        llIlIllIllIIIl.animProgress = 1.0;
        llIlIllIllIIIl.moved = false;
        llIlIllIllIIIl.title = llIlIllIllIIII;
    }

    @Override
    protected void onCalculateWidgetPositions() {
        WWindow llIlIllIIlIllI;
        if (llIlIllIIlIllI.id != null) {
            WindowConfig llIlIllIIllIII = llIlIllIIlIllI.theme.getWindowConfig(llIlIllIIlIllI.id);
            if (llIlIllIIllIII.x != -1.0) {
                llIlIllIIlIllI.x = llIlIllIIllIII.x;
                if (llIlIllIIlIllI.x + llIlIllIIlIllI.width > (double)Utils.getWindowWidth()) {
                    llIlIllIIlIllI.x = (double)Utils.getWindowWidth() - llIlIllIIlIllI.width;
                }
            }
            if (llIlIllIIllIII.y != -1.0) {
                llIlIllIIlIllI.y = llIlIllIIllIII.y;
                if (llIlIllIIlIllI.y + llIlIllIIlIllI.height > (double)Utils.getWindowHeight()) {
                    llIlIllIIlIllI.y = (double)Utils.getWindowHeight() - llIlIllIIlIllI.height;
                }
            }
        }
        super.onCalculateWidgetPositions();
        if (llIlIllIIlIllI.moved) {
            llIlIllIIlIllI.move(llIlIllIIlIllI.movedX - llIlIllIIlIllI.x, llIlIllIIlIllI.movedY - llIlIllIIlIllI.y);
        }
    }

    @Override
    public boolean render(GuiRenderer llIlIllIIIllII, double llIlIllIIIlIll, double llIlIllIIIIIll, double llIlIllIIIIIlI) {
        boolean llIlIllIIIlIII;
        WWindow llIlIllIIIIllI;
        if (!llIlIllIIIIllI.visible) {
            return true;
        }
        boolean bl = llIlIllIIIlIII = llIlIllIIIIllI.animProgress != 0.0 && llIlIllIIIIllI.animProgress != 1.0 || llIlIllIIIIllI.expanded && llIlIllIIIIllI.animProgress != 1.0;
        if (llIlIllIIIlIII) {
            llIlIllIIIllII.scissorStart(llIlIllIIIIllI.x, llIlIllIIIIllI.y, llIlIllIIIIllI.width, (llIlIllIIIIllI.height - llIlIllIIIIllI.header.height) * llIlIllIIIIllI.animProgress + llIlIllIIIIllI.header.height);
        }
        boolean llIlIllIIIIlll = super.render(llIlIllIIIllII, llIlIllIIIlIll, llIlIllIIIIIll, llIlIllIIIIIlI);
        if (llIlIllIIIlIII) {
            llIlIllIIIllII.scissorEnd();
        }
        return llIlIllIIIIlll;
    }

    @Override
    public void init() {
        WWindow llIlIllIlIlllI;
        llIlIllIlIlllI.header = llIlIllIlIlllI.header();
        llIlIllIlIlllI.header.theme = llIlIllIlIlllI.theme;
        super.add(llIlIllIlIlllI.header).expandWidgetX().widget();
        llIlIllIlIlllI.view = super.add(llIlIllIlIlllI.theme.view()).expandX().pad(llIlIllIlIlllI.padding).widget();
        if (llIlIllIlIlllI.id != null) {
            llIlIllIlIlllI.expanded = llIlIllIlIlllI.theme.getWindowConfig((String)llIlIllIlIlllI.id).expanded;
            llIlIllIlIlllI.animProgress = llIlIllIlIlllI.expanded ? 1.0 : 0.0;
        }
    }

    @Override
    public void clear() {
        WWindow llIlIllIlIIlIl;
        llIlIllIlIIlIl.view.clear();
    }

    @Override
    protected void renderWidget(WWidget llIlIlIllllIII, GuiRenderer llIlIlIlllIIIl, double llIlIlIlllIllI, double llIlIlIllIllll, double llIlIlIlllIlII) {
        WWindow llIlIlIlllIIll;
        if (llIlIlIlllIIll.expanded || llIlIlIlllIIll.animProgress > 0.0 || llIlIlIllllIII instanceof WHeader) {
            llIlIlIllllIII.render(llIlIlIlllIIIl, llIlIlIlllIllI, llIlIlIllIllll, llIlIlIlllIlII);
        }
        llIlIlIlllIIll.propagateEventsExpanded = llIlIlIlllIIll.expanded;
    }

    public void setExpanded(boolean llIlIllIIllllI) {
        WWindow llIlIllIIlllIl;
        llIlIllIIlllIl.expanded = llIlIllIIllllI;
        if (llIlIllIIlllIl.id != null) {
            WindowConfig llIlIllIlIIIII = llIlIllIIlllIl.theme.getWindowConfig(llIlIllIIlllIl.id);
            llIlIllIlIIIII.expanded = llIlIllIIllllI;
        }
    }

    protected abstract WHeader header();

    @Override
    public <T extends WWidget> Cell<T> add(T llIlIllIlIIlll) {
        WWindow llIlIllIlIlIlI;
        return llIlIllIlIlIlI.view.add(llIlIllIlIIlll);
    }

    @Override
    protected boolean propagateEvents(WWidget llIlIlIllIlIlI) {
        WWindow llIlIlIllIlIIl;
        return llIlIlIllIlIlI instanceof WHeader || llIlIlIllIlIIl.propagateEventsExpanded;
    }

    protected abstract class WHeader
    extends WContainer {
        private /* synthetic */ WTriangle triangle;
        private /* synthetic */ WHorizontalList list;

        @Override
        public void onMouseMoved(double lllllllllllllllllIIllIllllllllIl, double lllllllllllllllllIIllIllllllIlll, double lllllllllllllllllIIllIlllllllIll, double lllllllllllllllllIIllIlllllllIlI) {
            WHeader lllllllllllllllllIIllIlllllllllI;
            if (lllllllllllllllllIIllIlllllllllI.WWindow.this.dragging) {
                lllllllllllllllllIIllIlllllllllI.WWindow.this.move(lllllllllllllllllIIllIllllllllIl - lllllllllllllllllIIllIlllllllIll, lllllllllllllllllIIllIllllllIlll - lllllllllllllllllIIllIlllllllIlI);
                lllllllllllllllllIIllIlllllllllI.WWindow.this.moved = true;
                lllllllllllllllllIIllIlllllllllI.WWindow.this.movedX = lllllllllllllllllIIllIlllllllllI.x;
                lllllllllllllllllIIllIlllllllllI.WWindow.this.movedY = lllllllllllllllllIIllIlllllllllI.y;
                if (lllllllllllllllllIIllIlllllllllI.WWindow.this.id != null) {
                    WindowConfig lllllllllllllllllIIllIllllllllll = lllllllllllllllllIIllIlllllllllI.theme.getWindowConfig(lllllllllllllllllIIllIlllllllllI.WWindow.this.id);
                    lllllllllllllllllIIllIllllllllll.x = lllllllllllllllllIIllIlllllllllI.x;
                    lllllllllllllllllIIllIllllllllll.y = lllllllllllllllllIIllIlllllllllI.y;
                }
                lllllllllllllllllIIllIlllllllllI.WWindow.this.dragged = true;
            }
        }

        @Override
        public boolean onMouseReleased(double lllllllllllllllllIIlllIIIIIIlIIl, double lllllllllllllllllIIlllIIIIIIlIII, int lllllllllllllllllIIlllIIIIIIIlll) {
            WHeader lllllllllllllllllIIlllIIIIIIlIlI;
            if (lllllllllllllllllIIlllIIIIIIlIlI.WWindow.this.dragging) {
                lllllllllllllllllIIlllIIIIIIlIlI.WWindow.this.dragging = false;
                if (!lllllllllllllllllIIlllIIIIIIlIlI.WWindow.this.dragged) {
                    lllllllllllllllllIIlllIIIIIIlIlI.WWindow.this.setExpanded(!lllllllllllllllllIIlllIIIIIIlIlI.WWindow.this.expanded);
                }
            }
            return false;
        }

        @Override
        public boolean onMouseClicked(double lllllllllllllllllIIlllIIIIIlIIlI, double lllllllllllllllllIIlllIIIIIlIIIl, int lllllllllllllllllIIlllIIIIIIllIl, boolean lllllllllllllllllIIlllIIIIIIllll) {
            WHeader lllllllllllllllllIIlllIIIIIlIIll;
            if (lllllllllllllllllIIlllIIIIIlIIll.mouseOver && !lllllllllllllllllIIlllIIIIIIllll) {
                if (lllllllllllllllllIIlllIIIIIIllIl == 1) {
                    lllllllllllllllllIIlllIIIIIlIIll.WWindow.this.setExpanded(!lllllllllllllllllIIlllIIIIIlIIll.WWindow.this.expanded);
                } else {
                    lllllllllllllllllIIlllIIIIIlIIll.WWindow.this.dragging = true;
                    lllllllllllllllllIIlllIIIIIlIIll.WWindow.this.dragged = false;
                }
                return true;
            }
            return false;
        }

        @Override
        protected void onCalculateSize() {
            WHeader lllllllllllllllllIIlllIIIIIllIll;
            lllllllllllllllllIIlllIIIIIllIll.width = 0.0;
            lllllllllllllllllIIlllIIIIIllIll.height = 0.0;
            for (Cell lllllllllllllllllIIlllIIIIIlllII : lllllllllllllllllIIlllIIIIIllIll.cells) {
                double lllllllllllllllllIIlllIIIIIlllIl = lllllllllllllllllIIlllIIIIIlllII.padLeft() + ((WWidget)lllllllllllllllllIIlllIIIIIlllII.widget()).width + lllllllllllllllllIIlllIIIIIlllII.padRight();
                if (lllllllllllllllllIIlllIIIIIlllII.widget() instanceof WTriangle) {
                    lllllllllllllllllIIlllIIIIIlllIl *= 2.0;
                }
                lllllllllllllllllIIlllIIIIIllIll.width += lllllllllllllllllIIlllIIIIIlllIl;
                lllllllllllllllllIIlllIIIIIllIll.height = Math.max(lllllllllllllllllIIlllIIIIIllIll.height, lllllllllllllllllIIlllIIIIIlllII.padTop() + ((WWidget)lllllllllllllllllIIlllIIIIIlllII.widget()).height + lllllllllllllllllIIlllIIIIIlllII.padBottom());
            }
        }

        protected WHeader() {
            WHeader lllllllllllllllllIIlllIIIIlIllII;
        }

        @Override
        public void init() {
            WHeader lllllllllllllllllIIlllIIIIlIlIIl;
            if (lllllllllllllllllIIlllIIIIlIlIIl.WWindow.this.beforeHeaderInit != null) {
                lllllllllllllllllIIlllIIIIlIlIIl.list = lllllllllllllllllIIlllIIIIlIlIIl.add(lllllllllllllllllIIlllIIIIlIlIIl.theme.horizontalList()).expandX().widget();
                lllllllllllllllllIIlllIIIIlIlIIl.list.spacing = 0.0;
                lllllllllllllllllIIlllIIIIlIlIIl.WWindow.this.beforeHeaderInit.accept(lllllllllllllllllIIlllIIIIlIlIIl);
            }
            lllllllllllllllllIIlllIIIIlIlIIl.add(lllllllllllllllllIIlllIIIIlIlIIl.theme.label(lllllllllllllllllIIlllIIIIlIlIIl.WWindow.this.title, true)).expandCellX().center().pad(4.0);
            lllllllllllllllllIIlllIIIIlIlIIl.triangle = lllllllllllllllllIIlllIIIIlIlIIl.add(lllllllllllllllllIIlllIIIIlIlIIl.theme.triangle()).pad(4.0).right().centerY().widget();
            lllllllllllllllllIIlllIIIIlIlIIl.triangle.action = () -> {
                WHeader lllllllllllllllllIIllIlllllIIIll;
                lllllllllllllllllIIllIlllllIIIll.WWindow.this.setExpanded(!lllllllllllllllllIIllIlllllIIIll.WWindow.this.expanded);
            };
        }

        @Override
        public <T extends WWidget> Cell<T> add(T lllllllllllllllllIIlllIIIIlIIIlI) {
            WHeader lllllllllllllllllIIlllIIIIlIIlIl;
            if (lllllllllllllllllIIlllIIIIlIIlIl.list != null) {
                return lllllllllllllllllIIlllIIIIlIIlIl.list.add(lllllllllllllllllIIlllIIIIlIIIlI);
            }
            return super.add(lllllllllllllllllIIlllIIIIlIIIlI);
        }

        @Override
        public boolean render(GuiRenderer lllllllllllllllllIIllIlllllIlIII, double lllllllllllllllllIIllIlllllIIlll, double lllllllllllllllllIIllIlllllIIllI, double lllllllllllllllllIIllIlllllIlIlI) {
            WHeader lllllllllllllllllIIllIlllllIlllI;
            lllllllllllllllllIIllIlllllIlllI.WWindow.this.animProgress += (double)(lllllllllllllllllIIllIlllllIlllI.WWindow.this.expanded ? 1 : -1) * lllllllllllllllllIIllIlllllIlIlI * 14.0;
            lllllllllllllllllIIllIlllllIlllI.WWindow.this.animProgress = Utils.clamp(lllllllllllllllllIIllIlllllIlllI.WWindow.this.animProgress, 0.0, 1.0);
            lllllllllllllllllIIllIlllllIlllI.triangle.rotation = (1.0 - lllllllllllllllllIIllIlllllIlllI.WWindow.this.animProgress) * -90.0;
            return super.render(lllllllllllllllllIIllIlllllIlIII, lllllllllllllllllIIllIlllllIIlll, lllllllllllllllllIIllIlllllIIllI, lllllllllllllllllIIllIlllllIlIlI);
        }
    }
}

