/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1158
 *  net.minecraft.class_1159
 *  net.minecraft.class_1160
 *  net.minecraft.class_1297
 *  net.minecraft.class_1542
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  net.minecraft.class_4493$class_4534
 *  net.minecraft.class_4493$class_4535
 *  net.minecraft.class_4587
 *  org.lwjgl.opengl.GL11
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
    private static final /* synthetic */ class_310 mc;

    public static void applyCameraRots() {
        class_4184 llIIllIIIllIIl = Render3DUtils.mc.method_1561().field_4686;
        GL11.glRotated((double)class_3532.method_15393((float)llIIllIIIllIIl.method_19329()), (double)1.0, (double)0.0, (double)0.0);
        GL11.glRotated((double)class_3532.method_15338((double)((double)llIIllIIIllIIl.method_19330() + 180.0)), (double)0.0, (double)1.0, (double)0.0);
    }

    static {
        mc = class_310.method_1551();
    }

    public static class_243 getRenderPosition(double llIIllIIlllIll, double llIIllIIlllIlI, double llIIllIIlllIIl) {
        double llIIllIIlllllI = llIIllIIlllIll - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1352;
        double llIIllIIllllIl = llIIllIIlllIlI - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1351;
        double llIIllIIllllII = llIIllIIlllIIl - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(llIIllIIlllllI, llIIllIIllllIl, llIIllIIllllII);
    }

    public static void drawBoxOutline(class_4587 llIIlIllIllIll, class_238 llIIlIllIlIlll, Color llIIlIllIlIllI) {
        Render3DUtils.setup3DRender(true);
        RenderSystem.lineWidth((float)1.0f);
        Render3DUtils.drawOutlineBox(llIIlIllIllIll, llIIlIllIlIlll, llIIlIllIlIllI);
        Render3DUtils.end3DRender();
    }

    public static void setup3DRender(boolean llIIllIIIlIlIl) {
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((class_4493.class_4535)class_4493.class_4535.field_22541, (class_4493.class_4534)class_4493.class_4534.field_22523, (class_4493.class_4535)class_4493.class_4535.field_22534, (class_4493.class_4534)class_4493.class_4534.field_22527);
        if (llIIllIIIlIlIl) {
            RenderSystem.disableDepthTest();
        }
        RenderSystem.depthMask((boolean)class_310.method_29611());
        RenderSystem.enableCull();
    }

    public static class_243 getRenderPosition(class_243 llIIllIIlIllIl) {
        double llIIllIIllIIII = llIIllIIlIllIl.method_10216() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1352;
        double llIIllIIlIllll = llIIllIIlIllIl.method_10214() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1351;
        double llIIllIIlIlllI = llIIllIIlIllIl.method_10215() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(llIIllIIllIIII, llIIllIIlIllll, llIIllIIlIlllI);
    }

    public static double interpolate(double llIIlIlIlIIIlI, double llIIlIlIIllllI, double llIIlIlIIlllIl) {
        return llIIlIlIIllllI + (llIIlIlIlIIIlI - llIIlIlIIllllI) * llIIlIlIIlllIl;
    }

    public static void fixCameraRots() {
        class_4184 llIIllIIIlllII = Render3DUtils.mc.method_1561().field_4686;
        GL11.glRotated((double)(-class_3532.method_15338((double)((double)llIIllIIIlllII.method_19330() + 180.0))), (double)0.0, (double)1.0, (double)0.0);
        GL11.glRotated((double)(-class_3532.method_15393((float)llIIllIIIlllII.method_19329())), (double)1.0, (double)0.0, (double)0.0);
    }

    public static void drawEntityBox(class_4587 llIIlIllIIIIlI, class_1297 llIIlIllIIIIIl, float llIIlIllIIIlIl, Color llIIlIllIIIlII) {
        class_243 llIIlIllIIIIll = Render3DUtils.getEntityRenderPosition(llIIlIllIIIIIl, llIIlIllIIIlIl);
        Render3DUtils.drawEntityBox(llIIlIllIIIIlI, llIIlIllIIIIIl, llIIlIllIIIIll.field_1352, llIIlIllIIIIll.field_1351, llIIlIllIIIIll.field_1350, llIIlIllIIIlII);
    }

    public static void drawBox(class_4587 llIIlIlllIIIIl, class_238 llIIlIlllIIIII, Color llIIlIllIlllll) {
        Render3DUtils.setup3DRender(true);
        Render3DUtils.drawFilledBox(llIIlIlllIIIIl, llIIlIlllIIIII, llIIlIllIlllll);
        RenderSystem.lineWidth((float)1.0f);
        Render3DUtils.drawOutlineBox(llIIlIlllIIIIl, llIIlIlllIIIII, llIIlIllIlllll);
        Render3DUtils.end3DRender();
    }

    public Render3DUtils() {
        Render3DUtils llIIllIlIllIII;
    }

    public static void drawBoxInside(class_4587 llIIlIllIlIIlI, class_238 llIIlIllIIlllI, Color llIIlIllIIllIl) {
        Render3DUtils.setup3DRender(true);
        Render3DUtils.drawFilledBox(llIIlIllIlIIlI, llIIlIllIIlllI, llIIlIllIIllIl);
        Render3DUtils.end3DRender();
    }

    public static class_243 getEntityRenderPosition(class_1297 llIIllIlIlIIIl, double llIIllIlIIlIll) {
        double llIIllIlIIllll = llIIllIlIlIIIl.field_6014 + (llIIllIlIlIIIl.method_23317() - llIIllIlIlIIIl.field_6014) * llIIllIlIIlIll - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1352;
        double llIIllIlIIlllI = llIIllIlIlIIIl.field_6036 + (llIIllIlIlIIIl.method_23318() - llIIllIlIlIIIl.field_6036) * llIIllIlIIlIll - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1351;
        double llIIllIlIIllIl = llIIllIlIlIIIl.field_5969 + (llIIllIlIlIIIl.method_23321() - llIIllIlIlIIIl.field_5969) * llIIllIlIIlIll - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(llIIllIlIIllll, llIIllIlIIlllI, llIIllIlIIllIl);
    }

    public static void drawSphere(class_4587 llIIlIllllIllI, float llIIlIllllIlIl, int llIIlIllllllIl, Color llIIlIllllIIll, boolean llIIlIllllIIlI, class_243 llIIlIlllllIlI) {
        class_1159 llIIlIlllllIIl = llIIlIllllIllI.method_23760().method_23761();
        float llIIlIlllllIII = 3.141592f;
        Render3DUtils.setup3DRender(!llIIlIllllIIlI);
        float llIIlIllllIlll = 0.0f;
        while ((double)llIIlIllllIlll < Math.PI) {
            class_287 llIIllIIIIIIIl = class_289.method_1348().method_1349();
            llIIllIIIIIIIl.method_1328(1, class_290.field_1576);
            float llIIllIIIIIIII = 0.0f;
            while ((double)llIIllIIIIIIII < 6.314601203754922) {
                float llIIllIIIIIlII = (float)(llIIlIlllllIlI.method_10216() + (double)llIIlIllllIlIl * Math.cos(llIIllIIIIIIII) * Math.sin(llIIlIllllIlll));
                float llIIllIIIIIIll = (float)(llIIlIlllllIlI.method_10214() + (double)llIIlIllllIlIl * Math.sin(llIIllIIIIIIII) * Math.sin(llIIlIllllIlll));
                float llIIllIIIIIIlI = (float)(llIIlIlllllIlI.method_10215() + (double)llIIlIllllIlIl * Math.cos(llIIlIllllIlll));
                class_243 llIIllIIIIIlIl = Render3DUtils.getRenderPosition(llIIllIIIIIlII, llIIllIIIIIIll, llIIllIIIIIIlI);
                llIIllIIIIIIIl.method_22918(llIIlIlllllIIl, (float)llIIllIIIIIlIl.field_1352, (float)llIIllIIIIIlIl.field_1351, (float)llIIllIIIIIlIl.field_1350).method_1336(llIIlIllllIIll.r, llIIlIllllIIll.g, llIIlIllllIIll.b, llIIlIllllIIll.a).method_1344();
                llIIllIIIIIlII = (float)(llIIlIlllllIlI.method_10216() + (double)llIIlIllllIlIl * Math.cos(llIIllIIIIIIII) * Math.sin(llIIlIllllIlll + 3.141592f / (float)llIIlIllllllIl));
                llIIllIIIIIIll = (float)(llIIlIlllllIlI.method_10214() + (double)llIIlIllllIlIl * Math.sin(llIIllIIIIIIII) * Math.sin(llIIlIllllIlll + 3.141592f / (float)llIIlIllllllIl));
                llIIllIIIIIIlI = (float)(llIIlIlllllIlI.method_10215() + (double)llIIlIllllIlIl * Math.cos(llIIlIllllIlll + 3.141592f / (float)llIIlIllllllIl));
                llIIllIIIIIlIl = Render3DUtils.getRenderPosition(llIIllIIIIIlII, llIIllIIIIIIll, llIIllIIIIIIlI);
                llIIllIIIIIIIl.method_22918(llIIlIlllllIIl, (float)llIIllIIIIIlIl.field_1352, (float)llIIllIIIIIlIl.field_1351, (float)llIIllIIIIIlIl.field_1350).method_1336(llIIlIllllIIll.r, llIIlIllllIIll.g, llIIlIllllIIll.b, llIIlIllllIIll.a).method_1344();
                llIIllIIIIIIII += 3.141592f / (float)llIIlIllllllIl;
            }
            llIIllIIIIIIIl.method_1326();
            class_286.method_1309((class_287)llIIllIIIIIIIl);
            llIIlIllllIlll += 3.141592f / (float)llIIlIllllllIl;
        }
        Render3DUtils.end3DRender();
    }

    public static void drawFilledBox(class_4587 llIIlIlIIlIIIl, class_238 llIIlIlIIlIIII, Color llIIlIlIIIllll) {
        class_1159 llIIlIlIIIlllI = llIIlIlIIlIIIl.method_23760().method_23761();
        class_287 llIIlIlIIIllIl = class_289.method_1348().method_1349();
        llIIlIlIIIllIl.method_1328(7, class_290.field_1576);
        float llIIlIlIIIllII = (float)llIIlIlIIlIIII.field_1323;
        float llIIlIlIIIlIll = (float)llIIlIlIIlIIII.field_1322;
        float llIIlIlIIIlIlI = (float)llIIlIlIIlIIII.field_1321;
        float llIIlIlIIIlIIl = (float)llIIlIlIIlIIII.field_1320;
        float llIIlIlIIIlIII = (float)llIIlIlIIlIIII.field_1325;
        float llIIlIlIIIIlll = (float)llIIlIlIIlIIII.field_1324;
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIll, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIll, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIll, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIll, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIII, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIII, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIII, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIII, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIll, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIII, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIII, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIll, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIll, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIII, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIII, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIll, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIll, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIll, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIlIIl, llIIlIlIIIlIII, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIII, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIll, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIll, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIII, llIIlIlIIIIlll).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_22918(llIIlIlIIIlllI, llIIlIlIIIllII, llIIlIlIIIlIII, llIIlIlIIIlIlI).method_1336(llIIlIlIIIllll.r, llIIlIlIIIllll.g, llIIlIlIIIllll.b, llIIlIlIIIllll.a).method_1344();
        llIIlIlIIIllIl.method_1326();
        class_286.method_1309((class_287)llIIlIlIIIllIl);
    }

    public static void end3DRender() {
        RenderSystem.enableTexture();
        RenderSystem.disableCull();
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
    }

    public static void drawOutlineBox(class_4587 llIIlIIllIllll, class_238 llIIlIIllIlllI, Color llIIlIIlllIIll) {
        class_1159 llIIlIIlllIIlI = llIIlIIllIllll.method_23760().method_23761();
        class_287 llIIlIIlllIIIl = class_289.method_1348().method_1349();
        llIIlIIlllIIIl.method_1328(1, class_290.field_1576);
        class_265 llIIlIIlllIIII = class_259.method_1078((class_238)llIIlIIllIlllI);
        llIIlIIlllIIII.method_1104((llIIlIIlIlllIl, llIIlIIlIlIIll, llIIlIIlIllIll, llIIlIIlIllIlI, llIIlIIlIlIIII, llIIlIIlIllIII) -> {
            llIIlIIlllIIIl.method_22918(llIIlIIlllIIlI, (float)llIIlIIlIlllIl, (float)llIIlIIlIlIIll, (float)llIIlIIlIllIll).method_1336(llIIlIIlIllllI.r, llIIlIIlIllllI.g, llIIlIIlIllllI.b, llIIlIIlIllllI.a).method_1344();
            llIIlIIlllIIIl.method_22918(llIIlIIlllIIlI, (float)llIIlIIlIllIlI, (float)llIIlIIlIlIIII, (float)llIIlIIlIllIII).method_1336(llIIlIIlIllllI.r, llIIlIIlIllllI.g, llIIlIIlIllllI.b, llIIlIIlIllllI.a).method_1344();
        });
        llIIlIIlllIIIl.method_1326();
        class_286.method_1309((class_287)llIIlIIlllIIIl);
    }

    public static class_243 getRenderPosition(class_2338 llIIllIIlIIIIl) {
        double llIIllIIlIIlII = (double)llIIllIIlIIIIl.method_10263() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1352;
        double llIIllIIlIIIll = (double)llIIllIIlIIIIl.method_10264() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1351;
        double llIIllIIlIIIlI = (double)llIIllIIlIIIIl.method_10260() - Render3DUtils.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(llIIllIIlIIlII, llIIllIIlIIIll, llIIllIIlIIIlI);
    }

    public static void drawEntityBox(class_4587 llIIlIlIlIllIl, class_1297 llIIlIlIlIllII, double llIIlIlIlIlIll, double llIIlIlIlIlIlI, double llIIlIlIllIIIl, Color llIIlIlIlIlIII) {
        float llIIlIlIlIllll = class_3532.method_17821((float)mc.method_1488(), (float)llIIlIlIlIllII.field_5982, (float)llIIlIlIlIllII.field_6031);
        Render3DUtils.setup3DRender(true);
        llIIlIlIlIllIl.method_22904(llIIlIlIlIlIll, llIIlIlIlIlIlI, llIIlIlIllIIIl);
        llIIlIlIlIllIl.method_22907(new class_1158(new class_1160(0.0f, -1.0f, 0.0f), llIIlIlIlIllll, true));
        llIIlIlIlIllIl.method_22904(-llIIlIlIlIlIll, -llIIlIlIlIlIlI, -llIIlIlIllIIIl);
        class_238 llIIlIlIlIlllI = new class_238(llIIlIlIlIlIll - (double)llIIlIlIlIllII.method_17681() + 0.25, llIIlIlIlIlIlI, llIIlIlIllIIIl - (double)llIIlIlIlIllII.method_17681() + 0.25, llIIlIlIlIlIll + (double)llIIlIlIlIllII.method_17681() - 0.25, llIIlIlIlIlIlI + (double)llIIlIlIlIllII.method_17682() + 0.1, llIIlIlIllIIIl + (double)llIIlIlIlIllII.method_17681() - 0.25);
        if (llIIlIlIlIllII instanceof class_1542) {
            llIIlIlIlIlllI = new class_238(llIIlIlIlIlIll - 0.15, llIIlIlIlIlIlI + (double)0.1f, llIIlIlIllIIIl - 0.15, llIIlIlIlIlIll + 0.15, llIIlIlIlIlIlI + 0.5, llIIlIlIllIIIl + 0.15);
        }
        Render3DUtils.drawFilledBox(llIIlIlIlIllIl, llIIlIlIlIlllI, llIIlIlIlIlIII);
        RenderSystem.lineWidth((float)1.0f);
        Render3DUtils.drawOutlineBox(llIIlIlIlIllIl, llIIlIlIlIlllI, llIIlIlIlIlIII);
        Render3DUtils.end3DRender();
        llIIlIlIlIllIl.method_22904(llIIlIlIlIlIll, llIIlIlIlIlIlI, llIIlIlIllIIIl);
        llIIlIlIlIllIl.method_22907(new class_1158(new class_1160(0.0f, 1.0f, 0.0f), llIIlIlIlIllll, true));
        llIIlIlIlIllIl.method_22904(-llIIlIlIlIlIll, -llIIlIlIlIlIlI, -llIIlIlIllIIIl);
    }
}

