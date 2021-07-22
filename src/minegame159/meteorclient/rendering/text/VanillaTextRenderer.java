/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
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
    public static final /* synthetic */ TextRenderer INSTANCE;
    private /* synthetic */ double scale;
    private /* synthetic */ double alpha;
    private /* synthetic */ boolean building;

    @Override
    public double getHeight() {
        VanillaTextRenderer lllllllllllllllllIlIIIIllIlIllll;
        Objects.requireNonNull(Utils.mc.field_1772);
        return 9.0 * lllllllllllllllllIlIIIIllIlIllll.scale;
    }

    static {
        INSTANCE = new VanillaTextRenderer();
    }

    @Override
    public void begin(double lllllllllllllllllIlIIIIllIlIlIlI, boolean lllllllllllllllllIlIIIIllIlIlIIl, boolean lllllllllllllllllIlIIIIllIlIlIII) {
        lllllllllllllllllIlIIIIllIlIlIll.scale = lllllllllllllllllIlIIIIllIlIlIlI * 1.74;
        lllllllllllllllllIlIIIIllIlIlIll.building = true;
    }

    @Override
    public void setAlpha(double lllllllllllllllllIlIIIIllIllllIl) {
        lllllllllllllllllIlIIIIlllIIIIII.alpha = lllllllllllllllllIlIIIIllIllllIl;
    }

    @Override
    public double render(String lllllllllllllllllIlIIIIllIIllIll, double lllllllllllllllllIlIIIIllIIlIIIl, double lllllllllllllllllIlIIIIllIIlIIII, Color lllllllllllllllllIlIIIIllIIllIII, boolean lllllllllllllllllIlIIIIllIIlIlll) {
        VanillaTextRenderer lllllllllllllllllIlIIIIllIIlllII;
        Matrices.push();
        Matrices.scale(lllllllllllllllllIlIIIIllIIlllII.scale, lllllllllllllllllIlIIIIllIIlllII.scale, 1.0);
        int lllllllllllllllllIlIIIIllIIlIllI = lllllllllllllllllIlIIIIllIIllIII.a;
        lllllllllllllllllIlIIIIllIIllIII.a = (int)((double)(lllllllllllllllllIlIIIIllIIllIII.a / 255) * lllllllllllllllllIlIIIIllIIlllII.alpha * 255.0);
        RenderSystem.disableDepthTest();
        class_4597.class_4598 lllllllllllllllllIlIIIIllIIlIlIl = class_4597.method_22991((class_287)class_289.method_1348().method_1349());
        double lllllllllllllllllIlIIIIllIIlIlII = Utils.mc.field_1772.method_27521(lllllllllllllllllIlIIIIllIIllIll, (float)((lllllllllllllllllIlIIIIllIIlIIIl += 0.5 * lllllllllllllllllIlIIIIllIIlllII.scale) / lllllllllllllllllIlIIIIllIIlllII.scale), (float)((lllllllllllllllllIlIIIIllIIlIIII += 0.5 * lllllllllllllllllIlIIIIllIIlllII.scale) / lllllllllllllllllIlIIIIllIIlllII.scale), lllllllllllllllllIlIIIIllIIllIII.getPacked(), lllllllllllllllllIlIIIIllIIlIlll, Matrices.getTop(), (class_4597)lllllllllllllllllIlIIIIllIIlIlIl, true, 0, 0xF000F0);
        lllllllllllllllllIlIIIIllIIlIlIl.method_22993();
        RenderSystem.enableDepthTest();
        lllllllllllllllllIlIIIIllIIllIII.a = lllllllllllllllllIlIIIIllIIlIllI;
        Matrices.pop();
        return lllllllllllllllllIlIIIIllIIlIlII * lllllllllllllllllIlIIIIllIIlllII.scale;
    }

    @Override
    public void end() {
        lllllllllllllllllIlIIIIllIIIIlIl.scale = 1.74;
        lllllllllllllllllIlIIIIllIIIIlIl.building = false;
    }

    @Override
    public boolean isBuilding() {
        VanillaTextRenderer lllllllllllllllllIlIIIIllIIIlIIl;
        return lllllllllllllllllIlIIIIllIIIlIIl.building;
    }

    private VanillaTextRenderer() {
        VanillaTextRenderer lllllllllllllllllIlIIIIlllIIIlII;
        lllllllllllllllllIlIIIIlllIIIlII.scale = 1.74;
        lllllllllllllllllIlIIIIlllIIIlII.alpha = 1.0;
    }

    @Override
    public double getWidth(String lllllllllllllllllIlIIIIllIllIlll, int lllllllllllllllllIlIIIIllIllIIlI) {
        VanillaTextRenderer lllllllllllllllllIlIIIIllIllIlII;
        String lllllllllllllllllIlIIIIllIllIlIl = lllllllllllllllllIlIIIIllIllIlll;
        if (lllllllllllllllllIlIIIIllIllIIlI != lllllllllllllllllIlIIIIllIllIlIl.length()) {
            lllllllllllllllllIlIIIIllIllIlIl = lllllllllllllllllIlIIIIllIllIlIl.substring(0, lllllllllllllllllIlIIIIllIllIIlI);
        }
        return (double)Utils.mc.field_1772.method_1727(lllllllllllllllllIlIIIIllIllIlIl) * lllllllllllllllllIlIIIIllIllIlII.scale;
    }
}

