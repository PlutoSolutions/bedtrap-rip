/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_636
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.DropItemsEvent;
import minegame159.meteorclient.events.entity.player.AttackEntityEvent;
import minegame159.meteorclient.events.entity.player.BreakBlockEvent;
import minegame159.meteorclient.events.entity.player.InteractItemEvent;
import minegame159.meteorclient.events.entity.player.StartBreakingBlockEvent;
import minegame159.meteorclient.mixininterface.IClientPlayerInteractionManager;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.player.NoBreakDelay;
import minegame159.meteorclient.systems.modules.player.Reach;
import minegame159.meteorclient.systems.modules.world.Nuker;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_636.class})
public abstract class ClientPlayerInteractionManagerMixin
implements IClientPlayerInteractionManager {
    @Shadow
    private int field_3716;

    @Shadow
    protected abstract void method_2911();

    @Inject(method={"clickSlot"}, at={@At(value="HEAD")}, cancellable=true)
    private void onClickSlot(int n, int n2, int n3, class_1713 class_17132, class_1657 class_16572, CallbackInfoReturnable<class_1799> callbackInfoReturnable) {
        if (class_17132 == class_1713.field_7795 && n2 >= 0 && n2 < class_16572.field_7512.field_7761.size()) {
            if (MeteorClient.EVENT_BUS.post(DropItemsEvent.get(((class_1735)class_16572.field_7512.field_7761.get(n2)).method_7677())).isCancelled()) {
                callbackInfoReturnable.setReturnValue((Object)class_1799.field_8037);
            }
        } else if (n2 == -999 && MeteorClient.EVENT_BUS.post(DropItemsEvent.get(class_16572.field_7514.method_7399())).isCancelled()) {
            callbackInfoReturnable.setReturnValue((Object)class_1799.field_8037);
        }
    }

    @Inject(method={"dropCreativeStack"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDropCreativeStack(class_1799 class_17992, CallbackInfo callbackInfo) {
        if (MeteorClient.EVENT_BUS.post(DropItemsEvent.get(class_17992)).isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"attackEntity"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAttackEntity(class_1657 class_16572, class_1297 class_12972, CallbackInfo callbackInfo) {
        AttackEntityEvent attackEntityEvent = MeteorClient.EVENT_BUS.post(AttackEntityEvent.get(class_12972));
        if (attackEntityEvent.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"attackBlock"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAttackBlock(class_2338 class_23382, class_2350 class_23502, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        StartBreakingBlockEvent startBreakingBlockEvent = MeteorClient.EVENT_BUS.post(StartBreakingBlockEvent.get(class_23382, class_23502));
        if (startBreakingBlockEvent.isCancelled()) {
            callbackInfoReturnable.cancel();
        }
    }

    @Inject(method={"getReachDistance"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetReachDistance(CallbackInfoReturnable<Float> callbackInfoReturnable) {
        if (Modules.get().isActive(Reach.class)) {
            callbackInfoReturnable.setReturnValue((Object)Float.valueOf(Modules.get().get(Reach.class).getReach()));
        }
    }

    @Redirect(method={"updateBlockBreakingProgress"}, at=@At(value="FIELD", target="Lnet/minecraft/client/network/ClientPlayerInteractionManager;blockBreakingCooldown:I", opcode=181))
    private void onMethod_2902SetField_3716Proxy(class_636 class_6362, int n) {
        if (Modules.get().isActive(Nuker.class)) {
            n = 0;
        }
        if (Modules.get().isActive(NoBreakDelay.class)) {
            n = 0;
        }
        this.field_3716 = n;
    }

    @Redirect(method={"attackBlock"}, at=@At(value="FIELD", target="Lnet/minecraft/client/network/ClientPlayerInteractionManager;blockBreakingCooldown:I", opcode=181))
    private void onAttackBlockSetField_3719Proxy(class_636 class_6362, int n) {
        if (Modules.get().isActive(Nuker.class)) {
            n = 0;
        }
        this.field_3716 = n;
    }

    @Inject(method={"breakBlock"}, at={@At(value="HEAD")})
    private void onBreakBlock(class_2338 class_23382, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        MeteorClient.EVENT_BUS.post(BreakBlockEvent.get(class_23382));
    }

    @Inject(method={"interactItem"}, at={@At(value="HEAD")}, cancellable=true)
    private void onInteractItem(class_1657 class_16572, class_1937 class_19372, class_1268 class_12682, CallbackInfoReturnable<class_1269> callbackInfoReturnable) {
        InteractItemEvent interactItemEvent = MeteorClient.EVENT_BUS.post(InteractItemEvent.get(class_12682));
        if (interactItemEvent.toReturn != null) {
            callbackInfoReturnable.setReturnValue((Object)interactItemEvent.toReturn);
        }
    }

    @Override
    public void syncSelectedSlot2() {
        this.method_2911();
    }
}

