/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.renderer.operations;

import minegame159.meteorclient.gui.renderer.GuiRenderOperation;
import minegame159.meteorclient.rendering.text.TextRenderer;

public class TextOperation
extends GuiRenderOperation<TextOperation> {
    private TextRenderer renderer;
    private String text;
    public boolean title;

    public TextOperation set(String string, TextRenderer textRenderer, boolean bl) {
        this.text = string;
        this.renderer = textRenderer;
        this.title = bl;
        return this;
    }

    @Override
    protected void onRun() {
        this.renderer.render(this.text, this.x, this.y, this.color);
    }
}

