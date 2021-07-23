/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2708;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2708.class})
public interface PlayerPositionLookS2CPacketAccessor {
    @Accessor(value="yaw")
    public void setYaw(float var1);

    @Accessor(value="pitch")
    public void setPitch(float var1);
}

