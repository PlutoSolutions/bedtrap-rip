/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_4970.class})
public interface AbstractBlockAccessor {
    @Accessor(value="collidable")
    public boolean isCollidable();
}

