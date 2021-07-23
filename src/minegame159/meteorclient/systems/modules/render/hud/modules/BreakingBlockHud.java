/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.mixin.ClientPlayerInteractionManagerAccessor;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;

public class BreakingBlockHud
extends DoubleTextHudElement {
    @Override
    protected String getRight() {
        if (this.isInEditor()) {
            return "0%";
        }
        return String.format("%.0f%%", Float.valueOf(((ClientPlayerInteractionManagerAccessor)this.mc.field_1761).getBreakingProgress() * 100.0f));
    }

    public BreakingBlockHud(HUD hUD) {
        super(hUD, "breaking-block", "Displays percentage of the block you are breaking.", "Breaking Block: ");
    }
}

