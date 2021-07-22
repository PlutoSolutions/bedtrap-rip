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
    public /* synthetic */ boolean scrollOnlyWhenMouseOver;
    private /* synthetic */ double targetScroll;
    protected /* synthetic */ boolean handleMouseOver;
    private /* synthetic */ double scroll;
    private /* synthetic */ boolean moveAfterPositionWidgets;
    public /* synthetic */ boolean hasScrollBar;
    public /* synthetic */ double maxHeight;
    private /* synthetic */ double actualHeight;
    protected /* synthetic */ boolean handlePressed;
    protected /* synthetic */ boolean canScroll;

    protected double handleX() {
        WView lllllllllllllllllIlllIlIlIllIIIl;
        return lllllllllllllllllIlllIlIlIllIIIl.x + lllllllllllllllllIlllIlIlIllIIIl.width - lllllllllllllllllIlllIlIlIllIIIl.handleWidth();
    }

    @Override
    protected boolean propagateEvents(WWidget lllllllllllllllllIlllIlIlIlllIll) {
        WView lllllllllllllllllIlllIlIlIlllIlI;
        return lllllllllllllllllIlllIlIlIlllIll.y >= lllllllllllllllllIlllIlIlIlllIlI.y && lllllllllllllllllIlllIlIlIlllIll.y <= lllllllllllllllllIlllIlIlIlllIlI.y + lllllllllllllllllIlllIlIlIlllIlI.height || lllllllllllllllllIlllIlIlIlllIll.y + lllllllllllllllllIlllIlIlIlllIll.height >= lllllllllllllllllIlllIlIlIlllIlI.y && lllllllllllllllllIlllIlIlIlllIll.y + lllllllllllllllllIlllIlIlIlllIll.height <= lllllllllllllllllIlllIlIlIlllIlI.y + lllllllllllllllllIlllIlIlIlllIlI.height || lllllllllllllllllIlllIlIlIlllIlI.y >= lllllllllllllllllIlllIlIlIlllIll.y && lllllllllllllllllIlllIlIlIlllIlI.y <= lllllllllllllllllIlllIlIlIlllIll.y + lllllllllllllllllIlllIlIlIlllIll.height || lllllllllllllllllIlllIlIlIlllIlI.y + lllllllllllllllllIlllIlIlIlllIlI.height >= lllllllllllllllllIlllIlIlIlllIll.y && lllllllllllllllllIlllIlIlIlllIlI.y + lllllllllllllllllIlllIlIlIlllIlI.height <= lllllllllllllllllIlllIlIlIlllIll.y + lllllllllllllllllIlllIlIlIlllIll.height;
    }

    public WView() {
        WView lllllllllllllllllIlllIllIIIllIll;
        lllllllllllllllllIlllIllIIIllIll.maxHeight = Double.MAX_VALUE;
        lllllllllllllllllIlllIllIIIllIll.scrollOnlyWhenMouseOver = true;
        lllllllllllllllllIlllIllIIIllIll.hasScrollBar = true;
    }

    @Override
    public boolean onMouseReleased(double lllllllllllllllllIlllIllIIIIIIIl, double lllllllllllllllllIlllIllIIIIIIII, int lllllllllllllllllIlllIlIllllllll) {
        WView lllllllllllllllllIlllIllIIIIIIlI;
        if (lllllllllllllllllIlllIllIIIIIIlI.handlePressed) {
            lllllllllllllllllIlllIllIIIIIIlI.handlePressed = false;
        }
        return false;
    }

    protected double handleY() {
        WView lllllllllllllllllIlllIlIlIlIllIl;
        return lllllllllllllllllIlllIlIlIlIllIl.y + (lllllllllllllllllIlllIlIlIlIllIl.height - lllllllllllllllllIlllIlIlIlIllIl.handleHeight()) * (lllllllllllllllllIlllIlIlIlIllIl.scroll / (lllllllllllllllllIlllIlIlIlIllIl.actualHeight - lllllllllllllllllIlllIlIlIlIllIl.height));
    }

    @Override
    public void init() {
        WView lllllllllllllllllIlllIllIIIllIII;
        lllllllllllllllllIlllIllIIIllIII.maxHeight = (double)Utils.getWindowHeight() - lllllllllllllllllIlllIllIIIllIII.theme.scale(128.0);
    }

    @Override
    public boolean onMouseClicked(double lllllllllllllllllIlllIllIIIIlIlI, double lllllllllllllllllIlllIllIIIIlIIl, int lllllllllllllllllIlllIllIIIIIlIl, boolean lllllllllllllllllIlllIllIIIIIlll) {
        WView lllllllllllllllllIlllIllIIIIIllI;
        if (lllllllllllllllllIlllIllIIIIIllI.handleMouseOver && lllllllllllllllllIlllIllIIIIIlIl == 0 && !lllllllllllllllllIlllIllIIIIIlll) {
            lllllllllllllllllIlllIllIIIIIllI.handlePressed = true;
            return true;
        }
        return false;
    }

    @Override
    protected void onCalculateSize() {
        WView lllllllllllllllllIlllIllIIIlIlIl;
        boolean lllllllllllllllllIlllIllIIIlIlII = lllllllllllllllllIlllIllIIIlIlIl.canScroll;
        lllllllllllllllllIlllIllIIIlIlIl.canScroll = false;
        lllllllllllllllllIlllIllIIIlIlIl.widthRemove = 0.0;
        super.onCalculateSize();
        if (lllllllllllllllllIlllIllIIIlIlIl.height > lllllllllllllllllIlllIllIIIlIlIl.maxHeight) {
            lllllllllllllllllIlllIllIIIlIlIl.actualHeight = lllllllllllllllllIlllIllIIIlIlIl.height;
            lllllllllllllllllIlllIllIIIlIlIl.height = lllllllllllllllllIlllIllIIIlIlIl.maxHeight;
            lllllllllllllllllIlllIllIIIlIlIl.canScroll = true;
            if (lllllllllllllllllIlllIllIIIlIlIl.hasScrollBar) {
                lllllllllllllllllIlllIllIIIlIlIl.widthRemove = lllllllllllllllllIlllIllIIIlIlIl.handleWidth() * 2.0;
                lllllllllllllllllIlllIllIIIlIlIl.width += lllllllllllllllllIlllIllIIIlIlIl.widthRemove;
            }
            if (lllllllllllllllllIlllIllIIIlIlII) {
                lllllllllllllllllIlllIllIIIlIlIl.moveAfterPositionWidgets = true;
            }
        } else {
            lllllllllllllllllIlllIllIIIlIlIl.actualHeight = lllllllllllllllllIlllIllIIIlIlIl.height;
            lllllllllllllllllIlllIllIIIlIlIl.scroll = 0.0;
            lllllllllllllllllIlllIllIIIlIlIl.targetScroll = 0.0;
        }
    }

    @Override
    protected void onCalculateWidgetPositions() {
        WView lllllllllllllllllIlllIllIIIlIIII;
        super.onCalculateWidgetPositions();
        if (lllllllllllllllllIlllIllIIIlIIII.moveAfterPositionWidgets) {
            lllllllllllllllllIlllIllIIIlIIII.targetScroll = lllllllllllllllllIlllIllIIIlIIII.scroll = Utils.clamp(lllllllllllllllllIlllIllIIIlIIII.scroll, 0.0, lllllllllllllllllIlllIllIIIlIIII.actualHeight - lllllllllllllllllIlllIllIIIlIIII.height);
            lllllllllllllllllIlllIllIIIlIIII.moveCells(0.0, -lllllllllllllllllIlllIllIIIlIIII.scroll);
            lllllllllllllllllIlllIllIIIlIIII.moveAfterPositionWidgets = false;
        }
    }

    protected double handleWidth() {
        WView lllllllllllllllllIlllIlIlIllIlll;
        return lllllllllllllllllIlllIlIlIllIlll.theme.scale(6.0);
    }

    @Override
    public boolean render(GuiRenderer lllllllllllllllllIlllIlIllIllIII, double lllllllllllllllllIlllIlIllIlIIIl, double lllllllllllllllllIlllIlIllIlIIII, double lllllllllllllllllIlllIlIllIIllll) {
        WView lllllllllllllllllIlllIlIllIllIIl;
        lllllllllllllllllIlllIlIllIllIIl.updateScroll(lllllllllllllllllIlllIlIllIIllll);
        if (lllllllllllllllllIlllIlIllIllIIl.canScroll) {
            lllllllllllllllllIlllIlIllIllIII.scissorStart(lllllllllllllllllIlllIlIllIllIIl.x, lllllllllllllllllIlllIlIllIllIIl.y, lllllllllllllllllIlllIlIllIllIIl.width, lllllllllllllllllIlllIlIllIllIIl.height);
        }
        boolean lllllllllllllllllIlllIlIllIlIlII = super.render(lllllllllllllllllIlllIlIllIllIII, lllllllllllllllllIlllIlIllIlIIIl, lllllllllllllllllIlllIlIllIlIIII, lllllllllllllllllIlllIlIllIIllll);
        if (lllllllllllllllllIlllIlIllIllIIl.canScroll) {
            lllllllllllllllllIlllIlIllIllIII.scissorEnd();
        }
        return lllllllllllllllllIlllIlIllIlIlII;
    }

    private void updateScroll(double lllllllllllllllllIlllIlIllIIIIlI) {
        WView lllllllllllllllllIlllIlIllIIlIII;
        double lllllllllllllllllIlllIlIllIIIllI = lllllllllllllllllIlllIlIllIIlIII.scroll;
        double lllllllllllllllllIlllIlIllIIIlIl = lllllllllllllllllIlllIlIllIIlIII.actualHeight - lllllllllllllllllIlllIlIllIIlIII.height;
        if (Math.abs(lllllllllllllllllIlllIlIllIIlIII.targetScroll - lllllllllllllllllIlllIlIllIIlIII.scroll) < 1.0) {
            lllllllllllllllllIlllIlIllIIlIII.scroll = lllllllllllllllllIlllIlIllIIlIII.targetScroll;
        } else if (lllllllllllllllllIlllIlIllIIlIII.targetScroll > lllllllllllllllllIlllIlIllIIlIII.scroll) {
            lllllllllllllllllIlllIlIllIIlIII.scroll += (double)Math.round(lllllllllllllllllIlllIlIllIIlIII.theme.scale(lllllllllllllllllIlllIlIllIIIIlI * 300.0 + lllllllllllllllllIlllIlIllIIIIlI * 100.0 * (Math.abs(lllllllllllllllllIlllIlIllIIlIII.targetScroll - lllllllllllllllllIlllIlIllIIlIII.scroll) / 10.0)));
            if (lllllllllllllllllIlllIlIllIIlIII.scroll > lllllllllllllllllIlllIlIllIIlIII.targetScroll) {
                lllllllllllllllllIlllIlIllIIlIII.scroll = lllllllllllllllllIlllIlIllIIlIII.targetScroll;
            }
        } else if (lllllllllllllllllIlllIlIllIIlIII.targetScroll < lllllllllllllllllIlllIlIllIIlIII.scroll) {
            lllllllllllllllllIlllIlIllIIlIII.scroll -= (double)Math.round(lllllllllllllllllIlllIlIllIIlIII.theme.scale(lllllllllllllllllIlllIlIllIIIIlI * 300.0 + lllllllllllllllllIlllIlIllIIIIlI * 100.0 * (Math.abs(lllllllllllllllllIlllIlIllIIlIII.targetScroll - lllllllllllllllllIlllIlIllIIlIII.scroll) / 10.0)));
            if (lllllllllllllllllIlllIlIllIIlIII.scroll < lllllllllllllllllIlllIlIllIIlIII.targetScroll) {
                lllllllllllllllllIlllIlIllIIlIII.scroll = lllllllllllllllllIlllIlIllIIlIII.targetScroll;
            }
        }
        lllllllllllllllllIlllIlIllIIlIII.scroll = Utils.clamp(lllllllllllllllllIlllIlIllIIlIII.scroll, 0.0, lllllllllllllllllIlllIlIllIIIlIl);
        double lllllllllllllllllIlllIlIllIIIlII = lllllllllllllllllIlllIlIllIIlIII.scroll - lllllllllllllllllIlllIlIllIIIllI;
        if (lllllllllllllllllIlllIlIllIIIlII != 0.0) {
            lllllllllllllllllIlllIlIllIIlIII.moveCells(0.0, -lllllllllllllllllIlllIlIllIIIlII);
        }
    }

    @Override
    public void onMouseScrolled(double lllllllllllllllllIlllIlIlllIIIlI) {
        WView lllllllllllllllllIlllIlIlllIIIll;
        if (!lllllllllllllllllIlllIlIlllIIIll.scrollOnlyWhenMouseOver || lllllllllllllllllIlllIlIlllIIIll.mouseOver) {
            lllllllllllllllllIlllIlIlllIIIll.targetScroll -= (double)Math.round(lllllllllllllllllIlllIlIlllIIIll.theme.scale(lllllllllllllllllIlllIlIlllIIIlI * 40.0));
            lllllllllllllllllIlllIlIlllIIIll.targetScroll = Utils.clamp(lllllllllllllllllIlllIlIlllIIIll.targetScroll, 0.0, lllllllllllllllllIlllIlIlllIIIll.actualHeight - lllllllllllllllllIlllIlIlllIIIll.height);
        }
    }

    @Override
    public void onMouseMoved(double lllllllllllllllllIlllIlIllllIIII, double lllllllllllllllllIlllIlIlllIllll, double lllllllllllllllllIlllIlIlllIlllI, double lllllllllllllllllIlllIlIlllIllIl) {
        WView lllllllllllllllllIlllIlIllllIIIl;
        lllllllllllllllllIlllIlIllllIIIl.handleMouseOver = false;
        if (lllllllllllllllllIlllIlIllllIIIl.canScroll && lllllllllllllllllIlllIlIllllIIIl.hasScrollBar) {
            double lllllllllllllllllIlllIlIllllIllI = lllllllllllllllllIlllIlIllllIIIl.handleX();
            double lllllllllllllllllIlllIlIllllIlIl = lllllllllllllllllIlllIlIllllIIIl.handleY();
            if (lllllllllllllllllIlllIlIllllIIII >= lllllllllllllllllIlllIlIllllIllI && lllllllllllllllllIlllIlIllllIIII <= lllllllllllllllllIlllIlIllllIllI + lllllllllllllllllIlllIlIllllIIIl.handleWidth() && lllllllllllllllllIlllIlIlllIllll >= lllllllllllllllllIlllIlIllllIlIl && lllllllllllllllllIlllIlIlllIllll <= lllllllllllllllllIlllIlIllllIlIl + lllllllllllllllllIlllIlIllllIIIl.handleHeight()) {
                lllllllllllllllllIlllIlIllllIIIl.handleMouseOver = true;
            }
        }
        if (lllllllllllllllllIlllIlIllllIIIl.handlePressed) {
            double lllllllllllllllllIlllIlIllllIlII = lllllllllllllllllIlllIlIllllIIIl.scroll;
            double lllllllllllllllllIlllIlIllllIIll = lllllllllllllllllIlllIlIlllIllll - lllllllllllllllllIlllIlIlllIllIl;
            lllllllllllllllllIlllIlIllllIIIl.scroll += (double)Math.round(lllllllllllllllllIlllIlIllllIIll * ((lllllllllllllllllIlllIlIllllIIIl.actualHeight - lllllllllllllllllIlllIlIllllIIIl.handleHeight() / 2.0) / lllllllllllllllllIlllIlIllllIIIl.height));
            lllllllllllllllllIlllIlIllllIIIl.targetScroll = lllllllllllllllllIlllIlIllllIIIl.scroll = Utils.clamp(lllllllllllllllllIlllIlIllllIIIl.scroll, 0.0, lllllllllllllllllIlllIlIllllIIIl.actualHeight - lllllllllllllllllIlllIlIllllIIIl.height);
            double lllllllllllllllllIlllIlIllllIIlI = lllllllllllllllllIlllIlIllllIIIl.scroll - lllllllllllllllllIlllIlIllllIlII;
            if (lllllllllllllllllIlllIlIllllIIlI != 0.0) {
                lllllllllllllllllIlllIlIllllIIIl.moveCells(0.0, -lllllllllllllllllIlllIlIllllIIlI);
            }
        }
    }

    protected double handleHeight() {
        WView lllllllllllllllllIlllIlIlIllIIll;
        return lllllllllllllllllIlllIlIlIllIIll.height / lllllllllllllllllIlllIlIlIllIIll.actualHeight * lllllllllllllllllIlllIlIlIllIIll.height;
    }
}

