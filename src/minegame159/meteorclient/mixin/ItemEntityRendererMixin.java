/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1542
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_916
 *  net.minecraft.class_918
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import java.util.Random;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.render.RenderItemEntityEvent;
import net.minecraft.class_1542;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_916;
import net.minecraft.class_918;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_916.class})
public abstract class ItemEntityRendererMixin {
    @Shadow
    @Final
    private Random field_4725;
    @Shadow
    @Final
    private class_918 field_4726;

    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    private void render(class_1542 class_15422, float f, float f2, class_4587 class_45872, class_4597 class_45972, int n, CallbackInfo callbackInfo) {
        RenderItemEntityEvent renderItemEntityEvent = MeteorClient.EVENT_BUS.post(RenderItemEntityEvent.get(class_15422, f, f2, class_45872, class_45972, n, this.field_4725, this.field_4726));
        if (renderItemEntityEvent.isCancelled()) {
            callbackInfo.cancel();
        }
    }
}

