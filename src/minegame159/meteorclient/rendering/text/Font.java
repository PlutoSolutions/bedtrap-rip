/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.rendering.text;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import minegame159.meteorclient.rendering.MeshBuilder;
import minegame159.meteorclient.rendering.text.CharData;
import minegame159.meteorclient.utils.render.ByteTexture;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1044;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTTPackContext;
import org.lwjgl.stb.STBTTPackedchar;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;

public class Font {
    public final class_1044 texture;
    private final CharData[] charData;
    private final float ascent;
    private final int height;
    private final float scale;

    public double getWidth(String string, int n) {
        double d = 0.0;
        for (int i = 0; i < n; ++i) {
            int n2 = string.charAt(i);
            if (n2 < 32 || n2 > 128) {
                n2 = 32;
            }
            CharData charData = this.charData[n2 - 32];
            d += (double)charData.xAdvance;
            if (null == null) continue;
            return 0.0;
        }
        return d;
    }

    public double render(MeshBuilder meshBuilder, String string, double d, double d2, Color color, double d3) {
        d2 += (double)(this.ascent * this.scale) * d3;
        for (int i = 0; i < string.length(); ++i) {
            int n = string.charAt(i);
            if (n < 32 || n > 128) {
                n = 32;
            }
            CharData charData = this.charData[n - 32];
            meshBuilder.pos(d + (double)charData.x0 * d3, d2 + (double)charData.y0 * d3, 0.0).color(color).texture(charData.u0, charData.v0).endVertex();
            meshBuilder.pos(d + (double)charData.x1 * d3, d2 + (double)charData.y0 * d3, 0.0).color(color).texture(charData.u1, charData.v0).endVertex();
            meshBuilder.pos(d + (double)charData.x1 * d3, d2 + (double)charData.y1 * d3, 0.0).color(color).texture(charData.u1, charData.v1).endVertex();
            meshBuilder.pos(d + (double)charData.x0 * d3, d2 + (double)charData.y0 * d3, 0.0).color(color).texture(charData.u0, charData.v0).endVertex();
            meshBuilder.pos(d + (double)charData.x1 * d3, d2 + (double)charData.y1 * d3, 0.0).color(color).texture(charData.u1, charData.v1).endVertex();
            meshBuilder.pos(d + (double)charData.x0 * d3, d2 + (double)charData.y1 * d3, 0.0).color(color).texture(charData.u0, charData.v1).endVertex();
            d += (double)charData.xAdvance * d3;
            if (-1 < 0) continue;
            return 0.0;
        }
        return d;
    }

    public double getHeight() {
        return this.height;
    }

    public Font(ByteBuffer byteBuffer, int n) {
        IntBuffer intBuffer;
        this.height = n;
        STBTTFontinfo sTBTTFontinfo = STBTTFontinfo.create();
        STBTruetype.stbtt_InitFont((STBTTFontinfo)sTBTTFontinfo, (ByteBuffer)byteBuffer);
        this.charData = new CharData[128];
        STBTTPackedchar.Buffer buffer = STBTTPackedchar.create((int)this.charData.length);
        ByteBuffer byteBuffer2 = BufferUtils.createByteBuffer((int)0x400000);
        STBTTPackContext sTBTTPackContext = STBTTPackContext.create();
        STBTruetype.stbtt_PackBegin((STBTTPackContext)sTBTTPackContext, (ByteBuffer)byteBuffer2, (int)2048, (int)2048, (int)0, (int)1);
        STBTruetype.stbtt_PackSetOversampling((STBTTPackContext)sTBTTPackContext, (int)2, (int)2);
        STBTruetype.stbtt_PackFontRange((STBTTPackContext)sTBTTPackContext, (ByteBuffer)byteBuffer, (int)0, (float)n, (int)32, (STBTTPackedchar.Buffer)buffer);
        STBTruetype.stbtt_PackEnd((STBTTPackContext)sTBTTPackContext);
        this.texture = new ByteTexture(2048, 2048, byteBuffer2, ByteTexture.Format.A, ByteTexture.Filter.Linear, ByteTexture.Filter.Linear);
        this.scale = STBTruetype.stbtt_ScaleForPixelHeight((STBTTFontinfo)sTBTTFontinfo, (float)n);
        try (MemoryStack memoryStack = MemoryStack.stackPush();){
            intBuffer = memoryStack.mallocInt(1);
            STBTruetype.stbtt_GetFontVMetrics((STBTTFontinfo)sTBTTFontinfo, (IntBuffer)intBuffer, null, null);
            this.ascent = intBuffer.get(0);
        }
        for (int i = 0; i < this.charData.length; ++i) {
            intBuffer = (STBTTPackedchar)buffer.get(i);
            float f = 4.8828125E-4f;
            float f2 = 4.8828125E-4f;
            this.charData[i] = new CharData(intBuffer.xoff(), intBuffer.yoff(), intBuffer.xoff2(), intBuffer.yoff2(), (float)intBuffer.x0() * f, (float)intBuffer.y0() * f2, (float)intBuffer.x1() * f, (float)intBuffer.y1() * f2, intBuffer.xadvance());
            if (4 >= 0) continue;
            throw null;
        }
    }
}

