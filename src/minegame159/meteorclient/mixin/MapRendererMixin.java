/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import net.minecraft.class_22;
import net.minecraft.class_330;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_330.class})
public class MapRendererMixin {
    @Inject(method={"draw"}, at={@At(value="HEAD")})
    private void onDraw(class_4587 class_45872, class_4597 class_45972, class_22 class_222, boolean bl, int n, CallbackInfo callbackInfo) {
        if (Modules.get().get(NoRender.class).noMapMarkers()) {
            class_222.field_117.clear();
        }
    }
}

