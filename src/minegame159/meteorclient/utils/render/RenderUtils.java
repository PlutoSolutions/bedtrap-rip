/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1799
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_2586
 *  net.minecraft.class_308
 */
package minegame159.meteorclient.utils.render;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.FreeLook;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.Target;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_308;

public class RenderUtils {
    public static void drawTracerToPos(class_2338 lIlIIIIIIIIIIII, Color lIIllllllllllII, RenderEvent lIIlllllllllIll) {
        RenderUtils.drawLine(RenderUtils.getCameraVector(), (double)lIlIIIIIIIIIIII.method_10263() + 0.5, (double)lIlIIIIIIIIIIII.method_10264() + 0.5, (float)lIlIIIIIIIIIIII.method_10260() + 0.5f, lIIllllllllllII, lIIlllllllllIll);
    }

    public static void drawTracerToBlockEntity(class_2586 lIIlllllllIIIlI, Color lIIlllllllIIlII, RenderEvent lIIlllllllIIIll) {
        RenderUtils.drawTracerToPos(lIIlllllllIIIlI.method_11016(), lIIlllllllIIlII, lIIlllllllIIIll);
    }

    public static class_243 getCameraVector() {
        boolean lIlIIIIIIlIIIII = Modules.get().isActive(Freecam.class) || Modules.get().get(FreeLook.class).playerMode();
        return new class_243(0.0, 0.0, lIlIIIIIIlIIIII ? 1.0 : 75.0).method_1037(-((float)Math.toRadians(Utils.mc.field_1773.method_19418().method_19329()))).method_1024(-((float)Math.toRadians(Utils.mc.field_1773.method_19418().method_19330()))).method_1019(Utils.mc.field_1773.method_19418().method_19326());
    }

    public static void drawTracerToEntity(RenderEvent lIlIIIIIIIlIlIl, class_1297 lIlIIIIIIIlIlII, Color lIlIIIIIIIIlIlI, Target lIlIIIIIIIIlIIl, boolean lIlIIIIIIIIlIII) {
        double lIlIIIIIIIlIIII = lIlIIIIIIIlIlII.field_6014 + (lIlIIIIIIIlIlII.method_23317() - lIlIIIIIIIlIlII.field_6014) * (double)lIlIIIIIIIlIlIl.tickDelta;
        double lIlIIIIIIIIllll = lIlIIIIIIIlIlII.field_6036 + (lIlIIIIIIIlIlII.method_23318() - lIlIIIIIIIlIlII.field_6036) * (double)lIlIIIIIIIlIlIl.tickDelta;
        double lIlIIIIIIIIlllI = lIlIIIIIIIlIlII.field_5969 + (lIlIIIIIIIlIlII.method_23321() - lIlIIIIIIIlIlII.field_5969) * (double)lIlIIIIIIIlIlIl.tickDelta;
        double lIlIIIIIIIIllIl = lIlIIIIIIIlIlII.method_5829().field_1325 - lIlIIIIIIIlIlII.method_5829().field_1322;
        if (lIlIIIIIIIIlIIl == Target.Head) {
            lIlIIIIIIIIllll += lIlIIIIIIIIllIl;
        } else if (lIlIIIIIIIIlIIl == Target.Body) {
            lIlIIIIIIIIllll += lIlIIIIIIIIllIl / 2.0;
        }
        RenderUtils.drawLine(RenderUtils.getCameraVector(), lIlIIIIIIIlIIII, lIlIIIIIIIIllll, lIlIIIIIIIIlllI, lIlIIIIIIIIlIlI, lIlIIIIIIIlIlIl);
        if (lIlIIIIIIIIlIII) {
            Renderer.LINES.line(lIlIIIIIIIlIIII, lIlIIIIIIIlIlII.method_23318(), lIlIIIIIIIIlllI, lIlIIIIIIIlIIII, lIlIIIIIIIlIlII.method_23318() + lIlIIIIIIIIllIl, lIlIIIIIIIIlllI, lIlIIIIIIIIlIlI);
        }
    }

    public static void drawItem(class_1799 lIlIIIIIIlllIII, int lIlIIIIIIllIIll, int lIlIIIIIIllIIlI, boolean lIlIIIIIIllIIIl) {
        RenderSystem.disableLighting();
        RenderSystem.disableDepthTest();
        class_308.method_22890();
        Utils.mc.method_1480().method_4010(lIlIIIIIIlllIII, lIlIIIIIIllIIll, lIlIIIIIIllIIlI);
        if (lIlIIIIIIllIIIl) {
            Utils.mc.method_1480().method_4022(Utils.mc.field_1772, lIlIIIIIIlllIII, lIlIIIIIIllIIll, lIlIIIIIIllIIlI, null);
        }
        class_308.method_1450();
        RenderSystem.enableDepthTest();
    }

    public static void drawLine(class_243 lIIlllllllIlllI, double lIIllllllllIIll, double lIIlllllllIllII, double lIIllllllllIIIl, Color lIIlllllllIlIlI, RenderEvent lIIlllllllIllll) {
        Renderer.LINES.line(lIIlllllllIlllI.field_1352 - (Utils.mc.field_1773.method_19418().method_19326().field_1352 - lIIlllllllIllll.offsetX), lIIlllllllIlllI.field_1351 - (Utils.mc.field_1773.method_19418().method_19326().field_1351 - lIIlllllllIllll.offsetY), lIIlllllllIlllI.field_1350 - (Utils.mc.field_1773.method_19418().method_19326().field_1350 - lIIlllllllIllll.offsetZ), lIIllllllllIIll, lIIlllllllIllII, lIIllllllllIIIl, lIIlllllllIlIlI);
    }

    public RenderUtils() {
        RenderUtils lIlIIIIIIlllllI;
    }

    public static void drawItem(class_1799 lIlIIIIIIlIIllI, int lIlIIIIIIlIlIlI, int lIlIIIIIIlIlIIl, double lIlIIIIIIlIlIII, boolean lIlIIIIIIlIIlll) {
        RenderSystem.pushMatrix();
        RenderSystem.scaled((double)lIlIIIIIIlIlIII, (double)lIlIIIIIIlIlIII, (double)1.0);
        RenderUtils.drawItem(lIlIIIIIIlIIllI, (int)((double)lIlIIIIIIlIlIlI / lIlIIIIIIlIlIII), (int)((double)lIlIIIIIIlIlIIl / lIlIIIIIIlIlIII), lIlIIIIIIlIIlll);
        RenderSystem.popMatrix();
    }
}

