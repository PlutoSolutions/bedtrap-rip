/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.pressable;

import minegame159.meteorclient.gui.renderer.packer.GuiTexture;
import minegame159.meteorclient.gui.widgets.pressable.WPressable;

public abstract class WButton
extends WPressable {
    protected /* synthetic */ GuiTexture texture;
    protected /* synthetic */ String text;
    protected /* synthetic */ double textWidth;

    public WButton(String llllllllllllllllllIlIIlIIlIIIIll, GuiTexture llllllllllllllllllIlIIlIIlIIIIlI) {
        WButton llllllllllllllllllIlIIlIIlIIIIIl;
        llllllllllllllllllIlIIlIIlIIIIIl.text = llllllllllllllllllIlIIlIIlIIIIll;
        llllllllllllllllllIlIIlIIlIIIIIl.texture = llllllllllllllllllIlIIlIIlIIIIlI;
    }

    public void set(String llllllllllllllllllIlIIlIIIllIIII) {
        WButton llllllllllllllllllIlIIlIIIllIIll;
        if (llllllllllllllllllIlIIlIIIllIIll.text == null || (double)Math.round(llllllllllllllllllIlIIlIIIllIIll.theme.textWidth(llllllllllllllllllIlIIlIIIllIIII)) != llllllllllllllllllIlIIlIIIllIIll.textWidth) {
            llllllllllllllllllIlIIlIIIllIIll.invalidate();
        }
        llllllllllllllllllIlIIlIIIllIIll.text = llllllllllllllllllIlIIlIIIllIIII;
    }

    @Override
    protected void onCalculateSize() {
        WButton llllllllllllllllllIlIIlIIIlllIII;
        double llllllllllllllllllIlIIlIIIlllIIl = llllllllllllllllllIlIIlIIIlllIII.pad();
        if (llllllllllllllllllIlIIlIIIlllIII.text != null) {
            llllllllllllllllllIlIIlIIIlllIII.textWidth = llllllllllllllllllIlIIlIIIlllIII.theme.textWidth(llllllllllllllllllIlIIlIIIlllIII.text);
            llllllllllllllllllIlIIlIIIlllIII.width = llllllllllllllllllIlIIlIIIlllIIl + llllllllllllllllllIlIIlIIIlllIII.textWidth + llllllllllllllllllIlIIlIIIlllIIl;
            llllllllllllllllllIlIIlIIIlllIII.height = llllllllllllllllllIlIIlIIIlllIIl + llllllllllllllllllIlIIlIIIlllIII.theme.textHeight() + llllllllllllllllllIlIIlIIIlllIIl;
        } else {
            double llllllllllllllllllIlIIlIIIlllIll = llllllllllllllllllIlIIlIIIlllIII.theme.textHeight();
            llllllllllllllllllIlIIlIIIlllIII.width = llllllllllllllllllIlIIlIIIlllIIl + llllllllllllllllllIlIIlIIIlllIll + llllllllllllllllllIlIIlIIIlllIIl;
            llllllllllllllllllIlIIlIIIlllIII.height = llllllllllllllllllIlIIlIIIlllIIl + llllllllllllllllllIlIIlIIIlllIll + llllllllllllllllllIlIIlIIIlllIIl;
        }
    }
}

