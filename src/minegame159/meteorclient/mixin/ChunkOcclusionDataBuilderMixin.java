/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.ChunkOcclusionEvent;
import net.minecraft.class_2338;
import net.minecraft.class_852;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_852.class})
public class ChunkOcclusionDataBuilderMixin {
    @Inject(method={"markClosed"}, at={@At(value="HEAD")}, cancellable=true)
    private void onMarkClosed(class_2338 class_23382, CallbackInfo callbackInfo) {
        ChunkOcclusionEvent chunkOcclusionEvent = MeteorClient.EVENT_BUS.post(ChunkOcclusionEvent.get());
        if (chunkOcclusionEvent.isCancelled()) {
            callbackInfo.cancel();
        }
    }
}

