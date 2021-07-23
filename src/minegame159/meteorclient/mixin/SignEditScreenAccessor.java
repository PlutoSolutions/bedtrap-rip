/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2625;
import net.minecraft.class_498;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_498.class})
public interface SignEditScreenAccessor {
    @Accessor(value="sign")
    public class_2625 getSign();
}

