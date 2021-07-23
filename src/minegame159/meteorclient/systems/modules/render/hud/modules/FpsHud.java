/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.mixin.MinecraftClientAccessor;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;

public class FpsHud
extends DoubleTextHudElement {
    @Override
    protected String getRight() {
        return Integer.toString(((MinecraftClientAccessor)this.mc).getFps());
    }

    public FpsHud(HUD hUD) {
        super(hUD, "fps", "Displays your FPS.", "FPS: ");
    }
}

