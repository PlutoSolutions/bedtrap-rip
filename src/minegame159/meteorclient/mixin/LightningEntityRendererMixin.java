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
    private static void onSetLightningVertex(class_1159 matrix4f, class_4588 vertexConsumer, float f, float g, int i, float h, float j, float k, float l, float m, float n, float o, boolean bl, boolean bl2, boolean bl3, boolean bl4, CallbackInfo ci) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeLightningColor.get().booleanValue()) {
            Color color = ambience.lightningColor.get();
            vertexConsumer.method_22918(matrix4f, f + (bl ? o : -o), (float)(i * 16), g + (bl2 ? o : -o)).method_22915((float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, 0.3f).method_1344();
            vertexConsumer.method_22918(matrix4f, h + (bl ? n : -n), (float)((i + 1) * 16), j + (bl2 ? n : -n)).method_22915((float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, 0.3f).method_1344();
            vertexConsumer.method_22918(matrix4f, h + (bl3 ? n : -n), (float)((i + 1) * 16), j + (bl4 ? n : -n)).method_22915((float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, 0.3f).method_1344();
            vertexConsumer.method_22918(matrix4f, f + (bl3 ? o : -o), (float)(i * 16), g + (bl4 ? o : -o)).method_22915((float)color.r / 255.0f, (float)color.g / 255.0f, (float)color.b / 255.0f, 0.3f).method_1344();
            ci.cancel();
        }
    }
}

