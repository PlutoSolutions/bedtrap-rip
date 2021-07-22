/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1542
 *  net.minecraft.class_2653
 *  net.minecraft.class_2664
 *  net.minecraft.class_2672
 *  net.minecraft.class_2678
 *  net.minecraft.class_2716
 *  net.minecraft.class_2767
 *  net.minecraft.class_2775
 *  net.minecraft.class_2818
 *  net.minecraft.class_310
 *  net.minecraft.class_634
 *  net.minecraft.class_638
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.EntityDestroyEvent;
import minegame159.meteorclient.events.entity.player.PickItemsEvent;
import minegame159.meteorclient.events.game.GameJoinedEvent;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.packets.ContainerSlotUpdateEvent;
import minegame159.meteorclient.events.packets.PlaySoundPacketEvent;
import minegame159.meteorclient.events.world.ChunkDataEvent;
import minegame159.meteorclient.mixininterface.IExplosionS2CPacket;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.Velocity;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_2653;
import net.minecraft.class_2664;
import net.minecraft.class_2672;
import net.minecraft.class_2678;
import net.minecraft.class_2716;
import net.minecraft.class_2767;
import net.minecraft.class_2775;
import net.minecraft.class_2818;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={class_634.class})
public abstract class ClientPlayNetworkHandlerMixin {
    @Shadow
    private class_310 field_3690;
    @Shadow
    private class_638 field_3699;
    private boolean worldNotNull;

    @Inject(at={@At(value="HEAD")}, method={"onGameJoin"})
    private void onGameJoinHead(class_2678 class_26782, CallbackInfo callbackInfo) {
        this.worldNotNull = this.field_3699 != null;
    }

    @Inject(at={@At(value="TAIL")}, method={"onGameJoin"})
    private void onGameJoinTail(class_2678 class_26782, CallbackInfo callbackInfo) {
        if (this.worldNotNull) {
            MeteorClient.EVENT_BUS.post(GameLeftEvent.get());
        }
        MeteorClient.EVENT_BUS.post(GameJoinedEvent.get());
    }

    @Inject(at={@At(value="HEAD")}, method={"onPlaySound"})
    private void onPlaySound(class_2767 class_27672, CallbackInfo callbackInfo) {
        MeteorClient.EVENT_BUS.post(PlaySoundPacketEvent.get(class_27672));
    }

    @Inject(method={"onChunkData"}, at={@At(value="TAIL")})
    private void onChunkData(class_2672 class_26722, CallbackInfo callbackInfo) {
        class_2818 class_28182 = this.field_3690.field_1687.method_8497(class_26722.method_11523(), class_26722.method_11524());
        MeteorClient.EVENT_BUS.post(ChunkDataEvent.get(class_28182));
    }

    @Inject(method={"onScreenHandlerSlotUpdate"}, at={@At(value="TAIL")})
    private void onContainerSlotUpdate(class_2653 class_26532, CallbackInfo callbackInfo) {
        MeteorClient.EVENT_BUS.post(ContainerSlotUpdateEvent.get(class_26532));
    }

    @Inject(method={"onEntitiesDestroy"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/world/ClientWorld;removeEntity(I)V")}, locals=LocalCapture.CAPTURE_FAILSOFT)
    private void onEntityDestroy(class_2716 class_27162, CallbackInfo callbackInfo, int n, int n2) {
        MeteorClient.EVENT_BUS.post(EntityDestroyEvent.get(this.field_3690.field_1687.method_8469(n2)));
    }

    @Inject(method={"onExplosion"}, at={@At(value="INVOKE", target="Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/util/thread/ThreadExecutor;)V", shift=At.Shift.AFTER)})
    private void onExplosionVelocity(class_2664 class_26642, CallbackInfo callbackInfo) {
        Velocity velocity = Modules.get().get(Velocity.class);
        if (!velocity.explosions.get().booleanValue()) {
            return;
        }
        ((IExplosionS2CPacket)class_26642).setVelocityX((float)((double)class_26642.method_11472() * velocity.getHorizontal(velocity.explosionsHorizontal)));
        ((IExplosionS2CPacket)class_26642).setVelocityY((float)((double)class_26642.method_11473() * velocity.getVertical(velocity.explosionsVertical)));
        ((IExplosionS2CPacket)class_26642).setVelocityZ((float)((double)class_26642.method_11474() * velocity.getHorizontal(velocity.explosionsHorizontal)));
    }

    @Inject(method={"onItemPickupAnimation"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/world/ClientWorld;getEntityById(I)Lnet/minecraft/entity/Entity;", ordinal=0)})
    private void onItemPickupAnimation(class_2775 class_27752, CallbackInfo callbackInfo) {
        class_1297 class_12972 = this.field_3690.field_1687.method_8469(class_27752.method_11915());
        class_1297 class_12973 = this.field_3690.field_1687.method_8469(class_27752.method_11912());
        if (class_12972 instanceof class_1542 && class_12973 == this.field_3690.field_1724) {
            MeteorClient.EVENT_BUS.post(PickItemsEvent.get(((class_1542)class_12972).method_6983(), class_27752.method_11913()));
        }
    }
}

