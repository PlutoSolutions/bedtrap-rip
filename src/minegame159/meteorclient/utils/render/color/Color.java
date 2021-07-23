/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.render.color;

import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_243;
import net.minecraft.class_2487;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Color
implements ICopyable<Color>,
ISerializable<Color> {
    public int b;
    public int g;
    public int a;
    public int r;

    public Color(Color color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = color.a;
    }

    @Override
    public Color fromTag(class_2487 class_24872) {
        this.r = class_24872.method_10550("r");
        this.g = class_24872.method_10550("g");
        this.b = class_24872.method_10550("b");
        this.a = class_24872.method_10550("a");
        this.validate();
        return this;
    }

    public Color g(int n) {
        this.g = n;
        this.validate();
        return this;
    }

    public Color(int n, int n2, int n3) {
        this.r = n;
        this.g = n2;
        this.b = n3;
        this.a = 255;
        this.validate();
    }

    public int getPacked() {
        return Color.fromRGBA(this.r, this.g, this.b, this.a);
    }

    public static int toRGBAB(int n) {
        return n & 0xFF;
    }

    @Override
    public Color copy() {
        return new Color(this.r, this.g, this.b, this.a);
    }

    public String toString() {
        return String.valueOf(new StringBuilder().append(this.r).append(" ").append(this.g).append(" ").append(this.b).append(" ").append(this.a));
    }

    public Color a(int n) {
        this.a = n;
        this.validate();
        return this;
    }

    public static int toRGBAR(int n) {
        return n >> 16 & 0xFF;
    }

    public Color(int n) {
        this.r = Color.toRGBAR(n);
        this.g = Color.toRGBAG(n);
        this.b = Color.toRGBAB(n);
        this.a = Color.toRGBAA(n);
    }

    public Color(java.awt.Color color) {
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.a = color.getAlpha();
    }

    public static int toRGBAA(int n) {
        return n >> 24 & 0xFF;
    }

    public static int fromRGBA(int n, int n2, int n3, int n4) {
        return (n << 16) + (n2 << 8) + n3 + (n4 << 24);
    }

    public boolean equals(Color color) {
        if (color == null) {
            return false;
        }
        return this.r == color.r && this.g == color.g && this.b == color.b && this.a == color.a;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public Color set(Color color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = color.a;
        this.validate();
        return this;
    }

    public Color b(int n) {
        this.b = n;
        this.validate();
        return this;
    }

    public class_243 getVec3d() {
        return new class_243((double)this.r / 255.0, (double)this.g / 255.0, (double)this.b / 255.0);
    }

    public Color r(int n) {
        this.r = n;
        this.validate();
        return this;
    }

    public Color(float f, float f2, float f3, float f4) {
        this.r = (int)(f * 255.0f);
        this.g = (int)(f2 * 255.0f);
        this.b = (int)(f3 * 255.0f);
        this.a = (int)(f4 * 255.0f);
        this.validate();
    }

    public void validate() {
        if (this.r < 0) {
            this.r = 0;
        } else if (this.r > 255) {
            this.r = 255;
        }
        if (this.g < 0) {
            this.g = 0;
        } else if (this.g > 255) {
            this.g = 255;
        }
        if (this.b < 0) {
            this.b = 0;
        } else if (this.b > 255) {
            this.b = 255;
        }
        if (this.a < 0) {
            this.a = 0;
        } else if (this.a > 255) {
            this.a = 255;
        }
    }

    public static int toRGBAG(int n) {
        return n >> 8 & 0xFF;
    }

    @Override
    public ICopyable copy() {
        return this.copy();
    }

    public Color(int n, int n2, int n3, int n4) {
        this.r = n;
        this.g = n2;
        this.b = n3;
        this.a = n4;
        this.validate();
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10569("r", this.r);
        class_24872.method_10569("g", this.g);
        class_24872.method_10569("b", this.b);
        class_24872.method_10569("a", this.a);
        return class_24872;
    }

    public static Color fromHsv(double d, double d2, double d3) {
        double d4;
        double d5;
        double d6;
        if (d2 <= 0.0) {
            double d7 = d3;
            double d8 = d3;
            double d9 = d3;
            return new Color((int)(d7 * 255.0), (int)(d8 * 255.0), (int)(d9 * 255.0), 255);
        }
        double d10 = d;
        if (d10 >= 360.0) {
            d10 = 0.0;
        }
        int n = (int)(d10 /= 60.0);
        double d11 = d10 - (double)n;
        double d12 = d3 * (1.0 - d2);
        double d13 = d3 * (1.0 - d2 * d11);
        double d14 = d3 * (1.0 - d2 * (1.0 - d11));
        switch (n) {
            case 0: {
                d6 = d3;
                d5 = d14;
                d4 = d12;
                break;
            }
            case 1: {
                d6 = d13;
                d5 = d3;
                d4 = d12;
                break;
            }
            case 2: {
                d6 = d12;
                d5 = d3;
                d4 = d14;
                break;
            }
            case 3: {
                d6 = d12;
                d5 = d13;
                d4 = d3;
                break;
            }
            case 4: {
                d6 = d14;
                d5 = d12;
                d4 = d3;
                break;
            }
            default: {
                d6 = d3;
                d5 = d12;
                d4 = d13;
            }
        }
        return new Color((int)(d6 * 255.0), (int)(d5 * 255.0), (int)(d4 * 255.0), 255);
    }

    public Color set(int n, int n2, int n3, int n4) {
        this.r = n;
        this.g = n2;
        this.b = n3;
        this.a = n4;
        this.validate();
        return this;
    }

    public Color() {
        this(255, 255, 255, 255);
    }

    @Override
    public ICopyable set(ICopyable iCopyable) {
        return this.set((Color)iCopyable);
    }
}

