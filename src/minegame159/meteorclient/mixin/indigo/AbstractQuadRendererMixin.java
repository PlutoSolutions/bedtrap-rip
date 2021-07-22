/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.impl.client.indigo.renderer.mesh.MutableQuadViewImpl
 *  net.fabricmc.fabric.impl.client.indigo.renderer.render.AbstractQuadRenderer
 *  net.fabricmc.fabric.impl.client.indigo.renderer.render.BlockRenderInfo
 *  net.minecraft.class_1159
 *  net.minecraft.class_1160
 *  net.minecraft.class_1921
 *  net.minecraft.class_4581
 *  net.minecraft.class_4588
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin.indigo;

import java.util.function.Function;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.WallHack;
import net.fabricmc.fabric.impl.client.indigo.renderer.mesh.MutableQuadViewImpl;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.AbstractQuadRenderer;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.BlockRenderInfo;
import net.minecraft.class_1159;
import net.minecraft.class_1160;
import net.minecraft.class_1921;
import net.minecraft.class_4581;
import net.minecraft.class_4588;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={AbstractQuadRenderer.class}, remap=false)
public abstract class AbstractQuadRendererMixin {
    @Final
    @Shadow
    protected BlockRenderInfo blockInfo;
    @Final
    @Shadow
    protected Function<class_1921, class_4588> bufferFunc;
    @Final
    @Shadow
    protected class_1160 normalVec;

    @Shadow
    public static void bufferQuad(class_4588 buff, MutableQuadViewImpl quad, class_1159 matrix, int overlay, class_4581 normalMatrix, class_1160 normalVec) {
    }

    @Shadow
    protected abstract class_1159 matrix();

    @Shadow
    protected abstract int overlay();

    @Shadow
    protected abstract class_4581 normalMatrix();

    @Inject(method={"bufferQuad(Lnet/fabricmc/fabric/impl/client/indigo/renderer/mesh/MutableQuadViewImpl;Lnet/minecraft/class_1921;)V"}, at={@At(value="HEAD")}, cancellable=true, remap=false)
    private void onBufferQuad(MutableQuadViewImpl quad, class_1921 renderLayer, CallbackInfo ci) {
        WallHack wallHack = Modules.get().get(WallHack.class);
        if (wallHack.isActive()) {
            if (wallHack.blocks.get().contains((Object)this.blockInfo.blockState.method_26204())) {
                AbstractQuadRendererMixin.whBufferQuad(this.bufferFunc.apply(renderLayer), quad, this.matrix(), this.overlay(), this.normalMatrix(), this.normalVec, wallHack);
            } else {
                AbstractQuadRendererMixin.bufferQuad(this.bufferFunc.apply(renderLayer), quad, this.matrix(), this.overlay(), this.normalMatrix(), this.normalVec);
            }
        } else {
            AbstractQuadRendererMixin.bufferQuad(this.bufferFunc.apply(renderLayer), quad, this.matrix(), this.overlay(), this.normalMatrix(), this.normalVec);
        }
        ci.cancel();
    }

    private static void whBufferQuad(class_4588 buff, MutableQuadViewImpl quad, class_1159 matrix, int overlay, class_4581 normalMatrix, class_1160 normalVec, WallHack wallHack) {
        boolean useNormals = quad.hasVertexNormals();
        if (useNormals) {
            quad.populateMissingNormals();
        } else {
            class_1160 faceNormal = quad.faceNormal();
            normalVec.method_4949(faceNormal.method_4943(), faceNormal.method_4945(), faceNormal.method_4947());
            normalVec.method_23215(normalMatrix);
        }
        for (int i = 0; i < 4; ++i) {
            buff.method_22918(matrix, quad.x(i), quad.y(i), quad.z(i));
            int color = quad.spriteColor(i, 0);
            buff.method_1336(color & 0xFF, color >> 8 & 0xFF, color >> 16 & 0xFF, wallHack.opacity.get().intValue());
            buff.method_22913(quad.spriteU(i, 0), quad.spriteV(i, 0));
            buff.method_22922(overlay);
            buff.method_22916(quad.lightmap(i));
            if (useNormals) {
                normalVec.method_4949(quad.normalX(i), quad.normalY(i), quad.normalZ(i));
                normalVec.method_23215(normalMatrix);
            }
            buff.method_22914(normalVec.method_4943(), normalVec.method_4945(), normalVec.method_4947());
            buff.method_1344();
        }
    }
}

