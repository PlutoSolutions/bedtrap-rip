/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.stb.STBImage
 *  org.lwjgl.stb.STBImageResize
 */
package minegame159.meteorclient.gui.renderer.packer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.gui.renderer.packer.GuiTexture;
import minegame159.meteorclient.gui.renderer.packer.TextureRegion;
import minegame159.meteorclient.utils.render.ByteTexture;
import net.minecraft.class_2960;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;
import org.lwjgl.stb.STBImageResize;

public class TexturePacker {
    private static final int maxWidth = 2048;
    private final List<Image> images = new ArrayList<Image>();

    /*
     * Exception decompiling
     */
    public GuiTexture add(class_2960 var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 7[CATCHBLOCK]
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:429)
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:478)
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:728)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:806)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:258)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:192)
         * org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         * org.benf.cfr.reader.entities.Method.analyse(Method.java:521)
         * org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
         * org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:922)
         * org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:253)
         * org.benf.cfr.reader.Driver.doJar(Driver.java:135)
         * org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:65)
         * org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException(Decompilation failed);
    }

    public ByteTexture pack() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (Image object : this.images) {
            if (n3 + object.width > 2048) {
                n = Math.max(n, n3);
                n2 += n4;
                n3 = 0;
                n4 = 0;
            }
            object.x = 1 + n3;
            object.y = 1 + n2;
            n3 += 1 + object.width + 1;
            n4 = Math.max(n4, 1 + object.height + 1);
        }
        n = Math.max(n, n3);
        ByteBuffer byteBuffer = BufferUtils.createByteBuffer((int)(n * (n2 += n4) * 4));
        for (Image image : this.images) {
            byte[] arrby = new byte[image.width * 4];
            for (int i = 0; i < image.height; ++i) {
                ((Buffer)image.buffer).position(i * arrby.length);
                image.buffer.get(arrby);
                ((Buffer)byteBuffer).position(((image.y + i) * n + image.x) * 4);
                byteBuffer.put(arrby);
                if (null == null) continue;
                return null;
            }
            ((Buffer)image.buffer).rewind();
            image.free();
            image.region.x1 = (double)image.x / (double)n;
            image.region.y1 = (double)image.y / (double)n2;
            image.region.x2 = (double)(image.x + image.width) / (double)n;
            image.region.y2 = (double)(image.y + image.height) / (double)n2;
        }
        ((Buffer)byteBuffer).rewind();
        return new ByteTexture(n, n2, byteBuffer, ByteTexture.Format.RGBA, ByteTexture.Filter.Linear, ByteTexture.Filter.Linear);
    }

    private void addResized(GuiTexture guiTexture, ByteBuffer byteBuffer, int n, int n2, int n3) {
        double d = (double)n3 / (double)n;
        int n4 = (int)((double)n2 * d);
        ByteBuffer byteBuffer2 = BufferUtils.createByteBuffer((int)(n3 * n4 * 4));
        STBImageResize.stbir_resize_uint8((ByteBuffer)byteBuffer, (int)n, (int)n2, (int)0, (ByteBuffer)byteBuffer2, (int)n3, (int)n4, (int)0, (int)4);
        TextureRegion textureRegion = new TextureRegion(n3, n4);
        guiTexture.add(textureRegion);
        this.images.add(new Image(byteBuffer2, textureRegion, n3, n4, false));
    }

    private static class Image {
        public int y;
        private final boolean stb;
        public final int height;
        public final int width;
        public final ByteBuffer buffer;
        public final TextureRegion region;
        public int x;

        public Image(ByteBuffer byteBuffer, TextureRegion textureRegion, int n, int n2, boolean bl) {
            this.buffer = byteBuffer;
            this.region = textureRegion;
            this.width = n;
            this.height = n2;
            this.stb = bl;
        }

        public void free() {
            if (this.stb) {
                STBImage.stbi_image_free((ByteBuffer)this.buffer);
            }
        }
    }
}

