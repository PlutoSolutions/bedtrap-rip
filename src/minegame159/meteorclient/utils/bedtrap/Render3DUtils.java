/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.bedtrap;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1158;
import net.minecraft.class_1159;
import net.minecraft.class_1160;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4493;
import net.minecraft.class_4587;
import org.lwjgl.opengl.GL11;

public class Render3DUtils {
    private static final class_310 mc = class_310.method_1551();

    public static void applyCameraRots() {
        class_4184 class_41842 = Render3DUtils.mc.method_1561().field_4686;
        GL11.glRotated((double)class_3532.method_15393((float)class_41842.method_19329()), (double)1.0, (double)0.0, (double)0.0);
        GL11.glRotated((double)class_3532.method_15338((double)((double)class_41842.method_19330() + 180.0)), (double)0.0, (double)1.0, (double)0.0);
    }

    public static class_243 getRenderPosition(double d, double d2, double d3) {
        double d4 = d - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1352;
        double d5 = d2 - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1351;
        double d6 = d3 - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(d4, d5, d6);
    }

    public static void drawBoxOutline(class_4587 class_45872, class_238 class_2383, Color color) {
        Render3DUtils.setup3DRender(true);
        RenderSystem.lineWidth((float)1.0f);
        Render3DUtils.drawOutlineBox(class_45872, class_2383, color);
        Render3DUtils.end3DRender();
    }

    public static void setup3DRender(boolean bl) {
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((class_4493.class_4535)class_4493.class_4535.field_22541, (class_4493.class_4534)class_4493.class_4534.field_22523, (class_4493.class_4535)class_4493.class_4535.field_22534, (class_4493.class_4534)class_4493.class_4534.field_22527);
        if (bl) {
            RenderSystem.disableDepthTest();
        }
        RenderSystem.depthMask((boolean)class_310.method_29611());
        RenderSystem.enableCull();
    }

    public static class_243 getRenderPosition(class_243 class_2432) {
        double d = class_2432.method_10216() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1352;
        double d2 = class_2432.method_10214() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1351;
        double d3 = class_2432.method_10215() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(d, d2, d3);
    }

    public static double interpolate(double d, double d2, double d3) {
        return d2 + (d - d2) * d3;
    }

    public static void fixCameraRots() {
        class_4184 class_41842 = Render3DUtils.mc.method_1561().field_4686;
        GL11.glRotated((double)(-class_3532.method_15338((double)((double)class_41842.method_19330() + 180.0))), (double)0.0, (double)1.0, (double)0.0);
        GL11.glRotated((double)(-class_3532.method_15393((float)class_41842.method_19329())), (double)1.0, (double)0.0, (double)0.0);
    }

    public static void drawEntityBox(class_4587 class_45872, class_1297 class_12972, float f, Color color) {
        class_243 class_2432 = Render3DUtils.getEntityRenderPosition(class_12972, f);
        Render3DUtils.drawEntityBox(class_45872, class_12972, class_2432.field_1352, class_2432.field_1351, class_2432.field_1350, color);
    }

    public static void drawBox(class_4587 class_45872, class_238 class_2383, Color color) {
        Render3DUtils.setup3DRender(true);
        Render3DUtils.drawFilledBox(class_45872, class_2383, color);
        RenderSystem.lineWidth((float)1.0f);
        Render3DUtils.drawOutlineBox(class_45872, class_2383, color);
        Render3DUtils.end3DRender();
    }

    public static void drawBoxInside(class_4587 class_45872, class_238 class_2383, Color color) {
        Render3DUtils.setup3DRender(true);
        Render3DUtils.drawFilledBox(class_45872, class_2383, color);
        Render3DUtils.end3DRender();
    }

    public static class_243 getEntityRenderPosition(class_1297 class_12972, double d) {
        double d2 = class_12972.field_6014 + (class_12972.method_23317() - class_12972.field_6014) * d - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1352;
        double d3 = class_12972.field_6036 + (class_12972.method_23318() - class_12972.field_6036) * d - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1351;
        double d4 = class_12972.field_5969 + (class_12972.method_23321() - class_12972.field_5969) * d - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(d2, d3, d4);
    }

    public static void drawSphere(class_4587 class_45872, float f, int n, Color color, boolean bl, class_243 class_2432) {
        class_1159 class_11592 = class_45872.method_23760().method_23761();
        float f2 = 3.141592f;
        Render3DUtils.setup3DRender(!bl);
        float f3 = 0.0f;
        while ((double)f3 < Math.PI) {
            class_287 class_2872 = class_289.method_1348().method_1349();
            class_2872.method_1328(1, class_290.field_1576);
            float f4 = 0.0f;
            while ((double)f4 < 6.314601203754922) {
                float f5 = (float)(class_2432.method_10216() + (double)f * Math.cos(f4) * Math.sin(f3));
                float f6 = (float)(class_2432.method_10214() + (double)f * Math.sin(f4) * Math.sin(f3));
                float f7 = (float)(class_2432.method_10215() + (double)f * Math.cos(f3));
                class_243 class_2433 = Render3DUtils.getRenderPosition(f5, f6, f7);
                class_2872.method_22918(class_11592, (float)class_2433.field_1352, (float)class_2433.field_1351, (float)class_2433.field_1350).method_1336(color.r, color.g, color.b, color.a).method_1344();
                f5 = (float)(class_2432.method_10216() + (double)f * Math.cos(f4) * Math.sin(f3 + 3.141592f / (float)n));
                f6 = (float)(class_2432.method_10214() + (double)f * Math.sin(f4) * Math.sin(f3 + 3.141592f / (float)n));
                f7 = (float)(class_2432.method_10215() + (double)f * Math.cos(f3 + 3.141592f / (float)n));
                class_2433 = Render3DUtils.getRenderPosition(f5, f6, f7);
                class_2872.method_22918(class_11592, (float)class_2433.field_1352, (float)class_2433.field_1351, (float)class_2433.field_1350).method_1336(color.r, color.g, color.b, color.a).method_1344();
                f4 += 3.141592f / (float)n;
            }
            class_2872.method_1326();
            class_286.method_1309((class_287)class_2872);
            f3 += 3.141592f / (float)n;
        }
        Render3DUtils.end3DRender();
    }

    public static void drawFilledBox(class_4587 class_45872, class_238 class_2383, Color color) {
        class_1159 class_11592 = class_45872.method_23760().method_23761();
        class_287 class_2872 = class_289.method_1348().method_1349();
        class_2872.method_1328(7, class_290.field_1576);
        float f = (float)class_2383.field_1323;
        float f2 = (float)class_2383.field_1322;
        float f3 = (float)class_2383.field_1321;
        float f4 = (float)class_2383.field_1320;
        float f5 = (float)class_2383.field_1325;
        float f6 = (float)class_2383.field_1324;
        class_2872.method_22918(class_11592, f, f2, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f2, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f2, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f2, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f5, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f5, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f5, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f5, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f2, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f5, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f5, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f2, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f2, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f5, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f5, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f2, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f2, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f2, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f4, f5, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f5, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f2, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f2, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f5, f6).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, f, f5, f3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_1326();
        class_286.method_1309((class_287)class_2872);
    }

    public static void end3DRender() {
        RenderSystem.enableTexture();
        RenderSystem.disableCull();
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
    }

    public static void drawOutlineBox(class_4587 class_45872, class_238 class_2383, Color color) {
        class_1159 class_11592 = class_45872.method_23760().method_23761();
        class_287 class_2872 = class_289.method_1348().method_1349();
        class_2872.method_1328(1, class_290.field_1576);
        class_265 class_2652 = class_259.method_1078((class_238)class_2383);
        class_2652.method_1104((arg_0, arg_1, arg_2, arg_3, arg_4, arg_5) -> Render3DUtils.lambda$drawOutlineBox$0(class_2872, class_11592, color, arg_0, arg_1, arg_2, arg_3, arg_4, arg_5));
        class_2872.method_1326();
        class_286.method_1309((class_287)class_2872);
    }

    public static class_243 getRenderPosition(class_2338 class_23382) {
        double d = (double)class_23382.method_10263() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1352;
        double d2 = (double)class_23382.method_10264() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1351;
        double d3 = (double)class_23382.method_10260() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(d, d2, d3);
    }

    private static void lambda$drawOutlineBox$0(class_287 class_2872, class_1159 class_11592, Color color, double d, double d2, double d3, double d4, double d5, double d6) {
        class_2872.method_22918(class_11592, (float)d, (float)d2, (float)d3).method_1336(color.r, color.g, color.b, color.a).method_1344();
        class_2872.method_22918(class_11592, (float)d4, (float)d5, (float)d6).method_1336(color.r, color.g, color.b, color.a).method_1344();
    }

    public static void drawEntityBox(class_4587 class_45872, class_1297 class_12972, double d, double d2, double d3, Color color) {
        float f = class_3532.method_17821((float)mc.method_1488(), (float)class_12972.field_5982, (float)class_12972.field_6031);
        Render3DUtils.setup3DRender(true);
        class_45872.method_22904(d, d2, d3);
        class_45872.method_22907(new class_1158(new class_1160(0.0f, -1.0f, 0.0f), f, true));
        class_45872.method_22904(-d, -d2, -d3);
        class_238 class_2383 = new class_238(d - (double)class_12972.method_17681() + 0.25, d2, d3 - (double)class_12972.method_17681() + 0.25, d + (double)class_12972.method_17681() - 0.25, d2 + (double)class_12972.method_17682() + 0.1, d3 + (double)class_12972.method_17681() - 0.25);
        if (class_12972 instanceof class_1542) {
            class_2383 = new class_238(d - 0.15, d2 + (double)0.1f, d3 - 0.15, d + 0.15, d2 + 0.5, d3 + 0.15);
        }
        Render3DUtils.drawFilledBox(class_45872, class_2383, color);
        RenderSystem.lineWidth((float)1.0f);
        Render3DUtils.drawOutlineBox(class_45872, class_2383, color);
        Render3DUtils.end3DRender();
        class_45872.method_22904(d, d2, d3);
        class_45872.method_22907(new class_1158(new class_1160(0.0f, 1.0f, 0.0f), f, true));
        class_45872.method_22904(-d, -d2, -d3);
    }
}

