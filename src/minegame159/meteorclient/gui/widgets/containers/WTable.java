/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.DoubleArrayList
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntList
 */
package minegame159.meteorclient.gui.widgets.containers;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;

public class WTable
extends WContainer {
    private final /* synthetic */ List<List<Cell<?>>> rows;
    private final /* synthetic */ DoubleList rowHeights;
    public /* synthetic */ double spacing;
    private final /* synthetic */ DoubleList rowWidths;
    private /* synthetic */ int rowI;
    private final /* synthetic */ IntList rowExpandCellXCounts;
    private final /* synthetic */ DoubleList columnWidths;

    @Override
    protected void onCalculateSize() {
        WTable lllllllllllllllllIIlIIIlIllllIlI;
        lllllllllllllllllIIlIIIlIllllIlI.calculateInfo();
        lllllllllllllllllIIlIIIlIllllIlI.rowWidths.clear();
        lllllllllllllllllIIlIIIlIllllIlI.width = 0.0;
        lllllllllllllllllIIlIIIlIllllIlI.height = 0.0;
        for (int lllllllllllllllllIIlIIIlIllllIll = 0; lllllllllllllllllIIlIIIlIllllIll < lllllllllllllllllIIlIIIlIllllIlI.rows.size(); ++lllllllllllllllllIIlIIIlIllllIll) {
            List<Cell<?>> lllllllllllllllllIIlIIIlIlllllIl = lllllllllllllllllIIlIIIlIllllIlI.rows.get(lllllllllllllllllIIlIIIlIllllIll);
            double lllllllllllllllllIIlIIIlIlllllII = 0.0;
            for (int lllllllllllllllllIIlIIIlIllllllI = 0; lllllllllllllllllIIlIIIlIllllllI < lllllllllllllllllIIlIIIlIlllllIl.size(); ++lllllllllllllllllIIlIIIlIllllllI) {
                if (lllllllllllllllllIIlIIIlIllllllI > 0) {
                    lllllllllllllllllIIlIIIlIlllllII += lllllllllllllllllIIlIIIlIllllIlI.spacing();
                }
                lllllllllllllllllIIlIIIlIlllllII += lllllllllllllllllIIlIIIlIllllIlI.columnWidths.getDouble(lllllllllllllllllIIlIIIlIllllllI);
            }
            lllllllllllllllllIIlIIIlIllllIlI.rowWidths.add(lllllllllllllllllIIlIIIlIlllllII);
            lllllllllllllllllIIlIIIlIllllIlI.width = Math.max(lllllllllllllllllIIlIIIlIllllIlI.width, lllllllllllllllllIIlIIIlIlllllII);
            if (lllllllllllllllllIIlIIIlIllllIll > 0) {
                lllllllllllllllllIIlIIIlIllllIlI.height += lllllllllllllllllIIlIIIlIllllIlI.spacing();
            }
            lllllllllllllllllIIlIIIlIllllIlI.height += lllllllllllllllllIIlIIIlIllllIlI.rowHeights.getDouble(lllllllllllllllllIIlIIIlIllllIll);
        }
    }

    public int rowI() {
        WTable lllllllllllllllllIIlIIIllIIllIII;
        return lllllllllllllllllIIlIIIllIIllIII.rowI;
    }

    public WTable() {
        WTable lllllllllllllllllIIlIIIllIlIlIll;
        lllllllllllllllllIIlIIIllIlIlIll.spacing = 3.0;
        lllllllllllllllllIIlIIIllIlIlIll.rows = new ArrayList();
        lllllllllllllllllIIlIIIllIlIlIll.rowHeights = new DoubleArrayList();
        lllllllllllllllllIIlIIIllIlIlIll.columnWidths = new DoubleArrayList();
        lllllllllllllllllIIlIIIllIlIlIll.rowWidths = new DoubleArrayList();
        lllllllllllllllllIIlIIIllIlIlIll.rowExpandCellXCounts = new IntArrayList();
    }

    @Override
    public <T extends WWidget> Cell<T> add(T lllllllllllllllllIIlIIIllIlIIIll) {
        WTable lllllllllllllllllIIlIIIllIlIIIIl;
        Cell<T> lllllllllllllllllIIlIIIllIlIIIlI = super.add(lllllllllllllllllIIlIIIllIlIIIll);
        if (lllllllllllllllllIIlIIIllIlIIIIl.rows.size() <= lllllllllllllllllIIlIIIllIlIIIIl.rowI) {
            ArrayList<Cell<T>> lllllllllllllllllIIlIIIllIlIIlIl = new ArrayList<Cell<T>>();
            lllllllllllllllllIIlIIIllIlIIlIl.add(lllllllllllllllllIIlIIIllIlIIIlI);
            lllllllllllllllllIIlIIIllIlIIIIl.rows.add(lllllllllllllllllIIlIIIllIlIIlIl);
        } else {
            lllllllllllllllllIIlIIIllIlIIIIl.rows.get(lllllllllllllllllIIlIIIllIlIIIIl.rowI).add(lllllllllllllllllIIlIIIllIlIIIlI);
        }
        return lllllllllllllllllIIlIIIllIlIIIlI;
    }

    public void removeRow(int lllllllllllllllllIIlIIIllIIIllll) {
        WTable lllllllllllllllllIIlIIIllIIIlllI;
        block0: for (Cell<?> lllllllllllllllllIIlIIIllIIlIIIl : lllllllllllllllllIIlIIIllIIIlllI.rows.remove(lllllllllllllllllIIlIIIllIIIllll)) {
            Iterator lllllllllllllllllIIlIIIllIIlIIlI = lllllllllllllllllIIlIIIllIIIlllI.cells.iterator();
            while (lllllllllllllllllIIlIIIllIIlIIlI.hasNext()) {
                if (lllllllllllllllllIIlIIIllIIlIIlI.next() != lllllllllllllllllIIlIIIllIIlIIIl) continue;
                lllllllllllllllllIIlIIIllIIlIIlI.remove();
                continue block0;
            }
        }
        --lllllllllllllllllIIlIIIllIIIlllI.rowI;
    }

    protected double spacing() {
        WTable lllllllllllllllllIIlIIIllIIIIlII;
        return lllllllllllllllllIIlIIIllIIIIlII.theme.scale(lllllllllllllllllIIlIIIllIIIIlII.spacing);
    }

    @Override
    public void clear() {
        WTable lllllllllllllllllIIlIIIllIIIIlll;
        super.clear();
        lllllllllllllllllIIlIIIllIIIIlll.rows.clear();
        lllllllllllllllllIIlIIIllIIIIlll.rowI = 0;
    }

    public void row() {
        WTable lllllllllllllllllIIlIIIllIIlllII;
        ++lllllllllllllllllIIlIIIllIIlllII.rowI;
    }

    private void calculateInfo() {
        WTable lllllllllllllllllIIlIIIlIlIIlIII;
        lllllllllllllllllIIlIIIlIlIIlIII.rowHeights.clear();
        lllllllllllllllllIIlIIIlIlIIlIII.columnWidths.clear();
        lllllllllllllllllIIlIIIlIlIIlIII.rowExpandCellXCounts.clear();
        for (List<Cell<?>> lllllllllllllllllIIlIIIlIlIIlIIl : lllllllllllllllllIIlIIIlIlIIlIII.rows) {
            double lllllllllllllllllIIlIIIlIlIIlIll = 0.0;
            int lllllllllllllllllIIlIIIlIlIIlIlI = 0;
            for (int lllllllllllllllllIIlIIIlIlIIllII = 0; lllllllllllllllllIIlIIIlIlIIllII < lllllllllllllllllIIlIIIlIlIIlIIl.size(); ++lllllllllllllllllIIlIIIlIlIIllII) {
                Cell<?> lllllllllllllllllIIlIIIlIlIIlllI = lllllllllllllllllIIlIIIlIlIIlIIl.get(lllllllllllllllllIIlIIIlIlIIllII);
                lllllllllllllllllIIlIIIlIlIIlIll = Math.max(lllllllllllllllllIIlIIIlIlIIlIll, lllllllllllllllllIIlIIIlIlIIlllI.padTop() + ((WWidget)lllllllllllllllllIIlIIIlIlIIlllI.widget()).height + lllllllllllllllllIIlIIIlIlIIlllI.padBottom());
                double lllllllllllllllllIIlIIIlIlIIllIl = lllllllllllllllllIIlIIIlIlIIlllI.padLeft() + ((WWidget)lllllllllllllllllIIlIIIlIlIIlllI.widget()).width + lllllllllllllllllIIlIIIlIlIIlllI.padRight();
                if (lllllllllllllllllIIlIIIlIlIIlIII.columnWidths.size() <= lllllllllllllllllIIlIIIlIlIIllII) {
                    lllllllllllllllllIIlIIIlIlIIlIII.columnWidths.add(lllllllllllllllllIIlIIIlIlIIllIl);
                } else {
                    lllllllllllllllllIIlIIIlIlIIlIII.columnWidths.set(lllllllllllllllllIIlIIIlIlIIllII, Math.max(lllllllllllllllllIIlIIIlIlIIlIII.columnWidths.getDouble(lllllllllllllllllIIlIIIlIlIIllII), lllllllllllllllllIIlIIIlIlIIllIl));
                }
                if (!lllllllllllllllllIIlIIIlIlIIlllI.expandCellX) continue;
                ++lllllllllllllllllIIlIIIlIlIIlIlI;
            }
            lllllllllllllllllIIlIIIlIlIIlIII.rowHeights.add(lllllllllllllllllIIlIIIlIlIIlIll);
            lllllllllllllllllIIlIIIlIlIIlIII.rowExpandCellXCounts.add(lllllllllllllllllIIlIIIlIlIIlIlI);
        }
    }

    @Override
    protected void onCalculateWidgetPositions() {
        WTable lllllllllllllllllIIlIIIlIllIIIlI;
        double lllllllllllllllllIIlIIIlIllIIIIl = lllllllllllllllllIIlIIIlIllIIIlI.y;
        for (int lllllllllllllllllIIlIIIlIllIIIll = 0; lllllllllllllllllIIlIIIlIllIIIll < lllllllllllllllllIIlIIIlIllIIIlI.rows.size(); ++lllllllllllllllllIIlIIIlIllIIIll) {
            List<Cell<?>> lllllllllllllllllIIlIIIlIllIIlll = lllllllllllllllllIIlIIIlIllIIIlI.rows.get(lllllllllllllllllIIlIIIlIllIIIll);
            if (lllllllllllllllllIIlIIIlIllIIIll > 0) {
                lllllllllllllllllIIlIIIlIllIIIIl += lllllllllllllllllIIlIIIlIllIIIlI.spacing();
            }
            double lllllllllllllllllIIlIIIlIllIIllI = lllllllllllllllllIIlIIIlIllIIIlI.x;
            double lllllllllllllllllIIlIIIlIllIIlIl = lllllllllllllllllIIlIIIlIllIIIlI.rowHeights.getDouble(lllllllllllllllllIIlIIIlIllIIIll);
            double lllllllllllllllllIIlIIIlIllIIlII = lllllllllllllllllIIlIIIlIllIIIlI.rowExpandCellXCounts.getInt(lllllllllllllllllIIlIIIlIllIIIll) > 0 ? (lllllllllllllllllIIlIIIlIllIIIlI.width - lllllllllllllllllIIlIIIlIllIIIlI.rowWidths.getDouble(lllllllllllllllllIIlIIIlIllIIIll)) / (double)lllllllllllllllllIIlIIIlIllIIIlI.rowExpandCellXCounts.getInt(lllllllllllllllllIIlIIIlIllIIIll) : 0.0;
            for (int lllllllllllllllllIIlIIIlIllIlIII = 0; lllllllllllllllllIIlIIIlIllIlIII < lllllllllllllllllIIlIIIlIllIIlll.size(); ++lllllllllllllllllIIlIIIlIllIlIII) {
                Cell<?> lllllllllllllllllIIlIIIlIllIlIlI = lllllllllllllllllIIlIIIlIllIIlll.get(lllllllllllllllllIIlIIIlIllIlIII);
                if (lllllllllllllllllIIlIIIlIllIlIII > 0) {
                    lllllllllllllllllIIlIIIlIllIIllI += lllllllllllllllllIIlIIIlIllIIIlI.spacing();
                }
                double lllllllllllllllllIIlIIIlIllIlIIl = lllllllllllllllllIIlIIIlIllIIIlI.columnWidths.getDouble(lllllllllllllllllIIlIIIlIllIlIII);
                lllllllllllllllllIIlIIIlIllIlIlI.x = lllllllllllllllllIIlIIIlIllIIllI;
                lllllllllllllllllIIlIIIlIllIlIlI.y = lllllllllllllllllIIlIIIlIllIIIIl;
                lllllllllllllllllIIlIIIlIllIlIlI.width = lllllllllllllllllIIlIIIlIllIlIIl + (lllllllllllllllllIIlIIIlIllIlIlI.expandCellX ? lllllllllllllllllIIlIIIlIllIIlII : 0.0);
                lllllllllllllllllIIlIIIlIllIlIlI.height = lllllllllllllllllIIlIIIlIllIIlIl;
                lllllllllllllllllIIlIIIlIllIlIlI.alignWidget();
                lllllllllllllllllIIlIIIlIllIIllI += lllllllllllllllllIIlIIIlIllIlIIl + (lllllllllllllllllIIlIIIlIllIlIlI.expandCellX ? lllllllllllllllllIIlIIIlIllIIlII : 0.0);
            }
            lllllllllllllllllIIlIIIlIllIIIIl += lllllllllllllllllIIlIIIlIllIIlIl;
        }
    }
}

