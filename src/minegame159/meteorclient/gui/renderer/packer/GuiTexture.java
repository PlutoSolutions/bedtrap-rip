/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.renderer.packer;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.gui.renderer.packer.TextureRegion;

public class GuiTexture {
    private final /* synthetic */ List<TextureRegion> regions;

    void add(TextureRegion lllllllllllllllllllIIIIIlIIlIllI) {
        GuiTexture lllllllllllllllllllIIIIIlIIllIIl;
        lllllllllllllllllllIIIIIlIIllIIl.regions.add(lllllllllllllllllllIIIIIlIIlIllI);
    }

    public GuiTexture() {
        GuiTexture lllllllllllllllllllIIIIIlIIlllIl;
        lllllllllllllllllllIIIIIlIIlllIl.regions = new ArrayList<TextureRegion>(2);
    }

    public TextureRegion get(double lllllllllllllllllllIIIIIlIIIlIIl, double lllllllllllllllllllIIIIIlIIIIIlI) {
        GuiTexture lllllllllllllllllllIIIIIlIIIlIlI;
        double lllllllllllllllllllIIIIIlIIIIlll = Math.sqrt(lllllllllllllllllllIIIIIlIIIlIIl * lllllllllllllllllllIIIIIlIIIlIIl + lllllllllllllllllllIIIIIlIIIIIlI * lllllllllllllllllllIIIIIlIIIIIlI);
        double lllllllllllllllllllIIIIIlIIIIllI = Double.MAX_VALUE;
        TextureRegion lllllllllllllllllllIIIIIlIIIIlIl = null;
        for (TextureRegion lllllllllllllllllllIIIIIlIIIlIll : lllllllllllllllllllIIIIIlIIIlIlI.regions) {
            double lllllllllllllllllllIIIIIlIIIllII = Math.abs(lllllllllllllllllllIIIIIlIIIIlll - lllllllllllllllllllIIIIIlIIIlIll.diagonal);
            if (!(lllllllllllllllllllIIIIIlIIIllII < lllllllllllllllllllIIIIIlIIIIllI)) continue;
            lllllllllllllllllllIIIIIlIIIIllI = lllllllllllllllllllIIIIIlIIIllII;
            lllllllllllllllllllIIIIIlIIIIlIl = lllllllllllllllllllIIIIIlIIIlIll;
        }
        return lllllllllllllllllllIIIIIlIIIIlIl;
    }
}

