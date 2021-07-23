/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_5225;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_5225.class})
public interface TextHandlerAccessor {
    @Accessor(value="widthRetriever")
    public class_5225.class_5231 getWidthRetriever();
}

