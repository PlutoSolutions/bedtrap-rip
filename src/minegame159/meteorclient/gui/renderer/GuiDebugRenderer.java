/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_290
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
    private final /* synthetic */ MeshBuilder mb;
    private static final /* synthetic */ Color WIDGET_COLOR;
    private static final /* synthetic */ Color CELL_COLOR;

    private void lineBox(double lIIIIIlllIlI, double lIIIIIlllIIl, double lIIIIIllIIlI, double lIIIIIllIlll, Color lIIIIIllIllI) {
        GuiDebugRenderer lIIIIIlllIll;
        lIIIIIlllIll.line(lIIIIIlllIlI, lIIIIIlllIIl, lIIIIIlllIlI + lIIIIIllIIlI, lIIIIIlllIIl, lIIIIIllIllI);
        lIIIIIlllIll.line(lIIIIIlllIlI + lIIIIIllIIlI, lIIIIIlllIIl, lIIIIIlllIlI + lIIIIIllIIlI, lIIIIIlllIIl + lIIIIIllIlll, lIIIIIllIllI);
        lIIIIIlllIll.line(lIIIIIlllIlI, lIIIIIlllIIl, lIIIIIlllIlI, lIIIIIlllIIl + lIIIIIllIlll, lIIIIIllIllI);
        lIIIIIlllIll.line(lIIIIIlllIlI, lIIIIIlllIIl + lIIIIIllIlll, lIIIIIlllIlI + lIIIIIllIIlI, lIIIIIlllIIl + lIIIIIllIlll, lIIIIIllIllI);
    }

    static {
        CELL_COLOR = new Color(25, 225, 25);
        WIDGET_COLOR = new Color(25, 25, 225);
    }

    private void renderWidget(WWidget lIIIIlIIIllI) {
        GuiDebugRenderer lIIIIlIIIlIl;
        lIIIIlIIIlIl.lineBox(lIIIIlIIIllI.x, lIIIIlIIIllI.y, lIIIIlIIIllI.width, lIIIIlIIIllI.height, WIDGET_COLOR);
        if (lIIIIlIIIllI instanceof WContainer) {
            for (Cell<?> lIIIIlIIlIII : ((WContainer)lIIIIlIIIllI).cells) {
                lIIIIlIIIlIl.lineBox(lIIIIlIIlIII.x, lIIIIlIIlIII.y, lIIIIlIIlIII.width, lIIIIlIIlIII.height, CELL_COLOR);
                lIIIIlIIIlIl.renderWidget((WWidget)lIIIIlIIlIII.widget());
            }
        }
    }

    private void line(double lIIIIIlIIIlI, double lIIIIIlIIIIl, double lIIIIIlIIIII, double lIIIIIIlllll, Color lIIIIIIllllI) {
        GuiDebugRenderer lIIIIIlIlIIl;
        lIIIIIlIlIIl.mb.pos(lIIIIIlIIIlI, lIIIIIlIIIIl, 0.0).color(lIIIIIIllllI).endVertex();
        lIIIIIlIlIIl.mb.pos(lIIIIIlIIIII, lIIIIIIlllll, 0.0).color(lIIIIIIllllI).endVertex();
    }

    public GuiDebugRenderer() {
        GuiDebugRenderer lIIIIlIlIIll;
        lIIIIlIlIIll.mb = new MeshBuilder();
    }

    public void render(WWidget lIIIIlIIllll) {
        GuiDebugRenderer lIIIIlIlIIII;
        if (lIIIIlIIllll == null) {
            return;
        }
        lIIIIlIlIIII.mb.begin(null, DrawMode.Lines, class_290.field_1576);
        lIIIIlIlIIII.renderWidget(lIIIIlIIllll);
        lIIIIlIlIIII.mb.end();
    }
}

