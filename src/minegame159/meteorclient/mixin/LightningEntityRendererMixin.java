/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1159
 *  net.minecraft.class_4588
 *  net.minecraft.class_919
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.Ambience;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1159;
import net.minecraft.class_4588;
import net.minecraft.class_919;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_919.class})
public class LightningEntityRendererMixin {
    @Inject(method={"method_23183"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onSetLightningVertex(class_1159 class_11592, class_4588 class_45882, float f, float f2, int n, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean bl, boolean bl2, boolean bl3, boolean bl4, CallbackInfo callbackInfo) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeLightningColor.get().booleanValue()) {
            Color color = ambience.lightningColor.get();
            class_45882.method_22918(class_11592, f + (bl ? f9 : -f9), (float)(n * 16), f2 + (bl2 ? f9 : -f9)).method_22915((float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, 0.3f).method_1344();
            class_45882.method_22918(class_11592, f3 + (bl ? f8 : -f8), (float)((n + 1) * 16), f4 + (bl2 ? f8 : -f8)).method_22915((float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, 0.3f).method_1344();
            class_45882.method_22918(class_11592, f3 + (bl3 ? f8 : -f8), (float)((n + 1) * 16), f4 + (bl4 ? f8 : -f8)).method_22915((float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, 0.3f).method_1344();
            class_45882.method_22918(class_11592, f + (bl3 ? f9 : -f9), (float)(n * 16), f2 + (bl4 ? f9 : -f9)).method_22915((float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, 0.3f).method_1344();
            callbackInfo.cancel();
        }
    }
}

