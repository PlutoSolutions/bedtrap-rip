/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2828;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2828.class})
public interface PlayerMoveC2SPacketAccessor {
    @Accessor(value="y")
    public void setY(double var1);

    @Accessor(value="onGround")
    public void setOnGround(boolean var1);
}

