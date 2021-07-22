/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.mixin.MinecraftClientAccessor;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;

public class FpsHud
extends DoubleTextHudElement {
    @Override
    protected String getRight() {
        FpsHud llllllllllllllllllIlllIlIlIlIlIl;
        return Integer.toString(((MinecraftClientAccessor)llllllllllllllllllIlllIlIlIlIlIl.mc).getFps());
    }

    public FpsHud(HUD llllllllllllllllllIlllIlIlIlIlll) {
        super(llllllllllllllllllIlllIlIlIlIlll, "fps", "Displays your FPS.", "FPS: ");
        FpsHud llllllllllllllllllIlllIlIlIllIlI;
    }
}

