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
    public void method_4625(class_3300 lllllllllllllllllIllIlIllIIllIll) throws IOException {
    }

    public ByteTexture(int lllllllllllllllllIllIlIllllIIlII, int lllllllllllllllllIllIlIllllIlIlI, byte[] lllllllllllllllllIllIlIllllIlIIl, Format lllllllllllllllllIllIlIllllIIIIl, Filter lllllllllllllllllIllIlIllllIIlll, Filter lllllllllllllllllIllIlIlllIlllll) {
        ByteTexture lllllllllllllllllIllIlIllllIIlIl;
        if (!RenderSystem.isOnRenderThread()) {
            RenderSystem.recordRenderCall(() -> {
                ByteTexture lllllllllllllllllIllIlIlIlllIlll;
                lllllllllllllllllIllIlIlIlllIlll.upload(lllllllllllllllllIllIlIllllIIlII, lllllllllllllllllIllIlIllllIlIlI, lllllllllllllllllIllIlIllllIlIIl, lllllllllllllllllIllIlIllllIIIIl, lllllllllllllllllIllIlIllllIIlll, lllllllllllllllllIllIlIlllIlllll);
            });
        } else {
            lllllllllllllllllIllIlIllllIIlIl.upload(lllllllllllllllllIllIlIllllIIlII, lllllllllllllllllIllIlIllllIlIlI, lllllllllllllllllIllIlIllllIlIIl, lllllllllllllllllIllIlIllllIIIIl, lllllllllllllllllIllIlIllllIIlll, lllllllllllllllllIllIlIlllIlllll);
        }
    }

    private void upload(int lllllllllllllllllIllIlIllIlIIIlI, int lllllllllllllllllIllIlIllIlIIIIl, ByteBuffer lllllllllllllllllIllIlIllIlIIIII, Format lllllllllllllllllIllIlIllIIlllll, Filter lllllllllllllllllIllIlIllIlIIlIl, Filter lllllllllllllllllIllIlIllIlIIlII) {
        ByteTexture lllllllllllllllllIllIlIllIlIlIlI;
        lllllllllllllllllIllIlIllIlIlIlI.method_23207();
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
        GL30C.glTexParameteri((int)3553, (int)10241, (int)lllllllllllllllllIllIlIllIlIIlIl.toOpenGL());
        GL30C.glTexParameteri((int)3553, (int)10240, (int)lllllllllllllllllIllIlIllIlIIlII.toOpenGL());
        ((Buffer)lllllllllllllllllIllIlIllIlIIIII).rewind();
        GL30C.glTexImage2D((int)3553, (int)0, (int)lllllllllllllllllIllIlIllIIlllll.toOpenGL(), (int)lllllllllllllllllIllIlIllIlIIIlI, (int)lllllllllllllllllIllIlIllIlIIIIl, (int)0, (int)lllllllllllllllllIllIlIllIIlllll.toOpenGL(), (int)5121, (ByteBuffer)lllllllllllllllllIllIlIllIlIIIII);
    }

    private void upload(int lllllllllllllllllIllIlIlllIIIIII, int lllllllllllllllllIllIlIllIllIlll, byte[] lllllllllllllllllIllIlIllIlllllI, Format lllllllllllllllllIllIlIllIllIlIl, Filter lllllllllllllllllIllIlIllIllIlII, Filter lllllllllllllllllIllIlIllIllIIll) {
        ByteTexture lllllllllllllllllIllIlIlllIIIIIl;
        ByteBuffer lllllllllllllllllIllIlIllIlllIlI = BufferUtils.createByteBuffer((int)lllllllllllllllllIllIlIllIlllllI.length).put(lllllllllllllllllIllIlIllIlllllI);
        lllllllllllllllllIllIlIlllIIIIIl.upload(lllllllllllllllllIllIlIlllIIIIII, lllllllllllllllllIllIlIllIllIlll, lllllllllllllllllIllIlIllIlllIlI, lllllllllllllllllIllIlIllIllIlIl, lllllllllllllllllIllIlIllIllIlII, lllllllllllllllllIllIlIllIllIIll);
    }

    public ByteTexture(int lllllllllllllllllIllIlIlllIlIllI, int lllllllllllllllllIllIlIlllIlIlIl, ByteBuffer lllllllllllllllllIllIlIlllIIllIl, Format lllllllllllllllllIllIlIlllIlIIll, Filter lllllllllllllllllIllIlIlllIIlIll, Filter lllllllllllllllllIllIlIlllIlIIIl) {
        ByteTexture lllllllllllllllllIllIlIlllIlIlll;
        if (!RenderSystem.isOnRenderThread()) {
            RenderSystem.recordRenderCall(() -> {
                ByteTexture lllllllllllllllllIllIlIllIIlIIll;
                lllllllllllllllllIllIlIllIIlIIll.upload(lllllllllllllllllIllIlIlllIlIllI, lllllllllllllllllIllIlIlllIlIlIl, lllllllllllllllllIllIlIlllIIllIl, lllllllllllllllllIllIlIlllIlIIll, lllllllllllllllllIllIlIlllIIlIll, lllllllllllllllllIllIlIlllIlIIIl);
            });
        } else {
            lllllllllllllllllIllIlIlllIlIlll.upload(lllllllllllllllllIllIlIlllIlIllI, lllllllllllllllllIllIlIlllIlIlIl, lllllllllllllllllIllIlIlllIIllIl, lllllllllllllllllIllIlIlllIlIIll, lllllllllllllllllIllIlIlllIIlIll, lllllllllllllllllIllIlIlllIlIIIl);
        }
    }

    public static enum Format {
        A,
        RGB,
        RGBA;


        public int toOpenGL() {
            Format lllllllllllllllllIIlIIIlIIlIIlII;
            switch (lllllllllllllllllIIlIIIlIIlIIlII) {
                case A: {
                    return 6406;
                }
                case RGB: {
                    return 6407;
                }
                case RGBA: {
                    return 6408;
                }
            }
            return 0;
        }

        private Format() {
            Format lllllllllllllllllIIlIIIlIIlIlIIl;
        }
    }

    public static enum Filter {
        Nearest,
        Linear;


        private Filter() {
            Filter lllllIlIlllIIlI;
        }

        public int toOpenGL() {
            Filter lllllIlIllIllIl;
            return lllllIlIllIllIl == Nearest ? 9728 : 9729;
        }
    }
}

