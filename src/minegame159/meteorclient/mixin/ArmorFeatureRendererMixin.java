/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_572
 *  net.minecraft.class_970
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_572;
import net.minecraft.class_970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_970.class})
public class ArmorFeatureRendererMixin<T extends class_1309, M extends class_572<T>, A extends class_572<T>> {
    @Inject(method={"renderArmor"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderArmor(class_4587 class_45872, class_4597 class_45972, T t, class_1304 class_13042, int n, A a, CallbackInfo callbackInfo) {
        if (t instanceof class_1657 && Modules.get().get(NoRender.class).noArmor()) {
            callbackInfo.cancel();
        }
    }
}

