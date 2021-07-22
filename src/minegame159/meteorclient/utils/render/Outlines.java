/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_276
 *  net.minecraft.class_279
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_4618
 *  net.minecraft.class_761
 */
package minegame159.meteorclient.utils.render;

import java.io.IOException;
import minegame159.meteorclient.mixin.ShaderEffectAccessor;
import minegame159.meteorclient.mixin.WorldRendererAccessor;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_276;
import net.minecraft.class_279;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4618;
import net.minecraft.class_761;

public class Outlines {
    public static boolean loadingOutlineShader;
    public static class_4618 vertexConsumerProvider;
    public static boolean renderingOutlines;
    public static class_276 outlinesFbo;
    private static class_279 outlinesShader;

    public static void beginRender() {
        outlinesFbo.method_1230(class_310.field_1703);
        Utils.mc.method_1522().method_1235(false);
    }

    public static void load() {
        try {
            if (outlinesShader != null) {
                outlinesShader.close();
            }
            loadingOutlineShader = true;
            outlinesShader = new class_279(Utils.mc.method_1531(), Utils.mc.method_1478(), Utils.mc.method_1522(), new class_2960("meteor-client", "shaders/post/my_entity_outline.json"));
            outlinesShader.method_1259(Utils.mc.method_22683().method_4489(), Utils.mc.method_22683().method_4506());
            outlinesFbo = outlinesShader.method_1264("final");
            vertexConsumerProvider = new class_4618(Utils.mc.method_22940().method_23000());
            loadingOutlineShader = false;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static void endRender(float f) {
        class_761 class_7612 = Utils.mc.field_1769;
        WorldRendererAccessor worldRendererAccessor = (WorldRendererAccessor)class_7612;
        class_276 class_2763 = class_7612.method_22990();
        worldRendererAccessor.setEntityOutlinesFramebuffer(outlinesFbo);
        vertexConsumerProvider.method_23285();
        worldRendererAccessor.setEntityOutlinesFramebuffer(class_2763);
        outlinesShader.method_1258(f);
        Utils.mc.method_1522().method_1235(false);
    }

    public static void onResized(int n, int n2) {
        if (outlinesShader != null) {
            outlinesShader.method_1259(n, n2);
        }
    }

    public static void setUniform(String string, float f) {
        ((ShaderEffectAccessor)outlinesShader).getPasses().get(0).method_1295().method_1271(string).method_1251(f);
    }

    public static void renderFbo() {
        outlinesFbo.method_22594(Utils.mc.method_22683().method_4489(), Utils.mc.method_22683().method_4506(), false);
    }
}

