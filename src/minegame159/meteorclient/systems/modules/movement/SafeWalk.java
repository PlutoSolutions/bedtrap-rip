/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.ClipAtLedgeEvent;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class SafeWalk
extends Module {
    @EventHandler
    private void onClipAtLedge(ClipAtLedgeEvent lllllllllllllllllllllIlIlIllIllI) {
        lllllllllllllllllllllIlIlIllIllI.setClip(true);
    }

    public SafeWalk() {
        super(Categories.Movement, "safe-walk", "Prevents you from walking off blocks. Useful over a void.");
        SafeWalk lllllllllllllllllllllIlIlIlllIlI;
    }
}

