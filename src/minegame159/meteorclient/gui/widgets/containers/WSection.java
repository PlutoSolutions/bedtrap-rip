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
    protected /* synthetic */ String title;
    private /* synthetic */ double forcedHeight;
    private /* synthetic */ boolean firstTime;
    protected final /* synthetic */ WWidget headerWidget;
    protected /* synthetic */ boolean expanded;
    private /* synthetic */ WHeader header;
    public /* synthetic */ Runnable action;
    private /* synthetic */ double actualHeight;
    protected /* synthetic */ double animProgress;
    private /* synthetic */ double actualWidth;

    public void setExpanded(boolean llIllllIlIlIl) {
        llIllllIllIII.expanded = llIllllIlIlIl;
    }

    @Override
    public void init() {
        WSection llIlllllIIIIl;
        llIlllllIIIIl.header = llIlllllIIIIl.createHeader();
        llIlllllIIIIl.header.theme = llIlllllIIIIl.theme;
        super.add(llIlllllIIIIl.header).expandX();
    }

    @Override
    public <T extends WWidget> Cell<T> add(T llIllllIlllIl) {
        WSection llIllllIllllI;
        return super.add(llIllllIlllIl).padHorizontal(6.0);
    }

    @Override
    protected boolean propagateEvents(WWidget llIlllIIlllll) {
        WSection llIlllIlIIIlI;
        return llIlllIlIIIlI.expanded || llIlllIIlllll instanceof WHeader;
    }

    public boolean isExpanded() {
        WSection llIllllIlIIlI;
        return llIllllIlIIlI.expanded;
    }

    protected abstract WHeader createHeader();

    public WSection(String llIlllllIlIlI, boolean llIlllllIIlIl, WWidget llIlllllIIlII) {
        WSection llIlllllIIlll;
        llIlllllIIlll.forcedHeight = -1.0;
        llIlllllIIlll.firstTime = true;
        llIlllllIIlll.title = llIlllllIlIlI;
        llIlllllIIlll.expanded = llIlllllIIlIl;
        llIlllllIIlll.headerWidget = llIlllllIIlII;
        llIlllllIIlll.animProgress = llIlllllIIlIl ? 1.0 : 0.0;
    }

    @Override
    protected void onCalculateSize() {
        WSection llIllllIlIIII;
        if (llIllllIlIIII.forcedHeight == -1.0) {
            super.onCalculateSize();
            llIllllIlIIII.actualWidth = llIllllIlIIII.width;
            llIllllIlIIII.actualHeight = llIllllIlIIII.height;
        } else {
            llIllllIlIIII.width = llIllllIlIIII.actualWidth;
            llIllllIlIIII.height = llIllllIlIIII.forcedHeight;
            if (llIllllIlIIII.animProgress == 1.0) {
                llIllllIlIIII.forcedHeight = -1.0;
            }
        }
        if (llIllllIlIIII.firstTime) {
            llIllllIlIIII.firstTime = false;
            llIllllIlIIII.forcedHeight = (llIllllIlIIII.actualHeight - llIllllIlIIII.header.height) * llIllllIlIIII.animProgress + llIllllIlIIII.header.height;
            llIllllIlIIII.onCalculateSize();
        }
    }

    @Override
    public boolean render(GuiRenderer llIlllIllllIl, double llIllllIIIlII, double llIlllIlllIll, double llIllllIIIIlI) {
        boolean llIllllIIIIII;
        WSection llIlllIlllllI;
        if (!llIlllIlllllI.visible) {
            return true;
        }
        double llIllllIIIIIl = llIlllIlllllI.animProgress;
        llIlllIlllllI.animProgress += (double)(llIlllIlllllI.expanded ? 1 : -1) * llIllllIIIIlI * 14.0;
        llIlllIlllllI.animProgress = Utils.clamp(llIlllIlllllI.animProgress, 0.0, 1.0);
        if (llIlllIlllllI.animProgress != llIllllIIIIIl) {
            llIlllIlllllI.forcedHeight = (llIlllIlllllI.actualHeight - llIlllIlllllI.header.height) * llIlllIlllllI.animProgress + llIlllIlllllI.header.height;
            llIlllIlllllI.invalidate();
        }
        boolean bl = llIllllIIIIII = llIlllIlllllI.animProgress != 0.0 && llIlllIlllllI.animProgress != 1.0 || llIlllIlllllI.expanded && llIlllIlllllI.animProgress != 1.0;
        if (llIllllIIIIII) {
            llIlllIllllIl.scissorStart(llIlllIlllllI.x, llIlllIlllllI.y, llIlllIlllllI.width, (llIlllIlllllI.height - llIlllIlllllI.header.height) * llIlllIlllllI.animProgress + llIlllIlllllI.header.height);
        }
        boolean llIlllIllllll = super.render(llIlllIllllIl, llIllllIIIlII, llIlllIlllIll, llIllllIIIIlI);
        if (llIllllIIIIII) {
            llIlllIllllIl.scissorEnd();
        }
        return llIlllIllllll;
    }

    @Override
    protected void renderWidget(WWidget llIlllIlIlIIl, GuiRenderer llIlllIlIlIII, double llIlllIlIllIl, double llIlllIlIIllI, double llIlllIlIlIll) {
        WSection llIlllIllIIII;
        if (llIlllIllIIII.expanded || llIlllIllIIII.animProgress > 0.0 || llIlllIlIlIIl instanceof WHeader) {
            llIlllIlIlIIl.render(llIlllIlIlIII, llIlllIlIllIl, llIlllIlIIllI, llIlllIlIlIll);
        }
    }

    protected abstract class WHeader
    extends WHorizontalList {
        protected /* synthetic */ String title;

        public WHeader(String lIllIllIIlIIIl) {
            WHeader lIllIllIIlIIII;
            lIllIllIIlIIII.title = lIllIllIIlIIIl;
        }

        protected void onClick() {
            WHeader lIllIllIIIIIII;
            lIllIllIIIIIII.WSection.this.setExpanded(!lIllIllIIIIIII.WSection.this.expanded);
            if (lIllIllIIIIIII.WSection.this.action != null) {
                lIllIllIIIIIII.WSection.this.action.run();
            }
        }

        @Override
        public boolean onMouseClicked(double lIllIllIIIlIIl, double lIllIllIIIlIII, int lIllIllIIIIlll, boolean lIllIllIIIIIll) {
            WHeader lIllIllIIIlIlI;
            if (lIllIllIIIlIlI.mouseOver && lIllIllIIIIlll == 0 && !lIllIllIIIIIll) {
                lIllIllIIIlIlI.onClick();
                return true;
            }
            return false;
        }
    }
}

