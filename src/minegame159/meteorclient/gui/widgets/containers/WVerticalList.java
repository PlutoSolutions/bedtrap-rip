/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.containers;

import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;

public class WVerticalList
extends WContainer {
    public /* synthetic */ double spacing;
    protected /* synthetic */ double widthRemove;

    @Override
    protected void onCalculateWidgetPositions() {
        WVerticalList lllllllllllllllllIlIIllIIlllIlIl;
        double lllllllllllllllllIlIIllIIlllIlII = lllllllllllllllllIlIIllIIlllIlIl.y;
        for (int lllllllllllllllllIlIIllIIlllIllI = 0; lllllllllllllllllIlIIllIIlllIllI < lllllllllllllllllIlIIllIIlllIlIl.cells.size(); ++lllllllllllllllllIlIIllIIlllIllI) {
            Cell lllllllllllllllllIlIIllIIlllIlll = (Cell)lllllllllllllllllIlIIllIIlllIlIl.cells.get(lllllllllllllllllIlIIllIIlllIllI);
            if (lllllllllllllllllIlIIllIIlllIllI > 0) {
                lllllllllllllllllIlIIllIIlllIlII += lllllllllllllllllIlIIllIIlllIlIl.spacing();
            }
            lllllllllllllllllIlIIllIIlllIlll.x = lllllllllllllllllIlIIllIIlllIlIl.x + lllllllllllllllllIlIIllIIlllIlll.padLeft();
            lllllllllllllllllIlIIllIIlllIlll.y = lllllllllllllllllIlIIllIIlllIlII += lllllllllllllllllIlIIllIIlllIlll.padTop();
            lllllllllllllllllIlIIllIIlllIlll.width = lllllllllllllllllIlIIllIIlllIlIl.width - lllllllllllllllllIlIIllIIlllIlIl.widthRemove - lllllllllllllllllIlIIllIIlllIlll.padLeft() - lllllllllllllllllIlIIllIIlllIlll.padRight();
            lllllllllllllllllIlIIllIIlllIlll.height = ((WWidget)lllllllllllllllllIlIIllIIlllIlll.widget()).height;
            lllllllllllllllllIlIIllIIlllIlll.alignWidget();
            lllllllllllllllllIlIIllIIlllIlII += lllllllllllllllllIlIIllIIlllIlll.height + lllllllllllllllllIlIIllIIlllIlll.padBottom();
        }
    }

    public WVerticalList() {
        WVerticalList lllllllllllllllllIlIIllIlIIIlIIl;
        lllllllllllllllllIlIIllIlIIIlIIl.spacing = 3.0;
    }

    @Override
    protected void onCalculateSize() {
        WVerticalList lllllllllllllllllIlIIllIIlllllll;
        lllllllllllllllllIlIIllIIlllllll.width = 0.0;
        lllllllllllllllllIlIIllIIlllllll.height = 0.0;
        for (int lllllllllllllllllIlIIllIlIIIIIII = 0; lllllllllllllllllIlIIllIlIIIIIII < lllllllllllllllllIlIIllIIlllllll.cells.size(); ++lllllllllllllllllIlIIllIlIIIIIII) {
            Cell lllllllllllllllllIlIIllIlIIIIIIl = (Cell)lllllllllllllllllIlIIllIIlllllll.cells.get(lllllllllllllllllIlIIllIlIIIIIII);
            if (lllllllllllllllllIlIIllIlIIIIIII > 0) {
                lllllllllllllllllIlIIllIIlllllll.height += lllllllllllllllllIlIIllIIlllllll.spacing();
            }
            lllllllllllllllllIlIIllIIlllllll.width = Math.max(lllllllllllllllllIlIIllIIlllllll.width, lllllllllllllllllIlIIllIlIIIIIIl.padLeft() + ((WWidget)lllllllllllllllllIlIIllIlIIIIIIl.widget()).width + lllllllllllllllllIlIIllIlIIIIIIl.padRight());
            lllllllllllllllllIlIIllIIlllllll.height += lllllllllllllllllIlIIllIlIIIIIIl.padTop() + ((WWidget)lllllllllllllllllIlIIllIlIIIIIIl.widget()).height + lllllllllllllllllIlIIllIlIIIIIIl.padBottom();
        }
    }

    protected double spacing() {
        WVerticalList lllllllllllllllllIlIIllIlIIIIlIl;
        return lllllllllllllllllIlIIllIlIIIIlIl.theme.scale(lllllllllllllllllIlIIllIlIIIIlIl.spacing);
    }
}

