/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1044
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.widgets.WWidget;
import net.minecraft.class_1044;

public class WTexture
extends WWidget {
    private final class_1044 texture;
    private final double rotation;
    private final double width;
    private final double height;

    public WTexture(double d, double d2, double d3, class_1044 class_10442) {
        this.width = d;
        this.height = d2;
        this.rotation = d3;
        this.texture = class_10442;
    }

    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        guiRenderer.texture(this.x, this.y, ((WWidget)this).width, ((WWidget)this).height, this.rotation, this.texture);
    }

    @Override
    protected void onCalculateSize() {
        ((WWidget)this).width = this.theme.scale(this.width);
        ((WWidget)this).height = this.theme.scale(this.height);
    }
}

