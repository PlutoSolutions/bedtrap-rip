/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.utils;

import minegame159.meteorclient.gui.utils.AlignmentX;
import minegame159.meteorclient.gui.utils.AlignmentY;
import minegame159.meteorclient.gui.widgets.WWidget;

public class Cell<T extends WWidget> {
    private /* synthetic */ double padBottom;
    private /* synthetic */ double padTop;
    public /* synthetic */ double y;
    private /* synthetic */ boolean expandWidgetY;
    public /* synthetic */ boolean expandCellX;
    public /* synthetic */ double width;
    private /* synthetic */ double padRight;
    public /* synthetic */ double height;
    private /* synthetic */ double padLeft;
    private /* synthetic */ boolean expandWidgetX;
    public /* synthetic */ double x;
    private final /* synthetic */ T widget;
    private /* synthetic */ AlignmentX alignX;
    private /* synthetic */ AlignmentY alignY;

    public double padLeft() {
        Cell lIIIIIIIlllIIll;
        return lIIIIIIIlllIIll.s(lIIIIIIIlllIIll.padLeft);
    }

    public Cell(T lIIIIIIllIIllII) {
        Cell lIIIIIIllIIllll;
        lIIIIIIllIIllll.alignX = AlignmentX.Left;
        lIIIIIIllIIllll.alignY = AlignmentY.Top;
        lIIIIIIllIIllll.widget = lIIIIIIllIIllII;
    }

    public Cell<T> padBottom(double lIIIIIIlIIlIllI) {
        Cell lIIIIIIlIIllIIl;
        lIIIIIIlIIllIIl.padBottom = lIIIIIIlIIlIllI;
        return lIIIIIIlIIllIIl;
    }

    public double padBottom() {
        Cell lIIIIIIIlllIlIl;
        return lIIIIIIIlllIlIl.s(lIIIIIIIlllIlIl.padBottom);
    }

    public Cell<T> minWidth(double lIIIIIIlIllllII) {
        Cell lIIIIIIlIllllIl;
        ((WWidget)lIIIIIIlIllllIl.widget).minWidth = lIIIIIIlIllllII;
        return lIIIIIIlIllllIl;
    }

    public T widget() {
        Cell lIIIIIIllIIlIIl;
        return lIIIIIIllIIlIIl.widget;
    }

    public double padRight() {
        Cell lIIIIIIIllllIIl;
        return lIIIIIIIllllIIl.s(lIIIIIIIllllIIl.padRight);
    }

    public double padTop() {
        Cell lIIIIIIIllllIll;
        return lIIIIIIIllllIll.s(lIIIIIIIllllIll.padTop);
    }

    public Cell<T> padLeft(double lIIIIIIlIIlIIII) {
        Cell lIIIIIIlIIlIIll;
        lIIIIIIlIIlIIll.padLeft = lIIIIIIlIIlIIII;
        return lIIIIIIlIIlIIll;
    }

    public Cell<T> padRight(double lIIIIIIlIIlllII) {
        Cell lIIIIIIlIIlllll;
        lIIIIIIlIIlllll.padRight = lIIIIIIlIIlllII;
        return lIIIIIIlIIlllll;
    }

    public Cell<T> padTop(double lIIIIIIlIlIIIlI) {
        Cell lIIIIIIlIlIIlIl;
        lIIIIIIlIlIIlIl.padTop = lIIIIIIlIlIIIlI;
        return lIIIIIIlIlIIlIl;
    }

    public Cell<T> center() {
        Cell lIIIIIIlIlIllII;
        lIIIIIIlIlIllII.alignX = AlignmentX.Center;
        lIIIIIIlIlIllII.alignY = AlignmentY.Center;
        return lIIIIIIlIlIllII;
    }

    public Cell<T> top() {
        Cell lIIIIIIlIlIlIIl;
        lIIIIIIlIlIlIIl.alignY = AlignmentY.Top;
        return lIIIIIIlIlIlIIl;
    }

    private double s(double lIIIIIIIlIlllIl) {
        Cell lIIIIIIIllIIIII;
        return ((WWidget)lIIIIIIIllIIIII.widget).theme.scale(lIIIIIIIlIlllIl);
    }

    public Cell<T> centerX() {
        Cell lIIIIIIlIllIlll;
        lIIIIIIlIllIlll.alignX = AlignmentX.Center;
        return lIIIIIIlIllIlll;
    }

    public Cell<T> expandWidgetY() {
        Cell lIIIIIIIllIllII;
        lIIIIIIIllIllII.expandWidgetY = true;
        return lIIIIIIIllIllII;
    }

    public void move(double lIIIIIIllIIIlII, double lIIIIIIllIIIIII) {
        Cell lIIIIIIllIIIIlI;
        lIIIIIIllIIIIlI.x += lIIIIIIllIIIlII;
        lIIIIIIllIIIIlI.y += lIIIIIIllIIIIII;
        ((WWidget)lIIIIIIllIIIIlI.widget).move(lIIIIIIllIIIlII, lIIIIIIllIIIIII);
    }

    public Cell<T> bottom() {
        Cell lIIIIIIlIlIlllI;
        lIIIIIIlIlIlllI.alignY = AlignmentY.Bottom;
        return lIIIIIIlIlIlllI;
    }

    public Cell<T> padVertical(double lIIIIIIlIIIIllI) {
        Cell lIIIIIIlIIIIlIl;
        lIIIIIIlIIIIlIl.padTop = lIIIIIIlIIIIlIl.padBottom = lIIIIIIlIIIIllI;
        return lIIIIIIlIIIIlIl;
    }

    public Cell<T> centerY() {
        Cell lIIIIIIlIllIIlI;
        lIIIIIIlIllIIlI.alignY = AlignmentY.Center;
        return lIIIIIIlIllIIlI;
    }

    public Cell<T> right() {
        Cell lIIIIIIlIllIlII;
        lIIIIIIlIllIlII.alignX = AlignmentX.Right;
        return lIIIIIIlIllIlII;
    }

    public Cell<T> expandX() {
        Cell lIIIIIIIllIIlll;
        lIIIIIIIllIIlll.expandWidgetX = true;
        lIIIIIIIllIIlll.expandCellX = true;
        return lIIIIIIIllIIlll;
    }

    public Cell<T> pad(double lIIIIIIIllllllI) {
        Cell lIIIIIIIlllllll;
        lIIIIIIIlllllll.padBottom = lIIIIIIIlllllll.padLeft = lIIIIIIIllllllI;
        lIIIIIIIlllllll.padRight = lIIIIIIIlllllll.padLeft;
        lIIIIIIIlllllll.padTop = lIIIIIIIlllllll.padLeft;
        return lIIIIIIIlllllll;
    }

    public Cell<T> padHorizontal(double lIIIIIIlIIIlIlI) {
        Cell lIIIIIIlIIIllIl;
        lIIIIIIlIIIllIl.padRight = lIIIIIIlIIIllIl.padLeft = lIIIIIIlIIIlIlI;
        return lIIIIIIlIIIllIl;
    }

    public void alignWidget() {
        Cell lIIIIIIIllIIIll;
        if (lIIIIIIIllIIIll.expandWidgetX) {
            ((WWidget)lIIIIIIIllIIIll.widget).x = lIIIIIIIllIIIll.x;
            ((WWidget)lIIIIIIIllIIIll.widget).width = lIIIIIIIllIIIll.width;
        } else {
            switch (lIIIIIIIllIIIll.alignX) {
                case Left: {
                    ((WWidget)lIIIIIIIllIIIll.widget).x = lIIIIIIIllIIIll.x;
                    break;
                }
                case Center: {
                    ((WWidget)lIIIIIIIllIIIll.widget).x = lIIIIIIIllIIIll.x + lIIIIIIIllIIIll.width / 2.0 - ((WWidget)lIIIIIIIllIIIll.widget).width / 2.0;
                    break;
                }
                case Right: {
                    ((WWidget)lIIIIIIIllIIIll.widget).x = lIIIIIIIllIIIll.x + lIIIIIIIllIIIll.width - ((WWidget)lIIIIIIIllIIIll.widget).width;
                }
            }
        }
        if (lIIIIIIIllIIIll.expandWidgetY) {
            ((WWidget)lIIIIIIIllIIIll.widget).y = lIIIIIIIllIIIll.y;
            ((WWidget)lIIIIIIIllIIIll.widget).height = lIIIIIIIllIIIll.height;
        } else {
            switch (lIIIIIIIllIIIll.alignY) {
                case Top: {
                    ((WWidget)lIIIIIIIllIIIll.widget).y = lIIIIIIIllIIIll.y;
                    break;
                }
                case Center: {
                    ((WWidget)lIIIIIIIllIIIll.widget).y = lIIIIIIIllIIIll.y + lIIIIIIIllIIIll.height / 2.0 - ((WWidget)lIIIIIIIllIIIll.widget).height / 2.0;
                    break;
                }
                case Bottom: {
                    ((WWidget)lIIIIIIIllIIIll.widget).y = lIIIIIIIllIIIll.y + lIIIIIIIllIIIll.height - ((WWidget)lIIIIIIIllIIIll.widget).height;
                }
            }
        }
    }

    public Cell<T> expandWidgetX() {
        Cell lIIIIIIIllIllll;
        lIIIIIIIllIllll.expandWidgetX = true;
        return lIIIIIIIllIllll;
    }

    public Cell<T> expandCellX() {
        Cell lIIIIIIIllIlIlI;
        lIIIIIIIllIlIlI.expandCellX = true;
        return lIIIIIIIllIlIlI;
    }
}

