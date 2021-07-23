/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.renderer.packer;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.gui.renderer.packer.TextureRegion;

public class GuiTexture {
    private final List<TextureRegion> regions = new ArrayList<TextureRegion>(2);

    void add(TextureRegion textureRegion) {
        this.regions.add(textureRegion);
    }

    public TextureRegion get(double d, double d2) {
        double d3 = Math.sqrt(d * d + d2 * d2);
        double d4 = Double.MAX_VALUE;
        TextureRegion textureRegion = null;
        for (TextureRegion textureRegion2 : this.regions) {
            double d5 = Math.abs(d3 - textureRegion2.diagonal);
            if (!(d5 < d4)) continue;
            d4 = d5;
            textureRegion = textureRegion2;
        }
        return textureRegion;
    }
}

