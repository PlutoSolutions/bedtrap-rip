/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_634;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_634.class})
public interface ClientPlayNetworkHandlerAccessor {
    @Accessor(value="chunkLoadDistance")
    public int getChunkLoadDistance();
}

