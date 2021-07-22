/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1496
 *  net.minecraft.class_1724
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_1496;
import net.minecraft.class_1724;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1724.class})
public interface HorseScreenHandlerAccessor {
    @Accessor(value="entity")
    public class_1496 getEntity();
}

