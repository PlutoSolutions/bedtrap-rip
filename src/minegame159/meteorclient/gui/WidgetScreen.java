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
    private /* synthetic */ List<Runnable> onClosed;
    private /* synthetic */ boolean onClose;
    private /* synthetic */ double lastMouseX;
    private /* synthetic */ double lastMouseY;
    public /* synthetic */ boolean locked;
    protected final /* synthetic */ GuiTheme theme;
    private /* synthetic */ boolean debug;
    public /* synthetic */ Runnable taskAfterRender;
    private final /* synthetic */ WContainer root;
    public /* synthetic */ double animProgress;
    private /* synthetic */ boolean closed;
    private static final /* synthetic */ GuiRenderer RENDERER;
    protected /* synthetic */ Runnable enterAction;
    private static final /* synthetic */ GuiDebugRenderer DEBUG_RENDERER;
    protected /* synthetic */ class_437 parent;

    static {
        RENDERER = new GuiRenderer();
        DEBUG_RENDERER = new GuiDebugRenderer();
    }

    public boolean method_25400(char lllllllllllllllllllIlIIlIIIIIlll, int lllllllllllllllllllIlIIlIIIIIllI) {
        WidgetScreen lllllllllllllllllllIlIIlIIIIlIII;
        if (lllllllllllllllllllIlIIlIIIIlIII.locked) {
            return false;
        }
        return lllllllllllllllllllIlIIlIIIIlIII.root.charTyped(lllllllllllllllllllIlIIlIIIIIlll);
    }

    public void method_25410(class_310 lllllllllllllllllllIlIIIlllIIlll, int lllllllllllllllllllIlIIIlllIIllI, int lllllllllllllllllllIlIIIlllIIIIl) {
        WidgetScreen lllllllllllllllllllIlIIIlllIIlII;
        super.method_25410(lllllllllllllllllllIlIIIlllIIlll, lllllllllllllllllllIlIIIlllIIllI, lllllllllllllllllllIlIIIlllIIIIl);
        lllllllllllllllllllIlIIIlllIIlII.root.invalidate();
    }

    public void method_25432() {
        WidgetScreen lllllllllllllllllllIlIIIllIlIllI;
        if (!lllllllllllllllllllIlIIIllIlIllI.closed) {
            lllllllllllllllllllIlIIIllIlIllI.closed = true;
            lllllllllllllllllllIlIIIllIlIllI.onClosed();
            Input.setCursorStyle(CursorStyle.Default);
            lllllllllllllllllllIlIIIllIlIllI.loopWidgets(lllllllllllllllllllIlIIIllIlIllI.root, lllllllllllllllllllIlIIIlIlllIll -> {
                WTextBox lllllllllllllllllllIlIIIlIllllIl;
                if (lllllllllllllllllllIlIIIlIlllIll instanceof WTextBox && (lllllllllllllllllllIlIIIlIllllIl = (WTextBox)lllllllllllllllllllIlIIIlIlllIll).isFocused()) {
                    lllllllllllllllllllIlIIIlIllllIl.setFocused(false);
                }
            });
            MeteorClient.EVENT_BUS.unsubscribe((Object)lllllllllllllllllllIlIIIllIlIllI);
            GuiKeyEvents.canUseKeys = true;
            if (lllllllllllllllllllIlIIIllIlIllI.onClosed != null) {
                for (Runnable lllllllllllllllllllIlIIIllIlIlll : lllllllllllllllllllIlIIIllIlIllI.onClosed) {
                    lllllllllllllllllllIlIIIllIlIlll.run();
                }
            }
            if (lllllllllllllllllllIlIIIllIlIllI.onClose) {
                Utils.mc.method_1507(lllllllllllllllllllIlIIIllIlIllI.parent);
            }
        }
    }

    public boolean method_25404(int lllllllllllllllllllIlIIlIIIlllll, int lllllllllllllllllllIlIIlIIIllllI, int lllllllllllllllllllIlIIlIIIllIII) {
        boolean lllllllllllllllllllIlIIlIIIlllII;
        WidgetScreen lllllllllllllllllllIlIIlIIlIIIII;
        if (lllllllllllllllllllIlIIlIIlIIIII.locked) {
            return false;
        }
        boolean bl = lllllllllllllllllllIlIIlIIIlllII = lllllllllllllllllllIlIIlIIlIIIII.root.keyPressed(lllllllllllllllllllIlIIlIIIlllll, lllllllllllllllllllIlIIlIIIllIII) || super.method_25404(lllllllllllllllllllIlIIlIIIlllll, lllllllllllllllllllIlIIlIIIllllI, lllllllllllllllllllIlIIlIIIllIII);
        if (lllllllllllllllllllIlIIlIIIlllII) {
            return true;
        }
        if (lllllllllllllllllllIlIIlIIIlllll == 258) {
            AtomicReference<Object> lllllllllllllllllllIlIIlIIlIIIll = new AtomicReference<Object>(null);
            AtomicBoolean lllllllllllllllllllIlIIlIIlIIIlI = new AtomicBoolean(false);
            AtomicBoolean lllllllllllllllllllIlIIlIIlIIIIl = new AtomicBoolean(false);
            lllllllllllllllllllIlIIlIIlIIIII.loopWidgets(lllllllllllllllllllIlIIlIIlIIIII.root, lllllllllllllllllllIlIIIlIlIllII -> {
                if (lllllllllllllllllllIlIIlIIlIIIlI.get() || !(lllllllllllllllllllIlIIIlIlIllII instanceof WTextBox)) {
                    return;
                }
                WTextBox lllllllllllllllllllIlIIIlIllIIII = (WTextBox)lllllllllllllllllllIlIIIlIlIllII;
                if (lllllllllllllllllllIlIIlIIlIIIIl.get()) {
                    lllllllllllllllllllIlIIIlIllIIII.setFocused(true);
                    lllllllllllllllllllIlIIIlIllIIII.setCursorMax();
                    lllllllllllllllllllIlIIlIIlIIIlI.set(true);
                } else if (lllllllllllllllllllIlIIIlIllIIII.isFocused()) {
                    lllllllllllllllllllIlIIIlIllIIII.setFocused(false);
                    lllllllllllllllllllIlIIlIIlIIIIl.set(true);
                }
                if (lllllllllllllllllllIlIIlIIlIIIll.get() == null) {
                    lllllllllllllllllllIlIIlIIlIIIll.set(lllllllllllllllllllIlIIIlIllIIII);
                }
            });
            if (!lllllllllllllllllllIlIIlIIlIIIlI.get() && lllllllllllllllllllIlIIlIIlIIIll.get() != null) {
                ((WTextBox)lllllllllllllllllllIlIIlIIlIIIll.get()).setFocused(true);
                ((WTextBox)lllllllllllllllllllIlIIlIIlIIIll.get()).setCursorMax();
            }
            return true;
        }
        return false;
    }

    public void method_25419() {
        WidgetScreen lllllllllllllllllllIlIIIllIlllII;
        if (!lllllllllllllllllllIlIIIllIlllII.locked) {
            boolean lllllllllllllllllllIlIIIllIllllI = lllllllllllllllllllIlIIIllIlllII.onClose;
            lllllllllllllllllllIlIIIllIlllII.onClose = true;
            if (lllllllllllllllllllIlIIIllIlllII.theme.hideHUD() && !(lllllllllllllllllllIlIIIllIlllII.parent instanceof WidgetScreen)) {
                Utils.mc.field_1690.field_1842 = false;
            }
            lllllllllllllllllllIlIIIllIlllII.method_25432();
            lllllllllllllllllllIlIIIllIlllII.onClose = lllllllllllllllllllIlIIIllIllllI;
        }
    }

    protected void method_25426() {
        WidgetScreen lllllllllllllllllllIlIIlIlllIlII;
        MeteorClient.EVENT_BUS.subscribe((Object)lllllllllllllllllllIlIIlIlllIlII);
        if (lllllllllllllllllllIlIIlIlllIlII.theme.hideHUD()) {
            Utils.mc.field_1690.field_1842 = true;
        }
        lllllllllllllllllllIlIIlIlllIlII.closed = false;
    }

    public void onClosed(Runnable lllllllllllllllllllIlIIlIlllIIII) {
        WidgetScreen lllllllllllllllllllIlIIlIllIllll;
        if (lllllllllllllllllllIlIIlIllIllll.onClosed == null) {
            lllllllllllllllllllIlIIlIllIllll.onClosed = new ArrayList<Runnable>(2);
        }
        lllllllllllllllllllIlIIlIllIllll.onClosed.add(lllllllllllllllllllIlIIlIlllIIII);
    }

    public boolean method_25421() {
        return false;
    }

    public void method_16014(double lllllllllllllllllllIlIIlIlIIlIlI, double lllllllllllllllllllIlIIlIlIIlIIl) {
        WidgetScreen lllllllllllllllllllIlIIlIlIIIlll;
        if (lllllllllllllllllllIlIIlIlIIIlll.locked) {
            return;
        }
        double lllllllllllllllllllIlIIlIlIIlIII = Utils.mc.method_22683().method_4495();
        lllllllllllllllllllIlIIlIlIIIlll.root.mouseMoved(lllllllllllllllllllIlIIlIlIIlIlI *= lllllllllllllllllllIlIIlIlIIlIII, lllllllllllllllllllIlIIlIlIIlIIl *= lllllllllllllllllllIlIIlIlIIlIII, lllllllllllllllllllIlIIlIlIIIlll.lastMouseX, lllllllllllllllllllIlIIlIlIIIlll.lastMouseY);
        lllllllllllllllllllIlIIlIlIIIlll.lastMouseX = lllllllllllllllllllIlIIlIlIIlIlI;
        lllllllllllllllllllIlIIlIlIIIlll.lastMouseY = lllllllllllllllllllIlIIlIlIIlIIl;
    }

    public void method_25394(class_4587 lllllllllllllllllllIlIIIllllIlII, int lllllllllllllllllllIlIIIlllllIlI, int lllllllllllllllllllIlIIIllllIIlI, float lllllllllllllllllllIlIIIllllIIIl) {
        WidgetScreen lllllllllllllllllllIlIIIllllllII;
        if (!Utils.canUpdate()) {
            lllllllllllllllllllIlIIIllllllII.method_25420(lllllllllllllllllllIlIIIllllIlII);
        }
        double lllllllllllllllllllIlIIIllllIlll = Utils.mc.method_22683().method_4495();
        lllllllllllllllllllIlIIIlllllIlI = (int)((double)lllllllllllllllllllIlIIIlllllIlI * lllllllllllllllllllIlIIIllllIlll);
        lllllllllllllllllllIlIIIllllIIlI = (int)((double)lllllllllllllllllllIlIIIllllIIlI * lllllllllllllllllllIlIIIllllIlll);
        lllllllllllllllllllIlIIIllllllII.animProgress += (double)(lllllllllllllllllllIlIIIllllIIIl / 20.0f * 14.0f);
        lllllllllllllllllllIlIIIllllllII.animProgress = Utils.clamp(lllllllllllllllllllIlIIIllllllII.animProgress, 0.0, 1.0);
        GuiKeyEvents.canUseKeys = true;
        Utils.unscaledProjection();
        Matrices.begin(new class_4587());
        lllllllllllllllllllIlIIIllllllII.onRenderBefore(lllllllllllllllllllIlIIIllllIIIl);
        WidgetScreen.RENDERER.theme = lllllllllllllllllllIlIIIllllllII.theme;
        lllllllllllllllllllIlIIIllllllII.theme.beforeRender();
        RENDERER.begin();
        RENDERER.setAlpha(lllllllllllllllllllIlIIIllllllII.animProgress);
        lllllllllllllllllllIlIIIllllllII.root.render(RENDERER, lllllllllllllllllllIlIIIlllllIlI, lllllllllllllllllllIlIIIllllIIlI, lllllllllllllllllllIlIIIllllIIIl / 20.0f);
        RENDERER.setAlpha(1.0);
        RENDERER.end();
        boolean lllllllllllllllllllIlIIIllllIllI = RENDERER.renderTooltip(lllllllllllllllllllIlIIIlllllIlI, lllllllllllllllllllIlIIIllllIIlI, lllllllllllllllllllIlIIIllllIIIl / 20.0f);
        if (lllllllllllllllllllIlIIIllllllII.debug) {
            DEBUG_RENDERER.render(lllllllllllllllllllIlIIIllllllII.root);
            if (lllllllllllllllllllIlIIIllllIllI) {
                DEBUG_RENDERER.render(WidgetScreen.RENDERER.tooltipWidget);
            }
        }
        Utils.scaledProjection();
        if (lllllllllllllllllllIlIIIllllllII.taskAfterRender != null) {
            lllllllllllllllllllIlIIIllllllII.taskAfterRender.run();
            lllllllllllllllllllIlIIIllllllII.taskAfterRender = null;
        }
    }

    public WidgetScreen(GuiTheme lllllllllllllllllllIlIIllIIIIlll, String lllllllllllllllllllIlIIllIIIIllI) {
        super((class_2561)new class_2585(lllllllllllllllllllIlIIllIIIIllI));
        WidgetScreen lllllllllllllllllllIlIIllIIIlIII;
        lllllllllllllllllllIlIIllIIIlIII.parent = Utils.mc.field_1755;
        lllllllllllllllllllIlIIllIIIlIII.root = new WFullScreenRoot();
        lllllllllllllllllllIlIIllIIIlIII.theme = lllllllllllllllllllIlIIllIIIIlll;
        lllllllllllllllllllIlIIllIIIlIII.root.theme = lllllllllllllllllllIlIIllIIIIlll;
        if (lllllllllllllllllllIlIIllIIIlIII.parent != null) {
            lllllllllllllllllllIlIIllIIIlIII.animProgress = 1.0;
            if (lllllllllllllllllllIlIIllIIIlIII instanceof TabScreen && lllllllllllllllllllIlIIllIIIlIII.parent instanceof TabScreen && !(lllllllllllllllllllIlIIllIIIlIII instanceof HudTab.HudScreen)) {
                lllllllllllllllllllIlIIllIIIlIII.parent = ((TabScreen)lllllllllllllllllllIlIIllIIIlIII.parent).parent;
            }
        }
    }

    protected void onRenderBefore(float lllllllllllllllllllIlIIIlllIllIl) {
    }

    public void invalidate() {
        WidgetScreen lllllllllllllllllllIlIIlIlllIlll;
        lllllllllllllllllllIlIIlIlllIlll.root.invalidate();
    }

    public void clear() {
        WidgetScreen lllllllllllllllllllIlIIlIllllIll;
        lllllllllllllllllllIlIIlIllllIll.root.clear();
    }

    public void keyRepeated(int lllllllllllllllllllIlIIlIIIIllII, int lllllllllllllllllllIlIIlIIIIlllI) {
        WidgetScreen lllllllllllllllllllIlIIlIIIIllIl;
        if (lllllllllllllllllllIlIIlIIIIllIl.locked) {
            return;
        }
        lllllllllllllllllllIlIIlIIIIllIl.root.keyRepeated(lllllllllllllllllllIlIIlIIIIllII, lllllllllllllllllllIlIIlIIIIlllI);
    }

    protected void onClosed() {
    }

    public boolean method_16803(int lllllllllllllllllllIlIIlIIlIlllI, int lllllllllllllllllllIlIIlIIllIIIl, int lllllllllllllllllllIlIIlIIlIllII) {
        WidgetScreen lllllllllllllllllllIlIIlIIllIIll;
        if (lllllllllllllllllllIlIIlIIllIIll.locked) {
            return false;
        }
        if ((lllllllllllllllllllIlIIlIIlIllII == 2 || lllllllllllllllllllIlIIlIIlIllII == 8) && lllllllllllllllllllIlIIlIIlIlllI == 57) {
            lllllllllllllllllllIlIIlIIllIIll.debug = !lllllllllllllllllllIlIIlIIllIIll.debug;
            return true;
        }
        if ((lllllllllllllllllllIlIIlIIlIlllI == 257 || lllllllllllllllllllIlIIlIIlIlllI == 335) && lllllllllllllllllllIlIIlIIllIIll.enterAction != null) {
            lllllllllllllllllllIlIIlIIllIIll.enterAction.run();
            return true;
        }
        return super.method_16803(lllllllllllllllllllIlIIlIIlIlllI, lllllllllllllllllllIlIIlIIllIIIl, lllllllllllllllllllIlIIlIIlIllII);
    }

    public boolean method_25406(double lllllllllllllllllllIlIIlIlIllIII, double lllllllllllllllllllIlIIlIlIlIIlI, int lllllllllllllllllllIlIIlIlIlIIIl) {
        WidgetScreen lllllllllllllllllllIlIIlIlIlIlII;
        if (lllllllllllllllllllIlIIlIlIlIlII.locked) {
            return false;
        }
        double lllllllllllllllllllIlIIlIlIlIlIl = Utils.mc.method_22683().method_4495();
        return lllllllllllllllllllIlIIlIlIlIlII.root.mouseReleased(lllllllllllllllllllIlIIlIlIllIII *= lllllllllllllllllllIlIIlIlIlIlIl, lllllllllllllllllllIlIIlIlIlIIlI *= lllllllllllllllllllIlIIlIlIlIlIl, lllllllllllllllllllIlIIlIlIlIIIl);
    }

    public boolean method_25422() {
        WidgetScreen lllllllllllllllllllIlIIIllIIIIlI;
        return !lllllllllllllllllllIlIIIllIIIIlI.locked;
    }

    private void loopWidgets(WWidget lllllllllllllllllllIlIIIllIIlIII, Consumer<WWidget> lllllllllllllllllllIlIIIllIIIlll) {
        lllllllllllllllllllIlIIIllIIIlll.accept(lllllllllllllllllllIlIIIllIIlIII);
        if (lllllllllllllllllllIlIIIllIIlIII instanceof WContainer) {
            for (Cell<?> lllllllllllllllllllIlIIIllIIllIl : ((WContainer)lllllllllllllllllllIlIIIllIIlIII).cells) {
                WidgetScreen lllllllllllllllllllIlIIIllIIllII;
                lllllllllllllllllllIlIIIllIIllII.loopWidgets((WWidget)lllllllllllllllllllIlIIIllIIllIl.widget(), lllllllllllllllllllIlIIIllIIIlll);
            }
        }
    }

    public boolean method_25402(double lllllllllllllllllllIlIIlIllIIlll, double lllllllllllllllllllIlIIlIllIIIIl, int lllllllllllllllllllIlIIlIllIIIII) {
        WidgetScreen lllllllllllllllllllIlIIlIllIIIll;
        if (lllllllllllllllllllIlIIlIllIIIll.locked) {
            return false;
        }
        double lllllllllllllllllllIlIIlIllIIlII = Utils.mc.method_22683().method_4495();
        return lllllllllllllllllllIlIIlIllIIIll.root.mouseClicked(lllllllllllllllllllIlIIlIllIIlll *= lllllllllllllllllllIlIIlIllIIlII, lllllllllllllllllllIlIIlIllIIIIl *= lllllllllllllllllllIlIIlIllIIlII, lllllllllllllllllllIlIIlIllIIIII, false);
    }

    public boolean method_25401(double lllllllllllllllllllIlIIlIIlllIlI, double lllllllllllllllllllIlIIlIIllllIl, double lllllllllllllllllllIlIIlIIllllII) {
        WidgetScreen lllllllllllllllllllIlIIlIIllllll;
        if (lllllllllllllllllllIlIIlIIllllll.locked) {
            return false;
        }
        lllllllllllllllllllIlIIlIIllllll.root.mouseScrolled(lllllllllllllllllllIlIIlIIllllII);
        return super.method_25401(lllllllllllllllllllIlIIlIIlllIlI, lllllllllllllllllllIlIIlIIllllIl, lllllllllllllllllllIlIIlIIllllII);
    }

    public <W extends WWidget> Cell<W> add(W lllllllllllllllllllIlIIlIlllllll) {
        WidgetScreen lllllllllllllllllllIlIIllIIIIIII;
        return lllllllllllllllllllIlIIllIIIIIII.root.add(lllllllllllllllllllIlIIlIlllllll);
    }

    private static class WFullScreenRoot
    extends WContainer
    implements WRoot {
        private /* synthetic */ boolean valid;

        @Override
        protected void onCalculateSize() {
            lllllllllllllllllIllIllIlllIIIIl.width = Utils.getWindowWidth();
            lllllllllllllllllIllIllIlllIIIIl.height = Utils.getWindowHeight();
        }

        @Override
        public boolean render(GuiRenderer lllllllllllllllllIllIllIllIlIIlI, double lllllllllllllllllIllIllIllIlIIIl, double lllllllllllllllllIllIllIllIlIIII, double lllllllllllllllllIllIllIllIIllll) {
            WFullScreenRoot lllllllllllllllllIllIllIllIlIIll;
            if (!lllllllllllllllllIllIllIllIlIIll.valid) {
                lllllllllllllllllIllIllIllIlIIll.calculateSize();
                lllllllllllllllllIllIllIllIlIIll.calculateWidgetPositions();
                lllllllllllllllllIllIllIllIlIIll.valid = true;
                lllllllllllllllllIllIllIllIlIIll.mouseMoved(Utils.mc.field_1729.method_1603(), Utils.mc.field_1729.method_1604(), Utils.mc.field_1729.method_1603(), Utils.mc.field_1729.method_1604());
            }
            return super.render(lllllllllllllllllIllIllIllIlIIlI, lllllllllllllllllIllIllIllIlIIIl, lllllllllllllllllIllIllIllIlIIII, lllllllllllllllllIllIllIllIIllll);
        }

        private WFullScreenRoot() {
            WFullScreenRoot lllllllllllllllllIllIllIlllIlIII;
        }

        @Override
        public void invalidate() {
            lllllllllllllllllIllIllIlllIIlIl.valid = false;
        }

        @Override
        protected void onCalculateWidgetPositions() {
            WFullScreenRoot lllllllllllllllllIllIllIllIlllII;
            for (Cell lllllllllllllllllIllIllIllIlllIl : lllllllllllllllllIllIllIllIlllII.cells) {
                lllllllllllllllllIllIllIllIlllIl.x = 0.0;
                lllllllllllllllllIllIllIllIlllIl.y = 0.0;
                lllllllllllllllllIllIllIllIlllIl.width = lllllllllllllllllIllIllIllIlllII.width;
                lllllllllllllllllIllIllIllIlllIl.height = lllllllllllllllllIllIllIllIlllII.height;
                lllllllllllllllllIllIllIllIlllIl.alignWidget();
            }
        }
    }
}

