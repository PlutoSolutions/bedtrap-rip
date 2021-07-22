/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.renderer.operations;

import minegame159.meteorclient.gui.renderer.GuiRenderOperation;
import minegame159.meteorclient.rendering.text.TextRenderer;

public class TextOperation
extends GuiRenderOperation<TextOperation> {
    private /* synthetic */ TextRenderer renderer;
    private /* synthetic */ String text;
    public /* synthetic */ boolean title;

    public TextOperation set(String llllllllllllllllllIllIIllIIlIIlI, TextRenderer llllllllllllllllllIllIIllIIlIIIl, boolean llllllllllllllllllIllIIllIIlIIII) {
        TextOperation llllllllllllllllllIllIIllIIlIlll;
        llllllllllllllllllIllIIllIIlIlll.text = llllllllllllllllllIllIIllIIlIIlI;
        llllllllllllllllllIllIIllIIlIlll.renderer = llllllllllllllllllIllIIllIIlIIIl;
        llllllllllllllllllIllIIllIIlIlll.title = llllllllllllllllllIllIIllIIlIIII;
        return llllllllllllllllllIllIIllIIlIlll;
    }

    @Override
    protected void onRun() {
        TextOperation llllllllllllllllllIllIIllIIIlIll;
        llllllllllllllllllIllIIllIIIlIll.renderer.render(llllllllllllllllllIllIIllIIIlIll.text, llllllllllllllllllIllIIllIIIlIll.x, llllllllllllllllllIllIIllIIIlIll.y, llllllllllllllllllIllIIllIIIlIll.color);
    }

    public TextOperation() {
        TextOperation llllllllllllllllllIllIIllIlIIIII;
    }
}

