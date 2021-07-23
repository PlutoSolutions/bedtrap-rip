/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.renderer.GuiRenderOperation;
import minegame159.meteorclient.gui.renderer.Scissor;
import minegame159.meteorclient.gui.renderer.operations.TextOperation;
import minegame159.meteorclient.gui.renderer.packer.GuiTexture;
import minegame159.meteorclient.gui.renderer.packer.TexturePacker;
import minegame159.meteorclient.gui.renderer.packer.TextureRegion;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.MeshBuilder;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.render.ByteTexture;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1044;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import org.lwjgl.opengl.GL11;

public class GuiRenderer {
    public WWidget tooltipWidget;
    public static GuiTexture RESET;
    public static GuiTexture TRIANGLE;
    private static ByteTexture TEXTURE;
    private final MeshBuilder mb = new MeshBuilder();
    public static GuiTexture CIRCLE;
    private final Pool<TextOperation> textPool;
    public GuiTheme theme;
    public String tooltip;
    private final MeshBuilder mbTex = new MeshBuilder();
    private final List<Runnable> postTasks;
    public String lastTooltip;
    private static final TexturePacker TEXTURE_PACKER;
    public static GuiTexture EDIT;
    private double tooltipAnimProgress;
    private static final Color WHITE;
    private final List<TextOperation> texts;
    private final Pool<Scissor> scissorPool = new Pool<Scissor>(Scissor::new);
    private final Stack<Scissor> scissorStack = new Stack();

    public void quad(double d, double d2, double d3, double d4, Color color) {
        this.quad(d, d2, d3, d4, color, color);
    }

    public void quad(double d, double d2, double d3, double d4, Color color, Color color2, Color color3, Color color4) {
        this.mb.quad(d, d2, d3, d4, color, color2, color3, color4);
    }

    public void scissorEnd() {
        Scissor scissor = this.scissorStack.pop();
        scissor.apply();
        this.endRender();
        for (Runnable runnable : scissor.postTasks) {
            runnable.run();
        }
        if (!this.scissorStack.isEmpty()) {
            this.beginRender();
        }
        this.scissorPool.free(scissor);
    }

    public void text(String string, double d, double d2, Color color, boolean bl) {
        this.texts.add(this.getOp(this.textPool, d, d2, color).set(string, this.theme.textRenderer(), bl));
    }

    public void tooltip(String string) {
        this.tooltip = string;
    }

    private void lambda$texture$0(double d, double d2, double d3, double d4, class_1044 class_10442, double d5) {
        this.mbTex.begin(null, DrawMode.Triangles, class_290.field_20887);
        this.mbTex.pos(d, d2, 0.0).color(WHITE).texture(0.0, 0.0).endVertex();
        this.mbTex.pos(d + d3, d2, 0.0).color(WHITE).texture(1.0, 0.0).endVertex();
        this.mbTex.pos(d + d3, d2 + d4, 0.0).color(WHITE).texture(1.0, 1.0).endVertex();
        this.mbTex.pos(d, d2, 0.0).color(WHITE).texture(0.0, 0.0).endVertex();
        this.mbTex.pos(d + d3, d2 + d4, 0.0).color(WHITE).texture(1.0, 1.0).endVertex();
        this.mbTex.pos(d, d2 + d4, 0.0).color(WHITE).texture(0.0, 1.0).endVertex();
        class_10442.method_23207();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(d + d3 / 2.0), (double)(d2 + d4 / 2.0), (double)0.0);
        GL11.glRotated((double)d5, (double)0.0, (double)0.0, (double)1.0);
        GL11.glTranslated((double)(-d - d3 / 2.0), (double)(-d2 - d4 / 2.0), (double)0.0);
        this.mbTex.end();
        GL11.glPopMatrix();
    }

    public void absolutePost(Runnable runnable) {
        this.postTasks.add(runnable);
    }

    public static void init() {
        CIRCLE = GuiRenderer.addTexture(new class_2960("meteor-client", "textures/icons/gui/circle.png"));
        TRIANGLE = GuiRenderer.addTexture(new class_2960("meteor-client", "textures/icons/gui/triangle.png"));
        EDIT = GuiRenderer.addTexture(new class_2960("meteor-client", "textures/icons/gui/edit.png"));
        RESET = GuiRenderer.addTexture(new class_2960("meteor-client", "textures/icons/gui/reset.png"));
        TEXTURE = TEXTURE_PACKER.pack();
    }

    public void setAlpha(double d) {
        this.mb.alpha = d;
        this.mbTex.alpha = d;
        this.theme.textRenderer().setAlpha(d);
    }

    private <T extends GuiRenderOperation<T>> T getOp(Pool<T> pool, double d, double d2, Color color) {
        GuiRenderOperation guiRenderOperation = (GuiRenderOperation)pool.get();
        guiRenderOperation.set(d, d2, color);
        return (T)guiRenderOperation;
    }

    public void scissorStart(double d, double d2, double d3, double d4) {
        if (!this.scissorStack.isEmpty()) {
            Scissor scissor = this.scissorStack.peek();
            if (d < (double)scissor.x) {
                d = scissor.x;
            } else if (d + d3 > (double)(scissor.x + scissor.width)) {
                d3 -= d + d3 - (double)(scissor.x + scissor.width);
            }
            if (d2 < (double)scissor.y) {
                d2 = scissor.y;
            } else if (d2 + d4 > (double)(scissor.y + scissor.height)) {
                d4 -= d2 + d4 - (double)(scissor.y + scissor.height);
            }
            scissor.apply();
            this.endRender();
        }
        this.scissorStack.push(this.scissorPool.get().set(d, d2, d3, d4));
        this.beginRender();
    }

    public void quad(double d, double d2, double d3, double d4, GuiTexture guiTexture, Color color) {
        this.mbTex.texQuad(d, d2, d3, d4, guiTexture.get(d3, d4), color);
    }

    public boolean renderTooltip(double d, double d2, double d3) {
        this.tooltipAnimProgress += (double)(this.tooltip != null ? 1 : -1) * d3 * 14.0;
        this.tooltipAnimProgress = Utils.clamp(this.tooltipAnimProgress, 0.0, 1.0);
        boolean bl = false;
        if (this.tooltipAnimProgress > 0.0) {
            if (this.tooltip != null && !this.tooltip.equals(this.lastTooltip)) {
                this.tooltipWidget = this.theme.tooltip(this.tooltip);
                this.tooltipWidget.init();
            }
            this.tooltipWidget.move(-this.tooltipWidget.x + d + 12.0, -this.tooltipWidget.y + d2 + 12.0);
            this.setAlpha(this.tooltipAnimProgress);
            this.begin();
            this.tooltipWidget.render(this, d, d2, d3);
            this.end();
            this.setAlpha(1.0);
            this.lastTooltip = this.tooltip;
            bl = true;
        }
        this.tooltip = null;
        return bl;
    }

    public void quad(double d, double d2, double d3, double d4, Color color, Color color2) {
        this.quad(d, d2, d3, d4, color, color2, color2, color);
    }

    public void texture(double d, double d2, double d3, double d4, double d5, class_1044 class_10442) {
        this.post(() -> this.lambda$texture$0(d, d2, d3, d4, class_10442, d5));
    }

    public void quad(WWidget wWidget, Color color) {
        this.quad(wWidget.x, wWidget.y, wWidget.width, wWidget.height, color);
    }

    public void end() {
        this.scissorEnd();
        for (Runnable runnable : this.postTasks) {
            runnable.run();
        }
        this.postTasks.clear();
        GL11.glDisable((int)3089);
    }

    public void begin() {
        GL11.glEnable((int)3089);
        this.scissorStart(0.0, 0.0, Utils.getWindowWidth(), Utils.getWindowHeight());
    }

    public static GuiTexture addTexture(class_2960 class_29602) {
        return TEXTURE_PACKER.add(class_29602);
    }

    public void rotatedQuad(double d, double d2, double d3, double d4, double d5, GuiTexture guiTexture, Color color) {
        TextureRegion textureRegion = guiTexture.get(d3, d4);
        double d6 = Math.toRadians(d5);
        double d7 = Math.cos(d6);
        double d8 = Math.sin(d6);
        double d9 = d + d3 / 2.0;
        double d10 = d2 + d4 / 2.0;
        double d11 = (d - d9) * d7 - (d2 - d10) * d8 + d9;
        double d12 = (d2 - d10) * d7 + (d - d9) * d8 + d10;
        this.mbTex.pos(d11, d12, 0.0).color(color).texture(textureRegion.x1, textureRegion.y1).endVertex();
        double d13 = (d + d3 - d9) * d7 - (d2 - d10) * d8 + d9;
        double d14 = (d2 - d10) * d7 + (d + d3 - d9) * d8 + d10;
        this.mbTex.pos(d13, d14, 0.0).color(color).texture(textureRegion.x2, textureRegion.y1).endVertex();
        double d15 = (d + d3 - d9) * d7 - (d2 + d4 - d10) * d8 + d9;
        double d16 = (d2 + d4 - d10) * d7 + (d + d3 - d9) * d8 + d10;
        this.mbTex.pos(d15, d16, 0.0).color(color).texture(textureRegion.x2, textureRegion.y2).endVertex();
        this.mbTex.pos(d11, d12, 0.0).color(color).texture(textureRegion.x1, textureRegion.y1).endVertex();
        this.mbTex.pos(d15, d16, 0.0).color(color).texture(textureRegion.x2, textureRegion.y2).endVertex();
        d13 = (d - d9) * d7 - (d2 + d4 - d10) * d8 + d9;
        d14 = (d2 + d4 - d10) * d7 + (d - d9) * d8 + d10;
        this.mbTex.pos(d13, d14, 0.0).color(color).texture(textureRegion.x1, textureRegion.y2).endVertex();
    }

    public void post(Runnable runnable) {
        this.scissorStack.peek().postTasks.add(runnable);
    }

    static {
        WHITE = new Color(255, 255, 255);
        TEXTURE_PACKER = new TexturePacker();
    }

    public GuiRenderer() {
        this.textPool = new Pool<TextOperation>(TextOperation::new);
        this.texts = new ArrayList<TextOperation>();
        this.postTasks = new ArrayList<Runnable>();
    }

    private void endRender() {
        this.mb.end();
        TEXTURE.method_23207();
        this.mbTex.texture = true;
        this.mbTex.end();
        this.theme.textRenderer().begin(this.theme.scale(1.0));
        for (TextOperation textOperation : this.texts) {
            if (textOperation.title) continue;
            textOperation.run(this.textPool);
        }
        this.theme.textRenderer().end();
        this.theme.textRenderer().begin(this.theme.scale(1.25));
        for (TextOperation textOperation : this.texts) {
            if (!textOperation.title) continue;
            textOperation.run(this.textPool);
        }
        this.theme.textRenderer().end();
        this.texts.clear();
    }

    private void beginRender() {
        this.mb.begin(null, DrawMode.Triangles, class_290.field_1576);
        this.mbTex.begin(null, DrawMode.Triangles, class_290.field_20887);
    }
}

