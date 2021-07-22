/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.world.TickRate;

public class LagNotifierHud
extends DoubleTextHudElement {
    private static final Color RED = new Color(225, 45, 45);
    private static final Color AMBER = new Color(235, 158, 52);
    private static final Color YELLOW = new Color(255, 255, 5);

    public LagNotifierHud(HUD hUD) {
        super(hUD, "lag-notifier", "Displays if the server is lagging in ticks.", "Time since last tick ");
    }

    @Override
    protected String getRight() {
        if (this.isInEditor()) {
            this.rightColor = RED;
            this.visible = true;
            return "4,3";
        }
        float f = TickRate.INSTANCE.getTimeSinceLastTick();
        this.rightColor = f > 10.0f ? RED : (f > 3.0f ? AMBER : YELLOW);
        this.visible = f >= 1.0f;
        return String.format("%.1f", Float.valueOf(f));
    }
}

