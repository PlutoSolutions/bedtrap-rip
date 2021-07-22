/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.containers;

import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;

public class WVerticalList
extends WContainer {
    public double spacing = 3.0;
    protected double widthRemove;

    @Override
    protected void onCalculateWidgetPositions() {
        double d = this.y;
        for (int i = 0; i < this.cells.size(); ++i) {
            Cell cell = (Cell)this.cells.get(i);
            if (i > 0) {
                d += this.spacing();
            }
            cell.x = this.x + cell.padLeft();
            cell.y = d += cell.padTop();
            cell.width = this.width - this.widthRemove - cell.padLeft() - cell.padRight();
            cell.height = ((WWidget)cell.widget()).height;
            cell.alignWidget();
            d += cell.height + cell.padBottom();
        }
    }

    @Override
    protected void onCalculateSize() {
        this.width = 0.0;
        this.height = 0.0;
        for (int i = 0; i < this.cells.size(); ++i) {
            Cell cell = (Cell)this.cells.get(i);
            if (i > 0) {
                this.height += this.spacing();
            }
            this.width = Math.max(this.width, cell.padLeft() + ((WWidget)cell.widget()).width + cell.padRight());
            this.height += cell.padTop() + ((WWidget)cell.widget()).height + cell.padBottom();
            if (3 != 0) continue;
            return;
        }
    }

    protected double spacing() {
        return this.theme.scale(this.spacing);
    }
}

