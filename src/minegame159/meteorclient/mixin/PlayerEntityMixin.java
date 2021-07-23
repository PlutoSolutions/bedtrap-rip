/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.DropItemsEvent;
import minegame159.meteorclient.events.entity.player.ClipAtLedgeEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.Anchor;
import minegame159.meteorclient.systems.modules.player.SpeedMine;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1657.class})
public class PlayerEntityMixin {
    @Inject(method={"clipAtLedge"}, at={@At(value="HEAD")}, cancellable=true)
    protected void clipAtLedge(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        ClipAtLedgeEvent clipAtLedgeEvent = MeteorClient.EVENT_BUS.post(ClipAtLedgeEvent.get());
        if (clipAtLedgeEvent.isSet()) {
            callbackInfoReturnable.setReturnValue((Object)clipAtLedgeEvent.isClip());
        }
    }

    @Inject(method={"dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDropItem(class_1799 class_17992, boolean bl, boolean bl2, CallbackInfoReturnable<class_1542> callbackInfoReturnable) {
        if (Utils.mc.field_1687.field_9236 && MeteorClient.EVENT_BUS.post(DropItemsEvent.get(class_17992)).isCancelled()) {
            callbackInfoReturnable.cancel();
        }
    }

    @Inject(method={"getBlockBreakingSpeed"}, at={@At(value="RETURN")}, cancellable=true)
    public void onGetBlockBreakingSpeed(class_2680 class_26802, CallbackInfoReturnable<Float> callbackInfoReturnable) {
        SpeedMine speedMine = Modules.get().get(SpeedMine.class);
        if (!speedMine.isActive() || speedMine.mode.get() != SpeedMine.Mode.Normal) {
            return;
        }
        callbackInfoReturnable.setReturnValue((Object)Float.valueOf((float)((double)((Float)callbackInfoReturnable.getReturnValue()).floatValue() * speedMine.modifier.get())));
    }

    @Inject(method={"jump"}, at={@At(value="HEAD")}, cancellable=true)
    public void dontJump(CallbackInfo callbackInfo) {
        Anchor anchor = Modules.get().get(Anchor.class);
        if (anchor.isActive() && anchor.cancelJump) {
            callbackInfo.cancel();
        }
    }
}

