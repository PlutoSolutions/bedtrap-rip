/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1044
 *  net.minecraft.class_290
 *  net.minecraft.class_2960
 *  org.lwjgl.opengl.GL11
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
    public /* synthetic */ WWidget tooltipWidget;
    public static /* synthetic */ GuiTexture RESET;
    public static /* synthetic */ GuiTexture TRIANGLE;
    private static /* synthetic */ ByteTexture TEXTURE;
    private final /* synthetic */ MeshBuilder mb;
    public static /* synthetic */ GuiTexture CIRCLE;
    private final /* synthetic */ Pool<TextOperation> textPool;
    public /* synthetic */ GuiTheme theme;
    public /* synthetic */ String tooltip;
    private final /* synthetic */ MeshBuilder mbTex;
    private final /* synthetic */ List<Runnable> postTasks;
    public /* synthetic */ String lastTooltip;
    private static final /* synthetic */ TexturePacker TEXTURE_PACKER;
    public static /* synthetic */ GuiTexture EDIT;
    private /* synthetic */ double tooltipAnimProgress;
    private static final /* synthetic */ Color WHITE;
    private final /* synthetic */ List<TextOperation> texts;
    private final /* synthetic */ Pool<Scissor> scissorPool;
    private final /* synthetic */ Stack<Scissor> scissorStack;

    public void quad(double lIllllllIIIIlII, double lIllllllIIIlIIl, double lIllllllIIIlIII, double lIllllllIIIIIIl, Color lIllllllIIIIIII) {
        GuiRenderer lIllllllIIIIlIl;
        lIllllllIIIIlIl.quad(lIllllllIIIIlII, lIllllllIIIlIIl, lIllllllIIIlIII, lIllllllIIIIIIl, lIllllllIIIIIII, lIllllllIIIIIII);
    }

    public void quad(double lIllllllIlIlllI, double lIllllllIllIllI, double lIllllllIlIllII, double lIllllllIlIlIll, Color lIllllllIllIIll, Color lIllllllIllIIlI, Color lIllllllIlIlIII, Color lIllllllIlIIlll) {
        GuiRenderer lIllllllIlIllll;
        lIllllllIlIllll.mb.quad(lIllllllIlIlllI, lIllllllIllIllI, lIllllllIlIllII, lIllllllIlIlIll, lIllllllIllIIll, lIllllllIllIIlI, lIllllllIlIlIII, lIllllllIlIIlll);
    }

    public void scissorEnd() {
        GuiRenderer lIllllllllIIIlI;
        Scissor lIllllllllIIIIl = lIllllllllIIIlI.scissorStack.pop();
        lIllllllllIIIIl.apply();
        lIllllllllIIIlI.endRender();
        for (Runnable lIllllllllIIIll : lIllllllllIIIIl.postTasks) {
            lIllllllllIIIll.run();
        }
        if (!lIllllllllIIIlI.scissorStack.isEmpty()) {
            lIllllllllIIIlI.beginRender();
        }
        lIllllllllIIIlI.scissorPool.free(lIllllllllIIIIl);
    }

    public void text(String lIlllllIIIllIII, double lIlllllIIIlIlll, double lIlllllIIIlIllI, Color lIlllllIIIlIlIl, boolean lIlllllIIIlIlII) {
        GuiRenderer lIlllllIIIlllll;
        lIlllllIIIlllll.texts.add(lIlllllIIIlllll.getOp(lIlllllIIIlllll.textPool, lIlllllIIIlIlll, lIlllllIIIlIllI, lIlllllIIIlIlIl).set(lIlllllIIIllIII, lIlllllIIIlllll.theme.textRenderer(), lIlllllIIIlIlII));
    }

    public void tooltip(String lIlllllllIIIlII) {
        lIlllllllIIIlIl.tooltip = lIlllllllIIIlII;
    }

    public void absolutePost(Runnable lIllllIllllIIll) {
        GuiRenderer lIllllIllllIlII;
        lIllllIllllIlII.postTasks.add(lIllllIllllIIll);
    }

    public static void init() {
        CIRCLE = GuiRenderer.addTexture(new class_2960("meteor-client", "textures/icons/gui/circle.png"));
        TRIANGLE = GuiRenderer.addTexture(new class_2960("meteor-client", "textures/icons/gui/triangle.png"));
        EDIT = GuiRenderer.addTexture(new class_2960("meteor-client", "textures/icons/gui/edit.png"));
        RESET = GuiRenderer.addTexture(new class_2960("meteor-client", "textures/icons/gui/reset.png"));
        TEXTURE = TEXTURE_PACKER.pack();
    }

    public void setAlpha(double lIlllllllIIlIlI) {
        GuiRenderer lIlllllllIIlIIl;
        lIlllllllIIlIIl.mb.alpha = lIlllllllIIlIlI;
        lIlllllllIIlIIl.mbTex.alpha = lIlllllllIIlIlI;
        lIlllllllIIlIIl.theme.textRenderer().setAlpha(lIlllllllIIlIlI);
    }

    private <T extends GuiRenderOperation<T>> T getOp(Pool<T> lIllllIlllIIlll, double lIllllIlllIIllI, double lIllllIlllIlIlI, Color lIllllIlllIIlII) {
        GuiRenderOperation lIllllIlllIlIII = (GuiRenderOperation)lIllllIlllIIlll.get();
        lIllllIlllIlIII.set(lIllllIlllIIllI, lIllllIlllIlIlI, lIllllIlllIIlII);
        return (T)lIllllIlllIlIII;
    }

    public void scissorStart(double lIlllllllllIIIl, double lIllllllllIlIll, double lIllllllllIllll, double lIllllllllIlIIl) {
        GuiRenderer lIlllllllllIIlI;
        if (!lIlllllllllIIlI.scissorStack.isEmpty()) {
            Scissor lIlllllllllIIll = lIlllllllllIIlI.scissorStack.peek();
            if (lIlllllllllIIIl < (double)lIlllllllllIIll.x) {
                lIlllllllllIIIl = lIlllllllllIIll.x;
            } else if (lIlllllllllIIIl + lIllllllllIllll > (double)(lIlllllllllIIll.x + lIlllllllllIIll.width)) {
                lIllllllllIllll -= lIlllllllllIIIl + lIllllllllIllll - (double)(lIlllllllllIIll.x + lIlllllllllIIll.width);
            }
            if (lIllllllllIlIll < (double)lIlllllllllIIll.y) {
                lIllllllllIlIll = lIlllllllllIIll.y;
            } else if (lIllllllllIlIll + lIllllllllIlIIl > (double)(lIlllllllllIIll.y + lIlllllllllIIll.height)) {
                lIllllllllIlIIl -= lIllllllllIlIll + lIllllllllIlIIl - (double)(lIlllllllllIIll.y + lIlllllllllIIll.height);
            }
            lIlllllllllIIll.apply();
            lIlllllllllIIlI.endRender();
        }
        lIlllllllllIIlI.scissorStack.push(lIlllllllllIIlI.scissorPool.get().set(lIlllllllllIIIl, lIllllllllIlIll, lIllllllllIllll, lIllllllllIlIIl));
        lIlllllllllIIlI.beginRender();
    }

    public void quad(double lIlllllIllIIlll, double lIlllllIllIllIl, double lIlllllIllIIlIl, double lIlllllIllIIlII, GuiTexture lIlllllIllIlIlI, Color lIlllllIllIlIIl) {
        GuiRenderer lIlllllIllIlIII;
        lIlllllIllIlIII.mbTex.texQuad(lIlllllIllIIlll, lIlllllIllIllIl, lIlllllIllIIlIl, lIlllllIllIIlII, lIlllllIllIlIlI.get(lIlllllIllIIlIl, lIlllllIllIIlII), lIlllllIllIlIIl);
    }

    public boolean renderTooltip(double lIlllllllIlIIIl, double lIlllllllIlIIII, double lIlllllllIlIlII) {
        GuiRenderer lIlllllllIlIIlI;
        lIlllllllIlIIlI.tooltipAnimProgress += (double)(lIlllllllIlIIlI.tooltip != null ? 1 : -1) * lIlllllllIlIlII * 14.0;
        lIlllllllIlIIlI.tooltipAnimProgress = Utils.clamp(lIlllllllIlIIlI.tooltipAnimProgress, 0.0, 1.0);
        boolean lIlllllllIlIIll = false;
        if (lIlllllllIlIIlI.tooltipAnimProgress > 0.0) {
            if (lIlllllllIlIIlI.tooltip != null && !lIlllllllIlIIlI.tooltip.equals(lIlllllllIlIIlI.lastTooltip)) {
                lIlllllllIlIIlI.tooltipWidget = lIlllllllIlIIlI.theme.tooltip(lIlllllllIlIIlI.tooltip);
                lIlllllllIlIIlI.tooltipWidget.init();
            }
            lIlllllllIlIIlI.tooltipWidget.move(-lIlllllllIlIIlI.tooltipWidget.x + lIlllllllIlIIIl + 12.0, -lIlllllllIlIIlI.tooltipWidget.y + lIlllllllIlIIII + 12.0);
            lIlllllllIlIIlI.setAlpha(lIlllllllIlIIlI.tooltipAnimProgress);
            lIlllllllIlIIlI.begin();
            lIlllllllIlIIlI.tooltipWidget.render(lIlllllllIlIIlI, lIlllllllIlIIIl, lIlllllllIlIIII, lIlllllllIlIlII);
            lIlllllllIlIIlI.end();
            lIlllllllIlIIlI.setAlpha(1.0);
            lIlllllllIlIIlI.lastTooltip = lIlllllllIlIIlI.tooltip;
            lIlllllllIlIIll = true;
        }
        lIlllllllIlIIlI.tooltip = null;
        return lIlllllllIlIIll;
    }

    public void quad(double lIllllllIIllllI, double lIllllllIIlllIl, double lIllllllIIlIlIl, double lIllllllIIlIlII, Color lIllllllIIllIlI, Color lIllllllIIlIIlI) {
        GuiRenderer lIllllllIIllIII;
        lIllllllIIllIII.quad(lIllllllIIllllI, lIllllllIIlllIl, lIllllllIIlIlIl, lIllllllIIlIlII, lIllllllIIllIlI, lIllllllIIlIIlI, lIllllllIIlIIlI, lIllllllIIllIlI);
    }

    public void texture(double lIlllllIIIIlIll, double lIlllllIIIIlIlI, double lIlllllIIIIlIIl, double lIlllllIIIIlIII, double lIlllllIIIIIIII, class_1044 lIllllIllllllll) {
        GuiRenderer lIlllllIIIIllII;
        lIlllllIIIIllII.post(() -> {
            GuiRenderer lIllllIllIlIlII;
            lIllllIllIlIlII.mbTex.begin(null, DrawMode.Triangles, class_290.field_20887);
            lIllllIllIlIlII.mbTex.pos(lIlllllIIIIlIll, lIlllllIIIIlIlI, 0.0).color(WHITE).texture(0.0, 0.0).endVertex();
            lIllllIllIlIlII.mbTex.pos(lIlllllIIIIlIll + lIlllllIIIIlIIl, lIlllllIIIIlIlI, 0.0).color(WHITE).texture(1.0, 0.0).endVertex();
            lIllllIllIlIlII.mbTex.pos(lIlllllIIIIlIll + lIlllllIIIIlIIl, lIlllllIIIIlIlI + lIlllllIIIIlIII, 0.0).color(WHITE).texture(1.0, 1.0).endVertex();
            lIllllIllIlIlII.mbTex.pos(lIlllllIIIIlIll, lIlllllIIIIlIlI, 0.0).color(WHITE).texture(0.0, 0.0).endVertex();
            lIllllIllIlIlII.mbTex.pos(lIlllllIIIIlIll + lIlllllIIIIlIIl, lIlllllIIIIlIlI + lIlllllIIIIlIII, 0.0).color(WHITE).texture(1.0, 1.0).endVertex();
            lIllllIllIlIlII.mbTex.pos(lIlllllIIIIlIll, lIlllllIIIIlIlI + lIlllllIIIIlIII, 0.0).color(WHITE).texture(0.0, 1.0).endVertex();
            lIllllIllllllll.method_23207();
            GL11.glPushMatrix();
            GL11.glTranslated((double)(lIlllllIIIIlIll + lIlllllIIIIlIIl / 2.0), (double)(lIlllllIIIIlIlI + lIlllllIIIIlIII / 2.0), (double)0.0);
            GL11.glRotated((double)lIlllllIIIIIIII, (double)0.0, (double)0.0, (double)1.0);
            GL11.glTranslated((double)(-lIlllllIIIIlIll - lIlllllIIIIlIIl / 2.0), (double)(-lIlllllIIIIlIlI - lIlllllIIIIlIII / 2.0), (double)0.0);
            lIllllIllIlIlII.mbTex.end();
            GL11.glPopMatrix();
        });
    }

    public void quad(WWidget lIlllllIllllIll, Color lIlllllIlllIlll) {
        GuiRenderer lIlllllIllllIIl;
        lIlllllIllllIIl.quad(lIlllllIllllIll.x, lIlllllIllllIll.y, lIlllllIllllIll.width, lIlllllIllllIll.height, lIlllllIlllIlll);
    }

    public void end() {
        GuiRenderer llIIIIIIIIIlIII;
        llIIIIIIIIIlIII.scissorEnd();
        for (Runnable llIIIIIIIIIlIlI : llIIIIIIIIIlIII.postTasks) {
            llIIIIIIIIIlIlI.run();
        }
        llIIIIIIIIIlIII.postTasks.clear();
        GL11.glDisable((int)3089);
    }

    public void begin() {
        GuiRenderer llIIIIIIIIIlllI;
        GL11.glEnable((int)3089);
        llIIIIIIIIIlllI.scissorStart(0.0, 0.0, Utils.getWindowWidth(), Utils.getWindowHeight());
    }

    public static GuiTexture addTexture(class_2960 llIIIIIIIIlIIIl) {
        return TEXTURE_PACKER.add(llIIIIIIIIlIIIl);
    }

    public void rotatedQuad(double lIlllllIlIIllII, double lIlllllIlIIlIll, double lIlllllIIllIlII, double lIlllllIlIIlIIl, double lIlllllIlIIlIII, GuiTexture lIlllllIIllIIIl, Color lIlllllIlIIIllI) {
        GuiRenderer lIlllllIIlllIIl;
        TextureRegion lIlllllIlIIIlIl = lIlllllIIllIIIl.get(lIlllllIIllIlII, lIlllllIlIIlIIl);
        double lIlllllIlIIIlII = Math.toRadians(lIlllllIlIIlIII);
        double lIlllllIlIIIIll = Math.cos(lIlllllIlIIIlII);
        double lIlllllIlIIIIlI = Math.sin(lIlllllIlIIIlII);
        double lIlllllIlIIIIIl = lIlllllIlIIllII + lIlllllIIllIlII / 2.0;
        double lIlllllIlIIIIII = lIlllllIlIIlIll + lIlllllIlIIlIIl / 2.0;
        double lIlllllIIllllll = (lIlllllIlIIllII - lIlllllIlIIIIIl) * lIlllllIlIIIIll - (lIlllllIlIIlIll - lIlllllIlIIIIII) * lIlllllIlIIIIlI + lIlllllIlIIIIIl;
        double lIlllllIIlllllI = (lIlllllIlIIlIll - lIlllllIlIIIIII) * lIlllllIlIIIIll + (lIlllllIlIIllII - lIlllllIlIIIIIl) * lIlllllIlIIIIlI + lIlllllIlIIIIII;
        lIlllllIIlllIIl.mbTex.pos(lIlllllIIllllll, lIlllllIIlllllI, 0.0).color(lIlllllIlIIIllI).texture(lIlllllIlIIIlIl.x1, lIlllllIlIIIlIl.y1).endVertex();
        double lIlllllIIllllIl = (lIlllllIlIIllII + lIlllllIIllIlII - lIlllllIlIIIIIl) * lIlllllIlIIIIll - (lIlllllIlIIlIll - lIlllllIlIIIIII) * lIlllllIlIIIIlI + lIlllllIlIIIIIl;
        double lIlllllIIllllII = (lIlllllIlIIlIll - lIlllllIlIIIIII) * lIlllllIlIIIIll + (lIlllllIlIIllII + lIlllllIIllIlII - lIlllllIlIIIIIl) * lIlllllIlIIIIlI + lIlllllIlIIIIII;
        lIlllllIIlllIIl.mbTex.pos(lIlllllIIllllIl, lIlllllIIllllII, 0.0).color(lIlllllIlIIIllI).texture(lIlllllIlIIIlIl.x2, lIlllllIlIIIlIl.y1).endVertex();
        double lIlllllIIlllIll = (lIlllllIlIIllII + lIlllllIIllIlII - lIlllllIlIIIIIl) * lIlllllIlIIIIll - (lIlllllIlIIlIll + lIlllllIlIIlIIl - lIlllllIlIIIIII) * lIlllllIlIIIIlI + lIlllllIlIIIIIl;
        double lIlllllIIlllIlI = (lIlllllIlIIlIll + lIlllllIlIIlIIl - lIlllllIlIIIIII) * lIlllllIlIIIIll + (lIlllllIlIIllII + lIlllllIIllIlII - lIlllllIlIIIIIl) * lIlllllIlIIIIlI + lIlllllIlIIIIII;
        lIlllllIIlllIIl.mbTex.pos(lIlllllIIlllIll, lIlllllIIlllIlI, 0.0).color(lIlllllIlIIIllI).texture(lIlllllIlIIIlIl.x2, lIlllllIlIIIlIl.y2).endVertex();
        lIlllllIIlllIIl.mbTex.pos(lIlllllIIllllll, lIlllllIIlllllI, 0.0).color(lIlllllIlIIIllI).texture(lIlllllIlIIIlIl.x1, lIlllllIlIIIlIl.y1).endVertex();
        lIlllllIIlllIIl.mbTex.pos(lIlllllIIlllIll, lIlllllIIlllIlI, 0.0).color(lIlllllIlIIIllI).texture(lIlllllIlIIIlIl.x2, lIlllllIlIIIlIl.y2).endVertex();
        lIlllllIIllllIl = (lIlllllIlIIllII - lIlllllIlIIIIIl) * lIlllllIlIIIIll - (lIlllllIlIIlIll + lIlllllIlIIlIIl - lIlllllIlIIIIII) * lIlllllIlIIIIlI + lIlllllIlIIIIIl;
        lIlllllIIllllII = (lIlllllIlIIlIll + lIlllllIlIIlIIl - lIlllllIlIIIIII) * lIlllllIlIIIIll + (lIlllllIlIIllII - lIlllllIlIIIIIl) * lIlllllIlIIIIlI + lIlllllIlIIIIII;
        lIlllllIIlllIIl.mbTex.pos(lIlllllIIllllIl, lIlllllIIllllII, 0.0).color(lIlllllIlIIIllI).texture(lIlllllIlIIIlIl.x1, lIlllllIlIIIlIl.y2).endVertex();
    }

    public void post(Runnable lIllllIlllllIll) {
        GuiRenderer lIllllIlllllIlI;
        lIllllIlllllIlI.scissorStack.peek().postTasks.add(lIllllIlllllIll);
    }

    static {
        WHITE = new Color(255, 255, 255);
        TEXTURE_PACKER = new TexturePacker();
    }

    public GuiRenderer() {
        GuiRenderer llIIIIIIIIlIlIl;
        llIIIIIIIIlIlIl.mb = new MeshBuilder();
        llIIIIIIIIlIlIl.mbTex = new MeshBuilder();
        llIIIIIIIIlIlIl.scissorPool = new Pool<Scissor>(Scissor::new);
        llIIIIIIIIlIlIl.scissorStack = new Stack();
        llIIIIIIIIlIlIl.textPool = new Pool<TextOperation>(TextOperation::new);
        llIIIIIIIIlIlIl.texts = new ArrayList<TextOperation>();
        llIIIIIIIIlIlIl.postTasks = new ArrayList<Runnable>();
    }

    private void endRender() {
        GuiRenderer lIlllllllllllIl;
        lIlllllllllllIl.mb.end();
        TEXTURE.method_23207();
        lIlllllllllllIl.mbTex.texture = true;
        lIlllllllllllIl.mbTex.end();
        lIlllllllllllIl.theme.textRenderer().begin(lIlllllllllllIl.theme.scale(1.0));
        for (TextOperation lIlllllllllllll : lIlllllllllllIl.texts) {
            if (lIlllllllllllll.title) continue;
            lIlllllllllllll.run(lIlllllllllllIl.textPool);
        }
        lIlllllllllllIl.theme.textRenderer().end();
        lIlllllllllllIl.theme.textRenderer().begin(lIlllllllllllIl.theme.scale(1.25));
        for (TextOperation lIllllllllllllI : lIlllllllllllIl.texts) {
            if (!lIllllllllllllI.title) continue;
            lIllllllllllllI.run(lIlllllllllllIl.textPool);
        }
        lIlllllllllllIl.theme.textRenderer().end();
        lIlllllllllllIl.texts.clear();
    }

    private void beginRender() {
        GuiRenderer llIIIIIIIIIIIll;
        llIIIIIIIIIIIll.mb.begin(null, DrawMode.Triangles, class_290.field_1576);
        llIIIIIIIIIIIll.mbTex.begin(null, DrawMode.Triangles, class_290.field_20887);
    }
}

