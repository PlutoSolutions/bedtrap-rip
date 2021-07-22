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
    private double offsetZ;
    private double offsetX;
    public double alpha = 1.0;
    public boolean depthTest = false;
    private int count;
    public boolean texture = false;
    private final class_287 buffer;
    private double offsetY;

    public MeshBuilder pos(double d, double d2, double d3) {
        this.buffer.method_22912(d + this.offsetX, d2 + this.offsetY, d3 + this.offsetZ);
        return this;
    }

    public MeshBuilder color(int n) {
        this.buffer.method_22915((float)Color.toRGBAR(n) / 255.0f, (float)Color.toRGBAG(n) / 255.0f, (float)Color.toRGBAB(n) / 255.0f, (float)Color.toRGBAA(n) / 255.0f * (float)this.alpha);
        return this;
    }

    public void verticalQuad(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        this.quad(d, d2, d3, d, d5, d3, d4, d5, d6, d4, d2, d6, color);
    }

    public void boxEdges(double d, double d2, double d3, double d4, Color color) {
        this.boxEdges(d, d2, 0.0, d + d3, d2 + d4, 0.0, color, 0);
    }

    public MeshBuilder color(Color color) {
        this.buffer.method_22915((float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f * (float)this.alpha);
        return this;
    }

    public void line(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        this.line(d, d2, d3, d4, d5, d6, color, color);
    }

    public void begin(RenderEvent renderEvent, DrawMode drawMode, class_293 class_2932) {
        if (renderEvent != null) {
            this.offsetX = -renderEvent.offsetX;
            this.offsetY = -renderEvent.offsetY;
            this.offsetZ = -renderEvent.offsetZ;
        } else {
            this.offsetX = 0.0;
            this.offsetY = 0.0;
            this.offsetZ = 0.0;
        }
        this.buffer.method_1328(drawMode.toOpenGl(), class_2932);
        this.count = 0;
    }

    public MeshBuilder() {
        this.buffer = new class_287(0x200000);
    }

    public void verticalGradientQuad(double d, double d2, double d3, double d4, Color color, Color color2) {
        this.verticalGradientQuad(d, d2, 0.0, d + d3, d2, 0.0, d + d3, d2 + d4, 0.0, d, d2 + d4, 0.0, color, color2);
    }

    public void quad(double d, double d2, double d3, double d4, Color color) {
        this.quad(d, d2, 0.0, d + d3, d2, 0.0, d + d3, d2 + d4, 0.0, d, d2 + d4, 0.0, color);
    }

    public boolean isBuilding() {
        return this.buffer.method_22893();
    }

    public void quad(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, Color color, Color color2, Color color3, Color color4) {
        this.pos(d, d2, d3).color(color).endVertex();
        this.pos(d4, d5, d6).color(color2).endVertex();
        this.pos(d7, d8, d9).color(color3).endVertex();
        this.pos(d, d2, d3).color(color).endVertex();
        this.pos(d7, d8, d9).color(color3).endVertex();
        this.pos(d10, d11, d12).color(color4).endVertex();
    }

    public void line(double d, double d2, double d3, double d4, Color color, Color color2) {
        this.line(d, d2, 0.0, d3, d4, 0.0, color, color2);
    }

    public void horizontalGradientQuad(double d, double d2, double d3, double d4, Color color, Color color2) {
        this.horizontalGradientQuad(d, d2, 0.0, d + d3, d2, 0.0, d + d3, d2 + d4, 0.0, d, d2 + d4, 0.0, color, color2);
    }

    public void quad(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, Color color) {
        this.quad(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, color, color, color, color);
    }

    public void quad(double d, double d2, double d3, double d4, Color color, Color color2, Color color3, Color color4) {
        this.quad(d, d2, 0.0, d + d3, d2, 0.0, d + d3, d2 + d4, 0.0, d, d2 + d4, 0.0, color, color2, color3, color4);
    }

    public void horizontalGradientQuad(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, Color color, Color color2) {
        this.quad(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, color, color2, color2, color);
    }

    public void horizontalQuad(double d, double d2, double d3, double d4, double d5, Color color) {
        this.quad(d, d5, d2, d, d5, d4, d3, d5, d4, d3, d5, d2, color);
    }

    public void endVertex() {
        this.buffer.method_1344();
    }

    public void texQuad(double d, double d2, double d3, double d4, TextureRegion textureRegion, Color color) {
        this.pos(d, d2, 0.0).color(color).texture(textureRegion.x1, textureRegion.y1).endVertex();
        this.pos(d + d3, d2, 0.0).color(color).texture(textureRegion.x2, textureRegion.y1).endVertex();
        this.pos(d + d3, d2 + d4, 0.0).color(color).texture(textureRegion.x2, textureRegion.y2).endVertex();
        this.pos(d, d2, 0.0).color(color).texture(textureRegion.x1, textureRegion.y1).endVertex();
        this.pos(d + d3, d2 + d4, 0.0).color(color).texture(textureRegion.x2, textureRegion.y2).endVertex();
        this.pos(d, d2 + d4, 0.0).color(color).texture(textureRegion.x1, textureRegion.y2).endVertex();
    }

    public MeshBuilder(int n) {
        this.buffer = new class_287(n);
    }

    public MeshBuilder texture(double d, double d2) {
        this.buffer.method_22913((float)d, (float)d2);
        return this;
    }

    public void verticalGradientQuad(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, Color color, Color color2) {
        this.quad(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, color, color, color2, color2);
    }

    public void line(double d, double d2, double d3, double d4, double d5, double d6, Color color, Color color2) {
        this.pos(d, d2, d3).color(color).endVertex();
        this.pos(d4, d5, d6).color(color2).endVertex();
    }

    public void line(double d, double d2, double d3, double d4, Color color) {
        this.line(d, d2, 0.0, d3, d4, 0.0, color);
    }

    public void boxEdges(double d, double d2, double d3, double d4, double d5, double d6, Color color, int n) {
        if (Dir.is(n, (byte)32) && Dir.is(n, (byte)8)) {
            this.line(d, d2, d3, d, d5, d3, color);
        }
        if (Dir.is(n, (byte)32) && Dir.is(n, (byte)16)) {
            this.line(d, d2, d6, d, d5, d6, color);
        }
        if (Dir.is(n, (byte)64) && Dir.is(n, (byte)8)) {
            this.line(d4, d2, d3, d4, d5, d3, color);
        }
        if (Dir.is(n, (byte)64) && Dir.is(n, (byte)16)) {
            this.line(d4, d2, d6, d4, d5, d6, color);
        }
        if (Dir.is(n, (byte)8)) {
            this.line(d, d2, d3, d4, d2, d3, color);
        }
        if (Dir.is(n, (byte)8)) {
            this.line(d, d5, d3, d4, d5, d3, color);
        }
        if (Dir.is(n, (byte)16)) {
            this.line(d, d2, d6, d4, d2, d6, color);
        }
        if (Dir.is(n, (byte)16)) {
            this.line(d, d5, d6, d4, d5, d6, color);
        }
        if (Dir.is(n, (byte)32)) {
            this.line(d, d2, d3, d, d2, d6, color);
        }
        if (Dir.is(n, (byte)32)) {
            this.line(d, d5, d3, d, d5, d6, color);
        }
        if (Dir.is(n, (byte)64)) {
            this.line(d4, d2, d3, d4, d2, d6, color);
        }
        if (Dir.is(n, (byte)64)) {
            this.line(d4, d5, d3, d4, d5, d6, color);
        }
    }

    public void end() {
        this.buffer.method_1326();
        GL11.glPushMatrix();
        RenderSystem.multMatrix((class_1159)Matrices.getTop());
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((class_4493.class_4535)class_4493.class_4535.field_22541, (class_4493.class_4534)class_4493.class_4534.field_22523);
        if (this.depthTest) {
            RenderSystem.enableDepthTest();
        } else {
            RenderSystem.disableDepthTest();
        }
        RenderSystem.disableAlphaTest();
        if (this.texture) {
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
        class_286.method_1309((class_287)this.buffer);
        RenderSystem.enableAlphaTest();
        RenderSystem.enableDepthTest();
        RenderSystem.enableTexture();
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public void boxSides(double d, double d2, double d3, double d4, double d5, double d6, Color color, int n) {
        if (Dir.is(n, (byte)4)) {
            this.quad(d, d2, d3, d, d2, d6, d4, d2, d6, d4, d2, d3, color);
        }
        if (Dir.is(n, (byte)2)) {
            this.quad(d, d5, d3, d, d5, d6, d4, d5, d6, d4, d5, d3, color);
        }
        if (Dir.is(n, (byte)8)) {
            this.quad(d, d2, d3, d, d5, d3, d4, d5, d3, d4, d2, d3, color);
        }
        if (Dir.is(n, (byte)16)) {
            this.quad(d, d2, d6, d, d5, d6, d4, d5, d6, d4, d2, d6, color);
        }
        if (Dir.is(n, (byte)32)) {
            this.quad(d, d2, d3, d, d5, d3, d, d5, d6, d, d2, d6, color);
        }
        if (Dir.is(n, (byte)64)) {
            this.quad(d4, d2, d3, d4, d5, d3, d4, d5, d6, d4, d2, d6, color);
        }
    }
}

