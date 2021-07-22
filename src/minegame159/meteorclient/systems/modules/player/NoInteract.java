/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_465
 *  net.minecraft.class_485
 */
package minegame159.meteorclient.systems.modules.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_465;
import net.minecraft.class_485;

public class NoInteract
extends Module {
    public NoInteract() {
        super(Categories.Player, "no-interact", "Blocks interactions with certain types of inputs.");
    }

    @EventHandler
    private void onScreenOpen(OpenScreenEvent openScreenEvent) {
        if (openScreenEvent.screen == null) {
            return;
        }
        if (!openScreenEvent.screen.method_25421() && !(openScreenEvent.screen instanceof class_485) && openScreenEvent.screen instanceof class_465) {
            openScreenEvent.setCancelled(true);
        }
    }
}

