/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1540
 *  net.minecraft.class_1657
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4604
 *  net.minecraft.class_897
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixininterface.IEntityRenderer;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Nametags;
import minegame159.meteorclient.systems.modules.render.NoRender;
import net.minecraft.class_1297;
import net.minecraft.class_1540;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4604;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_897.class})
public abstract class EntityRendererMixin<T extends class_1297>
implements IEntityRenderer {
    @Shadow
    public abstract class_2960 method_3931(class_1297 var1);

    @Inject(method={"renderLabelIfPresent"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderLabel(T t, class_2561 class_25612, class_4587 class_45872, class_4597 class_45972, int n, CallbackInfo callbackInfo) {
        if (!(t instanceof class_1657)) {
            return;
        }
        if (Modules.get().isActive(Nametags.class)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"shouldRender"}, at={@At(value="HEAD")}, cancellable=true)
    private void shouldRender(T t, class_4604 class_46042, double d, double d2, double d3, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (Modules.get().get(NoRender.class).noEntity((class_1297)t)) {
            callbackInfoReturnable.cancel();
        }
        if (Modules.get().get(NoRender.class).noFallingBlocks() && t instanceof class_1540) {
            callbackInfoReturnable.cancel();
        }
    }

    @Override
    public class_2960 getTextureInterface(class_1297 class_12972) {
        return this.method_3931(class_12972);
    }
}

