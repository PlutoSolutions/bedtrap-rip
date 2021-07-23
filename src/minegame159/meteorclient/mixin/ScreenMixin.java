/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.List;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.GetTooltipEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_339;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={class_437.class})
public abstract class ScreenMixin {
    @Shadow
    public int field_22789;
    @Shadow
    public int field_22790;
    @Shadow
    @Nullable
    protected class_310 field_22787;

    @Shadow
    protected <T extends class_339> T method_25411(T t) {
        return null;
    }

    @Shadow
    public abstract void method_25393();

    @Inject(method={"renderBackground(Lnet/minecraft/client/util/math/MatrixStack;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderBackground(CallbackInfo callbackInfo) {
        if (Utils.canUpdate() && Modules.get().get(NoRender.class).noGuiBackground()) {
            callbackInfo.cancel();
        }
    }

    @ModifyArgs(method={"renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/item/ItemStack;II)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/screen/Screen;renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Ljava/util/List;II)V"))
    private void getList(Args args, class_4587 class_45872, class_1799 class_17992, int n, int n2) {
        GetTooltipEvent.Modify modify = MeteorClient.EVENT_BUS.post(GetTooltipEvent.Modify.get(class_17992, (List)args.get(1), class_45872, n, n2));
        args.set(0, (Object)modify.matrixStack);
        args.set(1, (Object)modify.list);
        args.set(2, (Object)modify.x);
        args.set(3, (Object)modify.y);
    }
}

