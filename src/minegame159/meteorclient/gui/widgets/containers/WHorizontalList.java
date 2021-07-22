/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.containers;

import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;

public class WHorizontalList
extends WContainer {
    public double spacing = 3.0;
    protected double calculatedWidth;
    protected int fillXCount;

    protected double spacing() {
        return this.theme.scale(this.spacing);
    }

    @Override
    protected void onCalculateSize() {
        this.width = 0.0;
        this.height = 0.0;
        this.fillXCount = 0;
        for (int i = 0; i < this.cells.size(); ++i) {
            Cell cell = (Cell)this.cells.get(i);
            if (i > 0) {
                this.width += this.spacing();
            }
            this.width += cell.padLeft() + ((WWidget)cell.widget()).width + cell.padRight();
            this.height = Math.max(this.height, cell.padTop() + ((WWidget)cell.widget()).height + cell.padBottom());
            if (!cell.expandCellX) continue;
            ++this.fillXCount;
            if (null == null) continue;
            return;
        }
        this.calculatedWidth = this.width;
    }

    @Override
    protected void onCalculateWidgetPositions() {
        double d = this.x;
        double d2 = (this.width - this.calculatedWidth) / (double)this.fillXCount;
        for (int i = 0; i < this.cells.size(); ++i) {
            Cell cell = (Cell)this.cells.get(i);
            if (i > 0) {
                d += this.spacing();
            }
            cell.x = d += cell.padLeft();
            cell.y = this.y + cell.padTop();
            cell.width = ((WWidget)cell.widget()).width;
            cell.height = this.height - cell.padTop() - cell.padTop();
            if (cell.expandCellX) {
                cell.width += d2;
            }
            cell.alignWidget();
            d += cell.width + cell.padRight();
            if (-1 <= 4) continue;
            return;
        }
    }
}

