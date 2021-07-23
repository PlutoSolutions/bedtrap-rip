/*
 * Decompiled with CFR 0.151.
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

