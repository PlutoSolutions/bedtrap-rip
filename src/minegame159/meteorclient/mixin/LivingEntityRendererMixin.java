/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1921
 *  net.minecraft.class_270
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_583
 *  net.minecraft.class_746
 *  net.minecraft.class_922
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Chams;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1921;
import net.minecraft.class_270;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_583;
import net.minecraft.class_746;
import net.minecraft.class_922;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={class_922.class})
public abstract class LivingEntityRendererMixin<T extends class_1309, M extends class_583<T>> {
    @Shadow
    @Nullable
    protected abstract class_1921 method_24302(T var1, boolean var2, boolean var3, boolean var4);

    @Redirect(method={"hasLabel"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/MinecraftClient;getCameraEntity()Lnet/minecraft/entity/Entity;"))
    private class_1297 hasLabelGetCameraEntityProxy(class_310 class_3102) {
        if (Modules.get().isActive(Freecam.class)) {
            return null;
        }
        return class_3102.method_1560();
    }

    @ModifyVariable(method={"render"}, ordinal=2, at=@At(value="STORE", ordinal=0))
    public float changeYaw(float f, class_1309 class_13092) {
        if (class_13092.equals((Object)Utils.mc.field_1724) && Rotations.rotationTimer < 10) {
            return Rotations.serverYaw;
        }
        return f;
    }

    @ModifyVariable(method={"render"}, ordinal=3, at=@At(value="STORE", ordinal=0))
    public float changeHeadYaw(float f, class_1309 class_13092) {
        if (class_13092.equals((Object)Utils.mc.field_1724) && Rotations.rotationTimer < 10) {
            return Rotations.serverYaw;
        }
        return f;
    }

    @ModifyVariable(method={"render"}, ordinal=5, at=@At(value="STORE", ordinal=3))
    public float changePitch(float f, class_1309 class_13092) {
        if (class_13092.equals((Object)Utils.mc.field_1724) && Rotations.rotationTimer < 10) {
            return Rotations.serverPitch;
        }
        return f;
    }

    @Redirect(method={"hasLabel"}, at=@At(value="INVOKE", target="net.minecraft.client.network.ClientPlayerEntity.getScoreboardTeam()Lnet/minecraft/scoreboard/AbstractTeam;"))
    private class_270 hasLabelClientPlayerEntityGetScoreboardTeamProxy(class_746 class_7462) {
        if (class_7462 == null) {
            return null;
        }
        return class_7462.method_5781();
    }

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void renderHead(T t, float f, float f2, class_4587 class_45872, class_4597 class_45972, int n, CallbackInfo callbackInfo) {
        Chams chams = Modules.get().get(Chams.class);
        if (chams.isActive() && chams.shouldRender((class_1297)t)) {
            GL11.glEnable((int)32823);
            GL11.glPolygonOffset((float)1.0f, (float)-1100000.0f);
        }
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void renderTail(T t, float f, float f2, class_4587 class_45872, class_4597 class_45972, int n, CallbackInfo callbackInfo) {
        Chams chams = Modules.get().get(Chams.class);
        if (chams.isActive() && chams.shouldRender((class_1297)t)) {
            GL11.glPolygonOffset((float)1.0f, (float)1100000.0f);
            GL11.glDisable((int)32823);
        }
    }

    @ModifyArgs(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V"))
    private void modifyScale(Args args, T t, float f, float f2, class_4587 class_45872, class_4597 class_45972, int n) {
        Chams chams = Modules.get().get(Chams.class);
        if (!(chams.isActive() && chams.players.get().booleanValue() && t instanceof class_1657)) {
            return;
        }
        if (chams.ignoreSelf.get().booleanValue() && t == Utils.mc.field_1724) {
            return;
        }
        args.set(0, (Object)Float.valueOf(-chams.playersScale.get().floatValue()));
        args.set(1, (Object)Float.valueOf(-chams.playersScale.get().floatValue()));
        args.set(2, (Object)Float.valueOf(chams.playersScale.get().floatValue()));
    }

    @ModifyArgs(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"))
    private void modifyColor(Args args, T t, float f, float f2, class_4587 class_45872, class_4597 class_45972, int n) {
        Chams chams = Modules.get().get(Chams.class);
        if (!(chams.isActive() && chams.players.get().booleanValue() && t instanceof class_1657)) {
            return;
        }
        if (chams.ignoreSelf.get().booleanValue() && t == Utils.mc.field_1724) {
            return;
        }
        Color color = PlayerUtils.getPlayerColor((class_1657)t, chams.playersColor.get());
        args.set(4, (Object)Float.valueOf((float)color.r / 255.0f));
        args.set(5, (Object)Float.valueOf((float)color.g / 255.0f));
        args.set(6, (Object)Float.valueOf((float)color.b / 255.0f));
        args.set(7, (Object)Float.valueOf((float)chams.playersColor.get().a / 255.0f));
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/LivingEntityRenderer;getRenderLayer(Lnet/minecraft/entity/LivingEntity;ZZZ)Lnet/minecraft/client/render/RenderLayer;"))
    private class_1921 getRenderLayer(class_922<T, M> class_9222, T t, boolean bl, boolean bl2, boolean bl3) {
        Chams chams = Modules.get().get(Chams.class);
        if (!chams.isActive() || !chams.players.get().booleanValue() || !(t instanceof class_1657) || chams.playersTexture.get().booleanValue()) {
            return this.method_24302(t, bl, bl2, bl3);
        }
        if (chams.ignoreSelf.get().booleanValue() && t == Utils.mc.field_1724) {
            return this.method_24302(t, bl, bl2, bl3);
        }
        return class_1921.method_29379((class_2960)Chams.BLANK);
    }
}

