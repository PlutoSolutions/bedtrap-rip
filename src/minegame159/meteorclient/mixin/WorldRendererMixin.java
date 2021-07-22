/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.class_1159
 *  net.minecraft.class_1297
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_276
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4604
 *  net.minecraft.class_757
 *  net.minecraft.class_761
 *  net.minecraft.class_765
 *  net.minecraft.class_898
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import javax.annotation.Nullable;
import minegame159.meteorclient.rendering.Blur;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.BlockSelection;
import minegame159.meteorclient.systems.modules.render.Chams;
import minegame159.meteorclient.systems.modules.render.ESP;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.systems.modules.render.NoRender;
import minegame159.meteorclient.systems.modules.world.Ambience;
import minegame159.meteorclient.utils.render.Outlines;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1159;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_276;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4604;
import net.minecraft.class_757;
import net.minecraft.class_761;
import net.minecraft.class_765;
import net.minecraft.class_898;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_761.class})
public abstract class WorldRendererMixin {
    @Shadow
    @Nullable
    private class_276 field_4101;

    @Shadow
    protected abstract void method_22977(class_1297 var1, double var2, double var4, double var6, float var8, class_4587 var9, class_4597 var10);

    @Inject(method={"loadEntityOutlineShader"}, at={@At(value="TAIL")})
    private void onLoadEntityOutlineShader(CallbackInfo info) {
        Outlines.load();
    }

    @Inject(method={"checkEmpty"}, at={@At(value="HEAD")}, cancellable=true)
    private void onCheckEmpty(class_4587 matrixStack, CallbackInfo info) {
        info.cancel();
    }

    @Inject(method={"renderWeather"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderWeather(class_765 manager, float f, double d, double e, double g, CallbackInfo info) {
        if (Modules.get().get(NoRender.class).noWeather()) {
            info.cancel();
        }
    }

    @Inject(method={"drawBlockOutline"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDrawHighlightedBlockOutline(class_4587 matrixStack, class_4588 vertexConsumer, class_1297 entity, double d, double e, double f, class_2338 blockPos, class_2680 blockState, CallbackInfo info) {
        if (Modules.get().isActive(BlockSelection.class)) {
            info.cancel();
        }
    }

    @ModifyArg(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/WorldRenderer;setupTerrain(Lnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/Frustum;ZIZ)V"), index=4)
    private boolean renderSetupTerrainModifyArg(boolean spectator) {
        return Modules.get().isActive(Freecam.class) || spectator;
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void onRenderTail(class_4587 matrices, float tickDelta, long limitTime, boolean renderBlockOutline, class_4184 camera, class_757 gameRenderer, class_765 lightmapTextureManager, class_1159 matrix4f, CallbackInfo info) {
        Blur.render();
    }

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void onRenderHead(class_4587 matrices, float tickDelta, long limitTime, boolean renderBlockOutline, class_4184 camera, class_757 gameRenderer, class_765 lightmapTextureManager, class_1159 matrix4f, CallbackInfo info) {
        Outlines.beginRender();
    }

    @Inject(method={"renderEntity"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderEntity(class_1297 entity, double cameraX, double cameraY, double cameraZ, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, CallbackInfo info) {
        if (vertexConsumers == Outlines.vertexConsumerProvider) {
            return;
        }
        ESP esp = Modules.get().get(ESP.class);
        Color color = esp.getOutlineColor(entity);
        if (esp.shouldDrawOutline(entity)) {
            class_276 prevBuffer = this.field_4101;
            this.field_4101 = Outlines.outlinesFbo;
            Outlines.setUniform("width", esp.outlineWidth.get().intValue());
            Outlines.setUniform("fillOpacity", esp.fillOpacity.get().floatValue() / 255.0f);
            Outlines.setUniform("shapeMode", esp.shapeMode.get().ordinal());
            Outlines.vertexConsumerProvider.method_23286(color.r, color.g, color.b, color.a);
            this.method_22977(entity, cameraX, cameraY, cameraZ, tickDelta, matrices, (class_4597)Outlines.vertexConsumerProvider);
            this.field_4101 = prevBuffer;
        }
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/OutlineVertexConsumerProvider;draw()V")})
    private void onRender(class_4587 matrices, float tickDelta, long limitTime, boolean renderBlockOutline, class_4184 camera, class_757 gameRenderer, class_765 lightmapTextureManager, class_1159 matrix4f, CallbackInfo info) {
        Outlines.endRender(tickDelta);
    }

    @Inject(method={"drawEntityOutlinesFramebuffer"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gl/Framebuffer;draw(IIZ)V")})
    private void onDrawEntityOutlinesFramebuffer(CallbackInfo info) {
        Outlines.renderFbo();
    }

    @Inject(method={"onResized"}, at={@At(value="HEAD")})
    private void onResized(int i, int j, CallbackInfo info) {
        Outlines.onResized(i, j);
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/EntityRenderDispatcher;shouldRender(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/render/Frustum;DDD)Z"))
    private <E extends class_1297> boolean shouldRenderRedirect(class_898 entityRenderDispatcher, E entity, class_4604 frustum, double x, double y, double z) {
        return Modules.get().isActive(Chams.class) || entityRenderDispatcher.method_3950(entity, frustum, x, y, z);
    }

    @Inject(method={"renderEndSky"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/Tessellator;draw()V")})
    private void onRenderEndSkyDraw(class_4587 matrices, CallbackInfo info) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.enderCustomSkyColor.get().booleanValue()) {
            Color customEndSkyColor = ambience.endSkyColor.get();
            class_289 tessellator = class_289.method_1348();
            class_287 bufferBuilder = tessellator.method_1349();
            class_1159 matrix4f = matrices.method_23760().method_23761();
            bufferBuilder.method_1343();
            bufferBuilder.method_22918(matrix4f, -100.0f, -100.0f, -100.0f).method_22913(0.0f, 0.0f).method_1336(customEndSkyColor.r, customEndSkyColor.g, customEndSkyColor.b, 255).method_1344();
            bufferBuilder.method_22918(matrix4f, -100.0f, -100.0f, 100.0f).method_22913(0.0f, 16.0f).method_1336(customEndSkyColor.r, customEndSkyColor.g, customEndSkyColor.b, 255).method_1344();
            bufferBuilder.method_22918(matrix4f, 100.0f, -100.0f, 100.0f).method_22913(16.0f, 16.0f).method_1336(customEndSkyColor.r, customEndSkyColor.g, customEndSkyColor.b, 255).method_1344();
            bufferBuilder.method_22918(matrix4f, 100.0f, -100.0f, -100.0f).method_22913(16.0f, 0.0f).method_1336(customEndSkyColor.r, customEndSkyColor.g, customEndSkyColor.b, 255).method_1344();
        }
    }
}

