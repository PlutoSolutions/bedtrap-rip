/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Fullbright;
import net.minecraft.class_1291;
import net.minecraft.class_1309;
import net.minecraft.class_746;
import net.minecraft.class_757;
import net.minecraft.class_765;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_765.class})
public class LightmapTextureManagerMixin {
    @Redirect(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z", ordinal=0))
    private boolean updateHasStatusEffectProxy(class_746 class_7462, class_1291 class_12912) {
        Fullbright fullbright = Modules.get().get(Fullbright.class);
        return Fullbright.isEnabled() && fullbright.mode.get() == Fullbright.Mode.Gamma || class_7462.method_6059(class_12912);
    }

    @Redirect(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/GameRenderer;getNightVisionStrength(Lnet/minecraft/entity/LivingEntity;F)F"))
    private float updateGetNightVisionStrengthProxy(class_1309 class_13092, float f) {
        Fullbright fullbright = Modules.get().get(Fullbright.class);
        return Fullbright.isEnabled() && fullbright.mode.get() == Fullbright.Mode.Gamma ? 1.0f : class_757.method_3174((class_1309)class_13092, (float)f);
    }
}

