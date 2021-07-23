/*
 * Decompiled with CFR 0.151.
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
    private float modifySwing(float f) {
        HandView handView = Modules.get().get(HandView.class);
        class_1268 class_12682 = (class_1268)MoreObjects.firstNonNull((Object)Utils.mc.field_1724.field_6266, (Object)class_1268.field_5808);
        if (handView.isActive()) {
            if (class_12682 == class_1268.field_5810 && !Utils.mc.field_1724.method_6079().method_7960()) {
                return f + handView.offSwing.get().floatValue();
            }
            if (class_12682 == class_1268.field_5808 && !Utils.mc.field_1724.method_6047().method_7960()) {
                return f + handView.mainSwing.get().floatValue();
            }
        }
        return f;
    }

    @Inject(method={"renderFirstPersonItem"}, at={@At(value="TAIL", target="Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")})
    private void sex(class_742 class_7422, float f, float f2, class_1268 class_12682, float f3, class_1799 class_17992, float f4, class_4587 class_45872, class_4597 class_45972, int n, CallbackInfo callbackInfo) {
        HandView handView = Modules.get().get(HandView.class);
        if (!handView.isActive()) {
            return;
        }
        class_4493.method_21937((double)handView.scaleX.get(), (double)handView.scaleY.get(), (double)handView.scaleZ.get());
        class_4493.method_21938((double)handView.posX.get(), (double)handView.posY.get(), (double)handView.posZ.get());
        class_4493.method_21981((float)(handView.rotationY.get().floatValue() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        class_4493.method_21981((float)(-(handView.rotationX.get().floatValue() * 360.0f)), (float)0.0f, (float)1.0f, (float)0.0f);
        class_4493.method_21981((float)(handView.rotationZ.get().floatValue() * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
    }

    @Inject(method={"updateHeldItems"}, at={@At(value="HEAD")}, cancellable=true)
    public void updateHeldItems1(CallbackInfo callbackInfo) {
        if (Modules.get().isActive(OldAnimations.class)) {
            callbackInfo.cancel();
            this.field_4053 = this.field_4043;
            this.field_4051 = this.field_4052;
            class_746 class_7462 = class_310.method_1551().field_1724;
            class_1799 class_17992 = class_7462.method_6047();
            class_1799 class_17993 = class_7462.method_6079();
            if (class_1799.method_7973((class_1799)this.field_4047, (class_1799)class_17992)) {
                this.field_4047 = class_17992;
            }
            if (class_1799.method_7973((class_1799)this.field_4048, (class_1799)class_17993)) {
                this.field_4048 = class_17993;
            }
            if (class_7462.method_3144()) {
                this.field_4043 = class_3532.method_15363((float)(this.field_4043 - 0.4f), (float)0.0f, (float)1.0f);
                this.field_4052 = class_3532.method_15363((float)(this.field_4052 - 0.4f), (float)0.0f, (float)1.0f);
            } else {
                this.field_4043 += class_3532.method_15363((float)((this.field_4047 == class_17992 ? 1.0f : 0.0f) - this.field_4043), (float)-0.4f, (float)0.4f);
                this.field_4052 += class_3532.method_15363((float)((float)(this.field_4048 == class_17993 ? 1 : 0) - this.field_4052), (float)-0.4f, (float)0.4f);
            }
            if (this.field_4043 < 0.1f) {
                this.field_4047 = class_17992;
            }
            if (this.field_4052 < 0.1f) {
                this.field_4048 = class_17993;
            }
        }
    }
}

