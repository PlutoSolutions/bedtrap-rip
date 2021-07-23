/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1799.class})
public interface ItemStackAccessor {
    @Accessor(value="item")
    public void setItem(class_1792 var1);

    @Accessor(value="empty")
    public void setEmpty(boolean var1);
}

