/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1743
 *  net.minecraft.class_2248
 *  net.minecraft.class_3614
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package minegame159.meteorclient.mixin;

import java.util.Set;
import net.minecraft.class_1743;
import net.minecraft.class_2248;
import net.minecraft.class_3614;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1743.class})
public interface AxeItemAccessor {
    @Accessor(value="field_23139")
    public static Set<class_3614> getEffectiveMaterials() {
        return null;
    }

    @Accessor(value="EFFECTIVE_BLOCKS")
    public static Set<class_2248> getEffectiveBlocks() {
        return null;
    }
}

