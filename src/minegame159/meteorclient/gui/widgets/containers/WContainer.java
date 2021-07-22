/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_312
 */
package minegame159.meteorclient.gui.widgets.containers;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_312;

public abstract class WContainer
extends WWidget {
    public final /* synthetic */ List<Cell<?>> cells;

    @Override
    public void calculateWidgetPositions() {
        WContainer llllIlIlllIIll;
        super.calculateWidgetPositions();
        for (Cell<?> llllIlIlllIlII : llllIlIlllIIll.cells) {
            ((WWidget)llllIlIlllIlII.widget()).calculateWidgetPositions();
        }
    }

    @Override
    public void mouseScrolled(double llllIIllllllll) {
        WContainer llllIIlllllllI;
        try {
            for (Cell<?> llllIlIIIIIIIl : llllIIlllllllI.cells) {
                if (!llllIIlllllllI.propagateEvents((WWidget)llllIlIIIIIIIl.widget())) continue;
                ((WWidget)llllIlIIIIIIIl.widget()).mouseScrolled(llllIIllllllll);
            }
        }
        catch (ConcurrentModificationException llllIIllllllII) {
            // empty catch block
        }
        super.mouseScrolled(llllIIllllllll);
    }

    public WContainer() {
        WContainer llllIllIllIlII;
        llllIllIllIlII.cells = new ArrayList();
    }

    @Override
    public boolean mouseClicked(double llllIlIIllIIII, double llllIlIIllIlII, int llllIlIIllIIll, boolean llllIlIIlIllIl) {
        WContainer llllIlIIllIllI;
        try {
            for (Cell<?> llllIlIIllIlll : llllIlIIllIllI.cells) {
                if (!llllIlIIllIllI.propagateEvents((WWidget)llllIlIIllIlll.widget()) || !((WWidget)llllIlIIllIlll.widget()).mouseClicked(llllIlIIllIIII, llllIlIIllIlII, llllIlIIllIIll, llllIlIIlIllIl)) continue;
                llllIlIIlIllIl = true;
            }
        }
        catch (ConcurrentModificationException llllIlIIlIllII) {
            // empty catch block
        }
        return super.mouseClicked(llllIlIIllIIII, llllIlIIllIlII, llllIlIIllIIll, llllIlIIlIllIl) || llllIlIIlIllIl;
    }

    @Override
    public void mouseMoved(double llllIlIIIlIIII, double llllIlIIIIlIlI, double llllIlIIIIlIIl, double llllIlIIIIlIII) {
        WContainer llllIlIIIlIIIl;
        try {
            for (Cell<?> llllIlIIIlIIlI : llllIlIIIlIIIl.cells) {
                if (!llllIlIIIlIIIl.propagateEvents((WWidget)llllIlIIIlIIlI.widget())) continue;
                ((WWidget)llllIlIIIlIIlI.widget()).mouseMoved(llllIlIIIlIIII, llllIlIIIIlIlI, llllIlIIIIlIIl, llllIlIIIIlIII);
            }
        }
        catch (ConcurrentModificationException llllIlIIIIIlll) {
            // empty catch block
        }
        super.mouseMoved(llllIlIIIlIIII, llllIlIIIIlIlI, llllIlIIIIlIIl, llllIlIIIIlIII);
    }

    @Override
    public boolean mouseReleased(double llllIlIIIllllI, double llllIlIIlIIIIl, int llllIlIIlIIIII) {
        WContainer llllIlIIlIIIll;
        try {
            for (Cell<?> llllIlIIlIIlII : llllIlIIlIIIll.cells) {
                if (!llllIlIIlIIIll.propagateEvents((WWidget)llllIlIIlIIlII.widget()) || !((WWidget)llllIlIIlIIlII.widget()).mouseReleased(llllIlIIIllllI, llllIlIIlIIIIl, llllIlIIlIIIII)) continue;
                return true;
            }
        }
        catch (ConcurrentModificationException llllIlIIIllIll) {
            // empty catch block
        }
        return super.mouseReleased(llllIlIIIllllI, llllIlIIlIIIIl, llllIlIIlIIIII);
    }

    public void clear() {
        WContainer llllIllIlIlIII;
        if (llllIllIlIlIII.cells.size() > 0) {
            llllIllIlIlIII.cells.clear();
            llllIllIlIlIII.invalidate();
        }
    }

    @Override
    public boolean keyRepeated(int llllIIlllIIIlI, int llllIIlllIIIIl) {
        WContainer llllIIlllIIIll;
        try {
            for (Cell<?> llllIIlllIIlll : llllIIlllIIIll.cells) {
                if (!llllIIlllIIIll.propagateEvents((WWidget)llllIIlllIIlll.widget()) || !((WWidget)llllIIlllIIlll.widget()).keyRepeated(llllIIlllIIIlI, llllIIlllIIIIl)) continue;
                return true;
            }
        }
        catch (ConcurrentModificationException llllIIlllIIIII) {
            // empty catch block
        }
        return llllIIlllIIIll.onKeyRepeated(llllIIlllIIIlI, llllIIlllIIIIl);
    }

    @Override
    public void move(double llllIllIIlllll, double llllIllIIllllI) {
        WContainer llllIllIIlllIl;
        super.move(llllIllIIlllll, llllIllIIllllI);
        for (Cell<?> llllIllIlIIIIl : llllIllIIlllIl.cells) {
            llllIllIlIIIIl.move(llllIllIIlllll, llllIllIIllllI);
        }
    }

    public <T extends WWidget> Cell<T> add(T llllIllIlIlIll) {
        WContainer llllIllIlIllII;
        llllIllIlIlIll.parent = llllIllIlIllII;
        llllIllIlIlIll.theme = llllIllIlIllII.theme;
        Cell<T> llllIllIlIllIl = new Cell<T>(llllIllIlIlIll).centerY();
        llllIllIlIllII.cells.add(llllIllIlIllIl);
        llllIllIlIlIll.init();
        llllIllIlIllII.invalidate();
        return llllIllIlIllIl;
    }

    @Override
    public boolean charTyped(char llllIIllIlIllI) {
        WContainer llllIIllIllIIl;
        try {
            for (Cell<?> llllIIllIllIlI : llllIIllIllIIl.cells) {
                if (!llllIIllIllIIl.propagateEvents((WWidget)llllIIllIllIlI.widget()) || !((WWidget)llllIIllIllIlI.widget()).charTyped(llllIIllIlIllI)) continue;
                return true;
            }
        }
        catch (ConcurrentModificationException llllIIllIlIlIl) {
            // empty catch block
        }
        return super.charTyped(llllIIllIlIllI);
    }

    @Override
    public void calculateSize() {
        WContainer llllIllIIIIIll;
        for (Cell<?> llllIllIIIIlII : llllIllIIIIIll.cells) {
            ((WWidget)llllIllIIIIlII.widget()).calculateSize();
        }
        super.calculateSize();
    }

    protected void renderWidget(WWidget llllIlIlIIIlIl, GuiRenderer llllIlIlIIlIIl, double llllIlIlIIlIII, double llllIlIlIIIIlI, double llllIlIlIIIllI) {
        llllIlIlIIIlIl.render(llllIlIlIIlIIl, llllIlIlIIlIII, llllIlIlIIIIlI, llllIlIlIIIllI);
    }

    @Override
    public boolean keyPressed(int llllIIllllIIll, int llllIIlllIllll) {
        WContainer llllIIllllIlII;
        try {
            for (Cell<?> llllIIllllIlIl : llllIIllllIlII.cells) {
                if (!llllIIllllIlII.propagateEvents((WWidget)llllIIllllIlIl.widget()) || !((WWidget)llllIIllllIlIl.widget()).keyPressed(llllIIllllIIll, llllIIlllIllll)) continue;
                return true;
            }
        }
        catch (ConcurrentModificationException llllIIlllIlllI) {
            // empty catch block
        }
        return llllIIllllIlII.onKeyPressed(llllIIllllIIll, llllIIlllIllll);
    }

    @Override
    protected void onCalculateSize() {
        WContainer llllIlIllllIlI;
        llllIlIllllIlI.width = 0.0;
        llllIlIllllIlI.height = 0.0;
        for (Cell<?> llllIlIlllllII : llllIlIllllIlI.cells) {
            llllIlIllllIlI.width = Math.max(llllIlIllllIlI.width, llllIlIlllllII.padLeft() + ((WWidget)llllIlIlllllII.widget()).width + llllIlIlllllII.padRight());
            llllIlIllllIlI.height = Math.max(llllIlIllllIlI.height, llllIlIlllllII.padTop() + ((WWidget)llllIlIlllllII.widget()).height + llllIlIlllllII.padBottom());
        }
    }

    public void moveCells(double llllIllIIIllll, double llllIllIIIlllI) {
        WContainer llllIllIIIllIl;
        for (Cell<?> llllIllIIlIIIl : llllIllIIIllIl.cells) {
            llllIllIIlIIIl.move(llllIllIIIllll, llllIllIIIlllI);
            class_312 llllIllIIlIIlI = Utils.mc.field_1729;
            ((WWidget)llllIllIIlIIIl.widget()).mouseMoved(llllIllIIlIIlI.method_1603(), llllIllIIlIIlI.method_1604(), llllIllIIlIIlI.method_1603(), llllIllIIlIIlI.method_1604());
        }
    }

    protected boolean propagateEvents(WWidget llllIlIIllllll) {
        return true;
    }

    @Override
    public boolean render(GuiRenderer llllIlIlIlIlll, double llllIlIlIllIll, double llllIlIlIllIlI, double llllIlIlIllIIl) {
        WContainer llllIlIlIllIII;
        if (super.render(llllIlIlIlIlll, llllIlIlIllIll, llllIlIlIllIlI, llllIlIlIllIIl)) {
            return true;
        }
        for (Cell<?> llllIlIlIllllI : llllIlIlIllIII.cells) {
            double llllIlIlIlllll = ((WWidget)llllIlIlIllllI.widget()).y;
            if (llllIlIlIlllll > (double)Utils.getWindowHeight()) break;
            if (!(llllIlIlIlllll + ((WWidget)llllIlIlIllllI.widget()).height > 0.0)) continue;
            llllIlIlIllIII.renderWidget((WWidget)llllIlIlIllllI.widget(), llllIlIlIlIlll, llllIlIlIllIll, llllIlIlIllIlI, llllIlIlIllIIl);
        }
        return false;
    }

    @Override
    protected void onCalculateWidgetPositions() {
        WContainer llllIlIllIlIlI;
        for (Cell<?> llllIlIllIllII : llllIlIllIlIlI.cells) {
            llllIlIllIllII.x = llllIlIllIlIlI.x + llllIlIllIllII.padLeft();
            llllIlIllIllII.y = llllIlIllIlIlI.y + llllIlIllIllII.padTop();
            llllIlIllIllII.width = llllIlIllIlIlI.width - llllIlIllIllII.padLeft() - llllIlIllIllII.padRight();
            llllIlIllIllII.height = llllIlIllIlIlI.height - llllIlIllIllII.padTop() - llllIlIllIllII.padBottom();
            llllIlIllIllII.alignWidget();
        }
    }
}

