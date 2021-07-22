/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1764
 *  net.minecraft.class_1799
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_1764;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_1764.class})
public interface CrossbowItemAccessor {
    @Invoker(value="getSpeed")
    public static float getSpeed(class_1799 class_17992) {
        return 0.0f;
    }
}

