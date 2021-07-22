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
    public /* synthetic */ double width;
    public /* synthetic */ String tooltip;
    protected /* synthetic */ double mouseOverTimer;
    public /* synthetic */ boolean visible;
    public /* synthetic */ double minWidth;
    public /* synthetic */ GuiTheme theme;
    public /* synthetic */ double x;
    public /* synthetic */ boolean mouseOver;
    public /* synthetic */ double y;
    public /* synthetic */ WWidget parent;
    public /* synthetic */ double height;

    public boolean onKeyRepeated(int llllllllllllllllllllIllIlIlllllI, int llllllllllllllllllllIllIlIllllIl) {
        return false;
    }

    public double pad() {
        WWidget llllllllllllllllllllIlllIIllllII;
        return llllllllllllllllllllIlllIIllllII.theme.pad();
    }

    protected void onCalculateSize() {
    }

    public void move(double llllllllllllllllllllIlllIlIIIlIl, double llllllllllllllllllllIlllIlIIIIIl) {
        WWidget llllllllllllllllllllIlllIlIIIllI;
        llllllllllllllllllllIlllIlIIIllI.x = Math.round(llllllllllllllllllllIlllIlIIIllI.x + llllllllllllllllllllIlllIlIIIlIl);
        llllllllllllllllllllIlllIlIIIllI.y = Math.round(llllllllllllllllllllIlllIlIIIllI.y + llllllllllllllllllllIlllIlIIIIIl);
    }

    public boolean onMouseClicked(double llllllllllllllllllllIlllIIIIlIll, double llllllllllllllllllllIlllIIIIlIlI, int llllllllllllllllllllIlllIIIIlIIl, boolean llllllllllllllllllllIlllIIIIlIII) {
        return false;
    }

    public void calculateSize() {
        WWidget llllllllllllllllllllIlllIIllIllI;
        llllllllllllllllllllIlllIIllIllI.onCalculateSize();
        double llllllllllllllllllllIlllIIllIlll = llllllllllllllllllllIlllIIllIllI.theme.scale(llllllllllllllllllllIlllIIllIllI.minWidth);
        if (llllllllllllllllllllIlllIIllIllI.width < llllllllllllllllllllIlllIIllIlll) {
            llllllllllllllllllllIlllIIllIllI.width = llllllllllllllllllllIlllIIllIlll;
        }
        llllllllllllllllllllIlllIIllIllI.width = Math.round(llllllllllllllllllllIlllIIllIllI.width);
        llllllllllllllllllllIlllIIllIllI.height = Math.round(llllllllllllllllllllIlllIIllIllI.height);
    }

    public void init() {
    }

    public boolean render(GuiRenderer llllllllllllllllllllIlllIIlIIlII, double llllllllllllllllllllIlllIIlIIIll, double llllllllllllllllllllIlllIIlIIlll, double llllllllllllllllllllIlllIIlIIIIl) {
        WWidget llllllllllllllllllllIlllIIlIlIlI;
        if (!llllllllllllllllllllIlllIIlIlIlI.visible) {
            return true;
        }
        if (llllllllllllllllllllIlllIIlIlIlI.isOver(llllllllllllllllllllIlllIIlIIIll, llllllllllllllllllllIlllIIlIIlll)) {
            llllllllllllllllllllIlllIIlIlIlI.mouseOverTimer += llllllllllllllllllllIlllIIlIIIIl;
            if (llllllllllllllllllllIlllIIlIlIlI.mouseOverTimer >= 1.0 && llllllllllllllllllllIlllIIlIlIlI.tooltip != null) {
                llllllllllllllllllllIlllIIlIIlII.tooltip(llllllllllllllllllllIlllIIlIlIlI.tooltip);
            }
        } else {
            llllllllllllllllllllIlllIIlIlIlI.mouseOverTimer = 0.0;
        }
        llllllllllllllllllllIlllIIlIlIlI.onRender(llllllllllllllllllllIlllIIlIIlII, llllllllllllllllllllIlllIIlIIIll, llllllllllllllllllllIlllIIlIIlll, llllllllllllllllllllIlllIIlIIIIl);
        return false;
    }

    public boolean mouseReleased(double llllllllllllllllllllIlllIIIIIIlI, double llllllllllllllllllllIlllIIIIIIIl, int llllllllllllllllllllIllIllllllII) {
        WWidget llllllllllllllllllllIllIllllllll;
        return llllllllllllllllllllIllIllllllll.onMouseReleased(llllllllllllllllllllIlllIIIIIIlI, llllllllllllllllllllIlllIIIIIIIl, llllllllllllllllllllIllIllllllII);
    }

    public void onMouseScrolled(double llllllllllllllllllllIllIllIlllII) {
    }

    public boolean keyRepeated(int llllllllllllllllllllIllIllIIlIII, int llllllllllllllllllllIllIllIIIIll) {
        WWidget llllllllllllllllllllIllIllIIlIIl;
        return llllllllllllllllllllIllIllIIlIIl.onKeyRepeated(llllllllllllllllllllIllIllIIlIII, llllllllllllllllllllIllIllIIIIll);
    }

    public boolean onMouseReleased(double llllllllllllllllllllIllIlllllIlI, double llllllllllllllllllllIllIlllllIIl, int llllllllllllllllllllIllIlllllIII) {
        return false;
    }

    public boolean keyPressed(int llllllllllllllllllllIllIllIlIlII, int llllllllllllllllllllIllIllIlIllI) {
        WWidget llllllllllllllllllllIllIllIllIII;
        return llllllllllllllllllllIllIllIllIII.onKeyPressed(llllllllllllllllllllIllIllIlIlII, llllllllllllllllllllIllIllIlIllI);
    }

    protected WWidget getRoot() {
        WWidget llllllllllllllllllllIllIlIIlllll;
        return llllllllllllllllllllIllIlIIlllll.parent != null ? llllllllllllllllllllIllIlIIlllll.parent.getRoot() : (llllllllllllllllllllIllIlIIlllll instanceof WRoot ? llllllllllllllllllllIllIlIIlllll : null);
    }

    public boolean onCharTyped(char llllllllllllllllllllIllIlIlIllll) {
        return false;
    }

    public boolean mouseClicked(double llllllllllllllllllllIlllIIIlIIII, double llllllllllllllllllllIlllIIIlIlII, int llllllllllllllllllllIlllIIIlIIll, boolean llllllllllllllllllllIlllIIIlIIlI) {
        WWidget llllllllllllllllllllIlllIIIlIIIl;
        return llllllllllllllllllllIlllIIIlIIIl.onMouseClicked(llllllllllllllllllllIlllIIIlIIII, llllllllllllllllllllIlllIIIlIlII, llllllllllllllllllllIlllIIIlIIll, llllllllllllllllllllIlllIIIlIIlI);
    }

    protected void onRender(GuiRenderer llllllllllllllllllllIlllIIIlllll, double llllllllllllllllllllIlllIIIllllI, double llllllllllllllllllllIlllIIIlllIl, double llllllllllllllllllllIlllIIIlllII) {
    }

    public boolean onKeyPressed(int llllllllllllllllllllIllIllIlIIII, int llllllllllllllllllllIllIllIIlllI) {
        return false;
    }

    public void mouseScrolled(double llllllllllllllllllllIllIllIllllI) {
        WWidget llllllllllllllllllllIllIlllIIIIl;
        llllllllllllllllllllIllIlllIIIIl.onMouseScrolled(llllllllllllllllllllIllIllIllllI);
    }

    public void onMouseMoved(double llllllllllllllllllllIllIlllIIlll, double llllllllllllllllllllIllIlllIIllI, double llllllllllllllllllllIllIlllIIlIl, double llllllllllllllllllllIllIlllIIlII) {
    }

    protected void onCalculateWidgetPositions() {
    }

    public void invalidate() {
        WWidget llllllllllllllllllllIllIlIlIlIIl;
        WWidget llllllllllllllllllllIllIlIlIlIll = llllllllllllllllllllIllIlIlIlIIl.getRoot();
        if (llllllllllllllllllllIllIlIlIlIll != null) {
            llllllllllllllllllllIllIlIlIlIll.invalidate();
        }
    }

    public boolean isOver(double llllllllllllllllllllIllIlIIlIlII, double llllllllllllllllllllIllIlIIlIIII) {
        WWidget llllllllllllllllllllIllIlIIlIIlI;
        return llllllllllllllllllllIllIlIIlIlII >= llllllllllllllllllllIllIlIIlIIlI.x && llllllllllllllllllllIllIlIIlIlII <= llllllllllllllllllllIllIlIIlIIlI.x + llllllllllllllllllllIllIlIIlIIlI.width && llllllllllllllllllllIllIlIIlIIII >= llllllllllllllllllllIllIlIIlIIlI.y && llllllllllllllllllllIllIlIIlIIII <= llllllllllllllllllllIllIlIIlIIlI.y + llllllllllllllllllllIllIlIIlIIlI.height;
    }

    @Override
    public GuiTheme getTheme() {
        WWidget llllllllllllllllllllIlllIIllllll;
        return llllllllllllllllllllIlllIIllllll.theme;
    }

    public boolean charTyped(char llllllllllllllllllllIllIlIllIIIl) {
        WWidget llllllllllllllllllllIllIlIllIlII;
        return llllllllllllllllllllIllIlIllIlII.onCharTyped(llllllllllllllllllllIllIlIllIIIl);
    }

    public WWidget() {
        WWidget llllllllllllllllllllIlllIlIIllII;
        llllllllllllllllllllIlllIlIIllII.visible = true;
    }

    public void mouseMoved(double llllllllllllllllllllIllIllllIIIl, double llllllllllllllllllllIllIllllIIII, double llllllllllllllllllllIllIlllIllll, double llllllllllllllllllllIllIlllIlllI) {
        WWidget llllllllllllllllllllIllIllllIIlI;
        llllllllllllllllllllIllIllllIIlI.mouseOver = llllllllllllllllllllIllIllllIIlI.isOver(llllllllllllllllllllIllIllllIIIl, llllllllllllllllllllIllIllllIIII);
        llllllllllllllllllllIllIllllIIlI.onMouseMoved(llllllllllllllllllllIllIllllIIIl, llllllllllllllllllllIllIllllIIII, llllllllllllllllllllIllIlllIllll, llllllllllllllllllllIllIlllIlllI);
    }

    public void calculateWidgetPositions() {
        WWidget llllllllllllllllllllIlllIIllIIIl;
        llllllllllllllllllllIlllIIllIIIl.x = Math.round(llllllllllllllllllllIlllIIllIIIl.x);
        llllllllllllllllllllIlllIIllIIIl.y = Math.round(llllllllllllllllllllIlllIIllIIIl.y);
        llllllllllllllllllllIlllIIllIIIl.onCalculateWidgetPositions();
    }
}

