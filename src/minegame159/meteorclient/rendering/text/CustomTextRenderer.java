/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_290
 *  org.lwjgl.BufferUtils
 */
package minegame159.meteorclient.rendering.text;

import java.io.File;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.MeshBuilder;
import minegame159.meteorclient.rendering.text.Font;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_290;
import org.lwjgl.BufferUtils;

public class CustomTextRenderer
implements TextRenderer {
    private /* synthetic */ boolean building;
    private final /* synthetic */ Font[] fonts;
    private /* synthetic */ Font font;
    private /* synthetic */ double scale;
    private final /* synthetic */ MeshBuilder mb;
    private static final /* synthetic */ Color SHADOW_COLOR;
    private /* synthetic */ boolean scaleOnly;

    static {
        SHADOW_COLOR = new Color(60, 60, 60, 180);
    }

    public CustomTextRenderer(File lllllllllllllllllllllIIllIIIlIll) {
        CustomTextRenderer lllllllllllllllllllllIIllIIIllII;
        lllllllllllllllllllllIIllIIIllII.mb = new MeshBuilder(16384);
        lllllllllllllllllllllIIllIIIllII.scale = 1.0;
        byte[] lllllllllllllllllllllIIllIIIlllI = Utils.readBytes(lllllllllllllllllllllIIllIIIlIll);
        ByteBuffer lllllllllllllllllllllIIllIIIllIl = BufferUtils.createByteBuffer((int)lllllllllllllllllllllIIllIIIlllI.length).put(lllllllllllllllllllllIIllIIIlllI);
        lllllllllllllllllllllIIllIIIllII.fonts = new Font[5];
        for (int lllllllllllllllllllllIIllIIlIIIl = 0; lllllllllllllllllllllIIllIIlIIIl < lllllllllllllllllllllIIllIIIllII.fonts.length; ++lllllllllllllllllllllIIllIIlIIIl) {
            ((Buffer)lllllllllllllllllllllIIllIIIllIl).flip();
            lllllllllllllllllllllIIllIIIllII.fonts[lllllllllllllllllllllIIllIIlIIIl] = new Font(lllllllllllllllllllllIIllIIIllIl, (int)Math.round(18.0 * ((double)lllllllllllllllllllllIIllIIlIIIl * 0.5 + 1.0)));
        }
        lllllllllllllllllllllIIllIIIllII.mb.texture = true;
    }

    @Override
    public void end() {
        CustomTextRenderer lllllllllllllllllllllIIlIIlllIll;
        if (!lllllllllllllllllllllIIlIIlllIll.building) {
            throw new RuntimeException("CustomTextRenderer.end() called without calling begin()");
        }
        if (!lllllllllllllllllllllIIlIIlllIll.scaleOnly) {
            lllllllllllllllllllllIIlIIlllIll.font.texture.method_23207();
            lllllllllllllllllllllIIlIIlllIll.mb.end();
        }
        lllllllllllllllllllllIIlIIlllIll.building = false;
        lllllllllllllllllllllIIlIIlllIll.scale = 1.0;
    }

    @Override
    public double getWidth(String lllllllllllllllllllllIIlIllIIlIl, int lllllllllllllllllllllIIlIllIIIII) {
        CustomTextRenderer lllllllllllllllllllllIIlIllIIIlI;
        Font lllllllllllllllllllllIIlIllIIIll = lllllllllllllllllllllIIlIllIIIlI.building ? lllllllllllllllllllllIIlIllIIIlI.font : lllllllllllllllllllllIIlIllIIIlI.fonts[0];
        return lllllllllllllllllllllIIlIllIIIll.getWidth(lllllllllllllllllllllIIlIllIIlIl, lllllllllllllllllllllIIlIllIIIII) * lllllllllllllllllllllIIlIllIIIlI.scale;
    }

    @Override
    public void begin(double lllllllllllllllllllllIIlIllIllll, boolean lllllllllllllllllllllIIlIllIlllI, boolean lllllllllllllllllllllIIlIlllIIlI) {
        CustomTextRenderer lllllllllllllllllllllIIlIlllIlIl;
        if (lllllllllllllllllllllIIlIlllIlIl.building) {
            throw new RuntimeException("CustomTextRenderer.begin() called twice");
        }
        if (!lllllllllllllllllllllIIlIllIlllI) {
            lllllllllllllllllllllIIlIlllIlIl.mb.begin(null, DrawMode.Triangles, class_290.field_20887);
        }
        if (lllllllllllllllllllllIIlIlllIIlI) {
            lllllllllllllllllllllIIlIlllIlIl.font = lllllllllllllllllllllIIlIlllIlIl.fonts[lllllllllllllllllllllIIlIlllIlIl.fonts.length - 1];
        } else {
            boolean lllllllllllllllllllllIIlIlllIllI;
            double lllllllllllllllllllllIIlIlllIlll = Math.floor(lllllllllllllllllllllIIlIllIllll * 10.0) / 10.0;
            if (lllllllllllllllllllllIIlIlllIlll >= 3.0) {
                int lllllllllllllllllllllIIlIllllIll = 5;
            } else if (lllllllllllllllllllllIIlIlllIlll >= 2.5) {
                int lllllllllllllllllllllIIlIllllIlI = 4;
            } else if (lllllllllllllllllllllIIlIlllIlll >= 2.0) {
                int lllllllllllllllllllllIIlIllllIIl = 3;
            } else if (lllllllllllllllllllllIIlIlllIlll >= 1.5) {
                int lllllllllllllllllllllIIlIllllIII = 2;
            } else {
                lllllllllllllllllllllIIlIlllIllI = true;
            }
            lllllllllllllllllllllIIlIlllIlIl.font = lllllllllllllllllllllIIlIlllIlIl.fonts[lllllllllllllllllllllIIlIlllIllI - true];
        }
        lllllllllllllllllllllIIlIlllIlIl.building = true;
        lllllllllllllllllllllIIlIlllIlIl.scaleOnly = lllllllllllllllllllllIIlIllIlllI;
        double lllllllllllllllllllllIIlIlllIIIl = lllllllllllllllllllllIIlIlllIlIl.font.getHeight() / 18.0;
        lllllllllllllllllllllIIlIlllIlIl.scale = 1.0 + (lllllllllllllllllllllIIlIllIllll - lllllllllllllllllllllIIlIlllIIIl) / lllllllllllllllllllllIIlIlllIIIl;
    }

    @Override
    public boolean isBuilding() {
        CustomTextRenderer lllllllllllllllllllllIIlIIlllllI;
        return lllllllllllllllllllllIIlIIlllllI.building;
    }

    @Override
    public void setAlpha(double lllllllllllllllllllllIIllIIIIlII) {
        lllllllllllllllllllllIIllIIIIlIl.mb.alpha = lllllllllllllllllllllIIllIIIIlII;
    }

    @Override
    public double getHeight() {
        CustomTextRenderer lllllllllllllllllllllIIlIlIlllII;
        Font lllllllllllllllllllllIIlIlIllIll = lllllllllllllllllllllIIlIlIlllII.building ? lllllllllllllllllllllIIlIlIlllII.font : lllllllllllllllllllllIIlIlIlllII.fonts[0];
        return lllllllllllllllllllllIIlIlIllIll.getHeight() * lllllllllllllllllllllIIlIlIlllII.scale;
    }

    @Override
    public double render(String lllllllllllllllllllllIIlIlIIIllI, double lllllllllllllllllllllIIlIlIIIlIl, double lllllllllllllllllllllIIlIlIIIlII, Color lllllllllllllllllllllIIlIlIIlIll, boolean lllllllllllllllllllllIIlIlIIIIlI) {
        double lllllllllllllllllllllIIlIlIIlIII;
        CustomTextRenderer lllllllllllllllllllllIIlIlIIllll;
        boolean lllllllllllllllllllllIIlIlIIlIIl = lllllllllllllllllllllIIlIlIIllll.building;
        if (!lllllllllllllllllllllIIlIlIIlIIl) {
            lllllllllllllllllllllIIlIlIIllll.begin();
        }
        if (lllllllllllllllllllllIIlIlIIIIlI) {
            double lllllllllllllllllllllIIlIlIlIIII = lllllllllllllllllllllIIlIlIIllll.font.render(lllllllllllllllllllllIIlIlIIllll.mb, lllllllllllllllllllllIIlIlIIIllI, lllllllllllllllllllllIIlIlIIIlIl + 1.0, lllllllllllllllllllllIIlIlIIIlII + 1.0, SHADOW_COLOR, lllllllllllllllllllllIIlIlIIllll.scale);
            lllllllllllllllllllllIIlIlIIllll.font.render(lllllllllllllllllllllIIlIlIIllll.mb, lllllllllllllllllllllIIlIlIIIllI, lllllllllllllllllllllIIlIlIIIlIl, lllllllllllllllllllllIIlIlIIIlII, lllllllllllllllllllllIIlIlIIlIll, lllllllllllllllllllllIIlIlIIllll.scale);
        } else {
            lllllllllllllllllllllIIlIlIIlIII = lllllllllllllllllllllIIlIlIIllll.font.render(lllllllllllllllllllllIIlIlIIllll.mb, lllllllllllllllllllllIIlIlIIIllI, lllllllllllllllllllllIIlIlIIIlIl, lllllllllllllllllllllIIlIlIIIlII, lllllllllllllllllllllIIlIlIIlIll, lllllllllllllllllllllIIlIlIIllll.scale);
        }
        if (!lllllllllllllllllllllIIlIlIIlIIl) {
            lllllllllllllllllllllIIlIlIIllll.end();
        }
        return lllllllllllllllllllllIIlIlIIlIII;
    }
}

