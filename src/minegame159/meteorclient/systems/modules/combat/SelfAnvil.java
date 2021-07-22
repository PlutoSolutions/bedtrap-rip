/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_2199
 *  net.minecraft.class_2248
 *  net.minecraft.class_471
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1792;
import net.minecraft.class_2199;
import net.minecraft.class_2248;
import net.minecraft.class_471;

public class SelfAnvil
extends Module {
    @EventHandler
    private void onTick(TickEvent.Pre llIllIIIlIIIIIl) {
        SelfAnvil llIllIIIlIIIIlI;
        if (BlockUtils.place(llIllIIIlIIIIlI.mc.field_1724.method_24515().method_10069(0, 2, 0), InvUtils.findInHotbar(llIllIIIIllllIl -> class_2248.method_9503((class_1792)llIllIIIIllllIl.method_7909()) instanceof class_2199), 0)) {
            llIllIIIlIIIIlI.toggle();
        }
    }

    public SelfAnvil() {
        super(Categories.Combat, "self-anvil", "Automatically places an anvil on you to prevent other players from going into your hole.");
        SelfAnvil llIllIIIlIIlIIl;
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent llIllIIIlIIIlIl) {
        if (llIllIIIlIIIlIl.screen instanceof class_471) {
            llIllIIIlIIIlIl.cancel();
        }
    }
}

