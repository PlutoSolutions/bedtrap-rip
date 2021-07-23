/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.Set;
import net.minecraft.class_2248;
import net.minecraft.class_2591;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2591.class})
public interface BlockEntityTypeAccessor {
    @Accessor
    public Set<class_2248> getBlocks();
}

