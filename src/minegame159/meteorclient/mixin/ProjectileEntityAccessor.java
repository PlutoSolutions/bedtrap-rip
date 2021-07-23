/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.UUID;
import net.minecraft.class_1676;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1676.class})
public interface ProjectileEntityAccessor {
    @Accessor
    public UUID getOwnerUuid();
}

