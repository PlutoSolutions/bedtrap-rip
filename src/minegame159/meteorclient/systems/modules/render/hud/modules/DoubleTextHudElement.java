/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.render.color.Color;

public abstract class DoubleTextHudElement
extends HudElement {
    protected /* synthetic */ Color rightColor;
    protected /* synthetic */ boolean visible;
    private /* synthetic */ double leftWidth;
    private /* synthetic */ String right;
    private /* synthetic */ String left;

    protected void setLeft(String lllllllllllllllllIIllIIlllIIIlIl) {
        lllllllllllllllllIIllIIlllIIIllI.left = lllllllllllllllllIIllIIlllIIIlIl;
        lllllllllllllllllIIllIIlllIIIllI.leftWidth = 0.0;
    }

    public DoubleTextHudElement(HUD lllllllllllllllllIIllIIllllIIIII, String lllllllllllllllllIIllIIlllIlllll, String lllllllllllllllllIIllIIllllIIIll, String lllllllllllllllllIIllIIlllIlllIl) {
        super(lllllllllllllllllIIllIIllllIIIII, lllllllllllllllllIIllIIlllIlllll, lllllllllllllllllIIllIIllllIIIll, true);
        DoubleTextHudElement lllllllllllllllllIIllIIllllIIllI;
        lllllllllllllllllIIllIIllllIIllI.visible = true;
        lllllllllllllllllIIllIIllllIIllI.rightColor = lllllllllllllllllIIllIIllllIIIII.secondaryColor.get();
        lllllllllllllllllIIllIIllllIIllI.left = lllllllllllllllllIIllIIlllIlllIl;
    }

    @Override
    public void render(HudRenderer lllllllllllllllllIIllIIlllIIllIl) {
        DoubleTextHudElement lllllllllllllllllIIllIIlllIIlllI;
        if (!lllllllllllllllllIIllIIlllIIlllI.visible) {
            return;
        }
        double lllllllllllllllllIIllIIlllIlIIII = lllllllllllllllllIIllIIlllIIlllI.box.getX();
        double lllllllllllllllllIIllIIlllIIllll = lllllllllllllllllIIllIIlllIIlllI.box.getY();
        lllllllllllllllllIIllIIlllIIllIl.text(lllllllllllllllllIIllIIlllIIlllI.left, lllllllllllllllllIIllIIlllIlIIII, lllllllllllllllllIIllIIlllIIllll, lllllllllllllllllIIllIIlllIIlllI.hud.primaryColor.get());
        lllllllllllllllllIIllIIlllIIllIl.text(lllllllllllllllllIIllIIlllIIlllI.right, lllllllllllllllllIIllIIlllIlIIII + lllllllllllllllllIIllIIlllIIlllI.leftWidth, lllllllllllllllllIIllIIlllIIllll, lllllllllllllllllIIllIIlllIIlllI.rightColor);
    }

    protected abstract String getRight();

    public DoubleTextHudElement(HUD lllllllllllllllllIIllIIlllllIIII, String lllllllllllllllllIIllIIllllIllll, String lllllllllllllllllIIllIIllllIlllI, String lllllllllllllllllIIllIIlllllIIll, boolean lllllllllllllllllIIllIIllllIllII) {
        super(lllllllllllllllllIIllIIlllllIIII, lllllllllllllllllIIllIIllllIllll, lllllllllllllllllIIllIIllllIlllI, lllllllllllllllllIIllIIllllIllII);
        DoubleTextHudElement lllllllllllllllllIIllIIlllllIIIl;
        lllllllllllllllllIIllIIlllllIIIl.visible = true;
        lllllllllllllllllIIllIIlllllIIIl.rightColor = lllllllllllllllllIIllIIlllllIIII.secondaryColor.get();
        lllllllllllllllllIIllIIlllllIIIl.left = lllllllllllllllllIIllIIlllllIIll;
    }

    @Override
    public void update(HudRenderer lllllllllllllllllIIllIIlllIllIIl) {
        DoubleTextHudElement lllllllllllllllllIIllIIlllIllIlI;
        lllllllllllllllllIIllIIlllIllIlI.right = lllllllllllllllllIIllIIlllIllIlI.getRight();
        lllllllllllllllllIIllIIlllIllIlI.leftWidth = lllllllllllllllllIIllIIlllIllIIl.textWidth(lllllllllllllllllIIllIIlllIllIlI.left);
        lllllllllllllllllIIllIIlllIllIlI.box.setSize(lllllllllllllllllIIllIIlllIllIlI.leftWidth + lllllllllllllllllIIllIIlllIllIIl.textWidth(lllllllllllllllllIIllIIlllIllIlI.right), lllllllllllllllllIIllIIlllIllIIl.textHeight());
    }
}

