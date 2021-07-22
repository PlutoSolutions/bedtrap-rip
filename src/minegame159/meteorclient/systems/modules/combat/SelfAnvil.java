/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
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
import net.minecraft.class_1799;
import net.minecraft.class_2199;
import net.minecraft.class_2248;
import net.minecraft.class_471;

public class SelfAnvil
extends Module {
    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (BlockUtils.place(this.mc.field_1724.method_24515().method_10069(0, 2, 0), InvUtils.findInHotbar(SelfAnvil::lambda$onTick$0), 0)) {
            this.toggle();
        }
    }

    private static boolean lambda$onTick$0(class_1799 class_17992) {
        return class_2248.method_9503((class_1792)class_17992.method_7909()) instanceof class_2199;
    }

    public SelfAnvil() {
        super(Categories.Combat, "self-anvil", "Automatically places an anvil on you to prevent other players from going into your hole.");
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent openScreenEvent) {
        if (openScreenEvent.screen instanceof class_471) {
            openScreenEvent.cancel();
        }
    }
}

