/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1044
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.stb.STBTTFontinfo
 *  org.lwjgl.stb.STBTTPackContext
 *  org.lwjgl.stb.STBTTPackedchar
 *  org.lwjgl.stb.STBTTPackedchar$Buffer
 *  org.lwjgl.stb.STBTruetype
 *  org.lwjgl.system.MemoryStack
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
    public final /* synthetic */ class_1044 texture;
    private final /* synthetic */ CharData[] charData;
    private final /* synthetic */ float ascent;
    private final /* synthetic */ int height;
    private final /* synthetic */ float scale;

    public double getWidth(String lllllllllllllllllIIllIlIIllIlIIl, int lllllllllllllllllIIllIlIIllIlIII) {
        double lllllllllllllllllIIllIlIIllIIlll = 0.0;
        for (int lllllllllllllllllIIllIlIIllIlIll = 0; lllllllllllllllllIIllIlIIllIlIll < lllllllllllllllllIIllIlIIllIlIII; ++lllllllllllllllllIIllIlIIllIlIll) {
            Font lllllllllllllllllIIllIlIIllIIllI;
            int lllllllllllllllllIIllIlIIllIllIl = lllllllllllllllllIIllIlIIllIlIIl.charAt(lllllllllllllllllIIllIlIIllIlIll);
            if (lllllllllllllllllIIllIlIIllIllIl < 32 || lllllllllllllllllIIllIlIIllIllIl > 128) {
                lllllllllllllllllIIllIlIIllIllIl = 32;
            }
            CharData lllllllllllllllllIIllIlIIllIllII = lllllllllllllllllIIllIlIIllIIllI.charData[lllllllllllllllllIIllIlIIllIllIl - 32];
            lllllllllllllllllIIllIlIIllIIlll += (double)lllllllllllllllllIIllIlIIllIllII.xAdvance;
        }
        return lllllllllllllllllIIllIlIIllIIlll;
    }

    public double render(MeshBuilder lllllllllllllllllIIllIlIIlIIIlll, String lllllllllllllllllIIllIlIIlIIIllI, double lllllllllllllllllIIllIlIIlIIllII, double lllllllllllllllllIIllIlIIlIIIlII, Color lllllllllllllllllIIllIlIIlIIlIlI, double lllllllllllllllllIIllIlIIlIIlIIl) {
        Font lllllllllllllllllIIllIlIIlIIlIII;
        lllllllllllllllllIIllIlIIlIIIlII += (double)(lllllllllllllllllIIllIlIIlIIlIII.ascent * lllllllllllllllllIIllIlIIlIIlIII.scale) * lllllllllllllllllIIllIlIIlIIlIIl;
        for (int lllllllllllllllllIIllIlIIlIlIIII = 0; lllllllllllllllllIIllIlIIlIlIIII < lllllllllllllllllIIllIlIIlIIIllI.length(); ++lllllllllllllllllIIllIlIIlIlIIII) {
            int lllllllllllllllllIIllIlIIlIlIIlI = lllllllllllllllllIIllIlIIlIIIllI.charAt(lllllllllllllllllIIllIlIIlIlIIII);
            if (lllllllllllllllllIIllIlIIlIlIIlI < 32 || lllllllllllllllllIIllIlIIlIlIIlI > 128) {
                lllllllllllllllllIIllIlIIlIlIIlI = 32;
            }
            CharData lllllllllllllllllIIllIlIIlIlIIIl = lllllllllllllllllIIllIlIIlIIlIII.charData[lllllllllllllllllIIllIlIIlIlIIlI - 32];
            lllllllllllllllllIIllIlIIlIIIlll.pos(lllllllllllllllllIIllIlIIlIIllII + (double)lllllllllllllllllIIllIlIIlIlIIIl.x0 * lllllllllllllllllIIllIlIIlIIlIIl, lllllllllllllllllIIllIlIIlIIIlII + (double)lllllllllllllllllIIllIlIIlIlIIIl.y0 * lllllllllllllllllIIllIlIIlIIlIIl, 0.0).color(lllllllllllllllllIIllIlIIlIIlIlI).texture(lllllllllllllllllIIllIlIIlIlIIIl.u0, lllllllllllllllllIIllIlIIlIlIIIl.v0).endVertex();
            lllllllllllllllllIIllIlIIlIIIlll.pos(lllllllllllllllllIIllIlIIlIIllII + (double)lllllllllllllllllIIllIlIIlIlIIIl.x1 * lllllllllllllllllIIllIlIIlIIlIIl, lllllllllllllllllIIllIlIIlIIIlII + (double)lllllllllllllllllIIllIlIIlIlIIIl.y0 * lllllllllllllllllIIllIlIIlIIlIIl, 0.0).color(lllllllllllllllllIIllIlIIlIIlIlI).texture(lllllllllllllllllIIllIlIIlIlIIIl.u1, lllllllllllllllllIIllIlIIlIlIIIl.v0).endVertex();
            lllllllllllllllllIIllIlIIlIIIlll.pos(lllllllllllllllllIIllIlIIlIIllII + (double)lllllllllllllllllIIllIlIIlIlIIIl.x1 * lllllllllllllllllIIllIlIIlIIlIIl, lllllllllllllllllIIllIlIIlIIIlII + (double)lllllllllllllllllIIllIlIIlIlIIIl.y1 * lllllllllllllllllIIllIlIIlIIlIIl, 0.0).color(lllllllllllllllllIIllIlIIlIIlIlI).texture(lllllllllllllllllIIllIlIIlIlIIIl.u1, lllllllllllllllllIIllIlIIlIlIIIl.v1).endVertex();
            lllllllllllllllllIIllIlIIlIIIlll.pos(lllllllllllllllllIIllIlIIlIIllII + (double)lllllllllllllllllIIllIlIIlIlIIIl.x0 * lllllllllllllllllIIllIlIIlIIlIIl, lllllllllllllllllIIllIlIIlIIIlII + (double)lllllllllllllllllIIllIlIIlIlIIIl.y0 * lllllllllllllllllIIllIlIIlIIlIIl, 0.0).color(lllllllllllllllllIIllIlIIlIIlIlI).texture(lllllllllllllllllIIllIlIIlIlIIIl.u0, lllllllllllllllllIIllIlIIlIlIIIl.v0).endVertex();
            lllllllllllllllllIIllIlIIlIIIlll.pos(lllllllllllllllllIIllIlIIlIIllII + (double)lllllllllllllllllIIllIlIIlIlIIIl.x1 * lllllllllllllllllIIllIlIIlIIlIIl, lllllllllllllllllIIllIlIIlIIIlII + (double)lllllllllllllllllIIllIlIIlIlIIIl.y1 * lllllllllllllllllIIllIlIIlIIlIIl, 0.0).color(lllllllllllllllllIIllIlIIlIIlIlI).texture(lllllllllllllllllIIllIlIIlIlIIIl.u1, lllllllllllllllllIIllIlIIlIlIIIl.v1).endVertex();
            lllllllllllllllllIIllIlIIlIIIlll.pos(lllllllllllllllllIIllIlIIlIIllII + (double)lllllllllllllllllIIllIlIIlIlIIIl.x0 * lllllllllllllllllIIllIlIIlIIlIIl, lllllllllllllllllIIllIlIIlIIIlII + (double)lllllllllllllllllIIllIlIIlIlIIIl.y1 * lllllllllllllllllIIllIlIIlIIlIIl, 0.0).color(lllllllllllllllllIIllIlIIlIIlIlI).texture(lllllllllllllllllIIllIlIIlIlIIIl.u0, lllllllllllllllllIIllIlIIlIlIIIl.v1).endVertex();
            lllllllllllllllllIIllIlIIlIIllII += (double)lllllllllllllllllIIllIlIIlIlIIIl.xAdvance * lllllllllllllllllIIllIlIIlIIlIIl;
        }
        return lllllllllllllllllIIllIlIIlIIllII;
    }

    public double getHeight() {
        Font lllllllllllllllllIIllIlIIlIlllIl;
        return lllllllllllllllllIIllIlIIlIlllIl.height;
    }

    public Font(ByteBuffer lllllllllllllllllIIllIlIIllllllI, int lllllllllllllllllIIllIlIlIIIIlII) {
        Font lllllllllllllllllIIllIlIlIIIIllI;
        lllllllllllllllllIIllIlIlIIIIllI.height = lllllllllllllllllIIllIlIlIIIIlII;
        STBTTFontinfo lllllllllllllllllIIllIlIlIIIIIll = STBTTFontinfo.create();
        STBTruetype.stbtt_InitFont((STBTTFontinfo)lllllllllllllllllIIllIlIlIIIIIll, (ByteBuffer)lllllllllllllllllIIllIlIIllllllI);
        lllllllllllllllllIIllIlIlIIIIllI.charData = new CharData[128];
        STBTTPackedchar.Buffer lllllllllllllllllIIllIlIlIIIIIlI = STBTTPackedchar.create((int)lllllllllllllllllIIllIlIlIIIIllI.charData.length);
        ByteBuffer lllllllllllllllllIIllIlIlIIIIIIl = BufferUtils.createByteBuffer((int)0x400000);
        STBTTPackContext lllllllllllllllllIIllIlIlIIIIIII = STBTTPackContext.create();
        STBTruetype.stbtt_PackBegin((STBTTPackContext)lllllllllllllllllIIllIlIlIIIIIII, (ByteBuffer)lllllllllllllllllIIllIlIlIIIIIIl, (int)2048, (int)2048, (int)0, (int)1);
        STBTruetype.stbtt_PackSetOversampling((STBTTPackContext)lllllllllllllllllIIllIlIlIIIIIII, (int)2, (int)2);
        STBTruetype.stbtt_PackFontRange((STBTTPackContext)lllllllllllllllllIIllIlIlIIIIIII, (ByteBuffer)lllllllllllllllllIIllIlIIllllllI, (int)0, (float)lllllllllllllllllIIllIlIlIIIIlII, (int)32, (STBTTPackedchar.Buffer)lllllllllllllllllIIllIlIlIIIIIlI);
        STBTruetype.stbtt_PackEnd((STBTTPackContext)lllllllllllllllllIIllIlIlIIIIIII);
        lllllllllllllllllIIllIlIlIIIIllI.texture = new ByteTexture(2048, 2048, lllllllllllllllllIIllIlIlIIIIIIl, ByteTexture.Format.A, ByteTexture.Filter.Linear, ByteTexture.Filter.Linear);
        lllllllllllllllllIIllIlIlIIIIllI.scale = STBTruetype.stbtt_ScaleForPixelHeight((STBTTFontinfo)lllllllllllllllllIIllIlIlIIIIIll, (float)lllllllllllllllllIIllIlIlIIIIlII);
        try (MemoryStack lllllllllllllllllIIllIlIlIIIlIll = MemoryStack.stackPush();){
            IntBuffer lllllllllllllllllIIllIlIlIIIllII = lllllllllllllllllIIllIlIlIIIlIll.mallocInt(1);
            STBTruetype.stbtt_GetFontVMetrics((STBTTFontinfo)lllllllllllllllllIIllIlIlIIIIIll, (IntBuffer)lllllllllllllllllIIllIlIlIIIllII, null, null);
            lllllllllllllllllIIllIlIlIIIIllI.ascent = lllllllllllllllllIIllIlIlIIIllII.get(0);
        }
        for (int lllllllllllllllllIIllIlIlIIIIlll = 0; lllllllllllllllllIIllIlIlIIIIlll < lllllllllllllllllIIllIlIlIIIIllI.charData.length; ++lllllllllllllllllIIllIlIlIIIIlll) {
            STBTTPackedchar lllllllllllllllllIIllIlIlIIIlIlI = (STBTTPackedchar)lllllllllllllllllIIllIlIlIIIIIlI.get(lllllllllllllllllIIllIlIlIIIIlll);
            float lllllllllllllllllIIllIlIlIIIlIIl = 4.8828125E-4f;
            float lllllllllllllllllIIllIlIlIIIlIII = 4.8828125E-4f;
            lllllllllllllllllIIllIlIlIIIIllI.charData[lllllllllllllllllIIllIlIlIIIIlll] = new CharData(lllllllllllllllllIIllIlIlIIIlIlI.xoff(), lllllllllllllllllIIllIlIlIIIlIlI.yoff(), lllllllllllllllllIIllIlIlIIIlIlI.xoff2(), lllllllllllllllllIIllIlIlIIIlIlI.yoff2(), (float)lllllllllllllllllIIllIlIlIIIlIlI.x0() * lllllllllllllllllIIllIlIlIIIlIIl, (float)lllllllllllllllllIIllIlIlIIIlIlI.y0() * lllllllllllllllllIIllIlIlIIIlIII, (float)lllllllllllllllllIIllIlIlIIIlIlI.x1() * lllllllllllllllllIIllIlIlIIIlIIl, (float)lllllllllllllllllIIllIlIlIIIlIlI.y1() * lllllllllllllllllIIllIlIlIIIlIII, lllllllllllllllllIIllIlIlIIIlIlI.xadvance());
        }
    }
}

