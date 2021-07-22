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
    private /* synthetic */ boolean valid;
    protected /* synthetic */ String text;

    @Override
    public boolean render(GuiRenderer lIIIIIIllIlIlll, double lIIIIIIllIllIll, double lIIIIIIllIlIlIl, double lIIIIIIllIlIlII) {
        WTooltip lIIIIIIllIlllIl;
        if (!lIIIIIIllIlllIl.valid) {
            lIIIIIIllIlllIl.calculateSize();
            lIIIIIIllIlllIl.calculateWidgetPositions();
            lIIIIIIllIlllIl.valid = true;
        }
        return super.render(lIIIIIIllIlIlll, lIIIIIIllIllIll, lIIIIIIllIlIlIl, lIIIIIIllIlIlII);
    }

    public WTooltip(String lIIIIIIlllIlIll) {
        WTooltip lIIIIIIlllIllII;
        lIIIIIIlllIllII.text = lIIIIIIlllIlIll;
    }

    @Override
    public void invalidate() {
        lIIIIIIlllIIlII.valid = false;
    }

    @Override
    public void init() {
        WTooltip lIIIIIIlllIIllI;
        lIIIIIIlllIIllI.add(lIIIIIIlllIIllI.theme.label(lIIIIIIlllIIllI.text)).pad(4.0);
    }
}

