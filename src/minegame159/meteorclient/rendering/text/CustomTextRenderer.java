/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.rendering.text;

import java.io.File;
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
    private boolean building;
    private final Font[] fonts;
    private Font font;
    private double scale = 1.0;
    private final MeshBuilder mb = new MeshBuilder(16384);
    private static final Color SHADOW_COLOR = new Color(60, 60, 60, 180);
    private boolean scaleOnly;

    public CustomTextRenderer(File file) {
        byte[] byArray = Utils.readBytes(file);
        ByteBuffer byteBuffer = BufferUtils.createByteBuffer((int)byArray.length).put(byArray);
        this.fonts = new Font[5];
        for (int i = 0; i < this.fonts.length; ++i) {
            byteBuffer.flip();
            this.fonts[i] = new Font(byteBuffer, (int)Math.round(18.0 * ((double)i * 0.5 + 1.0)));
            if (-3 < 0) continue;
            throw null;
        }
        this.mb.texture = true;
    }

    @Override
    public void end() {
        if (!this.building) {
            throw new RuntimeException("CustomTextRenderer.end() called without calling begin()");
        }
        if (!this.scaleOnly) {
            this.font.texture.method_23207();
            this.mb.end();
        }
        this.building = false;
        this.scale = 1.0;
    }

    @Override
    public double getWidth(String string, int n) {
        Font font = this.building ? this.font : this.fonts[0];
        return font.getWidth(string, n) * this.scale;
    }

    @Override
    public void begin(double d, boolean bl, boolean bl2) {
        double d2;
        if (this.building) {
            throw new RuntimeException("CustomTextRenderer.begin() called twice");
        }
        if (!bl) {
            this.mb.begin(null, DrawMode.Triangles, class_290.field_20887);
        }
        if (bl2) {
            this.font = this.fonts[this.fonts.length - 1];
        } else {
            d2 = Math.floor(d * 10.0) / 10.0;
            int n = d2 >= 3.0 ? 5 : (d2 >= 2.5 ? 4 : (d2 >= 2.0 ? 3 : (d2 >= 1.5 ? 2 : 1)));
            this.font = this.fonts[n - 1];
        }
        this.building = true;
        this.scaleOnly = bl;
        d2 = this.font.getHeight() / 18.0;
        this.scale = 1.0 + (d - d2) / d2;
    }

    @Override
    public boolean isBuilding() {
        return this.building;
    }

    @Override
    public void setAlpha(double d) {
        this.mb.alpha = d;
    }

    @Override
    public double getHeight() {
        Font font = this.building ? this.font : this.fonts[0];
        return font.getHeight() * this.scale;
    }

    @Override
    public double render(String string, double d, double d2, Color color, boolean bl) {
        double d3;
        boolean bl2 = this.building;
        if (!bl2) {
            this.begin();
        }
        if (bl) {
            d3 = this.font.render(this.mb, string, d + 1.0, d2 + 1.0, SHADOW_COLOR, this.scale);
            this.font.render(this.mb, string, d, d2, color, this.scale);
        } else {
            d3 = this.font.render(this.mb, string, d, d2, color, this.scale);
        }
        if (!bl2) {
            this.end();
        }
        return d3;
    }
}

