/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_1665;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1665.class})
public interface ProjectileInGroundAccessor {
    @Accessor(value="inGround")
    public boolean getInGround();
}

