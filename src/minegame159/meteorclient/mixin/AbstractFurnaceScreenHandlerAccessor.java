/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_1720;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_1720.class})
public interface AbstractFurnaceScreenHandlerAccessor {
    @Invoker(value="isSmeltable")
    public boolean isSmeltable(class_1799 var1);

    @Invoker(value="isFuel")
    public boolean isFuel(class_1799 var1);
}

