/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.utils.render.color;

import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_243;
import net.minecraft.class_2487;

public class Color
implements ICopyable<Color>,
ISerializable<Color> {
    public /* synthetic */ int b;
    public /* synthetic */ int g;
    public /* synthetic */ int a;
    public /* synthetic */ int r;

    public Color(Color llllllllllllllllllIIlIllIIllIIIl) {
        Color llllllllllllllllllIIlIllIIllIIII;
        llllllllllllllllllIIlIllIIllIIII.r = llllllllllllllllllIIlIllIIllIIIl.r;
        llllllllllllllllllIIlIllIIllIIII.g = llllllllllllllllllIIlIllIIllIIIl.g;
        llllllllllllllllllIIlIllIIllIIII.b = llllllllllllllllllIIlIllIIllIIIl.b;
        llllllllllllllllllIIlIllIIllIIII.a = llllllllllllllllllIIlIllIIllIIIl.a;
    }

    @Override
    public Color fromTag(class_2487 llllllllllllllllllIIlIlIlIIllIII) {
        Color llllllllllllllllllIIlIlIlIIlIlll;
        llllllllllllllllllIIlIlIlIIlIlll.r = llllllllllllllllllIIlIlIlIIllIII.method_10550("r");
        llllllllllllllllllIIlIlIlIIlIlll.g = llllllllllllllllllIIlIlIlIIllIII.method_10550("g");
        llllllllllllllllllIIlIlIlIIlIlll.b = llllllllllllllllllIIlIlIlIIllIII.method_10550("b");
        llllllllllllllllllIIlIlIlIIlIlll.a = llllllllllllllllllIIlIlIlIIllIII.method_10550("a");
        llllllllllllllllllIIlIlIlIIlIlll.validate();
        return llllllllllllllllllIIlIlIlIIlIlll;
    }

    public Color g(int llllllllllllllllllIIlIlIllIIIIII) {
        Color llllllllllllllllllIIlIlIllIIIIll;
        llllllllllllllllllIIlIlIllIIIIll.g = llllllllllllllllllIIlIlIllIIIIII;
        llllllllllllllllllIIlIlIllIIIIll.validate();
        return llllllllllllllllllIIlIlIllIIIIll;
    }

    public Color(int llllllllllllllllllIIlIllIlIllIll, int llllllllllllllllllIIlIllIlIllIlI, int llllllllllllllllllIIlIllIlIlllIl) {
        Color llllllllllllllllllIIlIllIlIlllII;
        llllllllllllllllllIIlIllIlIlllII.r = llllllllllllllllllIIlIllIlIllIll;
        llllllllllllllllllIIlIllIlIlllII.g = llllllllllllllllllIIlIllIlIllIlI;
        llllllllllllllllllIIlIllIlIlllII.b = llllllllllllllllllIIlIllIlIlllIl;
        llllllllllllllllllIIlIllIlIlllII.a = 255;
        llllllllllllllllllIIlIllIlIlllII.validate();
    }

    public int getPacked() {
        Color llllllllllllllllllIIlIlIlIlIIIll;
        return Color.fromRGBA(llllllllllllllllllIIlIlIlIlIIIll.r, llllllllllllllllllIIlIlIlIlIIIll.g, llllllllllllllllllIIlIlIlIlIIIll.b, llllllllllllllllllIIlIlIlIlIIIll.a);
    }

    public static int toRGBAB(int llllllllllllllllllIIlIllIIIlIlIl) {
        return llllllllllllllllllIIlIllIIIlIlIl & 0xFF;
    }

    @Override
    public Color copy() {
        Color llllllllllllllllllIIlIlIlIlIllII;
        return new Color(llllllllllllllllllIIlIlIlIlIllII.r, llllllllllllllllllIIlIlIlIlIllII.g, llllllllllllllllllIIlIlIlIlIllII.b, llllllllllllllllllIIlIlIlIlIllII.a);
    }

    public String toString() {
        Color llllllllllllllllllIIlIlIlIIlIlII;
        return String.valueOf(new StringBuilder().append(llllllllllllllllllIIlIlIlIIlIlII.r).append(" ").append(llllllllllllllllllIIlIlIlIIlIlII.g).append(" ").append(llllllllllllllllllIIlIlIlIIlIlII.b).append(" ").append(llllllllllllllllllIIlIlIlIIlIlII.a));
    }

    public Color a(int llllllllllllllllllIIlIlIlIllIlII) {
        Color llllllllllllllllllIIlIlIlIllIlll;
        llllllllllllllllllIIlIlIlIllIlll.a = llllllllllllllllllIIlIlIlIllIlII;
        llllllllllllllllllIIlIlIlIllIlll.validate();
        return llllllllllllllllllIIlIlIlIllIlll;
    }

    public static int toRGBAR(int llllllllllllllllllIIlIllIIIllIll) {
        return llllllllllllllllllIIlIllIIIllIll >> 16 & 0xFF;
    }

    public Color(int llllllllllllllllllIIlIllIIllIlll) {
        Color llllllllllllllllllIIlIllIIlllIII;
        llllllllllllllllllIIlIllIIlllIII.r = Color.toRGBAR(llllllllllllllllllIIlIllIIllIlll);
        llllllllllllllllllIIlIllIIlllIII.g = Color.toRGBAG(llllllllllllllllllIIlIllIIllIlll);
        llllllllllllllllllIIlIllIIlllIII.b = Color.toRGBAB(llllllllllllllllllIIlIllIIllIlll);
        llllllllllllllllllIIlIllIIlllIII.a = Color.toRGBAA(llllllllllllllllllIIlIllIIllIlll);
    }

    public Color(java.awt.Color llllllllllllllllllIIlIllIIlIlIIl) {
        Color llllllllllllllllllIIlIllIIlIlIlI;
        llllllllllllllllllIIlIllIIlIlIlI.r = llllllllllllllllllIIlIllIIlIlIIl.getRed();
        llllllllllllllllllIIlIllIIlIlIlI.g = llllllllllllllllllIIlIllIIlIlIIl.getGreen();
        llllllllllllllllllIIlIllIIlIlIlI.b = llllllllllllllllllIIlIllIIlIlIIl.getBlue();
        llllllllllllllllllIIlIllIIlIlIlI.a = llllllllllllllllllIIlIllIIlIlIIl.getAlpha();
    }

    public static int toRGBAA(int llllllllllllllllllIIlIllIIIlIIIl) {
        return llllllllllllllllllIIlIllIIIlIIIl >> 24 & 0xFF;
    }

    public static int fromRGBA(int llllllllllllllllllIIlIllIIlIIIII, int llllllllllllllllllIIlIllIIIlllll, int llllllllllllllllllIIlIllIIIllllI, int llllllllllllllllllIIlIllIIIlllIl) {
        return (llllllllllllllllllIIlIllIIlIIIII << 16) + (llllllllllllllllllIIlIllIIIlllll << 8) + llllllllllllllllllIIlIllIIIllllI + (llllllllllllllllllIIlIllIIIlllIl << 24);
    }

    public boolean equals(Color llllllllllllllllllIIlIlIlIIIllIl) {
        Color llllllllllllllllllIIlIlIlIIlIIII;
        if (llllllllllllllllllIIlIlIlIIIllIl == null) {
            return false;
        }
        return llllllllllllllllllIIlIlIlIIlIIII.r == llllllllllllllllllIIlIlIlIIIllIl.r && llllllllllllllllllIIlIlIlIIlIIII.g == llllllllllllllllllIIlIlIlIIIllIl.g && llllllllllllllllllIIlIlIlIIlIIII.b == llllllllllllllllllIIlIlIlIIIllIl.b && llllllllllllllllllIIlIlIlIIlIIII.a == llllllllllllllllllIIlIlIlIIIllIl.a;
    }

    @Override
    public Color set(Color llllllllllllllllllIIlIlIlIlIlllI) {
        Color llllllllllllllllllIIlIlIlIlIllll;
        llllllllllllllllllIIlIlIlIlIllll.r = llllllllllllllllllIIlIlIlIlIlllI.r;
        llllllllllllllllllIIlIlIlIlIllll.g = llllllllllllllllllIIlIlIlIlIlllI.g;
        llllllllllllllllllIIlIlIlIlIllll.b = llllllllllllllllllIIlIlIlIlIlllI.b;
        llllllllllllllllllIIlIlIlIlIllll.a = llllllllllllllllllIIlIlIlIlIlllI.a;
        llllllllllllllllllIIlIlIlIlIllll.validate();
        return llllllllllllllllllIIlIlIlIlIllll;
    }

    public Color b(int llllllllllllllllllIIlIlIlIllllII) {
        Color llllllllllllllllllIIlIlIlIlllIll;
        llllllllllllllllllIIlIlIlIlllIll.b = llllllllllllllllllIIlIlIlIllllII;
        llllllllllllllllllIIlIlIlIlllIll.validate();
        return llllllllllllllllllIIlIlIlIlllIll;
    }

    public class_243 getVec3d() {
        Color llllllllllllllllllIIlIlIlIlIIlIl;
        return new class_243((double)llllllllllllllllllIIlIlIlIlIIlIl.r / 255.0, (double)llllllllllllllllllIIlIlIlIlIIlIl.g / 255.0, (double)llllllllllllllllllIIlIlIlIlIIlIl.b / 255.0);
    }

    public Color r(int llllllllllllllllllIIlIlIllIIlIII) {
        Color llllllllllllllllllIIlIlIllIIlIIl;
        llllllllllllllllllIIlIlIllIIlIIl.r = llllllllllllllllllIIlIlIllIIlIII;
        llllllllllllllllllIIlIlIllIIlIIl.validate();
        return llllllllllllllllllIIlIlIllIIlIIl;
    }

    public Color(float llllllllllllllllllIIlIllIIlllllI, float llllllllllllllllllIIlIllIlIIIIlI, float llllllllllllllllllIIlIllIIllllII, float llllllllllllllllllIIlIllIlIIIIII) {
        Color llllllllllllllllllIIlIllIlIIIlII;
        llllllllllllllllllIIlIllIlIIIlII.r = (int)(llllllllllllllllllIIlIllIIlllllI * 255.0f);
        llllllllllllllllllIIlIllIlIIIlII.g = (int)(llllllllllllllllllIIlIllIlIIIIlI * 255.0f);
        llllllllllllllllllIIlIllIlIIIlII.b = (int)(llllllllllllllllllIIlIllIIllllII * 255.0f);
        llllllllllllllllllIIlIllIlIIIlII.a = (int)(llllllllllllllllllIIlIllIlIIIIII * 255.0f);
        llllllllllllllllllIIlIllIlIIIlII.validate();
    }

    public void validate() {
        Color llllllllllllllllllIIlIlIlIlIlIIl;
        if (llllllllllllllllllIIlIlIlIlIlIIl.r < 0) {
            llllllllllllllllllIIlIlIlIlIlIIl.r = 0;
        } else if (llllllllllllllllllIIlIlIlIlIlIIl.r > 255) {
            llllllllllllllllllIIlIlIlIlIlIIl.r = 255;
        }
        if (llllllllllllllllllIIlIlIlIlIlIIl.g < 0) {
            llllllllllllllllllIIlIlIlIlIlIIl.g = 0;
        } else if (llllllllllllllllllIIlIlIlIlIlIIl.g > 255) {
            llllllllllllllllllIIlIlIlIlIlIIl.g = 255;
        }
        if (llllllllllllllllllIIlIlIlIlIlIIl.b < 0) {
            llllllllllllllllllIIlIlIlIlIlIIl.b = 0;
        } else if (llllllllllllllllllIIlIlIlIlIlIIl.b > 255) {
            llllllllllllllllllIIlIlIlIlIlIIl.b = 255;
        }
        if (llllllllllllllllllIIlIlIlIlIlIIl.a < 0) {
            llllllllllllllllllIIlIlIlIlIlIIl.a = 0;
        } else if (llllllllllllllllllIIlIlIlIlIlIIl.a > 255) {
            llllllllllllllllllIIlIlIlIlIlIIl.a = 255;
        }
    }

    public static int toRGBAG(int llllllllllllllllllIIlIllIIIllIII) {
        return llllllllllllllllllIIlIllIIIllIII >> 8 & 0xFF;
    }

    public Color(int llllllllllllllllllIIlIllIlIIllIl, int llllllllllllllllllIIlIllIlIlIIIl, int llllllllllllllllllIIlIllIlIlIIII, int llllllllllllllllllIIlIllIlIIlIlI) {
        Color llllllllllllllllllIIlIllIlIIlllI;
        llllllllllllllllllIIlIllIlIIlllI.r = llllllllllllllllllIIlIllIlIIllIl;
        llllllllllllllllllIIlIllIlIIlllI.g = llllllllllllllllllIIlIllIlIlIIIl;
        llllllllllllllllllIIlIllIlIIlllI.b = llllllllllllllllllIIlIllIlIlIIII;
        llllllllllllllllllIIlIllIlIIlllI.a = llllllllllllllllllIIlIllIlIIlIlI;
        llllllllllllllllllIIlIllIlIIlllI.validate();
    }

    @Override
    public class_2487 toTag() {
        Color llllllllllllllllllIIlIlIlIIlllIl;
        class_2487 llllllllllllllllllIIlIlIlIIllllI = new class_2487();
        llllllllllllllllllIIlIlIlIIllllI.method_10569("r", llllllllllllllllllIIlIlIlIIlllIl.r);
        llllllllllllllllllIIlIlIlIIllllI.method_10569("g", llllllllllllllllllIIlIlIlIIlllIl.g);
        llllllllllllllllllIIlIlIlIIllllI.method_10569("b", llllllllllllllllllIIlIlIlIIlllIl.b);
        llllllllllllllllllIIlIlIlIIllllI.method_10569("a", llllllllllllllllllIIlIlIlIIlllIl.a);
        return llllllllllllllllllIIlIlIlIIllllI;
    }

    public static Color fromHsv(double llllllllllllllllllIIlIlIllllIIlI, double llllllllllllllllllIIlIlIllllIIIl, double llllllllllllllllllIIlIlIlllIIIIl) {
        double llllllllllllllllllIIlIlIlllIIlll;
        double llllllllllllllllllIIlIlIlllIlIII;
        double llllllllllllllllllIIlIlIlllIlIIl;
        if (llllllllllllllllllIIlIlIllllIIIl <= 0.0) {
            double llllllllllllllllllIIlIllIIIIIlII = llllllllllllllllllIIlIlIlllIIIIl;
            double llllllllllllllllllIIlIllIIIIIIll = llllllllllllllllllIIlIlIlllIIIIl;
            double llllllllllllllllllIIlIllIIIIIIlI = llllllllllllllllllIIlIlIlllIIIIl;
            return new Color((int)(llllllllllllllllllIIlIllIIIIIlII * 255.0), (int)(llllllllllllllllllIIlIllIIIIIIll * 255.0), (int)(llllllllllllllllllIIlIllIIIIIIlI * 255.0), 255);
        }
        double llllllllllllllllllIIlIlIlllIllll = llllllllllllllllllIIlIlIllllIIlI;
        if (llllllllllllllllllIIlIlIlllIllll >= 360.0) {
            llllllllllllllllllIIlIlIlllIllll = 0.0;
        }
        int llllllllllllllllllIIlIlIlllIlIlI = (int)(llllllllllllllllllIIlIlIlllIllll /= 60.0);
        double llllllllllllllllllIIlIlIlllIlIll = llllllllllllllllllIIlIlIlllIllll - (double)llllllllllllllllllIIlIlIlllIlIlI;
        double llllllllllllllllllIIlIlIlllIlllI = llllllllllllllllllIIlIlIlllIIIIl * (1.0 - llllllllllllllllllIIlIlIllllIIIl);
        double llllllllllllllllllIIlIlIlllIllIl = llllllllllllllllllIIlIlIlllIIIIl * (1.0 - llllllllllllllllllIIlIlIllllIIIl * llllllllllllllllllIIlIlIlllIlIll);
        double llllllllllllllllllIIlIlIlllIllII = llllllllllllllllllIIlIlIlllIIIIl * (1.0 - llllllllllllllllllIIlIlIllllIIIl * (1.0 - llllllllllllllllllIIlIlIlllIlIll));
        switch (llllllllllllllllllIIlIlIlllIlIlI) {
            case 0: {
                double llllllllllllllllllIIlIllIIIIIIIl = llllllllllllllllllIIlIlIlllIIIIl;
                double llllllllllllllllllIIlIllIIIIIIII = llllllllllllllllllIIlIlIlllIllII;
                double llllllllllllllllllIIlIlIllllllll = llllllllllllllllllIIlIlIlllIlllI;
                break;
            }
            case 1: {
                double llllllllllllllllllIIlIlIlllllllI = llllllllllllllllllIIlIlIlllIllIl;
                double llllllllllllllllllIIlIlIllllllIl = llllllllllllllllllIIlIlIlllIIIIl;
                double llllllllllllllllllIIlIlIllllllII = llllllllllllllllllIIlIlIlllIlllI;
                break;
            }
            case 2: {
                double llllllllllllllllllIIlIlIlllllIll = llllllllllllllllllIIlIlIlllIlllI;
                double llllllllllllllllllIIlIlIlllllIlI = llllllllllllllllllIIlIlIlllIIIIl;
                double llllllllllllllllllIIlIlIlllllIIl = llllllllllllllllllIIlIlIlllIllII;
                break;
            }
            case 3: {
                double llllllllllllllllllIIlIlIlllllIII = llllllllllllllllllIIlIlIlllIlllI;
                double llllllllllllllllllIIlIlIllllIlll = llllllllllllllllllIIlIlIlllIllIl;
                double llllllllllllllllllIIlIlIllllIllI = llllllllllllllllllIIlIlIlllIIIIl;
                break;
            }
            case 4: {
                double llllllllllllllllllIIlIlIllllIlIl = llllllllllllllllllIIlIlIlllIllII;
                double llllllllllllllllllIIlIlIllllIlII = llllllllllllllllllIIlIlIlllIlllI;
                double llllllllllllllllllIIlIlIllllIIll = llllllllllllllllllIIlIlIlllIIIIl;
                break;
            }
            default: {
                llllllllllllllllllIIlIlIlllIlIIl = llllllllllllllllllIIlIlIlllIIIIl;
                llllllllllllllllllIIlIlIlllIlIII = llllllllllllllllllIIlIlIlllIlllI;
                llllllllllllllllllIIlIlIlllIIlll = llllllllllllllllllIIlIlIlllIllIl;
            }
        }
        return new Color((int)(llllllllllllllllllIIlIlIlllIlIIl * 255.0), (int)(llllllllllllllllllIIlIlIlllIlIII * 255.0), (int)(llllllllllllllllllIIlIlIlllIIlll * 255.0), 255);
    }

    public Color set(int llllllllllllllllllIIlIlIllIIllll, int llllllllllllllllllIIlIlIllIlIIll, int llllllllllllllllllIIlIlIllIlIIlI, int llllllllllllllllllIIlIlIllIIllII) {
        Color llllllllllllllllllIIlIlIllIlIIII;
        llllllllllllllllllIIlIlIllIlIIII.r = llllllllllllllllllIIlIlIllIIllll;
        llllllllllllllllllIIlIlIllIlIIII.g = llllllllllllllllllIIlIlIllIlIIll;
        llllllllllllllllllIIlIlIllIlIIII.b = llllllllllllllllllIIlIlIllIlIIlI;
        llllllllllllllllllIIlIlIllIlIIII.a = llllllllllllllllllIIlIlIllIIllII;
        llllllllllllllllllIIlIlIllIlIIII.validate();
        return llllllllllllllllllIIlIlIllIlIIII;
    }

    public Color() {
        llllllllllllllllllIIlIllIllIIllI(255, 255, 255, 255);
        Color llllllllllllllllllIIlIllIllIIllI;
    }
}

