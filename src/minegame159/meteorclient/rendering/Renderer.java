/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_290
 */
package minegame159.meteorclient.rendering;

import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.MeshBuilder;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_2338;
import net.minecraft.class_290;

public class Renderer {
    private static /* synthetic */ boolean building;
    public static final /* synthetic */ MeshBuilder NORMAL;
    public static final /* synthetic */ MeshBuilder LINES;

    public static void quadWithLines(MeshBuilder llIlllIIlIllIll, MeshBuilder llIlllIIllIlIll, double llIlllIIlIllIIl, double llIlllIIllIlIIl, double llIlllIIlIlIlll, double llIlllIIlIlIllI, double llIlllIIllIIllI, double llIlllIIllIIlIl, double llIlllIIllIIlII, double llIlllIIlIlIIlI, double llIlllIIlIlIIIl, double llIlllIIlIlIIII, double llIlllIIllIIIII, double llIlllIIlIlllll, Color llIlllIIlIIllIl, Color llIlllIIlIIllII, ShapeMode llIlllIIlIIlIll) {
        if (llIlllIIlIIlIll == ShapeMode.Sides || llIlllIIlIIlIll == ShapeMode.Both) {
            llIlllIIlIllIll.quad(llIlllIIlIllIIl, llIlllIIllIlIIl, llIlllIIlIlIlll, llIlllIIlIlIllI, llIlllIIllIIllI, llIlllIIllIIlIl, llIlllIIllIIlII, llIlllIIlIlIIlI, llIlllIIlIlIIIl, llIlllIIlIlIIII, llIlllIIllIIIII, llIlllIIlIlllll, llIlllIIlIIllIl);
        }
        if (llIlllIIlIIlIll == ShapeMode.Lines || llIlllIIlIIlIll == ShapeMode.Both) {
            llIlllIIllIlIll.line(llIlllIIlIllIIl, llIlllIIllIlIIl, llIlllIIlIlIlll, llIlllIIlIlIllI, llIlllIIllIIllI, llIlllIIllIIlIl, llIlllIIlIIllII);
            llIlllIIllIlIll.line(llIlllIIlIlIllI, llIlllIIllIIllI, llIlllIIllIIlIl, llIlllIIllIIlII, llIlllIIlIlIIlI, llIlllIIlIlIIIl, llIlllIIlIIllII);
            llIlllIIllIlIll.line(llIlllIIllIIlII, llIlllIIlIlIIlI, llIlllIIlIlIIIl, llIlllIIlIlIIII, llIlllIIllIIIII, llIlllIIlIlllll, llIlllIIlIIllII);
            llIlllIIllIlIll.line(llIlllIIlIlIIII, llIlllIIllIIIII, llIlllIIlIlllll, llIlllIIlIllIIl, llIlllIIllIlIIl, llIlllIIlIlIlll, llIlllIIlIIllII);
        }
    }

    public static void boxWithLines(MeshBuilder llIlllIlIIIlIll, MeshBuilder llIlllIlIIIIIll, class_2338 llIlllIlIIIlIIl, Color llIlllIlIIIIIIl, Color llIlllIlIIIIlll, ShapeMode llIlllIlIIIIllI, int llIlllIlIIIIlIl) {
        Renderer.boxWithLines(llIlllIlIIIlIll, llIlllIlIIIIIll, llIlllIlIIIlIIl.method_10263(), llIlllIlIIIlIIl.method_10264(), llIlllIlIIIlIIl.method_10260(), llIlllIlIIIlIIl.method_10263() + 1, llIlllIlIIIlIIl.method_10264() + 1, llIlllIlIIIlIIl.method_10260() + 1, llIlllIlIIIIIIl, llIlllIlIIIIlll, llIlllIlIIIIllI, llIlllIlIIIIlIl);
    }

    public Renderer() {
        Renderer llIlllIllIllIIl;
    }

    public static void quadWithLinesHorizontal(MeshBuilder llIlllIIIIllIll, MeshBuilder llIlllIIIIllIlI, double llIlllIIIIllIIl, double llIlllIIIlIIIlI, double llIlllIIIlIIIIl, double llIlllIIIIlIllI, double llIlllIIIIlIlIl, Color llIlllIIIIlIlII, Color llIlllIIIIlIIll, ShapeMode llIlllIIIIlllII) {
        Renderer.quadWithLines(llIlllIIIIllIll, llIlllIIIIllIlI, llIlllIIIIllIIl, llIlllIIIlIIIlI, llIlllIIIlIIIIl, llIlllIIIIllIIl, llIlllIIIlIIIlI, llIlllIIIIlIlIl, llIlllIIIIlIllI, llIlllIIIlIIIlI, llIlllIIIIlIlIl, llIlllIIIIlIllI, llIlllIIIlIIIlI, llIlllIIIlIIIIl, llIlllIIIIlIlII, llIlllIIIIlIIll, llIlllIIIIlllII);
    }

    public static void quadWithLinesVertical(MeshBuilder llIllIllllllIll, MeshBuilder llIllIllllllIIl, double llIlllIIIIIIlII, double llIllIlllllIlll, double llIlllIIIIIIIlI, double llIllIlllllIlIl, double llIllIlllllIlII, double llIllIlllllIIll, Color llIllIllllllllI, Color llIllIlllllllIl, ShapeMode llIllIllllllIlI) {
        Renderer.quadWithLines(llIllIllllllIll, llIllIllllllIIl, llIlllIIIIIIlII, llIllIlllllIlll, llIlllIIIIIIIlI, llIlllIIIIIIlII, llIllIlllllIlII, llIlllIIIIIIIlI, llIllIlllllIlIl, llIllIlllllIlII, llIllIlllllIIll, llIllIlllllIlIl, llIllIlllllIlll, llIllIlllllIIll, llIllIllllllllI, llIllIlllllllIl, llIllIllllllIlI);
    }

    public static void boxWithLines(MeshBuilder llIlllIlIlIIllI, MeshBuilder llIlllIlIlIIlIl, double llIlllIlIlIIlII, double llIlllIlIIllIIl, double llIlllIlIIllIII, double llIlllIlIlIIIIl, Color llIlllIlIlIIIII, Color llIlllIlIIlllll, ShapeMode llIlllIlIIllllI, int llIlllIlIIlIIll) {
        Renderer.boxWithLines(llIlllIlIlIIllI, llIlllIlIlIIlIl, llIlllIlIlIIlII, llIlllIlIIllIIl, llIlllIlIIllIII, llIlllIlIlIIlII + llIlllIlIlIIIIl, llIlllIlIIllIIl + llIlllIlIlIIIIl, llIlllIlIIllIII + llIlllIlIlIIIIl, llIlllIlIlIIIII, llIlllIlIIlllll, llIlllIlIIllllI, llIlllIlIIlIIll);
    }

    public static void begin(RenderEvent llIlllIllIlIlIl) {
        if (!building) {
            NORMAL.begin(llIlllIllIlIlIl, DrawMode.Triangles, class_290.field_1576);
            LINES.begin(llIlllIllIlIlIl, DrawMode.Lines, class_290.field_1576);
            building = true;
        }
    }

    public static void quadWithLinesHorizontal(MeshBuilder llIlllIIlIIIIIl, MeshBuilder llIlllIIIllIlll, double llIlllIIIllllll, double llIlllIIIllIlIl, double llIlllIIIllllIl, double llIlllIIIllIIll, Color llIlllIIIllIIlI, Color llIlllIIIlllIlI, ShapeMode llIlllIIIllIIII) {
        Renderer.quadWithLines(llIlllIIlIIIIIl, llIlllIIIllIlll, llIlllIIIllllll, llIlllIIIllIlIl, llIlllIIIllllIl, llIlllIIIllllll, llIlllIIIllIlIl, llIlllIIIllllIl + llIlllIIIllIIll, llIlllIIIllllll + llIlllIIIllIIll, llIlllIIIllIlIl, llIlllIIIllllIl + llIlllIIIllIIll, llIlllIIIllllll + llIlllIIIllIIll, llIlllIIIllIlIl, llIlllIIIllllIl, llIlllIIIllIIlI, llIlllIIIlllIlI, llIlllIIIllIIII);
    }

    public static void boxWithLines(MeshBuilder llIlllIlIlllIll, MeshBuilder llIlllIlIlllIIl, double llIlllIllIIIllI, double llIlllIllIIIlIl, double llIlllIlIllIllI, double llIlllIlIllIlIl, double llIlllIlIllIlII, double llIlllIllIIIIIl, Color llIlllIlIllIIlI, Color llIlllIlIllIIIl, ShapeMode llIlllIlIlllllI, int llIlllIlIllllIl) {
        if (llIlllIlIlllllI == ShapeMode.Sides || llIlllIlIlllllI == ShapeMode.Both) {
            llIlllIlIlllIll.boxSides(llIlllIllIIIllI, llIlllIllIIIlIl, llIlllIlIllIllI, llIlllIlIllIlIl, llIlllIlIllIlII, llIlllIllIIIIIl, llIlllIlIllIIlI, llIlllIlIllllIl);
        }
        if (llIlllIlIlllllI == ShapeMode.Lines || llIlllIlIlllllI == ShapeMode.Both) {
            llIlllIlIlllIIl.boxEdges(llIlllIllIIIllI, llIlllIllIIIlIl, llIlllIlIllIllI, llIlllIlIllIlIl, llIlllIlIllIlII, llIlllIllIIIIIl, llIlllIlIllIIIl, llIlllIlIllllIl);
        }
    }

    public static void end() {
        if (building) {
            NORMAL.end();
            LINES.end();
            building = false;
        }
    }

    static {
        NORMAL = new MeshBuilder();
        LINES = new MeshBuilder();
    }
}

