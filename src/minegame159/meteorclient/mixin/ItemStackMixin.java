/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1836
 *  net.minecraft.class_1937
 *  net.minecraft.class_2561
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package minegame159.meteorclient.mixin;

import java.util.List;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.player.FinishUsingItem;
import minegame159.meteorclient.events.entity.player.StoppedUsingItemEvent;
import minegame159.meteorclient.events.game.GetTooltipEvent;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={class_1799.class})
public abstract class ItemStackMixin {
    @Inject(method={"getTooltip"}, at={@At(value="TAIL")}, locals=LocalCapture.CAPTURE_FAILSOFT)
    private void onGetTooltip(class_1657 class_16572, class_1836 class_18362, CallbackInfoReturnable<List<class_2561>> callbackInfoReturnable, List<class_2561> list) {
        if (Utils.canUpdate()) {
            MeteorClient.EVENT_BUS.post(GetTooltipEvent.Append.get((class_1799)this, list));
        }
    }

    @Inject(method={"finishUsing"}, at={@At(value="HEAD")})
    private void onFinishUsing(class_1937 class_19372, class_1309 class_13092, CallbackInfoReturnable<class_1799> callbackInfoReturnable) {
        if (class_13092 == Utils.mc.field_1724) {
            MeteorClient.EVENT_BUS.post(FinishUsingItem.get((class_1799)this));
        }
    }

    @Inject(method={"onStoppedUsing"}, at={@At(value="HEAD")})
    private void onStoppedUsing(class_1937 class_19372, class_1309 class_13092, int n, CallbackInfo callbackInfo) {
        if (class_13092 == Utils.mc.field_1724) {
            MeteorClient.EVENT_BUS.post(StoppedUsingItemEvent.get((class_1799)this));
        }
    }
}

