/*
 * Decompiled with CFR 0.151.
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

