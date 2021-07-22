/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  me.jellysquid.mods.sodium.client.model.light.LightPipeline
 *  me.jellysquid.mods.sodium.client.model.quad.ModelQuadView
 *  me.jellysquid.mods.sodium.client.render.pipeline.FluidRenderer
 *  me.jellysquid.mods.sodium.client.util.color.ColorARGB
 *  net.minecraft.class_1920
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin.sodium;

import java.util.Arrays;
import me.jellysquid.mods.sodium.client.model.light.LightPipeline;
import me.jellysquid.mods.sodium.client.model.quad.ModelQuadView;
import me.jellysquid.mods.sodium.client.render.pipeline.FluidRenderer;
import me.jellysquid.mods.sodium.client.util.color.ColorARGB;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.Ambience;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={FluidRenderer.class}, remap=false)
public class SodiumFluidRendererMixin {
    @Final
    @Shadow(remap=false)
    private int[] quadColors;

    @Inject(method={"calculateQuadColors"}, at={@At(value="TAIL")}, cancellable=true, remap=false)
    private void onCalculateQuadColors(ModelQuadView modelQuadView, class_1920 class_19202, class_2338 class_23382, LightPipeline lightPipeline, class_2350 class_23502, float f, boolean bl, CallbackInfo callbackInfo) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeLavaColor.get().booleanValue() && !bl) {
            Arrays.fill(this.quadColors, ColorARGB.toABGR((int)ambience.lavaColor.get().getPacked()));
        }
    }
}

