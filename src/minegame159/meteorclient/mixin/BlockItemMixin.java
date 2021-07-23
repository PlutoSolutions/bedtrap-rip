/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.player.PlaceBlockEvent;
import net.minecraft.class_1747;
import net.minecraft.class_1750;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1747.class})
public class BlockItemMixin {
    @Inject(method={"place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z"}, at={@At(value="HEAD")})
    private void onPlace(class_1750 class_17502, class_2680 class_26802, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        MeteorClient.EVENT_BUS.post(PlaceBlockEvent.get(class_17502.method_8037(), class_26802.method_26204()));
    }
}

