/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_4184
 *  net.minecraft.class_4493$class_1028
 *  net.minecraft.class_758
 *  net.minecraft.class_758$class_4596
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import minegame159.meteorclient.systems.modules.render.Xray;
import net.minecraft.class_4184;
import net.minecraft.class_4493;
import net.minecraft.class_758;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_758.class})
public class BackgroundRendererMixin {
    @Inject(method={"applyFog"}, at={@At(value="TAIL")}, cancellable=true)
    private static void onApplyFog(class_4184 class_41842, class_758.class_4596 class_45962, float f, boolean bl, CallbackInfo callbackInfo) {
        if ((Modules.get().get(NoRender.class).noFog() || Modules.get().isActive(Xray.class)) && class_45962 == class_758.class_4596.field_20946) {
            RenderSystem.fogStart((float)(f * 4.0f));
            RenderSystem.fogEnd((float)(f * 4.25f));
            RenderSystem.fogMode((class_4493.class_1028)class_4493.class_1028.field_5095);
            RenderSystem.setupNvFogDistance();
        }
    }
}

