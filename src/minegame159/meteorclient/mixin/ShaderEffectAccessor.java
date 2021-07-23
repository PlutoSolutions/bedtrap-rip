/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.List;
import net.minecraft.class_279;
import net.minecraft.class_283;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_279.class})
public interface ShaderEffectAccessor {
    @Accessor(value="passes")
    public List<class_283> getPasses();
}

