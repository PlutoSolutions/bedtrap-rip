/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_310
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 */
package minegame159.meteorclient.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.gui.GuiKeyEvents;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.renderer.GuiDebugRenderer;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.tabs.builtin.HudTab;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WRoot;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.rendering.Matrices;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.CursorStyle;
import minegame159.meteorclient.utils.misc.input.Input;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public abstract class WidgetScreen
extends class_437 {
    private List<Runnable> onClosed;
    private boolean onClose;
    private double lastMouseX;
    private double lastMouseY;
    public boolean locked;
    protected final GuiTheme theme;
    private boolean debug;
    public Runnable taskAfterRender;
    private final WContainer root;
    public double animProgress;
    private boolean closed;
    private static final GuiRenderer RENDERER = new GuiRenderer();
    protected Runnable enterAction;
    private static final GuiDebugRenderer DEBUG_RENDERER = new GuiDebugRenderer();
    protected class_437 parent;

    private static void lambda$keyPressed$0(AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, AtomicReference atomicReference, WWidget wWidget) {
        if (atomicBoolean.get() || !(wWidget instanceof WTextBox)) {
            return;
        }
        WTextBox wTextBox = (WTextBox)wWidget;
        if (atomicBoolean2.get()) {
            wTextBox.setFocused(true);
            wTextBox.setCursorMax();
            atomicBoolean.set(true);
        } else if (wTextBox.isFocused()) {
            wTextBox.setFocused(false);
            atomicBoolean2.set(true);
        }
        if (atomicReference.get() == null) {
            atomicReference.set(wTextBox);
        }
    }

    public boolean method_25400(char c, int n) {
        if (this.locked) {
            return false;
        }
        return this.root.charTyped(c);
    }

    public void method_25410(class_310 class_3102, int n, int n2) {
        super.method_25410(class_3102, n, n2);
        this.root.invalidate();
    }

    public void method_25432() {
        if (!this.closed) {
            this.closed = true;
            this.onClosed();
            Input.setCursorStyle(CursorStyle.Default);
            this.loopWidgets(this.root, WidgetScreen::lambda$removed$1);
            MeteorClient.EVENT_BUS.unsubscribe((Object)this);
            GuiKeyEvents.canUseKeys = true;
            if (this.onClosed != null) {
                for (Runnable runnable : this.onClosed) {
                    runnable.run();
                }
            }
            if (this.onClose) {
                Utils.mc.method_1507(this.parent);
            }
        }
    }

    public boolean method_25404(int n, int n2, int n3) {
        boolean bl;
        if (this.locked) {
            return false;
        }
        boolean bl2 = bl = this.root.keyPressed(n, n3) || super.method_25404(n, n2, n3);
        if (bl) {
            return true;
        }
        if (n == 258) {
            AtomicReference<Object> atomicReference = new AtomicReference<Object>(null);
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
            this.loopWidgets(this.root, arg_0 -> WidgetScreen.lambda$keyPressed$0(atomicBoolean, atomicBoolean2, atomicReference, arg_0));
            if (!atomicBoolean.get() && atomicReference.get() != null) {
                ((WTextBox)atomicReference.get()).setFocused(true);
                ((WTextBox)atomicReference.get()).setCursorMax();
            }
            return true;
        }
        return false;
    }

    public void method_25419() {
        if (!this.locked) {
            boolean bl = this.onClose;
            this.onClose = true;
            if (this.theme.hideHUD() && !(this.parent instanceof WidgetScreen)) {
                Utils.mc.field_1690.field_1842 = false;
            }
            this.method_25432();
            this.onClose = bl;
        }
    }

    protected void method_25426() {
        MeteorClient.EVENT_BUS.subscribe((Object)this);
        if (this.theme.hideHUD()) {
            Utils.mc.field_1690.field_1842 = true;
        }
        this.closed = false;
    }

    public void onClosed(Runnable runnable) {
        if (this.onClosed == null) {
            this.onClosed = new ArrayList<Runnable>(2);
        }
        this.onClosed.add(runnable);
    }

    public boolean method_25421() {
        return false;
    }

    public void method_16014(double d, double d2) {
        if (this.locked) {
            return;
        }
        double d3 = Utils.mc.method_22683().method_4495();
        this.root.mouseMoved(d *= d3, d2 *= d3, this.lastMouseX, this.lastMouseY);
        this.lastMouseX = d;
        this.lastMouseY = d2;
    }

    public void method_25394(class_4587 class_45872, int n, int n2, float f) {
        if (!Utils.canUpdate()) {
            this.method_25420(class_45872);
        }
        double d = Utils.mc.method_22683().method_4495();
        n = (int)((double)n * d);
        n2 = (int)((double)n2 * d);
        this.animProgress += (double)(f / 20.0f * 14.0f);
        this.animProgress = Utils.clamp(this.animProgress, 0.0, 1.0);
        GuiKeyEvents.canUseKeys = true;
        Utils.unscaledProjection();
        Matrices.begin(new class_4587());
        this.onRenderBefore(f);
        WidgetScreen.RENDERER.theme = this.theme;
        this.theme.beforeRender();
        RENDERER.begin();
        RENDERER.setAlpha(this.animProgress);
        this.root.render(RENDERER, n, n2, f / 20.0f);
        RENDERER.setAlpha(1.0);
        RENDERER.end();
        boolean bl = RENDERER.renderTooltip(n, n2, f / 20.0f);
        if (this.debug) {
            DEBUG_RENDERER.render(this.root);
            if (bl) {
                DEBUG_RENDERER.render(WidgetScreen.RENDERER.tooltipWidget);
            }
        }
        Utils.scaledProjection();
        if (this.taskAfterRender != null) {
            this.taskAfterRender.run();
            this.taskAfterRender = null;
        }
    }

    public WidgetScreen(GuiTheme guiTheme, String string) {
        super((class_2561)new class_2585(string));
        this.parent = Utils.mc.field_1755;
        this.root = new WFullScreenRoot(null);
        this.theme = guiTheme;
        this.root.theme = guiTheme;
        if (this.parent != null) {
            this.animProgress = 1.0;
            if (this instanceof TabScreen && this.parent instanceof TabScreen && !(this instanceof HudTab.HudScreen)) {
                this.parent = ((TabScreen)this.parent).parent;
            }
        }
    }

    protected void onRenderBefore(float f) {
    }

    public void invalidate() {
        this.root.invalidate();
    }

    public void clear() {
        this.root.clear();
    }

    public void keyRepeated(int n, int n2) {
        if (this.locked) {
            return;
        }
        this.root.keyRepeated(n, n2);
    }

    protected void onClosed() {
    }

    public boolean method_16803(int n, int n2, int n3) {
        if (this.locked) {
            return false;
        }
        if ((n3 == 2 || n3 == 8) && n == 57) {
            this.debug = !this.debug;
            return true;
        }
        if ((n == 257 || n == 335) && this.enterAction != null) {
            this.enterAction.run();
            return true;
        }
        return super.method_16803(n, n2, n3);
    }

    public boolean method_25406(double d, double d2, int n) {
        if (this.locked) {
            return false;
        }
        double d3 = Utils.mc.method_22683().method_4495();
        return this.root.mouseReleased(d *= d3, d2 *= d3, n);
    }

    private static void lambda$removed$1(WWidget wWidget) {
        WTextBox wTextBox;
        if (wWidget instanceof WTextBox && (wTextBox = (WTextBox)wWidget).isFocused()) {
            wTextBox.setFocused(false);
        }
    }

    public boolean method_25422() {
        return !this.locked;
    }

    private void loopWidgets(WWidget wWidget, Consumer<WWidget> consumer) {
        consumer.accept(wWidget);
        if (wWidget instanceof WContainer) {
            for (Cell<?> cell : ((WContainer)wWidget).cells) {
                this.loopWidgets((WWidget)cell.widget(), consumer);
            }
        }
    }

    public boolean method_25402(double d, double d2, int n) {
        if (this.locked) {
            return false;
        }
        double d3 = Utils.mc.method_22683().method_4495();
        return this.root.mouseClicked(d *= d3, d2 *= d3, n, false);
    }

    public boolean method_25401(double d, double d2, double d3) {
        if (this.locked) {
            return false;
        }
        this.root.mouseScrolled(d3);
        return super.method_25401(d, d2, d3);
    }

    public <W extends WWidget> Cell<W> add(W w) {
        return this.root.add(w);
    }

    private static class WFullScreenRoot
    extends WContainer
    implements WRoot {
        private boolean valid;

        @Override
        protected void onCalculateSize() {
            this.width = Utils.getWindowWidth();
            this.height = Utils.getWindowHeight();
        }

        @Override
        public boolean render(GuiRenderer guiRenderer, double d, double d2, double d3) {
            if (!this.valid) {
                this.calculateSize();
                this.calculateWidgetPositions();
                this.valid = true;
                this.mouseMoved(Utils.mc.field_1729.method_1603(), Utils.mc.field_1729.method_1604(), Utils.mc.field_1729.method_1603(), Utils.mc.field_1729.method_1604());
            }
            return super.render(guiRenderer, d, d2, d3);
        }

        private WFullScreenRoot() {
        }

        @Override
        public void invalidate() {
            this.valid = false;
        }

        WFullScreenRoot(1 var1_1) {
            this();
        }

        @Override
        protected void onCalculateWidgetPositions() {
            for (Cell cell : this.cells) {
                cell.x = 0.0;
                cell.y = 0.0;
                cell.width = this.width;
                cell.height = this.height;
                cell.alignWidget();
            }
        }
    }
}

