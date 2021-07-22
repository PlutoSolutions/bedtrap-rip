/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  net.minecraft.class_2960
 *  org.lwjgl.opengl.GL30C
 */
package minegame159.meteorclient.rendering.gl;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_2960;
import org.lwjgl.opengl.GL30C;

public class Shader {
    private final /* synthetic */ Object2IntMap<String> locations;
    private /* synthetic */ int id;

    public Shader(String lIIlllIIIIlIIlI, String lIIlllIIIIlIIIl) {
        boolean lIIlllIIIIIllll;
        Shader lIIlllIIIIlIIll;
        lIIlllIIIIlIIll.locations = new Object2IntOpenHashMap();
        int lIIlllIIIIlIIII = GL30C.glCreateShader((int)35633);
        GL30C.glShaderSource((int)lIIlllIIIIlIIII, (CharSequence)lIIlllIIIIlIIll.read(lIIlllIIIIlIIlI));
        GL30C.glCompileShader((int)lIIlllIIIIlIIII);
        boolean bl = lIIlllIIIIIllll = GL30C.glGetShaderi((int)lIIlllIIIIlIIII, (int)35713) == 1;
        if (!lIIlllIIIIIllll) {
            String lIIlllIIIIlIlIl = GL30C.glGetShaderInfoLog((int)lIIlllIIIIlIIII);
            MeteorClient.LOG.error("Failed to compile vertex shader ({}):\n{}", (Object)lIIlllIIIIlIIlI, (Object)lIIlllIIIIlIlIl);
            return;
        }
        int lIIlllIIIIIlllI = GL30C.glCreateShader((int)35632);
        GL30C.glShaderSource((int)lIIlllIIIIIlllI, (CharSequence)lIIlllIIIIlIIll.read(lIIlllIIIIlIIIl));
        GL30C.glCompileShader((int)lIIlllIIIIIlllI);
        boolean bl2 = lIIlllIIIIIllll = GL30C.glGetShaderi((int)lIIlllIIIIIlllI, (int)35713) == 1;
        if (!lIIlllIIIIIllll) {
            String lIIlllIIIIlIlII = GL30C.glGetShaderInfoLog((int)lIIlllIIIIIlllI);
            MeteorClient.LOG.error("Failed to compile fragment shader ({}):\n{}", (Object)lIIlllIIIIlIIIl, (Object)lIIlllIIIIlIlII);
            return;
        }
        lIIlllIIIIlIIll.id = GL30C.glCreateProgram();
        GL30C.glAttachShader((int)lIIlllIIIIlIIll.id, (int)lIIlllIIIIlIIII);
        GL30C.glAttachShader((int)lIIlllIIIIlIIll.id, (int)lIIlllIIIIIlllI);
        GL30C.glLinkProgram((int)lIIlllIIIIlIIll.id);
        GL30C.glDeleteShader((int)lIIlllIIIIlIIII);
        GL30C.glDeleteShader((int)lIIlllIIIIIlllI);
    }

    private int getLocation(String lIIllIllllllIll) {
        Shader lIIllIllllllllI;
        if (!lIIllIllllllllI.locations.containsKey((Object)lIIllIllllllIll)) {
            int lIIllIlllllllll = GL30C.glGetUniformLocation((int)lIIllIllllllllI.id, (CharSequence)lIIllIllllllIll);
            lIIllIllllllllI.locations.put((Object)lIIllIllllllIll, lIIllIlllllllll);
            return lIIllIlllllllll;
        }
        return lIIllIllllllllI.locations.getInt((Object)lIIllIllllllIll);
    }

    public void unbind() {
        GL30C.glUseProgram((int)0);
    }

    public void set(String lIIllIlllIllllI, float lIIllIlllIlllIl, float lIIllIllllIIIII) {
        Shader lIIllIlllIlllll;
        GL30C.glUniform2f((int)lIIllIlllIlllll.getLocation(lIIllIlllIllllI), (float)lIIllIlllIlllIl, (float)lIIllIllllIIIII);
    }

    public void set(String lIIllIlllllIlIl, int lIIllIlllllIlII) {
        Shader lIIllIlllllIIll;
        GL30C.glUniform1i((int)lIIllIlllllIIll.getLocation(lIIllIlllllIlIl), (int)lIIllIlllllIlII);
    }

    public void set(String lIIllIllllIllII, float lIIllIllllIlIII) {
        Shader lIIllIllllIlIlI;
        GL30C.glUniform1f((int)lIIllIllllIlIlI.getLocation(lIIllIllllIllII), (float)lIIllIllllIlIII);
    }

    private String read(String lIIllIllIllIlIl) {
        try {
            InputStream lIIllIllIlllIIl = Utils.mc.method_1478().method_14486(new class_2960("meteor-client", lIIllIllIllIlIl)).method_14482();
            StringBuilder lIIllIllIlllIII = new StringBuilder();
            try (BufferedReader lIIllIllIlllIlI = new BufferedReader(new InputStreamReader(lIIllIllIlllIIl));){
                lIIllIllIlllIlI.lines().forEach(lIIllIllIlIlIIl -> lIIllIllIlllIII.append((String)lIIllIllIlIlIIl).append('\n'));
            }
            return String.valueOf(lIIllIllIlllIII);
        }
        catch (IOException lIIllIllIllIlll) {
            lIIllIllIllIlll.printStackTrace();
            return "";
        }
    }

    public void set(String lIIllIlllIIIlIl, Color lIIllIlllIIIIIl) {
        Shader lIIllIlllIIIIll;
        lIIllIlllIIIIll.set(lIIllIlllIIIlIl, (float)lIIllIlllIIIIIl.r / 255.0f, (float)lIIllIlllIIIIIl.g / 255.0f, (float)lIIllIlllIIIIIl.b / 255.0f, (float)lIIllIlllIIIIIl.a / 255.0f);
    }

    public void set(String lIIllIlllIIlllI, float lIIllIlllIIllIl, float lIIllIlllIlIIlI, float lIIllIlllIIlIll, float lIIllIlllIlIIII) {
        Shader lIIllIlllIlIlIl;
        GL30C.glUniform4f((int)lIIllIlllIlIlIl.getLocation(lIIllIlllIIlllI), (float)lIIllIlllIIllIl, (float)lIIllIlllIlIIlI, (float)lIIllIlllIIlIll, (float)lIIllIlllIlIIII);
    }

    public void bind() {
        Shader lIIlllIIIIIIlIl;
        GL30C.glUseProgram((int)lIIlllIIIIIIlIl.id);
    }
}

