/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2960;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2960.class})
public interface IdentifierAccessor {
    @Accessor
    public void setPath(String var1);
}

