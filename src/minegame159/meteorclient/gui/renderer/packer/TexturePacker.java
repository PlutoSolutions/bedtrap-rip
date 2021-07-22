/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_4536
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.stb.STBImage
 *  org.lwjgl.stb.STBImageResize
 *  org.lwjgl.system.MemoryStack
 *  org.lwjgl.system.MemoryUtil
 */
package minegame159.meteorclient.gui.renderer.packer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.gui.renderer.packer.GuiTexture;
import minegame159.meteorclient.gui.renderer.packer.TextureRegion;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.ByteTexture;
import net.minecraft.class_2960;
import net.minecraft.class_4536;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;
import org.lwjgl.stb.STBImageResize;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class TexturePacker {
    private static final /* synthetic */ int maxWidth;
    private final /* synthetic */ List<Image> images;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public GuiTexture add(class_2960 lllllllllllllllllIIlIlIllIIIIlIl) {
        try {
            InputStream lllllllllllllllllIIlIlIllIIIlIIl = Utils.mc.method_1478().method_14486(lllllllllllllllllIIlIlIllIIIIlIl).method_14482();
            GuiTexture lllllllllllllllllIIlIlIllIIIlIII = new GuiTexture();
            try (MemoryStack lllllllllllllllllIIlIlIllIIIlIlI = MemoryStack.stackPush();){
                ByteBuffer lllllllllllllllllIIlIlIllIIIlIll = null;
                try {
                    TexturePacker lllllllllllllllllIIlIlIllIIIIlII;
                    lllllllllllllllllIIlIlIllIIIlIll = class_4536.method_24962((InputStream)lllllllllllllllllIIlIlIllIIIlIIl);
                    ((Buffer)lllllllllllllllllIIlIlIllIIIlIll).rewind();
                    IntBuffer lllllllllllllllllIIlIlIllIIlIIll = lllllllllllllllllIIlIlIllIIIlIlI.mallocInt(1);
                    IntBuffer lllllllllllllllllIIlIlIllIIlIIlI = lllllllllllllllllIIlIlIllIIIlIlI.mallocInt(1);
                    IntBuffer lllllllllllllllllIIlIlIllIIlIIIl = lllllllllllllllllIIlIlIllIIIlIlI.mallocInt(1);
                    ByteBuffer lllllllllllllllllIIlIlIllIIlIIII = STBImage.stbi_load_from_memory((ByteBuffer)lllllllllllllllllIIlIlIllIIIlIll, (IntBuffer)lllllllllllllllllIIlIlIllIIlIIll, (IntBuffer)lllllllllllllllllIIlIlIllIIlIIlI, (IntBuffer)lllllllllllllllllIIlIlIllIIlIIIl, (int)4);
                    int lllllllllllllllllIIlIlIllIIIllll = lllllllllllllllllIIlIlIllIIlIIll.get(0);
                    int lllllllllllllllllIIlIlIllIIIlllI = lllllllllllllllllIIlIlIllIIlIIlI.get(0);
                    TextureRegion lllllllllllllllllIIlIlIllIIIllIl = new TextureRegion(lllllllllllllllllIIlIlIllIIIllll, lllllllllllllllllIIlIlIllIIIlllI);
                    lllllllllllllllllIIlIlIllIIIlIII.add(lllllllllllllllllIIlIlIllIIIllIl);
                    lllllllllllllllllIIlIlIllIIIIlII.images.add(new Image(lllllllllllllllllIIlIlIllIIlIIII, lllllllllllllllllIIlIlIllIIIllIl, lllllllllllllllllIIlIlIllIIIllll, lllllllllllllllllIIlIlIllIIIlllI, true));
                    if (lllllllllllllllllIIlIlIllIIIllll > 20) {
                        lllllllllllllllllIIlIlIllIIIIlII.addResized(lllllllllllllllllIIlIlIllIIIlIII, lllllllllllllllllIIlIlIllIIlIIII, lllllllllllllllllIIlIlIllIIIllll, lllllllllllllllllIIlIlIllIIIlllI, 20);
                    }
                    if (lllllllllllllllllIIlIlIllIIIllll > 32) {
                        lllllllllllllllllIIlIlIllIIIIlII.addResized(lllllllllllllllllIIlIlIllIIIlIII, lllllllllllllllllIIlIlIllIIlIIII, lllllllllllllllllIIlIlIllIIIllll, lllllllllllllllllIIlIlIllIIIlllI, 32);
                    }
                    if (lllllllllllllllllIIlIlIllIIIllll > 48) {
                        lllllllllllllllllIIlIlIllIIIIlII.addResized(lllllllllllllllllIIlIlIllIIIlIII, lllllllllllllllllIIlIlIllIIlIIII, lllllllllllllllllIIlIlIllIIIllll, lllllllllllllllllIIlIlIllIIIlllI, 48);
                    }
                }
                catch (IOException lllllllllllllllllIIlIlIllIIIllII) {
                    lllllllllllllllllIIlIlIllIIIllII.printStackTrace();
                }
                finally {
                    MemoryUtil.memFree((Buffer)lllllllllllllllllIIlIlIllIIIlIll);
                }
            }
            return lllllllllllllllllIIlIlIllIIIlIII;
        }
        catch (IOException lllllllllllllllllIIlIlIllIIIIlll) {
            lllllllllllllllllIIlIlIllIIIIlll.printStackTrace();
            return null;
        }
    }

    static {
        maxWidth = 2048;
    }

    public ByteTexture pack() {
        TexturePacker lllllllllllllllllIIlIlIlIlIIlIlI;
        int lllllllllllllllllIIlIlIlIlIIlIIl = 0;
        int lllllllllllllllllIIlIlIlIlIIlIII = 0;
        int lllllllllllllllllIIlIlIlIlIIIlll = 0;
        int lllllllllllllllllIIlIlIlIlIIIllI = 0;
        for (Image lllllllllllllllllIIlIlIlIlIIlllI : lllllllllllllllllIIlIlIlIlIIlIlI.images) {
            if (lllllllllllllllllIIlIlIlIlIIIlll + lllllllllllllllllIIlIlIlIlIIlllI.width > 2048) {
                lllllllllllllllllIIlIlIlIlIIlIIl = Math.max(lllllllllllllllllIIlIlIlIlIIlIIl, lllllllllllllllllIIlIlIlIlIIIlll);
                lllllllllllllllllIIlIlIlIlIIlIII += lllllllllllllllllIIlIlIlIlIIIllI;
                lllllllllllllllllIIlIlIlIlIIIlll = 0;
                lllllllllllllllllIIlIlIlIlIIIllI = 0;
            }
            lllllllllllllllllIIlIlIlIlIIlllI.x = 1 + lllllllllllllllllIIlIlIlIlIIIlll;
            lllllllllllllllllIIlIlIlIlIIlllI.y = 1 + lllllllllllllllllIIlIlIlIlIIlIII;
            lllllllllllllllllIIlIlIlIlIIIlll += 1 + lllllllllllllllllIIlIlIlIlIIlllI.width + 1;
            lllllllllllllllllIIlIlIlIlIIIllI = Math.max(lllllllllllllllllIIlIlIlIlIIIllI, 1 + lllllllllllllllllIIlIlIlIlIIlllI.height + 1);
        }
        lllllllllllllllllIIlIlIlIlIIlIIl = Math.max(lllllllllllllllllIIlIlIlIlIIlIIl, lllllllllllllllllIIlIlIlIlIIIlll);
        ByteBuffer lllllllllllllllllIIlIlIlIlIIIlIl = BufferUtils.createByteBuffer((int)(lllllllllllllllllIIlIlIlIlIIlIIl * (lllllllllllllllllIIlIlIlIlIIlIII += lllllllllllllllllIIlIlIlIlIIIllI) * 4));
        for (Image lllllllllllllllllIIlIlIlIlIIlIll : lllllllllllllllllIIlIlIlIlIIlIlI.images) {
            byte[] lllllllllllllllllIIlIlIlIlIIllII = new byte[lllllllllllllllllIIlIlIlIlIIlIll.width * 4];
            for (int lllllllllllllllllIIlIlIlIlIIllIl = 0; lllllllllllllllllIIlIlIlIlIIllIl < lllllllllllllllllIIlIlIlIlIIlIll.height; ++lllllllllllllllllIIlIlIlIlIIllIl) {
                ((Buffer)lllllllllllllllllIIlIlIlIlIIlIll.buffer).position(lllllllllllllllllIIlIlIlIlIIllIl * lllllllllllllllllIIlIlIlIlIIllII.length);
                lllllllllllllllllIIlIlIlIlIIlIll.buffer.get(lllllllllllllllllIIlIlIlIlIIllII);
                ((Buffer)lllllllllllllllllIIlIlIlIlIIIlIl).position(((lllllllllllllllllIIlIlIlIlIIlIll.y + lllllllllllllllllIIlIlIlIlIIllIl) * lllllllllllllllllIIlIlIlIlIIlIIl + lllllllllllllllllIIlIlIlIlIIlIll.x) * 4);
                lllllllllllllllllIIlIlIlIlIIIlIl.put(lllllllllllllllllIIlIlIlIlIIllII);
            }
            ((Buffer)lllllllllllllllllIIlIlIlIlIIlIll.buffer).rewind();
            lllllllllllllllllIIlIlIlIlIIlIll.free();
            lllllllllllllllllIIlIlIlIlIIlIll.region.x1 = (double)lllllllllllllllllIIlIlIlIlIIlIll.x / (double)lllllllllllllllllIIlIlIlIlIIlIIl;
            lllllllllllllllllIIlIlIlIlIIlIll.region.y1 = (double)lllllllllllllllllIIlIlIlIlIIlIll.y / (double)lllllllllllllllllIIlIlIlIlIIlIII;
            lllllllllllllllllIIlIlIlIlIIlIll.region.x2 = (double)(lllllllllllllllllIIlIlIlIlIIlIll.x + lllllllllllllllllIIlIlIlIlIIlIll.width) / (double)lllllllllllllllllIIlIlIlIlIIlIIl;
            lllllllllllllllllIIlIlIlIlIIlIll.region.y2 = (double)(lllllllllllllllllIIlIlIlIlIIlIll.y + lllllllllllllllllIIlIlIlIlIIlIll.height) / (double)lllllllllllllllllIIlIlIlIlIIlIII;
        }
        ((Buffer)lllllllllllllllllIIlIlIlIlIIIlIl).rewind();
        return new ByteTexture(lllllllllllllllllIIlIlIlIlIIlIIl, lllllllllllllllllIIlIlIlIlIIlIII, lllllllllllllllllIIlIlIlIlIIIlIl, ByteTexture.Format.RGBA, ByteTexture.Filter.Linear, ByteTexture.Filter.Linear);
    }

    public TexturePacker() {
        TexturePacker lllllllllllllllllIIlIlIllIlIIIlI;
        lllllllllllllllllIIlIlIllIlIIIlI.images = new ArrayList<Image>();
    }

    private void addResized(GuiTexture lllllllllllllllllIIlIlIlIllIlIll, ByteBuffer lllllllllllllllllIIlIlIlIllIlIlI, int lllllllllllllllllIIlIlIlIllIlIIl, int lllllllllllllllllIIlIlIlIlIllllI, int lllllllllllllllllIIlIlIlIllIIlll) {
        TexturePacker lllllllllllllllllIIlIlIlIllIIIlI;
        double lllllllllllllllllIIlIlIlIllIIllI = (double)lllllllllllllllllIIlIlIlIllIIlll / (double)lllllllllllllllllIIlIlIlIllIlIIl;
        int lllllllllllllllllIIlIlIlIllIIlIl = (int)((double)lllllllllllllllllIIlIlIlIlIllllI * lllllllllllllllllIIlIlIlIllIIllI);
        ByteBuffer lllllllllllllllllIIlIlIlIllIIlII = BufferUtils.createByteBuffer((int)(lllllllllllllllllIIlIlIlIllIIlll * lllllllllllllllllIIlIlIlIllIIlIl * 4));
        STBImageResize.stbir_resize_uint8((ByteBuffer)lllllllllllllllllIIlIlIlIllIlIlI, (int)lllllllllllllllllIIlIlIlIllIlIIl, (int)lllllllllllllllllIIlIlIlIlIllllI, (int)0, (ByteBuffer)lllllllllllllllllIIlIlIlIllIIlII, (int)lllllllllllllllllIIlIlIlIllIIlll, (int)lllllllllllllllllIIlIlIlIllIIlIl, (int)0, (int)4);
        TextureRegion lllllllllllllllllIIlIlIlIllIIIll = new TextureRegion(lllllllllllllllllIIlIlIlIllIIlll, lllllllllllllllllIIlIlIlIllIIlIl);
        lllllllllllllllllIIlIlIlIllIlIll.add(lllllllllllllllllIIlIlIlIllIIIll);
        lllllllllllllllllIIlIlIlIllIIIlI.images.add(new Image(lllllllllllllllllIIlIlIlIllIIlII, lllllllllllllllllIIlIlIlIllIIIll, lllllllllllllllllIIlIlIlIllIIlll, lllllllllllllllllIIlIlIlIllIIlIl, false));
    }

    private static class Image {
        public /* synthetic */ int y;
        private final /* synthetic */ boolean stb;
        public final /* synthetic */ int height;
        public final /* synthetic */ int width;
        public final /* synthetic */ ByteBuffer buffer;
        public final /* synthetic */ TextureRegion region;
        public /* synthetic */ int x;

        public Image(ByteBuffer lIllllIIlIIlI, TextureRegion lIllllIIlIIIl, int lIllllIIlIllI, int lIllllIIIllll, boolean lIllllIIIlllI) {
            Image lIllllIIlIIll;
            lIllllIIlIIll.buffer = lIllllIIlIIlI;
            lIllllIIlIIll.region = lIllllIIlIIIl;
            lIllllIIlIIll.width = lIllllIIlIllI;
            lIllllIIlIIll.height = lIllllIIIllll;
            lIllllIIlIIll.stb = lIllllIIIlllI;
        }

        public void free() {
            Image lIllllIIIlIll;
            if (lIllllIIIlIll.stb) {
                STBImage.stbi_image_free((ByteBuffer)lIllllIIIlIll.buffer);
            }
        }
    }
}

