/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;
import minegame159.meteorclient.utils.Utils;

public class ServerHud
extends DoubleTextHudElement {
    @Override
    protected String getRight() {
        if (!Utils.canUpdate()) {
            return "None";
        }
        return Utils.getWorldName();
    }

    public ServerHud(HUD hUD) {
        super(hUD, "server", "Displays the server you're currently in.", "Server: ");
    }
}

