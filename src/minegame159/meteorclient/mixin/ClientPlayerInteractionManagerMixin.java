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
    private void onClickSlot(int syncId, int slotId, int clickData, class_1713 actionType, class_1657 player, CallbackInfoReturnable<class_1799> info) {
        if (actionType == class_1713.field_7795 && slotId >= 0 && slotId < player.field_7512.field_7761.size()) {
            if (MeteorClient.EVENT_BUS.post(DropItemsEvent.get(((class_1735)player.field_7512.field_7761.get(slotId)).method_7677())).isCancelled()) {
                info.setReturnValue((Object)class_1799.field_8037);
            }
        } else if (slotId == -999 && MeteorClient.EVENT_BUS.post(DropItemsEvent.get(player.field_7514.method_7399())).isCancelled()) {
            info.setReturnValue((Object)class_1799.field_8037);
        }
    }

    @Inject(method={"dropCreativeStack"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDropCreativeStack(class_1799 stack, CallbackInfo info) {
        if (MeteorClient.EVENT_BUS.post(DropItemsEvent.get(stack)).isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method={"attackEntity"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAttackEntity(class_1657 player, class_1297 target, CallbackInfo info) {
        AttackEntityEvent event = MeteorClient.EVENT_BUS.post(AttackEntityEvent.get(target));
        if (event.isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method={"attackBlock"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAttackBlock(class_2338 blockPos, class_2350 direction, CallbackInfoReturnable<Boolean> info) {
        StartBreakingBlockEvent event = MeteorClient.EVENT_BUS.post(StartBreakingBlockEvent.get(blockPos, direction));
        if (event.isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method={"getReachDistance"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetReachDistance(CallbackInfoReturnable<Float> info) {
        if (Modules.get().isActive(Reach.class)) {
            info.setReturnValue((Object)Float.valueOf(Modules.get().get(Reach.class).getReach()));
        }
    }

    @Redirect(method={"updateBlockBreakingProgress"}, at=@At(value="FIELD", target="Lnet/minecraft/client/network/ClientPlayerInteractionManager;blockBreakingCooldown:I", opcode=181))
    private void onMethod_2902SetField_3716Proxy(class_636 interactionManager, int value) {
        if (Modules.get().isActive(Nuker.class)) {
            value = 0;
        }
        if (Modules.get().isActive(NoBreakDelay.class)) {
            value = 0;
        }
        this.field_3716 = value;
    }

    @Redirect(method={"attackBlock"}, at=@At(value="FIELD", target="Lnet/minecraft/client/network/ClientPlayerInteractionManager;blockBreakingCooldown:I", opcode=181))
    private void onAttackBlockSetField_3719Proxy(class_636 interactionManager, int value) {
        if (Modules.get().isActive(Nuker.class)) {
            value = 0;
        }
        this.field_3716 = value;
    }

    @Inject(method={"breakBlock"}, at={@At(value="HEAD")})
    private void onBreakBlock(class_2338 blockPos, CallbackInfoReturnable<Boolean> info) {
        MeteorClient.EVENT_BUS.post(BreakBlockEvent.get(blockPos));
    }

    @Inject(method={"interactItem"}, at={@At(value="HEAD")}, cancellable=true)
    private void onInteractItem(class_1657 player, class_1937 world, class_1268 hand, CallbackInfoReturnable<class_1269> info) {
        InteractItemEvent event = MeteorClient.EVENT_BUS.post(InteractItemEvent.get(hand));
        if (event.toReturn != null) {
            info.setReturnValue((Object)event.toReturn);
        }
    }

    @Override
    public void syncSelectedSlot2() {
        this.method_2911();
    }
}

