/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1920
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_776
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.BreakIndicators;
import minegame159.meteorclient.systems.modules.render.NoRender;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_776;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_776.class})
public class BlockRenderManagerMixin {
    @Inject(method={"renderDamage"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderDamage(class_2680 class_26802, class_2338 class_23382, class_1920 class_19202, class_4587 class_45872, class_4588 class_45882, CallbackInfo callbackInfo) {
        if (Modules.get().isActive(BreakIndicators.class) || Modules.get().get(NoRender.class).noBlockBreakOverlay()) {
            callbackInfo.cancel();
        }
    }
}

