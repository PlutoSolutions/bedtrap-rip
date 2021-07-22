/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_5294
 *  net.minecraft.class_5294$class_5295
 *  net.minecraft.class_638
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.EntityAddedEvent;
import minegame159.meteorclient.events.entity.EntityRemovedEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import minegame159.meteorclient.systems.modules.world.Ambience;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_5294;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={class_638.class})
public class ClientWorldMixin {
    @Unique
    private final class_5294 endSky = new class_5294.class_5295();
    @Unique
    private final class_5294 customSky = new Ambience.Custom();

    @Inject(method={"addEntityPrivate"}, at={@At(value="TAIL")})
    private void onAddEntityPrivate(int id, class_1297 entity, CallbackInfo info) {
        MeteorClient.EVENT_BUS.post(EntityAddedEvent.get(entity));
    }

    @Inject(method={"finishRemovingEntity"}, at={@At(value="TAIL")})
    private void onFinishRemovingEntity(class_1297 entity, CallbackInfo info) {
        MeteorClient.EVENT_BUS.post(EntityRemovedEvent.get(entity));
    }

    @Inject(method={"method_23777"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetSkyColor(class_2338 blockPos, float tickDelta, CallbackInfoReturnable<class_243> info) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeSkyColor.get().booleanValue()) {
            info.setReturnValue((Object)ambience.skyColor.get().getVec3d());
        }
    }

    @Inject(method={"getSkyProperties"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetSkyProperties(CallbackInfoReturnable<class_5294> info) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.enderMode.get().booleanValue()) {
            info.setReturnValue((Object)(ambience.enderCustomSkyColor.get() != false ? this.customSky : this.endSky));
        }
    }

    @Inject(method={"getCloudsColor"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetCloudsColor(float tickDelta, CallbackInfoReturnable<class_243> info) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeCloudColor.get().booleanValue()) {
            info.setReturnValue((Object)ambience.cloudColor.get().getVec3d());
        }
    }

    @ModifyArgs(method={"doRandomBlockDisplayTicks"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/world/ClientWorld;randomBlockDisplayTick(IIIILjava/util/Random;ZLnet/minecraft/util/math/BlockPos$Mutable;)V"))
    private void doRandomBlockDisplayTicks(Args args) {
        if (Modules.get().get(NoRender.class).noBarrierInvis()) {
            args.set(5, (Object)true);
        }
    }
}

