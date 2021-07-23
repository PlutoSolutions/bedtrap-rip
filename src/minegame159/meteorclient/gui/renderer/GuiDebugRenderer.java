/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.renderer;

import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.MeshBuilder;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_290;

public class GuiDebugRenderer {
    private final MeshBuilder mb = new MeshBuilder();
    private static final Color WIDGET_COLOR;
    private static final Color CELL_COLOR;

    private void lineBox(double d, double d2, double d3, double d4, Color color) {
        this.line(d, d2, d + d3, d2, color);
        this.line(d + d3, d2, d + d3, d2 + d4, color);
        this.line(d, d2, d, d2 + d4, color);
        this.line(d, d2 + d4, d + d3, d2 + d4, color);
    }

    static {
        CELL_COLOR = new Color(25, 225, 25);
        WIDGET_COLOR = new Color(25, 25, 225);
    }

    private void renderWidget(WWidget wWidget) {
        this.lineBox(wWidget.x, wWidget.y, wWidget.width, wWidget.height, WIDGET_COLOR);
        if (wWidget instanceof WContainer) {
            for (Cell<?> cell : ((WContainer)wWidget).cells) {
                this.lineBox(cell.x, cell.y, cell.width, cell.height, CELL_COLOR);
                this.renderWidget((WWidget)cell.widget());
            }
        }
    }

    private void line(double d, double d2, double d3, double d4, Color color) {
        this.mb.pos(d, d2, 0.0).color(color).endVertex();
        this.mb.pos(d3, d4, 0.0).color(color).endVertex();
    }

    public void render(WWidget wWidget) {
        if (wWidget == null) {
            return;
        }
        this.mb.begin(null, DrawMode.Lines, class_290.field_1576);
        this.renderWidget(wWidget);
        this.mb.end();
    }
}

