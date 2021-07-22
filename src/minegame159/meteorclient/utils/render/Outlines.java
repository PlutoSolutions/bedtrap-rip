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
    public static /* synthetic */ boolean loadingOutlineShader;
    public static /* synthetic */ class_4618 vertexConsumerProvider;
    public static /* synthetic */ boolean renderingOutlines;
    public static /* synthetic */ class_276 outlinesFbo;
    private static /* synthetic */ class_279 outlinesShader;

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
        catch (IOException llllllllllllllllllllllllIlIIIlII) {
            llllllllllllllllllllllllIlIIIlII.printStackTrace();
        }
    }

    public static void endRender(float llllllllllllllllllllllllIIlllllI) {
        class_761 llllllllllllllllllllllllIIllllIl = Utils.mc.field_1769;
        WorldRendererAccessor llllllllllllllllllllllllIIllllII = (WorldRendererAccessor)llllllllllllllllllllllllIIllllIl;
        class_276 llllllllllllllllllllllllIIlllIll = llllllllllllllllllllllllIIllllIl.method_22990();
        llllllllllllllllllllllllIIllllII.setEntityOutlinesFramebuffer(outlinesFbo);
        vertexConsumerProvider.method_23285();
        llllllllllllllllllllllllIIllllII.setEntityOutlinesFramebuffer(llllllllllllllllllllllllIIlllIll);
        outlinesShader.method_1258(llllllllllllllllllllllllIIlllllI);
        Utils.mc.method_1522().method_1235(false);
    }

    public static void onResized(int llllllllllllllllllllllllIIllIlII, int llllllllllllllllllllllllIIllIIIl) {
        if (outlinesShader != null) {
            outlinesShader.method_1259(llllllllllllllllllllllllIIllIlII, llllllllllllllllllllllllIIllIIIl);
        }
    }

    public static void setUniform(String llllllllllllllllllllllllIIlIlllI, float llllllllllllllllllllllllIIlIllIl) {
        ((ShaderEffectAccessor)outlinesShader).getPasses().get(0).method_1295().method_1271(llllllllllllllllllllllllIIlIlllI).method_1251(llllllllllllllllllllllllIIlIllIl);
    }

    public static void renderFbo() {
        outlinesFbo.method_22594(Utils.mc.method_22683().method_4489(), Utils.mc.method_22683().method_4506(), false);
    }

    public Outlines() {
        Outlines llllllllllllllllllllllllIlIIIllI;
    }
}

