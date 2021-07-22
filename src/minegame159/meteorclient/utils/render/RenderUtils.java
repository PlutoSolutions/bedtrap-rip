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
    public static void drawTracerToPos(class_2338 class_23382, Color color, RenderEvent renderEvent) {
        RenderUtils.drawLine(RenderUtils.getCameraVector(), (double)class_23382.method_10263() + 0.5, (double)class_23382.method_10264() + 0.5, (float)class_23382.method_10260() + 0.5f, color, renderEvent);
    }

    public static void drawTracerToBlockEntity(class_2586 class_25862, Color color, RenderEvent renderEvent) {
        RenderUtils.drawTracerToPos(class_25862.method_11016(), color, renderEvent);
    }

    public static class_243 getCameraVector() {
        boolean bl = Modules.get().isActive(Freecam.class) || Modules.get().get(FreeLook.class).playerMode();
        return new class_243(0.0, 0.0, bl ? 1.0 : 75.0).method_1037(-((float)Math.toRadians(Utils.mc.field_1773.method_19418().method_19329()))).method_1024(-((float)Math.toRadians(Utils.mc.field_1773.method_19418().method_19330()))).method_1019(Utils.mc.field_1773.method_19418().method_19326());
    }

    public static void drawTracerToEntity(RenderEvent renderEvent, class_1297 class_12972, Color color, Target target, boolean bl) {
        double d = class_12972.field_6014 + (class_12972.method_23317() - class_12972.field_6014) * (double)renderEvent.tickDelta;
        double d2 = class_12972.field_6036 + (class_12972.method_23318() - class_12972.field_6036) * (double)renderEvent.tickDelta;
        double d3 = class_12972.field_5969 + (class_12972.method_23321() - class_12972.field_5969) * (double)renderEvent.tickDelta;
        double d4 = class_12972.method_5829().field_1325 - class_12972.method_5829().field_1322;
        if (target == Target.Head) {
            d2 += d4;
        } else if (target == Target.Body) {
            d2 += d4 / 2.0;
        }
        RenderUtils.drawLine(RenderUtils.getCameraVector(), d, d2, d3, color, renderEvent);
        if (bl) {
            Renderer.LINES.line(d, class_12972.method_23318(), d3, d, class_12972.method_23318() + d4, d3, color);
        }
    }

    public static void drawItem(class_1799 class_17992, int n, int n2, boolean bl) {
        RenderSystem.disableLighting();
        RenderSystem.disableDepthTest();
        class_308.method_22890();
        Utils.mc.method_1480().method_4010(class_17992, n, n2);
        if (bl) {
            Utils.mc.method_1480().method_4022(Utils.mc.field_1772, class_17992, n, n2, null);
        }
        class_308.method_1450();
        RenderSystem.enableDepthTest();
    }

    public static void drawLine(class_243 class_2432, double d, double d2, double d3, Color color, RenderEvent renderEvent) {
        Renderer.LINES.line(class_2432.field_1352 - (Utils.mc.field_1773.method_19418().method_19326().field_1352 - renderEvent.offsetX), class_2432.field_1351 - (Utils.mc.field_1773.method_19418().method_19326().field_1351 - renderEvent.offsetY), class_2432.field_1350 - (Utils.mc.field_1773.method_19418().method_19326().field_1350 - renderEvent.offsetZ), d, d2, d3, color);
    }

    public static void drawItem(class_1799 class_17992, int n, int n2, double d, boolean bl) {
        RenderSystem.pushMatrix();
        RenderSystem.scaled((double)d, (double)d, (double)1.0);
        RenderUtils.drawItem(class_17992, (int)((double)n / d), (int)((double)n2 / d), bl);
        RenderSystem.popMatrix();
    }
}

