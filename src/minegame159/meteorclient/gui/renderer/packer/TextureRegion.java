/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.renderer.packer;

public class TextureRegion {
    public double x1;
    public double y2;
    public double diagonal;
    public double y1;
    public double x2;

    public TextureRegion(double d, double d2) {
        this.diagonal = Math.sqrt(d * d + d2 * d2);
    }
}

