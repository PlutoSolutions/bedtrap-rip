/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.render.color.Color;

public abstract class DoubleTextHudElement
extends HudElement {
    protected Color rightColor;
    protected boolean visible = true;
    private double leftWidth;
    private String right;
    private String left;

    protected void setLeft(String string) {
        this.left = string;
        this.leftWidth = 0.0;
    }

    public DoubleTextHudElement(HUD hUD, String string, String string2, String string3) {
        super(hUD, string, string2, true);
        this.rightColor = hUD.secondaryColor.get();
        this.left = string3;
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        if (!this.visible) {
            return;
        }
        double d = this.box.getX();
        double d2 = this.box.getY();
        hudRenderer.text(this.left, d, d2, this.hud.primaryColor.get());
        hudRenderer.text(this.right, d + this.leftWidth, d2, this.rightColor);
    }

    protected abstract String getRight();

    public DoubleTextHudElement(HUD hUD, String string, String string2, String string3, boolean bl) {
        super(hUD, string, string2, bl);
        this.rightColor = hUD.secondaryColor.get();
        this.left = string3;
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        this.right = this.getRight();
        this.leftWidth = hudRenderer.textWidth(this.left);
        this.box.setSize(this.leftWidth + hudRenderer.textWidth(this.right), hudRenderer.textHeight());
    }
}

