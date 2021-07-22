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
    private static final /* synthetic */ Color RED;
    private static final /* synthetic */ Color AMBER;
    private static final /* synthetic */ Color YELLOW;

    public LagNotifierHud(HUD llllllllllllllllllIlIlIlIIlIlIlI) {
        super(llllllllllllllllllIlIlIlIIlIlIlI, "lag-notifier", "Displays if the server is lagging in ticks.", "Time since last tick ");
        LagNotifierHud llllllllllllllllllIlIlIlIIlIlIIl;
    }

    static {
        RED = new Color(225, 45, 45);
        AMBER = new Color(235, 158, 52);
        YELLOW = new Color(255, 255, 5);
    }

    @Override
    protected String getRight() {
        LagNotifierHud llllllllllllllllllIlIlIlIIlIIlIl;
        if (llllllllllllllllllIlIlIlIIlIIlIl.isInEditor()) {
            llllllllllllllllllIlIlIlIIlIIlIl.rightColor = RED;
            llllllllllllllllllIlIlIlIIlIIlIl.visible = true;
            return "4,3";
        }
        float llllllllllllllllllIlIlIlIIlIIlII = TickRate.INSTANCE.getTimeSinceLastTick();
        llllllllllllllllllIlIlIlIIlIIlIl.rightColor = llllllllllllllllllIlIlIlIIlIIlII > 10.0f ? RED : (llllllllllllllllllIlIlIlIIlIIlII > 3.0f ? AMBER : YELLOW);
        llllllllllllllllllIlIlIlIIlIIlIl.visible = llllllllllllllllllIlIlIlIIlIIlII >= 1.0f;
        return String.format("%.1f", Float.valueOf(llllllllllllllllllIlIlIlIIlIIlII));
    }
}

