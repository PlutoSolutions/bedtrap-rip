/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.rendering;

import net.minecraft.class_1158;
import net.minecraft.class_1159;
import net.minecraft.class_4587;

public class Matrices {
    private static class_4587 matrixStack;

    public static void pop() {
        matrixStack.method_22909();
    }

    public static void rotate(double d, double d2, double d3, double d4) {
        matrixStack.method_22907(new class_1158((float)(d2 * d), (float)(d3 * d), (float)(d4 * d), true));
    }

    public static class_1159 getTop() {
        return matrixStack.method_23760().method_23761();
    }

    public static class_4587 getMatrixStack() {
        return matrixStack;
    }

    public static void translate(double d, double d2, double d3) {
        matrixStack.method_22904(d, d2, d3);
    }

    public static void push() {
        matrixStack.method_22903();
    }

    public static void begin(class_4587 class_45872) {
        matrixStack = class_45872;
    }

    public static void scale(double d, double d2, double d3) {
        matrixStack.method_22905((float)d, (float)d2, (float)d3);
    }
}

