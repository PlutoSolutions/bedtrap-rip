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
import java.io.InputStream;
import java.io.InputStreamReader;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_2960;
import org.lwjgl.opengl.GL30C;

public class Shader {
    private final Object2IntMap<String> locations = new Object2IntOpenHashMap();
    private int id;

    public Shader(String string, String string2) {
        boolean bl;
        int n = GL30C.glCreateShader((int)35633);
        GL30C.glShaderSource((int)n, (CharSequence)this.read(string));
        GL30C.glCompileShader((int)n);
        boolean bl2 = bl = GL30C.glGetShaderi((int)n, (int)35713) == 1;
        if (!bl) {
            String string3 = GL30C.glGetShaderInfoLog((int)n);
            MeteorClient.LOG.error("Failed to compile vertex shader ({}):\n{}", (Object)string, (Object)string3);
            return;
        }
        int n2 = GL30C.glCreateShader((int)35632);
        GL30C.glShaderSource((int)n2, (CharSequence)this.read(string2));
        GL30C.glCompileShader((int)n2);
        boolean bl3 = bl = GL30C.glGetShaderi((int)n2, (int)35713) == 1;
        if (!bl) {
            String string4 = GL30C.glGetShaderInfoLog((int)n2);
            MeteorClient.LOG.error("Failed to compile fragment shader ({}):\n{}", (Object)string2, (Object)string4);
            return;
        }
        this.id = GL30C.glCreateProgram();
        GL30C.glAttachShader((int)this.id, (int)n);
        GL30C.glAttachShader((int)this.id, (int)n2);
        GL30C.glLinkProgram((int)this.id);
        GL30C.glDeleteShader((int)n);
        GL30C.glDeleteShader((int)n2);
    }

    private int getLocation(String string) {
        if (!this.locations.containsKey((Object)string)) {
            int n = GL30C.glGetUniformLocation((int)this.id, (CharSequence)string);
            this.locations.put((Object)string, n);
            return n;
        }
        return this.locations.getInt((Object)string);
    }

    public void unbind() {
        GL30C.glUseProgram((int)0);
    }

    public void set(String string, float f, float f2) {
        GL30C.glUniform2f((int)this.getLocation(string), (float)f, (float)f2);
    }

    private static void lambda$read$0(StringBuilder stringBuilder, String string) {
        stringBuilder.append(string).append('\n');
    }

    public void set(String string, int n) {
        GL30C.glUniform1i((int)this.getLocation(string), (int)n);
    }

    public void set(String string, float f) {
        GL30C.glUniform1f((int)this.getLocation(string), (float)f);
    }

    private String read(String string) {
        InputStream inputStream = Utils.mc.method_1478().method_14486(new class_2960("meteor-client", string)).method_14482();
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));){
            bufferedReader.lines().forEach(arg_0 -> Shader.lambda$read$0(stringBuilder, arg_0));
        }
    }

    public void set(String string, Color color) {
        this.set(string, (float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, (float)color.a / 255.0f);
    }

    public void set(String string, float f, float f2, float f3, float f4) {
        GL30C.glUniform4f((int)this.getLocation(string), (float)f, (float)f2, (float)f3, (float)f4);
    }

    public void bind() {
        GL30C.glUseProgram((int)this.id);
    }
}

