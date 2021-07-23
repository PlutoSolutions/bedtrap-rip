/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.BlockUpdateEvent;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_2818.class})
public class WorldChunkMixin {
    @Shadow
    @Final
    private class_1937 field_12858;

    @Inject(method={"setBlockState"}, at={@At(value="TAIL")})
    private void onSetBlockState(class_2338 class_23382, class_2680 class_26802, boolean bl, CallbackInfoReturnable<class_2680> callbackInfoReturnable) {
        if (this.field_12858.field_9236) {
            MeteorClient.EVENT_BUS.post(BlockUpdateEvent.get(class_23382, (class_2680)callbackInfoReturnable.getReturnValue(), class_26802));
        }
    }
}

