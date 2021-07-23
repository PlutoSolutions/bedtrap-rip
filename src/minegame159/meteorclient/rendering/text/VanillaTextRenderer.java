/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.rendering.text;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Objects;
import minegame159.meteorclient.rendering.Matrices;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_4597;

public class VanillaTextRenderer
implements TextRenderer {
    public static final TextRenderer INSTANCE = new VanillaTextRenderer();
    private double scale = 1.74;
    private double alpha = 1.0;
    private boolean building;

    @Override
    public double getHeight() {
        Objects.requireNonNull(Utils.mc.field_1772);
        return 9.0 * this.scale;
    }

    @Override
    public void begin(double d, boolean bl, boolean bl2) {
        this.scale = d * 1.74;
        this.building = true;
    }

    @Override
    public void setAlpha(double d) {
        this.alpha = d;
    }

    @Override
    public double render(String string, double d, double d2, Color color, boolean bl) {
        Matrices.push();
        Matrices.scale(this.scale, this.scale, 1.0);
        int n = color.a;
        color.a = (int)((double)(color.a / 255) * this.alpha * 255.0);
        RenderSystem.disableDepthTest();
        class_4597.class_4598 class_45982 = class_4597.method_22991((class_287)class_289.method_1348().method_1349());
        double d3 = Utils.mc.field_1772.method_27521(string, (float)((d += 0.5 * this.scale) / this.scale), (float)((d2 += 0.5 * this.scale) / this.scale), color.getPacked(), bl, Matrices.getTop(), (class_4597)class_45982, true, 0, 0xF000F0);
        class_45982.method_22993();
        RenderSystem.enableDepthTest();
        color.a = n;
        Matrices.pop();
        return d3 * this.scale;
    }

    @Override
    public void end() {
        this.scale = 1.74;
        this.building = false;
    }

    @Override
    public boolean isBuilding() {
        return this.building;
    }

    private VanillaTextRenderer() {
    }

    @Override
    public double getWidth(String string, int n) {
        String string2 = string;
        if (n != string2.length()) {
            string2 = string2.substring(0, n);
        }
        return (double)Utils.mc.field_1772.method_1727(string2) * this.scale;
    }
}

