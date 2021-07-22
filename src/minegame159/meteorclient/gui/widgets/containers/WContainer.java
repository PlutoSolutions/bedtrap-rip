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
    public final List<Cell<?>> cells = new ArrayList();

    @Override
    public void calculateWidgetPositions() {
        super.calculateWidgetPositions();
        for (Cell<?> cell : this.cells) {
            ((WWidget)cell.widget()).calculateWidgetPositions();
        }
    }

    @Override
    public void mouseScrolled(double d) {
        try {
            for (Cell<?> cell : this.cells) {
                if (!this.propagateEvents((WWidget)cell.widget())) continue;
                ((WWidget)cell.widget()).mouseScrolled(d);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        super.mouseScrolled(d);
    }

    @Override
    public boolean mouseClicked(double d, double d2, int n, boolean bl) {
        try {
            for (Cell<?> cell : this.cells) {
                if (!this.propagateEvents((WWidget)cell.widget()) || !((WWidget)cell.widget()).mouseClicked(d, d2, n, bl)) continue;
                bl = true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        return super.mouseClicked(d, d2, n, bl) || bl;
    }

    @Override
    public void mouseMoved(double d, double d2, double d3, double d4) {
        try {
            for (Cell<?> cell : this.cells) {
                if (!this.propagateEvents((WWidget)cell.widget())) continue;
                ((WWidget)cell.widget()).mouseMoved(d, d2, d3, d4);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        super.mouseMoved(d, d2, d3, d4);
    }

    @Override
    public boolean mouseReleased(double d, double d2, int n) {
        try {
            for (Cell<?> cell : this.cells) {
                if (!this.propagateEvents((WWidget)cell.widget()) || !((WWidget)cell.widget()).mouseReleased(d, d2, n)) continue;
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        return super.mouseReleased(d, d2, n);
    }

    public void clear() {
        if (this.cells.size() > 0) {
            this.cells.clear();
            this.invalidate();
        }
    }

    @Override
    public boolean keyRepeated(int n, int n2) {
        try {
            for (Cell<?> cell : this.cells) {
                if (!this.propagateEvents((WWidget)cell.widget()) || !((WWidget)cell.widget()).keyRepeated(n, n2)) continue;
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        return this.onKeyRepeated(n, n2);
    }

    @Override
    public void move(double d, double d2) {
        super.move(d, d2);
        for (Cell<?> cell : this.cells) {
            cell.move(d, d2);
        }
    }

    public <T extends WWidget> Cell<T> add(T t) {
        t.parent = this;
        t.theme = this.theme;
        Cell<T> cell = new Cell<T>(t).centerY();
        this.cells.add(cell);
        t.init();
        this.invalidate();
        return cell;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean charTyped(char c) {
        try {
            for (Cell<?> cell : this.cells) {
                if (this.propagateEvents((WWidget)cell.widget()) && ((WWidget)cell.widget()).charTyped(c)) {
                    return true;
                }
                "".length();
            }
            return super.charTyped(c);
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        return super.charTyped(c);
    }

    @Override
    public void calculateSize() {
        for (Cell<?> cell : this.cells) {
            ((WWidget)cell.widget()).calculateSize();
        }
        super.calculateSize();
    }

    protected void renderWidget(WWidget wWidget, GuiRenderer guiRenderer, double d, double d2, double d3) {
        wWidget.render(guiRenderer, d, d2, d3);
    }

    @Override
    public boolean keyPressed(int n, int n2) {
        try {
            for (Cell<?> cell : this.cells) {
                if (!this.propagateEvents((WWidget)cell.widget()) || !((WWidget)cell.widget()).keyPressed(n, n2)) continue;
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        return this.onKeyPressed(n, n2);
    }

    @Override
    protected void onCalculateSize() {
        this.width = 0.0;
        this.height = 0.0;
        for (Cell<?> cell : this.cells) {
            this.width = Math.max(this.width, cell.padLeft() + ((WWidget)cell.widget()).width + cell.padRight());
            this.height = Math.max(this.height, cell.padTop() + ((WWidget)cell.widget()).height + cell.padBottom());
        }
    }

    public void moveCells(double d, double d2) {
        for (Cell<?> cell : this.cells) {
            cell.move(d, d2);
            class_312 class_3122 = Utils.mc.field_1729;
            ((WWidget)cell.widget()).mouseMoved(class_3122.method_1603(), class_3122.method_1604(), class_3122.method_1603(), class_3122.method_1604());
        }
    }

    protected boolean propagateEvents(WWidget wWidget) {
        return true;
    }

    @Override
    public boolean render(GuiRenderer guiRenderer, double d, double d2, double d3) {
        if (super.render(guiRenderer, d, d2, d3)) {
            return true;
        }
        for (Cell<?> cell : this.cells) {
            double d4 = ((WWidget)cell.widget()).y;
            if (d4 > (double)Utils.getWindowHeight()) break;
            if (!(d4 + ((WWidget)cell.widget()).height > 0.0)) continue;
            this.renderWidget((WWidget)cell.widget(), guiRenderer, d, d2, d3);
        }
        return false;
    }

    @Override
    protected void onCalculateWidgetPositions() {
        for (Cell<?> cell : this.cells) {
            cell.x = this.x + cell.padLeft();
            cell.y = this.y + cell.padTop();
            cell.width = this.width - cell.padLeft() - cell.padRight();
            cell.height = this.height - cell.padTop() - cell.padBottom();
            cell.alignWidget();
        }
    }
}

