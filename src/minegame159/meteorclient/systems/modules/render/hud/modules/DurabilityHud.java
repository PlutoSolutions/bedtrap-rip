/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;

public class DurabilityHud
extends DoubleTextHudElement {
    @Override
    protected String getRight() {
        if (this.isInEditor()) {
            return "159";
        }
        Integer n = null;
        if (!this.mc.field_1724.method_6047().method_7960() && this.mc.field_1724.method_6047().method_7963()) {
            n = this.mc.field_1724.method_6047().method_7936() - this.mc.field_1724.method_6047().method_7919();
        }
        return n == null ? "" : n.toString();
    }

    public DurabilityHud(HUD hUD) {
        super(hUD, "durability", "Displays durability of the item you are holding.", "Durability: ");
    }
}

