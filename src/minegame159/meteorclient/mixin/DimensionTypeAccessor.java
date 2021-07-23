/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2874;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2874.class})
public interface DimensionTypeAccessor {
    @Accessor(value="OVERWORLD")
    public static class_2874 getOverworld() {
        return null;
    }
}

