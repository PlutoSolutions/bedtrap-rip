/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_22
 *  net.minecraft.class_330
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import net.minecraft.class_22;
import net.minecraft.class_330;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_330.class})
public class MapRendererMixin {
    @Inject(method={"draw"}, at={@At(value="HEAD")})
    private void onDraw(class_4587 matrices, class_4597 vertexConsumers, class_22 mapState, boolean bl, int light, CallbackInfo info) {
        if (Modules.get().get(NoRender.class).noMapMarkers()) {
            mapState.field_117.clear();
        }
    }
}

