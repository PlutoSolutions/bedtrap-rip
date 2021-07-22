/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.input;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WRoot;
import minegame159.meteorclient.gui.widgets.containers.WVerticalList;
import minegame159.meteorclient.gui.widgets.pressable.WPressable;
import minegame159.meteorclient.utils.Utils;

public abstract class WDropdown<T>
extends WPressable {
    protected /* synthetic */ boolean expanded;
    protected /* synthetic */ double maxValueWidth;
    protected /* synthetic */ double animProgress;
    public /* synthetic */ Runnable action;
    protected /* synthetic */ T[] values;
    protected /* synthetic */ T value;
    protected /* synthetic */ WDropdownRoot root;

    @Override
    public boolean render(GuiRenderer lllllllllllllllllIIllIllIIIIIlll, double lllllllllllllllllIIllIllIIIIIllI, double lllllllllllllllllIIllIllIIIIIlIl, double lllllllllllllllllIIllIlIlllllllI) {
        WDropdown lllllllllllllllllIIllIllIIIIIIlI;
        boolean lllllllllllllllllIIllIllIIIIIIll = super.render(lllllllllllllllllIIllIllIIIIIlll, lllllllllllllllllIIllIllIIIIIllI, lllllllllllllllllIIllIllIIIIIlIl, lllllllllllllllllIIllIlIlllllllI);
        lllllllllllllllllIIllIllIIIIIIlI.animProgress += (double)(lllllllllllllllllIIllIllIIIIIIlI.expanded ? 1 : -1) * lllllllllllllllllIIllIlIlllllllI * 14.0;
        lllllllllllllllllIIllIllIIIIIIlI.animProgress = Utils.clamp(lllllllllllllllllIIllIllIIIIIIlI.animProgress, 0.0, 1.0);
        if (!lllllllllllllllllIIllIllIIIIIIll && lllllllllllllllllIIllIllIIIIIIlI.animProgress > 0.0) {
            lllllllllllllllllIIllIllIIIIIlll.absolutePost(() -> {
                WDropdown lllllllllllllllllIIllIlIlIlIlIlI;
                lllllllllllllllllIIllIllIIIIIlll.scissorStart(lllllllllllllllllIIllIlIlIlIlIlI.x, lllllllllllllllllIIllIlIlIlIlIlI.y + lllllllllllllllllIIllIlIlIlIlIlI.height, lllllllllllllllllIIllIlIlIlIlIlI.width, lllllllllllllllllIIllIlIlIlIlIlI.root.height * lllllllllllllllllIIllIlIlIlIlIlI.animProgress);
                lllllllllllllllllIIllIlIlIlIlIlI.root.render(lllllllllllllllllIIllIllIIIIIlll, lllllllllllllllllIIllIllIIIIIllI, lllllllllllllllllIIllIllIIIIIlIl, lllllllllllllllllIIllIlIlllllllI);
                lllllllllllllllllIIllIllIIIIIlll.scissorEnd();
            });
        }
        if (lllllllllllllllllIIllIllIIIIIIlI.expanded && lllllllllllllllllIIllIllIIIIIIlI.root.mouseOver) {
            lllllllllllllllllIIllIllIIIIIIlI.theme.disableHoverColor = true;
        }
        return lllllllllllllllllIIllIllIIIIIIll;
    }

    @Override
    public boolean onMouseReleased(double lllllllllllllllllIIllIlIlllIIlII, double lllllllllllllllllIIllIlIlllIIIll, int lllllllllllllllllIIllIlIlllIIIlI) {
        WDropdown lllllllllllllllllIIllIlIlllIlIIl;
        if (super.onMouseReleased(lllllllllllllllllIIllIlIlllIIlII, lllllllllllllllllIIllIlIlllIIIll, lllllllllllllllllIIllIlIlllIIIlI)) {
            return true;
        }
        return lllllllllllllllllIIllIlIlllIlIIl.expanded && lllllllllllllllllIIllIlIlllIlIIl.root.mouseReleased(lllllllllllllllllIIllIlIlllIIlII, lllllllllllllllllIIllIlIlllIIIll, lllllllllllllllllIIllIlIlllIIIlI);
    }

    @Override
    public boolean onCharTyped(char lllllllllllllllllIIllIlIlIllIlll) {
        WDropdown lllllllllllllllllIIllIlIlIllIllI;
        if (super.onCharTyped(lllllllllllllllllIIllIlIlIllIlll)) {
            return true;
        }
        return lllllllllllllllllIIllIlIlIllIllI.expanded && lllllllllllllllllIIllIlIlIllIllI.root.charTyped(lllllllllllllllllIIllIlIlIllIlll);
    }

    protected abstract WDropdownRoot createRootWidget();

    @Override
    public boolean onKeyRepeated(int lllllllllllllllllIIllIlIlIllllll, int lllllllllllllllllIIllIlIlIlllIll) {
        WDropdown lllllllllllllllllIIllIlIllIIIIII;
        if (super.onKeyRepeated(lllllllllllllllllIIllIlIlIllllll, lllllllllllllllllIIllIlIlIlllIll)) {
            return true;
        }
        return lllllllllllllllllIIllIlIllIIIIII.expanded && lllllllllllllllllIIllIlIllIIIIII.root.keyRepeated(lllllllllllllllllIIllIlIlIllllll, lllllllllllllllllIIllIlIlIlllIll);
    }

    public WDropdown(T[] lllllllllllllllllIIllIllIlIIIlll, T lllllllllllllllllIIllIllIlIIlIIl) {
        WDropdown lllllllllllllllllIIllIllIlIIlIII;
        lllllllllllllllllIIllIllIlIIlIII.values = lllllllllllllllllIIllIllIlIIIlll;
        lllllllllllllllllIIllIllIlIIlIII.set(lllllllllllllllllIIllIllIlIIlIIl);
    }

    @Override
    public void move(double lllllllllllllllllIIllIllIIIlIIII, double lllllllllllllllllIIllIllIIIlIIlI) {
        WDropdown lllllllllllllllllIIllIllIIIlIIIl;
        super.move(lllllllllllllllllIIllIllIIIlIIII, lllllllllllllllllIIllIllIIIlIIlI);
        lllllllllllllllllIIllIllIIIlIIIl.root.move(lllllllllllllllllIIllIllIIIlIIII, lllllllllllllllllIIllIllIIIlIIlI);
    }

    @Override
    public boolean onMouseClicked(double lllllllllllllllllIIllIlIllllIllI, double lllllllllllllllllIIllIlIllllIIII, int lllllllllllllllllIIllIlIlllIllll, boolean lllllllllllllllllIIllIlIlllIlllI) {
        WDropdown lllllllllllllllllIIllIlIllllIIlI;
        if (!lllllllllllllllllIIllIlIllllIIlI.mouseOver && !lllllllllllllllllIIllIlIllllIIlI.root.mouseOver) {
            lllllllllllllllllIIllIlIllllIIlI.expanded = false;
        }
        if (super.onMouseClicked(lllllllllllllllllIIllIlIllllIllI, lllllllllllllllllIIllIlIllllIIII, lllllllllllllllllIIllIlIlllIllll, lllllllllllllllllIIllIlIlllIlllI)) {
            lllllllllllllllllIIllIlIlllIlllI = true;
        }
        if (lllllllllllllllllIIllIlIllllIIlI.expanded && lllllllllllllllllIIllIlIllllIIlI.root.mouseClicked(lllllllllllllllllIIllIlIllllIllI, lllllllllllllllllIIllIlIllllIIII, lllllllllllllllllIIllIlIlllIllll, lllllllllllllllllIIllIlIlllIlllI)) {
            lllllllllllllllllIIllIlIlllIlllI = true;
        }
        return lllllllllllllllllIIllIlIlllIlllI;
    }

    @Override
    public boolean onKeyPressed(int lllllllllllllllllIIllIlIllIIIlIl, int lllllllllllllllllIIllIlIllIIIlll) {
        WDropdown lllllllllllllllllIIllIlIllIIlIIl;
        if (super.onKeyPressed(lllllllllllllllllIIllIlIllIIIlIl, lllllllllllllllllIIllIlIllIIIlll)) {
            return true;
        }
        return lllllllllllllllllIIllIlIllIIlIIl.expanded && lllllllllllllllllIIllIlIllIIlIIl.root.keyPressed(lllllllllllllllllIIllIlIllIIIlIl, lllllllllllllllllIIllIlIllIIIlll);
    }

    public void set(T lllllllllllllllllIIllIllIIIllIII) {
        lllllllllllllllllIIllIllIIIllIll.value = lllllllllllllllllIIllIllIIIllIII;
    }

    @Override
    protected void onCalculateWidgetPositions() {
        WDropdown lllllllllllllllllIIllIllIIlIIllI;
        super.onCalculateWidgetPositions();
        lllllllllllllllllIIllIllIIlIIllI.root.x = lllllllllllllllllIIllIllIIlIIllI.x;
        lllllllllllllllllIIllIllIIlIIllI.root.y = lllllllllllllllllIIllIllIIlIIllI.y + lllllllllllllllllIIllIllIIlIIllI.height;
        lllllllllllllllllIIllIllIIlIIllI.root.calculateWidgetPositions();
    }

    @Override
    protected void onPressed(int lllllllllllllllllIIllIllIIlIIIlI) {
        WDropdown lllllllllllllllllIIllIllIIlIIIIl;
        lllllllllllllllllIIllIllIIlIIIIl.expanded = !lllllllllllllllllIIllIllIIlIIIIl.expanded;
    }

    @Override
    public void init() {
        WDropdown lllllllllllllllllIIllIllIIlllllI;
        lllllllllllllllllIIllIllIIlllllI.root = lllllllllllllllllIIllIllIIlllllI.createRootWidget();
        lllllllllllllllllIIllIllIIlllllI.root.theme = lllllllllllllllllIIllIllIIlllllI.theme;
        lllllllllllllllllIIllIllIIlllllI.root.spacing = 0.0;
        for (int lllllllllllllllllIIllIllIIllllll = 0; lllllllllllllllllIIllIllIIllllll < lllllllllllllllllIIllIllIIlllllI.values.length; ++lllllllllllllllllIIllIllIIllllll) {
            WDropdownValue lllllllllllllllllIIllIllIlIIIIIl = lllllllllllllllllIIllIllIIlllllI.createValueWidget();
            lllllllllllllllllIIllIllIlIIIIIl.theme = lllllllllllllllllIIllIllIIlllllI.theme;
            lllllllllllllllllIIllIllIlIIIIIl.value = lllllllllllllllllIIllIllIIlllllI.values[lllllllllllllllllIIllIllIIllllll];
            Cell<WDropdownValue> lllllllllllllllllIIllIllIlIIIIII = lllllllllllllllllIIllIllIIlllllI.root.add(lllllllllllllllllIIllIllIlIIIIIl).padHorizontal(2.0).expandWidgetX();
            if (lllllllllllllllllIIllIllIIllllll < lllllllllllllllllIIllIllIIlllllI.values.length - 1) continue;
            lllllllllllllllllIIllIllIlIIIIII.padBottom(2.0);
        }
    }

    @Override
    protected void onCalculateSize() {
        WDropdown lllllllllllllllllIIllIllIIllIIII;
        double lllllllllllllllllIIllIllIIlIllll = lllllllllllllllllIIllIllIIllIIII.pad();
        lllllllllllllllllIIllIllIIllIIII.maxValueWidth = 0.0;
        for (T lllllllllllllllllIIllIllIIllIIIl : lllllllllllllllllIIllIllIIllIIII.values) {
            double lllllllllllllllllIIllIllIIllIIlI = lllllllllllllllllIIllIllIIllIIII.theme.textWidth(lllllllllllllllllIIllIllIIllIIIl.toString());
            lllllllllllllllllIIllIllIIllIIII.maxValueWidth = Math.max(lllllllllllllllllIIllIllIIllIIII.maxValueWidth, lllllllllllllllllIIllIllIIllIIlI);
        }
        lllllllllllllllllIIllIllIIllIIII.root.calculateSize();
        lllllllllllllllllIIllIllIIllIIII.width = lllllllllllllllllIIllIllIIlIllll + lllllllllllllllllIIllIllIIllIIII.maxValueWidth + lllllllllllllllllIIllIllIIlIllll + lllllllllllllllllIIllIllIIllIIII.theme.textHeight() + lllllllllllllllllIIllIllIIlIllll;
        lllllllllllllllllIIllIllIIllIIII.height = lllllllllllllllllIIllIllIIlIllll + lllllllllllllllllIIllIllIIllIIII.theme.textHeight() + lllllllllllllllllIIllIllIIlIllll;
        lllllllllllllllllIIllIllIIllIIII.root.width = lllllllllllllllllIIllIllIIllIIII.width;
    }

    public T get() {
        WDropdown lllllllllllllllllIIllIllIIIlllll;
        return lllllllllllllllllIIllIllIIIlllll.value;
    }

    @Override
    public void onMouseScrolled(double lllllllllllllllllIIllIlIllIIllll) {
        WDropdown lllllllllllllllllIIllIlIllIIlllI;
        super.onMouseScrolled(lllllllllllllllllIIllIlIllIIllll);
        if (lllllllllllllllllIIllIlIllIIlllI.expanded) {
            lllllllllllllllllIIllIlIllIIlllI.root.mouseScrolled(lllllllllllllllllIIllIlIllIIllll);
        }
    }

    @Override
    public void onMouseMoved(double lllllllllllllllllIIllIlIllIlIllI, double lllllllllllllllllIIllIlIllIlIlIl, double lllllllllllllllllIIllIlIllIllIIl, double lllllllllllllllllIIllIlIllIlIIll) {
        WDropdown lllllllllllllllllIIllIlIllIlllII;
        super.onMouseMoved(lllllllllllllllllIIllIlIllIlIllI, lllllllllllllllllIIllIlIllIlIlIl, lllllllllllllllllIIllIlIllIllIIl, lllllllllllllllllIIllIlIllIlIIll);
        if (lllllllllllllllllIIllIlIllIlllII.expanded) {
            lllllllllllllllllIIllIlIllIlllII.root.mouseMoved(lllllllllllllllllIIllIlIllIlIllI, lllllllllllllllllIIllIlIllIlIlIl, lllllllllllllllllIIllIlIllIllIIl, lllllllllllllllllIIllIlIllIlIIll);
        }
    }

    protected abstract WDropdownValue createValueWidget();

    protected static abstract class WDropdownRoot
    extends WVerticalList
    implements WRoot {
        @Override
        public void invalidate() {
        }

        protected WDropdownRoot() {
            WDropdownRoot lIIlIllIIIlllI;
        }
    }

    protected abstract class WDropdownValue
    extends WPressable {
        protected /* synthetic */ T value;

        protected WDropdownValue() {
            WDropdownValue llllllllllllllllllllIlllIlIllllI;
        }

        @Override
        protected void onPressed(int llllllllllllllllllllIlllIlIlIlll) {
            WDropdownValue llllllllllllllllllllIlllIlIllIII;
            boolean llllllllllllllllllllIlllIlIlIllI = !llllllllllllllllllllIlllIlIllIII.WDropdown.this.value.equals(llllllllllllllllllllIlllIlIllIII.value);
            llllllllllllllllllllIlllIlIllIII.WDropdown.this.value = llllllllllllllllllllIlllIlIllIII.value;
            llllllllllllllllllllIlllIlIllIII.WDropdown.this.expanded = false;
            if (llllllllllllllllllllIlllIlIlIllI && llllllllllllllllllllIlllIlIllIII.WDropdown.this.action != null) {
                llllllllllllllllllllIlllIlIllIII.WDropdown.this.action.run();
            }
        }
    }
}

