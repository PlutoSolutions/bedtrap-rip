/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1159
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_293
 *  net.minecraft.class_4493
 *  net.minecraft.class_4493$class_4534
 *  net.minecraft.class_4493$class_4535
 *  org.lwjgl.opengl.GL11
 */
package minegame159.meteorclient.rendering;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.gui.renderer.packer.TextureRegion;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.Matrices;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.world.Dir;
import net.minecraft.class_1159;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_293;
import net.minecraft.class_4493;
import org.lwjgl.opengl.GL11;

public class MeshBuilder {
    private /* synthetic */ double offsetZ;
    private /* synthetic */ double offsetX;
    public /* synthetic */ double alpha;
    public /* synthetic */ boolean depthTest;
    private /* synthetic */ int count;
    public /* synthetic */ boolean texture;
    private final /* synthetic */ class_287 buffer;
    private /* synthetic */ double offsetY;

    public MeshBuilder pos(double lIIIlllIIllIlIl, double lIIIlllIIlllIII, double lIIIlllIIllIlll) {
        MeshBuilder lIIIlllIIllIllI;
        lIIIlllIIllIllI.buffer.method_22912(lIIIlllIIllIlIl + lIIIlllIIllIllI.offsetX, lIIIlllIIlllIII + lIIIlllIIllIllI.offsetY, lIIIlllIIllIlll + lIIIlllIIllIllI.offsetZ);
        return lIIIlllIIllIllI;
    }

    public MeshBuilder color(int lIIIlllIIIlllIl) {
        MeshBuilder lIIIlllIIIllllI;
        lIIIlllIIIllllI.buffer.method_22915((float)Color.toRGBAR(lIIIlllIIIlllIl) / 255.0f, (float)Color.toRGBAG(lIIIlllIIIlllIl) / 255.0f, (float)Color.toRGBAB(lIIIlllIIIlllIl) / 255.0f, (float)Color.toRGBAA(lIIIlllIIIlllIl) / 255.0f * (float)lIIIlllIIIllllI.alpha);
        return lIIIlllIIIllllI;
    }

    public void verticalQuad(double lIIIlIllIIlIIII, double lIIIlIllIIIllll, double lIIIlIllIIIlllI, double lIIIlIllIIlIlIl, double lIIIlIllIIIllII, double lIIIlIllIIIlIll, Color lIIIlIllIIIlIlI) {
        MeshBuilder lIIIlIllIIlIIIl;
        lIIIlIllIIlIIIl.quad(lIIIlIllIIlIIII, lIIIlIllIIIllll, lIIIlIllIIIlllI, lIIIlIllIIlIIII, lIIIlIllIIIllII, lIIIlIllIIIlllI, lIIIlIllIIlIlIl, lIIIlIllIIIllII, lIIIlIllIIIlIll, lIIIlIllIIlIlIl, lIIIlIllIIIllll, lIIIlIllIIIlIll, lIIIlIllIIIlIlI);
    }

    public void boxEdges(double lIIIlIIllIlllIl, double lIIIlIIllIlIllI, double lIIIlIIllIllIll, double lIIIlIIllIllIlI, Color lIIIlIIllIllIIl) {
        MeshBuilder lIIIlIIllIllllI;
        lIIIlIIllIllllI.boxEdges(lIIIlIIllIlllIl, lIIIlIIllIlIllI, 0.0, lIIIlIIllIlllIl + lIIIlIIllIllIll, lIIIlIIllIlIllI + lIIIlIIllIllIlI, 0.0, lIIIlIIllIllIIl, 0);
    }

    public MeshBuilder color(Color lIIIlllIIlIIlII) {
        MeshBuilder lIIIlllIIlIIlll;
        lIIIlllIIlIIlll.buffer.method_22915((float)lIIIlllIIlIIlII.r / 255.0f, (float)lIIIlllIIlIIlII.g / 255.0f, (float)lIIIlllIIlIIlII.b / 255.0f, (float)lIIIlllIIlIIlII.a / 255.0f * (float)lIIIlllIIlIIlll.alpha);
        return lIIIlllIIlIIlll;
    }

    public void line(double lIIIlIlIIIllIII, double lIIIlIlIIIlIlll, double lIIIlIlIIIllllI, double lIIIlIlIIIlIlIl, double lIIIlIlIIIlIlII, double lIIIlIlIIIlIIll, Color lIIIlIlIIIllIlI) {
        MeshBuilder lIIIlIlIIlIIIIl;
        lIIIlIlIIlIIIIl.line(lIIIlIlIIIllIII, lIIIlIlIIIlIlll, lIIIlIlIIIllllI, lIIIlIlIIIlIlIl, lIIIlIlIIIlIlII, lIIIlIlIIIlIIll, lIIIlIlIIIllIlI, lIIIlIlIIIllIlI);
    }

    public void begin(RenderEvent lIIIlllIlIlllII, DrawMode lIIIlllIlIllIlI, class_293 lIIIlllIlIllIII) {
        MeshBuilder lIIIlllIlIlIllI;
        if (lIIIlllIlIlllII != null) {
            lIIIlllIlIlIllI.offsetX = -lIIIlllIlIlllII.offsetX;
            lIIIlllIlIlIllI.offsetY = -lIIIlllIlIlllII.offsetY;
            lIIIlllIlIlIllI.offsetZ = -lIIIlllIlIlllII.offsetZ;
        } else {
            lIIIlllIlIlIllI.offsetX = 0.0;
            lIIIlllIlIlIllI.offsetY = 0.0;
            lIIIlllIlIlIllI.offsetZ = 0.0;
        }
        lIIIlllIlIlIllI.buffer.method_1328(lIIIlllIlIllIlI.toOpenGl(), lIIIlllIlIllIII);
        lIIIlllIlIlIllI.count = 0;
    }

    public MeshBuilder() {
        MeshBuilder lIIIlllIllIlIlI;
        lIIIlllIllIlIlI.alpha = 1.0;
        lIIIlllIllIlIlI.depthTest = false;
        lIIIlllIllIlIlI.texture = false;
        lIIIlllIllIlIlI.buffer = new class_287(0x200000);
    }

    public void verticalGradientQuad(double lIIIllIIIlllIlI, double lIIIllIIIlllIIl, double lIIIllIIIlllIII, double lIIIllIIIlllllI, Color lIIIllIIIllIllI, Color lIIIllIIIllllII) {
        MeshBuilder lIIIllIIlIIIIlI;
        lIIIllIIlIIIIlI.verticalGradientQuad(lIIIllIIIlllIlI, lIIIllIIIlllIIl, 0.0, lIIIllIIIlllIlI + lIIIllIIIlllIII, lIIIllIIIlllIIl, 0.0, lIIIllIIIlllIlI + lIIIllIIIlllIII, lIIIllIIIlllIIl + lIIIllIIIlllllI, 0.0, lIIIllIIIlllIlI, lIIIllIIIlllIIl + lIIIllIIIlllllI, 0.0, lIIIllIIIllIllI, lIIIllIIIllllII);
    }

    public void quad(double lIIIlIlllIIIIIl, double lIIIlIllIlllIlI, double lIIIlIllIlllIIl, double lIIIlIllIlllIII, Color lIIIlIllIllIlll) {
        MeshBuilder lIIIlIlllIIIIlI;
        lIIIlIlllIIIIlI.quad(lIIIlIlllIIIIIl, lIIIlIllIlllIlI, 0.0, lIIIlIlllIIIIIl + lIIIlIllIlllIIl, lIIIlIllIlllIlI, 0.0, lIIIlIlllIIIIIl + lIIIlIllIlllIIl, lIIIlIllIlllIlI + lIIIlIllIlllIII, 0.0, lIIIlIlllIIIIIl, lIIIlIllIlllIlI + lIIIlIllIlllIII, 0.0, lIIIlIllIllIlll);
    }

    public boolean isBuilding() {
        MeshBuilder lIIIlllIlIIIIII;
        return lIIIlllIlIIIIII.buffer.method_22893();
    }

    public void quad(double lIIIllIllIIlIll, double lIIIllIllIIlIIl, double lIIIllIllIIIlll, double lIIIllIllIIIlIl, double lIIIllIlllIIlII, double lIIIllIllIIIIIl, double lIIIllIlIllllll, double lIIIllIllIllllI, double lIIIllIlIllllIl, double lIIIllIlIllllII, double lIIIllIllIllIIl, double lIIIllIllIlIlll, Color lIIIllIllIlIlIl, Color lIIIllIlIlllIII, Color lIIIllIlIllIlll, Color lIIIllIlIllIllI) {
        MeshBuilder lIIIllIlllIllIl;
        lIIIllIlllIllIl.pos(lIIIllIllIIlIll, lIIIllIllIIlIIl, lIIIllIllIIIlll).color(lIIIllIllIlIlIl).endVertex();
        lIIIllIlllIllIl.pos(lIIIllIllIIIlIl, lIIIllIlllIIlII, lIIIllIllIIIIIl).color(lIIIllIlIlllIII).endVertex();
        lIIIllIlllIllIl.pos(lIIIllIlIllllll, lIIIllIllIllllI, lIIIllIlIllllIl).color(lIIIllIlIllIlll).endVertex();
        lIIIllIlllIllIl.pos(lIIIllIllIIlIll, lIIIllIllIIlIIl, lIIIllIllIIIlll).color(lIIIllIllIlIlIl).endVertex();
        lIIIllIlllIllIl.pos(lIIIllIlIllllll, lIIIllIllIllllI, lIIIllIlIllllIl).color(lIIIllIlIllIlll).endVertex();
        lIIIllIlllIllIl.pos(lIIIllIlIllllII, lIIIllIllIllIIl, lIIIllIllIlIlll).color(lIIIllIlIllIllI).endVertex();
    }

    public void line(double lIIIlIlIIllIllI, double lIIIlIlIIllIlIl, double lIIIlIlIIlIllIl, double lIIIlIlIIllIIll, Color lIIIlIlIIlIlIll, Color lIIIlIlIIlIlIlI) {
        MeshBuilder lIIIlIlIIllIIII;
        lIIIlIlIIllIIII.line(lIIIlIlIIllIllI, lIIIlIlIIllIlIl, 0.0, lIIIlIlIIlIllIl, lIIIlIlIIllIIll, 0.0, lIIIlIlIIlIlIll, lIIIlIlIIlIlIlI);
    }

    public void horizontalGradientQuad(double lIIIlIlllllllll, double lIIIlIllllllllI, double lIIIlIlllllllIl, double lIIIlIlllllIlIl, Color lIIIlIlllllIlII, Color lIIIlIlllllIIll) {
        MeshBuilder lIIIlIllllllIIl;
        lIIIlIllllllIIl.horizontalGradientQuad(lIIIlIlllllllll, lIIIlIllllllllI, 0.0, lIIIlIlllllllll + lIIIlIlllllllIl, lIIIlIllllllllI, 0.0, lIIIlIlllllllll + lIIIlIlllllllIl, lIIIlIllllllllI + lIIIlIlllllIlIl, 0.0, lIIIlIlllllllll, lIIIlIllllllllI + lIIIlIlllllIlIl, 0.0, lIIIlIlllllIlII, lIIIlIlllllIIll);
    }

    public void quad(double lIIIlIllllIIIll, double lIIIlIlllIlIlII, double lIIIlIllllIIIIl, double lIIIlIllllIIIII, double lIIIlIlllIlIIIl, double lIIIlIlllIlIIII, double lIIIlIlllIlllIl, double lIIIlIlllIIlllI, double lIIIlIlllIIllIl, double lIIIlIlllIllIlI, double lIIIlIlllIIlIll, double lIIIlIlllIllIII, Color lIIIlIlllIlIlll) {
        MeshBuilder lIIIlIllllIIlII;
        lIIIlIllllIIlII.quad(lIIIlIllllIIIll, lIIIlIlllIlIlII, lIIIlIllllIIIIl, lIIIlIllllIIIII, lIIIlIlllIlIIIl, lIIIlIlllIlIIII, lIIIlIlllIlllIl, lIIIlIlllIIlllI, lIIIlIlllIIllIl, lIIIlIlllIllIlI, lIIIlIlllIIlIll, lIIIlIlllIllIII, lIIIlIlllIlIlll, lIIIlIlllIlIlll, lIIIlIlllIlIlll, lIIIlIlllIlIlll);
    }

    public void quad(double lIIIllIlIlIlIlI, double lIIIllIlIIllIlI, double lIIIllIlIIllIII, double lIIIllIlIlIIlII, Color lIIIllIlIIlIllI, Color lIIIllIlIIlIlIl, Color lIIIllIlIlIIIIl, Color lIIIllIlIIlIIlI) {
        MeshBuilder lIIIllIlIlIllII;
        lIIIllIlIlIllII.quad(lIIIllIlIlIlIlI, lIIIllIlIIllIlI, 0.0, lIIIllIlIlIlIlI + lIIIllIlIIllIII, lIIIllIlIIllIlI, 0.0, lIIIllIlIlIlIlI + lIIIllIlIIllIII, lIIIllIlIIllIlI + lIIIllIlIlIIlII, 0.0, lIIIllIlIlIlIlI, lIIIllIlIIllIlI + lIIIllIlIlIIlII, 0.0, lIIIllIlIIlIllI, lIIIllIlIIlIlIl, lIIIllIlIlIIIIl, lIIIllIlIIlIIlI);
    }

    public void horizontalGradientQuad(double lIIIllIIIIlIlIl, double lIIIllIIIIlIlII, double lIIIllIIIIlIIll, double lIIIllIIIlIIIIl, double lIIIllIIIIlIIIl, double lIIIllIIIIlllll, double lIIIllIIIIllllI, double lIIIllIIIIlllIl, double lIIIllIIIIIllIl, double lIIIllIIIIllIll, double lIIIllIIIIIlIll, double lIIIllIIIIIlIlI, Color lIIIllIIIIIlIIl, Color lIIIllIIIIIlIII) {
        MeshBuilder lIIIllIIIlIIlIl;
        lIIIllIIIlIIlIl.quad(lIIIllIIIIlIlIl, lIIIllIIIIlIlII, lIIIllIIIIlIIll, lIIIllIIIlIIIIl, lIIIllIIIIlIIIl, lIIIllIIIIlllll, lIIIllIIIIllllI, lIIIllIIIIlllIl, lIIIllIIIIIllIl, lIIIllIIIIllIll, lIIIllIIIIIlIll, lIIIllIIIIIlIlI, lIIIllIIIIIlIIl, lIIIllIIIIIlIII, lIIIllIIIIIlIII, lIIIllIIIIIlIIl);
    }

    public void horizontalQuad(double lIIIlIllIlIlllI, double lIIIlIllIlIllIl, double lIIIlIllIlIllII, double lIIIlIllIlIlIll, double lIIIlIllIlIlIlI, Color lIIIlIllIlIlIIl) {
        MeshBuilder lIIIlIllIlIlIII;
        lIIIlIllIlIlIII.quad(lIIIlIllIlIlllI, lIIIlIllIlIlIlI, lIIIlIllIlIllIl, lIIIlIllIlIlllI, lIIIlIllIlIlIlI, lIIIlIllIlIlIll, lIIIlIllIlIllII, lIIIlIllIlIlIlI, lIIIlIllIlIlIll, lIIIlIllIlIllII, lIIIlIllIlIlIlI, lIIIlIllIlIllIl, lIIIlIllIlIlIIl);
    }

    public void endVertex() {
        MeshBuilder lIIIlllIIIlIIlI;
        lIIIlllIIIlIIlI.buffer.method_1344();
    }

    public void texQuad(double lIIIlIllIIIIIIl, double lIIIlIlIllllIIl, double lIIIlIlIllllIII, double lIIIlIlIllllllI, TextureRegion lIIIlIlIlllIllI, Color lIIIlIlIlllllII) {
        MeshBuilder lIIIlIllIIIIIlI;
        lIIIlIllIIIIIlI.pos(lIIIlIllIIIIIIl, lIIIlIlIllllIIl, 0.0).color(lIIIlIlIlllllII).texture(lIIIlIlIlllIllI.x1, lIIIlIlIlllIllI.y1).endVertex();
        lIIIlIllIIIIIlI.pos(lIIIlIllIIIIIIl + lIIIlIlIllllIII, lIIIlIlIllllIIl, 0.0).color(lIIIlIlIlllllII).texture(lIIIlIlIlllIllI.x2, lIIIlIlIlllIllI.y1).endVertex();
        lIIIlIllIIIIIlI.pos(lIIIlIllIIIIIIl + lIIIlIlIllllIII, lIIIlIlIllllIIl + lIIIlIlIllllllI, 0.0).color(lIIIlIlIlllllII).texture(lIIIlIlIlllIllI.x2, lIIIlIlIlllIllI.y2).endVertex();
        lIIIlIllIIIIIlI.pos(lIIIlIllIIIIIIl, lIIIlIlIllllIIl, 0.0).color(lIIIlIlIlllllII).texture(lIIIlIlIlllIllI.x1, lIIIlIlIlllIllI.y1).endVertex();
        lIIIlIllIIIIIlI.pos(lIIIlIllIIIIIIl + lIIIlIlIllllIII, lIIIlIlIllllIIl + lIIIlIlIllllllI, 0.0).color(lIIIlIlIlllllII).texture(lIIIlIlIlllIllI.x2, lIIIlIlIlllIllI.y2).endVertex();
        lIIIlIllIIIIIlI.pos(lIIIlIllIIIIIIl, lIIIlIlIllllIIl + lIIIlIlIllllllI, 0.0).color(lIIIlIlIlllllII).texture(lIIIlIlIlllIllI.x1, lIIIlIlIlllIllI.y2).endVertex();
    }

    public MeshBuilder(int lIIIlllIllIllll) {
        MeshBuilder lIIIlllIlllIIII;
        lIIIlllIlllIIII.alpha = 1.0;
        lIIIlllIlllIIII.depthTest = false;
        lIIIlllIlllIIII.texture = false;
        lIIIlllIlllIIII.buffer = new class_287(lIIIlllIllIllll);
    }

    public MeshBuilder texture(double lIIIlllIIlIlIll, double lIIIlllIIlIllIl) {
        MeshBuilder lIIIlllIIlIllll;
        lIIIlllIIlIllll.buffer.method_22913((float)lIIIlllIIlIlIll, (float)lIIIlllIIlIllIl);
        return lIIIlllIIlIllll;
    }

    public void verticalGradientQuad(double lIIIllIIlIlllll, double lIIIllIIlIllllI, double lIIIllIIlllIIlI, double lIIIllIIlIllIlI, double lIIIllIIlIlIlll, double lIIIllIIlIlIllI, double lIIIllIIllIlIll, double lIIIllIIlIlIlII, double lIIIllIIlIlIIll, double lIIIllIIlIlIIIl, double lIIIllIIlIIllll, double lIIIllIIllIIlII, Color lIIIllIIlIIlIll, Color lIIIllIIlIIlIlI) {
        MeshBuilder lIIIllIIllIIIII;
        lIIIllIIllIIIII.quad(lIIIllIIlIlllll, lIIIllIIlIllllI, lIIIllIIlllIIlI, lIIIllIIlIllIlI, lIIIllIIlIlIlll, lIIIllIIlIlIllI, lIIIllIIllIlIll, lIIIllIIlIlIlII, lIIIllIIlIlIIll, lIIIllIIlIlIIIl, lIIIllIIlIIllll, lIIIllIIllIIlII, lIIIllIIlIIlIll, lIIIllIIlIIlIll, lIIIllIIlIIlIlI, lIIIllIIlIIlIlI);
    }

    public void line(double lIIIlIlIlIIllll, double lIIIlIlIlIIlllI, double lIIIlIlIlIIllIl, double lIIIlIlIlIIllII, double lIIIlIlIlIIIIlI, double lIIIlIlIlIIlIlI, Color lIIIlIlIlIIlIIl, Color lIIIlIlIlIIlIII) {
        MeshBuilder lIIIlIlIlIlIIII;
        lIIIlIlIlIlIIII.pos(lIIIlIlIlIIllll, lIIIlIlIlIIlllI, lIIIlIlIlIIllIl).color(lIIIlIlIlIIlIIl).endVertex();
        lIIIlIlIlIlIIII.pos(lIIIlIlIlIIllII, lIIIlIlIlIIIIlI, lIIIlIlIlIIlIlI).color(lIIIlIlIlIIlIII).endVertex();
    }

    public void line(double lIIIlIlIIIIlIlI, double lIIIlIlIIIIlIIl, double lIIIlIlIIIIlIII, double lIIIlIlIIIIIlll, Color lIIIlIlIIIIIIII) {
        MeshBuilder lIIIlIlIIIIlIll;
        lIIIlIlIIIIlIll.line(lIIIlIlIIIIlIlI, lIIIlIlIIIIlIIl, 0.0, lIIIlIlIIIIlIII, lIIIlIlIIIIIlll, 0.0, lIIIlIlIIIIIIII);
    }

    public void boxEdges(double lIIIlIIllllIlIl, double lIIIlIIllllIlII, double lIIIlIIlllIlIlI, double lIIIlIIllllIIlI, double lIIIlIIllllIIIl, double lIIIlIIllllIIII, Color lIIIlIIlllIllll, int lIIIlIIlllIIlIl) {
        MeshBuilder lIIIlIIllllIllI;
        if (Dir.is(lIIIlIIlllIIlIl, (byte)32) && Dir.is(lIIIlIIlllIIlIl, (byte)8)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIlIl, lIIIlIIllllIlII, lIIIlIIlllIlIlI, lIIIlIIllllIlIl, lIIIlIIllllIIIl, lIIIlIIlllIlIlI, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)32) && Dir.is(lIIIlIIlllIIlIl, (byte)16)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIlIl, lIIIlIIllllIlII, lIIIlIIllllIIII, lIIIlIIllllIlIl, lIIIlIIllllIIIl, lIIIlIIllllIIII, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)64) && Dir.is(lIIIlIIlllIIlIl, (byte)8)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIIlI, lIIIlIIllllIlII, lIIIlIIlllIlIlI, lIIIlIIllllIIlI, lIIIlIIllllIIIl, lIIIlIIlllIlIlI, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)64) && Dir.is(lIIIlIIlllIIlIl, (byte)16)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIIlI, lIIIlIIllllIlII, lIIIlIIllllIIII, lIIIlIIllllIIlI, lIIIlIIllllIIIl, lIIIlIIllllIIII, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)8)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIlIl, lIIIlIIllllIlII, lIIIlIIlllIlIlI, lIIIlIIllllIIlI, lIIIlIIllllIlII, lIIIlIIlllIlIlI, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)8)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIlIl, lIIIlIIllllIIIl, lIIIlIIlllIlIlI, lIIIlIIllllIIlI, lIIIlIIllllIIIl, lIIIlIIlllIlIlI, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)16)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIlIl, lIIIlIIllllIlII, lIIIlIIllllIIII, lIIIlIIllllIIlI, lIIIlIIllllIlII, lIIIlIIllllIIII, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)16)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIlIl, lIIIlIIllllIIIl, lIIIlIIllllIIII, lIIIlIIllllIIlI, lIIIlIIllllIIIl, lIIIlIIllllIIII, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)32)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIlIl, lIIIlIIllllIlII, lIIIlIIlllIlIlI, lIIIlIIllllIlIl, lIIIlIIllllIlII, lIIIlIIllllIIII, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)32)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIlIl, lIIIlIIllllIIIl, lIIIlIIlllIlIlI, lIIIlIIllllIlIl, lIIIlIIllllIIIl, lIIIlIIllllIIII, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)64)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIIlI, lIIIlIIllllIlII, lIIIlIIlllIlIlI, lIIIlIIllllIIlI, lIIIlIIllllIlII, lIIIlIIllllIIII, lIIIlIIlllIllll);
        }
        if (Dir.is(lIIIlIIlllIIlIl, (byte)64)) {
            lIIIlIIllllIllI.line(lIIIlIIllllIIlI, lIIIlIIllllIIIl, lIIIlIIlllIlIlI, lIIIlIIllllIIlI, lIIIlIIllllIIIl, lIIIlIIllllIIII, lIIIlIIlllIllll);
        }
    }

    public void end() {
        MeshBuilder lIIIlllIlIIIlIl;
        lIIIlllIlIIIlIl.buffer.method_1326();
        GL11.glPushMatrix();
        RenderSystem.multMatrix((class_1159)Matrices.getTop());
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((class_4493.class_4535)class_4493.class_4535.field_22541, (class_4493.class_4534)class_4493.class_4534.field_22523);
        if (lIIIlllIlIIIlIl.depthTest) {
            RenderSystem.enableDepthTest();
        } else {
            RenderSystem.disableDepthTest();
        }
        RenderSystem.disableAlphaTest();
        if (lIIIlllIlIIIlIl.texture) {
            RenderSystem.enableTexture();
        } else {
            RenderSystem.disableTexture();
        }
        RenderSystem.disableLighting();
        RenderSystem.disableCull();
        GL11.glEnable((int)2848);
        RenderSystem.lineWidth((float)1.0f);
        RenderSystem.color4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        class_4493.method_22083((int)7425);
        class_286.method_1309((class_287)lIIIlllIlIIIlIl.buffer);
        RenderSystem.enableAlphaTest();
        RenderSystem.enableDepthTest();
        RenderSystem.enableTexture();
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public void boxSides(double lIIIlIlIllIlIlI, double lIIIlIlIllIlIIl, double lIIIlIlIllIlIII, double lIIIlIlIlIllllI, double lIIIlIlIlIlllIl, double lIIIlIlIllIIlIl, Color lIIIlIlIlIllIll, int lIIIlIlIllIIIll) {
        MeshBuilder lIIIlIlIllIIIlI;
        if (Dir.is(lIIIlIlIllIIIll, (byte)4)) {
            lIIIlIlIllIIIlI.quad(lIIIlIlIllIlIlI, lIIIlIlIllIlIIl, lIIIlIlIllIlIII, lIIIlIlIllIlIlI, lIIIlIlIllIlIIl, lIIIlIlIllIIlIl, lIIIlIlIlIllllI, lIIIlIlIllIlIIl, lIIIlIlIllIIlIl, lIIIlIlIlIllllI, lIIIlIlIllIlIIl, lIIIlIlIllIlIII, lIIIlIlIlIllIll);
        }
        if (Dir.is(lIIIlIlIllIIIll, (byte)2)) {
            lIIIlIlIllIIIlI.quad(lIIIlIlIllIlIlI, lIIIlIlIlIlllIl, lIIIlIlIllIlIII, lIIIlIlIllIlIlI, lIIIlIlIlIlllIl, lIIIlIlIllIIlIl, lIIIlIlIlIllllI, lIIIlIlIlIlllIl, lIIIlIlIllIIlIl, lIIIlIlIlIllllI, lIIIlIlIlIlllIl, lIIIlIlIllIlIII, lIIIlIlIlIllIll);
        }
        if (Dir.is(lIIIlIlIllIIIll, (byte)8)) {
            lIIIlIlIllIIIlI.quad(lIIIlIlIllIlIlI, lIIIlIlIllIlIIl, lIIIlIlIllIlIII, lIIIlIlIllIlIlI, lIIIlIlIlIlllIl, lIIIlIlIllIlIII, lIIIlIlIlIllllI, lIIIlIlIlIlllIl, lIIIlIlIllIlIII, lIIIlIlIlIllllI, lIIIlIlIllIlIIl, lIIIlIlIllIlIII, lIIIlIlIlIllIll);
        }
        if (Dir.is(lIIIlIlIllIIIll, (byte)16)) {
            lIIIlIlIllIIIlI.quad(lIIIlIlIllIlIlI, lIIIlIlIllIlIIl, lIIIlIlIllIIlIl, lIIIlIlIllIlIlI, lIIIlIlIlIlllIl, lIIIlIlIllIIlIl, lIIIlIlIlIllllI, lIIIlIlIlIlllIl, lIIIlIlIllIIlIl, lIIIlIlIlIllllI, lIIIlIlIllIlIIl, lIIIlIlIllIIlIl, lIIIlIlIlIllIll);
        }
        if (Dir.is(lIIIlIlIllIIIll, (byte)32)) {
            lIIIlIlIllIIIlI.quad(lIIIlIlIllIlIlI, lIIIlIlIllIlIIl, lIIIlIlIllIlIII, lIIIlIlIllIlIlI, lIIIlIlIlIlllIl, lIIIlIlIllIlIII, lIIIlIlIllIlIlI, lIIIlIlIlIlllIl, lIIIlIlIllIIlIl, lIIIlIlIllIlIlI, lIIIlIlIllIlIIl, lIIIlIlIllIIlIl, lIIIlIlIlIllIll);
        }
        if (Dir.is(lIIIlIlIllIIIll, (byte)64)) {
            lIIIlIlIllIIIlI.quad(lIIIlIlIlIllllI, lIIIlIlIllIlIIl, lIIIlIlIllIlIII, lIIIlIlIlIllllI, lIIIlIlIlIlllIl, lIIIlIlIllIlIII, lIIIlIlIlIllllI, lIIIlIlIlIlllIl, lIIIlIlIllIIlIl, lIIIlIlIlIllllI, lIIIlIlIllIlIIl, lIIIlIlIllIIlIl, lIIIlIlIlIllIll);
        }
    }
}

