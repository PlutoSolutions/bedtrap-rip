/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.Set;
import net.minecraft.class_1794;
import net.minecraft.class_2248;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1794.class})
public interface HoeItemAccessor {
    @Accessor(value="EFFECTIVE_BLOCKS")
    public static Set<class_2248> getEffectiveBlocks() {
        return null;
    }
}

