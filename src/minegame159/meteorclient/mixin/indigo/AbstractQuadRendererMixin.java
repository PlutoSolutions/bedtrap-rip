/*
 * Decompiled with CFR 0.151.
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
    public static void bufferQuad(class_4588 class_45882, MutableQuadViewImpl mutableQuadViewImpl, class_1159 class_11592, int n, class_4581 class_45812, class_1160 class_11602) {
    }

    @Shadow
    protected abstract class_1159 matrix();

    @Shadow
    protected abstract int overlay();

    @Shadow
    protected abstract class_4581 normalMatrix();

    @Inject(method={"bufferQuad(Lnet/fabricmc/fabric/impl/client/indigo/renderer/mesh/MutableQuadViewImpl;Lnet/minecraft/class_1921;)V"}, at={@At(value="HEAD")}, cancellable=true, remap=false)
    private void onBufferQuad(MutableQuadViewImpl mutableQuadViewImpl, class_1921 class_19212, CallbackInfo callbackInfo) {
        WallHack wallHack = Modules.get().get(WallHack.class);
        if (wallHack.isActive()) {
            if (wallHack.blocks.get().contains(this.blockInfo.blockState.method_26204())) {
                AbstractQuadRendererMixin.whBufferQuad(this.bufferFunc.apply(class_19212), mutableQuadViewImpl, this.matrix(), this.overlay(), this.normalMatrix(), this.normalVec, wallHack);
            } else {
                AbstractQuadRendererMixin.bufferQuad(this.bufferFunc.apply(class_19212), mutableQuadViewImpl, this.matrix(), this.overlay(), this.normalMatrix(), this.normalVec);
            }
        } else {
            AbstractQuadRendererMixin.bufferQuad(this.bufferFunc.apply(class_19212), mutableQuadViewImpl, this.matrix(), this.overlay(), this.normalMatrix(), this.normalVec);
        }
        callbackInfo.cancel();
    }

    private static void whBufferQuad(class_4588 class_45882, MutableQuadViewImpl mutableQuadViewImpl, class_1159 class_11592, int n, class_4581 class_45812, class_1160 class_11602, WallHack wallHack) {
        boolean bl = mutableQuadViewImpl.hasVertexNormals();
        if (bl) {
            mutableQuadViewImpl.populateMissingNormals();
        } else {
            class_1160 class_11603 = mutableQuadViewImpl.faceNormal();
            class_11602.method_4949(class_11603.method_4943(), class_11603.method_4945(), class_11603.method_4947());
            class_11602.method_23215(class_45812);
        }
        for (int i = 0; i < 4; ++i) {
            class_45882.method_22918(class_11592, mutableQuadViewImpl.x(i), mutableQuadViewImpl.y(i), mutableQuadViewImpl.z(i));
            int n2 = mutableQuadViewImpl.spriteColor(i, 0);
            class_45882.method_1336(n2 & 0xFF, n2 >> 8 & 0xFF, n2 >> 16 & 0xFF, wallHack.opacity.get().intValue());
            class_45882.method_22913(mutableQuadViewImpl.spriteU(i, 0), mutableQuadViewImpl.spriteV(i, 0));
            class_45882.method_22922(n);
            class_45882.method_22916(mutableQuadViewImpl.lightmap(i));
            if (bl) {
                class_11602.method_4949(mutableQuadViewImpl.normalX(i), mutableQuadViewImpl.normalY(i), mutableQuadViewImpl.normalZ(i));
                class_11602.method_23215(class_45812);
            }
            class_45882.method_22914(class_11602.method_4943(), class_11602.method_4945(), class_11602.method_4947());
            class_45882.method_1344();
        }
    }
}

