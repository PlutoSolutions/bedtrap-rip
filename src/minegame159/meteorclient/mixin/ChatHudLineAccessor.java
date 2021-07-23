/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_303;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_303.class})
public interface ChatHudLineAccessor<T> {
    @Accessor(value="creationTick")
    public void setTimestamp(int var1);

    @Accessor(value="text")
    public void setText(T var1);

    @Accessor(value="id")
    public void setId(int var1);
}

