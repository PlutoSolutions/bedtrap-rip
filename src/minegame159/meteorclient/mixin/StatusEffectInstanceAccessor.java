/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1293
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_1293;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1293.class})
public interface StatusEffectInstanceAccessor {
    @Accessor(value="duration")
    public void setDuration(int var1);

    @Accessor(value="amplifier")
    public void setAmplifier(int var1);
}

