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
    public PingHud(HUD lIIlIllllIIlll) {
        super(lIIlIllllIIlll, "ping", "Displays your ping.", "Ping: ");
        PingHud lIIlIllllIlIII;
    }

    @Override
    protected String getRight() {
        PingHud lIIlIllllIIlII;
        if (lIIlIllllIIlII.isInEditor()) {
            return "0";
        }
        class_640 lIIlIllllIIIll = lIIlIllllIIlII.mc.method_1562().method_2871(lIIlIllllIIlII.mc.field_1724.method_5667());
        if (lIIlIllllIIIll != null) {
            return Integer.toString(lIIlIllllIIIll.method_2959());
        }
        return "0";
    }
}

