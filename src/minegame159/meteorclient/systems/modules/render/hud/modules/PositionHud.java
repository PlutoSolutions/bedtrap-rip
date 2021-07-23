/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.player.PlayerUtils;

public class PositionHud
extends HudElement {
    private double left2Width;
    private double left1Width;
    private String right1;
    private final String left1 = "Pos: ";
    private String left2;
    private String right2;

    @Override
    public void update(HudRenderer hudRenderer) {
        this.left1Width = hudRenderer.textWidth("Pos: ");
        this.left2 = null;
        if (this.isInEditor()) {
            this.right1 = "0,0 0,0 0,0";
            this.box.setSize(this.left1Width + hudRenderer.textWidth(this.right1), hudRenderer.textHeight() * 2.0 + 2.0);
            return;
        }
        Freecam freecam = Modules.get().get(Freecam.class);
        double d = freecam.isActive() ? this.mc.field_1773.method_19418().method_19326().field_1352 : this.mc.field_1724.method_23317();
        double d2 = freecam.isActive() ? this.mc.field_1773.method_19418().method_19326().field_1351 - (double)this.mc.field_1724.method_18381(this.mc.field_1724.method_18376()) : this.mc.field_1724.method_23318();
        double d3 = freecam.isActive() ? this.mc.field_1773.method_19418().method_19326().field_1350 : this.mc.field_1724.method_23321();
        this.right1 = String.format("%.1f %.1f %.1f", d, d2, d3);
        switch (1.$SwitchMap$minegame159$meteorclient$utils$world$Dimension[PlayerUtils.getDimension().ordinal()]) {
            case 1: {
                this.left2 = "Nether Pos: ";
                this.right2 = String.format("%.1f %.1f %.1f", d / 8.0, d2, d3 / 8.0);
                break;
            }
            case 2: {
                this.left2 = "Overworld Pos: ";
                this.right2 = String.format("%.1f %.1f %.1f", d * 8.0, d2, d3 * 8.0);
            }
        }
        double d4 = this.left1Width + hudRenderer.textWidth(this.right1);
        if (this.left2 != null) {
            this.left2Width = hudRenderer.textWidth(this.left2);
            d4 = Math.max(d4, this.left2Width + hudRenderer.textWidth(this.right2));
        }
        this.box.setSize(d4, hudRenderer.textHeight() * 2.0 + 2.0);
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX();
        double d2 = this.box.getY();
        if (this.left2 != null) {
            hudRenderer.text(this.left2, d, d2, this.hud.primaryColor.get());
            hudRenderer.text(this.right2, d + this.left2Width, d2, this.hud.secondaryColor.get());
        }
        double d3 = this.box.alignX(this.left1Width + hudRenderer.textWidth(this.right1));
        double d4 = hudRenderer.textHeight() + 2.0;
        hudRenderer.text("Pos: ", d + d3, d2 + d4, this.hud.primaryColor.get());
        hudRenderer.text(this.right1, d + d3 + this.left1Width, d2 + d4, this.hud.secondaryColor.get());
    }

    public PositionHud(HUD hUD) {
        super(hUD, "coords", "Displays your coordinates in the world.");
    }
}

