/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_290
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 */
package minegame159.meteorclient.gui.tabs.builtin;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.events.render.Render2DEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.screens.HudElementScreen;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.tabs.WindowTabScreen;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_290;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class HudTab
extends Tab {
    public static /* synthetic */ HudTab INSTANCE;

    @Override
    public boolean isScreen(class_437 llllllllllllllllllIIIllIIIIIIIIl) {
        return llllllllllllllllllIIIllIIIIIIIIl instanceof HudScreen;
    }

    public HudTab() {
        super("HUD");
        HudTab llllllllllllllllllIIIllIIIIlIIIl;
        INSTANCE = llllllllllllllllllIIIllIIIIlIIIl;
    }

    @Override
    public TabScreen createScreen(GuiTheme llllllllllllllllllIIIllIIIIIIlII) {
        HudTab llllllllllllllllllIIIllIIIIIIlIl;
        return new HudScreen(llllllllllllllllllIIIllIIIIIIlII, llllllllllllllllllIIIllIIIIIIlIl);
    }

    @Override
    public void openScreen(GuiTheme llllllllllllllllllIIIllIIIIIlIlI) {
        HudTab llllllllllllllllllIIIllIIIIIllIl;
        Utils.mc.method_1507((class_437)llllllllllllllllllIIIllIIIIIllIl.createScreen(llllllllllllllllllIIIllIIIIIlIlI));
    }

    public static class HudScreen
    extends WindowTabScreen {
        private /* synthetic */ double lastMouseY;
        private final /* synthetic */ Color INACTIVE_OL_COLOR;
        private /* synthetic */ double mouseStartX;
        private final /* synthetic */ Color HOVER_OL_COLOR;
        private /* synthetic */ boolean selecting;
        private final /* synthetic */ List<HudElement> selectedElements;
        private /* synthetic */ boolean dragging;
        private final /* synthetic */ Color INACTIVE_BG_COLOR;
        private /* synthetic */ double mouseStartY;
        private /* synthetic */ double lastMouseX;
        private /* synthetic */ boolean dragged;
        private /* synthetic */ HudElement hoveredModule;
        private final /* synthetic */ HUD hud;
        private final /* synthetic */ Color HOVER_BG_COLOR;

        @Override
        public void method_16014(double lIIIIIlIllIlIII, double lIIIIIlIllIIIll) {
            HudScreen lIIIIIlIllIlIIl;
            double lIIIIIlIllIIllI = Utils.mc.method_22683().method_4495();
            lIIIIIlIllIlIII *= lIIIIIlIllIIllI;
            lIIIIIlIllIIIll *= lIIIIIlIllIIllI;
            if (lIIIIIlIllIlIIl.selecting) {
                lIIIIIlIllIlIIl.selectedElements.clear();
                for (HudElement lIIIIIllIIIIIlI : lIIIIIlIllIlIIl.hud.elements) {
                    double lIIIIIllIIIIllI = lIIIIIllIIIIIlI.box.getX();
                    double lIIIIIllIIIIlIl = lIIIIIllIIIIIlI.box.getY();
                    double lIIIIIllIIIIlII = lIIIIIllIIIIIlI.box.width;
                    double lIIIIIllIIIIIll = lIIIIIllIIIIIlI.box.height;
                    if (!lIIIIIlIllIlIIl.isInSelection(lIIIIIlIllIlIII, lIIIIIlIllIIIll, lIIIIIllIIIIllI, lIIIIIllIIIIlIl) && !lIIIIIlIllIlIIl.isInSelection(lIIIIIlIllIlIII, lIIIIIlIllIIIll, lIIIIIllIIIIllI + lIIIIIllIIIIlII, lIIIIIllIIIIlIl) && !lIIIIIlIllIlIIl.isInSelection(lIIIIIlIllIlIII, lIIIIIlIllIIIll, lIIIIIllIIIIllI, lIIIIIllIIIIlIl + lIIIIIllIIIIIll) && !lIIIIIlIllIlIIl.isInSelection(lIIIIIlIllIlIII, lIIIIIlIllIIIll, lIIIIIllIIIIllI + lIIIIIllIIIIlII, lIIIIIllIIIIlIl + lIIIIIllIIIIIll)) continue;
                    lIIIIIlIllIlIIl.selectedElements.add(lIIIIIllIIIIIlI);
                }
            } else if (lIIIIIlIllIlIIl.dragging) {
                for (HudElement lIIIIIllIIIIIIl : lIIIIIlIllIlIIl.selectedElements) {
                    lIIIIIllIIIIIIl.box.addPos(lIIIIIlIllIlIII - lIIIIIlIllIlIIl.lastMouseX, lIIIIIlIllIIIll - lIIIIIlIllIlIIl.lastMouseY);
                }
                double lIIIIIlIllIlIlI = lIIIIIlIllIlIIl.hud.snappingRange.get().intValue();
                if (lIIIIIlIllIlIlI > 0.0) {
                    double lIIIIIlIlllIIII = Double.MAX_VALUE;
                    double lIIIIIlIllIllll = Double.MAX_VALUE;
                    double lIIIIIlIllIlllI = 0.0;
                    double lIIIIIlIllIllIl = 0.0;
                    for (HudElement lIIIIIllIIIIIII : lIIIIIlIllIlIIl.selectedElements) {
                        lIIIIIlIlllIIII = Math.min(lIIIIIlIlllIIII, lIIIIIllIIIIIII.box.getX());
                        lIIIIIlIllIllll = Math.min(lIIIIIlIllIllll, lIIIIIllIIIIIII.box.getY());
                    }
                    for (HudElement lIIIIIlIlllllll : lIIIIIlIllIlIIl.selectedElements) {
                        lIIIIIlIllIlllI = Math.max(lIIIIIlIllIlllI, lIIIIIlIlllllll.box.getX() - lIIIIIlIlllIIII + lIIIIIlIlllllll.box.width);
                        lIIIIIlIllIllIl = Math.max(lIIIIIlIllIllIl, lIIIIIlIlllllll.box.getY() - lIIIIIlIllIllll + lIIIIIlIlllllll.box.height);
                    }
                    boolean lIIIIIlIllIllII = false;
                    boolean lIIIIIlIllIlIll = false;
                    for (HudElement lIIIIIlIlllIIIl : lIIIIIlIllIlIIl.hud.elements) {
                        if (lIIIIIlIllIlIIl.selectedElements.contains(lIIIIIlIlllIIIl)) continue;
                        double lIIIIIlIllllIIl = lIIIIIlIlllIIIl.box.getX();
                        double lIIIIIlIllllIII = lIIIIIlIlllIIIl.box.getY();
                        double lIIIIIlIlllIlll = lIIIIIlIlllIIIl.box.width;
                        double lIIIIIlIlllIllI = lIIIIIlIlllIIIl.box.height;
                        boolean lIIIIIlIlllIlIl = lIIIIIlIllIlIIl.isPointBetween(lIIIIIlIlllIIII, lIIIIIlIllIlllI, lIIIIIlIllllIIl) || lIIIIIlIllIlIIl.isPointBetween(lIIIIIlIlllIIII, lIIIIIlIllIlllI, lIIIIIlIllllIIl + lIIIIIlIlllIlll) || lIIIIIlIllIlIIl.isPointBetween(lIIIIIlIllllIIl, lIIIIIlIlllIlll, lIIIIIlIlllIIII) || lIIIIIlIllIlIIl.isPointBetween(lIIIIIlIllllIIl, lIIIIIlIlllIlll, lIIIIIlIlllIIII + lIIIIIlIllIlllI);
                        boolean lIIIIIlIlllIlII = lIIIIIlIllIlIIl.isPointBetween(lIIIIIlIllIllll, lIIIIIlIllIllIl, lIIIIIlIllllIII) || lIIIIIlIllIlIIl.isPointBetween(lIIIIIlIllIllll, lIIIIIlIllIllIl, lIIIIIlIllllIII + lIIIIIlIlllIllI) || lIIIIIlIllIlIIl.isPointBetween(lIIIIIlIllllIII, lIIIIIlIlllIllI, lIIIIIlIllIllll) || lIIIIIlIllIlIIl.isPointBetween(lIIIIIlIllllIII, lIIIIIlIlllIllI, lIIIIIlIllIllll + lIIIIIlIllIllIl);
                        double lIIIIIlIlllIIll = 0.0;
                        double lIIIIIlIlllIIlI = 0.0;
                        if (!lIIIIIlIllIllII && lIIIIIlIlllIlII) {
                            double lIIIIIlIllllllI = lIIIIIlIlllIIII + lIIIIIlIllIlllI;
                            double lIIIIIlIlllllIl = lIIIIIlIllllIIl + lIIIIIlIlllIlll;
                            if (Math.abs(lIIIIIlIllllIIl - lIIIIIlIlllIIII) < lIIIIIlIllIlIlI) {
                                lIIIIIlIlllIIll = lIIIIIlIllllIIl - lIIIIIlIlllIIII;
                            } else if (Math.abs(lIIIIIlIlllllIl - lIIIIIlIllllllI) <= lIIIIIlIllIlIlI) {
                                lIIIIIlIlllIIll = lIIIIIlIlllllIl - lIIIIIlIllllllI;
                            } else if (Math.abs(lIIIIIlIlllllIl - lIIIIIlIlllIIII) <= lIIIIIlIllIlIlI) {
                                lIIIIIlIlllIIll = lIIIIIlIlllllIl - lIIIIIlIlllIIII;
                            } else if (Math.abs(lIIIIIlIllllIIl - lIIIIIlIllllllI) <= lIIIIIlIllIlIlI) {
                                lIIIIIlIlllIIll = lIIIIIlIllllIIl - lIIIIIlIllllllI;
                            }
                        }
                        if (!lIIIIIlIllIlIll && lIIIIIlIlllIlIl) {
                            double lIIIIIlIlllllII = lIIIIIlIllIllll + lIIIIIlIllIllIl;
                            double lIIIIIlIllllIll = lIIIIIlIllllIII + lIIIIIlIlllIllI;
                            if (Math.abs(lIIIIIlIllllIII - lIIIIIlIllIllll) <= lIIIIIlIllIlIlI) {
                                lIIIIIlIlllIIlI = lIIIIIlIllllIII - lIIIIIlIllIllll;
                            } else if (Math.abs(lIIIIIlIllllIll - lIIIIIlIlllllII) <= lIIIIIlIllIlIlI) {
                                lIIIIIlIlllIIlI = lIIIIIlIllllIll - lIIIIIlIlllllII;
                            } else if (Math.abs(lIIIIIlIllllIll - lIIIIIlIllIllll) <= lIIIIIlIllIlIlI) {
                                lIIIIIlIlllIIlI = lIIIIIlIllllIll - lIIIIIlIllIllll;
                            } else if (Math.abs(lIIIIIlIllllIII - lIIIIIlIlllllII) <= lIIIIIlIllIlIlI) {
                                lIIIIIlIlllIIlI = lIIIIIlIllllIII - lIIIIIlIlllllII;
                            }
                        }
                        if (lIIIIIlIlllIIll != 0.0 || lIIIIIlIlllIIlI != 0.0) {
                            for (HudElement lIIIIIlIllllIlI : lIIIIIlIllIlIIl.selectedElements) {
                                lIIIIIlIllllIlI.box.addPos(lIIIIIlIlllIIll, lIIIIIlIlllIIlI);
                            }
                            if (lIIIIIlIlllIIll != 0.0) {
                                lIIIIIlIllIllII = true;
                            }
                            if (lIIIIIlIlllIIlI != 0.0) {
                                lIIIIIlIllIlIll = true;
                            }
                        }
                        if (!lIIIIIlIllIllII || !lIIIIIlIllIlIll) continue;
                        break;
                    }
                    lIIIIIlIllIlIIl.dragged = true;
                }
            }
            lIIIIIlIllIlIIl.lastMouseX = lIIIIIlIllIlIII;
            lIIIIIlIllIlIIl.lastMouseY = lIIIIIlIllIIIll;
        }

        @Override
        protected void method_25426() {
            HudScreen lIIIIIlllIlIIIl;
            super.method_25426();
            Utils.mc.field_1690.field_1842 = false;
        }

        private void renderElement(HudElement lIIIIIlIIIllIlI, Color lIIIIIlIIIlllIl, Color lIIIIIlIIIllIII) {
            HudScreen lIIIIIlIIIllIll;
            lIIIIIlIIIllIll.renderQuad(lIIIIIlIIIllIlI.box.getX(), lIIIIIlIIIllIlI.box.getY(), lIIIIIlIIIllIlI.box.width, lIIIIIlIIIllIlI.box.height, lIIIIIlIIIlllIl, lIIIIIlIIIllIII);
        }

        public HudScreen(GuiTheme lIIIIIlllIlIlIl, Tab lIIIIIlllIlIlII) {
            super(lIIIIIlllIlIlIl, lIIIIIlllIlIlII);
            HudScreen lIIIIIlllIllIIl;
            lIIIIIlllIllIIl.HOVER_BG_COLOR = new Color(200, 200, 200, 50);
            lIIIIIlllIllIIl.HOVER_OL_COLOR = new Color(200, 200, 200, 200);
            lIIIIIlllIllIIl.INACTIVE_BG_COLOR = new Color(200, 25, 25, 50);
            lIIIIIlllIllIIl.INACTIVE_OL_COLOR = new Color(200, 25, 25, 200);
            lIIIIIlllIllIIl.selectedElements = new ArrayList<HudElement>();
            lIIIIIlllIllIIl.hud = Modules.get().get(HUD.class);
        }

        @Override
        public void method_25394(class_4587 lIIIIIlIIlIlIlI, int lIIIIIlIIlIlIIl, int lIIIIIlIIlIlIII, float lIIIIIlIIlIllIl) {
            HudScreen lIIIIIlIIllIIIl;
            double lIIIIIlIIlIllII = Utils.mc.method_22683().method_4495();
            lIIIIIlIIlIlIIl = (int)((double)lIIIIIlIIlIlIIl * lIIIIIlIIlIllII);
            lIIIIIlIIlIlIII = (int)((double)lIIIIIlIIlIlIII * lIIIIIlIIlIllII);
            if (!Utils.canUpdate()) {
                lIIIIIlIIllIIIl.method_25420(lIIIIIlIIlIlIlI);
                Utils.unscaledProjection();
                lIIIIIlIIllIIIl.hud.onRender(Render2DEvent.get(0, 0, lIIIIIlIIlIllIl));
            } else {
                Utils.unscaledProjection();
            }
            Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
            for (HudElement lIIIIIlIIllIlII : lIIIIIlIIllIIIl.hud.elements) {
                if (lIIIIIlIIllIlII.active) continue;
                lIIIIIlIIllIIIl.renderElement(lIIIIIlIIllIlII, lIIIIIlIIllIIIl.INACTIVE_BG_COLOR, lIIIIIlIIllIIIl.INACTIVE_OL_COLOR);
            }
            for (HudElement lIIIIIlIIllIIll : lIIIIIlIIllIIIl.selectedElements) {
                lIIIIIlIIllIIIl.renderElement(lIIIIIlIIllIIll, lIIIIIlIIllIIIl.HOVER_BG_COLOR, lIIIIIlIIllIIIl.HOVER_OL_COLOR);
            }
            if (!lIIIIIlIIllIIIl.dragging) {
                lIIIIIlIIllIIIl.hoveredModule = null;
                for (HudElement lIIIIIlIIllIIlI : lIIIIIlIIllIIIl.hud.elements) {
                    if (!lIIIIIlIIllIIlI.box.isOver(lIIIIIlIIlIlIIl, lIIIIIlIIlIlIII)) continue;
                    if (!lIIIIIlIIllIIIl.selectedElements.contains(lIIIIIlIIllIIlI)) {
                        lIIIIIlIIllIIIl.renderElement(lIIIIIlIIllIIlI, lIIIIIlIIllIIIl.HOVER_BG_COLOR, lIIIIIlIIllIIIl.HOVER_OL_COLOR);
                    }
                    lIIIIIlIIllIIIl.hoveredModule = lIIIIIlIIllIIlI;
                    break;
                }
                if (lIIIIIlIIllIIIl.selecting) {
                    lIIIIIlIIllIIIl.renderQuad(lIIIIIlIIllIIIl.mouseStartX, lIIIIIlIIllIIIl.mouseStartY, (double)lIIIIIlIIlIlIIl - lIIIIIlIIllIIIl.mouseStartX, (double)lIIIIIlIIlIlIII - lIIIIIlIIllIIIl.mouseStartY, lIIIIIlIIllIIIl.HOVER_BG_COLOR, lIIIIIlIIllIIIl.HOVER_OL_COLOR);
                }
            }
            Renderer.NORMAL.end();
            Utils.scaledProjection();
        }

        private boolean isInSelection(double lIIIIIllIlIIlll, double lIIIIIllIlIIllI, double lIIIIIllIlIlllI, double lIIIIIllIlIIlII) {
            double lIIIIIllIlIlIIl;
            double lIIIIIllIlIlIll;
            double lIIIIIllIlIlIlI;
            double lIIIIIllIlIllII;
            HudScreen lIIIIIllIllIIIl;
            if (lIIIIIllIlIIlll >= lIIIIIllIllIIIl.mouseStartX) {
                double lIIIIIllIllIlIl = lIIIIIllIllIIIl.mouseStartX;
                double lIIIIIllIllIlII = lIIIIIllIlIIlll - lIIIIIllIllIIIl.mouseStartX;
            } else {
                lIIIIIllIlIllII = lIIIIIllIlIIlll;
                lIIIIIllIlIlIlI = lIIIIIllIllIIIl.mouseStartX - lIIIIIllIlIIlll;
            }
            if (lIIIIIllIlIIllI >= lIIIIIllIllIIIl.mouseStartY) {
                double lIIIIIllIllIIll = lIIIIIllIllIIIl.mouseStartY;
                double lIIIIIllIllIIlI = lIIIIIllIlIIllI - lIIIIIllIllIIIl.mouseStartY;
            } else {
                lIIIIIllIlIlIll = lIIIIIllIlIIllI;
                lIIIIIllIlIlIIl = lIIIIIllIllIIIl.mouseStartY - lIIIIIllIlIIllI;
            }
            return lIIIIIllIlIlllI >= lIIIIIllIlIllII && lIIIIIllIlIlllI <= lIIIIIllIlIllII + lIIIIIllIlIlIlI && lIIIIIllIlIIlII >= lIIIIIllIlIlIll && lIIIIIllIlIIlII <= lIIIIIllIlIlIll + lIIIIIllIlIlIIl;
        }

        @Override
        public boolean method_25402(double lIIIIIlllIIIlll, double lIIIIIlllIIIllI, int lIIIIIlllIIIlIl) {
            HudScreen lIIIIIlllIIIIll;
            if (lIIIIIlllIIIIll.hoveredModule != null) {
                if (lIIIIIlllIIIlIl == 1) {
                    if (!lIIIIIlllIIIIll.selectedElements.isEmpty()) {
                        lIIIIIlllIIIIll.selectedElements.clear();
                    }
                    Utils.mc.method_1507((class_437)new HudElementScreen(lIIIIIlllIIIIll.theme, lIIIIIlllIIIIll.hoveredModule));
                } else {
                    lIIIIIlllIIIIll.dragging = true;
                    lIIIIIlllIIIIll.dragged = false;
                    if (!lIIIIIlllIIIIll.selectedElements.contains(lIIIIIlllIIIIll.hoveredModule)) {
                        lIIIIIlllIIIIll.selectedElements.clear();
                        lIIIIIlllIIIIll.selectedElements.add(lIIIIIlllIIIIll.hoveredModule);
                    }
                }
                return true;
            }
            double lIIIIIlllIIIlII = Utils.mc.method_22683().method_4495();
            lIIIIIlllIIIIll.selecting = true;
            lIIIIIlllIIIIll.mouseStartX = lIIIIIlllIIIlll * lIIIIIlllIIIlII;
            lIIIIIlllIIIIll.mouseStartY = lIIIIIlllIIIllI * lIIIIIlllIIIlII;
            if (!lIIIIIlllIIIIll.selectedElements.isEmpty()) {
                lIIIIIlllIIIIll.selectedElements.clear();
                return true;
            }
            return false;
        }

        @Override
        public void method_25419() {
            HudScreen lIIIIIlllIIllll;
            super.method_25419();
            if (lIIIIIlllIIllll.theme.hideHUD() && lIIIIIlllIIllll.parent instanceof WidgetScreen) {
                Utils.mc.field_1690.field_1842 = true;
            }
        }

        private void renderQuad(double lIIIIIlIIIlIIII, double lIIIIIlIIIIllll, double lIIIIIlIIIIlllI, double lIIIIIlIIIIllIl, Color lIIIIIlIIIIllII, Color lIIIIIlIIIIlIll) {
            Renderer.NORMAL.quad(lIIIIIlIIIlIIII, lIIIIIlIIIIllll, lIIIIIlIIIIlllI, lIIIIIlIIIIllIl, lIIIIIlIIIIllII);
            Renderer.NORMAL.quad(lIIIIIlIIIlIIII - 1.0, lIIIIIlIIIIllll - 1.0, lIIIIIlIIIIlllI + 2.0, 1.0, lIIIIIlIIIIlIll);
            Renderer.NORMAL.quad(lIIIIIlIIIlIIII - 1.0, lIIIIIlIIIIllll + lIIIIIlIIIIllIl - 1.0, lIIIIIlIIIIlllI + 2.0, 1.0, lIIIIIlIIIIlIll);
            Renderer.NORMAL.quad(lIIIIIlIIIlIIII - 1.0, lIIIIIlIIIIllll, 1.0, lIIIIIlIIIIllIl, lIIIIIlIIIIlIll);
            Renderer.NORMAL.quad(lIIIIIlIIIlIIII + lIIIIIlIIIIlllI, lIIIIIlIIIIllll, 1.0, lIIIIIlIIIIllIl, lIIIIIlIIIIlIll);
        }

        private boolean isPointBetween(double lIIIIIlIlIIlIII, double lIIIIIlIlIIIlII, double lIIIIIlIlIIIllI) {
            return lIIIIIlIlIIIllI >= lIIIIIlIlIIlIII && lIIIIIlIlIIIllI <= lIIIIIlIlIIlIII + lIIIIIlIlIIIlII;
        }

        @Override
        public boolean method_25406(double lIIIIIlIlIIIIII, double lIIIIIlIIllllll, int lIIIIIlIIlllllI) {
            HudScreen lIIIIIlIIllllIl;
            if (lIIIIIlIIllllIl.dragging) {
                lIIIIIlIIllllIl.dragging = false;
                if (!lIIIIIlIIllllIl.dragged && !lIIIIIlIIllllIl.selectedElements.isEmpty()) {
                    lIIIIIlIIllllIl.selectedElements.forEach(HudElement::toggle);
                    lIIIIIlIIllllIl.selectedElements.clear();
                }
                if (lIIIIIlIIllllIl.selectedElements.size() <= 1) {
                    lIIIIIlIIllllIl.selectedElements.clear();
                }
                return true;
            }
            if (lIIIIIlIIllllIl.selecting) {
                lIIIIIlIIllllIl.selecting = false;
                return true;
            }
            return false;
        }
    }
}

