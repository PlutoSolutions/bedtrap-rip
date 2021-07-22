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
    public static HudTab INSTANCE;

    @Override
    public boolean isScreen(class_437 class_4372) {
        return class_4372 instanceof HudScreen;
    }

    public HudTab() {
        super("HUD");
        INSTANCE = this;
    }

    @Override
    public TabScreen createScreen(GuiTheme guiTheme) {
        return new HudScreen(guiTheme, this);
    }

    @Override
    public void openScreen(GuiTheme guiTheme) {
        Utils.mc.method_1507((class_437)this.createScreen(guiTheme));
    }

    public static class HudScreen
    extends WindowTabScreen {
        private double lastMouseY;
        private final Color INACTIVE_OL_COLOR;
        private double mouseStartX;
        private final Color HOVER_OL_COLOR;
        private boolean selecting;
        private final List<HudElement> selectedElements;
        private boolean dragging;
        private final Color INACTIVE_BG_COLOR;
        private double mouseStartY;
        private double lastMouseX;
        private boolean dragged;
        private HudElement hoveredModule;
        private final HUD hud;
        private final Color HOVER_BG_COLOR = new Color(200, 200, 200, 50);

        @Override
        public void method_16014(double d, double d2) {
            double d3 = Utils.mc.method_22683().method_4495();
            d *= d3;
            d2 *= d3;
            if (this.selecting) {
                this.selectedElements.clear();
                for (HudElement hudElement : this.hud.elements) {
                    double d4 = hudElement.box.getX();
                    double d5 = hudElement.box.getY();
                    double d6 = hudElement.box.width;
                    double d7 = hudElement.box.height;
                    if (!this.isInSelection(d, d2, d4, d5) && !this.isInSelection(d, d2, d4 + d6, d5) && !this.isInSelection(d, d2, d4, d5 + d7) && !this.isInSelection(d, d2, d4 + d6, d5 + d7)) continue;
                    this.selectedElements.add(hudElement);
                }
            } else if (this.dragging) {
                for (HudElement hudElement : this.selectedElements) {
                    hudElement.box.addPos(d - this.lastMouseX, d2 - this.lastMouseY);
                }
                double d8 = this.hud.snappingRange.get().intValue();
                if (d8 > 0.0) {
                    double d9 = Double.MAX_VALUE;
                    double d10 = Double.MAX_VALUE;
                    double d11 = 0.0;
                    double d12 = 0.0;
                    for (HudElement hudElement : this.selectedElements) {
                        d9 = Math.min(d9, hudElement.box.getX());
                        d10 = Math.min(d10, hudElement.box.getY());
                    }
                    for (HudElement hudElement : this.selectedElements) {
                        d11 = Math.max(d11, hudElement.box.getX() - d9 + hudElement.box.width);
                        d12 = Math.max(d12, hudElement.box.getY() - d10 + hudElement.box.height);
                    }
                    boolean bl = false;
                    boolean bl2 = false;
                    for (HudElement hudElement : this.hud.elements) {
                        double d13;
                        double d14;
                        if (this.selectedElements.contains(hudElement)) continue;
                        double d15 = hudElement.box.getX();
                        double d16 = hudElement.box.getY();
                        double d17 = hudElement.box.width;
                        double d18 = hudElement.box.height;
                        boolean bl3 = this.isPointBetween(d9, d11, d15) || this.isPointBetween(d9, d11, d15 + d17) || this.isPointBetween(d15, d17, d9) || this.isPointBetween(d15, d17, d9 + d11);
                        boolean bl4 = this.isPointBetween(d10, d12, d16) || this.isPointBetween(d10, d12, d16 + d18) || this.isPointBetween(d16, d18, d10) || this.isPointBetween(d16, d18, d10 + d12);
                        double d19 = 0.0;
                        double d20 = 0.0;
                        if (!bl && bl4) {
                            d14 = d9 + d11;
                            d13 = d15 + d17;
                            if (Math.abs(d15 - d9) < d8) {
                                d19 = d15 - d9;
                            } else if (Math.abs(d13 - d14) <= d8) {
                                d19 = d13 - d14;
                            } else if (Math.abs(d13 - d9) <= d8) {
                                d19 = d13 - d9;
                            } else if (Math.abs(d15 - d14) <= d8) {
                                d19 = d15 - d14;
                            }
                        }
                        if (!bl2 && bl3) {
                            d14 = d10 + d12;
                            d13 = d16 + d18;
                            if (Math.abs(d16 - d10) <= d8) {
                                d20 = d16 - d10;
                            } else if (Math.abs(d13 - d14) <= d8) {
                                d20 = d13 - d14;
                            } else if (Math.abs(d13 - d10) <= d8) {
                                d20 = d13 - d10;
                            } else if (Math.abs(d16 - d14) <= d8) {
                                d20 = d16 - d14;
                            }
                        }
                        if (d19 != 0.0 || d20 != 0.0) {
                            for (HudElement hudElement2 : this.selectedElements) {
                                hudElement2.box.addPos(d19, d20);
                            }
                            if (d19 != 0.0) {
                                bl = true;
                            }
                            if (d20 != 0.0) {
                                bl2 = true;
                            }
                        }
                        if (!bl || !bl2) continue;
                    }
                    this.dragged = true;
                }
            }
            this.lastMouseX = d;
            this.lastMouseY = d2;
        }

        @Override
        protected void method_25426() {
            super.method_25426();
            Utils.mc.field_1690.field_1842 = false;
        }

        private void renderElement(HudElement hudElement, Color color, Color color2) {
            this.renderQuad(hudElement.box.getX(), hudElement.box.getY(), hudElement.box.width, hudElement.box.height, color, color2);
        }

        public HudScreen(GuiTheme guiTheme, Tab tab) {
            super(guiTheme, tab);
            this.HOVER_OL_COLOR = new Color(200, 200, 200, 200);
            this.INACTIVE_BG_COLOR = new Color(200, 25, 25, 50);
            this.INACTIVE_OL_COLOR = new Color(200, 25, 25, 200);
            this.selectedElements = new ArrayList<HudElement>();
            this.hud = Modules.get().get(HUD.class);
        }

        @Override
        public void method_25394(class_4587 class_45872, int n, int n2, float f) {
            double d = Utils.mc.method_22683().method_4495();
            n = (int)((double)n * d);
            n2 = (int)((double)n2 * d);
            if (!Utils.canUpdate()) {
                this.method_25420(class_45872);
                Utils.unscaledProjection();
                this.hud.onRender(Render2DEvent.get(0, 0, f));
            } else {
                Utils.unscaledProjection();
            }
            Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
            for (HudElement hudElement : this.hud.elements) {
                if (hudElement.active) continue;
                this.renderElement(hudElement, this.INACTIVE_BG_COLOR, this.INACTIVE_OL_COLOR);
            }
            for (HudElement hudElement : this.selectedElements) {
                this.renderElement(hudElement, this.HOVER_BG_COLOR, this.HOVER_OL_COLOR);
            }
            if (!this.dragging) {
                this.hoveredModule = null;
                for (HudElement hudElement : this.hud.elements) {
                    if (!hudElement.box.isOver(n, n2)) continue;
                    if (!this.selectedElements.contains(hudElement)) {
                        this.renderElement(hudElement, this.HOVER_BG_COLOR, this.HOVER_OL_COLOR);
                    }
                    this.hoveredModule = hudElement;
                    break;
                }
                if (this.selecting) {
                    this.renderQuad(this.mouseStartX, this.mouseStartY, (double)n - this.mouseStartX, (double)n2 - this.mouseStartY, this.HOVER_BG_COLOR, this.HOVER_OL_COLOR);
                }
            }
            Renderer.NORMAL.end();
            Utils.scaledProjection();
        }

        private boolean isInSelection(double d, double d2, double d3, double d4) {
            double d5;
            double d6;
            double d7;
            double d8;
            if (d >= this.mouseStartX) {
                d8 = this.mouseStartX;
                d7 = d - this.mouseStartX;
            } else {
                d8 = d;
                d7 = this.mouseStartX - d;
            }
            if (d2 >= this.mouseStartY) {
                d6 = this.mouseStartY;
                d5 = d2 - this.mouseStartY;
            } else {
                d6 = d2;
                d5 = this.mouseStartY - d2;
            }
            return d3 >= d8 && d3 <= d8 + d7 && d4 >= d6 && d4 <= d6 + d5;
        }

        @Override
        public boolean method_25402(double d, double d2, int n) {
            if (this.hoveredModule != null) {
                if (n == 1) {
                    if (!this.selectedElements.isEmpty()) {
                        this.selectedElements.clear();
                    }
                    Utils.mc.method_1507((class_437)new HudElementScreen(this.theme, this.hoveredModule));
                } else {
                    this.dragging = true;
                    this.dragged = false;
                    if (!this.selectedElements.contains(this.hoveredModule)) {
                        this.selectedElements.clear();
                        this.selectedElements.add(this.hoveredModule);
                    }
                }
                return true;
            }
            double d3 = Utils.mc.method_22683().method_4495();
            this.selecting = true;
            this.mouseStartX = d * d3;
            this.mouseStartY = d2 * d3;
            if (!this.selectedElements.isEmpty()) {
                this.selectedElements.clear();
                return true;
            }
            return false;
        }

        @Override
        public void method_25419() {
            super.method_25419();
            if (this.theme.hideHUD() && this.parent instanceof WidgetScreen) {
                Utils.mc.field_1690.field_1842 = true;
            }
        }

        private void renderQuad(double d, double d2, double d3, double d4, Color color, Color color2) {
            Renderer.NORMAL.quad(d, d2, d3, d4, color);
            Renderer.NORMAL.quad(d - 1.0, d2 - 1.0, d3 + 2.0, 1.0, color2);
            Renderer.NORMAL.quad(d - 1.0, d2 + d4 - 1.0, d3 + 2.0, 1.0, color2);
            Renderer.NORMAL.quad(d - 1.0, d2, 1.0, d4, color2);
            Renderer.NORMAL.quad(d + d3, d2, 1.0, d4, color2);
        }

        private boolean isPointBetween(double d, double d2, double d3) {
            return d3 >= d && d3 <= d + d2;
        }

        @Override
        public boolean method_25406(double d, double d2, int n) {
            if (this.dragging) {
                this.dragging = false;
                if (!this.dragged && !this.selectedElements.isEmpty()) {
                    this.selectedElements.forEach(HudElement::toggle);
                    this.selectedElements.clear();
                }
                if (this.selectedElements.size() <= 1) {
                    this.selectedElements.clear();
                }
                return true;
            }
            if (this.selecting) {
                this.selecting = false;
                return true;
            }
            return false;
        }
    }
}

