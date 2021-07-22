/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_266
 *  net.minecraft.class_310
 *  net.minecraft.class_329
 *  net.minecraft.class_4493$class_4534
 *  net.minecraft.class_4493$class_4535
 *  net.minecraft.class_4587
 *  org.lwjgl.opengl.GL11
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.render.Render2DEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1297;
import net.minecraft.class_266;
import net.minecraft.class_310;
import net.minecraft.class_329;
import net.minecraft.class_4493;
import net.minecraft.class_4587;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_329.class})
public abstract class InGameHudMixin {
    @Shadow
    private int field_2011;
    @Shadow
    private int field_2029;
    @Shadow
    @Final
    private class_310 field_2035;

    @Shadow
    public abstract void method_1747();

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/player/PlayerInventory;getArmorStack(I)Lnet/minecraft/item/ItemStack;")})
    private void onRender(class_4587 class_45872, float f, CallbackInfo callbackInfo) {
        this.field_2035.method_16011().method_15396("meteor-client_render_2d");
        RenderSystem.pushMatrix();
        Utils.unscaledProjection();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((class_4493.class_4535)class_4493.class_4535.field_22541, (class_4493.class_4534)class_4493.class_4534.field_22523);
        RenderSystem.lineWidth((float)1.0f);
        GL11.glEnable((int)2848);
        MeteorClient.EVENT_BUS.post(Render2DEvent.get(this.field_2011, this.field_2029, f));
        GL11.glDisable((int)2848);
        RenderSystem.lineWidth((float)1.0f);
        Utils.scaledProjection();
        RenderSystem.popMatrix();
        this.field_2035.method_16011().method_15407();
    }

    @Inject(method={"renderStatusEffectOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderStatusEffectOverlay(CallbackInfo callbackInfo) {
        if (Modules.get().get(NoRender.class).noPotionIcons()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderPortalOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderPortalOverlay(float f, CallbackInfo callbackInfo) {
        if (Modules.get().get(NoRender.class).noPortalOverlay()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderPumpkinOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderPumpkinOverlay(CallbackInfo callbackInfo) {
        if (Modules.get().get(NoRender.class).noPumpkinOverlay()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderVignetteOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderVignetteOverlay(class_1297 class_12972, CallbackInfo callbackInfo) {
        if (Modules.get().get(NoRender.class).noVignette()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderScoreboardSidebar"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderScoreboardSidebar(class_4587 class_45872, class_266 class_2662, CallbackInfo callbackInfo) {
        if (Modules.get().get(NoRender.class).noScoreboard()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderCrosshair"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderCrosshair(class_4587 class_45872, CallbackInfo callbackInfo) {
        if (Modules.get().get(NoRender.class).noCrosshair()) {
            callbackInfo.cancel();
        }
    }
}

