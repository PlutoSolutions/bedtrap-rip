/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_1835
 *  net.minecraft.class_1937
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1835;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_1835.class})
public class TridentItemMixin {
    @Inject(method={"onStoppedUsing"}, at={@At(value="HEAD")})
    private void onStoppedUsingHead(class_1799 stack, class_1937 world, class_1309 user, int remainingUseTicks, CallbackInfo info) {
        if (user == Utils.mc.field_1724) {
            Utils.isReleasingTrident = true;
        }
    }

    @Inject(method={"onStoppedUsing"}, at={@At(value="TAIL")})
    private void onStoppedUsingTail(class_1799 stack, class_1937 world, class_1309 user, int remainingUseTicks, CallbackInfo info) {
        if (user == Utils.mc.field_1724) {
            Utils.isReleasingTrident = false;
        }
    }
}

