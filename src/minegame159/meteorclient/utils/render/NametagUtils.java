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
    private static final /* synthetic */ Vec3 cameraNegated;
    private static /* synthetic */ class_1159 projection;
    private static final /* synthetic */ Vec4 mmMat4;
    private static final /* synthetic */ Vec4 vec4;
    private static final /* synthetic */ Vec4 pmMat4;
    private static /* synthetic */ class_1159 model;
    private static /* synthetic */ double windowScale;
    private static final /* synthetic */ Vec3 camera;
    private static /* synthetic */ double scale;

    public static boolean to2D(Vec3 lllllllllllllllllIlIIIIIllIIIIII, double lllllllllllllllllIlIIIIIlIllllll) {
        scale = NametagUtils.getScale(lllllllllllllllllIlIIIIIllIIIIII) * lllllllllllllllllIlIIIIIlIllllll;
        vec4.set(NametagUtils.cameraNegated.x + lllllllllllllllllIlIIIIIllIIIIII.x, NametagUtils.cameraNegated.y + lllllllllllllllllIlIIIIIllIIIIII.y, NametagUtils.cameraNegated.z + lllllllllllllllllIlIIIIIllIIIIII.z, 1.0);
        ((IMatrix4f)model).multiplyMatrix(vec4, mmMat4);
        ((IMatrix4f)projection).multiplyMatrix(mmMat4, pmMat4);
        if (NametagUtils.pmMat4.w <= 0.0) {
            return false;
        }
        pmMat4.toScreen();
        double lllllllllllllllllIlIIIIIlIlllllI = NametagUtils.pmMat4.x * (double)Utils.mc.method_22683().method_4489();
        double lllllllllllllllllIlIIIIIlIllllIl = NametagUtils.pmMat4.y * (double)Utils.mc.method_22683().method_4506();
        if (Double.isInfinite(lllllllllllllllllIlIIIIIlIlllllI) || Double.isInfinite(lllllllllllllllllIlIIIIIlIllllIl)) {
            return false;
        }
        lllllllllllllllllIlIIIIIllIIIIII.set(lllllllllllllllllIlIIIIIlIlllllI / windowScale, (double)Utils.mc.method_22683().method_4506() - lllllllllllllllllIlIIIIIlIllllIl / windowScale, NametagUtils.pmMat4.z);
        return true;
    }

    private static double getScale(Vec3 lllllllllllllllllIlIIIIIlIllIIll) {
        double lllllllllllllllllIlIIIIIlIllIIlI = camera.distanceTo(lllllllllllllllllIlIIIIIlIllIIll);
        return Utils.clamp(1.0 - lllllllllllllllllIlIIIIIlIllIIlI * 0.01, 0.5, 2.147483647E9);
    }

    public static void begin(Vec3 lllllllllllllllllIlIIIIIlIllIlll) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)lllllllllllllllllIlIIIIIlIllIlll.x, (double)lllllllllllllllllIlIIIIIlIllIlll.y, (double)0.0);
        GL11.glScaled((double)scale, (double)scale, (double)1.0);
    }

    public static void onRender(class_4587 lllllllllllllllllIlIIIIIllIIlIII, class_1159 lllllllllllllllllIlIIIIIllIIIlIl) {
        model = lllllllllllllllllIlIIIIIllIIlIII.method_23760().method_23761().method_22673();
        projection = lllllllllllllllllIlIIIIIllIIIlIl;
        camera.set(Utils.mc.field_1773.method_19418().method_19326());
        cameraNegated.set(camera);
        cameraNegated.negate();
        windowScale = Utils.mc.method_22683().method_4476(1, Utils.mc.method_1573());
    }

    public NametagUtils() {
        NametagUtils lllllllllllllllllIlIIIIIllIIllII;
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

