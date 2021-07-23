/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_746.class})
public interface ClientPlayerEntityAccessor {
    @Accessor(value="field_3922")
    public void setMountJumpStrength(float var1);
}

