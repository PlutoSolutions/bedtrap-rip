/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.containers;

import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;

public class WHorizontalList
extends WContainer {
    public /* synthetic */ double spacing;
    protected /* synthetic */ double calculatedWidth;
    protected /* synthetic */ int fillXCount;

    protected double spacing() {
        WHorizontalList llllllllllllllllIlIlIlIIllIIIlll;
        return llllllllllllllllIlIlIlIIllIIIlll.theme.scale(llllllllllllllllIlIlIlIIllIIIlll.spacing);
    }

    @Override
    protected void onCalculateSize() {
        WHorizontalList llllllllllllllllIlIlIlIIlIllllll;
        llllllllllllllllIlIlIlIIlIllllll.width = 0.0;
        llllllllllllllllIlIlIlIIlIllllll.height = 0.0;
        llllllllllllllllIlIlIlIIlIllllll.fillXCount = 0;
        for (int llllllllllllllllIlIlIlIIllIIIIIl = 0; llllllllllllllllIlIlIlIIllIIIIIl < llllllllllllllllIlIlIlIIlIllllll.cells.size(); ++llllllllllllllllIlIlIlIIllIIIIIl) {
            Cell llllllllllllllllIlIlIlIIllIIIIlI = (Cell)llllllllllllllllIlIlIlIIlIllllll.cells.get(llllllllllllllllIlIlIlIIllIIIIIl);
            if (llllllllllllllllIlIlIlIIllIIIIIl > 0) {
                llllllllllllllllIlIlIlIIlIllllll.width += llllllllllllllllIlIlIlIIlIllllll.spacing();
            }
            llllllllllllllllIlIlIlIIlIllllll.width += llllllllllllllllIlIlIlIIllIIIIlI.padLeft() + ((WWidget)llllllllllllllllIlIlIlIIllIIIIlI.widget()).width + llllllllllllllllIlIlIlIIllIIIIlI.padRight();
            llllllllllllllllIlIlIlIIlIllllll.height = Math.max(llllllllllllllllIlIlIlIIlIllllll.height, llllllllllllllllIlIlIlIIllIIIIlI.padTop() + ((WWidget)llllllllllllllllIlIlIlIIllIIIIlI.widget()).height + llllllllllllllllIlIlIlIIllIIIIlI.padBottom());
            if (!llllllllllllllllIlIlIlIIllIIIIlI.expandCellX) continue;
            ++llllllllllllllllIlIlIlIIlIllllll.fillXCount;
        }
        llllllllllllllllIlIlIlIIlIllllll.calculatedWidth = llllllllllllllllIlIlIlIIlIllllll.width;
    }

    @Override
    protected void onCalculateWidgetPositions() {
        WHorizontalList llllllllllllllllIlIlIlIIlIllIlIl;
        double llllllllllllllllIlIlIlIIlIllIlII = llllllllllllllllIlIlIlIIlIllIlIl.x;
        double llllllllllllllllIlIlIlIIlIllIIll = (llllllllllllllllIlIlIlIIlIllIlIl.width - llllllllllllllllIlIlIlIIlIllIlIl.calculatedWidth) / (double)llllllllllllllllIlIlIlIIlIllIlIl.fillXCount;
        for (int llllllllllllllllIlIlIlIIlIllIllI = 0; llllllllllllllllIlIlIlIIlIllIllI < llllllllllllllllIlIlIlIIlIllIlIl.cells.size(); ++llllllllllllllllIlIlIlIIlIllIllI) {
            Cell llllllllllllllllIlIlIlIIlIllIlll = (Cell)llllllllllllllllIlIlIlIIlIllIlIl.cells.get(llllllllllllllllIlIlIlIIlIllIllI);
            if (llllllllllllllllIlIlIlIIlIllIllI > 0) {
                llllllllllllllllIlIlIlIIlIllIlII += llllllllllllllllIlIlIlIIlIllIlIl.spacing();
            }
            llllllllllllllllIlIlIlIIlIllIlll.x = llllllllllllllllIlIlIlIIlIllIlII += llllllllllllllllIlIlIlIIlIllIlll.padLeft();
            llllllllllllllllIlIlIlIIlIllIlll.y = llllllllllllllllIlIlIlIIlIllIlIl.y + llllllllllllllllIlIlIlIIlIllIlll.padTop();
            llllllllllllllllIlIlIlIIlIllIlll.width = ((WWidget)llllllllllllllllIlIlIlIIlIllIlll.widget()).width;
            llllllllllllllllIlIlIlIIlIllIlll.height = llllllllllllllllIlIlIlIIlIllIlIl.height - llllllllllllllllIlIlIlIIlIllIlll.padTop() - llllllllllllllllIlIlIlIIlIllIlll.padTop();
            if (llllllllllllllllIlIlIlIIlIllIlll.expandCellX) {
                llllllllllllllllIlIlIlIIlIllIlll.width += llllllllllllllllIlIlIlIIlIllIIll;
            }
            llllllllllllllllIlIlIlIIlIllIlll.alignWidget();
            llllllllllllllllIlIlIlIIlIllIlII += llllllllllllllllIlIlIlIIlIllIlll.width + llllllllllllllllIlIlIlIIlIllIlll.padRight();
        }
    }

    public WHorizontalList() {
        WHorizontalList llllllllllllllllIlIlIlIIllIIlIIl;
        llllllllllllllllIlIlIlIIllIIlIIl.spacing = 3.0;
    }
}

