/*
 * Decompiled with CFR 0.151.
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
    private void onRenderQuad(class_1920 class_19202, class_2680 class_26802, class_2338 class_23382, ModelQuadSinkDelegate modelQuadSinkDelegate, class_243 class_2432, class_322 class_3222, class_777 class_7772, QuadLightData quadLightData, ModelQuadFacing modelQuadFacing, CallbackInfo callbackInfo) {
        WallHack wallHack = Modules.get().get(WallHack.class);
        if (wallHack.isActive() && wallHack.blocks.get().contains(class_26802.method_26204())) {
            this.whRenderQuad(class_19202, class_26802, class_23382, modelQuadSinkDelegate, class_2432, class_3222, class_7772, quadLightData, modelQuadFacing, wallHack);
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderModel"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderModel(class_1920 class_19202, class_2680 class_26802, class_2338 class_23382, class_1087 class_10872, ModelQuadSinkDelegate modelQuadSinkDelegate, boolean bl, long l, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        Xray xray = Modules.get().get(Xray.class);
        if (xray.isActive() && xray.isBlocked(class_26802.method_26204())) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }

    private void whRenderQuad(class_1920 class_19202, class_2680 class_26802, class_2338 class_23382, ModelQuadSinkDelegate modelQuadSinkDelegate, class_243 class_2432, class_322 class_3222, class_777 class_7772, QuadLightData quadLightData, ModelQuadFacing modelQuadFacing, WallHack wallHack) {
        ModelQuadView modelQuadView = (ModelQuadView)class_7772;
        ModelQuadOrientation modelQuadOrientation = ModelQuadOrientation.orient((float[])quadLightData.br);
        ModelQuad modelQuad = this.cachedQuad;
        int n = ModelQuadUtil.getFacingNormal((class_2350)class_7772.method_3358());
        int[] nArray = null;
        if (class_7772.method_3360()) {
            nArray = this.biomeColorBlender.getColors(class_3222, class_19202, class_26802, class_23382, modelQuadView);
        }
        for (int i = 0; i < 4; ++i) {
            int n2 = modelQuadOrientation.getVertexIndex(i);
            modelQuad.setX(i, modelQuadView.getX(n2) + (float)class_2432.method_10216());
            modelQuad.setY(i, modelQuadView.getY(n2) + (float)class_2432.method_10214());
            modelQuad.setZ(i, modelQuadView.getZ(n2) + (float)class_2432.method_10215());
            int n3 = ColorABGR.mul((int)(nArray != null ? nArray[n2] : -1), (float)quadLightData.br[n2]);
            int n4 = wallHack.opacity.get();
            int n5 = ColorABGR.unpackBlue((int)n3);
            int n6 = ColorABGR.unpackGreen((int)n3);
            int n7 = ColorABGR.unpackRed((int)n3);
            modelQuad.setColor(i, ColorABGR.pack((int)n7, (int)n6, (int)n5, (int)n4));
            modelQuad.setTexU(i, modelQuadView.getTexU(n2));
            modelQuad.setTexV(i, modelQuadView.getTexV(n2));
            modelQuad.setLight(i, quadLightData.lm[n2]);
            modelQuad.setNormal(i, n);
            modelQuad.setSprite(modelQuadView.getSprite());
        }
        modelQuadSinkDelegate.get(modelQuadFacing).write((ModelQuadViewMutable)modelQuad);
    }
}

