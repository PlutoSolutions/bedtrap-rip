/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2743
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2743;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2743.class})
public interface EntityVelocityUpdateS2CPacketAccessor {
    @Accessor(value="velocityX")
    public void setX(int var1);

    @Accessor(value="velocityY")
    public void setY(int var1);

    @Accessor(value="velocityZ")
    public void setZ(int var1);
}

