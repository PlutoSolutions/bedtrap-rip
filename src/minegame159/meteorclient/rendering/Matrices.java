/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1158
 *  net.minecraft.class_1159
 *  net.minecraft.class_4587
 */
package minegame159.meteorclient.rendering;

import net.minecraft.class_1158;
import net.minecraft.class_1159;
import net.minecraft.class_4587;

public class Matrices {
    private static /* synthetic */ class_4587 matrixStack;

    public static void pop() {
        matrixStack.method_22909();
    }

    public static void rotate(double lllllllllllllllllIIIIllllIIIIlII, double lllllllllllllllllIIIIllllIIIIlll, double lllllllllllllllllIIIIllllIIIIllI, double lllllllllllllllllIIIIllllIIIIlIl) {
        matrixStack.method_22907(new class_1158((float)(lllllllllllllllllIIIIllllIIIIlll * lllllllllllllllllIIIIllllIIIIlII), (float)(lllllllllllllllllIIIIllllIIIIllI * lllllllllllllllllIIIIllllIIIIlII), (float)(lllllllllllllllllIIIIllllIIIIlIl * lllllllllllllllllIIIIllllIIIIlII), true));
    }

    public static class_1159 getTop() {
        return matrixStack.method_23760().method_23761();
    }

    public Matrices() {
        Matrices lllllllllllllllllIIIIllllIIllIIl;
    }

    public static class_4587 getMatrixStack() {
        return matrixStack;
    }

    public static void translate(double lllllllllllllllllIIIIllllIIlIIlI, double lllllllllllllllllIIIIllllIIIlllI, double lllllllllllllllllIIIIllllIIIllIl) {
        matrixStack.method_22904(lllllllllllllllllIIIIllllIIlIIlI, lllllllllllllllllIIIIllllIIIlllI, lllllllllllllllllIIIIllllIIIllIl);
    }

    public static void push() {
        matrixStack.method_22903();
    }

    public static void begin(class_4587 lllllllllllllllllIIIIllllIIlIllI) {
        matrixStack = lllllllllllllllllIIIIllllIIlIllI;
    }

    public static void scale(double lllllllllllllllllIIIIlllIlllllIl, double lllllllllllllllllIIIIlllIllllIIl, double lllllllllllllllllIIIIlllIllllIll) {
        matrixStack.method_22905((float)lllllllllllllllllIIIIlllIlllllIl, (float)lllllllllllllllllIIIIlllIllllIIl, (float)lllllllllllllllllIIIIlllIllllIll);
    }
}

