/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;

public class WatermarkHud
extends DoubleTextHudElement {
    public WatermarkHud(HUD hUD) {
        super(hUD, "watermark", "Displays a BedTrap watermark.", "BedTrap ");
    }

    @Override
    protected String getRight() {
        return "0.3.1";
    }
}

