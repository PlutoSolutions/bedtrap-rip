/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.class_276;
import net.minecraft.class_3191;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_761.class})
public interface WorldRendererAccessor {
    @Accessor
    public void setEntityOutlinesFramebuffer(class_276 var1);

    @Accessor(value="blockBreakingInfos")
    public Int2ObjectMap<class_3191> getBlockBreakingInfos();
}

