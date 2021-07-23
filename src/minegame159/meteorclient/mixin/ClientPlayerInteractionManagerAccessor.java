/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2338;
import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_636.class})
public interface ClientPlayerInteractionManagerAccessor {
    @Accessor(value="currentBreakingProgress")
    public float getBreakingProgress();

    @Accessor(value="currentBreakingPos")
    public class_2338 getCurrentBreakingBlockPos();
}

