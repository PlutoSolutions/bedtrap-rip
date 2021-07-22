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
    private final /* synthetic */ class_1044 texture;
    private final /* synthetic */ double rotation;
    private final /* synthetic */ double width;
    private final /* synthetic */ double height;

    public WTexture(double llllllllllllllllllIllIIlllIIIlII, double llllllllllllllllllIllIIlllIIlIII, double llllllllllllllllllIllIIlllIIIlll, class_1044 llllllllllllllllllIllIIlllIIIIIl) {
        WTexture llllllllllllllllllIllIIlllIIlIlI;
        llllllllllllllllllIllIIlllIIlIlI.width = llllllllllllllllllIllIIlllIIIlII;
        llllllllllllllllllIllIIlllIIlIlI.height = llllllllllllllllllIllIIlllIIlIII;
        llllllllllllllllllIllIIlllIIlIlI.rotation = llllllllllllllllllIllIIlllIIIlll;
        llllllllllllllllllIllIIlllIIlIlI.texture = llllllllllllllllllIllIIlllIIIIIl;
    }

    @Override
    protected void onRender(GuiRenderer llllllllllllllllllIllIIllIllIlIl, double llllllllllllllllllIllIIllIlllIIl, double llllllllllllllllllIllIIllIlllIII, double llllllllllllllllllIllIIllIllIlll) {
        WTexture llllllllllllllllllIllIIllIllIllI;
        llllllllllllllllllIllIIllIllIlIl.texture(llllllllllllllllllIllIIllIllIllI.x, llllllllllllllllllIllIIllIllIllI.y, ((WWidget)llllllllllllllllllIllIIllIllIllI).width, ((WWidget)llllllllllllllllllIllIIllIllIllI).height, llllllllllllllllllIllIIllIllIllI.rotation, llllllllllllllllllIllIIllIllIllI.texture);
    }

    @Override
    protected void onCalculateSize() {
        WTexture llllllllllllllllllIllIIllIllllll;
        ((WWidget)llllllllllllllllllIllIIllIllllll).width = llllllllllllllllllIllIIllIllllll.theme.scale(llllllllllllllllllIllIIllIllllll.width);
        ((WWidget)llllllllllllllllllIllIIllIllllll).height = llllllllllllllllllIllIIllIllllll.theme.scale(llllllllllllllllllIllIIllIllllll.height);
    }
}

