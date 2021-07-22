/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;
import minegame159.meteorclient.systems.modules.world.Timer;

public class SpeedHud
extends DoubleTextHudElement {
    @Override
    protected String getRight() {
        SpeedHud lllllllllllllllllIllllIIlllIlIlI;
        if (lllllllllllllllllIllllIIlllIlIlI.isInEditor()) {
            return "0,0";
        }
        double lllllllllllllllllIllllIIlllIIllI = Math.abs(lllllllllllllllllIllllIIlllIlIlI.mc.field_1724.method_23317() - lllllllllllllllllIllllIIlllIlIlI.mc.field_1724.field_6014);
        double lllllllllllllllllIllllIIlllIIlIl = Math.abs(lllllllllllllllllIllllIIlllIlIlI.mc.field_1724.method_23321() - lllllllllllllllllIllllIIlllIlIlI.mc.field_1724.field_5969);
        double lllllllllllllllllIllllIIlllIIlII = Math.sqrt(lllllllllllllllllIllllIIlllIIllI * lllllllllllllllllIllllIIlllIIllI + lllllllllllllllllIllllIIlllIIlIl * lllllllllllllllllIllllIIlllIIlIl);
        if (Modules.get().isActive(Timer.class)) {
            lllllllllllllllllIllllIIlllIIlII *= Modules.get().get(Timer.class).getMultiplier();
        }
        return String.format("%.1f", lllllllllllllllllIllllIIlllIIlII * 20.0);
    }

    public SpeedHud(HUD lllllllllllllllllIllllIIllllIlll) {
        super(lllllllllllllllllIllllIIllllIlll, "speed", "Displays your horizontal speed.", "Speed: ");
        SpeedHud lllllllllllllllllIllllIIlllllIII;
    }
}

