/*
 * Decompiled with CFR 0.151.
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

