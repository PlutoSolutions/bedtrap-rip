/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import net.minecraft.class_4184;
import net.minecraft.class_4588;
import net.minecraft.class_677;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_677.class_680.class, class_677.class_678.class})
public class FireworksSparkParticleSubMixin {
    @Inject(method={"buildGeometry"}, at={@At(value="HEAD")}, cancellable=true)
    private void buildExplosionGeometry(class_4588 class_45882, class_4184 class_41842, float f, CallbackInfo callbackInfo) {
        if (Modules.get().get(NoRender.class).noFireworkExplosions()) {
            callbackInfo.cancel();
        }
    }
}

