/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_418
 */
package minegame159.meteorclient.systems.modules.misc;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_418;

public class AutoRespawn
extends Module {
    public AutoRespawn() {
        super(Categories.Player, "auto-respawn", "Automatically respawns after death.");
        AutoRespawn llllllllllllllllIlIlIllIlIllIllI;
    }

    @EventHandler
    private void onOpenScreenEvent(OpenScreenEvent llllllllllllllllIlIlIllIlIllIIII) {
        AutoRespawn llllllllllllllllIlIlIllIlIllIIll;
        if (!(llllllllllllllllIlIlIllIlIllIIII.screen instanceof class_418)) {
            return;
        }
        llllllllllllllllIlIlIllIlIllIIll.mc.field_1724.method_7331();
        llllllllllllllllIlIlIllIlIllIIII.cancel();
    }
}

