/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1159
 *  net.minecraft.class_4587
 *  org.lwjgl.opengl.GL11
 */
package minegame159.meteorclient.utils.render;

import minegame159.meteorclient.mixininterface.IMatrix4f;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.Vec3;
import minegame159.meteorclient.utils.misc.Vec4;
import net.minecraft.class_1159;
import net.minecraft.class_4587;
import org.lwjgl.opengl.GL11;

public class NametagUtils {
    private static final Vec3 cameraNegated;
    private static class_1159 projection;
    private static final Vec4 mmMat4;
    private static final Vec4 vec4;
    private static final Vec4 pmMat4;
    private static class_1159 model;
    private static double windowScale;
    private static final Vec3 camera;
    private static double scale;

    public static boolean to2D(Vec3 vec3, double d) {
        scale = NametagUtils.getScale(vec3) * d;
        vec4.set(NametagUtils.cameraNegated.x + vec3.x, NametagUtils.cameraNegated.y + vec3.y, NametagUtils.cameraNegated.z + vec3.z, 1.0);
        ((IMatrix4f)model).multiplyMatrix(vec4, mmMat4);
        ((IMatrix4f)projection).multiplyMatrix(mmMat4, pmMat4);
        if (NametagUtils.pmMat4.w <= 0.0) {
            return false;
        }
        pmMat4.toScreen();
        double d2 = NametagUtils.pmMat4.x * (double)Utils.mc.method_22683().method_4489();
        double d3 = NametagUtils.pmMat4.y * (double)Utils.mc.method_22683().method_4506();
        if (Double.isInfinite(d2) || Double.isInfinite(d3)) {
            return false;
        }
        vec3.set(d2 / windowScale, (double)Utils.mc.method_22683().method_4506() - d3 / windowScale, NametagUtils.pmMat4.z);
        return true;
    }

    private static double getScale(Vec3 vec3) {
        double d = camera.distanceTo(vec3);
        return Utils.clamp(1.0 - d * 0.01, 0.5, 2.147483647E9);
    }

    public static void begin(Vec3 vec3) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)vec3.x, (double)vec3.y, (double)0.0);
        GL11.glScaled((double)scale, (double)scale, (double)1.0);
    }

    public static void onRender(class_4587 class_45872, class_1159 class_11592) {
        model = class_45872.method_23760().method_23761().method_22673();
        projection = class_11592;
        camera.set(Utils.mc.field_1773.method_19418().method_19326());
        cameraNegated.set(camera);
        cameraNegated.negate();
        windowScale = Utils.mc.method_22683().method_4476(1, Utils.mc.method_1573());
    }

    public static void end() {
        GL11.glPopMatrix();
    }

    static {
        vec4 = new Vec4();
        mmMat4 = new Vec4();
        pmMat4 = new Vec4();
        camera = new Vec3();
        cameraNegated = new Vec3();
    }
}

