/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;

public class DurabilityHud
extends DoubleTextHudElement {
    @Override
    protected String getRight() {
        DurabilityHud llllllllllllllllllllIlIlIIlIlIll;
        if (llllllllllllllllllllIlIlIIlIlIll.isInEditor()) {
            return "159";
        }
        Integer llllllllllllllllllllIlIlIIlIllII = null;
        if (!llllllllllllllllllllIlIlIIlIlIll.mc.field_1724.method_6047().method_7960() && llllllllllllllllllllIlIlIIlIlIll.mc.field_1724.method_6047().method_7963()) {
            llllllllllllllllllllIlIlIIlIllII = llllllllllllllllllllIlIlIIlIlIll.mc.field_1724.method_6047().method_7936() - llllllllllllllllllllIlIlIIlIlIll.mc.field_1724.method_6047().method_7919();
        }
        return llllllllllllllllllllIlIlIIlIllII == null ? "" : llllllllllllllllllllIlIlIIlIllII.toString();
    }

    public DurabilityHud(HUD llllllllllllllllllllIlIlIIllIIlI) {
        super(llllllllllllllllllllIlIlIIllIIlI, "durability", "Displays durability of the item you are holding.", "Durability: ");
        DurabilityHud llllllllllllllllllllIlIlIIllIIll;
    }
}

