/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.input;

import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.Utils;

public abstract class WSlider
extends WWidget {
    protected /* synthetic */ double max;
    public /* synthetic */ Runnable action;
    public /* synthetic */ Runnable actionOnRelease;
    protected /* synthetic */ double valueAtDragStart;
    protected /* synthetic */ double value;
    protected /* synthetic */ double min;
    protected /* synthetic */ boolean handleMouseOver;
    protected /* synthetic */ boolean dragging;

    public void set(double lIlIIlIlIIllIII) {
        WSlider lIlIIlIlIIllIIl;
        lIlIIlIlIIllIIl.value = Utils.clamp(lIlIIlIlIIllIII, lIlIIlIlIIllIIl.min, lIlIIlIlIIllIIl.max);
    }

    public double get() {
        WSlider lIlIIlIlIIlIlII;
        return lIlIIlIlIIlIlII.value;
    }

    protected double handleSize() {
        WSlider lIlIIlIllIlIllI;
        return lIlIIlIllIlIllI.theme.textHeight();
    }

    protected double valueWidth() {
        WSlider lIlIIlIlIIlIIII;
        double lIlIIlIlIIIllll = (lIlIIlIlIIlIIII.value - lIlIIlIlIIlIIII.min) / (lIlIIlIlIIlIIII.max - lIlIIlIlIIlIIII.min);
        return lIlIIlIlIIIllll * (lIlIIlIlIIlIIII.width - lIlIIlIlIIlIIII.handleSize());
    }

    @Override
    protected void onCalculateSize() {
        WSlider lIlIIlIllIlIIlI;
        double lIlIIlIllIlIIIl;
        lIlIIlIllIlIIlI.width = lIlIIlIllIlIIIl = lIlIIlIllIlIIlI.handleSize();
        lIlIIlIllIlIIlI.height = lIlIIlIllIlIIIl;
    }

    @Override
    public boolean onMouseReleased(double lIlIIlIlIIlllll, double lIlIIlIlIIllllI, int lIlIIlIlIIlllIl) {
        WSlider lIlIIlIlIlIIIII;
        if (lIlIIlIlIlIIIII.dragging) {
            if (lIlIIlIlIlIIIII.value != lIlIIlIlIlIIIII.valueAtDragStart && lIlIIlIlIlIIIII.actionOnRelease != null) {
                lIlIIlIlIlIIIII.actionOnRelease.run();
            }
            lIlIIlIlIlIIIII.dragging = false;
            return true;
        }
        return false;
    }

    @Override
    public void onMouseMoved(double lIlIIlIlIllIIll, double lIlIIlIlIlIIlll, double lIlIIlIlIllIIIl, double lIlIIlIlIllIIII) {
        WSlider lIlIIlIlIllIlII;
        double lIlIIlIlIlIllll = lIlIIlIlIllIlII.valueWidth();
        double lIlIIlIlIlIlllI = lIlIIlIlIllIlII.handleSize();
        double lIlIIlIlIlIllIl = lIlIIlIlIlIlllI / 2.0;
        double lIlIIlIlIlIllII = lIlIIlIlIllIlII.x + lIlIIlIlIlIllIl + lIlIIlIlIlIllll - lIlIIlIlIllIlII.height / 2.0;
        lIlIIlIlIllIlII.handleMouseOver = lIlIIlIlIllIIll >= lIlIIlIlIlIllII && lIlIIlIlIllIIll <= lIlIIlIlIlIllII + lIlIIlIlIllIlII.height && lIlIIlIlIlIIlll >= lIlIIlIlIllIlII.y && lIlIIlIlIlIIlll <= lIlIIlIlIllIlII.y + lIlIIlIlIllIlII.height;
        boolean lIlIIlIlIlIlIll = lIlIIlIlIllIIll >= lIlIIlIlIllIlII.x + lIlIIlIlIlIllIl && lIlIIlIlIllIIll <= lIlIIlIlIllIlII.x + lIlIIlIlIlIllIl + lIlIIlIlIllIlII.width - lIlIIlIlIlIlllI;
        boolean bl = lIlIIlIlIllIlII.mouseOver = lIlIIlIlIlIlIll && lIlIIlIlIlIIlll >= lIlIIlIlIllIlII.y && lIlIIlIlIlIIlll <= lIlIIlIlIllIlII.y + lIlIIlIlIllIlII.height;
        if (lIlIIlIlIllIlII.dragging) {
            if (lIlIIlIlIlIlIll) {
                lIlIIlIlIlIllll += lIlIIlIlIllIIll - lIlIIlIlIllIIIl;
                lIlIIlIlIlIllll = Utils.clamp(lIlIIlIlIlIllll, 0.0, lIlIIlIlIllIlII.width - lIlIIlIlIlIlllI);
                lIlIIlIlIllIlII.set(lIlIIlIlIlIllll / (lIlIIlIlIllIlII.width - lIlIIlIlIlIlllI) * (lIlIIlIlIllIlII.max - lIlIIlIlIllIlII.min) + lIlIIlIlIllIlII.min);
                if (lIlIIlIlIllIlII.action != null) {
                    lIlIIlIlIllIlII.action.run();
                }
            } else if (lIlIIlIlIllIlII.value > lIlIIlIlIllIlII.min && lIlIIlIlIllIIll < lIlIIlIlIllIlII.x + lIlIIlIlIlIllIl) {
                lIlIIlIlIllIlII.value = lIlIIlIlIllIlII.min;
                if (lIlIIlIlIllIlII.action != null) {
                    lIlIIlIlIllIlII.action.run();
                }
            } else if (lIlIIlIlIllIlII.value < lIlIIlIlIllIlII.max && lIlIIlIlIllIIll > lIlIIlIlIllIlII.x + lIlIIlIlIlIllIl + lIlIIlIlIllIlII.width - lIlIIlIlIlIlllI) {
                lIlIIlIlIllIlII.value = lIlIIlIlIllIlII.max;
                if (lIlIIlIlIllIlII.action != null) {
                    lIlIIlIlIllIlII.action.run();
                }
            }
        }
    }

    public WSlider(double lIlIIlIllIllllI, double lIlIIlIllIllIIl, double lIlIIlIllIlllII) {
        WSlider lIlIIlIllIllIll;
        lIlIIlIllIllIll.value = Utils.clamp(lIlIIlIllIllllI, lIlIIlIllIllIIl, lIlIIlIllIlllII);
        lIlIIlIllIllIll.min = lIlIIlIllIllIIl;
        lIlIIlIllIllIll.max = lIlIIlIllIlllII;
    }

    @Override
    public boolean onMouseClicked(double lIlIIlIllIIIllI, double lIlIIlIllIIIlIl, int lIlIIlIllIIIlII, boolean lIlIIlIllIIIIll) {
        WSlider lIlIIlIllIIIIlI;
        if (lIlIIlIllIIIIlI.mouseOver && !lIlIIlIllIIIIll) {
            lIlIIlIllIIIIlI.valueAtDragStart = lIlIIlIllIIIIlI.value;
            double lIlIIlIllIIlIIl = lIlIIlIllIIIIlI.handleSize();
            double lIlIIlIllIIlIII = lIlIIlIllIIIllI - (lIlIIlIllIIIIlI.x + lIlIIlIllIIlIIl / 2.0);
            lIlIIlIllIIIIlI.set(lIlIIlIllIIlIII / (lIlIIlIllIIIIlI.width - lIlIIlIllIIlIIl) * (lIlIIlIllIIIIlI.max - lIlIIlIllIIIIlI.min) + lIlIIlIllIIIIlI.min);
            if (lIlIIlIllIIIIlI.action != null) {
                lIlIIlIllIIIIlI.action.run();
            }
            lIlIIlIllIIIIlI.dragging = true;
            return true;
        }
        return false;
    }
}

