/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2350;
import net.minecraft.class_3965;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_3965.class})
public interface BlockHitResultAccessor {
    @Accessor(value="side")
    public void setSide(class_2350 var1);
}

