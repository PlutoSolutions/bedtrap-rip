/*
 * Decompiled with CFR 0.151.
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
    private void onLoadEntityOutlineShader(CallbackInfo callbackInfo) {
        Outlines.load();
    }

    @Inject(method={"checkEmpty"}, at={@At(value="HEAD")}, cancellable=true)
    private void onCheckEmpty(class_4587 class_45872, CallbackInfo callbackInfo) {
        callbackInfo.cancel();
    }

    @Inject(method={"renderWeather"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderWeather(class_765 class_7652, float f, double d, double d2, double d3, CallbackInfo callbackInfo) {
        if (Modules.get().get(NoRender.class).noWeather()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"drawBlockOutline"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDrawHighlightedBlockOutline(class_4587 class_45872, class_4588 class_45882, class_1297 class_12972, double d, double d2, double d3, class_2338 class_23382, class_2680 class_26802, CallbackInfo callbackInfo) {
        if (Modules.get().isActive(BlockSelection.class)) {
            callbackInfo.cancel();
        }
    }

    @ModifyArg(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/WorldRenderer;setupTerrain(Lnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/Frustum;ZIZ)V"), index=4)
    private boolean renderSetupTerrainModifyArg(boolean bl) {
        return Modules.get().isActive(Freecam.class) || bl;
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void onRenderTail(class_4587 class_45872, float f, long l, boolean bl, class_4184 class_41842, class_757 class_7572, class_765 class_7652, class_1159 class_11592, CallbackInfo callbackInfo) {
        Blur.render();
    }

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void onRenderHead(class_4587 class_45872, float f, long l, boolean bl, class_4184 class_41842, class_757 class_7572, class_765 class_7652, class_1159 class_11592, CallbackInfo callbackInfo) {
        Outlines.beginRender();
    }

    @Inject(method={"renderEntity"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderEntity(class_1297 class_12972, double d, double d2, double d3, float f, class_4587 class_45872, class_4597 class_45972, CallbackInfo callbackInfo) {
        if (class_45972 == Outlines.vertexConsumerProvider) {
            return;
        }
        ESP eSP = Modules.get().get(ESP.class);
        Color color = eSP.getOutlineColor(class_12972);
        if (eSP.shouldDrawOutline(class_12972)) {
            class_276 class_2763 = this.field_4101;
            this.field_4101 = Outlines.outlinesFbo;
            Outlines.setUniform("width", eSP.outlineWidth.get().intValue());
            Outlines.setUniform("fillOpacity", eSP.fillOpacity.get().floatValue() / 255.0f);
            Outlines.setUniform("shapeMode", eSP.shapeMode.get().ordinal());
            Outlines.vertexConsumerProvider.method_23286(color.r, color.g, color.b, color.a);
            this.method_22977(class_12972, d, d2, d3, f, class_45872, (class_4597)Outlines.vertexConsumerProvider);
            this.field_4101 = class_2763;
        }
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/OutlineVertexConsumerProvider;draw()V")})
    private void onRender(class_4587 class_45872, float f, long l, boolean bl, class_4184 class_41842, class_757 class_7572, class_765 class_7652, class_1159 class_11592, CallbackInfo callbackInfo) {
        Outlines.endRender(f);
    }

    @Inject(method={"drawEntityOutlinesFramebuffer"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gl/Framebuffer;draw(IIZ)V")})
    private void onDrawEntityOutlinesFramebuffer(CallbackInfo callbackInfo) {
        Outlines.renderFbo();
    }

    @Inject(method={"onResized"}, at={@At(value="HEAD")})
    private void onResized(int n, int n2, CallbackInfo callbackInfo) {
        Outlines.onResized(n, n2);
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/EntityRenderDispatcher;shouldRender(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/render/Frustum;DDD)Z"))
    private <E extends class_1297> boolean shouldRenderRedirect(class_898 class_8982, E e, class_4604 class_46042, double d, double d2, double d3) {
        return Modules.get().isActive(Chams.class) || class_8982.method_3950(e, class_46042, d, d2, d3);
    }

    @Inject(method={"renderEndSky"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/Tessellator;draw()V")})
    private void onRenderEndSkyDraw(class_4587 class_45872, CallbackInfo callbackInfo) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.enderCustomSkyColor.get().booleanValue()) {
            Color color = ambience.endSkyColor.get();
            class_289 class_2892 = class_289.method_1348();
            class_287 class_2872 = class_2892.method_1349();
            class_1159 class_11592 = class_45872.method_23760().method_23761();
            class_2872.method_1343();
            class_2872.method_22918(class_11592, -100.0f, -100.0f, -100.0f).method_22913(0.0f, 0.0f).method_1336(color.r, color.g, color.b, 255).method_1344();
            class_2872.method_22918(class_11592, -100.0f, -100.0f, 100.0f).method_22913(0.0f, 16.0f).method_1336(color.r, color.g, color.b, 255).method_1344();
            class_2872.method_22918(class_11592, 100.0f, -100.0f, 100.0f).method_22913(16.0f, 16.0f).method_1336(color.r, color.g, color.b, 255).method_1344();
            class_2872.method_22918(class_11592, 100.0f, -100.0f, -100.0f).method_22913(16.0f, 0.0f).method_1336(color.r, color.g, color.b, 255).method_1344();
        }
    }
}

