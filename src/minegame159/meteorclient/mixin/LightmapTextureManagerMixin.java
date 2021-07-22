/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1291
 *  net.minecraft.class_1309
 *  net.minecraft.class_746
 *  net.minecraft.class_757
 *  net.minecraft.class_765
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
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
    private boolean updateHasStatusEffectProxy(class_746 player, class_1291 effect) {
        Fullbright fullbright = Modules.get().get(Fullbright.class);
        return Fullbright.isEnabled() && fullbright.mode.get() == Fullbright.Mode.Gamma || player.method_6059(effect);
    }

    @Redirect(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/GameRenderer;getNightVisionStrength(Lnet/minecraft/entity/LivingEntity;F)F"))
    private float updateGetNightVisionStrengthProxy(class_1309 entity, float delta) {
        Fullbright fullbright = Modules.get().get(Fullbright.class);
        return Fullbright.isEnabled() && fullbright.mode.get() == Fullbright.Mode.Gamma ? 1.0f : class_757.method_3174((class_1309)entity, (float)delta);
    }
}

