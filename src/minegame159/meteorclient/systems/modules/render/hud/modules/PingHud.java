/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_640
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;
import net.minecraft.class_640;

public class PingHud
extends DoubleTextHudElement {
    public PingHud(HUD hUD) {
        super(hUD, "ping", "Displays your ping.", "Ping: ");
    }

    @Override
    protected String getRight() {
        if (this.isInEditor()) {
            return "0";
        }
        class_640 class_6402 = this.mc.method_1562().method_2871(this.mc.field_1724.method_5667());
        if (class_6402 != null) {
            return Integer.toString(class_6402.method_2959());
        }
        return "0";
    }
}

