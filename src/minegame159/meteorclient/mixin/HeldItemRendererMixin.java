/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  net.minecraft.class_1268
 *  net.minecraft.class_1799
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_4493
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_742
 *  net.minecraft.class_746
 *  net.minecraft.class_759
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import com.google.common.base.MoreObjects;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.bedtrap.OldAnimations;
import minegame159.meteorclient.systems.modules.render.HandView;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4493;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_759.class})
public abstract class HeldItemRendererMixin {
    @Shadow
    private float field_4053;
    @Shadow
    private float field_4043;
    @Shadow
    private float field_4052;
    @Shadow
    private float field_4051;
    @Shadow
    private class_1799 field_4048;
    @Shadow
    private class_1799 field_4047;

    @ModifyVariable(method={"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"}, at=@At(value="STORE", ordinal=0), index=6)
    private float modifySwing(float swingProgress) {
        HandView module = Modules.get().get(HandView.class);
        class_1268 hand = (class_1268)MoreObjects.firstNonNull((Object)Utils.mc.field_1724.field_6266, (Object)class_1268.field_5808);
        if (module.isActive()) {
            if (hand == class_1268.field_5810 && !Utils.mc.field_1724.method_6079().method_7960()) {
                return swingProgress + module.offSwing.get().floatValue();
            }
            if (hand == class_1268.field_5808 && !Utils.mc.field_1724.method_6047().method_7960()) {
                return swingProgress + module.mainSwing.get().floatValue();
            }
        }
        return swingProgress;
    }

    @Inject(method={"renderFirstPersonItem"}, at={@At(value="TAIL", target="Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")})
    private void sex(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
        HandView module = Modules.get().get(HandView.class);
        if (!module.isActive()) {
            return;
        }
        class_4493.method_21937((double)module.scaleX.get(), (double)module.scaleY.get(), (double)module.scaleZ.get());
        class_4493.method_21938((double)module.posX.get(), (double)module.posY.get(), (double)module.posZ.get());
        class_4493.method_21981((float)(module.rotationY.get().floatValue() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        class_4493.method_21981((float)(-(module.rotationX.get().floatValue() * 360.0f)), (float)0.0f, (float)1.0f, (float)0.0f);
        class_4493.method_21981((float)(module.rotationZ.get().floatValue() * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
    }

    @Inject(method={"updateHeldItems"}, at={@At(value="HEAD")}, cancellable=true)
    public void updateHeldItems1(CallbackInfo ci) {
        if (Modules.get().isActive(OldAnimations.class)) {
            ci.cancel();
            this.field_4053 = this.field_4043;
            this.field_4051 = this.field_4052;
            class_746 clientPlayerEntity = class_310.method_1551().field_1724;
            class_1799 itemStack = clientPlayerEntity.method_6047();
            class_1799 itemStack2 = clientPlayerEntity.method_6079();
            if (class_1799.method_7973((class_1799)this.field_4047, (class_1799)itemStack)) {
                this.field_4047 = itemStack;
            }
            if (class_1799.method_7973((class_1799)this.field_4048, (class_1799)itemStack2)) {
                this.field_4048 = itemStack2;
            }
            if (clientPlayerEntity.method_3144()) {
                this.field_4043 = class_3532.method_15363((float)(this.field_4043 - 0.4f), (float)0.0f, (float)1.0f);
                this.field_4052 = class_3532.method_15363((float)(this.field_4052 - 0.4f), (float)0.0f, (float)1.0f);
            } else {
                this.field_4043 += class_3532.method_15363((float)((this.field_4047 == itemStack ? 1.0f : 0.0f) - this.field_4043), (float)-0.4f, (float)0.4f);
                this.field_4052 += class_3532.method_15363((float)((float)(this.field_4048 == itemStack2 ? 1 : 0) - this.field_4052), (float)-0.4f, (float)0.4f);
            }
            if (this.field_4043 < 0.1f) {
                this.field_4047 = itemStack;
            }
            if (this.field_4052 < 0.1f) {
                this.field_4048 = itemStack2;
            }
        }
    }
}

