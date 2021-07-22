/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.widgets.WRoot;
import minegame159.meteorclient.gui.widgets.containers.WContainer;

public abstract class WTooltip
extends WContainer
implements WRoot {
    private boolean valid;
    protected String text;

    @Override
    public boolean render(GuiRenderer guiRenderer, double d, double d2, double d3) {
        if (!this.valid) {
            this.calculateSize();
            this.calculateWidgetPositions();
            this.valid = true;
        }
        return super.render(guiRenderer, d, d2, d3);
    }

    public WTooltip(String string) {
        this.text = string;
    }

    @Override
    public void invalidate() {
        this.valid = false;
    }

    @Override
    public void init() {
        this.add(this.theme.label(this.text)).pad(4.0);
    }
}

