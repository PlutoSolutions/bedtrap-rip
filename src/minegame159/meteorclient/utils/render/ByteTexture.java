/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1044
 *  net.minecraft.class_3300
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL30C
 */
package minegame159.meteorclient.utils.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import net.minecraft.class_1044;
import net.minecraft.class_3300;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30C;

public class ByteTexture
extends class_1044 {
    private void lambda$new$0(int n, int n2, byte[] arrby, Format format, Filter filter, Filter filter2) {
        this.upload(n, n2, arrby, format, filter, filter2);
    }

    public void method_4625(class_3300 class_33002) throws IOException {
    }

    public ByteTexture(int n, int n2, byte[] arrby, Format format, Filter filter, Filter filter2) {
        if (!RenderSystem.isOnRenderThread()) {
            RenderSystem.recordRenderCall(() -> this.lambda$new$0(n, n2, arrby, format, filter, filter2));
        } else {
            this.upload(n, n2, arrby, format, filter, filter2);
        }
    }

    private void upload(int n, int n2, ByteBuffer byteBuffer, Format format, Filter filter, Filter filter2) {
        this.method_23207();
        GL30C.glPixelStorei((int)3312, (int)0);
        GL30C.glPixelStorei((int)3313, (int)0);
        GL30C.glPixelStorei((int)3314, (int)0);
        GL30C.glPixelStorei((int)32878, (int)0);
        GL30C.glPixelStorei((int)3315, (int)0);
        GL30C.glPixelStorei((int)3316, (int)0);
        GL30C.glPixelStorei((int)32877, (int)0);
        GL30C.glPixelStorei((int)3317, (int)4);
        GL30C.glTexParameteri((int)3553, (int)10242, (int)10497);
        GL30C.glTexParameteri((int)3553, (int)10243, (int)10497);
        GL30C.glTexParameteri((int)3553, (int)10241, (int)filter.toOpenGL());
        GL30C.glTexParameteri((int)3553, (int)10240, (int)filter2.toOpenGL());
        ((Buffer)byteBuffer).rewind();
        GL30C.glTexImage2D((int)3553, (int)0, (int)format.toOpenGL(), (int)n, (int)n2, (int)0, (int)format.toOpenGL(), (int)5121, (ByteBuffer)byteBuffer);
    }

    private void upload(int n, int n2, byte[] arrby, Format format, Filter filter, Filter filter2) {
        ByteBuffer byteBuffer = BufferUtils.createByteBuffer((int)arrby.length).put(arrby);
        this.upload(n, n2, byteBuffer, format, filter, filter2);
    }

    public ByteTexture(int n, int n2, ByteBuffer byteBuffer, Format format, Filter filter, Filter filter2) {
        if (!RenderSystem.isOnRenderThread()) {
            RenderSystem.recordRenderCall(() -> this.lambda$new$1(n, n2, byteBuffer, format, filter, filter2));
        } else {
            this.upload(n, n2, byteBuffer, format, filter, filter2);
        }
    }

    private void lambda$new$1(int n, int n2, ByteBuffer byteBuffer, Format format, Filter filter, Filter filter2) {
        this.upload(n, n2, byteBuffer, format, filter, filter2);
    }

    public static enum Format {
        A,
        RGB,
        RGBA;


        public int toOpenGL() {
            switch (1.$SwitchMap$minegame159$meteorclient$utils$render$ByteTexture$Format[this.ordinal()]) {
                case 1: {
                    return 6406;
                }
                case 2: {
                    return 6407;
                }
                case 3: {
                    return 6408;
                }
            }
            return 0;
        }
    }

    public static enum Filter {
        Nearest,
        Linear;


        public int toOpenGL() {
            return this == Nearest ? 9728 : 9729;
        }
    }
}

