/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  me.jellysquid.mods.sodium.client.model.light.data.QuadLightData
 *  me.jellysquid.mods.sodium.client.model.quad.ModelQuad
 *  me.jellysquid.mods.sodium.client.model.quad.ModelQuadView
 *  me.jellysquid.mods.sodium.client.model.quad.ModelQuadViewMutable
 *  me.jellysquid.mods.sodium.client.model.quad.blender.BiomeColorBlender
 *  me.jellysquid.mods.sodium.client.model.quad.properties.ModelQuadFacing
 *  me.jellysquid.mods.sodium.client.model.quad.properties.ModelQuadOrientation
 *  me.jellysquid.mods.sodium.client.model.quad.sink.ModelQuadSinkDelegate
 *  me.jellysquid.mods.sodium.client.render.pipeline.BlockRenderer
 *  me.jellysquid.mods.sodium.client.util.ModelQuadUtil
 *  me.jellysquid.mods.sodium.client.util.color.ColorABGR
 *  net.minecraft.class_1087
 *  net.minecraft.class_1920
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_322
 *  net.minecraft.class_777
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin.sodium;

import me.jellysquid.mods.sodium.client.model.light.data.QuadLightData;
import me.jellysquid.mods.sodium.client.model.quad.ModelQuad;
import me.jellysquid.mods.sodium.client.model.quad.ModelQuadView;
import me.jellysquid.mods.sodium.client.model.quad.ModelQuadViewMutable;
import me.jellysquid.mods.sodium.client.model.quad.blender.BiomeColorBlender;
import me.jellysquid.mods.sodium.client.model.quad.properties.ModelQuadFacing;
import me.jellysquid.mods.sodium.client.model.quad.properties.ModelQuadOrientation;
import me.jellysquid.mods.sodium.client.model.quad.sink.ModelQuadSinkDelegate;
import me.jellysquid.mods.sodium.client.render.pipeline.BlockRenderer;
import me.jellysquid.mods.sodium.client.util.ModelQuadUtil;
import me.jellysquid.mods.sodium.client.util.color.ColorABGR;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.WallHack;
import minegame159.meteorclient.systems.modules.render.Xray;
import net.minecraft.class_1087;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_322;
import net.minecraft.class_777;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BlockRenderer.class}, remap=false)
public class SodiumBlockRendererMixin {
    @Shadow
    @Final
    private ModelQuad cachedQuad;
    @Shadow
    @Final
    private BiomeColorBlender biomeColorBlender;

    @Inject(method={"renderQuad"}, at={@At(value="INVOKE", target="Lme/jellysquid/mods/sodium/client/model/quad/ModelQuadViewMutable;setColor(II)V", shift=At.Shift.AFTER)}, cancellable=true)
    private void onRenderQuad(class_1920 world, class_2680 state, class_2338 pos, ModelQuadSinkDelegate consumer, class_243 offset, class_322 colorProvider, class_777 bakedQuad, QuadLightData light, ModelQuadFacing facing, CallbackInfo ci) {
        WallHack wallHack = Modules.get().get(WallHack.class);
        if (wallHack.isActive() && wallHack.blocks.get().contains((Object)state.method_26204())) {
            this.whRenderQuad(world, state, pos, consumer, offset, colorProvider, bakedQuad, light, facing, wallHack);
            ci.cancel();
        }
    }

    @Inject(method={"renderModel"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderModel(class_1920 world, class_2680 state, class_2338 pos, class_1087 model, ModelQuadSinkDelegate builder, boolean cull, long seed, CallbackInfoReturnable<Boolean> cir) {
        Xray xray = Modules.get().get(Xray.class);
        if (xray.isActive() && xray.isBlocked(state.method_26204())) {
            cir.setReturnValue((Object)false);
        }
    }

    private void whRenderQuad(class_1920 world, class_2680 state, class_2338 pos, ModelQuadSinkDelegate consumer, class_243 offset, class_322 colorProvider, class_777 bakedQuad, QuadLightData light, ModelQuadFacing facing, WallHack wallHack) {
        ModelQuadView src = (ModelQuadView)bakedQuad;
        ModelQuadOrientation order = ModelQuadOrientation.orient((float[])light.br);
        ModelQuad copy = this.cachedQuad;
        int norm = ModelQuadUtil.getFacingNormal((class_2350)bakedQuad.method_3358());
        int[] colors = null;
        if (bakedQuad.method_3360()) {
            colors = this.biomeColorBlender.getColors(colorProvider, world, state, pos, src);
        }
        for (int dstIndex = 0; dstIndex < 4; ++dstIndex) {
            int srcIndex = order.getVertexIndex(dstIndex);
            copy.setX(dstIndex, src.getX(srcIndex) + (float)offset.method_10216());
            copy.setY(dstIndex, src.getY(srcIndex) + (float)offset.method_10214());
            copy.setZ(dstIndex, src.getZ(srcIndex) + (float)offset.method_10215());
            int newColor = ColorABGR.mul((int)(colors != null ? colors[srcIndex] : -1), (float)light.br[srcIndex]);
            int alpha = wallHack.opacity.get();
            int blue = ColorABGR.unpackBlue((int)newColor);
            int green = ColorABGR.unpackGreen((int)newColor);
            int red = ColorABGR.unpackRed((int)newColor);
            copy.setColor(dstIndex, ColorABGR.pack((int)red, (int)green, (int)blue, (int)alpha));
            copy.setTexU(dstIndex, src.getTexU(srcIndex));
            copy.setTexV(dstIndex, src.getTexV(srcIndex));
            copy.setLight(dstIndex, light.lm[srcIndex]);
            copy.setNormal(dstIndex, norm);
            copy.setSprite(src.getSprite());
        }
        consumer.get(facing).write((ModelQuadViewMutable)copy);
    }
}

