/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1720
 *  net.minecraft.class_1799
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
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

